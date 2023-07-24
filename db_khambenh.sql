SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `user_id_created_by` bigint(20) NOT NULL,
  `user_id_confirmed_by` bigint(20) DEFAULT NULL,
  `ngay_dang_ky` date DEFAULT NULL,
  `trang_thai` varchar(255) DEFAULT NULL,
  `ho_ten_nguoi_benh` varchar(255) DEFAULT NULL,
  `gioi_tinh` varchar(10) DEFAULT NULL,
  `ngay_sinh` date DEFAULT NULL,
  `dien_thoai` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `dia_chi` varchar(255) DEFAULT NULL,
  `xac_nhan` varchar(255) DEFAULT NULL,
  `ngay_hen` date DEFAULT NULL,
  `gio_hen` time DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `ngay_tiep_nhan` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `health_insurance` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_verified` bit(1) NOT NULL,
  `mobile_number` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `isdelete` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `user_id_created_by` (`user_id_created_by`),
  ADD KEY `user_id_confirmed_by` (`user_id_confirmed_by`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `users`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id_created_by`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`user_id_confirmed_by`) REFERENCES `users` (`user_id`);
COMMIT;

