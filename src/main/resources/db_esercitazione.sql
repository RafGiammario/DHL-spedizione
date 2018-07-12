-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 13, 2018 at 01:02 PM
-- Server version: 5.7.21-0ubuntu0.16.04.1
-- PHP Version: 7.0.28-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_esercitazione`
--
DROP DATABASE IF EXISTS `db_esercitazione`;
CREATE DATABASE IF NOT EXISTS `db_esercitazione` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `db_esercitazione`;

-- --------------------------------------------------------

--
-- Table structure for table `costo_mezzo_trasporto`
--

DROP TABLE IF EXISTS `costo_mezzo_trasporto`;
CREATE TABLE `costo_mezzo_trasporto` (
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nome_mezzo` varchar(20) NOT NULL,
  `peso_massimo` float NOT NULL,
  `costo` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `costo_mezzo_trasporto`
--

INSERT INTO `costo_mezzo_trasporto` (`id`, `nome_mezzo`, `peso_massimo`, `costo`) VALUES
(1, 'A piedi', 1, 5.9),
(2, 'Bicicletta', 5, 7.9),
(3, 'Ape', 30, 12.9),
(4, 'Drone piccolo', 1.5, 6.5),
(5, 'Scooter', 7.5, 9.9),
(6, 'Furgone', 40, 13.9),
(7, 'Piccione viaggiatore', 0.7, 5.2),
(8, 'Drone grande', 3, 6.9),
(9, 'Automobile', 25, 11.5);

-- --------------------------------------------------------

--
-- Table structure for table `merce`
--

DROP TABLE IF EXISTS `merce`;
CREATE TABLE `merce` (
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `codice` varchar(20) NOT NULL,
  `descrizione` varchar(200) NOT NULL,
  `peso` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `merce`
--

INSERT INTO `merce` (`id`, `codice`, `descrizione`, `peso`) VALUES
(1, 'PNDRV8', 'Pen drive USB 64G no brand', 0.15),
(2, 'DCP-J715E', 'Stampante Brother DCP J715 W', 5.3),
(3, 'LNVCX1', 'Notebook Lenovo Carbon X1', 1.9),
(4, 'HUP20', 'Smartphone Huawei P20', 0.53),
(5, 'BSHT1R', 'Ampli Combo valvolare BlackStar HT 1-R', 6);

-- --------------------------------------------------------

--
-- Table structure for table `merce_spedizione`
--

DROP TABLE IF EXISTS `merce_spedizione`;
CREATE TABLE `merce_spedizione` (
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `id_spedizione` int(11) NOT NULL,
  `id_merce` int(11) NOT NULL,
  `quantita` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `merce_spedizione`
--

INSERT INTO `merce_spedizione` (`id`, `id_spedizione`, `id_merce`, `quantita`) VALUES
(1, 1, 3, 2),
(2, 2, 2, 1),
(3, 2, 3, 1),
(4, 3, 3, 1),
(5, 3, 4, 1),
(6, 4, 1, 2),
(7, 4, 4, 1),
(8, 5, 4, 2),
(9, 5, 1, 1),
(10, 6, 1, 3),
(11, 7, 2, 7),
(12, 8, 5, 1),
(13, 8, 3, 1),
(14, 9, 5, 6);

-- --------------------------------------------------------

--
-- Table structure for table `spedizione`
--

DROP TABLE IF EXISTS `spedizione`;
CREATE TABLE `spedizione` (
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `numero` int(11) NOT NULL,
  `data` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `spedizione`
--

INSERT INTO `spedizione` (`id`, `numero`, `data`) VALUES
(1, 12, '19/4'),
(2, 13, '15/6'),
(3, 6, '13/12'),
(4, 15, '16/11'),
(5, 3, '15/5'),
(6, 5, '6/6'),
(7, 9, '6/5'),
(8, 25, '12/13'),
(9, 20, '12/12');

--
-- Indexes for table `merce_spedizione`
--
ALTER TABLE `merce_spedizione`
  ADD KEY `id` (`id_spedizione`),
  ADD KEY `id_merce` (`id_merce`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `merce_spedizione`
--
ALTER TABLE `merce_spedizione`
  ADD CONSTRAINT `merce_spedizione_ibfk_1` FOREIGN KEY (`id_spedizione`) REFERENCES `spedizione` (`id`),
  ADD CONSTRAINT `merce_spedizione_ibfk_2` FOREIGN KEY (`id_merce`) REFERENCES `merce` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
