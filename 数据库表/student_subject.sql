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

 Date: 26/10/2025 14:14:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student_subject
-- ----------------------------
DROP TABLE IF EXISTS `student_subject`;
CREATE TABLE `student_subject`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '中间表主键',
  `sid` int(0) NOT NULL COMMENT '学生ID（关联student表的sid）',
  `subid` int(0) NOT NULL COMMENT '课程ID（关联subject表的subid）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_student_subject`(`sid`, `subid`) USING BTREE,
  INDEX `subid`(`subid`) USING BTREE,
  CONSTRAINT `student_subject_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `student_subject_ibfk_2` FOREIGN KEY (`subid`) REFERENCES `subject` (`subid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生选课关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_subject
-- ----------------------------
INSERT INTO `student_subject` VALUES (1, 1, 1);
INSERT INTO `student_subject` VALUES (2, 1, 3);
INSERT INTO `student_subject` VALUES (3, 2, 1);
INSERT INTO `student_subject` VALUES (4, 2, 2);
INSERT INTO `student_subject` VALUES (5, 3, 2);
INSERT INTO `student_subject` VALUES (6, 3, 3);
INSERT INTO `student_subject` VALUES (7, 4, 1);
INSERT INTO `student_subject` VALUES (8, 5, 2);
INSERT INTO `student_subject` VALUES (9, 5, 3);
INSERT INTO `student_subject` VALUES (10, 6, 1);
INSERT INTO `student_subject` VALUES (11, 6, 2);
INSERT INTO `student_subject` VALUES (12, 7, 3);

SET FOREIGN_KEY_CHECKS = 1;
