CREATE DATABASE  IF NOT EXISTS `house_finder_project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `house_finder_project`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: house_finder_project
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `user_id` int NOT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `pass_word` varchar(45) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `role_id_idx` (`role_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'admin','admin',1),(3,'mai','mai',2),(4,'hoanganh','1',2);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `post_id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `number_of_star` int DEFAULT NULL,
  `comment` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `assessor_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `house`
--

DROP TABLE IF EXISTS `house`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `house` (
  `house_id` int NOT NULL AUTO_INCREMENT,
  `house_owner_id` int DEFAULT NULL,
  `type_of_house_id` int DEFAULT NULL,
  `address` varchar(150) DEFAULT NULL,
  `description` text,
  `area` int DEFAULT NULL,
  `number_of_room` int DEFAULT NULL,
  `picture` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`house_id`),
  KEY `type_of_house_id_idx` (`type_of_house_id`),
  KEY `house_owner_id_idx` (`house_owner_id`),
  CONSTRAINT `owner_id` FOREIGN KEY (`house_owner_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `type_of_house_id` FOREIGN KEY (`type_of_house_id`) REFERENCES `type_of_house` (`type_of_house_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house`
--

LOCK TABLES `house` WRITE;
/*!40000 ALTER TABLE `house` DISABLE KEYS */;
INSERT INTO `house` VALUES (1,4,1,'Số 20 Ngõ 45 Đường Lê Lai, Quận Hoàn Kiếm, Hà Nội','Ở góc phố yên bình, có một căn nhà đầy ấn tượng, là biểu tượng của sự ấm cúng và hiện đại. Với màu sơn trắng tinh khôi, căn nhà trở nên nổi bật giữa những cây cỏ xanh mướt. Lối vào rộng lớn được trải bằng gạch đỏ, tạo nên một hành lang sang trọng dẫn đến cửa chính, nơi chiếc cổng gỗ nâu cổ điển chào đón mọi khách.\n\nKhông gian nội thất của căn nhà được thiết kế với sự chú ý đến từng chi tiết. Phòng khách rộng lớn với bức tường kính lớn mở ra hình ảnh của một khu vườn xinh đẹp. Bàn trà và sofa êm ái tạo nên một không gian thoải mái để gia đình và bạn bè quây quần.\n\nNhà bếp rộng lớn và hiện đại với đèn trần sang trọng, tủ bếp gỗ sáng bóng và bàn ăn góc nhỏ tinh tế. Các phòng ngủ được trang trí với màu sắc nhẹ nhàng, cùng với các cửa sổ lớn mang lại ánh sáng tự nhiên.',600,5,NULL),(2,4,2,'Số 123 Đường Nguyễn Văn Linh, Phường Bình Thuận, Quận 7, TP.Hồ Chí Minh','Nhà bếp rộng lớn và hiện đại với đèn trần sang trọng, tủ bếp gỗ sáng bóng và bàn ăn góc nhỏ tinh tế. Các phòng ngủ được trang trí với màu sắc nhẹ nhàng, cùng với các cửa sổ lớn mang lại ánh sáng tự nhiên.\n\nSân sau của căn nhà là nơi lý tưởng để tổ chức các buổi họp mặt gia đình. Sân vườn xanh mát với các loại hoa và cây cỏ được bố trí hài hòa, tạo nên không gian thư giãn dưới bầu trời xanh. Một khu BBQ nhỏ và bàn ghế ngoài trời là nơi lý tưởng để thưởng thức những buổi tiệc nướng ngoại trời.\n\nNhững chiếc cửa sổ lớn và những chiếc đèn trang trí tinh tế tạo nên không gian sống ấm cúng và thoải mái. Đây không chỉ là nơi ở, mà còn là nơi chứa đựng nhiều kỷ niệm đáng nhớ của gia đình, tạo nên một tổ ấm ấm áp và hạnh phúc.',300,6,NULL),(3,4,5,'Số 56 Đường Trần Hưng Đạo, Thành Phố Huế, Tỉnh Thừa Thiên Huế','Tại góc phố yên bình, nằm sâu trong lòng thành phố, có một ngôi nhà độc đáo, kết hợp giữa vẻ đẹp cổ điển và tiện nghi hiện đại. Với lối kiến trúc độc đáo, ngôi nhà toát lên vẻ sang trọng và quý phái. Bức tường màu kem ấm áp nổi bật giữa sự xanh tươi của khu vườn xung quanh.\n\nNgay từ cổng chính, bạn được đón tiếp bởi đường lát gạch đá đẹp mắt dẫn đến cửa gỗ chắc chắn. Sảnh rộng lớn trải thảm đỏ mềm mại, tạo nên không gian lịch lãm và ấm cúng. Nơi đây, một bức tượng nghệ thuật hiện đại góp phần tạo điểm nhấn nghệ thuật.\n\nPhòng khách trang trí bằng những chiếc sofa da cao cấp và bàn trà kính tinh tế, tạo nên không gian sang trọng và thoải mái. Các bức tranh nghệ thuật treo trên tường tạo điểm nhấn cho không gian sống, thể hiện sự tinh tế và gu thẩm mỹ của chủ nhân.\n\nNhà bếp được trang bị đầy đủ tiện nghi hiện đại, từ tủ lạnh đến lò nướng, tạo điều kiện thuận lợi cho việc nấu nướng và tổ chức bữa tiệc gia đình. Phòng ăn với bàn ăn bằng gỗ chất lượng cao và đèn trang trí tạo nên không gian gia đình ấm cúng và ấn tượng.\n\nPhòng ngủ chính với giường lớn, chăn trải mềm mại và bức tường màu trấn an tạo nên không gian nghỉ ngơi tuyệt vời. Phòng tắm sang trọng với đồ nội thất cao cấp và bồn tắm giữa không gian thoáng đãng.\n\nSân sau với hồ bơi và khu vườn riêng tư là nơi lý tưởng để thư giãn và tận hưởng những khoảnh khắc yên bình. Đèn trang trí bài trí xung quanh tạo nên không gian lãng mạn vào buổi tối.\n\nNgôi nhà này không chỉ là một địa điểm ở, mà còn là biểu tượng của phong cách sống đẳng cấp và sự thoải mái đúng nghĩa.',700,6,NULL);
/*!40000 ALTER TABLE `house` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `post_id` int NOT NULL AUTO_INCREMENT,
  `house_id` int NOT NULL,
  `purpose_id` int DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `poster_id` int DEFAULT NULL,
  `house_status` int DEFAULT NULL,
  `admin_id` int DEFAULT NULL,
  `post_status` int DEFAULT NULL,
  PRIMARY KEY (`post_id`,`house_id`),
  KEY `house_id_idx` (`house_id`),
  KEY `user_id_idx` (`poster_id`),
  KEY `admin_id_idx` (`admin_id`),
  KEY `post_status_idx` (`post_status`),
  KEY `post_purpose_idx` (`purpose_id`),
  KEY `house_status_idx` (`house_status`),
  CONSTRAINT `censor_id` FOREIGN KEY (`admin_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `house_status` FOREIGN KEY (`house_status`) REFERENCES `request_status` (`status_id`),
  CONSTRAINT `post_purpose` FOREIGN KEY (`purpose_id`) REFERENCES `purpose` (`purpose_id`),
  CONSTRAINT `post_status` FOREIGN KEY (`post_status`) REFERENCES `request_status` (`status_id`),
  CONSTRAINT `poster_id` FOREIGN KEY (`poster_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `product_id` FOREIGN KEY (`house_id`) REFERENCES `house` (`house_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,1,1,60000,4,4,1,2),(2,2,1,50000,4,4,1,2),(3,3,1,100000,4,4,1,2);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purpose`
--

DROP TABLE IF EXISTS `purpose`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purpose` (
  `purpose_id` int NOT NULL AUTO_INCREMENT,
  `purpose_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`purpose_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purpose`
--

LOCK TABLES `purpose` WRITE;
/*!40000 ALTER TABLE `purpose` DISABLE KEYS */;
INSERT INTO `purpose` VALUES (1,'sell'),(2,'rent');
/*!40000 ALTER TABLE `purpose` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `house_id` int NOT NULL,
  `seller_id` int NOT NULL,
  `customer_id` int NOT NULL,
  `purpose_id` int DEFAULT NULL,
  `request_status` int DEFAULT NULL,
  `date_time` datetime NOT NULL,
  PRIMARY KEY (`house_id`,`seller_id`,`customer_id`,`date_time`),
  KEY `house_owner_id_idx` (`seller_id`),
  KEY `customer_idx` (`customer_id`),
  KEY `request_status_idx` (`request_status`),
  KEY `purpose_idx` (`purpose_id`),
  CONSTRAINT `customer_id` FOREIGN KEY (`customer_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `property_id` FOREIGN KEY (`house_id`) REFERENCES `house` (`house_id`),
  CONSTRAINT `purpose` FOREIGN KEY (`purpose_id`) REFERENCES `purpose` (`purpose_id`),
  CONSTRAINT `request_status` FOREIGN KEY (`request_status`) REFERENCES `request_status` (`status_id`),
  CONSTRAINT `seller_id` FOREIGN KEY (`seller_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_status`
--

DROP TABLE IF EXISTS `request_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request_status` (
  `status_id` int NOT NULL AUTO_INCREMENT,
  `status_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_status`
--

LOCK TABLES `request_status` WRITE;
/*!40000 ALTER TABLE `request_status` DISABLE KEYS */;
INSERT INTO `request_status` VALUES (1,'waiting'),(2,'accepted'),(3,'rejected'),(4,'available'),(5,'unavailable');
/*!40000 ALTER TABLE `request_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin'),(2,'user');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_of_house`
--

DROP TABLE IF EXISTS `type_of_house`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_of_house` (
  `type_of_house_id` int NOT NULL AUTO_INCREMENT,
  `type_of_house_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`type_of_house_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_of_house`
--

LOCK TABLES `type_of_house` WRITE;
/*!40000 ALTER TABLE `type_of_house` DISABLE KEYS */;
INSERT INTO `type_of_house` VALUES (1,'department'),(2,'villa'),(3,'entire house'),(4,'room'),(5,'other');
/*!40000 ALTER TABLE `type_of_house` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(250) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `phone_number` int DEFAULT NULL,
  `email` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Nguyễn Thị Hồng','1985-07-12','Số 10 Đường Lê Lợi, Quận 1, TP.Hồ Chí Minh',901234567,' nguyenthihong@gmail.com'),(2,'Trần Văn An','1985-05-20','Số 25 Đường Trần Hưng Đạo, Quận Hoàn Kiếm, Hà Nội',912345678,' tranvanan@gmail.com'),(3,' Lê Thị Mai','1992-12-10','Số 30 Nguyễn Du, Quận Hai Bà Trưng, Hà Nội',987654321,'lethimai@gmail.com'),(4,'Nguyễn Hoàng Anh','2003-08-30','Tổ 3, phường Yên Thịnh, Thành phố Yên Bái',828053208,'hn54707@gmail.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-19 16:12:12
