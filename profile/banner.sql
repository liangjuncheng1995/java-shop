/*
Navicat MySQL Data Transfer

Source Server         : cheng
Source Server Version : 100137
Source Host           : localhost:3306
Source Database       : weishop

Target Server Type    : MYSQL
Target Server Version : 100137
File Encoding         : 65001

Date: 2020-12-25 18:06:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for banner1
-- ----------------------------
DROP TABLE IF EXISTS `banner1`;
CREATE TABLE `banner1` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL COMMENT '部分banner1可能有标题图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of banner1
-- ----------------------------
INSERT INTO `banner1` VALUES ('1', 'b-1', '首页顶部主banner1', '2019-07-28 04:47:15.000', '2019-08-04 01:03:16.000', null, null, null);
INSERT INTO `banner1` VALUES ('2', 'b-2', '热销商品banner1', '2019-08-01 00:37:47.000', '2019-09-20 00:56:45.843', null, null, null);
