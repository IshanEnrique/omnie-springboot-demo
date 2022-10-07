

CREATE TABLE  IF NOT EXISTS `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `pwd` varchar(200) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);


INSERT INTO `customer` (ID,`email`, `pwd`, `role`)
 VALUES (1,'johndoe@example.com', '54321', 'admin');
 
INSERT INTO `customer` (ID,`email`, `pwd`, `role`)
 VALUES (2,'happy@example.com', '$2y$12$oRRbkNfwuR8ug4MlzH5FOeui.//1mkd.RsOAJMbykTSupVy.x/vb2', 'admin');
 
 
commit