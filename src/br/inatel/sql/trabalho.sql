CREATE DATABASE  IF NOT EXISTS `trabalho` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `trabalho`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: trabalho
-- ------------------------------------------------------
-- Server version	5.7.26

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
-- Table structure for table `bandeja`
--

DROP TABLE IF EXISTS `bandeja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bandeja` (
  `id_bandeja` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  PRIMARY KEY (`id_bandeja`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bandeja`
--

LOCK TABLES `bandeja` WRITE;
/*!40000 ALTER TABLE `bandeja` DISABLE KEYS */;
INSERT INTO `bandeja` VALUES (1,'Forceps'),(2,'Tesoura'),(3,'Bisturis');
/*!40000 ALTER TABLE `bandeja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cirurgia`
--

DROP TABLE IF EXISTS `cirurgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cirurgia` (
  `id_cirurgia` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime NOT NULL,
  `bandeja_id` int(11) NOT NULL,
  `paciente_cpf` varchar(20) NOT NULL,
  `medico_crm` varchar(20) NOT NULL,
  `enfermeiro_cre` varchar(20) NOT NULL,
  PRIMARY KEY (`id_cirurgia`),
  KEY `fk_bandeja` (`bandeja_id`),
  KEY `fk_enfermeiro` (`enfermeiro_cre`),
  KEY `fk_medico` (`medico_crm`),
  KEY `fk_paciente` (`paciente_cpf`),
  CONSTRAINT `fk_bandeja` FOREIGN KEY (`bandeja_id`) REFERENCES `bandeja` (`id_bandeja`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_enfermeiro` FOREIGN KEY (`enfermeiro_cre`) REFERENCES `enfermeiro` (`cre`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_medico` FOREIGN KEY (`medico_crm`) REFERENCES `medico` (`crm`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_paciente` FOREIGN KEY (`paciente_cpf`) REFERENCES `paciente` (`cpf`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cirurgia`
--

LOCK TABLES `cirurgia` WRITE;
/*!40000 ALTER TABLE `cirurgia` DISABLE KEYS */;
INSERT INTO `cirurgia` VALUES (1,'2019-11-26 19:07:00',1,'05995555642','5452','6558');
/*!40000 ALTER TABLE `cirurgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enfermeiro`
--

DROP TABLE IF EXISTS `enfermeiro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enfermeiro` (
  `cre` varchar(20) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  PRIMARY KEY (`cre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enfermeiro`
--

LOCK TABLES `enfermeiro` WRITE;
/*!40000 ALTER TABLE `enfermeiro` DISABLE KEYS */;
INSERT INTO `enfermeiro` VALUES ('5544','Fernanda Araújo','(35) 9565-4554'),('6558','Josefina Amarante','(35) 9565-4554'),('7544','Paula Magalhães','(35) 9565-4554');
/*!40000 ALTER TABLE `enfermeiro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enfermeiro_has_cirurgia`
--

DROP TABLE IF EXISTS `enfermeiro_has_cirurgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enfermeiro_has_cirurgia` (
  `enfermeiro_cre` varchar(20) NOT NULL,
  `cirurgia_id` int(11) NOT NULL,
  KEY `fk_enfermeiro_cirurgia_cirurgia` (`cirurgia_id`),
  KEY `fk_enfermeiro_cirurgia_enfermeiro` (`enfermeiro_cre`),
  CONSTRAINT `fk_enfermeiro_cirurgia_cirurgia` FOREIGN KEY (`cirurgia_id`) REFERENCES `cirurgia` (`id_cirurgia`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_enfermeiro_cirurgia_enfermeiro` FOREIGN KEY (`enfermeiro_cre`) REFERENCES `enfermeiro` (`cre`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enfermeiro_has_cirurgia`
--

LOCK TABLES `enfermeiro_has_cirurgia` WRITE;
/*!40000 ALTER TABLE `enfermeiro_has_cirurgia` DISABLE KEYS */;
INSERT INTO `enfermeiro_has_cirurgia` VALUES ('5544',1);
/*!40000 ALTER TABLE `enfermeiro_has_cirurgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `esterilizacao`
--

DROP TABLE IF EXISTS `esterilizacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `esterilizacao` (
  `id_esterilizacao` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime NOT NULL,
  `enfermeiro_cre` varchar(20) NOT NULL,
  `bandeja_id` int(11) NOT NULL,
  PRIMARY KEY (`id_esterilizacao`),
  KEY `fk_esterilizacao_enfermeiro` (`enfermeiro_cre`),
  KEY `fk_esterilizacao_bandeja` (`bandeja_id`),
  CONSTRAINT `fk_esterilizacao_bandeja` FOREIGN KEY (`bandeja_id`) REFERENCES `bandeja` (`id_bandeja`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_esterilizacao_enfermeiro` FOREIGN KEY (`enfermeiro_cre`) REFERENCES `enfermeiro` (`cre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `esterilizacao`
--

LOCK TABLES `esterilizacao` WRITE;
/*!40000 ALTER TABLE `esterilizacao` DISABLE KEYS */;
INSERT INTO `esterilizacao` VALUES (1,'2019-11-19 19:06:00','6558',2),(2,'2019-11-19 19:06:00','5544',3);
/*!40000 ALTER TABLE `esterilizacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medico`
--

DROP TABLE IF EXISTS `medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medico` (
  `crm` varchar(20) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  PRIMARY KEY (`crm`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medico`
--

LOCK TABLES `medico` WRITE;
/*!40000 ALTER TABLE `medico` DISABLE KEYS */;
INSERT INTO `medico` VALUES ('1542','Joaquim de Jesus','(35) 9945-6526'),('5452','Mariana Guedes','(35) 9945-6526'),('8455','Juscelino Amaral','(35) 9945-6526');
/*!40000 ALTER TABLE `medico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paciente` (
  `cpf` varchar(20) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `idade` int(11) NOT NULL,
  PRIMARY KEY (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` VALUES ('04','hgf',65),('04887844545','Jorge Matias',35),('05','gsdfg',35),('05455545455','Maria Fernandes',21),('05995555642','Danilo Vidal Ribeiro',34),('06','dsafs',24),('07','sdf',5),('08','fdsg',6),('09','sdf',56),('10','sdf',81),('11','gfds',87),('12','tterw',56),('13','qwt',51);
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-25 11:27:11
