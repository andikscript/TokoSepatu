-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 04, 2022 at 05:14 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `store`
--

-- --------------------------------------------------------

--
-- Table structure for table `brand`
--

CREATE TABLE `brand` (
  `id_brand` varchar(3) NOT NULL,
  `nama_brand` varchar(1024) NOT NULL,
  `kategori` varchar(1024) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`id_brand`, `nama_brand`, `kategori`) VALUES
('B01', 'Converse', 'Sneakers'),
('B02', 'Converse', 'Wedges'),
('B03', 'Converse', 'Flat Shoes'),
('B04', 'Converse', 'Boots'),
('B05', 'Adidas', 'Sneakers'),
('B06', 'Vans', 'Sneakers'),
('B07', 'League', 'Sneakers'),
('B08', 'League', 'Wedges');

-- --------------------------------------------------------

--
-- Table structure for table `sepatu`
--

CREATE TABLE `sepatu` (
  `id` int(11) NOT NULL,
  `nama_produk` varchar(1024) NOT NULL,
  `ukuran` int(4) NOT NULL,
  `warna` varchar(255) NOT NULL,
  `stok` mediumint(9) NOT NULL,
  `harga` int(11) NOT NULL,
  `id_brand` varchar(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sepatu`
--

INSERT INTO `sepatu` (`id`, `nama_produk`, `ukuran`, `warna`, `stok`, `harga`, `id_brand`) VALUES
(1, 'Converse', 38, 'Kuning', 100, 150000, 'B01'),
(2, 'Converse', 39, 'Kuning', 100, 200000, 'B01'),
(3, 'Converse', 40, 'Kuning', 50, 250000, 'B02'),
(4, 'Converse', 41, 'Kuning', 45, 280000, 'B03'),
(5, 'Converse', 38, 'Hitam', 100, 400000, 'B04'),
(6, 'Converse ', 39, 'Hitam', 150, 350000, 'B01'),
(7, 'Converse', 40, 'Hitam', 200, 360000, 'B01'),
(8, 'Converse', 41, 'Hitam', 78, 350000, 'B01'),
(11, 'Converse', 38, 'Merah', 100, 150000, 'B01'),
(12, 'Converse', 39, 'Merah', 100, 150000, 'B01'),
(13, 'Converse', 40, 'Merah', 100, 150000, 'B02'),
(14, 'Converse', 41, 'Merah', 100, 150000, 'B03'),
(15, 'Adidas', 38, 'Kuning', 50, 150000, 'B05'),
(16, 'Adidas', 39, 'Kuning', 50, 150000, 'B05'),
(17, 'Adidas', 40, 'Kuning', 50, 150000, 'B05'),
(18, 'Adidas', 41, 'Kuning', 50, 150000, 'B05');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `nama` varchar(128) NOT NULL,
  `email` varchar(256) NOT NULL,
  `username` varchar(256) NOT NULL,
  `password` varchar(2048) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `nama`, `email`, `username`, `password`) VALUES
(2, 'andik', 'andikmindai@gmail.com', 'andikmindai', '266bff91c85b0d17c5b50033abce09b62cbc7f4610c6792f5108593de65ad3f8'),
(3, 'andik', 'andi@gmail.com', 'andik', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`id_brand`),
  ADD KEY `id_brand` (`id_brand`);

--
-- Indexes for table `sepatu`
--
ALTER TABLE `sepatu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_brand` (`id_brand`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `id_user` (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `sepatu`
--
ALTER TABLE `sepatu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `sepatu`
--
ALTER TABLE `sepatu`
  ADD CONSTRAINT `sepatu_ibfk_1` FOREIGN KEY (`id_brand`) REFERENCES `brand` (`id_brand`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
