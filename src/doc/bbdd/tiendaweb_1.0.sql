-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: tiendaweb
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
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `ID_CATEGORIA` int(11) NOT NULL AUTO_INCREMENT,
  `ID_SECCION` int(11) DEFAULT NULL,
  `NOMBRE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID_CATEGORIA`),
  KEY `FK_CATEGORIAS_SECCIONES` (`ID_SECCION`),
  CONSTRAINT `FK_CATEGORIAS_SECCIONES` FOREIGN KEY (`ID_SECCION`) REFERENCES `secciones` (`ID_SECCION`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,1,'Charcutería'),(2,1,'Pollería'),(3,1,'Carnicería');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `ID_CLIENTE` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `APELLIDOS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TELEFONO` varchar(9) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DNI` varchar(9) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DIRECCION` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID_CLIENTE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Isabel','Antón Pinz','656778899','1234567C','Avda. de la Albufera 245, 7º C'),(2,'Manuela','Colorado Pérez','654654654','22223333Q','Calle del Amparo 35, 2º B');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalles`
--

DROP TABLE IF EXISTS `detalles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalles` (
  `ID_DETALLE` int(11) NOT NULL AUTO_INCREMENT,
  `ID_PRODUCTO` int(11) DEFAULT NULL,
  `ID_PEDIDO` int(11) DEFAULT NULL,
  `PRECIO` double(4,2) DEFAULT NULL,
  `CANTIDAD` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_DETALLE`),
  KEY `FK_DETALLES_PRODUCTOS` (`ID_PRODUCTO`),
  KEY `FK_DETALLES_PEDIDOS` (`ID_PEDIDO`),
  CONSTRAINT `FK_DETALLES_PEDIDOS` FOREIGN KEY (`ID_PEDIDO`) REFERENCES `pedidos` (`ID_PEDIDO`),
  CONSTRAINT `FK_DETALLES_PRODUCTOS` FOREIGN KEY (`ID_PRODUCTO`) REFERENCES `productos` (`ID_PRODUCTO`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalles`
--

LOCK TABLES `detalles` WRITE;
/*!40000 ALTER TABLE `detalles` DISABLE KEYS */;
INSERT INTO `detalles` VALUES (13,17,12,NULL,1),(14,18,12,NULL,1),(15,49,12,NULL,1),(16,35,12,NULL,1),(17,45,12,NULL,1),(18,25,12,NULL,1),(25,17,14,NULL,1),(26,19,14,NULL,1),(27,20,14,NULL,1),(28,32,14,NULL,1),(29,40,14,NULL,1),(30,41,14,NULL,1),(31,19,15,NULL,1),(32,20,15,NULL,1);
/*!40000 ALTER TABLE `detalles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `ID_PEDIDO` int(11) NOT NULL AUTO_INCREMENT,
  `ID_CLIENTE` int(11) DEFAULT NULL,
  `TOTAL` double(4,2) DEFAULT NULL,
  `FECHA` date DEFAULT NULL,
  `NUM_ARTICULOS` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_PEDIDO`),
  KEY `FK_PEDIDOS_CLIENTES` (`ID_CLIENTE`),
  CONSTRAINT `FK_PEDIDOS_CLIENTES` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `clientes` (`ID_CLIENTE`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (12,1,29.48,NULL,6),(14,2,32.78,NULL,6),(15,2,2.84,NULL,2);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `ID_PRODUCTO` int(11) NOT NULL AUTO_INCREMENT,
  `ID_CATEGORIA` int(11) DEFAULT NULL,
  `NOMBRE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PRECIO` double(4,2) DEFAULT NULL,
  `NOMBRE_FOTO` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID_PRODUCTO`),
  KEY `FK_PRODUCTOS_CATEGORIAS` (`ID_CATEGORIA`),
  CONSTRAINT `FK_PRODUCTOS_CATEGORIAS` FOREIGN KEY (`ID_CATEGORIA`) REFERENCES `categorias` (`ID_CATEGORIA`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (16,2,'Alitas',3.25,'alitas'),(17,1,'Bacon -100g',1.90,'bacon'),(18,1,'Cecina',5.75,'cecina'),(19,1,'Chopped',0.99,'chopped'),(20,1,'Chorizo de Pamplona',1.85,'chorizo_pamplona'),(21,3,'Chuletas de cerdo (aguja)',2.95,'chuletas_aguja_cerdo'),(22,3,'Chuletas de cerdo',3.25,'chuletas_cerdo'),(23,3,'Chuletas de cordero',6.50,'chuletas_cordero'),(24,2,'Chuletas de pavo',4.29,'chuletas_pavo'),(25,3,'Chuletón',9.99,'chuleton'),(26,2,'Contramuslos',3.69,'contramuslos'),(27,3,'Costillas',6.55,'costillas'),(28,3,'Cuartos de cordero recental',12.99,'cuartos_recental'),(29,3,'Entrecot',15.99,'entrecot'),(30,3,'Carne para estofado (vaca)',5.85,'estofado_vacuno'),(31,3,'Filetes de aguja',7.35,'filetes_aguja'),(32,3,'Filetes de contra',8.20,'filetes_contra'),(33,1,'Jamón - 150g',3.75,'jamon'),(34,1,'Jamón York',4.25,'jamon_york'),(35,2,'Jamoncitos',3.59,'jamoncitos'),(36,1,'Lacón',3.99,'lacon'),(37,3,'Lomo de cerdo',3.75,'lomo'),(38,1,'Mortadela',1.25,'mortadela'),(39,1,'Mortadela con aceitunas',1.50,'mortadela_aceitunas'),(40,3,'Paletilla de cordero',16.99,'paletilla'),(41,3,'Panceta',2.85,'panceta'),(42,1,'Pavo',2.65,'pavo'),(43,2,'Pechuga de pollo',6.45,'pechuga'),(44,2,'Pechuga de pavo',7.00,'pechuga_pavo'),(45,2,'Pollo entero',5.50,'pollo_entero'),(46,3,'Presa',7.89,'presa'),(47,3,'Roti de cerdo',8.35,'roti_cerdo'),(48,2,'Roti de pavo',8.25,'roti_pavo'),(49,1,'Salami',2.75,'salami');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secciones`
--

DROP TABLE IF EXISTS `secciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `secciones` (
  `ID_SECCION` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID_SECCION`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `secciones`
--

LOCK TABLES `secciones` WRITE;
/*!40000 ALTER TABLE `secciones` DISABLE KEYS */;
INSERT INTO `secciones` VALUES (1,'Alimentación');
/*!40000 ALTER TABLE `secciones` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-09 15:18:49
