/*
 Navicat Premium Data Transfer

 Source Server         : mysql8
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : materials_management

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 08/11/2023 19:37:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for delmaterial
-- ----------------------------
DROP TABLE IF EXISTS `delmaterial`;
CREATE TABLE `delmaterial`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `materialId` int(11) NULL DEFAULT NULL,
  `userId` int(11) NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `delTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `isDel` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of delmaterial
-- ----------------------------
INSERT INTO `delmaterial` VALUES (2, 2, 1, '信息错误', '2023-11-06 09:52:01', '0');

-- ----------------------------
-- Table structure for issuematerial
-- ----------------------------
DROP TABLE IF EXISTS `issuematerial`;
CREATE TABLE `issuematerial`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `materialId` int(11) NULL DEFAULT NULL,
  `userId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `donationSite` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `receiver` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '接收人',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '接收人手机号',
  `destination` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '目的地',
  `sendTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发出时间',
  `hash` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '',
  `transactionHash` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '',
  `isExamine` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '0' COMMENT '1审核通过，0未审核',
  `isDel` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '0' COMMENT '1删除，0未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of issuematerial
-- ----------------------------
INSERT INTO `issuematerial` VALUES (1, 1, '1', '江软大学', '新东方', '15707862554', '山东', '2023-11-01 09:35:49', '0x1fdd18429037595dd7f0c094a31104df95257e8b71ff96d00dbab246f3f73371', '0x21704bf61b32c11cf3d413fa663e47446585e2c164883077d5fa6bc41e2302c9', '1', '0');
INSERT INTO `issuematerial` VALUES (3, 4, '1', '井冈山', '万方', '15707863556', '南昌', '2023-11-01 15:00:01', '0xc2caeac9c6708c48806158e8ebe069eca4dfc3a5423c2b5250e75b33d0699048', '0x85253cd3972b67da379ad708323f1bceab6e46f51efd9181533702ee337df28d', '1', '0');
INSERT INTO `issuematerial` VALUES (4, 3, '1', '吉安', '维普', '15508766254', '景德镇', '2023-11-01 15:00:44', '', '', '0', '0');

-- ----------------------------
-- Table structure for logmaterial
-- ----------------------------
DROP TABLE IF EXISTS `logmaterial`;
CREATE TABLE `logmaterial`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `recorder` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '记录员',
  `userId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `materialId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `currentLocation` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '当前所在地',
  `receiveTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '到达时间',
  `logTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '记录时间',
  `preHash` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '',
  `hash` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '',
  `transactionHash` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '',
  `isDel` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of logmaterial
-- ----------------------------
INSERT INTO `logmaterial` VALUES (1, 'admin', '2', '1', '15607962445', '南昌', '2023-11-01 09:48:09', '2023-11-01 09:48:24', '', '0x3d13b8901695d0f739cb4096b18ffccca4e81b9e78ab2189d43a236c365f1f3a', '0x10ff1b04f347e755f74112bec5e91633499ef43b230808ce1a5c3f8f30b4eeb9', '0');
INSERT INTO `logmaterial` VALUES (2, 'admin', '2', '1', '15607962445', '山东', '2023-11-01 14:35:25', '2023-11-01 14:35:32', '', '0xffcc2cd51d614ac5a6f0d62fc628648a5c027a50ca57cf9215fd3d35f214a454', '0x3afd0e27d4322b7d5b1d3d4ab04136c95070adefe1fa633f321a4ce13620ca95', '0');
INSERT INTO `logmaterial` VALUES (3, 'admin', '2', '4', '15607962445', '樟树', '2023-11-01 15:06:43', '2023-11-01 15:06:52', '', '', '0xd9a37cd20abf47201aad2a102afb172fa4eea56c36409cbc63b5af27d6bba7ff', '0');

-- ----------------------------
-- Table structure for materialsinfo
-- ----------------------------
DROP TABLE IF EXISTS `materialsinfo`;
CREATE TABLE `materialsinfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '物资ID，自动增长',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '物资名称，不允许为空',
  `userId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '购买者ID',
  `specifications` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '规格',
  `detail` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '物资详情',
  `dateOfManufacture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '生产日期',
  `supplier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `buyTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '购买时间',
  `img` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '图片',
  `isState` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '0' COMMENT '0未发放，1运输中，2已到达',
  `isOk` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '0' COMMENT '0未到达资助地，1到达资助地',
  `isDel` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '0' COMMENT '0未删除，1删除',
  `examine` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '0待审核，1审核通过',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `Unique_MaterialName`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of materialsinfo
-- ----------------------------
INSERT INTO `materialsinfo` VALUES (1, '高品质皮革手提包', '1', '3', '这款手提包采用优质牛皮制成，精心手工制作。它具有时尚的设计，内部有多个隔层，可容纳手机、钱包和文件。适合商务和休闲场合，是您日常携带的理想选择。', '2023-10-06 00:00:00', '江软', '2023-11-01 09:29:21', 'http://192.168.8.246:8000/images/1698802039224.webp,http://192.168.8.246:8000/images/1698802042946.webp,', '2', '0', '0', '1');
INSERT INTO `materialsinfo` VALUES (2, '不锈钢厨房刀具套装', '1', '3', '这套厨房刀具包括多种刀具，如厨师刀、切片刀、剁肉刀等，均由高质量的不锈钢制成，具有出色的切割性能。符合卫生标准，适用于各种烹饪需求。', '2023-10-18 00:00:00', '江软', '2023-11-01 09:30:14', 'http://192.168.8.246:8000/images/1698802186852.jpg,', '0', '0', '0', '');
INSERT INTO `materialsinfo` VALUES (3, '抗菌纤维毛巾', '1', '3', '这款毛巾采用抗菌纤维制成，有效抑制细菌滋生。柔软吸水，适用于浴室、厨房和健身房等多种场合。耐用且易清洗，保持卫生。', '2023-10-19 00:00:00', '江软', '2023-11-01 09:31:00', 'http://192.168.8.246:8000/images/1698802233760.webp,', '0', '0', '0', '0');
INSERT INTO `materialsinfo` VALUES (4, '智能蓝牙音响', '1', '1', '这款音响配备了蓝牙连接功能，可轻松连接手机、平板电脑和其他蓝牙设备。具有清晰的声音和强大的低音效果，是户外活动和家庭娱乐的理想音响选择。', '2023-10-11 00:00:00', '井冈山数字产业园', '2023-11-01 09:31:43', 'http://192.168.8.246:8000/images/1698802273060.jpg,http://192.168.8.246:8000/images/1698802276982.webp,', '1', '0', '0', '1');
INSERT INTO `materialsinfo` VALUES (5, '救援帐篷', '1', '6', '这款应急救援帐篷是紧急情况下的理想避难所。它易于携带和快速设置，可提供遮风挡雨，保护受灾群众免受恶劣天气的影响。坚固的材质和防水设计确保持久的使用。', '2023-10-10 00:00:00', '井冈山数字产业园', '2023-11-01 09:32:38', 'http://192.168.8.246:8000/images/1698802337891.webp,', '0', '0', '0', '');
INSERT INTO `materialsinfo` VALUES (6, '食品供应包', '1', '4', '我们的应急食品供应包含高热量、长保质期的食品，可满足受灾人员的基本营养需求。这些包装紧凑，易于分发，适用于各种应急情况，如自然灾害或人道主义援助。', '2023-10-13 00:00:00', '井冈山数字产业园', '2023-11-01 09:33:22', 'http://192.168.8.246:8000/images/1698802362977.jpg,http://192.168.8.246:8000/images/1698802367228.webp,http://192.168.228.246:8000/images/1698802371364.webp,', '0', '0', '0', '');
INSERT INTO `materialsinfo` VALUES (7, '急救包', '1', '3', '这个急救包包括救护所需的紧急医疗用品，如绷带、止血剂、消毒物品和急救手册。它设计精巧，便于携带，有助于迅速处理受伤者，减轻伤害严重性。', '2023-10-05 00:00:00', '井冈山数字产业园', '2023-11-01 09:34:30', 'http://192.168.8.246:8000/images/1698802445153.jpg,', '0', '0', '0', '');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `state` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '0' COMMENT '0未停用，1停用',
  `isDel` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 'personalInfo', '0', '0');
INSERT INTO `menu` VALUES (2, 'buyMaterial', '0', '0');
INSERT INTO `menu` VALUES (3, 'myMaterial', '0', '0');
INSERT INTO `menu` VALUES (4, 'searchMaterial', '0', '0');
INSERT INTO `menu` VALUES (5, 'material', '0', '0');
INSERT INTO `menu` VALUES (6, 'distributeMaterial', '0', '0');
INSERT INTO `menu` VALUES (7, 'materialDetail', '0', '0');
INSERT INTO `menu` VALUES (8, 'materialTrace', '0', '0');
INSERT INTO `menu` VALUES (9, 'userManage', '0', '0');
INSERT INTO `menu` VALUES (10, 'admin', '0', '0');

-- ----------------------------
-- Table structure for operatelog
-- ----------------------------
DROP TABLE IF EXISTS `operatelog`;
CREATE TABLE `operatelog`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `isDel` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operatelog
-- ----------------------------
INSERT INTO `operatelog` VALUES (1, 1, '2023-11-01 09:36:51', '高品质皮革手提包		物资审核通过,已上链', '0');
INSERT INTO `operatelog` VALUES (2, 1, '2023-11-01 09:48:24', '高品质皮革手提包		物资状态已更新,当前所在地:		南昌', '0');
INSERT INTO `operatelog` VALUES (3, 1, '2023-11-01 14:35:32', '高品质皮革手提包		物资状态已更新,当前所在地:		山东', '0');
INSERT INTO `operatelog` VALUES (4, 1, '2023-11-01 14:58:19', '急救包		物资请求删除待审核', '0');
INSERT INTO `operatelog` VALUES (5, 1, '2023-11-01 14:58:28', '急救包		物资审不核通过，删除失败', '0');
INSERT INTO `operatelog` VALUES (6, 1, '2023-11-01 14:59:01', '急救包		物资审核不通过', '0');
INSERT INTO `operatelog` VALUES (7, 1, '2023-11-01 15:01:15', '智能蓝牙音响		物资审核通过,已上链', '0');
INSERT INTO `operatelog` VALUES (8, 1, '2023-11-01 15:04:58', '智能蓝牙音响		物资审核通过,已上链', '0');
INSERT INTO `operatelog` VALUES (9, 1, '2023-11-01 15:06:52', '智能蓝牙音响		物资状态已更新,当前所在地:		樟树', '0');
INSERT INTO `operatelog` VALUES (10, 1, '2023-11-01 15:17:50', '扣除信誉分:		1分', '0');
INSERT INTO `operatelog` VALUES (11, 1, '2023-11-06 09:52:01', '不锈钢厨房刀具套装		物资请求删除待审核', '0');
INSERT INTO `operatelog` VALUES (12, 1, '2023-11-08 10:14:48', '账户被冻结', '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员');
INSERT INTO `role` VALUES (2, '普通用户');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NULL DEFAULT NULL,
  `menuId` int(11) NULL DEFAULT NULL,
  `isDel` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 2, 1, '0');
INSERT INTO `role_menu` VALUES (2, 2, 2, '0');
INSERT INTO `role_menu` VALUES (3, 2, 3, '0');
INSERT INTO `role_menu` VALUES (4, 2, 4, '0');
INSERT INTO `role_menu` VALUES (5, 2, 5, '0');
INSERT INTO `role_menu` VALUES (6, 2, 6, '0');
INSERT INTO `role_menu` VALUES (7, 2, 7, '0');
INSERT INTO `role_menu` VALUES (8, 2, 8, '0');
INSERT INTO `role_menu` VALUES (9, 1, 1, '0');
INSERT INTO `role_menu` VALUES (10, 1, 3, '0');
INSERT INTO `role_menu` VALUES (11, 1, 5, '0');
INSERT INTO `role_menu` VALUES (12, 1, 6, '0');
INSERT INTO `role_menu` VALUES (13, 1, 9, '0');
INSERT INTO `role_menu` VALUES (14, 1, 10, '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `gender` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '',
  `birthDate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `address` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `creditScore` int(3) NULL DEFAULT 100,
  `roleId` int(2) NULL DEFAULT 2,
  `isDel` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '0',
  `violationRecords` int(11) NULL DEFAULT 0 COMMENT '违规次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '佳期如梦', '$2a$10$/VnVfVll6oAAc9cT7/ZEGOUor7o8XtIGRqDbLpQDSmltxFwLaadx6', '男', '2001-08-21T16:00:00.000Z', '2334310165@qq.com', '15707962446', 'http://192.168.8.246:8000/images/1698369192133.jpg', '江西软件职业技术大学区块链学院', 90, 2, '0', 3);
INSERT INTO `user` VALUES (2, 'admin', '$2a$10$/VnVfVll6oAAc9cT7/ZEGOUor7o8XtIGRqDbLpQDSmltxFwLaadx6', '男', '2001-08-21T16:00:00.000Z', '1@qq.com', '15607962445', 'http://192.168.8.246:8000/images/1698369153940.jpg', '江西软件职业技术大学区块链学院', 100, 1, '0', 0);

SET FOREIGN_KEY_CHECKS = 1;
