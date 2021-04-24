-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.22 - MySQL Community Server - GPL
-- SO del servidor:              Linux
-- HeidiSQL Versión:             11.2.0.6224
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Volcando estructura para tabla db_prueba.tbl_rol
DROP TABLE IF EXISTS `tbl_rol`;
CREATE TABLE IF NOT EXISTS `tbl_rol` (
  `ro_rolid` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `permisos` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ro_rolid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_prueba.tbl_rol: ~4 rows (aproximadamente)
DELETE FROM `tbl_rol`;
/*!40000 ALTER TABLE `tbl_rol` DISABLE KEYS */;
INSERT INTO `tbl_rol` (`ro_rolid`, `nombre`, `permisos`, `descripcion`) VALUES
	(1, 'Administrador', 'Todos', 'administrador'),
	(2, 'Cliente', 'Basicos', 'cliente'),
	(3, 'Vendedor', 'Ventas', 'vendedor'),
	(4, 'Usuario', 'Basico', 'usuario');
/*!40000 ALTER TABLE `tbl_rol` ENABLE KEYS */;

-- Volcando estructura para tabla db_prueba.tbl_usuario
DROP TABLE IF EXISTS `tbl_usuario`;
CREATE TABLE IF NOT EXISTS `tbl_usuario` (
  `usu_usuarioid` int NOT NULL AUTO_INCREMENT,
  `usu_tipodocumento` varchar(45) DEFAULT NULL,
  `usu_documento` varchar(45) DEFAULT NULL,
  `usu_nombre` varchar(45) DEFAULT NULL,
  `usu_apellido` varchar(45) DEFAULT NULL,
  `usu_correo` varchar(45) DEFAULT NULL,
  `usu_movil` varchar(50) DEFAULT NULL,
  `usu_clave` varchar(45) DEFAULT NULL,
  `usu_estado` tinyint DEFAULT NULL,
  PRIMARY KEY (`usu_usuarioid`),
  UNIQUE KEY `usu_correo_UNIQUE` (`usu_correo`),
  UNIQUE KEY `usu_documento_UNIQUE` (`usu_documento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_prueba.tbl_usuario: ~1 rows (aproximadamente)
DELETE FROM `tbl_usuario`;
/*!40000 ALTER TABLE `tbl_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_usuario` ENABLE KEYS */;

-- Volcando estructura para tabla db_prueba.tbl_usuario_has_tbl_rol
DROP TABLE IF EXISTS `tbl_usuario_has_tbl_rol`;
CREATE TABLE IF NOT EXISTS `tbl_usuario_has_tbl_rol` (
  `usu_usuario_id` int DEFAULT NULL,
  `rol_rol_id` int DEFAULT NULL,
  KEY `fk_1_idx` (`usu_usuario_id`),
  KEY `kf_2_idx` (`rol_rol_id`,`usu_usuario_id`),
  CONSTRAINT `fk_1` FOREIGN KEY (`usu_usuario_id`) REFERENCES `tbl_usuario` (`usu_usuarioid`),
  CONSTRAINT `fk_2` FOREIGN KEY (`rol_rol_id`) REFERENCES `tbl_rol` (`ro_rolid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_prueba.tbl_usuario_has_tbl_rol: ~0 rows (aproximadamente)
DELETE FROM `tbl_usuario_has_tbl_rol`;
/*!40000 ALTER TABLE `tbl_usuario_has_tbl_rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_usuario_has_tbl_rol` ENABLE KEYS */;

-- Volcando estructura para disparador db_prueba.rolUsuario
DROP TRIGGER IF EXISTS `rolUsuario`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `rolUsuario` AFTER INSERT ON `tbl_usuario` FOR EACH ROW BEGIN
		INSERT INTO tbl_usuario_has_tbl_rol (usu_usuario_id,rol_rol_id) VALUES (NEW.usu_usuarioid,4);
		END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
