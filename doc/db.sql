/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : maven

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2014-07-03 13:54:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for operater
-- ----------------------------
DROP TABLE IF EXISTS `operater`;
CREATE TABLE `operater` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(1) DEFAULT NULL COMMENT '类型：0内部用户，1外部用户',
  `code` varchar(50) DEFAULT NULL COMMENT '账号',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `superUser` int(1) DEFAULT NULL COMMENT '是否是超级用户',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(100) DEFAULT NULL COMMENT '电话号码',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `operaterId` bigint(20) DEFAULT NULL COMMENT '操作者',
  `operaterCode` varchar(50) DEFAULT NULL COMMENT '操作者的账号',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operater
-- ----------------------------
INSERT INTO `operater` VALUES ('1', '0', 'admin', '超级管理员', '6e3afda58b394b32ad4a737e1b75c66e', '1', '', '', '2011-05-11 09:57:52', '2014-03-12 11:06:38', '1', 'admin', '1');
INSERT INTO `operater` VALUES ('2', '0', 'songkun', 'songkun', 'e10adc3949ba59abbe56e057f20f883e', '0', 'xintiankun@163.com', '123', '2011-05-27 15:19:16', '2014-04-25 20:47:01', '1', 'admin', '1');
INSERT INTO `operater` VALUES ('3', '0', 'test', 'test', 'e10adc3949ba59abbe56e057f20f883e', '0', '2222211', '3213213', '2014-03-12 00:10:30', '2014-04-26 11:54:05', '1', 'admin', '1');
INSERT INTO `operater` VALUES ('21', '0', 't', 't', 'e358efa489f58062f10dd7316b65649e', '0', '1@1.c', '', '2014-05-02 16:26:48', '2014-05-04 17:18:02', '1', 'admin', '1');
INSERT INTO `operater` VALUES ('22', '0', 'tt', 'tt', 'e358efa489f58062f10dd7316b65649e', '0', 'd@e.cdde', '', '2014-05-04 16:45:55', '2014-05-04 17:17:49', '1', 'admin', '1');
INSERT INTO `operater` VALUES ('25', '0', '1', '1', 'c4ca4238a0b923820dcc509a6f75849b', '0', '', '', '2014-05-04 17:19:31', '2014-05-04 17:19:31', '1', 'admin', '1');
INSERT INTO `operater` VALUES ('26', '0', '2', '2', 'c81e728d9d4c2f636f067f89cc14862c', '0', '', '', '2014-05-04 17:19:39', '2014-05-04 17:19:39', '1', 'admin', '1');
INSERT INTO `operater` VALUES ('27', '0', '3', '3', 'eccbc87e4b5ce2fe28308fd9f2a7baf3', '0', '', '', '2014-05-04 17:19:45', '2014-05-04 17:19:45', '1', 'admin', '1');
INSERT INTO `operater` VALUES ('34', '0', 'd', 'd', '8277e0910d750195b448797616e091ad', '0', '', '', '2014-07-01 20:52:56', '2014-07-01 20:52:56', '1', 'admin', '1');
INSERT INTO `operater` VALUES ('35', '0', 'eeees', '的', '8277e0910d750195b448797616e091ad', '0', '', '', '2014-07-01 20:56:07', '2014-07-01 20:56:07', '1', 'admin', '1');
INSERT INTO `operater` VALUES ('36', '0', 'mu', 'kd', '8277e0910d750195b448797616e091ad', '0', '', '', '2014-07-01 23:06:53', '2014-07-01 23:06:53', '1', 'admin', '1');
INSERT INTO `operater` VALUES ('38', '0', 'dddd', 'ddddd好吧', '8277e0910d750195b448797616e091ad', '0', '', '', '2014-07-03 10:38:56', '2014-07-03 10:38:56', '1', 'admin', '1');

-- ----------------------------
-- Table structure for operater_bind_role
-- ----------------------------
DROP TABLE IF EXISTS `operater_bind_role`;
CREATE TABLE `operater_bind_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) DEFAULT NULL COMMENT '后台操作员',
  `roleId` bigint(20) DEFAULT NULL COMMENT '角色',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `operaterId` bigint(20) DEFAULT NULL COMMENT '操作者',
  `operaterCode` varchar(50) DEFAULT NULL COMMENT '操作者的账号',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `FK_ID` (`userId`),
  CONSTRAINT `FK_ID` FOREIGN KEY (`userId`) REFERENCES `operater` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operater_bind_role
-- ----------------------------
INSERT INTO `operater_bind_role` VALUES ('4', '3', '2', '2014-07-03 13:36:52', '2014-07-03 13:36:52', '1', 'admin', '1');
INSERT INTO `operater_bind_role` VALUES ('5', '2', '1', '2014-07-03 13:40:34', '2014-07-03 13:40:34', '1', 'admin', '1');

-- ----------------------------
-- Table structure for permit
-- ----------------------------
DROP TABLE IF EXISTS `permit`;
CREATE TABLE `permit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '权限名称',
  `code` varchar(200) DEFAULT NULL COMMENT '权限代码',
  `leaf` int(1) DEFAULT NULL COMMENT '是否是叶子节点',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `ordinal` int(11) DEFAULT NULL COMMENT '排序，显示顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permit
-- ----------------------------
INSERT INTO `permit` VALUES ('1', '后台用户管理', 'operater', '0', '1', '1');
INSERT INTO `permit` VALUES ('2', '后台用户', 'operater.operater', '1', '1', '2');
INSERT INTO `permit` VALUES ('3', '用户角色', 'operater.role', '1', '1', '3');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `operaterId` bigint(20) DEFAULT NULL COMMENT '操作者',
  `operaterCode` varchar(50) DEFAULT NULL COMMENT '操作者的账号',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '厂商', 'test', '2011-05-12 15:23:49', '2011-06-19 18:59:06', '1', 'admin', '1');
INSERT INTO `role` VALUES ('2', 'test', 't', '2014-05-05 23:38:32', '2014-05-05 23:38:38', '1', 'admin', '1');

-- ----------------------------
-- Table structure for role_bind_permit
-- ----------------------------
DROP TABLE IF EXISTS `role_bind_permit`;
CREATE TABLE `role_bind_permit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permitId` bigint(20) DEFAULT NULL COMMENT '权限ID',
  `roleId` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `operaterId` bigint(20) DEFAULT NULL COMMENT '操作者',
  `operaterCode` varchar(50) DEFAULT NULL COMMENT '操作者的账号',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_bind_permit
-- ----------------------------
INSERT INTO `role_bind_permit` VALUES ('42', '1', '1', '2014-05-11 22:22:06', '2014-05-11 22:22:06', '1', 'admin', '1');
INSERT INTO `role_bind_permit` VALUES ('43', '2', '1', '2014-05-11 22:22:06', '2014-05-11 22:22:06', '1', 'admin', '1');
INSERT INTO `role_bind_permit` VALUES ('44', '3', '1', '2014-05-11 22:22:06', '2014-05-11 22:22:06', '1', 'admin', '1');
