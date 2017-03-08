# Host: 5778e8e13b6f3.bj.cdb.myqcloud.com:13166  (Version 5.6.28-log)
# Date: 2016-07-05 18:23:26
# Generator: MySQL-Front 5.3  (Build 5.39)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "schools"
#

DROP TABLE IF EXISTS `schools`;
CREATE TABLE `schools` (
  `school_id` int(11) NOT NULL AUTO_INCREMENT,
  `school_name` varchar(255) NOT NULL,
  `school_position` varchar(255) NOT NULL,
  `school_contact` varchar(255) NOT NULL,
  `school_post` varchar(255) NOT NULL,
  `school_tel` varchar(255) NOT NULL,
  `school_pass` varchar(255) DEFAULT NULL,
  `school_href` varchar(255) NOT NULL,
  PRIMARY KEY (`school_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "schools"
#

