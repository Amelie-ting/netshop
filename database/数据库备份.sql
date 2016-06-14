/*
Navicat MySQL Data Transfer

Source Server         : new
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : inventory_management

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2016-06-14 10:10:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `a_id` int(100) NOT NULL AUTO_INCREMENT,
  `a_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `a_password` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `a_phone` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`a_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '黄鼎梅', '123', null);
INSERT INTO `admin` VALUES ('2', '何孟奇', '124', null);

-- ----------------------------
-- Table structure for `cartitem`
-- ----------------------------
DROP TABLE IF EXISTS `cartitem`;
CREATE TABLE `cartitem` (
  `cartitemid` char(32) COLLATE utf8_bin NOT NULL,
  `orderby` int(32) NOT NULL AUTO_INCREMENT,
  `itemid` int(100) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `uid` int(32) DEFAULT NULL,
  PRIMARY KEY (`orderby`),
  KEY `orderid` (`orderby`)
) ENGINE=MyISAM AUTO_INCREMENT=44538 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of cartitem
-- ----------------------------
INSERT INTO `cartitem` VALUES ('F307AD7679414900856074E4025F1FE7', '44536', '37', '1', '46');
INSERT INTO `cartitem` VALUES ('1C56E582AC2747809C58504E47453CE6', '44535', '45', '1', '46');
INSERT INTO `cartitem` VALUES ('F4D81A66C4F54E58B6E987D730764100', '44537', '24', '1', '46');

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `ca_id` varchar(50) COLLATE utf8_bin NOT NULL,
  `ca_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ca_pid` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `orderBy` int(50) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ca_id`),
  KEY `orderby` (`orderBy`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('5', '连衣裙', '1', '20');
INSERT INTO `category` VALUES ('4', 'T恤', '1', '19');
INSERT INTO `category` VALUES ('3', '童装', null, '18');
INSERT INTO `category` VALUES ('2', '男装', null, '17');
INSERT INTO `category` VALUES ('1', '女装', null, '16');
INSERT INTO `category` VALUES ('6', 'T恤', '2', '21');
INSERT INTO `category` VALUES ('7', '儿童男装', '3', '22');
INSERT INTO `category` VALUES ('8', '儿童女装', '3', '23');

-- ----------------------------
-- Table structure for `invent`
-- ----------------------------
DROP TABLE IF EXISTS `invent`;
CREATE TABLE `invent` (
  `invid` int(50) NOT NULL AUTO_INCREMENT,
  `innumb` int(100) DEFAULT NULL,
  `outnumb` int(100) DEFAULT NULL,
  `mid` int(100) DEFAULT NULL,
  `indate` date DEFAULT NULL,
  `outdate` date DEFAULT NULL,
  `catypeid` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`invid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of invent
-- ----------------------------

-- ----------------------------
-- Table structure for `items`
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `item_id` int(100) NOT NULL AUTO_INCREMENT,
  `item_wid` int(11) DEFAULT NULL,
  `item_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `item_price` decimal(8,2) DEFAULT NULL,
  `item_gdate` date DEFAULT NULL,
  `item_caid` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `item_descn` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `item_pic` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `purprice` decimal(8,2) DEFAULT NULL,
  `barcode` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `item_stock` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=MyISAM AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of items
-- ----------------------------
INSERT INTO `items` VALUES ('24', '1', 'LUXLEAD洛诗琳 新款钉珠一字领拼接蕾丝网布 修身连衣裙 藏蓝色 L', '279.00', '2016-04-08', '5', '商品产地：中国大陆 | 领型：圆领 | 图案：纯色 | 流行元素：镶钻 | 衣门襟：套头 | 袖长：无袖 | 风格：OL通勤 | 适用年龄：30-34周岁 | 裙型：A字裙 | 版型：修身型', 'item_img/E795C2A38A1445DCB431AA1A3D2B58B2_1.jpg', '128.00', null, null);
INSERT INTO `items` VALUES ('26', '1', '含蕴 2016春夏新款连衣裙两件套女装修身背心裙中袖欧根纱开衫套装 灰色 L', '98.00', '2016-04-08', '5', '领型：圆领 | 质地：锦纶 | 组合形式：两件套 | 袖型：其它 | 腰型：中腰 | 袖长：无袖 | 裙长：短裙（76-90cm）| 风格：OL通勤 | 版型：修身型', 'item_img/2749B9C1358F4F46AE601E982A93C565_2.jpg', '30.00', null, null);
INSERT INTO `items` VALUES ('27', '1', '韩都衣舍 韩版2016夏装新款女装百搭显瘦牛仔半身裙OZ5524② 蓝色 S', '133.00', '2016-04-08', '5', '货号：OZ5524 | 尺码：S | 腰型：中腰 | 裙长：短裙 | 流行元素：简约 | 材质：棉', 'item_img/6568D53C11FE4FF6B868AC5B2D157AB8_3.jpg', '50.00', null, null);
INSERT INTO `items` VALUES ('28', '1', '晞薇 连衣裙2016春夏装新款女装包臀中裙修身通勤OL职业七分短袖显瘦印花一步裙8016 短袖 XL', '159.00', '2016-04-08', '5', '货号：8016 | 尺码：M | 裙型：包臀裙 | 类型：连衣裙 | 领型：圆领 | 质地：棉 | 组合形式：单件 |风格：OL通勤 | 版型：修身型 | 裙长：中裙（91-105cm） | 腰型：中腰 | 袖长：七分袖', 'item_img/C642E50B52BE427387F7008DBA851336_4.jpg', '100.00', null, null);
INSERT INTO `items` VALUES ('29', '1', '丽尚佰莲2016雪纺连衣裙夏季无袖打底背心裙子 1606# 红色', '158.00', '2016-04-08', '5', '货号：L1606 | 腰型：中腰 | 风格：休闲 | 领型：圆领  | 质地：其他 | 颜色：白色 | 适用年龄：25-29周岁  | 图案：花色 | 适用类型：约会  | 尺码：M  | 裙型：荷叶边裙  | 类型：连衣裙 | 流行元素：印花  | 组合形式：单件 | 衣门襟：拉链 | 版型：修身型 | 袖型：常规 | 裙长：短裙（76-90cm） | 袖长：无袖 | 款式：常规', 'item_img/A36A72DE792F4E93A8AD50C2CD41B35A_5.jpg', '89.00', null, null);
INSERT INTO `items` VALUES ('30', '1', '衣与草春装2016春夏新款女装修身雪纺印花百褶中长款连衣裙气质公主A字裙夏装6154 白色', '198.00', '2016-04-08', '5', '货号：16Y6154 | 尺码：S | 版型：修身型 | 类型：连衣裙 | 领型：圆领 | 质地：其它 | 颜色：白色系 | 组合形式：单件 | 袖型：其它 | 裙型：公主裙 | 袖长：短袖 | 裙长：中长裙（106-125cm） | 分类：套装裙 | 腰型：高腰', 'item_img/FC7F255312ED483CBE3F31465FC2E9F7_6.jpg', '90.00', null, null);
INSERT INTO `items` VALUES ('31', '1', '夏知麦 2016夏季新款女装 时尚套装A字裙大码雪纺印花蕾丝连衣裙夏（7224） 白色(短袖)', '159.00', '2016-04-08', '5', '货号：NR2-b31-8808-60 | 尺码：其它 | 版型：修身型 | 类型：连衣裙 | 领型：圆领 | 质地：其它 | 颜色：粉色系 | 组合形式：单件 | 袖型：公主袖 | 腰型：中腰 | 袖长：长袖 | 裙长：中长裙（106-125cm） | 分类：套装裙 | 风格：OL通勤', 'item_img/B1376D8540D541DB90FDDEC64AA3EF1C_7.jpg', '69.00', null, null);
INSERT INTO `items` VALUES ('32', '1', '亿齐创2016夏装新款印花连衣裙显瘦喇叭袖松紧腰裙子 黑色 均码', '30.00', '2016-04-08', '5', '货号：F1058 9065 | 腰型：松紧腰 | 风格：日韩 | 领型：圆领 | 质地：醋纤 | 颜色：白色 | 适用年龄：25-29周岁 | 图案：其它 | 适用类型：约会 | 尺码：均码 | 裙型：其他 | 类型：连衣裙 | 流行元素：露肩 | 组合形式：单件 | 衣门襟：套头 | 版型：直筒型 | 裙长：短裙（76-90cm） | 袖长：短袖 | 款式：常规', 'item_img/68FE9EC2D1C8476E81684ACCE3195022_8.jpg', '10.00', null, null);
INSERT INTO `items` VALUES ('33', '1', '魅力衣仕2016春夏新款女装时尚新品修身蕾丝印花裙子修身百褶沙滩裙显瘦雪纺连衣裙 玫紫色', '179.00', '2016-04-08', '5', '货号：MLYS045 | 尺码：L | 版型：修身型 | 类型：连衣裙 | 领型：方领 | 质地：其它 | 颜色：紫色系 | 组合形式：单件 | 袖型：其它 | 风格：OL通勤 | 袖长：短袖 | 裙长：短裙（76-90cm） | 裙型：不规则 | 腰型：高腰', 'item_img/523D2088B3AF4C7D881CE42F234AB6DC_9.jpg', '80.00', null, null);
INSERT INTO `items` VALUES ('34', '1', '森马短袖T恤夏装新款女士印花姐妹装镶钻白色打底衫韩版女 本白', '59.90', '2016-04-08', '4', '货号：19215000807 | 材质：棉/丝光棉 | 版型：直筒型 | 类型：T恤 | 领型：圆领 | 流行元素：印花 | 尺码：其它', 'item_img/0C63AC6BB7F945EDB47091DACFA77EE1_T1.jpg', '30.00', null, null);
INSERT INTO `items` VALUES ('35', '1', '七米一家短袖t恤女2016夏装新款民族风女装纯棉蕾丝T8676# 灰色', '79.00', '2016-04-08', '4', '货号：8676 | 尺码：其它 | 颜色：其它 | 风格：日韩 | 类型：T恤 | 领型：圆领 | 图案文化：其他 | 衣长：常规款 | 面料：布 | 材质：棉/丝光棉 | 袖型：短袖 | 适用年龄：25-29周岁 | 版型：修身型 | 图案：其它 | 流行元素：其它 | 上市时间：2016春季 | 适用类型：旅游', 'item_img/5B8E32A8F88247B9A50481D2D8F46244_T2.jpg', '40.00', null, null);
INSERT INTO `items` VALUES ('36', '1', '艾路丝婷2016夏装新款女装大象印花体恤圆领修身短袖棉T恤女3376 灰色', '89.00', '2016-04-08', '4', '货号：TX3376 | 材质：其它 | 天然纤维 | 版型：其它 | 类型：T恤 | 领型：圆领 | 流行元素：亮片 | 尺码：XL', 'item_img/9776D23428AE4757B1D8728232FA992F_T3.jpg', '30.00', null, null);
INSERT INTO `items` VALUES ('37', '1', '韩贝伊都短袖T恤女春季新品韩版大码上衣蕾丝拼接短袖纯色棉圆领T5002 圆领白色', '79.00', '2016-04-10', '4', '货号：T5002 | 尺码：M | 颜色：白色 | 风格：日韩 | 类型：T恤 | 领型：圆领 | 图案文化：其他 | 衣长：常规款 | 面料：棉 | 材质：棉/丝光棉 | 袖型：短袖 | 适用年龄：25-29周岁 | 版型：修身型 | 图案：纯色 | 流行元素：简约 | 上市时间：2016春季 | 适用类型：职场', 'item_img/6324ECA8F7444733A3E94C14E6DC1FE1_T4.jpg', '40.00', null, null);
INSERT INTO `items` VALUES ('38', '1', '露玫雅T恤女2016春季新款韩版文艺宽松胖mm大码中长款短袖T恤女9018 粉红色', '109.00', '2016-04-08', '4', '货号：BH4-431-D-9018 | 尺码：M | 颜色：粉色 | 风格：OL通勤 | 类型：T恤 | 领型：圆领 | 图案文化：其他 | 衣长：中长款 | 面料：其他 | 材质：棉/丝光棉 | 袖型：短袖 | 适用年龄：25-29周岁 | 版型：宽松型 | 图案：纯色 | 流行元素：其它 | 上市时间：2016春季 | 适用类型：职场', 'item_img/56135012283B4C87A4E46C3747D8DE87_T5.jpg', '50.00', null, null);
INSERT INTO `items` VALUES ('39', '1', '陌曦2016韩版条纹宽松圆领短袖T恤女', '49.00', '2016-04-08', '4', '货号：BH500 | 尺码：其它 | 衣长：其它 | 类型：T恤 | 领型：其它 | 流行元素：其它 | 颜色：白色系 | 材质：锦纶 | 袖型：其它 | 版型：其它 | 袖长：中袖', 'item_img/C8DF794561A04A58BF1D070F42F5EF21_T6.jpg', '20.00', null, null);
INSERT INTO `items` VALUES ('40', '2', 'AFS JEEP战地吉普夏季新款短袖T恤男棉宽松V领男装半袖大码2015新款 8627白色', '59.00', '2016-04-08', '6', '货号：8627-3147 | 尺码：M，L，XL，XXL，XXXL | 版型：宽松型（腰围>胸围）| 类型：T恤 | 领型：V领 | 流行元素：简约 | 颜色：其他 | 适用场景：休闲 | 袖型：短袖 | 人群：青年 | 主要材质：棉 | 图案：纯色 | 工艺：免烫 | 风格：商务休闲', 'item_img/BF7DB52445D140879B8ECBD836B6DAC2_m1.jpg', '30.00', null, null);
INSERT INTO `items` VALUES ('41', '1', '森马短袖T恤衫 2016夏装新款 男士图案印花男装针织衫韩版潮T学生 深蓝色 L', '75.90', '2016-04-08', '6', '货号：19215001802 | 人群：青年 | 风格：青春休闲 | 类型：T恤 | 领型：圆领 | 颜色：其他 | 袖型：短袖 | 版型：标准型（腰围=胸围） | 图案：其他 | 工艺： 印花 | 主要材质：棉', 'item_img/70FA0E7C200C48C98D9790F7BEE95B7F_m2.jpg', '40.00', null, null);
INSERT INTO `items` VALUES ('42', '1', '真维斯男装 2016夏装新款舒适休闲圆领短袖T恤', '32.90', '2016-04-08', '6', '货号：JE-60-173001 | 人群：青年 | 风格：青春休闲 | 类型：T恤 | 领型：圆领 | 流行元素：简约 | 颜色：其他 | 袖型：短袖 | 图案：纯色 | 版型：修身型 | 主要材质：其他', 'item_img/EDD57664287A429A9ED872914E7F72E2_m3.jpg', '20.00', null, null);
INSERT INTO `items` VALUES ('43', '3', '七范2016年夏季新款童装男童套装休闲运动夏装中大童儿童套装短袖T恤裤子男孩两件套 蓝颜色 160/建议身高145cm-155cm', '69.00', '2016-04-08', '7', '货号：T91038 | 配送：第三方物流 | 风格：休闲 | 类型：短袖 裤子 | 性别：男童 | 选购热点：运动休闲 | 面料：混纺 |薄厚：薄款 | 衣门襟：套头 | 品质险：无 | 季节：夏季 | 件数：2件 | 身高：100，110，120，130，140，150，160 | 是否带帽子：无', 'item_img/35D07FBBDEC14F2FB65A45275D063CD1_c1.jpg', '40.00', null, null);
INSERT INTO `items` VALUES ('44', '3', '小馒头 童装女童套装春秋儿童三件套中大童韩版休闲弹力衣服 女童长款灰色 140码建议身高130cm', '129.00', '2016-04-08', '8', '货号：NT8038 | 配送：第三方物流 | 风格：公主 | 类型：三件套 | 性别：女童 | 选购热点：运动休闲 | 面料：纯棉（95%） | 薄厚：常规 | 衣门襟：拉链衫 | 品质险：有 | 季节：春秋季 | 件数：3件 | 身高：110，120，130，140，150，160 | 是否带帽子：无', 'item_img/40FF27B4D5DF47209878E895683F387E_c2.jpg', '40.00', null, null);
INSERT INTO `items` VALUES ('45', '3', '美沫童装男童套装女童春装儿童套装宝宝卫衣裤子运动休闲两件套春夏季新款2016 M29 大脸短袖 100CM', '88.00', '2016-04-08', '7', '货号：M29 | 配送：第三方物流 | 风格：运动 | 类型：长袖 裤子 | 性别：中性 | 选购热点：运动休闲 | 面料：棉混纺布 | 薄厚：常规 | 衣门襟：拉链衫 | 品质险：无 | 季节：春秋季 | 件数：2件 | 身高：73，80，90，100 | 是否带帽子：无', 'item_img/67636A4655C2444A884A28B1E5E8F383_c3.jpg', '40.00', null, null);
INSERT INTO `items` VALUES ('46', '1', '测试一', '15.00', '2016-04-08', '4', '1', 'item_img/7E7C8F3C4C18492D95AD74390807F067_2749B9C1358F4F46AE601E982A93C565_2.jpg', '15.00', '1', '1');

-- ----------------------------
-- Table structure for `maufact`
-- ----------------------------
DROP TABLE IF EXISTS `maufact`;
CREATE TABLE `maufact` (
  `mid` int(100) NOT NULL AUTO_INCREMENT,
  `mname` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `madress` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `mtel` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `mrstore` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `mbankno` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of maufact
-- ----------------------------

-- ----------------------------
-- Table structure for `myorder`
-- ----------------------------
DROP TABLE IF EXISTS `myorder`;
CREATE TABLE `myorder` (
  `oid` varchar(32) COLLATE utf8_bin NOT NULL,
  `ouid` int(255) DEFAULT NULL,
  `odate` datetime DEFAULT NULL,
  `uevaluation` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `addrid` int(25) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of myorder
-- ----------------------------

-- ----------------------------
-- Table structure for `orderitem`
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `orderItemId` int(100) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) DEFAULT NULL,
  `subtotal` decimal(8,2) DEFAULT NULL,
  `itemid` int(32) DEFAULT NULL,
  `itemname` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `currPrice` decimal(8,2) DEFAULT NULL,
  `image` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `oid` char(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`orderItemId`)
) ENGINE=MyISAM AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of orderitem
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_id` int(255) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `u_password` varchar(25) COLLATE utf8_bin DEFAULT NULL,
  `u_nickname` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`u_id`),
  KEY `c_id` (`u_id`)
) ENGINE=MyISAM AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('46', '张三', '111', '小三');

-- ----------------------------
-- Table structure for `useraddress`
-- ----------------------------
DROP TABLE IF EXISTS `useraddress`;
CREATE TABLE `useraddress` (
  `a_id` int(100) NOT NULL AUTO_INCREMENT,
  `a_uid` int(10) DEFAULT NULL,
  `a_uname` varchar(25) COLLATE utf8_bin DEFAULT NULL,
  `a_state` varchar(25) COLLATE utf8_bin DEFAULT NULL,
  `a_city` varchar(25) COLLATE utf8_bin DEFAULT NULL,
  `a_county` varchar(25) COLLATE utf8_bin DEFAULT NULL,
  `a_addr` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `a_tel` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `a_zip` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `a_isDefault` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`a_id`)
) ENGINE=MyISAM AUTO_INCREMENT=83 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of useraddress
-- ----------------------------
INSERT INTO `useraddress` VALUES ('81', '46', '李四', '省份', '地级市', '市、县级市', '湘潭市湘潭县湖南科技大学-西南门', '16673380334', '535111', '1');
INSERT INTO `useraddress` VALUES ('80', '46', '张三', '湖南省', '湘潭市', '湘潭县', '湘潭市湘潭县湖南科技大学北门', '16673380337', '535222', '2');

-- ----------------------------
-- Table structure for `warehouse`
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse` (
  `wid` int(100) NOT NULL,
  `wname` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `wtype` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `wadminid` int(100) DEFAULT NULL,
  PRIMARY KEY (`wid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES ('1', '湖南', '大', null);
INSERT INTO `warehouse` VALUES ('2', '广西', '小', null);
