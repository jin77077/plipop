package com.yg.portfolio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.yg.portfolio.oauth.PrincipalOauth2UserService;
import com.yg.portfolio.service.UserService;


@Configuration //이 클래스를 통해 bean 등록이나 각종 설정을 하겠다는 표시
@EnableWebSecurity // Spring Security 설정할 클래스라고 정의합니다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	// OAuth 로그인 Service
	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;
	// 로그인 실패
	@Autowired
	private AuthFailureHandler authFailureHandler;
	@Autowired
	private UserService userServcie;
	
	
	@Bean //회원가입시 비번 암호화에 필요한 bean 등록
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
	    DaoAuthenticationProvider bean = new DaoAuthenticationProvider();
	    bean.setHideUserNotFoundExceptions(false);
	    bean.setUserDetailsService(userServcie);
	    bean.setPasswordEncoder(passwordEncoder());
	    
	    return bean;
	}
	 
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.authenticationProvider(this.daoAuthenticationProvider());
	}

	
	@Override
    public void configure(WebSecurity web) throws Exception {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //csrf 비활성화
		http.authorizeRequests() 
			// 회원정보, QNA글열람, 장바구니 설정(로그인 되었을때만 가능)
			.antMatchers("/members/**","/community/qna/{boardNum}", "/cart/*").authenticated()
			.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")// 매니저권한 필요
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")// 관리자권한 필요
			.anyRequest().permitAll() // 그 외의 주소는 아무나 접근가능
		.and()
			.formLogin()
			.loginPage("/users/loginForm") //로그인 폼
			.usernameParameter("userId") // 로그인시 사용되는 파라매터 수정 username -> userId
			.loginProcessingUrl("/login") // LOGIN 주소가 호출되면 시큐리티가 낚아채서 대신 로그인을 진행 ( 컨트롤러에 /login에 대한 메소드 안만들어도 됨 )
			.defaultSuccessUrl("/users/loginSucess", true) /*로그인 성공시 url*/
			.failureHandler(authFailureHandler)
		.and() // 로그아웃 설정
	        .logout()
	        .logoutSuccessUrl("/") // 로그아웃 성공 후 이동할 URL
			.invalidateHttpSession(true) // "/logout"으로 로그아웃시 세션값 삭제
		.and()
			// Oauth 로그인 프로세스 => 1.코드받기, 2.엑세스토큰, 3.사용자 프포필정보를 가져옴
			// 4-1.그 정보를 토대로 자동 회원가입을 진행 or 4-2. 정보가 부족시 추가정보 입력후 회원가입 시킴
			.oauth2Login()
			.loginPage("/loginForm")
			.defaultSuccessUrl("/users/loginSucess", true)
			// 구글 로그인이 완료된 뒤의 후처리 필요함 -> principalOauth2UserService에서 후처리 
			.userInfoEndpoint()
			.userService(principalOauth2UserService);
		    // 구글로그인 완료 시 코드X, 엑세스토큰+사용자프포필정보를 한번에 받음
	}

}