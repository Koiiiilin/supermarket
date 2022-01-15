CREATE DATABASE  IF NOT EXISTS `smbms` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `smbms`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: smbms
-- ------------------------------------------------------
-- Server version	5.7.28-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `shopper`
--

DROP TABLE IF EXISTS `shopper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopper`
--

LOCK TABLES `shopper` WRITE;
/*!40000 ALTER TABLE `shopper` DISABLE KEYS */;
INSERT INTO `shopper` VALUES (2,'zzz','b6c9a5ed2022f08033b9c11fde86ef96','n4TOV3L4');
/*!40000 ALTER TABLE `shopper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `smbms_address`
--

DROP TABLE IF EXISTS `smbms_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `smbms_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `contact` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系人姓名',
  `addressDesc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收货地址明细',
  `postCode` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮编',
  `tel` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系人电话',
  `createdBy` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint(20) DEFAULT NULL COMMENT '修改者',
  `modifyDate` datetime DEFAULT NULL COMMENT '修改时间',
  `userId` bigint(20) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `smbms_address`
--

LOCK TABLES `smbms_address` WRITE;
/*!40000 ALTER TABLE `smbms_address` DISABLE KEYS */;
INSERT INTO `smbms_address` VALUES (1,'王丽','北京市东城区东交民巷44号','100010','13678789999',1,'2016-04-13 00:00:00',NULL,NULL,1),(2,'张红丽','北京市海淀区丹棱街3号','100000','18567672312',1,'2016-04-13 00:00:00',NULL,NULL,1),(3,'任志强','北京市东城区美术馆后街23号','100021','13387906742',1,'2016-04-13 00:00:00',NULL,NULL,1),(4,'曹颖','北京市朝阳区朝阳门南大街14号','100053','13568902323',1,'2016-04-13 00:00:00',NULL,NULL,2),(5,'李慧','北京市西城区三里河路南三巷3号','100032','18032356666',1,'2016-04-13 00:00:00',NULL,NULL,3),(6,'王国强','北京市顺义区高丽营镇金马工业区18号','100061','13787882222',1,'2016-04-13 00:00:00',NULL,NULL,3);
/*!40000 ALTER TABLE `smbms_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `smbms_bill`
--

DROP TABLE IF EXISTS `smbms_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `smbms_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `billCode` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '账单编码',
  `productName` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品名称',
  `productDesc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品描述',
  `productUnit` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品单位',
  `productCount` decimal(20,2) DEFAULT NULL COMMENT '商品数量',
  `totalPrice` decimal(20,2) DEFAULT NULL COMMENT '商品总额',
  `isPayment` int(10) DEFAULT NULL COMMENT '是否支付（1：未支付 2：已支付）',
  `createdBy` bigint(20) DEFAULT NULL COMMENT '创建者（userId）',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint(20) DEFAULT NULL COMMENT '更新者（userId）',
  `modifyDate` datetime DEFAULT NULL COMMENT '更新时间',
  `providerId` int(20) DEFAULT NULL COMMENT '供应商ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `smbms_bill`
--

LOCK TABLES `smbms_bill` WRITE;
/*!40000 ALTER TABLE `smbms_bill` DISABLE KEYS */;
INSERT INTO `smbms_bill` VALUES (2,'BILL2016_002','香皂、肥皂、药皂','日用品-皂类','块',1000.00,10000.00,1,1,'2016-03-23 04:20:40',NULL,NULL,13),(3,'BILL2016_003','大豆油','食品-食用油','斤',300.00,5890.00,2,1,'2014-12-14 13:02:03',NULL,NULL,6),(4,'BILL2016_004','橄榄油','食品-进口食用油','斤',200.00,9800.00,2,1,'2013-10-10 03:12:13',NULL,NULL,7),(5,'BILL2016_005','洗洁精','日用品-厨房清洁','瓶',500.00,7000.00,2,1,'2014-12-14 13:02:03',NULL,NULL,9),(6,'BILL2016_006','美国大杏仁','食品-坚果','袋',300.00,5000.00,2,1,'2016-04-14 06:08:09',NULL,NULL,4),(7,'BILL2016_007','沐浴液、精油','日用品-沐浴类','瓶',500.00,23000.00,1,1,'2016-07-22 10:10:22',NULL,NULL,14),(8,'BILL2016_008','不锈钢盘碗','日用品-厨房用具','个',600.00,6000.00,2,1,'2016-04-14 05:12:13',NULL,NULL,14),(9,'BILL2016_009','塑料杯','日用品-杯子','个',350.00,1750.00,2,1,'2016-02-04 11:40:20',NULL,NULL,14),(10,'BILL2016_010','豆瓣酱','食品-调料','瓶',200.00,2000.00,2,1,'2013-10-29 05:07:03',NULL,NULL,8),(11,'BILL2016_011','海之蓝','饮料-国酒','瓶',50.00,10000.00,1,1,'2016-04-14 16:16:00',NULL,NULL,1),(12,'BILL2016_012','芝华士','饮料-洋酒','瓶',20.00,6000.00,1,1,'2016-09-09 17:00:00',NULL,NULL,1),(13,'BILL2016_013','长城红葡萄酒','饮料-红酒','瓶',60.00,800.00,2,1,'2016-11-14 15:23:00',NULL,NULL,1),(14,'BILL2016_014','泰国香米','食品-大米','斤',400.00,5000.00,2,1,'2016-10-09 15:20:00',NULL,NULL,3),(15,'BILL2016_015','东北大米','食品-大米','斤',600.00,4000.00,2,1,'2016-11-14 14:00:00',NULL,NULL,3),(17,'BILL2016_017','脉动','饮料','瓶',1500.00,4500.00,2,1,'2016-05-10 12:00:00',NULL,NULL,2),(18,'BILL2016_018','哇哈哈','饮料','瓶',2000.00,4000.00,2,1,'2015-11-24 15:12:03',NULL,NULL,2),(19,'BILL2021_015','水杯','水杯','瓶',1000.00,2700.00,1,1,NULL,NULL,NULL,2),(20,'BILL2021_016','酸奶西米露','风味酸奶，罐头','瓶',500.00,2500.00,2,1,'2021-03-21 00:00:00',NULL,NULL,22);
/*!40000 ALTER TABLE `smbms_bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `smbms_perms`
--

DROP TABLE IF EXISTS `smbms_perms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `smbms_perms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `action` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `smbms_perms`
--

LOCK TABLES `smbms_perms` WRITE;
/*!40000 ALTER TABLE `smbms_perms` DISABLE KEYS */;
INSERT INTO `smbms_perms` VALUES (1,'删除权限','admin:delPerm','/user/del_perm'),(2,'增加权限','admin:addPerm','/user/add_perm'),(3,'查看禁用列表','admin:findAllBan','/user/find_all_ban'),(4,'修改权限','admin:PermMod','/user/permModify'),(5,'查看权限列表','admin:findAllPerm','/user/find_all_perm'),(6,'修改用户信息','adminr:userMod','/user/toMod'),(7,'查看角色列表','admin:findAllRole','/user/find_all_role'),(8,'访问后台','user:login','/user/userlogin'),(9,'查询用户','admin:findAllByPage','/user/findAllByPage'),(10,'添加用户','adminr:addUser','/user/add_user'),(11,'更新用户','admin:update','/user/user_update'),(13,'发送邮件','user:email','/user/sendEmail'),(14,'用户管理','admin','/user/*'),(15,'用户功能','user','/user/sendEmail');
/*!40000 ALTER TABLE `smbms_perms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `smbms_provider`
--

DROP TABLE IF EXISTS `smbms_provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `smbms_provider` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `proCode` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供应商编码',
  `proName` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供应商名称',
  `proDesc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供应商详细描述',
  `proContact` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供应商联系人',
  `proPhone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `proAddress` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '地址',
  `proFax` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '传真',
  `createdBy` bigint(20) DEFAULT NULL COMMENT '创建者（userId）',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyDate` datetime DEFAULT NULL COMMENT '更新时间',
  `modifyBy` bigint(20) DEFAULT NULL COMMENT '更新者（userId）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `smbms_provider`
--

LOCK TABLES `smbms_provider` WRITE;
/*!40000 ALTER TABLE `smbms_provider` DISABLE KEYS */;
INSERT INTO `smbms_provider` VALUES (1,'BJ_GYS001','北京三木堂商贸有限公司','长期合作伙伴，主营产品:茅台、五粮液、郎酒、酒鬼酒、泸州老窖、赖茅酒、法国红酒等','张国强','13566667777','北京市丰台区育芳园北路','010-58858787',1,'2013-03-21 16:52:07',NULL,NULL),(2,'HB_GYS001','石家庄帅益食品贸易有限公司','长期合作伙伴，主营产品:饮料、水饮料、植物蛋白饮料、休闲食品、果汁饮料、功能饮料等','王军','13309094212','河北省石家庄新华区','0311-67738876',1,'2016-04-13 04:20:40',NULL,NULL),(3,'GZ_GYS001','深圳市泰香米业有限公司','初次合作伙伴，主营产品：良记金轮米,龙轮香米等','郑程瀚','13402013312','广东省深圳市福田区深南大道6006华丰大厦','0755-67776212',1,'2014-03-21 16:56:07',NULL,NULL),(4,'GZ_GYS002','深圳市喜来客商贸有限公司','长期合作伙伴，主营产品：坚果炒货.果脯蜜饯.天然花茶.营养豆豆.特色美食.进口食品.海味零食.肉脯肉','林妮','18599897645','广东省深圳市福龙工业区B2栋3楼西','0755-67772341',1,'2013-03-22 16:52:07',NULL,NULL),(5,'JS_GYS001','兴化佳美调味品厂','长期合作伙伴，主营产品：天然香辛料、鸡精、复合调味料','徐国洋','13754444221','江苏省兴化市林湖工业区','0523-21299098',1,'2015-11-22 16:52:07',NULL,NULL),(6,'BJ_GYS002','北京纳福尔食用油有限公司','长期合作伙伴，主营产品：山茶油、大豆油、花生油、橄榄油等','马莺','13422235678','北京市朝阳区珠江帝景1号楼','010-588634233',1,'2012-03-21 17:52:07',NULL,NULL),(7,'BJ_GYS003','北京国粮食用油有限公司','初次合作伙伴，主营产品：花生油、大豆油、小磨油等','王驰','13344441135','北京大兴青云店开发区','010-588134111',1,'2016-04-13 00:00:00',NULL,NULL),(8,'ZJ_GYS001','慈溪市广和绿色食品厂','长期合作伙伴，主营产品：豆瓣酱、黄豆酱、甜面酱，辣椒，大蒜等农产品','薛圣丹','18099953223','浙江省宁波市慈溪周巷小安村','0574-34449090',1,'2013-11-21 06:02:07',NULL,NULL),(9,'GX_GYS001','优百商贸有限公司','长期合作伙伴，主营产品：日化产品','李立国','13323566543','广西南宁市秀厢大道42-1号','0771-98861134',1,'2013-03-21 19:52:07',NULL,NULL),(10,'JS_GYS002','南京火头军信息技术有限公司','长期合作伙伴，主营产品：不锈钢厨具等','陈女士','13098992113','江苏省南京市浦口区浦口大道1号新城总部大厦A座903室','025-86223345',1,'2013-03-25 16:52:07',NULL,NULL),(11,'GZ_GYS003','广州市白云区美星五金制品厂','长期合作伙伴，主营产品：海绵床垫、坐垫、靠垫、海绵枕头、头枕等','梁天','13562276775','广州市白云区钟落潭镇福龙路20号','020-85542231',1,'2016-12-21 06:12:17',NULL,NULL),(12,'BJ_GYS004','北京隆盛日化科技','长期合作伙伴，主营产品：日化环保清洗剂，家居洗涤专卖、洗涤用品网、墙体除霉剂、墙面霉菌清除剂等','孙欣','13689865678','北京市大兴区旧宫','010-35576786',1,'2014-11-21 12:51:11',NULL,NULL),(13,'SD_GYS001','山东豪克华光联合发展有限公司','长期合作伙伴，主营产品：洗衣皂、洗衣粉、洗衣液、洗洁精、消杀类、香皂等','吴洪转','13245468787','山东济阳济北工业区仁和街21号','0531-53362445',1,'2015-01-28 10:52:07',NULL,NULL),(14,'JS_GYS003','无锡喜源坤商行','长期合作伙伴，主营产品：日化品批销','周一清','18567674532','江苏无锡盛岸西路','0510-32274422',1,'2016-04-23 11:11:11',NULL,NULL),(15,'ZJ_GYS002','乐摆日用品厂','长期合作伙伴，主营产品：各种中、高档塑料杯，塑料乐扣水杯（密封杯）、保鲜杯（保鲜盒）、广告杯、礼品杯','王世杰','13212331567','浙江省金华市义乌市义东路','0579-34452321',1,'2016-08-22 10:01:30',NULL,NULL),(21,'wzu_lyy','LYY',NULL,'林燕燕','15606636106','温州大学','123-123',NULL,NULL,NULL,NULL),(22,'ZJ_GYS123','三只松鼠有限公司','长期合作伙伴，主营产品：坚果炒货.果脯蜜饯.天然花茶.营养豆豆.特色美食.进口食品.海味零食.肉脯肉','林女士','12345678915','安徽省芜湖市无为市','456-456',1,'2021-06-16 00:00:00',NULL,NULL),(23,'test_123','123',NULL,'黄传晖','18211170212','温州大学','123-123',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `smbms_provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `smbms_role`
--

DROP TABLE IF EXISTS `smbms_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `smbms_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `roleCode` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色编码',
  `roleName` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `createdBy` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint(20) DEFAULT NULL COMMENT '修改者',
  `modifyDate` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `smbms_role`
--

LOCK TABLES `smbms_role` WRITE;
/*!40000 ALTER TABLE `smbms_role` DISABLE KEYS */;
INSERT INTO `smbms_role` VALUES (1,'admin','系统管理员',1,'2016-04-13 00:00:00',NULL,NULL),(2,'manager','经理',1,'2016-04-13 00:00:00',NULL,NULL),(3,'employee','普通员工',1,'2016-04-13 00:00:00',NULL,NULL),(5,'super','超级用户',1,'2021-09-08 00:00:00',NULL,NULL),(6,'root','root',1,'2021-09-08 00:00:00',NULL,NULL);
/*!40000 ALTER TABLE `smbms_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `smbms_role_permission`
--

DROP TABLE IF EXISTS `smbms_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `smbms_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `perm_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `smbms_role_permission`
--

LOCK TABLES `smbms_role_permission` WRITE;
/*!40000 ALTER TABLE `smbms_role_permission` DISABLE KEYS */;
INSERT INTO `smbms_role_permission` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(13,1,13),(14,1,14),(15,1,15),(16,2,1),(17,2,2),(18,2,3),(19,2,14),(20,2,5),(21,2,6),(22,2,7),(23,2,8),(24,2,9),(25,2,10),(26,2,11),(28,2,13),(29,2,15),(30,3,7),(31,3,8),(32,3,15),(33,4,8),(34,4,15),(35,5,1),(36,5,2),(37,5,3),(38,5,4),(39,5,5),(40,5,6),(41,5,7),(42,5,8),(43,5,9),(44,5,10),(45,5,11),(47,5,13),(49,5,15);
/*!40000 ALTER TABLE `smbms_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `smbms_user`
--

DROP TABLE IF EXISTS `smbms_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `smbms_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `userCode` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户编码',
  `userName` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名称',
  `userPassword` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户密码',
  `gender` int(10) DEFAULT NULL COMMENT '性别（1:女、 2:男）',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `phone` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机',
  `address` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '地址',
  `userRole` int(10) DEFAULT NULL COMMENT '用户角色（取自角色表-角色id）',
  `createdBy` bigint(20) DEFAULT NULL COMMENT '创建者（userId）',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint(20) DEFAULT NULL COMMENT '更新者（userId）',
  `modifyDate` datetime DEFAULT NULL COMMENT '更新时间',
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `roles` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `smbms_user`
--

LOCK TABLES `smbms_user` WRITE;
/*!40000 ALTER TABLE `smbms_user` DISABLE KEYS */;
INSERT INTO `smbms_user` VALUES (1,'admin','系统管理员','e544a48644aaf037a0963651fa5073b3',1,'1983-10-10','13688889999','浙江省温州市茶山镇温州大学',1,1,'2013-03-21 16:52:07',NULL,NULL,'9kgMBcVK',1,NULL),(2,'liming','李明','28eb7a3ce71660b155b5cd0cf52044b3',2,'1983-12-10','13688884457','北京市东城区前门东大街9号',2,1,'0000-00-00 00:00:00',NULL,NULL,'GTCcijUC',1,NULL),(5,'hanlubiao','韩路彪','0000000',2,'1984-06-05','18567542321','北京市朝阳区北辰中心12号',2,1,'2014-12-31 19:52:09',NULL,NULL,NULL,2,NULL),(6,'zhanghua','张华','0000000',1,'1983-06-15','13544561111','北京市海淀区学院路61号',2,1,'2013-02-11 10:51:17',NULL,NULL,NULL,1,NULL),(7,'wangyang','王洋','0000000',2,'1982-12-31','13444561124','北京市海淀区西二旗辉煌国际16层',3,1,'2014-06-11 19:09:07',NULL,NULL,NULL,2,NULL),(8,'zhaoyan','赵燕','0000000',1,'1986-03-07','18098764545','北京市海淀区回龙观小区10号楼',3,1,'2016-04-21 13:54:07',NULL,NULL,NULL,1,NULL),(10,'sunlei','孙磊','0000000',2,'1981-01-04','13387676765','北京市朝阳区管庄新月小区12楼',3,1,'2015-05-06 10:52:07',NULL,NULL,NULL,NULL,NULL),(11,'sunxing','孙兴','0000000',2,'1978-03-12','13367890900','北京市朝阳区建国门南大街10号',3,1,'2016-11-09 16:51:17',NULL,NULL,NULL,NULL,NULL),(12,'zhangchen','张晨','0000000',1,'1986-03-28','18098765434','朝阳区管庄路口北柏林爱乐三期13号楼',3,1,'2016-08-09 05:52:37',1,'2016-04-14 14:15:36',NULL,NULL,NULL),(13,'dengchao','邓超','e6817b457dcb8e04da565dfc964868fe',2,'1981-11-04','13689674534','北京市海淀区北航家属院10号楼',3,1,'2016-07-11 08:02:47',NULL,NULL,'EmhKAVoY',NULL,NULL),(15,'zhaomin','赵敏','0000000',1,'1987-12-04','18099897657','北京市昌平区天通苑3区12号楼',2,1,'2015-09-12 12:02:12',NULL,NULL,NULL,1,NULL);
/*!40000 ALTER TABLE `smbms_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `smbms_user_role`
--

DROP TABLE IF EXISTS `smbms_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `smbms_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `smbms_user_role`
--

LOCK TABLES `smbms_user_role` WRITE;
/*!40000 ALTER TABLE `smbms_user_role` DISABLE KEYS */;
INSERT INTO `smbms_user_role` VALUES (1,1,1),(2,2,2),(3,19,3),(14,21,5),(15,22,3),(16,23,5),(17,24,3),(18,25,1),(19,26,2),(20,27,4),(21,28,1),(22,29,4),(23,30,3),(24,31,3),(25,32,3),(26,33,2);
/*!40000 ALTER TABLE `smbms_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_book`
--

DROP TABLE IF EXISTS `t_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `price` decimal(11,2) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `sales` int(11) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `img_path` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_book`
--

LOCK TABLES `t_book` WRITE;
/*!40000 ALTER TABLE `t_book` DISABLE KEYS */;
INSERT INTO `t_book` VALUES (1,'java从入门到放弃',80.00,'国哥',9999,9,'static/img/default.jpg'),(2,'数据结构与算法',78.50,'严敏君',6,13,'static/img/default.jpg'),(3,'怎样拐跑别人的媳妇',68.00,'龙伍',99999,52,'static/img/default.jpg'),(4,'木虚肉盖饭',16.00,'小胖',1000,50,'static/img/default.jpg'),(5,'C++编程思想',45.50,'刚哥',14,95,'static/img/default.jpg'),(6,'蛋炒饭',9.90,'周星星',12,53,'static/img/default.jpg'),(7,'赌神',66.50,'龙伍',125,535,'static/img/default.jpg'),(8,'Java编程思想',99.50,'阳哥',47,36,'static/img/default.jpg'),(9,'JavaScript从入门到精通',9.90,'婷姐',85,95,'static/img/default.jpg'),(10,'cocos2d-x游戏编程入门',49.00,'国哥',52,62,'static/img/default.jpg'),(11,'C语言程序设计',28.00,'谭浩强',52,74,'static/img/default.jpg'),(12,'Lua语言程序设计',51.50,'雷丰阳',48,82,'static/img/default.jpg'),(13,'西游记',12.00,'罗贯中',19,9999,'static/img/default.jpg'),(14,'水浒传',33.05,'华仔',22,88,'static/img/default.jpg'),(15,'操作系统原理',133.05,'刘优',122,188,'static/img/default.jpg'),(16,'数据结构 java版',173.15,'封大神',21,81,'static/img/default.jpg'),(17,'UNIX高级环境编程',99.15,'乐天',210,810,'static/img/default.jpg'),(18,'javaScript高级编程',69.15,'国哥',210,810,'static/img/default.jpg'),(19,'大话设计模式',89.15,'国哥',20,10,'static/img/default.jpg'),(20,'人月神话',88.15,'刚哥',20,80,'static/img/default.jpg');
/*!40000 ALTER TABLE `t_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_order` (
  `orderId` varchar(50) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `price` decimal(11,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderId`),
  KEY `t_order_ibfk_1` (`user_id`),
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `shopper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
INSERT INTO `t_order` VALUES ('Order-Sun Mar 21 00:00:00 GMT+08:00 2021','2021-03-21 00:00:00',16.00,0,2),('OrderMon Jun 28 10:02:51 GMT+08:00 2021','2021-06-28 10:02:51',68.00,0,2),('OrderMon Jun 28 10:02:54 GMT+08:00 2021','2021-06-28 10:02:55',78.50,0,2),('OrderMon Jun 28 10:03:15 GMT+08:00 2021','2021-06-28 10:03:16',16.00,0,2),('OrderSat Jun 26 13:49:09 GMT+08:00 2021','2021-06-26 13:49:10',78.50,0,2),('OrderSat Jun 26 14:30:37 GMT+08:00 2021','2021-06-26 14:30:37',66.50,0,2),('OrderSat Jun 26 14:39:18 GMT+08:00 2021','2021-03-21 00:00:00',9.90,0,2),('OrderSat Jun 26 15:34:51 GMT+08:00 2021','2021-06-26 15:34:51',66.50,0,2),('OrderWed Sep 08 10:11:31 GMT+08:00 2021','2021-09-08 10:11:32',68.00,0,2);
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order_item`
--

DROP TABLE IF EXISTS `t_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `price` decimal(11,2) DEFAULT NULL,
  `totalPrice` decimal(11,2) DEFAULT NULL,
  `orderId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `t_order_item_ibfk_1` (`orderId`),
  CONSTRAINT `t_order_item_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `t_order` (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order_item`
--

LOCK TABLES `t_order_item` WRITE;
/*!40000 ALTER TABLE `t_order_item` DISABLE KEYS */;
INSERT INTO `t_order_item` VALUES (1,'java从入门到放弃',1,80.00,80.00,'OrderSat Jun 26 13:49:09 GMT+08:00 2021'),(2,'数据结构与算法',1,78.50,78.50,'OrderSat Jun 26 13:49:09 GMT+08:00 2021'),(3,'赌神',1,66.50,66.50,'OrderSat Jun 26 14:30:37 GMT+08:00 2021'),(4,'蛋炒饭',1,9.90,9.90,'OrderSat Jun 26 14:39:18 GMT+08:00 2021'),(5,'木虚肉盖饭',1,16.00,16.00,'Order-Sun Mar 21 00:00:00 GMT+08:00 2021'),(6,'赌神',1,66.50,66.50,'OrderSat Jun 26 15:34:51 GMT+08:00 2021');
/*!40000 ALTER TABLE `t_order_item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-15 19:23:40
