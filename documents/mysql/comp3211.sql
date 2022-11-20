/*
MySQL Backup
Source Server Version: 5.7.17
Source Database: comp3211
Date: 2022/3/13 20:15:42
*/
CREATE DATABASE comp3211;
USE comp3211;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `students`
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students`(
  `student_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_number` varchar(64) NOT NULL,
  `student_name` varchar(20) NOT NULL,
  `student_course` MEDIUMTEXT NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `events`
-- ----------------------------
DROP TABLE IF EXISTS `events`;
CREATE TABLE `events`(
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `event_title` varchar(64) NOT NULL,
  `event_description` MEDIUMTEXT NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `start_time` varchar(30) NOT NULL,
  `end_time` varchar(30) NOT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;