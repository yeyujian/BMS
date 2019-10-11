/*
Navicat MySQL Data Transfer

Source Host           : localhost:3306
Source Database       : bookMaster
Target Server Type    : MYSQL

Date: 2019-9-12 
*/
use bookMaster;

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `book`
-- ----------------------------


DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(20) DEFAULT NULL UNIQUE,
  `book_author` varchar(20) DEFAULT NULL,
  `book_publish` varchar(20) DEFAULT NULL,
  `book_category` varchar(25) DEFAULT NULL,
  `book_price` double DEFAULT NULL,
  `book_introduction` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `borrowingbooks`
-- ----------------------------
DROP TABLE IF EXISTS `borrowingbooks`;
CREATE TABLE `borrowingbooks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `return_time` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`)
--  CONSTRAINT `book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
--  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for `p_admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(20) DEFAULT NULL UNIQUE, 
  `admin_pwd` varchar(20) DEFAULT NULL,
  `system_admin` int(2) DEFAULT 0,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL UNIQUE,
  `user_pwd` varchar(20) DEFAULT NULL,
  `user_email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '巨人的陨落', '肯.福莱特', '江苏凤凰文艺出版社', '其他', '129', '在第一次世界大战中发生的故事');
INSERT INTO `book` VALUES ('2', '三体', '刘慈欣', '南京大学出版社', '其他', '68', '科幻小说');
INSERT INTO `book` VALUES ('3', '复活', '列夫.托尔斯泰', '上海译文出版社','其他', '19', '俄国小说');
INSERT INTO `book` VALUES ('6', '平凡的世界', '路遥', '上海文艺出版社', '其他', '88', '孙少平和孙少安两兄弟...');
INSERT INTO `book` VALUES ('15', '白鹿原', '陈忠实', '南京出版社', '其他', '36', '当代小说');
INSERT INTO `book` VALUES ('16', '计算机网络', '谢希仁', '电子工业出版社','其他', '49', '计算机专业书籍');


-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'root', 'root','1');
INSERT INTO `admin` VALUES ('2', 'yyj', 'yyj','0');

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'yyj', 'yyj','1234@qq.com');
INSERT INTO `user` VALUES ('2', 'chen', 'chen','1234@qq.com');
