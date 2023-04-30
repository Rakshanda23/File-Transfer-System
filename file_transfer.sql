-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 28, 2023 at 01:05 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `file_transfer`
--

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `trans_id` int(11) NOT NULL,
  `sender_name` varchar(50) NOT NULL,
  `receiver_name` varchar(50) NOT NULL,
  `file_name` varchar(50) NOT NULL,
  `time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`trans_id`, `sender_name`, `receiver_name`, `file_name`, `time`) VALUES
(1, 'abc', 'aaa', 'a', '2023-04-12 00:00:00'),
(2, 'abc', 'bbb', 'b', '2023-04-15 00:00:00'),
(3, 'abc', 'ccc', 'c', '2023-04-07 00:00:00'),
(4, 'sdf', 'fff', 'f', '2023-04-19 00:00:00'),
(5, 'sss', 'rrr', 'file1', '2023-04-16 00:00:00'),
(11, 'sdv', 'rag', 'file10', '2023-04-25 00:00:00'),
(12, 'sdv', 'rag', 'file10', '2023-04-25 00:00:00'),
(13, 'sdv', 'rag', 'file10', '2023-04-25 00:00:00'),
(14, 'sdv', 'rag', 'file10', '2023-04-25 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(50) NOT NULL,
  `user_name` varchar(150) NOT NULL,
  `ip_address` varchar(150) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `password` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_name`, `ip_address`, `status`, `password`) VALUES
(1, 'abc', '39847', 1, ''),
(2, 'xyx', '39.84.7', 0, ''),
(3, 'rs', '1.23.28', 0, 'ldljj'),
(4, 'rs', '1.23.28', 0, 'ldljj'),
(5, 'rs', '1.23.28', 0, 'ldljj'),
(6, 'rs', '1.23.28', 0, 'ldljj'),
(7, 'rsv', '1.23.28', 0, 'alwaysnforever'),
(8, 'rag', '1.23.28', 1, 'alwaysnforever'),
(9, 'rsv', '1.23.28', 0, 'alwaysnforever'),
(10, 'rsv', '1.23.28', 0, 'alwaysnforever'),
(11, 'rsv', '1.23.28', 0, 'alwaysnforever'),
(12, 'rsv', '1.23.28', 0, 'alwaysnforever');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`trans_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `trans_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
