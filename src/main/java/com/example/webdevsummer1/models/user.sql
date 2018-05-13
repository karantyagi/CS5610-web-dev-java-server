/*
-- Query: SHOW CREATE TABLE user
-- Date: 2018-05-13 18:07
*/
INSERT INTO `user` (`Table`,`Create Table`) VALUES ('user','CREATE TABLE `user` (\n  `id` int(11) NOT NULL AUTO_INCREMENT,\n  `date_of_birth` datetime DEFAULT NULL,\n  `email` varchar(255) DEFAULT NULL,\n  `first_name` varchar(255) DEFAULT NULL,\n  `last_name` varchar(255) DEFAULT NULL,\n  `password` varchar(255) DEFAULT NULL,\n  `phone` varchar(255) DEFAULT NULL,\n  `role` varchar(255) DEFAULT NULL,\n  `username` varchar(255) DEFAULT NULL,\n  PRIMARY KEY (`id`)\n) ENGINE=MyISAM DEFAULT CHARSET=utf8');
