-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: proj
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company`
(
    `id`          bigint       NOT NULL,
    `code`        varchar(255) NOT NULL,
    `countryId`   bigint       NOT NULL,
    `name`        varchar(255) NOT NULL,
    `sample`      bit(1)       NOT NULL,
    `type`        enum('IT_PRODUCT_BASE','IT_PROJECT_BASE','OTHERS') NOT NULL,
    `createdById` bigint DEFAULT NULL,
    `createdDate` datetime(6) DEFAULT NULL,
    `updatedById` bigint DEFAULT NULL,
    `updatedDate` datetime(6) DEFAULT NULL,
    `active`      bit(1)       NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKck8wy9n707u5ictdeeg36f3na` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK
TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `company_seq`
--

DROP TABLE IF EXISTS `company_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_seq`
--

LOCK
TABLES `company_seq` WRITE;
/*!40000 ALTER TABLE `company_seq` DISABLE KEYS */;
INSERT INTO `company_seq`
VALUES (1);
/*!40000 ALTER TABLE `company_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `companycalendar`
--

DROP TABLE IF EXISTS `companycalendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `companycalendar`
(
    `id`          bigint       NOT NULL,
    `companyId`   bigint       NOT NULL,
    `endDate`     date         NOT NULL,
    `eventName`   varchar(255) NOT NULL,
    `eventType`   enum('COMPANY_EVENT','HOLIDAY','MEETING','WORKDAY') NOT NULL,
    `recurring`   bit(1)       NOT NULL,
    `startDate`   date         NOT NULL,
    `createdById` bigint DEFAULT NULL,
    `createdDate` datetime(6) DEFAULT NULL,
    `updatedById` bigint DEFAULT NULL,
    `updatedDate` datetime(6) DEFAULT NULL,
    `active`      bit(1)       NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companycalendar`
--

LOCK
TABLES `companycalendar` WRITE;
/*!40000 ALTER TABLE `companycalendar` DISABLE KEYS */;
/*!40000 ALTER TABLE `companycalendar` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `companycalendar_seq`
--

DROP TABLE IF EXISTS `companycalendar_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `companycalendar_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companycalendar_seq`
--

LOCK
TABLES `companycalendar_seq` WRITE;
/*!40000 ALTER TABLE `companycalendar_seq` DISABLE KEYS */;
INSERT INTO `companycalendar_seq`
VALUES (1);
/*!40000 ALTER TABLE `companycalendar_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `companyweekend`
--

DROP TABLE IF EXISTS `companyweekend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `companyweekend`
(
    `id`          bigint NOT NULL,
    `companyId`   bigint NOT NULL,
    `day`         enum('FRIDAY','MONDAY','SATURDAY','SUNDAY','THURSDAY','TUESDAY','WEDNESDAY') NOT NULL,
    `createdById` bigint DEFAULT NULL,
    `createdDate` datetime(6) DEFAULT NULL,
    `updatedById` bigint DEFAULT NULL,
    `updatedDate` datetime(6) DEFAULT NULL,
    `active`      bit(1) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companyweekend`
--

LOCK
TABLES `companyweekend` WRITE;
/*!40000 ALTER TABLE `companyweekend` DISABLE KEYS */;
/*!40000 ALTER TABLE `companyweekend` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `companyweekend_seq`
--

DROP TABLE IF EXISTS `companyweekend_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `companyweekend_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companyweekend_seq`
--

LOCK
TABLES `companyweekend_seq` WRITE;
/*!40000 ALTER TABLE `companyweekend_seq` DISABLE KEYS */;
INSERT INTO `companyweekend_seq`
VALUES (1);
/*!40000 ALTER TABLE `companyweekend_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `companyworkinghour`
--

DROP TABLE IF EXISTS `companyworkinghour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `companyworkinghour`
(
    `id`          bigint       NOT NULL,
    `companyId`   bigint       NOT NULL,
    `dayOfWeek`   enum('FRIDAY','MONDAY','SATURDAY','SUNDAY','THURSDAY','TUESDAY','WEDNESDAY') DEFAULT NULL,
    `description` varchar(255) NOT NULL,
    `endDate`     date   DEFAULT NULL,
    `eventDate`   date   DEFAULT NULL,
    `minutes`     int          NOT NULL,
    `recurring`   bit(1)       NOT NULL,
    `scope`       enum('DATE_RANGE','DEFAULT','SPECIFIC_DATE','WEEK_DAY') NOT NULL,
    `startDate`   date   DEFAULT NULL,
    `createdById` bigint DEFAULT NULL,
    `createdDate` datetime(6) DEFAULT NULL,
    `updatedById` bigint DEFAULT NULL,
    `updatedDate` datetime(6) DEFAULT NULL,
    `active`      bit(1)       NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companyworkinghour`
--

LOCK
TABLES `companyworkinghour` WRITE;
/*!40000 ALTER TABLE `companyworkinghour` DISABLE KEYS */;
/*!40000 ALTER TABLE `companyworkinghour` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `companyworkinghour_seq`
--

DROP TABLE IF EXISTS `companyworkinghour_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `companyworkinghour_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companyworkinghour_seq`
--

LOCK
TABLES `companyworkinghour_seq` WRITE;
/*!40000 ALTER TABLE `companyworkinghour_seq` DISABLE KEYS */;
INSERT INTO `companyworkinghour_seq`
VALUES (1);
/*!40000 ALTER TABLE `companyworkinghour_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `component`
--

DROP TABLE IF EXISTS `component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `component`
(
    `id`          bigint       NOT NULL,
    `companyId`   bigint       NOT NULL,
    `name`        varchar(255) NOT NULL,
    `createdById` bigint DEFAULT NULL,
    `createdDate` datetime(6) DEFAULT NULL,
    `updatedById` bigint DEFAULT NULL,
    `updatedDate` datetime(6) DEFAULT NULL,
    `active`      bit(1)       NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK3v8enk8my1efr5htx5xac083v` (`companyId`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `component`
--

LOCK
TABLES `component` WRITE;
/*!40000 ALTER TABLE `component` DISABLE KEYS */;
/*!40000 ALTER TABLE `component` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `component_seq`
--

DROP TABLE IF EXISTS `component_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `component_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `component_seq`
--

LOCK
TABLES `component_seq` WRITE;
/*!40000 ALTER TABLE `component_seq` DISABLE KEYS */;
INSERT INTO `component_seq`
VALUES (1);
/*!40000 ALTER TABLE `component_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `contactus`
--

DROP TABLE IF EXISTS `contactus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contactus`
(
    `id`          bigint       NOT NULL,
    `addressed`   bit(1)       NOT NULL,
    `companyId`   bigint DEFAULT NULL,
    `details`     varchar(255) NOT NULL,
    `email`       varchar(255) NOT NULL,
    `name`        varchar(255) NOT NULL,
    `subject`     varchar(255) NOT NULL,
    `createdById` bigint DEFAULT NULL,
    `createdDate` datetime(6) DEFAULT NULL,
    `updatedById` bigint DEFAULT NULL,
    `updatedDate` datetime(6) DEFAULT NULL,
    `active`      bit(1)       NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contactus`
--

LOCK
TABLES `contactus` WRITE;
/*!40000 ALTER TABLE `contactus` DISABLE KEYS */;
/*!40000 ALTER TABLE `contactus` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `contactus_seq`
--

DROP TABLE IF EXISTS `contactus_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contactus_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contactus_seq`
--

LOCK
TABLES `contactus_seq` WRITE;
/*!40000 ALTER TABLE `contactus_seq` DISABLE KEYS */;
INSERT INTO `contactus_seq`
VALUES (1);
/*!40000 ALTER TABLE `contactus_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country`
(
    `id`           bigint        NOT NULL,
    `countryCode`  varchar(255)  NOT NULL,
    `countryCode3` varchar(255)  NOT NULL,
    `currency`     varchar(255) DEFAULT NULL,
    `flag`         varchar(255) DEFAULT NULL,
    `language`     varchar(255) DEFAULT NULL,
    `name`         varchar(255)  NOT NULL,
    `phoneCode`    varchar(5000) NOT NULL,
    `region`       varchar(255) DEFAULT NULL,
    `subRegion`    varchar(255) DEFAULT NULL,
    `createdById`  bigint       DEFAULT NULL,
    `createdDate`  datetime(6) DEFAULT NULL,
    `updatedById`  bigint       DEFAULT NULL,
    `updatedDate`  datetime(6) DEFAULT NULL,
    `active`       bit(1)        NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKmlnoip7x4goxmg4xppnygdm30` (`countryCode`),
    UNIQUE KEY `UKhx91srt1eo1ou2xswqskos82f` (`countryCode3`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK
TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `country_seq`
--

DROP TABLE IF EXISTS `country_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country_seq`
--

LOCK
TABLES `country_seq` WRITE;
/*!40000 ALTER TABLE `country_seq` DISABLE KEYS */;
INSERT INTO `country_seq`
VALUES (1);
/*!40000 ALTER TABLE `country_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `designation`
--

DROP TABLE IF EXISTS `designation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `designation`
(
    `id`          bigint       NOT NULL,
    `companyId`   bigint       NOT NULL,
    `name`        varchar(255) NOT NULL,
    `roleId`      bigint DEFAULT NULL,
    `createdById` bigint DEFAULT NULL,
    `createdDate` datetime(6) DEFAULT NULL,
    `updatedById` bigint DEFAULT NULL,
    `updatedDate` datetime(6) DEFAULT NULL,
    `active`      bit(1)       NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK6679dyu7jtarys6ts6xuc4j5d` (`companyId`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `designation`
--

LOCK
TABLES `designation` WRITE;
/*!40000 ALTER TABLE `designation` DISABLE KEYS */;
/*!40000 ALTER TABLE `designation` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `designation_seq`
--

DROP TABLE IF EXISTS `designation_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `designation_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `designation_seq`
--

LOCK
TABLES `designation_seq` WRITE;
/*!40000 ALTER TABLE `designation_seq` DISABLE KEYS */;
INSERT INTO `designation_seq`
VALUES (1);
/*!40000 ALTER TABLE `designation_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `entitydetail`
--

DROP TABLE IF EXISTS `entitydetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entitydetail`
(
    `id`          bigint NOT NULL,
    `detailType`  enum('ATTACHED_FILE','URL','DISCUSSION') NOT NULL,
    `details`     varchar(255) DEFAULT NULL,
    `entityId`    bigint NOT NULL,
    `entityType`  enum('COMPANY','PRODUCT','RELEASE','RESOURCE','CONCERN') NOT NULL,
    `link`        varchar(255) DEFAULT NULL,
    `name`        varchar(255) DEFAULT NULL,
    `createdById` bigint       DEFAULT NULL,
    `createdDate` datetime(6) DEFAULT NULL,
    `updatedById` bigint       DEFAULT NULL,
    `updatedDate` datetime(6) DEFAULT NULL,
    `active`      bit(1) NOT NULL,
    PRIMARY KEY (`id`),
    KEY           `idx_EntityDetail` (`entityType`,`entityId`,`detailType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entitydetail`
--

LOCK
TABLES `entitydetail` WRITE;
/*!40000 ALTER TABLE `entitydetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `entitydetail` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `entitydetail_seq`
--

DROP TABLE IF EXISTS `entitydetail_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entitydetail_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entitydetail_seq`
--

LOCK
TABLES `entitydetail_seq` WRITE;
/*!40000 ALTER TABLE `entitydetail_seq` DISABLE KEYS */;
INSERT INTO `entitydetail_seq`
VALUES (1);
/*!40000 ALTER TABLE `entitydetail_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `epic`
--

DROP TABLE IF EXISTS `epic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `epic`
(
    `id`                 bigint       NOT NULL,
    `code`               varchar(255) NOT NULL,
    `comments`           varchar(255) DEFAULT NULL,
    `componentId`        bigint       DEFAULT NULL,
    `dependOnEpicId`     bigint       DEFAULT NULL,
    `details`            varchar(255) DEFAULT NULL,
    `endDate`            date         DEFAULT NULL,
    `forcefullyAdded`    bit(1)       NOT NULL,
    `priorityId`         bigint       DEFAULT NULL,
    `productId`          bigint       NOT NULL,
    `raisedByResourceId` bigint       DEFAULT NULL,
    `releaseId`          bigint       DEFAULT NULL,
    `replicate`          bit(1)       NOT NULL,
    `replicatedById`     bigint       DEFAULT NULL,
    `requiredBy`         date         DEFAULT NULL,
    `risks`              varchar(255) DEFAULT NULL,
    `startDate`          date         DEFAULT NULL,
    `status`             enum('DELETED','OPEN','REOPEN','RESOLVED') NOT NULL,
    `title`              varchar(255) NOT NULL,
    `valueGain` double NOT NULL,
    `createdById`        bigint       DEFAULT NULL,
    `createdDate`        datetime(6) DEFAULT NULL,
    `updatedById`        bigint       DEFAULT NULL,
    `updatedDate`        datetime(6) DEFAULT NULL,
    `active`             bit(1)       NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `epic`
--

LOCK
TABLES `epic` WRITE;
/*!40000 ALTER TABLE `epic` DISABLE KEYS */;
/*!40000 ALTER TABLE `epic` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `epic_seq`
--

DROP TABLE IF EXISTS `epic_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `epic_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `epic_seq`
--

LOCK
TABLES `epic_seq` WRITE;
/*!40000 ALTER TABLE `epic_seq` DISABLE KEYS */;
INSERT INTO `epic_seq`
VALUES (1);
/*!40000 ALTER TABLE `epic_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `epicassignment`
--

DROP TABLE IF EXISTS `epicassignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `epicassignment`
(
    `id`                   bigint NOT NULL,
    `changedManually`      bit(1) NOT NULL,
    `epicId`               bigint NOT NULL,
    `estimate`             int    NOT NULL,
    `expectedDeliveryDate` date   DEFAULT NULL,
    `releaseId`            bigint NOT NULL,
    `resourceId`           bigint NOT NULL,
    `roleId`               bigint NOT NULL,
    `status`               enum('COMPLETED','ON_HOLD','OPEN','OVERDUE','STARTED') NOT NULL,
    `createdById`          bigint DEFAULT NULL,
    `createdDate`          datetime(6) DEFAULT NULL,
    `updatedById`          bigint DEFAULT NULL,
    `updatedDate`          datetime(6) DEFAULT NULL,
    `active`               bit(1) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK970c09y4wca18w7up7i1bq20r` (`epicId`,`resourceId`,`roleId`,`releaseId`,`active`),
    KEY                    `idx_epic_assignment_resource` (`resourceId`),
    KEY                    `idx_epic_assignment_release` (`releaseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `epicassignment`
--

LOCK
TABLES `epicassignment` WRITE;
/*!40000 ALTER TABLE `epicassignment` DISABLE KEYS */;
/*!40000 ALTER TABLE `epicassignment` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `epicassignment_seq`
--

DROP TABLE IF EXISTS `epicassignment_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `epicassignment_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `epicassignment_seq`
--

LOCK
TABLES `epicassignment_seq` WRITE;
/*!40000 ALTER TABLE `epicassignment_seq` DISABLE KEYS */;
INSERT INTO `epicassignment_seq`
VALUES (1);
/*!40000 ALTER TABLE `epicassignment_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `epicdetail`
--

DROP TABLE IF EXISTS `epicdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `epicdetail`
(
    `id`          bigint NOT NULL,
    `detailType`  enum('ATTACHED_FILE','COMMENT','REFERENCE','URL') NOT NULL,
    `details`     varchar(255) DEFAULT NULL,
    `epicId`      bigint NOT NULL,
    `link`        varchar(255) DEFAULT NULL,
    `name`        varchar(255) DEFAULT NULL,
    `createdById` bigint       DEFAULT NULL,
    `createdDate` datetime(6) DEFAULT NULL,
    `updatedById` bigint       DEFAULT NULL,
    `updatedDate` datetime(6) DEFAULT NULL,
    `active`      bit(1) NOT NULL,
    PRIMARY KEY (`id`),
    KEY           `idx_EpicDetail` (`epicId`,`detailType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `epicdetail`
--

LOCK
TABLES `epicdetail` WRITE;
/*!40000 ALTER TABLE `epicdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `epicdetail` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `epicdetail_seq`
--

DROP TABLE IF EXISTS `epicdetail_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `epicdetail_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `epicdetail_seq`
--

LOCK
TABLES `epicdetail_seq` WRITE;
/*!40000 ALTER TABLE `epicdetail_seq` DISABLE KEYS */;
INSERT INTO `epicdetail_seq`
VALUES (1);
/*!40000 ALTER TABLE `epicdetail_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `epicestimate`
--

DROP TABLE IF EXISTS `epicestimate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `epicestimate`
(
    `id`          bigint NOT NULL,
    `epicId`      bigint NOT NULL,
    `estimate`    int    NOT NULL,
    `resources`   int    NOT NULL,
    `roleId`      bigint NOT NULL,
    `createdById` bigint DEFAULT NULL,
    `createdDate` datetime(6) DEFAULT NULL,
    `updatedById` bigint DEFAULT NULL,
    `updatedDate` datetime(6) DEFAULT NULL,
    `active`      bit(1) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKntuvtj33cvqib69hl0ymq3670` (`epicId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `epicestimate`
--

LOCK
TABLES `epicestimate` WRITE;
/*!40000 ALTER TABLE `epicestimate` DISABLE KEYS */;
/*!40000 ALTER TABLE `epicestimate` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `epicestimate_seq`
--

DROP TABLE IF EXISTS `epicestimate_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `epicestimate_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `epicestimate_seq`
--

LOCK
TABLES `epicestimate_seq` WRITE;
/*!40000 ALTER TABLE `epicestimate_seq` DISABLE KEYS */;
INSERT INTO `epicestimate_seq`
VALUES (1);
/*!40000 ALTER TABLE `epicestimate_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `epiclink`
--

DROP TABLE IF EXISTS `epiclink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `epiclink`
(
    `id`           bigint NOT NULL,
    `details`      varchar(255) DEFAULT NULL,
    `epicId`       bigint NOT NULL,
    `linkType`     enum('DEPEND_ON','RELATED_TO') NOT NULL,
    `linkedEpicId` bigint NOT NULL,
    `createdById`  bigint       DEFAULT NULL,
    `createdDate`  datetime(6) DEFAULT NULL,
    `updatedById`  bigint       DEFAULT NULL,
    `updatedDate`  datetime(6) DEFAULT NULL,
    `active`       bit(1) NOT NULL,
    PRIMARY KEY (`id`),
    KEY            `idx_EpicLink` (`epicId`,`linkType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `epiclink`
--

LOCK
TABLES `epiclink` WRITE;
/*!40000 ALTER TABLE `epiclink` DISABLE KEYS */;
/*!40000 ALTER TABLE `epiclink` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `epiclink_seq`
--

DROP TABLE IF EXISTS `epiclink_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `epiclink_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `epiclink_seq`
--

LOCK
TABLES `epiclink_seq` WRITE;
/*!40000 ALTER TABLE `epiclink_seq` DISABLE KEYS */;
INSERT INTO `epiclink_seq`
VALUES (1);
/*!40000 ALTER TABLE `epiclink_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `priority`
--

DROP TABLE IF EXISTS `priority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `priority`
(
    `id`            bigint       NOT NULL,
    `companyId`     bigint       NOT NULL,
    `name`          varchar(255) NOT NULL,
    `priorityLevel` int          NOT NULL,
    `createdById`   bigint DEFAULT NULL,
    `createdDate`   datetime(6) DEFAULT NULL,
    `updatedById`   bigint DEFAULT NULL,
    `updatedDate`   datetime(6) DEFAULT NULL,
    `active`        bit(1)       NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `priority`
--

LOCK
TABLES `priority` WRITE;
/*!40000 ALTER TABLE `priority` DISABLE KEYS */;
/*!40000 ALTER TABLE `priority` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `priority_seq`
--

DROP TABLE IF EXISTS `priority_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `priority_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `priority_seq`
--

LOCK
TABLES `priority_seq` WRITE;
/*!40000 ALTER TABLE `priority_seq` DISABLE KEYS */;
INSERT INTO `priority_seq`
VALUES (1);
/*!40000 ALTER TABLE `priority_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product`
(
    `id`                         bigint       NOT NULL,
    `code`                       varchar(255) NOT NULL,
    `companyId`                  bigint       NOT NULL,
    `endDate`                    date   DEFAULT NULL,
    `name`                       varchar(255) NOT NULL,
    `otherActivitiesPercentTime` int          NOT NULL,
    `productManagerId`           bigint       NOT NULL,
    `releaseIteration`           enum('ANNUAL','BI_MONTHLY','BI_WEEKLY','MONTHLY','QUARTERLY','SEMI_ANNUAL','TRI_WEEKLY','WEEKLY') NOT NULL,
    `startDate`                  date         NOT NULL,
    `createdById`                bigint DEFAULT NULL,
    `createdDate`                datetime(6) DEFAULT NULL,
    `updatedById`                bigint DEFAULT NULL,
    `updatedDate`                datetime(6) DEFAULT NULL,
    `active`                     bit(1)       NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKeqpqkxe0bahds4i9h7vok85gh` (`companyId`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK
TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `product_seq`
--

DROP TABLE IF EXISTS `product_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_seq`
--

LOCK
TABLES `product_seq` WRITE;
/*!40000 ALTER TABLE `product_seq` DISABLE KEYS */;
INSERT INTO `product_seq`
VALUES (1);
/*!40000 ALTER TABLE `product_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `productresource`
--

DROP TABLE IF EXISTS `productresource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productresource`
(
    `id`                       bigint NOT NULL,
    `participationPercentTime` int    NOT NULL,
    `productId`                bigint NOT NULL,
    `resourceId`               bigint NOT NULL,
    `roleId`                   bigint NOT NULL,
    `createdById`              bigint DEFAULT NULL,
    `createdDate`              datetime(6) DEFAULT NULL,
    `updatedById`              bigint DEFAULT NULL,
    `updatedDate`              datetime(6) DEFAULT NULL,
    `active`                   bit(1) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productresource`
--

LOCK
TABLES `productresource` WRITE;
/*!40000 ALTER TABLE `productresource` DISABLE KEYS */;
/*!40000 ALTER TABLE `productresource` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `productresource_seq`
--

DROP TABLE IF EXISTS `productresource_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productresource_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productresource_seq`
--

LOCK
TABLES `productresource_seq` WRITE;
/*!40000 ALTER TABLE `productresource_seq` DISABLE KEYS */;
INSERT INTO `productresource_seq`
VALUES (1);
/*!40000 ALTER TABLE `productresource_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `releaseepichistory`
--

DROP TABLE IF EXISTS `releaseepichistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `releaseepichistory`
(
    `id`              bigint NOT NULL,
    `comments`        varchar(255) DEFAULT NULL,
    `epicId`          bigint NOT NULL,
    `forcefullyAdded` bit(1) NOT NULL,
    `releaseId`       bigint NOT NULL,
    `risks`           varchar(255) DEFAULT NULL,
    `createdById`     bigint       DEFAULT NULL,
    `createdDate`     datetime(6) DEFAULT NULL,
    `updatedById`     bigint       DEFAULT NULL,
    `updatedDate`     datetime(6) DEFAULT NULL,
    `active`          bit(1) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKpq43n4c78y1k2vxxqg5u8885j` (`releaseId`,`epicId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `releaseepichistory`
--

LOCK
TABLES `releaseepichistory` WRITE;
/*!40000 ALTER TABLE `releaseepichistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `releaseepichistory` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `releaseepichistory_seq`
--

DROP TABLE IF EXISTS `releaseepichistory_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `releaseepichistory_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `releaseepichistory_seq`
--

LOCK
TABLES `releaseepichistory_seq` WRITE;
/*!40000 ALTER TABLE `releaseepichistory_seq` DISABLE KEYS */;
INSERT INTO `releaseepichistory_seq`
VALUES (1);
/*!40000 ALTER TABLE `releaseepichistory_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `releaseresource`
--

DROP TABLE IF EXISTS `releaseresource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `releaseresource`
(
    `id`                       bigint NOT NULL,
    `participationPercentTime` int    NOT NULL,
    `releaseId`                bigint NOT NULL,
    `resourceId`               bigint NOT NULL,
    `roleId`                   bigint NOT NULL,
    `createdById`              bigint DEFAULT NULL,
    `createdDate`              datetime(6) DEFAULT NULL,
    `updatedById`              bigint DEFAULT NULL,
    `updatedDate`              datetime(6) DEFAULT NULL,
    `active`                   bit(1) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `releaseresource`
--

LOCK
TABLES `releaseresource` WRITE;
/*!40000 ALTER TABLE `releaseresource` DISABLE KEYS */;
/*!40000 ALTER TABLE `releaseresource` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `releaseresource_seq`
--

DROP TABLE IF EXISTS `releaseresource_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `releaseresource_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `releaseresource_seq`
--

LOCK
TABLES `releaseresource_seq` WRITE;
/*!40000 ALTER TABLE `releaseresource_seq` DISABLE KEYS */;
INSERT INTO `releaseresource_seq`
VALUES (1);
/*!40000 ALTER TABLE `releaseresource_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `releases`
--

DROP TABLE IF EXISTS `releases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `releases`
(
    `id`          bigint       NOT NULL,
    `endDate`     date         NOT NULL,
    `name`        varchar(255) NOT NULL,
    `productId`   bigint       NOT NULL,
    `startDate`   date         NOT NULL,
    `status`      enum('COMPLETED','OVERDUE','PLANNED','STARTED','UNPLANNED') NOT NULL,
    `version`     int          NOT NULL,
    `workingDays` int          NOT NULL,
    `createdById` bigint DEFAULT NULL,
    `createdDate` datetime(6) DEFAULT NULL,
    `updatedById` bigint DEFAULT NULL,
    `updatedDate` datetime(6) DEFAULT NULL,
    `active`      bit(1)       NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKp86c61ca4srcn7qk4wnv1ari0` (`productId`,`name`,`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `releases`
--

LOCK
TABLES `releases` WRITE;
/*!40000 ALTER TABLE `releases` DISABLE KEYS */;
/*!40000 ALTER TABLE `releases` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `releases_seq`
--

DROP TABLE IF EXISTS `releases_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `releases_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `releases_seq`
--

LOCK
TABLES `releases_seq` WRITE;
/*!40000 ALTER TABLE `releases_seq` DISABLE KEYS */;
INSERT INTO `releases_seq`
VALUES (1);
/*!40000 ALTER TABLE `releases_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `releasestatus`
--

DROP TABLE IF EXISTS `releasestatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `releasestatus`
(
    `id`          bigint       NOT NULL,
    `code`        enum('COMPLETED','OVERDUE','PLANNED','STARTED','UNPLANNED') NOT NULL,
    `name`        varchar(255) NOT NULL,
    `createdById` bigint DEFAULT NULL,
    `createdDate` datetime(6) DEFAULT NULL,
    `updatedById` bigint DEFAULT NULL,
    `updatedDate` datetime(6) DEFAULT NULL,
    `active`      bit(1)       NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKjfjhkhw9odmbf858sayem86uu` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `releasestatus`
--

LOCK
TABLES `releasestatus` WRITE;
/*!40000 ALTER TABLE `releasestatus` DISABLE KEYS */;
/*!40000 ALTER TABLE `releasestatus` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `releasestatus_seq`
--

DROP TABLE IF EXISTS `releasestatus_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `releasestatus_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `releasestatus_seq`
--

LOCK
TABLES `releasestatus_seq` WRITE;
/*!40000 ALTER TABLE `releasestatus_seq` DISABLE KEYS */;
INSERT INTO `releasestatus_seq`
VALUES (1);
/*!40000 ALTER TABLE `releasestatus_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `releaseworkingday`
--

DROP TABLE IF EXISTS `releaseworkingday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `releaseworkingday`
(
    `id`          bigint NOT NULL,
    `minutes`     int    NOT NULL,
    `releaseId`   bigint NOT NULL,
    `workingDate` date   NOT NULL,
    `createdById` bigint DEFAULT NULL,
    `createdDate` datetime(6) DEFAULT NULL,
    `updatedById` bigint DEFAULT NULL,
    `updatedDate` datetime(6) DEFAULT NULL,
    `active`      bit(1) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK2ptpdqw8qeuuhikdi24p5ook9` (`releaseId`,`workingDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `releaseworkingday`
--

LOCK
TABLES `releaseworkingday` WRITE;
/*!40000 ALTER TABLE `releaseworkingday` DISABLE KEYS */;
/*!40000 ALTER TABLE `releaseworkingday` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `releaseworkingday_seq`
--

DROP TABLE IF EXISTS `releaseworkingday_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `releaseworkingday_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `releaseworkingday_seq`
--

LOCK
TABLES `releaseworkingday_seq` WRITE;
/*!40000 ALTER TABLE `releaseworkingday_seq` DISABLE KEYS */;
INSERT INTO `releaseworkingday_seq`
VALUES (1);
/*!40000 ALTER TABLE `releaseworkingday_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resource`
(
    `id`                 bigint       NOT NULL,
    `companyId`          bigint       NOT NULL,
    `countryId`          bigint       NOT NULL,
    `dateOfBirth`        date         DEFAULT NULL,
    `designationId`      bigint       DEFAULT NULL,
    `email`              varchar(255) NOT NULL,
    `individualCapacity` bit(1)       NOT NULL,
    `lastWorkingDate`    date         DEFAULT NULL,
    `isLead`             bit(1)       NOT NULL,
    `leadResourceId`     bigint       DEFAULT NULL,
    `mobileNumber`       varchar(255) DEFAULT NULL,
    `name`               varchar(255) NOT NULL,
    `password`           varchar(255) NOT NULL,
    `roleId`             bigint       DEFAULT NULL,
    `status`             enum('ACTIVE','INACTIVE') NOT NULL,
    `createdById`        bigint       DEFAULT NULL,
    `createdDate`        datetime(6) DEFAULT NULL,
    `updatedById`        bigint       DEFAULT NULL,
    `updatedDate`        datetime(6) DEFAULT NULL,
    `active`             bit(1)       NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKk23go6319a77mja35jikor6ja` (`companyId`,`email`,`active`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK
TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `resource_seq`
--

DROP TABLE IF EXISTS `resource_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resource_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_seq`
--

LOCK
TABLES `resource_seq` WRITE;
/*!40000 ALTER TABLE `resource_seq` DISABLE KEYS */;
INSERT INTO `resource_seq`
VALUES (1);
/*!40000 ALTER TABLE `resource_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `resourceleave`
--

DROP TABLE IF EXISTS `resourceleave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resourceleave`
(
    `id`          bigint NOT NULL,
    `approvedAt`  datetime(6) DEFAULT NULL,
    `approvedBy`  bigint       DEFAULT NULL,
    `days` double DEFAULT NULL,
    `endDate`     date   NOT NULL,
    `leaveType`   enum('ANNUAL_LEAVE','CASUAL_LEAVE','COMPENSATION_LEAVE','SHORT_LEAVE','SICK_LEAVE') NOT NULL,
    `reason`      varchar(255) DEFAULT NULL,
    `resourceId`  bigint NOT NULL,
    `startDate`   date   NOT NULL,
    `status`      enum('APPROVED','PENDING','REJECTED') NOT NULL,
    `createdById` bigint       DEFAULT NULL,
    `createdDate` datetime(6) DEFAULT NULL,
    `updatedById` bigint       DEFAULT NULL,
    `updatedDate` datetime(6) DEFAULT NULL,
    `active`      bit(1) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resourceleave`
--

LOCK
TABLES `resourceleave` WRITE;
/*!40000 ALTER TABLE `resourceleave` DISABLE KEYS */;
/*!40000 ALTER TABLE `resourceleave` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `resourceleave_seq`
--

DROP TABLE IF EXISTS `resourceleave_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resourceleave_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resourceleave_seq`
--

LOCK
TABLES `resourceleave_seq` WRITE;
/*!40000 ALTER TABLE `resourceleave_seq` DISABLE KEYS */;
INSERT INTO `resourceleave_seq`
VALUES (1);
/*!40000 ALTER TABLE `resourceleave_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role`
(
    `id`          bigint       NOT NULL,
    `code`        enum('ADMIN','HR','PM','TR') NOT NULL,
    `companyId`   bigint       NOT NULL,
    `name`        varchar(255) NOT NULL,
    `required`    bit(1)       NOT NULL,
    `systemRole`  bit(1)       NOT NULL,
    `createdById` bigint DEFAULT NULL,
    `createdDate` datetime(6) DEFAULT NULL,
    `updatedById` bigint DEFAULT NULL,
    `updatedDate` datetime(6) DEFAULT NULL,
    `active`      bit(1)       NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKfvr1gccfwfq7b3qov97kvt1x` (`companyId`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK
TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `role_seq`
--

DROP TABLE IF EXISTS `role_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_seq`
--

LOCK
TABLES `role_seq` WRITE;
/*!40000 ALTER TABLE `role_seq` DISABLE KEYS */;
INSERT INTO `role_seq`
VALUES (1);
/*!40000 ALTER TABLE `role_seq` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `timelogging`
--

DROP TABLE IF EXISTS `timelogging`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timelogging`
(
    `id`            bigint NOT NULL,
    `comments`      varchar(255) DEFAULT NULL,
    `epicId`        bigint NOT NULL,
    `loggedForDate` date         DEFAULT NULL,
    `minutes`       int    NOT NULL,
    `releaseId`     bigint NOT NULL,
    `resourceId`    bigint NOT NULL,
    `createdById`   bigint       DEFAULT NULL,
    `createdDate`   datetime(6) DEFAULT NULL,
    `updatedById`   bigint       DEFAULT NULL,
    `updatedDate`   datetime(6) DEFAULT NULL,
    `active`        bit(1) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timelogging`
--

LOCK
TABLES `timelogging` WRITE;
/*!40000 ALTER TABLE `timelogging` DISABLE KEYS */;
/*!40000 ALTER TABLE `timelogging` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `timelogging_seq`
--

DROP TABLE IF EXISTS `timelogging_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timelogging_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timelogging_seq`
--

LOCK
TABLES `timelogging_seq` WRITE;
/*!40000 ALTER TABLE `timelogging_seq` DISABLE KEYS */;
INSERT INTO `timelogging_seq`
VALUES (1);
/*!40000 ALTER TABLE `timelogging_seq` ENABLE KEYS */;
UNLOCK
TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
--
-- Table structure for table `epicreleaseconcern`
--

DROP TABLE IF EXISTS `epicreleaseconcern`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `epicreleaseconcern`
(
    `id`                   bigint       NOT NULL,
    `assignedToResourceId` bigint       NOT NULL,
    `epicId`               bigint       NOT NULL,
    `releaseId`            bigint       NOT NULL,
    `status`               enum('ANSWERED','CLOSED','OPEN','RESOLVED') NOT NULL,
    `title`                varchar(255) NOT NULL,
    `createdById`          bigint DEFAULT NULL,
    `createdDate`          datetime(6) DEFAULT NULL,
    `updatedById`          bigint DEFAULT NULL,
    `updatedDate`          datetime(6) DEFAULT NULL,
    `active`               bit(1)       NOT NULL,
    PRIMARY KEY (`id`),
    KEY                    `idx_EpicReleaseConcern_epicId` (`epicId`),
    KEY                    `idx_EpicReleaseConcern_releaseId` (`releaseId`),
    KEY                    `idx_EpicReleaseConcern_assignedToResourceId` (`assignedToResourceId`),
    KEY                    `idx_EpicReleaseConcern_createdById` (`createdById`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `epicreleaseconcern`
--

LOCK
TABLES `epicreleaseconcern` WRITE;
/*!40000 ALTER TABLE `epicreleaseconcern` DISABLE KEYS */;
/*!40000 ALTER TABLE `epicreleaseconcern` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `epicreleaseconcern_seq`
--

DROP TABLE IF EXISTS `epicreleaseconcern_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `epicreleaseconcern_seq`
(
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `epicreleaseconcern_seq`
--

LOCK
TABLES `epicreleaseconcern_seq` WRITE;
/*!40000 ALTER TABLE `epicreleaseconcern_seq` DISABLE KEYS */;
INSERT INTO `epicreleaseconcern_seq`
VALUES (1);
/*!40000 ALTER TABLE `epicreleaseconcern_seq` ENABLE KEYS */;
UNLOCK
TABLES;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-20 19:45:05
