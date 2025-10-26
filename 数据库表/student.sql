/*
 Navicat Premium Data Transfer

 Source Server         : LY
 Source Server Type    : MySQL
 Source Server Version : 80038
 Source Host           : localhost:3306
 Source Schema         : javaee

 Target Server Type    : MySQL
 Target Server Version : 80038
 File Encoding         : 65001

 Date: 26/10/2025 14:13:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sid` int(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  `class_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '王芳', '女', 19, 2);
INSERT INTO `student` VALUES (2, '李明', '男', 20, 4);
INSERT INTO `student` VALUES (3, '张强', '男', 20, 2);
INSERT INTO `student` VALUES (4, '李梅', '女', 21, 3);
INSERT INTO `student` VALUES (5, '钱飞', '男', 22, 4);
INSERT INTO `student` VALUES (6, '李四', '男', 19, 4);
INSERT INTO `student` VALUES (7, 'Jerry', '女', 21, 2);

SET FOREIGN_KEY_CHECKS = 1;
