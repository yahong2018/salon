/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.101.59
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 192.168.101.59:3306
 Source Schema         : salon

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 30/03/2019 11:04:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attendance_sheet
-- ----------------------------
DROP TABLE IF EXISTS `attendance_sheet`;
CREATE TABLE `attendance_sheet` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stuff_id` bigint(20) NOT NULL,
  `attendance_time` datetime NOT NULL,
  `address` varchar(255) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_attendance_sheet_01` (`stuff_id`),
  KEY `idx_attendance_sheet_02` (`address`),
  KEY `idx_attendance_sheet_03` (`attendance_time`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COMMENT='签到表';

-- ----------------------------
-- Records of attendance_sheet
-- ----------------------------
BEGIN;
INSERT INTO `attendance_sheet` VALUES (1, 1, '2019-03-01 09:58:21', '常平店', '2019-03-01 09:58:21', 1, NULL, NULL, 0);
INSERT INTO `attendance_sheet` VALUES (2, 1, '2019-03-01 10:01:19', '常平店', '2019-03-01 10:01:19', 1, NULL, NULL, 0);
INSERT INTO `attendance_sheet` VALUES (3, 1, '2019-03-04 07:51:45', '常平店', '2019-03-04 07:51:45', 1, NULL, NULL, 0);
INSERT INTO `attendance_sheet` VALUES (4, 1, '2019-03-04 07:53:31', '常平店', '2019-03-04 07:53:31', 1, NULL, NULL, 0);
INSERT INTO `attendance_sheet` VALUES (39, 1, '2019-03-28 12:44:18', '在广东省泗安医院东城门诊部附近', '2019-03-28 12:44:23', 1, NULL, NULL, 0);
INSERT INTO `attendance_sheet` VALUES (47, 1, '2019-03-29 16:47:18', '', '2019-03-29 16:47:19', 1, NULL, NULL, 0);
INSERT INTO `attendance_sheet` VALUES (48, 1, '2019-03-29 17:02:22', '在广东省泗安医院东城门诊部附近', '2019-03-29 17:02:51', 1, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for body_part
-- ----------------------------
DROP TABLE IF EXISTS `body_part`;
CREATE TABLE `body_part` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `part_name` varchar(50) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_body_part_01` (`part_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部位';

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city_code` varchar(12) NOT NULL,
  `city_name` varchar(50) NOT NULL,
  `parent_id` varchar(12) NOT NULL,
  `name_path` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_city_01` (`city_code`),
  KEY `idx_city_02` (`city_name`),
  KEY `idx_city_03` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3574 DEFAULT CHARSET=utf8mb4 COMMENT='市';

-- ----------------------------
-- Records of city
-- ----------------------------
BEGIN;
INSERT INTO `city` VALUES (3, '0', '中国', '0', NULL);
INSERT INTO `city` VALUES (4, '110000', '北京市', '0', '北京市');
INSERT INTO `city` VALUES (5, '110100', '东城区', '110000', '北京市东城区');
INSERT INTO `city` VALUES (6, '110200', '西城区', '110000', '北京市西城区');
INSERT INTO `city` VALUES (7, '110500', '朝阳区', '110000', '北京市朝阳区');
INSERT INTO `city` VALUES (8, '110600', '丰台区', '110000', '北京市丰台区');
INSERT INTO `city` VALUES (9, '110700', '石景山区', '110000', '北京市石景山区');
INSERT INTO `city` VALUES (10, '110800', '海淀区', '110000', '北京市海淀区');
INSERT INTO `city` VALUES (11, '110900', '门头沟区', '110000', '北京市门头沟区');
INSERT INTO `city` VALUES (12, '111100', '房山区', '110000', '北京市房山区');
INSERT INTO `city` VALUES (13, '111200', '通州区', '110000', '北京市通州区');
INSERT INTO `city` VALUES (14, '111300', '顺义区', '110000', '北京市顺义区');
INSERT INTO `city` VALUES (15, '111400', '昌平区', '110000', '北京市昌平区');
INSERT INTO `city` VALUES (16, '111500', '大兴区', '110000', '北京市大兴区');
INSERT INTO `city` VALUES (17, '111600', '怀柔区', '110000', '北京市怀柔区');
INSERT INTO `city` VALUES (18, '111700', '平谷区', '110000', '北京市平谷区');
INSERT INTO `city` VALUES (19, '112800', '密云县', '110000', '北京市密云县');
INSERT INTO `city` VALUES (20, '112900', '延庆县', '110000', '北京市延庆县');
INSERT INTO `city` VALUES (21, '120000', '天津市', '0', '天津市');
INSERT INTO `city` VALUES (22, '120100', '和平区', '120000', '天津市和平区');
INSERT INTO `city` VALUES (23, '120200', '河东区', '120000', '天津市河东区');
INSERT INTO `city` VALUES (24, '120300', '河西区', '120000', '天津市河西区');
INSERT INTO `city` VALUES (25, '120400', '南开区', '120000', '天津市南开区');
INSERT INTO `city` VALUES (26, '120500', '河北区', '120000', '天津市河北区');
INSERT INTO `city` VALUES (27, '120600', '红桥区', '120000', '天津市红桥区');
INSERT INTO `city` VALUES (28, '120900', '滨海新区', '120000', '天津市滨海新区');
INSERT INTO `city` VALUES (29, '121000', '东丽区', '120000', '天津市东丽区');
INSERT INTO `city` VALUES (30, '121100', '西青区', '120000', '天津市西青区');
INSERT INTO `city` VALUES (31, '121200', '津南区', '120000', '天津市津南区');
INSERT INTO `city` VALUES (32, '121300', '北辰区', '120000', '天津市北辰区');
INSERT INTO `city` VALUES (33, '121400', '武清区', '120000', '天津市武清区');
INSERT INTO `city` VALUES (34, '121500', '宝坻区', '120000', '天津市宝坻区');
INSERT INTO `city` VALUES (35, '122100', '宁河县', '120000', '天津市宁河县');
INSERT INTO `city` VALUES (36, '122300', '静海县', '120000', '天津市静海县');
INSERT INTO `city` VALUES (37, '122500', '蓟县', '120000', '天津市蓟县');
INSERT INTO `city` VALUES (38, '130000', '河北省', '0', '河北省');
INSERT INTO `city` VALUES (39, '130100', '石家庄市', '130000', '河北省石家庄市');
INSERT INTO `city` VALUES (40, '130101', '市辖区', '130100', '河北省石家庄市市辖区');
INSERT INTO `city` VALUES (41, '130102', '长安区', '130101', NULL);
INSERT INTO `city` VALUES (42, '130103', '桥东区', '130101', NULL);
INSERT INTO `city` VALUES (43, '130104', '桥西区', '130101', NULL);
INSERT INTO `city` VALUES (44, '130105', '新华区', '130101', NULL);
INSERT INTO `city` VALUES (45, '130107', '井陉矿区', '130101', NULL);
INSERT INTO `city` VALUES (46, '130108', '裕华区', '130101', NULL);
INSERT INTO `city` VALUES (47, '130121', '井陉县', '130100', '河北省石家庄市井陉县');
INSERT INTO `city` VALUES (48, '130123', '正定县', '130100', '河北省石家庄市正定县');
INSERT INTO `city` VALUES (49, '130124', '栾城县', '130100', '河北省石家庄市栾城县');
INSERT INTO `city` VALUES (50, '130125', '行唐县', '130100', '河北省石家庄市行唐县');
INSERT INTO `city` VALUES (51, '130126', '灵寿县', '130100', '河北省石家庄市灵寿县');
INSERT INTO `city` VALUES (52, '130127', '高邑县', '130100', '河北省石家庄市高邑县');
INSERT INTO `city` VALUES (53, '130128', '深泽县', '130100', '河北省石家庄市深泽县');
INSERT INTO `city` VALUES (54, '130129', '赞皇县', '130100', '河北省石家庄市赞皇县');
INSERT INTO `city` VALUES (55, '130130', '无极县', '130100', '河北省石家庄市无极县');
INSERT INTO `city` VALUES (56, '130131', '平山县', '130100', '河北省石家庄市平山县');
INSERT INTO `city` VALUES (57, '130132', '元氏县', '130100', '河北省石家庄市元氏县');
INSERT INTO `city` VALUES (58, '130133', '赵县', '130100', '河北省石家庄市赵县');
INSERT INTO `city` VALUES (59, '130181', '辛集市', '130100', '河北省石家庄市辛集市');
INSERT INTO `city` VALUES (60, '130182', '藁城市', '130100', '河北省石家庄市藁城市');
INSERT INTO `city` VALUES (61, '130183', '晋州市', '130100', '河北省石家庄市晋州市');
INSERT INTO `city` VALUES (62, '130184', '新乐市', '130100', '河北省石家庄市新乐市');
INSERT INTO `city` VALUES (63, '130185', '鹿泉市', '130100', '河北省石家庄市鹿泉市');
INSERT INTO `city` VALUES (64, '130200', '唐山市', '130000', '河北省唐山市');
INSERT INTO `city` VALUES (65, '130201', '市辖区', '130200', '河北省唐山市市辖区');
INSERT INTO `city` VALUES (66, '130202', '路南区', '130201', NULL);
INSERT INTO `city` VALUES (67, '130203', '路北区', '130201', NULL);
INSERT INTO `city` VALUES (68, '130204', '古冶区', '130201', NULL);
INSERT INTO `city` VALUES (69, '130205', '开平区', '130201', NULL);
INSERT INTO `city` VALUES (70, '130207', '丰南区', '130201', NULL);
INSERT INTO `city` VALUES (71, '130208', '丰润区', '130201', NULL);
INSERT INTO `city` VALUES (72, '130223', '滦县', '130200', '河北省唐山市滦县');
INSERT INTO `city` VALUES (73, '130224', '滦南县', '130200', '河北省唐山市滦南县');
INSERT INTO `city` VALUES (74, '130225', '乐亭县', '130200', '河北省唐山市乐亭县');
INSERT INTO `city` VALUES (75, '130227', '迁西县', '130200', '河北省唐山市迁西县');
INSERT INTO `city` VALUES (76, '130229', '玉田县', '130200', '河北省唐山市玉田县');
INSERT INTO `city` VALUES (77, '130230', '唐海县', '130200', '河北省唐山市唐海县');
INSERT INTO `city` VALUES (78, '130281', '遵化市', '130200', '河北省唐山市遵化市');
INSERT INTO `city` VALUES (79, '130283', '迁安市', '130200', '河北省唐山市迁安市');
INSERT INTO `city` VALUES (80, '130300', '秦皇岛市', '130000', '河北省秦皇岛市');
INSERT INTO `city` VALUES (81, '130301', '海港区', '130300', '河北省秦皇岛市海港区');
INSERT INTO `city` VALUES (82, '130303', '山海关区', '130300', '河北省秦皇岛市山海关区');
INSERT INTO `city` VALUES (83, '130304', '北戴河区', '130300', '河北省秦皇岛市北戴河区');
INSERT INTO `city` VALUES (84, '130321', '青龙满族自治县', '130300', '河北省秦皇岛市青龙满族自治县');
INSERT INTO `city` VALUES (85, '130322', '昌黎县', '130300', '河北省秦皇岛市昌黎县');
INSERT INTO `city` VALUES (86, '130323', '抚宁县', '130300', '河北省秦皇岛市抚宁县');
INSERT INTO `city` VALUES (87, '130324', '卢龙县', '130300', '河北省秦皇岛市卢龙县');
INSERT INTO `city` VALUES (88, '130400', '邯郸市', '130000', '河北省邯郸市');
INSERT INTO `city` VALUES (89, '130401', '市辖区', '130400', '河北省邯郸市市辖区');
INSERT INTO `city` VALUES (90, '130402', '邯山区', '130401', NULL);
INSERT INTO `city` VALUES (91, '130403', '丛台区', '130401', NULL);
INSERT INTO `city` VALUES (92, '130404', '复兴区', '130401', NULL);
INSERT INTO `city` VALUES (93, '130406', '峰峰矿区', '130401', NULL);
INSERT INTO `city` VALUES (94, '130421', '邯郸县', '130400', '河北省邯郸市邯郸县');
INSERT INTO `city` VALUES (95, '130423', '临漳县', '130400', '河北省邯郸市临漳县');
INSERT INTO `city` VALUES (96, '130424', '成安县', '130400', '河北省邯郸市成安县');
INSERT INTO `city` VALUES (97, '130425', '大名县', '130400', '河北省邯郸市大名县');
INSERT INTO `city` VALUES (98, '130426', '涉县', '130400', '河北省邯郸市涉县');
INSERT INTO `city` VALUES (99, '130427', '磁县', '130400', '河北省邯郸市磁县');
INSERT INTO `city` VALUES (100, '130428', '肥乡县', '130400', '河北省邯郸市肥乡县');
INSERT INTO `city` VALUES (101, '130429', '永年县', '130400', '河北省邯郸市永年县');
INSERT INTO `city` VALUES (102, '130430', '邱县', '130400', '河北省邯郸市邱县');
INSERT INTO `city` VALUES (103, '130431', '鸡泽县', '130400', '河北省邯郸市鸡泽县');
INSERT INTO `city` VALUES (104, '130432', '广平县', '130400', '河北省邯郸市广平县');
INSERT INTO `city` VALUES (105, '130433', '馆陶县', '130400', '河北省邯郸市馆陶县');
INSERT INTO `city` VALUES (106, '130434', '魏县', '130400', '河北省邯郸市魏县');
INSERT INTO `city` VALUES (107, '130435', '曲周县', '130400', '河北省邯郸市曲周县');
INSERT INTO `city` VALUES (108, '130481', '武安市', '130400', '河北省邯郸市武安市');
INSERT INTO `city` VALUES (109, '130500', '邢台市', '130000', '河北省邢台市');
INSERT INTO `city` VALUES (110, '130501', '市辖区', '130500', '河北省邢台市市辖区');
INSERT INTO `city` VALUES (111, '130502', '桥东区', '130501', NULL);
INSERT INTO `city` VALUES (112, '130503', '桥西区', '130501', NULL);
INSERT INTO `city` VALUES (113, '130521', '邢台县', '130500', '河北省邢台市邢台县');
INSERT INTO `city` VALUES (114, '130522', '临城县', '130500', '河北省邢台市临城县');
INSERT INTO `city` VALUES (115, '130523', '内丘县', '130500', '河北省邢台市内丘县');
INSERT INTO `city` VALUES (116, '130524', '柏乡县', '130500', '河北省邢台市柏乡县');
INSERT INTO `city` VALUES (117, '130525', '隆尧县', '130500', '河北省邢台市隆尧县');
INSERT INTO `city` VALUES (118, '130526', '任县', '130500', '河北省邢台市任县');
INSERT INTO `city` VALUES (119, '130527', '南和县', '130500', '河北省邢台市南和县');
INSERT INTO `city` VALUES (120, '130528', '宁晋县', '130500', '河北省邢台市宁晋县');
INSERT INTO `city` VALUES (121, '130529', '巨鹿县', '130529', NULL);
INSERT INTO `city` VALUES (122, '130530', '新河县', '130500', '河北省邢台市新河县');
INSERT INTO `city` VALUES (123, '130531', '广宗县', '130500', '河北省邢台市广宗县');
INSERT INTO `city` VALUES (124, '130532', '平乡县', '130500', '河北省邢台市平乡县');
INSERT INTO `city` VALUES (125, '130533', '威县', '130500', '河北省邢台市威县');
INSERT INTO `city` VALUES (126, '130534', '清河县', '130500', '河北省邢台市清河县');
INSERT INTO `city` VALUES (127, '130535', '临西县', '130500', '河北省邢台市临西县');
INSERT INTO `city` VALUES (128, '130581', '南宫市', '130500', '河北省邢台市南宫市');
INSERT INTO `city` VALUES (129, '130582', '沙河市', '130500', '河北省邢台市沙河市');
INSERT INTO `city` VALUES (130, '130600', '保定市', '130000', '河北省保定市');
INSERT INTO `city` VALUES (131, '130601', '新市区', '130600', '河北省保定市新市区');
INSERT INTO `city` VALUES (132, '130603', '北市区', '130600', '河北省保定市北市区');
INSERT INTO `city` VALUES (133, '130604', '南市区', '130600', '河北省保定市南市区');
INSERT INTO `city` VALUES (134, '130621', '满城县', '130600', '河北省保定市满城县');
INSERT INTO `city` VALUES (135, '130622', '清苑县', '130600', '河北省保定市清苑县');
INSERT INTO `city` VALUES (136, '130623', '涞水县', '130600', '河北省保定市涞水县');
INSERT INTO `city` VALUES (137, '130624', '阜平县', '130600', '河北省保定市阜平县');
INSERT INTO `city` VALUES (138, '130625', '徐水县', '130600', '河北省保定市徐水县');
INSERT INTO `city` VALUES (139, '130626', '定兴县', '130600', '河北省保定市定兴县');
INSERT INTO `city` VALUES (140, '130627', '唐县', '130600', '河北省保定市唐县');
INSERT INTO `city` VALUES (141, '130628', '高阳县', '130600', '河北省保定市高阳县');
INSERT INTO `city` VALUES (142, '130629', '容城县', '130600', '河北省保定市容城县');
INSERT INTO `city` VALUES (143, '130630', '涞源县', '130600', '河北省保定市涞源县');
INSERT INTO `city` VALUES (144, '130631', '望都县', '130600', '河北省保定市望都县');
INSERT INTO `city` VALUES (145, '130632', '安新县', '130600', '河北省保定市安新县');
INSERT INTO `city` VALUES (146, '130633', '易县', '130600', '河北省保定市易县');
INSERT INTO `city` VALUES (147, '130634', '曲阳县', '130600', '河北省保定市曲阳县');
INSERT INTO `city` VALUES (148, '130635', '蠡县', '130600', '河北省保定市蠡县');
INSERT INTO `city` VALUES (149, '130636', '顺平县', '130600', '河北省保定市顺平县');
INSERT INTO `city` VALUES (150, '130637', '博野县', '130600', '河北省保定市博野县');
INSERT INTO `city` VALUES (151, '130638', '雄县', '130600', '河北省保定市雄县');
INSERT INTO `city` VALUES (152, '130681', '涿州市', '130600', '河北省保定市涿州市');
INSERT INTO `city` VALUES (153, '130682', '定州市', '130600', '河北省保定市定州市');
INSERT INTO `city` VALUES (154, '130683', '安国市', '130600', '河北省保定市安国市');
INSERT INTO `city` VALUES (155, '130684', '高碑店市', '130600', '河北省保定市高碑店市');
INSERT INTO `city` VALUES (156, '130685', '白沟新城县', '130600', '河北省保定市白沟新城县');
INSERT INTO `city` VALUES (157, '130700', '张家口市', '130000', '河北省张家口市');
INSERT INTO `city` VALUES (158, '130701', '市辖区', '130700', '河北省张家口市市辖区');
INSERT INTO `city` VALUES (159, '130702', '桥东区', '130701', NULL);
INSERT INTO `city` VALUES (160, '130703', '桥西区', '130701', NULL);
INSERT INTO `city` VALUES (161, '130705', '宣化区', '130701', NULL);
INSERT INTO `city` VALUES (162, '130706', '下花园区', '130701', NULL);
INSERT INTO `city` VALUES (163, '130721', '宣化县', '130700', '河北省张家口市宣化县');
INSERT INTO `city` VALUES (164, '130722', '张北县', '130700', '河北省张家口市张北县');
INSERT INTO `city` VALUES (165, '130723', '康保县', '130700', '河北省张家口市康保县');
INSERT INTO `city` VALUES (166, '130724', '沽源县', '130700', '河北省张家口市沽源县');
INSERT INTO `city` VALUES (167, '130725', '尚义县', '130700', '河北省张家口市尚义县');
INSERT INTO `city` VALUES (168, '130726', '蔚县', '130700', '河北省张家口市蔚县');
INSERT INTO `city` VALUES (169, '130727', '阳原县', '130700', '河北省张家口市阳原县');
INSERT INTO `city` VALUES (170, '130728', '怀安县', '130700', '河北省张家口市怀安县');
INSERT INTO `city` VALUES (171, '130729', '万全县', '130700', '河北省张家口市万全县');
INSERT INTO `city` VALUES (172, '130730', '怀来县', '130700', '河北省张家口市怀来县');
INSERT INTO `city` VALUES (173, '130731', '涿鹿县', '130700', '河北省张家口市涿鹿县');
INSERT INTO `city` VALUES (174, '130732', '赤城县', '130700', '河北省张家口市赤城县');
INSERT INTO `city` VALUES (175, '130733', '崇礼县', '130700', '河北省张家口市崇礼县');
INSERT INTO `city` VALUES (176, '130800', '承德市', '130000', '河北省承德市');
INSERT INTO `city` VALUES (177, '130801', '市辖区', '130800', '河北省承德市市辖区');
INSERT INTO `city` VALUES (178, '130802', '双桥区', '130801', NULL);
INSERT INTO `city` VALUES (179, '130803', '双滦区', '130801', NULL);
INSERT INTO `city` VALUES (180, '130804', '鹰手营子矿区', '130801', NULL);
INSERT INTO `city` VALUES (181, '130821', '承德县', '130800', '河北省承德市承德县');
INSERT INTO `city` VALUES (182, '130822', '兴隆县', '130800', '河北省承德市兴隆县');
INSERT INTO `city` VALUES (183, '130823', '平泉县', '130800', '河北省承德市平泉县');
INSERT INTO `city` VALUES (184, '130824', '滦平县', '130800', '河北省承德市滦平县');
INSERT INTO `city` VALUES (185, '130825', '隆化县', '130800', '河北省承德市隆化县');
INSERT INTO `city` VALUES (186, '130826', '丰宁满族自治县', '130800', '河北省承德市丰宁满族自治县');
INSERT INTO `city` VALUES (187, '130827', '宽城满族自治县', '130800', '河北省承德市宽城满族自治县');
INSERT INTO `city` VALUES (188, '130828', '围场满族蒙古族自治县', '130800', '河北省承德市围场满族蒙古族自治县');
INSERT INTO `city` VALUES (189, '130900', '沧州市', '130000', '河北省沧州市');
INSERT INTO `city` VALUES (190, '130901', '市辖区', '130900', '河北省沧州市市辖区');
INSERT INTO `city` VALUES (191, '130902', '新华区', '130901', NULL);
INSERT INTO `city` VALUES (192, '130903', '运河区', '130901', NULL);
INSERT INTO `city` VALUES (193, '130921', '沧县', '130900', '河北省沧州市沧县');
INSERT INTO `city` VALUES (194, '130922', '青县', '130900', '河北省沧州市青县');
INSERT INTO `city` VALUES (195, '130923', '东光县', '130900', '河北省沧州市东光县');
INSERT INTO `city` VALUES (196, '130924', '海兴县', '130900', '河北省沧州市海兴县');
INSERT INTO `city` VALUES (197, '130925', '盐山县', '130900', '河北省沧州市盐山县');
INSERT INTO `city` VALUES (198, '130926', '肃宁县', '130900', '河北省沧州市肃宁县');
INSERT INTO `city` VALUES (199, '130927', '南皮县', '130900', '河北省沧州市南皮县');
INSERT INTO `city` VALUES (200, '130928', '吴桥县', '130900', '河北省沧州市吴桥县');
INSERT INTO `city` VALUES (201, '130929', '献县', '130900', '河北省沧州市献县');
INSERT INTO `city` VALUES (202, '130930', '孟村回族自治县', '130900', '河北省沧州市孟村回族自治县');
INSERT INTO `city` VALUES (203, '130981', '泊头市', '130900', '河北省沧州市泊头市');
INSERT INTO `city` VALUES (204, '130982', '任丘市', '130900', '河北省沧州市任丘市');
INSERT INTO `city` VALUES (205, '130983', '黄骅市', '130900', '河北省沧州市黄骅市');
INSERT INTO `city` VALUES (206, '130984', '河间市', '130900', '河北省沧州市河间市');
INSERT INTO `city` VALUES (207, '131000', '廊坊市', '130000', '河北省廊坊市');
INSERT INTO `city` VALUES (208, '131001', '市辖区', '131000', '河北省廊坊市市辖区');
INSERT INTO `city` VALUES (209, '131002', '安次区', '131001', NULL);
INSERT INTO `city` VALUES (210, '131003', '广阳区', '131001', NULL);
INSERT INTO `city` VALUES (211, '131022', '固安县', '131000', '河北省廊坊市固安县');
INSERT INTO `city` VALUES (212, '131023', '永清县', '131000', '河北省廊坊市永清县');
INSERT INTO `city` VALUES (213, '131024', '香河县', '131000', '河北省廊坊市香河县');
INSERT INTO `city` VALUES (214, '131025', '大城县', '131000', '河北省廊坊市大城县');
INSERT INTO `city` VALUES (215, '131026', '文安县', '131000', '河北省廊坊市文安县');
INSERT INTO `city` VALUES (216, '131028', '大厂回族自治县', '131000', '河北省廊坊市大厂回族自治县');
INSERT INTO `city` VALUES (217, '131081', '霸州市', '131000', '河北省廊坊市霸州市');
INSERT INTO `city` VALUES (218, '131082', '三河市', '131000', '河北省廊坊市三河市');
INSERT INTO `city` VALUES (219, '131100', '衡水市', '130000', '河北省衡水市');
INSERT INTO `city` VALUES (220, '131101', '市辖区', '131100', '河北省衡水市市辖区');
INSERT INTO `city` VALUES (221, '131102', '桃城区', '131101', NULL);
INSERT INTO `city` VALUES (222, '131121', '枣强县', '131100', '河北省衡水市枣强县');
INSERT INTO `city` VALUES (223, '131122', '武邑县', '131100', '河北省衡水市武邑县');
INSERT INTO `city` VALUES (224, '131123', '武强县', '131100', '河北省衡水市武强县');
INSERT INTO `city` VALUES (225, '131124', '饶阳县', '131100', '河北省衡水市饶阳县');
INSERT INTO `city` VALUES (226, '131125', '安平县', '131100', '河北省衡水市安平县');
INSERT INTO `city` VALUES (227, '131126', '故城县', '131100', '河北省衡水市故城县');
INSERT INTO `city` VALUES (228, '131127', '景县', '131100', '河北省衡水市景县');
INSERT INTO `city` VALUES (229, '131128', '阜城县', '131100', '河北省衡水市阜城县');
INSERT INTO `city` VALUES (230, '131181', '冀州市', '131100', '河北省衡水市冀州市');
INSERT INTO `city` VALUES (231, '131182', '深州市', '131100', '河北省衡水市深州市');
INSERT INTO `city` VALUES (232, '140000', '山西省', '0', '山西省');
INSERT INTO `city` VALUES (233, '140100', '太原市', '140000', '山西省太原市');
INSERT INTO `city` VALUES (234, '140101', '市辖区', '140100', '山西省太原市市辖区');
INSERT INTO `city` VALUES (235, '140105', '小店区', '140101', NULL);
INSERT INTO `city` VALUES (236, '140106', '迎泽区', '140101', NULL);
INSERT INTO `city` VALUES (237, '140107', '杏花岭区', '140101', NULL);
INSERT INTO `city` VALUES (238, '140108', '尖草坪区', '140101', NULL);
INSERT INTO `city` VALUES (239, '140109', '万柏林区', '140101', NULL);
INSERT INTO `city` VALUES (240, '140110', '晋源区', '140101', NULL);
INSERT INTO `city` VALUES (241, '140121', '清徐县', '140100', '山西省太原市清徐县');
INSERT INTO `city` VALUES (242, '140122', '阳曲县', '140100', '山西省太原市阳曲县');
INSERT INTO `city` VALUES (243, '140123', '娄烦县', '140100', '山西省太原市娄烦县');
INSERT INTO `city` VALUES (244, '140181', '古交市', '140100', '山西省太原市古交市');
INSERT INTO `city` VALUES (245, '140200', '大同市', '140000', '山西省大同市');
INSERT INTO `city` VALUES (246, '140201', '市辖区', '140200', '山西省大同市市辖区');
INSERT INTO `city` VALUES (247, '140202', '城区', '140201', NULL);
INSERT INTO `city` VALUES (248, '140203', '矿区', '140201', NULL);
INSERT INTO `city` VALUES (249, '140211', '南郊区', '140201', NULL);
INSERT INTO `city` VALUES (250, '140212', '新荣区', '140201', NULL);
INSERT INTO `city` VALUES (251, '140221', '阳高县', '140200', '山西省大同市阳高县');
INSERT INTO `city` VALUES (252, '140222', '天镇县', '140200', '山西省大同市天镇县');
INSERT INTO `city` VALUES (253, '140223', '广灵县', '140200', '山西省大同市广灵县');
INSERT INTO `city` VALUES (254, '140224', '灵丘县', '140200', '山西省大同市灵丘县');
INSERT INTO `city` VALUES (255, '140225', '浑源县', '140200', '山西省大同市浑源县');
INSERT INTO `city` VALUES (256, '140226', '左云县', '140200', '山西省大同市左云县');
INSERT INTO `city` VALUES (257, '140227', '大同县', '140200', '山西省大同市大同县');
INSERT INTO `city` VALUES (258, '140300', '阳泉市', '140000', '山西省阳泉市');
INSERT INTO `city` VALUES (259, '140301', '市辖区', '140300', '山西省阳泉市市辖区');
INSERT INTO `city` VALUES (260, '140302', '城区', '140301', NULL);
INSERT INTO `city` VALUES (261, '140303', '矿区', '140301', NULL);
INSERT INTO `city` VALUES (262, '140311', '郊区', '140301', NULL);
INSERT INTO `city` VALUES (263, '140321', '平定县', '140300', '山西省阳泉市平定县');
INSERT INTO `city` VALUES (264, '140322', '盂县', '140300', '山西省阳泉市盂县');
INSERT INTO `city` VALUES (265, '140400', '长治市', '140000', '山西省长治市');
INSERT INTO `city` VALUES (266, '140401', '市辖区', '140400', '山西省长治市市辖区');
INSERT INTO `city` VALUES (267, '140402', '城区', '140401', NULL);
INSERT INTO `city` VALUES (268, '140411', '郊区', '140401', NULL);
INSERT INTO `city` VALUES (269, '140421', '长治县', '140400', '山西省长治市长治县');
INSERT INTO `city` VALUES (270, '140423', '襄垣县', '140400', '山西省长治市襄垣县');
INSERT INTO `city` VALUES (271, '140424', '屯留县', '140400', '山西省长治市屯留县');
INSERT INTO `city` VALUES (272, '140425', '平顺县', '140400', '山西省长治市平顺县');
INSERT INTO `city` VALUES (273, '140426', '黎城县', '140400', '山西省长治市黎城县');
INSERT INTO `city` VALUES (274, '140427', '壶关县', '140400', '山西省长治市壶关县');
INSERT INTO `city` VALUES (275, '140428', '长子县', '140400', '山西省长治市长子县');
INSERT INTO `city` VALUES (276, '140429', '武乡县', '140400', '山西省长治市武乡县');
INSERT INTO `city` VALUES (277, '140430', '沁县', '140400', '山西省长治市沁县');
INSERT INTO `city` VALUES (278, '140431', '沁源县', '140400', '山西省长治市沁源县');
INSERT INTO `city` VALUES (279, '140481', '潞城市', '140400', '山西省长治市潞城市');
INSERT INTO `city` VALUES (280, '140500', '晋城市', '140000', '山西省晋城市');
INSERT INTO `city` VALUES (281, '140501', '市辖区', '140500', '山西省晋城市市辖区');
INSERT INTO `city` VALUES (282, '140502', '城区', '140501', NULL);
INSERT INTO `city` VALUES (283, '140521', '沁水县', '140500', '山西省晋城市沁水县');
INSERT INTO `city` VALUES (284, '140522', '阳城县', '140500', '山西省晋城市阳城县');
INSERT INTO `city` VALUES (285, '140524', '陵川县', '140500', '山西省晋城市陵川县');
INSERT INTO `city` VALUES (286, '140525', '泽州县', '140500', '山西省晋城市泽州县');
INSERT INTO `city` VALUES (287, '140581', '高平市', '140500', '山西省晋城市高平市');
INSERT INTO `city` VALUES (288, '140600', '朔州市', '140000', '山西省朔州市');
INSERT INTO `city` VALUES (289, '140601', '市辖区', '140600', '山西省朔州市市辖区');
INSERT INTO `city` VALUES (290, '140602', '朔城区', '140601', NULL);
INSERT INTO `city` VALUES (291, '140603', '平鲁区', '140601', NULL);
INSERT INTO `city` VALUES (292, '140621', '山阴县', '140600', '山西省朔州市山阴县');
INSERT INTO `city` VALUES (293, '140622', '应县', '140600', '山西省朔州市应县');
INSERT INTO `city` VALUES (294, '140623', '右玉县', '140600', '山西省朔州市右玉县');
INSERT INTO `city` VALUES (295, '140624', '怀仁县', '140600', '山西省朔州市怀仁县');
INSERT INTO `city` VALUES (296, '140700', '晋中市', '140000', '山西省晋中市');
INSERT INTO `city` VALUES (297, '140701', '市辖区', '140700', '山西省晋中市市辖区');
INSERT INTO `city` VALUES (298, '140702', '榆次区', '140701', NULL);
INSERT INTO `city` VALUES (299, '140721', '榆社县', '140700', '山西省晋中市榆社县');
INSERT INTO `city` VALUES (300, '140722', '左权县', '140700', '山西省晋中市左权县');
INSERT INTO `city` VALUES (301, '140723', '和顺县', '140700', '山西省晋中市和顺县');
INSERT INTO `city` VALUES (302, '140724', '昔阳县', '140700', '山西省晋中市昔阳县');
INSERT INTO `city` VALUES (303, '140725', '寿阳县', '140700', '山西省晋中市寿阳县');
INSERT INTO `city` VALUES (304, '140726', '太谷县', '140700', '山西省晋中市太谷县');
INSERT INTO `city` VALUES (305, '140727', '祁县', '140700', '山西省晋中市祁县');
INSERT INTO `city` VALUES (306, '140728', '平遥县', '140700', '山西省晋中市平遥县');
INSERT INTO `city` VALUES (307, '140729', '灵石县', '140700', '山西省晋中市灵石县');
INSERT INTO `city` VALUES (308, '140781', '介休市', '140700', '山西省晋中市介休市');
INSERT INTO `city` VALUES (309, '140800', '运城市', '140000', '山西省运城市');
INSERT INTO `city` VALUES (310, '140801', '市辖区', '140800', '山西省运城市市辖区');
INSERT INTO `city` VALUES (311, '140802', '盐湖区', '140801', NULL);
INSERT INTO `city` VALUES (312, '140821', '临猗县', '140800', '山西省运城市临猗县');
INSERT INTO `city` VALUES (313, '140822', '万荣县', '140800', '山西省运城市万荣县');
INSERT INTO `city` VALUES (314, '140823', '闻喜县', '140800', '山西省运城市闻喜县');
INSERT INTO `city` VALUES (315, '140824', '稷山县', '140800', '山西省运城市稷山县');
INSERT INTO `city` VALUES (316, '140825', '新绛县', '140800', '山西省运城市新绛县');
INSERT INTO `city` VALUES (317, '140826', '绛县', '140800', '山西省运城市绛县');
INSERT INTO `city` VALUES (318, '140827', '垣曲县', '140800', '山西省运城市垣曲县');
INSERT INTO `city` VALUES (319, '140828', '夏县', '140800', '山西省运城市夏县');
INSERT INTO `city` VALUES (320, '140829', '平陆县', '140800', '山西省运城市平陆县');
INSERT INTO `city` VALUES (321, '140830', '芮城县', '140800', '山西省运城市芮城县');
INSERT INTO `city` VALUES (322, '140881', '永济市', '140800', '山西省运城市永济市');
INSERT INTO `city` VALUES (323, '140882', '河津市', '140800', '山西省运城市河津市');
INSERT INTO `city` VALUES (324, '140900', '忻州市', '140000', '山西省忻州市');
INSERT INTO `city` VALUES (325, '140901', '忻府区', '140900', '山西省忻州市忻府区');
INSERT INTO `city` VALUES (326, '140921', '定襄县', '140900', '山西省忻州市定襄县');
INSERT INTO `city` VALUES (327, '140922', '五台县', '140900', '山西省忻州市五台县');
INSERT INTO `city` VALUES (328, '140923', '代县', '140900', '山西省忻州市代县');
INSERT INTO `city` VALUES (329, '140924', '繁峙县', '140900', '山西省忻州市繁峙县');
INSERT INTO `city` VALUES (330, '140925', '宁武县', '140900', '山西省忻州市宁武县');
INSERT INTO `city` VALUES (331, '140926', '静乐县', '140900', '山西省忻州市静乐县');
INSERT INTO `city` VALUES (332, '140927', '神池县', '140900', '山西省忻州市神池县');
INSERT INTO `city` VALUES (333, '140928', '五寨县', '140900', '山西省忻州市五寨县');
INSERT INTO `city` VALUES (334, '140929', '岢岚县', '140900', '山西省忻州市岢岚县');
INSERT INTO `city` VALUES (335, '140930', '河曲县', '140900', '山西省忻州市河曲县');
INSERT INTO `city` VALUES (336, '140931', '保德县', '140900', '山西省忻州市保德县');
INSERT INTO `city` VALUES (337, '140932', '偏关县', '140900', '山西省忻州市偏关县');
INSERT INTO `city` VALUES (338, '140981', '原平市', '140900', '山西省忻州市原平市');
INSERT INTO `city` VALUES (339, '141000', '临汾市', '140000', '山西省临汾市');
INSERT INTO `city` VALUES (340, '141001', '市辖区', '141000', '山西省临汾市市辖区');
INSERT INTO `city` VALUES (341, '141002', '尧都区', '141000', '山西省临汾市尧都区');
INSERT INTO `city` VALUES (342, '141021', '曲沃县', '141000', '山西省临汾市曲沃县');
INSERT INTO `city` VALUES (343, '141022', '翼城县', '141000', '山西省临汾市翼城县');
INSERT INTO `city` VALUES (344, '141023', '襄汾县', '141000', '山西省临汾市襄汾县');
INSERT INTO `city` VALUES (345, '141024', '洪洞县', '141000', '山西省临汾市洪洞县');
INSERT INTO `city` VALUES (346, '141025', '古县', '141000', '山西省临汾市古县');
INSERT INTO `city` VALUES (347, '141026', '安泽县', '141000', '山西省临汾市安泽县');
INSERT INTO `city` VALUES (348, '141027', '浮山县', '141000', '山西省临汾市浮山县');
INSERT INTO `city` VALUES (349, '141028', '吉县', '141000', '山西省临汾市吉县');
INSERT INTO `city` VALUES (350, '141029', '乡宁县', '141000', '山西省临汾市乡宁县');
INSERT INTO `city` VALUES (351, '141030', '大宁县', '141000', '山西省临汾市大宁县');
INSERT INTO `city` VALUES (352, '141031', '隰县', '141000', '山西省临汾市隰县');
INSERT INTO `city` VALUES (353, '141032', '永和县', '141000', '山西省临汾市永和县');
INSERT INTO `city` VALUES (354, '141033', '蒲县', '141000', '山西省临汾市蒲县');
INSERT INTO `city` VALUES (355, '141034', '汾西县', '141000', '山西省临汾市汾西县');
INSERT INTO `city` VALUES (356, '141081', '侯马市', '141000', '山西省临汾市侯马市');
INSERT INTO `city` VALUES (357, '141082', '霍州市', '141000', '山西省临汾市霍州市');
INSERT INTO `city` VALUES (358, '141100', '吕梁市', '140000', '山西省吕梁市');
INSERT INTO `city` VALUES (359, '141101', '市辖区', '141100', '山西省吕梁市市辖区');
INSERT INTO `city` VALUES (360, '141102', '离石区', '141101', NULL);
INSERT INTO `city` VALUES (361, '141121', '文水县', '141100', '山西省吕梁市文水县');
INSERT INTO `city` VALUES (362, '141122', '交城县', '141100', '山西省吕梁市交城县');
INSERT INTO `city` VALUES (363, '141123', '兴县', '141100', '山西省吕梁市兴县');
INSERT INTO `city` VALUES (364, '141124', '临县', '141100', '山西省吕梁市临县');
INSERT INTO `city` VALUES (365, '141125', '柳林县', '141100', '山西省吕梁市柳林县');
INSERT INTO `city` VALUES (366, '141126', '石楼县', '141100', '山西省吕梁市石楼县');
INSERT INTO `city` VALUES (367, '141127', '岚县', '141100', '山西省吕梁市岚县');
INSERT INTO `city` VALUES (368, '141128', '方山县', '141100', '山西省吕梁市方山县');
INSERT INTO `city` VALUES (369, '141129', '中阳县', '141100', '山西省吕梁市中阳县');
INSERT INTO `city` VALUES (370, '141130', '交口县', '141100', '山西省吕梁市交口县');
INSERT INTO `city` VALUES (371, '141181', '孝义市', '141100', '山西省吕梁市孝义市');
INSERT INTO `city` VALUES (372, '141182', '汾阳市', '141100', '山西省吕梁市汾阳市');
INSERT INTO `city` VALUES (373, '150000', '内蒙古自治区', '0', '内蒙古自治区');
INSERT INTO `city` VALUES (374, '150100', '呼和浩特市', '150000', '内蒙古自治区呼和浩特市');
INSERT INTO `city` VALUES (375, '150101', '市辖区', '150100', '内蒙古自治区呼和浩特市市辖区');
INSERT INTO `city` VALUES (376, '150102', '新城区', '150101', NULL);
INSERT INTO `city` VALUES (377, '150103', '回民区', '150101', NULL);
INSERT INTO `city` VALUES (378, '150104', '玉泉区', '150101', NULL);
INSERT INTO `city` VALUES (379, '150105', '赛罕区', '150101', NULL);
INSERT INTO `city` VALUES (380, '150121', '土默特左旗', '150100', '内蒙古自治区呼和浩特市土默特左旗');
INSERT INTO `city` VALUES (381, '150122', '托克托县', '150100', '内蒙古自治区呼和浩特市托克托县');
INSERT INTO `city` VALUES (382, '150123', '和林格尔县', '150100', '内蒙古自治区呼和浩特市和林格尔县');
INSERT INTO `city` VALUES (383, '150124', '清水河县', '150100', '内蒙古自治区呼和浩特市清水河县');
INSERT INTO `city` VALUES (384, '150125', '武川县', '150100', '内蒙古自治区呼和浩特市武川县');
INSERT INTO `city` VALUES (385, '150200', '包头市', '150000', '内蒙古自治区包头市');
INSERT INTO `city` VALUES (386, '150201', '市辖区', '150200', '内蒙古自治区包头市市辖区');
INSERT INTO `city` VALUES (387, '150202', '东河区', '150201', NULL);
INSERT INTO `city` VALUES (388, '150203', '昆都仑区', '150201', NULL);
INSERT INTO `city` VALUES (389, '150204', '青山区', '150201', NULL);
INSERT INTO `city` VALUES (390, '150205', '石拐区', '150201', NULL);
INSERT INTO `city` VALUES (391, '150206', '白云矿区', '150201', NULL);
INSERT INTO `city` VALUES (392, '150207', '九原区', '150201', NULL);
INSERT INTO `city` VALUES (393, '150221', '土默特右旗', '150200', '内蒙古自治区包头市土默特右旗');
INSERT INTO `city` VALUES (394, '150222', '固阳县', '150200', '内蒙古自治区包头市固阳县');
INSERT INTO `city` VALUES (395, '150223', '达尔罕茂明安联合旗', '150200', '内蒙古自治区包头市达尔罕茂明安联合旗');
INSERT INTO `city` VALUES (396, '150300', '乌海市', '150000', '内蒙古自治区乌海市');
INSERT INTO `city` VALUES (397, '150301', '市辖区', '150300', '内蒙古自治区乌海市市辖区');
INSERT INTO `city` VALUES (398, '150302', '海勃湾区', '150301', NULL);
INSERT INTO `city` VALUES (399, '150303', '海南区', '150301', NULL);
INSERT INTO `city` VALUES (400, '150304', '乌达区', '150301', NULL);
INSERT INTO `city` VALUES (401, '150400', '赤峰市', '150000', '内蒙古自治区赤峰市');
INSERT INTO `city` VALUES (402, '150401', '市辖区', '150400', '内蒙古自治区赤峰市市辖区');
INSERT INTO `city` VALUES (403, '150402', '红山区', '150401', NULL);
INSERT INTO `city` VALUES (404, '150403', '元宝山区', '150401', NULL);
INSERT INTO `city` VALUES (405, '150404', '松山区', '150401', NULL);
INSERT INTO `city` VALUES (406, '150421', '阿鲁科尔沁旗', '150400', '内蒙古自治区赤峰市阿鲁科尔沁旗');
INSERT INTO `city` VALUES (407, '150422', '巴林左旗', '150400', '内蒙古自治区赤峰市巴林左旗');
INSERT INTO `city` VALUES (408, '150423', '巴林右旗', '150400', '内蒙古自治区赤峰市巴林右旗');
INSERT INTO `city` VALUES (409, '150424', '林西县', '150400', '内蒙古自治区赤峰市林西县');
INSERT INTO `city` VALUES (410, '150425', '克什克腾旗', '150400', '内蒙古自治区赤峰市克什克腾旗');
INSERT INTO `city` VALUES (411, '150426', '翁牛特旗', '150400', '内蒙古自治区赤峰市翁牛特旗');
INSERT INTO `city` VALUES (412, '150428', '喀喇沁旗', '150400', '内蒙古自治区赤峰市喀喇沁旗');
INSERT INTO `city` VALUES (413, '150429', '宁城县', '150400', '内蒙古自治区赤峰市宁城县');
INSERT INTO `city` VALUES (414, '150430', '敖汉旗', '150400', '内蒙古自治区赤峰市敖汉旗');
INSERT INTO `city` VALUES (415, '150500', '通辽市', '150000', '内蒙古自治区通辽市');
INSERT INTO `city` VALUES (416, '150501', '市辖区', '150500', '内蒙古自治区通辽市市辖区');
INSERT INTO `city` VALUES (417, '150502', '科尔沁区', '150501', NULL);
INSERT INTO `city` VALUES (418, '150521', '科尔沁左翼中旗', '150500', '内蒙古自治区通辽市科尔沁左翼中旗');
INSERT INTO `city` VALUES (419, '150522', '科尔沁左翼后旗', '150500', '内蒙古自治区通辽市科尔沁左翼后旗');
INSERT INTO `city` VALUES (420, '150523', '开鲁县', '150500', '内蒙古自治区通辽市开鲁县');
INSERT INTO `city` VALUES (421, '150524', '库伦旗', '150500', '内蒙古自治区通辽市库伦旗');
INSERT INTO `city` VALUES (422, '150525', '奈曼旗', '150500', '内蒙古自治区通辽市奈曼旗');
INSERT INTO `city` VALUES (423, '150526', '扎鲁特旗', '150500', '内蒙古自治区通辽市扎鲁特旗');
INSERT INTO `city` VALUES (424, '150581', '霍林郭勒市', '150500', '内蒙古自治区通辽市霍林郭勒市');
INSERT INTO `city` VALUES (425, '150600', '鄂尔多斯市', '150000', '内蒙古自治区鄂尔多斯市');
INSERT INTO `city` VALUES (426, '150602', '东胜区', '150600', '内蒙古自治区鄂尔多斯市东胜区');
INSERT INTO `city` VALUES (427, '150621', '达拉特旗', '150600', '内蒙古自治区鄂尔多斯市达拉特旗');
INSERT INTO `city` VALUES (428, '150622', '准格尔旗', '150600', '内蒙古自治区鄂尔多斯市准格尔旗');
INSERT INTO `city` VALUES (429, '150623', '鄂托克前旗', '150600', '内蒙古自治区鄂尔多斯市鄂托克前旗');
INSERT INTO `city` VALUES (430, '150624', '鄂托克旗', '150600', '内蒙古自治区鄂尔多斯市鄂托克旗');
INSERT INTO `city` VALUES (431, '150625', '杭锦旗', '150600', '内蒙古自治区鄂尔多斯市杭锦旗');
INSERT INTO `city` VALUES (432, '150626', '乌审旗', '150600', '内蒙古自治区鄂尔多斯市乌审旗');
INSERT INTO `city` VALUES (433, '150627', '伊金霍洛旗', '150600', '内蒙古自治区鄂尔多斯市伊金霍洛旗');
INSERT INTO `city` VALUES (434, '150700', '呼伦贝尔市', '150000', '内蒙古自治区呼伦贝尔市');
INSERT INTO `city` VALUES (435, '150701', '市辖区', '150700', '内蒙古自治区呼伦贝尔市市辖区');
INSERT INTO `city` VALUES (436, '150702', '海拉尔区', '150701', NULL);
INSERT INTO `city` VALUES (437, '150721', '阿荣旗', '150700', '内蒙古自治区呼伦贝尔市阿荣旗');
INSERT INTO `city` VALUES (438, '150722', '莫力达瓦达斡尔族自治旗', '150700', '内蒙古自治区呼伦贝尔市莫力达瓦达斡尔族自治旗');
INSERT INTO `city` VALUES (439, '150723', '鄂伦春自治旗', '150700', '内蒙古自治区呼伦贝尔市鄂伦春自治旗');
INSERT INTO `city` VALUES (440, '150724', '鄂温克族自治旗', '150700', '内蒙古自治区呼伦贝尔市鄂温克族自治旗');
INSERT INTO `city` VALUES (441, '150725', '陈巴尔虎旗', '150700', '内蒙古自治区呼伦贝尔市陈巴尔虎旗');
INSERT INTO `city` VALUES (442, '150726', '新巴尔虎左旗', '150700', '内蒙古自治区呼伦贝尔市新巴尔虎左旗');
INSERT INTO `city` VALUES (443, '150727', '新巴尔虎右旗', '150700', '内蒙古自治区呼伦贝尔市新巴尔虎右旗');
INSERT INTO `city` VALUES (444, '150781', '满洲里市', '150700', '内蒙古自治区呼伦贝尔市满洲里市');
INSERT INTO `city` VALUES (445, '150782', '牙克石市', '150700', '内蒙古自治区呼伦贝尔市牙克石市');
INSERT INTO `city` VALUES (446, '150783', '扎兰屯市', '150700', '内蒙古自治区呼伦贝尔市扎兰屯市');
INSERT INTO `city` VALUES (447, '150784', '额尔古纳市', '150700', '内蒙古自治区呼伦贝尔市额尔古纳市');
INSERT INTO `city` VALUES (448, '150785', '根河市', '150700', '内蒙古自治区呼伦贝尔市根河市');
INSERT INTO `city` VALUES (449, '150800', '巴彦淖尔市', '150000', '内蒙古自治区巴彦淖尔市');
INSERT INTO `city` VALUES (450, '150801', '市辖区', '150800', '内蒙古自治区巴彦淖尔市市辖区');
INSERT INTO `city` VALUES (451, '150802', '临河区', '150800', '内蒙古自治区巴彦淖尔市临河区');
INSERT INTO `city` VALUES (452, '150821', '五原县', '150800', '内蒙古自治区巴彦淖尔市五原县');
INSERT INTO `city` VALUES (453, '150822', '磴口县', '150800', '内蒙古自治区巴彦淖尔市磴口县');
INSERT INTO `city` VALUES (454, '150823', '乌拉特前旗', '150800', '内蒙古自治区巴彦淖尔市乌拉特前旗');
INSERT INTO `city` VALUES (455, '150824', '乌拉特中旗', '150800', '内蒙古自治区巴彦淖尔市乌拉特中旗');
INSERT INTO `city` VALUES (456, '150825', '乌拉特后旗', '150800', '内蒙古自治区巴彦淖尔市乌拉特后旗');
INSERT INTO `city` VALUES (457, '150826', '杭锦后旗', '150800', '内蒙古自治区巴彦淖尔市杭锦后旗');
INSERT INTO `city` VALUES (458, '150900', '乌兰察布市', '150000', '内蒙古自治区乌兰察布市');
INSERT INTO `city` VALUES (459, '150901', '市辖区', '150900', '内蒙古自治区乌兰察布市市辖区');
INSERT INTO `city` VALUES (460, '150902', '集宁区', '150901', NULL);
INSERT INTO `city` VALUES (461, '150921', '卓资县', '150900', '内蒙古自治区乌兰察布市卓资县');
INSERT INTO `city` VALUES (462, '150922', '化德县', '150900', '内蒙古自治区乌兰察布市化德县');
INSERT INTO `city` VALUES (463, '150923', '商都县', '150900', '内蒙古自治区乌兰察布市商都县');
INSERT INTO `city` VALUES (464, '150924', '兴和县', '150900', '内蒙古自治区乌兰察布市兴和县');
INSERT INTO `city` VALUES (465, '150925', '凉城县', '150900', '内蒙古自治区乌兰察布市凉城县');
INSERT INTO `city` VALUES (466, '150926', '察哈尔右翼前旗', '150900', '内蒙古自治区乌兰察布市察哈尔右翼前旗');
INSERT INTO `city` VALUES (467, '150927', '察哈尔右翼中旗', '150900', '内蒙古自治区乌兰察布市察哈尔右翼中旗');
INSERT INTO `city` VALUES (468, '150928', '察哈尔右翼后旗', '150900', '内蒙古自治区乌兰察布市察哈尔右翼后旗');
INSERT INTO `city` VALUES (469, '150929', '四子王旗', '150900', '内蒙古自治区乌兰察布市四子王旗');
INSERT INTO `city` VALUES (470, '150981', '丰镇市', '150900', '内蒙古自治区乌兰察布市丰镇市');
INSERT INTO `city` VALUES (471, '152200', '兴安盟', '150000', '内蒙古自治区兴安盟');
INSERT INTO `city` VALUES (472, '152201', '乌兰浩特市', '152200', '内蒙古自治区兴安盟乌兰浩特市');
INSERT INTO `city` VALUES (473, '152202', '阿尔山市', '152200', '内蒙古自治区兴安盟阿尔山市');
INSERT INTO `city` VALUES (474, '152221', '科尔沁右翼前旗', '152200', '内蒙古自治区兴安盟科尔沁右翼前旗');
INSERT INTO `city` VALUES (475, '152222', '科尔沁右翼中旗', '152200', '内蒙古自治区兴安盟科尔沁右翼中旗');
INSERT INTO `city` VALUES (476, '152223', '扎赉特旗', '152200', '内蒙古自治区兴安盟扎赉特旗');
INSERT INTO `city` VALUES (477, '152224', '突泉县', '152200', '内蒙古自治区兴安盟突泉县');
INSERT INTO `city` VALUES (478, '152500', '锡林郭勒盟', '150000', '内蒙古自治区锡林郭勒盟');
INSERT INTO `city` VALUES (479, '152501', '二连浩特市', '152500', '内蒙古自治区锡林郭勒盟二连浩特市');
INSERT INTO `city` VALUES (480, '152502', '锡林浩特市', '152500', '内蒙古自治区锡林郭勒盟锡林浩特市');
INSERT INTO `city` VALUES (481, '152522', '阿巴嘎旗', '152500', '内蒙古自治区锡林郭勒盟阿巴嘎旗');
INSERT INTO `city` VALUES (482, '152523', '苏尼特左旗', '152500', '内蒙古自治区锡林郭勒盟苏尼特左旗');
INSERT INTO `city` VALUES (483, '152524', '苏尼特右旗', '152500', '内蒙古自治区锡林郭勒盟苏尼特右旗');
INSERT INTO `city` VALUES (484, '152525', '东乌珠穆沁旗', '152500', '内蒙古自治区锡林郭勒盟东乌珠穆沁旗');
INSERT INTO `city` VALUES (485, '152526', '西乌珠穆沁旗', '152500', '内蒙古自治区锡林郭勒盟西乌珠穆沁旗');
INSERT INTO `city` VALUES (486, '152527', '太仆寺旗', '152500', '内蒙古自治区锡林郭勒盟太仆寺旗');
INSERT INTO `city` VALUES (487, '152528', '镶黄旗', '152500', '内蒙古自治区锡林郭勒盟镶黄旗');
INSERT INTO `city` VALUES (488, '152529', '正镶白旗', '152500', '内蒙古自治区锡林郭勒盟正镶白旗');
INSERT INTO `city` VALUES (489, '152530', '正蓝旗', '152500', '内蒙古自治区锡林郭勒盟正蓝旗');
INSERT INTO `city` VALUES (490, '152531', '多伦县', '152500', '内蒙古自治区锡林郭勒盟多伦县');
INSERT INTO `city` VALUES (491, '152900', '阿拉善盟', '150000', '内蒙古自治区阿拉善盟');
INSERT INTO `city` VALUES (492, '152921', '阿拉善左旗', '152900', '内蒙古自治区阿拉善盟阿拉善左旗');
INSERT INTO `city` VALUES (493, '152922', '阿拉善右旗', '152900', '内蒙古自治区阿拉善盟阿拉善右旗');
INSERT INTO `city` VALUES (494, '152923', '额济纳旗', '152900', '内蒙古自治区阿拉善盟额济纳旗');
INSERT INTO `city` VALUES (495, '210000', '辽宁省', '0', '辽宁省');
INSERT INTO `city` VALUES (496, '210100', '沈阳市', '210000', '辽宁省沈阳市');
INSERT INTO `city` VALUES (497, '210101', '市辖区', '210100', '辽宁省沈阳市市辖区');
INSERT INTO `city` VALUES (498, '210102', '和平区', '210101', NULL);
INSERT INTO `city` VALUES (499, '210103', '沈河区', '210101', NULL);
INSERT INTO `city` VALUES (500, '210104', '大东区', '210101', NULL);
INSERT INTO `city` VALUES (501, '210105', '皇姑区', '210101', NULL);
INSERT INTO `city` VALUES (502, '210106', '铁西区', '210101', NULL);
INSERT INTO `city` VALUES (503, '210111', '苏家屯区', '210101', NULL);
INSERT INTO `city` VALUES (504, '210112', '东陵区', '210101', NULL);
INSERT INTO `city` VALUES (505, '210113', '新城子区', '210101', NULL);
INSERT INTO `city` VALUES (506, '210114', '于洪区', '210101', NULL);
INSERT INTO `city` VALUES (507, '210122', '辽中县', '210100', '辽宁省沈阳市辽中县');
INSERT INTO `city` VALUES (508, '210123', '康平县', '210100', '辽宁省沈阳市康平县');
INSERT INTO `city` VALUES (509, '210124', '法库县', '210100', '辽宁省沈阳市法库县');
INSERT INTO `city` VALUES (510, '210181', '新民市', '210100', '辽宁省沈阳市新民市');
INSERT INTO `city` VALUES (511, '210182', '沈北新区', '210100', '辽宁省沈阳市沈北新区');
INSERT INTO `city` VALUES (512, '210200', '大连市', '210000', '辽宁省大连市');
INSERT INTO `city` VALUES (513, '210201', '市辖区', '210200', '辽宁省大连市市辖区');
INSERT INTO `city` VALUES (514, '210202', '中山区', '210201', NULL);
INSERT INTO `city` VALUES (515, '210203', '西岗区', '210201', NULL);
INSERT INTO `city` VALUES (516, '210204', '沙河口区', '210201', NULL);
INSERT INTO `city` VALUES (517, '210211', '甘井子区', '210201', NULL);
INSERT INTO `city` VALUES (518, '210212', '旅顺口区', '210201', NULL);
INSERT INTO `city` VALUES (519, '210213', '金州区', '210201', NULL);
INSERT INTO `city` VALUES (520, '210224', '长海县', '210200', '辽宁省大连市长海县');
INSERT INTO `city` VALUES (521, '210281', '瓦房店市', '210200', '辽宁省大连市瓦房店市');
INSERT INTO `city` VALUES (522, '210282', '普兰店市', '210200', '辽宁省大连市普兰店市');
INSERT INTO `city` VALUES (523, '210283', '庄河市', '210200', '辽宁省大连市庄河市');
INSERT INTO `city` VALUES (524, '210300', '鞍山市', '210000', '辽宁省鞍山市');
INSERT INTO `city` VALUES (525, '210301', '市辖区', '210300', '辽宁省鞍山市市辖区');
INSERT INTO `city` VALUES (526, '210302', '铁东区', '210301', NULL);
INSERT INTO `city` VALUES (527, '210303', '铁西区', '210301', NULL);
INSERT INTO `city` VALUES (528, '210304', '立山区', '210301', NULL);
INSERT INTO `city` VALUES (529, '210311', '千山区', '210301', NULL);
INSERT INTO `city` VALUES (530, '210321', '台安县', '210300', '辽宁省鞍山市台安县');
INSERT INTO `city` VALUES (531, '210323', '岫岩满族自治县', '210300', '辽宁省鞍山市岫岩满族自治县');
INSERT INTO `city` VALUES (532, '210381', '海城市', '210300', '辽宁省鞍山市海城市');
INSERT INTO `city` VALUES (533, '210400', '抚顺市', '210000', '辽宁省抚顺市');
INSERT INTO `city` VALUES (534, '210401', '市辖区', '210400', '辽宁省抚顺市市辖区');
INSERT INTO `city` VALUES (535, '210402', '新抚区', '210401', NULL);
INSERT INTO `city` VALUES (536, '210403', '东洲区', '210401', NULL);
INSERT INTO `city` VALUES (537, '210404', '望花区', '210401', NULL);
INSERT INTO `city` VALUES (538, '210411', '顺城区', '210401', NULL);
INSERT INTO `city` VALUES (539, '210421', '抚顺县', '210400', '辽宁省抚顺市抚顺县');
INSERT INTO `city` VALUES (540, '210422', '新宾满族自治县', '210400', '辽宁省抚顺市新宾满族自治县');
INSERT INTO `city` VALUES (541, '210423', '清原满族自治县', '210400', '辽宁省抚顺市清原满族自治县');
INSERT INTO `city` VALUES (542, '210500', '本溪市', '210000', '辽宁省本溪市');
INSERT INTO `city` VALUES (543, '210501', '市辖区', '210500', '辽宁省本溪市市辖区');
INSERT INTO `city` VALUES (544, '210502', '平山区', '210501', NULL);
INSERT INTO `city` VALUES (545, '210503', '溪湖??', '210501', NULL);
INSERT INTO `city` VALUES (546, '210504', '明山区', '210501', NULL);
INSERT INTO `city` VALUES (547, '210505', '南芬区', '210501', NULL);
INSERT INTO `city` VALUES (548, '210521', '本溪满族自治县', '210500', '辽宁省本溪市本溪满族自治县');
INSERT INTO `city` VALUES (549, '210522', '桓仁满族自治县', '210500', '辽宁省本溪市桓仁满族自治县');
INSERT INTO `city` VALUES (550, '210600', '丹东市', '210000', '辽宁省丹东市');
INSERT INTO `city` VALUES (551, '210601', '市辖区', '210600', '辽宁省丹东市市辖区');
INSERT INTO `city` VALUES (552, '210602', '元宝区', '210601', NULL);
INSERT INTO `city` VALUES (553, '210603', '振兴区', '210601', NULL);
INSERT INTO `city` VALUES (554, '210604', '振安区', '210601', NULL);
INSERT INTO `city` VALUES (555, '210624', '宽甸满族自治县', '210600', '辽宁省丹东市宽甸满族自治县');
INSERT INTO `city` VALUES (556, '210681', '东港市', '210600', '辽宁省丹东市东港市');
INSERT INTO `city` VALUES (557, '210682', '凤城市', '210600', '辽宁省丹东市凤城市');
INSERT INTO `city` VALUES (558, '210700', '锦州市', '210000', '辽宁省锦州市');
INSERT INTO `city` VALUES (559, '210701', '市辖区', '210700', '辽宁省锦州市市辖区');
INSERT INTO `city` VALUES (560, '210702', '古塔区', '210701', NULL);
INSERT INTO `city` VALUES (561, '210703', '凌河区', '210701', NULL);
INSERT INTO `city` VALUES (562, '210711', '太和区', '210701', NULL);
INSERT INTO `city` VALUES (563, '210726', '黑山县', '210700', '辽宁省锦州市黑山县');
INSERT INTO `city` VALUES (564, '210727', '义县', '210700', '辽宁省锦州市义县');
INSERT INTO `city` VALUES (565, '210781', '凌海市', '210700', '辽宁省锦州市凌海市');
INSERT INTO `city` VALUES (566, '210782', '北宁市', '210700', '辽宁省锦州市北宁市');
INSERT INTO `city` VALUES (567, '210800', '营口市', '210000', '辽宁省营口市');
INSERT INTO `city` VALUES (568, '210801', '市辖区', '210800', '辽宁省营口市市辖区');
INSERT INTO `city` VALUES (569, '210802', '站前区', '210801', NULL);
INSERT INTO `city` VALUES (570, '210803', '西市区', '210801', NULL);
INSERT INTO `city` VALUES (571, '210804', '鲅鱼圈区', '210801', NULL);
INSERT INTO `city` VALUES (572, '210811', '老边区', '210801', NULL);
INSERT INTO `city` VALUES (573, '210881', '盖州市', '210800', '辽宁省营口市盖州市');
INSERT INTO `city` VALUES (574, '210882', '大石桥市', '210800', '辽宁省营口市大石桥市');
INSERT INTO `city` VALUES (575, '210900', '阜新市', '210000', '辽宁省阜新市');
INSERT INTO `city` VALUES (576, '210901', '市辖区', '210900', '辽宁省阜新市市辖区');
INSERT INTO `city` VALUES (577, '210902', '海州区', '210901', NULL);
INSERT INTO `city` VALUES (578, '210903', '新邱区', '210901', NULL);
INSERT INTO `city` VALUES (579, '210904', '太平区', '210901', NULL);
INSERT INTO `city` VALUES (580, '210905', '清河门区', '210901', NULL);
INSERT INTO `city` VALUES (581, '210911', '细河区', '210901', NULL);
INSERT INTO `city` VALUES (582, '210921', '阜新蒙古族自治县', '210900', '辽宁省阜新市阜新蒙古族自治县');
INSERT INTO `city` VALUES (583, '210922', '彰武县', '210900', '辽宁省阜新市彰武县');
INSERT INTO `city` VALUES (584, '211000', '辽阳市', '210000', '辽宁省辽阳市');
INSERT INTO `city` VALUES (585, '211001', '市辖区', '211000', '辽宁省辽阳市市辖区');
INSERT INTO `city` VALUES (586, '211002', '白塔区', '211001', NULL);
INSERT INTO `city` VALUES (587, '211003', '文圣区', '211001', NULL);
INSERT INTO `city` VALUES (588, '211004', '宏伟区', '211001', NULL);
INSERT INTO `city` VALUES (589, '211005', '弓长岭区', '211001', NULL);
INSERT INTO `city` VALUES (590, '211011', '太子河区', '211001', NULL);
INSERT INTO `city` VALUES (591, '211021', '辽阳县', '211000', '辽宁省辽阳市辽阳县');
INSERT INTO `city` VALUES (592, '211081', '灯塔市', '211000', '辽宁省辽阳市灯塔市');
INSERT INTO `city` VALUES (593, '211100', '盘锦市', '210000', '辽宁省盘锦市');
INSERT INTO `city` VALUES (594, '211101', '市辖区', '211100', '辽宁省盘锦市市辖区');
INSERT INTO `city` VALUES (595, '211102', '双台子区', '211101', NULL);
INSERT INTO `city` VALUES (596, '211103', '兴隆台区', '211101', NULL);
INSERT INTO `city` VALUES (597, '211121', '大洼县', '211100', '辽宁省盘锦市大洼县');
INSERT INTO `city` VALUES (598, '211122', '盘山县', '211100', '辽宁省盘锦市盘山县');
INSERT INTO `city` VALUES (599, '211200', '铁岭市', '210000', '辽宁省铁岭市');
INSERT INTO `city` VALUES (600, '211201', '市辖区', '211200', '辽宁省铁岭市市辖区');
INSERT INTO `city` VALUES (601, '211202', '银州区', '211201', NULL);
INSERT INTO `city` VALUES (602, '211204', '清河区', '211201', NULL);
INSERT INTO `city` VALUES (603, '211221', '铁岭县', '211200', '辽宁省铁岭市铁岭县');
INSERT INTO `city` VALUES (604, '211223', '西丰县', '211200', '辽宁省铁岭市西丰县');
INSERT INTO `city` VALUES (605, '211224', '昌图县', '211200', '辽宁省铁岭市昌图县');
INSERT INTO `city` VALUES (606, '211281', '调兵山市', '211200', '辽宁省铁岭市调兵山市');
INSERT INTO `city` VALUES (607, '211282', '开原市', '211200', '辽宁省铁岭市开原市');
INSERT INTO `city` VALUES (608, '211300', '朝阳市', '210000', '辽宁省朝阳市');
INSERT INTO `city` VALUES (609, '211301', '市辖区', '211300', '辽宁省朝阳市市辖区');
INSERT INTO `city` VALUES (610, '211302', '双塔区', '211301', NULL);
INSERT INTO `city` VALUES (611, '211303', '龙城区', '211301', NULL);
INSERT INTO `city` VALUES (612, '211321', '朝阳县', '211300', '辽宁省朝阳市朝阳县');
INSERT INTO `city` VALUES (613, '211322', '建平县', '211300', '辽宁省朝阳市建平县');
INSERT INTO `city` VALUES (614, '211324', '喀喇沁左翼蒙古族自治县', '211300', '辽宁省朝阳市喀喇沁左翼蒙古族自治县');
INSERT INTO `city` VALUES (615, '211381', '北票市', '211300', '辽宁省朝阳市北票市');
INSERT INTO `city` VALUES (616, '211382', '凌源市', '211300', '辽宁省朝阳市凌源市');
INSERT INTO `city` VALUES (617, '211400', '葫芦岛市', '210000', '辽宁省葫芦岛市');
INSERT INTO `city` VALUES (618, '211401', '市辖区', '211400', '辽宁省葫芦岛市市辖区');
INSERT INTO `city` VALUES (619, '211402', '连山区', '211401', NULL);
INSERT INTO `city` VALUES (620, '211403', '龙港区', '211401', NULL);
INSERT INTO `city` VALUES (621, '211404', '南票区', '211401', NULL);
INSERT INTO `city` VALUES (622, '211421', '绥中县', '211400', '辽宁省葫芦岛市绥中县');
INSERT INTO `city` VALUES (623, '211422', '建昌县', '211400', '辽宁省葫芦岛市建昌县');
INSERT INTO `city` VALUES (624, '211481', '兴城市', '211400', '辽宁省葫芦岛市兴城市');
INSERT INTO `city` VALUES (625, '220000', '吉林省', '0', '吉林省');
INSERT INTO `city` VALUES (626, '220100', '长春市', '220000', '吉林省长春市');
INSERT INTO `city` VALUES (627, '220101', '市辖区', '220100', '吉林省长春市市辖区');
INSERT INTO `city` VALUES (628, '220102', '南关区', '220101', NULL);
INSERT INTO `city` VALUES (629, '220103', '宽城区', '220101', NULL);
INSERT INTO `city` VALUES (630, '220104', '朝阳区', '220101', NULL);
INSERT INTO `city` VALUES (631, '220105', '二道区', '220101', NULL);
INSERT INTO `city` VALUES (632, '220106', '绿园区', '220101', NULL);
INSERT INTO `city` VALUES (633, '220112', '双阳区', '220101', NULL);
INSERT INTO `city` VALUES (634, '220122', '农安县', '220100', '吉林省长春市农安县');
INSERT INTO `city` VALUES (635, '220181', '九台市', '220100', '吉林省长春市九台市');
INSERT INTO `city` VALUES (636, '220182', '榆树市', '220100', '吉林省长春市榆树市');
INSERT INTO `city` VALUES (637, '220183', '德惠市', '220100', '吉林省长春市德惠市');
INSERT INTO `city` VALUES (638, '220200', '吉林市', '220000', '吉林省吉林市');
INSERT INTO `city` VALUES (639, '220201', '市辖区', '220200', '吉林省吉林市市辖区');
INSERT INTO `city` VALUES (640, '220202', '昌邑区', '220201', NULL);
INSERT INTO `city` VALUES (641, '220203', '龙潭区', '220201', NULL);
INSERT INTO `city` VALUES (642, '220204', '船营区', '220201', NULL);
INSERT INTO `city` VALUES (643, '220211', '丰满区', '220201', NULL);
INSERT INTO `city` VALUES (644, '220221', '永吉县', '220200', '吉林省吉林市永吉县');
INSERT INTO `city` VALUES (645, '220281', '蛟河市', '220200', '吉林省吉林市蛟河市');
INSERT INTO `city` VALUES (646, '220282', '桦甸市', '220200', '吉林省吉林市桦甸市');
INSERT INTO `city` VALUES (647, '220283', '舒兰市', '220200', '吉林省吉林市舒兰市');
INSERT INTO `city` VALUES (648, '220284', '磐石市', '220200', '吉林省吉林市磐石市');
INSERT INTO `city` VALUES (649, '220300', '四平市', '220000', '吉林省四平市');
INSERT INTO `city` VALUES (650, '220301', '市辖区', '220300', '吉林省四平市市辖区');
INSERT INTO `city` VALUES (651, '220302', '铁西区', '220301', NULL);
INSERT INTO `city` VALUES (652, '220303', '铁东区', '220301', NULL);
INSERT INTO `city` VALUES (653, '220322', '梨树县', '220300', '吉林省四平市梨树县');
INSERT INTO `city` VALUES (654, '220323', '伊通满族自治县', '220300', '吉林省四平市伊通满族自治县');
INSERT INTO `city` VALUES (655, '220381', '公主岭市', '220300', '吉林省四平市公主岭市');
INSERT INTO `city` VALUES (656, '220382', '双辽市', '220300', '吉林省四平市双辽市');
INSERT INTO `city` VALUES (657, '220400', '辽源市', '220000', '吉林省辽源市');
INSERT INTO `city` VALUES (658, '220401', '市辖区', '220400', '吉林省辽源市市辖区');
INSERT INTO `city` VALUES (659, '220402', '龙山区', '220401', NULL);
INSERT INTO `city` VALUES (660, '220403', '西安区', '220401', NULL);
INSERT INTO `city` VALUES (661, '220421', '东丰县', '220400', '吉林省辽源市东丰县');
INSERT INTO `city` VALUES (662, '220422', '东辽县', '220400', '吉林省辽源市东辽县');
INSERT INTO `city` VALUES (663, '220500', '通化市', '220000', '吉林省通化市');
INSERT INTO `city` VALUES (664, '220501', '市辖区', '220500', '吉林省通化市市辖区');
INSERT INTO `city` VALUES (665, '220502', '东昌区', '220501', NULL);
INSERT INTO `city` VALUES (666, '220503', '二道江区', '220501', NULL);
INSERT INTO `city` VALUES (667, '220521', '通化县', '220500', '吉林省通化市通化县');
INSERT INTO `city` VALUES (668, '220523', '辉南县', '220500', '吉林省通化市辉南县');
INSERT INTO `city` VALUES (669, '220524', '柳河县', '220500', '吉林省通化市柳河县');
INSERT INTO `city` VALUES (670, '220581', '梅河口市', '220500', '吉林省通化市梅河口市');
INSERT INTO `city` VALUES (671, '220582', '集安市', '220500', '吉林省通化市集安市');
INSERT INTO `city` VALUES (672, '220600', '白山市', '220000', '吉林省白山市');
INSERT INTO `city` VALUES (673, '220601', '市辖区', '220600', '吉林省白山市市辖区');
INSERT INTO `city` VALUES (674, '220602', '八道江区', '220601', NULL);
INSERT INTO `city` VALUES (675, '220621', '抚松县', '220600', '吉林省白山市抚松县');
INSERT INTO `city` VALUES (676, '220622', '靖宇县', '220600', '吉林省白山市靖宇县');
INSERT INTO `city` VALUES (677, '220623', '长白朝鲜族自治县', '220600', '吉林省白山市长白朝鲜族自治县');
INSERT INTO `city` VALUES (678, '220625', '江源区', '220600', '吉林省白山市江源区');
INSERT INTO `city` VALUES (679, '220681', '临江市', '220600', '吉林省白山市临江市');
INSERT INTO `city` VALUES (680, '220700', '松原市', '220000', '吉林省松原市');
INSERT INTO `city` VALUES (681, '220701', '市辖区', '220700', '吉林省松原市市辖区');
INSERT INTO `city` VALUES (682, '220702', '宁江区', '220701', NULL);
INSERT INTO `city` VALUES (683, '220721', '前郭尔罗斯蒙古族自治县', '220700', '吉林省松原市前郭尔罗斯蒙古族自治县');
INSERT INTO `city` VALUES (684, '220722', '长岭县', '220700', '吉林省松原市长岭县');
INSERT INTO `city` VALUES (685, '220723', '乾安县', '220700', '吉林省松原市乾安县');
INSERT INTO `city` VALUES (686, '220724', '扶余市', '220700', '吉林省松原市扶余市');
INSERT INTO `city` VALUES (687, '220800', '白城市', '220000', '吉林省白城市');
INSERT INTO `city` VALUES (688, '220801', '市辖区', '220800', '吉林省白城市市辖区');
INSERT INTO `city` VALUES (689, '220802', '洮北区', '220800', '吉林省白城市洮北区');
INSERT INTO `city` VALUES (690, '220821', '镇赉县', '220800', '吉林省白城市镇赉县');
INSERT INTO `city` VALUES (691, '220822', '通榆县', '220800', '吉林省白城市通榆县');
INSERT INTO `city` VALUES (692, '220881', '洮南市', '220800', '吉林省白城市洮南市');
INSERT INTO `city` VALUES (693, '220882', '大安市', '220800', '吉林省白城市大安市');
INSERT INTO `city` VALUES (694, '222400', '延边朝鲜族自治州', '220000', '吉林省延边朝鲜族自治州');
INSERT INTO `city` VALUES (695, '222401', '延吉市', '222400', '吉林省延边朝鲜族自治州延吉市');
INSERT INTO `city` VALUES (696, '222402', '图们市', '222400', '吉林省延边朝鲜族自治州图们市');
INSERT INTO `city` VALUES (697, '222403', '敦化市', '222400', '吉林省延边朝鲜族自治州敦化市');
INSERT INTO `city` VALUES (698, '222404', '珲春市', '222400', '吉林省延边朝鲜族自治州珲春市');
INSERT INTO `city` VALUES (699, '222405', '龙井市', '222400', '吉林省延边朝鲜族自治州龙井市');
INSERT INTO `city` VALUES (700, '222406', '和龙市', '222400', '吉林省延边朝鲜族自治州和龙市');
INSERT INTO `city` VALUES (701, '222424', '汪清县', '222400', '吉林省延边朝鲜族自治州汪清县');
INSERT INTO `city` VALUES (702, '222426', '安图县', '222400', '吉林省延边朝鲜族自治州安图县');
INSERT INTO `city` VALUES (703, '230000', '黑龙江省', '0', '黑龙江省');
INSERT INTO `city` VALUES (704, '230100', '哈尔滨市', '230000', '黑龙江省哈尔滨市');
INSERT INTO `city` VALUES (705, '230101', '市辖区', '230100', '黑龙江省哈尔滨市市辖区');
INSERT INTO `city` VALUES (706, '230102', '道里区', '230101', NULL);
INSERT INTO `city` VALUES (707, '230103', '南岗区', '230101', NULL);
INSERT INTO `city` VALUES (708, '230104', '道外区', '230101', NULL);
INSERT INTO `city` VALUES (709, '230106', '香坊区', '230101', NULL);
INSERT INTO `city` VALUES (710, '230107', '动力区', '230101', NULL);
INSERT INTO `city` VALUES (711, '230108', '平房区', '230101', NULL);
INSERT INTO `city` VALUES (712, '230109', '松北区', '230101', NULL);
INSERT INTO `city` VALUES (713, '230111', '呼兰区', '230101', NULL);
INSERT INTO `city` VALUES (714, '230123', '依兰县', '230100', '黑龙江省哈尔滨市依兰县');
INSERT INTO `city` VALUES (715, '230124', '方正县', '230100', '黑龙江省哈尔滨市方正县');
INSERT INTO `city` VALUES (716, '230125', '宾县', '230100', '黑龙江省哈尔滨市宾县');
INSERT INTO `city` VALUES (717, '230126', '巴彦县', '230100', '黑龙江省哈尔滨市巴彦县');
INSERT INTO `city` VALUES (718, '230127', '木兰县', '230100', '黑龙江省哈尔滨市木兰县');
INSERT INTO `city` VALUES (719, '230128', '通河县', '230100', '黑龙江省哈尔滨市通河县');
INSERT INTO `city` VALUES (720, '230129', '延寿县', '230100', '黑龙江省哈尔滨市延寿县');
INSERT INTO `city` VALUES (721, '230181', '阿城市', '230100', '黑龙江省哈尔滨市阿城市');
INSERT INTO `city` VALUES (722, '230182', '双城市', '230100', '黑龙江省哈尔滨市双城市');
INSERT INTO `city` VALUES (723, '230183', '尚志市', '230100', '黑龙江省哈尔滨市尚志市');
INSERT INTO `city` VALUES (724, '230184', '五常市', '230100', '黑龙江省哈尔滨市五常市');
INSERT INTO `city` VALUES (725, '230200', '齐齐哈尔市', '230000', '黑龙江省齐齐哈尔市');
INSERT INTO `city` VALUES (726, '230201', '市辖区', '230200', '黑龙江省齐齐哈尔市市辖区');
INSERT INTO `city` VALUES (727, '230202', '龙沙区', '230201', NULL);
INSERT INTO `city` VALUES (728, '230203', '建华区', '230201', NULL);
INSERT INTO `city` VALUES (729, '230204', '铁锋区', '230201', NULL);
INSERT INTO `city` VALUES (730, '230205', '昂昂溪区', '230201', NULL);
INSERT INTO `city` VALUES (731, '230206', '富拉尔基区', '230201', NULL);
INSERT INTO `city` VALUES (732, '230207', '碾子山区', '230201', NULL);
INSERT INTO `city` VALUES (733, '230208', '梅里斯达斡尔族区', '230201', NULL);
INSERT INTO `city` VALUES (734, '230221', '龙江县', '230200', '黑龙江省齐齐哈尔市龙江县');
INSERT INTO `city` VALUES (735, '230223', '依安县', '230200', '黑龙江省齐齐哈尔市依安县');
INSERT INTO `city` VALUES (736, '230224', '泰来县', '230200', '黑龙江省齐齐哈尔市泰来县');
INSERT INTO `city` VALUES (737, '230225', '甘南县', '230200', '黑龙江省齐齐哈尔市甘南县');
INSERT INTO `city` VALUES (738, '230227', '富裕县', '230200', '黑龙江省齐齐哈尔市富裕县');
INSERT INTO `city` VALUES (739, '230229', '克山县', '230200', '黑龙江省齐齐哈尔市克山县');
INSERT INTO `city` VALUES (740, '230230', '克东县', '230200', '黑龙江省齐齐哈尔市克东县');
INSERT INTO `city` VALUES (741, '230231', '拜泉县', '230200', '黑龙江省齐齐哈尔市拜泉县');
INSERT INTO `city` VALUES (742, '230281', '讷河市', '230200', '黑龙江省齐齐哈尔市讷河市');
INSERT INTO `city` VALUES (743, '230300', '鸡西市', '230000', '黑龙江省鸡西市');
INSERT INTO `city` VALUES (744, '230301', '市辖区', '230300', '黑龙江省鸡西市市辖区');
INSERT INTO `city` VALUES (745, '230302', '鸡冠区', '230301', NULL);
INSERT INTO `city` VALUES (746, '230303', '恒山区', '230301', NULL);
INSERT INTO `city` VALUES (747, '230304', '滴道区', '230301', NULL);
INSERT INTO `city` VALUES (748, '230305', '梨树区', '230301', NULL);
INSERT INTO `city` VALUES (749, '230306', '城子河区', '230301', NULL);
INSERT INTO `city` VALUES (750, '230307', '麻山区', '230301', NULL);
INSERT INTO `city` VALUES (751, '230321', '鸡东县', '230300', '黑龙江省鸡西市鸡东县');
INSERT INTO `city` VALUES (752, '230381', '虎林市', '230300', '黑龙江省鸡西市虎林市');
INSERT INTO `city` VALUES (753, '230382', '密山市', '230300', '黑龙江省鸡西市密山市');
INSERT INTO `city` VALUES (754, '230400', '鹤岗市', '230000', '黑龙江省鹤岗市');
INSERT INTO `city` VALUES (755, '230401', '市辖区', '230400', '黑龙江省鹤岗市市辖区');
INSERT INTO `city` VALUES (756, '230402', '向阳区', '230401', NULL);
INSERT INTO `city` VALUES (757, '230403', '工农区', '230401', NULL);
INSERT INTO `city` VALUES (758, '230404', '南山区', '230401', NULL);
INSERT INTO `city` VALUES (759, '230405', '兴安区', '230401', NULL);
INSERT INTO `city` VALUES (760, '230406', '东山区', '230401', NULL);
INSERT INTO `city` VALUES (761, '230407', '兴山区', '230401', NULL);
INSERT INTO `city` VALUES (762, '230421', '萝北县', '230400', '黑龙江省鹤岗市萝北县');
INSERT INTO `city` VALUES (763, '230422', '绥滨县', '230400', '黑龙江省鹤岗市绥滨县');
INSERT INTO `city` VALUES (764, '230500', '双鸭山市', '230000', '黑龙江省双鸭山市');
INSERT INTO `city` VALUES (765, '230501', '市辖区', '230500', '黑龙江省双鸭山市市辖区');
INSERT INTO `city` VALUES (766, '230502', '尖山区', '230501', NULL);
INSERT INTO `city` VALUES (767, '230503', '岭东区', '230501', NULL);
INSERT INTO `city` VALUES (768, '230505', '四方台区', '230501', NULL);
INSERT INTO `city` VALUES (769, '230506', '宝山区', '230501', NULL);
INSERT INTO `city` VALUES (770, '230521', '集贤县', '230500', '黑龙江省双鸭山市集贤县');
INSERT INTO `city` VALUES (771, '230522', '友谊县', '230500', '黑龙江省双鸭山市友谊县');
INSERT INTO `city` VALUES (772, '230523', '宝清县', '230500', '黑龙江省双鸭山市宝清县');
INSERT INTO `city` VALUES (773, '230524', '饶河县', '230500', '黑龙江省双鸭山市饶河县');
INSERT INTO `city` VALUES (774, '230600', '大庆市', '230000', '黑龙江省大庆市');
INSERT INTO `city` VALUES (775, '230601', '市辖区', '230600', '黑龙江省大庆市市辖区');
INSERT INTO `city` VALUES (776, '230602', '萨尔图区', '230601', NULL);
INSERT INTO `city` VALUES (777, '230603', '龙凤区', '230601', NULL);
INSERT INTO `city` VALUES (778, '230604', '让胡路区', '230601', NULL);
INSERT INTO `city` VALUES (779, '230605', '红岗区', '230601', NULL);
INSERT INTO `city` VALUES (780, '230606', '大同区', '230601', NULL);
INSERT INTO `city` VALUES (781, '230621', '肇州县', '230600', '黑龙江省大庆市肇州县');
INSERT INTO `city` VALUES (782, '230622', '肇源县', '230600', '黑龙江省大庆市肇源县');
INSERT INTO `city` VALUES (783, '230623', '林甸县', '230600', '黑龙江省大庆市林甸县');
INSERT INTO `city` VALUES (784, '230624', '杜尔伯特蒙古族自治县', '230600', '黑龙江省大庆市杜尔伯特蒙古族自治县');
INSERT INTO `city` VALUES (785, '230700', '伊春市', '230000', '黑龙江省伊春市');
INSERT INTO `city` VALUES (786, '230701', '市辖区', '230700', '黑龙江省伊春市市辖区');
INSERT INTO `city` VALUES (787, '230702', '伊春区', '230701', NULL);
INSERT INTO `city` VALUES (788, '230703', '南岔区', '230701', NULL);
INSERT INTO `city` VALUES (789, '230704', '友好区', '230701', NULL);
INSERT INTO `city` VALUES (790, '230705', '西林区', '230701', NULL);
INSERT INTO `city` VALUES (791, '230706', '翠峦区', '230701', NULL);
INSERT INTO `city` VALUES (792, '230707', '新青区', '230701', NULL);
INSERT INTO `city` VALUES (793, '230708', '美溪区', '230701', NULL);
INSERT INTO `city` VALUES (794, '230709', '金山屯区', '230701', NULL);
INSERT INTO `city` VALUES (795, '230710', '五营区', '230701', NULL);
INSERT INTO `city` VALUES (796, '230711', '乌马河区', '230701', NULL);
INSERT INTO `city` VALUES (797, '230712', '汤旺河区', '230701', NULL);
INSERT INTO `city` VALUES (798, '230713', '带岭区', '230701', NULL);
INSERT INTO `city` VALUES (799, '230714', '乌伊岭区', '230701', NULL);
INSERT INTO `city` VALUES (800, '230715', '红星区', '230701', NULL);
INSERT INTO `city` VALUES (801, '230716', '上甘岭区', '230701', NULL);
INSERT INTO `city` VALUES (802, '230722', '嘉荫县', '230700', '黑龙江省伊春市嘉荫县');
INSERT INTO `city` VALUES (803, '230781', '铁力市', '230700', '黑龙江省伊春市铁力市');
INSERT INTO `city` VALUES (804, '230800', '佳木斯市', '230000', '黑龙江省佳木斯市');
INSERT INTO `city` VALUES (805, '230801', '市辖区', '230800', '黑龙江省佳木斯市市辖区');
INSERT INTO `city` VALUES (806, '230803', '向阳区', '230801', NULL);
INSERT INTO `city` VALUES (807, '230804', '前进区', '230801', NULL);
INSERT INTO `city` VALUES (808, '230805', '东风区', '230801', NULL);
INSERT INTO `city` VALUES (809, '230811', '郊区', '230801', NULL);
INSERT INTO `city` VALUES (810, '230822', '桦南县', '230800', '黑龙江省佳木斯市桦南县');
INSERT INTO `city` VALUES (811, '230826', '桦川县', '230800', '黑龙江省佳木斯市桦川县');
INSERT INTO `city` VALUES (812, '230828', '汤原县', '230800', '黑龙江省佳木斯市汤原县');
INSERT INTO `city` VALUES (813, '230833', '抚远县', '230800', '黑龙江省佳木斯市抚远县');
INSERT INTO `city` VALUES (814, '230881', '同江市', '230800', '黑龙江省佳木斯市同江市');
INSERT INTO `city` VALUES (815, '230882', '富锦市', '230800', '黑龙江省佳木斯市富锦市');
INSERT INTO `city` VALUES (816, '230900', '七台河市', '230000', '黑龙江省七台河市');
INSERT INTO `city` VALUES (817, '230901', '市辖区', '230900', '黑龙江省七台河市市辖区');
INSERT INTO `city` VALUES (818, '230902', '新兴区', '230901', NULL);
INSERT INTO `city` VALUES (819, '230903', '桃山区', '230901', NULL);
INSERT INTO `city` VALUES (820, '230904', '茄子河区', '230901', NULL);
INSERT INTO `city` VALUES (821, '230921', '勃利县', '230900', '黑龙江省七台河市勃利县');
INSERT INTO `city` VALUES (822, '231000', '牡丹江市', '230000', '黑龙江省牡丹江市');
INSERT INTO `city` VALUES (823, '231001', '市辖区', '231000', '黑龙江省牡丹江市市辖区');
INSERT INTO `city` VALUES (824, '231002', '东安区', '231001', NULL);
INSERT INTO `city` VALUES (825, '231003', '阳明区', '231001', NULL);
INSERT INTO `city` VALUES (826, '231004', '爱民区', '231001', NULL);
INSERT INTO `city` VALUES (827, '231005', '西安区', '231001', NULL);
INSERT INTO `city` VALUES (828, '231024', '东宁县', '231000', '黑龙江省牡丹江市东宁县');
INSERT INTO `city` VALUES (829, '231025', '林口县', '231000', '黑龙江省牡丹江市林口县');
INSERT INTO `city` VALUES (830, '231081', '绥芬河市', '231000', '黑龙江省牡丹江市绥芬河市');
INSERT INTO `city` VALUES (831, '231083', '海林市', '231000', '黑龙江省牡丹江市海林市');
INSERT INTO `city` VALUES (832, '231084', '宁安市', '231000', '黑龙江省牡丹江市宁安市');
INSERT INTO `city` VALUES (833, '231085', '穆棱市', '231000', '黑龙江省牡丹江市穆棱市');
INSERT INTO `city` VALUES (834, '231100', '黑河市', '230000', '黑龙江省黑河市');
INSERT INTO `city` VALUES (835, '231101', '市辖区', '231100', '黑龙江省黑河市市辖区');
INSERT INTO `city` VALUES (836, '231102', '爱辉区', '231101', NULL);
INSERT INTO `city` VALUES (837, '231121', '嫩江县', '231100', '黑龙江省黑河市嫩江县');
INSERT INTO `city` VALUES (838, '231123', '逊克县', '231100', '黑龙江省黑河市逊克县');
INSERT INTO `city` VALUES (839, '231124', '孙吴县', '231100', '黑龙江省黑河市孙吴县');
INSERT INTO `city` VALUES (840, '231181', '北安市', '231100', '黑龙江省黑河市北安市');
INSERT INTO `city` VALUES (841, '231182', '五大连池市', '231100', '黑龙江省黑河市五大连池市');
INSERT INTO `city` VALUES (842, '231200', '绥化市', '230000', '黑龙江省绥化市');
INSERT INTO `city` VALUES (843, '231201', '北林区', '231200', '黑龙江省绥化市北林区');
INSERT INTO `city` VALUES (844, '231221', '望奎县', '231200', '黑龙江省绥化市望奎县');
INSERT INTO `city` VALUES (845, '231222', '兰西县', '231200', '黑龙江省绥化市兰西县');
INSERT INTO `city` VALUES (846, '231223', '青冈县', '231200', '黑龙江省绥化市青冈县');
INSERT INTO `city` VALUES (847, '231224', '庆安县', '231200', '黑龙江省绥化市庆安县');
INSERT INTO `city` VALUES (848, '231225', '明水县', '231200', '黑龙江省绥化市明水县');
INSERT INTO `city` VALUES (849, '231226', '绥棱县', '231200', '黑龙江省绥化市绥棱县');
INSERT INTO `city` VALUES (850, '231281', '安达市', '231200', '黑龙江省绥化市安达市');
INSERT INTO `city` VALUES (851, '231282', '肇东市', '231200', '黑龙江省绥化市肇东市');
INSERT INTO `city` VALUES (852, '231283', '海伦市', '231200', '黑龙江省绥化市海伦市');
INSERT INTO `city` VALUES (853, '232700', '大兴安岭地区', '230000', '黑龙江省大兴安岭地区');
INSERT INTO `city` VALUES (854, '232701', '加格达奇区', '232700', '黑龙江省大兴安岭地区加格达奇区');
INSERT INTO `city` VALUES (855, '232702', '松岭区', '232700', '黑龙江省大兴安岭地区松岭区');
INSERT INTO `city` VALUES (856, '232703', '新林区', '232700', '黑龙江省大兴安岭地区新林区');
INSERT INTO `city` VALUES (857, '232704', '呼中区', '232700', '黑龙江省大兴安岭地区呼中区');
INSERT INTO `city` VALUES (858, '232721', '呼玛县', '232700', '黑龙江省大兴安岭地区呼玛县');
INSERT INTO `city` VALUES (859, '232722', '塔河县', '232700', '黑龙江省大兴安岭地区塔河县');
INSERT INTO `city` VALUES (860, '232723', '漠河县', '232700', '黑龙江省大兴安岭地区漠河县');
INSERT INTO `city` VALUES (861, '310000', '上海市', '0', '上海市');
INSERT INTO `city` VALUES (862, '310100', '黄浦区', '310000', '上海市黄浦区');
INSERT INTO `city` VALUES (863, '310300', '卢湾区', '310000', '上海市卢湾区');
INSERT INTO `city` VALUES (864, '310400', '徐汇区', '310000', '上海市徐汇区');
INSERT INTO `city` VALUES (865, '310500', '长宁区', '310000', '上海市长宁区');
INSERT INTO `city` VALUES (866, '310600', '静安区', '310000', '上海市静安区');
INSERT INTO `city` VALUES (867, '310700', '普陀区', '310000', '上海市普陀区');
INSERT INTO `city` VALUES (868, '310800', '闸北区', '310000', '上海市闸北区');
INSERT INTO `city` VALUES (869, '310900', '虹口区', '310000', '上海市虹口区');
INSERT INTO `city` VALUES (870, '311000', '杨浦区', '310000', '上海市杨浦区');
INSERT INTO `city` VALUES (871, '311200', '闵行区', '310000', '上海市闵行区');
INSERT INTO `city` VALUES (872, '311300', '宝山区', '310000', '上海市宝山区');
INSERT INTO `city` VALUES (873, '311400', '嘉定区', '310000', '上海市嘉定区');
INSERT INTO `city` VALUES (874, '311500', '浦东新区', '310000', '上海市浦东新区');
INSERT INTO `city` VALUES (875, '311600', '金山区', '310000', '上海市金山区');
INSERT INTO `city` VALUES (876, '311700', '松江区', '310000', '上海市松江区');
INSERT INTO `city` VALUES (877, '311800', '青浦区', '310000', '上海市青浦区');
INSERT INTO `city` VALUES (878, '311900', '南汇区', '310000', '上海市南汇区');
INSERT INTO `city` VALUES (879, '312000', '奉贤区', '310000', '上海市奉贤区');
INSERT INTO `city` VALUES (880, '313000', '崇明县', '310000', '上海市崇明县');
INSERT INTO `city` VALUES (881, '320000', '江苏省', '0', '江苏省');
INSERT INTO `city` VALUES (882, '320100', '南京市', '320000', '江苏省南京市');
INSERT INTO `city` VALUES (883, '320101', '市辖区', '320100', '江苏省南京市市辖区');
INSERT INTO `city` VALUES (884, '320102', '玄武区', '320101', NULL);
INSERT INTO `city` VALUES (885, '320103', '白下区', '320101', NULL);
INSERT INTO `city` VALUES (886, '320104', '秦淮区', '320101', NULL);
INSERT INTO `city` VALUES (887, '320105', '建邺区', '320101', NULL);
INSERT INTO `city` VALUES (888, '320106', '鼓楼区', '320101', NULL);
INSERT INTO `city` VALUES (889, '320107', '下关区', '320101', NULL);
INSERT INTO `city` VALUES (890, '320111', '浦口区', '320101', NULL);
INSERT INTO `city` VALUES (891, '320113', '栖霞区', '320101', NULL);
INSERT INTO `city` VALUES (892, '320114', '雨花台区', '320101', NULL);
INSERT INTO `city` VALUES (893, '320115', '江宁区', '320101', NULL);
INSERT INTO `city` VALUES (894, '320116', '六合区', '320101', NULL);
INSERT INTO `city` VALUES (895, '320124', '溧水区', '320100', '江苏省南京市溧水区');
INSERT INTO `city` VALUES (896, '320125', '高淳区', '320100', '江苏省南京市高淳区');
INSERT INTO `city` VALUES (897, '320200', '无锡市', '320000', '江苏省无锡市');
INSERT INTO `city` VALUES (898, '320201', '市辖区', '320200', '江苏省无锡市市辖区');
INSERT INTO `city` VALUES (899, '320202', '崇安区', '320201', NULL);
INSERT INTO `city` VALUES (900, '320203', '南长区', '320201', NULL);
INSERT INTO `city` VALUES (901, '320204', '北塘区', '320201', NULL);
INSERT INTO `city` VALUES (902, '320205', '锡山区', '320201', NULL);
INSERT INTO `city` VALUES (903, '320206', '惠山区', '320201', NULL);
INSERT INTO `city` VALUES (904, '320211', '滨湖区', '320201', NULL);
INSERT INTO `city` VALUES (905, '320281', '江阴市', '320200', '江苏省无锡市江阴市');
INSERT INTO `city` VALUES (906, '320282', '宜兴市', '320200', '江苏省无锡市宜兴市');
INSERT INTO `city` VALUES (907, '320300', '徐州市', '320000', '江苏省徐州市');
INSERT INTO `city` VALUES (908, '320301', '泉山区', '320300', '江苏省徐州市泉山区');
INSERT INTO `city` VALUES (909, '320302', '鼓楼区', '320300', '江苏省徐州市鼓楼区');
INSERT INTO `city` VALUES (910, '320303', '云龙区', '320300', '江苏省徐州市云龙区');
INSERT INTO `city` VALUES (911, '320304', '九里区', '320301', NULL);
INSERT INTO `city` VALUES (912, '320305', '贾汪区', '320300', '江苏省徐州市贾汪区');
INSERT INTO `city` VALUES (913, '320321', '丰县', '320300', '江苏省徐州市丰县');
INSERT INTO `city` VALUES (914, '320322', '沛县', '320300', '江苏省徐州市沛县');
INSERT INTO `city` VALUES (915, '320323', '铜山县', '320300', '江苏省徐州市铜山县');
INSERT INTO `city` VALUES (916, '320324', '睢宁县', '320300', '江苏省徐州市睢宁县');
INSERT INTO `city` VALUES (917, '320381', '新沂市', '320300', '江苏省徐州市新沂市');
INSERT INTO `city` VALUES (918, '320382', '邳州市', '320300', '江苏省徐州市邳州市');
INSERT INTO `city` VALUES (919, '320400', '常州市', '320000', '江苏省常州市');
INSERT INTO `city` VALUES (920, '320401', '市辖区', '320400', '江苏省常州市市辖区');
INSERT INTO `city` VALUES (921, '320402', '天宁区', '320401', NULL);
INSERT INTO `city` VALUES (922, '320404', '钟楼区', '320401', NULL);
INSERT INTO `city` VALUES (923, '320405', '戚墅堰区', '320401', NULL);
INSERT INTO `city` VALUES (924, '320411', '新北区', '320401', NULL);
INSERT INTO `city` VALUES (925, '320412', '武进区', '320401', NULL);
INSERT INTO `city` VALUES (926, '320481', '溧阳市', '320400', '江苏省常州市溧阳市');
INSERT INTO `city` VALUES (927, '320482', '金坛市', '320400', '江苏省常州市金坛市');
INSERT INTO `city` VALUES (928, '320500', '苏州市', '320000', '江苏省苏州市');
INSERT INTO `city` VALUES (929, '320501', '市辖区', '320500', '江苏省苏州市市辖区');
INSERT INTO `city` VALUES (930, '320502', '沧浪区', '320501', NULL);
INSERT INTO `city` VALUES (931, '320503', '平江区', '320501', NULL);
INSERT INTO `city` VALUES (932, '320504', '金阊区', '320501', NULL);
INSERT INTO `city` VALUES (933, '320505', '虎丘区', '320501', NULL);
INSERT INTO `city` VALUES (934, '320506', '吴中区', '320501', NULL);
INSERT INTO `city` VALUES (935, '320507', '相城区', '320501', NULL);
INSERT INTO `city` VALUES (936, '320581', '常熟市', '320500', '江苏省苏州市常熟市');
INSERT INTO `city` VALUES (937, '320582', '张家港市', '320500', '江苏省苏州市张家港市');
INSERT INTO `city` VALUES (938, '320583', '昆山市', '320500', '江苏省苏州市昆山市');
INSERT INTO `city` VALUES (939, '320584', '吴江市', '320500', '江苏省苏州市吴江市');
INSERT INTO `city` VALUES (940, '320585', '太仓市', '320500', '江苏省苏州市太仓市');
INSERT INTO `city` VALUES (941, '320600', '南通市', '320000', '江苏省南通市');
INSERT INTO `city` VALUES (942, '320601', '市辖区', '320600', '江苏省南通市市辖区');
INSERT INTO `city` VALUES (943, '320602', '崇川区', '320601', NULL);
INSERT INTO `city` VALUES (944, '320611', '港闸区', '320601', NULL);
INSERT INTO `city` VALUES (945, '320621', '海安县', '320600', '江苏省南通市海安县');
INSERT INTO `city` VALUES (946, '320623', '如东县', '320600', '江苏省南通市如东县');
INSERT INTO `city` VALUES (947, '320681', '启东市', '320600', '江苏省南通市启东市');
INSERT INTO `city` VALUES (948, '320682', '如皋市', '320600', '江苏省南通市如皋市');
INSERT INTO `city` VALUES (949, '320683', '通州市', '320600', '江苏省南通市通州市');
INSERT INTO `city` VALUES (950, '320684', '海门市', '320600', '江苏省南通市海门市');
INSERT INTO `city` VALUES (951, '320700', '连云港市', '320000', '江苏省连云港市');
INSERT INTO `city` VALUES (952, '320701', '市辖区', '320700', '江苏省连云港市市辖区');
INSERT INTO `city` VALUES (953, '320703', '连云区', '320701', NULL);
INSERT INTO `city` VALUES (954, '320705', '新浦区', '320701', NULL);
INSERT INTO `city` VALUES (955, '320706', '海州区', '320701', NULL);
INSERT INTO `city` VALUES (956, '320721', '赣榆县', '320700', '江苏省连云港市赣榆县');
INSERT INTO `city` VALUES (957, '320722', '东海县', '320700', '江苏省连云港市东海县');
INSERT INTO `city` VALUES (958, '320723', '灌云县', '320700', '江苏省连云港市灌云县');
INSERT INTO `city` VALUES (959, '320724', '灌南县', '320700', '江苏省连云港市灌南县');
INSERT INTO `city` VALUES (960, '320800', '淮安市', '320000', '江苏省淮安市');
INSERT INTO `city` VALUES (961, '320801', '市辖区', '320800', '江苏省淮安市市辖区');
INSERT INTO `city` VALUES (962, '320802', '清河区', '320801', NULL);
INSERT INTO `city` VALUES (963, '320803', '楚州区', '320801', NULL);
INSERT INTO `city` VALUES (964, '320804', '淮阴区', '320801', NULL);
INSERT INTO `city` VALUES (965, '320811', '清浦区', '320801', NULL);
INSERT INTO `city` VALUES (966, '320826', '涟水县', '320800', '江苏省淮安市涟水县');
INSERT INTO `city` VALUES (967, '320829', '洪泽县', '320800', '江苏省淮安市洪泽县');
INSERT INTO `city` VALUES (968, '320830', '盱眙县', '320800', '江苏省淮安市盱眙县');
INSERT INTO `city` VALUES (969, '320831', '金湖县', '320800', '江苏省淮安市金湖县');
INSERT INTO `city` VALUES (970, '320900', '盐城市', '320000', '江苏省盐城市');
INSERT INTO `city` VALUES (971, '320901', '市辖区', '320900', '江苏省盐城市市辖区');
INSERT INTO `city` VALUES (972, '320902', '亭湖区', '320901', NULL);
INSERT INTO `city` VALUES (973, '320903', '盐都区', '320901', NULL);
INSERT INTO `city` VALUES (974, '320921', '响水县', '320900', '江苏省盐城市响水县');
INSERT INTO `city` VALUES (975, '320922', '滨海县', '320900', '江苏省盐城市滨海县');
INSERT INTO `city` VALUES (976, '320923', '阜宁县', '320900', '江苏省盐城市阜宁县');
INSERT INTO `city` VALUES (977, '320924', '射阳县', '320900', '江苏省盐城市射阳县');
INSERT INTO `city` VALUES (978, '320925', '建湖县', '320900', '江苏省盐城市建湖县');
INSERT INTO `city` VALUES (979, '320981', '东台市', '320900', '江苏省盐城市东台市');
INSERT INTO `city` VALUES (980, '320982', '大丰市', '320900', '江苏省盐城市大丰市');
INSERT INTO `city` VALUES (981, '321000', '扬州市', '320000', '江苏省扬州市');
INSERT INTO `city` VALUES (982, '321001', '市辖区', '321000', '江苏省扬州市市辖区');
INSERT INTO `city` VALUES (983, '321002', '广陵区', '321001', NULL);
INSERT INTO `city` VALUES (984, '321003', '邗江区', '321001', NULL);
INSERT INTO `city` VALUES (985, '321011', '维扬区', '321001', NULL);
INSERT INTO `city` VALUES (986, '321023', '宝应县', '321000', '江苏省扬州市宝应县');
INSERT INTO `city` VALUES (987, '321081', '仪征市', '321000', '江苏省扬州市仪征市');
INSERT INTO `city` VALUES (988, '321084', '高邮市', '321000', '江苏省扬州市高邮市');
INSERT INTO `city` VALUES (989, '321088', '江都市', '321000', '江苏省扬州市江都市');
INSERT INTO `city` VALUES (990, '321100', '镇江市', '320000', '江苏省镇江市');
INSERT INTO `city` VALUES (991, '321101', '市辖区', '321100', '江苏省镇江市市辖区');
INSERT INTO `city` VALUES (992, '321102', '京口区', '321101', NULL);
INSERT INTO `city` VALUES (993, '321111', '润州区', '321101', NULL);
INSERT INTO `city` VALUES (994, '321112', '丹徒区', '321101', NULL);
INSERT INTO `city` VALUES (995, '321181', '丹阳市', '321100', '江苏省镇江市丹阳市');
INSERT INTO `city` VALUES (996, '321182', '扬中市', '321100', '江苏省镇江市扬中市');
INSERT INTO `city` VALUES (997, '321183', '句容市', '321100', '江苏省镇江市句容市');
INSERT INTO `city` VALUES (998, '321200', '泰州市', '320000', '江苏省泰州市');
INSERT INTO `city` VALUES (999, '321201', '市辖区', '321200', '江苏省泰州市市辖区');
INSERT INTO `city` VALUES (1000, '321202', '海陵区', '321201', NULL);
INSERT INTO `city` VALUES (1001, '321203', '高港区', '321201', NULL);
INSERT INTO `city` VALUES (1002, '321281', '兴化市', '321200', '江苏省泰州市兴化市');
INSERT INTO `city` VALUES (1003, '321282', '靖江市', '321200', '江苏省泰州市靖江市');
INSERT INTO `city` VALUES (1004, '321283', '泰兴市', '321200', '江苏省泰州市泰兴市');
INSERT INTO `city` VALUES (1005, '321284', '姜堰市', '321200', '江苏省泰州市姜堰市');
INSERT INTO `city` VALUES (1006, '321300', '宿迁市', '320000', '江苏省宿迁市');
INSERT INTO `city` VALUES (1007, '321301', '市辖区', '321300', '江苏省宿迁市市辖区');
INSERT INTO `city` VALUES (1008, '321302', '宿城区', '321301', NULL);
INSERT INTO `city` VALUES (1009, '321311', '宿豫区', '321301', NULL);
INSERT INTO `city` VALUES (1010, '321322', '沭阳县', '321300', '江苏省宿迁市沭阳县');
INSERT INTO `city` VALUES (1011, '321323', '泗阳县', '321300', '江苏省宿迁市泗阳县');
INSERT INTO `city` VALUES (1012, '321324', '泗洪县', '321300', '江苏省宿迁市泗洪县');
INSERT INTO `city` VALUES (1013, '330000', '浙江省', '0', '浙江省');
INSERT INTO `city` VALUES (1014, '330100', '杭州市', '330000', '浙江省杭州市');
INSERT INTO `city` VALUES (1015, '330101', '市辖区', '330100', '浙江省杭州市市辖区');
INSERT INTO `city` VALUES (1016, '330102', '上城区', '330101', NULL);
INSERT INTO `city` VALUES (1017, '330103', '下城区', '330101', NULL);
INSERT INTO `city` VALUES (1018, '330104', '江干区', '330101', NULL);
INSERT INTO `city` VALUES (1019, '330105', '拱墅区', '330101', NULL);
INSERT INTO `city` VALUES (1020, '330106', '西湖区', '330101', NULL);
INSERT INTO `city` VALUES (1021, '330108', '滨江区', '330101', NULL);
INSERT INTO `city` VALUES (1022, '330109', '萧山区', '330101', NULL);
INSERT INTO `city` VALUES (1023, '330110', '余杭区', '330101', NULL);
INSERT INTO `city` VALUES (1024, '330122', '桐庐县', '330100', '浙江省杭州市桐庐县');
INSERT INTO `city` VALUES (1025, '330127', '淳安县', '330100', '浙江省杭州市淳安县');
INSERT INTO `city` VALUES (1026, '330182', '建德市', '330100', '浙江省杭州市建德市');
INSERT INTO `city` VALUES (1027, '330183', '富阳市', '330100', '浙江省杭州市富阳市');
INSERT INTO `city` VALUES (1028, '330185', '临安市', '330100', '浙江省杭州市临安市');
INSERT INTO `city` VALUES (1029, '330200', '宁波市', '330000', '浙江省宁波市');
INSERT INTO `city` VALUES (1030, '330201', '市辖区', '330200', '浙江省宁波市市辖区');
INSERT INTO `city` VALUES (1031, '330203', '海曙区', '330201', NULL);
INSERT INTO `city` VALUES (1032, '330204', '江东区', '330201', NULL);
INSERT INTO `city` VALUES (1033, '330205', '江北区', '330201', NULL);
INSERT INTO `city` VALUES (1034, '330206', '北仑区', '330201', NULL);
INSERT INTO `city` VALUES (1035, '330211', '镇海区', '330201', NULL);
INSERT INTO `city` VALUES (1036, '330212', '鄞州区', '330201', NULL);
INSERT INTO `city` VALUES (1037, '330225', '象山县', '330200', '浙江省宁波市象山县');
INSERT INTO `city` VALUES (1038, '330226', '宁海县', '330200', '浙江省宁波市宁海县');
INSERT INTO `city` VALUES (1039, '330281', '余姚市', '330200', '浙江省宁波市余姚市');
INSERT INTO `city` VALUES (1040, '330282', '慈溪市', '330200', '浙江省宁波市慈溪市');
INSERT INTO `city` VALUES (1041, '330283', '奉化市', '330200', '浙江省宁波市奉化市');
INSERT INTO `city` VALUES (1042, '330300', '温州市', '330000', '浙江省温州市');
INSERT INTO `city` VALUES (1043, '330301', '市辖区', '330300', '浙江省温州市市辖区');
INSERT INTO `city` VALUES (1044, '330302', '鹿城区', '330301', NULL);
INSERT INTO `city` VALUES (1045, '330303', '龙湾区', '330301', NULL);
INSERT INTO `city` VALUES (1046, '330304', '瓯海区', '330301', NULL);
INSERT INTO `city` VALUES (1047, '330322', '洞头县', '330300', '浙江省温州市洞头县');
INSERT INTO `city` VALUES (1048, '330324', '永嘉县', '330300', '浙江省温州市永嘉县');
INSERT INTO `city` VALUES (1049, '330326', '平阳县', '330300', '浙江省温州市平阳县');
INSERT INTO `city` VALUES (1050, '330327', '苍南县', '330300', '浙江省温州市苍南县');
INSERT INTO `city` VALUES (1051, '330328', '文成县', '330300', '浙江省温州市文成县');
INSERT INTO `city` VALUES (1052, '330329', '泰顺县', '330300', '浙江省温州市泰顺县');
INSERT INTO `city` VALUES (1053, '330381', '瑞安市', '330300', '浙江省温州市瑞安市');
INSERT INTO `city` VALUES (1054, '330382', '乐清市', '330300', '浙江省温州市乐清市');
INSERT INTO `city` VALUES (1055, '330400', '嘉兴市', '330000', '浙江省嘉兴市');
INSERT INTO `city` VALUES (1056, '330401', '市辖区', '330400', '浙江省嘉兴市市辖区');
INSERT INTO `city` VALUES (1057, '330402', '南湖区', '330401', NULL);
INSERT INTO `city` VALUES (1058, '330411', '秀洲区', '330401', NULL);
INSERT INTO `city` VALUES (1059, '330421', '嘉善县', '330400', '浙江省嘉兴市嘉善县');
INSERT INTO `city` VALUES (1060, '330424', '海盐县', '330400', '浙江省嘉兴市海盐县');
INSERT INTO `city` VALUES (1061, '330481', '海宁市', '330400', '浙江省嘉兴市海宁市');
INSERT INTO `city` VALUES (1062, '330482', '平湖市', '330400', '浙江省嘉兴市平湖市');
INSERT INTO `city` VALUES (1063, '330483', '桐乡市', '330400', '浙江省嘉兴市桐乡市');
INSERT INTO `city` VALUES (1064, '330500', '湖州市', '330000', '浙江省湖州市');
INSERT INTO `city` VALUES (1065, '330501', '市辖区', '330500', '浙江省湖州市市辖区');
INSERT INTO `city` VALUES (1066, '330502', '吴兴区', '330501', NULL);
INSERT INTO `city` VALUES (1067, '330503', '南浔区', '330501', NULL);
INSERT INTO `city` VALUES (1068, '330521', '德清县', '330500', '浙江省湖州市德清县');
INSERT INTO `city` VALUES (1069, '330522', '长兴县', '330500', '浙江省湖州市长兴县');
INSERT INTO `city` VALUES (1070, '330523', '安吉县', '330500', '浙江省湖州市安吉县');
INSERT INTO `city` VALUES (1071, '330600', '绍兴市', '330000', '浙江省绍兴市');
INSERT INTO `city` VALUES (1072, '330601', '市辖区', '330600', '浙江省绍兴市市辖区');
INSERT INTO `city` VALUES (1073, '330602', '越城区', '330601', NULL);
INSERT INTO `city` VALUES (1074, '330621', '绍兴县', '330600', '浙江省绍兴市绍兴县');
INSERT INTO `city` VALUES (1075, '330624', '新昌县', '330600', '浙江省绍兴市新昌县');
INSERT INTO `city` VALUES (1076, '330681', '诸暨市', '330600', '浙江省绍兴市诸暨市');
INSERT INTO `city` VALUES (1077, '330682', '上虞市', '330600', '浙江省绍兴市上虞市');
INSERT INTO `city` VALUES (1078, '330683', '嵊州市', '330600', '浙江省绍兴市嵊州市');
INSERT INTO `city` VALUES (1079, '330700', '金华市', '330000', '浙江省金华市');
INSERT INTO `city` VALUES (1080, '330701', '市辖区', '330700', '浙江省金华市市辖区');
INSERT INTO `city` VALUES (1081, '330702', '婺城区', '330701', NULL);
INSERT INTO `city` VALUES (1082, '330703', '金东区', '330701', NULL);
INSERT INTO `city` VALUES (1083, '330723', '武义县', '330700', '浙江省金华市武义县');
INSERT INTO `city` VALUES (1084, '330726', '浦江县', '330700', '浙江省金华市浦江县');
INSERT INTO `city` VALUES (1085, '330727', '磐安县', '330700', '浙江省金华市磐安县');
INSERT INTO `city` VALUES (1086, '330781', '兰溪市', '330700', '浙江省金华市兰溪市');
INSERT INTO `city` VALUES (1087, '330782', '义乌市', '330700', '浙江省金华市义乌市');
INSERT INTO `city` VALUES (1088, '330783', '东阳市', '330700', '浙江省金华市东阳市');
INSERT INTO `city` VALUES (1089, '330784', '永康市', '330700', '浙江省金华市永康市');
INSERT INTO `city` VALUES (1090, '330800', '衢州市', '330000', '浙江省衢州市');
INSERT INTO `city` VALUES (1091, '330801', '市辖区', '330800', '浙江省衢州市市辖区');
INSERT INTO `city` VALUES (1092, '330802', '柯城区', '330801', NULL);
INSERT INTO `city` VALUES (1093, '330803', '衢江区', '330801', NULL);
INSERT INTO `city` VALUES (1094, '330822', '常山县', '330800', '浙江省衢州市常山县');
INSERT INTO `city` VALUES (1095, '330824', '开化县', '330800', '浙江省衢州市开化县');
INSERT INTO `city` VALUES (1096, '330825', '龙游县', '330800', '浙江省衢州市龙游县');
INSERT INTO `city` VALUES (1097, '330881', '江山市', '330800', '浙江省衢州市江山市');
INSERT INTO `city` VALUES (1098, '330900', '舟山市', '330000', '浙江省舟山市');
INSERT INTO `city` VALUES (1099, '330901', '市辖区', '330900', '浙江省舟山市市辖区');
INSERT INTO `city` VALUES (1100, '330902', '定海区', '330901', NULL);
INSERT INTO `city` VALUES (1101, '330903', '普陀区', '330901', NULL);
INSERT INTO `city` VALUES (1102, '330921', '岱山县', '330900', '浙江省舟山市岱山县');
INSERT INTO `city` VALUES (1103, '330922', '嵊泗县', '330900', '浙江省舟山市嵊泗县');
INSERT INTO `city` VALUES (1104, '331000', '台州市', '330000', '浙江省台州市');
INSERT INTO `city` VALUES (1105, '331001', '市辖区', '331000', '浙江省台州市市辖区');
INSERT INTO `city` VALUES (1106, '331002', '椒江区', '331001', NULL);
INSERT INTO `city` VALUES (1107, '331003', '黄岩区', '331001', NULL);
INSERT INTO `city` VALUES (1108, '331004', '路桥区', '331001', NULL);
INSERT INTO `city` VALUES (1109, '331021', '玉环县', '331000', '浙江省台州市玉环县');
INSERT INTO `city` VALUES (1110, '331022', '三门县', '331000', '浙江省台州市三门县');
INSERT INTO `city` VALUES (1111, '331023', '天台县', '331000', '浙江省台州市天台县');
INSERT INTO `city` VALUES (1112, '331024', '仙居县', '331000', '浙江省台州市仙居县');
INSERT INTO `city` VALUES (1113, '331081', '温岭市', '331000', '浙江省台州市温岭市');
INSERT INTO `city` VALUES (1114, '331082', '临海市', '331000', '浙江省台州市临海市');
INSERT INTO `city` VALUES (1115, '331100', '丽水市', '330000', '浙江省丽水市');
INSERT INTO `city` VALUES (1116, '331101', '市辖区', '331100', '浙江省丽水市市辖区');
INSERT INTO `city` VALUES (1117, '331102', '莲都区', '331101', NULL);
INSERT INTO `city` VALUES (1118, '331121', '青田县', '331100', '浙江省丽水市青田县');
INSERT INTO `city` VALUES (1119, '331122', '缙云县', '331100', '浙江省丽水市缙云县');
INSERT INTO `city` VALUES (1120, '331123', '遂昌县', '331100', '浙江省丽水市遂昌县');
INSERT INTO `city` VALUES (1121, '331124', '松阳县', '331100', '浙江省丽水市松阳县');
INSERT INTO `city` VALUES (1122, '331125', '云和县', '331100', '浙江省丽水市云和县');
INSERT INTO `city` VALUES (1123, '331126', '庆元县', '331100', '浙江省丽水市庆元县');
INSERT INTO `city` VALUES (1124, '331127', '景宁畲族自治县', '331100', '浙江省丽水市景宁畲族自治县');
INSERT INTO `city` VALUES (1125, '331181', '龙泉市', '331100', '浙江省丽水市龙泉市');
INSERT INTO `city` VALUES (1126, '340000', '安徽省', '0', '安徽省');
INSERT INTO `city` VALUES (1127, '340100', '合肥市', '340000', '安徽省合肥市');
INSERT INTO `city` VALUES (1128, '340101', '市辖区', '340100', '安徽省合肥市市辖区');
INSERT INTO `city` VALUES (1129, '340102', '瑶海区', '340101', NULL);
INSERT INTO `city` VALUES (1130, '340103', '庐阳区', '340101', NULL);
INSERT INTO `city` VALUES (1131, '340104', '蜀山区', '340101', NULL);
INSERT INTO `city` VALUES (1132, '340111', '包河区', '340101', NULL);
INSERT INTO `city` VALUES (1133, '340121', '长丰县', '340100', '安徽省合肥市长丰县');
INSERT INTO `city` VALUES (1134, '340122', '肥东县', '340100', '安徽省合肥市肥东县');
INSERT INTO `city` VALUES (1135, '340123', '肥西县', '340100', '安徽省合肥市肥西县');
INSERT INTO `city` VALUES (1136, '340124', '庐江县', '340100', '安徽省合肥市庐江县');
INSERT INTO `city` VALUES (1137, '340181', '巢湖市', '340100', '安徽省合肥市巢湖市');
INSERT INTO `city` VALUES (1138, '340200', '芜湖市', '340000', '安徽省芜湖市');
INSERT INTO `city` VALUES (1139, '340201', '市辖区', '340200', '安徽省芜湖市市辖区');
INSERT INTO `city` VALUES (1140, '340202', '镜湖区', '340201', NULL);
INSERT INTO `city` VALUES (1141, '340203', '马塘区', '340201', NULL);
INSERT INTO `city` VALUES (1142, '340207', '鸠江区', '340201', NULL);
INSERT INTO `city` VALUES (1143, '340221', '芜湖县', '340200', '安徽省芜湖市芜湖县');
INSERT INTO `city` VALUES (1144, '340222', '繁昌县', '340200', '安徽省芜湖市繁昌县');
INSERT INTO `city` VALUES (1145, '340223', '南陵县', '340200', '安徽省芜湖市南陵县');
INSERT INTO `city` VALUES (1146, '340225', '无为县', '340200', '安徽省芜湖市无为县');
INSERT INTO `city` VALUES (1147, '340300', '蚌埠市', '340000', '安徽省蚌埠市');
INSERT INTO `city` VALUES (1148, '340301', '市辖区', '340300', '安徽省蚌埠市市辖区');
INSERT INTO `city` VALUES (1149, '340302', '龙子湖区', '340301', NULL);
INSERT INTO `city` VALUES (1150, '340303', '蚌山区', '340301', NULL);
INSERT INTO `city` VALUES (1151, '340304', '禹会区', '340301', NULL);
INSERT INTO `city` VALUES (1152, '340311', '淮上区', '340301', NULL);
INSERT INTO `city` VALUES (1153, '340321', '怀远县', '340300', '安徽省蚌埠市怀远县');
INSERT INTO `city` VALUES (1154, '340322', '五河县', '340300', '安徽省蚌埠市五河县');
INSERT INTO `city` VALUES (1155, '340323', '固镇县', '340300', '安徽省蚌埠市固镇县');
INSERT INTO `city` VALUES (1156, '340400', '淮南市', '340000', '安徽省淮南市');
INSERT INTO `city` VALUES (1157, '340401', '市辖区', '340400', '安徽省淮南市市辖区');
INSERT INTO `city` VALUES (1158, '340402', '大通区', '340401', NULL);
INSERT INTO `city` VALUES (1159, '340403', '田家庵区', '340401', NULL);
INSERT INTO `city` VALUES (1160, '340404', '谢家集区', '340401', NULL);
INSERT INTO `city` VALUES (1161, '340405', '八公山区', '340401', NULL);
INSERT INTO `city` VALUES (1162, '340406', '潘集区', '340401', NULL);
INSERT INTO `city` VALUES (1163, '340421', '凤台县', '340400', '安徽省淮南市凤台县');
INSERT INTO `city` VALUES (1164, '340500', '马鞍山市', '340000', '安徽省马鞍山市');
INSERT INTO `city` VALUES (1165, '340501', '市辖区', '340500', '安徽省马鞍山市市辖区');
INSERT INTO `city` VALUES (1166, '340502', '金家庄区', '340501', NULL);
INSERT INTO `city` VALUES (1167, '340503', '花山区', '340501', NULL);
INSERT INTO `city` VALUES (1168, '340504', '雨山区', '340501', NULL);
INSERT INTO `city` VALUES (1169, '340521', '当涂县', '340500', '安徽省马鞍山市当涂县');
INSERT INTO `city` VALUES (1170, '340522', '含山县', '340500', '安徽省马鞍山市含山县');
INSERT INTO `city` VALUES (1171, '340523', '和县', '340500', '安徽省马鞍山市和县');
INSERT INTO `city` VALUES (1172, '340600', '淮北市', '340000', '安徽省淮北市');
INSERT INTO `city` VALUES (1173, '340601', '市辖区', '340600', '安徽省淮北市市辖区');
INSERT INTO `city` VALUES (1174, '340602', '杜集区', '340601', NULL);
INSERT INTO `city` VALUES (1175, '340603', '相山区', '340601', NULL);
INSERT INTO `city` VALUES (1176, '340604', '烈山区', '340601', NULL);
INSERT INTO `city` VALUES (1177, '340621', '濉溪县', '340600', '安徽省淮北市濉溪县');
INSERT INTO `city` VALUES (1178, '340700', '铜陵市', '340000', '安徽省铜陵市');
INSERT INTO `city` VALUES (1179, '340701', '市辖区', '340700', '安徽省铜陵市市辖区');
INSERT INTO `city` VALUES (1180, '340702', '铜官山区', '340701', NULL);
INSERT INTO `city` VALUES (1181, '340703', '狮子山区', '340701', NULL);
INSERT INTO `city` VALUES (1182, '340711', '郊区', '340701', NULL);
INSERT INTO `city` VALUES (1183, '340721', '铜陵县', '340700', '安徽省铜陵市铜陵县');
INSERT INTO `city` VALUES (1184, '340800', '安庆市', '340000', '安徽省安庆市');
INSERT INTO `city` VALUES (1185, '340801', '市辖区', '340800', '安徽省安庆市市辖区');
INSERT INTO `city` VALUES (1186, '340802', '迎江区', '340801', NULL);
INSERT INTO `city` VALUES (1187, '340803', '大观区', '340801', NULL);
INSERT INTO `city` VALUES (1188, '340811', '宜秀区', '340801', NULL);
INSERT INTO `city` VALUES (1189, '340822', '怀宁县', '340800', '安徽省安庆市怀宁县');
INSERT INTO `city` VALUES (1190, '340823', '枞阳县', '340800', '安徽省安庆市枞阳县');
INSERT INTO `city` VALUES (1191, '340824', '潜山县', '340800', '安徽省安庆市潜山县');
INSERT INTO `city` VALUES (1192, '340825', '太湖县', '340800', '安徽省安庆市太湖县');
INSERT INTO `city` VALUES (1193, '340826', '宿松县', '340800', '安徽省安庆市宿松县');
INSERT INTO `city` VALUES (1194, '340827', '望江县', '340800', '安徽省安庆市望江县');
INSERT INTO `city` VALUES (1195, '340828', '岳西县', '340800', '安徽省安庆市岳西县');
INSERT INTO `city` VALUES (1196, '340881', '桐城市', '340800', '安徽省安庆市桐城市');
INSERT INTO `city` VALUES (1197, '341000', '黄山市', '340000', '安徽省黄山市');
INSERT INTO `city` VALUES (1198, '341001', '黄山区', '341000', '安徽省黄山市黄山区');
INSERT INTO `city` VALUES (1199, '341002', '屯溪区', '341000', '安徽省黄山市屯溪区');
INSERT INTO `city` VALUES (1200, '341004', '徽州区', '341000', '安徽省黄山市徽州区');
INSERT INTO `city` VALUES (1201, '341021', '歙县', '341000', '安徽省黄山市歙县');
INSERT INTO `city` VALUES (1202, '341022', '休宁县', '341000', '安徽省黄山市休宁县');
INSERT INTO `city` VALUES (1203, '341023', '黟县', '341000', '安徽省黄山市黟县');
INSERT INTO `city` VALUES (1204, '341024', '祁门县', '341000', '安徽省黄山市祁门县');
INSERT INTO `city` VALUES (1205, '341091', '汤口镇', '341000', '安徽省黄山市汤口镇');
INSERT INTO `city` VALUES (1206, '341100', '滁州市', '340000', '安徽省滁州市');
INSERT INTO `city` VALUES (1207, '341101', '市辖区', '341100', '安徽省滁州市市辖区');
INSERT INTO `city` VALUES (1208, '341102', '琅琊区', '341101', NULL);
INSERT INTO `city` VALUES (1209, '341103', '南谯区', '341101', NULL);
INSERT INTO `city` VALUES (1210, '341122', '来安县', '341100', '安徽省滁州市来安县');
INSERT INTO `city` VALUES (1211, '341124', '全椒县', '341100', '安徽省滁州市全椒县');
INSERT INTO `city` VALUES (1212, '341125', '定远县', '341100', '安徽省滁州市定远县');
INSERT INTO `city` VALUES (1213, '341126', '凤阳县', '341100', '安徽省滁州市凤阳县');
INSERT INTO `city` VALUES (1214, '341181', '天长市', '341100', '安徽省滁州市天长市');
INSERT INTO `city` VALUES (1215, '341182', '明光市', '341100', '安徽省滁州市明光市');
INSERT INTO `city` VALUES (1216, '341200', '阜阳市', '340000', '安徽省阜阳市');
INSERT INTO `city` VALUES (1217, '341201', '颍泉区', '341200', '安徽省阜阳市颍泉区');
INSERT INTO `city` VALUES (1218, '341202', '颍州区', '341200', '安徽省阜阳市颍州区');
INSERT INTO `city` VALUES (1219, '341203', '颍东区', '341200', '安徽省阜阳市颍东区');
INSERT INTO `city` VALUES (1220, '341221', '临泉县', '341200', '安徽省阜阳市临泉县');
INSERT INTO `city` VALUES (1221, '341222', '太和县', '341200', '安徽省阜阳市太和县');
INSERT INTO `city` VALUES (1222, '341225', '阜南县', '341200', '安徽省阜阳市阜南县');
INSERT INTO `city` VALUES (1223, '341226', '颍上县', '341200', '安徽省阜阳市颍上县');
INSERT INTO `city` VALUES (1224, '341282', '界首市', '341200', '安徽省阜阳市界首市');
INSERT INTO `city` VALUES (1225, '341300', '宿州市', '340000', '安徽省宿州市');
INSERT INTO `city` VALUES (1226, '341301', '市辖区', '341300', '安徽省宿州市市辖区');
INSERT INTO `city` VALUES (1227, '341302', '埇桥区', '341301', NULL);
INSERT INTO `city` VALUES (1228, '341321', '砀山县', '341300', '安徽省宿州市砀山县');
INSERT INTO `city` VALUES (1229, '341322', '萧县', '341300', '安徽省宿州市萧县');
INSERT INTO `city` VALUES (1230, '341323', '灵璧县', '341300', '安徽省宿州市灵璧县');
INSERT INTO `city` VALUES (1231, '341324', '泗县', '341300', '安徽省宿州市泗县');
INSERT INTO `city` VALUES (1232, '341500', '六安市', '340000', '安徽省六安市');
INSERT INTO `city` VALUES (1233, '341501', '市辖区', '341500', '安徽省六安市市辖区');
INSERT INTO `city` VALUES (1234, '341502', '金安区', '341501', NULL);
INSERT INTO `city` VALUES (1235, '341503', '裕安区', '341501', NULL);
INSERT INTO `city` VALUES (1236, '341521', '寿县', '341500', '安徽省六安市寿县');
INSERT INTO `city` VALUES (1237, '341522', '霍邱县', '341500', '安徽省六安市霍邱县');
INSERT INTO `city` VALUES (1238, '341523', '舒城县', '341500', '安徽省六安市舒城县');
INSERT INTO `city` VALUES (1239, '341524', '金寨县', '341500', '安徽省六安市金寨县');
INSERT INTO `city` VALUES (1240, '341525', '霍山县', '341500', '安徽省六安市霍山县');
INSERT INTO `city` VALUES (1241, '341600', '亳州市', '340000', '安徽省亳州市');
INSERT INTO `city` VALUES (1242, '341601', '谯城区', '341600', '安徽省亳州市谯城区');
INSERT INTO `city` VALUES (1243, '341621', '涡阳县', '341600', '安徽省亳州市涡阳县');
INSERT INTO `city` VALUES (1244, '341622', '蒙城县', '341600', '安徽省亳州市蒙城县');
INSERT INTO `city` VALUES (1245, '341623', '利辛县', '341600', '安徽省亳州市利辛县');
INSERT INTO `city` VALUES (1246, '341700', '池州市', '340000', '安徽省池州市');
INSERT INTO `city` VALUES (1247, '341701', '市辖区', '341700', '安徽省池州市市辖区');
INSERT INTO `city` VALUES (1248, '341702', '贵池区', '341701', NULL);
INSERT INTO `city` VALUES (1249, '341721', '东至县', '341700', '安徽省池州市东至县');
INSERT INTO `city` VALUES (1250, '341722', '石台县', '341700', '安徽省池州市石台县');
INSERT INTO `city` VALUES (1251, '341723', '青阳县', '341700', '安徽省池州市青阳县');
INSERT INTO `city` VALUES (1252, '341800', '宣城市', '340000', '安徽省宣城市');
INSERT INTO `city` VALUES (1253, '341801', '市辖区', '341800', '安徽省宣城市市辖区');
INSERT INTO `city` VALUES (1254, '341802', '宣州区', '341801', NULL);
INSERT INTO `city` VALUES (1255, '341821', '郎溪县', '341800', '安徽省宣城市郎溪县');
INSERT INTO `city` VALUES (1256, '341822', '广德县', '341800', '安徽省宣城市广德县');
INSERT INTO `city` VALUES (1257, '341823', '泾县', '341800', '安徽省宣城市泾县');
INSERT INTO `city` VALUES (1258, '341824', '绩溪县', '341800', '安徽省宣城市绩溪县');
INSERT INTO `city` VALUES (1259, '341825', '旌德县', '341800', '安徽省宣城市旌德县');
INSERT INTO `city` VALUES (1260, '341881', '宁国市', '341800', '安徽省宣城市宁国市');
INSERT INTO `city` VALUES (1261, '350000', '福建省', '0', '福建省');
INSERT INTO `city` VALUES (1262, '350100', '福州市', '350000', '福建省福州市');
INSERT INTO `city` VALUES (1263, '350101', '市辖区', '350100', '福建省福州市市辖区');
INSERT INTO `city` VALUES (1264, '350102', '鼓楼区', '350101', NULL);
INSERT INTO `city` VALUES (1265, '350103', '台江区', '350101', NULL);
INSERT INTO `city` VALUES (1266, '350104', '仓山区', '350101', NULL);
INSERT INTO `city` VALUES (1267, '350105', '马尾区', '350101', NULL);
INSERT INTO `city` VALUES (1268, '350111', '晋安区', '350101', NULL);
INSERT INTO `city` VALUES (1269, '350121', '闽侯县', '350100', '福建省福州市闽侯县');
INSERT INTO `city` VALUES (1270, '350122', '连江县', '350100', '福建省福州市连江县');
INSERT INTO `city` VALUES (1271, '350123', '罗源县', '350100', '福建省福州市罗源县');
INSERT INTO `city` VALUES (1272, '350124', '闽清县', '350100', '福建省福州市闽清县');
INSERT INTO `city` VALUES (1273, '350125', '永泰县', '350100', '福建省福州市永泰县');
INSERT INTO `city` VALUES (1274, '350128', '平潭县', '350100', '福建省福州市平潭县');
INSERT INTO `city` VALUES (1275, '350181', '福清市', '350100', '福建省福州市福清市');
INSERT INTO `city` VALUES (1276, '350182', '长乐市', '350100', '福建省福州市长乐市');
INSERT INTO `city` VALUES (1277, '350200', '厦门市', '350000', '福建省厦门市');
INSERT INTO `city` VALUES (1278, '350201', '市辖区', '350200', '福建省厦门市市辖区');
INSERT INTO `city` VALUES (1279, '350203', '思明区', '350201', NULL);
INSERT INTO `city` VALUES (1280, '350205', '海沧区', '350201', NULL);
INSERT INTO `city` VALUES (1281, '350206', '湖里区', '350201', NULL);
INSERT INTO `city` VALUES (1282, '350211', '集美区', '350201', NULL);
INSERT INTO `city` VALUES (1283, '350212', '同安区', '350201', NULL);
INSERT INTO `city` VALUES (1284, '350213', '翔安区', '350201', NULL);
INSERT INTO `city` VALUES (1285, '350300', '莆田市', '350000', '福建省莆田市');
INSERT INTO `city` VALUES (1286, '350301', '市辖区', '350300', '福建省莆田市市辖区');
INSERT INTO `city` VALUES (1287, '350302', '城厢区', '350301', NULL);
INSERT INTO `city` VALUES (1288, '350303', '涵江区', '350301', NULL);
INSERT INTO `city` VALUES (1289, '350304', '荔城区', '350301', NULL);
INSERT INTO `city` VALUES (1290, '350305', '秀屿区', '350301', NULL);
INSERT INTO `city` VALUES (1291, '350322', '仙游县', '350300', '福建省莆田市仙游县');
INSERT INTO `city` VALUES (1292, '350400', '三明市', '350000', '福建省三明市');
INSERT INTO `city` VALUES (1293, '350401', '市辖区', '350400', '福建省三明市市辖区');
INSERT INTO `city` VALUES (1294, '350402', '梅列区', '350401', NULL);
INSERT INTO `city` VALUES (1295, '350403', '三元区', '350401', NULL);
INSERT INTO `city` VALUES (1296, '350421', '明溪县', '350400', '福建省三明市明溪县');
INSERT INTO `city` VALUES (1297, '350423', '清流县', '350400', '福建省三明市清流县');
INSERT INTO `city` VALUES (1298, '350424', '宁化县', '350400', '福建省三明市宁化县');
INSERT INTO `city` VALUES (1299, '350425', '大田县', '350400', '福建省三明市大田县');
INSERT INTO `city` VALUES (1300, '350426', '尤溪县', '350400', '福建省三明市尤溪县');
INSERT INTO `city` VALUES (1301, '350427', '沙县', '350400', '福建省三明市沙县');
INSERT INTO `city` VALUES (1302, '350428', '将乐县', '350400', '福建省三明市将乐县');
INSERT INTO `city` VALUES (1303, '350429', '泰宁县', '350400', '福建省三明市泰宁县');
INSERT INTO `city` VALUES (1304, '350430', '建宁县', '350400', '福建省三明市建宁县');
INSERT INTO `city` VALUES (1305, '350481', '永安市', '350400', '福建省三明市永安市');
INSERT INTO `city` VALUES (1306, '350500', '泉州市', '350000', '福建省泉州市');
INSERT INTO `city` VALUES (1307, '350501', '市辖区', '350500', '福建省泉州市市辖区');
INSERT INTO `city` VALUES (1308, '350502', '鲤城区', '350501', NULL);
INSERT INTO `city` VALUES (1309, '350503', '丰泽区', '350501', NULL);
INSERT INTO `city` VALUES (1310, '350504', '洛江区', '350501', NULL);
INSERT INTO `city` VALUES (1311, '350505', '泉港区', '350501', NULL);
INSERT INTO `city` VALUES (1312, '350521', '惠安县', '350500', '福建省泉州市惠安县');
INSERT INTO `city` VALUES (1313, '350524', '安溪县', '350500', '福建省泉州市安溪县');
INSERT INTO `city` VALUES (1314, '350525', '永春县', '350500', '福建省泉州市永春县');
INSERT INTO `city` VALUES (1315, '350526', '德化县', '350500', '福建省泉州市德化县');
INSERT INTO `city` VALUES (1316, '350527', '金门县', '350500', '福建省泉州市金门县');
INSERT INTO `city` VALUES (1317, '350581', '石狮市', '350500', '福建省泉州市石狮市');
INSERT INTO `city` VALUES (1318, '350582', '晋江市', '350500', '福建省泉州市晋江市');
INSERT INTO `city` VALUES (1319, '350583', '南安市', '350500', '福建省泉州市南安市');
INSERT INTO `city` VALUES (1320, '350600', '漳州市', '350000', '福建省漳州市');
INSERT INTO `city` VALUES (1321, '350601', '市辖区', '350600', '福建省漳州市市辖区');
INSERT INTO `city` VALUES (1322, '350602', '芗城区', '350601', NULL);
INSERT INTO `city` VALUES (1323, '350603', '龙文区', '350601', NULL);
INSERT INTO `city` VALUES (1324, '350622', '云霄县', '350600', '福建省漳州市云霄县');
INSERT INTO `city` VALUES (1325, '350623', '漳浦县', '350600', '福建省漳州市漳浦县');
INSERT INTO `city` VALUES (1326, '350624', '诏安县', '350600', '福建省漳州市诏安县');
INSERT INTO `city` VALUES (1327, '350625', '长泰县', '350600', '福建省漳州市长泰县');
INSERT INTO `city` VALUES (1328, '350626', '东山县', '350600', '福建省漳州市东山县');
INSERT INTO `city` VALUES (1329, '350627', '南靖县', '350600', '福建省漳州市南靖县');
INSERT INTO `city` VALUES (1330, '350628', '平和县', '350600', '福建省漳州市平和县');
INSERT INTO `city` VALUES (1331, '350629', '华安县', '350600', '福建省漳州市华安县');
INSERT INTO `city` VALUES (1332, '350681', '龙海市', '350600', '福建省漳州市龙海市');
INSERT INTO `city` VALUES (1333, '350700', '南平市', '350000', '福建省南平市');
INSERT INTO `city` VALUES (1334, '350701', '市辖区', '350700', '福建省南平市市辖区');
INSERT INTO `city` VALUES (1335, '350702', '延平区', '350701', NULL);
INSERT INTO `city` VALUES (1336, '350721', '顺昌县', '350700', '福建省南平市顺昌县');
INSERT INTO `city` VALUES (1337, '350722', '浦城县', '350700', '福建省南平市浦城县');
INSERT INTO `city` VALUES (1338, '350723', '光泽县', '350700', '福建省南平市光泽县');
INSERT INTO `city` VALUES (1339, '350724', '松溪县', '350700', '福建省南平市松溪县');
INSERT INTO `city` VALUES (1340, '350725', '政和县', '350700', '福建省南平市政和县');
INSERT INTO `city` VALUES (1341, '350781', '邵武市', '350700', '福建省南平市邵武市');
INSERT INTO `city` VALUES (1342, '350782', '武夷山市', '350700', '福建省南平市武夷山市');
INSERT INTO `city` VALUES (1343, '350783', '建瓯市', '350700', '福建省南平市建瓯市');
INSERT INTO `city` VALUES (1344, '350784', '建阳市', '350700', '福建省南平市建阳市');
INSERT INTO `city` VALUES (1345, '350800', '龙岩市', '350000', '福建省龙岩市');
INSERT INTO `city` VALUES (1346, '350801', '市辖区', '350800', '福建省龙岩市市辖区');
INSERT INTO `city` VALUES (1347, '350802', '新罗区', '350801', NULL);
INSERT INTO `city` VALUES (1348, '350821', '长汀县', '350800', '福建省龙岩市长汀县');
INSERT INTO `city` VALUES (1349, '350822', '永定县', '350800', '福建省龙岩市永定县');
INSERT INTO `city` VALUES (1350, '350823', '上杭县', '350800', '福建省龙岩市上杭县');
INSERT INTO `city` VALUES (1351, '350824', '武平县', '350800', '福建省龙岩市武平县');
INSERT INTO `city` VALUES (1352, '350825', '连城县', '350800', '福建省龙岩市连城县');
INSERT INTO `city` VALUES (1353, '350881', '漳平市', '350800', '福建省龙岩市漳平市');
INSERT INTO `city` VALUES (1354, '350900', '宁德市', '350000', '福建省宁德市');
INSERT INTO `city` VALUES (1355, '350901', '市辖区', '350900', '福建省宁德市市辖区');
INSERT INTO `city` VALUES (1356, '350902', '蕉城区', '350901', NULL);
INSERT INTO `city` VALUES (1357, '350921', '霞浦县', '350900', '福建省宁德市霞浦县');
INSERT INTO `city` VALUES (1358, '350922', '古田县', '350900', '福建省宁德市古田县');
INSERT INTO `city` VALUES (1359, '350923', '屏南县', '350900', '福建省宁德市屏南县');
INSERT INTO `city` VALUES (1360, '350924', '寿宁县', '350900', '福建省宁德市寿宁县');
INSERT INTO `city` VALUES (1361, '350925', '周宁县', '350900', '福建省宁德市周宁县');
INSERT INTO `city` VALUES (1362, '350926', '柘荣县', '350900', '福建省宁德市柘荣县');
INSERT INTO `city` VALUES (1363, '350981', '福安市', '350900', '福建省宁德市福安市');
INSERT INTO `city` VALUES (1364, '350982', '福鼎市', '350900', '福建省宁德市福鼎市');
INSERT INTO `city` VALUES (1365, '360000', '江西省', '0', '江西省');
INSERT INTO `city` VALUES (1366, '360100', '南昌市', '360000', '江西省南昌市');
INSERT INTO `city` VALUES (1367, '360101', '市辖区', '360100', '江西省南昌市市辖区');
INSERT INTO `city` VALUES (1368, '360102', '东湖区', '360101', NULL);
INSERT INTO `city` VALUES (1369, '360103', '西湖区', '360101', NULL);
INSERT INTO `city` VALUES (1370, '360104', '青云谱区', '360101', NULL);
INSERT INTO `city` VALUES (1371, '360105', '湾里区', '360101', NULL);
INSERT INTO `city` VALUES (1372, '360111', '青山湖区', '360101', NULL);
INSERT INTO `city` VALUES (1373, '360121', '南昌县', '360100', '江西省南昌市南昌县');
INSERT INTO `city` VALUES (1374, '360122', '新建县', '360100', '江西省南昌市新建县');
INSERT INTO `city` VALUES (1375, '360123', '安义县', '360100', '江西省南昌市安义县');
INSERT INTO `city` VALUES (1376, '360124', '进贤县', '360100', '江西省南昌市进贤县');
INSERT INTO `city` VALUES (1377, '360200', '景德镇市', '360000', '江西省景德镇市');
INSERT INTO `city` VALUES (1378, '360201', '市辖区', '360200', '江西省景德镇市市辖区');
INSERT INTO `city` VALUES (1379, '360202', '昌江区', '360201', NULL);
INSERT INTO `city` VALUES (1380, '360203', '珠山区', '360201', NULL);
INSERT INTO `city` VALUES (1381, '360222', '浮梁县', '360200', '江西省景德镇市浮梁县');
INSERT INTO `city` VALUES (1382, '360281', '乐平市', '360200', '江西省景德镇市乐平市');
INSERT INTO `city` VALUES (1383, '360300', '萍乡市', '360000', '江西省萍乡市');
INSERT INTO `city` VALUES (1384, '360301', '市辖区', '360300', '江西省萍乡市市辖区');
INSERT INTO `city` VALUES (1385, '360302', '安源区', '360301', NULL);
INSERT INTO `city` VALUES (1386, '360313', '湘东区', '360301', NULL);
INSERT INTO `city` VALUES (1387, '360321', '莲花县', '360300', '江西省萍乡市莲花县');
INSERT INTO `city` VALUES (1388, '360322', '上栗县', '360300', '江西省萍乡市上栗县');
INSERT INTO `city` VALUES (1389, '360323', '芦溪县', '360300', '江西省萍乡市芦溪县');
INSERT INTO `city` VALUES (1390, '360400', '九江市', '360000', '江西省九江市');
INSERT INTO `city` VALUES (1391, '360401', '市辖区', '360400', '江西省九江市市辖区');
INSERT INTO `city` VALUES (1392, '360402', '庐山区', '360401', NULL);
INSERT INTO `city` VALUES (1393, '360403', '浔阳区', '360401', NULL);
INSERT INTO `city` VALUES (1394, '360421', '九江县', '360400', '江西省九江市九江县');
INSERT INTO `city` VALUES (1395, '360423', '武宁县', '360400', '江西省九江市武宁县');
INSERT INTO `city` VALUES (1396, '360424', '修水县', '360400', '江西省九江市修水县');
INSERT INTO `city` VALUES (1397, '360425', '永修县', '360400', '江西省九江市永修县');
INSERT INTO `city` VALUES (1398, '360426', '德安县', '360400', '江西省九江市德安县');
INSERT INTO `city` VALUES (1399, '360427', '星子县', '360400', '江西省九江市星子县');
INSERT INTO `city` VALUES (1400, '360428', '都昌县', '360400', '江西省九江市都昌县');
INSERT INTO `city` VALUES (1401, '360429', '湖口县', '360400', '江西省九江市湖口县');
INSERT INTO `city` VALUES (1402, '360430', '彭泽县', '360400', '江西省九江市彭泽县');
INSERT INTO `city` VALUES (1403, '360481', '瑞昌市', '360400', '江西省九江市瑞昌市');
INSERT INTO `city` VALUES (1404, '360482', '共青城市', '360400', '江西省九江市共青城市');
INSERT INTO `city` VALUES (1405, '360500', '新余市', '360000', '江西省新余市');
INSERT INTO `city` VALUES (1406, '360501', '市辖区', '360500', '江西省新余市市辖区');
INSERT INTO `city` VALUES (1407, '360502', '渝水区', '360501', NULL);
INSERT INTO `city` VALUES (1408, '360521', '分宜县', '360500', '江西省新余市分宜县');
INSERT INTO `city` VALUES (1409, '360600', '鹰潭市', '360000', '江西省鹰潭市');
INSERT INTO `city` VALUES (1410, '360601', '市辖区', '360600', '江西省鹰潭市市辖区');
INSERT INTO `city` VALUES (1411, '360602', '月湖区', '360601', NULL);
INSERT INTO `city` VALUES (1412, '360622', '余江县', '360600', '江西省鹰潭市余江县');
INSERT INTO `city` VALUES (1413, '360681', '贵溪市', '360600', '江西省鹰潭市贵溪市');
INSERT INTO `city` VALUES (1414, '360700', '赣州市', '360000', '江西省赣州市');
INSERT INTO `city` VALUES (1415, '360701', '市辖区', '360700', '江西省赣州市市辖区');
INSERT INTO `city` VALUES (1416, '360702', '章贡区', '360701', NULL);
INSERT INTO `city` VALUES (1417, '360721', '赣县', '360700', '江西省赣州市赣县');
INSERT INTO `city` VALUES (1418, '360722', '信丰县', '360700', '江西省赣州市信丰县');
INSERT INTO `city` VALUES (1419, '360723', '大余县', '360700', '江西省赣州市大余县');
INSERT INTO `city` VALUES (1420, '360724', '上犹县', '360700', '江西省赣州市上犹县');
INSERT INTO `city` VALUES (1421, '360725', '崇义县', '360700', '江西省赣州市崇义县');
INSERT INTO `city` VALUES (1422, '360726', '安远县', '360700', '江西省赣州市安远县');
INSERT INTO `city` VALUES (1423, '360727', '龙南县', '360700', '江西省赣州市龙南县');
INSERT INTO `city` VALUES (1424, '360728', '定南县', '360700', '江西省赣州市定南县');
INSERT INTO `city` VALUES (1425, '360729', '全南县', '360700', '江西省赣州市全南县');
INSERT INTO `city` VALUES (1426, '360730', '宁都县', '360700', '江西省赣州市宁都县');
INSERT INTO `city` VALUES (1427, '360731', '于都县', '360700', '江西省赣州市于都县');
INSERT INTO `city` VALUES (1428, '360732', '兴国县', '360700', '江西省赣州市兴国县');
INSERT INTO `city` VALUES (1429, '360733', '会昌县', '360700', '江西省赣州市会昌县');
INSERT INTO `city` VALUES (1430, '360734', '寻乌县', '360700', '江西省赣州市寻乌县');
INSERT INTO `city` VALUES (1431, '360735', '石城县', '360700', '江西省赣州市石城县');
INSERT INTO `city` VALUES (1432, '360781', '瑞金市', '360700', '江西省赣州市瑞金市');
INSERT INTO `city` VALUES (1433, '360782', '南康市', '360700', '江西省赣州市南康市');
INSERT INTO `city` VALUES (1434, '360800', '吉安市', '360000', '江西省吉安市');
INSERT INTO `city` VALUES (1435, '360801', '市辖区', '360800', '江西省吉安市市辖区');
INSERT INTO `city` VALUES (1436, '360802', '吉州区', '360801', NULL);
INSERT INTO `city` VALUES (1437, '360803', '青原区', '360801', NULL);
INSERT INTO `city` VALUES (1438, '360821', '吉安县', '360800', '江西省吉安市吉安县');
INSERT INTO `city` VALUES (1439, '360822', '吉水县', '360800', '江西省吉安市吉水县');
INSERT INTO `city` VALUES (1440, '360823', '峡江县', '360800', '江西省吉安市峡江县');
INSERT INTO `city` VALUES (1441, '360824', '新干县', '360800', '江西省吉安市新干县');
INSERT INTO `city` VALUES (1442, '360825', '永丰县', '360800', '江西省吉安市永丰县');
INSERT INTO `city` VALUES (1443, '360826', '泰和县', '360800', '江西省吉安市泰和县');
INSERT INTO `city` VALUES (1444, '360827', '遂川县', '360800', '江西省吉安市遂川县');
INSERT INTO `city` VALUES (1445, '360828', '万安县', '360800', '江西省吉安市万安县');
INSERT INTO `city` VALUES (1446, '360829', '安福县', '360800', '江西省吉安市安福县');
INSERT INTO `city` VALUES (1447, '360830', '永新县', '360800', '江西省吉安市永新县');
INSERT INTO `city` VALUES (1448, '360881', '井冈山市', '360800', '江西省吉安市井冈山市');
INSERT INTO `city` VALUES (1449, '360900', '宜春市', '360000', '江西省宜春市');
INSERT INTO `city` VALUES (1450, '360901', '市辖区', '360900', '江西省宜春市市辖区');
INSERT INTO `city` VALUES (1451, '360902', '袁州区', '360901', NULL);
INSERT INTO `city` VALUES (1452, '360921', '奉新县', '360900', '江西省宜春市奉新县');
INSERT INTO `city` VALUES (1453, '360922', '万载县', '360900', '江西省宜春市万载县');
INSERT INTO `city` VALUES (1454, '360923', '上高县', '360900', '江西省宜春市上高县');
INSERT INTO `city` VALUES (1455, '360924', '宜丰县', '360900', '江西省宜春市宜丰县');
INSERT INTO `city` VALUES (1456, '360925', '靖安县', '360900', '江西省宜春市靖安县');
INSERT INTO `city` VALUES (1457, '360926', '铜鼓县', '360900', '江西省宜春市铜鼓县');
INSERT INTO `city` VALUES (1458, '360981', '丰城市', '360900', '江西省宜春市丰城市');
INSERT INTO `city` VALUES (1459, '360982', '樟树市', '360900', '江西省宜春市樟树市');
INSERT INTO `city` VALUES (1460, '360983', '高安市', '360900', '江西省宜春市高安市');
INSERT INTO `city` VALUES (1461, '361000', '抚州市', '360000', '江西省抚州市');
INSERT INTO `city` VALUES (1462, '361001', '市辖区', '361000', '江西省抚州市市辖区');
INSERT INTO `city` VALUES (1463, '361002', '临川区', '361001', NULL);
INSERT INTO `city` VALUES (1464, '361021', '南城县', '361000', '江西省抚州市南城县');
INSERT INTO `city` VALUES (1465, '361022', '黎川县', '361000', '江西省抚州市黎川县');
INSERT INTO `city` VALUES (1466, '361023', '南丰县', '361000', '江西省抚州市南丰县');
INSERT INTO `city` VALUES (1467, '361024', '崇仁县', '361000', '江西省抚州市崇仁县');
INSERT INTO `city` VALUES (1468, '361025', '乐安县', '361000', '江西省抚州市乐安县');
INSERT INTO `city` VALUES (1469, '361026', '宜黄县', '361000', '江西省抚州市宜黄县');
INSERT INTO `city` VALUES (1470, '361027', '金溪县', '361000', '江西省抚州市金溪县');
INSERT INTO `city` VALUES (1471, '361028', '资溪县', '361000', '江西省抚州市资溪县');
INSERT INTO `city` VALUES (1472, '361029', '东乡县', '361000', '江西省抚州市东乡县');
INSERT INTO `city` VALUES (1473, '361030', '广昌县', '361000', '江西省抚州市广昌县');
INSERT INTO `city` VALUES (1474, '361100', '上饶市', '360000', '江西省上饶市');
INSERT INTO `city` VALUES (1475, '361101', '市辖区', '361100', '江西省上饶市市辖区');
INSERT INTO `city` VALUES (1476, '361102', '信州区', '361101', NULL);
INSERT INTO `city` VALUES (1477, '361121', '上饶县', '361100', '江西省上饶市上饶县');
INSERT INTO `city` VALUES (1478, '361122', '广丰县', '361100', '江西省上饶市广丰县');
INSERT INTO `city` VALUES (1479, '361123', '玉山县', '361100', '江西省上饶市玉山县');
INSERT INTO `city` VALUES (1480, '361124', '铅山县', '361100', '江西省上饶市铅山县');
INSERT INTO `city` VALUES (1481, '361125', '横峰县', '361100', '江西省上饶市横峰县');
INSERT INTO `city` VALUES (1482, '361126', '弋阳县', '361100', '江西省上饶市弋阳县');
INSERT INTO `city` VALUES (1483, '361127', '余干县', '361100', '江西省上饶市余干县');
INSERT INTO `city` VALUES (1484, '361128', '鄱阳县', '361100', '江西省上饶市鄱阳县');
INSERT INTO `city` VALUES (1485, '361129', '万年县', '361100', '江西省上饶市万年县');
INSERT INTO `city` VALUES (1486, '361130', '婺源县', '361100', '江西省上饶市婺源县');
INSERT INTO `city` VALUES (1487, '361181', '德兴市', '361100', '江西省上饶市德兴市');
INSERT INTO `city` VALUES (1488, '370000', '山东省', '0', '山东省');
INSERT INTO `city` VALUES (1489, '370100', '济南市', '370000', '山东省济南市');
INSERT INTO `city` VALUES (1490, '370101', '市辖区', '370100', '山东省济南市市辖区');
INSERT INTO `city` VALUES (1491, '370102', '历下区', '370101', NULL);
INSERT INTO `city` VALUES (1492, '370103', '市中区', '370101', NULL);
INSERT INTO `city` VALUES (1493, '370104', '槐荫区', '370101', NULL);
INSERT INTO `city` VALUES (1494, '370105', '天桥区', '370101', NULL);
INSERT INTO `city` VALUES (1495, '370112', '历城区', '370101', NULL);
INSERT INTO `city` VALUES (1496, '370113', '长清区', '370101', NULL);
INSERT INTO `city` VALUES (1497, '370124', '平阴县', '370100', '山东省济南市平阴县');
INSERT INTO `city` VALUES (1498, '370125', '济阳县', '370100', '山东省济南市济阳县');
INSERT INTO `city` VALUES (1499, '370126', '商河县', '370100', '山东省济南市商河县');
INSERT INTO `city` VALUES (1500, '370181', '章丘市', '370100', '山东省济南市章丘市');
INSERT INTO `city` VALUES (1501, '370200', '青岛市', '370000', '山东省青岛市');
INSERT INTO `city` VALUES (1502, '370201', '市辖区', '370200', '山东省青岛市市辖区');
INSERT INTO `city` VALUES (1503, '370202', '市南区', '370201', NULL);
INSERT INTO `city` VALUES (1504, '370203', '市北区', '370201', NULL);
INSERT INTO `city` VALUES (1505, '370205', '四方区', '370201', NULL);
INSERT INTO `city` VALUES (1506, '370211', '黄岛区', '370201', NULL);
INSERT INTO `city` VALUES (1507, '370212', '崂山区', '370201', NULL);
INSERT INTO `city` VALUES (1508, '370213', '李沧区', '370201', NULL);
INSERT INTO `city` VALUES (1509, '370214', '城阳区', '370201', NULL);
INSERT INTO `city` VALUES (1510, '370281', '胶州市', '370200', '山东省青岛市胶州市');
INSERT INTO `city` VALUES (1511, '370282', '即墨市', '370200', '山东省青岛市即墨市');
INSERT INTO `city` VALUES (1512, '370283', '平度市', '370200', '山东省青岛市平度市');
INSERT INTO `city` VALUES (1513, '370284', '胶南市', '370200', '山东省青岛市胶南市');
INSERT INTO `city` VALUES (1514, '370285', '莱西市', '370200', '山东省青岛市莱西市');
INSERT INTO `city` VALUES (1515, '370300', '淄博市', '370000', '山东省淄博市');
INSERT INTO `city` VALUES (1516, '370301', '市辖区', '370300', '山东省淄博市市辖区');
INSERT INTO `city` VALUES (1517, '370302', '淄川区', '370301', NULL);
INSERT INTO `city` VALUES (1518, '370303', '张店区', '370301', NULL);
INSERT INTO `city` VALUES (1519, '370304', '博山区', '370301', NULL);
INSERT INTO `city` VALUES (1520, '370305', '临淄区', '370301', NULL);
INSERT INTO `city` VALUES (1521, '370306', '周村区', '370301', NULL);
INSERT INTO `city` VALUES (1522, '370321', '桓台县', '370300', '山东省淄博市桓台县');
INSERT INTO `city` VALUES (1523, '370322', '高青县', '370300', '山东省淄博市高青县');
INSERT INTO `city` VALUES (1524, '370323', '沂源县', '370300', '山东省淄博市沂源县');
INSERT INTO `city` VALUES (1525, '370400', '枣庄市', '370000', '山东省枣庄市');
INSERT INTO `city` VALUES (1526, '370401', '市辖区', '370400', '山东省枣庄市市辖区');
INSERT INTO `city` VALUES (1527, '370402', '市中区', '370401', NULL);
INSERT INTO `city` VALUES (1528, '370403', '薛城区', '370401', NULL);
INSERT INTO `city` VALUES (1529, '370404', '峄城区', '370401', NULL);
INSERT INTO `city` VALUES (1530, '370405', '台儿庄区', '370401', NULL);
INSERT INTO `city` VALUES (1531, '370406', '山亭区', '370401', NULL);
INSERT INTO `city` VALUES (1532, '370481', '滕州市', '370400', '山东省枣庄市滕州市');
INSERT INTO `city` VALUES (1533, '370500', '东营市', '370000', '山东省东营市');
INSERT INTO `city` VALUES (1534, '370501', '市辖区', '370500', '山东省东营市市辖区');
INSERT INTO `city` VALUES (1535, '370502', '东营区', '370501', NULL);
INSERT INTO `city` VALUES (1536, '370503', '河口区', '370501', NULL);
INSERT INTO `city` VALUES (1537, '370521', '垦利县', '370500', '山东省东营市垦利县');
INSERT INTO `city` VALUES (1538, '370522', '利津县', '370500', '山东省东营市利津县');
INSERT INTO `city` VALUES (1539, '370523', '广饶县', '370500', '山东省东营市广饶县');
INSERT INTO `city` VALUES (1540, '370600', '烟台市', '370000', '山东省烟台市');
INSERT INTO `city` VALUES (1541, '370601', '市辖区', '370600', '山东省烟台市市辖区');
INSERT INTO `city` VALUES (1542, '370602', '芝罘区', '370601', NULL);
INSERT INTO `city` VALUES (1543, '370611', '福山区', '370601', NULL);
INSERT INTO `city` VALUES (1544, '370612', '牟平区', '370601', NULL);
INSERT INTO `city` VALUES (1545, '370613', '莱山区', '370601', NULL);
INSERT INTO `city` VALUES (1546, '370634', '长岛县', '370600', '山东省烟台市长岛县');
INSERT INTO `city` VALUES (1547, '370681', '龙口市', '370600', '山东省烟台市龙口市');
INSERT INTO `city` VALUES (1548, '370682', '莱阳市', '370600', '山东省烟台市莱阳市');
INSERT INTO `city` VALUES (1549, '370683', '莱州市', '370600', '山东省烟台市莱州市');
INSERT INTO `city` VALUES (1550, '370684', '蓬莱市', '370600', '山东省烟台市蓬莱市');
INSERT INTO `city` VALUES (1551, '370685', '招远市', '370600', '山东省烟台市招远市');
INSERT INTO `city` VALUES (1552, '370686', '栖霞市', '370600', '山东省烟台市栖霞市');
INSERT INTO `city` VALUES (1553, '370687', '海阳市', '370600', '山东省烟台市海阳市');
INSERT INTO `city` VALUES (1554, '370700', '潍坊市', '370000', '山东省潍坊市');
INSERT INTO `city` VALUES (1555, '370701', '市辖区', '370700', '山东省潍坊市市辖区');
INSERT INTO `city` VALUES (1556, '370702', '潍城区', '370701', NULL);
INSERT INTO `city` VALUES (1557, '370703', '寒亭区', '370701', NULL);
INSERT INTO `city` VALUES (1558, '370704', '坊子区', '370701', NULL);
INSERT INTO `city` VALUES (1559, '370705', '奎文区', '370701', NULL);
INSERT INTO `city` VALUES (1560, '370724', '临朐县', '370700', '山东省潍坊市临朐县');
INSERT INTO `city` VALUES (1561, '370725', '昌乐县', '370700', '山东省潍坊市昌乐县');
INSERT INTO `city` VALUES (1562, '370781', '青州市', '370700', '山东省潍坊市青州市');
INSERT INTO `city` VALUES (1563, '370782', '诸城市', '370700', '山东省潍坊市诸城市');
INSERT INTO `city` VALUES (1564, '370783', '寿光市', '370700', '山东省潍坊市寿光市');
INSERT INTO `city` VALUES (1565, '370784', '安丘市', '370700', '山东省潍坊市安丘市');
INSERT INTO `city` VALUES (1566, '370785', '高密市', '370700', '山东省潍坊市高密市');
INSERT INTO `city` VALUES (1567, '370786', '昌邑市', '370700', '山东省潍坊市昌邑市');
INSERT INTO `city` VALUES (1568, '370800', '济宁市', '370000', '山东省济宁市');
INSERT INTO `city` VALUES (1569, '370801', '市辖区', '370800', '山东省济宁市市辖区');
INSERT INTO `city` VALUES (1570, '370802', '市中区', '370801', NULL);
INSERT INTO `city` VALUES (1571, '370811', '任城区', '370801', NULL);
INSERT INTO `city` VALUES (1572, '370826', '微山县', '370800', '山东省济宁市微山县');
INSERT INTO `city` VALUES (1573, '370827', '鱼台县', '370800', '山东省济宁市鱼台县');
INSERT INTO `city` VALUES (1574, '370828', '金乡县', '370800', '山东省济宁市金乡县');
INSERT INTO `city` VALUES (1575, '370829', '嘉祥县', '370800', '山东省济宁市嘉祥县');
INSERT INTO `city` VALUES (1576, '370830', '汶上县', '370800', '山东省济宁市汶上县');
INSERT INTO `city` VALUES (1577, '370831', '泗水县', '370800', '山东省济宁市泗水县');
INSERT INTO `city` VALUES (1578, '370832', '梁山县', '370800', '山东省济宁市梁山县');
INSERT INTO `city` VALUES (1579, '370881', '曲阜市', '370800', '山东省济宁市曲阜市');
INSERT INTO `city` VALUES (1580, '370882', '兖州市', '370800', '山东省济宁市兖州市');
INSERT INTO `city` VALUES (1581, '370883', '邹城市', '370800', '山东省济宁市邹城市');
INSERT INTO `city` VALUES (1582, '370900', '泰安市', '370000', '山东省泰安市');
INSERT INTO `city` VALUES (1583, '370901', '岱岳区', '370900', '山东省泰安市岱岳区');
INSERT INTO `city` VALUES (1584, '370902', '泰山区', '370900', '山东省泰安市泰山区');
INSERT INTO `city` VALUES (1585, '370921', '宁阳县', '370900', '山东省泰安市宁阳县');
INSERT INTO `city` VALUES (1586, '370923', '东平县', '370900', '山东省泰安市东平县');
INSERT INTO `city` VALUES (1587, '370982', '新泰市', '370900', '山东省泰安市新泰市');
INSERT INTO `city` VALUES (1588, '370983', '肥城市', '370900', '山东省泰安市肥城市');
INSERT INTO `city` VALUES (1589, '371000', '威海市', '370000', '山东省威海市');
INSERT INTO `city` VALUES (1590, '371001', '市辖区', '371000', '山东省威海市市辖区');
INSERT INTO `city` VALUES (1591, '371002', '环翠区', '371001', NULL);
INSERT INTO `city` VALUES (1592, '371081', '文登市', '371000', '山东省威海市文登市');
INSERT INTO `city` VALUES (1593, '371082', '荣成市', '371000', '山东省威海市荣成市');
INSERT INTO `city` VALUES (1594, '371083', '乳山市', '371000', '山东省威海市乳山市');
INSERT INTO `city` VALUES (1595, '371100', '日照市', '370000', '山东省日照市');
INSERT INTO `city` VALUES (1596, '371101', '市辖区', '371100', '山东省日照市市辖区');
INSERT INTO `city` VALUES (1597, '371102', '东港区', '371101', NULL);
INSERT INTO `city` VALUES (1598, '371103', '岚山区', '371101', NULL);
INSERT INTO `city` VALUES (1599, '371121', '五莲县', '371100', '山东省日照市五莲县');
INSERT INTO `city` VALUES (1600, '371122', '莒县', '371100', '山东省日照市莒县');
INSERT INTO `city` VALUES (1601, '371200', '莱芜市', '370000', '山东省莱芜市');
INSERT INTO `city` VALUES (1602, '371201', '市辖区', '371200', '山东省莱芜市市辖区');
INSERT INTO `city` VALUES (1603, '371202', '莱城区', '371201', NULL);
INSERT INTO `city` VALUES (1604, '371203', '钢城区', '371201', NULL);
INSERT INTO `city` VALUES (1605, '371300', '临沂市', '370000', '山东省临沂市');
INSERT INTO `city` VALUES (1606, '371301', '市辖区', '371300', '山东省临沂市市辖区');
INSERT INTO `city` VALUES (1607, '371302', '兰山区', '371301', NULL);
INSERT INTO `city` VALUES (1608, '371311', '罗庄区', '371301', NULL);
INSERT INTO `city` VALUES (1609, '371312', '河东区', '371301', NULL);
INSERT INTO `city` VALUES (1610, '371321', '沂南县', '371300', '山东省临沂市沂南县');
INSERT INTO `city` VALUES (1611, '371322', '郯城县', '371300', '山东省临沂市郯城县');
INSERT INTO `city` VALUES (1612, '371323', '沂水县', '371300', '山东省临沂市沂水县');
INSERT INTO `city` VALUES (1613, '371324', '苍山县', '371300', '山东省临沂市苍山县');
INSERT INTO `city` VALUES (1614, '371325', '费县', '371300', '山东省临沂市费县');
INSERT INTO `city` VALUES (1615, '371326', '平邑县', '371300', '山东省临沂市平邑县');
INSERT INTO `city` VALUES (1616, '371327', '莒南县', '371300', '山东省临沂市莒南县');
INSERT INTO `city` VALUES (1617, '371328', '蒙阴县', '371300', '山东省临沂市蒙阴县');
INSERT INTO `city` VALUES (1618, '371329', '临沭县', '371300', '山东省临沂市临沭县');
INSERT INTO `city` VALUES (1619, '371400', '德州市', '370000', '山东省德州市');
INSERT INTO `city` VALUES (1620, '371401', '市辖区', '371400', '山东省德州市市辖区');
INSERT INTO `city` VALUES (1621, '371402', '德城区', '371401', NULL);
INSERT INTO `city` VALUES (1622, '371421', '陵县', '371400', '山东省德州市陵县');
INSERT INTO `city` VALUES (1623, '371422', '宁津县', '371400', '山东省德州市宁津县');
INSERT INTO `city` VALUES (1624, '371423', '庆云县', '371400', '山东省德州市庆云县');
INSERT INTO `city` VALUES (1625, '371424', '临邑县', '371400', '山东省德州市临邑县');
INSERT INTO `city` VALUES (1626, '371425', '齐河县', '371400', '山东省德州市齐河县');
INSERT INTO `city` VALUES (1627, '371426', '平原县', '371400', '山东省德州市平原县');
INSERT INTO `city` VALUES (1628, '371427', '夏津县', '371400', '山东省德州市夏津县');
INSERT INTO `city` VALUES (1629, '371428', '武城县', '371400', '山东省德州市武城县');
INSERT INTO `city` VALUES (1630, '371481', '乐陵市', '371400', '山东省德州市乐陵市');
INSERT INTO `city` VALUES (1631, '371482', '禹城市', '371400', '山东省德州市禹城市');
INSERT INTO `city` VALUES (1632, '371500', '聊城市', '370000', '山东省聊城市');
INSERT INTO `city` VALUES (1633, '371501', '市辖区', '371500', '山东省聊城市市辖区');
INSERT INTO `city` VALUES (1634, '371502', '东昌府区', '371501', NULL);
INSERT INTO `city` VALUES (1635, '371521', '阳谷县', '371500', '山东省聊城市阳谷县');
INSERT INTO `city` VALUES (1636, '371522', '莘县', '371500', '山东省聊城市莘县');
INSERT INTO `city` VALUES (1637, '371523', '茌平县', '371500', '山东省聊城市茌平县');
INSERT INTO `city` VALUES (1638, '371524', '东阿县', '371500', '山东省聊城市东阿县');
INSERT INTO `city` VALUES (1639, '371525', '冠县', '371500', '山东省聊城市冠县');
INSERT INTO `city` VALUES (1640, '371526', '高唐县', '371500', '山东省聊城市高唐县');
INSERT INTO `city` VALUES (1641, '371581', '临清市', '371500', '山东省聊城市临清市');
INSERT INTO `city` VALUES (1642, '371600', '滨州市', '370000', '山东省滨州市');
INSERT INTO `city` VALUES (1643, '371601', '市辖区', '371600', '山东省滨州市市辖区');
INSERT INTO `city` VALUES (1644, '371602', '滨城区', '371601', NULL);
INSERT INTO `city` VALUES (1645, '371621', '惠民县', '371600', '山东省滨州市惠民县');
INSERT INTO `city` VALUES (1646, '371622', '阳信县', '371600', '山东省滨州市阳信县');
INSERT INTO `city` VALUES (1647, '371623', '无棣县', '371600', '山东省滨州市无棣县');
INSERT INTO `city` VALUES (1648, '371624', '沾化县', '371600', '山东省滨州市沾化县');
INSERT INTO `city` VALUES (1649, '371625', '博兴县', '371600', '山东省滨州市博兴县');
INSERT INTO `city` VALUES (1650, '371626', '邹平县', '371600', '山东省滨州市邹平县');
INSERT INTO `city` VALUES (1651, '371700', '菏泽市', '370000', '山东省菏泽市');
INSERT INTO `city` VALUES (1652, '371701', '市辖区', '371700', '山东省菏泽市市辖区');
INSERT INTO `city` VALUES (1653, '371702', '牡丹区', '371701', NULL);
INSERT INTO `city` VALUES (1654, '371721', '曹县', '371700', '山东省菏泽市曹县');
INSERT INTO `city` VALUES (1655, '371722', '单县', '371700', '山东省菏泽市单县');
INSERT INTO `city` VALUES (1656, '371723', '成武县', '371700', '山东省菏泽市成武县');
INSERT INTO `city` VALUES (1657, '371724', '巨野县', '371700', '山东省菏泽市巨野县');
INSERT INTO `city` VALUES (1658, '371725', '郓城县', '371700', '山东省菏泽市郓城县');
INSERT INTO `city` VALUES (1659, '371726', '鄄城县', '371700', '山东省菏泽市鄄城县');
INSERT INTO `city` VALUES (1660, '371727', '定陶县', '371700', '山东省菏泽市定陶县');
INSERT INTO `city` VALUES (1661, '371728', '东明县', '371700', '山东省菏泽市东明县');
INSERT INTO `city` VALUES (1662, '410000', '河南省', '0', '河南省');
INSERT INTO `city` VALUES (1663, '410100', '郑州市', '410000', '河南省郑州市');
INSERT INTO `city` VALUES (1664, '410101', '金水区', '410100', '河南省郑州市金水区');
INSERT INTO `city` VALUES (1665, '410102', '中原区', '410100', '河南省郑州市中原区');
INSERT INTO `city` VALUES (1666, '410103', '二七区', '410100', '河南省郑州市二七区');
INSERT INTO `city` VALUES (1667, '410104', '管城回族区', '410100', '河南省郑州市管城回族区');
INSERT INTO `city` VALUES (1668, '410106', '上街区', '410100', '河南省郑州市上街区');
INSERT INTO `city` VALUES (1669, '410108', '惠济区', '410100', '河南省郑州市惠济区');
INSERT INTO `city` VALUES (1670, '410122', '中牟县', '410100', '河南省郑州市中牟县');
INSERT INTO `city` VALUES (1671, '410181', '巩义市', '410100', '河南省郑州市巩义市');
INSERT INTO `city` VALUES (1672, '410182', '荥阳市', '410100', '河南省郑州市荥阳市');
INSERT INTO `city` VALUES (1673, '410183', '新密市', '410100', '河南省郑州市新密市');
INSERT INTO `city` VALUES (1674, '410184', '新郑市', '410100', '河南省郑州市新郑市');
INSERT INTO `city` VALUES (1675, '410185', '登封市', '410100', '河南省郑州市登封市');
INSERT INTO `city` VALUES (1676, '410200', '开封市', '410000', '河南省开封市');
INSERT INTO `city` VALUES (1677, '410201', '市辖区', '410200', '河南省开封市市辖区');
INSERT INTO `city` VALUES (1678, '410202', '龙亭区', '410201', NULL);
INSERT INTO `city` VALUES (1679, '410203', '顺河回族区', '410201', NULL);
INSERT INTO `city` VALUES (1680, '410204', '鼓楼区', '410201', NULL);
INSERT INTO `city` VALUES (1681, '410205', '禹王台区', '410201', NULL);
INSERT INTO `city` VALUES (1682, '410211', '金明区', '410201', NULL);
INSERT INTO `city` VALUES (1683, '410221', '杞县', '410200', '河南省开封市杞县');
INSERT INTO `city` VALUES (1684, '410222', '通许县', '410200', '河南省开封市通许县');
INSERT INTO `city` VALUES (1685, '410223', '尉氏县', '410200', '河南省开封市尉氏县');
INSERT INTO `city` VALUES (1686, '410224', '开封县', '410200', '河南省开封市开封县');
INSERT INTO `city` VALUES (1687, '410225', '兰考县', '410200', '河南省开封市兰考县');
INSERT INTO `city` VALUES (1688, '410300', '洛阳市', '410000', '河南省洛阳市');
INSERT INTO `city` VALUES (1689, '410301', '市辖区', '410300', '河南省洛阳市市辖区');
INSERT INTO `city` VALUES (1690, '410302', '老城区', '410301', NULL);
INSERT INTO `city` VALUES (1691, '410303', '西工区', '410301', NULL);
INSERT INTO `city` VALUES (1692, '410304', '廛河回族区', '410301', NULL);
INSERT INTO `city` VALUES (1693, '410305', '涧西区', '410301', NULL);
INSERT INTO `city` VALUES (1694, '410306', '吉利区', '410301', NULL);
INSERT INTO `city` VALUES (1695, '410307', '洛龙区', '410301', NULL);
INSERT INTO `city` VALUES (1696, '410322', '孟津县', '410300', '河南省洛阳市孟津县');
INSERT INTO `city` VALUES (1697, '410323', '新安县', '410300', '河南省洛阳市新安县');
INSERT INTO `city` VALUES (1698, '410324', '栾川县', '410300', '河南省洛阳市栾川县');
INSERT INTO `city` VALUES (1699, '410325', '嵩县', '410300', '河南省洛阳市嵩县');
INSERT INTO `city` VALUES (1700, '410326', '汝阳县', '410300', '河南省洛阳市汝阳县');
INSERT INTO `city` VALUES (1701, '410327', '宜阳县', '410300', '河南省洛阳市宜阳县');
INSERT INTO `city` VALUES (1702, '410328', '洛宁县', '410300', '河南省洛阳市洛宁县');
INSERT INTO `city` VALUES (1703, '410329', '伊川县', '410300', '河南省洛阳市伊川县');
INSERT INTO `city` VALUES (1704, '410381', '偃师市', '410300', '河南省洛阳市偃师市');
INSERT INTO `city` VALUES (1705, '410400', '平顶山市', '410000', '河南省平顶山市');
INSERT INTO `city` VALUES (1706, '410401', '市辖区', '410400', '河南省平顶山市市辖区');
INSERT INTO `city` VALUES (1707, '410402', '新华区', '410401', NULL);
INSERT INTO `city` VALUES (1708, '410403', '卫东区', '410401', NULL);
INSERT INTO `city` VALUES (1709, '410404', '石龙区', '410401', NULL);
INSERT INTO `city` VALUES (1710, '410411', '湛河区', '410401', NULL);
INSERT INTO `city` VALUES (1711, '410421', '宝丰县', '410400', '河南省平顶山市宝丰县');
INSERT INTO `city` VALUES (1712, '410422', '叶县', '410400', '河南省平顶山市叶县');
INSERT INTO `city` VALUES (1713, '410423', '鲁山县', '410400', '河南省平顶山市鲁山县');
INSERT INTO `city` VALUES (1714, '410425', '郏县', '410400', '河南省平顶山市郏县');
INSERT INTO `city` VALUES (1715, '410481', '舞钢市', '410400', '河南省平顶山市舞钢市');
INSERT INTO `city` VALUES (1716, '410482', '汝州市', '410400', '河南省平顶山市汝州市');
INSERT INTO `city` VALUES (1717, '410500', '安阳市', '410000', '河南省安阳市');
INSERT INTO `city` VALUES (1718, '410501', '市辖区', '410500', '河南省安阳市市辖区');
INSERT INTO `city` VALUES (1719, '410502', '文峰区', '410501', NULL);
INSERT INTO `city` VALUES (1720, '410503', '北关区', '410501', NULL);
INSERT INTO `city` VALUES (1721, '410505', '殷都区', '410501', NULL);
INSERT INTO `city` VALUES (1722, '410506', '龙安区', '410501', NULL);
INSERT INTO `city` VALUES (1723, '410522', '安阳县', '410500', '河南省安阳市安阳县');
INSERT INTO `city` VALUES (1724, '410523', '汤阴县', '410500', '河南省安阳市汤阴县');
INSERT INTO `city` VALUES (1725, '410526', '滑县', '410500', '河南省安阳市滑县');
INSERT INTO `city` VALUES (1726, '410527', '内黄县', '410500', '河南省安阳市内黄县');
INSERT INTO `city` VALUES (1727, '410581', '林州市', '410500', '河南省安阳市林州市');
INSERT INTO `city` VALUES (1728, '410600', '鹤壁市', '410000', '河南省鹤壁市');
INSERT INTO `city` VALUES (1729, '410601', '市辖区', '410600', '河南省鹤壁市市辖区');
INSERT INTO `city` VALUES (1730, '410602', '鹤山区', '410601', NULL);
INSERT INTO `city` VALUES (1731, '410603', '山城区', '410601', NULL);
INSERT INTO `city` VALUES (1732, '410611', '淇滨区', '410601', NULL);
INSERT INTO `city` VALUES (1733, '410621', '浚县', '410600', '河南省鹤壁市浚县');
INSERT INTO `city` VALUES (1734, '410622', '淇县', '410600', '河南省鹤壁市淇县');
INSERT INTO `city` VALUES (1735, '410700', '新乡市', '410000', '河南省新乡市');
INSERT INTO `city` VALUES (1736, '410701', '市辖区', '410700', '河南省新乡市市辖区');
INSERT INTO `city` VALUES (1737, '410702', '红旗区', '410700', '河南省新乡市红旗区');
INSERT INTO `city` VALUES (1738, '410703', '卫滨区', '410700', '河南省新乡市卫滨区');
INSERT INTO `city` VALUES (1739, '410704', '凤泉区', '410700', '河南省新乡市凤泉区');
INSERT INTO `city` VALUES (1740, '410711', '牧野区', '410700', '河南省新乡市牧野区');
INSERT INTO `city` VALUES (1741, '410721', '新乡县', '410700', '河南省新乡市新乡县');
INSERT INTO `city` VALUES (1742, '410724', '获嘉县', '410700', '河南省新乡市获嘉县');
INSERT INTO `city` VALUES (1743, '410725', '原阳县', '410700', '河南省新乡市原阳县');
INSERT INTO `city` VALUES (1744, '410726', '延津县', '410700', '河南省新乡市延津县');
INSERT INTO `city` VALUES (1745, '410727', '封丘县', '410700', '河南省新乡市封丘县');
INSERT INTO `city` VALUES (1746, '410728', '长垣县', '410700', '河南省新乡市长垣县');
INSERT INTO `city` VALUES (1747, '410781', '卫辉市', '410700', '河南省新乡市卫辉市');
INSERT INTO `city` VALUES (1748, '410782', '辉县市', '410700', '河南省新乡市辉县市');
INSERT INTO `city` VALUES (1749, '410800', '焦作市', '410000', '河南省焦作市');
INSERT INTO `city` VALUES (1750, '410801', '市辖区', '410800', '河南省焦作市市辖区');
INSERT INTO `city` VALUES (1751, '410802', '解放区', '410801', NULL);
INSERT INTO `city` VALUES (1752, '410803', '中站区', '410801', NULL);
INSERT INTO `city` VALUES (1753, '410804', '马村区', '410801', NULL);
INSERT INTO `city` VALUES (1754, '410811', '山阳区', '410801', NULL);
INSERT INTO `city` VALUES (1755, '410821', '修武县', '410800', '河南省焦作市修武县');
INSERT INTO `city` VALUES (1756, '410822', '博爱县', '410800', '河南省焦作市博爱县');
INSERT INTO `city` VALUES (1757, '410823', '武陟县', '410800', '河南省焦作市武陟县');
INSERT INTO `city` VALUES (1758, '410825', '温县', '410800', '河南省焦作市温县');
INSERT INTO `city` VALUES (1759, '410882', '沁阳市', '410800', '河南省焦作市沁阳市');
INSERT INTO `city` VALUES (1760, '410883', '孟州市', '410800', '河南省焦作市孟州市');
INSERT INTO `city` VALUES (1761, '410900', '濮阳市', '410000', '河南省濮阳市');
INSERT INTO `city` VALUES (1762, '410901', '市辖区', '410900', '河南省濮阳市市辖区');
INSERT INTO `city` VALUES (1763, '410902', '华龙区', '410901', NULL);
INSERT INTO `city` VALUES (1764, '410922', '清丰县', '410900', '河南省濮阳市清丰县');
INSERT INTO `city` VALUES (1765, '410923', '南乐县', '410900', '河南省濮阳市南乐县');
INSERT INTO `city` VALUES (1766, '410926', '范县', '410900', '河南省濮阳市范县');
INSERT INTO `city` VALUES (1767, '410927', '台前县', '410900', '河南省濮阳市台前县');
INSERT INTO `city` VALUES (1768, '410928', '濮阳县', '410900', '河南省濮阳市濮阳县');
INSERT INTO `city` VALUES (1769, '411000', '许昌市', '410000', '河南省许昌市');
INSERT INTO `city` VALUES (1770, '411001', '市辖区', '411000', '河南省许昌市市辖区');
INSERT INTO `city` VALUES (1771, '411002', '魏都区', '411001', NULL);
INSERT INTO `city` VALUES (1772, '411023', '许昌县', '411000', '河南省许昌市许昌县');
INSERT INTO `city` VALUES (1773, '411024', '鄢陵县', '411000', '河南省许昌市鄢陵县');
INSERT INTO `city` VALUES (1774, '411025', '襄城县', '411000', '河南省许昌市襄城县');
INSERT INTO `city` VALUES (1775, '411081', '禹州市', '411000', '河南省许昌市禹州市');
INSERT INTO `city` VALUES (1776, '411082', '长葛市', '411000', '河南省许昌市长葛市');
INSERT INTO `city` VALUES (1777, '411100', '漯河市', '410000', '河南省漯河市');
INSERT INTO `city` VALUES (1778, '411101', '召陵区', '411100', '河南省漯河市召陵区');
INSERT INTO `city` VALUES (1779, '411102', '源汇区', '411100', '河南省漯河市源汇区');
INSERT INTO `city` VALUES (1780, '411103', '郾城区', '411100', '河南省漯河市郾城区');
INSERT INTO `city` VALUES (1781, '411121', '舞阳县', '411100', '河南省漯河市舞阳县');
INSERT INTO `city` VALUES (1782, '411122', '临颍县', '411100', '河南省漯河市临颍县');
INSERT INTO `city` VALUES (1783, '411200', '三门峡市', '410000', '河南省三门峡市');
INSERT INTO `city` VALUES (1784, '411201', '市辖区', '411200', '河南省三门峡市市辖区');
INSERT INTO `city` VALUES (1785, '411202', '湖滨区', '411201', NULL);
INSERT INTO `city` VALUES (1786, '411221', '渑池县', '411200', '河南省三门峡市渑池县');
INSERT INTO `city` VALUES (1787, '411222', '陕县', '411200', '河南省三门峡市陕县');
INSERT INTO `city` VALUES (1788, '411224', '卢氏县', '411200', '河南省三门峡市卢氏县');
INSERT INTO `city` VALUES (1789, '411281', '义马市', '411200', '河南省三门峡市义马市');
INSERT INTO `city` VALUES (1790, '411282', '灵宝市', '411200', '河南省三门峡市灵宝市');
INSERT INTO `city` VALUES (1791, '411300', '南阳市', '410000', '河南省南阳市');
INSERT INTO `city` VALUES (1792, '411301', '市辖区', '411300', '河南省南阳市市辖区');
INSERT INTO `city` VALUES (1793, '411302', '宛城区', '411301', NULL);
INSERT INTO `city` VALUES (1794, '411303', '卧龙区', '411301', NULL);
INSERT INTO `city` VALUES (1795, '411321', '南召县', '411300', '河南省南阳市南召县');
INSERT INTO `city` VALUES (1796, '411322', '方城县', '411300', '河南省南阳市方城县');
INSERT INTO `city` VALUES (1797, '411323', '西峡县', '411300', '河南省南阳市西峡县');
INSERT INTO `city` VALUES (1798, '411324', '镇平县', '411300', '河南省南阳市镇平县');
INSERT INTO `city` VALUES (1799, '411325', '内乡县', '411300', '河南省南阳市内乡县');
INSERT INTO `city` VALUES (1800, '411326', '淅川县', '411300', '河南省南阳市淅川县');
INSERT INTO `city` VALUES (1801, '411327', '社旗县', '411300', '河南省南阳市社旗县');
INSERT INTO `city` VALUES (1802, '411328', '唐河县', '411300', '河南省南阳市唐河县');
INSERT INTO `city` VALUES (1803, '411329', '新野县', '411300', '河南省南阳市新野县');
INSERT INTO `city` VALUES (1804, '411330', '桐柏县', '411300', '河南省南阳市桐柏县');
INSERT INTO `city` VALUES (1805, '411381', '邓州市', '411300', '河南省南阳市邓州市');
INSERT INTO `city` VALUES (1806, '411400', '商丘市', '410000', '河南省商丘市');
INSERT INTO `city` VALUES (1807, '411401', '市辖区', '411400', '河南省商丘市市辖区');
INSERT INTO `city` VALUES (1808, '411402', '梁园区', '411400', '河南省商丘市梁园区');
INSERT INTO `city` VALUES (1809, '411403', '睢阳区', '411400', '河南省商丘市睢阳区');
INSERT INTO `city` VALUES (1810, '411421', '民权县', '411400', '河南省商丘市民权县');
INSERT INTO `city` VALUES (1811, '411422', '睢县', '411400', '河南省商丘市睢县');
INSERT INTO `city` VALUES (1812, '411423', '宁陵县', '411400', '河南省商丘市宁陵县');
INSERT INTO `city` VALUES (1813, '411424', '柘城县', '411400', '河南省商丘市柘城县');
INSERT INTO `city` VALUES (1814, '411425', '虞城县', '411400', '河南省商丘市虞城县');
INSERT INTO `city` VALUES (1815, '411426', '夏邑县', '411400', '河南省商丘市夏邑县');
INSERT INTO `city` VALUES (1816, '411481', '永城市', '411400', '河南省商丘市永城市');
INSERT INTO `city` VALUES (1817, '411482', '新区', '411400', '河南省商丘市新区');
INSERT INTO `city` VALUES (1818, '411500', '信阳市', '410000', '河南省信阳市');
INSERT INTO `city` VALUES (1819, '411501', '市辖区', '411500', '河南省信阳市市辖区');
INSERT INTO `city` VALUES (1820, '411502', '浉河区', '411501', NULL);
INSERT INTO `city` VALUES (1821, '411503', '平桥区', '411501', NULL);
INSERT INTO `city` VALUES (1822, '411521', '罗山县', '411500', '河南省信阳市罗山县');
INSERT INTO `city` VALUES (1823, '411522', '光山县', '411500', '河南省信阳市光山县');
INSERT INTO `city` VALUES (1824, '411523', '新县', '411500', '河南省信阳市新县');
INSERT INTO `city` VALUES (1825, '411524', '商城县', '411500', '河南省信阳市商城县');
INSERT INTO `city` VALUES (1826, '411525', '固始县', '411500', '河南省信阳市固始县');
INSERT INTO `city` VALUES (1827, '411526', '潢川县', '411500', '河南省信阳市潢川县');
INSERT INTO `city` VALUES (1828, '411527', '淮滨县', '411500', '河南省信阳市淮滨县');
INSERT INTO `city` VALUES (1829, '411528', '息县', '411500', '河南省信阳市息县');
INSERT INTO `city` VALUES (1830, '411600', '周口市', '410000', '河南省周口市');
INSERT INTO `city` VALUES (1831, '411601', '市辖区', '411600', '河南省周口市市辖区');
INSERT INTO `city` VALUES (1832, '411602', '川汇区', '411601', NULL);
INSERT INTO `city` VALUES (1833, '411621', '扶沟县', '411600', '河南省周口市扶沟县');
INSERT INTO `city` VALUES (1834, '411622', '西华县', '411600', '河南省周口市西华县');
INSERT INTO `city` VALUES (1835, '411623', '商水县', '411600', '河南省周口市商水县');
INSERT INTO `city` VALUES (1836, '411624', '沈丘县', '411600', '河南省周口市沈丘县');
INSERT INTO `city` VALUES (1837, '411625', '郸城县', '411600', '河南省周口市郸城县');
INSERT INTO `city` VALUES (1838, '411626', '淮阳县', '411600', '河南省周口市淮阳县');
INSERT INTO `city` VALUES (1839, '411627', '太康县', '411600', '河南省周口市太康县');
INSERT INTO `city` VALUES (1840, '411628', '鹿邑县', '411600', '河南省周口市鹿邑县');
INSERT INTO `city` VALUES (1841, '411681', '项城市', '411600', '河南省周口市项城市');
INSERT INTO `city` VALUES (1842, '411700', '驻马店市', '410000', '河南省驻马店市');
INSERT INTO `city` VALUES (1843, '411701', '市辖区', '411700', '河南省驻马店市市辖区');
INSERT INTO `city` VALUES (1844, '411702', '驿城区', '411701', NULL);
INSERT INTO `city` VALUES (1845, '411721', '西平县', '411700', '河南省驻马店市西平县');
INSERT INTO `city` VALUES (1846, '411722', '上蔡县', '411700', '河南省驻马店市上蔡县');
INSERT INTO `city` VALUES (1847, '411723', '平舆县', '411700', '河南省驻马店市平舆县');
INSERT INTO `city` VALUES (1848, '411724', '正阳县', '411700', '河南省驻马店市正阳县');
INSERT INTO `city` VALUES (1849, '411725', '确山县', '411700', '河南省驻马店市确山县');
INSERT INTO `city` VALUES (1850, '411726', '泌阳县', '411700', '河南省驻马店市泌阳县');
INSERT INTO `city` VALUES (1851, '411727', '汝南县', '411700', '河南省驻马店市汝南县');
INSERT INTO `city` VALUES (1852, '411728', '遂平县', '411700', '河南省驻马店市遂平县');
INSERT INTO `city` VALUES (1853, '411729', '新蔡县', '411700', '河南省驻马店市新蔡县');
INSERT INTO `city` VALUES (1854, '411800', '济源市', '410000', '河南省济源市');
INSERT INTO `city` VALUES (1855, '411801', '市辖区', '411800', '河南省济源市市辖区');
INSERT INTO `city` VALUES (1856, '420000', '湖北省', '0', '湖北省');
INSERT INTO `city` VALUES (1857, '420100', '武汉市', '420000', '湖北省武汉市');
INSERT INTO `city` VALUES (1858, '420101', '市辖区', '420100', '湖北省武汉市市辖区');
INSERT INTO `city` VALUES (1859, '420102', '江岸区', '420101', NULL);
INSERT INTO `city` VALUES (1860, '420103', '江汉区', '420101', NULL);
INSERT INTO `city` VALUES (1861, '420104', '硚口区', '420101', NULL);
INSERT INTO `city` VALUES (1862, '420105', '汉阳区', '420101', NULL);
INSERT INTO `city` VALUES (1863, '420106', '武昌区', '420101', NULL);
INSERT INTO `city` VALUES (1864, '420107', '青山区', '420101', NULL);
INSERT INTO `city` VALUES (1865, '420111', '洪山区', '420101', NULL);
INSERT INTO `city` VALUES (1866, '420112', '东西湖区', '420101', NULL);
INSERT INTO `city` VALUES (1867, '420113', '汉南区', '420101', NULL);
INSERT INTO `city` VALUES (1868, '420114', '蔡甸区', '420101', NULL);
INSERT INTO `city` VALUES (1869, '420115', '江夏区', '420101', NULL);
INSERT INTO `city` VALUES (1870, '420116', '黄陂区', '420101', NULL);
INSERT INTO `city` VALUES (1871, '420117', '新洲区', '420100', '湖北省武汉市新洲区');
INSERT INTO `city` VALUES (1872, '420200', '黄石市', '420000', '湖北省黄石市');
INSERT INTO `city` VALUES (1873, '420201', '市辖区', '420200', '湖北省黄石市市辖区');
INSERT INTO `city` VALUES (1874, '420202', '黄石港区', '420201', NULL);
INSERT INTO `city` VALUES (1875, '420203', '西塞山区', '420201', NULL);
INSERT INTO `city` VALUES (1876, '420204', '下陆区', '420201', NULL);
INSERT INTO `city` VALUES (1877, '420205', '铁山区', '420201', NULL);
INSERT INTO `city` VALUES (1878, '420222', '阳新县', '420200', '湖北省黄石市阳新县');
INSERT INTO `city` VALUES (1879, '420281', '大冶市', '420200', '湖北省黄石市大冶市');
INSERT INTO `city` VALUES (1880, '420300', '十堰市', '420000', '湖北省十堰市');
INSERT INTO `city` VALUES (1881, '420301', '市辖区', '420300', '湖北省十堰市市辖区');
INSERT INTO `city` VALUES (1882, '420302', '茅箭区', '420301', NULL);
INSERT INTO `city` VALUES (1883, '420303', '张湾区', '420301', NULL);
INSERT INTO `city` VALUES (1884, '420321', '郧县', '420300', '湖北省十堰市郧县');
INSERT INTO `city` VALUES (1885, '420322', '郧西县', '420300', '湖北省十堰市郧西县');
INSERT INTO `city` VALUES (1886, '420323', '竹山县', '420300', '湖北省十堰市竹山县');
INSERT INTO `city` VALUES (1887, '420324', '竹溪县', '420300', '湖北省十堰市竹溪县');
INSERT INTO `city` VALUES (1888, '420325', '房县', '420300', '湖北省十堰市房县');
INSERT INTO `city` VALUES (1889, '420381', '丹江口市', '420300', '湖北省十堰市丹江口市');
INSERT INTO `city` VALUES (1890, '420500', '宜昌市', '420000', '湖北省宜昌市');
INSERT INTO `city` VALUES (1891, '420501', '市辖区', '420500', '湖北省宜昌市市辖区');
INSERT INTO `city` VALUES (1892, '420502', '西陵区', '420501', NULL);
INSERT INTO `city` VALUES (1893, '420503', '伍家岗区', '420501', NULL);
INSERT INTO `city` VALUES (1894, '420504', '点军区', '420501', NULL);
INSERT INTO `city` VALUES (1895, '420505', '猇亭区', '420501', NULL);
INSERT INTO `city` VALUES (1896, '420506', '夷陵区', '420501', NULL);
INSERT INTO `city` VALUES (1897, '420525', '远安县', '420500', '湖北省宜昌市远安县');
INSERT INTO `city` VALUES (1898, '420526', '兴山县', '420500', '湖北省宜昌市兴山县');
INSERT INTO `city` VALUES (1899, '420527', '秭归县', '420500', '湖北省宜昌市秭归县');
INSERT INTO `city` VALUES (1900, '420528', '长阳土家族自治县', '420500', '湖北省宜昌市长阳土家族自治县');
INSERT INTO `city` VALUES (1901, '420529', '五峰土家族自治县', '420500', '湖北省宜昌市五峰土家族自治县');
INSERT INTO `city` VALUES (1902, '420581', '宜都市', '420500', '湖北省宜昌市宜都市');
INSERT INTO `city` VALUES (1903, '420582', '当阳市', '420500', '湖北省宜昌市当阳市');
INSERT INTO `city` VALUES (1904, '420583', '枝江市', '420500', '湖北省宜昌市枝江市');
INSERT INTO `city` VALUES (1905, '420600', '襄阳市', '420000', '湖北省襄阳市');
INSERT INTO `city` VALUES (1906, '420601', '市辖区', '420600', '湖北省襄阳市市辖区');
INSERT INTO `city` VALUES (1907, '420602', '襄城区', '420601', NULL);
INSERT INTO `city` VALUES (1908, '420606', '樊城区', '420601', NULL);
INSERT INTO `city` VALUES (1909, '420607', '襄州区', '420601', NULL);
INSERT INTO `city` VALUES (1910, '420624', '南漳县', '420600', '湖北省襄阳市南漳县');
INSERT INTO `city` VALUES (1911, '420625', '谷城县', '420600', '湖北省襄阳市谷城县');
INSERT INTO `city` VALUES (1912, '420626', '保康县', '420600', '湖北省襄阳市保康县');
INSERT INTO `city` VALUES (1913, '420682', '老河口市', '420600', '湖北省襄阳市老河口市');
INSERT INTO `city` VALUES (1914, '420683', '枣阳市', '420600', '湖北省襄阳市枣阳市');
INSERT INTO `city` VALUES (1915, '420684', '宜城市', '420600', '湖北省襄阳市宜城市');
INSERT INTO `city` VALUES (1916, '420700', '鄂州市', '420000', '湖北省鄂州市');
INSERT INTO `city` VALUES (1917, '420701', '市辖区', '420700', '湖北省鄂州市市辖区');
INSERT INTO `city` VALUES (1918, '420702', '梁子湖区', '420701', NULL);
INSERT INTO `city` VALUES (1919, '420703', '华容区', '420701', NULL);
INSERT INTO `city` VALUES (1920, '420704', '鄂城区', '420701', NULL);
INSERT INTO `city` VALUES (1921, '420800', '荆门市', '420000', '湖北省荆门市');
INSERT INTO `city` VALUES (1922, '420801', '市辖区', '420800', '湖北省荆门市市辖区');
INSERT INTO `city` VALUES (1923, '420802', '东宝区', '420801', NULL);
INSERT INTO `city` VALUES (1924, '420804', '掇刀区', '420801', NULL);
INSERT INTO `city` VALUES (1925, '420821', '京山县', '420800', '湖北省荆门市京山县');
INSERT INTO `city` VALUES (1926, '420822', '沙洋县', '420800', '湖北省荆门市沙洋县');
INSERT INTO `city` VALUES (1927, '420881', '钟祥市', '420800', '湖北省荆门市钟祥市');
INSERT INTO `city` VALUES (1928, '420900', '孝感市', '420000', '湖北省孝感市');
INSERT INTO `city` VALUES (1929, '420901', '市辖区', '420900', '湖北省孝感市市辖区');
INSERT INTO `city` VALUES (1930, '420902', '孝南区', '420901', NULL);
INSERT INTO `city` VALUES (1931, '420921', '孝昌县', '420900', '湖北省孝感市孝昌县');
INSERT INTO `city` VALUES (1932, '420922', '大悟县', '420900', '湖北省孝感市大悟县');
INSERT INTO `city` VALUES (1933, '420923', '云梦县', '420900', '湖北省孝感市云梦县');
INSERT INTO `city` VALUES (1934, '420981', '应城市', '420900', '湖北省孝感市应城市');
INSERT INTO `city` VALUES (1935, '420982', '安陆市', '420900', '湖北省孝感市安陆市');
INSERT INTO `city` VALUES (1936, '420984', '汉川市', '420900', '湖北省孝感市汉川市');
INSERT INTO `city` VALUES (1937, '421000', '荆州市', '420000', '湖北省荆州市');
INSERT INTO `city` VALUES (1938, '421001', '市辖区', '421000', '湖北省荆州市市辖区');
INSERT INTO `city` VALUES (1939, '421002', '沙市区', '421001', NULL);
INSERT INTO `city` VALUES (1940, '421003', '荆州区', '421001', NULL);
INSERT INTO `city` VALUES (1941, '421022', '公安县', '421000', '湖北省荆州市公安县');
INSERT INTO `city` VALUES (1942, '421023', '监利县', '421000', '湖北省荆州市监利县');
INSERT INTO `city` VALUES (1943, '421024', '江陵县', '421000', '湖北省荆州市江陵县');
INSERT INTO `city` VALUES (1944, '421081', '石首市', '421000', '湖北省荆州市石首市');
INSERT INTO `city` VALUES (1945, '421083', '洪湖市', '421000', '湖北省荆州市洪湖市');
INSERT INTO `city` VALUES (1946, '421087', '松滋市', '421000', '湖北省荆州市松滋市');
INSERT INTO `city` VALUES (1947, '421100', '黄冈市', '420000', '湖北省黄冈市');
INSERT INTO `city` VALUES (1948, '421101', '市辖区', '421100', '湖北省黄冈市市辖区');
INSERT INTO `city` VALUES (1949, '421102', '黄州区', '421101', NULL);
INSERT INTO `city` VALUES (1950, '421121', '团风县', '421100', '湖北省黄冈市团风县');
INSERT INTO `city` VALUES (1951, '421122', '红安县', '421100', '湖北省黄冈市红安县');
INSERT INTO `city` VALUES (1952, '421123', '罗田县', '421100', '湖北省黄冈市罗田县');
INSERT INTO `city` VALUES (1953, '421124', '英山县', '421100', '湖北省黄冈市英山县');
INSERT INTO `city` VALUES (1954, '421125', '浠水县', '421100', '湖北省黄冈市浠水县');
INSERT INTO `city` VALUES (1955, '421126', '蕲春县', '421100', '湖北省黄冈市蕲春县');
INSERT INTO `city` VALUES (1956, '421127', '黄梅县', '421100', '湖北省黄冈市黄梅县');
INSERT INTO `city` VALUES (1957, '421181', '麻城市', '421100', '湖北省黄冈市麻城市');
INSERT INTO `city` VALUES (1958, '421182', '武穴市', '421100', '湖北省黄冈市武穴市');
INSERT INTO `city` VALUES (1959, '421200', '咸宁市', '420000', '湖北省咸宁市');
INSERT INTO `city` VALUES (1960, '421201', '市辖区', '421200', '湖北省咸宁市市辖区');
INSERT INTO `city` VALUES (1961, '421202', '咸安区', '421201', NULL);
INSERT INTO `city` VALUES (1962, '421221', '嘉鱼县', '421200', '湖北省咸宁市嘉鱼县');
INSERT INTO `city` VALUES (1963, '421222', '通城县', '421200', '湖北省咸宁市通城县');
INSERT INTO `city` VALUES (1964, '421223', '崇阳县', '421200', '湖北省咸宁市崇阳县');
INSERT INTO `city` VALUES (1965, '421224', '通山县', '421200', '湖北省咸宁市通山县');
INSERT INTO `city` VALUES (1966, '421281', '赤壁市', '421200', '湖北省咸宁市赤壁市');
INSERT INTO `city` VALUES (1967, '421300', '随州市', '420000', '湖北省随州市');
INSERT INTO `city` VALUES (1968, '421301', '市辖区', '421300', '湖北省随州市市辖区');
INSERT INTO `city` VALUES (1969, '421302', '曾都区', '421301', NULL);
INSERT INTO `city` VALUES (1970, '421381', '广水市', '421300', '湖北省随州市广水市');
INSERT INTO `city` VALUES (1971, '422800', '恩施土家族苗族自治州', '420000', '湖北省恩施土家族苗族自治州');
INSERT INTO `city` VALUES (1972, '422801', '恩施市', '422800', '湖北省恩施土家族苗族自治州恩施市');
INSERT INTO `city` VALUES (1973, '422802', '利川市', '422800', '湖北省恩施土家族苗族自治州利川市');
INSERT INTO `city` VALUES (1974, '422822', '建始县', '422800', '湖北省恩施土家族苗族自治州建始县');
INSERT INTO `city` VALUES (1975, '422823', '巴东县', '422800', '湖北省恩施土家族苗族自治州巴东县');
INSERT INTO `city` VALUES (1976, '422825', '宣恩县', '422800', '湖北省恩施土家族苗族自治州宣恩县');
INSERT INTO `city` VALUES (1977, '422826', '咸丰县', '422800', '湖北省恩施土家族苗族自治州咸丰县');
INSERT INTO `city` VALUES (1978, '422827', '来凤县', '422800', '湖北省恩施土家族苗族自治州来凤县');
INSERT INTO `city` VALUES (1979, '422828', '鹤峰县', '422800', '湖北省恩施土家族苗族自治州鹤峰县');
INSERT INTO `city` VALUES (1980, '429000', '省直辖行政单位', '420000', '湖北省省直辖行政单位');
INSERT INTO `city` VALUES (1981, '429004', '仙桃市', '429000', '湖北省省直辖行政单位仙桃市');
INSERT INTO `city` VALUES (1982, '429005', '潜江市', '429000', '湖北省省直辖行政单位潜江市');
INSERT INTO `city` VALUES (1983, '429006', '天门市', '429000', '湖北省省直辖行政单位天门市');
INSERT INTO `city` VALUES (1984, '429021', '神农架林区', '429000', '湖北省省直辖行政单位神农架林区');
INSERT INTO `city` VALUES (1985, '430000', '湖南省', '0', '湖南省');
INSERT INTO `city` VALUES (1986, '430100', '长沙市', '430000', '湖南省长沙市');
INSERT INTO `city` VALUES (1987, '430101', '市辖区', '430100', '湖南省长沙市市辖区');
INSERT INTO `city` VALUES (1988, '430102', '芙蓉区', '430101', NULL);
INSERT INTO `city` VALUES (1989, '430103', '天心区', '430101', NULL);
INSERT INTO `city` VALUES (1990, '430104', '岳麓区', '430101', NULL);
INSERT INTO `city` VALUES (1991, '430105', '开福区', '430101', NULL);
INSERT INTO `city` VALUES (1992, '430111', '雨花区', '430101', NULL);
INSERT INTO `city` VALUES (1993, '430121', '长沙县', '430100', '湖南省长沙市长沙县');
INSERT INTO `city` VALUES (1994, '430122', '望城县', '430100', '湖南省长沙市望城县');
INSERT INTO `city` VALUES (1995, '430124', '宁乡县', '430100', '湖南省长沙市宁乡县');
INSERT INTO `city` VALUES (1996, '430181', '浏阳市', '430100', '湖南省长沙市浏阳市');
INSERT INTO `city` VALUES (1997, '430200', '株洲市', '430000', '湖南省株洲市');
INSERT INTO `city` VALUES (1998, '430201', '市辖区', '430200', '湖南省株洲市市辖区');
INSERT INTO `city` VALUES (1999, '430202', '荷塘区', '430201', NULL);
INSERT INTO `city` VALUES (2000, '430203', '芦淞区', '430201', NULL);
INSERT INTO `city` VALUES (2001, '430204', '石峰区', '430201', NULL);
INSERT INTO `city` VALUES (2002, '430211', '天元区', '430201', NULL);
INSERT INTO `city` VALUES (2003, '430221', '株洲县', '430200', '湖南省株洲市株洲县');
INSERT INTO `city` VALUES (2004, '430223', '攸县', '430200', '湖南省株洲市攸县');
INSERT INTO `city` VALUES (2005, '430224', '茶陵县', '430200', '湖南省株洲市茶陵县');
INSERT INTO `city` VALUES (2006, '430225', '炎陵县', '430200', '湖南省株洲市炎陵县');
INSERT INTO `city` VALUES (2007, '430281', '醴陵市', '430200', '湖南省株洲市醴陵市');
INSERT INTO `city` VALUES (2008, '430300', '湘潭市', '430000', '湖南省湘潭市');
INSERT INTO `city` VALUES (2009, '430301', '市辖区', '430300', '湖南省湘潭市市辖区');
INSERT INTO `city` VALUES (2010, '430302', '雨湖区', '430301', NULL);
INSERT INTO `city` VALUES (2011, '430304', '岳塘区', '430301', NULL);
INSERT INTO `city` VALUES (2012, '430321', '湘潭县', '430300', '湖南省湘潭市湘潭县');
INSERT INTO `city` VALUES (2013, '430381', '湘乡市', '430300', '湖南省湘潭市湘乡市');
INSERT INTO `city` VALUES (2014, '430382', '韶山市', '430300', '湖南省湘潭市韶山市');
INSERT INTO `city` VALUES (2015, '430400', '衡阳市', '430000', '湖南省衡阳市');
INSERT INTO `city` VALUES (2016, '430401', '市辖区', '430400', '湖南省衡阳市市辖区');
INSERT INTO `city` VALUES (2017, '430405', '珠晖区', '430401', NULL);
INSERT INTO `city` VALUES (2018, '430406', '雁峰区', '430401', NULL);
INSERT INTO `city` VALUES (2019, '430407', '石鼓区', '430401', NULL);
INSERT INTO `city` VALUES (2020, '430408', '蒸湘区', '430401', NULL);
INSERT INTO `city` VALUES (2021, '430412', '南岳区', '430401', NULL);
INSERT INTO `city` VALUES (2022, '430421', '衡阳县', '430400', '湖南省衡阳市衡阳县');
INSERT INTO `city` VALUES (2023, '430422', '衡南县', '430400', '湖南省衡阳市衡南县');
INSERT INTO `city` VALUES (2024, '430423', '衡山县', '430400', '湖南省衡阳市衡山县');
INSERT INTO `city` VALUES (2025, '430424', '衡东县', '430400', '湖南省衡阳市衡东县');
INSERT INTO `city` VALUES (2026, '430426', '祁东县', '430400', '湖南省衡阳市祁东县');
INSERT INTO `city` VALUES (2027, '430481', '耒阳市', '430400', '湖南省衡阳市耒阳市');
INSERT INTO `city` VALUES (2028, '430482', '常宁市', '430400', '湖南省衡阳市常宁市');
INSERT INTO `city` VALUES (2029, '430500', '邵阳市', '430000', '湖南省邵阳市');
INSERT INTO `city` VALUES (2030, '430501', '市辖区', '430500', '湖南省邵阳市市辖区');
INSERT INTO `city` VALUES (2031, '430502', '双清区', '430501', NULL);
INSERT INTO `city` VALUES (2032, '430503', '大祥区', '430501', NULL);
INSERT INTO `city` VALUES (2033, '430511', '北塔区', '430501', NULL);
INSERT INTO `city` VALUES (2034, '430521', '邵东县', '430500', '湖南省邵阳市邵东县');
INSERT INTO `city` VALUES (2035, '430522', '新邵县', '430500', '湖南省邵阳市新邵县');
INSERT INTO `city` VALUES (2036, '430523', '邵阳县', '430500', '湖南省邵阳市邵阳县');
INSERT INTO `city` VALUES (2037, '430524', '隆回县', '430500', '湖南省邵阳市隆回县');
INSERT INTO `city` VALUES (2038, '430525', '洞口县', '430500', '湖南省邵阳市洞口县');
INSERT INTO `city` VALUES (2039, '430527', '绥宁县', '430500', '湖南省邵阳市绥宁县');
INSERT INTO `city` VALUES (2040, '430528', '新宁县', '430500', '湖南省邵阳市新宁县');
INSERT INTO `city` VALUES (2041, '430529', '城步苗族自治县', '430500', '湖南省邵阳市城步苗族自治县');
INSERT INTO `city` VALUES (2042, '430581', '武冈市', '430500', '湖南省邵阳市武冈市');
INSERT INTO `city` VALUES (2043, '430600', '岳阳市', '430000', '湖南省岳阳市');
INSERT INTO `city` VALUES (2044, '430601', '市辖区', '430600', '湖南省岳阳市市辖区');
INSERT INTO `city` VALUES (2045, '430602', '岳阳楼区', '430601', NULL);
INSERT INTO `city` VALUES (2046, '430603', '云溪区', '430601', NULL);
INSERT INTO `city` VALUES (2047, '430611', '君山区', '430601', NULL);
INSERT INTO `city` VALUES (2048, '430621', '岳阳县', '430600', '湖南省岳阳市岳阳县');
INSERT INTO `city` VALUES (2049, '430623', '华容县', '430600', '湖南省岳阳市华容县');
INSERT INTO `city` VALUES (2050, '430624', '湘阴县', '430600', '湖南省岳阳市湘阴县');
INSERT INTO `city` VALUES (2051, '430626', '平江县', '430600', '湖南省岳阳市平江县');
INSERT INTO `city` VALUES (2052, '430681', '汨罗市', '430600', '湖南省岳阳市汨罗市');
INSERT INTO `city` VALUES (2053, '430682', '临湘市', '430600', '湖南省岳阳市临湘市');
INSERT INTO `city` VALUES (2054, '430700', '常德市', '430000', '湖南省常德市');
INSERT INTO `city` VALUES (2055, '430701', '市辖区', '430700', '湖南省常德市市辖区');
INSERT INTO `city` VALUES (2056, '430702', '武陵区', '430701', NULL);
INSERT INTO `city` VALUES (2057, '430703', '鼎城区', '430701', NULL);
INSERT INTO `city` VALUES (2058, '430721', '安乡县', '430700', '湖南省常德市安乡县');
INSERT INTO `city` VALUES (2059, '430722', '汉寿县', '430700', '湖南省常德市汉寿县');
INSERT INTO `city` VALUES (2060, '430723', '澧县', '430700', '湖南省常德市澧县');
INSERT INTO `city` VALUES (2061, '430724', '临澧县', '430700', '湖南省常德市临澧县');
INSERT INTO `city` VALUES (2062, '430725', '桃源县', '430700', '湖南省常德市桃源县');
INSERT INTO `city` VALUES (2063, '430726', '石门县', '430700', '湖南省常德市石门县');
INSERT INTO `city` VALUES (2064, '430781', '津市市', '430700', '湖南省常德市津市市');
INSERT INTO `city` VALUES (2065, '430800', '张家界市', '430000', '湖南省张家界市');
INSERT INTO `city` VALUES (2066, '430801', '市辖区', '430800', '湖南省张家界市市辖区');
INSERT INTO `city` VALUES (2067, '430802', '永定区', '430801', NULL);
INSERT INTO `city` VALUES (2068, '430811', '武陵源区', '430801', NULL);
INSERT INTO `city` VALUES (2069, '430821', '慈利县', '430800', '湖南省张家界市慈利县');
INSERT INTO `city` VALUES (2070, '430822', '桑植县', '430800', '湖南省张家界市桑植县');
INSERT INTO `city` VALUES (2071, '430900', '益阳市', '430000', '湖南省益阳市');
INSERT INTO `city` VALUES (2072, '430901', '市辖区', '430900', '湖南省益阳市市辖区');
INSERT INTO `city` VALUES (2073, '430902', '资阳区', '430901', NULL);
INSERT INTO `city` VALUES (2074, '430903', '赫山区', '430901', NULL);
INSERT INTO `city` VALUES (2075, '430921', '南县', '430900', '湖南省益阳市南县');
INSERT INTO `city` VALUES (2076, '430922', '桃江县', '430900', '湖南省益阳市桃江县');
INSERT INTO `city` VALUES (2077, '430923', '安化县', '430900', '湖南省益阳市安化县');
INSERT INTO `city` VALUES (2078, '430981', '沅江市', '430900', '湖南省益阳市沅江市');
INSERT INTO `city` VALUES (2079, '431000', '郴州市', '430000', '湖南省郴州市');
INSERT INTO `city` VALUES (2080, '431001', '市辖区', '431000', '湖南省郴州市市辖区');
INSERT INTO `city` VALUES (2081, '431002', '北湖区', '431001', NULL);
INSERT INTO `city` VALUES (2082, '431003', '苏仙区', '431001', NULL);
INSERT INTO `city` VALUES (2083, '431021', '桂阳县', '431000', '湖南省郴州市桂阳县');
INSERT INTO `city` VALUES (2084, '431022', '宜章县', '431000', '湖南省郴州市宜章县');
INSERT INTO `city` VALUES (2085, '431023', '永兴县', '431000', '湖南省郴州市永兴县');
INSERT INTO `city` VALUES (2086, '431024', '嘉禾县', '431000', '湖南省郴州市嘉禾县');
INSERT INTO `city` VALUES (2087, '431025', '临武县', '431000', '湖南省郴州市临武县');
INSERT INTO `city` VALUES (2088, '431026', '汝城县', '431000', '湖南省郴州市汝城县');
INSERT INTO `city` VALUES (2089, '431027', '桂东县', '431000', '湖南省郴州市桂东县');
INSERT INTO `city` VALUES (2090, '431028', '安仁县', '431000', '湖南省郴州市安仁县');
INSERT INTO `city` VALUES (2091, '431081', '资兴市', '431000', '湖南省郴州市资兴市');
INSERT INTO `city` VALUES (2092, '431100', '永州市', '430000', '湖南省永州市');
INSERT INTO `city` VALUES (2093, '431101', '市辖区', '431100', '湖南省永州市市辖区');
INSERT INTO `city` VALUES (2094, '431102', '零陵区', '431101', NULL);
INSERT INTO `city` VALUES (2095, '431103', '冷水滩区', '431101', NULL);
INSERT INTO `city` VALUES (2096, '431121', '祁阳县', '431100', '湖南省永州市祁阳县');
INSERT INTO `city` VALUES (2097, '431122', '东安县', '431100', '湖南省永州市东安县');
INSERT INTO `city` VALUES (2098, '431123', '双牌县', '431100', '湖南省永州市双牌县');
INSERT INTO `city` VALUES (2099, '431124', '道县', '431100', '湖南省永州市道县');
INSERT INTO `city` VALUES (2100, '431125', '江永县', '431100', '湖南省永州市江永县');
INSERT INTO `city` VALUES (2101, '431126', '宁远县', '431100', '湖南省永州市宁远县');
INSERT INTO `city` VALUES (2102, '431127', '蓝山县', '431100', '湖南省永州市蓝山县');
INSERT INTO `city` VALUES (2103, '431128', '新田县', '431100', '湖南省永州市新田县');
INSERT INTO `city` VALUES (2104, '431129', '江华瑶族自治县', '431100', '湖南省永州市江华瑶族自治县');
INSERT INTO `city` VALUES (2105, '431200', '怀化市', '430000', '湖南省怀化市');
INSERT INTO `city` VALUES (2106, '431201', '市辖区', '431200', '湖南省怀化市市辖区');
INSERT INTO `city` VALUES (2107, '431202', '鹤城区', '431201', NULL);
INSERT INTO `city` VALUES (2108, '431221', '中方县', '431200', '湖南省怀化市中方县');
INSERT INTO `city` VALUES (2109, '431222', '沅陵县', '431200', '湖南省怀化市沅陵县');
INSERT INTO `city` VALUES (2110, '431223', '辰溪县', '431200', '湖南省怀化市辰溪县');
INSERT INTO `city` VALUES (2111, '431224', '溆浦县', '431200', '湖南省怀化市溆浦县');
INSERT INTO `city` VALUES (2112, '431225', '会同县', '431200', '湖南省怀化市会同县');
INSERT INTO `city` VALUES (2113, '431226', '麻阳苗族自治县', '431200', '湖南省怀化市麻阳苗族自治县');
INSERT INTO `city` VALUES (2114, '431227', '新晃侗族自治县', '431200', '湖南省怀化市新晃侗族自治县');
INSERT INTO `city` VALUES (2115, '431228', '芷江侗族自治县', '431200', '湖南省怀化市芷江侗族自治县');
INSERT INTO `city` VALUES (2116, '431229', '靖州苗族侗族自治县', '431200', '湖南省怀化市靖州苗族侗族自治县');
INSERT INTO `city` VALUES (2117, '431230', '通道侗族自治县', '431200', '湖南省怀化市通道侗族自治县');
INSERT INTO `city` VALUES (2118, '431281', '洪江市', '431200', '湖南省怀化市洪江市');
INSERT INTO `city` VALUES (2119, '431300', '娄底市', '430000', '湖南省娄底市');
INSERT INTO `city` VALUES (2120, '431301', '市辖区', '431300', '湖南省娄底市市辖区');
INSERT INTO `city` VALUES (2121, '431302', '娄星区', '431301', NULL);
INSERT INTO `city` VALUES (2122, '431321', '双峰县', '431300', '湖南省娄底市双峰县');
INSERT INTO `city` VALUES (2123, '431322', '新化县', '431300', '湖南省娄底市新化县');
INSERT INTO `city` VALUES (2124, '431381', '冷水江市', '431300', '湖南省娄底市冷水江市');
INSERT INTO `city` VALUES (2125, '431382', '涟源市', '431300', '湖南省娄底市涟源市');
INSERT INTO `city` VALUES (2126, '433100', '湘西土家族苗族自治州', '430000', '湖南省湘西土家族苗族自治州');
INSERT INTO `city` VALUES (2127, '433101', '吉首市', '433100', '湖南省湘西土家族苗族自治州吉首市');
INSERT INTO `city` VALUES (2128, '433122', '泸溪县', '433100', '湖南省湘西土家族苗族自治州泸溪县');
INSERT INTO `city` VALUES (2129, '433123', '凤凰县', '433100', '湖南省湘西土家族苗族自治州凤凰县');
INSERT INTO `city` VALUES (2130, '433124', '花垣县', '433100', '湖南省湘西土家族苗族自治州花垣县');
INSERT INTO `city` VALUES (2131, '433125', '保靖县', '433100', '湖南省湘西土家族苗族自治州保靖县');
INSERT INTO `city` VALUES (2132, '433126', '古丈县', '433100', '湖南省湘西土家族苗族自治州古丈县');
INSERT INTO `city` VALUES (2133, '433127', '永顺县', '433100', '湖南省湘西土家族苗族自治州永顺县');
INSERT INTO `city` VALUES (2134, '433130', '龙山县', '433100', '湖南省湘西土家族苗族自治州龙山县');
INSERT INTO `city` VALUES (2135, '440000', '广东省', '0', '广东省');
INSERT INTO `city` VALUES (2136, '440100', '广州市', '440000', '广东省广州市');
INSERT INTO `city` VALUES (2137, '440101', '市辖区', '440100', '广东省广州市市辖区');
INSERT INTO `city` VALUES (2138, '440103', '荔湾区', '440100', '广东省广州市荔湾区');
INSERT INTO `city` VALUES (2139, '440104', '越秀区', '440100', '广东省广州市越秀区');
INSERT INTO `city` VALUES (2140, '440105', '海珠区', '440100', '广东省广州市海珠区');
INSERT INTO `city` VALUES (2141, '440106', '天河区', '440100', '广东省广州市天河区');
INSERT INTO `city` VALUES (2142, '440111', '白云区', '440100', '广东省广州市白云区');
INSERT INTO `city` VALUES (2143, '440112', '黄埔区', '440100', '广东省广州市黄埔区');
INSERT INTO `city` VALUES (2144, '440113', '番禺区', '440100', '广东省广州市番禺区');
INSERT INTO `city` VALUES (2145, '440114', '花都区', '440100', '广东省广州市花都区');
INSERT INTO `city` VALUES (2146, '440115', '南沙区', '440100', '广东省广州市南沙区');
INSERT INTO `city` VALUES (2147, '440116', '萝岗区', '440100', '广东省广州市萝岗区');
INSERT INTO `city` VALUES (2148, '440183', '增城市', '440100', '广东省广州市增城市');
INSERT INTO `city` VALUES (2149, '440184', '从化市', '440100', '广东省广州市从化市');
INSERT INTO `city` VALUES (2150, '440200', '韶关市', '440000', '广东省韶关市');
INSERT INTO `city` VALUES (2151, '440201', '市辖区', '440200', '广东省韶关市市辖区');
INSERT INTO `city` VALUES (2152, '440203', '武江区', '440201', NULL);
INSERT INTO `city` VALUES (2153, '440204', '浈江区', '440201', NULL);
INSERT INTO `city` VALUES (2154, '440205', '曲江区', '440201', NULL);
INSERT INTO `city` VALUES (2155, '440222', '始兴县', '440200', '广东省韶关市始兴县');
INSERT INTO `city` VALUES (2156, '440224', '仁化县', '440200', '广东省韶关市仁化县');
INSERT INTO `city` VALUES (2157, '440229', '翁源县', '440200', '广东省韶关市翁源县');
INSERT INTO `city` VALUES (2158, '440232', '乳源瑶族自治县', '440200', '广东省韶关市乳源瑶族自治县');
INSERT INTO `city` VALUES (2159, '440233', '新丰县', '440200', '广东省韶关市新丰县');
INSERT INTO `city` VALUES (2160, '440281', '乐昌市', '440200', '广东省韶关市乐昌市');
INSERT INTO `city` VALUES (2161, '440282', '南雄市', '440200', '广东省韶关市南雄市');
INSERT INTO `city` VALUES (2162, '440300', '深圳市', '440000', '广东省深圳市');
INSERT INTO `city` VALUES (2163, '440301', '市辖区', '440300', '广东省深圳市市辖区');
INSERT INTO `city` VALUES (2164, '440303', '罗湖区', '440300', '广东省深圳市罗湖区');
INSERT INTO `city` VALUES (2165, '440304', '福田区', '440300', '广东省深圳市福田区');
INSERT INTO `city` VALUES (2166, '440305', '南山区', '440300', '广东省深圳市南山区');
INSERT INTO `city` VALUES (2167, '440306', '宝安区', '440300', '广东省深圳市宝安区');
INSERT INTO `city` VALUES (2168, '440307', '龙岗区', '440300', '广东省深圳市龙岗区');
INSERT INTO `city` VALUES (2169, '440308', '盐田区', '440300', '广东省深圳市盐田区');
INSERT INTO `city` VALUES (2170, '440400', '珠海市', '440000', '广东省珠海市');
INSERT INTO `city` VALUES (2171, '440401', '市辖区', '440400', '广东省珠海市市辖区');
INSERT INTO `city` VALUES (2172, '440402', '香洲区', '440400', '广东省珠海市香洲区');
INSERT INTO `city` VALUES (2173, '440403', '斗门区', '440400', '广东省珠海市斗门区');
INSERT INTO `city` VALUES (2174, '440404', '金湾区', '440400', '广东省珠海市金湾区');
INSERT INTO `city` VALUES (2175, '440500', '汕头市', '440000', '广东省汕头市');
INSERT INTO `city` VALUES (2176, '440501', '市辖区', '440500', '广东省汕头市市辖区');
INSERT INTO `city` VALUES (2177, '440507', '龙湖区', '440501', NULL);
INSERT INTO `city` VALUES (2178, '440511', '金平区', '440501', NULL);
INSERT INTO `city` VALUES (2179, '440512', '濠江区', '440501', NULL);
INSERT INTO `city` VALUES (2180, '440513', '潮阳区', '440501', NULL);
INSERT INTO `city` VALUES (2181, '440514', '潮南区', '440501', NULL);
INSERT INTO `city` VALUES (2182, '440515', '澄海区', '440501', NULL);
INSERT INTO `city` VALUES (2183, '440523', '南澳县', '440500', '广东省汕头市南澳县');
INSERT INTO `city` VALUES (2184, '440600', '佛山市', '440000', '广东省佛山市');
INSERT INTO `city` VALUES (2185, '440601', '市辖区', '440600', '广东省佛山市市辖区');
INSERT INTO `city` VALUES (2186, '440604', '禅城区', '440600', '广东省佛山市禅城区');
INSERT INTO `city` VALUES (2187, '440605', '南海区', '440600', '广东省佛山市南海区');
INSERT INTO `city` VALUES (2188, '440606', '顺德区', '440600', '广东省佛山市顺德区');
INSERT INTO `city` VALUES (2189, '440607', '三水区', '440600', '广东省佛山市三水区');
INSERT INTO `city` VALUES (2190, '440608', '高明区', '440600', '广东省佛山市高明区');
INSERT INTO `city` VALUES (2191, '440700', '江门市', '440000', '广东省江门市');
INSERT INTO `city` VALUES (2192, '440701', '市辖区', '440700', '广东省江门市市辖区');
INSERT INTO `city` VALUES (2193, '440703', '蓬江区', '440701', NULL);
INSERT INTO `city` VALUES (2194, '440704', '江海区', '440701', NULL);
INSERT INTO `city` VALUES (2195, '440705', '新会区', '440701', NULL);
INSERT INTO `city` VALUES (2196, '440781', '台山市', '440700', '广东省江门市台山市');
INSERT INTO `city` VALUES (2197, '440783', '开平市', '440700', '广东省江门市开平市');
INSERT INTO `city` VALUES (2198, '440784', '鹤山市', '440700', '广东省江门市鹤山市');
INSERT INTO `city` VALUES (2199, '440785', '恩平市', '440700', '广东省江门市恩平市');
INSERT INTO `city` VALUES (2200, '440800', '湛江市', '440000', '广东省湛江市');
INSERT INTO `city` VALUES (2201, '440801', '市辖区', '440800', '广东省湛江市市辖区');
INSERT INTO `city` VALUES (2202, '440802', '赤坎区', '440801', NULL);
INSERT INTO `city` VALUES (2203, '440803', '霞山区', '440801', NULL);
INSERT INTO `city` VALUES (2204, '440804', '坡头区', '440801', NULL);
INSERT INTO `city` VALUES (2205, '440811', '麻章区', '440801', NULL);
INSERT INTO `city` VALUES (2206, '440823', '遂溪县', '440800', '广东省湛江市遂溪县');
INSERT INTO `city` VALUES (2207, '440825', '徐闻县', '440800', '广东省湛江市徐闻县');
INSERT INTO `city` VALUES (2208, '440881', '廉江市', '440800', '广东省湛江市廉江市');
INSERT INTO `city` VALUES (2209, '440882', '雷州市', '440800', '广东省湛江市雷州市');
INSERT INTO `city` VALUES (2210, '440883', '吴川市', '440800', '广东省湛江市吴川市');
INSERT INTO `city` VALUES (2211, '440900', '茂名市', '440000', '广东省茂名市');
INSERT INTO `city` VALUES (2212, '440901', '市辖区', '440900', '广东省茂名市市辖区');
INSERT INTO `city` VALUES (2213, '440902', '茂南区', '440901', NULL);
INSERT INTO `city` VALUES (2214, '440903', '茂港区', '440901', NULL);
INSERT INTO `city` VALUES (2215, '440923', '电白县', '440900', '广东省茂名市电白县');
INSERT INTO `city` VALUES (2216, '440981', '高州市', '440900', '广东省茂名市高州市');
INSERT INTO `city` VALUES (2217, '440982', '化州市', '440900', '广东省茂名市化州市');
INSERT INTO `city` VALUES (2218, '440983', '信宜市', '440900', '广东省茂名市信宜市');
INSERT INTO `city` VALUES (2219, '441200', '肇庆市', '440000', '广东省肇庆市');
INSERT INTO `city` VALUES (2220, '441201', '市辖区', '441200', '广东省肇庆市市辖区');
INSERT INTO `city` VALUES (2221, '441202', '端州区', '441201', NULL);
INSERT INTO `city` VALUES (2222, '441203', '鼎湖区', '441201', NULL);
INSERT INTO `city` VALUES (2223, '441223', '广宁县', '441200', '广东省肇庆市广宁县');
INSERT INTO `city` VALUES (2224, '441224', '怀集县', '441200', '广东省肇庆市怀集县');
INSERT INTO `city` VALUES (2225, '441225', '封开县', '441200', '广东省肇庆市封开县');
INSERT INTO `city` VALUES (2226, '441226', '德庆县', '441200', '广东省肇庆市德庆县');
INSERT INTO `city` VALUES (2227, '441283', '高要市', '441200', '广东省肇庆市高要市');
INSERT INTO `city` VALUES (2228, '441284', '四会市', '441200', '广东省肇庆市四会市');
INSERT INTO `city` VALUES (2229, '441300', '惠州市', '440000', '广东省惠州市');
INSERT INTO `city` VALUES (2230, '441301', '市辖区', '441300', '广东省惠州市市辖区');
INSERT INTO `city` VALUES (2231, '441302', '惠城区', '441300', '广东省惠州市惠城区');
INSERT INTO `city` VALUES (2232, '441303', '惠阳区', '441300', '广东省惠州市惠阳区');
INSERT INTO `city` VALUES (2233, '441322', '博罗县', '441300', '广东省惠州市博罗县');
INSERT INTO `city` VALUES (2234, '441323', '惠东县', '441300', '广东省惠州市惠东县');
INSERT INTO `city` VALUES (2235, '441324', '龙门县', '441300', '广东省惠州市龙门县');
INSERT INTO `city` VALUES (2236, '441400', '梅州市', '440000', '广东省梅州市');
INSERT INTO `city` VALUES (2237, '441401', '市辖区', '441400', '广东省梅州市市辖区');
INSERT INTO `city` VALUES (2238, '441402', '梅江区', '441401', NULL);
INSERT INTO `city` VALUES (2239, '441421', '梅县', '441400', '广东省梅州市梅县');
INSERT INTO `city` VALUES (2240, '441422', '大埔县', '441400', '广东省梅州市大埔县');
INSERT INTO `city` VALUES (2241, '441423', '丰顺县', '441400', '广东省梅州市丰顺县');
INSERT INTO `city` VALUES (2242, '441424', '五华县', '441400', '广东省梅州市五华县');
INSERT INTO `city` VALUES (2243, '441426', '平远县', '441400', '广东省梅州市平远县');
INSERT INTO `city` VALUES (2244, '441427', '蕉岭县', '441400', '广东省梅州市蕉岭县');
INSERT INTO `city` VALUES (2245, '441481', '兴宁市', '441400', '广东省梅州市兴宁市');
INSERT INTO `city` VALUES (2246, '441500', '汕尾市', '440000', '广东省汕尾市');
INSERT INTO `city` VALUES (2247, '441501', '市辖区', '441500', '广东省汕尾市市辖区');
INSERT INTO `city` VALUES (2248, '441502', '城区', '441501', NULL);
INSERT INTO `city` VALUES (2249, '441521', '海丰县', '441500', '广东省汕尾市海丰县');
INSERT INTO `city` VALUES (2250, '441523', '陆河县', '441500', '广东省汕尾市陆河县');
INSERT INTO `city` VALUES (2251, '441581', '陆丰市', '441500', '广东省汕尾市陆丰市');
INSERT INTO `city` VALUES (2252, '441600', '河源市', '440000', '广东省河源市');
INSERT INTO `city` VALUES (2253, '441601', '市辖区', '441600', '广东省河源市市辖区');
INSERT INTO `city` VALUES (2254, '441602', '源城区', '441601', NULL);
INSERT INTO `city` VALUES (2255, '441621', '紫金县', '441600', '广东省河源市紫金县');
INSERT INTO `city` VALUES (2256, '441622', '龙川县', '441600', '广东省河源市龙川县');
INSERT INTO `city` VALUES (2257, '441623', '连平县', '441600', '广东省河源市连平县');
INSERT INTO `city` VALUES (2258, '441624', '和平县', '441600', '广东省河源市和平县');
INSERT INTO `city` VALUES (2259, '441625', '东源县', '441600', '广东省河源市东源县');
INSERT INTO `city` VALUES (2260, '441700', '阳江市', '440000', '广东省阳江市');
INSERT INTO `city` VALUES (2261, '441701', '市辖区', '441700', '广东省阳江市市辖区');
INSERT INTO `city` VALUES (2262, '441702', '江城区', '441701', NULL);
INSERT INTO `city` VALUES (2263, '441721', '阳西县', '441700', '广东省阳江市阳西县');
INSERT INTO `city` VALUES (2264, '441723', '阳东县', '441700', '广东省阳江市阳东县');
INSERT INTO `city` VALUES (2265, '441781', '阳春市', '441700', '广东省阳江市阳春市');
INSERT INTO `city` VALUES (2266, '441800', '清远市', '440000', '广东省清远市');
INSERT INTO `city` VALUES (2267, '441801', '市辖区', '441800', '广东省清远市市辖区');
INSERT INTO `city` VALUES (2268, '441802', '清城区', '441801', NULL);
INSERT INTO `city` VALUES (2269, '441821', '佛冈县', '441800', '广东省清远市佛冈县');
INSERT INTO `city` VALUES (2270, '441823', '阳山县', '441800', '广东省清远市阳山县');
INSERT INTO `city` VALUES (2271, '441825', '连山壮族瑶族自治县', '441800', '广东省清远市连山壮族瑶族自治县');
INSERT INTO `city` VALUES (2272, '441826', '连南瑶族自治县', '441800', '广东省清远市连南瑶族自治县');
INSERT INTO `city` VALUES (2273, '441827', '清新县', '441800', '广东省清远市清新县');
INSERT INTO `city` VALUES (2274, '441881', '英德市', '441800', '广东省清远市英德市');
INSERT INTO `city` VALUES (2275, '441882', '连州市', '441800', '广东省清远市连州市');
INSERT INTO `city` VALUES (2276, '441900', '东莞市', '440000', '广东省东莞市');
INSERT INTO `city` VALUES (2277, '442000', '中山市', '440000', '广东省中山市');
INSERT INTO `city` VALUES (2278, '445100', '潮州市', '440000', '广东省潮州市');
INSERT INTO `city` VALUES (2279, '445101', '市辖区', '445100', '广东省潮州市市辖区');
INSERT INTO `city` VALUES (2280, '445102', '湘桥区', '445101', NULL);
INSERT INTO `city` VALUES (2281, '445121', '潮安区', '445100', '广东省潮州市潮安区');
INSERT INTO `city` VALUES (2282, '445122', '饶平县', '445100', '广东省潮州市饶平县');
INSERT INTO `city` VALUES (2283, '445200', '揭阳市', '440000', '广东省揭阳市');
INSERT INTO `city` VALUES (2284, '445201', '市辖区', '445200', '广东省揭阳市市辖区');
INSERT INTO `city` VALUES (2285, '445202', '榕城区', '445201', NULL);
INSERT INTO `city` VALUES (2286, '445221', '揭东县', '445200', '广东省揭阳市揭东县');
INSERT INTO `city` VALUES (2287, '445222', '揭西县', '445200', '广东省揭阳市揭西县');
INSERT INTO `city` VALUES (2288, '445224', '惠来县', '445200', '广东省揭阳市惠来县');
INSERT INTO `city` VALUES (2289, '445281', '普宁市', '445200', '广东省揭阳市普宁市');
INSERT INTO `city` VALUES (2290, '445300', '云浮市', '440000', '广东省云浮市');
INSERT INTO `city` VALUES (2291, '445301', '市辖区', '445300', '广东省云浮市市辖区');
INSERT INTO `city` VALUES (2292, '445302', '云城区', '445301', NULL);
INSERT INTO `city` VALUES (2293, '445321', '新兴县', '445300', '广东省云浮市新兴县');
INSERT INTO `city` VALUES (2294, '445322', '郁南县', '445300', '广东省云浮市郁南县');
INSERT INTO `city` VALUES (2295, '445323', '云安县', '445300', '广东省云浮市云安县');
INSERT INTO `city` VALUES (2296, '445381', '罗定市', '445300', '广东省云浮市罗定市');
INSERT INTO `city` VALUES (2297, '450000', '广西壮族自治区', '0', '广西壮族自治区');
INSERT INTO `city` VALUES (2298, '450100', '南宁市', '450000', '广西壮族自治区南宁市');
INSERT INTO `city` VALUES (2299, '450101', '市辖区', '450100', '广西壮族自治区南宁市市辖区');
INSERT INTO `city` VALUES (2300, '450102', '兴宁区', '450101', NULL);
INSERT INTO `city` VALUES (2301, '450103', '青秀区', '450101', NULL);
INSERT INTO `city` VALUES (2302, '450105', '江南区', '450101', NULL);
INSERT INTO `city` VALUES (2303, '450107', '西乡塘区', '450101', NULL);
INSERT INTO `city` VALUES (2304, '450108', '良庆区', '450101', NULL);
INSERT INTO `city` VALUES (2305, '450109', '邕宁区', '450101', NULL);
INSERT INTO `city` VALUES (2306, '450122', '武鸣县', '450100', '广西壮族自治区南宁市武鸣县');
INSERT INTO `city` VALUES (2307, '450123', '隆安县', '450100', '广西壮族自治区南宁市隆安县');
INSERT INTO `city` VALUES (2308, '450124', '马山县', '450100', '广西壮族自治区南宁市马山县');
INSERT INTO `city` VALUES (2309, '450125', '上林县', '450100', '广西壮族自治区南宁市上林县');
INSERT INTO `city` VALUES (2310, '450126', '宾阳县', '450100', '广西壮族自治区南宁市宾阳县');
INSERT INTO `city` VALUES (2311, '450127', '横县', '450100', '广西壮族自治区南宁市横县');
INSERT INTO `city` VALUES (2312, '450200', '柳州市', '450000', '广西壮族自治区柳州市');
INSERT INTO `city` VALUES (2313, '450201', '市辖区', '450200', '广西壮族自治区柳州市市辖区');
INSERT INTO `city` VALUES (2314, '450202', '城中区', '450201', NULL);
INSERT INTO `city` VALUES (2315, '450203', '鱼峰区', '450201', NULL);
INSERT INTO `city` VALUES (2316, '450204', '柳南区', '450201', NULL);
INSERT INTO `city` VALUES (2317, '450205', '柳北区', '450201', NULL);
INSERT INTO `city` VALUES (2318, '450221', '柳江县', '450200', '广西壮族自治区柳州市柳江县');
INSERT INTO `city` VALUES (2319, '450222', '柳城县', '450200', '广西壮族自治区柳州市柳城县');
INSERT INTO `city` VALUES (2320, '450223', '鹿寨县', '450200', '广西壮族自治区柳州市鹿寨县');
INSERT INTO `city` VALUES (2321, '450224', '融安县', '450200', '广西壮族自治区柳州市融安县');
INSERT INTO `city` VALUES (2322, '450225', '融水苗族自治县', '450200', '广西壮族自治区柳州市融水苗族自治县');
INSERT INTO `city` VALUES (2323, '450226', '三江侗族自治县', '450200', '广西壮族自治区柳州市三江侗族自治县');
INSERT INTO `city` VALUES (2324, '450300', '桂林市', '450000', '广西壮族自治区桂林市');
INSERT INTO `city` VALUES (2325, '450301', '市辖区', '450300', '广西壮族自治区桂林市市辖区');
INSERT INTO `city` VALUES (2326, '450302', '秀峰区', '450301', NULL);
INSERT INTO `city` VALUES (2327, '450303', '叠彩区', '450301', NULL);
INSERT INTO `city` VALUES (2328, '450304', '象山区', '450301', NULL);
INSERT INTO `city` VALUES (2329, '450305', '七星区', '450301', NULL);
INSERT INTO `city` VALUES (2330, '450311', '雁山区', '450301', NULL);
INSERT INTO `city` VALUES (2331, '450321', '阳朔县', '450300', '广西壮族自治区桂林市阳朔县');
INSERT INTO `city` VALUES (2332, '450322', '临桂区', '450300', '广西壮族自治区桂林市临桂区');
INSERT INTO `city` VALUES (2333, '450323', '灵川县', '450300', '广西壮族自治区桂林市灵川县');
INSERT INTO `city` VALUES (2334, '450324', '全州县', '450300', '广西壮族自治区桂林市全州县');
INSERT INTO `city` VALUES (2335, '450325', '兴安县', '450300', '广西壮族自治区桂林市兴安县');
INSERT INTO `city` VALUES (2336, '450326', '永福县', '450300', '广西壮族自治区桂林市永福县');
INSERT INTO `city` VALUES (2337, '450327', '灌阳县', '450300', '广西壮族自治区桂林市灌阳县');
INSERT INTO `city` VALUES (2338, '450328', '龙胜各族自治县', '450300', '广西壮族自治区桂林市龙胜各族自治县');
INSERT INTO `city` VALUES (2339, '450329', '资源县', '450300', '广西壮族自治区桂林市资源县');
INSERT INTO `city` VALUES (2340, '450330', '平乐县', '450300', '广西壮族自治区桂林市平乐县');
INSERT INTO `city` VALUES (2341, '450331', '荔浦县', '450300', '广西壮族自治区桂林市荔浦县');
INSERT INTO `city` VALUES (2342, '450332', '恭城瑶族自治县', '450300', '广西壮族自治区桂林市恭城瑶族自治县');
INSERT INTO `city` VALUES (2343, '450400', '梧州市', '450000', '广西壮族自治区梧州市');
INSERT INTO `city` VALUES (2344, '450401', '市辖区', '450400', '广西壮族自治区梧州市市辖区');
INSERT INTO `city` VALUES (2345, '450403', '万秀区', '450401', NULL);
INSERT INTO `city` VALUES (2346, '450404', '蝶山区', '450401', NULL);
INSERT INTO `city` VALUES (2347, '450405', '长洲区', '450401', NULL);
INSERT INTO `city` VALUES (2348, '450421', '苍梧县', '450400', '广西壮族自治区梧州市苍梧县');
INSERT INTO `city` VALUES (2349, '450422', '藤县', '450400', '广西壮族自治区梧州市藤县');
INSERT INTO `city` VALUES (2350, '450423', '蒙山县', '450400', '广西壮族自治区梧州市蒙山县');
INSERT INTO `city` VALUES (2351, '450481', '岑溪市', '450400', '广西壮族自治区梧州市岑溪市');
INSERT INTO `city` VALUES (2352, '450500', '北海市', '450000', '广西壮族自治区北海市');
INSERT INTO `city` VALUES (2353, '450501', '市辖区', '450500', '广西壮族自治区北海市市辖区');
INSERT INTO `city` VALUES (2354, '450502', '海城区', '450501', NULL);
INSERT INTO `city` VALUES (2355, '450503', '银海区', '450501', NULL);
INSERT INTO `city` VALUES (2356, '450512', '铁山港区', '450501', NULL);
INSERT INTO `city` VALUES (2357, '450521', '合浦县', '450500', '广西壮族自治区北海市合浦县');
INSERT INTO `city` VALUES (2358, '450600', '防城港市', '450000', '广西壮族自治区防城港市');
INSERT INTO `city` VALUES (2359, '450601', '市辖区', '450600', '广西壮族自治区防城港市市辖区');
INSERT INTO `city` VALUES (2360, '450602', '港口区', '450601', NULL);
INSERT INTO `city` VALUES (2361, '450603', '防城区', '450601', NULL);
INSERT INTO `city` VALUES (2362, '450621', '上思县', '450600', '广西壮族自治区防城港市上思县');
INSERT INTO `city` VALUES (2363, '450681', '东兴市', '450600', '广西壮族自治区防城港市东兴市');
INSERT INTO `city` VALUES (2364, '450700', '钦州市', '450000', '广西壮族自治区钦州市');
INSERT INTO `city` VALUES (2365, '450701', '市辖区', '450700', '广西壮族自治区钦州市市辖区');
INSERT INTO `city` VALUES (2366, '450702', '钦南区', '450700', '广西壮族自治区钦州市钦南区');
INSERT INTO `city` VALUES (2367, '450703', '钦北区', '450700', '广西壮族自治区钦州市钦北区');
INSERT INTO `city` VALUES (2368, '450721', '灵山县', '450700', '广西壮族自治区钦州市灵山县');
INSERT INTO `city` VALUES (2369, '450722', '浦北县', '450700', '广西壮族自治区钦州市浦北县');
INSERT INTO `city` VALUES (2370, '450800', '贵港市', '450000', '广西壮族自治区贵港市');
INSERT INTO `city` VALUES (2371, '450801', '市辖区', '450800', '广西壮族自治区贵港市市辖区');
INSERT INTO `city` VALUES (2372, '450802', '港北区', '450801', NULL);
INSERT INTO `city` VALUES (2373, '450803', '港南区', '450801', NULL);
INSERT INTO `city` VALUES (2374, '450804', '覃塘区', '450801', NULL);
INSERT INTO `city` VALUES (2375, '450821', '平南县', '450800', '广西壮族自治区贵港市平南县');
INSERT INTO `city` VALUES (2376, '450881', '桂平市', '450800', '广西壮族自治区贵港市桂平市');
INSERT INTO `city` VALUES (2377, '450900', '玉林市', '450000', '广西壮族自治区玉林市');
INSERT INTO `city` VALUES (2378, '450901', '市辖区', '450900', '广西壮族自治区玉林市市辖区');
INSERT INTO `city` VALUES (2379, '450902', '玉州区', '450901', NULL);
INSERT INTO `city` VALUES (2380, '450921', '容县', '450900', '广西壮族自治区玉林市容县');
INSERT INTO `city` VALUES (2381, '450922', '陆川县', '450900', '广西壮族自治区玉林市陆川县');
INSERT INTO `city` VALUES (2382, '450923', '博白县', '450900', '广西壮族自治区玉林市博白县');
INSERT INTO `city` VALUES (2383, '450924', '兴业县', '450900', '广西壮族自治区玉林市兴业县');
INSERT INTO `city` VALUES (2384, '450981', '北流市', '450900', '广西壮族自治区玉林市北流市');
INSERT INTO `city` VALUES (2385, '451000', '百色市', '450000', '广西壮族自治区百色市');
INSERT INTO `city` VALUES (2386, '451001', '市辖区', '451000', '广西壮族自治区百色市市辖区');
INSERT INTO `city` VALUES (2387, '451002', '右江区', '451001', NULL);
INSERT INTO `city` VALUES (2388, '451021', '田阳县', '451000', '广西壮族自治区百色市田阳县');
INSERT INTO `city` VALUES (2389, '451022', '田东县', '451000', '广西壮族自治区百色市田东县');
INSERT INTO `city` VALUES (2390, '451023', '平果县', '451000', '广西壮族自治区百色市平果县');
INSERT INTO `city` VALUES (2391, '451024', '德保县', '451000', '广西壮族自治区百色市德保县');
INSERT INTO `city` VALUES (2392, '451025', '靖西县', '451000', '广西壮族自治区百色市靖西县');
INSERT INTO `city` VALUES (2393, '451026', '那坡县', '451000', '广西壮族自治区百色市那坡县');
INSERT INTO `city` VALUES (2394, '451027', '凌云县', '451000', '广西壮族自治区百色市凌云县');
INSERT INTO `city` VALUES (2395, '451028', '乐业县', '451000', '广西壮族自治区百色市乐业县');
INSERT INTO `city` VALUES (2396, '451029', '田林县', '451000', '广西壮族自治区百色市田林县');
INSERT INTO `city` VALUES (2397, '451030', '西林县', '451000', '广西壮族自治区百色市西林县');
INSERT INTO `city` VALUES (2398, '451031', '隆林各族自治县', '451000', '广西壮族自治区百色市隆林各族自治县');
INSERT INTO `city` VALUES (2399, '451100', '贺州市', '450000', '广西壮族自治区贺州市');
INSERT INTO `city` VALUES (2400, '451101', '市辖区', '451100', '广西壮族自治区贺州市市辖区');
INSERT INTO `city` VALUES (2401, '451102', '八步区', '451101', NULL);
INSERT INTO `city` VALUES (2402, '451121', '昭平县', '451100', '广西壮族自治区贺州市昭平县');
INSERT INTO `city` VALUES (2403, '451122', '钟山县', '451100', '广西壮族自治区贺州市钟山县');
INSERT INTO `city` VALUES (2404, '451123', '富川瑶族自治县', '451100', '广西壮族自治区贺州市富川瑶族自治县');
INSERT INTO `city` VALUES (2405, '451200', '河池市', '450000', '广西壮族自治区河池市');
INSERT INTO `city` VALUES (2406, '451201', '市辖区', '451200', '广西壮族自治区河池市市辖区');
INSERT INTO `city` VALUES (2407, '451202', '金城江区', '451201', NULL);
INSERT INTO `city` VALUES (2408, '451221', '南丹县', '451200', '广西壮族自治区河池市南丹县');
INSERT INTO `city` VALUES (2409, '451222', '天峨县', '451200', '广西壮族自治区河池市天峨县');
INSERT INTO `city` VALUES (2410, '451223', '凤山县', '451200', '广西壮族自治区河池市凤山县');
INSERT INTO `city` VALUES (2411, '451224', '东兰县', '451200', '广西壮族自治区河池市东兰县');
INSERT INTO `city` VALUES (2412, '451225', '罗城仫佬族自治县', '451200', '广西壮族自治区河池市罗城仫佬族自治县');
INSERT INTO `city` VALUES (2413, '451226', '环江毛南族自治县', '451200', '广西壮族自治区河池市环江毛南族自治县');
INSERT INTO `city` VALUES (2414, '451227', '巴马瑶族自治县', '451200', '广西壮族自治区河池市巴马瑶族自治县');
INSERT INTO `city` VALUES (2415, '451228', '都安瑶族自治县', '451200', '广西壮族自治区河池市都安瑶族自治县');
INSERT INTO `city` VALUES (2416, '451229', '大化瑶族自治县', '451200', '广西壮族自治区河池市大化瑶族自治县');
INSERT INTO `city` VALUES (2417, '451281', '宜州市', '451200', '广西壮族自治区河池市宜州市');
INSERT INTO `city` VALUES (2418, '451300', '来宾市', '450000', '广西壮族自治区来宾市');
INSERT INTO `city` VALUES (2419, '451301', '市辖区', '451300', '广西壮族自治区来宾市市辖区');
INSERT INTO `city` VALUES (2420, '451302', '兴宾区', '451301', NULL);
INSERT INTO `city` VALUES (2421, '451321', '忻城县', '451300', '广西壮族自治区来宾市忻城县');
INSERT INTO `city` VALUES (2422, '451322', '象州县', '451300', '广西壮族自治区来宾市象州县');
INSERT INTO `city` VALUES (2423, '451323', '武宣县', '451300', '广西壮族自治区来宾市武宣县');
INSERT INTO `city` VALUES (2424, '451324', '金秀瑶族自治县', '451300', '广西壮族自治区来宾市金秀瑶族自治县');
INSERT INTO `city` VALUES (2425, '451381', '合山市', '451300', '广西壮族自治区来宾市合山市');
INSERT INTO `city` VALUES (2426, '451400', '崇左市', '450000', '广西壮族自治区崇左市');
INSERT INTO `city` VALUES (2427, '451401', '市辖区', '451400', '广西壮族自治区崇左市市辖区');
INSERT INTO `city` VALUES (2428, '451402', '江洲区', '451401', NULL);
INSERT INTO `city` VALUES (2429, '451421', '扶绥县', '451400', '广西壮族自治区崇左市扶绥县');
INSERT INTO `city` VALUES (2430, '451422', '宁明县', '451400', '广西壮族自治区崇左市宁明县');
INSERT INTO `city` VALUES (2431, '451423', '龙州县', '451400', '广西壮族自治区崇左市龙州县');
INSERT INTO `city` VALUES (2432, '451424', '大新县', '451400', '广西壮族自治区崇左市大新县');
INSERT INTO `city` VALUES (2433, '451425', '天等县', '451400', '广西壮族自治区崇左市天等县');
INSERT INTO `city` VALUES (2434, '451481', '凭祥市', '451400', '广西壮族自治区崇左市凭祥市');
INSERT INTO `city` VALUES (2435, '460000', '海南省', '0', '海南省');
INSERT INTO `city` VALUES (2436, '460100', '海口市', '460000', '海南省海口市');
INSERT INTO `city` VALUES (2437, '460101', '市辖区', '460100', '海南省海口市市辖区');
INSERT INTO `city` VALUES (2438, '460105', '秀英区', '460101', NULL);
INSERT INTO `city` VALUES (2439, '460106', '龙华区', '460101', NULL);
INSERT INTO `city` VALUES (2440, '460107', '琼山区', '460101', NULL);
INSERT INTO `city` VALUES (2441, '460108', '美兰区', '460101', NULL);
INSERT INTO `city` VALUES (2442, '460200', '三亚市', '460000', '海南省三亚市');
INSERT INTO `city` VALUES (2443, '460201', '市辖区', '460200', '海南省三亚市市辖区');
INSERT INTO `city` VALUES (2444, '469000', '省直辖县级行政单位', '460000', '海南省省直辖县级行政单位');
INSERT INTO `city` VALUES (2445, '469001', '五指山市', '469000', '海南省省直辖县级行政单位五指山市');
INSERT INTO `city` VALUES (2446, '469002', '琼海市', '469000', '海南省省直辖县级行政单位琼海市');
INSERT INTO `city` VALUES (2447, '469003', '儋州市', '469000', '海南省省直辖县级行政单位儋州市');
INSERT INTO `city` VALUES (2448, '469005', '文昌市', '469000', '海南省省直辖县级行政单位文昌市');
INSERT INTO `city` VALUES (2449, '469006', '万宁市', '469000', '海南省省直辖县级行政单位万宁市');
INSERT INTO `city` VALUES (2450, '469007', '东方市', '469000', '海南省省直辖县级行政单位东方市');
INSERT INTO `city` VALUES (2451, '469025', '定安县', '469000', '海南省省直辖县级行政单位定安县');
INSERT INTO `city` VALUES (2452, '469026', '屯昌县', '469000', '海南省省直辖县级行政单位屯昌县');
INSERT INTO `city` VALUES (2453, '469027', '澄迈县', '469000', '海南省省直辖县级行政单位澄迈县');
INSERT INTO `city` VALUES (2454, '469028', '临高县', '469000', '海南省省直辖县级行政单位临高县');
INSERT INTO `city` VALUES (2455, '469030', '白沙黎族自治县', '469000', '海南省省直辖县级行政单位白沙黎族自治县');
INSERT INTO `city` VALUES (2456, '469031', '昌江黎族自治县', '469000', '海南省省直辖县级行政单位昌江黎族自治县');
INSERT INTO `city` VALUES (2457, '469033', '乐东黎族自治县', '469000', '海南省省直辖县级行政单位乐东黎族自治县');
INSERT INTO `city` VALUES (2458, '469034', '陵水黎族自治县', '469000', '海南省省直辖县级行政单位陵水黎族自治县');
INSERT INTO `city` VALUES (2459, '469035', '保亭黎族苗族自治县', '469000', '海南省省直辖县级行政单位保亭黎族苗族自治县');
INSERT INTO `city` VALUES (2460, '469036', '琼中黎族苗族自治县', '469000', '海南省省直辖县级行政单位琼中黎族苗族自治县');
INSERT INTO `city` VALUES (2461, '469037', '西沙群岛', '469000', '海南省省直辖县级行政单位西沙群岛');
INSERT INTO `city` VALUES (2462, '469038', '南沙群岛', '469000', '海南省省直辖县级行政单位南沙群岛');
INSERT INTO `city` VALUES (2463, '469039', '中沙群岛的岛礁及其海域', '469000', '海南省省直辖县级行政单位中沙群岛的岛礁及其海域');
INSERT INTO `city` VALUES (2464, '500000', '重庆市', '0', '重庆市');
INSERT INTO `city` VALUES (2465, '500100', '万州区', '500000', '重庆市万州区');
INSERT INTO `city` VALUES (2466, '500101', '万州区', '500100', '重庆市万州区万州区');
INSERT INTO `city` VALUES (2467, '500200', '涪陵区', '500000', '重庆市涪陵区');
INSERT INTO `city` VALUES (2468, '500201', '涪陵区', '500200', '重庆市涪陵区涪陵区');
INSERT INTO `city` VALUES (2469, '500300', '渝中区', '500000', '重庆市渝中区');
INSERT INTO `city` VALUES (2470, '500301', '渝中区', '500300', '重庆市渝中区渝中区');
INSERT INTO `city` VALUES (2471, '500400', '大渡口区', '500000', '重庆市大渡口区');
INSERT INTO `city` VALUES (2472, '500401', '大渡口区', '500400', '重庆市大渡口区大渡口区');
INSERT INTO `city` VALUES (2473, '500500', '江北区', '500000', '重庆市江北区');
INSERT INTO `city` VALUES (2474, '500501', '江北区', '500500', '重庆市江北区江北区');
INSERT INTO `city` VALUES (2475, '500600', '沙坪坝区', '500000', '重庆市沙坪坝区');
INSERT INTO `city` VALUES (2476, '500601', '沙坪坝区', '500600', '重庆市沙坪坝区沙坪坝区');
INSERT INTO `city` VALUES (2477, '500700', '九龙坡区', '500000', '重庆市九龙坡区');
INSERT INTO `city` VALUES (2478, '500701', '九龙坡区', '500700', '重庆市九龙坡区九龙坡区');
INSERT INTO `city` VALUES (2479, '500800', '南岸区', '500000', '重庆市南岸区');
INSERT INTO `city` VALUES (2480, '500801', '南岸区', '500800', '重庆市南岸区南岸区');
INSERT INTO `city` VALUES (2481, '500900', '北碚区', '500000', '重庆市北碚区');
INSERT INTO `city` VALUES (2482, '500901', '北碚区', '500900', '重庆市北碚区北碚区');
INSERT INTO `city` VALUES (2483, '501000', '万盛区', '500000', '重庆市万盛区');
INSERT INTO `city` VALUES (2484, '501001', '万盛区', '501000', '重庆市万盛区万盛区');
INSERT INTO `city` VALUES (2485, '501100', '双桥区', '500000', '重庆市双桥区');
INSERT INTO `city` VALUES (2486, '501101', '双桥区', '501100', '重庆市双桥区双桥区');
INSERT INTO `city` VALUES (2487, '501200', '渝北区', '500000', '重庆市渝北区');
INSERT INTO `city` VALUES (2488, '501201', '渝北区', '501200', '重庆市渝北区渝北区');
INSERT INTO `city` VALUES (2489, '501300', '巴南区', '500000', '重庆市巴南区');
INSERT INTO `city` VALUES (2490, '501301', '巴南区', '501300', '重庆市巴南区巴南区');
INSERT INTO `city` VALUES (2491, '501400', '黔江区', '500000', '重庆市黔江区');
INSERT INTO `city` VALUES (2492, '501401', '黔江区', '501400', '重庆市黔江区黔江区');
INSERT INTO `city` VALUES (2493, '501500', '长寿区', '500000', '重庆市长寿区');
INSERT INTO `city` VALUES (2494, '501501', '长寿区', '501500', '重庆市长寿区长寿区');
INSERT INTO `city` VALUES (2495, '502200', '綦江区', '500000', '重庆市綦江区');
INSERT INTO `city` VALUES (2496, '502201', '綦江区', '502200', '重庆市綦江区綦江区');
INSERT INTO `city` VALUES (2497, '502300', '潼南县', '500000', '重庆市潼南县');
INSERT INTO `city` VALUES (2498, '502301', '潼南县', '502300', '重庆市潼南县潼南县');
INSERT INTO `city` VALUES (2499, '502400', '铜梁县', '500000', '重庆市铜梁县');
INSERT INTO `city` VALUES (2500, '502401', '铜梁县', '502400', '重庆市铜梁县铜梁县');
INSERT INTO `city` VALUES (2501, '502500', '大足区', '500000', '重庆市大足区');
INSERT INTO `city` VALUES (2502, '502501', '大足区', '502500', '重庆市大足区大足区');
INSERT INTO `city` VALUES (2503, '502600', '荣昌县', '500000', '重庆市荣昌县');
INSERT INTO `city` VALUES (2504, '502601', '荣昌县', '502600', '重庆市荣昌县荣昌县');
INSERT INTO `city` VALUES (2505, '502700', '璧山县', '500000', '重庆市璧山县');
INSERT INTO `city` VALUES (2506, '502701', '璧山县', '502700', '重庆市璧山县璧山县');
INSERT INTO `city` VALUES (2507, '502800', '梁平县', '500000', '重庆市梁平县');
INSERT INTO `city` VALUES (2508, '502801', '梁平县', '502800', '重庆市梁平县梁平县');
INSERT INTO `city` VALUES (2509, '502900', '城口县', '500000', '重庆市城口县');
INSERT INTO `city` VALUES (2510, '502901', '城口县', '502900', '重庆市城口县城口县');
INSERT INTO `city` VALUES (2511, '503000', '丰都县', '500000', '重庆市丰都县');
INSERT INTO `city` VALUES (2512, '503001', '丰都县', '503000', '重庆市丰都县丰都县');
INSERT INTO `city` VALUES (2513, '503100', '垫江县', '500000', '重庆市垫江县');
INSERT INTO `city` VALUES (2514, '503101', '垫江县', '503100', '重庆市垫江县垫江县');
INSERT INTO `city` VALUES (2515, '503200', '武隆县', '500000', '重庆市武隆县');
INSERT INTO `city` VALUES (2516, '503201', '武隆县', '503200', '重庆市武隆县武隆县');
INSERT INTO `city` VALUES (2517, '503300', '忠县', '500000', '重庆市忠县');
INSERT INTO `city` VALUES (2518, '503301', '忠县', '503300', '重庆市忠县忠县');
INSERT INTO `city` VALUES (2519, '503400', '开县', '500000', '重庆市开县');
INSERT INTO `city` VALUES (2520, '503401', '开县', '503400', '重庆市开县开县');
INSERT INTO `city` VALUES (2521, '503500', '云阳县', '500000', '重庆市云阳县');
INSERT INTO `city` VALUES (2522, '503501', '云阳县', '503500', '重庆市云阳县云阳县');
INSERT INTO `city` VALUES (2523, '503600', '奉节县', '500000', '重庆市奉节县');
INSERT INTO `city` VALUES (2524, '503601', '奉节县', '503600', '重庆市奉节县奉节县');
INSERT INTO `city` VALUES (2525, '503700', '巫山县', '500000', '重庆市巫山县');
INSERT INTO `city` VALUES (2526, '503701', '巫山县', '503700', '重庆市巫山县巫山县');
INSERT INTO `city` VALUES (2527, '503800', '巫溪县', '500000', '重庆市巫溪县');
INSERT INTO `city` VALUES (2528, '503801', '巫溪县', '503800', '重庆市巫溪县巫溪县');
INSERT INTO `city` VALUES (2529, '504000', '石柱县', '500000', '重庆市石柱县');
INSERT INTO `city` VALUES (2530, '504001', '石柱县', '504000', '重庆市石柱县石柱县');
INSERT INTO `city` VALUES (2531, '504100', '秀山县', '500000', '重庆市秀山县');
INSERT INTO `city` VALUES (2532, '504101', '秀山县', '504100', '重庆市秀山县秀山县');
INSERT INTO `city` VALUES (2533, '504200', '酉阳县', '500000', '重庆市酉阳县');
INSERT INTO `city` VALUES (2534, '504201', '酉阳县', '504200', '重庆市酉阳县酉阳县');
INSERT INTO `city` VALUES (2535, '504300', '彭水县', '500000', '重庆市彭水县');
INSERT INTO `city` VALUES (2536, '504301', '彭水县', '504300', '重庆市彭水县彭水县');
INSERT INTO `city` VALUES (2537, '508100', '江津区', '500000', '重庆市江津区');
INSERT INTO `city` VALUES (2538, '508101', '江津区', '508100', '重庆市江津区江津区');
INSERT INTO `city` VALUES (2539, '508200', '合川区', '500000', '重庆市合川区');
INSERT INTO `city` VALUES (2540, '508201', '合川区', '508200', '重庆市合川区合川区');
INSERT INTO `city` VALUES (2541, '508300', '永川区', '500000', '重庆市永川区');
INSERT INTO `city` VALUES (2542, '508301', '永川区', '508300', '重庆市永川区永川区');
INSERT INTO `city` VALUES (2543, '508400', '南川区', '500000', '重庆市南川区');
INSERT INTO `city` VALUES (2544, '508401', '南川区', '508400', '重庆市南川区南川区');
INSERT INTO `city` VALUES (2545, '510000', '四川省', '0', '四川省');
INSERT INTO `city` VALUES (2546, '510100', '成都市', '510000', '四川省成都市');
INSERT INTO `city` VALUES (2547, '510101', '市辖区', '510100', '四川省成都市市辖区');
INSERT INTO `city` VALUES (2548, '510104', '锦江区', '510101', NULL);
INSERT INTO `city` VALUES (2549, '510105', '青羊区', '510101', NULL);
INSERT INTO `city` VALUES (2550, '510106', '金牛区', '510101', NULL);
INSERT INTO `city` VALUES (2551, '510107', '武侯区', '510101', NULL);
INSERT INTO `city` VALUES (2552, '510108', '成华区', '510101', NULL);
INSERT INTO `city` VALUES (2553, '510112', '龙泉驿区', '510100', '四川省成都市龙泉驿区');
INSERT INTO `city` VALUES (2554, '510113', '青白江区', '510101', NULL);
INSERT INTO `city` VALUES (2555, '510114', '新都区', '510101', NULL);
INSERT INTO `city` VALUES (2556, '510115', '温江区', '510101', NULL);
INSERT INTO `city` VALUES (2557, '510121', '金堂县', '510100', '四川省成都市金堂县');
INSERT INTO `city` VALUES (2558, '510122', '双流县', '510100', '四川省成都市双流县');
INSERT INTO `city` VALUES (2559, '510124', '郫县', '510100', '四川省成都市郫县');
INSERT INTO `city` VALUES (2560, '510129', '大邑县', '510100', '四川省成都市大邑县');
INSERT INTO `city` VALUES (2561, '510131', '蒲江县', '510100', '四川省成都市蒲江县');
INSERT INTO `city` VALUES (2562, '510132', '新津县', '510100', '四川省成都市新津县');
INSERT INTO `city` VALUES (2563, '510181', '都江堰市', '510100', '四川省成都市都江堰市');
INSERT INTO `city` VALUES (2564, '510182', '彭州市', '510100', '四川省成都市彭州市');
INSERT INTO `city` VALUES (2565, '510183', '邛崃市', '510100', '四川省成都市邛崃市');
INSERT INTO `city` VALUES (2566, '510184', '崇州市', '510100', '四川省成都市崇州市');
INSERT INTO `city` VALUES (2567, '510300', '自贡市', '510000', '四川省自贡市');
INSERT INTO `city` VALUES (2568, '510301', '市辖区', '510300', '四川省自贡市市辖区');
INSERT INTO `city` VALUES (2569, '510302', '自流井区', '510301', NULL);
INSERT INTO `city` VALUES (2570, '510303', '贡井区', '510301', NULL);
INSERT INTO `city` VALUES (2571, '510304', '大安区', '510301', NULL);
INSERT INTO `city` VALUES (2572, '510311', '沿滩区', '510301', NULL);
INSERT INTO `city` VALUES (2573, '510321', '荣县', '510300', '四川省自贡市荣县');
INSERT INTO `city` VALUES (2574, '510322', '富顺县', '510300', '四川省自贡市富顺县');
INSERT INTO `city` VALUES (2575, '510400', '攀枝花市', '510000', '四川省攀枝花市');
INSERT INTO `city` VALUES (2576, '510401', '市辖区', '510400', '四川省攀枝花市市辖区');
INSERT INTO `city` VALUES (2577, '510402', '东区', '510401', NULL);
INSERT INTO `city` VALUES (2578, '510403', '西区', '510401', NULL);
INSERT INTO `city` VALUES (2579, '510411', '仁和区', '510401', NULL);
INSERT INTO `city` VALUES (2580, '510421', '米易县', '510400', '四川省攀枝花市米易县');
INSERT INTO `city` VALUES (2581, '510422', '盐边县', '510400', '四川省攀枝花市盐边县');
INSERT INTO `city` VALUES (2582, '510500', '泸州市', '510000', '四川省泸州市');
INSERT INTO `city` VALUES (2583, '510501', '市辖区', '510500', '四川省泸州市市辖区');
INSERT INTO `city` VALUES (2584, '510502', '江阳区', '510501', NULL);
INSERT INTO `city` VALUES (2585, '510503', '纳溪区', '510501', NULL);
INSERT INTO `city` VALUES (2586, '510504', '龙马潭区', '510501', NULL);
INSERT INTO `city` VALUES (2587, '510521', '泸县', '510500', '四川省泸州市泸县');
INSERT INTO `city` VALUES (2588, '510522', '合江县', '510500', '四川省泸州市合江县');
INSERT INTO `city` VALUES (2589, '510524', '叙永县', '510500', '四川省泸州市叙永县');
INSERT INTO `city` VALUES (2590, '510525', '古蔺县', '510500', '四川省泸州市古蔺县');
INSERT INTO `city` VALUES (2591, '510600', '德阳市', '510000', '四川省德阳市');
INSERT INTO `city` VALUES (2592, '510601', '市辖区', '510600', '四川省德阳市市辖区');
INSERT INTO `city` VALUES (2593, '510603', '旌阳区', '510601', NULL);
INSERT INTO `city` VALUES (2594, '510623', '中江县', '510600', '四川省德阳市中江县');
INSERT INTO `city` VALUES (2595, '510626', '罗江县', '510600', '四川省德阳市罗江县');
INSERT INTO `city` VALUES (2596, '510681', '广汉市', '510600', '四川省德阳市广汉市');
INSERT INTO `city` VALUES (2597, '510682', '什邡市', '510600', '四川省德阳市什邡市');
INSERT INTO `city` VALUES (2598, '510683', '绵竹市', '510600', '四川省德阳市绵竹市');
INSERT INTO `city` VALUES (2599, '510700', '绵阳市', '510000', '四川省绵阳市');
INSERT INTO `city` VALUES (2600, '510701', '市辖区', '510700', '四川省绵阳市市辖区');
INSERT INTO `city` VALUES (2601, '510703', '涪城区', '510701', NULL);
INSERT INTO `city` VALUES (2602, '510704', '游仙区', '510701', NULL);
INSERT INTO `city` VALUES (2603, '510722', '三台县', '510700', '四川省绵阳市三台县');
INSERT INTO `city` VALUES (2604, '510723', '盐亭县', '510700', '四川省绵阳市盐亭县');
INSERT INTO `city` VALUES (2605, '510724', '安县', '510700', '四川省绵阳市安县');
INSERT INTO `city` VALUES (2606, '510725', '梓潼县', '510700', '四川省绵阳市梓潼县');
INSERT INTO `city` VALUES (2607, '510726', '北川羌族自治县', '510700', '四川省绵阳市北川羌族自治县');
INSERT INTO `city` VALUES (2608, '510727', '平武县', '510700', '四川省绵阳市平武县');
INSERT INTO `city` VALUES (2609, '510781', '江油市', '510700', '四川省绵阳市江油市');
INSERT INTO `city` VALUES (2610, '510800', '广元市', '510000', '四川省广元市');
INSERT INTO `city` VALUES (2611, '510801', '市辖区', '510800', '四川省广元市市辖区');
INSERT INTO `city` VALUES (2612, '510802', '市中区', '510801', NULL);
INSERT INTO `city` VALUES (2613, '510811', '元坝区', '510801', NULL);
INSERT INTO `city` VALUES (2614, '510812', '朝天区', '510801', NULL);
INSERT INTO `city` VALUES (2615, '510821', '旺苍县', '510800', '四川省广元市旺苍县');
INSERT INTO `city` VALUES (2616, '510822', '青川县', '510800', '四川省广元市青川县');
INSERT INTO `city` VALUES (2617, '510823', '剑阁县', '510800', '四川省广元市剑阁县');
INSERT INTO `city` VALUES (2618, '510824', '苍溪县', '510800', '四川省广元市苍溪县');
INSERT INTO `city` VALUES (2619, '510900', '遂宁市', '510000', '四川省遂宁市');
INSERT INTO `city` VALUES (2620, '510901', '市辖区', '510900', '四川省遂宁市市辖区');
INSERT INTO `city` VALUES (2621, '510903', '船山区', '510901', NULL);
INSERT INTO `city` VALUES (2622, '510904', '安居区', '510901', NULL);
INSERT INTO `city` VALUES (2623, '510921', '蓬溪县', '510900', '四川省遂宁市蓬溪县');
INSERT INTO `city` VALUES (2624, '510922', '射洪县', '510900', '四川省遂宁市射洪县');
INSERT INTO `city` VALUES (2625, '510923', '大英县', '510900', '四川省遂宁市大英县');
INSERT INTO `city` VALUES (2626, '511000', '内江市', '510000', '四川省内江市');
INSERT INTO `city` VALUES (2627, '511001', '市辖区', '511000', '四川省内江市市辖区');
INSERT INTO `city` VALUES (2628, '511002', '市中区', '511001', NULL);
INSERT INTO `city` VALUES (2629, '511011', '东兴区', '511001', NULL);
INSERT INTO `city` VALUES (2630, '511024', '威远县', '511000', '四川省内江市威远县');
INSERT INTO `city` VALUES (2631, '511025', '资中县', '511000', '四川省内江市资中县');
INSERT INTO `city` VALUES (2632, '511028', '隆昌县', '511000', '四川省内江市隆昌县');
INSERT INTO `city` VALUES (2633, '511100', '乐山市', '510000', '四川省乐山市');
INSERT INTO `city` VALUES (2634, '511101', '市辖区', '511100', '四川省乐山市市辖区');
INSERT INTO `city` VALUES (2635, '511102', '市中区', '511101', NULL);
INSERT INTO `city` VALUES (2636, '511111', '沙湾区', '511101', NULL);
INSERT INTO `city` VALUES (2637, '511112', '五通桥区', '511101', NULL);
INSERT INTO `city` VALUES (2638, '511113', '金口河区', '511101', NULL);
INSERT INTO `city` VALUES (2639, '511123', '犍为县', '511100', '四川省乐山市犍为县');
INSERT INTO `city` VALUES (2640, '511124', '井研县', '511100', '四川省乐山市井研县');
INSERT INTO `city` VALUES (2641, '511126', '夹江县', '511100', '四川省乐山市夹江县');
INSERT INTO `city` VALUES (2642, '511129', '沐川县', '511100', '四川省乐山市沐川县');
INSERT INTO `city` VALUES (2643, '511132', '峨边彝族自治县', '511100', '四川省乐山市峨边彝族自治县');
INSERT INTO `city` VALUES (2644, '511133', '马边彝族自治县', '511100', '四川省乐山市马边彝族自治县');
INSERT INTO `city` VALUES (2645, '511181', '峨眉山市', '511100', '四川省乐山市峨眉山市');
INSERT INTO `city` VALUES (2646, '511300', '南充市', '510000', '四川省南充市');
INSERT INTO `city` VALUES (2647, '511301', '市辖区', '511300', '四川省南充市市辖区');
INSERT INTO `city` VALUES (2648, '511302', '顺庆区', '511301', NULL);
INSERT INTO `city` VALUES (2649, '511303', '高坪区', '511301', NULL);
INSERT INTO `city` VALUES (2650, '511304', '嘉陵区', '511301', NULL);
INSERT INTO `city` VALUES (2651, '511321', '南部县', '511300', '四川省南充市南部县');
INSERT INTO `city` VALUES (2652, '511322', '营山县', '511300', '四川省南充市营山县');
INSERT INTO `city` VALUES (2653, '511323', '蓬安县', '511300', '四川省南充市蓬安县');
INSERT INTO `city` VALUES (2654, '511324', '仪陇县', '511300', '四川省南充市仪陇县');
INSERT INTO `city` VALUES (2655, '511325', '西充县', '511300', '四川省南充市西充县');
INSERT INTO `city` VALUES (2656, '511381', '阆中市', '511300', '四川省南充市阆中市');
INSERT INTO `city` VALUES (2657, '511400', '眉山市', '510000', '四川省眉山市');
INSERT INTO `city` VALUES (2658, '511401', '市辖区', '511400', '四川省眉山市市辖区');
INSERT INTO `city` VALUES (2659, '511402', '东坡区', '511401', NULL);
INSERT INTO `city` VALUES (2660, '511421', '仁寿县', '511400', '四川省眉山市仁寿县');
INSERT INTO `city` VALUES (2661, '511422', '彭山县', '511400', '四川省眉山市彭山县');
INSERT INTO `city` VALUES (2662, '511423', '洪雅县', '511400', '四川省眉山市洪雅县');
INSERT INTO `city` VALUES (2663, '511424', '丹棱县', '511400', '四川省眉山市丹棱县');
INSERT INTO `city` VALUES (2664, '511425', '青神县', '511400', '四川省眉山市青神县');
INSERT INTO `city` VALUES (2665, '511500', '宜宾市', '510000', '四川省宜宾市');
INSERT INTO `city` VALUES (2666, '511501', '市辖区', '511500', '四川省宜宾市市辖区');
INSERT INTO `city` VALUES (2667, '511502', '翠屏区', '511500', '四川省宜宾市翠屏区');
INSERT INTO `city` VALUES (2668, '511521', '宜宾县', '511500', '四川省宜宾市宜宾县');
INSERT INTO `city` VALUES (2669, '511522', '南溪县', '511500', '四川省宜宾市南溪县');
INSERT INTO `city` VALUES (2670, '511523', '江安县', '511500', '四川省宜宾市江安县');
INSERT INTO `city` VALUES (2671, '511524', '长宁县', '511500', '四川省宜宾市长宁县');
INSERT INTO `city` VALUES (2672, '511525', '高县', '511500', '四川省宜宾市高县');
INSERT INTO `city` VALUES (2673, '511526', '珙县', '511500', '四川省宜宾市珙县');
INSERT INTO `city` VALUES (2674, '511527', '筠连县', '511500', '四川省宜宾市筠连县');
INSERT INTO `city` VALUES (2675, '511528', '兴文县', '511500', '四川省宜宾市兴文县');
INSERT INTO `city` VALUES (2676, '511529', '屏山县', '511500', '四川省宜宾市屏山县');
INSERT INTO `city` VALUES (2677, '511600', '广安市', '510000', '四川省广安市');
INSERT INTO `city` VALUES (2678, '511601', '市辖区', '511600', '四川省广安市市辖区');
INSERT INTO `city` VALUES (2679, '511602', '广安区', '511601', NULL);
INSERT INTO `city` VALUES (2680, '511621', '岳池县', '511600', '四川省广安市岳池县');
INSERT INTO `city` VALUES (2681, '511622', '武胜县', '511600', '四川省广安市武胜县');
INSERT INTO `city` VALUES (2682, '511623', '邻水县', '511600', '四川省广安市邻水县');
INSERT INTO `city` VALUES (2683, '511681', '华蓥市', '511600', '四川省广安市华蓥市');
INSERT INTO `city` VALUES (2684, '511682', '广安区', '511600', '四川省广安市广安区');
INSERT INTO `city` VALUES (2685, '511700', '达州市', '510000', '四川省达州市');
INSERT INTO `city` VALUES (2686, '511701', '市辖区', '511700', '四川省达州市市辖区');
INSERT INTO `city` VALUES (2687, '511702', '通川区', '511701', NULL);
INSERT INTO `city` VALUES (2688, '511721', '达川区', '511700', '四川省达州市达川区');
INSERT INTO `city` VALUES (2689, '511722', '宣汉县', '511700', '四川省达州市宣汉县');
INSERT INTO `city` VALUES (2690, '511723', '开江县', '511700', '四川省达州市开江县');
INSERT INTO `city` VALUES (2691, '511724', '大竹县', '511700', '四川省达州市大竹县');
INSERT INTO `city` VALUES (2692, '511725', '渠县', '511700', '四川省达州市渠县');
INSERT INTO `city` VALUES (2693, '511781', '万源市', '511700', '四川省达州市万源市');
INSERT INTO `city` VALUES (2694, '511800', '雅安市', '510000', '四川省雅安市');
INSERT INTO `city` VALUES (2695, '511801', '雨城区', '511800', '四川省雅安市雨城区');
INSERT INTO `city` VALUES (2696, '511802', '雨城区', '511801', NULL);
INSERT INTO `city` VALUES (2697, '511821', '名山区', '511800', '四川省雅安市名山区');
INSERT INTO `city` VALUES (2698, '511822', '荥经县', '511800', '四川省雅安市荥经县');
INSERT INTO `city` VALUES (2699, '511823', '汉源县', '511800', '四川省雅安市汉源县');
INSERT INTO `city` VALUES (2700, '511824', '石棉县', '511800', '四川省雅安市石棉县');
INSERT INTO `city` VALUES (2701, '511825', '天全县', '511800', '四川省雅安市天全县');
INSERT INTO `city` VALUES (2702, '511826', '芦山县', '511800', '四川省雅安市芦山县');
INSERT INTO `city` VALUES (2703, '511827', '宝兴县', '511800', '四川省雅安市宝兴县');
INSERT INTO `city` VALUES (2704, '511900', '巴中市', '510000', '四川省巴中市');
INSERT INTO `city` VALUES (2705, '511901', '市辖区', '511900', '四川省巴中市市辖区');
INSERT INTO `city` VALUES (2706, '511902', '巴州区', '511901', NULL);
INSERT INTO `city` VALUES (2707, '511921', '通江县', '511900', '四川省巴中市通江县');
INSERT INTO `city` VALUES (2708, '511922', '南江县', '511900', '四川省巴中市南江县');
INSERT INTO `city` VALUES (2709, '511923', '平昌县', '511900', '四川省巴中市平昌县');
INSERT INTO `city` VALUES (2710, '512000', '资阳市', '510000', '四川省资阳市');
INSERT INTO `city` VALUES (2711, '512001', '市辖区', '512000', '四川省资阳市市辖区');
INSERT INTO `city` VALUES (2712, '512002', '雁江区', '512001', NULL);
INSERT INTO `city` VALUES (2713, '512021', '安岳县', '512000', '四川省资阳市安岳县');
INSERT INTO `city` VALUES (2714, '512022', '乐至县', '512000', '四川省资阳市乐至县');
INSERT INTO `city` VALUES (2715, '512081', '简阳市', '512000', '四川省资阳市简阳市');
INSERT INTO `city` VALUES (2716, '513200', '阿坝藏族羌族自治州', '510000', '四川省阿坝藏族羌族自治州');
INSERT INTO `city` VALUES (2717, '513221', '汶川县', '513200', '四川省阿坝藏族羌族自治州汶川县');
INSERT INTO `city` VALUES (2718, '513222', '理县', '513200', '四川省阿坝藏族羌族自治州理县');
INSERT INTO `city` VALUES (2719, '513223', '茂县', '513200', '四川省阿坝藏族羌族自治州茂县');
INSERT INTO `city` VALUES (2720, '513224', '松潘县', '513200', '四川省阿坝藏族羌族自治州松潘县');
INSERT INTO `city` VALUES (2721, '513225', '九寨沟县', '513200', '四川省阿坝藏族羌族自治州九寨沟县');
INSERT INTO `city` VALUES (2722, '513226', '金川县', '513200', '四川省阿坝藏族羌族自治州金川县');
INSERT INTO `city` VALUES (2723, '513227', '小金县', '513200', '四川省阿坝藏族羌族自治州小金县');
INSERT INTO `city` VALUES (2724, '513228', '黑水县', '513200', '四川省阿坝藏族羌族自治州黑水县');
INSERT INTO `city` VALUES (2725, '513229', '马尔康县', '513200', '四川省阿坝藏族羌族自治州马尔康县');
INSERT INTO `city` VALUES (2726, '513230', '壤塘县', '513200', '四川省阿坝藏族羌族自治州壤塘县');
INSERT INTO `city` VALUES (2727, '513231', '阿坝县', '513200', '四川省阿坝藏族羌族自治州阿坝县');
INSERT INTO `city` VALUES (2728, '513232', '若尔盖县', '513200', '四川省阿坝藏族羌族自治州若尔盖县');
INSERT INTO `city` VALUES (2729, '513233', '红原县', '513200', '四川省阿坝藏族羌族自治州红原县');
INSERT INTO `city` VALUES (2730, '513300', '甘孜藏族自治州', '510000', '四川省甘孜藏族自治州');
INSERT INTO `city` VALUES (2731, '513321', '康定县', '513300', '四川省甘孜藏族自治州康定县');
INSERT INTO `city` VALUES (2732, '513322', '泸定县', '513300', '四川省甘孜藏族自治州泸定县');
INSERT INTO `city` VALUES (2733, '513323', '丹巴县', '513300', '四川省甘孜藏族自治州丹巴县');
INSERT INTO `city` VALUES (2734, '513324', '九龙县', '513300', '四川省甘孜藏族自治州九龙县');
INSERT INTO `city` VALUES (2735, '513325', '雅江县', '513300', '四川省甘孜藏族自治州雅江县');
INSERT INTO `city` VALUES (2736, '513326', '道孚县', '513300', '四川省甘孜藏族自治州道孚县');
INSERT INTO `city` VALUES (2737, '513327', '炉霍县', '513300', '四川省甘孜藏族自治州炉霍县');
INSERT INTO `city` VALUES (2738, '513328', '甘孜县', '513300', '四川省甘孜藏族自治州甘孜县');
INSERT INTO `city` VALUES (2739, '513329', '新龙县', '513300', '四川省甘孜藏族自治州新龙县');
INSERT INTO `city` VALUES (2740, '513330', '德格县', '513300', '四川省甘孜藏族自治州德格县');
INSERT INTO `city` VALUES (2741, '513331', '白玉县', '513300', '四川省甘孜藏族自治州白玉县');
INSERT INTO `city` VALUES (2742, '513332', '石渠县', '513300', '四川省甘孜藏族自治州石渠县');
INSERT INTO `city` VALUES (2743, '513333', '色达县', '513300', '四川省甘孜藏族自治州色达县');
INSERT INTO `city` VALUES (2744, '513334', '理塘县', '513300', '四川省甘孜藏族自治州理塘县');
INSERT INTO `city` VALUES (2745, '513335', '巴塘县', '513300', '四川省甘孜藏族自治州巴塘县');
INSERT INTO `city` VALUES (2746, '513336', '乡城县', '513300', '四川省甘孜藏族自治州乡城县');
INSERT INTO `city` VALUES (2747, '513337', '稻城县', '513300', '四川省甘孜藏族自治州稻城县');
INSERT INTO `city` VALUES (2748, '513338', '得荣县', '513300', '四川省甘孜藏族自治州得荣县');
INSERT INTO `city` VALUES (2749, '513400', '凉山彝族自治州', '510000', '四川省凉山彝族自治州');
INSERT INTO `city` VALUES (2750, '513401', '西昌市', '513400', '四川省凉山彝族自治州西昌市');
INSERT INTO `city` VALUES (2751, '513422', '木里藏族自治县', '513400', '四川省凉山彝族自治州木里藏族自治县');
INSERT INTO `city` VALUES (2752, '513423', '盐源县', '513400', '四川省凉山彝族自治州盐源县');
INSERT INTO `city` VALUES (2753, '513424', '德昌县', '513400', '四川省凉山彝族自治州德昌县');
INSERT INTO `city` VALUES (2754, '513425', '会理县', '513400', '四川省凉山彝族自治州会理县');
INSERT INTO `city` VALUES (2755, '513426', '会东县', '513400', '四川省凉山彝族自治州会东县');
INSERT INTO `city` VALUES (2756, '513427', '宁南县', '513400', '四川省凉山彝族自治州宁南县');
INSERT INTO `city` VALUES (2757, '513428', '普格县', '513400', '四川省凉山彝族自治州普格县');
INSERT INTO `city` VALUES (2758, '513429', '布拖县', '513400', '四川省凉山彝族自治州布拖县');
INSERT INTO `city` VALUES (2759, '513430', '金阳县', '513400', '四川省凉山彝族自治州金阳县');
INSERT INTO `city` VALUES (2760, '513431', '昭觉县', '513400', '四川省凉山彝族自治州昭觉县');
INSERT INTO `city` VALUES (2761, '513432', '喜德县', '513400', '四川省凉山彝族自治州喜德县');
INSERT INTO `city` VALUES (2762, '513433', '冕宁县', '513400', '四川省凉山彝族自治州冕宁县');
INSERT INTO `city` VALUES (2763, '513434', '越西县', '513400', '四川省凉山彝族自治州越西县');
INSERT INTO `city` VALUES (2764, '513435', '甘洛县', '513400', '四川省凉山彝族自治州甘洛县');
INSERT INTO `city` VALUES (2765, '513436', '美姑县', '513400', '四川省凉山彝族自治州美姑县');
INSERT INTO `city` VALUES (2766, '513437', '雷波县', '513400', '四川省凉山彝族自治州雷波县');
INSERT INTO `city` VALUES (2767, '520000', '贵州省', '0', '贵州省');
INSERT INTO `city` VALUES (2768, '520100', '贵阳市', '520000', '贵州省贵阳市');
INSERT INTO `city` VALUES (2769, '520101', '市辖区', '520100', '贵州省贵阳市市辖区');
INSERT INTO `city` VALUES (2770, '520102', '南明区', '520101', NULL);
INSERT INTO `city` VALUES (2771, '520103', '云岩区', '520101', NULL);
INSERT INTO `city` VALUES (2772, '520111', '花溪区', '520101', NULL);
INSERT INTO `city` VALUES (2773, '520112', '乌当区', '520100', '贵州省贵阳市乌当区');
INSERT INTO `city` VALUES (2774, '520113', '白云区', '520101', NULL);
INSERT INTO `city` VALUES (2775, '520114', '小河区', '520101', NULL);
INSERT INTO `city` VALUES (2776, '520121', '开阳县', '520100', '贵州省贵阳市开阳县');
INSERT INTO `city` VALUES (2777, '520122', '息烽县', '520100', '贵州省贵阳市息烽县');
INSERT INTO `city` VALUES (2778, '520123', '修文县', '520100', '贵州省贵阳市修文县');
INSERT INTO `city` VALUES (2779, '520181', '清镇市', '520100', '贵州省贵阳市清镇市');
INSERT INTO `city` VALUES (2780, '520200', '六盘水市', '520000', '贵州省六盘水市');
INSERT INTO `city` VALUES (2781, '520201', '钟山区', '520200', '贵州省六盘水市钟山区');
INSERT INTO `city` VALUES (2782, '520203', '六枝特区', '520200', '贵州省六盘水市六枝特区');
INSERT INTO `city` VALUES (2783, '520221', '水城县', '520200', '贵州省六盘水市水城县');
INSERT INTO `city` VALUES (2784, '520222', '盘县', '520200', '贵州省六盘水市盘县');
INSERT INTO `city` VALUES (2785, '520300', '遵义市', '520000', '贵州省遵义市');
INSERT INTO `city` VALUES (2786, '520301', '市辖区', '520300', '贵州省遵义市市辖区');
INSERT INTO `city` VALUES (2787, '520302', '红花岗区', '520301', NULL);
INSERT INTO `city` VALUES (2788, '520303', '汇川区', '520301', NULL);
INSERT INTO `city` VALUES (2789, '520321', '遵义县', '520300', '贵州省遵义市遵义县');
INSERT INTO `city` VALUES (2790, '520322', '桐梓县', '520300', '贵州省遵义市桐梓县');
INSERT INTO `city` VALUES (2791, '520323', '绥阳县', '520300', '贵州省遵义市绥阳县');
INSERT INTO `city` VALUES (2792, '520324', '正安县', '520300', '贵州省遵义市正安县');
INSERT INTO `city` VALUES (2793, '520325', '道真仡佬族苗族自治县', '520300', '贵州省遵义市道真仡佬族苗族自治县');
INSERT INTO `city` VALUES (2794, '520326', '务川仡佬族苗族自治县', '520300', '贵州省遵义市务川仡佬族苗族自治县');
INSERT INTO `city` VALUES (2795, '520327', '凤冈县', '520300', '贵州省遵义市凤冈县');
INSERT INTO `city` VALUES (2796, '520328', '湄潭县', '520300', '贵州省遵义市湄潭县');
INSERT INTO `city` VALUES (2797, '520329', '余庆县', '520300', '贵州省遵义市余庆县');
INSERT INTO `city` VALUES (2798, '520330', '习水县', '520300', '贵州省遵义市习水县');
INSERT INTO `city` VALUES (2799, '520381', '赤水市', '520300', '贵州省遵义市赤水市');
INSERT INTO `city` VALUES (2800, '520382', '仁怀市', '520300', '贵州省遵义市仁怀市');
INSERT INTO `city` VALUES (2801, '520400', '安顺市', '520000', '贵州省安顺市');
INSERT INTO `city` VALUES (2802, '520401', '市辖区', '520400', '贵州省安顺市市辖区');
INSERT INTO `city` VALUES (2803, '520402', '西秀区', '520401', NULL);
INSERT INTO `city` VALUES (2804, '520421', '平坝县', '520400', '贵州省安顺市平坝县');
INSERT INTO `city` VALUES (2805, '520422', '普定县', '520400', '贵州省安顺市普定县');
INSERT INTO `city` VALUES (2806, '520423', '镇宁布依族苗族自治县', '520400', '贵州省安顺市镇宁布依族苗族自治县');
INSERT INTO `city` VALUES (2807, '520424', '关岭布依族苗族自治县', '520400', '贵州省安顺市关岭布依族苗族自治县');
INSERT INTO `city` VALUES (2808, '520425', '紫云苗族布依族自治县', '520400', '贵州省安顺市紫云苗族布依族自治县');
INSERT INTO `city` VALUES (2809, '522200', '铜仁市', '520000', '贵州省铜仁市');
INSERT INTO `city` VALUES (2810, '522201', '碧江区', '522200', '贵州省铜仁市碧江区');
INSERT INTO `city` VALUES (2811, '522222', '江口县', '522200', '贵州省铜仁市江口县');
INSERT INTO `city` VALUES (2812, '522223', '玉屏侗族自治县', '522200', '贵州省铜仁市玉屏侗族自治县');
INSERT INTO `city` VALUES (2813, '522224', '石阡县', '522200', '贵州省铜仁市石阡县');
INSERT INTO `city` VALUES (2814, '522225', '思南县', '522200', '贵州省铜仁市思南县');
INSERT INTO `city` VALUES (2815, '522226', '印江土家族苗族自治县', '522200', '贵州省铜仁市印江土家族苗族自治县');
INSERT INTO `city` VALUES (2816, '522227', '德江县', '522200', '贵州省铜仁市德江县');
INSERT INTO `city` VALUES (2817, '522228', '沿河土家族自治县', '522200', '贵州省铜仁市沿河土家族自治县');
INSERT INTO `city` VALUES (2818, '522229', '松桃苗族自治县', '522200', '贵州省铜仁市松桃苗族自治县');
INSERT INTO `city` VALUES (2819, '522230', '万山区', '522200', '贵州省铜仁市万山区');
INSERT INTO `city` VALUES (2820, '522300', '黔西南布依族苗族自治州', '520000', '贵州省黔西南布依族苗族自治州');
INSERT INTO `city` VALUES (2821, '522301', '兴义市', '522300', '贵州省黔西南布依族苗族自治州兴义市');
INSERT INTO `city` VALUES (2822, '522322', '兴仁县', '522300', '贵州省黔西南布依族苗族自治州兴仁县');
INSERT INTO `city` VALUES (2823, '522323', '普安县', '522300', '贵州省黔西南布依族苗族自治州普安县');
INSERT INTO `city` VALUES (2824, '522324', '晴隆县', '522300', '贵州省黔西南布依族苗族自治州晴隆县');
INSERT INTO `city` VALUES (2825, '522325', '贞丰县', '522300', '贵州省黔西南布依族苗族自治州贞丰县');
INSERT INTO `city` VALUES (2826, '522326', '望谟县', '522300', '贵州省黔西南布依族苗族自治州望谟县');
INSERT INTO `city` VALUES (2827, '522327', '册亨县', '522300', '贵州省黔西南布依族苗族自治州册亨县');
INSERT INTO `city` VALUES (2828, '522328', '安龙县', '522300', '贵州省黔西南布依族苗族自治州安龙县');
INSERT INTO `city` VALUES (2829, '522400', '毕节市', '520000', '贵州省毕节市');
INSERT INTO `city` VALUES (2830, '522401', '七星关区', '522400', '贵州省毕节市七星关区');
INSERT INTO `city` VALUES (2831, '522422', '大方县', '522400', '贵州省毕节市大方县');
INSERT INTO `city` VALUES (2832, '522423', '黔西县', '522400', '贵州省毕节市黔西县');
INSERT INTO `city` VALUES (2833, '522424', '金沙县', '522400', '贵州省毕节市金沙县');
INSERT INTO `city` VALUES (2834, '522425', '织金县', '522400', '贵州省毕节市织金县');
INSERT INTO `city` VALUES (2835, '522426', '纳雍县', '522400', '贵州省毕节市纳雍县');
INSERT INTO `city` VALUES (2836, '522427', '威宁彝族回族苗族自治县', '522400', '贵州省毕节市威宁彝族回族苗族自治县');
INSERT INTO `city` VALUES (2837, '522428', '赫章县', '522400', '贵州省毕节市赫章县');
INSERT INTO `city` VALUES (2838, '522600', '黔东南苗族侗族自治州', '520000', '贵州省黔东南苗族侗族自治州');
INSERT INTO `city` VALUES (2839, '522601', '凯里市', '522600', '贵州省黔东南苗族侗族自治州凯里市');
INSERT INTO `city` VALUES (2840, '522622', '黄平县', '522600', '贵州省黔东南苗族侗族自治州黄平县');
INSERT INTO `city` VALUES (2841, '522623', '施秉县', '522600', '贵州省黔东南苗族侗族自治州施秉县');
INSERT INTO `city` VALUES (2842, '522624', '三穗县', '522600', '贵州省黔东南苗族侗族自治州三穗县');
INSERT INTO `city` VALUES (2843, '522625', '镇远县', '522600', '贵州省黔东南苗族侗族自治州镇远县');
INSERT INTO `city` VALUES (2844, '522626', '岑巩县', '522600', '贵州省黔东南苗族侗族自治州岑巩县');
INSERT INTO `city` VALUES (2845, '522627', '天柱县', '522600', '贵州省黔东南苗族侗族自治州天柱县');
INSERT INTO `city` VALUES (2846, '522628', '锦屏县', '522600', '贵州省黔东南苗族侗族自治州锦屏县');
INSERT INTO `city` VALUES (2847, '522629', '剑河县', '522600', '贵州省黔东南苗族侗族自治州剑河县');
INSERT INTO `city` VALUES (2848, '522630', '台江县', '522600', '贵州省黔东南苗族侗族自治州台江县');
INSERT INTO `city` VALUES (2849, '522631', '黎平县', '522600', '贵州省黔东南苗族侗族自治州黎平县');
INSERT INTO `city` VALUES (2850, '522632', '榕江县', '522600', '贵州省黔东南苗族侗族自治州榕江县');
INSERT INTO `city` VALUES (2851, '522633', '从江县', '522600', '贵州省黔东南苗族侗族自治州从江县');
INSERT INTO `city` VALUES (2852, '522634', '雷山县', '522600', '贵州省黔东南苗族侗族自治州雷山县');
INSERT INTO `city` VALUES (2853, '522635', '麻江县', '522600', '贵州省黔东南苗族侗族自治州麻江县');
INSERT INTO `city` VALUES (2854, '522636', '丹寨县', '522600', '贵州省黔东南苗族侗族自治州丹寨县');
INSERT INTO `city` VALUES (2855, '522700', '黔南布依族苗族自治州', '520000', '贵州省黔南布依族苗族自治州');
INSERT INTO `city` VALUES (2856, '522701', '都匀市', '522700', '贵州省黔南布依族苗族自治州都匀市');
INSERT INTO `city` VALUES (2857, '522702', '福泉市', '522700', '贵州省黔南布依族苗族自治州福泉市');
INSERT INTO `city` VALUES (2858, '522722', '荔波县', '522700', '贵州省黔南布依族苗族自治州荔波县');
INSERT INTO `city` VALUES (2859, '522723', '贵定县', '522700', '贵州省黔南布依族苗族自治州贵定县');
INSERT INTO `city` VALUES (2860, '522725', '瓮安县', '522700', '贵州省黔南布依族苗族自治州瓮安县');
INSERT INTO `city` VALUES (2861, '522726', '独山县', '522700', '贵州省黔南布依族苗族自治州独山县');
INSERT INTO `city` VALUES (2862, '522727', '平塘县', '522700', '贵州省黔南布依族苗族自治州平塘县');
INSERT INTO `city` VALUES (2863, '522728', '罗甸县', '522700', '贵州省黔南布依族苗族自治州罗甸县');
INSERT INTO `city` VALUES (2864, '522729', '长顺县', '522700', '贵州省黔南布依族苗族自治州长顺县');
INSERT INTO `city` VALUES (2865, '522730', '龙里县', '522700', '贵州省黔南布依族苗族自治州龙里县');
INSERT INTO `city` VALUES (2866, '522731', '惠水县', '522700', '贵州省黔南布依族苗族自治州惠水县');
INSERT INTO `city` VALUES (2867, '522732', '三都水族自治县', '522700', '贵州省黔南布依族苗族自治州三都水族自治县');
INSERT INTO `city` VALUES (2868, '530000', '云南省', '0', '云南省');
INSERT INTO `city` VALUES (2869, '530100', '昆明市', '530000', '云南省昆明市');
INSERT INTO `city` VALUES (2870, '530101', '市辖区', '530100', '云南省昆明市市辖区');
INSERT INTO `city` VALUES (2871, '530102', '五华区', '530101', NULL);
INSERT INTO `city` VALUES (2872, '530103', '盘龙区', '530101', NULL);
INSERT INTO `city` VALUES (2873, '530111', '官渡区', '530101', NULL);
INSERT INTO `city` VALUES (2874, '530112', '西山区', '530101', NULL);
INSERT INTO `city` VALUES (2875, '530113', '东川区', '530101', NULL);
INSERT INTO `city` VALUES (2876, '530121', '呈贡县', '530100', '云南省昆明市呈贡县');
INSERT INTO `city` VALUES (2877, '530122', '晋宁县', '530100', '云南省昆明市晋宁县');
INSERT INTO `city` VALUES (2878, '530124', '富民县', '530100', '云南省昆明市富民县');
INSERT INTO `city` VALUES (2879, '530125', '宜良县', '530100', '云南省昆明市宜良县');
INSERT INTO `city` VALUES (2880, '530126', '石林彝族自治县', '530100', '云南省昆明市石林彝族自治县');
INSERT INTO `city` VALUES (2881, '530127', '嵩明县', '530100', '云南省昆明市嵩明县');
INSERT INTO `city` VALUES (2882, '530128', '禄劝彝族苗族自治县', '530100', '云南省昆明市禄劝彝族苗族自治县');
INSERT INTO `city` VALUES (2883, '530129', '寻甸回族彝族自治县', '530100', '云南省昆明市寻甸回族彝族自治县');
INSERT INTO `city` VALUES (2884, '530181', '安宁市', '530100', '云南省昆明市安宁市');
INSERT INTO `city` VALUES (2885, '530300', '曲靖市', '530000', '云南省曲靖市');
INSERT INTO `city` VALUES (2886, '530301', '市辖区', '530300', '云南省曲靖市市辖区');
INSERT INTO `city` VALUES (2887, '530302', '麒麟区', '530301', NULL);
INSERT INTO `city` VALUES (2888, '530321', '马龙县', '530300', '云南省曲靖市马龙县');
INSERT INTO `city` VALUES (2889, '530322', '陆良县', '530300', '云南省曲靖市陆良县');
INSERT INTO `city` VALUES (2890, '530323', '师宗县', '530300', '云南省曲靖市师宗县');
INSERT INTO `city` VALUES (2891, '530324', '罗平县', '530300', '云南省曲靖市罗平县');
INSERT INTO `city` VALUES (2892, '530325', '富源县', '530300', '云南省曲靖市富源县');
INSERT INTO `city` VALUES (2893, '530326', '会泽县', '530300', '云南省曲靖市会泽县');
INSERT INTO `city` VALUES (2894, '530328', '沾益县', '530300', '云南省曲靖市沾益县');
INSERT INTO `city` VALUES (2895, '530381', '宣威市', '530300', '云南省曲靖市宣威市');
INSERT INTO `city` VALUES (2896, '530400', '玉溪市', '530000', '云南省玉溪市');
INSERT INTO `city` VALUES (2897, '530401', '市辖区', '530400', '云南省玉溪市市辖区');
INSERT INTO `city` VALUES (2898, '530402', '红塔区', '530401', NULL);
INSERT INTO `city` VALUES (2899, '530421', '江川县', '530400', '云南省玉溪市江川县');
INSERT INTO `city` VALUES (2900, '530422', '澄江县', '530400', '云南省玉溪市澄江县');
INSERT INTO `city` VALUES (2901, '530423', '通海县', '530400', '云南省玉溪市通海县');
INSERT INTO `city` VALUES (2902, '530424', '华宁县', '530400', '云南省玉溪市华宁县');
INSERT INTO `city` VALUES (2903, '530425', '易门县', '530400', '云南省玉溪市易门县');
INSERT INTO `city` VALUES (2904, '530426', '峨山彝族自治县', '530400', '云南省玉溪市峨山彝族自治县');
INSERT INTO `city` VALUES (2905, '530427', '新平彝族傣族自治县', '530400', '云南省玉溪市新平彝族傣族自治县');
INSERT INTO `city` VALUES (2906, '530428', '元江哈尼族彝族傣族自治县', '530400', '云南省玉溪市元江哈尼族彝族傣族自治县');
INSERT INTO `city` VALUES (2907, '530500', '保山市', '530000', '云南省保山市');
INSERT INTO `city` VALUES (2908, '530501', '市辖区', '530500', '云南省保山市市辖区');
INSERT INTO `city` VALUES (2909, '530502', '隆阳区', '530500', '云南省保山市隆阳区');
INSERT INTO `city` VALUES (2910, '530521', '施甸县', '530500', '云南省保山市施甸县');
INSERT INTO `city` VALUES (2911, '530522', '腾冲县', '530500', '云南省保山市腾冲县');
INSERT INTO `city` VALUES (2912, '530523', '龙陵县', '530500', '云南省保山市龙陵县');
INSERT INTO `city` VALUES (2913, '530524', '昌宁县', '530500', '云南省保山市昌宁县');
INSERT INTO `city` VALUES (2914, '530600', '昭通市', '530000', '云南省昭通市');
INSERT INTO `city` VALUES (2915, '530601', '市辖区', '530600', '云南省昭通市市辖区');
INSERT INTO `city` VALUES (2916, '530602', '昭阳区', '530601', NULL);
INSERT INTO `city` VALUES (2917, '530621', '鲁甸县', '530600', '云南省昭通市鲁甸县');
INSERT INTO `city` VALUES (2918, '530622', '巧家县', '530600', '云南省昭通市巧家县');
INSERT INTO `city` VALUES (2919, '530623', '盐津县', '530600', '云南省昭通市盐津县');
INSERT INTO `city` VALUES (2920, '530624', '大关县', '530600', '云南省昭通市大关县');
INSERT INTO `city` VALUES (2921, '530625', '永善县', '530600', '云南省昭通市永善县');
INSERT INTO `city` VALUES (2922, '530626', '绥江县', '530600', '云南省昭通市绥江县');
INSERT INTO `city` VALUES (2923, '530627', '镇雄县', '530600', '云南省昭通市镇雄县');
INSERT INTO `city` VALUES (2924, '530628', '彝良县', '530600', '云南省昭通市彝良县');
INSERT INTO `city` VALUES (2925, '530629', '威信县', '530600', '云南省昭通市威信县');
INSERT INTO `city` VALUES (2926, '530630', '水富县', '530600', '云南省昭通市水富县');
INSERT INTO `city` VALUES (2927, '530700', '丽江市', '530000', '云南省丽江市');
INSERT INTO `city` VALUES (2928, '530701', '市辖区', '530700', '云南省丽江市市辖区');
INSERT INTO `city` VALUES (2929, '530702', '古城区', '530701', NULL);
INSERT INTO `city` VALUES (2930, '530721', '玉龙纳西族自治县', '530700', '云南省丽江市玉龙纳西族自治县');
INSERT INTO `city` VALUES (2931, '530722', '永胜县', '530700', '云南省丽江市永胜县');
INSERT INTO `city` VALUES (2932, '530723', '华坪县', '530700', '云南省丽江市华坪县');
INSERT INTO `city` VALUES (2933, '530724', '宁蒗彝族自治县', '530700', '云南省丽江市宁蒗彝族自治县');
INSERT INTO `city` VALUES (2934, '530800', '普洱市', '530000', '云南省普洱市');
INSERT INTO `city` VALUES (2935, '530801', '市辖区', '530800', '云南省普洱市市辖区');
INSERT INTO `city` VALUES (2936, '530802', '翠云区', '530801', NULL);
INSERT INTO `city` VALUES (2937, '530821', '宁洱哈尼族彝族自治县', '530800', '云南省普洱市宁洱哈尼族彝族自治县');
INSERT INTO `city` VALUES (2938, '530822', '墨江哈尼族自治县', '530800', '云南省普洱市墨江哈尼族自治县');
INSERT INTO `city` VALUES (2939, '530823', '景东彝族自治县', '530800', '云南省普洱市景东彝族自治县');
INSERT INTO `city` VALUES (2940, '530824', '景谷傣族彝族自治县', '530800', '云南省普洱市景谷傣族彝族自治县');
INSERT INTO `city` VALUES (2941, '530825', '镇沅彝族哈尼族拉祜族自治县', '530800', '云南省普洱市镇沅彝族哈尼族拉祜族自治县');
INSERT INTO `city` VALUES (2942, '530826', '江城哈尼族彝族自治县', '530800', '云南省普洱市江城哈尼族彝族自治县');
INSERT INTO `city` VALUES (2943, '530827', '孟连傣族拉祜族佤族自治县', '530800', '云南省普洱市孟连傣族拉祜族佤族自治县');
INSERT INTO `city` VALUES (2944, '530828', '澜沧拉祜族自治县', '530800', '云南省普洱市澜沧拉祜族自治县');
INSERT INTO `city` VALUES (2945, '530829', '西盟佤族自治县', '530800', '云南省普洱市西盟佤族自治县');
INSERT INTO `city` VALUES (2946, '530900', '临沧市', '530000', '云南省临沧市');
INSERT INTO `city` VALUES (2947, '530901', '市辖区', '530900', '云南省临沧市市辖区');
INSERT INTO `city` VALUES (2948, '530902', '临翔区', '530901', NULL);
INSERT INTO `city` VALUES (2949, '530921', '凤庆县', '530900', '云南省临沧市凤庆县');
INSERT INTO `city` VALUES (2950, '530922', '云县', '530900', '云南省临沧市云县');
INSERT INTO `city` VALUES (2951, '530923', '永德县', '530900', '云南省临沧市永德县');
INSERT INTO `city` VALUES (2952, '530924', '镇康县', '530900', '云南省临沧市镇康县');
INSERT INTO `city` VALUES (2953, '530925', '双江拉祜族佤族布朗族傣族自治县', '530900', '云南省临沧市双江拉祜族佤族布朗族傣族自治县');
INSERT INTO `city` VALUES (2954, '530926', '耿马傣族佤族自治县', '530900', '云南省临沧市耿马傣族佤族自治县');
INSERT INTO `city` VALUES (2955, '530927', '沧源佤族自治县', '530900', '云南省临沧市沧源佤族自治县');
INSERT INTO `city` VALUES (2956, '532300', '楚雄彝族自治州', '530000', '云南省楚雄彝族自治州');
INSERT INTO `city` VALUES (2957, '532301', '楚雄市', '532300', '云南省楚雄彝族自治州楚雄市');
INSERT INTO `city` VALUES (2958, '532322', '双柏县', '532300', '云南省楚雄彝族自治州双柏县');
INSERT INTO `city` VALUES (2959, '532323', '牟定县', '532300', '云南省楚雄彝族自治州牟定县');
INSERT INTO `city` VALUES (2960, '532324', '南华县', '532300', '云南省楚雄彝族自治州南华县');
INSERT INTO `city` VALUES (2961, '532325', '姚安县', '532300', '云南省楚雄彝族自治州姚安县');
INSERT INTO `city` VALUES (2962, '532326', '大姚县', '532300', '云南省楚雄彝族自治州大姚县');
INSERT INTO `city` VALUES (2963, '532327', '永仁县', '532300', '云南省楚雄彝族自治州永仁县');
INSERT INTO `city` VALUES (2964, '532328', '元谋县', '532300', '云南省楚雄彝族自治州元谋县');
INSERT INTO `city` VALUES (2965, '532329', '武定县', '532300', '云南省楚雄彝族自治州武定县');
INSERT INTO `city` VALUES (2966, '532331', '禄丰县', '532300', '云南省楚雄彝族自治州禄丰县');
INSERT INTO `city` VALUES (2967, '532500', '红河哈尼族彝族自治州', '530000', '云南省红河哈尼族彝族自治州');
INSERT INTO `city` VALUES (2968, '532501', '个旧市', '532500', '云南省红河哈尼族彝族自治州个旧市');
INSERT INTO `city` VALUES (2969, '532502', '开远市', '532500', '云南省红河哈尼族彝族自治州开远市');
INSERT INTO `city` VALUES (2970, '532522', '蒙自市', '532500', '云南省红河哈尼族彝族自治州蒙自市');
INSERT INTO `city` VALUES (2971, '532523', '屏边苗族自治县', '532500', '云南省红河哈尼族彝族自治州屏边苗族自治县');
INSERT INTO `city` VALUES (2972, '532524', '建水县', '532500', '云南省红河哈尼族彝族自治州建水县');
INSERT INTO `city` VALUES (2973, '532525', '石屏县', '532500', '云南省红河哈尼族彝族自治州石屏县');
INSERT INTO `city` VALUES (2974, '532526', '弥勒市', '532500', '云南省红河哈尼族彝族自治州弥勒市');
INSERT INTO `city` VALUES (2975, '532527', '泸西县', '532500', '云南省红河哈尼族彝族自治州泸西县');
INSERT INTO `city` VALUES (2976, '532528', '元阳县', '532500', '云南省红河哈尼族彝族自治州元阳县');
INSERT INTO `city` VALUES (2977, '532529', '红河县', '532500', '云南省红河哈尼族彝族自治州红河县');
INSERT INTO `city` VALUES (2978, '532530', '金平苗族瑶族傣族自治县', '532500', '云南省红河哈尼族彝族自治州金平苗族瑶族傣族自治县');
INSERT INTO `city` VALUES (2979, '532531', '绿春县', '532500', '云南省红河哈尼族彝族自治州绿春县');
INSERT INTO `city` VALUES (2980, '532532', '河口瑶族自治县', '532500', '云南省红河哈尼族彝族自治州河口瑶族自治县');
INSERT INTO `city` VALUES (2981, '532600', '文山壮族苗族自治州', '530000', '云南省文山壮族苗族自治州');
INSERT INTO `city` VALUES (2982, '532621', '文山市', '532600', '云南省文山壮族苗族自治州文山市');
INSERT INTO `city` VALUES (2983, '532622', '砚山县', '532600', '云南省文山壮族苗族自治州砚山县');
INSERT INTO `city` VALUES (2984, '532623', '西畴县', '532600', '云南省文山壮族苗族自治州西畴县');
INSERT INTO `city` VALUES (2985, '532624', '麻栗坡县', '532600', '云南省文山壮族苗族自治州麻栗坡县');
INSERT INTO `city` VALUES (2986, '532625', '马关县', '532600', '云南省文山壮族苗族自治州马关县');
INSERT INTO `city` VALUES (2987, '532626', '丘北县', '532600', '云南省文山壮族苗族自治州丘北县');
INSERT INTO `city` VALUES (2988, '532627', '广南县', '532600', '云南省文山壮族苗族自治州广南县');
INSERT INTO `city` VALUES (2989, '532628', '富宁县', '532600', '云南省文山壮族苗族自治州富宁县');
INSERT INTO `city` VALUES (2990, '532800', '西双版纳傣族自治州', '530000', '云南省西双版纳傣族自治州');
INSERT INTO `city` VALUES (2991, '532801', '景洪市', '532800', '云南省西双版纳傣族自治州景洪市');
INSERT INTO `city` VALUES (2992, '532822', '勐海县', '532800', '云南省西双版纳傣族自治州勐海县');
INSERT INTO `city` VALUES (2993, '532823', '勐腊县', '532800', '云南省西双版纳傣族自治州勐腊县');
INSERT INTO `city` VALUES (2994, '532900', '大理白族自治州', '530000', '云南省大理白族自治州');
INSERT INTO `city` VALUES (2995, '532901', '大理市', '532900', '云南省大理白族自治州大理市');
INSERT INTO `city` VALUES (2996, '532922', '漾濞彝族自治县', '532900', '云南省大理白族自治州漾濞彝族自治县');
INSERT INTO `city` VALUES (2997, '532923', '祥云县', '532900', '云南省大理白族自治州祥云县');
INSERT INTO `city` VALUES (2998, '532924', '宾川县', '532900', '云南省大理白族自治州宾川县');
INSERT INTO `city` VALUES (2999, '532925', '弥渡县', '532900', '云南省大理白族自治州弥渡县');
INSERT INTO `city` VALUES (3000, '532926', '南涧彝族自治县', '532900', '云南省大理白族自治州南涧彝族自治县');
INSERT INTO `city` VALUES (3001, '532927', '巍山彝族回族自治县', '532900', '云南省大理白族自治州巍山彝族回族自治县');
INSERT INTO `city` VALUES (3002, '532928', '永平县', '532900', '云南省大理白族自治州永平县');
INSERT INTO `city` VALUES (3003, '532929', '云龙县', '532900', '云南省大理白族自治州云龙县');
INSERT INTO `city` VALUES (3004, '532930', '洱源县', '532900', '云南省大理白族自治州洱源县');
INSERT INTO `city` VALUES (3005, '532931', '剑川县', '532900', '云南省大理白族自治州剑川县');
INSERT INTO `city` VALUES (3006, '532932', '鹤庆县', '532900', '云南省大理白族自治州鹤庆县');
INSERT INTO `city` VALUES (3007, '533100', '德宏傣族景颇族自治州', '530000', '云南省德宏傣族景颇族自治州');
INSERT INTO `city` VALUES (3008, '533102', '瑞丽市', '533100', '云南省德宏傣族景颇族自治州瑞丽市');
INSERT INTO `city` VALUES (3009, '533103', '潞西市', '533100', '云南省德宏傣族景颇族自治州潞西市');
INSERT INTO `city` VALUES (3010, '533122', '梁河县', '533100', '云南省德宏傣族景颇族自治州梁河县');
INSERT INTO `city` VALUES (3011, '533123', '盈江县', '533100', '云南省德宏傣族景颇族自治州盈江县');
INSERT INTO `city` VALUES (3012, '533124', '陇川县', '533100', '云南省德宏傣族景颇族自治州陇川县');
INSERT INTO `city` VALUES (3013, '533300', '怒江傈僳族自治州', '530000', '云南省怒江傈僳族自治州');
INSERT INTO `city` VALUES (3014, '533321', '泸水县', '533300', '云南省怒江傈僳族自治州泸水县');
INSERT INTO `city` VALUES (3015, '533323', '福贡县', '533300', '云南省怒江傈僳族自治州福贡县');
INSERT INTO `city` VALUES (3016, '533324', '贡山独龙族怒族自治县', '533300', '云南省怒江傈僳族自治州贡山独龙族怒族自治县');
INSERT INTO `city` VALUES (3017, '533325', '兰坪白族普米族自治县', '533300', '云南省怒江傈僳族自治州兰坪白族普米族自治县');
INSERT INTO `city` VALUES (3018, '533400', '迪庆藏族自治州', '530000', '云南省迪庆藏族自治州');
INSERT INTO `city` VALUES (3019, '533421', '香格里拉县', '533400', '云南省迪庆藏族自治州香格里拉县');
INSERT INTO `city` VALUES (3020, '533422', '德钦县', '533400', '云南省迪庆藏族自治州德钦县');
INSERT INTO `city` VALUES (3021, '533423', '维西傈僳族自治县', '533400', '云南省迪庆藏族自治州维西傈僳族自治县');
INSERT INTO `city` VALUES (3022, '540000', '西藏自治区', '0', '西藏自治区');
INSERT INTO `city` VALUES (3023, '540100', '拉萨市', '540000', '西藏自治区拉萨市');
INSERT INTO `city` VALUES (3024, '540101', '市辖区', '540100', '西藏自治区拉萨市市辖区');
INSERT INTO `city` VALUES (3025, '540102', '城关区', '540101', NULL);
INSERT INTO `city` VALUES (3026, '540121', '林周县', '540100', '西藏自治区拉萨市林周县');
INSERT INTO `city` VALUES (3027, '540122', '当雄县', '540100', '西藏自治区拉萨市当雄县');
INSERT INTO `city` VALUES (3028, '540123', '尼木县', '540100', '西藏自治区拉萨市尼木县');
INSERT INTO `city` VALUES (3029, '540124', '曲水县', '540100', '西藏自治区拉萨市曲水县');
INSERT INTO `city` VALUES (3030, '540125', '堆龙德庆县', '540100', '西藏自治区拉萨市堆龙德庆县');
INSERT INTO `city` VALUES (3031, '540126', '达孜县', '540100', '西藏自治区拉萨市达孜县');
INSERT INTO `city` VALUES (3032, '540127', '墨竹工卡县', '540100', '西藏自治区拉萨市墨竹工卡县');
INSERT INTO `city` VALUES (3033, '542100', '昌都地区', '540000', '西藏自治区昌都地区');
INSERT INTO `city` VALUES (3034, '542121', '昌都县', '542100', '西藏自治区昌都地区昌都县');
INSERT INTO `city` VALUES (3035, '542122', '江达县', '542100', '西藏自治区昌都地区江达县');
INSERT INTO `city` VALUES (3036, '542123', '贡觉县', '542100', '西藏自治区昌都地区贡觉县');
INSERT INTO `city` VALUES (3037, '542124', '类乌齐县', '542100', '西藏自治区昌都地区类乌齐县');
INSERT INTO `city` VALUES (3038, '542125', '丁青县', '542100', '西藏自治区昌都地区丁青县');
INSERT INTO `city` VALUES (3039, '542126', '察雅县', '542100', '西藏自治区昌都地区察雅县');
INSERT INTO `city` VALUES (3040, '542127', '八宿县', '542100', '西藏自治区昌都地区八宿县');
INSERT INTO `city` VALUES (3041, '542128', '左贡县', '542100', '西藏自治区昌都地区左贡县');
INSERT INTO `city` VALUES (3042, '542129', '芒康县', '542100', '西藏自治区昌都地区芒康县');
INSERT INTO `city` VALUES (3043, '542132', '洛隆县', '542100', '西藏自治区昌都地区洛隆县');
INSERT INTO `city` VALUES (3044, '542133', '边坝县', '542100', '西藏自治区昌都地区边坝县');
INSERT INTO `city` VALUES (3045, '542200', '山南地区', '540000', '西藏自治区山南地区');
INSERT INTO `city` VALUES (3046, '542221', '乃东县', '542200', '西藏自治区山南地区乃东县');
INSERT INTO `city` VALUES (3047, '542222', '扎囊县', '542200', '西藏自治区山南地区扎囊县');
INSERT INTO `city` VALUES (3048, '542223', '贡嘎县', '542200', '西藏自治区山南地区贡嘎县');
INSERT INTO `city` VALUES (3049, '542224', '桑日县', '542200', '西藏自治区山南地区桑日县');
INSERT INTO `city` VALUES (3050, '542225', '琼结县', '542200', '西藏自治区山南地区琼结县');
INSERT INTO `city` VALUES (3051, '542226', '曲松县', '542200', '西藏自治区山南地区曲松县');
INSERT INTO `city` VALUES (3052, '542227', '措美县', '542200', '西藏自治区山南地区措美县');
INSERT INTO `city` VALUES (3053, '542228', '洛扎县', '542200', '西藏自治区山南地区洛扎县');
INSERT INTO `city` VALUES (3054, '542229', '加查县', '542200', '西藏自治区山南地区加查县');
INSERT INTO `city` VALUES (3055, '542231', '隆子县', '542200', '西藏自治区山南地区隆子县');
INSERT INTO `city` VALUES (3056, '542232', '错那县', '542200', '西藏自治区山南地区错那县');
INSERT INTO `city` VALUES (3057, '542233', '浪卡子县', '542200', '西藏自治区山南地区浪卡子县');
INSERT INTO `city` VALUES (3058, '542300', '日喀则地区', '540000', '西藏自治区日喀则地区');
INSERT INTO `city` VALUES (3059, '542301', '日喀则市', '542300', '西藏自治区日喀则地区日喀则市');
INSERT INTO `city` VALUES (3060, '542322', '南木林县', '542300', '西藏自治区日喀则地区南木林县');
INSERT INTO `city` VALUES (3061, '542323', '江孜县', '542300', '西藏自治区日喀则地区江孜县');
INSERT INTO `city` VALUES (3062, '542324', '定日县', '542300', '西藏自治区日喀则地区定日县');
INSERT INTO `city` VALUES (3063, '542325', '萨迦县', '542300', '西藏自治区日喀则地区萨迦县');
INSERT INTO `city` VALUES (3064, '542326', '拉孜县', '542300', '西藏自治区日喀则地区拉孜县');
INSERT INTO `city` VALUES (3065, '542327', '昂仁县', '542300', '西藏自治区日喀则地区昂仁县');
INSERT INTO `city` VALUES (3066, '542328', '谢通门县', '542300', '西藏自治区日喀则地区谢通门县');
INSERT INTO `city` VALUES (3067, '542329', '白朗县', '542300', '西藏自治区日喀则地区白朗县');
INSERT INTO `city` VALUES (3068, '542330', '仁布县', '542300', '西藏自治区日喀则地区仁布县');
INSERT INTO `city` VALUES (3069, '542331', '康马县', '542300', '西藏自治区日喀则地区康马县');
INSERT INTO `city` VALUES (3070, '542332', '定结县', '542300', '西藏自治区日喀则地区定结县');
INSERT INTO `city` VALUES (3071, '542333', '仲巴县', '542300', '西藏自治区日喀则地区仲巴县');
INSERT INTO `city` VALUES (3072, '542334', '亚东县', '542300', '西藏自治区日喀则地区亚东县');
INSERT INTO `city` VALUES (3073, '542335', '吉隆县', '542300', '西藏自治区日喀则地区吉隆县');
INSERT INTO `city` VALUES (3074, '542336', '聂拉木县', '542300', '西藏自治区日喀则地区聂拉木县');
INSERT INTO `city` VALUES (3075, '542337', '萨嘎县', '542300', '西藏自治区日喀则地区萨嘎县');
INSERT INTO `city` VALUES (3076, '542338', '岗巴县', '542300', '西藏自治区日喀则地区岗巴县');
INSERT INTO `city` VALUES (3077, '542400', '那曲地区', '540000', '西藏自治区那曲地区');
INSERT INTO `city` VALUES (3078, '542421', '那曲县', '542400', '西藏自治区那曲地区那曲县');
INSERT INTO `city` VALUES (3079, '542422', '嘉黎县', '542400', '西藏自治区那曲地区嘉黎县');
INSERT INTO `city` VALUES (3080, '542423', '比如县', '542400', '西藏自治区那曲地区比如县');
INSERT INTO `city` VALUES (3081, '542424', '聂荣县', '542400', '西藏自治区那曲地区聂荣县');
INSERT INTO `city` VALUES (3082, '542425', '安多县', '542400', '西藏自治区那曲地区安多县');
INSERT INTO `city` VALUES (3083, '542426', '申扎县', '542400', '西藏自治区那曲地区申扎县');
INSERT INTO `city` VALUES (3084, '542427', '索县', '542400', '西藏自治区那曲地区索县');
INSERT INTO `city` VALUES (3085, '542428', '班戈县', '542400', '西藏自治区那曲地区班戈县');
INSERT INTO `city` VALUES (3086, '542429', '巴青县', '542400', '西藏自治区那曲地区巴青县');
INSERT INTO `city` VALUES (3087, '542430', '尼玛县', '542400', '西藏自治区那曲地区尼玛县');
INSERT INTO `city` VALUES (3088, '542500', '阿里地区', '540000', '西藏自治区阿里地区');
INSERT INTO `city` VALUES (3089, '542521', '普兰县', '542500', '西藏自治区阿里地区普兰县');
INSERT INTO `city` VALUES (3090, '542522', '札达县', '542500', '西藏自治区阿里地区札达县');
INSERT INTO `city` VALUES (3091, '542523', '噶尔县', '542500', '西藏自治区阿里地区噶尔县');
INSERT INTO `city` VALUES (3092, '542524', '日土县', '542500', '西藏自治区阿里地区日土县');
INSERT INTO `city` VALUES (3093, '542525', '革吉县', '542500', '西藏自治区阿里地区革吉县');
INSERT INTO `city` VALUES (3094, '542526', '改则县', '542500', '西藏自治区阿里地区改则县');
INSERT INTO `city` VALUES (3095, '542527', '措勤县', '542500', '西藏自治区阿里地区措勤县');
INSERT INTO `city` VALUES (3096, '542600', '林芝地区', '540000', '西藏自治区林芝地区');
INSERT INTO `city` VALUES (3097, '542621', '林芝县', '542600', '西藏自治区林芝地区林芝县');
INSERT INTO `city` VALUES (3098, '542622', '工布江达县', '542600', '西藏自治区林芝地区工布江达县');
INSERT INTO `city` VALUES (3099, '542623', '米林县', '542600', '西藏自治区林芝地区米林县');
INSERT INTO `city` VALUES (3100, '542624', '墨脱县', '542600', '西藏自治区林芝地区墨脱县');
INSERT INTO `city` VALUES (3101, '542625', '波密县', '542600', '西藏自治区林芝地区波密县');
INSERT INTO `city` VALUES (3102, '542626', '察隅县', '542600', '西藏自治区林芝地区察隅县');
INSERT INTO `city` VALUES (3103, '542627', '朗县', '542600', '西藏自治区林芝地区朗县');
INSERT INTO `city` VALUES (3104, '610000', '陕西省', '0', '陕西省');
INSERT INTO `city` VALUES (3105, '610100', '西安市', '610000', '陕西省西安市');
INSERT INTO `city` VALUES (3106, '610101', '长安区', '610100', '陕西省西安市长安区');
INSERT INTO `city` VALUES (3107, '610102', '新城区', '610100', '陕西省西安市新城区');
INSERT INTO `city` VALUES (3108, '610103', '碑林区', '610100', '陕西省西安市碑林区');
INSERT INTO `city` VALUES (3109, '610104', '莲湖区', '610100', '陕西省西安市莲湖区');
INSERT INTO `city` VALUES (3110, '610111', '灞桥区', '610100', '陕西省西安市灞桥区');
INSERT INTO `city` VALUES (3111, '610112', '未央区', '610100', '陕西省西安市未央区');
INSERT INTO `city` VALUES (3112, '610113', '雁塔区', '610100', '陕西省西安市雁塔区');
INSERT INTO `city` VALUES (3113, '610114', '阎良区', '610100', '陕西省西安市阎良区');
INSERT INTO `city` VALUES (3114, '610115', '临潼区', '610100', '陕西省西安市临潼区');
INSERT INTO `city` VALUES (3115, '610122', '蓝田县', '610100', '陕西省西安市蓝田县');
INSERT INTO `city` VALUES (3116, '610124', '周至县', '610100', '陕西省西安市周至县');
INSERT INTO `city` VALUES (3117, '610125', '户县', '610100', '陕西省西安市户县');
INSERT INTO `city` VALUES (3118, '610126', '高陵县', '610100', '陕西省西安市高陵县');
INSERT INTO `city` VALUES (3119, '610200', '铜川市', '610000', '陕西省铜川市');
INSERT INTO `city` VALUES (3120, '610201', '市辖区', '610200', '陕西省铜川市市辖区');
INSERT INTO `city` VALUES (3121, '610202', '王益区', '610201', NULL);
INSERT INTO `city` VALUES (3122, '610203', '印台区', '610201', NULL);
INSERT INTO `city` VALUES (3123, '610204', '耀州区', '610201', NULL);
INSERT INTO `city` VALUES (3124, '610222', '宜君县', '610200', '陕西省铜川市宜君县');
INSERT INTO `city` VALUES (3125, '610300', '宝鸡市', '610000', '陕西省宝鸡市');
INSERT INTO `city` VALUES (3126, '610301', '市辖区', '610300', '陕西省宝鸡市市辖区');
INSERT INTO `city` VALUES (3127, '610302', '渭滨区', '610301', NULL);
INSERT INTO `city` VALUES (3128, '610303', '金台区', '610301', NULL);
INSERT INTO `city` VALUES (3129, '610304', '陈仓区', '610301', NULL);
INSERT INTO `city` VALUES (3130, '610322', '凤翔县', '610300', '陕西省宝鸡市凤翔县');
INSERT INTO `city` VALUES (3131, '610323', '岐山县', '610300', '陕西省宝鸡市岐山县');
INSERT INTO `city` VALUES (3132, '610324', '扶风县', '610300', '陕西省宝鸡市扶风县');
INSERT INTO `city` VALUES (3133, '610326', '眉县', '610300', '陕西省宝鸡市眉县');
INSERT INTO `city` VALUES (3134, '610327', '陇县', '610300', '陕西省宝鸡市陇县');
INSERT INTO `city` VALUES (3135, '610328', '千阳县', '610300', '陕西省宝鸡市千阳县');
INSERT INTO `city` VALUES (3136, '610329', '麟游县', '610300', '陕西省宝鸡市麟游县');
INSERT INTO `city` VALUES (3137, '610330', '凤县', '610300', '陕西省宝鸡市凤县');
INSERT INTO `city` VALUES (3138, '610331', '太白县', '610300', '陕西省宝鸡市太白县');
INSERT INTO `city` VALUES (3139, '610400', '咸阳市', '610000', '陕西省咸阳市');
INSERT INTO `city` VALUES (3140, '610401', '市辖区', '610400', '陕西省咸阳市市辖区');
INSERT INTO `city` VALUES (3141, '610402', '秦都区', '610401', NULL);
INSERT INTO `city` VALUES (3142, '610404', '渭城区', '610401', NULL);
INSERT INTO `city` VALUES (3143, '610422', '三原县', '610400', '陕西省咸阳市三原县');
INSERT INTO `city` VALUES (3144, '610423', '泾阳县', '610400', '陕西省咸阳市泾阳县');
INSERT INTO `city` VALUES (3145, '610424', '乾县', '610400', '陕西省咸阳市乾县');
INSERT INTO `city` VALUES (3146, '610425', '礼泉县', '610400', '陕西省咸阳市礼泉县');
INSERT INTO `city` VALUES (3147, '610426', '永寿县', '610400', '陕西省咸阳市永寿县');
INSERT INTO `city` VALUES (3148, '610427', '彬县', '610400', '陕西省咸阳市彬县');
INSERT INTO `city` VALUES (3149, '610428', '长武县', '610400', '陕西省咸阳市长武县');
INSERT INTO `city` VALUES (3150, '610429', '旬邑县', '610400', '陕西省咸阳市旬邑县');
INSERT INTO `city` VALUES (3151, '610430', '淳化县', '610400', '陕西省咸阳市淳化县');
INSERT INTO `city` VALUES (3152, '610431', '武功县', '610400', '陕西省咸阳市武功县');
INSERT INTO `city` VALUES (3153, '610481', '兴平市', '610400', '陕西省咸阳市兴平市');
INSERT INTO `city` VALUES (3154, '610500', '渭南市', '610000', '陕西省渭南市');
INSERT INTO `city` VALUES (3155, '610501', '市辖区', '610500', '陕西省渭南市市辖区');
INSERT INTO `city` VALUES (3156, '610502', '临渭区', '610501', NULL);
INSERT INTO `city` VALUES (3157, '610521', '华县', '610500', '陕西省渭南市华县');
INSERT INTO `city` VALUES (3158, '610522', '潼关县', '610500', '陕西省渭南市潼关县');
INSERT INTO `city` VALUES (3159, '610523', '大荔县', '610500', '陕西省渭南市大荔县');
INSERT INTO `city` VALUES (3160, '610524', '合阳县', '610500', '陕西省渭南市合阳县');
INSERT INTO `city` VALUES (3161, '610525', '澄城县', '610500', '陕西省渭南市澄城县');
INSERT INTO `city` VALUES (3162, '610526', '蒲城县', '610500', '陕西省渭南市蒲城县');
INSERT INTO `city` VALUES (3163, '610527', '白水县', '610500', '陕西省渭南市白水县');
INSERT INTO `city` VALUES (3164, '610528', '富平县', '610500', '陕西省渭南市富平县');
INSERT INTO `city` VALUES (3165, '610581', '韩城市', '610500', '陕西省渭南市韩城市');
INSERT INTO `city` VALUES (3166, '610582', '华阴市', '610500', '陕西省渭南市华阴市');
INSERT INTO `city` VALUES (3167, '610600', '延安市', '610000', '陕西省延安市');
INSERT INTO `city` VALUES (3168, '610601', '市辖区', '610600', '陕西省延安市市辖区');
INSERT INTO `city` VALUES (3169, '610602', '宝塔区', '610601', NULL);
INSERT INTO `city` VALUES (3170, '610621', '延长县', '610600', '陕西省延安市延长县');
INSERT INTO `city` VALUES (3171, '610622', '延川县', '610600', '陕西省延安市延川县');
INSERT INTO `city` VALUES (3172, '610623', '子长县', '610600', '陕西省延安市子长县');
INSERT INTO `city` VALUES (3173, '610624', '安塞县', '610600', '陕西省延安市安塞县');
INSERT INTO `city` VALUES (3174, '610625', '志丹县', '610600', '陕西省延安市志丹县');
INSERT INTO `city` VALUES (3175, '610626', '吴起县', '610600', '陕西省延安市吴起县');
INSERT INTO `city` VALUES (3176, '610627', '甘泉县', '610600', '陕西省延安市甘泉县');
INSERT INTO `city` VALUES (3177, '610628', '富县', '610600', '陕西省延安市富县');
INSERT INTO `city` VALUES (3178, '610629', '洛川县', '610600', '陕西省延安市洛川县');
INSERT INTO `city` VALUES (3179, '610630', '宜川县', '610600', '陕西省延安市宜川县');
INSERT INTO `city` VALUES (3180, '610631', '黄龙县', '610600', '陕西省延安市黄龙县');
INSERT INTO `city` VALUES (3181, '610632', '黄陵县', '610600', '陕西省延安市黄陵县');
INSERT INTO `city` VALUES (3182, '610700', '汉中市', '610000', '陕西省汉中市');
INSERT INTO `city` VALUES (3183, '610701', '市辖区', '610700', '陕西省汉中市市辖区');
INSERT INTO `city` VALUES (3184, '610702', '汉台区', '610701', NULL);
INSERT INTO `city` VALUES (3185, '610721', '南郑县', '610700', '陕西省汉中市南郑县');
INSERT INTO `city` VALUES (3186, '610722', '城固县', '610700', '陕西省汉中市城固县');
INSERT INTO `city` VALUES (3187, '610723', '洋县', '610700', '陕西省汉中市洋县');
INSERT INTO `city` VALUES (3188, '610724', '西乡县', '610700', '陕西省汉中市西乡县');
INSERT INTO `city` VALUES (3189, '610725', '勉县', '610700', '陕西省汉中市勉县');
INSERT INTO `city` VALUES (3190, '610726', '宁强县', '610700', '陕西省汉中市宁强县');
INSERT INTO `city` VALUES (3191, '610727', '略阳县', '610700', '陕西省汉中市略阳县');
INSERT INTO `city` VALUES (3192, '610728', '镇巴县', '610700', '陕西省汉中市镇巴县');
INSERT INTO `city` VALUES (3193, '610729', '留坝县', '610700', '陕西省汉中市留坝县');
INSERT INTO `city` VALUES (3194, '610730', '佛坪县', '610700', '陕西省汉中市佛坪县');
INSERT INTO `city` VALUES (3195, '610800', '榆林市', '610000', '陕西省榆林市');
INSERT INTO `city` VALUES (3196, '610801', '市辖区', '610800', '陕西省榆林市市辖区');
INSERT INTO `city` VALUES (3197, '610802', '榆阳区', '610800', '陕西省榆林市榆阳区');
INSERT INTO `city` VALUES (3198, '610821', '神木县', '610800', '陕西省榆林市神木县');
INSERT INTO `city` VALUES (3199, '610822', '府谷县', '610800', '陕西省榆林市府谷县');
INSERT INTO `city` VALUES (3200, '610823', '横山县', '610800', '陕西省榆林市横山县');
INSERT INTO `city` VALUES (3201, '610824', '靖边县', '610800', '陕西省榆林市靖边县');
INSERT INTO `city` VALUES (3202, '610825', '定边县', '610800', '陕西省榆林市定边县');
INSERT INTO `city` VALUES (3203, '610826', '绥德县', '610800', '陕西省榆林市绥德县');
INSERT INTO `city` VALUES (3204, '610827', '米脂县', '610800', '陕西省榆林市米脂县');
INSERT INTO `city` VALUES (3205, '610828', '佳县', '610800', '陕西省榆林市佳县');
INSERT INTO `city` VALUES (3206, '610829', '吴堡县', '610800', '陕西省榆林市吴堡县');
INSERT INTO `city` VALUES (3207, '610830', '清涧县', '610800', '陕西省榆林市清涧县');
INSERT INTO `city` VALUES (3208, '610831', '子洲县', '610800', '陕西省榆林市子洲县');
INSERT INTO `city` VALUES (3209, '610900', '安康市', '610000', '陕西省安康市');
INSERT INTO `city` VALUES (3210, '610901', '市辖区', '610900', '陕西省安康市市辖区');
INSERT INTO `city` VALUES (3211, '610902', '汉滨区', '610901', NULL);
INSERT INTO `city` VALUES (3212, '610921', '汉阴县', '610900', '陕西省安康市汉阴县');
INSERT INTO `city` VALUES (3213, '610922', '石泉县', '610900', '陕西省安康市石泉县');
INSERT INTO `city` VALUES (3214, '610923', '宁陕县', '610900', '陕西省安康市宁陕县');
INSERT INTO `city` VALUES (3215, '610924', '紫阳县', '610900', '陕西省安康市紫阳县');
INSERT INTO `city` VALUES (3216, '610925', '岚皋县', '610900', '陕西省安康市岚皋县');
INSERT INTO `city` VALUES (3217, '610926', '平利县', '610900', '陕西省安康市平利县');
INSERT INTO `city` VALUES (3218, '610927', '镇坪县', '610900', '陕西省安康市镇坪县');
INSERT INTO `city` VALUES (3219, '610928', '旬阳县', '610900', '陕西省安康市旬阳县');
INSERT INTO `city` VALUES (3220, '610929', '白河县', '610900', '陕西省安康市白河县');
INSERT INTO `city` VALUES (3221, '611000', '商洛市', '610000', '陕西省商洛市');
INSERT INTO `city` VALUES (3222, '611001', '市辖区', '611000', '陕西省商洛市市辖区');
INSERT INTO `city` VALUES (3223, '611002', '商州区', '611001', NULL);
INSERT INTO `city` VALUES (3224, '611021', '洛南县', '611000', '陕西省商洛市洛南县');
INSERT INTO `city` VALUES (3225, '611022', '丹凤县', '611000', '陕西省商洛市丹凤县');
INSERT INTO `city` VALUES (3226, '611023', '商南县', '611000', '陕西省商洛市商南县');
INSERT INTO `city` VALUES (3227, '611024', '山阳县', '611000', '陕西省商洛市山阳县');
INSERT INTO `city` VALUES (3228, '611025', '镇安县', '611000', '陕西省商洛市镇安县');
INSERT INTO `city` VALUES (3229, '611026', '柞水县', '611000', '陕西省商洛市柞水县');
INSERT INTO `city` VALUES (3230, '611100', '杨凌示范区', '610000', '陕西省杨凌示范区');
INSERT INTO `city` VALUES (3231, '611103', '杨凌区', '611100', '陕西省杨凌示范区杨凌区');
INSERT INTO `city` VALUES (3232, '620000', '甘肃省', '0', '甘肃省');
INSERT INTO `city` VALUES (3233, '620100', '兰州市', '620000', '甘肃省兰州市');
INSERT INTO `city` VALUES (3234, '620101', '市辖区', '620100', '甘肃省兰州市市辖区');
INSERT INTO `city` VALUES (3235, '620102', '城关区', '620101', NULL);
INSERT INTO `city` VALUES (3236, '620103', '七里河区', '620101', NULL);
INSERT INTO `city` VALUES (3237, '620104', '西固区', '620101', NULL);
INSERT INTO `city` VALUES (3238, '620105', '安宁区', '620101', NULL);
INSERT INTO `city` VALUES (3239, '620111', '红古区', '620101', NULL);
INSERT INTO `city` VALUES (3240, '620121', '永登县', '620100', '甘肃省兰州市永登县');
INSERT INTO `city` VALUES (3241, '620122', '皋兰县', '620100', '甘肃省兰州市皋兰县');
INSERT INTO `city` VALUES (3242, '620123', '榆中县', '620100', '甘肃省兰州市榆中县');
INSERT INTO `city` VALUES (3243, '620200', '嘉峪关市', '620000', '甘肃省嘉峪关市');
INSERT INTO `city` VALUES (3244, '620201', '市辖区', '620200', '甘肃省嘉峪关市市辖区');
INSERT INTO `city` VALUES (3245, '620300', '金昌市', '620000', '甘肃省金昌市');
INSERT INTO `city` VALUES (3246, '620301', '金川区', '620300', '甘肃省金昌市金川区');
INSERT INTO `city` VALUES (3247, '620321', '永昌县', '620300', '甘肃省金昌市永昌县');
INSERT INTO `city` VALUES (3248, '620400', '白银市', '620000', '甘肃省白银市');
INSERT INTO `city` VALUES (3249, '620401', '市辖区', '620400', '甘肃省白银市市辖区');
INSERT INTO `city` VALUES (3250, '620402', '白银区', '620401', NULL);
INSERT INTO `city` VALUES (3251, '620403', '平川区', '620401', NULL);
INSERT INTO `city` VALUES (3252, '620421', '靖远县', '620400', '甘肃省白银市靖远县');
INSERT INTO `city` VALUES (3253, '620422', '会宁县', '620400', '甘肃省白银市会宁县');
INSERT INTO `city` VALUES (3254, '620423', '景泰县', '620400', '甘肃省白银市景泰县');
INSERT INTO `city` VALUES (3255, '620500', '天水市', '620000', '甘肃省天水市');
INSERT INTO `city` VALUES (3256, '620501', '麦积区', '620500', '甘肃省天水市麦积区');
INSERT INTO `city` VALUES (3257, '620502', '秦州区', '620500', '甘肃省天水市秦州区');
INSERT INTO `city` VALUES (3258, '620521', '清水县', '620500', '甘肃省天水市清水县');
INSERT INTO `city` VALUES (3259, '620522', '秦安县', '620500', '甘肃省天水市秦安县');
INSERT INTO `city` VALUES (3260, '620523', '甘谷县', '620500', '甘肃省天水市甘谷县');
INSERT INTO `city` VALUES (3261, '620524', '武山县', '620500', '甘肃省天水市武山县');
INSERT INTO `city` VALUES (3262, '620525', '张家川回族自治县', '620500', '甘肃省天水市张家川回族自治县');
INSERT INTO `city` VALUES (3263, '620600', '武威市', '620000', '甘肃省武威市');
INSERT INTO `city` VALUES (3264, '620601', '市辖区', '620600', '甘肃省武威市市辖区');
INSERT INTO `city` VALUES (3265, '620602', '凉州区', '620601', NULL);
INSERT INTO `city` VALUES (3266, '620621', '民勤县', '620600', '甘肃省武威市民勤县');
INSERT INTO `city` VALUES (3267, '620622', '古浪县', '620600', '甘肃省武威市古浪县');
INSERT INTO `city` VALUES (3268, '620623', '天祝藏族自治县', '620600', '甘肃省武威市天祝藏族自治县');
INSERT INTO `city` VALUES (3269, '620700', '张掖市', '620000', '甘肃省张掖市');
INSERT INTO `city` VALUES (3270, '620701', '市辖区', '620700', '甘肃省张掖市市辖区');
INSERT INTO `city` VALUES (3271, '620702', '甘州区', '620701', NULL);
INSERT INTO `city` VALUES (3272, '620721', '肃南裕固族自治县', '620700', '甘肃省张掖市肃南裕固族自治县');
INSERT INTO `city` VALUES (3273, '620722', '民乐县', '620700', '甘肃省张掖市民乐县');
INSERT INTO `city` VALUES (3274, '620723', '临泽县', '620700', '甘肃省张掖市临泽县');
INSERT INTO `city` VALUES (3275, '620724', '高台县', '620700', '甘肃省张掖市高台县');
INSERT INTO `city` VALUES (3276, '620725', '山丹县', '620700', '甘肃省张掖市山丹县');
INSERT INTO `city` VALUES (3277, '620800', '平凉市', '620000', '甘肃省平凉市');
INSERT INTO `city` VALUES (3278, '620801', '市辖区', '620800', '甘肃省平凉市市辖区');
INSERT INTO `city` VALUES (3279, '620802', '崆峒区', '620801', NULL);
INSERT INTO `city` VALUES (3280, '620821', '泾川县', '620800', '甘肃省平凉市泾川县');
INSERT INTO `city` VALUES (3281, '620822', '灵台县', '620800', '甘肃省平凉市灵台县');
INSERT INTO `city` VALUES (3282, '620823', '崇信县', '620800', '甘肃省平凉市崇信县');
INSERT INTO `city` VALUES (3283, '620824', '华亭县', '620800', '甘肃省平凉市华亭县');
INSERT INTO `city` VALUES (3284, '620825', '庄浪县', '620800', '甘肃省平凉市庄浪县');
INSERT INTO `city` VALUES (3285, '620826', '静宁县', '620800', '甘肃省平凉市静宁县');
INSERT INTO `city` VALUES (3286, '620900', '酒泉市', '620000', '甘肃省酒泉市');
INSERT INTO `city` VALUES (3287, '620901', '市辖区', '620900', '甘肃省酒泉市市辖区');
INSERT INTO `city` VALUES (3288, '620902', '肃州区', '620901', NULL);
INSERT INTO `city` VALUES (3289, '620921', '金塔县', '620900', '甘肃省酒泉市金塔县');
INSERT INTO `city` VALUES (3290, '620922', '瓜洲县', '620900', '甘肃省酒泉市瓜洲县');
INSERT INTO `city` VALUES (3291, '620923', '肃北蒙古族自治县', '620900', '甘肃省酒泉市肃北蒙古族自治县');
INSERT INTO `city` VALUES (3292, '620924', '阿克塞哈萨克族自治县', '620900', '甘肃省酒泉市阿克塞哈萨克族自治县');
INSERT INTO `city` VALUES (3293, '620981', '玉门市', '620900', '甘肃省酒泉市玉门市');
INSERT INTO `city` VALUES (3294, '620982', '敦煌市', '620900', '甘肃省酒泉市敦煌市');
INSERT INTO `city` VALUES (3295, '621000', '庆阳市', '620000', '甘肃省庆阳市');
INSERT INTO `city` VALUES (3296, '621001', '市辖区', '621000', '甘肃省庆阳市市辖区');
INSERT INTO `city` VALUES (3297, '621002', '西峰区', '621001', NULL);
INSERT INTO `city` VALUES (3298, '621021', '庆城县', '621000', '甘肃省庆阳市庆城县');
INSERT INTO `city` VALUES (3299, '621022', '环县', '621000', '甘肃省庆阳市环县');
INSERT INTO `city` VALUES (3300, '621023', '华池县', '621000', '甘肃省庆阳市华池县');
INSERT INTO `city` VALUES (3301, '621024', '合水县', '621000', '甘肃省庆阳市合水县');
INSERT INTO `city` VALUES (3302, '621025', '正宁县', '621000', '甘肃省庆阳市正宁县');
INSERT INTO `city` VALUES (3303, '621026', '宁县', '621000', '甘肃省庆阳市宁县');
INSERT INTO `city` VALUES (3304, '621027', '镇原县', '621000', '甘肃省庆阳市镇原县');
INSERT INTO `city` VALUES (3305, '621100', '定西市', '620000', '甘肃省定西市');
INSERT INTO `city` VALUES (3306, '621101', '市辖区', '621100', '甘肃省定西市市辖区');
INSERT INTO `city` VALUES (3307, '621102', '安定区', '621101', NULL);
INSERT INTO `city` VALUES (3308, '621121', '通渭县', '621100', '甘肃省定西市通渭县');
INSERT INTO `city` VALUES (3309, '621122', '陇西县', '621100', '甘肃省定西市陇西县');
INSERT INTO `city` VALUES (3310, '621123', '渭源县', '621100', '甘肃省定西市渭源县');
INSERT INTO `city` VALUES (3311, '621124', '临洮县', '621100', '甘肃省定西市临洮县');
INSERT INTO `city` VALUES (3312, '621125', '漳县', '621100', '甘肃省定西市漳县');
INSERT INTO `city` VALUES (3313, '621126', '岷县', '621100', '甘肃省定西市岷县');
INSERT INTO `city` VALUES (3314, '621200', '陇南市', '620000', '甘肃省陇南市');
INSERT INTO `city` VALUES (3315, '621201', '武都区', '621200', '甘肃省陇南市武都区');
INSERT INTO `city` VALUES (3316, '621221', '成县', '621200', '甘肃省陇南市成县');
INSERT INTO `city` VALUES (3317, '621222', '文县', '621200', '甘肃省陇南市文县');
INSERT INTO `city` VALUES (3318, '621223', '宕昌县', '621200', '甘肃省陇南市宕昌县');
INSERT INTO `city` VALUES (3319, '621224', '康县', '621200', '甘肃省陇南市康县');
INSERT INTO `city` VALUES (3320, '621225', '西和县', '621200', '甘肃省陇南市西和县');
INSERT INTO `city` VALUES (3321, '621226', '礼县', '621200', '甘肃省陇南市礼县');
INSERT INTO `city` VALUES (3322, '621227', '徽县', '621200', '甘肃省陇南市徽县');
INSERT INTO `city` VALUES (3323, '621228', '两当县', '621200', '甘肃省陇南市两当县');
INSERT INTO `city` VALUES (3324, '622900', '临夏回族自治州', '620000', '甘肃省临夏回族自治州');
INSERT INTO `city` VALUES (3325, '622901', '临夏市', '622900', '甘肃省临夏回族自治州临夏市');
INSERT INTO `city` VALUES (3326, '622921', '临夏县', '622900', '甘肃省临夏回族自治州临夏县');
INSERT INTO `city` VALUES (3327, '622922', '康乐县', '622900', '甘肃省临夏回族自治州康乐县');
INSERT INTO `city` VALUES (3328, '622923', '永靖县', '622900', '甘肃省临夏回族自治州永靖县');
INSERT INTO `city` VALUES (3329, '622924', '广河县', '622900', '甘肃省临夏回族自治州广河县');
INSERT INTO `city` VALUES (3330, '622925', '和政县', '622900', '甘肃省临夏回族自治州和政县');
INSERT INTO `city` VALUES (3331, '622926', '东乡族自治县', '622900', '甘肃省临夏回族自治州东乡族自治县');
INSERT INTO `city` VALUES (3332, '622927', '积石山保安族东乡族撒拉族自治县', '622900', '甘肃省临夏回族自治州积石山保安族东乡族撒拉族自治县');
INSERT INTO `city` VALUES (3333, '623000', '甘南藏族自治州', '620000', '甘肃省甘南藏族自治州');
INSERT INTO `city` VALUES (3334, '623001', '合作市', '623000', '甘肃省甘南藏族自治州合作市');
INSERT INTO `city` VALUES (3335, '623021', '临潭县', '623000', '甘肃省甘南藏族自治州临潭县');
INSERT INTO `city` VALUES (3336, '623022', '卓尼县', '623000', '甘肃省甘南藏族自治州卓尼县');
INSERT INTO `city` VALUES (3337, '623023', '舟曲县', '623000', '甘肃省甘南藏族自治州舟曲县');
INSERT INTO `city` VALUES (3338, '623024', '迭部县', '623000', '甘肃省甘南藏族自治州迭部县');
INSERT INTO `city` VALUES (3339, '623025', '玛曲县', '623000', '甘肃省甘南藏族自治州玛曲县');
INSERT INTO `city` VALUES (3340, '623026', '碌曲县', '623000', '甘肃省甘南藏族自治州碌曲县');
INSERT INTO `city` VALUES (3341, '623027', '夏河县', '623000', '甘肃省甘南藏族自治州夏河县');
INSERT INTO `city` VALUES (3342, '630000', '青海省', '0', '青海省');
INSERT INTO `city` VALUES (3343, '630100', '西宁市', '630000', '青海省西宁市');
INSERT INTO `city` VALUES (3344, '630101', '市辖区', '630100', '青海省西宁市市辖区');
INSERT INTO `city` VALUES (3345, '630102', '城东区', '630101', NULL);
INSERT INTO `city` VALUES (3346, '630103', '城中区', '630101', NULL);
INSERT INTO `city` VALUES (3347, '630104', '城西区', '630101', NULL);
INSERT INTO `city` VALUES (3348, '630105', '城北区', '630101', NULL);
INSERT INTO `city` VALUES (3349, '630121', '大通回族土族自治县', '630100', '青海省西宁市大通回族土族自治县');
INSERT INTO `city` VALUES (3350, '630122', '湟中县', '630100', '青海省西宁市湟中县');
INSERT INTO `city` VALUES (3351, '630123', '湟源县', '630100', '青海省西宁市湟源县');
INSERT INTO `city` VALUES (3352, '632100', '海东市', '630000', '青海省海东市');
INSERT INTO `city` VALUES (3353, '632121', '平安县', '632100', '青海省海东市平安县');
INSERT INTO `city` VALUES (3354, '632122', '民和回族土族自治县', '632100', '青海省海东市民和回族土族自治县');
INSERT INTO `city` VALUES (3355, '632123', '乐都区', '632100', '青海省海东市乐都区');
INSERT INTO `city` VALUES (3356, '632126', '互助土族自治县', '632100', '青海省海东市互助土族自治县');
INSERT INTO `city` VALUES (3357, '632127', '化隆回族自治县', '632100', '青海省海东市化隆回族自治县');
INSERT INTO `city` VALUES (3358, '632128', '循化撒拉族自治县', '632100', '青海省海东市循化撒拉族自治县');
INSERT INTO `city` VALUES (3359, '632200', '海北藏族自治州', '630000', '青海省海北藏族自治州');
INSERT INTO `city` VALUES (3360, '632221', '门源回族自治县', '632200', '青海省海北藏族自治州门源回族自治县');
INSERT INTO `city` VALUES (3361, '632222', '祁连县', '632200', '青海省海北藏族自治州祁连县');
INSERT INTO `city` VALUES (3362, '632223', '海晏县', '632200', '青海省海北藏族自治州海晏县');
INSERT INTO `city` VALUES (3363, '632224', '刚察县', '632200', '青海省海北藏族自治州刚察县');
INSERT INTO `city` VALUES (3364, '632300', '黄南藏族自治州', '630000', '青海省黄南藏族自治州');
INSERT INTO `city` VALUES (3365, '632321', '同仁县', '632300', '青海省黄南藏族自治州同仁县');
INSERT INTO `city` VALUES (3366, '632322', '尖扎县', '632300', '青海省黄南藏族自治州尖扎县');
INSERT INTO `city` VALUES (3367, '632323', '泽库县', '632300', '青海省黄南藏族自治州泽库县');
INSERT INTO `city` VALUES (3368, '632324', '河南蒙古族自治县', '632300', '青海省黄南藏族自治州河南蒙古族自治县');
INSERT INTO `city` VALUES (3369, '632500', '海南藏族自治州', '630000', '青海省海南藏族自治州');
INSERT INTO `city` VALUES (3370, '632521', '共和县', '632500', '青海省海南藏族自治州共和县');
INSERT INTO `city` VALUES (3371, '632522', '同德县', '632500', '青海省海南藏族自治州同德县');
INSERT INTO `city` VALUES (3372, '632523', '贵德县', '632500', '青海省海南藏族自治州贵德县');
INSERT INTO `city` VALUES (3373, '632524', '兴海县', '632500', '青海省海南藏族自治州兴海县');
INSERT INTO `city` VALUES (3374, '632525', '贵南县', '632500', '青海省海南藏族自治州贵南县');
INSERT INTO `city` VALUES (3375, '632600', '果洛藏族自治州', '630000', '青海省果洛藏族自治州');
INSERT INTO `city` VALUES (3376, '632621', '玛沁县', '632600', '青海省果洛藏族自治州玛沁县');
INSERT INTO `city` VALUES (3377, '632622', '班玛县', '632600', '青海省果洛藏族自治州班玛县');
INSERT INTO `city` VALUES (3378, '632623', '甘德县', '632600', '青海省果洛藏族自治州甘德县');
INSERT INTO `city` VALUES (3379, '632624', '达日县', '632600', '青海省果洛藏族自治州达日县');
INSERT INTO `city` VALUES (3380, '632625', '久治县', '632600', '青海省果洛藏族自治州久治县');
INSERT INTO `city` VALUES (3381, '632626', '玛多县', '632600', '青海省果洛藏族自治州玛多县');
INSERT INTO `city` VALUES (3382, '632700', '玉树藏族自治州', '630000', '青海省玉树藏族自治州');
INSERT INTO `city` VALUES (3383, '632721', '玉树县', '632700', '青海省玉树藏族自治州玉树县');
INSERT INTO `city` VALUES (3384, '632722', '杂多县', '632700', '青海省玉树藏族自治州杂多县');
INSERT INTO `city` VALUES (3385, '632723', '称多县', '632700', '青海省玉树藏族自治州称多县');
INSERT INTO `city` VALUES (3386, '632724', '治多县', '632700', '青海省玉树藏族自治州治多县');
INSERT INTO `city` VALUES (3387, '632725', '囊谦县', '632700', '青海省玉树藏族自治州囊谦县');
INSERT INTO `city` VALUES (3388, '632726', '曲麻莱县', '632700', '青海省玉树藏族自治州曲麻莱县');
INSERT INTO `city` VALUES (3389, '632800', '海西蒙古族藏族自治州', '630000', '青海省海西蒙古族藏族自治州');
INSERT INTO `city` VALUES (3390, '632801', '格尔木市', '632800', '青海省海西蒙古族藏族自治州格尔木市');
INSERT INTO `city` VALUES (3391, '632802', '德令哈市', '632800', '青海省海西蒙古族藏族自治州德令哈市');
INSERT INTO `city` VALUES (3392, '632821', '乌兰县', '632800', '青海省海西蒙古族藏族自治州乌兰县');
INSERT INTO `city` VALUES (3393, '632822', '都兰县', '632800', '青海省海西蒙古族藏族自治州都兰县');
INSERT INTO `city` VALUES (3394, '632823', '天峻县', '632800', '青海省海西蒙古族藏族自治州天峻县');
INSERT INTO `city` VALUES (3395, '640000', '宁夏回族自治区', '0', '宁夏回族自治区');
INSERT INTO `city` VALUES (3396, '640100', '银川市', '640000', '宁夏回族自治区银川市');
INSERT INTO `city` VALUES (3397, '640104', '兴庆区', '640100', '宁夏回族自治区银川市兴庆区');
INSERT INTO `city` VALUES (3398, '640105', '西夏区', '640100', '宁夏回族自治区银川市西夏区');
INSERT INTO `city` VALUES (3399, '640106', '金凤区', '640100', '宁夏回族自治区银川市金凤区');
INSERT INTO `city` VALUES (3400, '640121', '永宁县', '640100', '宁夏回族自治区银川市永宁县');
INSERT INTO `city` VALUES (3401, '640122', '贺兰县', '640100', '宁夏回族自治区银川市贺兰县');
INSERT INTO `city` VALUES (3402, '640181', '灵武市', '640100', '宁夏回族自治区银川市灵武市');
INSERT INTO `city` VALUES (3403, '640200', '石嘴山市', '640000', '宁夏回族自治区石嘴山市');
INSERT INTO `city` VALUES (3404, '640202', '大武口区', '640200', '宁夏回族自治区石嘴山市大武口区');
INSERT INTO `city` VALUES (3405, '640205', '惠农县', '640200', '宁夏回族自治区石嘴山市惠农县');
INSERT INTO `city` VALUES (3406, '640221', '平罗县', '640200', '宁夏回族自治区石嘴山市平罗县');
INSERT INTO `city` VALUES (3407, '640300', '吴忠市', '640000', '宁夏回族自治区吴忠市');
INSERT INTO `city` VALUES (3408, '640301', '红寺堡区', '640300', '宁夏回族自治区吴忠市红寺堡区');
INSERT INTO `city` VALUES (3409, '640302', '利通区', '640300', '宁夏回族自治区吴忠市利通区');
INSERT INTO `city` VALUES (3410, '640323', '盐池县', '640300', '宁夏回族自治区吴忠市盐池县');
INSERT INTO `city` VALUES (3411, '640324', '同心县', '640300', '宁夏回族自治区吴忠市同心县');
INSERT INTO `city` VALUES (3412, '640381', '青铜峡市', '640300', '宁夏回族自治区吴忠市青铜峡市');
INSERT INTO `city` VALUES (3413, '640400', '固原市', '640000', '宁夏回族自治区固原市');
INSERT INTO `city` VALUES (3414, '640401', '市辖区', '640400', '宁夏回族自治区固原市市辖区');
INSERT INTO `city` VALUES (3415, '640402', '原州区', '640400', '宁夏回族自治区固原市原州区');
INSERT INTO `city` VALUES (3416, '640422', '西吉县', '640400', '宁夏回族自治区固原市西吉县');
INSERT INTO `city` VALUES (3417, '640423', '隆德县', '640400', '宁夏回族自治区固原市隆德县');
INSERT INTO `city` VALUES (3418, '640424', '泾源县', '640400', '宁夏回族自治区固原市泾源县');
INSERT INTO `city` VALUES (3419, '640425', '彭阳县', '640400', '宁夏回族自治区固原市彭阳县');
INSERT INTO `city` VALUES (3420, '640500', '中卫市', '640000', '宁夏回族自治区中卫市');
INSERT INTO `city` VALUES (3421, '640501', '市辖区', '640500', '宁夏回族自治区中卫市市辖区');
INSERT INTO `city` VALUES (3422, '640502', '沙坡头区', '640500', '宁夏回族自治区中卫市沙坡头区');
INSERT INTO `city` VALUES (3423, '640521', '中宁县', '640500', '宁夏回族自治区中卫市中宁县');
INSERT INTO `city` VALUES (3424, '640522', '海原县', '640500', '宁夏回族自治区中卫市海原县');
INSERT INTO `city` VALUES (3425, '650000', '新疆维吾尔自治区', '0', '新疆维吾尔自治区');
INSERT INTO `city` VALUES (3426, '650100', '乌鲁木齐市', '650000', '新疆维吾尔自治区乌鲁木齐市');
INSERT INTO `city` VALUES (3427, '650101', '市辖区', '650100', '新疆维吾尔自治区乌鲁木齐市市辖区');
INSERT INTO `city` VALUES (3428, '650102', '天山区', '650101', NULL);
INSERT INTO `city` VALUES (3429, '650103', '沙依巴克区', '650101', NULL);
INSERT INTO `city` VALUES (3430, '650104', '新市区', '650101', NULL);
INSERT INTO `city` VALUES (3431, '650105', '水磨沟区', '650101', NULL);
INSERT INTO `city` VALUES (3432, '650106', '头屯河区', '650101', NULL);
INSERT INTO `city` VALUES (3433, '650107', '达坂城区', '650101', NULL);
INSERT INTO `city` VALUES (3434, '650108', '东山区', '650101', NULL);
INSERT INTO `city` VALUES (3435, '650121', '乌鲁木齐县', '650100', '新疆维吾尔自治区乌鲁木齐市乌鲁木齐县');
INSERT INTO `city` VALUES (3436, '650200', '克拉玛依市', '650000', '新疆维吾尔自治区克拉玛依市');
INSERT INTO `city` VALUES (3437, '650201', '市辖区', '650200', '新疆维吾尔自治区克拉玛依市市辖区');
INSERT INTO `city` VALUES (3438, '650202', '独山子区', '650201', NULL);
INSERT INTO `city` VALUES (3439, '650203', '克拉玛依区', '650201', NULL);
INSERT INTO `city` VALUES (3440, '650204', '白碱滩区', '650201', NULL);
INSERT INTO `city` VALUES (3441, '650205', '乌尔禾区', '650201', NULL);
INSERT INTO `city` VALUES (3442, '652100', '吐鲁番地区', '650000', '新疆维吾尔自治区吐鲁番地区');
INSERT INTO `city` VALUES (3443, '652101', '吐鲁番市', '652100', '新疆维吾尔自治区吐鲁番地区吐鲁番市');
INSERT INTO `city` VALUES (3444, '652122', '鄯善县', '652100', '新疆维吾尔自治区吐鲁番地区鄯善县');
INSERT INTO `city` VALUES (3445, '652123', '托克逊县', '652100', '新疆维吾尔自治区吐鲁番地区托克逊县');
INSERT INTO `city` VALUES (3446, '652200', '哈密地区', '650000', '新疆维吾尔自治区哈密地区');
INSERT INTO `city` VALUES (3447, '652201', '哈密市', '652200', '新疆维吾尔自治区哈密地区哈密市');
INSERT INTO `city` VALUES (3448, '652222', '巴里坤哈萨克自治县', '652200', '新疆维吾尔自治区哈密地区巴里坤哈萨克自治县');
INSERT INTO `city` VALUES (3449, '652223', '伊吾县', '652200', '新疆维吾尔自治区哈密地区伊吾县');
INSERT INTO `city` VALUES (3450, '652300', '昌吉回族自治州', '650000', '新疆维吾尔自治区昌吉回族自治州');
INSERT INTO `city` VALUES (3451, '652301', '昌吉市', '652300', '新疆维吾尔自治区昌吉回族自治州昌吉市');
INSERT INTO `city` VALUES (3452, '652302', '阜康市', '652300', '新疆维吾尔自治区昌吉回族自治州阜康市');
INSERT INTO `city` VALUES (3453, '652303', '米泉市', '652300', '新疆维吾尔自治区昌吉回族自治州米泉市');
INSERT INTO `city` VALUES (3454, '652323', '呼图壁县', '652300', '新疆维吾尔自治区昌吉回族自治州呼图壁县');
INSERT INTO `city` VALUES (3455, '652324', '玛纳斯县', '652300', '新疆维吾尔自治区昌吉回族自治州玛纳斯县');
INSERT INTO `city` VALUES (3456, '652325', '奇台县', '652300', '新疆维吾尔自治区昌吉回族自治州奇台县');
INSERT INTO `city` VALUES (3457, '652327', '吉木萨尔县', '652300', '新疆维吾尔自治区昌吉回族自治州吉木萨尔县');
INSERT INTO `city` VALUES (3458, '652328', '木垒哈萨克自治县', '652300', '新疆维吾尔自治区昌吉回族自治州木垒哈萨克自治县');
INSERT INTO `city` VALUES (3459, '652700', '博尔塔拉蒙古自治州', '650000', '新疆维吾尔自治区博尔塔拉蒙古自治州');
INSERT INTO `city` VALUES (3460, '652701', '博乐市', '652700', '新疆维吾尔自治区博尔塔拉蒙古自治州博乐市');
INSERT INTO `city` VALUES (3461, '652722', '精河县', '652700', '新疆维吾尔自治区博尔塔拉蒙古自治州精河县');
INSERT INTO `city` VALUES (3462, '652723', '温泉县', '652700', '新疆维吾尔自治区博尔塔拉蒙古自治州温泉县');
INSERT INTO `city` VALUES (3463, '652800', '巴音郭楞蒙古自治州', '650000', '新疆维吾尔自治区巴音郭楞蒙古自治州');
INSERT INTO `city` VALUES (3464, '652801', '库尔勒市', '652800', '新疆维吾尔自治区巴音郭楞蒙古自治州库尔勒市');
INSERT INTO `city` VALUES (3465, '652822', '轮台县', '652800', '新疆维吾尔自治区巴音郭楞蒙古自治州轮台县');
INSERT INTO `city` VALUES (3466, '652823', '尉犁县', '652800', '新疆维吾尔自治区巴音郭楞蒙古自治州尉犁县');
INSERT INTO `city` VALUES (3467, '652824', '若羌县', '652800', '新疆维吾尔自治区巴音郭楞蒙古自治州若羌县');
INSERT INTO `city` VALUES (3468, '652825', '且末县', '652800', '新疆维吾尔自治区巴音郭楞蒙古自治州且末县');
INSERT INTO `city` VALUES (3469, '652826', '焉耆回族自治县', '652800', '新疆维吾尔自治区巴音郭楞蒙古自治州焉耆回族自治县');
INSERT INTO `city` VALUES (3470, '652827', '和静县', '652800', '新疆维吾尔自治区巴音郭楞蒙古自治州和静县');
INSERT INTO `city` VALUES (3471, '652828', '和硕县', '652800', '新疆维吾尔自治区巴音郭楞蒙古自治州和硕县');
INSERT INTO `city` VALUES (3472, '652829', '博湖县', '652800', '新疆维吾尔自治区巴音郭楞蒙古自治州博湖县');
INSERT INTO `city` VALUES (3473, '652900', '阿克苏地区', '650000', '新疆维吾尔自治区阿克苏地区');
INSERT INTO `city` VALUES (3474, '652901', '阿克苏市', '652900', '新疆维吾尔自治区阿克苏地区阿克苏市');
INSERT INTO `city` VALUES (3475, '652922', '温宿县', '652900', '新疆维吾尔自治区阿克苏地区温宿县');
INSERT INTO `city` VALUES (3476, '652923', '库车县', '652900', '新疆维吾尔自治区阿克苏地区库车县');
INSERT INTO `city` VALUES (3477, '652924', '沙雅县', '652900', '新疆维吾尔自治区阿克苏地区沙雅县');
INSERT INTO `city` VALUES (3478, '652925', '新和县', '652900', '新疆维吾尔自治区阿克苏地区新和县');
INSERT INTO `city` VALUES (3479, '652926', '拜城县', '652900', '新疆维吾尔自治区阿克苏地区拜城县');
INSERT INTO `city` VALUES (3480, '652927', '乌什县', '652900', '新疆维吾尔自治区阿克苏地区乌什县');
INSERT INTO `city` VALUES (3481, '652928', '阿瓦提县', '652900', '新疆维吾尔自治区阿克苏地区阿瓦提县');
INSERT INTO `city` VALUES (3482, '652929', '柯坪县', '652900', '新疆维吾尔自治区阿克苏地区柯坪县');
INSERT INTO `city` VALUES (3483, '653000', '克孜勒苏柯尔克孜自治州', '650000', '新疆维吾尔自治区克孜勒苏柯尔克孜自治州');
INSERT INTO `city` VALUES (3484, '653001', '阿图什市', '653000', '新疆维吾尔自治区克孜勒苏柯尔克孜自治州阿图什市');
INSERT INTO `city` VALUES (3485, '653022', '阿克陶县', '653000', '新疆维吾尔自治区克孜勒苏柯尔克孜自治州阿克陶县');
INSERT INTO `city` VALUES (3486, '653023', '阿合奇县', '653000', '新疆维吾尔自治区克孜勒苏柯尔克孜自治州阿合奇县');
INSERT INTO `city` VALUES (3487, '653024', '乌恰县', '653000', '新疆维吾尔自治区克孜勒苏柯尔克孜自治州乌恰县');
INSERT INTO `city` VALUES (3488, '653100', '喀什地区', '650000', '新疆维吾尔自治区喀什地区');
INSERT INTO `city` VALUES (3489, '653101', '喀什市', '653100', '新疆维吾尔自治区喀什地区喀什市');
INSERT INTO `city` VALUES (3490, '653121', '疏附县', '653100', '新疆维吾尔自治区喀什地区疏附县');
INSERT INTO `city` VALUES (3491, '653122', '疏勒县', '653100', '新疆维吾尔自治区喀什地区疏勒县');
INSERT INTO `city` VALUES (3492, '653123', '英吉沙县', '653100', '新疆维吾尔自治区喀什地区英吉沙县');
INSERT INTO `city` VALUES (3493, '653124', '泽普县', '653100', '新疆维吾尔自治区喀什地区泽普县');
INSERT INTO `city` VALUES (3494, '653125', '莎车县', '653100', '新疆维吾尔自治区喀什地区莎车县');
INSERT INTO `city` VALUES (3495, '653126', '叶城县', '653100', '新疆维吾尔自治区喀什地区叶城县');
INSERT INTO `city` VALUES (3496, '653127', '麦盖提县', '653100', '新疆维吾尔自治区喀什地区麦盖提县');
INSERT INTO `city` VALUES (3497, '653128', '岳普湖县', '653100', '新疆维吾尔自治区喀什地区岳普湖县');
INSERT INTO `city` VALUES (3498, '653129', '伽师县', '653100', '新疆维吾尔自治区喀什地区伽师县');
INSERT INTO `city` VALUES (3499, '653130', '巴楚县', '653100', '新疆维吾尔自治区喀什地区巴楚县');
INSERT INTO `city` VALUES (3500, '653131', '塔什库尔干塔吉克自治县', '653100', '新疆维吾尔自治区喀什地区塔什库尔干塔吉克自治县');
INSERT INTO `city` VALUES (3501, '653200', '和田地区', '650000', '新疆维吾尔自治区和田地区');
INSERT INTO `city` VALUES (3502, '653201', '和田市', '653200', '新疆维吾尔自治区和田地区和田市');
INSERT INTO `city` VALUES (3503, '653221', '和田县', '653200', '新疆维吾尔自治区和田地区和田县');
INSERT INTO `city` VALUES (3504, '653222', '墨玉县', '653200', '新疆维吾尔自治区和田地区墨玉县');
INSERT INTO `city` VALUES (3505, '653223', '皮山县', '653200', '新疆维吾尔自治区和田地区皮山县');
INSERT INTO `city` VALUES (3506, '653224', '洛浦县', '653200', '新疆维吾尔自治区和田地区洛浦县');
INSERT INTO `city` VALUES (3507, '653225', '策勒县', '653200', '新疆维吾尔自治区和田地区策勒县');
INSERT INTO `city` VALUES (3508, '653226', '于田县', '653200', '新疆维吾尔自治区和田地区于田县');
INSERT INTO `city` VALUES (3509, '653227', '民丰县', '653200', '新疆维吾尔自治区和田地区民丰县');
INSERT INTO `city` VALUES (3510, '654000', '伊犁哈萨克自治州', '650000', '新疆维吾尔自治区伊犁哈萨克自治州');
INSERT INTO `city` VALUES (3511, '654002', '伊宁市', '654000', '新疆维吾尔自治区伊犁哈萨克自治州伊宁市');
INSERT INTO `city` VALUES (3512, '654003', '奎屯市', '654000', '新疆维吾尔自治区伊犁哈萨克自治州奎屯市');
INSERT INTO `city` VALUES (3513, '654021', '伊宁县', '654000', '新疆维吾尔自治区伊犁哈萨克自治州伊宁县');
INSERT INTO `city` VALUES (3514, '654022', '察布查尔锡伯自治县', '654000', '新疆维吾尔自治区伊犁哈萨克自治州察布查尔锡伯自治县');
INSERT INTO `city` VALUES (3515, '654023', '霍城县', '654000', '新疆维吾尔自治区伊犁哈萨克自治州霍城县');
INSERT INTO `city` VALUES (3516, '654024', '巩留县', '654000', '新疆维吾尔自治区伊犁哈萨克自治州巩留县');
INSERT INTO `city` VALUES (3517, '654025', '新源县', '654000', '新疆维吾尔自治区伊犁哈萨克自治州新源县');
INSERT INTO `city` VALUES (3518, '654026', '昭苏县', '654000', '新疆维吾尔自治区伊犁哈萨克自治州昭苏县');
INSERT INTO `city` VALUES (3519, '654027', '特克斯县', '654000', '新疆维吾尔自治区伊犁哈萨克自治州特克斯县');
INSERT INTO `city` VALUES (3520, '654028', '尼勒克县', '654000', '新疆维吾尔自治区伊犁哈萨克自治州尼勒克县');
INSERT INTO `city` VALUES (3521, '654200', '塔城地区', '650000', '新疆维吾尔自治区塔城地区');
INSERT INTO `city` VALUES (3522, '654201', '塔城市', '654200', '新疆维吾尔自治区塔城地区塔城市');
INSERT INTO `city` VALUES (3523, '654202', '乌苏市', '654200', '新疆维吾尔自治区塔城地区乌苏市');
INSERT INTO `city` VALUES (3524, '654221', '额敏县', '654200', '新疆维吾尔自治区塔城地区额敏县');
INSERT INTO `city` VALUES (3525, '654223', '沙湾县', '654200', '新疆维吾尔自治区塔城地区沙湾县');
INSERT INTO `city` VALUES (3526, '654224', '托里县', '654200', '新疆维吾尔自治区塔城地区托里县');
INSERT INTO `city` VALUES (3527, '654225', '裕民县', '654200', '新疆维吾尔自治区塔城地区裕民县');
INSERT INTO `city` VALUES (3528, '654226', '和布克赛尔蒙古自治县', '654200', '新疆维吾尔自治区塔城地区和布克赛尔蒙古自治县');
INSERT INTO `city` VALUES (3529, '654300', '阿勒泰地区', '650000', '新疆维吾尔自治区阿勒泰地区');
INSERT INTO `city` VALUES (3530, '654301', '阿勒泰市', '654300', '新疆维吾尔自治区阿勒泰地区阿勒泰市');
INSERT INTO `city` VALUES (3531, '654321', '布尔津县', '654300', '新疆维吾尔自治区阿勒泰地区布尔津县');
INSERT INTO `city` VALUES (3532, '654322', '富蕴县', '654300', '新疆维吾尔自治区阿勒泰地区富蕴县');
INSERT INTO `city` VALUES (3533, '654323', '福海县', '654300', '新疆维吾尔自治区阿勒泰地区福海县');
INSERT INTO `city` VALUES (3534, '654324', '哈巴河县', '654300', '新疆维吾尔自治区阿勒泰地区哈巴河县');
INSERT INTO `city` VALUES (3535, '654325', '青河县', '654300', '新疆维吾尔自治区阿勒泰地区青河县');
INSERT INTO `city` VALUES (3536, '654326', '吉木乃县', '654300', '新疆维吾尔自治区阿勒泰地区吉木乃县');
INSERT INTO `city` VALUES (3537, '659000', '省直辖行政单位', '650000', '新疆维吾尔自治区省直辖行政单位');
INSERT INTO `city` VALUES (3538, '659001', '石河子市', '659000', '新疆维吾尔自治区省直辖行政单位石河子市');
INSERT INTO `city` VALUES (3539, '659002', '阿拉尔市', '659000', '新疆维吾尔自治区省直辖行政单位阿拉尔市');
INSERT INTO `city` VALUES (3540, '659003', '图木舒克市', '659000', '新疆维吾尔自治区省直辖行政单位图木舒克市');
INSERT INTO `city` VALUES (3541, '659004', '五家渠市', '659000', '新疆维吾尔自治区省直辖行政单位五家渠市');
INSERT INTO `city` VALUES (3542, '441901', '莞城区', '441900', '广东省东莞市莞城区');
INSERT INTO `city` VALUES (3543, '441902', '南城区', '441900', '广东省东莞市南城区');
INSERT INTO `city` VALUES (3544, '441903', '东城区', '441900', '广东省东莞市东城区');
INSERT INTO `city` VALUES (3545, '441904', '万江区', '441900', '广东省东莞市万江区');
INSERT INTO `city` VALUES (3546, '441905', '石龙镇', '441900', '广东省东莞市石龙镇');
INSERT INTO `city` VALUES (3547, '441906', '石排镇', '441900', '广东省东莞市石排镇');
INSERT INTO `city` VALUES (3548, '441907', '茶山镇', '441900', '广东省东莞市茶山镇');
INSERT INTO `city` VALUES (3549, '441908', '企石镇', '441900', '广东省东莞市企石镇');
INSERT INTO `city` VALUES (3550, '441909', '桥头镇', '441900', '广东省东莞市桥头镇');
INSERT INTO `city` VALUES (3551, '441910', '东坑镇', '441900', '广东省东莞市东坑镇');
INSERT INTO `city` VALUES (3552, '441911', '横沥镇', '441900', '广东省东莞市横沥镇');
INSERT INTO `city` VALUES (3553, '441912', '常平镇', '441900', '广东省东莞市常平镇');
INSERT INTO `city` VALUES (3554, '441913', '虎门镇', '441900', '广东省东莞市虎门镇');
INSERT INTO `city` VALUES (3555, '441914', '长安镇', '441900', '广东省东莞市长安镇');
INSERT INTO `city` VALUES (3556, '441915', '沙田镇', '441900', '广东省东莞市沙田镇');
INSERT INTO `city` VALUES (3557, '441916', '厚街镇', '441900', '广东省东莞市厚街镇');
INSERT INTO `city` VALUES (3558, '441917', '寮步镇', '441900', '广东省东莞市寮步镇');
INSERT INTO `city` VALUES (3559, '441918', '大岭山镇', '441900', '广东省东莞市大岭山镇');
INSERT INTO `city` VALUES (3560, '441919', '大朗镇', '441900', '广东省东莞市大朗镇');
INSERT INTO `city` VALUES (3561, '441920', '黄江镇', '441900', '广东省东莞市黄江镇');
INSERT INTO `city` VALUES (3562, '441921', '樟木头镇', '441900', '广东省东莞市樟木头镇');
INSERT INTO `city` VALUES (3563, '441922', '谢岗镇', '441900', '广东省东莞市谢岗镇');
INSERT INTO `city` VALUES (3564, '441923', '塘厦镇', '441900', '广东省东莞市塘厦镇');
INSERT INTO `city` VALUES (3565, '441924', '清溪镇', '441900', '广东省东莞市清溪镇');
INSERT INTO `city` VALUES (3566, '441925', '凤岗镇', '441900', '广东省东莞市凤岗镇');
INSERT INTO `city` VALUES (3567, '441926', '麻涌镇', '441900', '广东省东莞市麻涌镇');
INSERT INTO `city` VALUES (3568, '441927', '中堂镇', '441900', '广东省东莞市中堂镇');
INSERT INTO `city` VALUES (3569, '441928', '高埗镇', '441900', '广东省东莞市高埗镇');
INSERT INTO `city` VALUES (3570, '441929', '石碣镇', '441900', '广东省东莞市石碣镇');
INSERT INTO `city` VALUES (3571, '441930', '望牛墩镇', '441900', '广东省东莞市望牛墩镇');
INSERT INTO `city` VALUES (3572, '441931', '洪梅镇', '441900', '广东省东莞市洪梅镇');
INSERT INTO `city` VALUES (3573, '441932', '道滘镇', '441900', '广东省东莞市道滘镇');
COMMIT;

-- ----------------------------
-- Table structure for holiday
-- ----------------------------
DROP TABLE IF EXISTS `holiday`;
CREATE TABLE `holiday` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_id` bigint(20) NOT NULL,
  `day` date NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_holiday_01` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='休息日';

-- ----------------------------
-- Records of holiday
-- ----------------------------
BEGIN;
INSERT INTO `holiday` VALUES (1, 2, '2019-03-06', '2019-03-08 16:42:12', 1, '2019-03-08 16:42:16', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(50) NOT NULL,
  `job_level` tinyint(4) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_job_01` (`job_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='职务';

-- ----------------------------
-- Records of job
-- ----------------------------
BEGIN;
INSERT INTO `job` VALUES (2, '店长', 1, '2019-03-06 10:13:51', 1, NULL, NULL, 0);
INSERT INTO `job` VALUES (4, '院长', 0, '2019-03-06 10:13:51', 1, NULL, NULL, 0);
INSERT INTO `job` VALUES (5, '前台', 2, '2019-03-06 10:13:51', 1, NULL, NULL, 0);
INSERT INTO `job` VALUES (6, '顾问', 3, '2019-03-06 10:13:51', 1, NULL, NULL, 0);
INSERT INTO `job` VALUES (7, '美容师', 4, '2019-03-06 10:13:51', 1, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `initial_store_id` bigint(20) NOT NULL,
  `member_name` varchar(50) NOT NULL,
  `tel` varchar(50) NOT NULL,
  `gender` tinyint(4) NOT NULL,
  `weixin` varchar(50) NOT NULL,
  `birthday` datetime DEFAULT NULL,
  `zodiac` tinyint(4) DEFAULT NULL,
  `member_grade_id` bigint(20) NOT NULL,
  `blood_type` tinyint(4) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `last_day_of_menses` datetime DEFAULT NULL,
  `cycle_of_menses` tinyint(4) DEFAULT NULL,
  `period_of_menses` tinyint(4) DEFAULT NULL,
  `remark_of_menses` varchar(500) DEFAULT NULL,
  `profession` varchar(50) DEFAULT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `member_code` char(10) DEFAULT NULL,
  `introducer` varchar(50) DEFAULT NULL,
  `primary_beautician` bigint(20) DEFAULT NULL,
  `entry_time` datetime DEFAULT NULL,
  `balance` double(10,2) NOT NULL,
  `integral` double(10,2) NOT NULL,
  `debt` double(10,2) NOT NULL,
  `amount_consumer` double(10,2) NOT NULL,
  `amount_charge` double(10,2) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_member_01` (`initial_store_id`),
  KEY `idx_member_02` (`member_name`),
  KEY `idx_member_03` (`birthday`),
  KEY `idx_member_04` (`tel`),
  KEY `idx_member_05` (`primary_beautician`),
  KEY `idx_member_06` (`introducer`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='会员';

-- ----------------------------
-- Records of member
-- ----------------------------
BEGIN;
INSERT INTO `member` VALUES (1, 1, '1', '1', 1, '1', '2019-03-16 11:52:52', 1, 1, 1, 1, 1, '2019-03-16 11:52:59', 1, 1, '1', '1', 1, '1', '1', '1', '1', 1, '2019-03-16 11:53:15', 1.00, 11.00, 1.00, 1.00, 1.00, '2019-03-16 11:53:32', 1, '2019-03-16 11:53:39', 1, 1);
INSERT INTO `member` VALUES (2, 2, '2', '2', 1, '2', '2019-03-16 11:58:32', 2, 2, 2, 2, 2, '2019-03-16 11:58:22', 2, 2, '2', '2', 2, '2', '2', '2', '2', 2, '2019-03-16 11:58:00', 2.00, 2.00, 2.00, 2.00, 2.00, '2019-03-16 11:58:05', 2, '2019-03-16 11:58:08', 2, 2);
INSERT INTO `member` VALUES (3, 3, '3', '3', 1, '3', NULL, 3, 3, 3, 3, 3, '2019-03-19 14:26:54', 3, 3, NULL, NULL, NULL, NULL, NULL, NULL, '3', 3, '2019-03-19 14:27:13', 3.00, 3.00, 3.00, 3.00, 3.00, '2019-03-19 14:27:09', 3, '2019-03-19 14:27:06', 3, 0);
INSERT INTO `member` VALUES (5, 2, '2', '2', 1, '2', '2019-03-16 11:58:32', 2, 2, 2, 2, 2, '2019-03-16 11:58:22', 2, 2, '2', '2', 2, '2', '2', '2', '2', 2, '2019-03-16 11:58:00', 2.00, 2.00, 2.00, 2.00, 2.00, '2019-03-16 11:58:05', 2, '2019-03-16 11:58:08', 2, 2);
INSERT INTO `member` VALUES (6, 2, '2', '2', 1, '2', '2019-03-16 11:58:32', 2, 2, 2, 2, 2, '2019-03-16 11:58:22', 2, 2, '2', '2', 2, '2', '2', '2', '2', 2, '2019-03-16 11:58:00', 2.00, 2.00, 2.00, 2.00, 2.00, '2019-03-16 11:58:05', 2, '2019-03-16 11:58:08', 2, 2);
INSERT INTO `member` VALUES (7, 2, '2', '2', 0, '2', '2019-03-16 11:58:32', 2, 2, 2, 2, 2, '2019-03-16 11:58:22', 2, 2, '2', '2', 2, '2', '2', '2', '2', 2, '2019-03-16 11:58:00', 2.00, 2.00, 2.00, 2.00, 2.00, '2019-03-16 11:58:05', 2, '2019-03-16 11:58:08', 2, 2);
INSERT INTO `member` VALUES (8, 2, '2', '2', 0, '2', '2019-03-16 11:58:32', 2, 2, 2, 2, 2, '2019-03-16 11:58:22', 2, 2, '2', '2', 2, '2', '2', '2', '2', 2, '2019-03-16 11:58:00', 2.00, 2.00, 2.00, 2.00, 2.00, '2019-03-16 11:58:05', 2, '2019-03-16 11:58:08', 2, 2);
INSERT INTO `member` VALUES (9, 2, '2', '2', 0, '2', '2019-03-16 11:58:32', 2, 2, 2, 2, 2, '2019-03-16 11:58:22', 2, 2, '2', '2', 2, '2', '2', '2', '2', 2, '2019-03-16 11:58:00', 2.00, 2.00, 2.00, 2.00, 2.00, '2019-03-16 11:58:05', 2, '2019-03-16 11:58:08', 2, 2);
INSERT INTO `member` VALUES (10, 2, '2', '2', 0, '2', '2019-03-16 11:58:32', 2, 2, 2, 2, 2, '2019-03-16 11:58:22', 2, 2, '2', '2', 2, '2', '2', '2', '2', 2, '2019-03-16 11:58:00', 2.00, 2.00, 2.00, 2.00, 2.00, '2019-03-16 11:58:05', 2, '2019-03-16 11:58:08', 2, 2);
INSERT INTO `member` VALUES (11, 2, '2', '2', 0, '2', '2019-03-16 11:58:32', 2, 2, 2, 2, 2, '2019-03-16 11:58:22', 2, 2, '2', '2', 2, '2', '2', '2', '2', 2, '2019-03-16 11:58:00', 2.00, 2.00, 2.00, 2.00, 2.00, '2019-03-16 11:58:05', 2, '2019-03-16 11:58:08', 2, 2);
INSERT INTO `member` VALUES (12, 2, '2', '2', 0, '2', '2019-03-16 11:58:32', 2, 2, 2, 2, 2, '2019-03-16 11:58:22', 2, 2, '2', '2', 2, '2', '2', '2', '2', 2, '2019-03-16 11:58:00', 2.00, 2.00, 2.00, 2.00, 2.00, '2019-03-16 11:58:05', 2, '2019-03-16 11:58:08', 2, 2);
INSERT INTO `member` VALUES (14, 2, '123', '123', 0, '123', NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 0.00, 0.00, '2019-03-20 01:49:49', 32, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for member_grade
-- ----------------------------
DROP TABLE IF EXISTS `member_grade`;
CREATE TABLE `member_grade` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(10) NOT NULL,
  `grade_level` tinyint(4) NOT NULL,
  `salon_id` bigint(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_member_grade_01` (`grade_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员级别';

-- ----------------------------
-- Table structure for member_tag
-- ----------------------------
DROP TABLE IF EXISTS `member_tag`;
CREATE TABLE `member_tag` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_member_tag_01` (`member_id`),
  KEY `idx_member_tag_02` (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='会员标签';

-- ----------------------------
-- Records of member_tag
-- ----------------------------
BEGIN;
INSERT INTO `member_tag` VALUES (2, 2, 2, '2019-03-20 14:34:04', 2, '2019-03-20 14:34:07', 2, 2);
COMMIT;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `salon_id` bigint(20) NOT NULL,
  `title` varchar(50) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_notice_01` (`salon_id`),
  KEY `idx_notice_02` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='公告表';

-- ----------------------------
-- Records of notice
-- ----------------------------
BEGIN;
INSERT INTO `notice` VALUES (1, 1, '标题1', '这是公告内容', '2019-03-15 02:05:18', 1, 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for nurse
-- ----------------------------
DROP TABLE IF EXISTS `nurse`;
CREATE TABLE `nurse` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reservation_id` bigint(20) DEFAULT NULL,
  `member_id` bigint(20) NOT NULL,
  `stuff_id` bigint(20) NOT NULL,
  `room_id` bigint(20) NOT NULL,
  `time_start` datetime NOT NULL,
  `time_end` datetime DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_nurse_01` (`member_id`),
  KEY `idx_nurse_02` (`stuff_id`),
  KEY `idx_nurse_03` (`room_id`),
  KEY `idx_nurse_04` (`time_start`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='护理';

-- ----------------------------
-- Table structure for nurse_item
-- ----------------------------
DROP TABLE IF EXISTS `nurse_item`;
CREATE TABLE `nurse_item` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nurse_id` bigint(20) NOT NULL,
  `service_id` bigint(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_nurse_item_01` (`nurse_id`),
  KEY `idx_nurse_item_02` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='护理项目';

-- ----------------------------
-- Table structure for nurse_log
-- ----------------------------
DROP TABLE IF EXISTS `nurse_log`;
CREATE TABLE `nurse_log` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stuff_id` bigint(20) NOT NULL,
  `member_id` bigint(20) NOT NULL,
  `log_content` varchar(1000) NOT NULL,
  `log_type` tinyint(4) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_nurse_log_01` (`stuff_id`),
  KEY `idx_nurse_log_02` (`member_id`),
  KEY `idx_nurse_log_03` (`create_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='回访日志/护理日志';

-- ----------------------------
-- Table structure for nurse_log_model
-- ----------------------------
DROP TABLE IF EXISTS `nurse_log_model`;
CREATE TABLE `nurse_log_model` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `model_content` varchar(1000) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `nurse_log_model_01` (`model_content`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='日志模版内容';

-- ----------------------------
-- Records of nurse_log_model
-- ----------------------------
BEGIN;
INSERT INTO `nurse_log_model` VALUES (1, '1', '2019-03-14 18:09:09', 1, '2019-03-14 18:09:12', 1, 1);
INSERT INTO `nurse_log_model` VALUES (2, '2', '2019-03-14 18:09:22', 2, '2019-03-14 18:09:25', 2, 2);
INSERT INTO `nurse_log_model` VALUES (3, '3', '2019-03-14 18:09:31', 3, '2019-03-14 18:09:34', 3, 3);
INSERT INTO `nurse_log_model` VALUES (4, '4', '2019-03-14 18:09:43', 4, '2019-03-14 18:09:45', 4, 4);
COMMIT;

-- ----------------------------
-- Table structure for operate_log
-- ----------------------------
DROP TABLE IF EXISTS `operate_log`;
CREATE TABLE `operate_log` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `opt_user_id` bigint(20) NOT NULL,
  `opt_role_id` bigint(20) NOT NULL,
  `opt_action` varchar(10) NOT NULL,
  `opt_date` datetime NOT NULL,
  `opt_info` varchar(100) NOT NULL,
  `opt_terminal` tinyint(4) DEFAULT NULL,
  `opt_url` varchar(500) DEFAULT NULL,
  `opt_statu` int(11) DEFAULT NULL,
  `opt_result` varchar(10) NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_operate_log_01` (`opt_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统操作日志';

-- ----------------------------
-- Table structure for pictures
-- ----------------------------
DROP TABLE IF EXISTS `pictures`;
CREATE TABLE `pictures` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `master_data_id` bigint(20) DEFAULT NULL,
  `record_type` tinyint(4) NOT NULL,
  `pic_type` tinyint(4) NOT NULL,
  `pic_url` varchar(255) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_pictures_01` (`master_data_id`),
  KEY `idx_pictures_02` (`record_type`),
  KEY `idx_pictures_03` (`pic_type`)
) ENGINE=InnoDB AUTO_INCREMENT=444 DEFAULT CHARSET=utf8mb4 COMMENT='系统照片';

-- ----------------------------
-- Records of pictures
-- ----------------------------
BEGIN;
INSERT INTO `pictures` VALUES (10, 1, 1, 0, '/20190321/6e702fff-e2b7-4d79-aba2-a462f0f856d5.jpg', '2019-03-14 08:14:19', 1, '2019-03-21 08:15:21', 1, 0);
INSERT INTO `pictures` VALUES (11, 0, 0, 0, '/20190314/28608a3f-9772-42c1-a249-c9e79fbbfa52.jpg', '2019-03-14 08:14:57', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (12, 2, 1, 0, '/20190321/7c995e3e-b014-498f-9598-178679df5fa3.jpg', '2019-03-14 08:15:04', 1, '2019-03-21 09:09:31', 32, 0);
INSERT INTO `pictures` VALUES (13, 0, 0, 0, '/20190314/8c99b5c6-77a0-4cf8-a603-59183c82b729.jpg', '2019-03-14 08:15:04', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (14, 2, 0, 0, '/20190314/abc.jpg', '2019-03-14 08:38:36', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (15, 2, 0, 0, '/20190314/abc.jpg', '2019-03-14 08:38:46', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (16, 1, 5, 0, '/20190314/efe40306-75c3-48c4-81b0-134c7c30b44a.jpg', '2019-03-14 08:38:52', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (17, 1, 0, 0, '/20190314/86aea808-902a-48ca-afe1-5929aa931f75.jpg', '2019-03-14 08:39:01', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (18, 1, 0, 0, '/20190314/db232ded-b299-49f9-9115-f76bce5ebf47.jpg', '2019-03-14 08:39:09', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (19, 1, 0, 0, '/20190314/dd79fcbb-675d-4d41-b681-eedf20bd32ab.jpg', '2019-03-14 08:39:19', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (20, 1, 5, 0, '/20190314/c83f4e01-5423-4e3f-a2bd-04da08e66173.jpg', '2019-03-14 08:39:30', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (21, 1, 5, 0, '/20190314/c96b923b-1f73-441b-8b93-f77c3e5352d0.jpg', '2019-03-14 09:34:16', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (23, NULL, 2, 0, '/20190318/ad007edc-5048-4b0d-bf47-65f9c366b6c5.jpg', '2019-03-18 10:11:21', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (24, NULL, 2, 0, '/20190318/7ca417a9-9cfd-4b0f-9f27-bd07956d5a2d.jpg', '2019-03-18 10:13:06', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (25, NULL, 2, 0, '/20190318/43f7748d-f753-448f-b9b1-95c709b71347.jpg', '2019-03-18 10:13:38', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (26, NULL, 2, 0, '/20190318/5f335a82-8f45-4141-8efd-18f9c6b67677.jpg', '2019-03-18 10:13:53', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (27, NULL, 2, 0, '/20190318/6e198e99-35d2-42f6-bd86-7462bdb3ae27.jpg', '2019-03-18 10:14:45', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (28, NULL, 2, 0, '/20190319/29233132-30cc-4f14-bc48-303cc0f2b3c5.jpg', '2019-03-19 02:38:22', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (29, NULL, 2, 0, '/20190319/7172842a-6a3c-4574-ba23-1ab8094206e8.jpg', '2019-03-19 02:39:47', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (30, NULL, 2, 0, '/20190319/ab12bbf3-e4a0-4e8e-a553-56f11c8fb56a.jpg', '2019-03-19 02:46:20', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (31, NULL, 2, 0, '/20190319/9788883c-1e5f-4373-863e-9e344a31cd32.jpg', '2019-03-19 02:52:05', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (32, NULL, 2, 0, '/20190319/da447f01-224b-4f36-a615-ce1a74b4ceec.jpg', '2019-03-19 02:58:29', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (33, NULL, 2, 0, '/20190319/b3004101-b5ba-49da-a535-595d2dc8a9ce.jpg', '2019-03-19 02:58:36', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (34, NULL, 2, 0, '/20190319/d666c934-a08f-4845-9fe3-896d314d81cd.jpg', '2019-03-19 03:35:01', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (35, NULL, 2, 0, '/20190319/8e0c3e1a-100e-44f6-8b86-25fe78846a73.jpg', '2019-03-19 03:35:05', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (36, NULL, 2, 0, '/20190319/ed721416-64da-4ca9-a757-12ea1c0e273c.jpg', '2019-03-19 03:45:31', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (37, NULL, 2, 0, '/20190319/a13e1691-1d5e-42ad-80df-f1c429cb3c51.jpg', '2019-03-19 03:45:34', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (38, NULL, 2, 0, '/20190319/f075239d-3896-452e-a816-150245c01bb3.jpg', '2019-03-19 03:47:16', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (39, NULL, 2, 0, '/20190319/b015d948-d979-4f22-b2a8-531bb890e64f.jpg', '2019-03-19 03:47:20', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (40, 12, 2, 0, '/20190319/d6350fbb-a44f-4877-b800-0a8960a8ceb1.jpg', '2019-03-19 03:49:52', 1, '2019-03-19 03:50:02', 1, 0);
INSERT INTO `pictures` VALUES (41, 13, 2, 0, '/20190319/0abce7e7-6093-4d32-a05b-2cbd52f4f9f3.jpg', '2019-03-19 03:54:23', 1, '2019-03-19 03:54:37', 1, 0);
INSERT INTO `pictures` VALUES (42, NULL, 2, 0, '/20190319/b8073192-7f4d-4123-b267-ccf72ce60db0.jpg', '2019-03-19 03:54:25', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (44, 23, 2, 0, '/20190319/8445b866-de35-4d4d-9ad5-349816bd90e4.jpg', '2019-03-19 09:25:46', 1, '2019-03-19 09:25:48', 1, 0);
INSERT INTO `pictures` VALUES (45, 24, 2, 0, '/20190320/a5c6f995-de0a-409c-a828-d758c78fd9aa.jpg', '2019-03-20 03:05:42', 1, '2019-03-20 03:06:21', 1, 0);
INSERT INTO `pictures` VALUES (46, 24, 2, 0, '/20190320/65aac35f-1c37-4524-bf35-df150132ae03.jpg', '2019-03-20 03:05:56', 1, '2019-03-20 03:06:21', 1, 0);
INSERT INTO `pictures` VALUES (47, NULL, 2, 0, '/20190320/13ab8fcd-fe57-451d-93a2-5fd341c0b37d.jpg', '2019-03-20 03:05:59', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (48, NULL, 2, 0, '/20190320/23e0c4d9-51fd-4860-a8d2-4c4bb2f7ed98.jpg', '2019-03-20 03:40:16', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (49, NULL, 2, 0, '/20190320/8cf359a2-3cc1-4a3e-adca-c1dc134199e4.jpg', '2019-03-20 03:40:21', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (50, NULL, 2, 0, '/20190320/0f19d132-a62e-4bd4-91ac-2591fb781a2e.jpg', '2019-03-20 03:46:30', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (51, NULL, 2, 0, '/20190320/0422cfd3-d332-4b42-9b67-1818b937040c.jpg', '2019-03-20 03:46:46', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (52, 26, 2, 0, '/20190320/907cfb88-f5dd-4802-9f37-abcf6397f3c0.jpg', '2019-03-20 03:46:59', 1, '2019-03-20 03:48:40', 1, 0);
INSERT INTO `pictures` VALUES (53, 26, 2, 0, '/20190320/9dae5fa9-e4a0-47b1-ae4d-665500a8c17d.jpg', '2019-03-20 03:47:04', 1, '2019-03-20 03:48:40', 1, 0);
INSERT INTO `pictures` VALUES (54, NULL, 2, 0, '/20190320/a8cc5756-6a2a-4b16-88ed-3a3823fe90e2.jpg', '2019-03-20 03:55:52', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (55, NULL, 2, 0, '/20190320/223708ed-6e1e-4a5e-b7c0-53794153ad9c.jpg', '2019-03-20 03:56:51', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (56, NULL, 2, 0, '/20190320/0062b484-3689-48d5-9093-bf92971c8e28.jpg', '2019-03-20 03:57:50', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (57, NULL, 2, 0, '/20190320/de0712a0-b91b-41ed-856d-8d80359d0986.jpg', '2019-03-20 03:58:35', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (58, NULL, 2, 0, '/20190320/514d8c90-ad66-4f4f-826d-0e4aa5b6ffa3.jpg', '2019-03-20 06:17:45', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (59, NULL, 2, 0, '/20190320/17518b78-eaa5-4aa9-820a-b0f676793e6e.jpg', '2019-03-20 06:18:04', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (60, NULL, 2, 0, '/20190320/18aa5e90-840d-4813-a203-b3fc0faef080.jpg', '2019-03-20 06:23:53', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (61, NULL, 2, 0, '/20190320/a510602f-dfc5-499c-b9e9-5a44746a164f.jpg', '2019-03-20 06:32:16', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (63, NULL, 0, 2, '/20190320/5c7a27e8-8b93-4471-bba9-309fa7a7b0fb.jpg', '2019-03-20 08:13:15', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (64, NULL, 0, 2, '/20190320/365e7b3b-010f-4dd3-b517-b322d982352b.jpg', '2019-03-20 08:15:01', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (65, NULL, 0, 2, '/20190320/de60a5cd-533b-43b7-bbea-a01f0de4c14c.jpg', '2019-03-20 08:15:27', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (66, NULL, 0, 2, '/20190320/c72132f5-41c0-4c0e-8439-49fa9817ec64.jpg', '2019-03-20 08:15:49', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (67, NULL, 0, 2, '/20190320/c8fda5d6-330a-450b-b4dd-79afbd998419.jpg', '2019-03-20 08:16:20', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (68, NULL, 0, 2, '/20190320/dcc78966-a367-4aa5-b0bb-57115caf45cd.jpg', '2019-03-20 08:21:13', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (69, 2, 0, 3, '/20190327/2b5ac043-120b-431e-a6ed-8f90f119207a.jpg', '2019-03-20 08:21:34', 1, '2019-03-28 09:19:57', 1, 0);
INSERT INTO `pictures` VALUES (70, NULL, 2, 0, '/20190320/67ae4f94-14e4-4a80-8e7c-8619141d60bd.jpg', '2019-03-20 09:38:18', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (72, 14, 2, 0, '/20190320/ebc18d67-8648-4442-a74f-84dc06beda89.jpg', '2019-03-20 09:44:12', 1, '2019-03-20 09:44:24', 1, 0);
INSERT INTO `pictures` VALUES (73, 2, 0, 4, '/20190322/dcf15bbc-3cad-4f27-918a-88ceee12faf5.jpg', '2019-03-20 09:57:10', 1, '2019-03-28 09:19:57', 1, 0);
INSERT INTO `pictures` VALUES (74, 2, 0, 2, '/20190322/62ceadd0-93a1-491a-8ee9-fa5609fe416d.jpg', '2019-03-20 09:57:10', 1, '2019-03-28 09:19:57', 1, 0);
INSERT INTO `pictures` VALUES (75, 2, 0, 1, '/20190322/6af484ed-a55e-4c05-a002-c951afc5bc2d.jpg', '2019-03-20 09:57:10', 1, '2019-03-28 09:19:57', 1, 0);
INSERT INTO `pictures` VALUES (78, 2, 0, 0, '/20190320/effcd5a3-52bf-413c-a045-af2fb5ef566e.jpg', '2019-03-20 09:57:10', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (79, 12, 0, 0, '/20190321/a0048ba0-b629-4a92-b4bf-6f609e34a39f.jpg', '2019-03-21 03:27:24', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (80, 12, 0, 0, '/20190321/0f3f73e1-35e3-44d6-9ba4-2aaa20496fbc.jpg', '2019-03-21 03:28:49', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (81, 12, 0, 0, '/20190321/1cbe2093-b79f-48a9-8e3b-70da0f9bbef9.jpg', '2019-03-21 03:28:54', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (82, NULL, 0, 1, '/20190321/fb0b3b53-5107-4039-a9b6-5e1cf7ae12e8.jpg', '2019-03-21 03:31:48', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (83, NULL, 0, 4, '/20190321/1341d332-2b7c-44ab-8ae5-5bf06e4b93d3.jpg', '2019-03-21 03:37:05', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (84, 2, 0, 0, '/20190321/4936480b-a4f5-4d26-a8fe-c965a55e8c28.jpg', '2019-03-21 03:41:31', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (85, 2, 0, 0, '/20190321/1b2f9c11-7f6c-41f2-82ed-6b95b57ac035.jpg', '2019-03-21 06:18:14', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (86, NULL, 2, 0, '/20190321/a0242c71-2915-4233-8748-0b10226020a9.jpg', '2019-03-21 08:47:52', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (87, NULL, 2, 0, '/20190321/75d2e060-3b71-47f3-b460-9214aa76332a.jpg', '2019-03-21 08:48:55', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (88, NULL, 0, 0, '/20190321/d74af4c8-1a95-4a50-b6b0-010103d4370f.jpg', '2019-03-21 08:58:06', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (89, NULL, 0, 0, '/20190321/df24e2de-737e-48a0-9522-b283c9a04134.jpg', '2019-03-21 09:04:05', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (90, NULL, 2, 0, '/20190321/2735871b-64ae-4c01-86b8-78cc98d6cb92.jpg', '2019-03-21 09:15:29', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (91, NULL, 0, 0, '/20190321/c6f01b40-0a08-44c0-876e-258d04e56eeb.jpg', '2019-03-21 09:16:05', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (92, NULL, 0, 0, '/20190321/42cb8214-42e5-4fd9-a98d-dd526ba914ca.jpg', '2019-03-21 09:16:05', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (93, NULL, 0, 0, '/20190321/929f6644-ddc0-46bd-a98d-f2088e2f5258.jpg', '2019-03-21 09:16:12', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (94, NULL, 2, 0, '/20190321/8787754d-7eb5-4352-bead-12d0f4a92b4f.jpg', '2019-03-21 09:16:51', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (95, NULL, 0, 0, '/20190321/7a6c2cb4-9519-4160-b371-328e866d082d.jpg', '2019-03-21 09:18:36', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (96, NULL, 2, 0, '/20190321/0c5db1da-fc51-478c-a995-21aa7f6a71cb.jpg', '2019-03-21 09:18:54', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (97, NULL, 0, 0, '/20190321/9b0f8b12-3045-441a-bf0e-5d3e3e30493f.jpg', '2019-03-21 09:19:07', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (98, NULL, 2, 0, '/20190321/4a5ecb78-201c-4628-ba52-39cc298ea7d0.jpg', '2019-03-21 09:19:22', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (99, NULL, 2, 0, '/20190321/147c53a0-6976-45ee-844a-8f746c2783f5.jpg', '2019-03-21 09:20:07', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (100, NULL, 0, 0, '/20190321/a25d25f2-3e36-4e9c-86e5-f9c36c9af4d7.jpg', '2019-03-21 09:21:06', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (101, NULL, 0, 0, '/20190321/2d88fef8-f3df-4179-ab0e-16fa5d866124.jpg', '2019-03-21 09:23:18', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (102, NULL, 0, 0, '/20190321/b466d946-a2d2-4a22-b442-f20d4f7a49f6.jpg', '2019-03-21 09:26:14', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (103, NULL, 2, 0, '/20190321/8a4f9ec6-6796-459c-9bb0-951f778161a9.jpg', '2019-03-21 09:32:23', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (104, NULL, 2, 0, '/20190321/8746a405-58f9-4196-acf2-1f06791b2c6e.jpg', '2019-03-21 09:33:01', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (107, NULL, 0, 0, '/20190321/3431d188-6dbe-4610-b552-d87ed58f6fe7.jpg', '2019-03-21 09:40:11', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (108, NULL, 0, 0, '/20190321/dd2905ac-68cd-432f-a6c6-ce54a55e95be.jpg', '2019-03-21 09:41:15', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (109, 3, 0, 0, '/20190321/1bcd0e02-4d4b-4e0b-8365-eec0fc98e363.jpg', '2019-03-21 09:43:05', 32, '2019-03-21 09:44:02', 32, 0);
INSERT INTO `pictures` VALUES (111, 12, 0, 0, '/20190321/7d023778-c129-49ee-ad10-cc42dcd6b6b5.jpg', '2019-03-21 09:49:54', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (112, 3, 0, 0, '/20190321/6f751496-99ac-4ed3-9841-5676daac7b75.jpg', '2019-03-21 09:50:29', 32, '2019-03-21 09:51:05', 32, 0);
INSERT INTO `pictures` VALUES (121, NULL, 2, 0, '/20190321/5fb4bc12-f081-4ce8-bc38-a1d92cb0b29e.jpg', '2019-03-21 11:16:03', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (122, NULL, 2, 0, '/20190321/d26e613a-da46-4506-be91-1439ab3895a4.jpg', '2019-03-21 11:16:06', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (123, NULL, 2, 0, '/20190321/5a547399-c5e8-4121-bdf0-9dda9c0b4079.jpg', '2019-03-21 11:16:09', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (124, NULL, 2, 0, '/20190321/cb22765d-434f-4f66-bbd5-342a67ac9ec5.jpg', '2019-03-21 11:19:33', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (125, NULL, 2, 0, '/20190321/efd05087-f1be-4358-b278-de89859ae5f9.jpg', '2019-03-21 11:19:37', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (126, NULL, 2, 0, '/20190321/0fa190f6-7b90-4a75-8370-73bd45f52be3.jpg', '2019-03-21 11:19:41', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (127, NULL, 2, 0, '/20190321/e2563356-d5fb-4285-b334-a39b85840012.jpg', '2019-03-21 11:31:58', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (128, NULL, 2, 0, '/20190321/f45af004-a826-443b-a740-1a35b6faf7a9.jpg', '2019-03-21 11:40:14', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (129, NULL, 2, 0, '/20190321/b5db7142-d344-4f9a-8224-77a8a6fb0667.jpg', '2019-03-21 11:40:36', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (130, NULL, 2, 0, '/20190321/ee48736c-f3c8-4289-b658-0a0abbb46763.jpg', '2019-03-21 11:47:39', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (131, NULL, 2, 0, '/20190321/0e646af5-d06c-4389-9b97-10afb64da9c7.jpg', '2019-03-21 11:47:43', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (132, NULL, 2, 0, '/20190321/06b1fa90-f79f-4319-9b88-736cdfeea966.jpg', '2019-03-21 12:07:57', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (133, NULL, 2, 0, '/20190321/7ad0f941-ad21-4ad6-8e09-acf1650f73d6.jpg', '2019-03-21 12:08:00', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (134, NULL, 2, 0, '/20190321/07e3eaf5-10f9-467c-811c-902304feb28a.jpg', '2019-03-21 12:10:28', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (135, NULL, 2, 0, '/20190321/4747c10a-88c6-4f43-b6bc-64b3bfb79816.jpg', '2019-03-21 12:10:34', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (136, NULL, 2, 0, '/20190321/1a7b3d36-7110-4c13-8d8f-f2aad8560234.jpg', '2019-03-21 12:10:38', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (137, NULL, 2, 0, '/20190322/e0b090c9-50b6-47fc-9189-4cf4c5a887c1.jpg', '2019-03-22 00:23:29', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (138, NULL, 2, 0, '/20190322/463ea959-6698-441a-81af-3ff8ba111550.jpg', '2019-03-22 00:24:12', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (139, NULL, 2, 0, '/20190322/b58be34b-668c-4333-bb1b-c404fc81c5c0.jpg', '2019-03-22 00:24:18', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (140, NULL, 2, 0, '/20190322/b4645dc6-363b-4a9c-bb47-b7f28685b2d6.jpg', '2019-03-22 00:30:26', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (141, NULL, 2, 0, '/20190322/10c8bdd9-790d-46b9-b8c5-b01d7ffd1097.jpg', '2019-03-22 00:30:53', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (142, NULL, 2, 0, '/20190322/7c505351-99f6-4a61-a053-bbdc471b5caa.jpg', '2019-03-22 00:31:08', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (143, NULL, 2, 0, '/20190322/ab28e648-299f-4c41-a67d-a14444f651c8.jpg', '2019-03-22 00:41:01', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (144, NULL, 2, 0, '/20190322/f7f66d18-6b69-448b-82e7-08c9b3256109.jpg', '2019-03-22 00:41:08', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (145, NULL, 2, 0, '/20190322/4bdfb3b9-5a09-41ec-af84-68f432e9b3e9.jpg', '2019-03-22 00:42:30', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (146, NULL, 2, 0, '/20190322/68b2b027-2217-4c98-aef6-09ddc799fa70.jpg', '2019-03-22 00:42:40', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (147, NULL, 2, 0, '/20190322/c3441c03-c386-416c-bafc-d29905ba8095.jpg', '2019-03-22 00:42:49', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (148, NULL, 2, 0, '/20190322/c6311557-eb22-44f7-9a9d-bd9e59ca01d4.jpg', '2019-03-22 00:45:39', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (149, NULL, 2, 0, '/20190322/d1b704cd-006b-4958-bfcd-fa182617836e.jpg', '2019-03-22 00:46:59', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (150, NULL, 2, 0, '/20190322/1c7742aa-0799-4d20-8d51-e1ae403c4a60.jpg', '2019-03-22 00:47:04', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (151, NULL, 2, 0, '/20190322/18ef1836-8f95-4298-9f0a-e1e52d8c0cb9.jpg', '2019-03-22 00:47:08', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (152, NULL, 2, 0, '/20190322/070a4159-90e9-4bae-82b8-0190cabe8b0c.jpg', '2019-03-22 00:47:14', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (153, 12, 0, 0, '/20190322/59d08bc3-8a0b-415d-af88-7e9c238f41ed.jpg', '2019-03-22 00:47:39', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (154, 12, 0, 0, '/20190322/5a1ae077-17b2-4b95-b196-3370ea9affd8.jpg', '2019-03-22 00:48:39', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (155, 12, 0, 0, '/20190322/373ddd48-f4fb-4393-ba49-7fcf92a97fda.jpg', '2019-03-22 00:52:53', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (156, 12, 0, 0, '/20190322/d11103c3-5bbe-48e5-b559-dcf19cb186ad.jpg', '2019-03-22 00:53:32', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (157, 12, 0, 0, '/20190322/a6e2997f-caec-4ce6-aeed-480fc483dbdf.jpg', '2019-03-22 00:55:46', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (158, NULL, 2, 0, '/20190322/8e38e51c-f1e4-4ea1-a16e-b107031125c1.jpg', '2019-03-22 02:25:36', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (159, NULL, 2, 0, '/20190322/6315c093-a965-43de-9213-5a39ad8cb608.jpg', '2019-03-22 02:25:39', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (160, 32, 2, 0, '/20190322/0772b068-e9ec-444a-be83-12a15988beff.jpg', '2019-03-22 02:25:43', 1, '2019-03-22 02:27:51', 1, 0);
INSERT INTO `pictures` VALUES (161, NULL, 2, 0, '/20190322/e7c27194-266e-4e5e-85bc-04359f07fc85.jpg', '2019-03-22 02:25:49', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (162, 12, 0, 0, '/20190322/dfe31561-9fa2-40e0-8b1f-5dfd35846fdb.jpg', '2019-03-22 08:49:40', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (163, NULL, 0, 0, '/20190322/0781b387-4985-42da-9501-36170c17ff57.jpg', '2019-03-22 09:16:33', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (164, NULL, 0, 0, '/20190322/d279752a-1a25-4235-9024-469b86947036.jpg', '2019-03-22 09:16:36', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (165, NULL, 0, 0, '/20190322/a0cbae2e-c68f-4c73-a569-8d887df72fa1.jpg', '2019-03-22 09:21:06', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (166, NULL, 0, 0, '/20190322/a5b6b456-906f-46d1-a1d2-351967b766ec.jpg', '2019-03-22 09:21:09', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (167, 12, 0, 0, '/20190322/bbea93b0-cd9a-4fd3-b3d2-221e1e017fa8.jpg', '2019-03-22 09:50:28', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (168, NULL, 0, 0, '/20190322/d7d43369-2b48-4769-bb2c-5bba4a2d6b3e.jpg', '2019-03-22 09:52:12', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (169, NULL, 0, 0, '/20190322/b75f766b-36d5-4c77-bec4-fc755cb5a857.jpg', '2019-03-22 09:56:35', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (170, NULL, 0, 0, '/20190322/c432cfe4-eaa4-4a61-9796-0ebd90fe8c67.jpg', '2019-03-22 09:59:51', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (171, NULL, 0, 0, '/20190322/e799f92f-8327-4cf0-a839-a18622262cfb.jpg', '2019-03-22 10:04:07', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (172, NULL, 0, 0, '/20190322/469c7c4d-1c66-4208-aff5-bb64b487a0fd.jpg', '2019-03-22 10:04:08', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (173, 34, 2, 0, '/20190322/e9f62a2f-90f9-4a94-8699-d3ebe19902f0.jpg', '2019-03-22 10:45:43', 1, '2019-03-22 10:45:44', 1, 0);
INSERT INTO `pictures` VALUES (174, NULL, 0, 2, '/20190322/39c5be32-9d27-4543-a6e7-2549861e693d.jpg', '2019-03-22 11:02:25', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (175, NULL, 0, 2, '/20190322/62ceadd0-93a1-491a-8ee9-fa5609fe416d.jpg', '2019-03-22 11:06:41', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (176, NULL, 0, 2, '/20190322/5df7bfce-861b-4a31-b64d-ed80b9b107fb.jpg', '2019-03-22 11:06:56', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (177, NULL, 0, 2, '/20190322/24c0ddb3-19a0-41c8-97ff-b6a581da115e.jpg', '2019-03-22 11:07:28', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (178, NULL, 0, 2, '/20190322/57a099db-0914-4bc2-b1a0-11b8931d1e3d.jpg', '2019-03-22 11:07:48', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (179, NULL, 0, 3, '/20190322/5f2d0a49-9836-42d0-bac7-8e4681be4f9f.jpg', '2019-03-22 11:08:34', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (180, NULL, 0, 3, '/20190322/a17fa22a-1b20-4779-941e-84824d9da46a.jpg', '2019-03-22 11:12:39', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (181, NULL, 0, 3, '/20190322/0e1a1ccf-2213-431c-a3d5-f71e2cc6da96.jpg', '2019-03-22 11:17:18', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (182, NULL, 0, 3, '/20190322/10f673a7-26f5-4792-a63c-b37236a4f04f.jpg', '2019-03-22 11:18:38', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (183, NULL, 0, 3, '/20190322/5b852d55-8e37-48c2-9af3-ab4cbc2b036a.jpg', '2019-03-22 11:26:24', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (184, NULL, 0, 4, '/20190322/819b890e-c222-4ac8-a590-13408125bceb.jpg', '2019-03-22 11:27:58', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (185, NULL, 0, 4, '/20190322/c84828e1-3c92-4463-b5a9-3903e3c9ed01.jpg', '2019-03-22 11:28:20', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (186, NULL, 0, 1, '/20190322/dcbb6a38-e2e9-4794-b3e1-425f79218e05.jpg', '2019-03-22 11:28:57', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (187, NULL, 0, 3, '/20190322/d5d91661-0ef6-496a-8ff0-3fdf7923dc05.jpg', '2019-03-22 11:30:03', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (188, NULL, 0, 4, '/20190322/dcf15bbc-3cad-4f27-918a-88ceee12faf5.jpg', '2019-03-22 11:38:27', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (189, NULL, 0, 3, '/20190322/d9d468ab-dc4a-4e01-8e2b-18c710cb3b24.jpg', '2019-03-22 11:38:47', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (190, NULL, 0, 1, '/20190322/6af484ed-a55e-4c05-a002-c951afc5bc2d.jpg', '2019-03-22 11:38:59', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (191, 36, 2, 0, '/20190323/718dc4de-1aab-4336-adb1-b837528d3cb7.jpg', '2019-03-23 01:06:12', 1, '2019-03-23 01:07:06', 1, 0);
INSERT INTO `pictures` VALUES (192, 36, 2, 0, '/20190323/be35a2ef-7c99-43df-a13e-92df9f3ce126.jpg', '2019-03-23 01:06:32', 1, '2019-03-23 01:07:06', 1, 0);
INSERT INTO `pictures` VALUES (193, 37, 2, 0, '/20190323/a24f939f-6a31-46a8-aa50-ca84fab76c9c.jpg', '2019-03-23 01:16:16', 1, '2019-03-23 01:17:47', 1, 0);
INSERT INTO `pictures` VALUES (194, NULL, 2, 0, '/20190323/58d698d2-2b16-48bf-81ec-d87d994dddaf.jpg', '2019-03-23 01:16:46', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (195, NULL, 2, 0, '/20190323/ea6272ff-1ff4-4d00-bac1-9a9691438303.jpg', '2019-03-23 01:17:22', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (196, 37, 2, 0, '/20190323/61f5f633-89a2-4cd8-8c99-a1ebd6b87a46.jpg', '2019-03-23 01:18:47', 1, '2019-03-23 01:19:34', 1, 0);
INSERT INTO `pictures` VALUES (197, NULL, 2, 0, '/20190323/29f184b5-d3e3-48a4-9254-b6ac154cdc8b.jpg', '2019-03-23 01:23:25', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (198, NULL, 2, 0, '/20190323/e1cf5454-5625-4254-b6dd-39eaa23c06a1.jpg', '2019-03-23 01:23:40', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (199, NULL, 2, 0, '/20190323/39996d5d-e7cd-407d-ad09-fa63c0390e8a.jpg', '2019-03-23 01:27:20', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (200, NULL, 2, 0, '/20190323/e2376112-df44-46ba-b3c7-ae0bfc9dd0db.jpg', '2019-03-23 01:27:24', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (201, NULL, 3, 0, '/20190323/7e424da5-8185-4a07-b28f-f264a1f00ad8.jpg', '2019-03-23 01:54:29', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (202, NULL, 3, 0, '/20190323/4a4f1d35-0eae-4bcb-ab8f-122a03130a26.jpg', '2019-03-23 02:05:47', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (203, NULL, 3, 0, '/20190323/c6d23e0c-9d5f-4f67-a55d-8613ee4a599a.jpg', '2019-03-23 02:05:50', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (204, NULL, 3, 0, '/20190323/a3989c8b-76da-414f-9bf5-05f4ba681356.jpg', '2019-03-23 02:25:45', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (205, NULL, 3, 0, '/20190323/f3222382-c707-4222-a3d4-919abfcae03f.jpg', '2019-03-23 02:27:54', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (206, 32, 3, 0, '/20190323/48ff5a80-69ff-4fa8-97c2-fb9761f9c5e1.jpg', '2019-03-23 02:30:05', 1, '2019-03-23 02:31:38', 1, 0);
INSERT INTO `pictures` VALUES (207, 33, 3, 0, '/20190323/ff4047bf-80bf-42ae-a150-7519599c743e.jpg', '2019-03-23 02:33:41', 1, '2019-03-23 02:33:59', 1, 0);
INSERT INTO `pictures` VALUES (208, NULL, 3, 0, '/20190323/79a0b2a0-ae3c-48be-92f6-e4c674a5df2d.jpg', '2019-03-23 02:35:15', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (209, 37, 3, 0, '/20190323/419c5d95-aa18-4e2f-aa8c-dbde54940440.jpg', '2019-03-23 02:37:26', 1, '2019-03-23 02:39:40', 1, 0);
INSERT INTO `pictures` VALUES (210, 38, 3, 0, '/20190323/7c4483cb-9f58-47a5-9898-2c91fac1aa7a.jpg', '2019-03-23 02:42:15', 1, '2019-03-23 02:42:24', 1, 0);
INSERT INTO `pictures` VALUES (211, 1, 0, 1, '/20190323/b2e289f1-07e0-4b73-bb8f-4327dec002d2.jpg', '2019-03-23 02:51:08', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (212, 1, 0, 2, '/20190323/2c314e92-18ee-4a33-acd9-4f2168e51a02.jpg', '2019-03-23 02:54:07', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (213, 1, 0, 3, '/20190323/0578f270-b0d8-4a1d-b229-e91e32da9c40.jpg', '2019-03-23 03:07:38', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (214, 1, 0, 4, '/20190323/72f4e3dc-63a7-4a02-aa4f-d75ec691d143.jpg', '2019-03-23 03:07:38', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (215, NULL, 4, 0, '/20190323/0070e5a5-6f2d-49fb-b49a-4bc6b2dbbce8.jpg', '2019-03-23 03:08:11', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (216, NULL, 4, 0, '/20190323/e4294f2a-e306-408a-931b-50660dee5971.jpg', '2019-03-23 03:08:11', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (217, NULL, 4, 0, '/20190323/7e1069e1-9faf-4b28-a559-3e34b3b56e03.jpg', '2019-03-23 03:09:15', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (218, NULL, 4, 0, '/20190323/6155bb95-4e1b-44e3-a011-2ba80ba0aeec.jpg', '2019-03-23 03:09:17', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (219, NULL, 4, 0, '/20190323/3dab261b-7069-48a0-8581-323be5b6ae7e.jpg', '2019-03-23 03:14:13', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (220, NULL, 4, 0, '/20190323/c0e0c6ff-e969-4f72-a69d-ffc303df6380.jpg', '2019-03-23 03:14:13', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (221, NULL, 4, 0, '/20190323/b563b03b-0b71-4efb-a1fc-4fe890a2f9b1.jpg', '2019-03-23 03:14:48', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (222, NULL, 4, 0, '/20190323/9db38da7-cfcc-499d-94d2-492d04ca02d5.jpg', '2019-03-23 03:14:48', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (223, NULL, 4, 0, '/20190323/7a37a1b3-7707-4fe2-8a81-99042fd3c2df.jpg', '2019-03-23 03:16:12', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (224, NULL, 4, 0, '/20190323/c0bd2a9f-36b4-4e77-853d-532f434a9691.jpg', '2019-03-23 03:16:12', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (225, NULL, 4, 0, '/20190323/4d134f45-f27f-4a63-ad3e-2b008be55899.jpg', '2019-03-23 03:16:54', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (226, NULL, 4, 0, '/20190323/77fd4d69-09af-4838-995a-3632d6934606.jpg', '2019-03-23 03:16:54', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (227, 39, 2, 0, '/20190323/2f7854ef-6114-4227-9405-62c329e5edb1.jpg', '2019-03-23 04:41:02', 1, '2019-03-23 04:41:13', 1, 0);
INSERT INTO `pictures` VALUES (228, 39, 2, 0, '/20190323/345f6d95-4e0f-4369-877e-f344e4056cbb.jpg', '2019-03-23 04:41:06', 1, '2019-03-23 04:41:13', 1, 0);
INSERT INTO `pictures` VALUES (277, 9, 4, 0, '/20190325/0979ef75-67d8-436b-bab4-3c484c22fd5c.jpg', '2019-03-25 08:22:46', 1, '2019-03-25 08:26:39', 1, 0);
INSERT INTO `pictures` VALUES (278, NULL, 4, 0, '/20190325/18330bf7-ff4a-4aaa-bf4d-3b786527931b.jpg', '2019-03-25 09:24:13', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (279, NULL, 4, 0, '/20190325/771053ae-2174-4820-8611-87e462e77920.jpg', '2019-03-25 10:17:22', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (280, NULL, 4, 0, '/20190325/6dbe10d7-55a0-49f1-baa2-56cfce1306cf.jpg', '2019-03-25 11:59:47', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (281, NULL, 4, 0, '/20190325/1d4d4822-5a5e-400d-8e60-48ce5a6900ba.jpg', '2019-03-25 11:59:33', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (282, NULL, 4, 0, '/20190325/18f66556-b665-45c7-8fef-cfc20ca18e95.jpg', '2019-03-25 11:59:35', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (285, NULL, 0, 2, '/20190326/25e50aed-163f-4279-a4ce-721c856db292.jpg', '2019-03-26 07:29:23', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (286, NULL, 0, 3, '/20190326/bd514df5-3e33-479c-8503-4a8e7c693b4b.jpg', '2019-03-26 07:30:04', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (287, NULL, 0, 2, '/20190326/90029fbc-3754-4491-89ba-223288681308.jpg', '2019-03-26 07:31:03', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (288, NULL, 0, 2, '/20190326/343feef6-dbc7-4c51-8e86-07d60c0f4162.jpg', '2019-03-26 07:42:03', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (289, NULL, 0, 3, '/20190326/6cb0ffdb-e3b5-4d22-b54a-ffae40a1db4a.jpg', '2019-03-26 07:42:07', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (290, NULL, 0, 1, '/20190326/67b88989-ba87-4782-9b28-cd5de3e35254.jpg', '2019-03-26 07:42:17', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (291, NULL, 0, 4, '/20190326/65e91df3-8647-47eb-b5e8-eeab071dc38a.jpg', '2019-03-26 07:42:25', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (292, NULL, 0, 2, '/20190326/48e21bcb-4c16-46a1-ba32-397df99e9b2d.jpg', '2019-03-26 08:20:52', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (293, NULL, 0, 2, '/20190326/1a489891-98f4-4b8a-ba17-e940760f2507.jpg', '2019-03-26 08:23:55', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (294, NULL, 0, 3, '/20190326/ab5ef1e2-3963-452a-9a1d-60aa0b1e11bb.jpg', '2019-03-26 08:23:58', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (295, NULL, 0, 1, '/20190326/e34b5290-825e-4e7d-895e-81e71d52c1f6.jpg', '2019-03-26 08:24:01', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (296, NULL, 0, 4, '/20190326/eea4d025-b2f0-4176-8e2f-959a53bcfaaf.jpg', '2019-03-26 08:24:05', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (297, NULL, 0, 0, '/20190326/63c66118-ded9-40c7-b863-a8948b9b4cf6.jpg', '2019-03-26 08:24:10', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (298, NULL, 0, 2, '/20190326/95a07517-32eb-4626-92b4-a6716b5a430b.jpg', '2019-03-26 08:25:36', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (299, NULL, 0, 3, '/20190326/205a2af1-ace4-4c8c-bc63-72ff76149b80.jpg', '2019-03-26 08:25:40', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (300, NULL, 0, 1, '/20190326/5c9d4d8b-36cd-49ee-9d33-7171f6654311.jpg', '2019-03-26 08:25:45', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (301, NULL, 0, 4, '/20190326/b31ce853-397d-4877-a812-1447a42e2140.jpg', '2019-03-26 08:25:48', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (302, NULL, 0, 0, '/20190326/9f555843-a936-4133-a21c-0197c437ac0c.jpg', '2019-03-26 08:25:53', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (303, NULL, 0, 0, '/20190326/56ed09a5-01b7-4daa-9720-2eff6dade85d.jpg', '2019-03-26 08:25:56', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (304, NULL, 0, 0, '/20190326/b769b28a-324f-4547-a3ce-2f6a9f1c2607.jpg', '2019-03-26 08:26:00', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (305, NULL, 0, 0, '/20190326/91bfaa04-72f8-430f-8da4-ce53953dfd19.jpg', '2019-03-26 08:26:04', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (306, NULL, 0, 0, '/20190326/d2abf3c1-7579-4f18-aff1-020066d8c3b1.jpg', '2019-03-26 08:26:09', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (307, NULL, 0, 0, '/20190326/ce9fe8b3-011a-456b-84a8-a52717e930dd.jpg', '2019-03-26 08:26:13', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (308, NULL, 0, 4, '/20190326/d8a2e7de-13d6-457c-8521-fa86aa141e91.jpg', '2019-03-26 08:37:40', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (309, NULL, 0, 4, '/20190326/fe167b2d-f2a2-472d-83f7-771abe0ba228.jpg', '2019-03-26 08:38:37', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (310, NULL, 0, 4, '/20190326/0ca41520-2908-48b9-b202-31bfc1900f0b.jpg', '2019-03-26 08:42:29', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (311, NULL, 0, 1, '/20190326/0a0f9d1f-c9b2-43c9-a79b-78f7eaf953de.jpg', '2019-03-26 08:43:10', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (312, NULL, 0, 2, '/20190326/f8d25420-1bba-4e69-9f2b-93c395f40b12.jpg', '2019-03-26 08:43:59', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (313, NULL, 0, 3, '/20190326/659db5aa-653b-4dcb-bc8b-dee5b94a2417.jpg', '2019-03-26 08:44:07', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (314, NULL, 0, 2, '/20190326/6a8af11c-27b9-4574-ac77-b792b70bbeec.jpg', '2019-03-26 08:47:02', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (315, NULL, 0, 3, '/20190326/b1bdbc12-0205-4e22-9e57-5066761d2845.jpg', '2019-03-26 08:48:06', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (316, NULL, 0, 4, '/20190326/92f24bcd-243a-4f68-be0b-94f038f4d633.jpg', '2019-03-26 08:48:17', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (317, NULL, 0, 4, '/20190326/f907c329-bdc6-4467-bee3-b5559819a736.jpg', '2019-03-26 08:48:18', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (318, NULL, 0, 1, '/20190326/7130f7b8-6d83-4ca0-a106-c3ce35222278.jpg', '2019-03-26 08:48:26', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (319, NULL, 0, 3, '/20190326/540ba697-7b6e-4187-a53d-c2251a885ae5.jpg', '2019-03-26 08:48:38', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (320, NULL, 0, 2, '/20190327/632490d1-0f6f-4fcb-b3b3-356c92083845.jpg', '2019-03-27 02:16:03', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (321, NULL, 0, 3, '/20190327/f1fd38b1-3a22-4b79-a9d8-6659354dd63a.jpg', '2019-03-27 02:16:07', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (322, NULL, 0, 1, '/20190327/dd56ad36-7a55-4a3d-8a53-3c0ab36c9de6.jpg', '2019-03-27 02:16:10', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (323, NULL, 0, 2, '/20190327/f9cc67c6-489b-4963-8c45-d00133fc457e.jpg', '2019-03-27 02:17:57', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (324, NULL, 0, 3, '/20190327/73288d01-7fe1-4d8f-aedb-3a53ad0502f9.jpg', '2019-03-27 02:18:00', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (325, NULL, 0, 1, '/20190327/ecc6e420-1962-4c35-ae3e-747c4bdbd302.jpg', '2019-03-27 02:18:03', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (326, NULL, 0, 4, '/20190327/07a6069e-26da-46fd-ba11-24e931cda865.jpg', '2019-03-27 02:18:07', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (327, NULL, 0, 2, '/20190327/80180c85-9c47-49c7-bbfd-1356f74636c4.jpg', '2019-03-27 02:19:43', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (328, NULL, 0, 3, '/20190327/134829db-81aa-409c-8013-a2b800b84da0.jpg', '2019-03-27 02:19:46', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (329, NULL, 0, 1, '/20190327/680a07e9-b094-4a2c-bd3c-11735e5110a2.jpg', '2019-03-27 02:19:52', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (330, NULL, 0, 4, '/20190327/327d6f23-1877-4d32-a07a-880e03633845.jpg', '2019-03-27 02:19:55', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (331, NULL, 0, 2, '/20190327/20a85e22-a78c-42c8-8144-5c69c22a315c.jpg', '2019-03-27 02:29:25', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (332, NULL, 0, 3, '/20190327/dc7eae5f-2802-44c4-894f-caa7abdb27da.jpg', '2019-03-27 02:29:29', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (333, NULL, 0, 1, '/20190327/53ca1903-0574-494f-a86b-82a5c1c38ab0.jpg', '2019-03-27 02:29:32', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (334, NULL, 0, 4, '/20190327/f974a484-ac59-4dc7-bfcf-facda3b66d43.jpg', '2019-03-27 02:29:37', 32, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (335, NULL, 0, 2, '/20190327/34efc05d-8d5c-42b5-bda3-b153f6719f83.jpg', '2019-03-27 06:57:49', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (336, NULL, 0, 3, '/20190327/da2088ce-6bcc-45c0-9853-1a37c20ce8ce.jpg', '2019-03-27 06:57:56', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (337, NULL, 0, 1, '/20190327/c5338548-b110-4b8a-b78f-aa9a8d662385.jpg', '2019-03-27 06:58:02', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (338, NULL, 0, 4, '/20190327/ed1274b3-1d15-4d8c-8bb9-ee6de41acbe1.jpg', '2019-03-27 06:58:08', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (339, NULL, 0, 2, '/20190327/665080ea-fda6-4de6-aa49-a396477c3f43.jpg', '2019-03-27 07:07:06', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (340, NULL, 0, 3, '/20190327/012cd1eb-31e7-4c87-a83c-84f726ffec38.jpg', '2019-03-27 07:07:13', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (341, NULL, 0, 1, '/20190327/a573fdfc-9d1a-4fc5-8f81-2cf77d81e924.jpg', '2019-03-27 07:07:18', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (342, NULL, 0, 4, '/20190327/2e294651-e263-4314-bfa5-8e907fd7469f.jpg', '2019-03-27 07:07:24', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (343, NULL, 0, 2, '/20190327/2f6e4d4e-ad37-4261-9fae-123cc3b9a533.jpg', '2019-03-27 07:42:05', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (344, NULL, 0, 3, '/20190327/276b8eb7-74a3-46fc-8a06-ceb9d7da8360.jpg', '2019-03-27 07:42:11', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (345, NULL, 0, 1, '/20190327/332462e1-f05a-487f-9a0e-9a65448757df.jpg', '2019-03-27 07:42:16', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (346, NULL, 0, 4, '/20190327/f0861d26-6837-4e72-8232-2e3e52a348f5.jpg', '2019-03-27 07:42:21', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (347, NULL, 0, 2, '/20190327/ab848a7a-b633-4122-84ed-d40b12973ad1.jpg', '2019-03-27 07:44:01', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (348, NULL, 0, 3, '/20190327/17ce281a-c7c2-49fa-beb0-47992fce07c5.jpg', '2019-03-27 07:44:08', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (349, NULL, 0, 1, '/20190327/b80d95ce-dfbd-473f-8c92-8ded4d7425a9.jpg', '2019-03-27 07:44:13', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (350, NULL, 0, 4, '/20190327/4bc6f9fa-ba61-41a2-bb51-e2319f4031fa.jpg', '2019-03-27 07:44:18', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (351, NULL, 0, 2, '/20190327/0c504e57-30cb-4482-900c-08c956d37e70.jpg', '2019-03-27 07:59:21', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (352, NULL, 0, 3, '/20190327/2784fe4c-9dfa-496e-b5ec-654c419ee2a4.jpg', '2019-03-27 07:59:25', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (353, NULL, 0, 1, '/20190327/d495689d-1bdb-4cfc-a2da-e40c997f1329.jpg', '2019-03-27 07:59:27', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (354, NULL, 0, 4, '/20190327/2e2d3f44-697a-430a-aba9-d6d64082661e.jpg', '2019-03-27 07:59:31', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (355, NULL, 0, 2, '/20190327/de23359a-93b9-49dd-b4c2-9b607edabf2c.jpg', '2019-03-27 08:05:37', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (356, NULL, 0, 3, '/20190327/aa599611-86f0-462a-a760-e4c8293090c7.jpg', '2019-03-27 08:05:42', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (357, NULL, 0, 1, '/20190327/99777df6-be4a-482d-a128-1c6dc1777e16.jpg', '2019-03-27 08:06:40', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (358, NULL, 0, 4, '/20190327/4d4fd208-2add-42b7-8a28-a544e0d0a2a2.jpg', '2019-03-27 08:06:45', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (359, NULL, 0, 2, '/20190327/ac48e110-4f8b-4342-a05b-d960ed770715.jpg', '2019-03-27 08:21:50', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (360, NULL, 0, 2, '/20190327/3e2b5f04-9122-4f41-926b-2fa25915061a.jpg', '2019-03-27 08:21:53', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (361, NULL, 0, 3, '/20190327/3840c03f-0019-4326-87b9-e75936febdc1.jpg', '2019-03-27 08:21:53', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (362, NULL, 0, 1, '/20190327/94003dc9-76ee-4247-b6fe-f98418ec3398.jpg', '2019-03-27 08:21:57', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (363, NULL, 0, 3, '/20190327/46793dbb-3388-438e-bae1-fab7f959e140.jpg', '2019-03-27 08:21:58', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (364, NULL, 0, 1, '/20190327/83a12238-8a41-4747-bfcf-0e24bedaaf0d.jpg', '2019-03-27 08:22:04', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (365, NULL, 0, 4, '/20190327/1948717b-fdd8-48d2-b73e-f3aa93ebd706.jpg', '2019-03-27 08:22:11', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (366, NULL, 0, 2, '/20190327/68d61a02-34b4-4f67-a314-3f81d7d27f0e.jpg', '2019-03-27 08:23:45', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (367, NULL, 0, 3, '/20190327/5b9c01ba-b67e-45c8-a9e8-109581c06975.jpg', '2019-03-27 08:23:51', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (368, NULL, 0, 1, '/20190327/5e6c4745-56b9-4b80-80c1-c62eb38ef95c.jpg', '2019-03-27 08:23:55', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (369, NULL, 0, 4, '/20190327/e0ad5aa9-f9d8-4f75-a121-a94240e9b2ad.jpg', '2019-03-27 08:25:52', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (370, NULL, 0, 1, '/20190327/b7cc0578-b3d7-481b-ae35-d67ed99767ff.jpg', '2019-03-27 08:26:02', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (371, NULL, 0, 3, '/20190327/c5fc418b-dff4-4bab-a807-6f665cebdd05.jpg', '2019-03-27 08:26:07', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (372, NULL, 0, 2, '/20190327/bd4036da-9538-44f2-b821-32c7854b220f.jpg', '2019-03-27 08:26:12', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (373, NULL, 0, 2, '/20190327/56ed538f-ffdd-458b-9059-e0cf8d447884.jpg', '2019-03-27 08:34:58', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (374, NULL, 0, 3, '/20190327/0261bde2-f80a-4447-a386-4fe2b2514781.jpg', '2019-03-27 08:35:04', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (375, NULL, 0, 1, '/20190327/a4a80321-4663-4882-be1e-2eb5e81f46ce.jpg', '2019-03-27 08:35:21', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (376, NULL, 0, 4, '/20190327/e12d46a2-c4eb-4728-9a5a-d99ecb0c9495.jpg', '2019-03-27 08:35:26', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (377, NULL, 0, 2, '/20190327/1c8ded07-2018-439a-80f9-a680a267cee8.jpg', '2019-03-27 08:44:19', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (378, NULL, 0, 2, '/20190327/1402d04d-327c-46a7-80f5-de58cce4fc7b.jpg', '2019-03-27 11:07:31', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (379, NULL, 0, 3, '/20190327/d26ad58d-9d8b-42f7-9514-45e3b934ecf9.jpg', '2019-03-27 11:08:40', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (380, NULL, 0, 0, '/20190327/872874e9-2882-4579-8847-46e212a8d31c.jpg', '2019-03-27 11:11:46', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (381, NULL, 0, 0, '/20190327/fe54750a-306c-4715-b253-de076c6e65b4.jpg', '2019-03-27 11:12:19', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (382, NULL, 0, 3, '/20190327/3d099d4e-ea38-4600-be3d-819ce4c8c3ff.jpg', '2019-03-27 11:12:29', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (383, NULL, 0, 0, '/20190327/1f1fc601-8fa6-49d0-ac2b-42ae9766e810.jpg', '2019-03-27 11:16:13', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (384, NULL, 0, 3, '/20190327/ed89b3ab-94cc-4f51-965e-6a14561b2701.jpg', '2019-03-27 11:16:18', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (385, NULL, 0, 0, '/20190327/b825f6b1-86eb-4693-bdc4-ee178ec208f6.jpg', '2019-03-27 11:19:09', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (386, NULL, 0, 3, '/20190327/4733aa36-0453-4b13-867b-2164b1bbbe82.jpg', '2019-03-27 11:19:16', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (387, NULL, 0, 3, '/20190327/9e703722-1bb3-46dc-9b66-2e12a21df7bb.jpg', '2019-03-27 11:20:06', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (388, NULL, 0, 3, '/20190327/2b5ac043-120b-431e-a6ed-8f90f119207a.jpg', '2019-03-27 11:22:47', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (389, 2, 0, 0, '/20190327/3013d3be-9a8e-4bc9-8477-bf3d5cea4c7c.jpg', '2019-03-27 11:23:08', 1, '2019-03-27 11:23:15', 1, 0);
INSERT INTO `pictures` VALUES (390, NULL, 4, 0, '/20190327/5067b001-d31c-4d0c-aceb-1653232ca0c6.jpg', '2019-03-27 11:40:02', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (391, NULL, 4, 0, '/20190327/a88758b7-fbd8-4a2f-bc92-7ddbe1bf486b.jpg', '2019-03-27 11:40:02', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (392, NULL, 4, 0, '/20190327/ade128ea-219f-4d55-8d3b-de4057ac96bb.jpg', '2019-03-27 11:40:36', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (393, NULL, 4, 0, '/20190327/b4442707-c36c-4965-86b2-f267147f9259.jpg', '2019-03-27 11:40:36', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (394, NULL, 4, 0, '/20190327/755817c0-bf61-49f6-b299-e2b93f92c23d.jpg', '2019-03-27 11:53:52', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (395, NULL, 4, 0, '/20190327/9149f287-4b49-4e0e-8b40-cdf266d4c085.jpg', '2019-03-27 11:53:52', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (396, NULL, 4, 0, '/20190327/51f7de01-99a9-4573-8269-c15d7321170d.jpg', '2019-03-27 11:56:15', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (397, NULL, 4, 0, '/20190327/a5106ebe-baad-4850-9126-00934777b8f2.jpg', '2019-03-27 11:56:15', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (398, NULL, 4, 0, '/20190328/a984a1ad-c890-4e15-9403-306d583dac20.jpg', '2019-03-28 01:36:30', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (399, NULL, 4, 0, '/20190328/5164b3dd-e6e5-4a0c-9149-ee7f8d458e5f.jpg', '2019-03-28 01:45:51', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (400, NULL, 4, 0, '/20190328/b4caf3f4-194a-4010-becb-58264802e37f.jpg', '2019-03-28 01:45:51', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (401, NULL, 4, 0, '/20190328/b0141461-dff2-42aa-b339-6addc05205f5.jpg', '2019-03-28 01:48:33', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (402, NULL, 4, 0, '/20190328/743d5729-3a51-4ee1-a292-4e00d2a2437e.jpg', '2019-03-28 01:48:33', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (403, NULL, 4, 0, '/20190328/ff17bbf6-3208-4721-8251-0bf50ab906a6.jpg', '2019-03-28 01:49:18', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (404, NULL, 4, 0, '/20190328/17c1de08-a384-4da0-b3ad-befdc1cfa4b8.jpg', '2019-03-28 01:51:02', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (405, NULL, 4, 0, '/20190328/afefaa57-3f75-474c-abcd-9e05c06919db.jpg', '2019-03-28 01:51:02', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (406, NULL, 4, 0, '/20190328/3f2bc7ed-3f70-438d-b0ea-30d19bddc094.jpg', '2019-03-28 01:54:21', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (407, NULL, 4, 0, '/20190328/bb7d8af0-6707-4176-a279-9c775aae254b.jpg', '2019-03-28 01:54:21', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (408, NULL, 4, 0, '/20190328/87e4ea71-b667-43f3-befe-8456a080fbb1.jpg', '2019-03-28 01:55:12', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (409, NULL, 4, 0, '/20190328/b13b74df-9984-439b-af91-b08079499645.jpg', '2019-03-28 02:00:59', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (410, NULL, 4, 0, '/20190328/8d28169e-baf1-4f98-92ff-1f37b0186333.jpg', '2019-03-28 02:00:59', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (411, NULL, 4, 0, '/20190328/c8bda5f1-443c-4c2e-873b-2e5f7c41793c.jpg', '2019-03-28 02:05:51', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (412, NULL, 4, 0, '/20190328/846b3c2b-187e-461e-ad86-a1f70c1d14b5.jpg', '2019-03-28 02:05:51', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (413, NULL, 4, 0, '/20190328/f6ab7d89-5a37-4202-9602-31cc3f5fab15.jpg', '2019-03-28 02:09:39', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (414, NULL, 4, 0, '/20190328/6a16aa40-872f-421c-ba85-25186660b15a.jpg', '2019-03-28 02:09:39', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (415, NULL, 4, 0, '/20190328/7d602dc9-05be-47f4-9e82-5b7b9e4e05c7.jpg', '2019-03-28 02:12:27', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (416, NULL, 4, 0, '/20190328/32c1e817-3530-4317-9678-c19bb276091d.jpg', '2019-03-28 02:12:27', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (417, NULL, 4, 0, '/20190328/fc871be4-a8d3-4522-b9f2-c7e861c90221.jpg', '2019-03-28 02:14:15', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (418, NULL, 4, 0, '/20190328/182189cf-8f5f-4122-bfb3-d191bf7ad9eb.jpg', '2019-03-28 02:14:15', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (419, NULL, 4, 0, '/20190328/732d2e99-2c04-47f3-ad39-a455ea4ce25d.jpg', '2019-03-28 02:19:35', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (420, NULL, 4, 0, '/20190328/6e22c73a-54bf-43f7-803e-8a20e653abda.jpg', '2019-03-28 02:19:35', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (421, NULL, 4, 0, '/20190328/8d2ddb3a-dac8-4558-af9c-cf49f9857a8d.jpg', '2019-03-28 02:22:09', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (422, NULL, 4, 0, '/20190328/3cdc1c52-6439-4f4b-9979-56c542dd031e.jpg', '2019-03-28 02:22:09', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (423, NULL, 4, 0, '/20190328/324ad84c-0318-4c4d-b188-f553a1ad5365.jpg', '2019-03-28 02:25:11', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (424, NULL, 4, 0, '/20190328/598221bf-a6ed-4b10-bc75-8bf757607e7d.jpg', '2019-03-28 02:25:11', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (425, NULL, 4, 0, '/20190328/4f644edd-442d-4b0a-8968-e225d863f465.jpg', '2019-03-28 02:27:07', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (426, NULL, 4, 0, '/20190328/1109628e-3a1a-4c1a-b955-9e4d9e475c0b.jpg', '2019-03-28 02:27:07', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (427, NULL, 4, 0, '/20190328/fd671984-ceae-4dd3-be3c-2f3cc14654a8.jpg', '2019-03-28 02:29:14', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (428, NULL, 4, 0, '/20190328/c7071546-6ab7-4324-b0ca-e8d241031d8e.jpg', '2019-03-28 02:29:14', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (429, NULL, 4, 0, '/20190328/039f1dd5-3a7a-49d4-9461-8e334232d729.jpg', '2019-03-28 02:29:58', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (430, NULL, 4, 0, '/20190328/addd62e5-a5ef-45c5-914b-7122263a6596.jpg', '2019-03-28 02:29:58', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (431, NULL, 4, 0, '/20190328/4de96f77-a923-4be4-8714-38ea5e9b939b.jpg', '2019-03-28 02:33:09', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (432, NULL, 4, 0, '/20190328/d7c5e484-52a9-43f0-8cfc-3bff6c9fc62e.jpg', '2019-03-28 02:33:09', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (433, NULL, 4, 0, '/20190328/b2c08c46-1c64-49d7-8a5f-28579a951605.jpg', '2019-03-28 02:52:28', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (434, NULL, 4, 0, '/20190328/6f8fcbb7-e96d-41c9-a0cd-6f3eb439e8f5.jpg', '2019-03-28 02:52:28', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (435, 44, 4, 0, '/20190328/1d831811-c05b-49a2-a8bb-c4d8692fe809.jpg', '2019-03-28 02:59:08', 1, '2019-03-28 02:59:36', 1, 0);
INSERT INTO `pictures` VALUES (436, 45, 2, 0, '/20190328/4d33d1ec-3a22-48bb-b31a-df3dfbe8d55f.jpg', '2019-03-28 03:04:18', 1, '2019-03-28 03:04:25', 1, 0);
INSERT INTO `pictures` VALUES (437, 45, 2, 0, '/20190328/770ff699-b48e-4a9a-abf0-3f10f57dc018.jpg', '2019-03-28 03:04:18', 1, '2019-03-28 03:04:25', 1, 0);
INSERT INTO `pictures` VALUES (438, 46, 2, 0, '/20190329/b294c0ec-59cb-433a-b683-405d38069efe.jpg', '2019-03-29 11:34:04', 1, '2019-03-29 11:34:20', 1, 0);
INSERT INTO `pictures` VALUES (439, 46, 2, 0, '/20190329/5130b26c-22e6-410c-aea3-c0bd2412108b.jpg', '2019-03-29 11:34:16', 1, '2019-03-29 11:34:20', 1, 0);
INSERT INTO `pictures` VALUES (440, NULL, 3, 0, '/20190329/7b967af7-0846-4301-aa86-60e7c22995f1.jpg', '2019-03-29 11:40:27', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (441, NULL, 3, 0, '/20190329/47d0baca-df12-4ec2-be8d-b8bd9b3b3157.jpg', '2019-03-29 11:40:27', 1, NULL, NULL, 0);
INSERT INTO `pictures` VALUES (442, 41, 3, 0, '/20190329/267ee4de-9080-48ee-bda9-a0d436d0aa7d.jpg', '2019-03-29 11:42:59', 1, '2019-03-29 11:43:24', 1, 0);
INSERT INTO `pictures` VALUES (443, 41, 3, 0, '/20190329/ad0c1d10-edaf-487d-a987-994cab8ab72b.jpg', '2019-03-29 11:42:59', 1, '2019-03-29 11:43:25', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_id` bigint(20) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `product_class` tinyint(4) NOT NULL,
  `product_series_id` bigint(20) NOT NULL,
  `price_market` double(8,2) NOT NULL,
  `price` double(8,2) NOT NULL,
  `product_code` varchar(20) DEFAULT NULL,
  `bar_code` varchar(100) DEFAULT NULL,
  `shelf_life` tinyint(4) NOT NULL,
  `day_of_pre_warning` int(11) NOT NULL,
  `stock_of_pre_warning` int(11) NOT NULL,
  `record_status` tinyint(4) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_product_01` (`product_name`),
  KEY `idx_product_02` (`product_class`),
  KEY `idx_product_03` (`product_series_id`),
  KEY `idx_product_04` (`bar_code`),
  KEY `idx_product_05` (`shelf_life`),
  KEY `idx_product_06` (`day_of_pre_warning`),
  KEY `idx_product_07` (`stock_of_pre_warning`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='产品';

-- ----------------------------
-- Records of product
-- ----------------------------
BEGIN;
INSERT INTO `product` VALUES (1, 2, '1', 1, 1, 1.00, 1.00, '1', '1', 1, 1, 1, 1, '1', '2019-03-06 09:58:56', 1, '2019-03-06 09:59:00', 1, 1);
INSERT INTO `product` VALUES (2, 2, '产品3', 0, 1, 100.00, 80.00, 'abcd', 'abcd', 12, 2, 2, 0, '简介', '2019-03-08 09:02:43', 1, '2019-03-08 09:14:24', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for product_property
-- ----------------------------
DROP TABLE IF EXISTS `product_property`;
CREATE TABLE `product_property` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `property_name` varchar(50) NOT NULL,
  `property_type` tinyint(4) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_product_property_01` (`property_name`),
  KEY `idx_product_property_02` (`property_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品基础属性表';

-- ----------------------------
-- Table structure for product_property_map
-- ----------------------------
DROP TABLE IF EXISTS `product_property_map`;
CREATE TABLE `product_property_map` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL,
  `product_property_id` bigint(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_product_property_map_01` (`product_id`),
  KEY `idx_product_property_map_02` (`product_property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品属性关系表';

-- ----------------------------
-- Table structure for product_series
-- ----------------------------
DROP TABLE IF EXISTS `product_series`;
CREATE TABLE `product_series` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_id` bigint(20) DEFAULT NULL,
  `series_name` varchar(50) NOT NULL,
  `parent_id` bigint(20) NOT NULL,
  `record_status` tinyint(4) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_product_series_01` (`series_name`),
  KEY `idx_product_series_02` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='产品品牌/系列';

-- ----------------------------
-- Records of product_series
-- ----------------------------
BEGIN;
INSERT INTO `product_series` VALUES (1, 2, '品牌2', 0, 0, '2019-03-08 06:33:40', 1, '2019-03-08 07:09:13', 1, 0);
INSERT INTO `product_series` VALUES (14, 2, '系列11', 1, 0, '2019-03-08 07:02:05', 1, NULL, NULL, 0);
INSERT INTO `product_series` VALUES (15, 2, '品牌1', 0, 0, '2019-03-11 01:43:22', 1, NULL, NULL, 0);
INSERT INTO `product_series` VALUES (16, NULL, '品牌3', 0, 0, '2019-03-12 08:25:31', 2, NULL, NULL, 0);
INSERT INTO `product_series` VALUES (17, NULL, '品牌4', 0, 0, '2019-03-12 08:26:13', 1, NULL, NULL, 0);
INSERT INTO `product_series` VALUES (18, 2, '品牌3', 0, 0, '2019-03-22 08:18:56', 1, NULL, NULL, 0);
INSERT INTO `product_series` VALUES (19, 2, '品牌4', 0, 0, '2019-03-22 08:28:55', 1, NULL, NULL, 0);
INSERT INTO `product_series` VALUES (20, 2, '品牌5', 0, 0, '2019-03-22 08:29:57', 1, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for product_stock
-- ----------------------------
DROP TABLE IF EXISTS `product_stock`;
CREATE TABLE `product_stock` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL,
  `warehouse_id` bigint(20) NOT NULL,
  `stock_qty` int(11) NOT NULL,
  `cost` double(10,2) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_product_stock_01` (`product_id`),
  KEY `idx_product_stock_02` (`stock_qty`),
  KEY `idx_product_stock_03` (`cost`),
  KEY `idx_product_stock_04` (`warehouse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='产品库存表';

-- ----------------------------
-- Records of product_stock
-- ----------------------------
BEGIN;
INSERT INTO `product_stock` VALUES (1, 1, 1, 1, 1.00, '2019-03-05 15:46:53', 1, '2019-03-05 15:46:55', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for product_stock_movement
-- ----------------------------
DROP TABLE IF EXISTS `product_stock_movement`;
CREATE TABLE `product_stock_movement` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `record_create_type` tinyint(4) NOT NULL,
  `movement_type` tinyint(4) NOT NULL,
  `movement_no` char(12) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `movement_qty` int(11) NOT NULL,
  `date_of_manufacture` datetime NOT NULL,
  `purchase_cost` double(10,2) DEFAULT NULL,
  `warehouse_id` bigint(20) NOT NULL,
  `reference_record_no` varchar(50) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_product_stock_movement_01` (`movement_type`),
  KEY `idx_product_stock_movement_02` (`movement_no`),
  KEY `idx_product_stock_movement_03` (`date_of_manufacture`),
  KEY `idx_product_stock_movement_04` (`create_date`),
  KEY `idx_product_stock_movement_05` (`product_id`),
  KEY `idx_product_stock_movement_06` (`create_by`),
  KEY `idx_product_stock_movement_07` (`update_by`),
  KEY `idx_product_stock_movement_08` (`update_date`),
  KEY `idx_product_stock_movement_09` (`warehouse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='产品库存异动';

-- ----------------------------
-- Records of product_stock_movement
-- ----------------------------
BEGIN;
INSERT INTO `product_stock_movement` VALUES (1, 1, 1, '1', 1, 1, '2019-03-06 14:28:46', NULL, 1, '1', '2019-03-06 14:28:51', 1, '2019-03-06 14:28:54', 1, 1, '1');
COMMIT;

-- ----------------------------
-- Table structure for program_privilege
-- ----------------------------
DROP TABLE IF EXISTS `program_privilege`;
CREATE TABLE `program_privilege` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `program_id` varchar(50) NOT NULL,
  `privilege_code` varchar(50) NOT NULL,
  `privilege_name` varchar(120) NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `IDX_PROGRAM_PRIVILEGE_0` (`program_id`),
  KEY `IDX_PROGRAM_PRIVILEGE_1` (`privilege_code`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of program_privilege
-- ----------------------------
BEGIN;
INSERT INTO `program_privilege` VALUES (1, 'SYS01', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (2, 'SYS01_01', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (3, 'SYS01_01', 'INSERT', '新增');
INSERT INTO `program_privilege` VALUES (4, 'SYS01_01', 'UPDATE', '修改');
INSERT INTO `program_privilege` VALUES (5, 'SYS01_01', 'DELETE', '删除');
INSERT INTO `program_privilege` VALUES (6, 'SYS01_01', 'STOP_USER', '暂停账户');
INSERT INTO `program_privilege` VALUES (7, 'SYS01_01', 'START_USER', '启用账户');
INSERT INTO `program_privilege` VALUES (8, 'SYS01_01', 'RESET_PASSWORD', '重设密码');
INSERT INTO `program_privilege` VALUES (9, 'SYS01_01', 'ASSIGN_ROLE', '授权');
INSERT INTO `program_privilege` VALUES (10, 'SYS01_02', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (11, 'SYS01_02', 'INSERT', '新增');
INSERT INTO `program_privilege` VALUES (12, 'SYS01_02', 'UPDATE', '修改');
INSERT INTO `program_privilege` VALUES (13, 'SYS01_02', 'DELETE', '删除');
INSERT INTO `program_privilege` VALUES (14, 'SYS01_02', 'ASSIGN_ROLE', '授权');
INSERT INTO `program_privilege` VALUES (15, 'SYS01_03', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (16, 'SYS01_03', 'INSERT', '新增');
INSERT INTO `program_privilege` VALUES (17, 'SYS01_03', 'UPDATE', '修改');
INSERT INTO `program_privilege` VALUES (18, 'SYS01_03', 'DELETE', '删除');
INSERT INTO `program_privilege` VALUES (19, 'SYS02', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (20, 'SYS02_01', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (21, 'SYS02_01_01', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (22, 'SYS02_01_02', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (23, 'SYS02_01_02', 'DELETE', '删除');
INSERT INTO `program_privilege` VALUES (24, 'SYS02_02', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (25, 'SYS02_02_01', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (26, 'SYS02_02_02', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (27, 'SYS02_03', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (28, 'SYS02_03_01', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (29, 'SYS02_03_02', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (30, 'SYS02_04', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (31, 'SYS02_04_01', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (32, 'SYS02_04_02', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (33, 'SYS02_05', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (34, 'SYS02_06', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (35, 'SYS02_06_01', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (36, 'SYS02_06_02', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (37, 'SYS02_07', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (38, 'SYS02_07_01', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (39, 'SYS02_07_02', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (40, 'SYS02_07_03', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (41, 'SYS02_08', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (42, 'SYS02_08_01', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (43, 'SYS02_08_02', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (44, 'SYS02_08_03', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (45, 'SYS02_08_04', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (46, 'SYS02_08_05', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (47, 'SYS02_08_06', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (48, 'SYS03', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (49, 'SYS03_01', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (50, 'SYS03_02', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (51, 'SYS03_03', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (52, 'SYS03_04', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (53, 'SYS04', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (54, 'SYS04_01', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (55, 'SYS04_02', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (56, 'SYS04_03', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (57, 'SYS04_04', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (58, 'SYS05', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (59, 'SYS05_01', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (60, 'SYS05_02', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (61, 'SYS05_03', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (62, 'SYS06', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (63, 'SYS06_01', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (64, 'SYS06_02', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (65, 'SYS06_03', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (68, 'SYS01_05', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (69, 'SYS02_01_03', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (70, 'SYS07', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (71, 'SYS01_04', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (72, 'SYS07_01', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (73, 'SYS02_09_01', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (74, 'SYS02_09_02', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (75, 'SYS02_09_03', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (76, 'SYS02_09_04', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (77, 'SYS02_09_05', 'RUN', '系统运行');
INSERT INTO `program_privilege` VALUES (78, 'SYS02_09_06', 'RUN', '系统运行');
COMMIT;

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) NOT NULL,
  `stuff_id` bigint(20) NOT NULL,
  `room_id` bigint(20) NOT NULL,
  `time_start` datetime NOT NULL,
  `time_end` datetime NOT NULL,
  `record_status` tinyint(4) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_appointment_01` (`member_id`),
  KEY `idx_appointment_02` (`stuff_id`),
  KEY `idx_appointment_03` (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='预约';

-- ----------------------------
-- Records of reservation
-- ----------------------------
BEGIN;
INSERT INTO `reservation` VALUES (1, 1, 1, 1, '2019-03-06 17:35:32', '2019-03-07 17:35:36', 1, '2019-03-05 17:35:40', 1, '2019-03-07 17:35:46', 123, 123);
INSERT INTO `reservation` VALUES (2, 2, 2, 2, '2019-03-06 17:36:57', '2019-03-07 17:37:01', 1, '2019-03-07 17:37:04', 1, '2019-03-07 17:37:08', 123, 123);
INSERT INTO `reservation` VALUES (3, 3, 3, 5, '2019-03-05 17:38:25', '2019-03-07 17:38:29', 1, '2019-03-07 17:38:33', 1, '2019-03-07 17:38:37', 123, 123);
COMMIT;

-- ----------------------------
-- Table structure for reservation_item
-- ----------------------------
DROP TABLE IF EXISTS `reservation_item`;
CREATE TABLE `reservation_item` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reservation_id` bigint(20) NOT NULL,
  `service_id` bigint(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_reservation_item_01` (`reservation_id`),
  KEY `idx_reservation_item_02` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='预约项目';

-- ----------------------------
-- Table structure for retroactive
-- ----------------------------
DROP TABLE IF EXISTS `retroactive`;
CREATE TABLE `retroactive` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `audit_person` bigint(20) NOT NULL,
  `date` datetime NOT NULL,
  `reson` varchar(100) NOT NULL,
  `audit_statu` tinyint(4) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `audit_opinion` varchar(100) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_retroactive_01` (`date`),
  KEY `idx_retroactive_02` (`audit_statu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='补签记录表';

-- ----------------------------
-- Table structure for role_action
-- ----------------------------
DROP TABLE IF EXISTS `role_action`;
CREATE TABLE `role_action` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stuff_id` bigint(20) NOT NULL,
  `system_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_role_action_01` (`stuff_id`),
  KEY `idx_role_action_02` (`system_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限映射表';

-- ----------------------------
-- Records of role_action
-- ----------------------------
BEGIN;
INSERT INTO `role_action` VALUES (1, 1, 1);
INSERT INTO `role_action` VALUES (2, 2, 2);
INSERT INTO `role_action` VALUES (3, 2, 32);
INSERT INTO `role_action` VALUES (4, 17, 38);
INSERT INTO `role_action` VALUES (5, 18, 39);
INSERT INTO `role_action` VALUES (6, 19, 40);
INSERT INTO `role_action` VALUES (7, 20, 41);
INSERT INTO `role_action` VALUES (8, 21, 42);
INSERT INTO `role_action` VALUES (9, 22, 43);
INSERT INTO `role_action` VALUES (10, 23, 44);
INSERT INTO `role_action` VALUES (11, 24, 45);
INSERT INTO `role_action` VALUES (12, 25, 46);
INSERT INTO `role_action` VALUES (13, 26, 47);
INSERT INTO `role_action` VALUES (14, 27, 48);
INSERT INTO `role_action` VALUES (15, 38, 51);
INSERT INTO `role_action` VALUES (16, 37, 50);
INSERT INTO `role_action` VALUES (17, 41, 52);
INSERT INTO `role_action` VALUES (18, 39, 54);
INSERT INTO `role_action` VALUES (19, 40, 53);
INSERT INTO `role_action` VALUES (20, 42, 55);
INSERT INTO `role_action` VALUES (21, 43, 56);
INSERT INTO `role_action` VALUES (22, 44, 57);
INSERT INTO `role_action` VALUES (23, 45, 58);
COMMIT;

-- ----------------------------
-- Table structure for role_privilege
-- ----------------------------
DROP TABLE IF EXISTS `role_privilege`;
CREATE TABLE `role_privilege` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `program_privilege_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `program_id` varchar(50) NOT NULL,
  `privilege_code` varchar(50) NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `IDX_ROLE_PRIVILEGE_0` (`role_id`),
  KEY `IDX_ROLE_PRIVILEGE_1` (`program_id`)
) ENGINE=InnoDB AUTO_INCREMENT=221 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role_privilege
-- ----------------------------
BEGIN;
INSERT INTO `role_privilege` VALUES (32, 10, 1, 'SYS01_02', 'RUN');
INSERT INTO `role_privilege` VALUES (33, 1, 1, 'SYS01', 'RUN');
INSERT INTO `role_privilege` VALUES (38, 2, 1, 'SYS01_01', 'RUN');
INSERT INTO `role_privilege` VALUES (39, 3, 1, 'SYS01_01', 'INSERT');
INSERT INTO `role_privilege` VALUES (40, 4, 1, 'SYS01_01', 'UPDATE');
INSERT INTO `role_privilege` VALUES (41, 5, 1, 'SYS01_01', 'DELETE');
INSERT INTO `role_privilege` VALUES (42, 6, 1, 'SYS01_01', 'STOP_USER');
INSERT INTO `role_privilege` VALUES (43, 7, 1, 'SYS01_01', 'START_USER');
INSERT INTO `role_privilege` VALUES (44, 8, 1, 'SYS01_01', 'RESET_PASSWORD');
INSERT INTO `role_privilege` VALUES (45, 11, 1, 'SYS01_02', 'INSERT');
INSERT INTO `role_privilege` VALUES (46, 12, 1, 'SYS01_02', 'UPDATE');
INSERT INTO `role_privilege` VALUES (47, 13, 1, 'SYS01_02', 'DELETE');
INSERT INTO `role_privilege` VALUES (48, 14, 1, 'SYS01_02', 'ASSIGN_ROLE');
INSERT INTO `role_privilege` VALUES (52, 2, 2, 'SYS01_01', 'RUN');
INSERT INTO `role_privilege` VALUES (53, 1, 2, 'SYS01', 'RUN');
INSERT INTO `role_privilege` VALUES (59, 6, 9, 'SYS01_01', 'STOP_USER');
INSERT INTO `role_privilege` VALUES (60, 2, 9, 'SYS01_01', 'RUN');
INSERT INTO `role_privilege` VALUES (61, 1, 9, 'SYS01', 'RUN');
INSERT INTO `role_privilege` VALUES (62, 7, 9, 'SYS01_01', 'START_USER');
INSERT INTO `role_privilege` VALUES (63, 8, 9, 'SYS01_01', 'RESET_PASSWORD');
INSERT INTO `role_privilege` VALUES (64, 3, 9, 'SYS01_01', 'INSERT');
INSERT INTO `role_privilege` VALUES (65, 4, 9, 'SYS01_01', 'UPDATE');
INSERT INTO `role_privilege` VALUES (66, 5, 9, 'SYS01_01', 'DELETE');
INSERT INTO `role_privilege` VALUES (67, 9, 9, 'SYS01_01', 'ASSIGN_ROLE');
INSERT INTO `role_privilege` VALUES (68, 10, 9, 'SYS01_02', 'RUN');
INSERT INTO `role_privilege` VALUES (69, 11, 9, 'SYS01_02', 'INSERT');
INSERT INTO `role_privilege` VALUES (70, 12, 9, 'SYS01_02', 'UPDATE');
INSERT INTO `role_privilege` VALUES (71, 13, 9, 'SYS01_02', 'DELETE');
INSERT INTO `role_privilege` VALUES (72, 14, 9, 'SYS01_02', 'ASSIGN_ROLE');
INSERT INTO `role_privilege` VALUES (73, 15, 9, 'SYS01_03', 'RUN');
INSERT INTO `role_privilege` VALUES (74, 16, 9, 'SYS01_03', 'INSERT');
INSERT INTO `role_privilege` VALUES (75, 17, 9, 'SYS01_03', 'UPDATE');
INSERT INTO `role_privilege` VALUES (76, 18, 9, 'SYS01_03', 'DELETE');
INSERT INTO `role_privilege` VALUES (85, 21, 2, 'SYS02_01_01', 'RUN');
INSERT INTO `role_privilege` VALUES (86, 20, 2, 'SYS02_01', 'RUN');
INSERT INTO `role_privilege` VALUES (87, 19, 2, 'SYS02', 'RUN');
INSERT INTO `role_privilege` VALUES (88, 21, 1, 'SYS02_01_01', 'RUN');
INSERT INTO `role_privilege` VALUES (89, 20, 1, 'SYS02_01', 'RUN');
INSERT INTO `role_privilege` VALUES (90, 19, 1, 'SYS02', 'RUN');
INSERT INTO `role_privilege` VALUES (93, 25, 1, 'SYS02_02_01', 'RUN');
INSERT INTO `role_privilege` VALUES (94, 24, 1, 'SYS02_02', 'RUN');
INSERT INTO `role_privilege` VALUES (95, 31, 1, 'SYS02_04_01', 'RUN');
INSERT INTO `role_privilege` VALUES (96, 30, 1, 'SYS02_04', 'RUN');
INSERT INTO `role_privilege` VALUES (97, 32, 1, 'SYS02_04_02', 'RUN');
INSERT INTO `role_privilege` VALUES (98, 33, 1, 'SYS02_05', 'RUN');
INSERT INTO `role_privilege` VALUES (99, 26, 1, 'SYS02_02_02', 'RUN');
INSERT INTO `role_privilege` VALUES (100, 28, 1, 'SYS02_03_01', 'RUN');
INSERT INTO `role_privilege` VALUES (101, 27, 1, 'SYS02_03', 'RUN');
INSERT INTO `role_privilege` VALUES (102, 29, 1, 'SYS02_03_02', 'RUN');
INSERT INTO `role_privilege` VALUES (107, 35, 1, 'SYS02_06_01', 'RUN');
INSERT INTO `role_privilege` VALUES (108, 34, 1, 'SYS02_06', 'RUN');
INSERT INTO `role_privilege` VALUES (109, 36, 1, 'SYS02_06_02', 'RUN');
INSERT INTO `role_privilege` VALUES (110, 3, 2, 'SYS01_01', 'INSERT');
INSERT INTO `role_privilege` VALUES (111, 68, 0, 'SYS01_05', 'RUN');
INSERT INTO `role_privilege` VALUES (112, 1, 0, 'SYS01', 'RUN');
INSERT INTO `role_privilege` VALUES (113, 21, 0, 'SYS02_01_01', 'RUN');
INSERT INTO `role_privilege` VALUES (114, 20, 0, 'SYS02_01', 'RUN');
INSERT INTO `role_privilege` VALUES (115, 19, 0, 'SYS02', 'RUN');
INSERT INTO `role_privilege` VALUES (116, 22, 0, 'SYS02_01_02', 'RUN');
INSERT INTO `role_privilege` VALUES (117, 23, 0, 'SYS02_01_02', 'DELETE');
INSERT INTO `role_privilege` VALUES (118, 25, 0, 'SYS02_02_01', 'RUN');
INSERT INTO `role_privilege` VALUES (119, 24, 0, 'SYS02_02', 'RUN');
INSERT INTO `role_privilege` VALUES (120, 26, 0, 'SYS02_02_02', 'RUN');
INSERT INTO `role_privilege` VALUES (121, 29, 0, 'SYS02_03_02', 'RUN');
INSERT INTO `role_privilege` VALUES (122, 27, 0, 'SYS02_03', 'RUN');
INSERT INTO `role_privilege` VALUES (123, 31, 0, 'SYS02_04_01', 'RUN');
INSERT INTO `role_privilege` VALUES (124, 30, 0, 'SYS02_04', 'RUN');
INSERT INTO `role_privilege` VALUES (125, 32, 0, 'SYS02_04_02', 'RUN');
INSERT INTO `role_privilege` VALUES (126, 33, 0, 'SYS02_05', 'RUN');
INSERT INTO `role_privilege` VALUES (127, 35, 0, 'SYS02_06_01', 'RUN');
INSERT INTO `role_privilege` VALUES (128, 34, 0, 'SYS02_06', 'RUN');
INSERT INTO `role_privilege` VALUES (129, 36, 0, 'SYS02_06_02', 'RUN');
INSERT INTO `role_privilege` VALUES (130, 2, 0, 'SYS01_01', 'RUN');
INSERT INTO `role_privilege` VALUES (131, 3, 0, 'SYS01_01', 'INSERT');
INSERT INTO `role_privilege` VALUES (132, 4, 0, 'SYS01_01', 'UPDATE');
INSERT INTO `role_privilege` VALUES (133, 5, 0, 'SYS01_01', 'DELETE');
INSERT INTO `role_privilege` VALUES (134, 6, 0, 'SYS01_01', 'STOP_USER');
INSERT INTO `role_privilege` VALUES (135, 7, 0, 'SYS01_01', 'START_USER');
INSERT INTO `role_privilege` VALUES (136, 8, 0, 'SYS01_01', 'RESET_PASSWORD');
INSERT INTO `role_privilege` VALUES (137, 28, 0, 'SYS02_03_01', 'RUN');
INSERT INTO `role_privilege` VALUES (138, 10, 0, 'SYS01_02', 'RUN');
INSERT INTO `role_privilege` VALUES (139, 11, 0, 'SYS01_02', 'INSERT');
INSERT INTO `role_privilege` VALUES (140, 12, 0, 'SYS01_02', 'UPDATE');
INSERT INTO `role_privilege` VALUES (141, 13, 0, 'SYS01_02', 'DELETE');
INSERT INTO `role_privilege` VALUES (142, 14, 0, 'SYS01_02', 'ASSIGN_ROLE');
INSERT INTO `role_privilege` VALUES (143, 68, 1, 'SYS01_05', 'RUN');
INSERT INTO `role_privilege` VALUES (144, 69, 1, 'SYS02_01_03', 'RUN');
INSERT INTO `role_privilege` VALUES (145, 38, 1, 'SYS02_07_01', 'RUN');
INSERT INTO `role_privilege` VALUES (146, 37, 1, 'SYS02_07', 'RUN');
INSERT INTO `role_privilege` VALUES (147, 39, 1, 'SYS02_07_02', 'RUN');
INSERT INTO `role_privilege` VALUES (148, 40, 1, 'SYS02_07_03', 'RUN');
INSERT INTO `role_privilege` VALUES (149, 42, 1, 'SYS02_08_01', 'RUN');
INSERT INTO `role_privilege` VALUES (150, 41, 1, 'SYS02_08', 'RUN');
INSERT INTO `role_privilege` VALUES (151, 43, 1, 'SYS02_08_02', 'RUN');
INSERT INTO `role_privilege` VALUES (152, 44, 1, 'SYS02_08_03', 'RUN');
INSERT INTO `role_privilege` VALUES (153, 45, 1, 'SYS02_08_04', 'RUN');
INSERT INTO `role_privilege` VALUES (154, 46, 1, 'SYS02_08_05', 'RUN');
INSERT INTO `role_privilege` VALUES (155, 47, 1, 'SYS02_08_06', 'RUN');
INSERT INTO `role_privilege` VALUES (156, 49, 1, 'SYS03_01', 'RUN');
INSERT INTO `role_privilege` VALUES (157, 48, 1, 'SYS03', 'RUN');
INSERT INTO `role_privilege` VALUES (158, 50, 1, 'SYS03_02', 'RUN');
INSERT INTO `role_privilege` VALUES (159, 51, 1, 'SYS03_03', 'RUN');
INSERT INTO `role_privilege` VALUES (160, 52, 1, 'SYS03_04', 'RUN');
INSERT INTO `role_privilege` VALUES (161, 54, 1, 'SYS04_01', 'RUN');
INSERT INTO `role_privilege` VALUES (162, 53, 1, 'SYS04', 'RUN');
INSERT INTO `role_privilege` VALUES (163, 55, 1, 'SYS04_02', 'RUN');
INSERT INTO `role_privilege` VALUES (164, 56, 1, 'SYS04_03', 'RUN');
INSERT INTO `role_privilege` VALUES (165, 57, 1, 'SYS04_04', 'RUN');
INSERT INTO `role_privilege` VALUES (166, 59, 1, 'SYS05_01', 'RUN');
INSERT INTO `role_privilege` VALUES (167, 58, 1, 'SYS05', 'RUN');
INSERT INTO `role_privilege` VALUES (168, 60, 1, 'SYS05_02', 'RUN');
INSERT INTO `role_privilege` VALUES (169, 61, 1, 'SYS05_03', 'RUN');
INSERT INTO `role_privilege` VALUES (170, 63, 1, 'SYS06_01', 'RUN');
INSERT INTO `role_privilege` VALUES (171, 62, 1, 'SYS06', 'RUN');
INSERT INTO `role_privilege` VALUES (172, 64, 1, 'SYS06_02', 'RUN');
INSERT INTO `role_privilege` VALUES (173, 65, 1, 'SYS06_03', 'RUN');
INSERT INTO `role_privilege` VALUES (174, 15, 1, 'SYS01_03', 'RUN');
INSERT INTO `role_privilege` VALUES (175, 16, 1, 'SYS01_03', 'INSERT');
INSERT INTO `role_privilege` VALUES (176, 17, 1, 'SYS01_03', 'UPDATE');
INSERT INTO `role_privilege` VALUES (177, 18, 1, 'SYS01_03', 'DELETE');
INSERT INTO `role_privilege` VALUES (178, 38, 1, 'SYS02_07_01', 'RUN');
INSERT INTO `role_privilege` VALUES (179, 37, 1, 'SYS02_07', 'RUN');
INSERT INTO `role_privilege` VALUES (180, 39, 1, 'SYS02_07_02', 'RUN');
INSERT INTO `role_privilege` VALUES (181, 40, 1, 'SYS02_07_03', 'RUN');
INSERT INTO `role_privilege` VALUES (182, 42, 1, 'SYS02_08_01', 'RUN');
INSERT INTO `role_privilege` VALUES (183, 41, 1, 'SYS02_08', 'RUN');
INSERT INTO `role_privilege` VALUES (184, 43, 1, 'SYS02_08_02', 'RUN');
INSERT INTO `role_privilege` VALUES (185, 44, 1, 'SYS02_08_03', 'RUN');
INSERT INTO `role_privilege` VALUES (186, 45, 1, 'SYS02_08_04', 'RUN');
INSERT INTO `role_privilege` VALUES (187, 46, 1, 'SYS02_08_05', 'RUN');
INSERT INTO `role_privilege` VALUES (188, 47, 1, 'SYS02_08_06', 'RUN');
INSERT INTO `role_privilege` VALUES (189, 49, 1, 'SYS03_01', 'RUN');
INSERT INTO `role_privilege` VALUES (190, 48, 1, 'SYS03', 'RUN');
INSERT INTO `role_privilege` VALUES (191, 50, 1, 'SYS03_02', 'RUN');
INSERT INTO `role_privilege` VALUES (192, 51, 1, 'SYS03_03', 'RUN');
INSERT INTO `role_privilege` VALUES (193, 52, 1, 'SYS03_04', 'RUN');
INSERT INTO `role_privilege` VALUES (194, 54, 1, 'SYS04_01', 'RUN');
INSERT INTO `role_privilege` VALUES (195, 53, 1, 'SYS04', 'RUN');
INSERT INTO `role_privilege` VALUES (196, 55, 1, 'SYS04_02', 'RUN');
INSERT INTO `role_privilege` VALUES (197, 56, 1, 'SYS04_03', 'RUN');
INSERT INTO `role_privilege` VALUES (198, 57, 1, 'SYS04_04', 'RUN');
INSERT INTO `role_privilege` VALUES (199, 59, 1, 'SYS05_01', 'RUN');
INSERT INTO `role_privilege` VALUES (200, 58, 1, 'SYS05', 'RUN');
INSERT INTO `role_privilege` VALUES (201, 60, 1, 'SYS05_02', 'RUN');
INSERT INTO `role_privilege` VALUES (202, 61, 1, 'SYS05_03', 'RUN');
INSERT INTO `role_privilege` VALUES (203, 63, 1, 'SYS06_01', 'RUN');
INSERT INTO `role_privilege` VALUES (204, 62, 1, 'SYS06', 'RUN');
INSERT INTO `role_privilege` VALUES (205, 64, 1, 'SYS06_02', 'RUN');
INSERT INTO `role_privilege` VALUES (206, 65, 1, 'SYS06_03', 'RUN');
INSERT INTO `role_privilege` VALUES (207, 15, 1, 'SYS01_03', 'RUN');
INSERT INTO `role_privilege` VALUES (208, 16, 1, 'SYS01_03', 'INSERT');
INSERT INTO `role_privilege` VALUES (209, 17, 1, 'SYS01_03', 'UPDATE');
INSERT INTO `role_privilege` VALUES (210, 18, 1, 'SYS01_03', 'DELETE');
INSERT INTO `role_privilege` VALUES (211, 9, 1, 'SYS01_01', 'ASSIGN_ROLE');
INSERT INTO `role_privilege` VALUES (212, 72, 1, 'SYS07_01', 'RUN');
INSERT INTO `role_privilege` VALUES (213, 70, 1, 'SYS07', 'RUN');
INSERT INTO `role_privilege` VALUES (214, 73, 1, 'SYS02_09_01', 'RUN');
INSERT INTO `role_privilege` VALUES (215, 74, 1, 'SYS02_09_02', 'RUN');
INSERT INTO `role_privilege` VALUES (216, 75, 1, 'SYS02_09_03', 'RUN');
INSERT INTO `role_privilege` VALUES (217, 76, 1, 'SYS02_09_04', 'RUN');
INSERT INTO `role_privilege` VALUES (218, 77, 1, 'SYS02_09_05', 'RUN');
INSERT INTO `role_privilege` VALUES (219, 78, 1, 'SYS02_09_06', 'RUN');
INSERT INTO `role_privilege` VALUES (220, 71, 1, 'SYS01_04', 'RUN');
COMMIT;

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `IDX_ROLE_USER_0` (`role_id`),
  KEY `IDX_ROLE_USER_1` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role_user
-- ----------------------------
BEGIN;
INSERT INTO `role_user` VALUES (2, 2, 2);
INSERT INTO `role_user` VALUES (4, 1, 18);
INSERT INTO `role_user` VALUES (5, 1, 14);
INSERT INTO `role_user` VALUES (6, 2, 14);
INSERT INTO `role_user` VALUES (7, 3, 14);
INSERT INTO `role_user` VALUES (8, 1, 4);
INSERT INTO `role_user` VALUES (9, 2, 4);
INSERT INTO `role_user` VALUES (13, 2, 38);
INSERT INTO `role_user` VALUES (15, 2, 34);
INSERT INTO `role_user` VALUES (16, 1, 32);
INSERT INTO `role_user` VALUES (18, 2, 39);
INSERT INTO `role_user` VALUES (19, 2, 40);
INSERT INTO `role_user` VALUES (20, 2, 41);
INSERT INTO `role_user` VALUES (21, 2, 42);
INSERT INTO `role_user` VALUES (22, 2, 43);
INSERT INTO `role_user` VALUES (23, 2, 44);
INSERT INTO `role_user` VALUES (24, 2, 45);
INSERT INTO `role_user` VALUES (25, 2, 46);
INSERT INTO `role_user` VALUES (26, 2, 47);
INSERT INTO `role_user` VALUES (27, 2, 48);
INSERT INTO `role_user` VALUES (28, 1, 1);
INSERT INTO `role_user` VALUES (29, 2, 51);
INSERT INTO `role_user` VALUES (30, 2, 50);
INSERT INTO `role_user` VALUES (31, 2, 53);
INSERT INTO `role_user` VALUES (32, 2, 54);
INSERT INTO `role_user` VALUES (33, 2, 52);
INSERT INTO `role_user` VALUES (34, 2, 55);
INSERT INTO `role_user` VALUES (35, 2, 56);
INSERT INTO `role_user` VALUES (36, 2, 57);
INSERT INTO `role_user` VALUES (37, 2, 58);
INSERT INTO `role_user` VALUES (38, 1, 59);
COMMIT;

-- ----------------------------
-- Table structure for salon
-- ----------------------------
DROP TABLE IF EXISTS `salon`;
CREATE TABLE `salon` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `salon_name` varchar(120) NOT NULL,
  `parent_id` bigint(20) NOT NULL,
  `tel` varchar(50) NOT NULL,
  `city_id` bigint(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `door_2_door` bit(1) NOT NULL DEFAULT b'0',
  `bed_num` int(11) NOT NULL DEFAULT '0',
  `area` double(8,2) NOT NULL DEFAULT '0.00',
  `time_open` varchar(50) DEFAULT NULL,
  `time_close` varchar(50) DEFAULT NULL,
  `longitude` decimal(10,7) NOT NULL,
  `latitude` decimal(10,7) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  `audit` tinyint(5) NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_beauty_salon_01` (`salon_name`),
  KEY `idx_beauty_salon_02` (`tel`),
  KEY `idx_beauty_salon_03` (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='美容院/门店';

-- ----------------------------
-- Records of salon
-- ----------------------------
BEGIN;
INSERT INTO `salon` VALUES (1, '鸿发美容院', -1, '18664151832', 0, '广东东莞', b'0', 0, 0.00, NULL, NULL, 0.0000000, 0.0000000, NULL, '2019-02-28 01:44:56', 1, NULL, NULL, 0, 1);
INSERT INTO `salon` VALUES (2, '大岭山分店好', 1, '189999999966666666', 1, '广东东莞大岭山', b'0', 0, 0.00, '', '', 113.7838360, 23.0338060, '鸿发美容院大岭山分店在哪里呢', '2019-02-28 01:44:56', 1, '2019-03-28 09:19:57', 1, 0, 1);
INSERT INTO `salon` VALUES (3, '常平分店1', 1, '18664151832', 1, '广东东莞常平', b'0', 1, 200.00, '16:00:00', '16:00:00', 0.0000000, 0.0000000, 'asd', '2019-02-28 01:44:56', 1, '2019-03-27 08:58:40', 32, 0, 1);
INSERT INTO `salon` VALUES (5, '分店', 1, '18664151832', 1, '广东东莞大岭山', b'0', 0, 0.00, '00:00:00', '00:00:00', 0.0000000, 0.0000000, '鸿发美容院大岭山分店', '2019-02-28 01:44:56', 1, '2019-03-21 03:37:10', 1, 0, 0);
INSERT INTO `salon` VALUES (6, '分店', 1, '18664151832', 1, '广东东莞大岭山', b'0', 0, 0.00, '00:00:00', '00:00:00', 0.0000000, 0.0000000, '鸿发美容院大岭山分店', '2019-02-28 01:44:56', 1, '2019-03-21 03:37:10', 1, 0, 0);
INSERT INTO `salon` VALUES (8, '分店', 1, '18664151832', 1, '广东东莞大岭山', b'0', 0, 0.00, '00:00:00', '00:00:00', 0.0000000, 0.0000000, '鸿发美容院大岭山分店', '2019-02-28 01:44:56', 1, '2019-03-21 03:37:10', 1, 0, 0);
INSERT INTO `salon` VALUES (12, '分店', 1, '18664151832', 1, '广东东莞大岭山', b'0', 0, 0.00, '00:00:00', '00:00:00', 0.0000000, 0.0000000, '鸿发美容院大岭山分店', '2019-02-28 01:44:56', 1, '2019-03-21 03:37:10', 1, 0, 0);
INSERT INTO `salon` VALUES (13, '分店1111', 1, '18664151832', 1, '广东东莞大岭山', b'0', 0, 0.00, '00:00:00', '00:00:00', 0.0000000, 0.0000000, '鸿发美容院大岭山分店', '2019-02-28 01:44:56', 1, '2019-03-21 03:37:10', 1, 0, 0);
INSERT INTO `salon` VALUES (14, '分店33333', 1, '18664151832', 1, '广东东莞大岭山', b'0', 0, 0.00, '00:00:00', '00:00:00', 0.0000000, 0.0000000, '鸿发美容院大岭山分店', '2019-02-28 01:44:56', 1, '2019-03-21 03:37:10', 1, 0, 0);
INSERT INTO `salon` VALUES (15, '柏林', 1, '16455545444', 0, '东莞莞樟路', b'1', 5, 100.00, NULL, NULL, 0.0000000, 0.0000000, '服务好，技师漂亮（滑稽）', '2019-03-22 09:21:16', 32, NULL, NULL, 0, 0);
INSERT INTO `salon` VALUES (16, '柏林', -1, '13929433191', 0, '东莞莞樟路', b'1', 5, 100.00, NULL, NULL, 0.0000000, 0.0000000, '服务好，技师漂亮（滑稽）', '2019-03-22 09:22:56', 32, NULL, NULL, 0, 0);
INSERT INTO `salon` VALUES (17, '20号美容院', -1, '18664151832', 0, '东莞东城', b'1', 0, 0.00, NULL, NULL, 0.0000000, 0.0000000, NULL, '2019-03-26 09:00:49', 1, NULL, NULL, 0, 0);
INSERT INTO `salon` VALUES (18, '30号美容院', -1, '1688888888', 0, '东莞东城', b'1', 0, 0.00, NULL, NULL, 0.0000000, 0.0000000, NULL, '2019-03-26 09:10:16', 1, NULL, NULL, 0, 0);
INSERT INTO `salon` VALUES (19, '给你我会', 1, '13929433196', 441903, '广东省东莞市东城区莞樟路133-5号', b'1', 0, 0.00, NULL, NULL, 113.7937850, 23.0298930, NULL, '2019-03-27 02:45:44', 1, NULL, NULL, 0, 0);
INSERT INTO `salon` VALUES (20, '东城美容院', -1, '1378888888', 0, '东莞东城', b'0', 0, 0.00, NULL, NULL, 0.0000000, 0.0000000, NULL, '2019-03-27 03:20:49', 32, NULL, NULL, 0, 0);
INSERT INTO `salon` VALUES (21, '明年', 1, '13929433197', 441903, '广东省东莞市东城区万达广场', b'1', 0, 0.00, NULL, NULL, 113.7838360, 23.0338060, NULL, '2019-03-27 07:59:40', 1, NULL, NULL, 0, 0);
INSERT INTO `salon` VALUES (22, '救命', 1, '13929477194', 441903, '广东省东莞市东城区万达广场', b'1', 0, 0.00, NULL, NULL, 113.7838360, 23.0338060, NULL, '2019-03-27 08:27:06', 1, NULL, NULL, 0, 0);
INSERT INTO `salon` VALUES (23, '刚好回家家', 1, '156699445666', 130121, '河北省石家庄市井陉县哼哼唧唧108', b'1', 0, 0.00, NULL, NULL, 114.1452400, 38.0321480, NULL, '2019-03-27 08:38:18', 1, NULL, NULL, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for salon_invite_code
-- ----------------------------
DROP TABLE IF EXISTS `salon_invite_code`;
CREATE TABLE `salon_invite_code` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `salon_id` bigint(20) NOT NULL,
  `invite_code` varchar(10) NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_salon_invite_code_01` (`salon_id`),
  KEY `idx_salon_invite_code_02` (`invite_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='美容院邀请码';

-- ----------------------------
-- Records of salon_invite_code
-- ----------------------------
BEGIN;
INSERT INTO `salon_invite_code` VALUES (1, 1, 'EvEmwig9');
INSERT INTO `salon_invite_code` VALUES (2, 20, 'pwcmOe3G');
COMMIT;

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stuff_id` bigint(20) NOT NULL,
  `shift_id` bigint(20) NOT NULL,
  `day` date NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_schedule_01` (`stuff_id`),
  KEY `idx_schedule_02` (`day`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COMMENT='排班信息表';

-- ----------------------------
-- Records of schedule
-- ----------------------------
BEGIN;
INSERT INTO `schedule` VALUES (1, 1, 10, '2019-03-15', '2019-03-15 17:10:53', 1, '2019-03-15 17:10:56', 1, 1);
INSERT INTO `schedule` VALUES (2, 2, 10, '2019-04-01', '2019-03-21 10:29:32', 32, NULL, NULL, 0);
INSERT INTO `schedule` VALUES (5, 1, 11, '2019-03-21', '2019-03-21 10:33:53', 32, NULL, NULL, 0);
INSERT INTO `schedule` VALUES (6, 1, 11, '2019-03-02', '2019-03-21 10:33:53', 32, NULL, NULL, 0);
INSERT INTO `schedule` VALUES (7, 1, 13, '2019-03-06', '2019-03-21 10:35:02', 32, NULL, NULL, 0);
INSERT INTO `schedule` VALUES (13, 1, 13, '2019-03-01', '2019-03-21 10:46:57', 32, NULL, NULL, 0);
INSERT INTO `schedule` VALUES (14, 3, 10, '2019-03-08', '2019-03-22 00:54:33', 32, NULL, NULL, 0);
INSERT INTO `schedule` VALUES (15, 3, 13, '2019-03-01', '2019-03-22 00:54:33', 32, NULL, NULL, 0);
INSERT INTO `schedule` VALUES (16, 3, 10, '2019-03-22', '2019-03-22 00:58:37', 32, NULL, NULL, 0);
INSERT INTO `schedule` VALUES (17, 1, 10, '2019-03-08', '2019-03-22 02:27:44', 32, NULL, NULL, 0);
INSERT INTO `schedule` VALUES (18, 1, 11, '2019-03-13', '2019-03-25 08:37:20', 32, NULL, NULL, 0);
INSERT INTO `schedule` VALUES (24, 1, 64, '2019-03-28', '2019-03-28 12:03:10', 1, NULL, NULL, 0);
INSERT INTO `schedule` VALUES (26, 1, 65, '2019-03-29', '2019-03-29 15:09:58', 1, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for service
-- ----------------------------
DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_id` bigint(20) NOT NULL,
  `service_name` varchar(50) NOT NULL,
  `service_series_id` bigint(20) NOT NULL,
  `card_type` tinyint(4) NOT NULL,
  `record_status` tinyint(4) NOT NULL,
  `expired_time` double(3,1) DEFAULT NULL,
  `period_per_time` int(11) NOT NULL,
  `price_per_time` double(8,2) NOT NULL,
  `price_market` double(8,2) NOT NULL,
  `price` double(8,2) NOT NULL,
  `return_visit` int(11) DEFAULT NULL,
  `time_total` int(11) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_service_card_01` (`service_series_id`),
  KEY `idx_service_card_02` (`card_type`),
  KEY `idx_service_card_03` (`service_name`),
  KEY `idx_service_card_04` (`record_status`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COMMENT='次卡/服务项目';

-- ----------------------------
-- Records of service
-- ----------------------------
BEGIN;
INSERT INTO `service` VALUES (1, 0, '二号项目', 4, 0, 0, 30.0, 1, 100.00, 200.00, 1000.00, 10, 20, '', '2019-03-06 01:52:42', 1, '2019-03-06 02:47:20', 1, 0);
INSERT INTO `service` VALUES (2, 0, '8号号项目', 4, 0, 0, 30.0, 1, 100.00, 200.00, 1000.00, 10, 20, '', '2019-03-06 07:24:56', 1, '2019-03-06 07:26:50', 1, 0);
INSERT INTO `service` VALUES (3, 0, '四号号项目', 4, 0, 0, 30.0, 1, 100.00, 200.00, 1000.00, 10, 20, '', '2019-03-06 07:25:05', 1, NULL, NULL, 0);
INSERT INTO `service` VALUES (4, 0, '9号号项目', 4, 0, 0, 30.0, 1, 100.00, 200.00, 1000.00, 10, 20, '', '2019-03-06 08:53:36', 1, NULL, NULL, 0);
INSERT INTO `service` VALUES (5, 0, '10号号项目', 4, 0, 0, 30.0, 1, 100.00, 200.00, 1000.00, 10, 20, '', '2019-03-06 08:57:03', 1, NULL, NULL, 0);
INSERT INTO `service` VALUES (6, 0, '7号号项目', 4, 0, 0, 30.0, 1, 100.00, 200.00, 1000.00, 10, 20, '', '2019-03-06 08:59:00', 1, NULL, NULL, 0);
INSERT INTO `service` VALUES (7, 0, '6号号项目', 4, 0, 0, 30.0, 1, 100.00, 200.00, 1000.00, 10, 20, '', '2019-03-06 09:09:12', 2, NULL, NULL, 0);
INSERT INTO `service` VALUES (12, 2, '刮痧2', 7, 0, 1, 0.0, 5, 5.00, 0.00, 5.00, 5, 0, '哈提', '2019-03-19 03:50:02', 1, '2019-03-25 09:39:30', 32, 0);
INSERT INTO `service` VALUES (13, 2, '按摩', 5, 4, 0, 12.0, 48, 0.00, 800.00, 360.00, 1, 1, '垃圾篓', '2019-03-19 03:54:37', 1, NULL, NULL, 0);
INSERT INTO `service` VALUES (14, 2, '墨迹健', 11, 0, 1, 0.0, 58, 10.00, 0.00, 100.00, 1, 0, '空军建军节', '2019-03-19 07:25:02', 1, '2019-03-20 09:47:41', 1, 0);
INSERT INTO `service` VALUES (15, 2, '泰国大保健', 11, 0, 1, 0.0, 58, 10.00, 0.00, 100.00, 1, 0, '', '2019-03-19 07:25:02', 1, '2019-03-20 09:57:13', 1, 0);
INSERT INTO `service` VALUES (16, 2, '马来西亚按摩', 11, 0, 0, 0.0, 58, 10.00, 0.00, 100.00, 1, 0, NULL, '2019-03-19 07:25:02', 1, NULL, NULL, 0);
INSERT INTO `service` VALUES (17, 2, '特大优惠项目', 11, 0, 0, 0.0, 58, 10.00, 0.00, 100.00, 1, 0, NULL, '2019-03-19 07:25:02', 1, NULL, NULL, 0);
INSERT INTO `service` VALUES (18, 2, '优惠项目1', 11, 0, 0, 0.0, 58, 10.00, 0.00, 100.00, 11, 0, NULL, '2019-03-19 07:25:02', 1, NULL, NULL, 0);
INSERT INTO `service` VALUES (19, 2, '优惠项目2', 14, 0, 0, 0.0, 58, 10.00, 0.00, 100.00, 1, 0, NULL, '2019-03-19 07:25:02', 1, NULL, NULL, 0);
INSERT INTO `service` VALUES (20, 2, '优惠项目3', 14, 0, 0, 0.0, 58, 10.00, 0.00, 100.00, 1, 0, NULL, '2019-03-19 07:25:02', 1, NULL, NULL, 0);
INSERT INTO `service` VALUES (21, 2, '优惠项目4', 14, 0, 0, 0.0, 58, 10.00, 0.00, 100.00, 1, 0, NULL, '2019-03-19 07:25:02', 1, NULL, NULL, 0);
INSERT INTO `service` VALUES (22, 2, '优惠项目5', 14, 0, 0, 0.0, 58, 10.00, 0.00, 100.00, 1, 0, NULL, '2019-03-19 07:25:02', 1, NULL, NULL, 0);
INSERT INTO `service` VALUES (23, 2, '推背', 6, 1, 0, 1.0, 10, 0.00, 300.00, 100.00, 1, 6, '流星雨', '2019-03-19 09:25:48', 1, NULL, NULL, 0);
INSERT INTO `service` VALUES (47, 2, '9', 8, 0, 0, NULL, 9, 6.00, -1.00, 6.00, 8, -1, '给哈哈哈', '2019-03-29 16:27:45', 1, NULL, NULL, 0);
INSERT INTO `service` VALUES (48, 2, '9', 8, 0, 0, NULL, 6, 5.00, -1.00, 3.00, 5, -1, '规划局', '2019-03-29 16:29:18', 1, NULL, NULL, 0);
INSERT INTO `service` VALUES (49, 2, '9', 4, 0, 0, 0.0, 9, 9.00, -1.00, 9.00, 9, -1, '体验一下', '2019-03-29 16:30:46', 1, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for service_series
-- ----------------------------
DROP TABLE IF EXISTS `service_series`;
CREATE TABLE `service_series` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_id` bigint(20) NOT NULL,
  `series_name` varchar(50) NOT NULL,
  `parent_id` bigint(20) NOT NULL,
  `record_status` tinyint(4) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_service_series_01` (`series_name`),
  KEY `idx_service_series_02` (`parent_id`),
  KEY `idx_service_series_03` (`record_status`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COMMENT='项目类别/系列';

-- ----------------------------
-- Records of service_series
-- ----------------------------
BEGIN;
INSERT INTO `service_series` VALUES (2, 2, '推背2', 0, 0, '2019-03-05 02:47:03', 1, NULL, NULL, 0);
INSERT INTO `service_series` VALUES (3, 2, '推背3', 0, 0, '2019-03-05 02:47:08', 1, NULL, NULL, 0);
INSERT INTO `service_series` VALUES (4, 2, '推背4', 2, 0, '2019-03-05 02:47:08', 1, NULL, NULL, 0);
INSERT INTO `service_series` VALUES (5, 2, '推背5', 3, 0, '2019-03-05 02:47:08', 1, NULL, NULL, 0);
INSERT INTO `service_series` VALUES (6, 2, '推背6', 3, 0, '2019-03-05 02:47:08', 1, NULL, NULL, 0);
INSERT INTO `service_series` VALUES (7, 2, '泰式按摩', 3, 0, '2019-03-16 04:01:24', 1, NULL, NULL, 0);
INSERT INTO `service_series` VALUES (8, 2, '洗脸', 2, 0, '2019-03-16 04:04:13', 1, NULL, NULL, 0);
INSERT INTO `service_series` VALUES (9, 2, '哈哈哈', 0, 0, '2019-03-16 04:05:09', 1, NULL, NULL, 0);
INSERT INTO `service_series` VALUES (10, 2, '泰式搓背', 7, 0, '2019-03-16 04:05:09', 1, NULL, NULL, 0);
INSERT INTO `service_series` VALUES (11, 2, '泰国大保健', 7, 0, '2019-03-16 04:05:09', 1, NULL, NULL, 0);
INSERT INTO `service_series` VALUES (12, 2, '面部', 0, 0, '2019-03-19 08:10:30', 1, NULL, NULL, 0);
INSERT INTO `service_series` VALUES (13, 2, '推背7', 2, 0, '2019-03-23 16:02:57', 1, NULL, NULL, 0);
INSERT INTO `service_series` VALUES (14, 2, '北图', 2, 0, '2019-03-23 16:03:19', 1, NULL, NULL, 0);
INSERT INTO `service_series` VALUES (15, 2, '就看到', 2, 0, '2019-03-23 16:03:41', 1, NULL, NULL, 0);
INSERT INTO `service_series` VALUES (16, 2, '看到家里是', 2, 0, '2019-03-23 16:03:56', 1, NULL, NULL, 0);
INSERT INTO `service_series` VALUES (17, 2, '测试', 0, 0, '2019-03-29 16:24:46', 1, NULL, NULL, 0);
INSERT INTO `service_series` VALUES (18, 2, '测试测试', 0, 0, '2019-03-29 16:33:12', 1, NULL, NULL, 0);
INSERT INTO `service_series` VALUES (19, 2, '背时', 0, 0, '2019-03-29 16:40:27', 1, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for service_suite
-- ----------------------------
DROP TABLE IF EXISTS `service_suite`;
CREATE TABLE `service_suite` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_id` bigint(20) NOT NULL,
  `suite_name` varchar(50) NOT NULL,
  `price_market` double(8,2) NOT NULL,
  `price` double(8,2) NOT NULL,
  `time_create` datetime NOT NULL,
  `time_expired` datetime DEFAULT NULL,
  `record_status` tinyint(4) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_service_suite_01` (`suite_name`),
  KEY `idx_service_suite_02` (`record_status`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COMMENT='套卡/服务套餐';

-- ----------------------------
-- Records of service_suite
-- ----------------------------
BEGIN;
INSERT INTO `service_suite` VALUES (2, 2, '套卡12', 100.00, 80.00, '2019-03-04 18:47:08', '2019-03-04 18:47:08', 0, '简介1', '2019-03-07 02:06:26', 1, '2019-03-07 02:37:28', 1, 0);
INSERT INTO `service_suite` VALUES (5, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:49', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:49', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (6, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:50', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:50', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (7, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:51', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:51', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (8, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:52', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:52', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (9, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:52', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:52', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (10, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:53', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:53', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (11, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:53', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:53', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (12, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:53', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:53', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (13, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:53', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:53', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (14, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:55', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:55', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (15, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:55', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:55', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (16, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:55', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:55', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (17, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:56', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (18, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:56', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (19, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:56', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (20, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:56', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (21, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:56', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (22, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:56', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (23, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:57', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:57', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (24, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:57', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:57', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (25, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:57', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:57', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (26, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:25:57', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:25:57', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (27, 2, '哈哈大保健', 420.00, 420.00, '2019-03-23 02:26:03', '2019-03-22 16:00:00', 0, '明明哦', '2019-03-23 02:26:03', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (30, 2, 'lolly', 600.00, 600.00, '2019-03-23 02:31:33', '2019-03-22 16:00:00', 0, '楼OK了', '2019-03-23 02:31:33', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (31, 2, 'lolly', 600.00, 600.00, '2019-03-23 02:31:33', '2019-03-22 16:00:00', 0, '楼OK了', '2019-03-23 02:31:33', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (32, 2, 'lolly', 600.00, 600.00, '2019-03-23 02:31:34', '2019-03-22 16:00:00', 0, '楼OK了', '2019-03-23 02:31:34', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (33, 2, '今生今世', 300.00, 300.00, '2019-03-23 02:33:59', '2019-03-26 16:00:00', 0, '名额', '2019-03-23 02:33:59', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (34, 2, '恐恐', 680.00, 680.00, '2019-03-23 02:38:21', '2019-03-22 16:00:00', 0, '匿名', '2019-03-23 02:38:21', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (35, 2, '恐恐', 680.00, 680.00, '2019-03-23 02:38:21', '2019-03-22 16:00:00', 0, '匿名', '2019-03-23 02:38:21', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (36, 2, '恐恐', 680.00, 680.00, '2019-03-23 02:38:21', '2019-03-22 16:00:00', 0, '匿名', '2019-03-23 02:38:21', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (37, 2, '恐恐', 680.00, 680.00, '2019-03-23 02:39:40', '2019-03-22 16:00:00', 0, '匿名', '2019-03-23 02:39:40', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (38, 2, '论坛', 700.00, 700.00, '2019-03-23 02:42:24', '2019-03-22 16:00:00', 0, '明年', '2019-03-23 02:42:24', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (39, 2, '套卡007号', 10.00, 20.00, '2019-03-29 11:29:22', '1970-01-01 08:00:00', 0, '', '2019-03-29 11:29:22', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (40, 2, '套卡008', 10.00, 20.00, '2019-03-29 11:30:30', '1970-01-01 08:00:00', 0, '', '2019-03-29 11:30:30', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (41, 2, '套卡100', 10.00, 20.00, '2019-03-29 11:43:24', '1970-01-01 08:00:00', 0, '', '2019-03-29 11:43:24', 1, NULL, NULL, 0);
INSERT INTO `service_suite` VALUES (42, 2, '套卡10', 20.00, 100.00, '2019-03-29 11:59:00', '2019-03-31 00:00:00', 0, '', '2019-03-29 11:59:00', 1, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for service_suite_item
-- ----------------------------
DROP TABLE IF EXISTS `service_suite_item`;
CREATE TABLE `service_suite_item` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `service_suite_id` bigint(20) NOT NULL,
  `service_id` bigint(20) NOT NULL,
  `times` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_service_suite_item_01` (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb4 COMMENT='套卡明细';

-- ----------------------------
-- Records of service_suite_item
-- ----------------------------
BEGIN;
INSERT INTO `service_suite_item` VALUES (7, 2, 1, 1, '2019-03-07 02:37:28', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (8, 2, 4, 1, '2019-03-07 02:37:28', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (9, 2, 5, 1, '2019-03-07 02:37:28', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (19, 5, 1, 10, '2019-03-23 02:25:49', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (20, 5, 5, 10, '2019-03-23 02:25:49', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (21, 5, 8, 10, '2019-03-23 02:25:49', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (22, 6, 1, 10, '2019-03-23 02:25:50', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (23, 6, 5, 10, '2019-03-23 02:25:51', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (24, 6, 8, 10, '2019-03-23 02:25:51', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (25, 7, 1, 10, '2019-03-23 02:25:51', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (26, 7, 5, 10, '2019-03-23 02:25:51', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (27, 7, 8, 10, '2019-03-23 02:25:51', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (28, 8, 1, 10, '2019-03-23 02:25:52', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (29, 8, 5, 10, '2019-03-23 02:25:52', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (30, 8, 8, 10, '2019-03-23 02:25:52', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (31, 9, 1, 10, '2019-03-23 02:25:52', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (32, 9, 5, 10, '2019-03-23 02:25:52', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (33, 9, 8, 10, '2019-03-23 02:25:52', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (34, 10, 1, 10, '2019-03-23 02:25:53', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (35, 10, 5, 10, '2019-03-23 02:25:53', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (36, 10, 8, 10, '2019-03-23 02:25:53', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (37, 11, 1, 10, '2019-03-23 02:25:53', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (38, 11, 5, 10, '2019-03-23 02:25:53', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (39, 11, 8, 10, '2019-03-23 02:25:53', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (40, 12, 1, 10, '2019-03-23 02:25:53', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (41, 12, 5, 10, '2019-03-23 02:25:53', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (42, 12, 8, 10, '2019-03-23 02:25:53', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (43, 13, 1, 10, '2019-03-23 02:25:53', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (44, 13, 5, 10, '2019-03-23 02:25:53', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (45, 13, 8, 10, '2019-03-23 02:25:53', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (46, 14, 1, 10, '2019-03-23 02:25:55', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (47, 14, 5, 10, '2019-03-23 02:25:55', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (48, 14, 8, 10, '2019-03-23 02:25:55', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (49, 15, 1, 10, '2019-03-23 02:25:55', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (50, 15, 5, 10, '2019-03-23 02:25:55', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (51, 15, 8, 10, '2019-03-23 02:25:55', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (52, 16, 1, 10, '2019-03-23 02:25:55', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (53, 16, 5, 10, '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (54, 16, 8, 10, '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (55, 17, 1, 10, '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (56, 17, 5, 10, '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (57, 17, 8, 10, '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (58, 18, 1, 10, '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (59, 18, 5, 10, '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (60, 18, 8, 10, '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (61, 19, 1, 10, '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (62, 19, 5, 10, '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (63, 19, 8, 10, '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (64, 20, 1, 10, '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (65, 20, 5, 10, '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (66, 20, 8, 10, '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (67, 21, 1, 10, '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (68, 21, 5, 10, '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (69, 21, 8, 10, '2019-03-23 02:25:56', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (70, 22, 1, 10, '2019-03-23 02:25:57', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (71, 22, 5, 10, '2019-03-23 02:25:57', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (72, 22, 8, 10, '2019-03-23 02:25:57', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (73, 23, 1, 10, '2019-03-23 02:25:57', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (74, 23, 5, 10, '2019-03-23 02:25:57', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (75, 23, 8, 10, '2019-03-23 02:25:57', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (76, 24, 1, 10, '2019-03-23 02:25:57', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (77, 24, 5, 10, '2019-03-23 02:25:57', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (78, 24, 8, 10, '2019-03-23 02:25:57', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (79, 25, 1, 10, '2019-03-23 02:25:57', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (80, 25, 5, 10, '2019-03-23 02:25:57', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (81, 25, 8, 10, '2019-03-23 02:25:57', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (82, 26, 1, 10, '2019-03-23 02:25:57', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (83, 26, 5, 10, '2019-03-23 02:25:57', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (84, 26, 8, 10, '2019-03-23 02:25:57', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (85, 27, 1, 10, '2019-03-23 02:26:03', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (86, 27, 5, 10, '2019-03-23 02:26:03', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (87, 27, 8, 10, '2019-03-23 02:26:03', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (92, 30, 1, 10, '2019-03-23 02:31:34', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (93, 31, 1, 10, '2019-03-23 02:31:34', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (95, 30, 5, 10, '2019-03-23 02:31:34', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (96, 31, 5, 10, '2019-03-23 02:31:34', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (98, 31, 8, 10, '2019-03-23 02:31:34', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (99, 30, 8, 10, '2019-03-23 02:31:34', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (100, 32, 1, 10, '2019-03-23 02:31:37', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (101, 32, 5, 10, '2019-03-23 02:31:38', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (102, 32, 8, 10, '2019-03-23 02:31:38', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (103, 33, 1, 10, '2019-03-23 02:33:59', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (104, 33, 5, 10, '2019-03-23 02:33:59', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (105, 33, 8, 10, '2019-03-23 02:33:59', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (106, 35, 6, 11, '2019-03-23 02:38:21', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (107, 34, 6, 11, '2019-03-23 02:38:21', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (108, 36, 6, 11, '2019-03-23 02:38:21', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (109, 35, 7, 11, '2019-03-23 02:38:21', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (110, 34, 7, 11, '2019-03-23 02:38:21', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (111, 36, 7, 11, '2019-03-23 02:38:21', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (112, 37, 6, 11, '2019-03-23 02:39:40', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (113, 37, 7, 11, '2019-03-23 02:39:40', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (114, 38, 5, 2, '2019-03-23 02:42:24', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (115, 39, 1, 10, '2019-03-29 11:29:22', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (116, 39, 2, 20, '2019-03-29 11:29:22', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (117, 40, 1, 10, '2019-03-29 11:30:30', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (118, 40, 2, 20, '2019-03-29 11:30:30', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (119, 41, 1, 10, '2019-03-29 11:43:24', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (120, 41, 2, 20, '2019-03-29 11:43:24', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (121, 42, 1, 10, '2019-03-29 11:59:00', 1, NULL, NULL, 0);
INSERT INTO `service_suite_item` VALUES (122, 42, 2, 10, '2019-03-29 11:59:00', 1, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for shift
-- ----------------------------
DROP TABLE IF EXISTS `shift`;
CREATE TABLE `shift` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_id` bigint(20) NOT NULL,
  `shift_type` tinyint(4) NOT NULL,
  `time_start` varchar(12) DEFAULT NULL,
  `time_end` varchar(12) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_schedule_times_02` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COMMENT='班次';

-- ----------------------------
-- Records of shift
-- ----------------------------
BEGIN;
INSERT INTO `shift` VALUES (10, 1, 0, '17:04 ', ' 21:04', '2019-03-20 01:23:16', 32, NULL, NULL, 0);
INSERT INTO `shift` VALUES (11, 1, 3, '13:03 ', '21:04', '2019-03-20 01:23:16', 32, NULL, NULL, 0);
INSERT INTO `shift` VALUES (12, 1, 2, '06:04 ', ' 10:03', '2019-03-20 01:23:17', 32, NULL, NULL, 0);
INSERT INTO `shift` VALUES (13, 1, 1, '01:03 ', ' 04:06', '2019-03-20 01:23:17', 32, NULL, NULL, 0);
INSERT INTO `shift` VALUES (14, 3, 0, '20:03 ', ' 23:05', '2019-03-20 01:23:53', 32, NULL, NULL, 0);
INSERT INTO `shift` VALUES (15, 3, 3, '14:04 ', ' 15:04', '2019-03-20 01:23:53', 32, NULL, NULL, 0);
INSERT INTO `shift` VALUES (16, 3, 2, '08:04 ', ' 10:04', '2019-03-20 01:23:53', 32, NULL, NULL, 0);
INSERT INTO `shift` VALUES (17, 3, 1, '03:03 ', ' 03:04', '2019-03-20 01:23:53', 32, NULL, NULL, 0);
INSERT INTO `shift` VALUES (24, 19, 0, '9:00', '19:00', '2019-03-27 02:45:44', 1, NULL, NULL, 0);
INSERT INTO `shift` VALUES (25, 19, 1, '9:00', '19:00', '2019-03-27 02:45:44', 1, NULL, NULL, 0);
INSERT INTO `shift` VALUES (26, 19, 2, '9:00', '19:00', '2019-03-27 02:45:44', 1, NULL, NULL, 0);
INSERT INTO `shift` VALUES (27, 19, 3, '9:00', '19:00', '2019-03-27 02:45:44', 1, NULL, NULL, 0);
INSERT INTO `shift` VALUES (50, 6, 1, '12:15', '16:30', '2019-03-27 05:10:09', 32, NULL, NULL, 0);
INSERT INTO `shift` VALUES (51, 21, 0, '9:00', '19:00', '2019-03-27 07:59:40', 1, NULL, NULL, 0);
INSERT INTO `shift` VALUES (52, 21, 1, '9:00', '19:00', '2019-03-27 07:59:40', 1, NULL, NULL, 0);
INSERT INTO `shift` VALUES (53, 21, 2, '9:00', '19:00', '2019-03-27 07:59:40', 1, NULL, NULL, 0);
INSERT INTO `shift` VALUES (54, 21, 3, '9:00', '19:00', '2019-03-27 07:59:40', 1, NULL, NULL, 0);
INSERT INTO `shift` VALUES (55, 22, 0, '9:00', '19:00', '2019-03-27 08:27:06', 1, NULL, NULL, 0);
INSERT INTO `shift` VALUES (56, 22, 1, '9:00', '19:00', '2019-03-27 08:27:06', 1, NULL, NULL, 0);
INSERT INTO `shift` VALUES (57, 22, 2, '9:00', '19:00', '2019-03-27 08:27:06', 1, NULL, NULL, 0);
INSERT INTO `shift` VALUES (58, 22, 3, '9:00', '19:00', '2019-03-27 08:27:06', 1, NULL, NULL, 0);
INSERT INTO `shift` VALUES (59, 23, 0, '9:00', '19:00', '2019-03-27 08:38:19', 1, NULL, NULL, 0);
INSERT INTO `shift` VALUES (60, 23, 1, '9:00', '19:00', '2019-03-27 08:38:19', 1, NULL, NULL, 0);
INSERT INTO `shift` VALUES (61, 23, 2, '9:00', '19:00', '2019-03-27 08:38:19', 1, NULL, NULL, 0);
INSERT INTO `shift` VALUES (62, 23, 3, '9:00', '19:00', '2019-03-27 08:38:19', 1, NULL, NULL, 0);
INSERT INTO `shift` VALUES (63, 2, 0, '08:30 ', ' 17:30', '2019-03-28 03:10:08', 1, '2019-03-29 16:44:25', 1, 0);
INSERT INTO `shift` VALUES (64, 2, 3, '20:45 ', ' 20:50', '2019-03-28 03:10:08', 1, '2019-03-29 16:44:25', 1, 0);
INSERT INTO `shift` VALUES (65, 2, 2, '16:55 ', ' 19:30', '2019-03-28 03:10:08', 1, '2019-03-29 16:44:26', 1, 0);
INSERT INTO `shift` VALUES (66, 2, 1, '08:00', '11:00', '2019-03-28 03:10:08', 1, '2019-03-29 16:44:26', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for stock_transfer_application
-- ----------------------------
DROP TABLE IF EXISTS `stock_transfer_application`;
CREATE TABLE `stock_transfer_application` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_no` char(12) NOT NULL,
  `out_warehouse_id` bigint(20) NOT NULL,
  `remark_application` varchar(500) DEFAULT NULL,
  `remark_audit` varchar(500) DEFAULT NULL,
  `record_status` tinyint(4) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `opt_lock` int(11) NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_stock_transfer_application_01` (`application_no`),
  KEY `idx_stock_transfer_application_02` (`create_date`),
  KEY `idx_stock_transfer_application_03` (`create_by`),
  KEY `idx_stock_transfer_application_04` (`update_by`),
  KEY `idx_stock_transfer_application_05` (`update_date`),
  KEY `idx_stock_transfer_application_06` (`record_status`),
  KEY `idx_stock_transfer_application_07` (`out_warehouse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='调拨申请单';

-- ----------------------------
-- Records of stock_transfer_application
-- ----------------------------
BEGIN;
INSERT INTO `stock_transfer_application` VALUES (1, '1', 1, '1', '1', 1, '2019-03-05 15:06:28', 1, '2019-03-05 15:06:32', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for stock_transfer_application_item
-- ----------------------------
DROP TABLE IF EXISTS `stock_transfer_application_item`;
CREATE TABLE `stock_transfer_application_item` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stock_transfer_application_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `qty` double(10,2) NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_stock_transfer_application_item_01` (`stock_transfer_application_id`),
  KEY `idx_stock_transfer_application_item_02` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='调拨明细';

-- ----------------------------
-- Records of stock_transfer_application_item
-- ----------------------------
BEGIN;
INSERT INTO `stock_transfer_application_item` VALUES (1, 1, 1, 1.00);
COMMIT;

-- ----------------------------
-- Table structure for store_room
-- ----------------------------
DROP TABLE IF EXISTS `store_room`;
CREATE TABLE `store_room` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_id` bigint(20) NOT NULL,
  `room_name` varchar(50) NOT NULL,
  `record_status` tinyint(4) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_store_room_01` (`store_id`),
  KEY `idx_store_room_02` (`room_name`),
  KEY `idx_store_room_03` (`record_status`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='门店房间';

-- ----------------------------
-- Records of store_room
-- ----------------------------
BEGIN;
INSERT INTO `store_room` VALUES (1, 2, '快乐神仙房1', 1, '2019-02-28 01:44:56', 1, '2019-03-25 00:56:19', 32, 0);
INSERT INTO `store_room` VALUES (2, 2, '消除烦恼房', 0, '2019-02-28 01:44:56', 1, NULL, NULL, 0);
INSERT INTO `store_room` VALUES (3, 2, '轻松房', 0, '2019-02-28 01:44:56', 1, NULL, NULL, 0);
INSERT INTO `store_room` VALUES (5, 1, '神仙房', 1, '2019-02-27 17:44:56', 1, NULL, NULL, 0);
INSERT INTO `store_room` VALUES (10, 1, '神仙房5', 1, '2019-03-01 08:45:39', 1, NULL, NULL, 0);
INSERT INTO `store_room` VALUES (12, 1, '神仙房6', 1, '2019-03-02 01:05:10', 1, NULL, NULL, 0);
INSERT INTO `store_room` VALUES (13, 2, '快乐神仙房2', 0, '2019-03-02 01:05:10', 1, NULL, NULL, 0);
INSERT INTO `store_room` VALUES (14, 2, '快乐神仙房13', 0, '2019-03-02 01:05:10', 1, NULL, NULL, 0);
INSERT INTO `store_room` VALUES (15, 2, '快乐神仙房14', 0, '2019-03-02 01:05:10', 1, NULL, NULL, 0);
INSERT INTO `store_room` VALUES (16, 2, '快乐神仙房18', 0, '2019-03-02 01:05:10', 1, NULL, NULL, 0);
INSERT INTO `store_room` VALUES (17, 2, '快乐神仙房15', 0, '2019-03-02 01:05:10', 1, NULL, NULL, 0);
INSERT INTO `store_room` VALUES (18, 2, '快乐神仙房16', 0, '2019-03-02 01:05:10', 1, NULL, NULL, 0);
INSERT INTO `store_room` VALUES (19, 2, '快乐神仙房17', 0, '2019-03-02 01:05:10', 1, NULL, NULL, 0);
INSERT INTO `store_room` VALUES (20, 2, '快乐神仙房17', 1, '2019-03-02 01:05:10', 1, '2019-03-25 00:59:39', 32, 0);
COMMIT;

-- ----------------------------
-- Table structure for stuff
-- ----------------------------
DROP TABLE IF EXISTS `stuff`;
CREATE TABLE `stuff` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_id` bigint(20) NOT NULL,
  `stuff_name` varchar(50) NOT NULL,
  `tel` varchar(50) NOT NULL,
  `gender` tinyint(4) NOT NULL,
  `entry_time` datetime DEFAULT NULL,
  `work_age` double(3,1) DEFAULT NULL,
  `birth_day` datetime DEFAULT NULL,
  `special` varchar(120) DEFAULT NULL,
  `dream` varchar(120) DEFAULT NULL,
  `weixin` varchar(50) DEFAULT NULL,
  `qq` varchar(50) DEFAULT NULL,
  `address` varchar(120) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_stuff_01` (`store_id`),
  KEY `idx_stuff_02` (`stuff_name`),
  KEY `idx_stuff_03` (`tel`),
  KEY `idx_stuff_04` (`gender`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COMMENT='院长/店长/员工';

-- ----------------------------
-- Records of stuff
-- ----------------------------
BEGIN;
INSERT INTO `stuff` VALUES (1, 2, '吴浙', '189888888', 0, '2019-02-17 16:00:00', 1.0, '2005-03-20 00:00:00', '篮球', '', 'qq', 'null', '莞樟路', '2019-02-17 16:00:00', 2, '2019-03-21 08:15:22', 1, 1);
INSERT INTO `stuff` VALUES (2, 1, '小李', '13988888888', 1, '2019-02-27 09:01:22', 1.0, '2022-03-15 00:00:00', '', NULL, '', NULL, '', '2019-02-27 09:01:22', 2, '2019-03-21 09:09:31', 32, 1);
INSERT INTO `stuff` VALUES (3, 1, '小王子', '189888888', 0, '2019-02-17 16:00:00', 1.0, NULL, '篮球', NULL, NULL, NULL, NULL, '2019-02-17 16:00:00', 2, '2019-02-17 16:00:00', 1, 1);
INSERT INTO `stuff` VALUES (4, 1, '王子', '189888888', 0, '2019-02-17 16:00:00', 1.0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-02-17 16:00:00', 2, '2019-02-17 16:00:00', 1, 1);
INSERT INTO `stuff` VALUES (5, 2, 'www', '189888888', 0, '2019-02-17 16:00:00', 1.0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-02-17 16:00:00', 2, '2019-02-17 16:00:00', 1, 1);
INSERT INTO `stuff` VALUES (6, 2, 'zzz', '189888888', 0, '2019-02-17 16:00:00', 1.0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-02-17 16:00:00', 2, '2019-02-17 16:00:00', 1, 1);
INSERT INTO `stuff` VALUES (7, 2, 'aaa', '189888888', 0, '2019-02-17 16:00:00', 1.0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-02-17 16:00:00', 2, '2019-02-17 16:00:00', 1, 1);
INSERT INTO `stuff` VALUES (8, 2, 'bbb', '189888888', 0, '2019-02-17 16:00:00', 1.0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-02-17 16:00:00', 2, '2019-02-17 16:00:00', 1, 1);
INSERT INTO `stuff` VALUES (9, 2, '笔笔', '189888888', 0, '2019-02-17 16:00:00', 1.0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-02-17 16:00:00', 2, '2019-02-17 16:00:00', 1, 1);
INSERT INTO `stuff` VALUES (10, 2, 'ccc', '189888888', 0, '2019-02-17 16:00:00', 1.0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-02-17 16:00:00', 2, '2019-02-17 16:00:00', 1, 1);
INSERT INTO `stuff` VALUES (11, 2, '串串', '189888888', 0, '2019-02-17 16:00:00', 1.0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-02-17 16:00:00', 2, '2019-02-17 16:00:00', 1, 1);
INSERT INTO `stuff` VALUES (15, 2, '小二', '13916888888', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-18 06:05:46', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (16, 2, '小二', '13916888888', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-18 06:10:10', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (17, 2, '小二', '13916888888', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-18 06:16:13', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (18, 15, '16455545444', '16455545444', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-22 09:21:16', 32, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (19, 16, '13929433191', '13929433191', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-22 09:22:56', 32, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (20, 17, '18664151832', '18664151832', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-26 09:00:51', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (21, 18, '1688888888', '1688888888', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-26 09:10:16', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (22, 19, '13929433196', '13929433196', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-27 02:45:44', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (23, 15, '丽丽', '13929466193', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-27 02:56:26', 32, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (24, 20, '1378888888', '1378888888', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-27 03:20:49', 32, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (25, 21, '13929433197', '13929433197', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-27 07:59:40', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (26, 22, '13929477194', '13929477194', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-27 08:27:06', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (27, 23, '156699445666', '156699445666', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-27 08:38:18', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (28, 2, '国家级', '5566662558', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-28 03:51:33', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (29, 2, '和姐姐j', '1896542336', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-28 03:52:07', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (30, 2, '和姐姐j', '1896542336', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-28 03:52:21', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (31, 2, '和姐姐j', '1896542336', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-28 03:53:04', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (32, 2, '和姐姐j', '1896542336', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-28 03:54:02', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (33, 2, '和姐姐j', '1896542336', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-28 03:54:02', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (34, 2, '和姐姐j', '1896542336', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-28 03:55:17', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (35, 2, '和姐姐j', '1896542336', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-28 03:55:19', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (36, 2, '符合环境', '566666', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-28 03:58:31', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (37, 2, '立即解决', '556622589', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-28 03:58:49', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (38, 2, '立即解决', '556622589', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-28 03:58:49', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (39, 2, '退居第三', '669823366', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-28 06:21:02', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (40, 2, '退居第三', '669823366', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-28 06:21:02', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (41, 2, '退居第三', '669823366', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-28 06:21:02', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (42, 2, '李四', '66365558', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-28 06:21:38', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (43, 2, '李留', '1369582455', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-28 06:43:19', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (44, 2, '刘大姐', '13985412333', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-28 15:12:19', 1, NULL, NULL, 0);
INSERT INTO `stuff` VALUES (45, 2, '刘德和', '456666555', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-28 15:13:07', 1, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for stuff_job
-- ----------------------------
DROP TABLE IF EXISTS `stuff_job`;
CREATE TABLE `stuff_job` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stuff_id` bigint(20) NOT NULL,
  `job_id` bigint(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_stuff_job_01` (`stuff_id`),
  KEY `idx_stuff_job_02` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COMMENT='员工职务';

-- ----------------------------
-- Records of stuff_job
-- ----------------------------
BEGIN;
INSERT INTO `stuff_job` VALUES (1, 1, 2, '2019-03-06 10:43:48', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (2, 2, 4, '2019-03-06 10:43:48', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (3, 13, 7, '2019-03-18 03:35:03', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (4, 14, 7, '2019-03-18 03:50:31', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (5, 15, 7, '2019-03-18 06:05:46', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (6, 16, 7, '2019-03-18 06:10:10', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (7, 17, 7, '2019-03-18 06:16:13', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (8, 18, 2, '2019-03-22 09:21:16', 32, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (9, 19, 2, '2019-03-22 09:22:56', 32, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (10, 20, 2, '2019-03-26 09:00:52', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (11, 21, 2, '2019-03-26 09:10:16', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (12, 22, 2, '2019-03-27 02:45:44', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (13, 23, 2, '2019-03-27 02:56:26', 32, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (14, 23, 5, '2019-03-27 02:56:26', 32, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (15, 24, 2, '2019-03-27 03:20:49', 32, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (16, 25, 2, '2019-03-27 07:59:40', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (17, 26, 2, '2019-03-27 08:27:06', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (18, 27, 2, '2019-03-27 08:38:19', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (19, 37, 2, '2019-03-28 03:58:49', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (20, 38, 2, '2019-03-28 03:58:49', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (21, 41, 2, '2019-03-28 06:21:02', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (22, 40, 2, '2019-03-28 06:21:02', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (23, 39, 2, '2019-03-28 06:21:02', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (24, 41, 5, '2019-03-28 06:21:02', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (25, 40, 5, '2019-03-28 06:21:02', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (26, 39, 5, '2019-03-28 06:21:02', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (27, 41, 7, '2019-03-28 06:21:02', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (28, 40, 7, '2019-03-28 06:21:02', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (29, 39, 7, '2019-03-28 06:21:02', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (30, 42, 2, '2019-03-28 06:21:38', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (31, 42, 5, '2019-03-28 06:21:38', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (32, 43, 2, '2019-03-28 06:43:19', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (33, 43, 5, '2019-03-28 06:43:19', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (34, 43, 7, '2019-03-28 06:43:19', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (35, 44, 2, '2019-03-28 15:12:19', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (36, 44, 5, '2019-03-28 15:12:19', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (37, 44, 7, '2019-03-28 15:12:19', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (38, 45, 2, '2019-03-28 15:13:07', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (39, 45, 5, '2019-03-28 15:13:07', 1, NULL, NULL, 0);
INSERT INTO `stuff_job` VALUES (40, 45, 7, '2019-03-28 15:13:07', 1, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for stuff_score
-- ----------------------------
DROP TABLE IF EXISTS `stuff_score`;
CREATE TABLE `stuff_score` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stuff_id` bigint(20) NOT NULL,
  `existing` bigint(20) NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_stuff_score_01` (`stuff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工的积分表';

-- ----------------------------
-- Table structure for stuff_score_record
-- ----------------------------
DROP TABLE IF EXISTS `stuff_score_record`;
CREATE TABLE `stuff_score_record` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stuff_id` bigint(20) NOT NULL,
  `matter` varchar(500) NOT NULL,
  `get_point` bigint(20) NOT NULL,
  `get_by_id` bigint(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_stuff_score_record_01` (`stuff_id`),
  KEY `idx_stuff_score_record_02` (`get_by_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工的积分产生记录表';

-- ----------------------------
-- Table structure for system_program
-- ----------------------------
DROP TABLE IF EXISTS `system_program`;
CREATE TABLE `system_program` (
  `record_id` varchar(50) NOT NULL,
  `program_code` varchar(50) NOT NULL,
  `program_name` varchar(120) NOT NULL,
  `url` varchar(255) NOT NULL,
  `glyph` varchar(100) DEFAULT NULL,
  `show_order` int(11) NOT NULL,
  `parameters` varchar(255) NOT NULL,
  `parent_id` varchar(50) NOT NULL,
  PRIMARY KEY (`record_id`) USING BTREE,
  KEY `IDX_SYSTEM_PROGRAM_0` (`program_code`) USING BTREE,
  KEY `IDX_SYSTEM_PROGRAM_1` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of system_program
-- ----------------------------
BEGIN;
INSERT INTO `system_program` VALUES ('SYS01', 'SYS01', '系统管理', '', '0xf013', 6, '', 'SYS01');
INSERT INTO `system_program` VALUES ('SYS01_01', 'SYS01_01', '用户管理', 'system/user/user.html', '0xf007', 0, '', 'SYS01');
INSERT INTO `system_program` VALUES ('SYS01_02', 'SYS01_02', '角色管理', 'system/role/role.html', '0xf233', 1, '', 'SYS01');
INSERT INTO `system_program` VALUES ('SYS01_03', 'SYS01_03', '系统参数', 'system/resources/resource_tree.html', '0xf085', 2, '', 'SYS01');
INSERT INTO `system_program` VALUES ('SYS01_04', 'SYS01_04', '产品属性', ' system/salon/salon.html', ' ', 3, ' ', 'SYS01');
INSERT INTO `system_program` VALUES ('SYS01_05', 'SYS01_05', '美容院管理', 'system/salon/salon.html', ' ', 4, ' ', 'SYS01');
INSERT INTO `system_program` VALUES ('SYS02', 'SYS02', '美容院管理', '', ' ', 1, ' ', 'SYS02');
INSERT INTO `system_program` VALUES ('SYS02_01', 'SYS02_01', '门店管理', '', ' ', 0, ' ', 'SYS02');
INSERT INTO `system_program` VALUES ('SYS02_01_01', 'SYS02_01_01', '房间管理', 'system/salon/store_room.html', ' ', 1, ' ', 'SYS02_01');
INSERT INTO `system_program` VALUES ('SYS02_01_03', 'SYS02_01_03', '门店列表', 'system/salon/auditStore.html', ' ', 0, ' ', 'SYS02_01');
INSERT INTO `system_program` VALUES ('SYS02_02', 'SYS02_02', '顾客管理', 'system/member/member.html', ' ', 1, ' ', 'SYS02');
INSERT INTO `system_program` VALUES ('SYS02_02_01', 'SYS02_02_01', '顾客标签', 'system/member/member_tag.html', ' ', 0, ' ', 'SYS02_02');
INSERT INTO `system_program` VALUES ('SYS02_02_02', 'SYS02_02_02', '顾客档案', 'system/member/member_archives.html', ' ', 1, ' ', 'SYS02_02');
INSERT INTO `system_program` VALUES ('SYS02_03', 'SYS02_03', '员工管理', '', ' ', 2, ' ', 'SYS02');
INSERT INTO `system_program` VALUES ('SYS02_03_01', 'SYS02_03_01', '职务管理', 'system/stuff/job.html', ' ', 0, ' ', 'SYS01');
INSERT INTO `system_program` VALUES ('SYS02_03_02', 'SYS02_03_02', '考勤管理', 'system/stuff/stuff_timeSheet.html', ' ', 1, ' ', 'SYS02_03');
INSERT INTO `system_program` VALUES ('SYS02_03_03', 'SYS02_03_03', '补卡申请', ' ', ' ', 2, ' ', 'SYS02_03');
INSERT INTO `system_program` VALUES ('SYS02_04', 'SYS02_04', '排班管理', '', ' ', 3, ' ', 'SYS02');
INSERT INTO `system_program` VALUES ('SYS02_04_01', 'SYS02_04_01', '排班设置', ' system/shift_schedule/settings.html', ' ', 0, ' ', 'SYS02_04');
INSERT INTO `system_program` VALUES ('SYS02_04_02', 'SYS02_04_02', '排班情况', ' system/stuff_schedule/stuff_settings.html', ' ', 1, ' ', 'SYS02_04');
INSERT INTO `system_program` VALUES ('SYS02_05', 'SYS02_05', '充值卡管理', '  system/vip_suite/vip_suite.html', ' ', 4, ' ', 'SYS02');
INSERT INTO `system_program` VALUES ('SYS02_06', 'SYS02_06', '服务项目管理', ' ', ' ', 5, ' ', 'SYS02');
INSERT INTO `system_program` VALUES ('SYS02_06_01', 'SYS02_06_01', '次卡管理', 'system/salon/service.html', ' ', 0, ' ', 'SYS02_06');
INSERT INTO `system_program` VALUES ('SYS02_06_02', 'SYS02_06_02', '套卡管理', 'system/salon/service_suite.html', ' ', 1, ' ', 'SYS02_06');
INSERT INTO `system_program` VALUES ('SYS02_07', 'SYS02_07', '产品管理', ' ', ' ', 6, ' ', 'SYS02');
INSERT INTO `system_program` VALUES ('SYS02_07_01', 'SYS02_07_01', '品牌管理', ' ', ' ', 0, ' ', 'SYS02_07');
INSERT INTO `system_program` VALUES ('SYS02_07_02', 'SYS02_07_02', '系列管理', ' ', ' ', 1, ' ', 'SYS02_07');
INSERT INTO `system_program` VALUES ('SYS02_07_03', 'SYS02_07_03', '产品设置', '  ', ' ', 2, ' ', 'SYS02_07');
INSERT INTO `system_program` VALUES ('SYS02_08', 'SYS02_08', '消息管理', ' ', ' ', 8, ' ', 'SYS02');
INSERT INTO `system_program` VALUES ('SYS02_08_01', 'SYS02_08_01', '店务消息', ' ', ' ', 0, ' ', 'SYS02_08');
INSERT INTO `system_program` VALUES ('SYS02_08_02', 'SYS02_08_02', '店内工作消息', ' ', ' ', 1, ' ', 'SYS02_08');
INSERT INTO `system_program` VALUES ('SYS02_08_03', 'SYS02_08_03', '门店动态消息', ' ', ' ', 2, ' ', 'SYS02_08');
INSERT INTO `system_program` VALUES ('SYS02_08_04', 'SYS02_08_04', '库存消息', ' ', ' ', 3, ' ', 'SYS02_08');
INSERT INTO `system_program` VALUES ('SYS02_08_05', 'SYS02_08_05', '活动消息', ' ', ' ', 4, ' ', 'SYS02_08');
INSERT INTO `system_program` VALUES ('SYS02_08_06', 'SYS02_08_06', '公告消息', ' ', ' ', 5, ' ', 'SYS02_08');
INSERT INTO `system_program` VALUES ('SYS02_09', 'SYS02_09', '仓库管理', ' ', ' ', 7, ' ', 'SYS02');
INSERT INTO `system_program` VALUES ('SYS02_09_01', 'SYS02_09_01', '产品入库', ' ', ' ', 0, ' ', 'SYS02_09');
INSERT INTO `system_program` VALUES ('SYS02_09_02', 'SYS02_09_02', '产品出库', ' ', ' ', 1, ' ', 'SYS02_09');
INSERT INTO `system_program` VALUES ('SYS02_09_03', 'SYS02_09_03', '产品调库', ' ', ' ', 2, ' ', 'SYS02_09');
INSERT INTO `system_program` VALUES ('SYS02_09_04', 'SYS02_09_04', '库存详情', ' ', ' ', 3, ' ', 'SYS02_09');
INSERT INTO `system_program` VALUES ('SYS02_09_05', 'SYS02_09_05', '库存盘点', ' ', ' ', 4, ' ', 'SYS02_09');
INSERT INTO `system_program` VALUES ('SYS02_09_06', 'SYS02_09_06', '仓库设置', ' ', ' ', 5, ' ', 'SYS02_09');
INSERT INTO `system_program` VALUES ('SYS03', 'SYS03', '交易中心', ' ', ' ', 2, ' ', 'SYS03');
INSERT INTO `system_program` VALUES ('SYS03_01', 'SYS03_01', '积分/代金券换购记录', ' ', ' ', 0, ' ', 'SYS03');
INSERT INTO `system_program` VALUES ('SYS03_02', 'SYS03_02', '会员卡充值记录', ' ', ' ', 1, ' ', 'SYS03');
INSERT INTO `system_program` VALUES ('SYS03_03', 'SYS03_03', '划卡记录', ' ', ' ', 2, ' ', 'SYS03');
INSERT INTO `system_program` VALUES ('SYS03_04', 'SYS03_04', '商品销售记录', ' ', ' ', 3, ' ', 'SYS03');
INSERT INTO `system_program` VALUES ('SYS04', 'SYS04', '营销中心', ' ', ' ', 3, ' ', 'SYS04');
INSERT INTO `system_program` VALUES ('SYS04_01', 'SYS04_01', '拓客', ' ', ' ', 0, ' ', 'SYS04');
INSERT INTO `system_program` VALUES ('SYS04_02', 'SYS04_02', '推送新闻', ' ', ' ', 1, ' ', 'SYS04');
INSERT INTO `system_program` VALUES ('SYS04_03', 'SYS04_03', '红包/拼团', ' ', ' ', 2, ' ', 'SYS04');
INSERT INTO `system_program` VALUES ('SYS04_04', 'SYS04_04', '小程序维护', ' ', ' ', 3, ' ', 'SYS04');
INSERT INTO `system_program` VALUES ('SYS05', 'SYS05', '结算中心', ' ', ' ', 4, ' ', 'SYS05');
INSERT INTO `system_program` VALUES ('SYS05_01', 'SYS05_01', '门店收支记录', ' ', ' ', 0, ' ', 'SYS05');
INSERT INTO `system_program` VALUES ('SYS05_02', 'SYS05_02', '门店出货记录', ' ', ' ', 1, ' ', 'SYS05');
INSERT INTO `system_program` VALUES ('SYS05_03', 'SYS05_03', '门店消耗积分/代金券汇总', ' ', ' ', 2, ' ', 'SYS05');
INSERT INTO `system_program` VALUES ('SYS06', 'SYS06', '客服中心', ' ', ' ', 5, ' ', 'SYS06');
INSERT INTO `system_program` VALUES ('SYS06_01', 'SYS06_01', '投诉/建议/咨询', ' ', ' ', 0, ' ', 'SYS06');
INSERT INTO `system_program` VALUES ('SYS06_02', 'SYS06_02', '订单处理进度', ' ', ' ', 1, ' ', 'SYS06');
INSERT INTO `system_program` VALUES ('SYS06_03', 'SYS06_03', '订单客户评价', ' ', ' ', 2, ' ', 'SYS06');
INSERT INTO `system_program` VALUES ('SYS07', 'SYS07', '系统日志', ' ', ' ', 6, ' ', 'SYS07');
INSERT INTO `system_program` VALUES ('SYS07_01', 'SYS07_01', '系统日志列表', 'test', NULL, 1, 'test', 'SYS07');
COMMIT;

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(20) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `IDX_SYSTEM_ROLE_0` (`role_code`),
  KEY `IDX_SYSTEM_ROLE_1` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of system_role
-- ----------------------------
BEGIN;
INSERT INTO `system_role` VALUES (1, 'admin', '系统管理员');
INSERT INTO `system_role` VALUES (2, 'stuff', '员工');
INSERT INTO `system_role` VALUES (3, 'member', '顾客');
INSERT INTO `system_role` VALUES (9, 'test', 'zhgan');
COMMIT;

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(20) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `pwd` varchar(50) NOT NULL,
  `user_status` tinyint(4) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `online` bit(1) NOT NULL DEFAULT b'0',
  `last_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `IDX_SYSTEM_USER_0` (`user_code`),
  KEY `IDX_SYSTEM_USER_1` (`user_name`),
  KEY `IDX_SYSTEM_USER_2` (`user_status`),
  KEY `IDX_SYSTEM_USER_3` (`online`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of system_user
-- ----------------------------
BEGIN;
INSERT INTO `system_user` VALUES (1, 'C00001', '刘永红', 'e10adc3949ba59abbe56e057f20f883e', 0, '823259113@qq.com', b'1', '2019-03-30 10:54:15');
INSERT INTO `system_user` VALUES (2, 'C00002', '吴鸿建54321', 'e10adc3949ba59abbe56e057f20f883e', 0, '823259113@qq.com', b'0', '2019-03-30 10:40:19');
INSERT INTO `system_user` VALUES (4, '321', '321', 'e10adc3949ba59abbe56e057f20f883e', 0, '2548485782@qq.com', b'1', '2019-03-21 08:06:20');
INSERT INTO `system_user` VALUES (14, '115', '15', '15', 0, '15', b'0', '2019-03-12 14:49:58');
INSERT INTO `system_user` VALUES (15, '16', '7', '7', 0, '7', b'0', '2019-03-12 14:50:07');
INSERT INTO `system_user` VALUES (16, '17', '6', '6', 1, '6', b'0', '2019-03-12 14:50:18');
INSERT INTO `system_user` VALUES (17, '889', '89', '89', 89, '89', b'0', '2019-03-12 14:50:28');
INSERT INTO `system_user` VALUES (18, '99', '99', '9', 9, '9', b'0', '2019-03-12 14:50:35');
INSERT INTO `system_user` VALUES (19, '89', '78', '78', 78, '78', b'0', '2019-03-12 14:50:47');
INSERT INTO `system_user` VALUES (22, '655', '5', '5', 0, '5', b'0', '2019-03-12 14:51:07');
INSERT INTO `system_user` VALUES (23, '2', '2', '12', 0, '12', b'0', '2019-03-12 14:53:33');
INSERT INTO `system_user` VALUES (24, '121', '12', '12', 0, '12', b'0', '2019-03-12 14:53:46');
INSERT INTO `system_user` VALUES (25, '323', '232', '2', 23, '2', b'0', '2019-03-12 14:53:53');
INSERT INTO `system_user` VALUES (26, '12323', '32', '322', 23, '23', b'0', '2019-03-12 14:54:00');
INSERT INTO `system_user` VALUES (27, '232', '2', '2', 2, '2', b'0', '2019-03-12 14:54:07');
INSERT INTO `system_user` VALUES (28, '1231', '23123', '2', 123, '123', b'0', '2019-03-12 14:54:14');
INSERT INTO `system_user` VALUES (29, '23123', '123', '23', 32, '2', b'0', '2019-03-12 14:54:21');
INSERT INTO `system_user` VALUES (30, '222', '2', '22', 2, '2', b'0', '2019-03-12 14:54:34');
INSERT INTO `system_user` VALUES (31, '121', '23', '12312', 123, '23', b'0', '2019-03-12 14:54:49');
INSERT INTO `system_user` VALUES (32, 'c00003', '湛丰源', 'e10adc3949ba59abbe56e057f20f883e', 0, '2548485782@qq.com', b'1', '2019-03-30 10:59:03');
INSERT INTO `system_user` VALUES (33, 'c00004', '湛丰源', 'd41d8cd98f00b204e9800998ecf8427e', 0, '2548485782@qq.com', b'0', NULL);
INSERT INTO `system_user` VALUES (34, 'c00005', '湛丰源', 'd41d8cd98f00b204e9800998ecf8427e', 0, '2548485782@qq.com', b'0', NULL);
INSERT INTO `system_user` VALUES (38, '13916888888', '小二', 'e10adc3949ba59abbe56e057f20f883e', 0, NULL, b'0', NULL);
INSERT INTO `system_user` VALUES (39, '16455545444', '16455545444', 'e10adc3949ba59abbe56e057f20f883e', 0, NULL, b'0', NULL);
INSERT INTO `system_user` VALUES (40, '13929433191', '13929433191', 'e10adc3949ba59abbe56e057f20f883e', 0, NULL, b'0', NULL);
INSERT INTO `system_user` VALUES (41, '18664151832', '18664151832', 'e10adc3949ba59abbe56e057f20f883e', 1, NULL, b'0', NULL);
INSERT INTO `system_user` VALUES (42, '1688888888', '1688888888', 'e10adc3949ba59abbe56e057f20f883e', 1, NULL, b'0', NULL);
INSERT INTO `system_user` VALUES (43, '13929433196', '13929433196', 'e10adc3949ba59abbe56e057f20f883e', 0, NULL, b'0', NULL);
INSERT INTO `system_user` VALUES (44, '13929466193', '丽丽', 'e10adc3949ba59abbe56e057f20f883e', 0, NULL, b'0', NULL);
INSERT INTO `system_user` VALUES (45, '1378888888', '1378888888', 'e10adc3949ba59abbe56e057f20f883e', 1, NULL, b'0', NULL);
INSERT INTO `system_user` VALUES (46, '13929433197', '13929433197', 'e10adc3949ba59abbe56e057f20f883e', 0, NULL, b'0', NULL);
INSERT INTO `system_user` VALUES (47, '13929477194', '13929477194', 'e10adc3949ba59abbe56e057f20f883e', 0, NULL, b'0', NULL);
INSERT INTO `system_user` VALUES (48, '156699445666', '156699445666', 'e10adc3949ba59abbe56e057f20f883e', 0, NULL, b'0', NULL);
INSERT INTO `system_user` VALUES (50, '556622589', '立即解决', 'e10adc3949ba59abbe56e057f20f883e', 0, NULL, b'0', NULL);
INSERT INTO `system_user` VALUES (51, '556622589', '立即解决', 'e10adc3949ba59abbe56e057f20f883e', 0, NULL, b'0', NULL);
INSERT INTO `system_user` VALUES (52, '669823366', '退居第三', 'e10adc3949ba59abbe56e057f20f883e', 0, NULL, b'0', NULL);
INSERT INTO `system_user` VALUES (53, '669823366', '退居第三', 'e10adc3949ba59abbe56e057f20f883e', 0, NULL, b'0', NULL);
INSERT INTO `system_user` VALUES (54, '669823366', '退居第三', 'e10adc3949ba59abbe56e057f20f883e', 0, NULL, b'0', NULL);
INSERT INTO `system_user` VALUES (55, '66365558', '李四', 'e10adc3949ba59abbe56e057f20f883e', 0, NULL, b'0', NULL);
INSERT INTO `system_user` VALUES (56, '1369582455', '李留', 'e10adc3949ba59abbe56e057f20f883e', 0, NULL, b'0', NULL);
INSERT INTO `system_user` VALUES (57, '13985412333', '刘大姐', 'e10adc3949ba59abbe56e057f20f883e', 0, NULL, b'0', NULL);
INSERT INTO `system_user` VALUES (58, '456666555', '刘德和', 'e10adc3949ba59abbe56e057f20f883e', 0, NULL, b'0', NULL);
INSERT INTO `system_user` VALUES (59, 'admin', 'admin', 'd41d8cd98f00b204e9800998ecf8427e', 0, '', b'0', NULL);
COMMIT;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `record_type` tinyint(4) NOT NULL,
  `tag_name` varchar(50) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  KEY `idx_tag_01` (`record_type`),
  KEY `idx_tag_02` (`tag_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签';

-- ----------------------------
-- Table structure for time_sheet
-- ----------------------------
DROP TABLE IF EXISTS `time_sheet`;
CREATE TABLE `time_sheet` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stuff_id` bigint(20) NOT NULL,
  `day` date NOT NULL,
  `time_start` datetime DEFAULT NULL,
  `time_end` datetime DEFAULT NULL,
  `time_sheet_type` tinyint(4) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_clocking_in_01` (`stuff_id`),
  KEY `idx_clocking_in_02` (`day`),
  KEY `idx_clocking_in_03` (`time_sheet_type`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='考勤表';

-- ----------------------------
-- Records of time_sheet
-- ----------------------------
BEGIN;
INSERT INTO `time_sheet` VALUES (2, 1, '2019-03-01', '2019-03-18 09:00:00', '2019-03-18 18:00:00', 1, '2019-03-18 11:46:32', 1, '2019-03-18 11:46:35', 1, 1);
INSERT INTO `time_sheet` VALUES (3, 1, '2019-03-02', '2019-03-02 14:59:02', NULL, 2, '2019-03-02 15:02:42', 1, '2019-03-02 15:02:47', 1, 1);
INSERT INTO `time_sheet` VALUES (4, 1, '2019-03-06', '2019-03-06 15:09:43', '2019-03-06 15:08:15', 0, '2019-03-06 15:07:52', 1, '2019-03-06 15:08:00', 1, 1);
INSERT INTO `time_sheet` VALUES (5, 1, '2019-03-15', NULL, '2019-03-15 15:10:16', 1, '2019-03-27 15:10:22', 1, '2019-03-27 15:10:26', 1, 1);
INSERT INTO `time_sheet` VALUES (7, 1, '2019-03-28', NULL, '2019-03-28 12:44:18', 2, '2019-03-28 12:44:24', 1, NULL, NULL, 0);
INSERT INTO `time_sheet` VALUES (9, 1, '2019-03-29', '2019-03-29 16:47:18', '2019-03-29 17:02:22', 2, '2019-03-29 16:47:19', 1, '2019-03-29 17:02:51', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for verification_code_temporary
-- ----------------------------
DROP TABLE IF EXISTS `verification_code_temporary`;
CREATE TABLE `verification_code_temporary` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone_no` varchar(11) NOT NULL,
  `verification_code` varchar(10) NOT NULL,
  `valid_time` tinyint(4) NOT NULL,
  `effectiveness` tinyint(4) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_verification_code_temporary_01` (`phone_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短信验证码临时表';

-- ----------------------------
-- Table structure for vip_suite
-- ----------------------------
DROP TABLE IF EXISTS `vip_suite`;
CREATE TABLE `vip_suite` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_id` bigint(20) NOT NULL,
  `suite_name` varchar(50) NOT NULL,
  `price` double(8,2) NOT NULL,
  `vip_suite_status` tinyint(4) NOT NULL,
  `description` varchar(500) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_vip_suite_01` (`suite_name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='充值卡';

-- ----------------------------
-- Records of vip_suite
-- ----------------------------
BEGIN;
INSERT INTO `vip_suite` VALUES (1, 2, '一号充值卡', 100.00, 0, '100块充值卡', '2019-03-07 08:59:54', 1, NULL, NULL, 0);
INSERT INTO `vip_suite` VALUES (3, 2, '一号充值卡', 100.00, 0, '100块充值卡', '2019-03-07 09:06:21', 1, NULL, NULL, 0);
INSERT INTO `vip_suite` VALUES (4, 2, '二号充值卡', 1000.00, 0, '1000块充值卡', '2019-03-07 09:06:36', 1, NULL, NULL, 0);
INSERT INTO `vip_suite` VALUES (5, 1, '一号店充值卡', 999.00, 0, '999快充值卡', '2019-03-22 13:00:21', 1, NULL, NULL, 0);
INSERT INTO `vip_suite` VALUES (6, 2, '测试', 1555.00, 0, '人格的乳房', '2019-03-25 07:44:24', 1, NULL, NULL, 0);
INSERT INTO `vip_suite` VALUES (7, 2, '测试34532', 1555.00, 0, '史蒂夫史蒂夫', '2019-03-25 08:19:14', 1, NULL, NULL, 0);
INSERT INTO `vip_suite` VALUES (8, 2, '测试8888', 1555.00, 0, '士大夫似的', '2019-03-25 08:23:09', 1, NULL, NULL, 0);
INSERT INTO `vip_suite` VALUES (9, 2, '测试8888', 1555.00, 0, '士大夫似的', '2019-03-25 08:26:01', 1, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for vip_suite_item
-- ----------------------------
DROP TABLE IF EXISTS `vip_suite_item`;
CREATE TABLE `vip_suite_item` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vip_suite_id` bigint(20) NOT NULL,
  `record_type` tinyint(4) NOT NULL,
  `discount` tinyint(4) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_vip_suite_item_01` (`record_type`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COMMENT='充值卡折扣项目';

-- ----------------------------
-- Records of vip_suite_item
-- ----------------------------
BEGIN;
INSERT INTO `vip_suite_item` VALUES (10, 3, 0, 8, '2019-03-07 09:06:21', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item` VALUES (11, 3, 0, 8, '2019-03-07 09:06:21', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item` VALUES (12, 3, 0, 8, '2019-03-07 09:06:21', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item` VALUES (13, 3, 1, 0, '2019-03-07 09:06:21', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item` VALUES (14, 3, 1, 0, '2019-03-07 09:06:22', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item` VALUES (15, 3, 1, 0, '2019-03-07 09:06:22', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item` VALUES (16, 3, 2, 0, '2019-03-07 09:06:22', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item` VALUES (17, 3, 2, 0, '2019-03-07 09:06:22', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item` VALUES (18, 3, 2, 0, '2019-03-07 09:06:22', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item` VALUES (19, 4, 0, 8, '2019-03-07 09:06:36', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item` VALUES (20, 4, 0, 8, '2019-03-07 09:06:36', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item` VALUES (21, 4, 0, 8, '2019-03-07 09:06:36', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item` VALUES (22, 4, 1, 0, '2019-03-07 09:06:36', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item` VALUES (23, 4, 1, 0, '2019-03-07 09:06:36', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item` VALUES (24, 4, 1, 0, '2019-03-07 09:06:36', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item` VALUES (25, 4, 2, 0, '2019-03-07 09:06:37', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item` VALUES (26, 4, 2, 0, '2019-03-07 09:06:37', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item` VALUES (27, 4, 2, 0, '2019-03-07 09:06:37', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item` VALUES (37, 9, 0, 9, '2019-03-25 08:26:08', 1, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for vip_suite_item_discount_range
-- ----------------------------
DROP TABLE IF EXISTS `vip_suite_item_discount_range`;
CREATE TABLE `vip_suite_item_discount_range` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vip_suite_item_id` bigint(20) NOT NULL,
  `service_id` bigint(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_vip_suite_item_discount_range_01` (`vip_suite_item_id`),
  KEY `idx_vip_suite_item_discount_range_02` (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='充值卡折扣项目适用范围表';

-- ----------------------------
-- Records of vip_suite_item_discount_range
-- ----------------------------
BEGIN;
INSERT INTO `vip_suite_item_discount_range` VALUES (1, 37, 2, '2019-03-25 08:26:25', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item_discount_range` VALUES (2, 37, 4, '2019-03-25 08:26:32', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item_discount_range` VALUES (3, 37, 8, '2019-03-25 08:26:38', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item_discount_range` VALUES (4, 37, 13, '2019-03-25 08:26:38', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item_discount_range` VALUES (5, 37, 14, '2019-03-25 08:26:38', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item_discount_range` VALUES (6, 37, 15, '2019-03-25 08:26:38', 1, NULL, NULL, 0);
INSERT INTO `vip_suite_item_discount_range` VALUES (7, 37, 16, '2019-03-25 08:26:38', 1, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for visual_range
-- ----------------------------
DROP TABLE IF EXISTS `visual_range`;
CREATE TABLE `visual_range` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stuff_id` bigint(20) NOT NULL,
  `statu` int(11) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_visual_range_01` (`stuff_id`),
  KEY `idx_visual_range_02` (`statu`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='可视范围表';

-- ----------------------------
-- Records of visual_range
-- ----------------------------
BEGIN;
INSERT INTO `visual_range` VALUES (1, 1, 0, '2019-03-15 02:05:19', 1, 0, NULL, NULL);
INSERT INTO `visual_range` VALUES (2, 2, 0, '2019-03-15 02:05:19', 1, 0, NULL, NULL);
INSERT INTO `visual_range` VALUES (3, 3, 0, '2019-03-15 02:05:19', 1, 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for visual_range_mapping
-- ----------------------------
DROP TABLE IF EXISTS `visual_range_mapping`;
CREATE TABLE `visual_range_mapping` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `notice_id` bigint(20) NOT NULL,
  `visual_range_id` bigint(20) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) DEFAULT NULL,
  `update_date` varchar(45) DEFAULT NULL,
  `update_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_visual_range_mapping_01` (`notice_id`),
  KEY `idx_visual_range_mapping_02` (`visual_range_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='可视范围映射表';

-- ----------------------------
-- Records of visual_range_mapping
-- ----------------------------
BEGIN;
INSERT INTO `visual_range_mapping` VALUES (1, 1, 1, '2019-03-15 02:05:19', 1, 0, NULL, NULL);
INSERT INTO `visual_range_mapping` VALUES (2, 1, 2, '2019-03-15 02:05:19', 1, 0, NULL, NULL);
INSERT INTO `visual_range_mapping` VALUES (3, 1, 3, '2019-03-15 02:05:19', 1, 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for work_summary
-- ----------------------------
DROP TABLE IF EXISTS `work_summary`;
CREATE TABLE `work_summary` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stuff_id` bigint(20) NOT NULL,
  `summary` varchar(1000) NOT NULL,
  `plan` varchar(1000) NOT NULL,
  `cur_month` date NOT NULL,
  `summary_type` tinyint(4) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `opt_lock` int(11) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `idx_work_summary_01` (`stuff_id`),
  KEY `idx_work_summary_02` (`cur_month`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='工作总结表';

-- ----------------------------
-- Records of work_summary
-- ----------------------------
BEGIN;
INSERT INTO `work_summary` VALUES (1, 1, '1', '1', '2019-03-13', 1, '2019-03-13 11:30:10', 1, '2019-03-13 11:30:13', 1, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
