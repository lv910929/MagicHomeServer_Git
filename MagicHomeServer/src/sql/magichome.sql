/*
Navicat MySQL Data Transfer

Source Server         : lv
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : magichome

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2015-04-07 09:15:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `accountName` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `mobile` varchar(11) NOT NULL COMMENT '手机号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('10', '15088160532', 'a82ba434211ae6850c97ead0ab4161e0', '15088160532');
INSERT INTO `account` VALUES ('11', '111115', 'c50d13616a9d6d9b2bb1b6eaa1dbab2d', '15065156165');
INSERT INTO `account` VALUES ('13', '15151', '5efbbcd216964465df3a672a51664994', '15065156165');
INSERT INTO `account` VALUES ('14', '1111', '0a0fde4794de6bb9fb02136d97a37ca5', '15088160532');
INSERT INTO `account` VALUES ('15', '15664846565', '48b3a8f27733ac1d2a0c190652721562', '1561615616');
INSERT INTO `account` VALUES ('16', '18631502016', '45275cf5361e5406d0c9617bd5949d9e', '18631502016');
INSERT INTO `account` VALUES ('17', '13851206988', '42d78356e1fc985f121fe87e39142096', '13851206988');
INSERT INTO `account` VALUES ('18', '12006351651', '1b9822ec0c90641a06a2c8df9b96a3fe', '12006351651');
INSERT INTO `account` VALUES ('19', '18654165412', 'fc051ace58c47c557040b6e577786dff', '18654165412');
INSERT INTO `account` VALUES ('20', '1575615615', '5027fa629c7f4847c5475599d48a3e30', '1575615615');

-- ----------------------------
-- Table structure for `admin_user`
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(10) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `mobile` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('1', 'admin', 'e3274be5c857fb42ab72d786e281b4b8', null);

-- ----------------------------
-- Table structure for `device`
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account_name` varchar(20) NOT NULL COMMENT '智能家居账号',
  `mac_address` varchar(30) NOT NULL,
  `domain_name` varchar(30) NOT NULL COMMENT '域名',
  `software_version` int(10) NOT NULL COMMENT '中控软件版本',
  `hardware_version` int(10) NOT NULL COMMENT '中控硬件版本',
  `register_time` datetime NOT NULL COMMENT '启用时间',
  `update_time` datetime DEFAULT NULL COMMENT '联网时间',
  `device_status` int(1) unsigned NOT NULL COMMENT '联网状态（1：联网中，2：未联网）',
  PRIMARY KEY (`id`),
  KEY `hardware_version` (`hardware_version`),
  KEY `account_id` (`account_name`),
  KEY `mac_address` (`mac_address`),
  KEY `software_version` (`software_version`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('2', '18631502016', '60:be:b5:82:06:3e', '192.168.1.93', '1', '1', '2015-03-26 09:04:06', null, '2');
INSERT INTO `device` VALUES ('3', '15151', '60:be:b5:82:06:3e', '192.168.1.93', '1', '1', '2015-03-26 10:30:06', null, '2');

-- ----------------------------
-- Table structure for `device_hardware`
-- ----------------------------
DROP TABLE IF EXISTS `device_hardware`;
CREATE TABLE `device_hardware` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `hardware_version` int(10) NOT NULL COMMENT '中控硬件版本',
  `hardware_name` varchar(20) DEFAULT NULL COMMENT '中控硬件名',
  PRIMARY KEY (`id`),
  KEY `hardware_version` (`hardware_version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device_hardware
-- ----------------------------

-- ----------------------------
-- Table structure for `device_software`
-- ----------------------------
DROP TABLE IF EXISTS `device_software`;
CREATE TABLE `device_software` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `software_version` int(10) NOT NULL COMMENT '中控软件版本',
  `size` varchar(10) NOT NULL COMMENT '版本大小',
  `release_time` datetime NOT NULL COMMENT '版本更新时间',
  `download_url` varchar(100) NOT NULL COMMENT '下载更新地址',
  `info` varchar(100) DEFAULT NULL COMMENT '版本描述信息',
  `software_type` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `device_version` (`software_version`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device_software
-- ----------------------------
INSERT INTO `device_software` VALUES ('4', '20', '23M', '2015-03-20 15:35:46', 'Http://192.168.1.65:8080/', 'version 2.0.6', '1');

-- ----------------------------
-- Table structure for `heart_log`
-- ----------------------------
DROP TABLE IF EXISTS `heart_log`;
CREATE TABLE `heart_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mac_address` varchar(20) NOT NULL COMMENT 'mac地址',
  `upload_time` datetime NOT NULL COMMENT '心跳上报时间',
  PRIMARY KEY (`id`),
  KEY `mac_address` (`mac_address`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of heart_log
-- ----------------------------
INSERT INTO `heart_log` VALUES ('2', '60:be:b5:82:06:3e', '2015-03-30 16:09:03');
INSERT INTO `heart_log` VALUES ('3', '60:be:b5:82:06:3e', '2015-03-30 16:09:23');
INSERT INTO `heart_log` VALUES ('4', '60:be:b5:82:06:3e', '2015-03-30 16:54:24');
INSERT INTO `heart_log` VALUES ('5', '60:be:b5:82:06:3e', '2015-03-30 16:54:35');
INSERT INTO `heart_log` VALUES ('6', '60:be:b5:82:06:3e', '2015-03-30 16:54:44');
INSERT INTO `heart_log` VALUES ('7', '60:be:b5:82:06:3e', '2015-03-30 16:54:54');
INSERT INTO `heart_log` VALUES ('8', '60:be:b5:82:06:3e', '2015-03-30 16:55:05');
INSERT INTO `heart_log` VALUES ('9', '60:be:b5:82:06:3e', '2015-03-30 16:55:18');
INSERT INTO `heart_log` VALUES ('10', '60:be:b5:82:06:3e', '2015-03-30 16:55:28');
INSERT INTO `heart_log` VALUES ('11', '60:be:b5:82:06:3e', '2015-03-30 16:55:38');

-- ----------------------------
-- Table structure for `problem_reason`
-- ----------------------------
DROP TABLE IF EXISTS `problem_reason`;
CREATE TABLE `problem_reason` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `reason_name` varchar(100) NOT NULL COMMENT '故障原因',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of problem_reason
-- ----------------------------
INSERT INTO `problem_reason` VALUES ('3', '15616516516165156');
INSERT INTO `problem_reason` VALUES ('5', '2000000000000000');
INSERT INTO `problem_reason` VALUES ('6', '10000000');

-- ----------------------------
-- Table structure for `repair_records`
-- ----------------------------
DROP TABLE IF EXISTS `repair_records`;
CREATE TABLE `repair_records` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '维修id',
  `account_name` varchar(20) NOT NULL COMMENT '用户id',
  `reason_id` int(10) NOT NULL COMMENT '故障原因',
  `description` varchar(300) DEFAULT NULL COMMENT '故障详细描述',
  `begin_time` datetime DEFAULT NULL COMMENT '维修开始时间',
  `update_time` datetime DEFAULT NULL COMMENT '维修进度更新时间',
  `end_time` datetime DEFAULT NULL COMMENT '维修结束时间',
  `repair_status` int(1) unsigned NOT NULL COMMENT '（1：未开始 2：正在维修 3：已完成）',
  PRIMARY KEY (`id`),
  KEY `account_id` (`account_name`),
  KEY `reason_id` (`reason_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repair_records
-- ----------------------------
INSERT INTO `repair_records` VALUES ('1', '15026615816', '3', '1111', '2015-04-01 10:09:03', null, null, '1');

-- ----------------------------
-- Table structure for `user_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `user_feedback`;
CREATE TABLE `user_feedback` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account_name` varchar(20) NOT NULL COMMENT '用户id',
  `content` varchar(300) DEFAULT NULL COMMENT '用户反馈信息',
  `create_time` datetime NOT NULL,
  `status` int(1) unsigned NOT NULL COMMENT '（1：未读 2：已读）',
  PRIMARY KEY (`id`),
  KEY `account_id` (`account_name`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_feedback
-- ----------------------------
INSERT INTO `user_feedback` VALUES ('3', '1562215615', '2000000', '2015-03-20 13:43:20', '2');
INSERT INTO `user_feedback` VALUES ('4', '1651651651', '11111', '2015-03-20 14:05:26', '1');
INSERT INTO `user_feedback` VALUES ('5', '1898165165', '115613132230320', '2015-03-20 15:30:02', '1');
