SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

DROP DATABASE IF EXISTS `db_esame`;
CREATE DATABASE IF NOT EXISTS `db_esame` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `db_esame`;

DROP TABLE IF EXISTS `tariffa_corriere`;
CREATE TABLE `tariffa_corriere` (
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nome_corriere` varchar(20) NOT NULL,
  `nome_tariffa` varchar(20) NOT NULL,
  `peso_massimo` float NOT NULL,
  `costo` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `tariffa_corriere` (`id`, `nome_corriere`, `nome_tariffa`, `peso_massimo`, `costo`) VALUES
(1, 'SDA', 'Economy', 1, 5.9),
(2, 'SDA', 'Veloce', 5, 7.9),
(3, 'SDA', 'Bigbox', 30, 12.9),
(4, 'DHL', 'Veloce', 1.5, 6.5),
(5, 'DHL', 'Assicurata', 7.5, 9.9),
(6, 'DHL', 'XXL', 40, 13.9),
(7, 'UPS', 'Economy', 0.7, 5.2),
(8, 'UPS', 'Standard', 3, 6.9),
(9, 'UPS', 'Jumbo', 25, 11.5);

DROP TABLE IF EXISTS `articolo`;
CREATE TABLE `articolo` (
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `codice` varchar(20) NOT NULL,
  `descrizione` varchar(200) NOT NULL,
  `peso` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `articolo` (`id`, `codice`, `descrizione`, `peso`) VALUES
(1, 'PNDRV8', 'Pen drive USB 8G no brand', 0.15),
(2, 'DCP-J715E', 'Stampante Brother DCP J715 W', 5.3),
(3, 'LNVCX1', 'Notebook Lenovo Carbon X1', 1.9),
(4, 'ALCPPC3', 'Smartphone Alcatel POP C3', 0.53),
(5, 'BSHT1R', 'Ampli Combo valvolare BlackStar HT 1-R', 6);


DROP TABLE IF EXISTS `voce`;
CREATE TABLE `voce` (
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `id_ordine` int(11) NOT NULL,
  `id_articolo` int(11) NOT NULL,
  `quantita` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `voce` (`id`, `id_ordine`, `id_articolo`, `quantita`) VALUES
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

DROP TABLE IF EXISTS `ordine`;
CREATE TABLE `ordine` (
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `numero` int(11) NOT NULL,
  `data` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `ordine` (`id`, `numero`, `data`) VALUES
(1, 12, '19/4'),
(2, 13, '15/6'),
(3, 6, '13/12'),
(4, 15, '16/11'),
(5, 3, '15/5'),
(6, 5, '6/6'),
(7, 9, '6/5'),
(8, 25, '12/13'),
(9, 20, '12/12');

ALTER TABLE `voce`
  ADD KEY `id` (`id_ordine`),
  ADD KEY `id_articolo` (`id_articolo`);

ALTER TABLE `voce`
  ADD CONSTRAINT `ordine_ibfk_1` FOREIGN KEY (`id_ordine`) REFERENCES `ordine` (`id`),
  ADD CONSTRAINT `ordine_ibfk_2` FOREIGN KEY (`id_articolo`) REFERENCES `articolo` (`id`);
COMMIT;

