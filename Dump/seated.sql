CREATE DATABASE  IF NOT EXISTS `gipsy_fingers` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `gipsy_fingers`;
-- MySQL dump 10.13  Distrib 5.5.9, for Win32 (x86)
--
-- Host: localhost    Database: gipsy_fingers
-- ------------------------------------------------------
-- Server version	5.5.8

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
-- Table structure for table `seated`
--

DROP TABLE IF EXISTS `seated`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seated` (
  `user_id` int(11) NOT NULL,
  `hall_id` int(11) NOT NULL,
  `time` int(11) NOT NULL,
  `sit_x` int(11) NOT NULL,
  `sit_y` int(11) NOT NULL,
  `date` varchar(255) NOT NULL,
  `film_id` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seated`
--

LOCK TABLES `seated` WRITE;
/*!40000 ALTER TABLE `seated` DISABLE KEYS */;
INSERT INTO `seated` VALUES (1,2,1,0,0,'07-05-20122',2),(1,2,1,0,1,'07-05-20122',2),(1,2,1,0,2,'07-05-20122',2),(1,2,1,0,3,'07-05-20122',2),(1,1,0,0,0,'06-05-20121',1),(1,1,0,0,1,'06-05-20121',1),(1,1,0,0,2,'06-05-20121',1),(1,2,1,3,3,'07-05-20122',2),(1,3,2,0,0,'07-05-20122',3),(1,3,2,0,1,'07-05-20122',3),(1,3,2,0,2,'07-05-20122',3),(1,3,2,0,3,'07-05-20122',3),(1,3,2,0,0,'07-05-2012',3),(1,3,2,0,1,'07-05-2012',3),(1,3,2,0,2,'07-05-2012',3),(1,3,2,0,3,'07-05-2012',3),(1,2,1,0,0,'07-05-2012',2),(1,2,1,0,1,'07-05-2012',2),(2,2,1,0,10,'07-05-2012',2),(2,2,1,2,10,'07-05-2012',2),(2,2,1,1,10,'07-05-2012',2),(2,3,2,0,6,'07-05-2012',3),(2,3,2,0,7,'07-05-2012',3),(2,3,2,0,8,'07-05-2012',3),(2,3,2,0,9,'07-05-2012',3),(2,3,2,0,10,'07-05-2012',3),(1,3,2,0,4,'07-05-2012',3),(3,3,2,1,0,'07-05-2012',3),(3,3,2,1,1,'07-05-2012',3),(3,3,2,1,2,'07-05-2012',3),(3,3,2,1,3,'07-05-2012',3),(3,3,2,1,4,'07-05-2012',3),(1,2,2,0,0,'09-05-2012',2),(1,2,2,0,1,'09-05-2012',2),(1,2,2,0,2,'09-05-2012',2),(2,3,0,0,0,'12-05-2012',1),(2,3,0,0,1,'12-05-2012',1),(2,3,0,0,2,'12-05-2012',1),(2,3,0,0,3,'12-05-2012',1),(2,3,0,0,4,'12-05-2012',1),(2,2,1,0,0,'12-05-2012',1),(2,2,1,0,1,'12-05-2012',1),(2,2,1,1,0,'05-05-2012',1),(2,2,1,1,1,'05-05-2012',1),(2,2,1,1,3,'05-05-2012',1),(2,2,1,1,2,'05-05-2012',1),(2,2,1,1,4,'05-05-2012',1),(1,1,0,0,0,'06-05-2012',1),(1,1,0,0,3,'06-05-2012',1),(1,1,0,0,5,'06-05-2012',1),(1,1,0,0,6,'06-05-2012',1),(1,1,0,0,7,'06-05-2012',1),(1,1,0,0,4,'06-05-2012',1);
/*!40000 ALTER TABLE `seated` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-05-24 23:39:32
