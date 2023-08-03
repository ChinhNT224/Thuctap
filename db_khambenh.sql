INSERT INTO `customer` (`customer_id`, `active`, `created_date`, `email`, `health_insurance`, `is_verified`, `isdelete`, `mobile_number`, `name`, `password`) VALUES
(1, 1, '2023-07-25 02:31:04', 'johndoe@example.com', NULL, b'1', NULL, 9629834692, 'John Doe', '$2a$10$0jdaPJhgz9dY27qeDyzncOGN1dEP/4elr0ZVWR4L/YAXsATRXduOW'),
(2, 1, '2023-07-25 02:32:42', 'chinhnt1@example.com', 987654321, b'0', NULL, 9629834692, 'Chinh', '$2a$10$BXWUHx6fhK9nZfyGPnAPyug.i3rp/GWSrGAjCqkAgowetQSqMhtXa'),
(3, 1, '2023-07-26 13:32:06', 'chinhnt2@example.com', 987654321, b'0', NULL, 9629834692, 'Chinh', '$2a$10$g/03gnp7erKi95DPRmOwretplNUnz9bPagzFL5kzu0PEt6.gHqoDG');

INSERT INTO `users` (`user_id`, `created_date`, `email`, `is_verified`, `mobile_number`, `name`, `password`, `role`, `active`, `isdelete`) VALUES
(1, '2023-03-14 15:18:53.000000',  'test@gmail.com', b'0', 9629834692, 'Chinh', '$2a$10$27oaaXZoz2q0o3ayM8mK4eddQt3olwLGpu1bpgZ67zjP327rirZ0e', 'admin', 1, NULL),
(2, '2023-03-14 16:24:54.848281',  'chinhnt@gmail.com', b'0', 9629834692, 'thanh', '$2a$10$cItkH5EiN8czVBFoqLDtQuSCeEInSRwM9IrMfjtEMD6JPUC2YC.jW', 'accounting', 1, NULL),
(3, '2023-03-14 16:29:02.009494',  'reception@gmail.com', b'0', 9868761234, 'reception', '$2a$10$3KFUAWe3kNuhKXqDjKz5PebbvkQ.RS5rA98tuuO9Z6mqwFuO8FlcO', 'reception', 1, NULL),
(4, '2023-03-15 09:14:54.839995',  'thanhnt21092001@gmail.com', b'0', 9629834692, 'thanh', '$2a$10$E2TmkZ44SlLlLBEt8EixReyCEL7ElA9G3UYgX.YoCNx8kOk3ndEl2', 'reception', 1, NULL),
(5, '2023-03-15 10:36:47.731210',  'thanhdzai2109@gmail.com', b'0', 9835984353, 'Thanh', '$2a$10$Bj8kqtU/eVurOo0oN0GzcuMHil/luuX90K.FQVAVgUpiQYDGpMe/i', 'user', 1, NULL),
(6, '2023-07-17 01:37:21.000000',  'trongchinhnguyen221@gmail.com', b'0', 9629834691, 'Chính', '$2a$10$2ZA6gnGssZBOY42Smi4F3ea/CnEBAyOR8hrIDf9cWwWgIBg2gjcH.', 'user', 1, NULL),
(7, '2023-07-17 01:45:29.000000',  'chinhlatao135@gmail.com', b'1', 9863743423, 'Chinhs', '$2a$10$p3/j7LRxHO90ugqw.T10L.JB.WtgCcRMQuTnZQcUOWMASYiTgkMZ6', 'accounting', 1, NULL);

INSERT INTO `orders` (`order_id`, `user_id_created_by`, `user_id_confirmed_by`, `trang_thai`, `ho_ten_nguoi_benh`, `gioi_tinh`, `ngay_sinh`, `dien_thoai`, `email`, `dia_chi`, `ngay_hen`, `gio_hen`, `ngay_tao`, `ngay_tiep_nhan`) VALUES
(1, 1, 3, 'confirmed', 'Alice', 'female', '1990-01-01', '123456789', 'alice@example.com', '123 Main Street',  '2023-07-25', '07:00:00', '2023-07-23', '2023-07-24'),
(2, 2, NULL, 'confirmed', 'Alice', 'female', '1990-01-01', '123456789', 'alice@example.com', '123 Main Street',  '2023-07-25', '07:00:00', '2023-07-23', '2023-07-24'),
(3, 1, NULL, 'pending', 'Alice', 'female', '1990-01-01', '123456789', 'alice@example.com', '123 Main Street', '2023-07-25', '07:00:00', '2023-07-23', '2023-07-24'),
(4, 2, NULL, 'pending', 'Alice', 'female', '1990-01-01', '123456789', 'alice@example.com', '123 Main Street', '2023-07-25', '07:00:00', '2023-07-23', '2023-07-24'),
(5, 1, NULL, 'confirmed', 'Alice', 'female', '1990-01-01', '123456789', 'alice@example.com', '123 Main Street',  '2023-07-25', '07:00:00', '2023-07-23', '2023-07-24'),
(6, 2, NULL, 'confirmed', NULL, 'female', '1990-01-01', '123456789', 'alice@example.com', '123 Main Street',  '2023-07-25', '07:00:00', '2023-07-23', '2023-07-24'),
(7, 1, NULL, 'pending', 'Alice', 'female', '1990-01-01', '123456789', 'alice@example.com', '123 Main Street', '2023-07-25', '07:00:00', '2023-07-23', '2023-07-24'),
(8, 1, 3, 'confirmed', 'Chinh', 'female', '1990-01-01', '123456789', 'alice@example.com', '123 Main Street', '2023-07-25', '07:00:00', NULL, NULL),
(9, 1, NULL, 'pending', 'Chinh', 'female', '1990-01-01', '123456789', 'alice@example.com', '123 Main Street', '2023-07-25', '07:00:00', NULL, NULL),
(10, 1, NULL, 'pending', 'Chinh', 'female', '1990-01-01', '123456789', 'alice@example.com', '123 Main Street', '2023-07-25', '07:00:00', NULL, NULL),
(11, 1, NULL, 'pending', 'Chinh', 'female', '1990-01-01', '123456789', 'alice123@example.com', '123 Main Street', '2023-07-25', '07:00:00', NULL, NULL);