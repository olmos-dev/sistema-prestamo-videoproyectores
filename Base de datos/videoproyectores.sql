-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-08-2018 a las 19:50:29
-- Versión del servidor: 10.1.30-MariaDB
-- Versión de PHP: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `videoproyectores`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agotado`
--

CREATE TABLE `agotado` (
  `id` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `proyector` int(11) DEFAULT NULL,
  `VGA` int(11) DEFAULT NULL,
  `HDMI` int(11) DEFAULT NULL,
  `ext` int(11) DEFAULT NULL,
  `bocinas` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `agotado`
--

INSERT INTO `agotado` (`id`, `fecha`, `proyector`, `VGA`, `HDMI`, `ext`, `bocinas`) VALUES
(1, NULL, 1, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `llenarinventario`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `llenarinventario` (
`inventario` varchar(11)
,`No` varchar(20)
,`estado` varchar(11)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamo`
--

CREATE TABLE `prestamo` (
  `folio` int(11) NOT NULL,
  `inventario` varchar(11) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `fecha` date DEFAULT NULL,
  `hora` varchar(8) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `matricula` varchar(9) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `nombre` varchar(50) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `materia` varchar(60) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `horario` varchar(13) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `maestro` varchar(50) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `aula` varchar(20) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `VGA` varchar(2) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `HDMI` varchar(2) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `extension` varchar(2) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `bocinas` varchar(2) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `estado` varchar(9) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `notas` varchar(256) COLLATE utf8mb4_spanish2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `prestamovista`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `prestamovista` (
`folio` int(11)
,`inventario` varchar(11)
,`fecha` date
,`hora` varchar(8)
,`matricula` varchar(9)
,`nombre` varchar(50)
,`materia` varchar(60)
,`horario` varchar(13)
,`maestro` varchar(50)
,`aula` varchar(20)
,`VGA` varchar(2)
,`HDMI` varchar(2)
,`extension` varchar(2)
,`bocinas` varchar(2)
,`estado` varchar(9)
,`notas` varchar(256)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyector`
--

CREATE TABLE `proyector` (
  `inventario` varchar(11) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `No` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `estado` varchar(11) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `notas` varchar(256) COLLATE utf8mb4_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `proyector`
--

INSERT INTO `proyector` (`inventario`, `No`, `estado`, `notas`) VALUES
('No Solicito', '', 'Funciona', '');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `proyectorvista`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `proyectorvista` (
`inventario` varchar(11)
,`No` varchar(20)
,`estado` varchar(11)
,`notas` varchar(256)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `llenarinventario`
--
DROP TABLE IF EXISTS `llenarinventario`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `llenarinventario`  AS  select `proyector`.`inventario` AS `inventario`,`proyector`.`No` AS `No`,`proyector`.`estado` AS `estado` from `proyector` where (`proyector`.`estado` = 'Funciona') ;

-- --------------------------------------------------------

--
-- Estructura para la vista `prestamovista`
--
DROP TABLE IF EXISTS `prestamovista`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `prestamovista`  AS  select `prestamo`.`folio` AS `folio`,`prestamo`.`inventario` AS `inventario`,`prestamo`.`fecha` AS `fecha`,`prestamo`.`hora` AS `hora`,`prestamo`.`matricula` AS `matricula`,`prestamo`.`nombre` AS `nombre`,`prestamo`.`materia` AS `materia`,`prestamo`.`horario` AS `horario`,`prestamo`.`maestro` AS `maestro`,`prestamo`.`aula` AS `aula`,`prestamo`.`VGA` AS `VGA`,`prestamo`.`HDMI` AS `HDMI`,`prestamo`.`extension` AS `extension`,`prestamo`.`bocinas` AS `bocinas`,`prestamo`.`estado` AS `estado`,`prestamo`.`notas` AS `notas` from `prestamo` order by `prestamo`.`folio` desc ;

-- --------------------------------------------------------

--
-- Estructura para la vista `proyectorvista`
--
DROP TABLE IF EXISTS `proyectorvista`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `proyectorvista`  AS  select `proyector`.`inventario` AS `inventario`,`proyector`.`No` AS `No`,`proyector`.`estado` AS `estado`,`proyector`.`notas` AS `notas` from `proyector` where (`proyector`.`inventario` <> 'No Solicito') order by `proyector`.`inventario` ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `agotado`
--
ALTER TABLE `agotado`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `prestamo`
--
ALTER TABLE `prestamo`
  ADD PRIMARY KEY (`folio`),
  ADD KEY `id` (`inventario`),
  ADD KEY `inventario` (`inventario`);

--
-- Indices de la tabla `proyector`
--
ALTER TABLE `proyector`
  ADD PRIMARY KEY (`inventario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `agotado`
--
ALTER TABLE `agotado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `prestamo`
--
ALTER TABLE `prestamo`
  ADD CONSTRAINT `prestamo_ibfk_1` FOREIGN KEY (`inventario`) REFERENCES `proyector` (`inventario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
