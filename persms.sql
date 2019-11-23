/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : persms

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2019-11-23 13:53:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `deptno` int(11) NOT NULL auto_increment,
  `dname` varchar(14) NOT NULL,
  `loc` varchar(13) default NULL,
  PRIMARY KEY  (`deptno`),
  UNIQUE KEY `dname` (`dname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('10', 'ACCOUNTING', 'NEW YORK');
INSERT INTO `dept` VALUES ('20', 'RESEARCH', 'DALLAS');
INSERT INTO `dept` VALUES ('30', 'SALES', 'CHICAGO');
INSERT INTO `dept` VALUES ('40', '研发中心', '武汉');
INSERT INTO `dept` VALUES ('61', '研发部', '北京');
INSERT INTO `dept` VALUES ('63', '运营中心', '北京');

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `empno` int(11) NOT NULL auto_increment,
  `ename` varchar(20) NOT NULL,
  `job` varchar(10) NOT NULL,
  `sal` decimal(10,2) default NULL,
  `comm` decimal(10,2) default NULL,
  `hiredate` date default NULL,
  `mgr` int(11) default NULL,
  `deptno` int(11) default NULL,
  PRIMARY KEY  (`empno`),
  KEY `deptno` (`deptno`),
  KEY `mgr` (`mgr`),
  CONSTRAINT `emp_ibfk_1` FOREIGN KEY (`deptno`) REFERENCES `dept` (`deptno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES ('7521', 'WARD', 'SALESMAN', '100000.00', '400.00', '2017-03-01', '7782', '30');
INSERT INTO `emp` VALUES ('7782', 'CLARK', 'MANAGER', '100000.00', '400.00', '2016-10-11', '7839', '30');
INSERT INTO `emp` VALUES ('7839', 'King', 'CEO', '100000.00', null, '2017-03-23', null, null);
INSERT INTO `emp` VALUES ('7840', 'Tom', 'MANAGER', '100000.00', '200.00', '2017-02-15', '7839', '10');
INSERT INTO `emp` VALUES ('7841', 'Allen', 'MANAGER', '100000.00', null, '2017-03-07', '7839', '20');
INSERT INTO `emp` VALUES ('7849', 'Green', 'SALEMAN', '100000.00', '200.00', '2017-03-07', '7782', '30');
INSERT INTO `emp` VALUES ('7851', '王五', 'Manager', '100000.00', null, null, null, null);
INSERT INTO `emp` VALUES ('9001', '张晓菲', '总经理助理', '5000.00', '1000.00', '2011-12-11', '9000', '10');
INSERT INTO `emp` VALUES ('9002', '张晓明', 'MANAGER', '20000.00', '1000.00', '2010-11-20', '9000', '20');
INSERT INTO `emp` VALUES ('9003', '田小军', 'SALESMAN', '20000.00', '1000.00', '2011-01-12', '8882', '30');
INSERT INTO `emp` VALUES ('9004', '小李', '表演艺术家', '3000.00', null, '2017-06-13', null, null);
INSERT INTO `emp` VALUES ('9006', '张三', '搬砖工', '3000.00', '2000.00', '2017-07-29', null, null);
INSERT INTO `emp` VALUES ('9008', 'Tom', '会计', '5000.00', '3000.00', null, null, '10');

-- ----------------------------
-- Table structure for tb_functions
-- ----------------------------
DROP TABLE IF EXISTS `tb_functions`;
CREATE TABLE `tb_functions` (
  `func_id` int(11) NOT NULL auto_increment,
  `func_name` varchar(50) default NULL,
  `func_url` varchar(255) default NULL COMMENT '主要用户菜单的控制，根据不同的用户生成不同的菜单',
  `func_code` varchar(50) default NULL COMMENT 'shiro都是根据权限编码进行权限认证的，故而必须根据登录用户的id,找到它所有的权限编码',
  `parent_id` int(11) default NULL,
  `func_type` int(11) default '1',
  `status` int(11) default '1',
  `sort_num` int(11) default '0',
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`func_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_functions
-- ----------------------------
INSERT INTO `tb_functions` VALUES ('1', '系统管理', null, 'user:add', null, '1', '1', '0', '2017-08-09 21:37:24', null);
INSERT INTO `tb_functions` VALUES ('2', '用户管理', 'userManager.do', 'user:add', '1', '1', '1', '0', '2017-08-09 21:38:02', null);
INSERT INTO `tb_functions` VALUES ('3', '角色管理', 'roleManager.do', 'user:add', '1', '1', '1', '0', '2017-08-09 21:38:24', null);
INSERT INTO `tb_functions` VALUES ('4', '权限管理', 'functionManager.do', 'user:add', '1', '1', '1', '0', '2017-08-09 21:38:50', null);
INSERT INTO `tb_functions` VALUES ('5', '新建', '/system/user/create', 'user:add', '2', '0', '1', '0', '2017-08-09 21:39:29', null);
INSERT INTO `tb_functions` VALUES ('6', '修改', '/system/user/edit', 'system:user:edit', '2', '0', '1', '0', '2017-08-09 21:39:58', null);
INSERT INTO `tb_functions` VALUES ('7', '删除', '/system/user/delete', 'system:user:delete', '2', '0', '1', '0', '2017-08-09 21:40:23', null);
INSERT INTO `tb_functions` VALUES ('8', '详情', '/system/user/detail', 'system:user:detail', '2', '0', '1', '0', '2017-08-09 21:40:48', null);
INSERT INTO `tb_functions` VALUES ('9', '授权', '/system/user/grant', 'system:user:grant', '2', '0', '1', '0', '2017-08-09 21:41:30', null);
INSERT INTO `tb_functions` VALUES ('10', '部门管理', null, null, null, '1', '1', '0', '2019-11-23 13:10:19', null);
INSERT INTO `tb_functions` VALUES ('11', '部门类别', 'department.do', 'dep:add', '10', '1', '1', '0', '2019-11-28 13:11:48', null);
INSERT INTO `tb_functions` VALUES ('12', '员工管理', 'empmanager.do', 'dep:add', '10', '1', '1', '0', '2019-11-26 13:13:25', null);
INSERT INTO `tb_functions` VALUES ('13', '岗位管理', 'positionmanager.do', 'dep:add', '10', '1', '1', '0', '2019-11-26 13:17:20', null);

-- ----------------------------
-- Table structure for tb_roles
-- ----------------------------
DROP TABLE IF EXISTS `tb_roles`;
CREATE TABLE `tb_roles` (
  `role_id` int(11) NOT NULL auto_increment,
  `role_name` varchar(50) default NULL,
  `note` varchar(255) default NULL,
  `system` bit(1) default '\0',
  `status` int(11) default '1',
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_roles
-- ----------------------------
INSERT INTO `tb_roles` VALUES ('1', '系统管理员', '系统超级管理员角色', '\0', '1', '2017-08-09 21:35:09', null);
INSERT INTO `tb_roles` VALUES ('2', '平台管理员', '平台管理员角色，管理平台产品、订单等信息', '\0', '1', '2017-08-09 21:35:41', null);
INSERT INTO `tb_roles` VALUES ('3', '普通会员', '初等级会员', '\0', '1', '2017-08-09 21:36:04', null);
INSERT INTO `tb_roles` VALUES ('4', '中级会员', '消费金额在40000以上的会员', '\0', '1', '2017-08-09 21:36:30', null);

-- ----------------------------
-- Table structure for tb_role_function
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_function`;
CREATE TABLE `tb_role_function` (
  `role_id` int(11) NOT NULL,
  `func_id` int(11) NOT NULL,
  PRIMARY KEY  (`role_id`,`func_id`),
  KEY `func_id` (`func_id`),
  CONSTRAINT `tb_role_function_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `tb_roles` (`role_id`),
  CONSTRAINT `tb_role_function_ibfk_2` FOREIGN KEY (`func_id`) REFERENCES `tb_functions` (`func_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_function
-- ----------------------------
INSERT INTO `tb_role_function` VALUES ('1', '1');
INSERT INTO `tb_role_function` VALUES ('2', '1');
INSERT INTO `tb_role_function` VALUES ('1', '2');
INSERT INTO `tb_role_function` VALUES ('2', '2');
INSERT INTO `tb_role_function` VALUES ('1', '3');
INSERT INTO `tb_role_function` VALUES ('1', '4');
INSERT INTO `tb_role_function` VALUES ('1', '5');
INSERT INTO `tb_role_function` VALUES ('1', '6');
INSERT INTO `tb_role_function` VALUES ('1', '7');
INSERT INTO `tb_role_function` VALUES ('1', '8');
INSERT INTO `tb_role_function` VALUES ('1', '9');
INSERT INTO `tb_role_function` VALUES ('1', '10');
INSERT INTO `tb_role_function` VALUES ('3', '10');
INSERT INTO `tb_role_function` VALUES ('1', '11');
INSERT INTO `tb_role_function` VALUES ('3', '11');
INSERT INTO `tb_role_function` VALUES ('1', '12');
INSERT INTO `tb_role_function` VALUES ('3', '12');
INSERT INTO `tb_role_function` VALUES ('1', '13');
INSERT INTO `tb_role_function` VALUES ('3', '13');

-- ----------------------------
-- Table structure for tb_users
-- ----------------------------
DROP TABLE IF EXISTS `tb_users`;
CREATE TABLE `tb_users` (
  `user_id` int(11) NOT NULL auto_increment,
  `user_name` varchar(20) default NULL,
  `password` varchar(36) default NULL,
  `salt` varchar(36) default NULL,
  `phone` varchar(13) default NULL,
  `email` varchar(100) default NULL,
  `status` int(11) default '1',
  `note` varchar(255) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_users
-- ----------------------------
INSERT INTO `tb_users` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'A371827A84C04C49A152242EE7DBA7EC', '15688125399', 'admin@zhidisoft.com', '1', '无', '2017-08-09 21:32:04', null);
INSERT INTO `tb_users` VALUES ('2', 'adminPT', 'e10adc3949ba59abbe56e057f20f883e', 'AB96C33B40304962A7897A3FE73C9DED', '18900125688', 'adminPT@zhidisoft.com', '1', '无', '2017-08-09 21:32:47', null);
INSERT INTO `tb_users` VALUES ('3', 'hellokitty', 'e10adc3949ba59abbe56e057f20f883e', 'D76B927144084191B642ACDCC95F807A', '15788125390', 'hellokitty@zhidisoft.com', '1', '无', '2017-08-09 21:33:46', null);
INSERT INTO `tb_users` VALUES ('4', 'tmall', 'e10adc3949ba59abbe56e057f20f883e', null, '15900123456', 'tmall@zhidisoft.com', '1', '无', '2017-08-09 21:34:36', null);
INSERT INTO `tb_users` VALUES ('5', 'wangxiaoming', 'e10adc3949ba59abbe56e057f20f883e', null, '15678901239', 'wangxiaoming@zhidisoft.com', '1', null, '2017-08-24 23:13:08', null);

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY  (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `tb_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_users` (`user_id`),
  CONSTRAINT `tb_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `tb_roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('1', '1');
INSERT INTO `tb_user_role` VALUES ('1', '2');
INSERT INTO `tb_user_role` VALUES ('2', '2');
INSERT INTO `tb_user_role` VALUES ('2', '3');
INSERT INTO `tb_user_role` VALUES ('4', '3');
