-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3312
-- Tiempo de generación: 23-05-2023 a las 22:15:13
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `drogueria`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias_proveedores`
--

CREATE TABLE `categorias_proveedores` (
  `id_categoria_proveedor` int(11) NOT NULL,
  `id_proveedor` int(11) NOT NULL,
  `id_descuento` int(11) NOT NULL,
  `id_tipo_proveedor` int(11) NOT NULL,
  `id_cumplimiento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_cliente` int(11) NOT NULL,
  `cc` varchar(100) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_cliente`, `cc`, `nombre`, `direccion`, `telefono`) VALUES
(22, 'CD 1004', 'DEVIS', 'BARRANQUILLA', '3014729698'),
(23, 'CD 1005', 'YESS', 'CALI', '3014'),
(24, 'PA 1097', 'JEISON', 'CANADA', '123456789');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cumplimientos`
--

CREATE TABLE `cumplimientos` (
  `id_cumplimiento` int(11) NOT NULL,
  `tipo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descuentos`
--

CREATE TABLE `descuentos` (
  `id_descuento` int(11) NOT NULL,
  `tipo` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalles_ventas`
--

CREATE TABLE `detalles_ventas` (
  `id_detalle_venta` int(11) NOT NULL,
  `id_venta` int(11) NOT NULL,
  `id_presentacion_medicamento` int(11) NOT NULL,
  `precio_unitario` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicamentos`
--

CREATE TABLE `medicamentos` (
  `id_medicamento` int(11) NOT NULL,
  `nombre_generico` varchar(45) NOT NULL,
  `nombre_comercial` varchar(45) NOT NULL,
  `existencia_minima` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicamentos_proveedores`
--

CREATE TABLE `medicamentos_proveedores` (
  `id_medicamento_proveedor` int(11) NOT NULL,
  `id_medicamentos` int(11) NOT NULL,
  `id_proveedor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `presentaciones_medicamentos`
--

CREATE TABLE `presentaciones_medicamentos` (
  `id_presentacion_medicamento` int(11) NOT NULL,
  `id_medicamento_proveedor` int(11) NOT NULL,
  `id_tamaño` int(11) NOT NULL,
  `id_tipo_presentacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `id_proveedor` int(11) NOT NULL,
  `nit` varchar(20) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `direccion` varchar(20) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `fax` varchar(20) NOT NULL,
  `ciudad` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `idrol` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`idrol`, `nombre`, `descripcion`, `estado`) VALUES
(1, 'Administrador', 'Rol de administrador del sistema', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tamaños`
--

CREATE TABLE `tamaños` (
  `id_tamaño` int(11) NOT NULL,
  `tipo` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `tamaños`
--

INSERT INTO `tamaños` (`id_tamaño`, `tipo`) VALUES
(1, 'grande'),
(2, 'pequeño');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_presentaciones`
--

CREATE TABLE `tipos_presentaciones` (
  `id_tipo_presentacion` int(11) NOT NULL,
  `tipo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_proveedores`
--

CREATE TABLE `tipos_proveedores` (
  `id_tipo_proveedor` int(11) NOT NULL,
  `tipo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL,
  `idrol` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `tipo_documento` varchar(20) DEFAULT NULL,
  `num_documento` varchar(20) DEFAULT NULL,
  `direccion` varchar(70) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `estado` int(11) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idusuario`, `idrol`, `nombre`, `apellido`, `tipo_documento`, `num_documento`, `direccion`, `telefono`, `email`, `estado`, `password`) VALUES
(1, 1, 'DEVIS', 'VERGARA', 'CEDULA DE CIUDADANIA', '2042', 'sdasd', '301', 'devis@hotmail.com', 1, '123456789');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `id_venta` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `precio_total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias_proveedores`
--
ALTER TABLE `categorias_proveedores`
  ADD PRIMARY KEY (`id_categoria_proveedor`),
  ADD UNIQUE KEY `id_categorias_proveedores_UNIQUE` (`id_categoria_proveedor`),
  ADD KEY `id_proveedor_proveedores_idx` (`id_proveedor`),
  ADD KEY `id_descuento_descuentos_idx` (`id_descuento`),
  ADD KEY `id_tipo_proveedor_tipos_proveedores_idx` (`id_tipo_proveedor`),
  ADD KEY `id_cumplimiento_idx` (`id_cumplimiento`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_cliente`),
  ADD UNIQUE KEY `cc_UNIQUE` (`cc`);

--
-- Indices de la tabla `cumplimientos`
--
ALTER TABLE `cumplimientos`
  ADD PRIMARY KEY (`id_cumplimiento`);

--
-- Indices de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  ADD PRIMARY KEY (`id_descuento`);

--
-- Indices de la tabla `detalles_ventas`
--
ALTER TABLE `detalles_ventas`
  ADD PRIMARY KEY (`id_detalle_venta`),
  ADD KEY `id_venta_venta_idx` (`id_venta`),
  ADD KEY `id_presentacion_medicamento_presentaciones_medicamentos_idx` (`id_presentacion_medicamento`);

--
-- Indices de la tabla `medicamentos`
--
ALTER TABLE `medicamentos`
  ADD PRIMARY KEY (`id_medicamento`);

--
-- Indices de la tabla `medicamentos_proveedores`
--
ALTER TABLE `medicamentos_proveedores`
  ADD PRIMARY KEY (`id_medicamento_proveedor`),
  ADD KEY `id_medicamento_medicamentos_proveedores_idx` (`id_medicamentos`),
  ADD KEY `id_proveedor_medicamentos_proveedores_idx` (`id_proveedor`);

--
-- Indices de la tabla `presentaciones_medicamentos`
--
ALTER TABLE `presentaciones_medicamentos`
  ADD UNIQUE KEY `id_presentaciones_medicamentos_UNIQUE` (`id_presentacion_medicamento`),
  ADD KEY `id_medicamento_proveedor_presentaciones_medicamentos_idx` (`id_medicamento_proveedor`),
  ADD KEY `id_tamaño_presentaciones_medicamentos_idx` (`id_tamaño`),
  ADD KEY `id_tipo_presentacion_tipos_presentaciones_idx` (`id_tipo_presentacion`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`id_proveedor`),
  ADD UNIQUE KEY `nit_UNIQUE` (`nit`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`idrol`);

--
-- Indices de la tabla `tamaños`
--
ALTER TABLE `tamaños`
  ADD PRIMARY KEY (`id_tamaño`);

--
-- Indices de la tabla `tipos_presentaciones`
--
ALTER TABLE `tipos_presentaciones`
  ADD PRIMARY KEY (`id_tipo_presentacion`);

--
-- Indices de la tabla `tipos_proveedores`
--
ALTER TABLE `tipos_proveedores`
  ADD PRIMARY KEY (`id_tipo_proveedor`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idusuario`),
  ADD KEY `idrol` (`idrol`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id_venta`),
  ADD UNIQUE KEY `id_cliente_UNIQUE` (`id_cliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias_proveedores`
--
ALTER TABLE `categorias_proveedores`
  MODIFY `id_categoria_proveedor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `cumplimientos`
--
ALTER TABLE `cumplimientos`
  MODIFY `id_cumplimiento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  MODIFY `id_descuento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalles_ventas`
--
ALTER TABLE `detalles_ventas`
  MODIFY `id_detalle_venta` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `medicamentos`
--
ALTER TABLE `medicamentos`
  MODIFY `id_medicamento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `medicamentos_proveedores`
--
ALTER TABLE `medicamentos_proveedores`
  MODIFY `id_medicamento_proveedor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `presentaciones_medicamentos`
--
ALTER TABLE `presentaciones_medicamentos`
  MODIFY `id_presentacion_medicamento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `id_proveedor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `idrol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tamaños`
--
ALTER TABLE `tamaños`
  MODIFY `id_tamaño` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipos_presentaciones`
--
ALTER TABLE `tipos_presentaciones`
  MODIFY `id_tipo_presentacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipos_proveedores`
--
ALTER TABLE `tipos_proveedores`
  MODIFY `id_tipo_proveedor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idusuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id_venta` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `categorias_proveedores`
--
ALTER TABLE `categorias_proveedores`
  ADD CONSTRAINT `id_cumplimiento_cumplimientos` FOREIGN KEY (`id_cumplimiento`) REFERENCES `cumplimientos` (`id_cumplimiento`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_descuento_descuentos` FOREIGN KEY (`id_descuento`) REFERENCES `descuentos` (`id_descuento`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_proveedor_proveedores` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedores` (`id_proveedor`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_tipo_proveedor` FOREIGN KEY (`id_tipo_proveedor`) REFERENCES `tipos_proveedores` (`id_tipo_proveedor`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `detalles_ventas`
--
ALTER TABLE `detalles_ventas`
  ADD CONSTRAINT `id_presentacion_medicamento_presentaciones_medicamentos` FOREIGN KEY (`id_presentacion_medicamento`) REFERENCES `presentaciones_medicamentos` (`id_presentacion_medicamento`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_venta_venta` FOREIGN KEY (`id_venta`) REFERENCES `ventas` (`id_venta`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `medicamentos_proveedores`
--
ALTER TABLE `medicamentos_proveedores`
  ADD CONSTRAINT `id_medicamento_medicamentos_proveedores` FOREIGN KEY (`id_medicamentos`) REFERENCES `medicamentos` (`id_medicamento`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_proveedor_medicamentos_proveedores` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedores` (`id_proveedor`);

--
-- Filtros para la tabla `presentaciones_medicamentos`
--
ALTER TABLE `presentaciones_medicamentos`
  ADD CONSTRAINT `id_medicamento_proveedor_presentaciones_medicamentos` FOREIGN KEY (`id_medicamento_proveedor`) REFERENCES `medicamentos` (`id_medicamento`),
  ADD CONSTRAINT `id_tamaño_presentaciones_medicamentos` FOREIGN KEY (`id_tamaño`) REFERENCES `tamaños` (`id_tamaño`),
  ADD CONSTRAINT `id_tipo_presentacion_tipos_presentaciones` FOREIGN KEY (`id_tipo_presentacion`) REFERENCES `tipos_presentaciones` (`id_tipo_presentacion`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`idrol`) REFERENCES `rol` (`idrol`);

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `id_clientes_ventas` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
