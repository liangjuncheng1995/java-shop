/*
Navicat MySQL Data Transfer

Source Server         : cheng
Source Server Version : 100137
Source Host           : localhost:3306
Source Database       : weishop

Target Server Type    : MYSQL
Target Server Version : 100137
File Encoding         : 65001

Date: 2020-12-25 17:44:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for theme
-- ----------------------------
DROP TABLE IF EXISTS `theme`;
CREATE TABLE `theme` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(60) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `tpl_name` varchar(30) DEFAULT NULL,
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `entrance_img` varchar(255) DEFAULT NULL,
  `extend` varchar(255) DEFAULT NULL,
  `internal_top_img` varchar(255) DEFAULT NULL,
  `title_img` varchar(255) DEFAULT NULL,
  `online` tinyint(3) unsigned DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of theme
-- ----------------------------
INSERT INTO `theme` VALUES ('1', '清凉一夏，折扣季', '秋天是金色的。麦穗是金色的，秋阳是金色的。虽然冬快至，但宜人的温度总是让我们心情愉快#我们为您精选了一系列秋冬折扣商品，慢慢挑选吧~', 't-1', '2019-07-18 07:10:59.000', 'janna', '2020-02-25 15:07:01.465', null, null, null, null, null, '1');
INSERT INTO `theme` VALUES ('4', '每周上新', '风袖`每周上新`活动#每周都有一款折扣商品，每周都有适合你的唯一专属#有Ins复古风装饰；金属CD唱片夹；每周来逛逛，找到你所喜爱的东西', 't-2', '2019-07-30 00:00:14.000', null, '2020-02-25 15:07:01.465', null, null, null, null, null, '1');
INSERT INTO `theme` VALUES ('5', '风袖甄选', '甄选', 't-3', '2019-07-30 17:20:23.000', 'diana', '2020-02-25 15:07:01.465', null, null, null, null, null, '1');
INSERT INTO `theme` VALUES ('6', '时尚穿搭', '帅点才有女朋友', 't-4', '2019-08-01 02:43:18.000', 'irelia', '2020-02-25 15:07:01.465', null, null, null, null, null, '1');
INSERT INTO `theme` VALUES ('7', '热卖好评', '林白选的那一定是最好的', 't-5', '2019-08-09 07:19:37.000', 'camille', '2020-02-25 15:07:01.465', null, null, null, null, null, '1');
INSERT INTO `theme` VALUES ('8', '热门推荐', null, 't-6', '2019-09-10 11:43:06.000', 'camille', '2020-02-25 15:07:01.465', null, null, null, null, null, '1');
INSERT INTO `theme` VALUES ('10', null, null, null, '2019-10-10 16:59:43.363', null, '2019-10-10 16:59:43.363', null, null, null, null, null, '1');
INSERT INTO `theme` VALUES ('11', null, null, null, '2020-02-06 21:49:43.994', null, '2020-02-06 21:49:43.994', null, null, null, null, null, '1');
