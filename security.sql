-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.4.31-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.6.0.6765
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

-- 테이블 security.board 구조 내보내기
CREATE TABLE IF NOT EXISTS `board` (
  `bno` int(11) NOT NULL AUTO_INCREMENT,
  `id` int(11) DEFAULT NULL,
  `title` varchar(200) NOT NULL,
  `writer` varchar(200) NOT NULL,
  `regdate` datetime DEFAULT current_timestamp(),
  `updatedate` datetime DEFAULT current_timestamp(),
  KEY `bno` (`bno`),
  KEY `FK_board_user` (`id`),
  CONSTRAINT `FK_board_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 security.board:~13 rows (대략적) 내보내기
INSERT INTO `board` (`bno`, `id`, `title`, `writer`, `regdate`, `updatedate`) VALUES
	(6, NULL, 'aaa', 'sssss', '2023-11-27 23:13:10', '2023-11-27 23:13:10'),
	(7, 7, '22222', '22222222', '2023-11-27 23:13:51', '2023-11-27 23:13:51'),
	(8, 6, '3333', '3333', '2023-11-27 23:58:00', '2023-11-27 23:58:00'),
	(9, 5, '4444', '4444', '2023-11-28 00:00:47', '2023-11-28 00:00:47'),
	(11, 5, '5555', '55555', '2023-11-28 00:00:58', '2023-11-28 00:00:58'),
	(14, 6, '88888', '88888', '2023-11-28 00:03:15', '2023-11-28 00:03:15'),
	(15, 5, 'zzzz', 'zzzzz', '2023-11-28 00:52:13', '2023-11-28 00:52:13'),
	(16, 5, 'asd', 'asd', '2023-11-28 00:52:17', '2023-11-28 00:52:17'),
	(17, 5, '123123', '123123', '2023-11-28 00:52:21', '2023-11-28 00:52:21'),
	(18, 5, '2222', '222', '2023-11-28 00:52:29', '2023-11-28 00:52:29'),
	(19, 5, '222', '2', '2023-11-28 00:52:41', '2023-11-28 00:52:41'),
	(20, 6, 'eeeeeeee', 'eee', '2023-11-28 00:58:41', '2023-11-28 00:58:41'),
	(21, 6, 'ㄷㄷㄷ', 'ㄷㄷㄷ', '2023-11-28 00:58:45', '2023-11-28 00:58:45');

-- 테이블 security.persistent_logins 구조 내보내기
CREATE TABLE IF NOT EXISTS `persistent_logins` (
  `series` varchar(64) NOT NULL,
  `username` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  KEY `series` (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 security.persistent_logins:~1 rows (대략적) 내보내기
INSERT INTO `persistent_logins` (`series`, `username`, `token`, `last_used`) VALUES
	('35OB0Q9lcilTEkVJZr6+BQ==', 'manager1', 'tc9iOI0PqlA0dFV9sknhmw==', '2023-12-02 02:15:55');

-- 테이블 security.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT current_timestamp(),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 security.user:~7 rows (대략적) 내보내기
INSERT INTO `user` (`id`, `username`, `password`, `email`, `role`, `createdate`) VALUES
	(1, 'hth3333', '$2a$10$gvu0TyQC5MdkjAE38wqEXuevN39YKjhC5FfZegEUL2qzycFhN.Cke', 'hth5545@gmail.com', 'ROLE_USER', '2023-11-26 12:48:30'),
	(2, 'hth4444', '$2a$10$fPNMIT/tluKF6Aj/8EFyeuCJf1vg37.9gOT5d1X0E9gTS6urKSFru', 'hth5545@gmail.com', 'ROLE_USER', '2023-11-26 13:45:02'),
	(3, 'hth5555', '$2a$10$0str77CBfHmXjFfdbCEEYOJO.JxuqMdYtsrohmRvaKnC3xYALV5I.', 'hth5545@gmail.com', 'ROLE_MANAGER', '2023-11-26 13:47:08'),
	(4, 'hth6666', '$2a$10$dDVL0X7tp../qINxH.WjSulPin1qFM9a.WvlHpdGwFRWpfmASFIVG', 'hth5545@gmail.com', 'ROLE_ADMIN', '2023-11-26 13:47:49'),
	(5, 'user1', '$2a$10$f9AUwBmHmACOgLUvNRuII.NKeiJ/3w/ej42SEZVSM0wRkoE06v.iu', 'user@gmail.com', 'ROLE_USER', '2023-11-26 21:15:20'),
	(6, 'manager1', '$2a$10$renfgjiVhB/VWFjWGMsr1OraXvCb1Oj4JOuxVzyf0J5whnxhuVi/i', 'manager1@gmail.com', 'ROLE_MANAGER', '2023-11-26 21:15:51'),
	(7, 'admin1', '$2a$10$yUwkBP3oFPu459Kq4C7P7ORn.Qy5ugER8OWIFQM/4.awa1bi4nxLS', 'admin1@gmail.com', 'ROLE_ADMIN', '2023-11-26 21:16:02');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
