/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : video_download

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 25/04/2021 14:03:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for al_file
-- ----------------------------
DROP TABLE IF EXISTS `al_file`;
CREATE TABLE `al_file`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `qr_code_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二维码文件地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of al_file
-- ----------------------------
INSERT INTO `al_file` VALUES ('819e711c2e964bd7881c2880e7b03827', 'http://192.168.2.184:8080/video/test.mp4', 'http://192.168.2.184:8080/myFile/819e711c2e964bd7881c2880e7b03827.jpg');
INSERT INTO `al_file` VALUES ('84a085a6f9194980a07b9b3fc5336521', 'http://192.168.2.184:8080/fff.mp4', 'http://192.168.2.184:8080/myFile/84a085a6f9194980a07b9b3fc5336521.jpg');

SET FOREIGN_KEY_CHECKS = 1;
