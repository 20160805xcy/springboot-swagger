/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : wolf

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2018-12-06 17:06:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '姓名',
  `user_age` int(4) DEFAULT NULL COMMENT '年龄',
  `user_address` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地址',
  UNIQUE KEY `主键id` (`id`) USING HASH COMMENT '主键id'
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'jack', '26', '深圳南山');
INSERT INTO `user` VALUES ('2', '孙悟空', '22', '深圳西乡');
INSERT INTO `user` VALUES ('3', null, '18', '没有name');
INSERT INTO `user` VALUES ('4', 'jjj', '12', 'sz');
INSERT INTO `user` VALUES ('5', 'jjj', '12', 'sz');
