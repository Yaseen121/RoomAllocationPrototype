-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Mar 13, 2018 at 01:18 AM
-- Server version: 5.6.34-log
-- PHP Version: 7.1.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `roomallocation`
--

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

CREATE TABLE `bookings` (
  `bookingID` int(11) NOT NULL,
  `roomID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `startTime` datetime NOT NULL,
  `OPEN` tinyint(1) NOT NULL,
  `Queue1` int(11) DEFAULT NULL,
  `Queue2` int(11) DEFAULT NULL,
  `Queue3` int(11) DEFAULT NULL,
  `Queue4` int(11) DEFAULT NULL,
  `Queue5` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookings`
--

INSERT INTO `bookings` (`bookingID`, `roomID`, `userID`, `startTime`, `OPEN`, `Queue1`, `Queue2`, `Queue3`, `Queue4`, `Queue5`) VALUES
(1, 2, 1, '2018-02-23 13:00:00', 1, NULL, NULL, NULL, NULL, NULL),
(2, 5, 2, '2018-02-23 17:00:00', 0, NULL, NULL, NULL, NULL, NULL),
(3, 4, 3, '2018-02-25 13:00:00', 0, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `openhours`
--

CREATE TABLE `openhours` (
  `USERTYPE` enum('User') NOT NULL,
  `STARTHOUR` varchar(8) NOT NULL,
  `ENDHOUR` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `openhours`
--

INSERT INTO `openhours` (`USERTYPE`, `STARTHOUR`, `ENDHOUR`) VALUES
('User', '07:00:00', '20:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `ROOMID` int(11) NOT NULL,
  `ROOMNAME` varchar(30) NOT NULL,
  `ROOMTYPE` enum('Classroom','Lab','LectureHall') NOT NULL,
  `CAPACITY` int(11) NOT NULL,
  `DEPT` varchar(30) NOT NULL,
  `PERMITTEDUSERS` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`ROOMID`, `ROOMNAME`, `ROOMTYPE`, `CAPACITY`, `DEPT`, `PERMITTEDUSERS`) VALUES
(2, 'room1', 'Classroom', 50, 'eecs', 8),
(3, 'room2', 'Lab', 80, 'eecs', 2),
(4, 'room3', 'LectureHall', 300, 'eecs', 2),
(5, 'roomGeo', 'Classroom', 40, 'geo', 8);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `USERID` int(30) NOT NULL,
  `PASSWORD` varchar(30) NOT NULL,
  `USERTYPE` enum('Student','Staff','Guest','Admin') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`USERID`, `PASSWORD`, `USERTYPE`) VALUES
(1, '123', 'Student'),
(2, '123', 'Student'),
(3, '123', 'Staff'),
(4, '123', 'Admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`bookingID`);

--
-- Indexes for table `openhours`
--
ALTER TABLE `openhours`
  ADD PRIMARY KEY (`USERTYPE`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`ROOMID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`USERID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bookings`
--
ALTER TABLE `bookings`
  MODIFY `bookingID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `ROOMID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `USERID` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
