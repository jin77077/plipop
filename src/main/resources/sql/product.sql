CREATE TABLE `product` (
  `productNo` int(11) NOT NULL AUTO_INCREMENT,
  `productName` varchar(100) NOT NULL,
  `category` varchar(50) NOT NULL,
  `discountRate` varchar(20) DEFAULT '0',
  `consumerPrice` int(11) NOT NULL,
  `salePrice` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `mainImage` varchar(100) NOT NULL,
  `hotYn` tinyint(1) DEFAULT 0,
  `mdPickYn` tinyint(1) DEFAULT 0,
  `quickYn` tinyint(1) DEFAULT 0,
  `bestYn` tinyint(1) DEFAULT 0,
  `saleYn` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`productNo`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;

INSERT INTO plipop.product (productName,category,discountRate,consumerPrice,salePrice,stock,mainImage,hotYn,mdPickYn,quickYn,bestYn,saleYn) 
VALUES ('[wool] 울 캐시 세미핏 싱글코트OT','outer','36',140000,89600,100,'1_main.png',1,0,1,0,0),
	   ('Monster 야상 패딩파카OT','outer','45',140000,77000,200,'2_main.png',0,1,0,0,0),
	   ('[당일발송][오리털] 카이 유틸리티 숏패딩OT','outer','51',140000,68600,100,'3_main.png',0,0,0,1,0),
	   ('프리미엄 플러스 숏야상패딩','outer','45',120000,66000,100,'4_main.png',1,0,0,0,0),
	   ('[wool]양털 울 무스탕 자켓OT','outer','23',90000,69300,100,'5_main.png',0,1,0,0,1),
	   ('조크 오버 양털후드 야상점퍼OT','outer','42',153000,88700,100,'6_main.png',0,0,1,1,0),
	   ('하찌 라운드 니트 집업OT','outer','20',45000,36000,100,'7_main.png',0,1,0,0,1),
	   ('폴링 아가일 오버 가디건 OT','outer','19',48000,38800,100,'8_main.png',0,1,0,0,0),
	   ('맬론 숏패딩','outer','41',79000,46600,100,'9_main.png',0,0,0,1,0),
	   ('[당일발송][기모]웜히트 스탠다드 반폴라','top','45',29000,15900,100,'10_main.png',1,0,1,0,0),
	   ('도톰 터틀넥 워머 하찌니트','top','36',70000,44800,100,'11_main.png',0,1,1,1,0),
	   ('터치미 오버양털 반집업아노락OT','top','22',36000,28000,100,'12_main.png',0,1,0,0,1),
	   ('[wool]앙고라 단가라 니트','top','35',48000,31200,100,'13_main.png',1,0,1,0,0),
	   ('[기모]톤다운 사각체크 맨투맨','top','37',50000,31500,100,'14_main.png',1,0,0,0,1),
	   ('[기모]GROOVY 양털 반집업 맨투맨','top','33',70000,46800,100,'15_main.png',0,0,1,0,0),
	   ('[기모]빌리지 오버 후드티','top','33',48000,32100,100,'16_main.png',0,1,1,0,0),
	   ('[wool]왕꽈배기 클릭 브이넥니트','top','35',90000,58500,100,'17_main.png',1,0,0,1,0),
	   ('[BLACK_label]데일리 와이드 밴딩바지','pants','54',30000,13700,200,'18_main.png',0,0,1,0,0),
	   ('코듀로이 본딩 와이드 바지','pants','20',25000,20000,100,'19_main.png',0,1,0,1,0),
	   ('[왕기모] 두툼 슬림슬랙스','pants','37',30000,18900,100,'20_main.png',0,0,0,1,1),
	   ('깔끔핏 사이드밴딩 치노팬츠','pants','20',25000,20000,100,'21_main.png',0,1,0,1,0),
	   ('옆밴딩 깔끔 세미와이드 슬랙스','pants','25',25500,19100,100,'22_main.png',0,0,1,0,0),
	   ('투버튼 High-waist 와이드 슬랙스','pants','36',30000,19200,100,'23_main.png',0,0,1,1,0),
	   ('[기모]레이티드 조거 밴딩바지','pants','34',47000,31000,100,'24_main.png',0,0,0,0,1),
	   ('[Big양기모] 해리 양기모 조거바지','pants','32',50000,34000,1,'25_main.png',1,1,0,0,0),
	   ('[BOX소가죽]7cm키높이수제화 첼시부츠','shoes','74',218000,56600,50,'26_main.png',0,1,0,1,0),
	   ('제제 캔버스 스니커즈shoes','shoes','29',27000,19100,50,'27_main.png',1,1,0,0,0),
	   ('[BOX소가죽]7cm키높이수제화 더비슈즈','shoes','74',195000,50700,50,'28_main.png',0,0,1,1,0),
	   ('[슬립온]7cm키높이소가죽수제화shoes','shoes','79',164000,34400,50,'29_main.png',0,0,0,0,1),
	   ('트래버스 운동화 shoes','shoes','21',45000,35500,50,'30_main.png',0,1,1,0,0),
	   ('소가죽_7cm키높이 어글리 수제화 운동화 shoes','shoes','58',189000,79300,50,'31_main.png',1,0,0,1,0),
	   ('[U-Tip]7cm키높이수제화더비shoes','shoes','80',230000,45900,50,'32_main.png',0,1,0,1,0),
	   ('아이보리 캔버스 2type 스니커즈shoes','shoes','13',40000,34800,50,'33_main.png',0,1,0,0,0),
	   ('배런 라운드 오버 가디건','outer','35',65000,42200,100,'34_main.png',0,1,1,1,0),
	   ('[wool]울 오버 맨투맨','top','35',48000,31200,100,'35_main.png',0,0,0,1,0);