-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-01-2020 a las 03:09:15
-- Versión del servidor: 5.6.24
-- Versión de PHP: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `bd_ventas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE IF NOT EXISTS `empleado` (
  `idE` int(15) NOT NULL,
  `nombre` varchar(70) NOT NULL,
  `user` varchar(80) NOT NULL,
  `clave` varchar(20) NOT NULL,
  `tipo` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`idE`, `nombre`, `user`, `clave`, `tipo`) VALUES
(12222, 'sandra', 'sandra45', '1111', 'Administrador'),
(123445, 'jhon ', 'jhon23', '1995', 'Administrador'),
(1063726235, 'carlos diaz', 'carlos03', 'ca1234', 'Empleado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE IF NOT EXISTS `productos` (
  `idP` int(20) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `caracteristica` varchar(600) NOT NULL,
  `precio` int(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`idP`, `Nombre`, `caracteristica`, `precio`) VALUES
(1, 'celular', 'samsumg', 500000),
(3, 'pan', 'sabor a queso ', 4000),
(4, 'auriculares ', 'rojo', 20000),
(5, 'carnet', 'plastico', 2000),
(6, 'cable', '3 metros', 1000),
(7, 'carnet', 'blanco', 5000),
(8, 'ropa', 'a la moda', 120000),
(9, 'protector', 'rojos,azules,narranjas', 7000),
(12, 'platano', 'verde', 1200);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE IF NOT EXISTS `ventas` (
  `idV` int(20) NOT NULL,
  `idE` int(15) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`idV`, `idE`, `fecha`) VALUES
(1, 1063726235, '2020-01-03'),
(2, 1063726235, '2020-01-03'),
(3, 1063726235, '2020-01-03'),
(4, 1063726235, '2020-01-03'),
(5, 1063726235, '2020-01-04'),
(6, 1063726235, '2020-01-04'),
(7, 1063726235, '2020-01-04'),
(8, 1063726235, '2020-01-04'),
(19, 12222, '2020-01-05'),
(20, 12222, '2020-01-05'),
(21, 12222, '2020-01-05'),
(22, 12222, '2020-01-05'),
(23, 12222, '2020-01-05'),
(24, 12222, '2020-01-05'),
(25, 12222, '2020-01-05'),
(26, 1063726235, '2020-01-05'),
(27, 1063726235, '2020-01-05'),
(28, 12222, '2020-01-05'),
(29, 12222, '2020-01-05'),
(30, 12222, '2020-01-05'),
(31, 1063726235, '2020-01-05'),
(32, 1063726235, '2020-01-05'),
(33, 1063726235, '2020-01-05'),
(34, 12222, '2020-01-05'),
(35, 12222, '2020-01-05'),
(36, 12222, '2020-01-05'),
(37, 12222, '2020-01-05'),
(38, 12222, '2020-01-05'),
(39, 12222, '2020-01-05'),
(40, 1063726235, '2020-01-05'),
(41, 1063726235, '2020-01-05'),
(42, 12222, '2020-01-05'),
(43, 12222, '2020-01-05'),
(44, 12222, '2020-01-05'),
(45, 12222, '2020-01-05'),
(46, 12222, '2020-01-05'),
(47, 12222, '2020-01-05'),
(48, 12222, '2020-01-05'),
(49, 12222, '2020-01-05'),
(50, 12222, '2020-01-05'),
(51, 1063726235, '2020-01-06'),
(52, 1063726235, '2020-01-06'),
(53, 123445, '2020-01-06');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta_detalle`
--

CREATE TABLE IF NOT EXISTS `venta_detalle` (
  `idVentas_detalle` int(50) NOT NULL,
  `idV` int(20) NOT NULL,
  `idP` int(20) NOT NULL,
  `cantidad` int(70) NOT NULL,
  `precio` int(70) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `venta_detalle`
--

INSERT INTO `venta_detalle` (`idVentas_detalle`, `idV`, `idP`, `cantidad`, `precio`) VALUES
(1, 1, 1, 2, 1000000),
(2, 4, 4, 2, 40000),
(3, 4, 1, 1, 500000),
(4, 7, 7, 3, 15000),
(5, 7, 8, 1, 120000),
(17, 20, 1, 2, 1000000),
(18, 21, 4, 1, 20000),
(19, 22, 1, 1, 500000),
(20, 23, 1, 1, 500000),
(21, 23, 4, 2, 40000),
(22, 23, 7, 1, 5000),
(23, 26, 4, 2, 40000),
(24, 26, 1, 1, 500000),
(25, 28, 1, 3, 1500000),
(26, 29, 5, 2, 4000),
(27, 29, 1, 3, 1500000),
(28, 31, 6, 2, 2000),
(29, 31, 1, 1, 500000),
(30, 31, 1, 2, 1000000),
(31, 34, 1, 1, 500000),
(32, 34, 3, 1, 4000),
(33, 34, 6, 1, 1000),
(34, 37, 5, 1, 2000),
(35, 38, 1, 2, 1000000),
(36, 38, 3, 1, 4000),
(37, 40, 7, 2, 10000),
(38, 40, 1, 1, 500000),
(39, 42, 1, 2, 1000000),
(41, 42, 4, 1, 20000),
(42, 45, 1, 2, 1000000),
(43, 45, 5, 1, 2000),
(46, 49, 1, 1, 500000),
(47, 49, 6, 2, 2000),
(48, 51, 7, 1, 5000),
(49, 51, 3, 2, 8000),
(50, 53, 1, 1, 500000);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`idE`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`idP`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`idV`), ADD KEY `idE` (`idE`), ADD KEY `idE_2` (`idE`);

--
-- Indices de la tabla `venta_detalle`
--
ALTER TABLE `venta_detalle`
  ADD PRIMARY KEY (`idVentas_detalle`), ADD KEY `idV` (`idV`), ADD KEY `idP` (`idP`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `idV` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=54;
--
-- AUTO_INCREMENT de la tabla `venta_detalle`
--
ALTER TABLE `venta_detalle`
  MODIFY `idVentas_detalle` int(50) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=51;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
ADD CONSTRAINT `hace ` FOREIGN KEY (`idE`) REFERENCES `empleado` (`idE`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `venta_detalle`
--
ALTER TABLE `venta_detalle`
ADD CONSTRAINT `venta_detalle_ibfk_1` FOREIGN KEY (`idV`) REFERENCES `ventas` (`idV`) ON UPDATE CASCADE,
ADD CONSTRAINT `venta_detalle_ibfk_2` FOREIGN KEY (`idP`) REFERENCES `productos` (`idP`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
