-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: student_management
-- ------------------------------------------------------
-- Server version	5.7.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` float NOT NULL AUTO_INCREMENT,
  `user_name` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'admin','$2a$12$R6lwthV5PWkp./z6TepadO0H3vFl5tsnZayuPnDH6tY9MTMK8Vgly',1,'admin@gmail.com'),(2,'user1','$2a$12$R6lwthV5PWkp./z6TepadO0H3vFl5tsnZayuPnDH6tY9MTMK8Vgly',1,'user1@gmail.com');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_roles`
--

DROP TABLE IF EXISTS `account_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_roles` (
  `id` float NOT NULL AUTO_INCREMENT,
  `account_id` int(11) DEFAULT NULL,
  `roles_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_roles`
--

LOCK TABLES `account_roles` WRITE;
/*!40000 ALTER TABLE `account_roles` DISABLE KEYS */;
INSERT INTO `account_roles` VALUES (1,1,1),(2,2,2);
/*!40000 ALTER TABLE `account_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `class_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `code` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `limit_class` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `description` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `address` varchar(250) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1,'Lớp 10a1','10a1K57',45,50,1,1,NULL,'2023-07-15','2023-09-15','105A1'),(2,'Lớp 10a2','10a2K57',45,50,0,0,NULL,'2023-09-01','2023-12-24','105A2'),(3,'10a3','10a3K58',30,50,1,1,NULL,'2023-09-02','2023-12-25','105A3'),(4,'10a4','10a4',30,50,0,0,'intern','2023-09-02','2023-12-25','105A2'),(5,'10a5','10a5',30,50,0,0,'intern','2023-09-02','2023-12-25','105A2'),(6,'11a1','11a1',25,50,1,1,'junior','2023-09-02','2023-12-25','105A2'),(10,'11a2','11a2K57',30,50,1,0,NULL,'2023-09-02','2023-12-25','105A2'),(11,'11a3','11a3',30,50,1,1,'junior','2023-09-02','2023-12-25','105A2'),(12,'11a4','11a4',30,50,1,0,'junior','2023-09-02','2023-12-25','105A2'),(13,'11a5','11a5',30,50,1,1,'junior','2023-09-02','2023-12-25','105A2'),(14,'12a1','12a1',30,50,1,1,'mid','2023-09-02','2023-12-25','105A2'),(15,'12a2','12a2',30,50,1,1,'mid','2023-09-02','2023-12-25','105A2'),(16,'12a3','12a3',30,50,1,1,'mid','2023-09-02','2023-12-25','105A2'),(18,'12a4','12a4',25,50,1,0,'abc','2023-05-04','2023-05-30','P103A5'),(28,'Kinh te 1','KT1',50,80,1,1,'inter','2023-04-11','2023-04-29','Phong 105A3'),(35,'Kinh te 1','KT1',50,80,1,0,'inter','2023-04-11','2023-04-29','Phong 105A3');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_course`
--

DROP TABLE IF EXISTS `class_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_course` (
  `class_course_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`class_course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_course`
--

LOCK TABLES `class_course` WRITE;
/*!40000 ALTER TABLE `class_course` DISABLE KEYS */;
INSERT INTO `class_course` VALUES (1,1,1),(2,2,2),(3,3,1),(4,4,2);
/*!40000 ALTER TABLE `class_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_semester`
--

DROP TABLE IF EXISTS `class_semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_semester` (
  `class_semester_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL,
  `semester_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`class_semester_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_semester`
--

LOCK TABLES `class_semester` WRITE;
/*!40000 ALTER TABLE `class_semester` DISABLE KEYS */;
INSERT INTO `class_semester` VALUES (1,1,1),(2,2,1),(3,3,2);
/*!40000 ALTER TABLE `class_semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_student`
--

DROP TABLE IF EXISTS `class_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_student` (
  `class_student_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`class_student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_student`
--

LOCK TABLES `class_student` WRITE;
/*!40000 ALTER TABLE `class_student` DISABLE KEYS */;
INSERT INTO `class_student` VALUES (1,1,1,1),(2,1,2,1),(55,3,5,0),(56,1,3,0),(64,1,4,1),(65,2,4,0),(66,3,4,0),(90,2,3,1),(91,2,5,1),(92,1,5,0),(96,2,2,0),(97,1,6,1),(98,1,36,1),(99,2,37,0),(100,3,40,1),(101,1,55,0),(102,2,55,1),(103,3,55,1),(104,1,54,1),(105,4,59,1),(106,5,58,1),(107,16,61,1),(108,18,61,1),(109,2,61,1);
/*!40000 ALTER TABLE `class_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_subject`
--

DROP TABLE IF EXISTS `class_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_subject` (
  `class_subject_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `day` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `lesson` int(11) DEFAULT NULL,
  PRIMARY KEY (`class_subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_subject`
--

LOCK TABLES `class_subject` WRITE;
/*!40000 ALTER TABLE `class_subject` DISABLE KEYS */;
INSERT INTO `class_subject` VALUES (14,1,1,'2',1),(15,1,3,'3',1),(16,1,5,'4',1),(17,1,5,'5',1),(18,1,5,'6',1),(19,1,2,'2',2),(20,1,3,'3',2),(21,1,2,'4',2),(22,1,2,'5',2),(23,1,2,'6',2),(24,1,3,'2',3),(25,1,3,'3',3),(26,1,2,'4',3),(27,1,2,'5',3),(28,1,2,'6',3),(29,1,4,'2',4),(30,1,3,'3',4),(31,1,1,'4',4),(32,1,1,'5',4),(33,1,1,'6',4),(34,1,5,'2',5),(35,1,3,'3',5),(36,1,4,'4',5),(37,1,4,'5',5),(38,1,4,'6',5),(40,2,2,'2',2),(41,2,3,'2',3),(42,2,4,'2',4),(43,2,8,'3',1),(51,2,1,'2',1),(52,2,5,'2',5),(53,3,1,'2',1),(54,3,2,'2',2),(55,3,3,'2',3),(56,3,4,'2',4),(57,3,9,'2',5),(58,3,4,'3',1),(59,3,2,'3',2),(60,3,8,'3',3),(61,3,4,'3',4),(62,3,5,'3',5),(63,3,5,'5',1),(64,3,6,'5',2),(65,3,3,'5',3),(66,3,9,'5',4),(67,3,7,'5',5),(68,3,7,'4',1),(69,3,2,'4',2),(70,3,3,'4',3),(71,3,4,'4',4),(72,3,1,'6',1),(73,3,2,'6',2),(74,3,3,'6',3),(75,3,4,'6',4),(76,4,1,'2',1),(77,4,0,'2',2),(78,4,0,'2',3),(79,4,0,'2',4),(80,4,0,'2',5),(81,4,2,'2',1),(82,4,0,'2',2),(83,4,0,'2',3),(84,4,0,'2',4),(85,4,0,'2',5),(86,4,1,'2',1),(87,4,3,'2',2),(88,4,2,'2',3),(89,4,5,'2',4),(90,4,7,'2',5),(91,4,4,'3',1),(92,4,6,'3',2),(93,4,2,'3',3),(94,4,8,'3',4),(95,4,0,'3',5);
/*!40000 ALTER TABLE `class_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_teacher`
--

DROP TABLE IF EXISTS `class_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_teacher` (
  `clas_teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`clas_teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_teacher`
--

LOCK TABLES `class_teacher` WRITE;
/*!40000 ALTER TABLE `class_teacher` DISABLE KEYS */;
/*!40000 ALTER TABLE `class_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,NULL,NULL,'K57'),(2,NULL,NULL,'K58');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `department_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `website` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lesson` (
  `lesson_id` int(11) NOT NULL AUTO_INCREMENT,
  `lesson` int(11) DEFAULT NULL,
  PRIMARY KEY (`lesson_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson`
--

LOCK TABLES `lesson` WRITE;
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `majoring`
--

DROP TABLE IF EXISTS `majoring`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `majoring` (
  `majoring_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`majoring_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `majoring`
--

LOCK TABLES `majoring` WRITE;
/*!40000 ALTER TABLE `majoring` DISABLE KEYS */;
/*!40000 ALTER TABLE `majoring` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mark`
--

DROP TABLE IF EXISTS `mark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mark` (
  `mark_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `mark` float DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`mark_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mark`
--

LOCK TABLES `mark` WRITE;
/*!40000 ALTER TABLE `mark` DISABLE KEYS */;
INSERT INTO `mark` VALUES (1,1,1,1,1,7.5,'2023-05-02'),(2,1,1,1,2,8,'2023-05-02'),(3,1,1,1,3,9,'2023-05-02'),(4,1,1,1,2,4.5,'2023-05-02'),(5,1,2,1,1,5.5,'1970-01-01'),(6,1,2,1,2,5,'2023-05-02'),(7,1,1,1,1,5,'2023-05-17'),(8,1,1,1,1,7.25,'2023-05-17'),(9,1,1,2,2,9,NULL),(10,1,2,1,3,7.5,'2023-05-02'),(11,2,2,1,1,8,NULL),(12,1,1,2,1,7,'2023-05-17');
/*!40000 ALTER TABLE `mark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result` (
  `result_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_year_score` float DEFAULT NULL,
  `second_year_score` float DEFAULT NULL,
  `third_year_score` float DEFAULT NULL,
  `fourth_year_score` float DEFAULT NULL,
  `fifth_year_score` float DEFAULT NULL,
  `rank_result` int(11) DEFAULT NULL,
  `gap` float DEFAULT NULL,
  PRIMARY KEY (`result_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule_exam`
--

DROP TABLE IF EXISTS `schedule_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule_exam` (
  `schedule_exam_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `day` date DEFAULT NULL,
  `lesson` int(11) DEFAULT NULL,
  `address` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`schedule_exam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_exam`
--

LOCK TABLES `schedule_exam` WRITE;
/*!40000 ALTER TABLE `schedule_exam` DISABLE KEYS */;
INSERT INTO `schedule_exam` VALUES (1,1,1,'2023-05-20',1,'307a4'),(2,1,3,'2023-05-05',2,'105a3'),(3,2,5,'2023-05-25',3,'108a1');
/*!40000 ALTER TABLE `schedule_exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school`
--

DROP TABLE IF EXISTS `school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `school` (
  `school_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `website` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`school_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school`
--

LOCK TABLES `school` WRITE;
/*!40000 ALTER TABLE `school` DISABLE KEYS */;
INSERT INTO `school` VALUES (1,'transport and communicate','utc','utc.@gmail.com','0240650123','CauGiay','utc.com');
/*!40000 ALTER TABLE `school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school_year`
--

DROP TABLE IF EXISTS `school_year`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `school_year` (
  `year_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`year_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school_year`
--

LOCK TABLES `school_year` WRITE;
/*!40000 ALTER TABLE `school_year` DISABLE KEYS */;
/*!40000 ALTER TABLE `school_year` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semester` (
  `semester_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `main` int(11) DEFAULT NULL,
  `extra_evening` int(11) DEFAULT NULL,
  `extra_summer` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`semester_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES (1,'hoc ky 1',1,NULL,NULL,NULL),(2,'hoc ky 2',2,NULL,NULL,NULL);
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `code` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `teacher_major_id` int(11) DEFAULT NULL,
  `class_main` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `address` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Hoàng','Minh Đức','1605',1,'CNTT',1,'2023-05-15','duchm1605@gmail.com','0394501234','Bắc Giang',0),(2,'Nguyễn','Ngọc Linh','1606',1,'CNTT',0,'2023-05-10','linhkute02@gmail.com','0394501235','Hà Nội',0),(3,'Ngô','Văn Long','1607',NULL,NULL,1,'2023-04-25','datht@gmail.com','0394501234','Hà Nội',1),(4,'Ngô','Thu Hiền','1608',NULL,NULL,0,'2023-04-26','hiennt12@gmail.com','0394501234','Hà Nội',0),(5,'Nguyễn','Hương','1609',NULL,NULL,0,'2023-04-04','huong01@gmail.com','0394501234','Hà Nội',0),(36,'Hoàng','Hải','1610',NULL,NULL,1,'2023-04-04','haihv16@gmail.com','0123456789','Hà Nội',0),(39,'nguyen','lan','1612',NULL,NULL,1,'2023-04-04','duchm@gmail.com','0394501234','HN',1),(41,'nguyen','ninh','1614',NULL,NULL,0,'2023-04-04','duchm@gmail.com','0394501234','HN',0),(44,'nguyen','anh','1711',NULL,NULL,0,'2023-04-04','duchm@gmail.com','0394501234','HN',0),(50,'nguyen','dao','1712',NULL,NULL,0,'2023-04-04','duchm@gmail.com','0394501234','HN',1),(51,'nguyen','hang','1713',NULL,NULL,0,'2023-04-04','duchm@gmail.com','0394501234','HN',0),(52,'nguyen','chinh','1714',NULL,NULL,0,'2023-04-04','duchm@gmail.com','0394501234','HN',1),(53,'nguyen','oanh','1715',NULL,NULL,1,'2023-04-04','duchm@gmail.com','0394501234','HN',0),(54,'nguyen','hien','1716',NULL,NULL,0,'2023-04-04','duchm@gmail.com','0394501234','HN',0),(57,'Hoàng','Linh','1605',NULL,NULL,1,'2023-05-10','abc@gmail.com','0394501235','Hà Nội',1),(58,'Hoàng','Đức','1605',NULL,NULL,1,'2023-06-17','abc@gmail.com','0123456789','Bắc Giang',1),(59,'Ngô','Hậu','1605',NULL,NULL,0,'2023-05-16','abc@gmail.com','0123456789','Hà Nội',0),(60,'Hoàng','Linh','1605',NULL,NULL,1,'2023-05-11','abc@gmail.com','0123456789','Bac Giang',1),(61,'Nguyễn     ','Linh','1605',NULL,NULL,0,'2023-05-05','linhnt1605@gmail.com','0123456789','Hà Nội',0),(62,'Ngô','Thành','1701',NULL,NULL,1,'2023-05-03','thanhnv1701@gmail.com','0123456789','Hải Dương',1),(63,'Ngô','Lan','1702',NULL,NULL,0,'2023-05-02','lannt1702@gmail.com','0123456789','Hà Nội',0),(64,'Hoàng','Linh','1605',NULL,NULL,1,'2023-05-15','abc1@gmail.com','0123456789','Hà Nội',1),(65,'Trần','Linh','1605',NULL,NULL,0,'2023-05-07','linh1605@gmail.com','0394501234','HN',0);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `subject_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `number_credits` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'toan hoc','toan1',3,1,'Lap trinh java co ban'),(2,'vat ly','ly1',3,1,'Thiet ke giao dien voi angular'),(3,'hoa hoc','hoa1',3,1,'abc'),(4,'sinh hoc','sinh1',3,1,'toan cao cap'),(5,'lich su','su1',3,1,'abc'),(6,'dia ly','dia1',3,1,'adf'),(7,'van hoc','van1',3,1,'ad'),(8,'ngoai ngu','anh1',3,1,'ap'),(9,'cong dan','cd1',3,1,'adc');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject_lesson`
--

DROP TABLE IF EXISTS `subject_lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject_lesson` (
  `subject_lesson_id` int(11) NOT NULL AUTO_INCREMENT,
  `lesson_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`subject_lesson_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject_lesson`
--

LOCK TABLES `subject_lesson` WRITE;
/*!40000 ALTER TABLE `subject_lesson` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject_lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject_semester`
--

DROP TABLE IF EXISTS `subject_semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject_semester` (
  `subject_semester_id` int(11) NOT NULL AUTO_INCREMENT,
  `semester_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`subject_semester_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject_semester`
--

LOCK TABLES `subject_semester` WRITE;
/*!40000 ALTER TABLE `subject_semester` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject_semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(250) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'student_management'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-29 17:45:57
