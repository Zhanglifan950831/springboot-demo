/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : gift_card

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-05-19 09:15:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for card_info
-- ----------------------------
DROP TABLE IF EXISTS `card_info`;
CREATE TABLE `card_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `card_id` varchar(30) DEFAULT NULL COMMENT '礼品卡id',
  `theme_id` int(10) DEFAULT NULL COMMENT '主题id',
  `amount` int(10) DEFAULT NULL COMMENT '礼品卡金额(单位：分）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of card_info
-- ----------------------------
INSERT INTO `card_info` VALUES ('1', 'pD-TFwPytZFGBA8JqSBKXbUHAOBo', '1', '1');
INSERT INTO `card_info` VALUES ('2', 'pD-TFwIADjCkImlEBU3C0fRlGl7g', '1', '2');
INSERT INTO `card_info` VALUES ('3', 'pD-TFwJ4Q0rghq6ZPvO7b_vkvGkg', '3', '2');
INSERT INTO `card_info` VALUES ('4', 'pD-TFwNbvuNxVBQJyy5eai25hyl4', '1', '11');
INSERT INTO `card_info` VALUES ('5', 'pD-TFwI8tS9jeRszCKBeuhsTuuXg', '1', '3');
INSERT INTO `card_info` VALUES ('6', 'pD-TFwEhn6UGzntOQfFOJnsJxnFw', '1', '6');
INSERT INTO `card_info` VALUES ('7', 'pD-TFwK8461WtUjQ95VQ4Ajxwj-4', '4', '10');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '分类名称',
  `sub_title` varchar(20) DEFAULT NULL COMMENT '子标题',
  `check_status` int(2) DEFAULT NULL COMMENT '审核状态（1：待审核；2：审核通过；3、审核不通过；4、上架；5、下架）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='分类表';

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '测试礼品卡', '好物随心选 心意随时达', '2');
INSERT INTO `category` VALUES ('2', '测试品牌卡', '称心好礼，送TA所爱', '2');

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `order_status` int(2) DEFAULT '1' COMMENT '订单状态',
  `total_fee` int(10) DEFAULT NULL COMMENT '费用(单位：分）',
  `order_no` varchar(20) DEFAULT NULL COMMENT '订单号',
  `openid` varchar(30) DEFAULT NULL COMMENT '购买人微信id',
  `send_status` int(2) DEFAULT '0' COMMENT '礼品卡赠送状态',
  `order_time` bigint(20) DEFAULT NULL COMMENT '下单时间',
  `card_pic` varchar(150) DEFAULT NULL COMMENT '卡面',
  `theme_id` int(10) DEFAULT NULL COMMENT '主题id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('1', '2', '1', '18042500001', 'AQAAzlV2e7EvjeoKBg_kxcB4-bdQ', '0', '1524646300', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0', '1');
INSERT INTO `order_info` VALUES ('2', '1', '6', '18042500002', null, '0', '1524646778', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0', '1');
INSERT INTO `order_info` VALUES ('3', '1', '18', '18042500003', null, '0', '1524650142', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8tz9lK8Q07Hrs62H2KX6KYy3PInQWEHthv0r5RCgBzRR3rcSlIjic2L3ibodeoWFqrH46qicKlK047vA/0', '1');
INSERT INTO `order_info` VALUES ('4', '1', '12', '18042600001', null, '0', '1524708046', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0', '1');
INSERT INTO `order_info` VALUES ('5', '1', '1', '18042600002', null, '0', '1524708738', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0', '1');
INSERT INTO `order_info` VALUES ('6', '1', '1', '18042600003', null, '0', '1524722453', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0', '1');
INSERT INTO `order_info` VALUES ('7', '1', '1', '18042600004', null, '0', '1524722471', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0', '1');
INSERT INTO `order_info` VALUES ('8', '1', '1', '18042600005', null, '0', '1524722597', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0', '1');
INSERT INTO `order_info` VALUES ('9', '1', '1', '18042600006', null, '0', '1524722664', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0', '1');
INSERT INTO `order_info` VALUES ('10', '1', '1', '18042600007', null, '0', '1524722842', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0', '1');
INSERT INTO `order_info` VALUES ('11', '1', '1', '18042600008', null, '0', '1524725079', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0', '1');
INSERT INTO `order_info` VALUES ('12', '1', '1', '18042600009', null, '0', '1524725107', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0', '1');
INSERT INTO `order_info` VALUES ('13', '1', '1', '18042600010', null, '0', '1524725491', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0', '1');
INSERT INTO `order_info` VALUES ('14', '1', '6', '18042600011', null, '0', '1524726247', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0', '1');
INSERT INTO `order_info` VALUES ('15', '1', '10', '18042600012', null, '0', '1524726307', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0', '4');
INSERT INTO `order_info` VALUES ('16', '1', '0', '18042700001', null, '0', '1524793400', null, '1');

-- ----------------------------
-- Table structure for order_info_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_info_detail`;
CREATE TABLE `order_info_detail` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `order_id` int(10) DEFAULT NULL COMMENT '订单id',
  `amount` int(10) DEFAULT NULL COMMENT '金额',
  `send_state` int(2) DEFAULT NULL COMMENT '赠送状态',
  `card_name` varchar(20) DEFAULT NULL COMMENT '卡名称',
  `card_no` varchar(20) DEFAULT NULL COMMENT '卡号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_info_detail
-- ----------------------------
INSERT INTO `order_info_detail` VALUES ('1', '1', '1', '0', '创纪云礼品卡', null);
INSERT INTO `order_info_detail` VALUES ('2', '1', '3', '0', '创纪云礼品卡', null);
INSERT INTO `order_info_detail` VALUES ('3', '1', '6', '0', '创纪云礼品卡', null);
INSERT INTO `order_info_detail` VALUES ('4', '1', '6', '0', '创纪云礼品卡', null);
INSERT INTO `order_info_detail` VALUES ('5', '1', '1', '0', '创纪云礼品卡', null);
INSERT INTO `order_info_detail` VALUES ('6', '1', '1', '0', '创纪云礼品卡', null);
INSERT INTO `order_info_detail` VALUES ('7', '1', '1', '0', '创纪云礼品卡', null);
INSERT INTO `order_info_detail` VALUES ('8', '1', '1', '0', '创纪云礼品卡', null);
INSERT INTO `order_info_detail` VALUES ('9', '1', '1', '0', '创纪云礼品卡', null);
INSERT INTO `order_info_detail` VALUES ('10', '1', '1', '0', '创纪云礼品卡', null);
INSERT INTO `order_info_detail` VALUES ('11', '1', '1', '0', '创纪云礼品卡', null);
INSERT INTO `order_info_detail` VALUES ('12', '1', '1', '0', '创纪云礼品卡', null);
INSERT INTO `order_info_detail` VALUES ('13', '1', '1', '0', '创纪云礼品卡', null);
INSERT INTO `order_info_detail` VALUES ('14', '1', '6', '0', '创纪云礼品卡', null);
INSERT INTO `order_info_detail` VALUES ('15', '1', '10', '0', '创纪云礼品卡', null);
INSERT INTO `order_info_detail` VALUES ('16', '1', '1', '0', '创纪云礼品卡', null);

-- ----------------------------
-- Table structure for page_shelf
-- ----------------------------
DROP TABLE IF EXISTS `page_shelf`;
CREATE TABLE `page_shelf` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `page_id` varchar(50) DEFAULT NULL COMMENT '货架id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of page_shelf
-- ----------------------------

-- ----------------------------
-- Table structure for pic_resource
-- ----------------------------
DROP TABLE IF EXISTS `pic_resource`;
CREATE TABLE `pic_resource` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pic_url` varchar(150) DEFAULT NULL COMMENT '微信CDN返回图片url',
  `description` varchar(20) DEFAULT NULL COMMENT '图片描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='图片资源表';

-- ----------------------------
-- Records of pic_resource
-- ----------------------------
INSERT INTO `pic_resource` VALUES ('1', 'https://mmbiz.qlogo.cn/mmbiz/p98FjXy8LaeMq67mEpDmkj05EtiaVcGOibVaVux3Agib1ibcHFkCoic7HuQWFawx9XGCNWIO085drjdxTib2nBHlYGAA/0?wx_fmt=gif', 'logo');
INSERT INTO `pic_resource` VALUES ('2', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0', '感谢有你卡面1');
INSERT INTO `pic_resource` VALUES ('3', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8tz9lK8Q07Hrs62H2KX6KYy3PInQWEHthv0r5RCgBzRR3rcSlIjic2L3ibodeoWFqrH46qicKlK047vA/0', '感谢有你卡面2');

-- ----------------------------
-- Table structure for theme_card_price_config
-- ----------------------------
DROP TABLE IF EXISTS `theme_card_price_config`;
CREATE TABLE `theme_card_price_config` (
  `id` int(10) NOT NULL,
  `theme_id` int(10) DEFAULT NULL COMMENT '主题id',
  `price` int(5) DEFAULT NULL COMMENT '卡面金额'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主题卡面金额配置表';

-- ----------------------------
-- Records of theme_card_price_config
-- ----------------------------
INSERT INTO `theme_card_price_config` VALUES ('1', '1', '10');
INSERT INTO `theme_card_price_config` VALUES ('2', '1', '20');
INSERT INTO `theme_card_price_config` VALUES ('3', '1', '30');
INSERT INTO `theme_card_price_config` VALUES ('4', '2', '20');
INSERT INTO `theme_card_price_config` VALUES ('5', '2', '40');
INSERT INTO `theme_card_price_config` VALUES ('6', '2', '60');
INSERT INTO `theme_card_price_config` VALUES ('7', '3', '5');
INSERT INTO `theme_card_price_config` VALUES ('8', '3', '10');
INSERT INTO `theme_card_price_config` VALUES ('9', '3', '15');
INSERT INTO `theme_card_price_config` VALUES ('10', '4', '2');
INSERT INTO `theme_card_price_config` VALUES ('11', '4', '4');
INSERT INTO `theme_card_price_config` VALUES ('12', '4', '6');

-- ----------------------------
-- Table structure for theme_config
-- ----------------------------
DROP TABLE IF EXISTS `theme_config`;
CREATE TABLE `theme_config` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL COMMENT '主题名称',
  `theme_pic_url` varchar(125) DEFAULT NULL COMMENT '主题图片',
  `category_id` int(10) DEFAULT NULL COMMENT '分类id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='主题配置表';

-- ----------------------------
-- Records of theme_config
-- ----------------------------
INSERT INTO `theme_config` VALUES ('1', '感谢有你', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0', '1');
INSERT INTO `theme_config` VALUES ('2', '为你点赞', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8tz9lK8Q07Hrs62H2KX6KYy3PInQWEHthv0r5RCgBzRR3rcSlIjic2L3ibodeoWFqrH46qicKlK047vA/0', '1');
INSERT INTO `theme_config` VALUES ('3', '狗年旺旺', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8tz9lK8Q07Hrs62H2KX6KYy3PInQWEHthv0r5RCgBzRR3rcSlIjic2L3ibodeoWFqrH46qicKlK047vA/0', '2');
INSERT INTO `theme_config` VALUES ('4', '喜悦相伴', 'http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0', '2');
