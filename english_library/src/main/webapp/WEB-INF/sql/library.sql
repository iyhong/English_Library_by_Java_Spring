-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        5.5.32 - MySQL Community Server (GPL)
-- 서버 OS:                        Win32
-- HeidiSQL 버전:                  8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- library 의 데이터베이스 구조 덤핑
CREATE DATABASE IF NOT EXISTS `library` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `library`;


-- 테이블 library의 구조를 덤프합니다. book
CREATE TABLE IF NOT EXISTS `book` (
  `book_code` int(11) NOT NULL AUTO_INCREMENT,
  `library_id` varchar(50) NOT NULL,
  `state_no` int(10) NOT NULL DEFAULT '1',
  `genre_no` int(10) NOT NULL,
  `book_name` varchar(50) NOT NULL,
  `book_author` varchar(50) NOT NULL,
  `book_publisher` varchar(50) NOT NULL,
  `book_firstday` datetime DEFAULT NULL,
  `book_totalday` int(11) NOT NULL DEFAULT '0',
  `book_totalcount` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`book_code`),
  KEY `FK__library` (`library_id`),
  KEY `FK__state` (`state_no`),
  KEY `FK__genre` (`genre_no`),
  CONSTRAINT `FK__genre` FOREIGN KEY (`genre_no`) REFERENCES `genre` (`genre_no`),
  CONSTRAINT `FK__library` FOREIGN KEY (`library_id`) REFERENCES `library` (`library_id`),
  CONSTRAINT `FK__state` FOREIGN KEY (`state_no`) REFERENCES `state` (`state_no`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Dumping data for table library.book: ~10 rows (대략적)
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`book_code`, `library_id`, `state_no`, `genre_no`, `book_name`, `book_author`, `book_publisher`, `book_firstday`, `book_totalday`, `book_totalcount`) VALUES
	(1, 'l01', 3, 1, 'java basic', '박성환', '스마트정보교육원', NULL, 0, 0),
	(2, 'l01', 1, 1, 'java script', '임백준', '한빛미디어', NULL, 0, 0),
	(3, '4', 1, 1, 'c++', '4', '4', NULL, 0, 0),
	(4, '4', 2, 2, 'node.js', '1', '1', '2017-01-26 17:28:06', 0, 1),
	(5, '4', 2, 1, '12', '12', '12', '2017-01-26 17:08:50', 22, 2),
	(6, '4', 1, 2, 'aaa', 'aa', 'aa', NULL, 0, 0),
	(7, '4', 1, 2, 'ff', 'ff', 'ff', NULL, 0, 0),
	(8, '4', 1, 4, 'gogo', 'nana', 'ju', NULL, 0, 0),
	(9, '4', 1, 3, 'asdf', 'afd', 'asdf', '2017-01-26 17:25:10', 2, 1),
	(10, '1', 1, 1, 'umin', 'umin', 'umin', '2017-01-26 17:33:08', 7, 1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;


-- 테이블 library의 구조를 덤프합니다. disposal
CREATE TABLE IF NOT EXISTS `disposal` (
  `disposal_no` int(10) NOT NULL AUTO_INCREMENT,
  `book_code` varchar(50) NOT NULL,
  `disposal_bookname` varchar(50) NOT NULL,
  `disposal_author` varchar(50) NOT NULL,
  `genre_no` int(11) NOT NULL,
  `disposal_publisher` varchar(50) NOT NULL,
  `disposal_registerday` datetime NOT NULL,
  PRIMARY KEY (`disposal_no`),
  KEY `FK_disposal_genre` (`genre_no`),
  CONSTRAINT `FK_disposal_genre` FOREIGN KEY (`genre_no`) REFERENCES `genre` (`genre_no`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- Dumping data for table library.disposal: ~1 rows (대략적)
/*!40000 ALTER TABLE `disposal` DISABLE KEYS */;
INSERT INTO `disposal` (`disposal_no`, `book_code`, `disposal_bookname`, `disposal_author`, `genre_no`, `disposal_publisher`, `disposal_registerday`) VALUES
	(15, '1', 'java basic', '박성환', 1, '스마트정보교육원', '2017-01-26 17:28:31');
/*!40000 ALTER TABLE `disposal` ENABLE KEYS */;


-- 프로시저 library의 구조를 덤프합니다. disposal_book
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `disposal_book`(IN _payment int(10), IN _rental_code VARCHAR(20), IN _book_code VARCHAR(20), OUT RESULT INT)
BEGIN
	/* 만약 SQL에러라면 ROLLBACK 처리한다. */
	DECLARE exit handler for SQLEXCEPTION
	BEGIN
		ROLLBACK;        
		SET RESULT = -1;  
	END;

	/* 트랜젝션 시작 */
	START TRANSACTION;
		update rental
		set rental_end=sysdate(), rental_payment=_payment,rentalstate_no=2 
		where rental_code=_rental_code; 


		update book 
		set book.state_no='1'
 		where book.book_code=_book_code; 


	/* 커밋 */
	COMMIT;
	SET RESULT = 0;
END//
DELIMITER ;


-- 이벤트 library의 구조를 덤프합니다. ev
DELIMITER //
CREATE DEFINER=`root`@`localhost` EVENT `ev` ON SCHEDULE EVERY 3 SECOND STARTS '2017-02-02 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO DELETE book FROM book, disposal WHERE book.book_code=disposal.book_code//
DELIMITER ;


-- 테이블 library의 구조를 덤프합니다. genre
CREATE TABLE IF NOT EXISTS `genre` (
  `genre_no` int(11) NOT NULL AUTO_INCREMENT,
  `genre_name` varchar(50) NOT NULL,
  PRIMARY KEY (`genre_no`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table library.genre: ~4 rows (대략적)
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` (`genre_no`, `genre_name`) VALUES
	(1, '문학'),
	(2, '소설'),
	(3, '수필'),
	(4, '참고서');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;


-- 테이블 library의 구조를 덤프합니다. library
CREATE TABLE IF NOT EXISTS `library` (
  `library_id` varchar(50) NOT NULL,
  `library_pw` varchar(50) NOT NULL,
  `local_no` int(10) NOT NULL,
  PRIMARY KEY (`library_id`),
  KEY `FK_library_local` (`local_no`),
  CONSTRAINT `FK_library_local` FOREIGN KEY (`local_no`) REFERENCES `local` (`local_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table library.library: ~6 rows (대략적)
/*!40000 ALTER TABLE `library` DISABLE KEYS */;
INSERT INTO `library` (`library_id`, `library_pw`, `local_no`) VALUES
	('1', '1', 1),
	('12', '12', 1),
	('123', '123', 1),
	('4', '4', 1),
	('l01', 'pw01', 2),
	('l02', 'pw02', 1);
/*!40000 ALTER TABLE `library` ENABLE KEYS */;


-- 테이블 library의 구조를 덤프합니다. local
CREATE TABLE IF NOT EXISTS `local` (
  `local_no` int(11) NOT NULL AUTO_INCREMENT,
  `local_name` varchar(50) NOT NULL,
  PRIMARY KEY (`local_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table library.local: ~2 rows (대략적)
/*!40000 ALTER TABLE `local` DISABLE KEYS */;
INSERT INTO `local` (`local_no`, `local_name`) VALUES
	(1, '서울'),
	(2, '전주');
/*!40000 ALTER TABLE `local` ENABLE KEYS */;


-- 테이블 library의 구조를 덤프합니다. member
CREATE TABLE IF NOT EXISTS `member` (
  `member_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_name` varchar(50) NOT NULL,
  `member_phone` varchar(50) NOT NULL,
  `memberlevel_no` int(11) NOT NULL,
  PRIMARY KEY (`member_id`),
  KEY `FK_member_memberlevel` (`memberlevel_no`),
  CONSTRAINT `FK_member_memberlevel` FOREIGN KEY (`memberlevel_no`) REFERENCES `memberlevel` (`memberlevel_no`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table library.member: ~6 rows (대략적)
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`member_id`, `member_name`, `member_phone`, `memberlevel_no`) VALUES
	(1, 'kim', '01088882222', 2),
	(2, 'hong', '01011112222', 1),
	(3, 'park', '01011112222', 1),
	(4, '4', '4', 1),
	(5, 'kkekeki', '12908910', 2),
	(6, 'umin', '01101010', 2);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;


-- 테이블 library의 구조를 덤프합니다. memberlevel
CREATE TABLE IF NOT EXISTS `memberlevel` (
  `memberlevel_no` int(10) NOT NULL AUTO_INCREMENT,
  `memberlevel_name` varchar(50) NOT NULL,
  `price` int(10) NOT NULL,
  PRIMARY KEY (`memberlevel_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table library.memberlevel: ~2 rows (대략적)
/*!40000 ALTER TABLE `memberlevel` DISABLE KEYS */;
INSERT INTO `memberlevel` (`memberlevel_no`, `memberlevel_name`, `price`) VALUES
	(1, '무료회원', 500),
	(2, '유료회원', 300);
/*!40000 ALTER TABLE `memberlevel` ENABLE KEYS */;


-- 테이블 library의 구조를 덤프합니다. payment
CREATE TABLE IF NOT EXISTS `payment` (
  `payment_no` int(10) NOT NULL AUTO_INCREMENT,
  `payment_name` varchar(50) NOT NULL,
  `payment_value` int(10) NOT NULL,
  PRIMARY KEY (`payment_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table library.payment: ~2 rows (대략적)
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` (`payment_no`, `payment_name`, `payment_value`) VALUES
	(1, '일반회원', 500),
	(2, '유료회원', 300);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;


-- 테이블 library의 구조를 덤프합니다. rental
CREATE TABLE IF NOT EXISTS `rental` (
  `rental_code` varchar(50) NOT NULL,
  `book_code` int(11) NOT NULL,
  `rental_start` datetime NOT NULL,
  `rental_end` datetime DEFAULT NULL,
  `member_id` int(10) NOT NULL,
  `rental_payment` int(10) NOT NULL,
  `rentalstate_no` int(10) NOT NULL DEFAULT '1',
  `auto_num` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`rental_code`),
  KEY `FK_rental_member` (`member_id`),
  KEY `FK_rental_rentalstate` (`rentalstate_no`),
  KEY `FK_rental_book` (`book_code`),
  KEY `auto_num` (`auto_num`),
  CONSTRAINT `FK_rental_book` FOREIGN KEY (`book_code`) REFERENCES `book` (`book_code`),
  CONSTRAINT `FK_rental_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FK_rental_rentalstate` FOREIGN KEY (`rentalstate_no`) REFERENCES `rentalstate` (`rentalstate_no`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- Dumping data for table library.rental: ~21 rows (대략적)
/*!40000 ALTER TABLE `rental` DISABLE KEYS */;
INSERT INTO `rental` (`rental_code`, `book_code`, `rental_start`, `rental_end`, `member_id`, `rental_payment`, `rentalstate_no`, `auto_num`) VALUES
	('100028', 10, '2017-01-19 00:00:00', '2017-01-26 17:33:18', 1, 3500, 2, 29),
	('400003', 1, '2017-01-24 00:00:00', '2017-01-26 14:27:01', 1, 1000, 2, 4),
	('400005', 5, '2017-01-04 00:00:00', '2017-01-26 14:28:30', 1, 11000, 2, 6),
	('400006', 6, '2017-01-04 00:00:00', '2017-01-26 15:20:16', 1, 11000, 2, 11),
	('400011', 6, '2017-01-04 00:00:00', NULL, 1, 1, 1, 12),
	('400012', 7, '2017-01-26 00:00:00', '2017-01-26 15:30:40', 1, 0, 2, 13),
	('400014', 5, '2017-01-24 00:00:00', '2017-01-26 16:45:38', 1, 1000, 2, 15),
	('400015', 5, '2017-01-04 00:00:00', '2017-01-26 16:56:56', 1, 11000, 2, 16),
	('400016', 5, '2017-01-12 00:00:00', '2017-01-26 17:01:12', 1, 7000, 2, 17),
	('400017', 5, '2017-01-26 00:00:00', '2017-01-26 17:08:41', 1, 0, 2, 18),
	('400018', 5, '2017-01-25 00:00:00', '2017-01-26 17:11:58', 1, 500, 2, 19),
	('400019', 5, '2017-01-24 00:00:00', '2017-01-26 17:13:18', 1, 1000, 2, 20),
	('400020', 5, '2017-01-04 00:00:00', '2017-01-26 17:19:30', 1, 11000, 2, 21),
	('400021', 5, '2017-01-12 00:00:00', '2017-01-26 17:20:25', 1, 7000, 2, 22),
	('400022', 5, '2017-01-11 00:00:00', '2017-01-26 17:24:06', 1, 7500, 2, 23),
	('400023', 9, '2017-01-24 00:00:00', '2017-01-26 17:25:24', 1, 1000, 2, 24),
	('400024', 5, '2017-01-11 00:00:00', '2017-01-26 17:26:51', 1, 7500, 2, 25),
	('400025', 5, '2017-01-04 00:00:00', '2017-01-26 17:27:46', 1, 11000, 2, 26),
	('400026', 5, '2017-01-18 00:00:00', NULL, 1, 1, 1, 27),
	('400027', 4, '2017-01-11 00:00:00', NULL, 1, 15, 1, 28),
	('null00013', 5, '2017-01-25 00:00:00', '2017-01-26 16:44:47', 1, 500, 2, 14);
/*!40000 ALTER TABLE `rental` ENABLE KEYS */;


-- 테이블 library의 구조를 덤프합니다. rentalstate
CREATE TABLE IF NOT EXISTS `rentalstate` (
  `rentalstate_no` int(10) NOT NULL AUTO_INCREMENT,
  `rentalstate_name` varchar(50) NOT NULL,
  PRIMARY KEY (`rentalstate_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table library.rentalstate: ~2 rows (대략적)
/*!40000 ALTER TABLE `rentalstate` DISABLE KEYS */;
INSERT INTO `rentalstate` (`rentalstate_no`, `rentalstate_name`) VALUES
	(1, '대여'),
	(2, '반납');
/*!40000 ALTER TABLE `rentalstate` ENABLE KEYS */;


-- 테이블 library의 구조를 덤프합니다. state
CREATE TABLE IF NOT EXISTS `state` (
  `state_no` int(10) NOT NULL AUTO_INCREMENT,
  `state_name` varchar(50) NOT NULL,
  PRIMARY KEY (`state_no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table library.state: ~3 rows (대략적)
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` (`state_no`, `state_name`) VALUES
	(1, '대여가능'),
	(2, '대여불가'),
	(3, '폐기');
/*!40000 ALTER TABLE `state` ENABLE KEYS */;


-- sample 의 데이터베이스 구조 덤핑
CREATE DATABASE IF NOT EXISTS `sample` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sample`;


-- 테이블 sample의 구조를 덤프합니다. clone
CREATE TABLE IF NOT EXISTS `clone` (
  `no` int(10) NOT NULL AUTO_INCREMENT,
  `num` int(10) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `rd` datetime DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- Dumping data for table sample.clone: ~25 rows (대략적)
/*!40000 ALTER TABLE `clone` DISABLE KEYS */;
INSERT INTO `clone` (`no`, `num`, `name`, `rd`) VALUES
	(1, 193, '1', '2017-02-02 15:49:03'),
	(2, 193, '1', '2017-02-02 15:49:03'),
	(3, 155, '1', '2017-02-02 15:49:03'),
	(4, 154, '1', '2017-02-02 15:49:03'),
	(5, 196, '1', '2017-02-02 15:49:03'),
	(6, 197, '1', '2017-02-02 15:49:03'),
	(7, 198, '1', '2017-02-02 15:49:03'),
	(8, 199, '1', '2017-02-02 15:49:03'),
	(9, 200, '1', '2017-02-02 15:49:03'),
	(10, 201, '1', '2017-02-02 15:49:03'),
	(11, 202, '1', '2017-02-02 15:49:03'),
	(12, 203, '1', '2017-02-02 15:49:04'),
	(13, 204, '1', '2017-02-02 15:49:04'),
	(14, 205, '1', '2017-02-02 15:49:04'),
	(15, 206, '1', '2017-02-02 15:49:04'),
	(16, 207, '1', '2017-02-02 15:49:04'),
	(17, 208, '1', '2017-02-02 15:49:04'),
	(18, 209, '1', '2017-02-02 15:49:04'),
	(19, 210, '1', '2017-02-02 15:49:04'),
	(20, 211, '1', '2017-02-02 15:49:04'),
	(21, 212, '1', '2017-02-02 15:49:04'),
	(22, 213, '1', '2017-02-02 15:49:04'),
	(23, 214, '1', '2017-02-02 15:49:04'),
	(24, 215, '1', '2017-02-02 15:49:04'),
	(25, 216, '1', '2017-02-02 15:49:04');
/*!40000 ALTER TABLE `clone` ENABLE KEYS */;


-- 이벤트 sample의 구조를 덤프합니다. e22
DELIMITER //
CREATE DEFINER=`root`@`localhost` EVENT `e22` ON SCHEDULE EVERY 3 SECOND STARTS '2017-02-02 16:41:39' ON COMPLETION NOT PRESERVE ENABLE DO delete test from test, clone where test.`no`=clone.num//
DELIMITER ;


-- 테이블 sample의 구조를 덤프합니다. test
CREATE TABLE IF NOT EXISTS `test` (
  `no` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `rd` datetime DEFAULT NULL,
  `check` int(10) DEFAULT '0',
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=utf8;

-- Dumping data for table sample.test: ~13 rows (대략적)
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` (`no`, `name`, `rd`, `check`) VALUES
	(156, '1', '2017-02-02 15:34:45', 1),
	(157, '1', '2017-02-02 15:34:46', 1),
	(158, '1', '2017-02-02 15:34:47', 1),
	(159, '1', '2017-02-02 15:34:48', 1),
	(178, '1', '2017-02-02 15:35:07', 1),
	(179, '1', '2017-02-02 15:35:08', 1),
	(180, '1', '2017-02-02 15:35:09', 1),
	(181, '1', '2017-02-02 15:35:10', 1),
	(182, '1', '2017-02-02 15:35:11', 1),
	(188, '1', '2017-02-02 15:35:17', 1),
	(189, '1', '2017-02-02 15:35:18', 1),
	(190, '1', '2017-02-02 15:35:19', 1),
	(191, '1', '2017-02-02 15:35:20', 1);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
