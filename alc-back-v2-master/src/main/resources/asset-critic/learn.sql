-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 22, 2022 at 10:36 AM
-- Server version: 5.7.24
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `learn`
--

-- --------------------------------------------------------

--
-- Table structure for table `calendrier_prof`
--

CREATE TABLE `calendrier_prof` (
  `id` bigint(20) NOT NULL,
  `end_recur` datetime DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `start_recur` datetime DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `etudiant` bigint(20) DEFAULT NULL,
  `prof` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `categorie_prof`
--

CREATE TABLE `categorie_prof` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `lesson_rate` decimal(19,2) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categorie_prof`
--

INSERT INTO `categorie_prof` (`id`, `code`, `lesson_rate`, `level`) VALUES
(1, 'c1', '230.00', 'Junior'),
(2, 'c2', '270.00', 'Middle'),
(3, 'c3', '300.00', 'Senior');

-- --------------------------------------------------------

--
-- Table structure for table `categorie_section`
--

CREATE TABLE `categorie_section` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `numero_order` int(11) DEFAULT NULL,
  `super_categorie_section` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categorie_section`
--

INSERT INTO `categorie_section` (`id`, `code`, `libelle`, `numero_order`, `super_categorie_section`) VALUES
(1, 'Additional', 'Warm up', 1, 160),
(2, 'Additional', 'Get to know', 4, 160),
(3, 'Additional', 'Discussion', 6, 160),
(4, 'Additional', 'Life Story', 8, 160),
(5, 'Additional', 'Games', 10, 160),
(6, 'Study the information', 'Let\'s practice', 5, 159),
(7, 'Study the information', 'Let\'s sum up', 7, 159),
(8, 'Additional', 'Vocabulary', 3, 160),
(9, 'Home Work Review', 'Home Work Review', 2, 159),
(10, 'Additional', 'Role Play', 9, 160);

-- --------------------------------------------------------

--
-- Table structure for table `centre`
--

CREATE TABLE `centre` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `log` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `centre`
--

INSERT INTO `centre` (`id`, `description`, `libelle`, `log`, `password`, `ref`) VALUES
(83, NULL, 'American Center 1', NULL, NULL, 'a1'),
(88, NULL, 'American Center 2', NULL, NULL, 'a2');

-- --------------------------------------------------------

--
-- Table structure for table `class_average_bonus`
--

CREATE TABLE `class_average_bonus` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `nombre_session` int(11) NOT NULL,
  `prix` decimal(19,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `class_average_bonus_prof`
--

CREATE TABLE `class_average_bonus_prof` (
  `id` bigint(20) NOT NULL,
  `annee` int(11) NOT NULL,
  `mois` int(11) NOT NULL,
  `class_average_bonus` bigint(20) DEFAULT NULL,
  `prof` bigint(20) DEFAULT NULL,
  `salary` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `class_room`
--

CREATE TABLE `class_room` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `responsable` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `cours`
--

CREATE TABLE `cours` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` longtext,
  `etat_cours` varchar(255) DEFAULT NULL,
  `image` longtext,
  `libelle` longtext,
  `nombre_link_en_cours` int(11) NOT NULL,
  `nombre_link_finalise` int(11) NOT NULL,
  `nombre_section_en_cours` int(11) NOT NULL,
  `nombre_section_finalise` int(11) NOT NULL,
  `numero_order` int(11) NOT NULL,
  `parcours` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cours`
--

INSERT INTO `cours` (`id`, `code`, `description`, `etat_cours`, `image`, `libelle`, `nombre_link_en_cours`, `nombre_link_finalise`, `nombre_section_en_cours`, `nombre_section_finalise`, `numero_order`, `parcours`) VALUES
(10000, NULL, NULL, NULL, NULL, 'Intro lesson', 0, 0, 0, 0, 0, 200),
(10001, NULL, NULL, NULL, NULL, '1 How to be a winner', 0, 0, 0, 0, 1, 200),
(10002, NULL, NULL, NULL, NULL, '2 Something to shout about!', 0, 0, 0, 0, 2, 200),
(10003, NULL, NULL, NULL, NULL, '3 Memory and memories', 0, 0, 0, 0, 3, 200),
(10004, NULL, NULL, NULL, NULL, 'Intro lesson', 0, 0, 0, 0, 0, 201),
(10005, NULL, NULL, NULL, NULL, '1 Saying Hello!', 0, 0, 0, 0, 1, 201),
(10006, NULL, NULL, NULL, NULL, '2 Hey! What’s up?', 0, 0, 0, 0, 2, 201),
(10007, NULL, NULL, NULL, NULL, '3 Let’s get acquainted', 0, 0, 0, 0, 3, 201),
(10008, NULL, NULL, NULL, NULL, '33 Inventions', 0, 0, 0, 0, 33, 201),
(10009, NULL, NULL, NULL, NULL, 'Intro lesson', 0, 0, 0, 0, 0, 202),
(10010, NULL, NULL, NULL, NULL, '1 Friends', 0, 0, 0, 0, 1, 202),
(10011, NULL, NULL, NULL, NULL, '2 Mates', 0, 0, 0, 0, 2, 202),
(10012, NULL, NULL, NULL, NULL, '3 Modern devices and appliances', 0, 0, 0, 0, 3, 202),
(10013, NULL, NULL, NULL, NULL, '1 Likes and Dislikes', 0, 0, 0, 0, 1, 202),
(10014, NULL, NULL, NULL, NULL, '2 Loves & hates', 0, 0, 0, 0, 2, 202),
(10015, NULL, NULL, NULL, NULL, '3 The world around you', 0, 0, 0, 0, 3, 202),
(10016, NULL, NULL, NULL, NULL, 'Intro lesson', 0, 0, 0, 0, 0, 203),
(10017, NULL, NULL, NULL, NULL, '1 Friends', 0, 0, 0, 0, 1, 203),
(10018, NULL, NULL, NULL, NULL, '2 Mates', 0, 0, 0, 0, 2, 203),
(10019, NULL, NULL, NULL, NULL, '3 Modern devices and appliances', 0, 0, 0, 0, 3, 203);

-- --------------------------------------------------------

--
-- Table structure for table `cour_seq`
--

CREATE TABLE `cour_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cour_seq`
--

INSERT INTO `cour_seq` (`next_val`) VALUES
(10020);

-- --------------------------------------------------------

--
-- Table structure for table `dictionary`
--

CREATE TABLE `dictionary` (
  `id` bigint(20) NOT NULL,
  `definition` longtext,
  `word` longtext,
  `etudiant` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `etat_etudiant`
--

CREATE TABLE `etat_etudiant` (
  `id` bigint(20) NOT NULL,
  `etat` varchar(255) DEFAULT NULL,
  `etat_number` bigint(20) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_class` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `nationnalité` varchar(255) DEFAULT NULL,
  `nbr_class` varchar(255) DEFAULT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `schedule1` varchar(255) DEFAULT NULL,
  `schedule2` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `etat_etudiant_schedule`
--

CREATE TABLE `etat_etudiant_schedule` (
  `id` bigint(20) NOT NULL,
  `couleur` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `etat_inscription`
--

CREATE TABLE `etat_inscription` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `etat_inscription`
--

INSERT INTO `etat_inscription` (`id`, `libelle`, `ref`) VALUES
(3, 'refused', 'E3'),
(2, 'Validated', 'E2'),
(1, 'Pending', 'E1');

-- --------------------------------------------------------

--
-- Table structure for table `etudiant_class_room`
--

CREATE TABLE `etudiant_class_room` (
  `id` bigint(20) NOT NULL,
  `class_room` bigint(20) DEFAULT NULL,
  `etudiant` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `etudiant_cours`
--

CREATE TABLE `etudiant_cours` (
  `id` bigint(20) NOT NULL,
  `date_fin` datetime DEFAULT NULL,
  `payer` bit(1) NOT NULL,
  `cours` bigint(20) DEFAULT NULL,
  `etudiant` bigint(20) DEFAULT NULL,
  `prof` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `etudiant_review`
--

CREATE TABLE `etudiant_review` (
  `id` bigint(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `date_review` datetime DEFAULT NULL,
  `review` int(11) NOT NULL,
  `cours` bigint(20) DEFAULT NULL,
  `etudiant` bigint(20) DEFAULT NULL,
  `prof` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `faq`
--

CREATE TABLE `faq` (
  `id` bigint(20) NOT NULL,
  `description` longtext,
  `libelle` longtext,
  `faq_type` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `faq_etudiant`
--

CREATE TABLE `faq_etudiant` (
  `id` bigint(20) NOT NULL,
  `description` longtext,
  `libelle` longtext,
  `admin` bigint(20) DEFAULT NULL,
  `etudiant` bigint(20) DEFAULT NULL,
  `faq_type` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `faq_prof`
--

CREATE TABLE `faq_prof` (
  `id` bigint(20) NOT NULL,
  `description` longtext,
  `libelle` longtext,
  `admin` bigint(20) DEFAULT NULL,
  `faq_type` bigint(20) DEFAULT NULL,
  `prof` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `faq_type`
--

CREATE TABLE `faq_type` (
  `id` bigint(20) NOT NULL,
  `destinataire` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `faq_type`
--

INSERT INTO `faq_type` (`id`, `destinataire`, `libelle`) VALUES
(1485, 'teacher', 'Common questions'),
(1486, 'teacher', 'Salary'),
(1487, 'student', 'Exams'),
(1488, 'student', 'Diplome'),
(1, 'teacher', 'DE Class Support'),
(2, 'teacher', 'Teachers effectiveness'),
(3, 'teacher', 'Methodological care');

-- --------------------------------------------------------

--
-- Table structure for table `fonction`
--

CREATE TABLE `fonction` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `free_trialformule`
--

CREATE TABLE `free_trialformule` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `date_confirmation` date DEFAULT NULL,
  `dayspeerweek` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `teacher_age_range` varchar(255) DEFAULT NULL,
  `teacher_genderoption` varchar(255) DEFAULT NULL,
  `teacher_personnality` varchar(255) DEFAULT NULL,
  `timeperday` varchar(255) DEFAULT NULL,
  `inscription` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `groupe_etude`
--

CREATE TABLE `groupe_etude` (
  `id` bigint(20) NOT NULL,
  `description` longtext,
  `libelle` varchar(255) DEFAULT NULL,
  `nombre_etudiant` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `groupe_etude`
--

INSERT INTO `groupe_etude` (`id`, `description`, `libelle`, `nombre_etudiant`) VALUES
(25908, NULL, 'Group for 10 person', 10),
(25907, NULL, 'Group for 5 person', 5),
(25906, NULL, 'Group for 2 person', 2),
(25905, NULL, 'Group for 1 person', 1);

-- --------------------------------------------------------

--
-- Table structure for table `groupe_etudiant`
--

CREATE TABLE `groupe_etudiant` (
  `id` bigint(20) NOT NULL,
  `date_debut` datetime DEFAULT NULL,
  `date_fin` datetime DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `niveau` varchar(255) DEFAULT NULL,
  `nombre_place` bigint(20) DEFAULT NULL,
  `nombre_place_non_vide` bigint(20) DEFAULT NULL,
  `nombre_placevide` bigint(20) DEFAULT NULL,
  `groupe_etude` bigint(20) DEFAULT NULL,
  `parcours` bigint(20) DEFAULT NULL,
  `prof` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `groupe_etudiant`
--

INSERT INTO `groupe_etudiant` (`id`, `date_debut`, `date_fin`, `libelle`, `niveau`, `nombre_place`, `nombre_place_non_vide`, `nombre_placevide`, `groupe_etude`, `parcours`, `prof`) VALUES
(6, NULL, NULL, 'GroupYsf', NULL, 2, 1, 1, 25906, 200, 2);

-- --------------------------------------------------------

--
-- Table structure for table `groupe_etudiant_detail`
--

CREATE TABLE `groupe_etudiant_detail` (
  `id` bigint(20) NOT NULL,
  `etudiant` bigint(20) DEFAULT NULL,
  `groupe_etudiant` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `groupe_etudiant_detail`
--

INSERT INTO `groupe_etudiant_detail` (`id`, `etudiant`, `groupe_etudiant`) VALUES
(7, 4, 6);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(11);

-- --------------------------------------------------------

--
-- Table structure for table `homeworkqstrps_seq`
--

CREATE TABLE `homeworkqstrps_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `homeworkqstrps_seq`
--

INSERT INTO `homeworkqstrps_seq` (`next_val`) VALUES
(1000140);

-- --------------------------------------------------------

--
-- Table structure for table `homeworkqst_seq`
--

CREATE TABLE `homeworkqst_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `homeworkqst_seq`
--

INSERT INTO `homeworkqst_seq` (`next_val`) VALUES
(1000078);

-- --------------------------------------------------------

--
-- Table structure for table `homework_seq`
--

CREATE TABLE `homework_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `homework_seq`
--

INSERT INTO `homework_seq` (`next_val`) VALUES
(100026);

-- --------------------------------------------------------

--
-- Table structure for table `home_work`
--

CREATE TABLE `home_work` (
  `id` bigint(20) NOT NULL,
  `libelle` longtext,
  `url_image` longtext,
  `url_video` longtext,
  `cours` bigint(20) DEFAULT NULL,
  `type_home_work` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `home_work`
--

INSERT INTO `home_work` (`id`, `libelle`, `url_image`, `url_video`, `cours`, `type_home_work`) VALUES
(100000, 'Phrasebook', NULL, NULL, 10004, 4),
(100001, 'Phrasebook', NULL, NULL, 10005, 4),
(100002, 'Phrasebook', NULL, NULL, 10006, 4),
(100003, 'Phrasebook', NULL, NULL, 10007, 4),
(100004, 'Reading', NULL, NULL, 10005, 1),
(100005, 'Reading', NULL, NULL, 10006, 1),
(100006, 'Reading', NULL, NULL, 10007, 1),
(100007, 'Write it Up', NULL, NULL, 10005, 3),
(100008, 'Write it Up', NULL, NULL, 10006, 3),
(100009, 'Write it Up', NULL, NULL, 10007, 3),
(100010, 'Phrasebook', NULL, NULL, 10016, 4),
(100011, 'Phrasebook', NULL, NULL, 10017, 4),
(100012, 'Phrasebook', NULL, NULL, 10018, 4),
(100013, 'Phrasebook', NULL, NULL, 10019, 4),
(100014, 'Reading', 'https://drive.google.com/uc?id=19zVK6-oHbU-9aY647fx_5kTWZljHlYUJ&export=download', NULL, 10016, 1),
(100015, 'Reading', 'https://drive.google.com/uc?id=1XP3EoHbm3RYvb4osRVmQMp0t8wlXn44h&export=download', NULL, 10017, 1),
(100016, 'Reading', 'https://drive.google.com/uc?id=1UeB5bTbVX5vdxhBfF8sJquFxiHuxyR6G&export=download', NULL, 10018, 1),
(100017, 'Reading', 'https://drive.google.com/uc?id=1HhVfn0BlDSJav75t6AFrPxNAVYJ1YhN7&export=download', NULL, 10019, 1),
(100018, 'Watch it', NULL, 'https://www.youtube.com/watch?v=_53mo10_Mbk', 10016, 5),
(100019, 'Watch it', NULL, 'https://www.youtube.com/watch?v=tZOgdnKJ5Sc&t=66s', 10017, 5),
(100020, 'Watch it', NULL, 'https://www.youtube.com/watch?v=tpN9CPwZ-oE&t=43s', 10018, 5),
(100021, 'Watch it', NULL, 'https://www.youtube.com/watch?v=xRc3WviXk2M', 10019, 5),
(100022, 'Write it Up', 'https://drive.google.com/uc?id=17EOBeD32FGR_LGZnsXSy17f7keD2TLeS&export=download', NULL, 10016, 3),
(100023, 'Write it Up', 'https://drive.google.com/uc?id=1crY3rla4zDBoGaJt5jJp1KeupkiAyQxw&export=download', NULL, 10017, 3),
(100024, 'Write it Up', 'https://drive.google.com/uc?id=1xf3vrB2a1RmPDMBVo-A6lsH4d6OT_z2w&export=download', NULL, 10018, 3),
(100025, 'Write it Up', 'https://drive.google.com/uc?id=1i4CuVRTjpIft7oiDxwKHr4JQpZUggJ7e&export=download', NULL, 10019, 3);

-- --------------------------------------------------------

--
-- Table structure for table `home_work_etudiant`
--

CREATE TABLE `home_work_etudiant` (
  `id` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `note` double DEFAULT NULL,
  `resultat` varchar(255) DEFAULT NULL,
  `etudiant` bigint(20) DEFAULT NULL,
  `home_work` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `home_work_question`
--

CREATE TABLE `home_work_question` (
  `id` bigint(20) NOT NULL,
  `libelle` longtext,
  `numero` int(11) NOT NULL,
  `point_reponse_juste` double NOT NULL,
  `point_reponsefausse` double NOT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `home_work` bigint(20) DEFAULT NULL,
  `type_de_question` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `home_work_question`
--

INSERT INTO `home_work_question` (`id`, `libelle`, `numero`, `point_reponse_juste`, `point_reponsefausse`, `ref`, `home_work`, `type_de_question`) VALUES
(1000000, 'See you later.', 1, 1, 0, NULL, 100000, 3),
(1000001, 'Nice to meet you too!', 2, 1, 0, NULL, 100000, 3),
(1000002, 'Where is your city?', 3, 1, 0, NULL, 100000, 3),
(1000003, 'What is your phone number?', 4, 1, 0, NULL, 100000, 3),
(1000004, 'Hey, what’s up?', 5, 1, 0, NULL, 100000, 3),
(1000005, 'Have a nice day!', 1, 1, 0, NULL, 100001, 3),
(1000006, 'Nice to meet you!', 2, 1, 0, NULL, 100001, 3),
(1000007, 'Good evening!', 3, 1, 0, NULL, 100001, 3),
(1000008, 'My mother is from America.', 4, 1, 0, NULL, 100001, 3),
(1000009, 'How are you?', 5, 1, 0, NULL, 100001, 3),
(1000010, 'I am from Canada too.', 6, 1, 0, NULL, 100001, 3),
(1000011, 'It is my hobby.', 7, 1, 0, NULL, 100001, 3),
(1000012, 'He is twenty years old.', 8, 1, 0, NULL, 100001, 3),
(1000013, 'My name is Ann.', 9, 1, 0, NULL, 100001, 3),
(1000014, 'Good morning!', 10, 1, 0, NULL, 100001, 3),
(1000015, 'We are students.', 1, 1, 0, NULL, 100002, 3),
(1000016, 'Here is my visiting card.', 2, 1, 0, NULL, 100002, 3),
(1000017, 'Good luck!', 3, 1, 0, NULL, 100002, 3),
(1000018, 'See you later!', 4, 1, 0, NULL, 100002, 3),
(1000019, 'It is wrong.', 5, 1, 0, NULL, 100002, 3),
(1000020, 'Who are they?', 6, 1, 0, NULL, 100002, 3),
(1000021, 'I am a teacher.', 7, 1, 0, NULL, 100002, 3),
(1000022, 'She is a good friend.', 8, 1, 0, NULL, 100002, 3),
(1000023, 'Thank you!', 9, 1, 0, NULL, 100002, 3),
(1000024, 'What is your name?', 10, 1, 0, NULL, 100002, 3),
(1000025, 'They are not at work.', 1, 1, 0, NULL, 100003, 3),
(1000026, 'I am not in the photo.', 2, 1, 0, NULL, 100003, 3),
(1000027, 'My mother is also here.', 3, 1, 0, NULL, 100003, 3),
(1000028, 'I am in the middle.', 4, 1, 0, NULL, 100003, 3),
(1000029, 'Here is my photo.', 5, 1, 0, NULL, 100003, 3),
(1000030, 'She is my English teacher.', 6, 1, 0, NULL, 100003, 3),
(1000031, 'Who is she?', 7, 1, 0, NULL, 100003, 3),
(1000032, 'I am English.', 8, 1, 0, NULL, 100003, 3),
(1000033, 'I am from England.', 9, 1, 0, NULL, 100003, 3),
(1000034, 'What is your nationality?', 10, 1, 0, NULL, 100003, 3),
(1000035, 'He always greets me when we meet.', 1, 1, 0, NULL, 100010, 3),
(1000036, 'Michael was a party animal. He didn’t work at all.', 2, 1, 0, NULL, 100010, 3),
(1000037, 'I was not honest with him. I am really sorry about it.', 3, 1, 0, NULL, 100010, 3),
(1000038, 'You were very keen on toy soldiers at that time.', 4, 1, 0, NULL, 100010, 3),
(1000039, 'He’s the most creative of his band members.', 5, 1, 0, NULL, 100010, 3),
(1000040, 'James is well educated. He is a very interesting person.', 1, 1, 0, NULL, 100011, 3),
(1000041, 'Unfortunately, he was also greedy and arrogant.', 2, 1, 0, NULL, 100011, 3),
(1000042, 'She was afraid of darkness when she was a little girl.', 3, 1, 0, NULL, 100011, 3),
(1000043, 'He has a girlfriend. She is very committed.', 4, 1, 0, NULL, 100011, 3),
(1000044, 'He was born in 1990.', 5, 1, 0, NULL, 100011, 3),
(1000045, 'Michael was a party animal. He didn’t work at all.', 6, 1, 0, NULL, 100011, 3),
(1000046, 'I was dishonest with him. I am really sorry about it.', 7, 1, 0, NULL, 100011, 3),
(1000047, 'You were very childish when you were younger.', 8, 1, 0, NULL, 100011, 3),
(1000048, 'He’s fair to his band members.', 9, 1, 0, NULL, 100011, 3),
(1000049, 'Don’t be so boastful! It’s a bad trait.', 10, 1, 0, NULL, 100011, 3),
(1000050, 'Is your husband open-minded?', 1, 1, 0, NULL, 100012, 3),
(1000051, 'Your sympathetic smile is not pleasant for me.', 2, 1, 0, NULL, 100012, 3),
(1000052, 'He will be much wiser than you. I’m sure.', 3, 1, 0, NULL, 100012, 3),
(1000053, 'Please, don’t think that I am hard-hearted and ungrateful.', 4, 1, 0, NULL, 100012, 3),
(1000054, 'I am quite keen on art. I often go to exhibitions.', 5, 1, 0, NULL, 100012, 3),
(1000055, 'When will you be more tolerant of that?', 6, 1, 0, NULL, 100012, 3),
(1000056, 'What are you tired of?', 7, 1, 0, NULL, 100012, 3),
(1000057, 'Will you be more reserved next time?', 8, 1, 0, NULL, 100012, 3),
(1000058, 'I am not a workaholic. I am quite lazy.', 9, 1, 0, NULL, 100012, 3),
(1000059, 'Are you responsible for this project?', 10, 1, 0, NULL, 100012, 3),
(1000060, 'Do you have a little money for me?', 1, 1, 0, NULL, 100013, 3),
(1000061, 'She has all the modern equipment but she doesn\'t have simple scales!', 2, 1, 0, NULL, 100013, 3),
(1000062, 'You will have a good time shopping with us.', 3, 1, 0, NULL, 100013, 3),
(1000063, 'Are there all the modern gadgets in this shop?', 4, 1, 0, NULL, 100013, 3),
(1000064, 'My alarm clock is always slow. But I don’t have another one.', 5, 1, 0, NULL, 100013, 3),
(1000065, 'We don’t have a doorbell, so, please, knock.', 6, 1, 0, NULL, 100013, 3),
(1000066, 'We have a great range of electronic goods.', 7, 1, 0, NULL, 100013, 3),
(1000067, 'You have a chance to know people’s opinion about any kind of products.', 8, 1, 0, NULL, 100013, 3),
(1000068, 'I have no hard drive, so, bring yours, please.', 9, 1, 0, NULL, 100013, 3),
(1000069, 'My juicer is so old. I will have a new one soon.', 10, 1, 0, NULL, 100013, 3),
(1000070, 'Photo Finish by Polly Sweetnam Lisa works at Black and Sons. Black and Sons is a large company. Lisa works in the Accounts Department. The work is very important. But it is not very exciting. \'Your holiday is next week, Lisa. Are you excited?\' \'Oh, yes! I go with Alice. We go to Greece. It will be fun. Perhaps something exciting will happen.\' Alice is Lisa\'s friend. Alice works for Black and Sons too. She works in the Marketing Department. Alice is an artist. She designs advertisements for Black and Sons. \'I want to work with Alice. I want to work in the Marketing Department. The Marketing Department is always busy. Everybody is very friendly. They have exciting jobs.\' Alice phones Lisa. They need to get their passports today. They need to go to the Post Office. \'Hi, Lisa.\' \'Hello, Alice.\' \'Let\'s meet at lunch time. Then we get our passports for our holiday.\' \'OK. I\'ll meet you outside the office at 12:30.\' Lisa and Alice are in the Post Office. The assistant gives the passport forms to the two friends. \'Please fill in these forms. Where are your passport photos?\' \'Here are my photos.\' \'Oh, no! My photos aren\'t in my bag! My photos are at home!\' \'Lisa, go to the photo booth in the old bus station. Get your photos there.\' \'Oh, no! I hate the old bus station. I don\'t want to go there.\' \'But you must get the photos today. Listen, I fill in the forms and you go to the bus station, Lisa. Go and get your photos. I\'ll wait for you here.\' Nobody goes to the bus station now. There is a new bus station in the centre of town. The old bus station is empty and dark. Lisa walks quickly. Her shoes make a loud noise on the ground. Then she sees the photo booth. There is nobody in the photo booth. Lisa is happy. She can get her photos quickly. Lisa goes into the photo booth. She puts her money into the machine. She closes the little curtain. She sits down. She waits. FLASH! The camera is very quick. FLASH! FLASH! FLASH! Now Lisa wants to get her passport today. Lisa goes outside the photo booth. Her photos come out of a slot in the machine. Lisa hears a noise. Some photos come out of the slot. Are they her photos? No, it is too soon. Lisa\'s photos can\'t be ready. But who is in the photos? There is nobody in the bus station. Nobody else waits for photos. Lisa is alone. Lisa takes the photos out of the slot. There is a man in the pictures. But something is wrong. She is frightened. The four photos show a murder! There is a man in the first photo. He is very large. He wears a black coat and a red scarf. The man sits in the photo booth. He smiles. It is not a nice smile. It is a cruel smile. The man is in the second photo. The man looks to the right. He is afraid. Something happens in the third photo. Somebody holds the man\'s scarf. Somebody holds the man\'s scarf very tightly. The large man is pulling at his scarf. There is no man in the fourth photo. There are two hands. The hands hold the red scarf. There is a ring on one of the hands. It is a strange ring. There is a snake on the ring. There is a tattoo on the other hand. It is a tattoo of a bird. The hands are the murderer\'s hands. Now Lisa is very frightened. \'The man with the red scarf is dead. But where is his body? Where is the murderer? Is the murderer in the bus station? The photos show the murder. The murderer knows about the photos. The murderer needs to come back to the photo booth to take the photos. I\'m in danger!\' Lisa and Alice are at the police station. They tell their story to the police. They show the photos to a detective. She is very interested. The detective has a plan. \'I know the man in the photos. He is a criminal. Now he is dead. But we need to catch his murderer. The murderer will go back to the photo booth to pick up his photos. But he will find your photos, Lisa. He will look for you. We need your help, Lisa.\' Lisa is in the bus station. She walks towards the photo booth. She goes to get her photos. The police are in the bus station. They hide. They watch Lisa. They wait for the murderer. Lisa walks slowly. She is very frightened. A man is standing by the photo booth. He is young and handsome. Is this the murderer? The man sees Lisa. He smiles at her. He has a nice smile. He holds some photos. Lisa sees his hands. She sees the snake ring. She sees the bird tattoo! The police catch the murderer. The detective is pleased. Lisa is safe. \'Thank you for your help, Lisa. Well done, Lisa! It\'s exciting to catch criminals.\' \'Exciting? No! It\'s very frightening.\' \'But everything is OK now. You go on holiday. It will be fun.\' \'Yes. Perhaps something exciting will happen!\'', 1, 1, 0, 'Photo Finish by Polly Sweetnam', 100014, 11701),
(1000071, 'Gulliver in Lilliput by Jonathan Swift Part 1 It is 4th May, 1699. The \'Antelope\' goes to the Far East. My name’s Lemuel Gulliver. I’m a ship’s doctor. I like traveling. I’m going to the East Indies. One night the sea is rough. \'What’s wrong?\' \'Help!\' \'It’s a storm!\' There are a lot of waves. The ship and the other men disappear under the water. Gulliver swims and swims. Finally, Gulliver arrives at a beach. He sees no houses. He is very tired and he goes to sleep. \'Where am I? What’s wrong? I can’t move my arms! I can’t move my legs! I can’t move my head!\' There is something on Gulliver’s hand. It moves up his arm. \'What’s that?!\' Gulliver opens his eyes. He sees a very little man. The man is about 15 centimetres tall. He looks at Gulliver. Gulliver is very surprised. Gulliver says ‘Hello’ to the little man. The little man runs away! There are hundreds of little men. They have got bows and arrows. They speak a strange language. \'What do they say? I don’t understand.\' Gulliver is very hungry. He is very thirsty too. I’m hungry and I’m thirsty! But the little men do not understand Gulliver. He moves his hand up and down. Finally, his hand is free. He points to his mouth. I’m hungry and I’m thirsty! Now the little men understand. They give Gulliver meat, bread, fruit and milk. Gulliver is happy. Gulliver goes to sleep again. The little men take him to the city. They need more than 1000 horses! Gulliver wakes up. He is next to big temple. He cannot escape. Gulliver stands up. He sees a city, trees and mountains. Everything is very small.', 1, 1, 0, 'Gulliver in Lilliput by Jonathan Swift', 100015, 11701),
(1000072, 'Gulliver in Lilliput by Jonathan Swift Part 2 One day the emperor visits Gulliver. He speaks to Gulliver but Gulliver does not understand the emperor. The emperor sends ten clever teachers. They teach Gulliver their language. Gulliver learns very quickly. The country’s name is Lilliput. The language is called Lilliputian. Gulliver is very happy. Now he can talk to the little people. One day Gulliver asks the emperor a question. ‘Please, your majesty, can I go? I want to be free!’ But the emperor is not sure. ‘Is Gulliver dangerous?’ he thinks. The emperor asks some officers to look in Gulliver’s pockets. Gulliver helps the men. The officers make a list of the things they find. They tell the emperor what is in Gulliver’s pockets. This strange thing is for his hair. This machine says ‘tick tock’. This is a book. But we don’t understand the words. We don‘t know what this is. ‘Fine. You can free Gulliver.’ Gulliver is free now. He visits the city. The children play with him in the streets. Gulliver is very happy. One day the emperor talks to Gulliver. \'We need your help. The people of Blefuscu want to attack Lilliput.’ \'What‘s Blefuscu?’ \'It‘s a country. The people of Blefuscu are bad.’ \'Why do they want to attack Lilliput?’ \'Because we eat our eggs like this and ...’ \'Oh! And is that important?’ \'… they eat their eggs like that.’ \'Very important.’ \'Really?’ Gulliver helps the emperor. He goes to Blefuscu and pulls its ships to Lilliput. Now Blefuscu cannot attack Lilliput. The emperor of Lilliput is very happy. But there are some bad men in Lilliput. They want to kill Gulliver. ‘He is too big. He eats too much,’ say the bad men. So one day Gulliver leaves Lilliput. He goes to Blefuscu and looks for new adventures there.', 1, 1, 0, '', 100016, 11701),
(1000073, 'Marcel and the Shakespeare letters by Stephen Rabley Part 1 Marcel is a French mouse. He’s a detective and he lives in Paris. But Marcel doesn’t live in Paris all year. Every November he visits London. His old friend – Henry – has a small flat there. Marcel loves London. The beautiful buildings…the big black taxis…the museums and shops. He loves Paris, but he loves London too. This story is about one of Marcel’s November holidays. It starts at three o’clock on Tuesday afternoon. Marcel walks from Knightsbridge station to Henry’s flat. He has two heavy bags with him. Henry’s address is 42 Old Wilton Street. Marcel looks at the numbers – 36 – 38 – 40. Yes, here it is. Number 42. He sees a sign. It says:’ Professor J.T. Barton.’ Marcel looks at it and thinks, ‘That’s new.’ He goes down to Henry’s flat. Henry opens the door and smiles. ‘Marcel!’ he says. ‘Come in, come in!’ ‘Hello, Henry,’ Marcel says. ‘How are you?’ (His English is very good.) ‘I’m very well. And you?’ ‘Yes, thanks.’ ‘Good, good.’ Henry takes Marcel’s coat. ‘Now,’ he says, ‘let’s have some tea.’ The two friends sit in big chairs. They drink tea and talk. At five o’clock Marcel says, ‘There’s a new person in number 42. What’s his name?’ ‘Barton,’ Henry says. ‘Professor Barton. He’s very, very clever. And that’s not all. After tomorrow he’s going to be famous, too!’ ‘Famous?!’ Marcel looks at his English friend. ‘Why?’ ‘It’s a very interesting story,’ Henry says. ‘He has some letters. They were under the floor of an old woman’s house in Oxford.’ ‘And…?’ Marcel says. ‘And they’re from Shakespeare to his son,’ Henry says. ‘Shakespeare!’ ‘Yes.’ Henry smiles. ‘Shakespeare. The old lady telephones Professor Barton, and the professor visited her. After their conversation about the letters she said, “I want to give them to the British Museum in London. Do you have an opportunity to do that for me?”‘ ‘And Professor Barton said yes?’ ‘That’s right.’ ‘Where are the letters now?’ Marcel asks. ‘In the professor’s flat.’ He’s going to give them to the British Museum tomorrow morning at ten o’clock. A lot of journalists and TV people are going to be there.’ Suddenly there’s a big BANG! and then a long WHIZZZ! ‘What’s that noise?’ Marcel asks. He goes to the window. Then he remembers. ‘Oh – fireworks. Of course, it’s 5th of November – your “Guy Fawkes Day”‘. Then a man walks down the steps from 42 Old Wilton Street. Marcel looks at him. ‘Is that Professor Barton?’ he asks. ‘Yes,’ Henry answers. ‘He always goes to the cinema on Tuesday evening,’ ‘Aha!’ Marcel says. ‘Why do you say “Aha!”?’ Henry looks at the French friend. Then suddenly he understands. ‘Oh, you want to look at the Shakespeare letters,’ he smiles. ‘OK. Why not?’', 1, 1, 0, 'Marcel and the Shakespeare letters by Stephen Rabley', 100017, 11701),
(1000074, 'What does the proverb “A peacock who sits on his tail is just another turkey.” mean? Explain it in several sentences. Do you tend to be such kind of peacock sometimes?', 1, 1, 0, NULL, 100022, 2),
(1000075, 'Write full answers to the questions: What do you think is difficult about sharing a flat with other people? Why? What are the three most important personal characteristics of a flatmate? Why?', 1, 1, 0, NULL, 100023, 2),
(1000076, 'Give definitions to the following words: hard-hearted; open-minded; party animal; workaholic; wise.', 1, 1, 0, NULL, 100024, 2),
(1000077, 'List all the devices you can’t live without. Explain your choice.', 1, 1, 0, NULL, 100025, 2);

-- --------------------------------------------------------

--
-- Table structure for table `howe_workqstreponse`
--

CREATE TABLE `howe_workqstreponse` (
  `id` bigint(20) NOT NULL,
  `etat_reponse` varchar(255) DEFAULT NULL,
  `lib` longtext,
  `numero` int(11) NOT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `home_work_question` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `howe_workqstreponse`
--

INSERT INTO `howe_workqstreponse` (`id`, `etat_reponse`, `lib`, `numero`, `ref`, `home_work_question`) VALUES
(1000000, 'true', 'أراك لاحقًا.', 1, NULL, 1000000),
(1000001, 'true', 'سُعدت برؤيتك!', 2, NULL, 1000001),
(1000002, 'true', 'اين مدينتك؟', 3, NULL, 1000002),
(1000003, 'true', 'ما هو رقم هاتفك؟', 4, NULL, 1000003),
(1000004, 'true', 'مرحبا، كيف حالك؟', 5, NULL, 1000004),
(1000005, 'true', 'طاب يومك!', 1, NULL, 1000005),
(1000006, 'true', 'سعيد بلقائك!', 2, NULL, 1000006),
(1000007, 'true', 'مساء الخير!', 3, NULL, 1000007),
(1000008, 'true', 'أمي من أمريكا.', 4, NULL, 1000008),
(1000009, 'true', 'كيف حالك؟', 5, NULL, 1000009),
(1000010, 'true', 'انا من كندا ايضا.', 6, NULL, 1000010),
(1000011, 'true', 'إنها هوايتي.', 7, NULL, 1000011),
(1000012, 'true', 'يبلغ من العمر عشرين عامًا.', 8, NULL, 1000012),
(1000013, 'true', 'اسمي آن.', 9, NULL, 1000013),
(1000014, 'true', 'صباح الخير!', 10, NULL, 1000014),
(1000015, 'true', 'نحن طلاب.', 1, NULL, 1000015),
(1000016, 'true', 'ها هي بطاقة الزيارة الخاصة بي.', 2, NULL, 1000016),
(1000017, 'true', 'حظا طيبا وفقك الله!', 3, NULL, 1000017),
(1000018, 'true', 'أراك لاحقًا!', 4, NULL, 1000018),
(1000019, 'true', 'انه خطأ.', 5, NULL, 1000019),
(1000020, 'true', 'من هؤلاء؟', 6, NULL, 1000020),
(1000021, 'true', 'انا معلم.', 7, NULL, 1000021),
(1000022, 'true', 'إنها صديقة جيده.', 8, NULL, 1000022),
(1000023, 'true', 'شكرًا لك!', 9, NULL, 1000023),
(1000024, 'true', 'ما اسمك؟', 10, NULL, 1000024),
(1000025, 'true', 'هم ليسوا في العمل.', 1, NULL, 1000025),
(1000026, 'true', 'انا لست في الصورة', 2, NULL, 1000026),
(1000027, 'true', 'والدتي هنا أيضا.', 3, NULL, 1000027),
(1000028, 'true', 'أنا في الوسط.', 4, NULL, 1000028),
(1000029, 'true', 'ها هي صورتي.', 5, NULL, 1000029),
(1000030, 'true', 'هي مدرستي للغة الإنجليزية.', 6, NULL, 1000030),
(1000031, 'true', 'من هي؟', 7, NULL, 1000031),
(1000032, 'true', 'أنا انجليزي.', 8, NULL, 1000032),
(1000033, 'true', 'انا من انجلترا.', 9, NULL, 1000033),
(1000034, 'true', 'ما هيه جنسيتك؟', 10, NULL, 1000034),
(1000035, 'true', 'دائما يرحب بي عندما نلتقي.', 1, NULL, 1000035),
(1000036, 'true', 'كان مايكل حيوان الحفلة. لم يعمل إطلاقا.', 2, NULL, 1000036),
(1000037, 'true', 'لم أكن صادقة معه. أنا حقا آسف لذلك.', 3, NULL, 1000037),
(1000038, 'true', 'كنت حريصًا جدًا على لعبة الجنود في ذلك الوقت.', 4, NULL, 1000038),
(1000039, 'true', 'إنه أكثر أعضاء فرقته إبداعًا.', 5, NULL, 1000039),
(1000040, 'true', 'جيمس متعلم جيدًا. إنه شخص مثير للاهتمام للغاية.', 1, NULL, 1000040),
(1000041, 'true', 'لسوء الحظ ، كان أيضًا جشعًا ومتغطرسًا.', 2, NULL, 1000041),
(1000042, 'true', 'كانت خائفة من الظلام عندما كانت طفلة صغيرة.', 3, NULL, 1000042),
(1000043, 'true', 'هو لديه صديقة. هي ملتزمة جدا.', 4, NULL, 1000043),
(1000044, 'true', 'ولد عام 1990.', 5, NULL, 1000044),
(1000045, 'true', 'كان مايكل حيوان الحفلة. لم يعمل إطلاقا.', 6, NULL, 1000045),
(1000046, 'true', 'كنت غير أمين معه. أنا حقا آسف لذلك.', 7, NULL, 1000046),
(1000047, 'true', 'كنت طفولية جدا عندما كنت أصغر سنا.', 8, NULL, 1000047),
(1000048, 'true', 'إنه عادل لأعضاء فرقته.', 9, NULL, 1000048),
(1000049, 'true', 'لا تكن متفاخرًا! إنها صفة سيئة.', 10, NULL, 1000049),
(1000050, 'true', 'هل زوجك متفتح الذهن؟', 1, NULL, 1000050),
(1000051, 'true', 'ابتسامتك المتعاطفة ليست لطيفة بالنسبة لي.', 2, NULL, 1000051),
(1000052, 'true', 'سيكون أكثر حكمة منك. أنا متأكد.', 3, NULL, 1000052),
(1000053, 'true', 'من فضلك ، لا تعتقد أنني قاسي القلب وغير ممتن.', 4, NULL, 1000053),
(1000054, 'true', 'أنا حريص جدا على الفن. كثيرا ما أذهب إلى المعارض.', 5, NULL, 1000054),
(1000055, 'true', 'متى ستكون أكثر تسامحا مع ذلك؟', 6, NULL, 1000055),
(1000056, 'true', 'ما الذي تعبت منه؟', 7, NULL, 1000056),
(1000057, 'true', 'هل ستكون أكثر تحفظا في المرة القادمة؟', 8, NULL, 1000057),
(1000058, 'true', 'أنا لست مدمن عمل. أنا كسول جدا.', 9, NULL, 1000058),
(1000059, 'true', 'هل أنت مسؤول عن هذا المشروع؟', 10, NULL, 1000059),
(1000060, 'true', 'هل لديك القليل من المال لي؟', 1, NULL, 1000060),
(1000061, 'true', 'لديها كل المعدات الحديثة ولكن ليس لديها موازين بسيطة!', 2, NULL, 1000061),
(1000062, 'true', 'سيكون لديك وقت ممتع للتسوق معنا.', 3, NULL, 1000062),
(1000063, 'true', 'هل توجد جميع الأدوات الحديثة في هذا المحل؟', 4, NULL, 1000063),
(1000064, 'true', 'المنبه دائما بطيء. لكن ليس لدي واحدة أخرى.', 5, NULL, 1000064),
(1000065, 'true', 'ليس لدينا جرس باب ، لذا من فضلك ، اطرق.', 6, NULL, 1000065),
(1000066, 'true', 'لدينا مجموعة كبيرة من السلع الإلكترونية.', 7, NULL, 1000066),
(1000067, 'true', 'لديك فرصة لمعرفة آراء الناس حول أي نوع من المنتجات.', 8, NULL, 1000067),
(1000068, 'true', 'ليس لدي محرك أقراص صلبة ، لذا أحضر محرك الأقراص الخاص بك ، من فضلك.', 9, NULL, 1000068),
(1000069, 'true', 'عصاري قديمة جدا. سأحصل على واحدة جديدة قريبا.', 10, NULL, 1000069),
(1000070, 'true', 'أراك لاحقًا.', 1, NULL, 1000000),
(1000071, 'true', 'سُعدت برؤيتك!', 2, NULL, 1000001),
(1000072, 'true', 'اين مدينتك؟', 3, NULL, 1000002),
(1000073, 'true', 'ما هو رقم هاتفك؟', 4, NULL, 1000003),
(1000074, 'true', 'مرحبا، كيف حالك؟', 5, NULL, 1000004),
(1000075, 'true', 'طاب يومك!', 1, NULL, 1000005),
(1000076, 'true', 'سعيد بلقائك!', 2, NULL, 1000006),
(1000077, 'true', 'مساء الخير!', 3, NULL, 1000007),
(1000078, 'true', 'أمي من أمريكا.', 4, NULL, 1000008),
(1000079, 'true', 'كيف حالك؟', 5, NULL, 1000009),
(1000080, 'true', 'انا من كندا ايضا.', 6, NULL, 1000010),
(1000081, 'true', 'إنها هوايتي.', 7, NULL, 1000011),
(1000082, 'true', 'يبلغ من العمر عشرين عامًا.', 8, NULL, 1000012),
(1000083, 'true', 'اسمي آن.', 9, NULL, 1000013),
(1000084, 'true', 'صباح الخير!', 10, NULL, 1000014),
(1000085, 'true', 'نحن طلاب.', 1, NULL, 1000015),
(1000086, 'true', 'ها هي بطاقة الزيارة الخاصة بي.', 2, NULL, 1000016),
(1000087, 'true', 'حظا طيبا وفقك الله!', 3, NULL, 1000017),
(1000088, 'true', 'أراك لاحقًا!', 4, NULL, 1000018),
(1000089, 'true', 'انه خطأ.', 5, NULL, 1000019),
(1000090, 'true', 'من هؤلاء؟', 6, NULL, 1000020),
(1000091, 'true', 'انا معلم.', 7, NULL, 1000021),
(1000092, 'true', 'إنها صديقة جيده.', 8, NULL, 1000022),
(1000093, 'true', 'شكرًا لك!', 9, NULL, 1000023),
(1000094, 'true', 'ما اسمك؟', 10, NULL, 1000024),
(1000095, 'true', 'هم ليسوا في العمل.', 1, NULL, 1000025),
(1000096, 'true', 'انا لست في الصورة', 2, NULL, 1000026),
(1000097, 'true', 'والدتي هنا أيضا.', 3, NULL, 1000027),
(1000098, 'true', 'أنا في الوسط.', 4, NULL, 1000028),
(1000099, 'true', 'ها هي صورتي.', 5, NULL, 1000029),
(1000100, 'true', 'هي مدرستي للغة الإنجليزية.', 6, NULL, 1000030),
(1000101, 'true', 'من هي؟', 7, NULL, 1000031),
(1000102, 'true', 'أنا انجليزي.', 8, NULL, 1000032),
(1000103, 'true', 'انا من انجلترا.', 9, NULL, 1000033),
(1000104, 'true', 'ما هيه جنسيتك؟', 10, NULL, 1000034),
(1000105, 'true', 'دائما يرحب بي عندما نلتقي.', 1, NULL, 1000035),
(1000106, 'true', 'كان مايكل حيوان الحفلة. لم يعمل إطلاقا.', 2, NULL, 1000036),
(1000107, 'true', 'لم أكن صادقة معه. أنا حقا آسف لذلك.', 3, NULL, 1000037),
(1000108, 'true', 'كنت حريصًا جدًا على لعبة الجنود في ذلك الوقت.', 4, NULL, 1000038),
(1000109, 'true', 'إنه أكثر أعضاء فرقته إبداعًا.', 5, NULL, 1000039),
(1000110, 'true', 'جيمس متعلم جيدًا. إنه شخص مثير للاهتمام للغاية.', 1, NULL, 1000040),
(1000111, 'true', 'لسوء الحظ ، كان أيضًا جشعًا ومتغطرسًا.', 2, NULL, 1000041),
(1000112, 'true', 'كانت خائفة من الظلام عندما كانت طفلة صغيرة.', 3, NULL, 1000042),
(1000113, 'true', 'هو لديه صديقة. هي ملتزمة جدا.', 4, NULL, 1000043),
(1000114, 'true', 'ولد عام 1990.', 5, NULL, 1000044),
(1000115, 'true', 'كان مايكل حيوان الحفلة. لم يعمل إطلاقا.', 6, NULL, 1000045),
(1000116, 'true', 'كنت غير أمين معه. أنا حقا آسف لذلك.', 7, NULL, 1000046),
(1000117, 'true', 'كنت طفولية جدا عندما كنت أصغر سنا.', 8, NULL, 1000047),
(1000118, 'true', 'إنه عادل لأعضاء فرقته.', 9, NULL, 1000048),
(1000119, 'true', 'لا تكن متفاخرًا! إنها صفة سيئة.', 10, NULL, 1000049),
(1000120, 'true', 'هل زوجك متفتح الذهن؟', 1, NULL, 1000050),
(1000121, 'true', 'ابتسامتك المتعاطفة ليست لطيفة بالنسبة لي.', 2, NULL, 1000051),
(1000122, 'true', 'سيكون أكثر حكمة منك. أنا متأكد.', 3, NULL, 1000052),
(1000123, 'true', 'من فضلك ، لا تعتقد أنني قاسي القلب وغير ممتن.', 4, NULL, 1000053),
(1000124, 'true', 'أنا حريص جدا على الفن. كثيرا ما أذهب إلى المعارض.', 5, NULL, 1000054),
(1000125, 'true', 'متى ستكون أكثر تسامحا مع ذلك؟', 6, NULL, 1000055),
(1000126, 'true', 'ما الذي تعبت منه؟', 7, NULL, 1000056),
(1000127, 'true', 'هل ستكون أكثر تحفظا في المرة القادمة؟', 8, NULL, 1000057),
(1000128, 'true', 'أنا لست مدمن عمل. أنا كسول جدا.', 9, NULL, 1000058),
(1000129, 'true', 'هل أنت مسؤول عن هذا المشروع؟', 10, NULL, 1000059),
(1000130, 'true', 'هل لديك القليل من المال لي؟', 1, NULL, 1000060),
(1000131, 'true', 'لديها كل المعدات الحديثة ولكن ليس لديها موازين بسيطة!', 2, NULL, 1000061),
(1000132, 'true', 'سيكون لديك وقت ممتع للتسوق معنا.', 3, NULL, 1000062),
(1000133, 'true', 'هل توجد جميع الأدوات الحديثة في هذا المحل؟', 4, NULL, 1000063),
(1000134, 'true', 'المنبه دائما بطيء. لكن ليس لدي واحدة أخرى.', 5, NULL, 1000064),
(1000135, 'true', 'ليس لدينا جرس باب ، لذا من فضلك ، اطرق.', 6, NULL, 1000065),
(1000136, 'true', 'لدينا مجموعة كبيرة من السلع الإلكترونية.', 7, NULL, 1000066),
(1000137, 'true', 'لديك فرصة لمعرفة آراء الناس حول أي نوع من المنتجات.', 8, NULL, 1000067),
(1000138, 'true', 'ليس لدي محرك أقراص صلبة ، لذا أحضر محرك الأقراص الخاص بك ، من فضلك.', 9, NULL, 1000068),
(1000139, 'true', 'عصاري قديمة جدا. سأحصل على واحدة جديدة قريبا.', 10, NULL, 1000069);

-- --------------------------------------------------------

--
-- Table structure for table `inscription`
--

CREATE TABLE `inscription` (
  `id` bigint(20) NOT NULL,
  `date_registration` varchar(255) DEFAULT NULL,
  `datedebutinscription` date DEFAULT NULL,
  `datefininscription` date DEFAULT NULL,
  `numero_inscription` int(11) NOT NULL,
  `skype` varchar(255) DEFAULT NULL,
  `etat_inscription` bigint(20) DEFAULT NULL,
  `etudiant` bigint(20) DEFAULT NULL,
  `fonction` bigint(20) DEFAULT NULL,
  `groupe_etude` bigint(20) DEFAULT NULL,
  `interet_etudiant` bigint(20) DEFAULT NULL,
  `niveau_etude` bigint(20) DEFAULT NULL,
  `pack_student` bigint(20) DEFAULT NULL,
  `parcours` bigint(20) DEFAULT NULL,
  `skill` bigint(20) DEFAULT NULL,
  `statut_social` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `inscription`
--

INSERT INTO `inscription` (`id`, `date_registration`, `datedebutinscription`, `datefininscription`, `numero_inscription`, `skype`, `etat_inscription`, `etudiant`, `fonction`, `groupe_etude`, `interet_etudiant`, `niveau_etude`, `pack_student`, `parcours`, `skill`, `statut_social`) VALUES
(1, '2022/07/07 15:17:21', NULL, NULL, 0, NULL, 2, 4, NULL, 25906, 2, 2, 2, 200, 12, 1);

-- --------------------------------------------------------

--
-- Table structure for table `interet_etudiant`
--

CREATE TABLE `interet_etudiant` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `interet_etudiant`
--

INSERT INTO `interet_etudiant` (`id`, `code`, `libelle`) VALUES
(1, '1', 'Discussing with family'),
(2, '2', 'Boosting my studies '),
(3, '3', 'Self cultivation'),
(4, '4', 'Boosting my career\r\n'),
(5, '5', 'Travel'),
(6, '6', 'Other');

-- --------------------------------------------------------

--
-- Table structure for table `invite_student`
--

CREATE TABLE `invite_student` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `date_accept_invitation` datetime DEFAULT NULL,
  `date_pay_pack` datetime DEFAULT NULL,
  `date_sent_invitation` datetime DEFAULT NULL,
  `email_invited` varchar(255) DEFAULT NULL,
  `is_accepted` bit(1) NOT NULL,
  `is_paid_pack` bit(1) NOT NULL,
  `etudiant` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `level_seq`
--

CREATE TABLE `level_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `level_seq`
--

INSERT INTO `level_seq` (`next_val`) VALUES
(204);

-- --------------------------------------------------------

--
-- Table structure for table `news`
--

CREATE TABLE `news` (
  `id` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `date_debut` datetime DEFAULT NULL,
  `date_fin` datetime DEFAULT NULL,
  `description` longtext,
  `destinataire` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `libelle` longtext,
  `numero_ordre` int(11) NOT NULL,
  `ref` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `niveau_etude`
--

CREATE TABLE `niveau_etude` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `niveau_etude`
--

INSERT INTO `niveau_etude` (`id`, `code`, `libelle`) VALUES
(1, '1', 'Secondary'),
(2, '2', 'Primary '),
(3, '3', 'College'),
(4, '4', 'Other');

-- --------------------------------------------------------

--
-- Table structure for table `pack_student`
--

CREATE TABLE `pack_student` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` longtext,
  `expectations` longtext,
  `for_groupe` bit(1) NOT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `nombre_cours` int(11) NOT NULL,
  `old_price` varchar(255) DEFAULT NULL,
  `pre_requisites` longtext,
  `prix` double DEFAULT NULL,
  `rating` varchar(255) DEFAULT NULL,
  `total_students` int(11) NOT NULL,
  `why_take_this_course` longtext,
  `level` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pack_student`
--

INSERT INTO `pack_student` (`id`, `code`, `description`, `expectations`, `for_groupe`, `img_url`, `libelle`, `nombre_cours`, `old_price`, `pre_requisites`, `prix`, `rating`, `total_students`, `why_take_this_course`, `level`) VALUES
(2, 'pi1', 'Explore complex topics relevant to modern life', 'This course covers vocabulary at the CEFR C1-C2 levels. You will work on gaining fluency by speaking at length about a wide array of topics. In addition, you will receive corrections from a native English speaker to help improve coherence and grammatical accuracy.', b'0', 'https://images.pexels.com/photos/1595391/pexels-photo-1595391.jpeg?auto=compress&cs=tinysrgb&w=600', 'Advanced for Individual', 50, '340', 'You should be able to speak at length about abstract topics before beginning this course.\n\n', 200, '4.5', 0, 'Challenge yourself with new topics to expand you vocabulary and increase your comfort with the English language.', 200),
(5, 'Pg1', 'Advanced Conversation Topics\nExplore complex topics relevant to modern life', 'This course covers vocabulary at the CEFR C1-C2 levels. You will work on gaining fluency by speaking at length about a wide array of topics. In addition, you will receive corrections from a native English speaker to help improve coherence and grammatical accuracy.', b'1', 'https://images.pexels.com/photos/1516440/pexels-photo-1516440.jpeg?auto=compress&cs=tinysrgb&w=600', 'Advanced for Groups', 50, '210', 'You should be able to speak at length about abstract topics before beginning this course.\n\n', 120, '4.1', 0, 'Challenge yourself with new topics to expand you vocabulary and increase your comfort with the English language.', 200);

-- --------------------------------------------------------

--
-- Table structure for table `paiement`
--

CREATE TABLE `paiement` (
  `id` bigint(20) NOT NULL,
  `date_paiement` datetime DEFAULT NULL,
  `groupe_etudiant` bigint(20) DEFAULT NULL,
  `prof` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `parcours`
--

CREATE TABLE `parcours` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `date_creation` date DEFAULT NULL,
  `date_publication` date DEFAULT NULL,
  `description` longtext,
  `libelle` varchar(255) DEFAULT NULL,
  `nombre_cours` int(11) NOT NULL,
  `numero_order` int(11) NOT NULL,
  `centre` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `parcours`
--

INSERT INTO `parcours` (`id`, `code`, `date_creation`, `date_publication`, `description`, `libelle`, `nombre_cours`, `numero_order`, `centre`) VALUES
(200, 'Advanced', NULL, NULL, NULL, 'Advanced', 4, 0, 83),
(201, 'Elementry', NULL, NULL, NULL, 'Elementry', 5, 0, 83),
(202, 'Intermediate', NULL, NULL, NULL, 'Intermediate', 7, 0, 83),
(203, 'Pre Intermediate', NULL, NULL, NULL, 'Pre Intermediate', 4, 0, 83);

-- --------------------------------------------------------

--
-- Table structure for table `prof_review`
--

CREATE TABLE `prof_review` (
  `id` bigint(20) NOT NULL,
  `comment` longtext,
  `date_review` datetime DEFAULT NULL,
  `review` int(11) NOT NULL,
  `cours` bigint(20) DEFAULT NULL,
  `etudiant` bigint(20) DEFAULT NULL,
  `prof` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `id` bigint(20) NOT NULL,
  `libelle` longtext,
  `numero` bigint(20) DEFAULT NULL,
  `point_reponse_juste` double NOT NULL,
  `point_reponsefausse` double NOT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `url_img` longtext,
  `url_video` longtext,
  `home_work` bigint(20) DEFAULT NULL,
  `quiz` bigint(20) DEFAULT NULL,
  `type_de_question` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `libelle`, `numero`, `point_reponse_juste`, `point_reponsefausse`, `ref`, `url_img`, `url_video`, `home_work`, `quiz`, `type_de_question`) VALUES
(10, '\nLucas goes to school @every day@ of the week. He has many @subjects@ to go to each school day: English, art, science, mathematics, gym, @and history@. His mother packs a big @backpack@ full of books and lunch for Lucas.\n\nHis first class is English, and he likes that@ teacher@ very much. His English teacher says that he@ is a good @pupil, which Lucas knows means that she @thinks@ he is a good student.', 1, 1, 0, 'At school', NULL, NULL, NULL, 9, 10);

-- --------------------------------------------------------

--
-- Table structure for table `quiz`
--

CREATE TABLE `quiz` (
  `id` bigint(20) NOT NULL,
  `date_debut` datetime DEFAULT NULL,
  `date_fin` datetime DEFAULT NULL,
  `lib` longtext,
  `numero` bigint(20) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `seuil_reussite` bigint(20) DEFAULT NULL,
  `section` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `quiz`
--

INSERT INTO `quiz` (`id`, `date_debut`, `date_fin`, `lib`, `numero`, `ref`, `seuil_reussite`, `section`) VALUES
(9, '2022-07-13 14:48:17', '2022-07-13 14:48:17', 'Warm up', NULL, 'quiz-200019', NULL, 200019);

-- --------------------------------------------------------

--
-- Table structure for table `quiz_class_room`
--

CREATE TABLE `quiz_class_room` (
  `id` bigint(20) NOT NULL,
  `class_room` bigint(20) DEFAULT NULL,
  `quiz` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `quiz_etudiant`
--

CREATE TABLE `quiz_etudiant` (
  `id` bigint(20) NOT NULL,
  `note` double DEFAULT NULL,
  `question_current` bigint(20) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `resultat` varchar(255) DEFAULT NULL,
  `etudiant` bigint(20) DEFAULT NULL,
  `quiz` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `reclamation_etudiant`
--

CREATE TABLE `reclamation_etudiant` (
  `id` bigint(20) NOT NULL,
  `commentaire_traiteur` varchar(255) DEFAULT NULL,
  `date_reclamation` datetime DEFAULT NULL,
  `date_reponse` datetime DEFAULT NULL,
  `date_traitement` datetime DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `message` longtext,
  `objet_reclamation_etudiant` varchar(255) DEFAULT NULL,
  `post_view` bit(1) NOT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `set_from` varchar(255) DEFAULT NULL,
  `traite` bit(1) DEFAULT NULL,
  `type_reclamation_etudiant` bigint(20) DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `reclamation_prof`
--

CREATE TABLE `reclamation_prof` (
  `id` bigint(20) NOT NULL,
  `commentaire_traiteur` varchar(255) DEFAULT NULL,
  `date_reclamation` datetime DEFAULT NULL,
  `date_reponse` datetime DEFAULT NULL,
  `date_traitement` datetime DEFAULT NULL,
  `message` longtext,
  `objet_reclamation_prof` varchar(255) DEFAULT NULL,
  `post_view` bit(1) NOT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `traite` bit(1) DEFAULT NULL,
  `admin` bigint(20) DEFAULT NULL,
  `prof` bigint(20) DEFAULT NULL,
  `type_reclamation_prof` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `recommend_teacher`
--

CREATE TABLE `recommend_teacher` (
  `id` bigint(20) NOT NULL,
  `commentaire` varchar(255) DEFAULT NULL,
  `date_recommamdation` datetime DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `nombrevote` int(11) NOT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `prof` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `reponse`
--

CREATE TABLE `reponse` (
  `id` bigint(20) NOT NULL,
  `etat_reponse` varchar(255) DEFAULT NULL,
  `lib` longtext,
  `numero` bigint(20) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `question` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `reponse_etudiant`
--

CREATE TABLE `reponse_etudiant` (
  `id` bigint(20) NOT NULL,
  `answer` longtext,
  `note` double DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `question` bigint(20) DEFAULT NULL,
  `quiz_etudiant` bigint(20) DEFAULT NULL,
  `reponse` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `reponse_etudiant_home_work`
--

CREATE TABLE `reponse_etudiant_home_work` (
  `id` bigint(20) NOT NULL,
  `answer` longtext,
  `note` double DEFAULT NULL,
  `prof_note` varchar(10000) DEFAULT NULL,
  `home_work_etudiant` bigint(20) DEFAULT NULL,
  `question` bigint(20) DEFAULT NULL,
  `reponse` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `authority` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `authority`) VALUES
(3, 'STUDENT'),
(2, 'TEACHER'),
(1, 'ADMIN'),
(4, 'SUPER_ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `salary`
--

CREATE TABLE `salary` (
  `id` bigint(20) NOT NULL,
  `annee` int(11) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `mois` int(11) NOT NULL,
  `nbr_session_mensuel` decimal(19,2) DEFAULT NULL,
  `payer` bit(1) NOT NULL,
  `total_bonus_class_average` decimal(19,2) DEFAULT NULL,
  `total_bonus_workload` decimal(19,2) DEFAULT NULL,
  `total_payment` decimal(19,2) DEFAULT NULL,
  `prof` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `schdedule_vo`
--

CREATE TABLE `schdedule_vo` (
  `id` bigint(20) NOT NULL,
  `color` varchar(255) DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `prof` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `schedule_prof`
--

CREATE TABLE `schedule_prof` (
  `id` bigint(20) NOT NULL,
  `end_time` datetime DEFAULT NULL,
  `grp_name` varchar(255) DEFAULT NULL,
  `prof_name` varchar(255) DEFAULT NULL,
  `profs_id` bigint(20) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `cours` bigint(20) DEFAULT NULL,
  `groupe_etudiant` bigint(20) DEFAULT NULL,
  `prof` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `schedule_prof`
--

INSERT INTO `schedule_prof` (`id`, `end_time`, `grp_name`, `prof_name`, `profs_id`, `ref`, `start_time`, `subject`, `cours`, `groupe_etudiant`, `prof`) VALUES
(8, '2022-07-13 11:00:00', 'GroupYsf', 'teacher', 2, '5235', '2022-07-13 10:00:00', 'Intro lesson', 10000, 6, 2);

-- --------------------------------------------------------

--
-- Table structure for table `section`
--

CREATE TABLE `section` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `content` int(11) NOT NULL,
  `contenu` longtext,
  `indication_prof` longtext,
  `libelle` longtext,
  `numero_order` int(11) DEFAULT NULL,
  `questions` longtext,
  `url` int(11) NOT NULL,
  `url_image` longtext,
  `url_image2` longtext,
  `url_image3` longtext,
  `url_video` longtext,
  `categorie_section` bigint(20) DEFAULT NULL,
  `cours` bigint(20) DEFAULT NULL,
  `session_cours` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `section`
--

INSERT INTO `section` (`id`, `code`, `content`, `contenu`, `indication_prof`, `libelle`, `numero_order`, `questions`, `url`, `url_image`, `url_image2`, `url_image3`, `url_video`, `categorie_section`, `cours`, `session_cours`) VALUES
(200000, 'Time to talk', 0, '1. What is your definition of success?\n2. If you have not created the success you want yet, what is preventing this from occurring? Is it you, or something else?\n3. Are you seeking approval from anyone else besides yourself?\n4. Do you care what other people think?\n5. Are you making an impact in the world that feels good to you?\n6. Do you believe that you can create the opportunities that you desire?\n7. What would you need to include MORE of in your life?\n8. What would you need to have LESS of in your life?\n9. What are you putting up with that goes against your values?\n10. Do you have someone that supports you regardless of your ability to be successful?\n', NULL, 'Time to talk', 6, NULL, 0, 'https://drive.google.com/uc?id=18Zb_1qkCctGjb-VcPJTlqfZVlrauJs4y&export=download', NULL, NULL, NULL, 3, 10000, NULL),
(200001, 'Time to talk', 0, '1. When you hear the word \"happiness\", what is the first thing you think about?\n2. How important is happiness for one\'s health?\n3. What part of daily routine makes you the happiest?\n4. What has been the happiest moment of your life?\n5. How important is talent in achieving success?\n6. How do you measure success?\n7. Do you think failures can contribute to a person\'s success?\n8. Can you think of any famous people who encountered failure before success?\n9. Paraphrase the following quotation: \"Success usually comes to those who are too busy to be looking for it.\"\n10. \"You always pass failure on the way to success.\" Do you agree with the quotation? Why (not)?\n', NULL, 'Time to talk', 6, NULL, 0, 'https://drive.google.com/uc?id=1aTkmcNlgaq1sjML7ZI8KAisqdhNZ74nF&export=download', NULL, NULL, NULL, 3, 10001, NULL),
(200002, 'Time to talk', 0, '1. How do you understand the word perseverance?\n2. How often do you set goals for yourself?\n3. How do you feel when you reach your goals?\n4. Do you have enough resilience? Any ideas on how to develop this trait?\n5. What is your opinion on premonitions? Are they real?\n6. Do you feel that problems are opportunities? Why or why not?\n7. “Problems don’t matter. Solutions do.” Do you agree or disagree?\n8. Can money bring you happiness?\n9. What parts of everyday life can money help to improve? What parts can it potentially make worse?\n10. How would your life be different if you had £10,000, £100,000, £1m? What would be the very first thing you would buy?\n', NULL, 'Time to talk', 6, NULL, 0, 'https://drive.google.com/uc?id=1BKl1Vq8sEeqQDrJbZdqNVes4bHfkkuHW&export=download', NULL, NULL, NULL, 3, 10002, NULL),
(200003, 'Time to talk', 0, '1. Do you have a good memory or a poor memory?\n2. What\'s your most vivid (clear or sharp) memory? When was it? Describe it in detail.\n3. There are different methods for improving memory. Which methods do you find most and least appealing?\n4. Do you have a photographic memory?\n5. Memories make the man. What do you think this means? Can you give some examples?\n6. Have you ever forgotten an important date, like a birthday or an anniversary? Can you tell us what happened?\n7. As people grow older, sometimes the distant past is easier to remember than the near past. Why do you think this is? Have you ever seen examples of this?\n8. Many people find that a certain sound or smell brings back a childhood memory very strongly. Why do you think this is? What has this effect on you?\n9. If you could edit your memories, which ones would you erase and why?\n10. How would life be different if you could remember everything?\n', NULL, 'Time to talk', 6, NULL, 0, 'https://drive.google.com/uc?id=13iVkZ6u3qWckMcTx9BF_KyBQtAfokvaq&export=download', NULL, NULL, NULL, 3, 10003, NULL),
(200004, 'Study and choose best options', 0, '', NULL, 'Study and choose best options', 10, NULL, 0, 'https://drive.google.com/uc?id=17dgDVryKpEGhQ3RuIsg_TUVsoYuEeE1s&export=download', NULL, NULL, NULL, 5, 10000, NULL),
(200005, 'Study and choose best options', 0, '', NULL, 'Study and choose best options', 10, NULL, 0, 'https://drive.google.com/uc?id=1O6L4oNvWYsywPX9QmDMXzoWDt4Kan4fe&export=download', NULL, NULL, NULL, 5, 10001, NULL),
(200006, 'Study the information', 0, '\n', NULL, 'Study the information', 4, NULL, 0, 'https://drive.google.com/uc?id=1r5ArBXas5SRMURvQDu9WaXDGzEXQh7vQ&export=download', NULL, NULL, NULL, 2, 10000, NULL),
(200007, 'Study the information', 0, '\n', NULL, 'Study the information', 4, NULL, 0, 'https://drive.google.com/uc?id=1N4wkU7zgE72Fpkxv4A0sU-s8qrBxEu3q&export=download', NULL, NULL, NULL, 2, 10003, NULL),
(200008, 'Put words to gap', 0, '', NULL, 'Put words to gap', 8, NULL, 0, 'https://drive.google.com/uc?id=1I9VN1OVXi2cj4tVepKymQx39XaOkBykO&export=download', NULL, NULL, NULL, 4, 10001, NULL),
(200009, 'True or false', 0, '', NULL, 'True or false', 8, NULL, 0, NULL, NULL, NULL, NULL, 4, 10002, NULL),
(200010, 'Put words to gap', 0, '', NULL, 'Put words to gap', 8, NULL, 0, 'https://drive.google.com/uc?id=1EHv1TFXtiSC3wRowymiKn-Gp9OjFZV3k&export=download', NULL, NULL, NULL, 4, 10003, NULL),
(200011, '', 0, '', NULL, '', 9, NULL, 0, NULL, NULL, NULL, NULL, 10, 10001, NULL),
(200012, 'Let’s imagine', 0, '\n\nLook at the photographs below.\nCompare and contrast two or three of them, saying what kind of success is portrayed in each one and who you think might be feeling the happiest.\nUseful language:\nComparing\n\nContrasting\n\nSpeculating\nBoth pictures show…\nIn both pictures the people are…\nBoth pictures were taken…\nThe main connection/similarity between these pictures is that…\nThe first picture shows…, whereas the second one…\nIn the picture on the left… whereas in the other one…\nThe main/most striking difference between the two pictures is…\nThere are several differences between the pictures: firstly, ...\nThey could / might / may be…\nThey seem/appear to be…\nI suppose / assume / imagine that…\nIt could / might / may be that…\nI can’t be sure/certain, but perhaps…\nAlthough I can’t be sure, perhaps…\n', NULL, 'Let’s imagine', 9, NULL, 0, 'https://drive.google.com/uc?id=1KBhO0pQTNhE01vMR9yq3VVddP88rR95S&export=download', NULL, NULL, NULL, 10, 10001, NULL),
(200013, 'Let’s imagine', 0, 'Try to convince a friend not to drop out of university, using useful language below:\n\nUseful language:\nAttempting to persuade\n\nResponding positively\n\nResponding negatively\n\nEnding an attempt to persuade\nBut surely the best course of action would be to…\nSurely the most sensible thing would be…\nIt is essential / vital / extremely important that you…\nI really think it would be a pity if we…\nWe’d be making a mistake if we didn’t…\nIf we don’t… now, we won’t be able to… later.\nHave you considered the consequences?\nYou might regret it later if you don’t…\nYou do realise that if… then… don’t you?\nYou might have a point.\nOK, let me sleep on it.\nHmmm… OK, let’s look at the facts again.\nThere’s no point.\nMy decision is final.\nMy mind’s made up, I’m afraid.\nBelieve me, there’s no other way.\nWell, I’ve tried.\nDon’t say I didn’t warn you.\nNot much left to say, then.\nWell, it’s your life.\n', NULL, 'Let’s imagine', 9, NULL, 0, 'https://drive.google.com/uc?id=1jiVfE7Ywk2r3BB0tE4PKyJdVkuS8Il91&export=download', NULL, NULL, NULL, 10, 10002, NULL),
(200014, 'Let’s imagine', 0, 'Your friend is asking for some advice on how to train their memory.\nDescribe one method you prefer using useful language below:\n\nIntroducing the topic\n\nAdvantages\n\nDisadvantages\n\nSummarising\nFirst of all, I’d like to say that…\nThe first point I’d like to make is…\nI’d like to begin by…\nMany people think that…\nSome people say/claim…\nWhy do some people believe… ?\nHowever, if that is the case, then why… ?\nIn the first place…\nThe main advantage is that you are free to…\nThere is no one else to…\nThen there is…\nWhat’s more…\nAnother advantage is that…\nOn the other hand…\nSecondly, …\nAnother disadvantage is…\nLast but not least, …\nIn conclusion (I believe that)...\nTo sum up, there are arguments…\nHowever, in my view…\n', NULL, 'Let’s imagine', 9, NULL, 0, 'https://drive.google.com/uc?id=11lDir1CtblQ476D1sltgSWEIsx45jFoz&export=download', NULL, NULL, NULL, 10, 10003, NULL),
(200015, 'Learn the words', 0, '', NULL, 'Learn the words', 3, NULL, 0, 'https://drive.google.com/uc?id=1mXXyltNvDUoo_z83prsVkr43nHCTfESk&export=download', NULL, NULL, NULL, 8, 10000, NULL),
(200016, 'Learn the words', 0, '', NULL, 'Learn the words', 3, NULL, 0, 'https://drive.google.com/uc?id=1Tbv9ONhjaEayGvl7aqbKfcWqlGO4JQpb&export=download', NULL, NULL, NULL, 8, 10001, NULL),
(200017, 'Learn the words', 0, '', NULL, 'Learn the words', 3, NULL, 0, 'https://drive.google.com/uc?id=1k3RiXUVy7IMMDRDFiYAHP1Xum1Z4dw62&export=download', NULL, NULL, NULL, 8, 10002, NULL),
(200018, 'Learn the words', 0, '', NULL, 'Learn the words', 3, NULL, 0, 'https://drive.google.com/uc?id=1d3vTjqAj0m1mNW9wJFVvTpxXw6R_rOzE&export=download', NULL, NULL, NULL, 8, 10003, NULL),
(200019, 'Describe what you see', 0, 'Answer the following questions:\n“Strive to be a person of action, good deeds and a willing vessel of hope.”\nHow far do you agree with the quote?\nThink of top 5 keys to success and share your ideas with your teacher.\nTalk about someone you know who has become successful, saying what qualities and circumstances have helped him/her.\n', NULL, 'Describe what you see', 1, NULL, 0, 'https://drive.google.com/uc?id=1s0f0Kcw_xVfqq8uFRE9laLBl93AioFIw&export=download', NULL, NULL, NULL, 1, 10000, NULL),
(200020, 'Describe what you see', 0, '\nLook at the pictures showing people expressing joy on different occasions.\nWhat has happened to make them feel happy?\nDiscuss success. Think about:\nits importance in our lives\nhappiness it brings\nproblems it causes\n', NULL, 'Describe what you see', 1, NULL, 0, 'https://drive.google.com/uc?id=1_j04SIL-SeapG7T-GMYLYnJedV5oW8me&export=download', NULL, NULL, NULL, 1, 10001, NULL),
(200021, 'Describe what you see', 0, 'Look at the following factors which may be important for career success.\nChoose a maximum of two in each group which you consider absolutely necessary.\nCompare your choices with your teacher, saying in what situations you feel that the factors you selected can play a vital part.\n\nBackground\n\nPeople skills\n\nOther personal qualities\nsocial background\nfinancial backing\ninfluential acquaintances\nwell-rounded education\nrelevant experience\npositive image\nsensitivity to others’ feelings\ncompetitive spirit\nability to delegate\nwillingness to compromise\ndetermination\nresilience\npowers of intuition\ncharisma\nforesight\n', NULL, 'Describe what you see', 1, NULL, 0, 'https://drive.google.com/uc?id=1vRZ4k9T5TYWeYfW5gVEOaqv7Gsi5pYbS&export=download', NULL, NULL, NULL, 1, 10002, NULL),
(200022, 'Describe what you see', 0, 'Finish the sentences:\nI have a good/poor memory for...\nI keep forgetting...\nI have only vivid/vague memories of...\n', NULL, 'Describe what you see', 1, NULL, 0, 'https://drive.google.com/uc?id=1iAHUZbnbWqNU7B71HZ5gLZ5n8EJvF6zE&export=download', NULL, NULL, NULL, 1, 10003, NULL),
(200023, 'Time to talk', 0, '1. Where do you work?\n2. Is your job interesting? Why?\n3. Can you spell your surname? And your email address?\n4. What country are you from?\n5. Do you have a pet? If yes, what is its name?\n6. What film star phone number would you like to have? Why?\n7. Do you like traveling?\n8. What makes you happy?\n9. How often do you go to the cinema? What was the last film you saw?\n10. Do you have a hobby?\n', NULL, 'Time to talk', 6, NULL, 0, 'https://drive.google.com/uc?id=1GFIlTEiayFeNTnyaXtCYfF08bzt_KxAv&export=download', NULL, NULL, NULL, 3, 10004, NULL),
(200024, 'Time to talk', 0, '1. What is your job?\n2. Is your friend fine?\n3. How do you spell your name?\n4. How do you spell your surname?\n5. How old is your friend?\n6. What is your friend’s phone number?\n7. What is your e-mail address?\n8. How do you spell your e-mail address?\n9. Is your friend a teacher?\n10. Is your job interesting?\n', NULL, 'Time to talk', 6, NULL, 0, NULL, NULL, NULL, NULL, 3, 10005, NULL),
(200025, 'Time to talk', 0, '1. How are you?\n2. What country are you from?\n3. Is your teacher fine?\n4. Is your city big?\n5. What is your email address?\n6. Is your hobby interesting?\n7. Is your friend old?\n8. How old are you?\n9. Are you a student?\n10. How old is your teacher?\n', NULL, 'Time to talk', 6, NULL, 0, 'https://drive.google.com/uc?id=1QgQDQcOLFIAJZlvFf4se9Mpybetgg6UD&export=download', NULL, NULL, NULL, 3, 10006, NULL),
(200026, 'Time to talk', 0, '1. Is your teacher from England?\n2. Are you at home on the holidays?\n3. Are you at work on Monday?\n4. When are you at home?\n5. Do you read a book? Is it interesting?\n6. Is China a big country?\n7. Are you Polish?\n8. Are German people nice?\n9. Is your friend from Canada?\n10. Is it a good idea to work on Sunday?\n\n', NULL, 'Time to talk', 6, NULL, 0, 'https://drive.google.com/uc?id=1RHEJ8kY6XoW5ukbaztkYol-zJI4Y28YQ&export=download', NULL, NULL, NULL, 3, 10007, NULL),
(200027, 'Let’s have fun', 0, '\nIt’s high time to have some rest! Let’s go on a perfect holiday. What will it be like?\nWhere will we go?\nWill it be a hotel or a hostel?\nWho will you go with? Why?\nHow many days would you like to spend there?\nWhat places would you like to visit?\nWhat will you do at night?\nWill you take a lot of pictures or just enjoy the happy moments?\n', NULL, 'Let’s have fun', 10, NULL, 0, 'https://drive.google.com/uc?id=11lW8eCMj0xk5f5V6Mxa0pTSF8cgAYwhk&export=download', NULL, NULL, NULL, 5, 10004, NULL),
(200028, 'Let’s have fun', 0, 'Look at the international English words. Which ones do you know?\n\nHospital, hotel, police, airport, station, pizza, coffee, football, taxi, alcohol, chocolate, passport, park, cocktail.\n', NULL, 'Let’s have fun', 10, NULL, 0, 'https://drive.google.com/uc?id=12tlvVKzgBUZo1z98jbZZNYHKQ_xH7DKm&export=download', NULL, NULL, NULL, 5, 10005, NULL),
(200029, 'Let’s have fun', 0, 'Talk about a perfect friend and use the following words:\nshe/he\nher / his\nname\nhobby\njob\ngood\nnice\nit\nsurname\nfine\n', NULL, 'Let’s have fun', 10, NULL, 0, 'https://drive.google.com/uc?id=1HEZpItpIiRpvpb674EoLjk0BwkhNAZ4_&export=download', NULL, NULL, NULL, 5, 10006, NULL),
(200030, 'Let’s have fun', 0, 'Name 5 words that describe each nation.\n\nNow name the countries where these people live. Which country do you like most? Why?\nCanadians\nChinese\nGreeks\nEnglish\nFrench\nAustralians\nGermans\nItalians\n', NULL, 'Let’s have fun', 10, NULL, 0, 'https://drive.google.com/uc?id=1pZjRczZEHa4HAbtW4Fn3DaeLv1T8cLhf&export=download', NULL, NULL, NULL, 5, 10007, NULL),
(200031, 'Study the information', 0, '\n', NULL, 'Study the information', 4, NULL, 0, 'https://drive.google.com/uc?id=1TXdkDjOvReT3nY7BQogbKQA9H3Av4w1a&export=download', NULL, NULL, NULL, 2, 10004, NULL),
(200032, 'Study the information', 0, '', NULL, 'Study the information', 4, NULL, 0, 'https://drive.google.com/uc?id=1u01kmxoX_CAhkox1u2ReGh4dr4IyKV-y&export=download', NULL, NULL, NULL, 2, 10005, NULL),
(200033, 'Study the information', 0, '\n', NULL, 'Study the information', 4, NULL, 0, 'https://drive.google.com/uc?id=1YDN4uMY0YR2VKY7JHEdL1liw2npZtBvh&export=download', NULL, NULL, NULL, 2, 10006, NULL),
(200034, 'Study the information', 0, '\n', NULL, 'Study the information', 4, NULL, 0, 'https://drive.google.com/uc?id=1s4Ir85WqLz9nzlAgEjmsn3lyss3jMMrQ&export=download', NULL, NULL, NULL, 2, 10007, NULL),
(200035, 'Put in order', 0, '', NULL, 'Put in order', 8, NULL, 0, NULL, NULL, NULL, NULL, 4, 10004, NULL),
(200036, 'Put in order', 0, '', NULL, 'Put in order', 8, NULL, 0, NULL, NULL, NULL, NULL, 4, 10005, NULL),
(200037, 'Put words to gap', 0, '', NULL, 'Put words to gap', 8, NULL, 0, NULL, NULL, NULL, NULL, 4, 10006, NULL),
(200038, 'Fill in the gap', 0, '', NULL, 'Fill in the gap', 8, NULL, 0, 'https://drive.google.com/uc?id=1-iji1QShJWCA9tjl66uu7xEOIk0R4j5h&export=download', NULL, NULL, NULL, 4, 10007, NULL),
(200039, 'Let’s imagine', 0, 'Imagine that you’re at the party trying to make new friends\nE.g.\nA: Hey! What’s up?\nB: Oh, hey. What’s your name?\n\nA: My name is Daenerys, the Mother of Dragons. And yours?\nB: And I’m Jon, the King of the North. Where are you from, Daenerys?\n\nA: I’m from Dragonstone. And you?\nB: And I’m from Winterfell.\nHey! What’s ...?\nMy name is ...?\nI am from ...?\nWhere ...?\nWhat do you ...?\nWhat is your ...?\nAre you here with …?\nWho is ...\nMy phone number is …\nI’ll call you on …\n', NULL, 'Let’s imagine', 9, NULL, 0, 'https://drive.google.com/uc?id=17aGf6yX2DnsTmi95hpdPs53ZRGeDloZX&export=download', NULL, NULL, NULL, 10, 10004, NULL),
(200040, 'Let’s imagine', 0, 'Imagine that it’s your first day at the new place of work\nE.g.\nA: Hi, how are you?\nB: I’m fine, and you?\n\nA: I’m fine, thanks. I’m a new sales manager here.\nB: Cool! What is your name?\n\nA: My name is Leo. And yours?\nB: I’m Jack. And what’s your surname?\nHow are you?\nI’m a new ...\nMy name is ...\nMy surname ...\nIt is ...\nI am ... years old\nMy phone number is ...\nMy email address is ...\nGood luck!\n', NULL, 'Let’s imagine', 9, NULL, 0, 'https://drive.google.com/uc?id=1Ym-IreaulhUv618Cp2DUuPW-OXStAhVM&export=download', NULL, NULL, NULL, 10, 10005, NULL),
(200041, 'Let’s imagine', 0, 'You’re talking to your new colleague\nE.g.\nA: How are you today?\nB: I’m OK, thanks. How are you?\n\nA: I’m great. What is your name?\nB: My name is Yennefer. And what is your name?\n\nA: My name is Geralt. Where are you from, Yennefer?\nB: I am from Vengerberg. And you, Geralt?\n\nHow are you today?\nWhat is your ...?\nMy name is...\nI am from...\nI am... too.\nMy hobby is...\nMy job is…\nWhat do you …?\nI like ...\n\nThank you!\n', NULL, 'Let’s imagine', 9, NULL, 0, 'https://drive.google.com/uc?id=13sK3xAqvqDiF9SP_z5eSBsPTmO4U7oAH&export=download', NULL, NULL, NULL, 10, 10006, NULL),
(200042, 'Let’s imagine', 0, 'How to make friends in a journey\nE.g.\nA: Hello! Who are you?\nB: Hi! I’m Max. And you?\n\nA: I’m Kim. Nice to meet you, Max.\nB: Nice to meet you too, Kim. How are you?\n\nA: I’m fine, thanks. And you?\nB: I’m great, thanks. Which country are you from?\n\nHello!\nNice to...\nI’m fine...\nI am from...\nCool! I want to...\nWhere…?\nI go to...\nYes, it is...\nIs your country…?\nMy country is...\n', NULL, 'Let’s imagine', 9, NULL, 0, 'https://drive.google.com/uc?id=1O5P50dzYuXNMqIQq_Y6Eh9VofAVBP9-Q&export=download', NULL, NULL, NULL, 10, 10007, NULL),
(200043, 'Learn the words', 0, '', NULL, 'Learn the words', 3, NULL, 0, 'https://drive.google.com/uc?id=1E26-xlhCRh_ESpUoNJvI0PMThKU3Swyi&export=download', NULL, NULL, NULL, 8, 10004, NULL),
(200044, 'Learn the words', 0, '', NULL, 'Learn the words', 3, NULL, 0, 'https://drive.google.com/uc?id=1t48qXBip7T1NkIMxZATLNvWJAYTeOUuL&export=download', NULL, NULL, NULL, 8, 10005, NULL),
(200045, 'Learn the words', 0, '', NULL, 'Learn the words', 3, NULL, 0, 'https://drive.google.com/uc?id=1g5hPS24I0oW-OtrD93G1qDwgYfD0QnDH&export=download', NULL, NULL, NULL, 8, 10006, NULL),
(200046, 'Learn the words', 0, '', NULL, 'Learn the words', 3, NULL, 0, 'https://drive.google.com/uc?id=1wAcjbNCK9B8jbFozDE63SbOUKwWSdU45&export=download', NULL, NULL, NULL, 8, 10007, NULL),
(200047, 'Learn the words', 0, '', NULL, 'Learn the words', 3, NULL, 0, 'https://drive.google.com/uc?id=1IzwKsM26s70S4DWYfIz9rITcFj7Olh33&export=download', NULL, NULL, NULL, 8, 10008, NULL),
(200048, 'Describe what you see', 0, 'Finish the statements\nSome of the sentences can be false. Let your teacher guess which ones!\nMy favorite food is …\nMy favorite music is …\nMy favorite day of the week is …\nMy favorite country is …\nMy favorite actor is ...\n', NULL, 'Describe what you see', 1, NULL, 0, 'https://drive.google.com/uc?id=1pzIkTf9JlQs4V8SJhR68ieEBNydzjklk&export=download', NULL, NULL, NULL, 1, 10004, NULL),
(200049, 'Describe what you see', 0, 'What ways to say hello do you know?\nWhat\'s up?\nHowdy!\nGreetings!\nLook who it is!\nGood day!\n', NULL, 'Describe what you see', 1, NULL, 0, 'https://drive.google.com/uc?id=1RgInjcCzb5GpIC9Ek3jZrR6RHNPN7khW&export=download', NULL, NULL, NULL, 1, 10005, NULL),
(200050, 'Describe what you see', 0, 'What do you say to “Nice to meet you”?\nThink of more ideas\n\nHow do you do?\nNot really...\nNice to meet you too!\nWish I could say the same.\n', NULL, 'Describe what you see', 1, NULL, 0, 'https://drive.google.com/uc?id=19thWEhPRqlWC4J5APwa3G4yFimlbZ2zn&export=download', NULL, NULL, NULL, 1, 10006, NULL),
(200051, 'Describe what you see', 0, 'Finish the sentences\nI am from…\nI live in...\nMy favorite country is…\nMy favorite city is...\nMy friend is from…\n', NULL, 'Describe what you see', 1, NULL, 0, 'https://drive.google.com/uc?id=1kN92mhlM4XJfbn5I91q0UHngPuaK_Wsx&export=download', NULL, NULL, NULL, 1, 10007, NULL),
(200052, 'Time to talk', 0, '1. How do you relax after work?\n2. Do you have any hobbies? Is there anything special about them?\n3. How often do you have a vacation?\n4. Who do you normally go with?\n5. Where do you normally go when on vacation?\n6. What places would you like to visit and why?\n7. What else would you do in those places?\n8. Where would you never go? Why?\n9. Would you like to try extreme sports?\n10. Do you have any vacation plans for this year?\n', NULL, 'Time to talk', 6, NULL, 0, 'https://drive.google.com/uc?id=1S9hZiKRflJJaKhh_0psBRTMuu4l3FnT8&export=download', NULL, NULL, NULL, 3, 10009, NULL),
(200053, 'Time to talk', 0, '1. How do you make new friends?\n2. How many best friends does a person usually have?\n3. What traits of character are important for the best friend? And why?\n4. Does friendship always mean happy moments?\n5. Why do people need friends?\n6. How do you maintain a good friendship?\n7. Does a perfect friend have to be the same as you or not? And why?\n8. How are your friendships different now than they were when you were a child?\n9. Are your childhood mates those you have the strongest bonds with?\n10. Do you think it is a good idea to borrow money from a friend? Why or why not?\n', NULL, 'Time to talk', 6, NULL, 0, 'https://drive.google.com/uc?id=1UIwMVq5mu7-2SnZLJg6KrQZmqSY2C8cB&export=download', NULL, NULL, NULL, 3, 10010, NULL),
(200054, 'Time to talk', 0, '1. There is an old English saying \"A friend in need is a friend indeed.\" What do you think this means?\n2. A true friend is someone who can call you at 3 am to ask you to help start their car. Do you agree?\n3. Do you think that most people are naturally able to get along with others or is it a skill you need to learn?\n4. Is there a maximum number of friends one person can have? How many friends do you think you can have before it becomes too difficult to keep in touch with them or keep track of their lives?\n5. Are you friends with people because of, or in spite of, what they do?\n6. How much do you socialize with people from work? What do you usually do?\n7. Do you have any friends from a different culture or country than you? Do your differences make it harder to be friends?\n8. In some cultures, friendship networks are important for doing business and getting jobs. How about in your culture?\n9. How does social media affect our friendships?\n10. Did the concept of \"friendship\" change with time?\n', NULL, 'Time to talk', 6, NULL, 0, 'https://drive.google.com/uc?id=1PP7C-bWtaxJelqiD4wGoKeUivamlIXlz&export=download', NULL, NULL, NULL, 3, 10011, NULL),
(200055, 'Time to talk', 0, '1. What\'s your favorite home gadget?\n2. Do you have an addiction to modern devices?\n3. How many hours do you spend in front of your computer?\n4. What are the advantages & disadvantages of modern TV-sets?\n5. What’s the main purpose of using new appliances nowadays?\n6. What are the most popular gadgets among teenagers nowadays? And why?\n7. What do you think about people who don\'t watch television?\n8. Do you have PlayStation or Xbox? Which console is better?\n9. Do you have any favorite applications on your smartphone?\n10. What do you think is one device the world needs the most these days?\n', NULL, 'Time to talk', 6, NULL, 0, 'https://drive.google.com/uc?id=1aHd6XulAWuyLm2eQkNNdVy5rVNmi5R7d&export=download', NULL, NULL, NULL, 3, 10012, NULL),
(200056, 'Let’s have fun', 0, 'Think about a perfect house you would love to live in.\nWhat country and region would it be?\nWould it be a lively public place or a quiet reserved one?\nWho would you like to have as your neighbor?\nWould you like to take your old furniture there or some new?\n', NULL, 'Let’s have fun', 10, NULL, 0, 'https://drive.google.com/uc?id=1RW5ka4Kik_tM_B3pSWtmCR3rP2uya7DH&export=download', NULL, NULL, NULL, 5, 10009, NULL),
(200057, 'Let’s have fun', 0, 'Talking about a perfect day...\nUse these words:\nWho would you like to spend your perfect day with?\nWhat would you do?\nWhere would you like to be and when (choose any historical period)?\nincredible\nhighly\nfully\nkeen on\nadore\n', NULL, 'Let’s have fun', 10, NULL, 0, 'https://drive.google.com/uc?id=1nwIS4SBmf1qDdNahGPHpmjCSsNKMXIbz&export=download', NULL, NULL, NULL, 5, 10013, NULL),
(200058, 'Let’s have fun', 0, 'Here is an old English proverb “Love me, love my dog”.\nCan you guess its meaning? Choose the correct option:\n\na) to prefer dogs to humans as a company\nb) to accept everything about someone, to love despite faults\nc) display your emotions openly, make your feelings obvious about someone\nd) to live a lonely life, to have no friends except your dog\n\nDo you agree with it? Do you know any love proverbs/idioms in your language?\n\n\n\n\n\n\nAnswer: b\n', NULL, 'Let’s have fun', 10, NULL, 0, 'https://drive.google.com/uc?id=1OkK8ipAj0jV7fURSSV-MDLv0oKD5KUGv&export=download', NULL, NULL, NULL, 5, 10014, NULL),
(200059, 'Let’s have fun', 0, 'Compare\n1. Who is it better to be:\n2. Why do you think so?\n3. What would you do then?\na vegan with superpowers\na true old hippie\na joyful goth\na revolutioner\na king\na Buddhist monk\na hermit\nthe happiest person on Earth\nthe only person on the uninhabited island\nthe last person on Earth\n', NULL, 'Let’s have fun', 10, NULL, 0, 'https://drive.google.com/uc?id=1HTZbchmEBMxrv3p3B0aZ4JEN0UTM4viP&export=download', NULL, NULL, NULL, 5, 10015, NULL),
(200060, 'Study the information', 0, '\n', NULL, 'Study the information', 4, NULL, 0, 'https://drive.google.com/uc?id=1xckBptoAvaG8MI24hG1iHRancN-gcqfi&export=download', NULL, NULL, NULL, 2, 10009, NULL),
(200061, 'Study the information', 0, '\n', NULL, 'Study the information', 4, NULL, 0, 'https://drive.google.com/uc?id=1GZr7iZ8YgN9gXEOzLNi5UmbdOJnCHa7P&export=download', NULL, NULL, NULL, 2, 10013, NULL),
(200062, 'Study the information', 0, '\n', NULL, 'Study the information', 4, NULL, 0, 'https://drive.google.com/uc?id=1WofJW9pzJnKczToBLDkyqzK5bS99W_eH&export=download', NULL, NULL, NULL, 2, 10014, NULL),
(200063, 'Study the information', 0, '\n', NULL, 'Study the information', 4, NULL, 0, 'https://drive.google.com/uc?id=1SbNtlKC5Zo7-d8shPdpw4AeMO7LxsDsV&export=download', NULL, NULL, NULL, 2, 10015, NULL),
(200064, 'Put in order', 0, '', NULL, 'Put in order', 8, NULL, 0, NULL, NULL, NULL, NULL, 4, 10009, NULL),
(200065, 'Put words to gap', 0, '', NULL, 'Put words to gap', 8, NULL, 0, NULL, NULL, NULL, NULL, 4, 10013, NULL),
(200066, 'Put in order', 0, '', NULL, 'Put in order', 8, NULL, 0, NULL, NULL, NULL, NULL, 4, 10014, NULL),
(200067, 'Put words to gap', 0, '', NULL, 'Put words to gap', 8, NULL, 0, NULL, NULL, NULL, NULL, 4, 10015, NULL),
(200068, 'Let’s imagine', 0, 'Telling your friend about a regular day at work\nE.g.\nA: So, what time do you usually get to work?\nB: I get to work at 10.\n\nA: And what do you start your working day with?\nB: I start with drinking a mug of a very strong coffee.\n\nA: Lucky you! And what’s next?\nB: Then I usually have a lunch break. Just kidding.\n\nI get to work at …\nI start with …\nThen I …\nThere are …\nMy boss comes at …\nMy lunch time is …\nI (don’t) make a lot of calls a day because …\nI finish work at …\nThen I …\nAnd that’s …\n', NULL, 'Let’s imagine', 9, NULL, 0, 'https://drive.google.com/uc?id=1-yyX8U0-MnEUHYXgUNMfMCqLAx4N0CQj&export=download', NULL, NULL, NULL, 10, 10009, NULL),
(200069, 'Let’s imagine', 0, 'Imagine that you’re a famous novel writer giving an interview\nE.g.\nA: So, where did the idea of your last novel come from?\nB: It came from my childhood. You know, for some reason, I clearly remember the magic I had in my head when I was a kid.\n\nA: And how did you decide that you should transform your childhood experience into a novel?\nB: It happened when I sold the house of my parents. I was looking through some old stuff in the attic when suddenly all details of the plot clicked into the right place.\n\nA: I see. And what is your greatest source of inspiration?\nB: Let me think...\n\nThe idea of the novel came from ...\nIt happened ...\nLet me think ...\nI was approximately ...\nMaybe the main character was ...\nHis name was ...\nExactly!\nI was really lonely so I wrote the novel because ...\nI guess …\nI will write ...\n', NULL, 'Let’s imagine', 9, NULL, 0, 'https://drive.google.com/uc?id=1FzcXJ_lJn3WZDWkM1YVhPrVIGtZiENlW&export=download', NULL, NULL, NULL, 10, 10010, NULL),
(200070, 'Let’s imagine', 0, 'Tell your bestie about your new friend that you met in the summer camp\nE.g.\nA: Oh, Lord! You won’t believe who I met in the summer camp last month!\nB: You think so? OK, who?\n\nA: The craziest person ever!\nB: You exaggerate, don\'t you?\n\nA: Maybe I do. But my new friend is just so awesome!\nB: So, would you like to tell me more about him or her?\n\nOh, Lord! You won’t …!\nThe … person ever!\nMaybe … But ...\nOk, he’s ...\nMoreover, he/she ...\nAnd the coolest part ...\nYou know, it’s so rare ...\nI think we really ...\nI hope ...\nI can’t wait to …!\n', NULL, 'Let’s imagine', 9, NULL, 0, 'https://drive.google.com/uc?id=1tSTw8e5ynSliT9V8Mq0W-s9WW-pI0u4e&export=download', NULL, NULL, NULL, 10, 10011, NULL),
(200071, 'Let’s imagine', 0, 'You want to buy a new electric kettle\nE.g.\nA: Good afternoon!\nB: Hello! Can I help you?\n\nA: I would like to buy an electric kettle.\nB: OK. What kind of electric kettle do you want?\n\nA: I want something which will fit into the new design of my kitchen. Something high-tech in white and grey colors.\nB: I see. Do you have any brand preferences?\n\nGood afternoon!\nI would like a ...\nI want something which ...\nThe brand I like is ...\nLet me think ...\nThat’s a pity.\nI’m not familiar with ...\nIt looks a bit weird.\nDo you have a ...\nWow! It’s really … I’ll take it!\n', NULL, 'Let’s imagine', 9, NULL, 0, 'https://drive.google.com/uc?id=1N3mB6qvflABlNsQoX78-UACvp4zfoz7o&export=download', NULL, NULL, NULL, 10, 10012, NULL),
(200072, 'Learn the words', 0, '', NULL, 'Learn the words', 3, NULL, 0, 'https://drive.google.com/uc?id=1SMY4J2wJI_6vGmJXbG0kvrwjC4AmpQ-m&export=download', NULL, NULL, NULL, 8, 10009, NULL),
(200073, 'Learn the words', 0, '', NULL, 'Learn the words', 3, NULL, 0, 'https://drive.google.com/uc?id=1tTPvJovAtjhOKecRAu3HrUR80tIvTpW9&export=download', NULL, NULL, NULL, 8, 10010, NULL),
(200074, 'Learn the words', 0, '', NULL, 'Learn the words', 3, NULL, 0, 'https://drive.google.com/uc?id=1dfRMZBNiuVXXMJYjGho4f4P_Jd-T6bEt&export=download', NULL, NULL, NULL, 8, 10011, NULL),
(200075, 'Learn the words', 0, '', NULL, 'Learn the words', 3, NULL, 0, 'https://drive.google.com/uc?id=1miKXW1TIDkg03RUu1Os62zzvpLe61r65&export=download', NULL, NULL, NULL, 8, 10012, NULL),
(200076, 'Describe what you see', 0, 'Way of working\nDo you work remotely or at the office? Have you tried both ways?\nWhich is better – working remotely or at the office? Name 3 reasons for thinking this or that way?\nDo you think you will change your future way of working?\n', NULL, 'Describe what you see', 1, NULL, 0, 'https://drive.google.com/uc?id=1YLYWXZT3Wf4pqCU7U1tZRF3aVO6GVKZ_&export=download', NULL, NULL, NULL, 1, 10009, NULL),
(200077, 'Describe what you see', 0, 'Is this about you?\nI\'m an arrogant person who doesn\'t trust people at all.\nAll my best friends are really committed and polite people.\nI\'m a couch potato who is fond of yummy food and proud of that.\n', NULL, 'Describe what you see', 1, NULL, 0, 'https://drive.google.com/uc?id=18An5oAMgy69NoeBLnrV0JqKWQYU-MFZV&export=download', NULL, NULL, NULL, 1, 10010, NULL),
(200078, 'Describe what you see', 0, 'Read the following phrase and answer questions below:\nIn the cookie of life friends are the chocolate chips.\n\nDo you agree with it?\nDo you like cookies with chocolate chips?\nNow replace the cookies&chips with your favorite food and make your own quote: E.g. In the pizza of life friends are the cheese.\n', NULL, 'Describe what you see', 1, NULL, 0, 'https://drive.google.com/uc?id=1ZkHmqwMmPErGGjxilpaOj-XdZTnsbCuY&export=download', NULL, NULL, NULL, 1, 10011, NULL),
(200079, 'Describe what you see', 0, 'Finish the sentences:\nAn alarm clock rings at ...\nI would like to have a lie-in but ...\nThen I take a shower and ...\nI switch on an electric kettle to ...\nMy favorite breakfast is ...\n', NULL, 'Describe what you see', 1, NULL, 0, 'https://drive.google.com/uc?id=1FMhUJs0KT0zVkTwWEXrISNyAc4spyXQz&export=download', NULL, NULL, NULL, 1, 10012, NULL),
(200080, 'Time to talk', 0, '1. How do you relax after work?\n2. Do you have any hobbies? Is there anything special about them?\n3. How often do you have a vacation?\n4. Who do you normally go with?\n5. Where do you normally go when on vacation?\n6. What places would you like to visit and why?\n7. What else would you do in those places?\n8. Where would you never go? Why?\n9. Would you like to try extreme sports?\n10. Do you have any vacation plans for this year?\n', NULL, 'Time to talk', 6, NULL, 0, 'https://drive.google.com/uc?id=1ghHR6e-jJ7oH55rf_0WGWpIliBT0-Z10&export=download', NULL, NULL, NULL, 3, 10016, NULL),
(200081, 'Time to talk', 0, '1. How do you make new friends?\n2. How many best friends does a person usually have?\n3. What traits of character are important for the best friend? And why?\n4. Does friendship always mean happy moments?\n5. Why do people need friends?\n6. How do you maintain a good friendship?\n7. Does a perfect friend have to be the same as you or not? And why?\n8. How are your friendships different now than they were when you were a child?\n9. Are your childhood mates those you have the strongest bonds with?\n10. Do you think it is a good idea to borrow money from a friend? Why or why not?\n', NULL, 'Time to talk', 6, NULL, 0, 'https://drive.google.com/uc?id=1KANvnc3035n8UIAKpliFgR5MH3Ng62ed&export=download', NULL, NULL, NULL, 3, 10017, NULL),
(200082, 'Time to talk', 0, '1. There is an old English saying \"A friend in need is a friend indeed.\" What do you think this means?\n2. A true friend is someone who can call you at 3 am to ask you to help start their car. Do you agree?\n3. Do you think that most people are naturally able to get along with others or is it a skill you need to learn?\n4. Is there a maximum number of friends one person can have? How many friends do you think you can have before it becomes too difficult to keep in touch with them or keep track of their lives?\n5. Are you friends with people because of, or in spite of, what they do?\n6. How much do you socialize with people from work? What do you usually do?\n7. Do you have any friends from a different culture or country than you? Do your differences make it harder to be friends?\n8. In some cultures, friendship networks are important for doing business and getting jobs. How about in your culture?\n9. How does social media affect our friendships?\n10. Did the concept of \"friendship\" change with time?\n', NULL, 'Time to talk', 6, NULL, 0, 'https://drive.google.com/uc?id=13osXV-WZcniilHxSccMwUdQcuHfnvf8L&export=download', NULL, NULL, NULL, 3, 10018, NULL),
(200083, 'Time to talk', 0, '1. What\'s your favorite home gadget?\n2. Do you have an addiction to modern devices?\n3. How many hours do you spend in front of your computer?\n4. What are the advantages & disadvantages of modern TV-sets?\n5. What’s the main purpose of using new appliances nowadays?\n6. What are the most popular gadgets among teenagers nowadays? And why?\n7. What do you think about people who don\'t watch television?\n8. Do you have PlayStation or Xbox? Which console is better?\n9. Do you have any favorite applications on your smartphone?\n10. What do you think is one device the world needs the most these days?\n', NULL, 'Time to talk', 6, NULL, 0, 'https://drive.google.com/uc?id=1U_3h2Z3ceh1RyyfFLSFgfmtYcL52y8Ml&export=download', NULL, NULL, NULL, 3, 10019, NULL),
(200084, 'Let’s have fun', 0, '\nCompare\nWhich is the best place to go – mountains or the seaside?\nWhere do you go more often and why?\nWhere are the nearest mountains?\nWhere is the nearest seaside?\nWhere do most people in your country go and why?\nWhat other places are good for rest?\nWhere else would you go?\n', NULL, 'Let’s have fun', 10, NULL, 0, 'https://drive.google.com/uc?id=1HBPmKPhz5GQkCy-JHn3kqEX-AqfK4s35&export=download', NULL, NULL, NULL, 5, 10016, NULL),
(200085, 'Let’s have fun', 0, 'Match the idioms about friendship to their meanings.\nDo you know any idioms about friends and friendship in your language?\n\n1) birds of a feather flock together\n2) through thick and thin\n3) to bury the hatchet\n4) to get on like a house on fire\n\na) to end a conflict\nb) people who have the same outlook/tastes/interests are in each other\'s company\nc) to get on well with someone\nd) through all circumstances no matter how difficult\n\n\n\nAnswer: 1b), 2d), 3a), 4c)\n', NULL, 'Let’s have fun', 10, NULL, 0, 'https://drive.google.com/uc?id=1jOLlGumOvTiAJnVF_PDjiHPu2kbNGmij&export=download', NULL, NULL, NULL, 5, 10017, NULL),
(200086, 'Let’s have fun', 0, 'Explain the following in your own words:\nminion\nrude\nworkaholic\ncouch potato\nHulk\nsaxophone\nparty animal\nhard-hearted\npizza\n', NULL, 'Let’s have fun', 10, NULL, 0, 'https://drive.google.com/uc?id=1hk_NDReFIE1FWjqQ6wiSY1Nc4oxlxost&export=download', NULL, NULL, NULL, 5, 10018, NULL),
(200087, 'Let’s have fun', 0, 'Good or bad?\nHere’s the list of some modern gadgets and appliances. What pros and cons do they have? Try to think of at least 3 advantages and 3 disadvantages for each item.\nWhich of these gadgets do you use most often? Why?\nAnd which of them do you rarely use (or maybe even never)? Why?\nChoose one gadget from the list which you consider the most crucial for you. Explain your choice.\n\nsmartphone\nlaptop\nfridge\ntablet\nair conditioner\nelectric kettle\nmicrowave\nhairdryer\nfan\ne-cigarettes\n', NULL, 'Let’s have fun', 10, NULL, 0, NULL, NULL, NULL, NULL, 5, 10019, NULL),
(200088, 'Study the information', 0, '\n', NULL, 'Study the information', 4, NULL, 0, 'https://drive.google.com/uc?id=1Fe97Q89oyhgtH3xSi6_cbLrXHxQ7o7Iq&export=download', NULL, NULL, NULL, 2, 10016, NULL),
(200089, 'Study the information', 0, '\n', NULL, 'Study the information', 4, NULL, 0, 'https://drive.google.com/uc?id=1xlbnIBDkzw2gaz0LPSI3M2pZT3dT1HVb&export=download', NULL, NULL, NULL, 2, 10019, NULL),
(200090, 'Put words to gap', 0, '', NULL, 'Put words to gap', 8, NULL, 0, 'https://drive.google.com/uc?id=1hNZ7fQpq8bWQe0PVY9P47woh1l01FTMs&export=download', NULL, NULL, NULL, 4, 10019, NULL),
(200091, 'Let’s imagine', 0, 'Telling your friend about a regular day at work\nE.g.\nA: So, what time do you usually get to work?\nB: I get to work at 10.\n\nA: And what do you start your working day with?\nB: I start with drinking a mug of a very strong coffee.\n\nA: Lucky you! And what’s next?\nB: Then I usually have a lunch break. Just kidding.\n\nI get to work at …\nI start with …\nThen I …\nThere are …\nMy boss comes at …\nMy lunch time is …\nI (don’t) make a lot of calls a day because …\nI finish work at …\nThen I …\nAnd that’s …\n', NULL, 'Let’s imagine', 9, NULL, 0, 'https://drive.google.com/uc?id=1CUb2RvivkJipGn8v1_GaMY1L2dbJMjfM&export=download', NULL, NULL, NULL, 10, 10016, NULL),
(200092, 'Let’s imagine', 0, 'Imagine that you’re a famous novel writer giving an interview\nE.g.\nA: So, where did the idea of your last novel come from?\nB: It came from my childhood. You know, for some reason, I clearly remember the magic I had in my head when I was a kid.\n\nA: And how did you decide that you should transform your childhood experience into a novel?\nB: It happened when I sold the house of my parents. I was looking through some old stuff in the attic when suddenly all details of the plot clicked into the right place.\n\nA: I see. And what is your greatest source of inspiration?\nB: Let me think...\n\nThe idea of the novel came from ...\nIt happened ...\nLet me think ...\nI was approximately ...\nMaybe the main character was ...\nHis name was ...\nExactly!\nI was really lonely so I wrote the novel because ...\nI guess …\nI will write ...\n', NULL, 'Let’s imagine', 9, NULL, 0, 'https://drive.google.com/uc?id=13qZsEEEFL-ddCFGjJYqPMNESMlW_X3IB&export=download', NULL, NULL, NULL, 10, 10017, NULL),
(200093, 'Let’s imagine', 0, 'Tell your bestie about your new friend that you met in the summer camp\nE.g.\nA: Oh, Lord! You won’t believe who I met in the summer camp last month!\nB: You think so? OK, who?\n\nA: The craziest person ever!\nB: You exaggerate, don\'t you?\n\nA: Maybe I do. But my new friend is just so awesome!\nB: So, would you like to tell me more about him or her?\n\nOh, Lord! You won’t …!\nThe … person ever!\nMaybe … But ...\nOk, he’s ...\nMoreover, he/she ...\nAnd the coolest part ...\nYou know, it’s so rare ...\nI think we really ...\nI hope ...\nI can’t wait to …!\n', NULL, 'Let’s imagine', 9, NULL, 0, 'https://drive.google.com/uc?id=17rHuau6EkMZgL8i3_mA-TYuPGLpYAVfu&export=download', NULL, NULL, NULL, 10, 10018, NULL),
(200094, 'Let’s imagine', 0, 'You want to buy a new electric kettle\nE.g.\nA: Good afternoon!\nB: Hello! Can I help you?\n\nA: I would like to buy an electric kettle.\nB: OK. What kind of electric kettle do you want?\n\nA: I want something which will fit into the new design of my kitchen. Something high-tech in white and grey colors.\nB: I see. Do you have any brand preferences?\n\nGood afternoon!\nI would like a ...\nI want something which ...\nThe brand I like is ...\nLet me think ...\nThat’s a pity.\nI’m not familiar with ...\nIt looks a bit weird.\nDo you have a ...\nWow! It’s really … I’ll take it!\n', NULL, 'Let’s imagine', 9, NULL, 0, 'https://drive.google.com/uc?id=1MeMACiPYJ6nuEWFhQxr6ABTZrs9ewZmW&export=download', NULL, NULL, NULL, 10, 10019, NULL),
(200095, 'Learn the words', 0, '', NULL, 'Learn the words', 3, NULL, 0, 'https://drive.google.com/uc?id=1k8OKB4b0ZOhjc5tuRWMN5_MJ8pyULtVu&export=download', NULL, NULL, NULL, 8, 10016, NULL),
(200096, 'Learn the words', 0, '', NULL, 'Learn the words', 3, NULL, 0, 'https://drive.google.com/uc?id=1akhoqNrbrW1Sd5aL92e4hZuDclmqsYLR&export=download', NULL, NULL, NULL, 8, 10017, NULL),
(200097, 'Learn the words', 0, '', NULL, 'Learn the words', 3, NULL, 0, 'https://drive.google.com/uc?id=1rUZ2niNVXzCDHQyDeSrvoKbPb2jF4b5N&export=download', NULL, NULL, NULL, 8, 10018, NULL),
(200098, 'Learn the words', 0, '', NULL, 'Learn the words', 3, NULL, 0, 'https://drive.google.com/uc?id=1RYSiei118Tn-mAYvptPT6RKU-Er0QZQJ&export=download', NULL, NULL, NULL, 8, 10019, NULL),
(200099, 'Describe what you see', 0, 'Way of working\nDo you work remotely or at the office? Have you tried both ways?\nWhich is better – working remotely or at the office? Name 3 reasons for thinking this or that way?\nDo you think you will change your future way of working?\n', NULL, 'Describe what you see', 1, NULL, 0, 'https://drive.google.com/uc?id=1KHu8ApEgcBWbEVlfvjT3KIX9oEFxVPoW&export=download', NULL, NULL, NULL, 1, 10016, NULL),
(200100, 'Describe what you see', 0, 'Is this about you?\nI\'m an arrogant person who doesn\'t trust people at all.\nAll my best friends are really committed and polite people.\nI\'m a couch potato who is fond of yummy food and proud of that.\n', NULL, 'Describe what you see', 1, NULL, 0, 'https://drive.google.com/uc?id=1XOVR_3vkluBDBIpi7ktSrloD8OzThuBk&export=download', NULL, NULL, NULL, 1, 10017, NULL),
(200101, 'Describe what you see', 0, 'Read the following phrase and answer questions below:\nIn the cookie of life friends are the chocolate chips.\n\nDo you agree with it?\nDo you like cookies with chocolate chips?\nNow replace the cookies&chips with your favorite food and make your own quote: E.g. In the pizza of life friends are the cheese.\n', NULL, 'Describe what you see', 1, NULL, 0, 'https://drive.google.com/uc?id=1fc1VvgBKyQsuk0JXTyW692o4m9exZ-ru&export=download', NULL, NULL, NULL, 1, 10018, NULL),
(200102, 'Describe what you see', 0, 'Finish the sentences:\nAn alarm clock rings at ...\nI would like to have a lie-in but ...\nThen I take a shower and ...\nI switch on an electric kettle to ...\nMy favorite breakfast is ...\n', NULL, 'Describe what you see', 1, NULL, 0, 'https://drive.google.com/uc?id=1yDFssxU99Sc3MCnNmu3I3dCTaRai0C21&export=download', NULL, NULL, NULL, 1, 10019, NULL),
(200103, NULL, 0, NULL, NULL, 'Home Work Review', 2, NULL, 0, NULL, NULL, NULL, NULL, 9, 10000, NULL),
(200104, NULL, 0, NULL, NULL, 'Let\'s practice', 5, NULL, 0, NULL, NULL, NULL, NULL, 6, 10000, NULL),
(200105, NULL, 0, NULL, NULL, 'Let\'s practice', 5, NULL, 0, NULL, NULL, NULL, NULL, 6, 10004, NULL),
(200106, NULL, 0, NULL, NULL, 'Let\'s practice', 5, NULL, 0, NULL, NULL, NULL, NULL, 6, 10010, NULL),
(200107, NULL, 0, NULL, NULL, 'Let\'s practice', 5, NULL, 0, NULL, NULL, NULL, NULL, 6, 10009, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `section_item`
--

CREATE TABLE `section_item` (
  `id` bigint(20) NOT NULL,
  `example` longtext,
  `explanation` longtext,
  `image_url` longtext,
  `response` longtext,
  `translation` longtext,
  `section` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `section_item_synonyms`
--

CREATE TABLE `section_item_synonyms` (
  `section_item_id` bigint(20) NOT NULL,
  `synonyms` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `section_seq`
--

CREATE TABLE `section_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `section_seq`
--

INSERT INTO `section_seq` (`next_val`) VALUES
(200108);

-- --------------------------------------------------------

--
-- Table structure for table `section_sequence`
--

CREATE TABLE `section_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `section_sequence`
--

INSERT INTO `section_sequence` (`next_val`) VALUES
(200109);

-- --------------------------------------------------------

--
-- Table structure for table `session_cours`
--

CREATE TABLE `session_cours` (
  `id` bigint(20) NOT NULL,
  `annee` double NOT NULL,
  `date_debut` datetime DEFAULT NULL,
  `date_fin` datetime DEFAULT NULL,
  `duree` double NOT NULL,
  `mois` double NOT NULL,
  `payer` bit(1) NOT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `totalheure` double NOT NULL,
  `cours` bigint(20) DEFAULT NULL,
  `groupe_etudiant` bigint(20) DEFAULT NULL,
  `prof` bigint(20) DEFAULT NULL,
  `salary` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `skill`
--

CREATE TABLE `skill` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `skill`
--

INSERT INTO `skill` (`id`, `code`, `libelle`) VALUES
(1, NULL, 'Reading'),
(2, NULL, 'Dancing'),
(3, NULL, 'Playing video games'),
(4, NULL, 'Listening music'),
(5, NULL, 'Sewing'),
(6, NULL, 'Cook'),
(7, NULL, 'Playing tennis'),
(8, NULL, 'Horse riding'),
(9, '', 'Doing sport'),
(10, NULL, 'Playing football'),
(11, NULL, 'Biking'),
(12, NULL, 'Travel'),
(13, NULL, 'Hunting'),
(14, NULL, 'Fishing'),
(15, NULL, 'Hiking '),
(16, NULL, 'Sailing'),
(17, NULL, 'Swimming'),
(18, NULL, 'Gardening'),
(19, NULL, 'Running'),
(20, NULL, 'Scuba diving'),
(21, NULL, 'Other');

-- --------------------------------------------------------

--
-- Table structure for table `statut_social`
--

CREATE TABLE `statut_social` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `statut_social`
--

INSERT INTO `statut_social` (`id`, `code`, `libelle`) VALUES
(1, '1', 'Student'),
(2, '2', 'Unemployed'),
(3, '3', 'Employed');

-- --------------------------------------------------------

--
-- Table structure for table `super_categorie_section`
--

CREATE TABLE `super_categorie_section` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` longtext
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `super_categorie_section`
--

INSERT INTO `super_categorie_section` (`id`, `code`, `libelle`) VALUES
(159, 'Obligatory', 'Obligatory'),
(160, 'Additional', 'Additional');

-- --------------------------------------------------------

--
-- Table structure for table `tranche_horaire_prof`
--

CREATE TABLE `tranche_horaire_prof` (
  `id` bigint(20) NOT NULL,
  `day` int(11) NOT NULL,
  `end_hour` varchar(255) DEFAULT NULL,
  `group_index` int(11) NOT NULL,
  `profs_id` bigint(20) DEFAULT NULL,
  `start_hour` varchar(255) DEFAULT NULL,
  `prof` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `type_de_question`
--

CREATE TABLE `type_de_question` (
  `id` bigint(20) NOT NULL,
  `lib` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `type_de_question`
--

INSERT INTO `type_de_question` (`id`, `lib`, `ref`) VALUES
(2, 'Write it up', 't2'),
(11675, 'Describe what you see', 't7'),
(11701, 'Read and add new words', 't8'),
(6, 'Write the correct form', 't6'),
(5, 'True or False', 't5'),
(4, 'Correct the mistake', 't4'),
(3, 'Translate the phrase', 't3'),
(1, 'Choose the correct alternative', 't1'),
(23386, 'Watch and add new words', 't9'),
(7, 'Put words to gap', 't10'),
(8, 'Put in order', 't11'),
(9, 'Word By Word', 't12'),
(10, 'Drag and Drop', 't13');

-- --------------------------------------------------------

--
-- Table structure for table `type_home_work`
--

CREATE TABLE `type_home_work` (
  `id` bigint(20) NOT NULL,
  `lib` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `type_home_work`
--

INSERT INTO `type_home_work` (`id`, `lib`) VALUES
(5, 'Watch it'),
(4, 'Phrasebook'),
(3, 'Write it Up'),
(2, 'Let\'s Practice'),
(1, 'Reading'),
(6, 'Life Story');

-- --------------------------------------------------------

--
-- Table structure for table `type_reclamation_etudiant`
--

CREATE TABLE `type_reclamation_etudiant` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `type_reclamation_prof`
--

CREATE TABLE `type_reclamation_prof` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `type_teacher`
--

CREATE TABLE `type_teacher` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `dtype` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `account_non_expired` bit(1) NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `addresse` varchar(255) DEFAULT NULL,
  `age` int(11) NOT NULL,
  `country` varchar(255) DEFAULT NULL,
  `credentials_non_expired` bit(1) NOT NULL,
  `date_naissance` datetime DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `skype` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL,
  `group_option` varchar(255) DEFAULT NULL,
  `langue` varchar(255) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `teacher_locality` varchar(255) DEFAULT NULL,
  `about` varchar(512) DEFAULT NULL,
  `etat_etudiant_schedule` bigint(20) DEFAULT NULL,
  `fonction` bigint(20) DEFAULT NULL,
  `groupe_etude` bigint(20) DEFAULT NULL,
  `interet_etudiant` bigint(20) DEFAULT NULL,
  `niveau_etude` bigint(20) DEFAULT NULL,
  `pack_student` bigint(20) DEFAULT NULL,
  `parcours` bigint(20) DEFAULT NULL,
  `skill` bigint(20) DEFAULT NULL,
  `statut_social` bigint(20) DEFAULT NULL,
  `categorie_prof` bigint(20) DEFAULT NULL,
  `level_max` bigint(20) DEFAULT NULL,
  `level_min` bigint(20) DEFAULT NULL,
  `type_teacher` bigint(20) DEFAULT NULL,
  `token` varchar(512) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`dtype`, `id`, `account_non_expired`, `account_non_locked`, `addresse`, `age`, `country`, `credentials_non_expired`, `date_naissance`, `enabled`, `image`, `nom`, `numero`, `password`, `prenom`, `role`, `skype`, `username`, `ville`, `group_option`, `langue`, `ref`, `teacher_locality`, `about`, `etat_etudiant_schedule`, `fonction`, `groupe_etude`, `interet_etudiant`, `niveau_etude`, `pack_student`, `parcours`, `skill`, `statut_social`, `categorie_prof`, `level_max`, `level_min`, `type_teacher`, `token`) VALUES
('Admin', 1, b'1', b'1', 'marrakech', 23, NULL, b'1', NULL, b'1', 'http://localhost:8036/user/image/admin@gmail.com/admin@gmail.com.jpg', 'Elmoudene', '0605120314', '$2a$10$wD6Yg9jCpPMuW4trEgOLg.qSA9.YyOq5CbIQr7jpufP54DRfQWMzi', 'Youssef', 'ADMIN', NULL, 'youssefelmoudene09@gmail.com', 'AIT OURIR', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('Prof', 2, b'1', b'1', NULL, 0, NULL, b'1', NULL, b'1', 'http://localhost:8036/user/image/teacher@gmail.com/teacher@gmail.com.jpg', 'teacher', '0605120314', '$2a$10$QxrRnDmrTaOrXxFSJKWXd.mvkursY3xQ36iJ3bTl2iVSesP2WxiAu', 'teacher', 'TEACHER', NULL, 'engflexy.contact@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL),
('Admin', 3, b'1', b'1', 'Marrakech', 22, NULL, b'1', NULL, b'1', 'http://localhost:8036/user/image/zouani@gmail.com/zouani@gmail.com.jpg', 'Zouani', '0760102030', '$2a$10$G6z1mCKHaDMLUZv9RiK1/OwjvXfK4tnNm/DMA.Wsm7KEadi4GL/Ua', 'Younes', 'ADMIN', NULL, 'alc.image.driver@gmail.com', 'Marrakech', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('Etudiant', 4, b'1', b'1', NULL, 0, NULL, b'1', NULL, b'1', 'http://localhost:8036/user/image/profile/elmoudene.ysf@gmail.com', 'YOUSSEF EL MOUDENE', '0605120314', '$2a$10$Nnt5XUykeFvslVAyuE16T.WMMDt8h7xi2vESoyuHdofoJFcjqs0.S', NULL, 'STUDENT', NULL, 'elmoudene.ysf@gmail.com', NULL, NULL, 'ar', NULL, ' ', NULL, NULL, NULL, 25906, NULL, NULL, NULL, 200, 9, NULL, NULL, NULL, NULL, NULL, NULL),
('Prof', 22, b'1', b'1', NULL, 0, NULL, b'1', NULL, b'1', 'http://localhost:8036/user/image/profile/kamal@gmail.com', 'Kamal zouani', '0605120314', '$2a$10$P8pqvn3oPKwVXCw6Mbakx.rKlzBUG4Mxv1dGS8RKSnJ9M0bthSKEO', NULL, 'TEACHER', NULL, 'kamal@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_authorities`
--

CREATE TABLE `user_authorities` (
  `user_id` bigint(20) NOT NULL,
  `authorities` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_authorities`
--

INSERT INTO `user_authorities` (`user_id`, `authorities`) VALUES
(4, 3),
(1, 1),
(3, 1),
(2, 2),
(22, 2);

-- --------------------------------------------------------

--
-- Table structure for table `vocabulary`
--

CREATE TABLE `vocabulary` (
  `id` bigint(20) NOT NULL,
  `exemple` longtext,
  `explication` longtext,
  `image` longtext,
  `libelle` longtext,
  `numero` bigint(20) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL,
  `word` varchar(255) DEFAULT NULL,
  `section` bigint(20) DEFAULT NULL,
  `vocabulary_quiz` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `vocabulary_quiz`
--

CREATE TABLE `vocabulary_quiz` (
  `id` bigint(20) NOT NULL,
  `date_debut` datetime DEFAULT NULL,
  `date_fin` datetime DEFAULT NULL,
  `libelle` longtext,
  `ref` varchar(255) DEFAULT NULL,
  `section` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `voc_seq`
--

CREATE TABLE `voc_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `voc_seq`
--

INSERT INTO `voc_seq` (`next_val`) VALUES
(200000);

-- --------------------------------------------------------

--
-- Table structure for table `workload_bonus`
--

CREATE TABLE `workload_bonus` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `nombre_session` int(11) NOT NULL,
  `prix` decimal(19,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `workload_bonus_prof`
--

CREATE TABLE `workload_bonus_prof` (
  `id` bigint(20) NOT NULL,
  `annee` int(11) NOT NULL,
  `mois` int(11) NOT NULL,
  `prof` bigint(20) DEFAULT NULL,
  `salary` bigint(20) DEFAULT NULL,
  `workload_bonus` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `calendrier_prof`
--
ALTER TABLE `calendrier_prof`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK86sy40mo24p9v4rnsm1r5rn1h` (`etudiant`),
  ADD KEY `FK848i9iq657x10rivjoiymux6k` (`prof`);

--
-- Indexes for table `categorie_prof`
--
ALTER TABLE `categorie_prof`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `categorie_section`
--
ALTER TABLE `categorie_section`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK94utvxafjedauxs1ma946sftc` (`super_categorie_section`);

--
-- Indexes for table `centre`
--
ALTER TABLE `centre`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `class_average_bonus`
--
ALTER TABLE `class_average_bonus`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `class_average_bonus_prof`
--
ALTER TABLE `class_average_bonus_prof`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKd8bpaum15ggyqen23877lus5q` (`class_average_bonus`),
  ADD KEY `FK233kbv6m28tue4y27kk4335ts` (`prof`),
  ADD KEY `FKtj8o44pxl2ik6jpxur0ge9ut3` (`salary`);

--
-- Indexes for table `class_room`
--
ALTER TABLE `class_room`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKq1347h44tb493da7p7fpcnsm` (`responsable`);

--
-- Indexes for table `cours`
--
ALTER TABLE `cours`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK43xvdnkmtxk4pthgcvr1lj2kh` (`parcours`);

--
-- Indexes for table `dictionary`
--
ALTER TABLE `dictionary`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKilr9upxata9qit7s91ko8vr3k` (`etudiant`);

--
-- Indexes for table `etat_etudiant`
--
ALTER TABLE `etat_etudiant`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `etat_etudiant_schedule`
--
ALTER TABLE `etat_etudiant_schedule`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `etat_inscription`
--
ALTER TABLE `etat_inscription`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `etudiant_class_room`
--
ALTER TABLE `etudiant_class_room`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK60t3lkk2fy1oepcvn9qav31w6` (`class_room`),
  ADD KEY `FKq5dmvnfwwip729t76vwyrucyc` (`etudiant`);

--
-- Indexes for table `etudiant_cours`
--
ALTER TABLE `etudiant_cours`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3mdxvxka7xwv4kbq3v6u9n4fs` (`cours`),
  ADD KEY `FKnyl3ct5tiavch6pjs6hwlk9p` (`etudiant`),
  ADD KEY `FKoabapd07aktao0fgp7fcmjyc3` (`prof`);

--
-- Indexes for table `etudiant_review`
--
ALTER TABLE `etudiant_review`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK110834rsyepeo8v9lik52af8w` (`cours`),
  ADD KEY `FKgwo0me4rltxhwuiy7me8895n0` (`etudiant`),
  ADD KEY `FKkn1id83ba8mh3ln0048uvpngx` (`prof`);

--
-- Indexes for table `faq`
--
ALTER TABLE `faq`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrgxco5rgxcl8cnnivuye1swgm` (`faq_type`);

--
-- Indexes for table `faq_etudiant`
--
ALTER TABLE `faq_etudiant`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqd7c6mdcv3db5gbfowba9ngp7` (`admin`),
  ADD KEY `FKlwp96jfg70od8vayyn1sqgwj3` (`etudiant`),
  ADD KEY `FKb7g08te0f990r53il8kv3edk3` (`faq_type`);

--
-- Indexes for table `faq_prof`
--
ALTER TABLE `faq_prof`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt6mi1i99cb2d984yrtdy4td8g` (`admin`),
  ADD KEY `FKpkw1qjw8wju8co2sa879t5nfc` (`faq_type`),
  ADD KEY `FK6jqujiv17ovcynjeami5u3la0` (`prof`);

--
-- Indexes for table `faq_type`
--
ALTER TABLE `faq_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `fonction`
--
ALTER TABLE `fonction`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `free_trialformule`
--
ALTER TABLE `free_trialformule`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKk5f2fpqwq2n1t7gg2hmjq4ugw` (`inscription`);

--
-- Indexes for table `groupe_etude`
--
ALTER TABLE `groupe_etude`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `groupe_etudiant`
--
ALTER TABLE `groupe_etudiant`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7mdffyl8d7byt5tbdqhf78tux` (`groupe_etude`),
  ADD KEY `FKf3f4p84vcfmyaap00vr5terxd` (`parcours`),
  ADD KEY `FK3yj2m07x1aug1jebp8el1ery5` (`prof`);

--
-- Indexes for table `groupe_etudiant_detail`
--
ALTER TABLE `groupe_etudiant_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKp6o0i94mh23jp7sii2x75q7jf` (`etudiant`),
  ADD KEY `FKpjpnev6mm1utuikijx35daugc` (`groupe_etudiant`);

--
-- Indexes for table `home_work`
--
ALTER TABLE `home_work`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhswua5krt4s6scfv9lhfornmr` (`cours`),
  ADD KEY `FKli22lh6q1k3ubk11cex54jnws` (`type_home_work`);

--
-- Indexes for table `home_work_etudiant`
--
ALTER TABLE `home_work_etudiant`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKtail5a3rvvns9v6a6f0ucg6d1` (`etudiant`),
  ADD KEY `FK38eenp9ys8b73oba7toc9cdns` (`home_work`);

--
-- Indexes for table `home_work_question`
--
ALTER TABLE `home_work_question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6xm80lxun6esb5jl6ku7fwkmt` (`home_work`),
  ADD KEY `FKh0l6881rm1uctd5xemul163qt` (`type_de_question`);

--
-- Indexes for table `howe_workqstreponse`
--
ALTER TABLE `howe_workqstreponse`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3mk5raehourpcqin5o7b5xdpj` (`home_work_question`);

--
-- Indexes for table `inscription`
--
ALTER TABLE `inscription`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjqqx1gy9a3j1vh43lah28codm` (`etat_inscription`),
  ADD KEY `FK6cte6jfjie9t8tg6oj92c2dqp` (`etudiant`),
  ADD KEY `FKa3eiicdqp9u0fld3n9dc7cxnh` (`fonction`),
  ADD KEY `FK91qbeeauxvguyv3devi6tmpe5` (`groupe_etude`),
  ADD KEY `FK39vcult8qwm6082449wt7xi1v` (`interet_etudiant`),
  ADD KEY `FKhiuov1scc6mk3cncr0pt7l29` (`niveau_etude`),
  ADD KEY `FK7dttm7orlsthsla1nwc0at6ev` (`pack_student`),
  ADD KEY `FKfxb52ippjhv4pv9m2hjl8cval` (`parcours`),
  ADD KEY `FKt3bl0vpok073s5jj2s19q7kd7` (`skill`),
  ADD KEY `FKn7kikbdt7o6jqpu3et36v0k5c` (`statut_social`);

--
-- Indexes for table `interet_etudiant`
--
ALTER TABLE `interet_etudiant`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `invite_student`
--
ALTER TABLE `invite_student`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKe30k6o2p50vcdaxkups7ouds` (`etudiant`);

--
-- Indexes for table `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `niveau_etude`
--
ALTER TABLE `niveau_etude`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pack_student`
--
ALTER TABLE `pack_student`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjeun88kntt2vhrp6tb36lmo7o` (`level`);

--
-- Indexes for table `paiement`
--
ALTER TABLE `paiement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKctdwk5tglquu9hc1fi3jaca1p` (`groupe_etudiant`),
  ADD KEY `FKq8qv9evr3ol87i0xo6qminjhn` (`prof`);

--
-- Indexes for table `parcours`
--
ALTER TABLE `parcours`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKh3ihogjoyr5sb88mdasp4b29q` (`centre`);

--
-- Indexes for table `prof_review`
--
ALTER TABLE `prof_review`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKinl8jxqfhgr2kaq57rni95ypp` (`cours`),
  ADD KEY `FK43yc2ojxru0lf6xxh462vxmhr` (`etudiant`),
  ADD KEY `FK1n5eaa0pd2mgmapag8u0n4qmx` (`prof`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf3q3vgi79fjbkeb0awtlxqpi0` (`home_work`),
  ADD KEY `FKgm7374be0rrwco31uvy9f0vx2` (`quiz`),
  ADD KEY `FKi8a8qe3rlmggorr6fekdn2n46` (`type_de_question`);

--
-- Indexes for table `quiz`
--
ALTER TABLE `quiz`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKs7s806gpa0cowgi72bghsk3r` (`section`);

--
-- Indexes for table `quiz_class_room`
--
ALTER TABLE `quiz_class_room`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnkpb1rcfb73icdyuag9ye477t` (`class_room`),
  ADD KEY `FKh3b5gv6q6oils3exqpf03rbts` (`quiz`);

--
-- Indexes for table `quiz_etudiant`
--
ALTER TABLE `quiz_etudiant`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqwbl44o7itwi2v8nm04w9dd7j` (`etudiant`),
  ADD KEY `FK2iv3wd370lcabo4c27jxo34lo` (`quiz`);

--
-- Indexes for table `reclamation_etudiant`
--
ALTER TABLE `reclamation_etudiant`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjj3ji2ew5elk4wg2xmkdpp49w` (`type_reclamation_etudiant`),
  ADD KEY `FKogkxs59bs0qp7t99s2xaopt3o` (`user`);

--
-- Indexes for table `reclamation_prof`
--
ALTER TABLE `reclamation_prof`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKabw0p2ca4j776im8napco7aa0` (`admin`),
  ADD KEY `FKtqjl7u52p1ux2jrdd0tvwxpsb` (`prof`),
  ADD KEY `FKmyj5js5tkr7weq4o7qe473dsg` (`type_reclamation_prof`);

--
-- Indexes for table `recommend_teacher`
--
ALTER TABLE `recommend_teacher`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKs5iuwhh8bc0s9ca4psrecdq56` (`prof`);

--
-- Indexes for table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKd37uym4s0mpl1vk75ysy2wd6t` (`question`);

--
-- Indexes for table `reponse_etudiant`
--
ALTER TABLE `reponse_etudiant`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjfkks7p2e235gfhrg23u5lvdq` (`question`),
  ADD KEY `FK6au20luv87x2e7jbeem6vjhjj` (`quiz_etudiant`),
  ADD KEY `FKrwii71bjhw0w3qd13v6na7jng` (`reponse`);

--
-- Indexes for table `reponse_etudiant_home_work`
--
ALTER TABLE `reponse_etudiant_home_work`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpfp0mtqnyy7ktl66kvvmxkksr` (`home_work_etudiant`),
  ADD KEY `FKs3fi5ie6fuant9ho2wfu3wcju` (`question`),
  ADD KEY `FKipb07m9ccayton4lf3rtvnmf4` (`reponse`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `salary`
--
ALTER TABLE `salary`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdc0td9t86x46yco4udfg5336e` (`prof`);

--
-- Indexes for table `schdedule_vo`
--
ALTER TABLE `schdedule_vo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9ceda90vkewpsw0xumvk3d4sh` (`prof`);

--
-- Indexes for table `schedule_prof`
--
ALTER TABLE `schedule_prof`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnx1yssye6hna28dt1bnyapgv6` (`cours`),
  ADD KEY `FKnw29papmfa7cl3d1aly62qwaj` (`groupe_etudiant`),
  ADD KEY `FK42u6vvfgl94899nnp0f98nlwb` (`prof`);

--
-- Indexes for table `section`
--
ALTER TABLE `section`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKj8qd5h093paw4w71oetaakfmv` (`categorie_section`),
  ADD KEY `FKdpo4rekh2oooyxj12qqsncdlo` (`cours`),
  ADD KEY `FKenfws2o00pdc1vdqe4idtsmn7` (`session_cours`);

--
-- Indexes for table `section_item`
--
ALTER TABLE `section_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2owrl9pakrc9dyqlo20tdhwxe` (`section`);

--
-- Indexes for table `section_item_synonyms`
--
ALTER TABLE `section_item_synonyms`
  ADD KEY `FKr8rvgsbmtfld1j137u2r7fyd9` (`section_item_id`);

--
-- Indexes for table `session_cours`
--
ALTER TABLE `session_cours`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKp9kd319hk6ri4fj7iveyvneet` (`cours`),
  ADD KEY `FKkn6g0xcmopmujf8pohqilacob` (`groupe_etudiant`),
  ADD KEY `FKhy4e8bi00hoqolsy7nkp2iawa` (`prof`),
  ADD KEY `FKdlpy35pos2jwlqexss8811jkl` (`salary`);

--
-- Indexes for table `skill`
--
ALTER TABLE `skill`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `statut_social`
--
ALTER TABLE `statut_social`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `super_categorie_section`
--
ALTER TABLE `super_categorie_section`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tranche_horaire_prof`
--
ALTER TABLE `tranche_horaire_prof`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkti3tl5dq1ll42disf2tri5fw` (`prof`);

--
-- Indexes for table `type_de_question`
--
ALTER TABLE `type_de_question`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `type_home_work`
--
ALTER TABLE `type_home_work`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `type_reclamation_etudiant`
--
ALTER TABLE `type_reclamation_etudiant`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `type_reclamation_prof`
--
ALTER TABLE `type_reclamation_prof`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `type_teacher`
--
ALTER TABLE `type_teacher`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKea5jtyb2y1nl27td27pl7invt` (`etat_etudiant_schedule`),
  ADD KEY `FKol9pdx1aut17cnmxdejejhpei` (`fonction`),
  ADD KEY `FKpygqxvsjr9ntxj62dj4w4otna` (`groupe_etude`),
  ADD KEY `FKdbd4p2ib8n1irskimlrryi1tr` (`interet_etudiant`),
  ADD KEY `FKku3h9qqvmvs47hsponjn9rxcl` (`niveau_etude`),
  ADD KEY `FKkwbeojhv6ojos6goafiy91jr3` (`pack_student`),
  ADD KEY `FKn9b379p476tt54th2d3mg29u4` (`parcours`),
  ADD KEY `FKhc9uud4n4b9hyg4a0u3olngo9` (`skill`),
  ADD KEY `FKkxxguj1ad0xo9v87f8d6kherm` (`statut_social`),
  ADD KEY `FKavyg1uphafcv0jhletd7nkqo7` (`categorie_prof`),
  ADD KEY `FKt4vn964bn40kmlo80ytu37xd9` (`level_max`),
  ADD KEY `FKhmh84rol68d5m99isehl47m2w` (`level_min`),
  ADD KEY `FK79gvd6lpngstx3yii75h7b8mt` (`type_teacher`);

--
-- Indexes for table `user_authorities`
--
ALTER TABLE `user_authorities`
  ADD KEY `FKpcisayyxnlrmbqnga98ip5i8g` (`authorities`),
  ADD KEY `FK56unbhbs88d628ft61ax25i7` (`user_id`);

--
-- Indexes for table `vocabulary`
--
ALTER TABLE `vocabulary`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3jv1nxfl64v4026wcla5by5r8` (`section`),
  ADD KEY `FK98vkct8iywsgu1mcbi3gob4in` (`vocabulary_quiz`);

--
-- Indexes for table `vocabulary_quiz`
--
ALTER TABLE `vocabulary_quiz`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKb1qgj4v65w4gcva0pa3vcaao` (`section`);

--
-- Indexes for table `workload_bonus`
--
ALTER TABLE `workload_bonus`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `workload_bonus_prof`
--
ALTER TABLE `workload_bonus_prof`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK61rcn9lurc14do2x7ar2vmitr` (`prof`),
  ADD KEY `FKtce3utyklt0fnldo44vep98tn` (`salary`),
  ADD KEY `FKtgqp8q6oiv9mbfu4jbcxiyhay` (`workload_bonus`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `type_teacher`
--
ALTER TABLE `type_teacher`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
