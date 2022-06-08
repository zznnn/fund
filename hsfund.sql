/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : hsfund

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 28/05/2022 16:29:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bank
-- ----------------------------
DROP TABLE IF EXISTS `bank`;
CREATE TABLE `bank`  (
  `bank_account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行卡号:6225-7600-08219524',
  `client_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户id',
  `bank_current_balance` decimal(10, 4) NULL DEFAULT NULL COMMENT '卡内余额',
  PRIMARY KEY (`bank_account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bank_jour
-- ----------------------------
DROP TABLE IF EXISTS `bank_jour`;
CREATE TABLE `bank_jour`  (
  `bank_jour_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行卡流水号',
  `bank_account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡号',
  `bank_change` decimal(10, 4) NULL DEFAULT NULL COMMENT '变化金额',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client`  (
  `client_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '证件号',
  `client_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户姓名',
  `client_gender` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `client_age` tinyint(1) NULL DEFAULT NULL COMMENT '客户年龄',
  `client_organ_flag` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构标志',
  `client_id_kind` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件类型',
  `client_phnumber` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `client_risk` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户风险等级',
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for date
-- ----------------------------
DROP TABLE IF EXISTS `date`;
CREATE TABLE `date`  (
  `date_id` int(11) NOT NULL COMMENT '日期数据编号',
  `date_time` date NOT NULL COMMENT '日期',
  PRIMARY KEY (`date_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fund
-- ----------------------------
DROP TABLE IF EXISTS `fund`;
CREATE TABLE `fund`  (
  `fund_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品代码',
  `fund_name` varchar(160) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `fund_netvalue` decimal(10, 4) NULL DEFAULT NULL COMMENT '产品净值',
  `fund_risk` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品风险等级',
  `fund_birth` date NULL DEFAULT NULL COMMENT '产品发行日期',
  `fund_trustee` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '基金托管人',
  `fund_manager` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '基金经理',
  `fund_currency` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易币种',
  `fund_others` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其他',
  PRIMARY KEY (`fund_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase`  (
  `purchase_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申购流水号',
  `client_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户id',
  `bank_account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡号',
  `fund_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '基金号',
  `purchase_date` date NULL DEFAULT NULL COMMENT '交易日期',
  `purchase_count` decimal(10, 4) NULL DEFAULT NULL COMMENT '交易金额',
  `purchase_share` decimal(10, 4) NULL DEFAULT NULL COMMENT '对应份额',
  `purchase_status` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请状态',
  PRIMARY KEY (`purchase_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for redem
-- ----------------------------
DROP TABLE IF EXISTS `redem`;
CREATE TABLE `redem`  (
  `redem_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '赎回申请号',
  `client_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户id',
  `bank_account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡号 ',
  `fund_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '基金号',
  `redem_date` date NULL DEFAULT NULL COMMENT '交易日期',
  `redem_share` decimal(10, 4) NULL DEFAULT NULL COMMENT '赎回份额',
  `redem_count` decimal(10, 4) NULL DEFAULT NULL COMMENT '实际收益',
  `redem_current_share` decimal(10, 4) NULL DEFAULT NULL COMMENT '剩余份额',
  `redem_estimated_date` date NULL DEFAULT NULL COMMENT '预计到账日期',
  `redem_status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '赎回申请状态',
  PRIMARY KEY (`redem_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for trend_record
-- ----------------------------
DROP TABLE IF EXISTS `trend_record`;
CREATE TABLE `trend_record`  (
  `trend_record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '净值变化记录id，最大为4294967295',
  `fund_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品代码',
  `fund_netvalue` decimal(10, 4) NULL DEFAULT NULL COMMENT '产品净值',
  `date_time` date NULL DEFAULT NULL COMMENT '工作日期',
  PRIMARY KEY (`trend_record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `date`(`date_id`, `date_time`) VALUES (1, '2022-05-26');


INSERT INTO `fund` VALUES ('000001', '华夏成长混合', 3.5509, '中高风险', '2001-12-18', '华夏基金', '王泽实', '人民币', '暂无评级');
INSERT INTO `fund` VALUES ('000003', '中海可转债债券A', 1.0291, '中风险', '2013-03-20', '中海基金', '章俊', '人民币', '基金评级1星');
INSERT INTO `fund` VALUES ('001000', '中欧明睿新起点混合', 1.7121, '中高风险', '2015-01-29', '中欧基金', '葛兰', '人民币', '基金评级3星');
INSERT INTO `fund` VALUES ('001373', '易方达新丝路灵活配置混合', 1.8912, '中高风险', '2015-05-27', '易方达基金', '祁禾', '人民币', '基金评级3星');
INSERT INTO `fund` VALUES ('002029', '安信动态策略混合C', 1.5973, '中风险', '2015-11-17', '安信基金', '袁玮', '人民币', '基金评级2星');
INSERT INTO `fund` VALUES ('002477', '博时安瑞18个月定开债C', 1.4123, '中低风险', '2016-03-30', '博时基金', '李汉楠', '人民币', '暂无评级');
INSERT INTO `fund` VALUES ('002705', '邦德锐兴债券C', 1.0987, '中风险', '2005-12-08', '姜晓丽', '盛丰衍', '人民币', '暂无评级');
INSERT INTO `fund` VALUES ('003096', '中欧医疗健康混合C', 2.6716, '中高风险', '2016-09-29', '中欧基金', '葛兰', '人民币', '基金评级3星');
INSERT INTO `fund` VALUES ('003494', '富国天惠成长混合C', 2.9024, '中高风险', '2017-03-23', '富国基金', '朱少醒', '人民币', '基金评级3星');
INSERT INTO `fund` VALUES ('005693', '广发中证军工ETF联接C', 1.0098, '低风险', '2000-05-31', '毛从容', '李童', '人民币', '暂无评级');
INSERT INTO `fund` VALUES ('005827', '易方达蓝筹精选混合', 1.8846, '中高风险', '2018-09-05', '易方达基金', '张坤', '人民币', '暂无评级');
INSERT INTO `fund` VALUES ('006228', '中欧医疗创新股票A', 1.7383, '高风险', '2019-02-28', '中欧基金', '葛兰', '人民币', '暂无评级');
INSERT INTO `fund` VALUES ('006598', '国泰利享中短债债券C', 4.7830, '高风险', '2018-09-18', '马龙', '罗英语', '人民币', '暂无评级');
INSERT INTO `fund` VALUES ('009617', '东兴兴利债券C', 1.8342, '高风险', '2007-11-17', '武磊', '曾彭', '人民币', '基金评级1星');
INSERT INTO `fund` VALUES ('010340', '易方达高质量严选三年持有', 0.9040, '中高风险', '2020-11-12', '易方达基金', '萧楠', '人民币', '暂无评级');
INSERT INTO `fund` VALUES ('070009', '嘉实超短债C', 4.0900, '中风险', '2013-09-07', '姜晓丽', '李金灿', '人民币', '暂无评级');
INSERT INTO `fund` VALUES ('110001', '易方达平稳增长混合', 4.7400, '中风险', '2002-08-23', '王斌', '侯昊', '港币', '暂无评级');
INSERT INTO `fund` VALUES ('110011', '易方达优质精选混合(QDII)', 7.5528, '高风险', '2008-06-19', '易方达基金', '张坤', '人民币', '基金评级5星');
INSERT INTO `fund` VALUES ('110013', '易方达科翔混合', 4.4880, '中风险', '2008-11-13', '陈皓', '姜文', '人民币', '暂无评级');
INSERT INTO `fund` VALUES ('112414', '招商中证白酒指数C', 2.0981, '低风险', '2003-09-08', '过蓓蓓', '张慧', '人民币', '基金评级3星');
INSERT INTO `fund` VALUES ('161005', '富国天惠成长混合A/B(LOF)', 5.7442, '中高风险', '2005-11-16', '富国基金', '朱少醒', '人民币', '基金评级3星');
INSERT INTO `fund` VALUES ('161716', '招商双债增强债券(LOF)C', 2.0983, '中风险', '2006-09-13', '姜晓丽', '陈蒙', '港币', '暂无评级');
INSERT INTO `fund` VALUES ('161725', '招商中证白酒指数(LOF)A', 1.0886, '高风险', '2009-08-07', '王海峰', '侯昊', '人民币', '基金评级1星');
INSERT INTO `fund` VALUES ('211103', '天弘中证光伏产业指数C', 4.7823, '低风险', '2001-12-09', '过蓓蓓', '张慧', '人民币', '基金评级3星');
INSERT INTO `fund` VALUES ('217022', '招商产业债券A', 2.9801, '高风险', '2015-09-13', '马龙', '李梦', '人民币', '基金评级3星');
INSERT INTO `fund` VALUES ('260108', '景顺长城新兴成长混合', 3.1200, '中风险', '2012-07-09', '叶帅', '李童', '人民币', '基金评级2星');
INSERT INTO `fund` VALUES ('400015', '东方新能源汽车主题混合', 3.3836, '低风险', '2001-02-03', '王海峰', '刘笑明', '人民币', '暂无评级');
INSERT INTO `fund` VALUES ('400912', '华夏国证半导体芯片ETC联接C', 2.9876, '中风险', '2009-12-08', '赵宗庭', '李童', '人民币', '基金评级1星');
INSERT INTO `fund` VALUES ('420102', '天弘永利债券B', 3.4000, '高风险', '2019-12-31', '武磊', '肖瑞晋', '人民币', '基金评级3星');
INSERT INTO `fund` VALUES ('501058', '汇添富中证新能源汽车产业指数(LOF)C', 3.0002, '低风险', '2005-08-23', '过蓓蓓', '张慧', '人民币', '基金评级3星');

SET FOREIGN_KEY_CHECKS = 1;

