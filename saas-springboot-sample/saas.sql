/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.7.24-log : Database - saas
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`saas` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `saas`;

/*Table structure for table `t_datasources` */

DROP TABLE IF EXISTS `t_datasources`;

CREATE TABLE `t_datasources` (
  `ds_key` varchar(50) NOT NULL,
  `ds_url` varchar(200) NOT NULL,
  `ds_username` varchar(50) NOT NULL,
  `ds_password` varchar(50) NOT NULL,
  `driver_class_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_datasources` */

insert  into `t_datasources`(`ds_key`,`ds_url`,`ds_username`,`ds_password`,`driver_class_name`) values ('sample','jdbc:mysql://127.0.0.1:3306/sample?characterEncoding=utf-8','root','root','com.mysql.jdbc.Driver');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
