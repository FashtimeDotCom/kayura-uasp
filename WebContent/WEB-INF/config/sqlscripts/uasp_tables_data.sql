/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50615
Source Host           : localhost:3306
Source Database       : kayura_db

Target Server Type    : MYSQL
Target Server Version : 50615
File Encoding         : 65001

Date: 2015-10-15 15:54:52
*/


-- ----------------------------
-- Table structure for uasp_users
-- ----------------------------
DROP TABLE IF EXISTS `uasp_users`;
CREATE TABLE `uasp_users` (
  `User_Id` char(36) NOT NULL,
  `UserName` varchar(64) NOT NULL,
  `DisplayName` varchar(64) NOT NULL,
  `Password` varchar(256) NOT NULL,
  `Email` varchar(256) DEFAULT NULL,
  `MobileNo` varchar(32) DEFAULT NULL,
  `Keyword` varchar(2048) DEFAULT NULL,
  `CreateTime` datetime NOT NULL,
  `ExpireTime` datetime DEFAULT NULL COMMENT 'null 表示不过期.',
  `AuthType` varchar(64) NOT NULL COMMENT 'USER 员工；ADMIN 管理员；',
  `IsEnabled` bit(1) NOT NULL,
  `IsLocked` bit(1) NOT NULL,
  PRIMARY KEY (`User_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uasp_users
-- ----------------------------
INSERT INTO `uasp_users` VALUES ('BD817FA7-716E-11E5-86C6-D8CB8A43F8DD', 'admin', '管理员', '123456', 'admin@kayura.org', '13556090295', 'admin glr', '2015-10-13 13:54:32', null, 'ADMIN', '', '\0');
