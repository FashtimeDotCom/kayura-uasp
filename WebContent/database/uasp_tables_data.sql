/*
MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50615
Source Host           : localhost:3306
Source Database       : kayura_db

Target Server Type    : MYSQL
Target Server Version : 50615
File Encoding         : 65001

Date: 2015-12-21 15:54:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `uasp_autologins`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_autologins`;
CREATE TABLE `uasp_autologins` (
`Series_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`User_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Token`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`LastUsed`  datetime NOT NULL ,
PRIMARY KEY (`Series_Id`),
FOREIGN KEY (`User_Id`) REFERENCES `uasp_users` (`User_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
INDEX `FK_UASP_AutoLogin_Ref_User` (`User_Id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_autologins
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_companies`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_companies`;
CREATE TABLE `uasp_companies` (
`Company_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Parent_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`RowType`  smallint(6) NOT NULL COMMENT '0 分类；1 公司；' ,
`Code`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`ShortName`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Name`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Type`  varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '词典' ,
`Address`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Postcode`  varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Telephone`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Fax`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`LinkMan`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Grade`  varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '词典' ,
`Certificate`  varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '词典' ,
`ShopCard`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`VocaWork`  varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '词典' ,
`EstaDate`  datetime NULL DEFAULT NULL ,
`Serial`  int(11) NOT NULL ,
`Description`  varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UpdatedTime`  datetime NOT NULL ,
PRIMARY KEY (`Company_Id`),
FOREIGN KEY (`Parent_Id`) REFERENCES `uasp_companies` (`Company_Id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_UASP_Parent_Ref_Company` (`Parent_Id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_companies
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_departments`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_departments`;
CREATE TABLE `uasp_departments` (
`Department_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Parent_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Company_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`RowType`  smallint(6) NOT NULL COMMENT '0 分类；1 项；' ,
`Code`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Name`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Description`  varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Serial`  int(11) NULL DEFAULT NULL ,
`Status`  smallint(6) NULL DEFAULT NULL ,
`UpdatedTime`  datetime NOT NULL ,
PRIMARY KEY (`Department_Id`),
FOREIGN KEY (`Parent_Id`) REFERENCES `uasp_departments` (`Department_Id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`Company_Id`) REFERENCES `uasp_companies` (`Company_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
INDEX `FK_UASP_Department_Ref_Company` (`Company_Id`) USING BTREE ,
INDEX `FK_UASP_Parent_Ref_Department` (`Parent_Id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_departments
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_dictdata`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_dictdata`;
CREATE TABLE `uasp_dictdata` (
`Data_Id`  int(11) NOT NULL AUTO_INCREMENT ,
`Parent_Id`  int(11) NULL DEFAULT NULL ,
`Dict_Id`  varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`DataName`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`DataValue`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`IsFixed`  bit(1) NOT NULL ,
PRIMARY KEY (`Data_Id`),
FOREIGN KEY (`Parent_Id`) REFERENCES `uasp_dictdata` (`Data_Id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`Dict_Id`) REFERENCES `usap_dict` (`Dict_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
INDEX `FK_UASP_DictData_Ref_Dict` (`Dict_Id`) USING BTREE ,
INDEX `FK_UASP_Parent_Ref_DictData` (`Parent_Id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of uasp_dictdata
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_employees`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_employees`;
CREATE TABLE `uasp_employees` (
`Employee_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Code`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Name`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Sex`  varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '词典' ,
`BirthDay`  datetime NULL DEFAULT NULL ,
`Phone`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Mobile`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Email`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Status`  smallint(6) NOT NULL ,
`UpdatedTime`  datetime NOT NULL ,
PRIMARY KEY (`Employee_Id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_employees
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_emppositions`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_emppositions`;
CREATE TABLE `uasp_emppositions` (
`EmpPosition_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Department_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Position_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Employee_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`EmpPosition_Id`),
FOREIGN KEY (`Position_Id`) REFERENCES `uasp_positions` (`Position_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
FOREIGN KEY (`Department_Id`) REFERENCES `uasp_departments` (`Department_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
FOREIGN KEY (`Employee_Id`) REFERENCES `uasp_employees` (`Employee_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
INDEX `FK_UASP_EmpPosition_Ref_Department` (`Department_Id`) USING BTREE ,
INDEX `FK_UASP_EmpPosition_Ref_Employee` (`Employee_Id`) USING BTREE ,
INDEX `FK_UASP_EmpPosition_Ref_Position` (`Position_Id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_emppositions
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_filedir`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_filedir`;
CREATE TABLE `uasp_filedir` (
`FileDir_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Parent_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Title`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Description`  varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`FileDir_Id`),
FOREIGN KEY (`Parent_Id`) REFERENCES `uasp_filedir` (`FileDir_Id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_UASP_Parent_Ref_FileDir` (`Parent_Id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_filedir
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_fileinfos`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_fileinfos`;
CREATE TABLE `uasp_fileinfos` (
`File_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`FileSize`  int(11) NOT NULL ,
`FileType`  varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Postfix`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`SaveMode`  smallint(6) NOT NULL COMMENT '0 磁盘;1数据库' ,
`DiskPath`  varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ValueStatus`  smallint(6) NOT NULL COMMENT '0 临时保存,1 确认保存' ,
`Md5`  varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`RefCount`  int(11) NOT NULL ,
`IsReadonly`  bit(1) NOT NULL ,
`IsCompress`  bit(1) NOT NULL ,
`Ratio`  decimal(18,4) NULL DEFAULT NULL ,
`IsEncrypted`  bit(1) NOT NULL ,
PRIMARY KEY (`File_Id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_fileinfos
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_filerelations`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_filerelations`;
CREATE TABLE `uasp_filerelations` (
`FileRelation_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`File_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`FileDir_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Tenant_Id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号' ,
`BizDataId`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`FileName`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UploaderId`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UploaderName`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UploadTime`  datetime NULL DEFAULT NULL ,
`IsReadonly`  bit(1) NULL DEFAULT NULL ,
`FileSerial`  int(11) NULL DEFAULT NULL ,
`BizTypes`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Tags`  varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`FileRelation_Id`),
FOREIGN KEY (`Tenant_Id`) REFERENCES `uasp_tenants` (`Tenant_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
FOREIGN KEY (`FileDir_Id`) REFERENCES `uasp_filedir` (`FileDir_Id`) ON DELETE SET NULL ON UPDATE RESTRICT,
FOREIGN KEY (`File_Id`) REFERENCES `uasp_fileinfos` (`File_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
INDEX `FK_UASP_FileRelation_Ref_FileDir` (`FileDir_Id`) USING BTREE ,
INDEX `FK_UASP_FileRelation_Ref_FileInfo` (`File_Id`) USING BTREE ,
INDEX `FK_UASP_FileRelation_Ref_Tenant` (`Tenant_Id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_filerelations
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_grouproles`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_grouproles`;
CREATE TABLE `uasp_grouproles` (
`Group_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Role_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
FOREIGN KEY (`Role_Id`) REFERENCES `uasp_roles` (`Role_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
FOREIGN KEY (`Group_Id`) REFERENCES `uasp_groups` (`Group_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
INDEX `FK_UASP_GroupRole_Ref_Group` (`Group_Id`) USING BTREE ,
INDEX `FK_UASP_GroupRole_Ref_Role` (`Role_Id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_grouproles
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_groups`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_groups`;
CREATE TABLE `uasp_groups` (
`Group_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Tenant_Id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号' ,
`Name`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Enabled`  bit(1) NOT NULL COMMENT '0 禁用；1 启用；' ,
PRIMARY KEY (`Group_Id`),
FOREIGN KEY (`Tenant_Id`) REFERENCES `uasp_tenants` (`Tenant_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
INDEX `FK_UASP_Group_Ref_Tenant` (`Tenant_Id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_groups
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_groupusers`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_groupusers`;
CREATE TABLE `uasp_groupusers` (
`Group_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`User_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
FOREIGN KEY (`User_Id`) REFERENCES `uasp_users` (`User_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
FOREIGN KEY (`Group_Id`) REFERENCES `uasp_groups` (`Group_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
INDEX `FK_UASP_GroupUser_Ref_Group` (`Group_Id`) USING BTREE ,
INDEX `FK_UASP_GroupUser_Ref_User` (`User_Id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_groupusers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_menuplans`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_menuplans`;
CREATE TABLE `uasp_menuplans` (
`MenuPlan_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Tenant_Id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '此值为NULL时，为公共菜单方案' ,
`Code`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Name`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`MenuPlan_Id`),
FOREIGN KEY (`Tenant_Id`) REFERENCES `uasp_tenants` (`Tenant_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
INDEX `FK_UASP_MenuPlan_Ref_Tenant` (`Tenant_Id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_menuplans
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_menus`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_menus`;
CREATE TABLE `uasp_menus` (
`Menu_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Parent_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`MenuPlan_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Module_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Type`  smallint(6) NOT NULL COMMENT '1 菜单分类；2 菜单项；' ,
`DisplayName`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Description`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Icon`  varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Serial`  int(11) NULL DEFAULT NULL ,
`Enabled`  bit(1) NOT NULL COMMENT '0 禁用；1 启用；' ,
PRIMARY KEY (`Menu_Id`),
FOREIGN KEY (`Module_Id`) REFERENCES `uasp_modules` (`Module_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
FOREIGN KEY (`MenuPlan_Id`) REFERENCES `uasp_menuplans` (`MenuPlan_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
INDEX `FK_UASP_Menu_Ref_MenuPlan` (`MenuPlan_Id`) USING BTREE ,
INDEX `FK_UASP_Menu_Ref_Module` (`Module_Id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_menus
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_modules`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_modules`;
CREATE TABLE `uasp_modules` (
`Module_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Parent_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Type`  smallint(6) NOT NULL COMMENT '1 模块分类；2 模块项；' ,
`Code`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Name`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Description`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Icon`  varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`URI`  varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Enabled`  bit(1) NOT NULL COMMENT '0 禁用；1 可用；' ,
PRIMARY KEY (`Module_Id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_modules
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_positions`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_positions`;
CREATE TABLE `uasp_positions` (
`Position_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Department_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Code`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Name`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Level`  int(11) NULL DEFAULT NULL ,
`Description`  varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Serial`  int(11) NULL DEFAULT NULL ,
`Status`  smallint(6) NULL DEFAULT NULL ,
`UpdatedTime`  datetime NOT NULL ,
PRIMARY KEY (`Position_Id`),
FOREIGN KEY (`Department_Id`) REFERENCES `uasp_departments` (`Department_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
INDEX `FK_UASP_Position_Ref_Department` (`Department_Id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_positions
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_profiles`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_profiles`;
CREATE TABLE `uasp_profiles` (
`Profile_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`User_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Category`  varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`ObjectType`  varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`ValueType`  varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'xml, json' ,
`Content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`Profile_Id`),
FOREIGN KEY (`User_Id`) REFERENCES `uasp_users` (`User_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
INDEX `FK_UASP_Profiles_Ref_User` (`User_Id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_profiles
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_rolemodules`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_rolemodules`;
CREATE TABLE `uasp_rolemodules` (
`Role_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Module_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
FOREIGN KEY (`Module_Id`) REFERENCES `uasp_modules` (`Module_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
FOREIGN KEY (`Role_Id`) REFERENCES `uasp_roles` (`Role_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
INDEX `FK_UASP_RoleModel_Ref_Role` (`Role_Id`) USING BTREE ,
INDEX `FK_UASP_RoleModule_Ref_Module` (`Module_Id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_rolemodules
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_roles`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_roles`;
CREATE TABLE `uasp_roles` (
`Role_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Tenant_Id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号' ,
`Name`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Enabled`  bit(1) NOT NULL COMMENT '0 禁用；1 启用；' ,
PRIMARY KEY (`Role_Id`),
FOREIGN KEY (`Tenant_Id`) REFERENCES `uasp_tenants` (`Tenant_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
INDEX `FK_UASP_Role_Ref_Tenant` (`Tenant_Id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_roles
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_settings`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_settings`;
CREATE TABLE `uasp_settings` (
`Registry_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Tenant_Id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号' ,
`Path`  varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`ItemKey`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`ItemValue`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
PRIMARY KEY (`Registry_Id`),
FOREIGN KEY (`Tenant_Id`) REFERENCES `uasp_tenants` (`Tenant_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
INDEX `FK_UASP_Settings_Ref_Tenant` (`Tenant_Id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_settings
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_tenants`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_tenants`;
CREATE TABLE `uasp_tenants` (
`Tenant_Id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号' ,
`DisplayName`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Company`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Telephone`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ActiveTime`  datetime NOT NULL ,
`ExpireTime`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`Tenant_Id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_tenants
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_useremployee`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_useremployee`;
CREATE TABLE `uasp_useremployee` (
`User_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Employee_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
FOREIGN KEY (`User_Id`) REFERENCES `uasp_users` (`User_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
FOREIGN KEY (`Employee_Id`) REFERENCES `uasp_employees` (`Employee_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
INDEX `FK_UASP_UserEmployee_Ref_Employee` (`Employee_Id`) USING BTREE ,
INDEX `FK_UASP_UserEmployee_Ref_User` (`User_Id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_useremployee
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `uasp_users`
-- ----------------------------
DROP TABLE IF EXISTS `uasp_users`;
CREATE TABLE `uasp_users` (
`User_Id`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Tenant_Id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号' ,
`UserName`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`DisplayName`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Salt`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Password`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Email`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`MobileNo`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Keyword`  varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateTime`  datetime NOT NULL ,
`ExpireTime`  datetime NULL DEFAULT NULL COMMENT 'NULL 表时不过期.' ,
`UserType`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'USER 员工；MANAGER 业务管理员；ADMIN 根管理员；' ,
`IsEnabled`  bit(1) NOT NULL ,
`IsLocked`  bit(1) NOT NULL ,
PRIMARY KEY (`User_Id`),
FOREIGN KEY (`Tenant_Id`) REFERENCES `uasp_tenants` (`Tenant_Id`) ON DELETE CASCADE ON UPDATE RESTRICT,
INDEX `FK_UASP_User_Ref_Tenant` (`Tenant_Id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of uasp_users
-- ----------------------------
BEGIN;
INSERT INTO `uasp_users` VALUES ('BD817FA7-716E-11E5-86C6-D8CB8A43F8DD', null, 'admin', '管理员', 'BD817FA7716E11E586C6D8CB8A43F8DD', '123456', 'admin@kayura.org', '13556090295', 'admin glr', '2015-10-13 13:54:32', null, 'ADMIN', '', '');
COMMIT;

-- ----------------------------
-- Table structure for `usap_dict`
-- ----------------------------
DROP TABLE IF EXISTS `usap_dict`;
CREATE TABLE `usap_dict` (
`Dict_Id`  varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Name`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Description`  varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`Dict_Id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of usap_dict
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Auto increment value for `uasp_dictdata`
-- ----------------------------
ALTER TABLE `uasp_dictdata` AUTO_INCREMENT=1;
