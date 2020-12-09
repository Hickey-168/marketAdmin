/*
 Navicat Premium Data Transfer

 Source Server         : Mysql_local
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : supermarket

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 09/12/2020 16:28:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '文章内容',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `delete_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否有效  1.有效  2无效',
  `goods_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发布号作者表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (5, '学生小明', '2020-11-28 09:08:45', '2020-12-08 00:06:39', '1', 'coca_cola');
INSERT INTO `orders` VALUES (6, '学生小红', '2020-11-30 10:49:28', '2020-12-08 00:07:19', '1', 'tea');
INSERT INTO `orders` VALUES (10, '学生小明', '2020-12-01 14:57:45', '2020-12-08 00:08:04', '1', 'chips');
INSERT INTO `orders` VALUES (11, '学生小明', '2020-12-03 15:23:42', '2020-12-08 10:59:20', '1', 'yogurt');
INSERT INTO `orders` VALUES (19, '学生小红', '2020-12-06 13:37:07', '2020-12-08 10:54:57', '1', 'nike');
INSERT INTO `orders` VALUES (21, '学生小明', '2020-12-08 10:28:17', '2020-12-08 11:03:16', '1', 'milk');
INSERT INTO `orders` VALUES (22, '学生小红', '2020-12-08 10:38:30', '2020-12-08 11:03:20', '1', 'ice-cream');
INSERT INTO `orders` VALUES (23, '学生小张', '2020-12-08 10:41:11', '2020-12-08 11:03:24', '1', 'banana');
INSERT INTO `orders` VALUES (24, '学生小花', '2020-12-08 10:58:30', '2020-12-08 11:03:27', '1', 'apple');
INSERT INTO `orders` VALUES (27, '学生小黑', '2020-12-08 11:08:04', '2020-12-08 11:12:03', '1', 'notebook');
INSERT INTO `orders` VALUES (28, '学生小李', '2020-12-08 11:14:54', '2020-12-08 11:15:04', '1', 'pen');
INSERT INTO `orders` VALUES (29, '学生小蓝', '2020-12-08 11:15:42', '2020-12-08 11:15:42', '1', 'mask');
INSERT INTO `orders` VALUES (30, '学生小绿', '2020-12-08 11:26:15', '2020-12-08 23:33:42', '1', 'tea');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL DEFAULT 0 COMMENT '自定id,主要供前端展示权限列表分类排序使用.',
  `menu_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '归属菜单,前端判断并展示菜单使用,',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单的中文释义',
  `permission_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '权限的代码/通配符,对应代码中@RequiresPermissions 的value',
  `permission_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '本权限的中文释义',
  `required_permission` tinyint(1) NULL DEFAULT 2 COMMENT '是否本菜单必选权限, 1.必选 2非必选 通常是\"列表\"权限是必选',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (101, 'order', '订单管理', 'order:list', '列表', 1);
INSERT INTO `sys_permission` VALUES (102, 'order', '订单管理', 'order:add', '新增', 2);
INSERT INTO `sys_permission` VALUES (103, 'order', '订单管理', 'order:update', '修改', 2);
INSERT INTO `sys_permission` VALUES (601, 'user', '用户', 'user:list', '列表', 1);
INSERT INTO `sys_permission` VALUES (602, 'user', '用户', 'user:add', '新增', 2);
INSERT INTO `sys_permission` VALUES (603, 'user', '用户', 'user:update', '修改', 2);
INSERT INTO `sys_permission` VALUES (701, 'role', '角色权限', 'role:list', '列表', 1);
INSERT INTO `sys_permission` VALUES (702, 'role', '角色权限', 'role:add', '新增', 2);
INSERT INTO `sys_permission` VALUES (703, 'role', '角色权限', 'role:update', '修改', 2);
INSERT INTO `sys_permission` VALUES (704, 'role', '角色权限', 'role:delete', '删除', 2);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `delete_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否有效  1有效  2无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '经理', '2020-11-18 16:24:34', '2020-12-07 22:28:14', '1');
INSERT INTO `sys_role` VALUES (2, '学生客户', '2020-11-18 16:24:34', '2020-12-07 22:28:21', '1');
INSERT INTO `sys_role` VALUES (3, '代理商', '2020-11-18 16:28:47', '2020-12-08 01:50:27', '1');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` int(11) NULL DEFAULT NULL COMMENT '权限id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `delete_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否有效 1有效     2无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色-权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1, 101, '2020-12-07 16:26:21', '2020-12-07 22:27:45', '2');
INSERT INTO `sys_role_permission` VALUES (2, 1, 102, '2020-12-07 16:26:21', '2020-12-07 22:27:46', '2');
INSERT INTO `sys_role_permission` VALUES (5, 1, 602, '2020-12-07 16:28:28', '2020-12-08 22:56:58', '2');
INSERT INTO `sys_role_permission` VALUES (6, 1, 601, '2020-12-07 16:28:28', '2020-12-07 22:27:48', '1');
INSERT INTO `sys_role_permission` VALUES (7, 1, 603, '2020-12-07 16:28:28', '2020-12-07 22:27:48', '1');
INSERT INTO `sys_role_permission` VALUES (8, 1, 703, '2020-12-07 16:28:28', '2020-12-07 22:27:49', '1');
INSERT INTO `sys_role_permission` VALUES (9, 1, 701, '2020-12-07 16:28:28', '2020-12-07 22:27:51', '1');
INSERT INTO `sys_role_permission` VALUES (10, 1, 702, '2020-12-07 16:28:28', '2020-12-07 22:27:52', '1');
INSERT INTO `sys_role_permission` VALUES (11, 1, 704, '2020-12-07 16:28:31', '2020-12-07 22:27:52', '2');
INSERT INTO `sys_role_permission` VALUES (12, 1, 103, '2020-12-07 16:28:31', '2020-12-08 01:50:40', '2');
INSERT INTO `sys_role_permission` VALUES (13, 3, 601, '2020-12-07 16:28:47', '2020-12-07 22:26:13', '1');
INSERT INTO `sys_role_permission` VALUES (14, 3, 701, '2020-12-07 16:28:47', '2020-12-07 22:26:18', '2');
INSERT INTO `sys_role_permission` VALUES (15, 3, 702, '2020-12-07 16:35:01', '2020-12-07 22:26:21', '2');
INSERT INTO `sys_role_permission` VALUES (16, 3, 704, '2020-12-07 16:35:01', '2020-12-07 22:26:24', '2');
INSERT INTO `sys_role_permission` VALUES (17, 3, 102, '2020-12-07 16:35:01', '2020-12-09 00:18:18', '2');
INSERT INTO `sys_role_permission` VALUES (18, 3, 101, '2020-12-07 16:35:01', '2020-12-09 00:18:18', '2');
INSERT INTO `sys_role_permission` VALUES (19, 3, 603, '2020-12-07 16:35:01', '2020-12-09 00:18:18', '2');
INSERT INTO `sys_role_permission` VALUES (20, 1, 101, '2020-12-07 22:23:45', '2020-12-07 22:23:45', '1');
INSERT INTO `sys_role_permission` VALUES (21, 1, 102, '2020-12-07 22:23:45', '2020-12-07 22:23:45', '1');
INSERT INTO `sys_role_permission` VALUES (22, 1, 103, '2020-12-07 22:23:45', '2020-12-08 22:56:58', '2');
INSERT INTO `sys_role_permission` VALUES (23, 1, 601, '2020-12-07 22:23:45', '2020-12-07 22:23:45', '1');
INSERT INTO `sys_role_permission` VALUES (24, 1, 602, '2020-12-07 22:23:45', '2020-12-08 22:56:58', '2');
INSERT INTO `sys_role_permission` VALUES (25, 1, 603, '2020-12-07 22:23:45', '2020-12-07 22:23:45', '1');
INSERT INTO `sys_role_permission` VALUES (26, 1, 701, '2020-12-07 22:23:45', '2020-12-07 22:23:45', '1');
INSERT INTO `sys_role_permission` VALUES (27, 1, 702, '2020-12-07 22:23:45', '2020-12-07 22:23:45', '1');
INSERT INTO `sys_role_permission` VALUES (28, 1, 703, '2020-12-07 22:23:45', '2020-12-07 22:23:45', '1');
INSERT INTO `sys_role_permission` VALUES (29, 1, 704, '2020-12-07 22:23:45', '2020-12-08 22:56:58', '2');
INSERT INTO `sys_role_permission` VALUES (30, 3, 702, '2020-12-09 00:15:43', '2020-12-09 00:18:18', '2');
INSERT INTO `sys_role_permission` VALUES (31, 3, 701, '2020-12-09 00:15:43', '2020-12-09 00:18:18', '2');
INSERT INTO `sys_role_permission` VALUES (32, 3, 703, '2020-12-09 00:15:43', '2020-12-09 00:18:18', '2');
INSERT INTO `sys_role_permission` VALUES (33, 3, 602, '2020-12-09 00:15:43', '2020-12-09 00:18:18', '2');
INSERT INTO `sys_role_permission` VALUES (34, 3, 101, '2020-12-09 01:20:03', '2020-12-09 01:20:03', '1');
INSERT INTO `sys_role_permission` VALUES (35, 3, 102, '2020-12-09 01:20:03', '2020-12-09 01:20:03', '1');
INSERT INTO `sys_role_permission` VALUES (36, 3, 103, '2020-12-09 01:20:03', '2020-12-09 01:20:03', '1');
INSERT INTO `sys_role_permission` VALUES (37, 1, 103, '2020-12-09 01:20:22', '2020-12-09 01:20:22', '1');
INSERT INTO `sys_role_permission` VALUES (38, 1, 602, '2020-12-09 01:20:22', '2020-12-09 01:20:22', '1');
INSERT INTO `sys_role_permission` VALUES (39, 1, 704, '2020-12-09 01:20:22', '2020-12-09 01:20:22', '1');
INSERT INTO `sys_role_permission` VALUES (40, 2, 101, '2020-12-09 01:20:31', '2020-12-09 01:20:31', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `role_id` int(11) NULL DEFAULT 0 COMMENT '角色ID',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `delete_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否有效  1有效  2无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10008 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '运营后台用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (10003, 'admin', '123456', '超市经理', 1, '2020-11-19 11:55:38', '2020-12-07 22:24:55', '1');
INSERT INTO `sys_user` VALUES (10004, 'xiaoming', '123456', '学生小明', 2, '2020-11-30 16:13:02', '2020-12-08 23:50:03', '1');
INSERT INTO `sys_user` VALUES (10005, 'agent', '123456', '进货员小张', 3, '2020-11-21 14:02:56', '2020-12-08 01:35:12', '1');
INSERT INTO `sys_user` VALUES (10007, 'xiaohua', '123456', '学生小花', 2, '2020-11-30 16:29:41', '2020-12-08 23:50:09', '1');

SET FOREIGN_KEY_CHECKS = 1;
