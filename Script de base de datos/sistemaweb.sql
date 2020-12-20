-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 20-12-2020 a las 00:45:45
-- Versión del servidor: 8.0.22
-- Versión de PHP: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistemaweb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `todos`
--

CREATE TABLE `todos` (
  `id` varchar(80) NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `descripcion` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `todos`
--

INSERT INTO `todos` (`id`, `nombre`, `descripcion`) VALUES
('0a83d639-a8b7-45da-a6f9-0af9d95e4edb', 'recoger basura', '							recoger la basura de la casa\n a las 9pm\n						'),
('4712031d-2eb8-40e1-8e13-8336d479a415', 'recoger basura', 'recoger la basura de la casa a las 9pm'),
('7d99951f-8211-4954-abf8-35f542eaf7b1', 'recoger cama', 'recoger la cama a las 3am'),
('5a99dbeb-1014-46ed-b0d1-67a7e781355e', 'recoger cama', 'recoger la cama a las 3am'),
('c8dddf92-1d6a-4f40-b3e7-78e57acf9b07', 'Esperar la navidad', '							Preparar ponche y canelones \nel 23 en la noche\n						');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
