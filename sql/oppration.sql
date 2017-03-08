﻿# Host: 127.0.0.1  (Version 5.6.24)
# Date: 2016-07-02 17:11:51
# Generator: MySQL-Front 5.3  (Build 5.39)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "oppration"
#

DROP TABLE IF EXISTS `oppration`;
CREATE TABLE `oppration` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `time` date NOT NULL DEFAULT '1000-01-01',
  `action` varchar(255) NOT NULL DEFAULT '0',
  `user` varchar(255) NOT NULL DEFAULT '',
  `book` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Id` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

#
# Data for table "oppration"
#

INSERT INTO `oppration` VALUES (1,'2016-06-11','借阅','1','1'),(2,'2016-06-11','借阅','2','18'),(3,'2016-07-01','借阅','1','1'),(4,'2016-07-01','归还','1','1'),(5,'2016-07-01','借阅','1','1'),(6,'2016-07-01','归还','1','1'),(7,'2016-07-01','借阅','1','1'),(8,'2016-07-01','归还','1','1'),(9,'2016-07-01','借阅','1','1'),(10,'2016-07-01','归还','1','1'),(11,'2016-07-01','借阅','1','1'),(12,'2016-07-01','归还','1','1'),(13,'2016-07-01','借阅','1','1'),(14,'2016-07-01','归还','1','1'),(15,'2016-07-01','借阅','1','1'),(16,'2016-07-01','归还','1','1'),(17,'2016-07-01','借阅','1','1'),(18,'2016-07-01','归还','1','1'),(19,'2016-07-01','借阅','1','1'),(20,'2016-07-01','归还','1','1'),(21,'2016-07-01','借阅','1','1'),(22,'2016-07-01','归还','1','1'),(23,'2016-07-01','借阅','1','1'),(24,'2016-07-01','归还','1','1'),(25,'2016-07-01','借阅','1','1'),(26,'2016-07-01','归还','1','1'),(27,'2016-07-01','借阅','1','1'),(28,'2016-07-01','归还','1','1'),(29,'2016-07-02','借阅','1','1'),(30,'2016-07-02','归还','1','1');