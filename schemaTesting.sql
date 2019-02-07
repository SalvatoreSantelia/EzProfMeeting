CREATE DATABASE  IF NOT EXISTS `ezprofmeeting` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ezprofmeeting`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ezprofmeeting
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `ruolo` varchar(45) NOT NULL,
  PRIMARY KEY (`email`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `password_UNIQUE` (`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabella necessaria al Login';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('mia@email.it','miapassword2','studente'),('miaemail','miapassword','studente'),('prof@prof.it','profprofprof','professore');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messaggio`
--

DROP TABLE IF EXISTS `messaggio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messaggio` (
  `idMessaggio` int(11) NOT NULL AUTO_INCREMENT,
  `dataMessaggio` date NOT NULL,
  `testoMessaggio` varchar(45) NOT NULL,
  `idProfessore` int(11) DEFAULT NULL,
  `idStudente` int(11) DEFAULT NULL,
  `lato` varchar(45) DEFAULT NULL,
  `orarioMessaggio` time(6) DEFAULT NULL,
  PRIMARY KEY (`idMessaggio`),
  KEY `idProfessore_idx` (`idProfessore`),
  KEY `idStudente_idx` (`idStudente`),
  CONSTRAINT `idProfessore` FOREIGN KEY (`idProfessore`) REFERENCES `professore` (`idProfessore`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idStudente` FOREIGN KEY (`idStudente`) REFERENCES `studente` (`idStudente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messaggio`
--

LOCK TABLES `messaggio` WRITE;
/*!40000 ALTER TABLE `messaggio` DISABLE KEYS */;
INSERT INTO `messaggio` VALUES (1,'2019-12-12','ciao',2,2,'studente','12:12:24.000000'),(2,'2019-12-12','ciao',1,1,'professore','14:23:59.000000'),(3,'2019-11-10','hey',1,2,'stuente','17:18:35.000000'),(4,'2019-01-28','CIAOPROF',1,1,'studente','11:28:33.000000'),(5,'2019-01-28','CIAOPROF',1,1,'studente','15:37:20.000000'),(6,'2019-01-28','CIAOPROF',1,1,'studente','16:11:54.000000'),(7,'2019-01-28','CIAOPROF',1,1,'studente','16:58:57.000000'),(8,'2019-02-04','CIAOPROF',1,1,'studente','15:46:53.000000'),(9,'2019-02-04','CIAOPROF',1,1,'studente','15:47:35.000000'),(10,'2019-02-04','CIAOPROF',1,1,'studente','15:48:45.000000'),(11,'2019-02-04','CIAOPROF',1,1,'studente','16:06:08.000000'),(12,'2019-02-04','CIAOPROF',1,1,'studente','16:13:08.000000'),(13,'2019-02-04','CIAOPROF',1,1,'studente','16:15:58.000000'),(14,'2019-02-04','CIAOPROF',1,1,'studente','16:17:28.000000'),(15,'2019-02-04','CIAOPROF',1,1,'studente','16:22:07.000000'),(16,'2019-02-04','CIAOPROF',1,1,'studente','16:23:28.000000'),(17,'2019-02-04','CIAOPROF',1,1,'studente','16:26:23.000000'),(18,'2019-02-04','CIAOPROF',1,1,'studente','16:29:23.000000'),(19,'2019-02-04','CIAOPROF',1,1,'studente','16:37:00.000000'),(20,'2019-02-04','CIAOPROF',1,1,'studente','16:38:50.000000'),(21,'2019-02-04','CIAOPROF',1,1,'studente','16:44:40.000000'),(22,'2019-02-04','CIAOPROF',1,1,'studente','16:47:29.000000'),(23,'2019-02-04','CIAOPROF',1,1,'studente','17:24:21.000000'),(24,'2019-02-04','CIAOPROF',1,1,'studente','17:26:33.000000'),(25,'2019-02-04','CIAOPROF',1,1,'studente','17:29:03.000000'),(26,'2019-02-04','CIAOPROF',1,1,'studente','17:45:24.000000'),(27,'2019-02-04','CIAOPROF',1,1,'studente','18:20:01.000000'),(28,'2019-02-04','CIAOPROF',1,1,'studente','18:45:13.000000'),(29,'2019-02-04','CIAOPROF',1,1,'studente','18:55:23.000000'),(30,'2019-02-04','CIAOPROF',1,1,'studente','18:57:13.000000'),(31,'2019-02-04','CIAOPROF',1,1,'studente','19:13:36.000000'),(32,'2019-02-05','CIAOPROF',1,1,'studente','11:23:04.000000'),(33,'2019-02-05','CIAOPROF',1,1,'studente','16:49:44.000000'),(34,'2019-02-05','CIAOPROF',1,1,'studente','18:14:20.000000'),(35,'2019-02-05','CIAOPROF',1,1,'studente','18:14:21.000000'),(36,'2019-02-05','CIAOPROF',1,1,'studente','18:24:51.000000'),(37,'2019-02-06','CIAOPROF',1,1,'studente','13:18:06.000000'),(38,'2019-02-06','CIAOPROF',1,1,'studente','13:19:41.000000'),(39,'2019-02-06','CIAOPROF',1,1,'studente','13:22:22.000000'),(40,'2019-02-06','CIAOPROF',1,1,'studente','13:23:40.000000'),(41,'2019-02-06','CIAOPROF',1,1,'studente','13:24:42.000000'),(42,'2019-02-06','CIAOPROF',1,1,'studente','13:24:43.000000'),(43,'2019-02-06','CIAOPROF',1,1,'studente','13:31:03.000000'),(44,'2019-02-06','CIAOPROF',1,1,'studente','13:31:04.000000'),(45,'2019-02-06','CIAOPROF',1,1,'studente','13:33:45.000000'),(46,'2019-02-06','CIAOPROF',1,1,'studente','13:33:46.000000'),(47,'2019-02-06','CIAOPROF',1,1,'studente','18:09:26.000000'),(48,'2019-02-06','CIAOPROF',1,1,'studente','18:10:32.000000'),(49,'2019-02-06','CIAOPROF',1,1,'studente','18:10:33.000000'),(50,'2019-02-06','CIAOPROF',1,1,'studente','22:57:55.000000'),(51,'2019-02-06','CIAOPROF',1,1,'studente','22:57:55.000000'),(52,'2019-02-07','CIAOPROF',1,1,'studente','18:48:34.000000'),(53,'2019-02-07','CIAOPROF',1,1,'studente','18:48:34.000000'),(54,'2019-02-07','CIAOPROF',1,1,'studente','18:52:09.000000'),(55,'2019-02-07','CIAOPROF',1,1,'studente','18:52:10.000000'),(56,'2019-02-07','CIAOPROF',1,1,'studente','21:34:25.000000'),(57,'2019-02-07','CIAOPROF',1,1,'studente','21:34:26.000000');
/*!40000 ALTER TABLE `messaggio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prenotazione`
--

DROP TABLE IF EXISTS `prenotazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prenotazione` (
  `idPrenotazione` int(11) NOT NULL AUTO_INCREMENT,
  `listaStudenti` varchar(80) DEFAULT NULL,
  `motivazione` varchar(45) DEFAULT NULL,
  `orario` time NOT NULL,
  `idRicevimento` int(11) DEFAULT NULL,
  `idStudente` int(11) DEFAULT NULL,
  `presenza` bit(1) DEFAULT b'0',
  PRIMARY KEY (`idPrenotazione`),
  KEY `idRicevimento` (`idRicevimento`),
  KEY `idStud_idx` (`idStudente`),
  CONSTRAINT `idRicevimento` FOREIGN KEY (`idRicevimento`) REFERENCES `ricevimento` (`idRicevimento`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idStud` FOREIGN KEY (`idStudente`) REFERENCES `studente` (`idStudente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prenotazione`
--

LOCK TABLES `prenotazione` WRITE;
/*!40000 ALTER TABLE `prenotazione` DISABLE KEYS */;
INSERT INTO `prenotazione` VALUES (1,'Rocco Aliberti','nesuna','11:12:36',2,1,''),(2,'Rocco Aliberti - Luca Postiglione','Sono stupido','11:59:59',2,2,'\0'),(3,'Rocco Aliberti','nesuna','11:12:36',2,2,'\0'),(4,'Santelia Salvatore,Ricci Luca','Revisione Prova intercorso','11:12:36',2,1,'');
/*!40000 ALTER TABLE `prenotazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professore`
--

DROP TABLE IF EXISTS `professore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professore` (
  `idProfessore` int(11) NOT NULL AUTO_INCREMENT,
  `nomeProfessore` varchar(45) NOT NULL,
  `cognomeProfessore` varchar(45) NOT NULL,
  `emailProfessore` varchar(45) NOT NULL,
  `telefonoProfessore` varchar(45) DEFAULT NULL,
  `ufficioProfessore` varchar(45) NOT NULL,
  PRIMARY KEY (`idProfessore`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professore`
--

LOCK TABLES `professore` WRITE;
/*!40000 ALTER TABLE `professore` DISABLE KEYS */;
INSERT INTO `professore` VALUES (1,'Filomena','Ferrucci','fferrucci@unisa.it','0819876543','stecca F'),(2,'Carmine','Gravino','cgravino@unisa.it','0819876543','stecca F'),(3,'Mamma','Papa','prof@prof.it','666','ufficio 21');
/*!40000 ALTER TABLE `professore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ricevimento`
--

DROP TABLE IF EXISTS `ricevimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ricevimento` (
  `idRicevimento` int(11) NOT NULL AUTO_INCREMENT,
  `orarioInizio` time NOT NULL,
  `orarioFine` time NOT NULL,
  `luogo` varchar(80) NOT NULL,
  `data` date NOT NULL,
  `postiTotali` int(11) NOT NULL,
  `postiDisponibili` int(11) NOT NULL,
  `idProfessore` int(11) DEFAULT NULL,
  PRIMARY KEY (`idRicevimento`),
  KEY `idProfessore_idx` (`idProfessore`),
  CONSTRAINT `idProf` FOREIGN KEY (`idProfessore`) REFERENCES `professore` (`idProfessore`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ricevimento`
--

LOCK TABLES `ricevimento` WRITE;
/*!40000 ALTER TABLE `ricevimento` DISABLE KEYS */;
INSERT INTO `ricevimento` VALUES (1,'12:00:00','12:30:00','stecca F','2019-02-16',2,0,1),(2,'12:00:00','12:30:00','stecca F','2019-02-16',2,0,2),(3,'11:12:38','11:31:40','stecca F','2018-12-12',2,1,2),(4,'11:12:12','11:31:40','stecca F','2018-12-12',2,0,2);
/*!40000 ALTER TABLE `ricevimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studente`
--

DROP TABLE IF EXISTS `studente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studente` (
  `idStudente` int(11) NOT NULL AUTO_INCREMENT,
  `nomeStudente` varchar(45) NOT NULL,
  `cognomeStudente` varchar(45) NOT NULL,
  `matricola` varchar(45) NOT NULL,
  `emailStudente` varchar(45) NOT NULL,
  `telefonoStudente` varchar(45) DEFAULT NULL,
  `numAssenza` int(11) DEFAULT '0',
  PRIMARY KEY (`idStudente`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studente`
--

LOCK TABLES `studente` WRITE;
/*!40000 ALTER TABLE `studente` DISABLE KEYS */;
INSERT INTO `studente` VALUES (1,'Salvatore','Santelia','0512104001','s.santelia1@studenti.unisa.it','3334455678',77),(2,'Rocco','Aliberti','0512104627','r.aliberti18@studenti.unisa.it','3334455678',1);
/*!40000 ALTER TABLE `studente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-07 21:43:08
