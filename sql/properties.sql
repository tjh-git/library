# Host: 127.0.0.1  (Version 5.6.24)
# Date: 2016-07-14 08:49:49
# Generator: MySQL-Front 5.3  (Build 6.26)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "properties"
#

DROP TABLE IF EXISTS `properties`;
CREATE TABLE `properties` (
  `school_id` int(11) NOT NULL DEFAULT '0',
  `book_layout` varchar(255) DEFAULT NULL,
  `book_lunguage` varchar(255) DEFAULT NULL,
  `book_size` varchar(255) DEFAULT NULL,
  `book_from` varchar(255) DEFAULT NULL,
  `book_code` varchar(255) DEFAULT NULL,
  `class_code` varchar(255) DEFAULT NULL,
  `grade_code` varchar(255) DEFAULT NULL,
  `user_type` varchar(255) DEFAULT NULL,
  `library_div` varchar(255) DEFAULT NULL,
  `borrow_max_time` varchar(255) DEFAULT NULL,
  `s_max_borrow` varchar(255) DEFAULT NULL,
  `t_max_borrow` varchar(255) DEFAULT NULL,
  `s_overtime_fine` varchar(255) DEFAULT NULL,
  `t_overtime_fine` varchar(255) DEFAULT NULL,
  `s_one_length` varchar(255) DEFAULT NULL,
  `t_one_length` varchar(255) DEFAULT NULL,
  `s_isFine` varchar(255) DEFAULT NULL,
  `t_isFine` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`school_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "properties"
#

INSERT INTO `properties` VALUES (0,'平装, 精装','简体中文, 繁体中文, 英文','8开, 16开, 32开, 异本','赠送, 购买','第一书库, 第二书库, 第三书库, 第四出库, 第五书库','二班, 三班, 四班, 五班, 六班','一年级, 二年级, 三年级, 四年级, 五年级, 六年级, 七年级, 八年级','教师, 学生','软件园分馆, 蒋震图书馆','100','8','30','2000','5000','30','90','on','on');
