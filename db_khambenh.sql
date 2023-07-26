-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 26, 2023 lúc 03:33 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `db_khambenh`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `customer_id` bigint(20) NOT NULL,
  `active` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `health_insurance` bigint(20) DEFAULT NULL,
  `is_verified` bit(1) NOT NULL,
  `isdelete` int(11) DEFAULT NULL,
  `mobile_number` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `customer`
--

INSERT INTO `customer` (`customer_id`, `active`, `created_date`, `email`, `health_insurance`, `is_verified`, `isdelete`, `mobile_number`, `name`, `password`) VALUES
(1, 1, '2023-07-25 02:31:04', 'johndoe@example.com', NULL, b'1', NULL, 9629834692, 'John Doe', '$2a$10$0jdaPJhgz9dY27qeDyzncOGN1dEP/4elr0ZVWR4L/YAXsATRXduOW'),
(2, 1, '2023-07-25 02:32:42', 'chinhnt1@example.com', 987654321, b'0', NULL, 9629834692, 'Chinh', '$2a$10$BXWUHx6fhK9nZfyGPnAPyug.i3rp/GWSrGAjCqkAgowetQSqMhtXa'),
(3, 1, '2023-07-26 13:32:06', 'chinhnt2@example.com', 987654321, b'0', NULL, 9629834692, 'Chinh', '$2a$10$g/03gnp7erKi95DPRmOwretplNUnz9bPagzFL5kzu0PEt6.gHqoDG');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

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

--
-- Đang đổ dữ liệu cho bảng `orders`
--

INSERT INTO `orders` (`order_id`, `user_id_created_by`, `user_id_confirmed_by`, `ngay_dang_ky`, `trang_thai`, `ho_ten_nguoi_benh`, `gioi_tinh`, `ngay_sinh`, `dien_thoai`, `email`, `dia_chi`, `xac_nhan`, `ngay_hen`, `gio_hen`, `ngay_tao`, `ngay_tiep_nhan`) VALUES
(1, 7, 3, '2023-07-23', 'confirmed', 'Alice', 'female', '1990-01-01', '123456789', 'alice@example.com', '123 Main Street', 'yes', '2023-07-25', '07:00:00', '2023-07-23', '2023-07-24'),
(2, 7, NULL, '2023-07-23', 'confirmed', 'Alice', 'female', '1990-01-01', '123456789', 'alice@example.com', '123 Main Street', 'yes', '2023-07-25', '07:00:00', '2023-07-23', '2023-07-24'),
(3, 6, NULL, '2023-07-23', 'pending', 'Alice', 'female', '1990-01-01', '123456789', 'alice@example.com', '123 Main Street', 'no', '2023-07-25', '07:00:00', '2023-07-23', '2023-07-24'),
(4, 2, NULL, '2023-07-23', 'pending', 'Alice', 'female', '1990-01-01', '123456789', 'alice@example.com', '123 Main Street', 'no', '2023-07-25', '07:00:00', '2023-07-23', '2023-07-24'),
(5, 7, NULL, '2023-07-23', 'confirmed', 'Alice', 'female', '1990-01-01', '123456789', 'alice@example.com', '123 Main Street', 'yes', '2023-07-25', '07:00:00', '2023-07-23', '2023-07-24'),
(6, 7, NULL, NULL, 'confirmed', NULL, 'female', '1990-01-01', '123456789', 'alice@example.com', '123 Main Street', 'yes', '2023-07-25', '07:00:00', '2023-07-23', '2023-07-24'),
(7, 1, NULL, '2023-07-23', 'pending', 'Alice', 'female', '1990-01-01', '123456789', 'alice@example.com', '123 Main Street', NULL, '2023-07-25', '07:00:00', '2023-07-23', '2023-07-24'),
(8, 1, 3, NULL, 'confirmed', 'Chinh', 'female', '1990-01-01', '123456789', 'alice@example.com', '123 Main Street', NULL, '2023-07-25', '07:00:00', NULL, NULL),
(9, 1, NULL, NULL, 'pending', 'Chinh', 'female', '1990-01-01', '123456789', 'alice@example.com', '123 Main Street', NULL, '2023-07-25', '07:00:00', NULL, NULL),
(10, 1, NULL, NULL, 'pending', 'Chinh', 'female', '1990-01-01', '123456789', 'alice@example.com', '123 Main Street', NULL, '2023-07-25', '07:00:00', NULL, NULL),
(11, 1, NULL, NULL, 'pending', 'Chinh', 'female', '1990-01-01', '123456789', 'alice123@example.com', '123 Main Street', NULL, '2023-07-25', '07:00:00', NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

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

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`user_id`, `created_date`, `health_insurance`, `email`, `is_verified`, `mobile_number`, `name`, `password`, `role`, `active`, `isdelete`) VALUES
(1, '2023-03-14 15:18:53.000000', NULL, 'test@gmail.com', b'0', 9629834692, 'Chinh', '$2a$10$27oaaXZoz2q0o3ayM8mK4eddQt3olwLGpu1bpgZ67zjP327rirZ0e', 'admin', 1, NULL),
(2, '2023-03-14 16:24:54.848281', NULL, 'chinhnt@gmail.com', b'0', 9629834692, 'thanh', '$2a$10$cItkH5EiN8czVBFoqLDtQuSCeEInSRwM9IrMfjtEMD6JPUC2YC.jW', 'accounting', 1, NULL),
(3, '2023-03-14 16:29:02.009494', NULL, 'reception@gmail.com', b'0', 9868761234, 'reception', '$2a$10$3KFUAWe3kNuhKXqDjKz5PebbvkQ.RS5rA98tuuO9Z6mqwFuO8FlcO', 'reception', 1, NULL),
(4, '2023-03-15 09:14:54.839995', NULL, 'thanhnt21092001@gmail.com', b'0', 9629834692, 'thanh', '$2a$10$E2TmkZ44SlLlLBEt8EixReyCEL7ElA9G3UYgX.YoCNx8kOk3ndEl2', 'reception', 1, NULL),
(5, '2023-03-15 10:36:47.731210', NULL, 'thanhdzai2109@gmail.com', b'0', 9835984353, 'Thanh', '$2a$10$Bj8kqtU/eVurOo0oN0GzcuMHil/luuX90K.FQVAVgUpiQYDGpMe/i', 'user', 1, NULL),
(6, '2023-07-17 01:37:21.000000', NULL, 'trongchinhnguyen221@gmail.com', b'0', 9629834691, 'Chính', '$2a$10$2ZA6gnGssZBOY42Smi4F3ea/CnEBAyOR8hrIDf9cWwWgIBg2gjcH.', 'user', 1, NULL),
(7, '2023-07-17 01:45:29.000000', NULL, 'chinhlatao135@gmail.com', b'1', 9863743423, 'Chinhs', '$2a$10$p3/j7LRxHO90ugqw.T10L.JB.WtgCcRMQuTnZQcUOWMASYiTgkMZ6', 'accounting', 1, NULL);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `user_id_created_by` (`user_id_created_by`),
  ADD KEY `user_id_confirmed_by` (`user_id_confirmed_by`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id_created_by`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`user_id_confirmed_by`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
