-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-05-2021 a las 11:27:22
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tienda_online`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`, `descripcion`) VALUES
(5, 'TodoTerreno', 'Coches Todoterreno, 4x4, ideales para la conduccion por cualquier tipo de terreno'),
(6, 'Deportivo', 'Coches aerodinamicos rapidos ideales para gente que le guste la adrenalina'),
(7, 'Turismo', 'Coches de calle standar familiares');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `configuracion`
--

CREATE TABLE `configuracion` (
  `id` int(11) NOT NULL,
  `clave` varchar(255) DEFAULT NULL,
  `valor` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `configuracion`
--

INSERT INTO `configuracion` (`id`, `clave`, `valor`, `tipo`) VALUES
(1, 'numFactura', '6', 'entero');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descuentos`
--

CREATE TABLE `descuentos` (
  `id` int(11) NOT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `descuento` float DEFAULT NULL,
  `fecha_inicio` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `fecha_fin` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalles_pedido`
--

CREATE TABLE `detalles_pedido` (
  `id` int(11) NOT NULL,
  `id_pedido` int(11) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `precio_unidad` float DEFAULT NULL,
  `unidades` int(11) DEFAULT NULL,
  `impuesto` float DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detalles_pedido`
--

INSERT INTO `detalles_pedido` (`id`, `id_pedido`, `id_producto`, `precio_unidad`, `unidades`, `impuesto`, `total`) VALUES
(2, 6, 2, 100000, 2, 21, 200000),
(3, 7, 2, 100000, 2, 21, 200000),
(4, 7, 8, 2000000, 1, 21, 2000000),
(5, 8, 12, 2, 1, 10, 2),
(6, 9, 12, 2, 1, 10, 2),
(7, 10, 7, 1500, 2, 21, 3000),
(8, 11, 2, 100000, 1, 21, 100000),
(9, 11, 5, 50000, 1, 21, 50000),
(10, 12, 2, 100000, 2, 21, 200000),
(11, 12, 5, 50000, 1, 21, 50000),
(12, 13, 9, 15000, 1, 10, 15000),
(13, 13, 6, 15000, 1, 10, 15000),
(14, 14, 10, 25000, 1, 10, 25000),
(15, 15, 9, 15000, 1, 10, 15000),
(16, 15, 10, 25000, 1, 10, 25000),
(17, 15, 11, 120000, 1, 10, 120000),
(18, 16, 2, 100000, 2, 21, 200000),
(19, 16, 5, 50000, 1, 21, 50000),
(20, 16, 6, 15000, 1, 10, 15000),
(21, 16, 7, 1500, 1, 21, 1500),
(22, 17, 14, 18000, 2, 10, 36000),
(23, 17, 11, 120000, 1, 10, 120000),
(24, 18, 8, 2000000, 1, 21, 2000000),
(25, 18, 9, 15000, 2, 10, 30000),
(26, 18, 11, 120000, 1, 10, 120000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `impuestos`
--

CREATE TABLE `impuestos` (
  `id` int(11) NOT NULL,
  `impuesto` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodos_pago`
--

CREATE TABLE `metodos_pago` (
  `id` int(11) NOT NULL,
  `metodo_pago` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `opciones_menu`
--

CREATE TABLE `opciones_menu` (
  `id` int(11) NOT NULL,
  `id_rol` int(11) DEFAULT NULL,
  `opcion` varchar(255) DEFAULT NULL,
  `ruta` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `opciones_menu`
--

INSERT INTO `opciones_menu` (`id`, `id_rol`, `opcion`, `ruta`) VALUES
(1, 2, 'Ver Productos', '/productos'),
(2, 2, 'Ver Usuarios', '/usuarios'),
(4, 2, 'Ver enviados', '/pedidos/enviar'),
(5, 1, 'Ver cancelados', '/pedidos/cancelar');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `metodo_pago` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `num_factura` varchar(255) DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`id`, `id_usuario`, `fecha`, `metodo_pago`, `estado`, `num_factura`, `total`) VALUES
(6, 3, '2021-05-13 09:42:26', 'paypal', 'enviado', 'factura-1', 200000),
(7, 3, '2021-05-13 09:42:35', 'paypal', 'enviado', 'factura-2', 2200000),
(10, 4, '2021-05-12 07:13:59', 'paypal', 'cancelado', NULL, 3000),
(11, 3, '2021-05-12 07:56:32', 'tarjeta', 'Sin confirmar', NULL, 150000),
(12, 3, '2021-05-12 09:15:32', 'tarjeta', 'Sin confirmar', NULL, 250000),
(13, 4, '2021-05-13 09:42:43', 'tarjeta', 'enviado', 'factura-3', 30000),
(14, 3, '2021-05-13 08:10:43', 'paypal', 'Sin confirmar', NULL, 25000),
(15, 3, '2021-05-13 12:32:24', 'tarjeta', 'enviado', 'factura-4', 160000),
(16, 4, '2021-05-13 17:54:11', 'paypal', 'cancelado', NULL, 266500),
(17, 4, '2021-05-14 07:34:34', 'paypal', 'enviado', 'factura-5', 156000),
(18, 4, '2021-05-14 08:42:19', 'tarjeta', 'enviado', 'factura-6', 2150000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `id_categoria` int(11) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `fecha_alta` timestamp NOT NULL DEFAULT current_timestamp(),
  `fecha_baja` timestamp NULL DEFAULT NULL,
  `impuesto` float DEFAULT NULL,
  `imagen` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `id_categoria`, `nombre`, `descripcion`, `precio`, `stock`, `fecha_alta`, `fecha_baja`, `impuesto`, `imagen`) VALUES
(2, 5, 'Audi A9', 'Audi A9 SIN FULL EQUIP', 100000, 3, '2021-05-13 11:42:38', NULL, 21, 'https://espaciocoches.com/wp-content/uploads/2017/08/audi-a9-nicecarsinfo.jpg'),
(5, 6, 'Audi A8', 'Audi A8', 50000, 97, '2021-05-13 11:42:26', NULL, 21, 'https://upload.wikimedia.org/wikipedia/commons/d/d9/Audi_A8_L_D5_IMG_0066.jpg'),
(6, 7, 'Renault Captur', 'Renault Captur Full Equip', 15000, 148, '2021-05-11 17:28:50', NULL, 10, 'https://www.motor.es/fotos-noticias/2020/01/precio-renault-captur-glp-202064230-1579686863_1.jpg'),
(7, 7, 'Citroen AX', 'Citroen AX basico del año 1990', 1500, 147, '2021-05-14 07:43:56', NULL, 21, 'https://www.automotorsl.com/wp-content/uploads/2018/08/20-anos-citroen-ax-zx-AUTOMOTOR-TOLEDO-11-940x477.jpg'),
(8, 6, 'Ferrari F10', 'Ferrari F10 F1', 2000000, 0, '2021-05-09 22:00:00', '2021-07-28 22:00:00', 21, 'https://i1.wp.com/noticias.coches.com/wp-content/uploads/2020/01/Ferrari-F10-Formula-1.jpg?w=785&ssl=1'),
(9, 6, 'Mazda RX-8', 'Mazda RX-8 Full equip', 15000, 12, '2021-05-11 17:24:11', NULL, 10, 'https://www.autoscout24.es/assets/auto/images/model/mazda/mazda-rx-8/mazda-rx-8-l-03.jpg'),
(10, 5, 'BMW Serie 2', 'BMW Serie 2 Full Equip', 25000, 23, '2021-05-11 17:24:35', NULL, 10, 'https://soymotor.com/sites/default/files/imagenes/noticia/bmw-serie-2-2017.jpg'),
(11, 6, 'Mercedes SLR Mclaren', 'Mercedes SLR Mclaren Basico', 120000, 89, '2021-05-11 17:25:38', NULL, 10, 'https://www.diariomotor.com/imagenes/2020/04/2008-mercedes-benz-slr-mclaren-roadster-_12.jpg'),
(14, 7, 'Wolksvagen golf R32', 'Wolksvagen golf R32 Full Equip', 18000, 18, '2021-05-14 07:27:59', NULL, 10, 'https://cdn.motor1.com/images/mgl/G6VbA/s1/vw-golf-r-2021.jpg'),
(16, 6, 'Coche 1', 'Coche', 1000, 10, '2021-05-14 08:46:27', NULL, 10, 'https://cochesmiticos.com//wp-content/uploads/2018/08/iStock-825047116.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `cif` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `rol` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `rol`) VALUES
(1, 'Admin'),
(2, 'Empleado'),
(3, 'Cliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `id_rol` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `clave` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido1` varchar(255) DEFAULT NULL,
  `apellido2` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `imagen` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `id_rol`, `email`, `clave`, `nombre`, `apellido1`, `apellido2`, `direccion`, `localidad`, `provincia`, `telefono`, `dni`, `imagen`) VALUES
(3, 1, 'admin@admin.es', 'jAcbewsh347mOCf3DwuXgqWsJE/wdqv3M0x97ii1UBkiyKohDNk7XhxSd4+qT4Ke', 'Jefazo', 'admin', 'admin', 'C/ Admin', 'Caceres', 'Cáceres', '6161660', '981919191e', 'https://i.ytimg.com/vi/Lk5sv599prA/hqdefault.jpg'),
(4, 3, 'cliente@cliente.es', 'cWd68Cvz3qYJP/x5+0dpevybtWztCo1njZ+3CzvA4oPZlcPA/L8Ihlv0/wUzVuhU', 'Cliente Vip', 'cliente', 'App2', 'C/ Cliente 1234', '', 'Almería', '61695678', '', 'https://ceslava.s3-accelerate.amazonaws.com/2016/04/mistery-man-gravatar-wordpress-avatar-persona-misteriosa-510x510.png'),
(5, 2, 'empleado@empleado.es', 'jAcbewsh347mOCf3DwuXgqWsJE/wdqv3M0x97ii1UBkiyKohDNk7XhxSd4+qT4Ke', 'Empleado', 'empleado', 'empleado', 'C/ Empleado', '', 'Albacete', '', '9568469t', 'https://ceslava.s3-accelerate.amazonaws.com/2016/04/mistery-man-gravatar-wordpress-avatar-persona-misteriosa-510x510.png'),
(6, 2, 'empleado2@prueba.es', 'MCdytws+6lqzNGVXQkpi1VHk0NWUZsLUmDxOvNC+mwkXMtxiuom3cc2qNWdmySox', '', '', '', '', '', 'Albacete', '', '', 'https://ceslava.s3-accelerate.amazonaws.com/2016/04/mistery-man-gravatar-wordpress-avatar-persona-misteriosa-510x510.png'),
(8, 3, 'arturohernnde@gmail.com', 'dihXPFMT1+QntWuPP/c7nFWq8S7O+HOb2EVxkMginC6m3kRwkiPQO0+83gDWza1j', 'Arturo', 'Hernandez', 'Nuñez', 'C/ calle', 'Zamora', 'Zamora', '980161644', '71028898e', 'https://ceslava.s3-accelerate.amazonaws.com/2016/04/mistery-man-gravatar-wordpress-avatar-persona-misteriosa-510x510.png'),
(9, 3, 'prueba@prueba.es', 'P37lSsCaHkevm44NruKBVb0zTHbeBAdv8DgxL91X8hYdfl2Um5xrM98tfskaxYdE', 'dsaffda', 'dsafdsaf', 'dsafsadf', '', '', '', '', '', 'https://ceslava.s3-accelerate.amazonaws.com/2016/04/mistery-man-gravatar-wordpress-avatar-persona-misteriosa-510x510.png'),
(10, 3, 'prueba@prub.es', '3GAV/sNf1RDeFYeV/EgXk3klWJ+7hE2QVcJ0hnlnXdtpTXVIxmCV3KxShsE7I7hf', 'prueba', 'prueba', 'prueba', 'C/ Burgos', 'Burgos', 'Burgos', '980165103', '71028898e', 'https://ceslava.s3-accelerate.amazonaws.com/2016/04/mistery-man-gravatar-wordpress-avatar-persona-misteriosa-510x510.png'),
(11, 3, 'hernandezarturo224@gmail.com', 'Sh701Eajg+CN8ArxUJP+O93PX44vwCty8JumD4TkXnw5xobDLgxggoHe7sAlh/N+', 'Arturo', 'Hdez', 'Nunez', 'C/ Zamora', 'Zamora', 'Zamora', '980563248', '71028898e', 'https://ceslava.s3-accelerate.amazonaws.com/2016/04/mistery-man-gravatar-wordpress-avatar-persona-misteriosa-510x510.png'),
(12, 3, 'arturohernandez1@usal.es', '+nz05I/3b3mrqHrzM74KaqoTZleiFR657ONuFQ9Zjs7BEMRqZCwdonXClfv5HFLM', 'Arturo', 'Hernandez', 'Nuñez', 'C/ Avda Requejo', 'Zamora', 'Zamora', '61698673', '71028898e', 'https://ceslava.s3-accelerate.amazonaws.com/2016/04/mistery-man-gravatar-wordpress-avatar-persona-misteriosa-510x510.png'),
(19, 3, 'cliente234@cliente.es', 'AZL2Yqr+ovQ7I19n5RqFtUYl89EBAVJOuwwuUUe8WoOi6+4f0lnpIS2ZSmVeqyE6', 'sadf', 'dsfsa', '', '', '', 'Cáceres', '', 'dsafdfa', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valoraciones`
--

CREATE TABLE `valoraciones` (
  `id` int(11) NOT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `valoracion` int(11) DEFAULT NULL,
  `comentario` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `valoraciones`
--

INSERT INTO `valoraciones` (`id`, `id_producto`, `id_usuario`, `valoracion`, `comentario`) VALUES
(1, 2, 3, 4, 'Genial'),
(2, 5, 3, 5, 'Estupendo'),
(3, 6, 3, 3, 'Normalillo'),
(4, 7, 3, 3, 'Normalillo'),
(5, 2, 1, 3, 'Olee'),
(6, 2, 3, 5, 'Coche excepcional'),
(7, 8, 3, 4, 'Rapido pero no ganó el mundial :('),
(8, 9, 3, 4, 'Bonito pero no lo potente que me esperaba'),
(9, 10, 3, 3, 'Muy caro para lo que da'),
(10, 11, 3, 5, 'Increible obra de ingenieria'),
(11, 9, 4, 5, 'Me encanta, muy comodo y buena relación calidad precio'),
(12, 6, 4, 2, 'Se me rompio a los 3 meses y no me ofrecieron solucion'),
(13, 6, 4, 2, 'Se me rompio a los 3 meses y no me ofrecieron solucion'),
(14, 14, 4, 5, 'Es muy bonito');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `detalles_pedido`
--
ALTER TABLE `detalles_pedido`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `impuestos`
--
ALTER TABLE `impuestos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `opciones_menu`
--
ALTER TABLE `opciones_menu`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalles_pedido`
--
ALTER TABLE `detalles_pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `impuestos`
--
ALTER TABLE `impuestos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `opciones_menu`
--
ALTER TABLE `opciones_menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
