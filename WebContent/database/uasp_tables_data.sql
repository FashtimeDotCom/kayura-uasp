
drop table if exists UASP_AutoLogins;

drop table if exists UASP_Companies;

drop table if exists UASP_Departments;

drop table if exists UASP_DictData;

drop table if exists UASP_EmpPositions;

drop table if exists UASP_Employees;

drop table if exists UASP_FileDir;

drop table if exists UASP_FileInfos;

drop table if exists UASP_FileRelations;

drop table if exists UASP_GroupRoles;

drop table if exists UASP_GroupUsers;

drop table if exists UASP_Groups;

drop table if exists UASP_MenuPlans;

drop table if exists UASP_Menus;

drop table if exists UASP_Modules;

drop table if exists UASP_Positions;

drop table if exists UASP_Profiles;

drop table if exists UASP_RoleModules;

drop table if exists UASP_Roles;

drop table if exists UASP_Settings;

drop table if exists UASP_Tenants;

drop table if exists UASP_UserEmployee;

drop table if exists UASP_Users;

drop table if exists USAP_Dict;

/*==============================================================*/
/* Table: UASP_AutoLogins                                       */
/*==============================================================*/
create table UASP_AutoLogins
(
   Series_Id            char(36) not null,
   User_Id              char(36) not null,
   Token                varchar(64) not null,
   LastUsed             datetime not null,
   primary key (Series_Id)
);

/*==============================================================*/
/* Table: UASP_Companies                                        */
/*==============================================================*/
create table UASP_Companies
(
   Company_Id           char(36) not null,
   Parent_Id            char(36),
   RowType              smallint not null comment '0 分类；1 公司；',
   Code                 varchar(32) not null,
   ShortName            varchar(64) not null,
   Name                 varchar(1024),
   Type                 varchar(16) comment '词典',
   Address              varchar(1024),
   Postcode             varchar(8),
   Telephone            varchar(64),
   Fax                  varchar(64),
   LinkMan              varchar(32),
   Grade                varchar(16) comment '词典',
   Certificate          varchar(16) comment '词典',
   ShopCard             varchar(64),
   VocaWork             varchar(16) comment '词典',
   EstaDate             datetime,
   Serial               int not null,
   Description          varchar(4096),
   UpdatedTime          datetime not null,
   primary key (Company_Id)
);

/*==============================================================*/
/* Table: UASP_Departments                                      */
/*==============================================================*/
create table UASP_Departments
(
   Department_Id        char(36) not null,
   Parent_Id            char(36),
   Company_Id           char(36) not null,
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
/* Table: UASP_DictData                                         */
/*==============================================================*/
create table UASP_DictData
(
   Data_Id              int not null auto_increment,
   Parent_Id            int,
   Dict_Id              varchar(16),
   DataName             varchar(32) not null,
   DataValue            varchar(1024) not null,
   IsFixed              bit not null,
   primary key (Data_Id)
);

/*==============================================================*/
/* Table: UASP_EmpPositions                                     */
/*==============================================================*/
create table UASP_EmpPositions
(
   EmpPosition_Id       char(36) not null,
   Department_Id        char(36),
   Position_Id          char(36),
   Employee_Id          char(36),
   primary key (EmpPosition_Id)
);

/*==============================================================*/
/* Table: UASP_Employees                                        */
/*==============================================================*/
create table UASP_Employees
(
   Employee_Id          char(36) not null,
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
/* Table: UASP_FileDir                                          */
/*==============================================================*/
create table UASP_FileDir
(
   FileDir_Id           char(36) not null,
   Parent_Id            char(36),
   Title                varchar(1024) not null,
   Description          varchar(4096),
   primary key (FileDir_Id)
);

/*==============================================================*/
/* Table: UASP_FileInfos                                        */
/*==============================================================*/
create table UASP_FileInfos
(
   File_Id              char(36) not null,
   FileSize             int not null,
   FileType             varchar(512) not null,
   Postfix              varchar(32),
   SaveMode             smallint not null comment '0 磁盘;1数据库',
   DiskPath             varchar(2048),
   ValueStatus          smallint not null comment '0 临时保存,1 确认保存',
   Md5                  varchar(512) not null,
   RefCount             int not null,
   IsReadonly           bit not null,
   IsCompress           bit not null,
   Ratio                decimal(18,4),
   IsEncrypted          bit not null,
   primary key (File_Id)
);

/*==============================================================*/
/* Table: UASP_FileRelations                                    */
/*==============================================================*/
create table UASP_FileRelations
(
   FileRelation_Id      char(36) not null,
   File_Id              char(36),
   FileDir_Id           char(36),
   Tenant_Id            varchar(32) comment '编号',
   BizDataId            varchar(64),
   FileName             varchar(1024),
   UploaderId           varchar(64),
   UploaderName         varchar(64),
   UploadTime           datetime,
   IsReadonly           bit,
   FileSerial           int,
   BizTypes             varchar(1024),
   Tags                 varchar(4096),
   primary key (FileRelation_Id)
);

/*==============================================================*/
/* Table: UASP_GroupRoles                                       */
/*==============================================================*/
create table UASP_GroupRoles
(
   Group_Id             char(36),
   Role_Id              char(36)
);

/*==============================================================*/
/* Table: UASP_GroupUsers                                       */
/*==============================================================*/
create table UASP_GroupUsers
(
   Group_Id             char(36),
   User_Id              char(36)
);

/*==============================================================*/
/* Table: UASP_Groups                                           */
/*==============================================================*/
create table UASP_Groups
(
   Group_Id             char(36) not null,
   Tenant_Id            varchar(32) comment '编号',
   Name                 varchar(64) not null,
   Enabled              bit not null comment '0 禁用；1 启用；',
   primary key (Group_Id)
);

/*==============================================================*/
/* Table: UASP_MenuPlans                                        */
/*==============================================================*/
create table UASP_MenuPlans
(
   MenuPlan_Id          char(36) not null,
   Tenant_Id            varchar(32) comment '此值为NULL时，为公共菜单方案',
   Code                 varchar(32) not null,
   Name                 varchar(64) not null,
   primary key (MenuPlan_Id)
);

/*==============================================================*/
/* Table: UASP_Menus                                            */
/*==============================================================*/
create table UASP_Menus
(
   Menu_Id              char(36) not null,
   Parent_Id            char(36),
   MenuPlan_Id          char(36) not null,
   Module_Id            char(36),
   Type                 smallint not null comment '1 菜单分类；2 菜单项；',
   DisplayName          varchar(64),
   Description          varchar(1024),
   Icon                 varchar(2048),
   Serial               int,
   Enabled              bit not null comment '0 禁用；1 启用；',
   primary key (Menu_Id)
);

/*==============================================================*/
/* Table: UASP_Modules                                          */
/*==============================================================*/
create table UASP_Modules
(
   Module_Id            char(36) not null,
   Parent_Id            char(36),
   Type                 smallint not null comment '1 模块分类；2 模块项；',
   Code                 varchar(32) not null,
   Name                 varchar(64) not null,
   Description          varchar(1024),
   Icon                 varchar(2048),
   URI                  varchar(2048),
   Enabled              bit not null comment '0 禁用；1 可用；',
   primary key (Module_Id)
);

/*==============================================================*/
/* Table: UASP_Positions                                        */
/*==============================================================*/
create table UASP_Positions
(
   Position_Id          char(36) not null,
   Department_Id        char(36),
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
   Profile_Id           char(36) not null,
   User_Id              char(36) not null,
   Category             varchar(2048) not null,
   ObjectType           varchar(2048) not null,
   ValueType            varchar(2048) not null comment 'xml, json',
   Content              text not null,
   primary key (Profile_Id)
);

/*==============================================================*/
/* Table: UASP_RoleModules                                      */
/*==============================================================*/
create table UASP_RoleModules
(
   Role_Id              char(36),
   Module_Id            char(36)
);

/*==============================================================*/
/* Table: UASP_Roles                                            */
/*==============================================================*/
create table UASP_Roles
(
   Role_Id              char(36) not null,
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
   Registry_Id          char(36) not null,
   Tenant_Id            varchar(32) comment '编号',
   Path                 varchar(4096) not null,
   ItemKey              varchar(64) not null,
   ItemValue            text,
   primary key (Registry_Id)
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
   primary key (Tenant_Id)
);

/*==============================================================*/
/* Table: UASP_UserEmployee                                     */
/*==============================================================*/
create table UASP_UserEmployee
(
   User_Id              char(36),
   Employee_Id          char(36)
);

/*==============================================================*/
/* Table: UASP_Users                                            */
/*==============================================================*/
create table UASP_Users
(
   User_Id              char(36) not null,
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
   UserType             varchar(64) not null comment 'USER 员工；MANAGER 业务管理员；ADMIN 根管理员；',
   IsEnabled            bit not null,
   IsLocked             bit not null,
   primary key (User_Id)
);

/*==============================================================*/
/* Table: USAP_Dict                                             */
/*==============================================================*/
create table USAP_Dict
(
   Dict_Id              varchar(16) not null,
   Name                 varchar(64) not null,
   Description          varchar(4096),
   primary key (Dict_Id)
);

alter table UASP_AutoLogins add constraint FK_UASP_AutoLogin_Ref_User foreign key (User_Id)
      references UASP_Users (User_Id) on delete cascade on update restrict;

alter table UASP_Companies add constraint FK_UASP_Parent_Ref_Company foreign key (Parent_Id)
      references UASP_Companies (Company_Id) on delete restrict on update restrict;

alter table UASP_Departments add constraint FK_UASP_Department_Ref_Company foreign key (Company_Id)
      references UASP_Companies (Company_Id) on delete cascade on update restrict;

alter table UASP_Departments add constraint FK_UASP_Parent_Ref_Department foreign key (Parent_Id)
      references UASP_Departments (Department_Id) on delete restrict on update restrict;

alter table UASP_DictData add constraint FK_UASP_DictData_Ref_Dict foreign key (Dict_Id)
      references USAP_Dict (Dict_Id) on delete cascade on update restrict;

alter table UASP_DictData add constraint FK_UASP_Parent_Ref_DictData foreign key (Parent_Id)
      references UASP_DictData (Data_Id) on delete restrict on update restrict;

alter table UASP_EmpPositions add constraint FK_UASP_EmpPosition_Ref_Department foreign key (Department_Id)
      references UASP_Departments (Department_Id) on delete cascade on update restrict;

alter table UASP_EmpPositions add constraint FK_UASP_EmpPosition_Ref_Employee foreign key (Employee_Id)
      references UASP_Employees (Employee_Id) on delete cascade on update restrict;

alter table UASP_EmpPositions add constraint FK_UASP_EmpPosition_Ref_Position foreign key (Position_Id)
      references UASP_Positions (Position_Id) on delete cascade on update restrict;

alter table UASP_FileDir add constraint FK_UASP_Parent_Ref_FileDir foreign key (Parent_Id)
      references UASP_FileDir (FileDir_Id) on delete restrict on update restrict;

alter table UASP_FileRelations add constraint FK_UASP_FileRelation_Ref_FileDir foreign key (FileDir_Id)
      references UASP_FileDir (FileDir_Id) on delete set null on update restrict;

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

alter table UASP_MenuPlans add constraint FK_UASP_MenuPlan_Ref_Tenant foreign key (Tenant_Id)
      references UASP_Tenants (Tenant_Id) on delete cascade on update restrict;

alter table UASP_Menus add constraint FK_UASP_Menu_Ref_MenuPlan foreign key (MenuPlan_Id)
      references UASP_MenuPlans (MenuPlan_Id) on delete cascade on update restrict;

alter table UASP_Menus add constraint FK_UASP_Menu_Ref_Module foreign key (Module_Id)
      references UASP_Modules (Module_Id) on delete cascade on update restrict;

alter table UASP_Positions add constraint FK_UASP_Position_Ref_Department foreign key (Department_Id)
      references UASP_Departments (Department_Id) on delete cascade on update restrict;

alter table UASP_Profiles add constraint FK_UASP_Profiles_Ref_User foreign key (User_Id)
      references UASP_Users (User_Id) on delete cascade on update restrict;

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

alter table UASP_Users add constraint FK_UASP_User_Ref_Tenant foreign key (Tenant_Id)
      references UASP_Tenants (Tenant_Id) on delete cascade on update restrict;

-- ----------------------------
-- Records of uasp_users
-- ----------------------------
INSERT INTO `uasp_users` VALUES ('BD817FA7-716E-11E5-86C6-D8CB8A43F8DD', null, 'admin', '管理员', 'DI9JGSpp2M8gQ/tDVUQBuQ==', '18529aa9bc0764394e88e1ceb0c153e6ff621d18f377eb977e299bb66dbab5b0', 'admin@kayura.org', '13556090295', 'admin glr', '2015-10-13 13:54:32', null, 'ADMIN,USER', '', '\0');

