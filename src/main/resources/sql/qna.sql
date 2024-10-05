CREATE TABLE `qna` (
  `boardNum` int(11) NOT NULL AUTO_INCREMENT,
  `groupNo` int(11) NOT NULL DEFAULT 0,
  `groupStep` int(11) NOT NULL DEFAULT 0,
  `writeDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `userId` varchar(100) NOT NULL,
  `userName` varchar(100) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `count` int(11) DEFAULT 0,
  `password` varchar(4) DEFAULT NULL,
  `chkSecret` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`boardNum`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;

INSERT INTO plipop.qna (groupNo,groupStep,writeDate,userId,userName,title,content,count,password,chkSecret) VALUES
	 (1,1,'2022-01-27 18:18:40.0','unhot2','최윤규','배송문의 드립니다.','<p>얼마전에 바지를 주문했는데 3일이 지나도 안내문자가 안와서요 언제쯤 오는지 알 수 있을까요?</p>',3,'1234','secret'),
	 (2,1,'2022-01-27 18:20:00.0','cjn3646','최지나','배송시간 문의','<p>배송 주문한지 일주일이 넘었는데</p><p>배송이 안오네요 언제쯤 오나요??</p>',3,NULL,'public'),
	 (3,1,'2022-01-27 18:21:05.0','jdk123','지동근','사이즈 문의','<p>사이즈가 M이라고 해서 샀는데</p><p>생각보다 작은데 교환되나요?? 100은 아닌것 같아서요</p>',3,'1234','secret'),
	 (4,1,'2022-01-27 18:22:23.0','needPk','김혜린','배송비 환불문의','<p>&nbsp;</p><p>세일상품이라 배송비 안나오는데 결제 확인해보니&nbsp;</p><p>같이 결제되어있더라구요 환불 좀 부탁드립니다.</p>',4,NULL,'public'),
	 (1,2,'2022-01-27 18:23:47.0','manager','매니저','''킬이브''에서 답변드립니다','<p>안녕하세요 킬이브입니다.</p><p>먼저 불편을 끼쳐드려 죄송합니다</p><p>평균적인 배송기간은 3~5일으로 배송사의 사정에 따라</p><p>조금씩 차이가 날 수 있습니다.</p><p>5일이후에도 배송이 오지 않을 경우 고객센터로</p><p>연락주시면 안내도와 드리겠습니다.&nbsp;</p><p>감사합니다.</p>',1,'1234','reply'),
	 (2,2,'2022-01-27 18:23:53.0','manager','매니저','''킬이브''에서 답변드립니다','<p>안녕하세요 킬이브입니다.</p><p>먼저 불편을 끼쳐드려 죄송합니다</p><p>평균적인 배송기간은 3~5일으로 배송사의 사정에 따라</p><p>조금씩 차이가 날 수 있습니다.</p><p>5일이후에도 배송이 오지 않을 경우 고객센터로</p><p>연락주시면 안내도와 드리겠습니다.&nbsp;</p><p>감사합니다.</p>',4,'','reply'),
	 (3,2,'2022-01-27 18:26:04.0','manager','매니저','''킬이브''에서 답변드립니다','<p>안녕하세요 킬이브입니다.</p><p>먼저 불편을 끼쳐드려 죄송합니다</p><p>고객분마다 체형이 달라 사이즈에 따라 오차가 발생할 수 있습니다.</p><p>단순 변심 교환의 경우 무료로 환불 및 반품 도와드리고 있습니다.</p><p>고객센터로 연락주시면 환불 및 반품 도와드리도록 하겠습니다.</p><p>감사합니다.</p>',0,'1234','reply'),
	 (4,2,'2022-01-27 18:27:16.0','manager','매니저','''킬이브''에서 답변드립니다','<p>안녕하세요 킬이브입니다.</p><p>먼저 불편을 끼쳐드려 죄송합니다</p><p>확인결과 고객님께서 주문해주신 상품은&nbsp;</p><p>세일로 인한 배송비가 청구되지 않아야 함에도 불구하고</p><p>배송비가 같이 결제된 점 귀측의 사유로 환불처리 도와드리도록 하겠습니다.</p><p>고객센터로 연락주시면 환불계좌 및 환불 안내 도와드리겠습니다.</p><p>감사합니다.</p>',0,'','reply'),
	 (9,1,'2022-01-27 18:28:38.0','kkej89','김국종','상품색상 문의','<p>코트를 구입했는데 색상이 그레이블루 인데</p><p>너무 진한것 같은데 그레이 블루 색상이 맞나요??</p><p>확인 좀 부탁드립니다.</p>',3,'1234','secret'),	 
	 (10,1,'2022-01-27 18:30:21.0','pps2995','박소임','상품이 이상합니다','<p>코트를 주문했는데 상품이 이상합니다.</p><p>실도 삐져나와있고 색상도 실제상품이랑 다른것 같은데</p><p>이런식으로 해도 되는건가요?</p><p>환불 처리 해주세요</p>',2,NULL,'public'),
	 (9,2,'2022-01-27 18:31:53.0','manager','매니저','''킬이브''에서 답변드립니다','<p>안녕하세요 킬이브입니다.</p><p>확인 결과 고객님께서 주문해주신 ''배런 라운드 오버 가디건''의 경우 그레이블루 색상이</p><p>맞으며 색상과 다른 경우 모니터의 설정에 따라서 같은 색상이 달라질 수 있습니다.</p><p>이 부분 확인하시고 구매 부탁드리겠습니다.</p><p>감사합니다.</p>',0,'1234','reply'),
	 (10,2,'2022-01-27 18:33:21.0','manager','매니저','''킬이브''에서 답변드립니다','<p>안녕하세요 킬이브입니다.</p><p>확인 결과 고객님께서 주문해주신 ''배런 라운드 오버 가디건''의 경우 그레이색상이</p><p>맞으며 색상과 다른 경우 모니터의 설정에 따라서 같은 색상이 달라질 수 있습니다.</p><p>또한 실이 삐져나와있는 부분은 생산공정 과정에서 첨가되어 있을 수 있는 사안으로</p><p>불량은 아니라고 판단됩니다.</p><p>하지만 킬이브는 단순변심에 의한 교환과 반품도 가능하므로</p><p>고객센터로 연락주시면 교환과 반품 도와드리도록 하겠습니다.</p><p>감사합니다.</p>',0,'','reply');