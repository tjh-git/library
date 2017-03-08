# Host: 115.28.61.167  (Version 5.5.47-0ubuntu0.14.04.1)
# Date: 2016-07-13 12:21:17
# Generator: MySQL-Front 5.3  (Build 6.26)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "book_catalog"
#

DROP TABLE IF EXISTS `book_catalog`;
CREATE TABLE `book_catalog` (
  `catalog_id` int(11) NOT NULL AUTO_INCREMENT,
  `bookcode` varchar(255) NOT NULL DEFAULT '0',
  `have_vol` int(1) NOT NULL,
  `seriesname` varchar(255) NOT NULL,
  `writer` varchar(255) NOT NULL,
  `translator` varchar(255) NOT NULL,
  `bookname` varchar(255) NOT NULL,
  `bookab` varchar(255) NOT NULL,
  `publishcom` varchar(255) NOT NULL,
  `booklanguage` varchar(255) NOT NULL,
  `booktype` varchar(255) NOT NULL,
  `publishdate` date NOT NULL,
  `editiontimes` int(10) NOT NULL,
  `printtimes` int(10) NOT NULL,
  `booksize` varchar(255) NOT NULL,
  `bookbind` varchar(255) NOT NULL,
  `bookpagenum` int(20) NOT NULL,
  `bookprice` decimal(6,2) NOT NULL DEFAULT '0.00',
  `is_refbook` varchar(2) NOT NULL,
  `is_journal` varchar(2) NOT NULL,
  `operator_id` varchar(255) NOT NULL,
  `getdate` datetime NOT NULL,
  `operator_name` varchar(255) NOT NULL,
  `seriesab` varchar(255) NOT NULL,
  `second_writer` varchar(255) NOT NULL,
  `other_putup` varchar(255) NOT NULL,
  `together_putup` varchar(255) NOT NULL,
  PRIMARY KEY (`catalog_id`),
  KEY `bookname` (`bookname`,`bookab`,`publishcom`),
  KEY `bookname_2` (`bookname`),
  KEY `publishcom` (`publishcom`),
  KEY `bookab` (`bookab`),
  KEY `bookcode` (`bookcode`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

#
# Data for table "book_catalog"
#

INSERT INTO `book_catalog` VALUES (0,'9787112184194',1,'','甄峰','','基于大数据的城市研究与规划方法创新','JYDSJDCSYJYGHFFCX','中国建筑工业出版社','中文','TU984','2016-07-12',1,1,'16开','平装',123,20.00,'否','否','0','2016-07-12 10:24:39','aaaaaa','','','基于大数据','基于大数据'),(15,'9787510823398',1,'','谢国计','','别让不好意思毁了你','BRBHYSHLN','机械工业出版社','简体中文','A783','2013-11-01',1,1,'16开','平装',122,35.00,'否','否','3','2016-07-13 10:14:10','user','','','',''),(17,'9787301271957',1,'','马浩','','管理的幻觉: 沉醉于臆想中的现实','GLDHJ: CZYYXZDXS','清华大学出版社','简体中文','A8','2016-07-25',1,1,'16开','平装',259,38.00,'否','否','0','2016-07-13 11:37:39','aaaaaa','','','','');
