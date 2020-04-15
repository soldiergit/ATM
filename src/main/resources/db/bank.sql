-- MySQL dump 10.13  Distrib 5.7.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bank
-- ------------------------------------------------------
-- Server version	5.7.29

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
-- Table structure for table `account_info`
--
DROP TABLE IF EXISTS `account_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cardNo` int(32) NOT NULL COMMENT '银行卡账号',
  `pwd` int(6) NOT NULL,
  `fullName` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `balance` double(20,2) NOT NULL COMMENT '余额',
  `surplusInputNum` int(3) NOT NULL COMMENT '可输入密码的剩余次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='ATM账户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_info`
--

LOCK TABLES `account_info` WRITE;
/*!40000 ALTER TABLE `account_info` DISABLE KEYS */;
INSERT INTO `account_info` VALUES (1,123,123,'张三',910.00,5);
/*!40000 ALTER TABLE `account_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher_info`
--

DROP TABLE IF EXISTS `voucher_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `voucher_info` (
  `watercourseNum` int(64) NOT NULL AUTO_INCREMENT,
  `atmId` int(16) NOT NULL,
  `money` int(6) NOT NULL,
  `updateTime` datetime NOT NULL,
  `cardNo` int(32) NOT NULL,
  `optionNum` int(3) NOT NULL,
  PRIMARY KEY (`watercourseNum`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher_info`
--

LOCK TABLES `voucher_info` WRITE;
/*!40000 ALTER TABLE `voucher_info` DISABLE KEYS */;
INSERT INTO `voucher_info` VALUES (1,1,100,'2020-03-23 18:38:55',123,2),(2,1,100,'2020-03-23 21:56:17',123,1),(3,1,100,'2020-03-23 22:24:30',123,3),(4,1,100,'2020-03-23 22:36:46',123,1),(5,1,100,'2020-03-23 22:52:57',123,1),(6,1,100,'2020-03-23 22:58:23',123,2),(7,1,100,'2020-03-23 22:59:08',123,1),(8,1,100,'2020-03-23 23:02:21',123,2),(9,1,100,'2020-03-23 23:04:51',123,2);
/*!40000 ALTER TABLE `voucher_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-23 23:30:36
