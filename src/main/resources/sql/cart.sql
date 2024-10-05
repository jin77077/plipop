CREATE TABLE `cart` (
  `cartNo` int(11) NOT NULL AUTO_INCREMENT,
  `productNo` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `size` varchar(100) NOT NULL,
  `color` varchar(100) NOT NULL,
  `userId` varchar(100) NOT NULL,
  PRIMARY KEY (`cartNo`),
  KEY `cart_FK` (`productNo`),
  CONSTRAINT `cart_FK` FOREIGN KEY (`productNo`) REFERENCES `product` (`productNo`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;

INSERT INTO plipop.cart (productNo,quantity,`size`,color,userId) 
VALUES (33,1,'260mm','아이보리','unhot2'),
	   (2,1,'100(M)','블랙','unhot2'),
	   (18,1,'31~34 (L)','그레이','unhot2'),
	   (13,1,'FREE (95~100)','블랙','unhot2');