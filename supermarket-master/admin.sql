/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.1.49-community : Database - market
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`market` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `market`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `username` varchar(24) NOT NULL,
  `passwd` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`username`,`passwd`) values ('root','admin');

/*Table structure for table `bill` */

DROP TABLE IF EXISTS `bill`;

CREATE TABLE `bill` (
  `bill_id` varchar(24) NOT NULL,
  `bill_name` varchar(24) DEFAULT NULL,
  `bill_com` varchar(24) DEFAULT NULL,
  `bill_supplier` varchar(24) DEFAULT NULL,
  `bill_num` int(8) DEFAULT NULL,
  `bill_money` varchar(24) DEFAULT NULL,
  `bill_pay` varchar(24) DEFAULT NULL,
  `bill_time` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bill` */

insert  into `bill`(`bill_id`,`bill_name`,`bill_com`,`bill_supplier`,`bill_num`,`bill_money`,`bill_pay`,`bill_time`) values ('1','哇哈哈','我的','邯郸市五得利面粉厂',100,'1000','已付款','2018-06-28'),('100','香飘飘','母鸡','北京市粮油总公司',11,'11','已付款','2018-06-28'),('11','11','11','北京市粮油总公司',11,'11','已付款','2018-06-28'),('123123123','1asdas','dasdq','北京市粮油总公司',11,'11.00','未付款','2018-07-03'),('1233232','1','123','邯郸市五得利面粉厂',123,'123','已付款','2018-06-28'),('2','喜之郎','火星','北京市粮油总公司',100,'1000','未付款','2018-06-28'),('3','阿瑟东','as带我去','北京市粮油总公司',123,'12314','未付款','2018-06-28'),('4','辣条','卫龙','邯郸市五得利面粉厂',13,'69','已付款','2018-06-28'),('4324','2342','42311','北京市粮油总公司',123124,'324234','未付款','2018-06-28'),('4353534534','34534','534534','北京市粮油总公司',534534,'5345','未付款','2018-06-28'),('5','醒醒','啊是大','北京市粮油总公司',123,'12312','未付款','2018-06-28'),('61','大苏打','阿大撒','北京市粮油总公司',1232,'122','未付款','2018-06-28'),('7','1231','12312','北京市粮油总公司',12312,'123123','未付款','2018-06-28');

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `supplier_num` varchar(24) NOT NULL,
  `supplier_name` varchar(24) DEFAULT NULL,
  `supplier_contacts` varchar(24) DEFAULT NULL,
  `supplier_c_phone` varchar(11) DEFAULT NULL,
  `supplier_address` varchar(24) DEFAULT NULL,
  `supplier_fax` varchar(24) DEFAULT NULL,
  `supplier_des` varchar(32) DEFAULT NULL,
  `supplier_time` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`supplier_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `supplier` */

insert  into `supplier`(`supplier_num`,`supplier_name`,`supplier_contacts`,`supplier_c_phone`,`supplier_address`,`supplier_fax`,`supplier_des`,`supplier_time`) values ('1','大白象','周诚','1212431','1231','132','12313','2018-07-01'),('2','康师傅','1232','1212431','1231','12312','12313','2018-07-01'),('3','康师傅','1232','213123','1231','3123','31212','2018-07-01'),('4','大白象','权威','131','1231','12312','12312','2018-06-28'),('5','12312','312312','13100202371','12312','sdas','sad','2018-07-03');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_number` varchar(24) NOT NULL,
  `user_name` varchar(24) DEFAULT NULL,
  `user_passwd` varchar(24) DEFAULT NULL,
  `user_sex` varchar(8) DEFAULT NULL,
  `user_age` int(3) DEFAULT NULL,
  `user_birth` varchar(24) DEFAULT NULL,
  `user_phone` varchar(11) DEFAULT NULL,
  `user_address` varchar(24) DEFAULT NULL,
  `user_type` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`user_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_number`,`user_name`,`user_passwd`,`user_sex`,`user_age`,`user_birth`,`user_phone`,`user_address`,`user_type`) values ('1','zc','123','男',20,'1998/10/12','12312312','123123','普通用户'),('1233','zc','asda','女',20,'1998/10/12','113','1233','管理员'),('1SQWE','中国','123','男',20,'1998/10/02','13100202371','123123','经理'),('2','zc','123','男',20,'1998/10/12','13100202371','','普通用户'),('ASDASD','1','1','男',23,'1997/10/12','1','1','普通用户'),('ISDIAS','周诚','123','男',21,'1997/10/12','13100202371','12312','普通用户');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
