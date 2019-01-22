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
INSERT INTO `login` VALUES ('miaemail','miapassword','studente');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messaggio`
--

DROP TABLE IF EXISTS `messaggio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messaggio` (
  `idMessaggio` int(11) NOT NULL,
  `dataMessaggio` date NOT NULL,
  `testoMessaggio` varchar(45) NOT NULL,
  `idProfessore` int(11) DEFAULT NULL,
  `idStudente` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMessaggio`),
  KEY `idProfessore_idx` (`idProfessore`),
  KEY `idStudente_idx` (`idStudente`),
  CONSTRAINT `idProfessore` FOREIGN KEY (`idProfessore`) REFERENCES `professore` (`idProfessore`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idStudente` FOREIGN KEY (`idStudente`) REFERENCES `studente` (`idStudente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messaggio`
--

LOCK TABLES `messaggio` WRITE;
/*!40000 ALTER TABLE `messaggio` DISABLE KEYS */;
/*!40000 ALTER TABLE `messaggio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prenotazione`
--

DROP TABLE IF EXISTS `prenotazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prenotazione` (
  `idPrenotazione` int(11) NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prenotazione`
--

LOCK TABLES `prenotazione` WRITE;
/*!40000 ALTER TABLE `prenotazione` DISABLE KEYS */;
INSERT INTO `prenotazione` VALUES (1,'Rocco Aliberti','nesuna','11:12:36',2,1,''),(2,'Rocco Aliberti - Luca Postiglione','Sono stupido','11:59:59',2,2,'\0'),(3,'Rocco Aliberti','nesuna','11:12:36',2,2,'\0'),(4,'Rocco Aliberti','nesuna','11:12:36',2,1,'');
/*!40000 ALTER TABLE `prenotazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professore`
--

DROP TABLE IF EXISTS `professore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professore` (
  `idProfessore` int(11) NOT NULL,
  `nomeProfessore` varchar(45) NOT NULL,
  `cognomeProfessore` varchar(45) NOT NULL,
  `emailProfessore` varchar(45) NOT NULL,
  `telefonoProfessore` varchar(45) DEFAULT NULL,
  `ufficioProfessore` varchar(45) NOT NULL,
  PRIMARY KEY (`idProfessore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professore`
--

LOCK TABLES `professore` WRITE;
/*!40000 ALTER TABLE `professore` DISABLE KEYS */;
INSERT INTO `professore` VALUES (1,'Filomena','Ferrucci','fferrucci@unisa.it','0819876543','stecca F'),(2,'Carmine','Gravino','cgravino@unisa.it','0819876543','stecca F');
/*!40000 ALTER TABLE `professore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ricevimento`
--

DROP TABLE IF EXISTS `ricevimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ricevimento` (
  `idRicevimento` int(11) NOT NULL,
  `orarioInizio` time NOT NULL,
  `orarioFine` time NOT NULL,
  `luogo` varchar(80) NOT NULL,
  `data` date NOT NULL,
  `idProfessore` int(11) DEFAULT NULL,
  PRIMARY KEY (`idRicevimento`),
  KEY `idProfessore_idx` (`idProfessore`),
  CONSTRAINT `idProf` FOREIGN KEY (`idProfessore`) REFERENCES `professore` (`idProfessore`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ricevimento`
--

LOCK TABLES `ricevimento` WRITE;
/*!40000 ALTER TABLE `ricevimento` DISABLE KEYS */;
INSERT INTO `ricevimento` VALUES (1,'12:00:00','12:30:00','stecca F','2019-02-16',1),(2,'12:00:00','12:30:00','stecca F','2019-02-16',2),(3,'11:12:38','11:31:40','stecca F','2018-12-12',2),(4,'11:12:38','11:31:40','stecca F','2018-12-12',2);
/*!40000 ALTER TABLE `ricevimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studente`
--

DROP TABLE IF EXISTS `studente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studente` (
  `idStudente` int(11) NOT NULL,
  `nomeStudente` varchar(45) NOT NULL,
  `cognomeStudente` varchar(45) NOT NULL,
  `matricola` varchar(45) NOT NULL,
  `emailStudente` varchar(45) NOT NULL,
  `telefonoStudente` varchar(45) DEFAULT NULL,
  `numAssenza` int(11) DEFAULT '0',
  PRIMARY KEY (`idStudente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studente`
--

LOCK TABLES `studente` WRITE;
/*!40000 ALTER TABLE `studente` DISABLE KEYS */;
INSERT INTO `studente` VALUES (1,'Salvatore','Santelia','0512104001','s.santelia1@studenti.unisa.it','3334455678',5),(2,'Rocco','Aliberti','0512104627','r.aliberti18@studenti.unisa.it','3334455678',1);
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

-- Dump completed on 2019-01-22 15:33:16
