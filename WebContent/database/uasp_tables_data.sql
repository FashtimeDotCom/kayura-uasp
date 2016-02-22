drop table if exists UASP_AliveApps;

drop table if exists UASP_AutoLogins;

drop table if exists UASP_AutoNumbers;

drop table if exists UASP_Companies;

drop table if exists UASP_Departments;

drop table if exists UASP_DictItems;

drop table if exists UASP_EmpPositions;

drop table if exists UASP_Employees;

drop table if exists UASP_FileInfos;

drop table if exists UASP_FileRelations;

drop table if exists UASP_GroupRoles;

drop table if exists UASP_GroupUsers;

drop table if exists UASP_Groups;

drop table if exists UASP_MenuItems;

drop table if exists UASP_MenuSchemes;

drop table if exists UASP_Modules;

drop table if exists UASP_NumberCount;

drop table if exists UASP_Positions;

drop table if exists UASP_Profiles;

drop table if exists UASP_RecycleNumbers;

drop table if exists UASP_RoleModules;

drop table if exists UASP_Roles;

drop table if exists UASP_Settings;

drop table if exists UASP_SubApps;

drop table if exists UASP_TableMeta;

drop table if exists UASP_Tenants;

drop table if exists UASP_UserEmployee;

drop table if exists UASP_UserRoles;

drop table if exists UASP_Users;

drop table if exists USAP_DictDefine;

/*==============================================================*/
/* Table: UASP_AliveApps                                        */
/*==============================================================*/
create table UASP_AliveApps
(
   Tenant_Id            varchar(32) not null comment '编号',
   SubApp_Id            char(32) not null,
   ActiveTime           datetime not null,
   ExpireTime           datetime,
   Enabled              smallint not null comment '0 启用；1 禁用；',
   primary key (Tenant_Id, SubApp_Id)
);

/*==============================================================*/
/* Table: UASP_AutoLogins                                       */
/*==============================================================*/
create table UASP_AutoLogins
(
   Series_Id            char(32) not null,
   User_Id              char(32) not null,
   Token                varchar(64) not null,
   LastUsed             datetime not null,
   primary key (Series_Id)
);

/*==============================================================*/
/* Table: UASP_AutoNumbers                                      */
/*==============================================================*/
create table UASP_AutoNumbers
(
   AutoNumber_Id        char(32) not null,
   Tenant_Id            varchar(32) comment '编号',
   Code                 nvarchar(16) not null,
   Description          nvarchar(1024),
   Expression           nvarchar(1024) comment '${类型}-${年}-${月}-${日}-${计数}',
   Handler              nvarchar(256) comment 'java 类型',
   IsFillLack           bit not null,
   Increment            smallint not null,
   UpdatedTime          datetime not null,
   CustomCycle          nvarchar(1024) comment '${类型}-${年}-${月}-${日}',
   CountType            smallint not null comment '按总计数，年计数，季计数，月计数，周计数，天计数',
   primary key (AutoNumber_Id)
);

/*==============================================================*/
/* Table: UASP_Companies                                        */
/*==============================================================*/
create table UASP_Companies
(
   Company_Id           char(32) not null,
   Parent_Id            char(32),
   Tenant_Id            varchar(32) comment '编号',
   RowType              smallint not null comment '0 分类；1 公司；',
   Code                 varchar(32) not null,
   ShortName            varchar(64) not null,
   FullName             varchar(1024),
   Description          varchar(4096),
   IndustryType_Id      varchar(32),
   Address              varchar(1024),
   Postcode             varchar(8),
   Telephone            varchar(128),
   Email                varchar(1024),
   Fax                  varchar(64),
   Linkman              varchar(32),
   EstaDate             datetime,
   Serial               int not null,
   Status               smallint,
   UpdatedTime          datetime not null,
   primary key (Company_Id)
);

/*==============================================================*/
/* Table: UASP_Departments                                      */
/*==============================================================*/
create table UASP_Departments
(
   Department_Id        char(32) not null,
   Parent_Id            char(32),
   Company_Id           char(32) not null,
   RowType              smallint not null comment '0 分类；1 项；',
   Code                 varchar(32) not null,
   Name                 varchar(64) not null,
   Description          varchar(4096),
   Serial               int,
   Status               smallint,
   UpdatedTime          datetime not null,
   primary key (Department_Id)
);

/*==============================================================*/
/* Table: UASP_DictItems                                        */
/*==============================================================*/
create table UASP_DictItems
(
   Item_Id              char(32) not null,
   Parent_Id            char(32),
   Dict_Id              char(32) not null,
   Name                 varchar(32) not null,
   Value                varchar(1024) not null,
   Serial               int not null default 0,
   IsFixed              bit not null,
   primary key (Item_Id)
);

/*==============================================================*/
/* Table: UASP_EmpPositions                                     */
/*==============================================================*/
create table UASP_EmpPositions
(
   EmpPosition_Id       char(32) not null,
   Department_Id        char(32),
   Position_Id          char(32),
   Employee_Id          char(32),
   primary key (EmpPosition_Id)
);

/*==============================================================*/
/* Table: UASP_Employees                                        */
/*==============================================================*/
create table UASP_Employees
(
   Employee_Id          char(32) not null,
   Code                 varchar(32),
   Name                 varchar(64) not null,
   Sex                  varchar(16) comment '词典',
   BirthDay             datetime,
   Phone                varchar(64),
   Mobile               varchar(64),
   Email                varchar(1024),
   Status               smallint not null,
   UpdatedTime          datetime not null,
   primary key (Employee_Id)
);

/*==============================================================*/
/* Table: UASP_FileInfos                                        */
/*==============================================================*/
create table UASP_FileInfos
(
   File_Id              char(32) not null,
   FileSize             int not null,
   ContentType          varchar(512) not null,
   LogicPath            varchar(2048),
   MD5                  varchar(512),
   AllowChange          bit not null,
   IsEncrypted          bit not null,
   Salt                 varchar(32),
   primary key (File_Id)
);

/*==============================================================*/
/* Table: UASP_FileRelations                                    */
/*==============================================================*/
create table UASP_FileRelations
(
   Fr_Id                char(32) not null,
   File_Id              char(32) not null,
   Tenant_Id            varchar(32) comment '编号',
   BizId                varchar(64) not null,
   Category             varchar(1024),
   FileName             varchar(1024) not null,
   Postfix              varchar(32),
   UploaderId           varchar(64),
   UploaderName         varchar(64),
   UploadTime           datetime,
   Serial               int not null,
   Tags                 varchar(4096),
   primary key (Fr_Id)
);

/*==============================================================*/
/* Table: UASP_GroupRoles                                       */
/*==============================================================*/
create table UASP_GroupRoles
(
   Group_Id             char(32) not null,
   Role_Id              char(32) not null,
   primary key (Group_Id, Role_Id)
);

/*==============================================================*/
/* Table: UASP_GroupUsers                                       */
/*==============================================================*/
create table UASP_GroupUsers
(
   Group_Id             char(32) not null,
   User_Id              char(32) not null,
   primary key (Group_Id, User_Id)
);

/*==============================================================*/
/* Table: UASP_Groups                                           */
/*==============================================================*/
create table UASP_Groups
(
   Group_Id             char(32) not null,
   Tenant_Id            varchar(32) comment '编号',
   Name                 varchar(64) not null,
   Enabled              bit not null comment '0 禁用；1 启用；',
   primary key (Group_Id)
);

/*==============================================================*/
/* Table: UASP_MenuItems                                        */
/*==============================================================*/
create table UASP_MenuItems
(
   MenuItem_Id          char(32) not null,
   Parent_Id            char(32),
   MenuPlan_Id          char(32) not null,
   Module_Id            char(32),
   Type                 smallint not null comment '1 菜单分类；2 菜单项；',
   DisplayName          varchar(64),
   Description          varchar(1024),
   Icon                 varchar(2048),
   Serial               int,
   Enabled              bit not null comment '0 禁用；1 启用；',
   primary key (MenuItem_Id)
);

/*==============================================================*/
/* Table: UASP_MenuSchemes                                      */
/*==============================================================*/
create table UASP_MenuSchemes
(
   MenuScheme_Id        char(32) not null,
   Tenant_Id            varchar(32) comment '此值为NULL时，为公共菜单方案',
   Code                 varchar(32) not null,
   Name                 varchar(64) not null,
   primary key (MenuScheme_Id)
);

/*==============================================================*/
/* Table: UASP_Modules                                          */
/*==============================================================*/
create table UASP_Modules
(
   Module_Id            char(32) not null,
   SubApp_Id            char(32),
   Parent_Id            char(32),
   Type                 smallint not null comment '1 模块分类；2 模块项；',
   Code                 varchar(32) not null,
   Name                 varchar(64) not null,
   Description          varchar(1024),
   Icon                 varchar(2048),
   URI                  varchar(2048),
   Serial               int not null,
   Actions              varchar(4096),
   Enabled              bit not null comment '0 禁用；1 可用；',
   primary key (Module_Id)
);

/*==============================================================*/
/* Table: UASP_NumberCount                                      */
/*==============================================================*/
create table UASP_NumberCount
(
   NumberCount_Id       char(32) not null,
   AutoNumber_Id        char(32),
   CountCycle           nvarchar(1024),
   Count                int,
   Remark               nvarchar(1024),
   primary key (NumberCount_Id)
);

/*==============================================================*/
/* Table: UASP_Positions                                        */
/*==============================================================*/
create table UASP_Positions
(
   Position_Id          char(32) not null,
   Department_Id        char(32),
   Code                 varchar(32),
   Name                 varchar(64),
   Level                int,
   Description          varchar(4096),
   Serial               int,
   Status               smallint,
   UpdatedTime          datetime not null,
   primary key (Position_Id)
);

/*==============================================================*/
/* Table: UASP_Profiles                                         */
/*==============================================================*/
create table UASP_Profiles
(
   Profile_Id           char(32) not null,
   User_Id              char(32) not null,
   Category             varchar(2048) not null,
   ObjectType           varchar(2048) not null,
   ValueType            varchar(2048) not null comment 'xml, json',
   Content              text not null,
   primary key (Profile_Id)
);

/*==============================================================*/
/* Table: UASP_RecycleNumbers                                   */
/*==============================================================*/
create table UASP_RecycleNumbers
(
   RecycleNumber_Id     char(32) not null,
   NumberCount_Id       char(32) not null,
   RecycleNo            varchar(1024) not null,
   primary key (RecycleNumber_Id)
);

/*==============================================================*/
/* Table: UASP_RoleModules                                      */
/*==============================================================*/
create table UASP_RoleModules
(
   Role_Id              char(32) not null,
   Module_Id            char(32) not null,
   Actions              varchar(4096),
   primary key (Role_Id, Module_Id)
);

/*==============================================================*/
/* Table: UASP_Roles                                            */
/*==============================================================*/
create table UASP_Roles
(
   Role_Id              char(32) not null,
   Tenant_Id            varchar(32) comment '编号',
   Name                 varchar(64) not null,
   Enabled              bit not null comment '0 禁用；1 启用；',
   primary key (Role_Id)
);

/*==============================================================*/
/* Table: UASP_Settings                                         */
/*==============================================================*/
create table UASP_Settings
(
   Registry_Id          char(32) not null,
   Tenant_Id            varchar(32) comment '编号',
   Path                 varchar(4096) not null,
   ItemKey              varchar(64) not null,
   ItemValue            text,
   primary key (Registry_Id)
);

/*==============================================================*/
/* Table: UASP_SubApps                                          */
/*==============================================================*/
create table UASP_SubApps
(
   SubApp_Id            char(32) not null,
   Code                 varchar(32) not null,
   Name                 varchar(64) not null,
   Enabled              smallint not null comment '0 禁用；1 启动',
   primary key (SubApp_Id)
);

/*==============================================================*/
/* Table: UASP_TableMeta                                        */
/*==============================================================*/
create table UASP_TableMeta
(
   Meta_Id              int not null auto_increment,
   TableName            varchar(1024) not null,
   TableKey             varchar(64) not null,
   MetaKey              varchar(64) not null,
   MetaValue            text,
   primary key (Meta_Id)
);

/*==============================================================*/
/* Table: UASP_Tenants                                          */
/*==============================================================*/
create table UASP_Tenants
(
   Tenant_Id            varchar(32) not null comment '编号',
   DisplayName          varchar(256) not null,
   Company              varchar(256),
   Telephone            varchar(128),
   ActiveTime           datetime not null,
   ExpireTime           datetime,
   Enabled              smallint not null comment '0 启用；1 禁用；',
   primary key (Tenant_Id)
);

/*==============================================================*/
/* Table: UASP_UserEmployee                                     */
/*==============================================================*/
create table UASP_UserEmployee
(
   User_Id              char(32) not null,
   Employee_Id          char(32) not null,
   primary key (User_Id, Employee_Id)
);

/*==============================================================*/
/* Table: UASP_UserRoles                                        */
/*==============================================================*/
create table UASP_UserRoles
(
   Role_Id              char(32) not null,
   User_Id              char(32) not null,
   primary key (Role_Id, User_Id)
);

/*==============================================================*/
/* Table: UASP_Users                                            */
/*==============================================================*/
create table UASP_Users
(
   User_Id              char(32) not null,
   Tenant_Id            varchar(32) comment '编号',
   UserName             varchar(64) not null,
   DisplayName          varchar(64) not null,
   Salt                 varchar(32),
   Password             varchar(256) not null,
   Email                varchar(256),
   MobileNo             varchar(32),
   Keyword              varchar(2048),
   CreateTime           datetime not null,
   ExpireTime           datetime comment 'NULL 表时不过期.',
   Roles                varchar(128) not null comment 'USER 员工；ADMIN 业务管理员；ROOT 根管理员；',
   IsEnabled            bit not null,
   IsLocked             bit not null,
   primary key (User_Id)
);

/*==============================================================*/
/* Table: USAP_DictDefine                                       */
/*==============================================================*/
create table USAP_DictDefine
(
   Dict_Id              char(32) not null,
   Tenant_Id            varchar(32) comment '编号',
   Code                 varchar(32) not null,
   Name                 varchar(64) not null,
   DataType             smallint not null default 0 comment '0 列表；1 树型；',
   Description          varchar(4096),
   primary key (Dict_Id)
);

alter table UASP_AliveApps add constraint FK_UASP_AliveApp_Ref_SubApp foreign key (SubApp_Id)
      references UASP_SubApps (SubApp_Id) on delete cascade on update restrict;

alter table UASP_AliveApps add constraint FK_UASP_AliveApp_Ref_Tenant foreign key (Tenant_Id)
      references UASP_Tenants (Tenant_Id) on delete cascade on update restrict;

alter table UASP_AutoLogins add constraint FK_UASP_AutoLogin_Ref_User foreign key (User_Id)
      references UASP_Users (User_Id) on delete cascade on update restrict;

alter table UASP_AutoNumbers add constraint FK_UASP_AutoNumber_Ref_Tenant foreign key (Tenant_Id)
      references UASP_Tenants (Tenant_Id) on delete cascade on update restrict;

alter table UASP_Companies add constraint FK_UASP_Company_Parent_Ref_ID foreign key (Parent_Id)
      references UASP_Companies (Company_Id) on delete restrict on update restrict;

alter table UASP_Companies add constraint FK_UASP_Company_Ref_Tenant foreign key (Tenant_Id)
      references UASP_Tenants (Tenant_Id) on delete cascade on update restrict;

alter table UASP_Departments add constraint FK_UASP_Department_Parent_Ref_ID foreign key (Parent_Id)
      references UASP_Departments (Department_Id) on delete restrict on update restrict;

alter table UASP_Departments add constraint FK_UASP_Department_Ref_Company foreign key (Company_Id)
      references UASP_Companies (Company_Id) on delete cascade on update restrict;

alter table UASP_DictItems add constraint FK_UASP_DictItem_Parent_Ref_ID foreign key (Parent_Id)
      references UASP_DictItems (Item_Id) on delete restrict on update restrict;

alter table UASP_DictItems add constraint FK_UASP_DictItem_Ref_DictDefine foreign key (Dict_Id)
      references USAP_DictDefine (Dict_Id) on delete cascade on update restrict;

alter table UASP_EmpPositions add constraint FK_UASP_EmpPosition_Ref_Department foreign key (Department_Id)
      references UASP_Departments (Department_Id) on delete cascade on update restrict;

alter table UASP_EmpPositions add constraint FK_UASP_EmpPosition_Ref_Employee foreign key (Employee_Id)
      references UASP_Employees (Employee_Id) on delete cascade on update restrict;

alter table UASP_EmpPositions add constraint FK_UASP_EmpPosition_Ref_Position foreign key (Position_Id)
      references UASP_Positions (Position_Id) on delete cascade on update restrict;

alter table UASP_FileRelations add constraint FK_UASP_FileRelation_Ref_FileInfo foreign key (File_Id)
      references UASP_FileInfos (File_Id) on delete cascade on update restrict;

alter table UASP_FileRelations add constraint FK_UASP_FileRelation_Ref_Tenant foreign key (Tenant_Id)
      references UASP_Tenants (Tenant_Id) on delete cascade on update restrict;

alter table UASP_GroupRoles add constraint FK_UASP_GroupRole_Ref_Group foreign key (Group_Id)
      references UASP_Groups (Group_Id) on delete cascade on update restrict;

alter table UASP_GroupRoles add constraint FK_UASP_GroupRole_Ref_Role foreign key (Role_Id)
      references UASP_Roles (Role_Id) on delete cascade on update restrict;

alter table UASP_GroupUsers add constraint FK_UASP_GroupUser_Ref_Group foreign key (Group_Id)
      references UASP_Groups (Group_Id) on delete cascade on update restrict;

alter table UASP_GroupUsers add constraint FK_UASP_GroupUser_Ref_User foreign key (User_Id)
      references UASP_Users (User_Id) on delete cascade on update restrict;

alter table UASP_Groups add constraint FK_UASP_Group_Ref_Tenant foreign key (Tenant_Id)
      references UASP_Tenants (Tenant_Id) on delete cascade on update restrict;

alter table UASP_MenuItems add constraint FK_UASP_MenuItem_Parent_Ref_ID foreign key (Parent_Id)
      references UASP_MenuItems (MenuItem_Id) on delete restrict on update restrict;

alter table UASP_MenuItems add constraint FK_UASP_MenuItem_Ref_MenuScheme foreign key (MenuPlan_Id)
      references UASP_MenuSchemes (MenuScheme_Id) on delete cascade on update restrict;

alter table UASP_MenuItems add constraint FK_UASP_MenuItem_Ref_Module foreign key (Module_Id)
      references UASP_Modules (Module_Id) on delete cascade on update restrict;

alter table UASP_MenuSchemes add constraint FK_UASP_MenuScheme_Ref_Tenant foreign key (Tenant_Id)
      references UASP_Tenants (Tenant_Id) on delete cascade on update restrict;

alter table UASP_Modules add constraint FK_UASP_Module_Parent_Ref_ID foreign key (Parent_Id)
      references UASP_Modules (Module_Id) on delete restrict on update restrict;

alter table UASP_Modules add constraint FK_UASP_Module_Ref_SubApp foreign key (SubApp_Id)
      references UASP_SubApps (SubApp_Id) on delete cascade on update restrict;

alter table UASP_NumberCount add constraint FK_UASP_NumberCount_Ref_AutoNumber foreign key (AutoNumber_Id)
      references UASP_AutoNumbers (AutoNumber_Id) on delete cascade on update restrict;

alter table UASP_Positions add constraint FK_UASP_Position_Ref_Department foreign key (Department_Id)
      references UASP_Departments (Department_Id) on delete cascade on update restrict;

alter table UASP_Profiles add constraint FK_UASP_Profiles_Ref_User foreign key (User_Id)
      references UASP_Users (User_Id) on delete cascade on update restrict;

alter table UASP_RecycleNumbers add constraint FK_UASP_RecycleNumber_Ref_NumberCount foreign key (NumberCount_Id)
      references UASP_NumberCount (NumberCount_Id) on delete cascade on update restrict;

alter table UASP_RoleModules add constraint FK_UASP_RoleModel_Ref_Role foreign key (Role_Id)
      references UASP_Roles (Role_Id) on delete cascade on update restrict;

alter table UASP_RoleModules add constraint FK_UASP_RoleModule_Ref_Module foreign key (Module_Id)
      references UASP_Modules (Module_Id) on delete cascade on update restrict;

alter table UASP_Roles add constraint FK_UASP_Role_Ref_Tenant foreign key (Tenant_Id)
      references UASP_Tenants (Tenant_Id) on delete cascade on update restrict;

alter table UASP_Settings add constraint FK_UASP_Settings_Ref_Tenant foreign key (Tenant_Id)
      references UASP_Tenants (Tenant_Id) on delete cascade on update restrict;

alter table UASP_UserEmployee add constraint FK_UASP_UserEmployee_Ref_Employee foreign key (Employee_Id)
      references UASP_Employees (Employee_Id) on delete cascade on update restrict;

alter table UASP_UserEmployee add constraint FK_UASP_UserEmployee_Ref_User foreign key (User_Id)
      references UASP_Users (User_Id) on delete cascade on update restrict;

alter table UASP_UserRoles add constraint FK_UASP_UserRole_Ref_Role foreign key (Role_Id)
      references UASP_Roles (Role_Id) on delete cascade on update restrict;

alter table UASP_UserRoles add constraint FK_UASP_UserRole_Ref_User foreign key (User_Id)
      references UASP_Users (User_Id) on delete cascade on update restrict;

alter table UASP_Users add constraint FK_UASP_User_Ref_Tenant foreign key (Tenant_Id)
      references UASP_Tenants (Tenant_Id) on delete cascade on update restrict;

alter table USAP_DictDefine add constraint FK_UASP_DictDefine_Ref_Tenant foreign key (Tenant_Id)
      references UASP_Tenants (Tenant_Id) on delete cascade on update restrict;


-- ----------------------------
-- Records of uasp_users
-- ----------------------------
INSERT INTO `uasp_users` VALUES ('BD817FA7716E11E586C6D8CB8A43F8DD', null, 'admin', '管理员', 'DI9JGSpp2M8gQ/tDVUQBuQ==', '18529aa9bc0764394e88e1ceb0c153e6ff621d18f377eb977e299bb66dbab5b0', 'admin@kayura.org', '13556090295', 'admin glr', '2015-10-13 13:54:32', null, 'ADMIN,USER', '', '\0');

-- ----------------------------
-- Records of usap_dictdefine
-- ----------------------------
INSERT INTO `usap_dictdefine` VALUES ('2A8DDB30D5EF11E58D5F00163E003262', null, 'Tndustry', '企业类型', '0', null);
INSERT INTO `usap_dictdefine` VALUES ('745F9DDCD94411E5943F10BF48BBBEC9', null, 'Province', '省份', '0', null);
INSERT INTO `usap_dictdefine` VALUES ('DBF4DC05D94411E5943F10BF48BBBEC9', null, 'Country', '国家', '0', null);

-- ----------------------------
-- Records of uasp_dictitems
-- ----------------------------
INSERT INTO `uasp_dictitems` VALUES ('46491CCBD5EF11E58D5F00163E003262', null, '2A8DDB30D5EF11E58D5F00163E003262', '国企', '01', '0', '\0');
INSERT INTO `uasp_dictitems` VALUES ('669CE589D5EF11E58D5F00163E003262', null, '2A8DDB30D5EF11E58D5F00163E003262', '私企', '02', '1', '\0');

INSERT INTO `uasp_dictitems` VALUES ('07C4DD821B0F47558CF950037F614B51', null, '745F9DDCD94411E5943F10BF48BBBEC9', '广东', 'S01', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('1359C28A2BC64E40B40559BF15598BEA', null, '745F9DDCD94411E5943F10BF48BBBEC9', '山西', 'S02', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('1B1D8D3CA53642B2A9783B74940094B6', null, '745F9DDCD94411E5943F10BF48BBBEC9', '安徽', 'S03', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('1C8A9F40E317448081F1C5EA8F9760EF', null, '745F9DDCD94411E5943F10BF48BBBEC9', '广西', 'Q05', '4', '\0');
INSERT INTO `uasp_dictitems` VALUES ('2706E212622F4659BB88449741503C6A', null, '745F9DDCD94411E5943F10BF48BBBEC9', '江苏', 'S05', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('30C5FAF026C6485ABCE26BA52BE8620C', null, '745F9DDCD94411E5943F10BF48BBBEC9', '北京', 'Z01', '2', '\0');
INSERT INTO `uasp_dictitems` VALUES ('313E5CAAB9E44B63A5A3599EF30909C9', null, '745F9DDCD94411E5943F10BF48BBBEC9', '辽宁', 'S06', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('3ADB586CA492445798323C1C7DBF6336', null, '745F9DDCD94411E5943F10BF48BBBEC9', '山东', 'S07', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('4DA69EF51F2E4521AC8263AF87856192', null, '745F9DDCD94411E5943F10BF48BBBEC9', '甘肃', 'S08', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('4F1149D8929A4C079D2D723C60B46ABD', null, '745F9DDCD94411E5943F10BF48BBBEC9', '湖南', 'S09', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('50B41B91264044DDAFF464A4ED84136E', null, '745F9DDCD94411E5943F10BF48BBBEC9', '澳门', 'T02', '3', '\0');
INSERT INTO `uasp_dictitems` VALUES ('5C446A35058449F994989897B3CCF995', null, '745F9DDCD94411E5943F10BF48BBBEC9', '新疆', 'Q01', '4', '\0');
INSERT INTO `uasp_dictitems` VALUES ('6130AD100B934354AC5CDCB5B85F2754', null, '745F9DDCD94411E5943F10BF48BBBEC9', '浙江', 'S10', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('65A1C2AA5F0A41C9878FCB579E10431A', null, '745F9DDCD94411E5943F10BF48BBBEC9', '河北', 'S11', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('67AD03B15F59474AB5E42B72AF2BF334', null, '745F9DDCD94411E5943F10BF48BBBEC9', '青海', 'S11', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('6968AB7C582A4718B2E88D8791AA0CF5', null, '745F9DDCD94411E5943F10BF48BBBEC9', '江西', 'S12', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('6D9390542F0A44AFB2844DEFB5F8FC3C', null, '745F9DDCD94411E5943F10BF48BBBEC9', '四川', 'S13', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('70078BC56C8C40519566CE8516FE3B96', null, '745F9DDCD94411E5943F10BF48BBBEC9', '云南', 'S14', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('7715DFC3CDED44F0B9855DA15D03525C', null, '745F9DDCD94411E5943F10BF48BBBEC9', '西藏', 'Q04', '4', '\0');
INSERT INTO `uasp_dictitems` VALUES ('7D2C1DAD584545DC986798051A1001E8', null, '745F9DDCD94411E5943F10BF48BBBEC9', '宁夏', 'Q02', '4', '\0');
INSERT INTO `uasp_dictitems` VALUES ('7F737034D2C04725967E5332D02014E4', null, '745F9DDCD94411E5943F10BF48BBBEC9', '河南', 'S15', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('80E70E13C11B4550BB5CBAF522179B0C', null, '745F9DDCD94411E5943F10BF48BBBEC9', '海南', 'S16', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('86E5C63DA8294E04A8D38DD12012ADAD', null, '745F9DDCD94411E5943F10BF48BBBEC9', '吉林', 'S17', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('8F872EC2724845948572AA70BA238E53', null, '745F9DDCD94411E5943F10BF48BBBEC9', '福建', 'S18', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('930DCA4C9392470CBA12F9A83B8ED982', null, '745F9DDCD94411E5943F10BF48BBBEC9', '香港', 'T01', '3', '\0');
INSERT INTO `uasp_dictitems` VALUES ('9F966C8E44814F59A0C997536B95483F', null, '745F9DDCD94411E5943F10BF48BBBEC9', '内蒙古', 'Q03', '4', '\0');
INSERT INTO `uasp_dictitems` VALUES ('AF1F9CEEFDAF49ECB5F9FC91CBB89871', null, '745F9DDCD94411E5943F10BF48BBBEC9', '湖北', 'S19', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('CC70B85161B446709901602DD054D876', null, '745F9DDCD94411E5943F10BF48BBBEC9', '陕西', 'S20', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('D061BE3577F5419BB1C5843832567500', null, '745F9DDCD94411E5943F10BF48BBBEC9', '天津', 'Z03', '2', '\0');
INSERT INTO `uasp_dictitems` VALUES ('D42BADAC7F6147F69327D5378DE1CB44', null, '745F9DDCD94411E5943F10BF48BBBEC9', '台湾', 'S21', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('D6AD00DDD5764A229D018F0FBFEA103A', null, '745F9DDCD94411E5943F10BF48BBBEC9', '贵州', 'S22', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('D80E8822ECB349A9A1E0BE11642C78C6', null, '745F9DDCD94411E5943F10BF48BBBEC9', '上海', 'Z02', '2', '\0');
INSERT INTO `uasp_dictitems` VALUES ('E611900B68764077AE94FB462437F281', null, '745F9DDCD94411E5943F10BF48BBBEC9', '黑龙江', 'S23', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('EE7BBE2428D248A3B6F8A5ED294F57AF', null, '745F9DDCD94411E5943F10BF48BBBEC9', '四川', 'S04', '1', '\0');
INSERT INTO `uasp_dictitems` VALUES ('F6FE6CC20E7F40B38455342FE3018A78', null, '745F9DDCD94411E5943F10BF48BBBEC9', '重庆', 'Z04', '2', '\0');

