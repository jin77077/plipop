CREATE TABLE `user` (
  `createDate` datetime(6) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `emailProvider` varchar(100) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(30) NOT NULL,
  `username` varchar(100) NOT NULL,
  `userId` varchar(100) NOT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `emailReceiveYn` varchar(2) DEFAULT NULL,
  `smsReceiveYn` varchar(2) DEFAULT NULL,
  `zipCode` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `detailAddress` varchar(255) DEFAULT NULL,
  `provider` varchar(100) DEFAULT NULL,
  `providerId` varchar(100) DEFAULT NULL,
  `birthYear` int(4) DEFAULT NULL,
  `birthMonth` int(2) DEFAULT NULL,
  `birthDay` int(2) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO plipop.`user` (createDate,email,emailProvider,password,`role`,username,userId,phone,gender,emailReceiveYn,smsReceiveYn,zipCode,address,detailAddress,provider,providerId,birthYear,birthMonth,birthDay) VALUES
	 ('2021-11-18 22:17:52.0','admin@yg.com',NULL,'$2a$10$92PyBxWEvFZ2UoP04QE5/O08s0ikap8WkDf3q8N1Caf4R/TaKEdKK','ROLE_ADMIN','관리자','admin','01022221111','M','on','on','06035','서울 강남구 가로수길 5','122-1',NULL,NULL,2021,11,18),
	 ('2021-11-18 22:18:17.0','manager@yg.com',NULL,'$2a$10$/iVmCuh9BcUlf2C8dysvDO0i9HSIsDzwB0iuISNn2eZLPlVnx43Pa','ROLE_MANAGER','매니저','manager','01022222222','M','on','on','15653','경기 안산시 단원구 대부남동 600-34','123',NULL,NULL,2021,11,18),
	 ('2021-11-18 22:19:05.0','unhot2','nate.com','$2a$10$c4jLw03pnkYM5uTOU3.dau23K.a6XRhCl755WfEG0VWmo/a2kBcbm','ROLE_USER','성근모','unhot2','01082883640','M',NULL,NULL,'02113','서울 중랑구 중랑천로 146-4','백운빌 502호',NULL,NULL,1989,2,28);
