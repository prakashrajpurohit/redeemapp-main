-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 09, 2022 at 12:26 PM
-- Server version: 5.7.23-23
-- PHP Version: 7.3.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `a1627jg2_prizex`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_admin`
--

CREATE TABLE `tbl_admin` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(200) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_admin`
--

INSERT INTO `tbl_admin` (`id`, `username`, `password`, `email`, `image`) VALUES
(1, 'admin', 'admin', 'admin@yourwebsite.com', 'profile.png');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_bid`
--

CREATE TABLE `tbl_bid` (
  `bd_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `o_id` int(11) NOT NULL,
  `bd_value` varchar(20) NOT NULL,
  `bd_amount` varchar(255) NOT NULL,
  `bd_date` varchar(255) NOT NULL,
  `bd_status` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_bid`
--

INSERT INTO `tbl_bid` (`bd_id`, `u_id`, `o_id`, `bd_value`, `bd_amount`, `bd_date`, `bd_status`) VALUES
(10, 2, 1, '25', '25', '2022-01-04', 1),
(11, 2, 1, '25', '25', '2022-01-04', 1),
(12, 2, 1, '25', '25', '2022-01-04', 1),
(13, 2, 16, '200', '200', '2022-01-04', 1),
(14, 9, 15, '25', '25', '2022-01-05', 1),
(15, 77, 1, '25', '25', '2022-01-05', 1),
(16, 173, 1, '25', '25', '2022-01-05', 1),
(17, 186, 15, '25', '25', '2022-01-05', 1),
(18, 217, 1, '25', '25', '2022-01-05', 1),
(19, 217, 1, '25', '1', '2022-01-05', 1),
(20, 217, 1, '25', '1', '2022-01-05', 1),
(21, 217, 1, '25', '1', '2022-01-05', 1),
(22, 231, 15, '25', '25', '2022-01-05', 1),
(23, 215, 1, '25', '25', '2022-01-05', 1),
(24, 215, 1, '25', '25', '2022-01-05', 1),
(25, 282, 15, '25', '25', '2022-01-05', 1),
(26, 299, 15, '25', '25', '2022-01-05', 1),
(27, 284, 15, '25', '25', '2022-01-05', 1),
(28, 347, 15, '25', '25', '2022-01-05', 1),
(29, 215, 1, '25', '25', '2022-01-05', 1),
(30, 282, 15, '25', '25', '2022-01-05', 1),
(31, 346, 15, '25', '25', '2022-01-05', 1),
(32, 330, 15, '25', '25', '2022-01-05', 1),
(33, 215, 1, '25', '25', '2022-01-05', 1),
(34, 286, 15, '25', '25', '2022-01-05', 1),
(35, 323, 15, '25', '25', '2022-01-05', 1),
(36, 455, 15, '25', '25', '2022-01-05', 1),
(37, 408, 1, '25', '25', '2022-01-05', 1),
(38, 312, 15, '25', '25', '2022-01-05', 1),
(39, 208, 15, '25', '25', '2022-01-05', 1),
(40, 208, 1, '25', '25', '2022-01-05', 1),
(41, 331, 15, '25', '25', '2022-01-05', 1),
(42, 408, 4, '200', '200', '2022-01-05', 1),
(43, 137, 1, '25', '25', '2022-01-05', 1),
(44, 318, 1, '25', '25', '2022-01-05', 1),
(45, 318, 1, '25', '25', '2022-01-05', 1),
(46, 318, 1, '25', '25', '2022-01-05', 1),
(47, 318, 1, '25', '25', '2022-01-05', 1),
(48, 410, 15, '25', '25', '2022-01-05', 1),
(49, 318, 1, '25', '25', '2022-01-05', 1),
(50, 318, 15, '25', '25', '2022-01-05', 1),
(51, 318, 15, '25', '25', '2022-01-05', 1),
(52, 217, 1, '25', '25', '2022-01-05', 1),
(53, 318, 15, '25', '25', '2022-01-05', 1),
(54, 282, 15, '25', '25', '2022-01-05', 1),
(55, 428, 15, '25', '25', '2022-01-05', 1),
(56, 318, 15, '25', '25', '2022-01-05', 1),
(57, 217, 1, '25', '25', '2022-01-05', 1),
(58, 217, 1, '25', '25', '2022-01-05', 1),
(59, 446, 15, '25', '25', '2022-01-05', 1),
(60, 318, 15, '25', '25', '2022-01-05', 1),
(61, 318, 1, '25', '25', '2022-01-05', 1),
(62, 313, 15, '25', '25', '2022-01-05', 1),
(63, 318, 15, '25', '25', '2022-01-05', 1),
(64, 408, 4, '200', '200', '2022-01-05', 1),
(65, 318, 1, '25', '25', '2022-01-05', 1),
(66, 318, 15, '25', '25', '2022-01-05', 1),
(67, 318, 15, '25', '25', '2022-01-05', 1),
(68, 252, 15, '25', '25', '2022-01-05', 1),
(69, 215, 1, '25', '25', '2022-01-05', 1),
(70, 215, 1, '25', '25', '2022-01-05', 1),
(71, 215, 1, '25', '25', '2022-01-05', 1),
(72, 418, 15, '25', '25', '2022-01-05', 1),
(73, 215, 1, '25', '25', '2022-01-05', 1),
(74, 318, 1, '25', '25', '2022-01-05', 1),
(75, 363, 15, '25', '25', '2022-01-05', 1),
(76, 829, 1, '25', '25', '2022-01-05', 1),
(77, 770, 15, '25', '25', '2022-01-05', 1),
(78, 318, 15, '25', '25', '2022-01-05', 1),
(79, 273, 15, '25', '25', '2022-01-05', 1),
(80, 836, 1, '25', '25', '2022-01-05', 1),
(81, 221, 16, '200', '200', '2022-01-05', 1),
(82, 289, 15, '25', '25', '2022-01-05', 1),
(83, 381, 15, '25', '25', '2022-01-05', 1),
(84, 331, 15, '25', '25', '2022-01-05', 1),
(85, 298, 15, '25', '25', '2022-01-05', 1),
(86, 299, 16, '200', '200', '2022-01-05', 1),
(87, 980, 1, '25', '25', '2022-01-05', 1),
(88, 999, 15, '25', '25', '2022-01-05', 1),
(89, 1121, 15, '25', '25', '2022-01-05', 1),
(90, 346, 15, '25', '25', '2022-01-05', 1),
(91, 1078, 15, '25', '25', '2022-01-05', 1),
(92, 993, 1, '25', '25', '2022-01-05', 1),
(93, 869, 15, '25', '25', '2022-01-05', 1),
(94, 1176, 15, '25', '25', '2022-01-05', 1),
(95, 1144, 15, '25', '25', '2022-01-05', 1),
(96, 1160, 15, '25', '25', '2022-01-05', 1),
(97, 282, 1, '25', '25', '2022-01-05', 1),
(98, 836, 15, '25', '25', '2022-01-05', 1),
(99, 1406, 1, '25', '25', '2022-01-05', 1),
(100, 982, 15, '25', '25', '2022-01-05', 1),
(101, 150, 1, '25', '25', '2022-01-05', 1),
(102, 1446, 15, '25', '25', '2022-01-05', 1),
(103, 331, 23, '25', '25', '2022-01-05', 1),
(104, 1374, 1, '25', '25', '2022-01-05', 1),
(105, 836, 1, '25', '25', '2022-01-05', 1),
(106, 836, 1, '25', '25', '2022-01-05', 1),
(107, 836, 1, '25', '25', '2022-01-05', 1),
(108, 836, 1, '25', '25', '2022-01-05', 1),
(109, 478, 1, '25', '25', '2022-01-05', 1),
(110, 829, 3, '200', '200', '2022-01-05', 1),
(111, 1584, 1, '25', '25', '2022-01-05', 1),
(112, 1624, 1, '25', '25', '2022-01-05', 1),
(113, 1707, 1, '25', '25', '2022-01-05', 1),
(114, 1024, 1, '25', '25', '2022-01-05', 1),
(115, 1707, 1, '25', '25', '2022-01-05', 1),
(116, 1707, 3, '200', '200', '2022-01-05', 1),
(117, 890, 1, '25', '25', '2022-01-05', 1),
(118, 282, 23, '25', '25', '2022-01-06', 1),
(119, 1299, 16, '200', '200', '2022-01-06', 1),
(120, 1, 24, '110', '110', '2022-01-16', 1),
(122, 1, 57, '5JZN', '450', '2022-02-04', 1),
(123, 1, 57, 'E4JC', '450', '2022-02-04', 1),
(124, 1, 2, '110', '110', '2022-02-04', 1),
(125, 2132, 3, '200', '200', '2022-02-08', 1),
(126, 2132, 3, '200', '200', '2022-02-08', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_cat`
--

CREATE TABLE `tbl_cat` (
  `c_id` int(11) NOT NULL,
  `c_image` varchar(255) CHARACTER SET utf8 NOT NULL,
  `c_name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `c_desc` varchar(255) CHARACTER SET utf8 NOT NULL,
  `c_color` varchar(255) CHARACTER SET utf8 NOT NULL,
  `c_view` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `c_status` varchar(255) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_cat`
--

INSERT INTO `tbl_cat` (`c_id`, `c_image`, `c_name`, `c_desc`, `c_color`, `c_view`, `c_status`) VALUES
(1, '', 'Banner', 'Its a Banner', '0cbaba', '1', '1'),
(2, '', 'Auction', 'Auction are here', '0cbaba', '1', '1'),
(3, '', 'Raffle', 'Raffle App', '0cbaba', '1', '1'),
(4, '', 'Redemption', 'redeem here', '0cbaba', '1', '1');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_coin_list`
--

CREATE TABLE `tbl_coin_list` (
  `c_id` int(11) NOT NULL,
  `c_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `c_coin` int(11) NOT NULL,
  `c_amount` int(11) NOT NULL,
  `c_status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_coin_list`
--

INSERT INTO `tbl_coin_list` (`c_id`, `c_name`, `c_coin`, `c_amount`, `c_status`) VALUES
(1, 'New Pack', 5, 1, 1),
(2, 'Bronze Pack', 9, 4, 1),
(3, 'Silver Pack', 25, 9, 1),
(4, 'Gold Pack', 51, 14, 1),
(11, 'Platinum Pack', 101, 29, 1),
(12, 'Taitanium Pack', 251, 49, 1),
(13, 'Treasure Box', 1050, 999, 1),
(14, 'Gold', 1000, 10, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_network`
--

CREATE TABLE `tbl_network` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `money` varchar(255) NOT NULL,
  `refferal_user_id` int(11) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_offers`
--

CREATE TABLE `tbl_offers` (
  `o_id` int(11) NOT NULL,
  `c_id` varchar(255) NOT NULL,
  `o_name` varchar(255) NOT NULL,
  `o_image` varchar(255) NOT NULL,
  `o_oimage` varchar(255) NOT NULL,
  `o_desc` text NOT NULL,
  `o_amount` varchar(255) NOT NULL,
  `o_link` varchar(255) NOT NULL,
  `o_date` varchar(255) NOT NULL,
  `o_edate` varchar(255) NOT NULL,
  `o_stime` varchar(255) NOT NULL,
  `o_etime` varchar(255) NOT NULL,
  `o_type` varchar(255) NOT NULL,
  `o_min` varchar(255) NOT NULL,
  `o_max` varchar(255) NOT NULL,
  `o_price` varchar(255) NOT NULL,
  `o_qty` varchar(255) NOT NULL,
  `o_status` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_offers`
--

INSERT INTO `tbl_offers` (`o_id`, `c_id`, `o_name`, `o_image`, `o_oimage`, `o_desc`, `o_amount`, `o_link`, `o_date`, `o_edate`, `o_stime`, `o_etime`, `o_type`, `o_min`, `o_max`, `o_price`, `o_qty`, `o_status`) VALUES
(1, '1', 'Amazon Gift Card', '39881_png_20211230_101545_0000.png', '', 'Delivery Details:-\r\n• The gift card will be delivered to you within 72 hours on the mail id you have provided while claiming it.\r\n\r\nHow to use:-\r\n• To add your GC to your Amazon Pay balance, visit www.amazon.in/addgiftcard\r\n• You can apply the 14 digit code on amazon.in/addgiftcard and add the Gift Card balance in your Amazon.in account.', '1', '', '2021-12-30', '2022-01-07', '06:00', '18:00:02', '1', '1', '100', '100', '1', 0),
(2, '2', 'Refer & earn', '4046_20220101_091238_0000.png', '', '', '', '', '2022-01-01', '2022-02-15', '09:23:00', '00:00:00', '2', '', '', '', '', 1),
(3, '3', 'USB Cable', '70333_0001-6639454401_20210826_075659_0000.png', '', '.Delivery Details:- • Our team will contact you within 72 hours on the mail id you have provided while claiming it to process the claim.', '1', '', '2022-01-04', '2022-01-05', '15:00:00', '10:00:00', '3', '', '', '299', '2', 1),
(4, '4', 'Paytm', '50697_20210825_220224_0000.png', '', '.Delivery Details:- • Our team will contact you within 72 hours on the mail id you have provided while claiming it to process the claim.', '1', '', '2022-01-04', '2022-02-27', '14:11:00', '10:00:00', '1', '', '', '499', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_order`
--

CREATE TABLE `tbl_order` (
  `o_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `offer_id` int(11) NOT NULL,
  `total_amount` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dis_amount` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pay_amount` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `o_address` varchar(2554) COLLATE utf8_unicode_ci NOT NULL,
  `order_status` int(11) NOT NULL DEFAULT '1',
  `order_date` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `o_status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_settings`
--

CREATE TABLE `tbl_settings` (
  `id` int(11) NOT NULL,
  `email_from` varchar(255) NOT NULL,
  `onesignal_app_id` text NOT NULL,
  `onesignal_rest_key` text NOT NULL,
  `app_name` varchar(255) NOT NULL,
  `app_logo` varchar(255) NOT NULL,
  `app_email` varchar(255) NOT NULL,
  `app_version` varchar(255) NOT NULL,
  `app_author` varchar(255) NOT NULL,
  `app_contact` varchar(255) NOT NULL,
  `app_website` varchar(255) NOT NULL,
  `app_description` text NOT NULL,
  `app_developed_by` varchar(255) NOT NULL,
  `app_privacy_policy` text NOT NULL,
  `api_all_order_by` varchar(255) NOT NULL,
  `api_latest_limit` int(3) NOT NULL,
  `api_cat_order_by` varchar(255) NOT NULL,
  `api_cat_post_order_by` varchar(255) NOT NULL,
  `publisher_id` text NOT NULL,
  `interstital_ad` text NOT NULL,
  `interstital_ad_id` text NOT NULL,
  `interstital_ad_click` varchar(255) NOT NULL,
  `banner_ad` text NOT NULL,
  `banner_ad_id` text NOT NULL,
  `native_ad_id` varchar(255) NOT NULL,
  `publisher_id_ios` varchar(500) NOT NULL,
  `app_id_ios` varchar(500) NOT NULL,
  `interstital_ad_ios` varchar(500) NOT NULL,
  `interstital_ad_id_ios` varchar(500) NOT NULL,
  `interstital_ad_click_ios` varchar(500) NOT NULL,
  `banner_ad_ios` varchar(500) NOT NULL,
  `banner_ad_id_ios` varchar(500) NOT NULL,
  `native_ad_id_ios` varchar(255) NOT NULL,
  `how_to_play` text NOT NULL,
  `about_us` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_settings`
--

INSERT INTO `tbl_settings` (`id`, `email_from`, `onesignal_app_id`, `onesignal_rest_key`, `app_name`, `app_logo`, `app_email`, `app_version`, `app_author`, `app_contact`, `app_website`, `app_description`, `app_developed_by`, `app_privacy_policy`, `api_all_order_by`, `api_latest_limit`, `api_cat_order_by`, `api_cat_post_order_by`, `publisher_id`, `interstital_ad`, `interstital_ad_id`, `interstital_ad_click`, `banner_ad`, `banner_ad_id`, `native_ad_id`, `publisher_id_ios`, `app_id_ios`, `interstital_ad_ios`, `interstital_ad_id_ios`, `interstital_ad_click_ios`, `banner_ad_ios`, `banner_ad_id_ios`, `native_ad_id_ios`, `how_to_play`, `about_us`) VALUES
(1, '', 'e667c917-2865-4ccf-9a30-fb2c04a15e69', 'OGQ5MWQ0NTMtYzcwZi00ZGY5LWIwNDktZGZmNzkzM2E0Yzdm', 'PrizeX App', 'img_coin.png', '', '', '', '', '', '', '', 'Enter your app privacy policy from Admin panel', 'ASC', 15, 'cid', 'DESC', '2096905823943315', 'true', '2887875904580143_2887877791246621', '4', 'true', '2887875904580143_2893749237326143', '2887875904580143_3108839169150481', 'pub-', '', 'true', 'ca-app-pub-3940256099942544/1033173712', '5', 'true', 'ca-app-pub-6736820514271711/5507792689', 'ca-app-pub-3940256099942544/2247696110', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_transaction`
--

CREATE TABLE `tbl_transaction` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `type` int(255) NOT NULL,
  `type_no` int(11) NOT NULL,
  `date` varchar(255) NOT NULL,
  `money` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_users`
--

CREATE TABLE `tbl_users` (
  `id` int(11) NOT NULL,
  `login_type` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `device_id` varchar(255) DEFAULT NULL,
  `wallet` varchar(11) NOT NULL DEFAULT '0',
  `code` int(11) NOT NULL DEFAULT '0',
  `refferal_code` int(11) DEFAULT NULL,
  `confirm_code` varchar(255) DEFAULT NULL,
  `network` int(11) NOT NULL DEFAULT '0',
  `ban` int(11) NOT NULL DEFAULT '0',
  `status` varchar(255) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_users`
--

INSERT INTO `tbl_users` (`id`, `login_type`, `name`, `email`, `image`, `password`, `phone`, `device_id`, `wallet`, `code`, `refferal_code`, `confirm_code`, `network`, `ban`, `status`) VALUES
(1, 1, 'Wow Codes!!', 'hello@wowcodes.in', '58272_abc', '123', '70013', '06bd60586db78ab7hj', '5965', 7812, 0, '1234', 1, 0, '1');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_wallet_passbook`
--

CREATE TABLE `tbl_wallet_passbook` (
  `wp_id` int(11) NOT NULL,
  `wp_user` int(11) NOT NULL,
  `wp_package_id` int(11) NOT NULL,
  `wp_order_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `wp_date` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `wp_status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_admin`
--
ALTER TABLE `tbl_admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_bid`
--
ALTER TABLE `tbl_bid`
  ADD PRIMARY KEY (`bd_id`);

--
-- Indexes for table `tbl_cat`
--
ALTER TABLE `tbl_cat`
  ADD PRIMARY KEY (`c_id`);

--
-- Indexes for table `tbl_coin_list`
--
ALTER TABLE `tbl_coin_list`
  ADD PRIMARY KEY (`c_id`);

--
-- Indexes for table `tbl_network`
--
ALTER TABLE `tbl_network`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `refferal_user_id` (`refferal_user_id`);

--
-- Indexes for table `tbl_offers`
--
ALTER TABLE `tbl_offers`
  ADD PRIMARY KEY (`o_id`);

--
-- Indexes for table `tbl_order`
--
ALTER TABLE `tbl_order`
  ADD PRIMARY KEY (`o_id`);

--
-- Indexes for table `tbl_settings`
--
ALTER TABLE `tbl_settings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_transaction`
--
ALTER TABLE `tbl_transaction`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_users`
--
ALTER TABLE `tbl_users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_wallet_passbook`
--
ALTER TABLE `tbl_wallet_passbook`
  ADD PRIMARY KEY (`wp_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_admin`
--
ALTER TABLE `tbl_admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_bid`
--
ALTER TABLE `tbl_bid`
  MODIFY `bd_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=127;

--
-- AUTO_INCREMENT for table `tbl_cat`
--
ALTER TABLE `tbl_cat`
  MODIFY `c_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `tbl_coin_list`
--
ALTER TABLE `tbl_coin_list`
  MODIFY `c_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `tbl_network`
--
ALTER TABLE `tbl_network`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1405;

--
-- AUTO_INCREMENT for table `tbl_offers`
--
ALTER TABLE `tbl_offers`
  MODIFY `o_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- AUTO_INCREMENT for table `tbl_order`
--
ALTER TABLE `tbl_order`
  MODIFY `o_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=136;

--
-- AUTO_INCREMENT for table `tbl_settings`
--
ALTER TABLE `tbl_settings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_transaction`
--
ALTER TABLE `tbl_transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19982;

--
-- AUTO_INCREMENT for table `tbl_users`
--
ALTER TABLE `tbl_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2146;

--
-- AUTO_INCREMENT for table `tbl_wallet_passbook`
--
ALTER TABLE `tbl_wallet_passbook`
  MODIFY `wp_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=144;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
