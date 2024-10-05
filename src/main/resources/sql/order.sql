CREATE TABLE `order` (
  `merchant_uid` varchar(100) NOT NULL COMMENT '결제번호',
  `userId` varchar(100) NOT NULL COMMENT '아이디',
  `name` varchar(150) NOT NULL COMMENT '상품명',
  `amount` int(11) NOT NULL COMMENT '결제가격',
  `delivery_price` int(1) NOT NULL COMMENT '배송비',
  `delivery_name` varchar(100) NOT NULL COMMENT '수신자명',
  `delivery_tel` varchar(100) NOT NULL COMMENT '수신자연락처',
  `delivery_postcode` varchar(100) NOT NULL COMMENT '수신자우편번호',
  `delivery_addr` varchar(255) NOT NULL COMMENT '수신자주소',
  `delivery_message` varchar(255) DEFAULT NULL COMMENT '배송메세지',
  `imp_uid` varchar(100) NOT NULL COMMENT 'import 결제번호',
  `paid_at` varchar(100) NOT NULL COMMENT '결제일시',
  `receipt_url` varchar(255) DEFAULT NULL COMMENT '결제전표URL',
  PRIMARY KEY (`merchant_uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO plipop.`order` (merchant_uid,userId,name,amount,delivery_price,delivery_name,delivery_tel,delivery_postcode,delivery_addr,delivery_message,imp_uid,paid_at,receipt_url) VALUES
	 ('merchant_1639720962069','unhot2','[wool] 울 캐시 세미오버 싱글코트OT 외 1건',448000,0,'최윤규','01082883640','02113','서울 중랑구 중랑천로 146-4백운빌 502호','','imp_279966219162','1639720980','https://mockup-pg-web.kakao.com/v1/confirmation/p/T2976706825274493637/af1dd9bb94976e35664fccb0ae8c9803f6f257f4c89849a0b4b45b5241065fdd'),
	 ('merchant_1639725951631','unhot2','Monster 야상 패딩파카OT 외 1건',231000,0,'최윤규','01082883640','02113','서울 중랑구 중랑천로 146-4백운빌 502호','문앞에 놔주세요','imp_842952298582','1639725965','https://mockup-pg-web.kakao.com/v1/confirmation/p/T2976728239981710273/4d7c34d60f052ec104d6bcf6880aa08249ae54358a52705e8e007000cf5dd154'),
	 ('merchant_1640239898788','unhot2','배런 라운드 오버 가디건 외 5건',498000,0,'최윤규','01082883640','02113','서울 중랑구 중랑천로 146-4백운빌 502호','문앞에 놔주세요','imp_359899971184','1640239918','https://mockup-pg-web.kakao.com/v1/confirmation/p/T2978935629833618851/cac17df67d35aa4b057ba713e0d9f1439f2a71beb993adbd5d7a9c02eeedfde5'),
	 ('merchant_1640240119987','unhot2','터치미 오버양털 반집업아노락OT 1건',168000,0,'최윤규','01082883640','02113','서울 중랑구 중랑천로 146-4백운빌 502호','ㅈㅂㅈㅂㄷ','imp_769120577240','1640240134','https://mockup-pg-web.kakao.com/v1/confirmation/p/T2978936574726363879/4625c204931075cc16b3bd5d3db0e3512522251f12dd973a1ff184ddb673e4af');