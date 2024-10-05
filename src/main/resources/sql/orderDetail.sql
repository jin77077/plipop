CREATE TABLE `orderDetail` (
  `merchant_uid` varchar(100) NOT NULL,
  `productNo` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `color` varchar(100) NOT NULL,
  `size` varchar(100) NOT NULL,
  `totalPrice` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO plipop.orderDetail (merchant_uid,productNo,quantity,color,`size`,totalPrice) VALUES
	 ('merchant_1639720962069',1,3,'아이보리','100(L)',268800),
	 ('merchant_1639720962069',1,2,'블랙','105(XL)',179200),
	 ('merchant_1639725951631',2,2,'블랙','100(M)',154000),
	 ('merchant_1639725951631',2,1,'카키','100(M)',77000),
	 ('merchant_1640239898788',34,2,'카키','FREE (95~100)',84400),
	 ('merchant_1640239898788',35,2,'블루','FREE (95~100)',62400),
	 ('merchant_1640239898788',26,1,'PU','255mm ~ 255mm',56600),
	 ('merchant_1640239898788',11,4,'블랙','FREE (95~100)',179200),
	 ('merchant_1640239898788',20,2,'블랙','31~32 (L)',37800),
	 ('merchant_1640239898788',8,2,'네이비','FREE (95~100)',77600),
	 ('merchant_1640240119987',12,6,'블랙','FREE (95~100)',168000);