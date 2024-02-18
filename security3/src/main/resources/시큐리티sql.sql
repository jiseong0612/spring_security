-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        11.4.0-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- security 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `security` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `security`;

-- 테이블 security.admin 구조 내보내기
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(2000) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `provider` varchar(50) DEFAULT NULL,
  `providerid` varchar(50) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- 테이블 데이터 security.admin:~7 rows (대략적) 내보내기
DELETE FROM `admin`;
INSERT INTO `admin` (`id`, `username`, `password`, `email`, `role`, `provider`, `providerid`, `createdate`) VALUES
	(1, 'admin01', '$2a$10$yMDKURT4zb9KpIo3a6OM7OyHHNpln1k6kDRzXIvfGacvOpnZFcjnW', 'admin@gmail.com', 'ROLE_ADMIN', NULL, NULL, '2024-02-18 02:54:38'),
	(2, 'admin02', '$2a$10$yMDKURT4zb9KpIo3a6OM7OyHHNpln1k6kDRzXIvfGacvOpnZFcjnW', 'admin@gmail.com', 'ROLE_ADMIN', NULL, NULL, '2024-02-18 02:55:22'),
	(3, 'admin02', '$2a$10$yMDKURT4zb9KpIo3a6OM7OyHHNpln1k6kDRzXIvfGacvOpnZFcjnW', 'admin@gmail.com', 'ROLE_ADMIN', NULL, NULL, '2024-02-18 02:55:30'),
	(4, 'admin03', '$2a$10$yMDKURT4zb9KpIo3a6OM7OyHHNpln1k6kDRzXIvfGacvOpnZFcjnW', 'admin@gmail.com', 'ROLE_ADMIN', NULL, NULL, '2024-02-18 02:55:31'),
	(5, 'manager01', '$2a$10$yMDKURT4zb9KpIo3a6OM7OyHHNpln1k6kDRzXIvfGacvOpnZFcjnW', 'admin@gmail.com', 'ROLE_MANAGER', NULL, NULL, '2024-02-18 03:00:41'),
	(6, 'manager02', '$2a$10$Ev2cpE/Q/FZf1T8napiXUuU6k5xmYduEOSRfa9.iUiTFm1IFRHeHK', '123', 'ROLE_MANAGER', NULL, NULL, '2024-02-18 22:19:19'),
	(7, 'manager03', '$2a$10$2fnPPv4Mw5MSYW9JWBj18OXA32uqQ685J4hiFrj0oO8ceQoVk/P1.', '123', 'ROLE_MANAGER', NULL, NULL, '2024-02-18 22:23:16');

-- 테이블 security.persistent_logins 구조 내보내기
CREATE TABLE IF NOT EXISTS `persistent_logins` (
  `series` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `token` varchar(50) DEFAULT NULL,
  `last_used` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 security.persistent_logins:~0 rows (대략적) 내보내기
DELETE FROM `persistent_logins`;

-- 테이블 security.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(2000) DEFAULT NULL,
  `password` varchar(2000) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `provider` varchar(50) DEFAULT NULL,
  `providerid` varchar(50) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 security.user:~9 rows (대략적) 내보내기
DELETE FROM `user`;
INSERT INTO `user` (`id`, `username`, `password`, `email`, `role`, `provider`, `providerid`, `createdate`) VALUES
	(1, 'user01', '$2a$10$yMDKURT4zb9KpIo3a6OM7OyHHNpln1k6kDRzXIvfGacvOpnZFcjnW', 'member@gmail.com', 'ROLE_USER', NULL, NULL, '2024-02-18 02:54:38'),
	(2, 'user02', '$2a$10$yMDKURT4zb9KpIo3a6OM7OyHHNpln1k6kDRzXIvfGacvOpnZFcjnW', 'member@gmail.com', 'ROLE_USER', NULL, NULL, '2024-02-18 02:55:22'),
	(3, 'user03', '$2a$10$yMDKURT4zb9KpIo3a6OM7OyHHNpln1k6kDRzXIvfGacvOpnZFcjnW', 'member@gmail.com', 'ROLE_USER', NULL, NULL, '2024-02-18 02:55:30'),
	(4, 'user04', '$2a$10$yMDKURT4zb9KpIo3a6OM7OyHHNpln1k6kDRzXIvfGacvOpnZFcjnW', 'member@gmail.com', 'ROLE_USER', NULL, NULL, '2024-02-18 02:55:31'),
	(5, 'user05', '$2a$10$yMDKURT4zb9KpIo3a6OM7OyHHNpln1k6kDRzXIvfGacvOpnZFcjnW', 'member@gmail.com', 'ROLE_USER', NULL, NULL, '2024-02-18 03:00:41'),
	(6, 'user06', '$2a$10$yMDKURT4zb9KpIo3a6OM7OyHHNpln1k6kDRzXIvfGacvOpnZFcjnW', 'member@gmail.com', 'ROLE_USER', NULL, NULL, '2024-02-18 14:50:06'),
	(13, 'google_117886206361248955217', '$2a$10$qImN8.ummZR8aimYtEqhyuyvvCssIxcwsIpVOAt0KljkE/pM.KEx.', 'member@gmail.com', 'ROLE_USER', 'google', '117886206361248955217', '2024-02-19 00:57:05'),
	(14, 'user07', '$2a$10$ILgp97QkpezRR35N/hnwIuTy4ScJH/SR0laeu0YIdnzf2FHfWLcBO', 'member@gmail.com', 'ROLE_USER', NULL, NULL, '2024-02-19 00:59:08'),
	(15, 'user08', '$2a$10$qEOP8C1JFIrRkRAlaWTo1.oMqTcotMWQDOciqR5H/ICjso1d2EbS2', 'member@gmail.com', 'ROLE_USER', NULL, NULL, '2024-02-19 00:59:58');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
