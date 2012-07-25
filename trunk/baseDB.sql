/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50151
Source Host           : localhost:3306
Source Database       : base

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2012-07-25 14:35:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `usable` char(1) NOT NULL,
  `realname` varchar(30) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `age` smallint(6) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `createBy` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateBy` varchar(30) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 初始的数据记录
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', 'zhaoqian', 'zhaoqian', 'Y', '赵谦', '男', '25', '江苏无锡', 'zhaoqianjava@foxmail.com', 'zhaoqian', '2012-07-23 17:33:32', 'zhaoqian', '2012-07-23 17:33:38');
INSERT INTO `userinfo` VALUES ('2', 'admin', 'admin', 'Y', 'admin', '男', null, null, null, null, null, null, null);
INSERT INTO `userinfo` VALUES ('3', 'credo', 'credo', 'N', null, null, null, null, null, null, null, null, null);
