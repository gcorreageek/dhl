-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: localhost    Database: ProyService1
-- ------------------------------------------------------
-- Server version	5.7.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOG`
--

LOCK TABLES `DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOG` VALUES ('00000000000001','jhipster','classpath:config/liquibase/changelog/00000000000000_initial_schema.xml','2016-10-19 05:54:34',1,'EXECUTED','7:e252e2818760f55d8484cbfdccefd113','createTable, createIndex (x2), createTable (x2), addPrimaryKey, addForeignKeyConstraint (x2), loadData, dropDefaultValue, loadData (x2), createTable (x2), addPrimaryKey, createIndex (x2), addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),('20161019074950-1','jhipster','classpath:config/liquibase/changelog/20161019074950_added_entity_UserPlus.xml','2016-10-19 05:54:35',2,'EXECUTED','7:b329182e9d82928806fc4d7f8885f8eb','createTable, dropDefaultValue','',NULL,'3.4.2',NULL,NULL),('20161019074951-1','jhipster','classpath:config/liquibase/changelog/20161019074951_added_entity_UserImagen.xml','2016-10-19 05:54:35',3,'EXECUTED','7:cd5a3636f2f93e62f12882c41ef12319','createTable','',NULL,'3.4.2',NULL,NULL),('20161019074952-1','jhipster','classpath:config/liquibase/changelog/20161019074952_added_entity_Friend.xml','2016-10-19 05:54:35',4,'EXECUTED','7:b1f2d8c6a0560a68828b7b09ecbc7183','createTable','',NULL,'3.4.2',NULL,NULL),('20161019074953-1','jhipster','classpath:config/liquibase/changelog/20161019074953_added_entity_Hash.xml','2016-10-19 05:54:35',5,'EXECUTED','7:b1c070b01a758b2a1fdc11b672023e40','createTable','',NULL,'3.4.2',NULL,NULL),('20161019074954-1','jhipster','classpath:config/liquibase/changelog/20161019074954_added_entity_UserHash.xml','2016-10-19 05:54:35',6,'EXECUTED','7:9fa7ff6d0d3dd32d9656ca83f885c295','createTable','',NULL,'3.4.2',NULL,NULL),('20161019074955-1','jhipster','classpath:config/liquibase/changelog/20161019074955_added_entity_Article.xml','2016-10-19 05:54:35',7,'EXECUTED','7:365a08706c9ff87474014e0a060c73d6','createTable, dropDefaultValue','',NULL,'3.4.2',NULL,NULL),('20161019074956-1','jhipster','classpath:config/liquibase/changelog/20161019074956_added_entity_ArticleHash.xml','2016-10-19 05:54:35',8,'EXECUTED','7:66126190c7cae8c1bc2be08218fe3fbe','createTable','',NULL,'3.4.2',NULL,NULL),('20161019074957-1','jhipster','classpath:config/liquibase/changelog/20161019074957_added_entity_ArticleReaction.xml','2016-10-19 05:54:35',9,'EXECUTED','7:f050d96c84393876d0abfeb873816d48','createTable','',NULL,'3.4.2',NULL,NULL),('20161019074950-2','jhipster','classpath:config/liquibase/changelog/20161019074950_added_entity_constraints_UserPlus.xml','2016-10-19 05:54:35',10,'EXECUTED','7:77a52dab9564240e52740a98b7014d08','addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),('20161019074951-2','jhipster','classpath:config/liquibase/changelog/20161019074951_added_entity_constraints_UserImagen.xml','2016-10-19 05:54:35',11,'EXECUTED','7:e7a4eb163ea37cf1e94d8a543fe82d20','addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),('20161019074952-2','jhipster','classpath:config/liquibase/changelog/20161019074952_added_entity_constraints_Friend.xml','2016-10-19 05:54:35',12,'EXECUTED','7:97c6ceec8b7b0827971d10d06867e341','addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),('20161019074953-2','jhipster','classpath:config/liquibase/changelog/20161019074953_added_entity_constraints_Hash.xml','2016-10-19 05:54:35',13,'EXECUTED','7:a1dceea01c7dfe5d5ec257b9f2bd90c2','addForeignKeyConstraint','',NULL,'3.4.2',NULL,NULL),('20161019074954-2','jhipster','classpath:config/liquibase/changelog/20161019074954_added_entity_constraints_UserHash.xml','2016-10-19 05:54:35',14,'EXECUTED','7:17508d74a337c4b6b222f75d3be88130','addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),('20161019074955-2','jhipster','classpath:config/liquibase/changelog/20161019074955_added_entity_constraints_Article.xml','2016-10-19 05:54:35',15,'EXECUTED','7:0d2bd7cd4c32560ae6c50b86b79095e3','addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),('20161019074956-2','jhipster','classpath:config/liquibase/changelog/20161019074956_added_entity_constraints_ArticleHash.xml','2016-10-19 05:54:35',16,'EXECUTED','7:377d19f9801224c81060bd17fab77cfc','addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL),('20161019074957-2','jhipster','classpath:config/liquibase/changelog/20161019074957_added_entity_constraints_ArticleReaction.xml','2016-10-19 05:54:35',17,'EXECUTED','7:e5a841b2063f603bc6dbafe99be1ef72','addForeignKeyConstraint (x2)','',NULL,'3.4.2',NULL,NULL);
/*!40000 ALTER TABLE `DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOGLOCK`
--

LOCK TABLES `DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOGLOCK` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_name` varchar(255) DEFAULT NULL,
  `article_description` varchar(255) DEFAULT NULL,
  `article_date_time` timestamp NULL,
  `user_imagen_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_article_user_imagen_id` (`user_imagen_id`),
  KEY `fk_article_user_id` (`user_id`),
  CONSTRAINT `fk_article_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`),
  CONSTRAINT `fk_article_user_imagen_id` FOREIGN KEY (`user_imagen_id`) REFERENCES `user_imagen` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_hash`
--

DROP TABLE IF EXISTS `article_hash`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_hash` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hash_id` bigint(20) DEFAULT NULL,
  `article_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_article_hash_hash_id` (`hash_id`),
  KEY `fk_article_hash_article_id` (`article_id`),
  CONSTRAINT `fk_article_hash_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
  CONSTRAINT `fk_article_hash_hash_id` FOREIGN KEY (`hash_id`) REFERENCES `hash` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_hash`
--

LOCK TABLES `article_hash` WRITE;
/*!40000 ALTER TABLE `article_hash` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_hash` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_reaction`
--

DROP TABLE IF EXISTS `article_reaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_reaction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_reaction_type` varchar(255) DEFAULT NULL,
  `article_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_article_reaction_article_id` (`article_id`),
  KEY `fk_article_reaction_user_id` (`user_id`),
  CONSTRAINT `fk_article_reaction_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
  CONSTRAINT `fk_article_reaction_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_reaction`
--

LOCK TABLES `article_reaction` WRITE;
/*!40000 ALTER TABLE `article_reaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_reaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend`
--

DROP TABLE IF EXISTS `friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `friend_type` varchar(255) DEFAULT NULL,
  `user_1_id` bigint(20) DEFAULT NULL,
  `user_2_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_friend_user_1_id` (`user_1_id`),
  KEY `fk_friend_user_2_id` (`user_2_id`),
  CONSTRAINT `fk_friend_user_1_id` FOREIGN KEY (`user_1_id`) REFERENCES `jhi_user` (`id`),
  CONSTRAINT `fk_friend_user_2_id` FOREIGN KEY (`user_2_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend`
--

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
/*!40000 ALTER TABLE `friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hash`
--

DROP TABLE IF EXISTS `hash`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hash` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hash_name` varchar(255) DEFAULT NULL,
  `hash_type` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_hash_user_id` (`user_id`),
  CONSTRAINT `fk_hash_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hash`
--

LOCK TABLES `hash` WRITE;
/*!40000 ALTER TABLE `hash` DISABLE KEYS */;
/*!40000 ALTER TABLE `hash` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_authority`
--

DROP TABLE IF EXISTS `jhi_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_authority`
--

LOCK TABLES `jhi_authority` WRITE;
/*!40000 ALTER TABLE `jhi_authority` DISABLE KEYS */;
INSERT INTO `jhi_authority` VALUES ('ROLE_ADMIN'),('ROLE_USER');
/*!40000 ALTER TABLE `jhi_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_event`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` varchar(50) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `idx_persistent_audit_event` (`principal`,`event_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_event`
--

LOCK TABLES `jhi_persistent_audit_event` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` DISABLE KEYS */;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_evt_data`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_evt_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`name`),
  KEY `idx_persistent_audit_evt_data` (`event_id`),
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_evt_data`
--

LOCK TABLES `jhi_persistent_audit_evt_data` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user`
--

DROP TABLE IF EXISTS `jhi_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password_hash` varchar(60) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(5) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NOT NULL,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `idx_user_login` (`login`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `idx_user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user`
--

LOCK TABLES `jhi_user` WRITE;
/*!40000 ALTER TABLE `jhi_user` DISABLE KEYS */;
INSERT INTO `jhi_user` VALUES (1,'system','$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG','System','System','system@localhost','','en',NULL,NULL,'system','2016-10-19 07:54:34',NULL,'system','2016-10-19 07:54:34'),(2,'anonymoususer','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO','Anonymous','User','anonymous@localhost','','en',NULL,NULL,'system','2016-10-19 07:54:34',NULL,'system','2016-10-19 07:54:34'),(3,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','Administrator','Administrator','admin@localhost','','en',NULL,NULL,'system','2016-10-19 07:54:34',NULL,'system','2016-10-19 07:54:34'),(4,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','User','User','user@localhost','','en',NULL,NULL,'system','2016-10-19 07:54:34',NULL,'system','2016-10-19 07:54:34');
/*!40000 ALTER TABLE `jhi_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user_authority`
--

DROP TABLE IF EXISTS `jhi_user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user_authority`
--

LOCK TABLES `jhi_user_authority` WRITE;
/*!40000 ALTER TABLE `jhi_user_authority` DISABLE KEYS */;
INSERT INTO `jhi_user_authority` VALUES (1,'ROLE_ADMIN'),(3,'ROLE_ADMIN'),(1,'ROLE_USER'),(3,'ROLE_USER'),(4,'ROLE_USER');
/*!40000 ALTER TABLE `jhi_user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_hash`
--

DROP TABLE IF EXISTS `user_hash`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_hash` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hash_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_hash_hash_id` (`hash_id`),
  KEY `fk_user_hash_user_id` (`user_id`),
  CONSTRAINT `fk_user_hash_hash_id` FOREIGN KEY (`hash_id`) REFERENCES `hash` (`id`),
  CONSTRAINT `fk_user_hash_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_hash`
--

LOCK TABLES `user_hash` WRITE;
/*!40000 ALTER TABLE `user_hash` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_hash` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_imagen`
--

DROP TABLE IF EXISTS `user_imagen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_imagen` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_imagen_name` varchar(255) DEFAULT NULL,
  `user_imagen_path` varchar(255) DEFAULT NULL,
  `user_imagen_path_image` varchar(255) DEFAULT NULL,
  `user_imagen_main` bit(1) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_imagen_user_id` (`user_id`),
  CONSTRAINT `fk_user_imagen_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_imagen`
--

LOCK TABLES `user_imagen` WRITE;
/*!40000 ALTER TABLE `user_imagen` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_imagen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_plus`
--

DROP TABLE IF EXISTS `user_plus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_plus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `weigh` varchar(255) DEFAULT NULL,
  `height` varchar(255) DEFAULT NULL,
  `birthday` timestamp NULL,
  `sex` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `languaje` varchar(255) DEFAULT NULL,
  `disabled_perfil` bit(1) DEFAULT NULL,
  `show_weigh` bit(1) DEFAULT NULL,
  `show_height` bit(1) DEFAULT NULL,
  `show_birthday` bit(1) DEFAULT NULL,
  `show_sex` bit(1) DEFAULT NULL,
  `show_country` bit(1) DEFAULT NULL,
  `show_languaje` bit(1) DEFAULT NULL,
  `notification_news` bit(1) DEFAULT NULL,
  `options` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_plus_user_id` (`user_id`),
  CONSTRAINT `fk_user_plus_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_plus`
--

LOCK TABLES `user_plus` WRITE;
/*!40000 ALTER TABLE `user_plus` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_plus` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-19 23:06:32
