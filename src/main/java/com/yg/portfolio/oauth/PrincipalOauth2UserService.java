package com.yg.portfolio.oauth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.yg.portfolio.model.PrincipalDetails;
import com.yg.portfolio.model.User;
import com.yg.portfolio.oauth.provider.FacebookUserInfo;
import com.yg.portfolio.oauth.provider.GoogleUserInfo;
import com.yg.portfolio.oauth.provider.NaverUserInfo;
import com.yg.portfolio.oauth.provider.OAuth2UserInfo;
import com.yg.portfolio.repository.UserRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository;

	// 구글로부터 받은 userRequest 데이터에 대한 후처리 되는 함수
	// userRequest.getClientRegistration() = clientId, clienSecret, scope,
	// redirectUrl 등 정보
	// userRequest.getAccessToken() = 엑세스토큰 값
	// super.loadUser(userRequest).getAttributes() = sub(구글로그인 primary key), name,
	// given_name, email 등의 정보
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oauth2User = super.loadUser(userRequest);

		// 인터페이스를 통해서 구글,네이버,페이스북마다 각각 다른 값을 하나의 oAuthUserInfo에 담기위한 설정
		OAuth2UserInfo oAuth2UserInfo = null;
		if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
		} else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
			oAuth2UserInfo = new FacebookUserInfo(oauth2User.getAttributes());

		} else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			oAuth2UserInfo = new NaverUserInfo((Map) oauth2User.getAttributes().get("response"));
		}

		// 구글로그인버튼 클릭 -> 구글로그인창 -> 로그인 -> code리턴(OauthCline라이브러리가 받음) -> AccessToken 요청
		// : 여기까지가 userRequest정보
		// userRequest정보를 loadUser함수에 사용하여 회원프로필 정보를 받을 수 있음
		User userEntity = userRepository.findByUserId(oAuth2UserInfo.getUserId()); // userId이 있는지 찾음
		if (userEntity == null) {
			userEntity = new User();
			userEntity.setUserId(oAuth2UserInfo.getUserId());
			userEntity.setUserName(oAuth2UserInfo.getName());
			userEntity.setPassword(bCryptPasswordEncoder.encode("plipop"));
			String test = oAuth2UserInfo.getEmail();
			String [] email = test.split("@");
			userEntity.setEmail(email[0]);
			userEntity.setEmailProvider(email[1]);
			userEntity.setRole("ROLE_USER");
			userEntity.setEmailReceiveYn("on"); // 이메일 수신여부 기본값 Y로 설정
			userEntity.setSmsReceiveYn("on"); // 문자 수신여부 기본값 Y로 설정
			userEntity.setProvider(oAuth2UserInfo.getProvider());
			userEntity.setProviderId(oAuth2UserInfo.getProviderId());
			userRepository.oAuthJoin(userEntity);
		}
		return new PrincipalDetails(userEntity, oauth2User.getAttributes());
	}
}
