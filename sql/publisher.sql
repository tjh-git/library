# Host: 127.0.0.1  (Version 5.6.24)
# Date: 2016-07-14 08:49:37
# Generator: MySQL-Front 5.3  (Build 6.26)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "publisher"
#

DROP TABLE IF EXISTS `publisher`;
CREATE TABLE `publisher` (
  `school_id` int(11) NOT NULL DEFAULT '0',
  `book_publisher` text,
  PRIMARY KEY (`school_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "publisher"
#

INSERT INTO `publisher` VALUES (0,'清华大学出版社,北京大学出版社,机械工业出版社,山东大学出版社');
