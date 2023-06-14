/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE IF NOT EXISTS `canastabd` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `canastabd`;

CREATE TABLE IF NOT EXISTS `categoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `clave` int DEFAULT NULL,
  `nombre` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `categoria` (`id`, `clave`, `nombre`) VALUES
	(1, 4448823, 'Higiene Personal'),
	(2, 7983910, 'Alimentos y Bebidas'),
	(3, 7655582, 'Deportes y Actividades'),
	(4, 1575131, 'Herramientas'),
	(5, 9477750, 'Mascotas'),
	(6, 9897866, 'Electrónica'),
	(7, 3120103, 'Hogar'),
	(8, 9524355, 'Ropa y Accesorios'),
	(9, 777711, 'Salud y Belleza'),
	(10, 3487273, 'Electrodomésticos');

CREATE TABLE IF NOT EXISTS `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom_empresa` varchar(30) DEFAULT NULL,
  `tamaño_empresa` varchar(20) DEFAULT NULL,
  `id_persona` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_persona` (`id_persona`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `cliente` (`id`, `nom_empresa`, `tamaño_empresa`, `id_persona`) VALUES
	(1, 'Fortaleza', 'Grande', 6),
	(2, 'Hogares', 'Grande', 11);

CREATE TABLE IF NOT EXISTS `compra_venta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `clave` int DEFAULT NULL,
  `tipo` varchar(10) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `id_usuario` int DEFAULT NULL,
  `id_cliente` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `compra_venta_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `compra_venta_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `direccion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `calle` varchar(20) DEFAULT NULL,
  `numero` int DEFAULT NULL,
  `colonia` varchar(20) DEFAULT NULL,
  `ciudad` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `direccion` (`id`, `calle`, `numero`, `colonia`, `ciudad`) VALUES
	(1, 'Tabachin', 10, 'Obrero Campesino', 'Xalapa'),
	(2, 'Del Obrero', 90, 'Progreso', 'Xalapa'),
	(3, 'Sauces', 123, 'Centro', 'Veracruz'),
	(4, 'Ruiz Cortinez', 200, 'Unidad y Progreso', 'Xalapa'),
	(5, 'Uno', 102, 'Progreso', 'Veracruz'),
	(6, 'Tres', 2003, 'Avenida', 'Veracruz'),
	(7, 'Cuatro', 10, 'Lopez', 'New York'),
	(8, 'Cinco', 10, 'Uno', 'Londres'),
	(9, 'Diamante', 200, 'Unidad', 'Xalapa'),
	(10, 'Diamante', 120, 'Tres', 'Veracruz'),
	(11, 'Tabachin', 20, 'Lomas Verdes', 'Xalapa'),
	(12, 'Economia', 200, 'Ferrer Guardi', 'Xalapa');

CREATE TABLE IF NOT EXISTS `persona` (
  `id` int NOT NULL AUTO_INCREMENT,
  `clave` int DEFAULT NULL,
  `fecha_nac` date DEFAULT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `apellido` varchar(30) DEFAULT NULL,
  `correo` varchar(40) DEFAULT NULL,
  `telefono` varchar(11) DEFAULT NULL,
  `id_direccion` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_direccion` (`id_direccion`),
  CONSTRAINT `persona_ibfk_1` FOREIGN KEY (`id_direccion`) REFERENCES `direccion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `persona` (`id`, `clave`, `fecha_nac`, `nombre`, `apellido`, `correo`, `telefono`, `id_direccion`) VALUES
	(1, 826150210, '2001-09-12', 'Miguel', 'Vasquez', 'mvas@gmail.com', '2283044402', 1),
	(2, 433216509, '2023-06-13', 'Eduardo', 'Rosas', 'edu@gmail.com', '2283044402', 2),
	(3, 499676891, '2004-09-24', 'Margarita', 'Rosas', 'mar@gmail.com', '2282875674', 3),
	(4, 595823135, '2017-06-13', 'Magally', 'Landeros', 'cxar@gmail.com', '2280907685', 4),
	(5, 873216053, '2023-06-13', 'Luis', 'Herrera', 'luis@gmail.com', '2280938495', 5),
	(6, 592606968, '2023-06-13', 'David', 'Ruiz', 'dav@gmail.com', '2283040987', 6),
	(7, 196109648, '2023-06-13', 'Lewis', 'Roberts', 'les@gmail.com', '1234567890', 7),
	(8, 800490031, '2023-06-13', 'Lance', 'Stroll', 'lace@gmail.com', '1234557890', 8),
	(9, 668719271, '2023-06-13', 'Roberto', 'JR', 'jos@gmail.com', '1234567899', 9),
	(10, 352969914, '2023-06-13', 'Cristian', 'Leucebio', 'mvas@gmail.com', '2293040002', 10),
	(11, 281688319, '2023-06-13', 'Miguel', 'Rosas', 'asdas@gmail.com', '2283457689', 11),
	(12, 902793168, '2023-06-13', 'Juan Luis', 'Lopez Herrara', 'jlopez@gmail.com', '2289768985', 12);

CREATE TABLE IF NOT EXISTS `producto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `clave` int DEFAULT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `cantidad` double DEFAULT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  `id_proveedor` int DEFAULT NULL,
  `id_categoria` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_proveedor` (`id_proveedor`),
  KEY `id_categoria` (`id_categoria`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id`),
  CONSTRAINT `producto_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `producto` (`id`, `clave`, `nombre`, `precio`, `cantidad`, `tipo`, `id_proveedor`, `id_categoria`) VALUES
	(1, 780991274, 'FreshSmile', 40, 100, 'Piezas', 3, 1),
	(2, 236533874, 'FreshGlide', 35, 50, 'Piezas', 3, 1),
	(3, 485374203, 'Balon Soccer', 560, 20, 'Piezas', 4, 3),
	(4, 815112793, 'Basketball', 90, 100, 'Piezas', 5, 3),
	(5, 74658678, 'Camara', 800, 40, 'Piezas', 7, 6);

CREATE TABLE IF NOT EXISTS `productos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_producto` int DEFAULT NULL,
  `id_compra` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_producto` (`id_producto`),
  KEY `id_compra` (`id_compra`),
  CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `productos_ibfk_2` FOREIGN KEY (`id_compra`) REFERENCES `compra_venta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `proveedor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `compania` varchar(20) DEFAULT NULL,
  `fecha_Entrega` date DEFAULT NULL,
  `fecha_rec_pro` date DEFAULT NULL,
  `id_persona` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_persona` (`id_persona`),
  CONSTRAINT `proveedor_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `proveedor` (`id`, `compania`, `fecha_Entrega`, `fecha_rec_pro`, `id_persona`) VALUES
	(1, 'Barcel', '2023-07-18', '2023-07-03', 3),
	(2, 'Sabritas', '2023-07-07', '2023-08-31', 4),
	(3, 'P&G', '2023-06-07', '2023-06-13', 5),
	(4, 'Nike', '2023-07-13', '2023-06-13', 7),
	(5, 'Adidas', '2023-07-13', '2023-06-13', 8),
	(6, 'La costeña', '2023-07-13', '2023-06-13', 9),
	(7, 'Steren', '2023-07-13', '2023-06-13', 10);

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(14) DEFAULT NULL,
  `contrasena` varchar(80) DEFAULT NULL,
  `id_persona` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_persona` (`id_persona`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `usuario` (`id`, `usuario`, `contrasena`, `id_persona`) VALUES
	(1, 'Miguel', 'Miguel', 1),
	(2, 'Eduardo', '$2a$10$.p4Hl/EQf/FdpRvfT8rQH.usk0KuQQtIphdpoXWfuDYCwrqtkTkhi', 2),
	(3, 'julopez', '123456', 12);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
