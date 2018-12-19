create schema ezprofmeeting;
use ezprofmeeting;

CREATE TABLE `studente` (
  `idStudente` int(11) NOT NULL,
  `nomeStudente` varchar(45) NOT NULL,
  `cognomeStudente` varchar(45) NOT NULL,
  `matricola` varchar(45) NOT NULL,
  `emailStudente` varchar(45) NOT NULL,
  `telefonoStudente` varchar(45) DEFAULT NULL,
  `numAssenza` int(11) DEFAULT '0',
  PRIMARY KEY (`idStudente`)
);

CREATE TABLE `professore` (
  `idProfessore` int(11) NOT NULL,
  `nomeProfessore` varchar(45) NOT NULL,
  `cognomeProfessore` varchar(45) NOT NULL,
  `emailProfessore` varchar(45) NOT NULL,
  `telefonoProfessore` varchar(45) DEFAULT NULL,
  `ufficioProfessore` varchar(45) NOT NULL,
  PRIMARY KEY (`idProfessore`)
);

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
);

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
);

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
);