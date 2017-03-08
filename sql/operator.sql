# Host: 5778e8e13b6f3.bj.cdb.myqcloud.com:13166  (Version 5.6.28-log)
# Date: 2016-07-05 18:34:37
# Generator: MySQL-Front 5.3  (Build 5.39)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "operator"
#

DROP TABLE IF EXISTS `operator`;
CREATE TABLE `operator` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `school_id` int(11) NOT NULL,
  `operator_name` varchar(233) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "operator"
#

INSERT INTO `operator` VALUES (0,'aaaaaa','aaaaaa',0,'管理员'),(1,'1','admin',1,'学校管理员');
