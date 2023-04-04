INSERT
IGNORE INTO `statut_social` (`id`, `code`, `libelle`)
VALUES (1, '1', 'Student'),
       (2, '2', 'Unemployed'),
       (3, '3', 'Employed');
--
-- Dumping data for table `categorie_prof`
--

INSERT
IGNORE INTO `categorie_prof` (`id`, `code`, `lesson_rate`, `level`)
VALUES (1, 'c1', '230.00', 'Junior'),
       (2, 'c2', '270.00', 'Middle'),
       (3, 'c3', '300.00', 'Senior');

--
-- Dumping data for table `categorie_section`
--

INSERT
IGNORE INTO `categorie_section` (`id`, `code`, `libelle`, `numero_order`, `super_categorie_section`)
VALUES (1, 'Warm up', 'Warm up', 1, 159),
       (2, 'Get to know', 'Get to know', 4, 159),
       (3, 'Discussion', 'Discussion', 6, 159),
       (4, 'Life Story', 'Life Story', 8, 160), --Additional
       (5, 'Games', 'Games', 10, 160), --Additional
       (6, "Let's practice", "Let's practice", 5, 159),
       (7, 'Study the information', "Study the information", 7, 159),
       (8, 'Vocabulary', 'Vocabulary', 3, 159),
       (9, 'Home Work Review', 'Home Work Review', 2, 159),
       (10, 'Role Play', 'Role Play', 9, 160); --Additional

--
-- Dumping data for table `centre`
--

INSERT
IGNORE INTO `centre` (`id`, `description`, `libelle`, `log`, `password`, `ref`)
VALUES (83, NULL, 'American Center 1', NULL, NULL, 'a1'),
       (88, NULL, 'American Center 2', NULL, NULL, 'a2');

--


--
--
-- Dumping data for table `etat_inscription`
--

INSERT
IGNORE INTO `etat_inscription` (`id`, `libelle`, `ref`)
VALUES (3, 'refused', 'E3'),
       (2, 'Validated', 'E2'),
       (1, 'Pending', 'E1');



INSERT
IGNORE INTO `faq_type` (`id`, `libelle`, `destinataire`)
VALUES (1485, 'Common questions', 'teacher'),
       (1486, 'Salary', 'teacher'),
       (1487, 'Exams', 'student'),
       (1488, 'Diplome', 'student'),
       (1, 'DE Class Support', 'teacher'),
       (2, 'Teachers effectiveness', 'teacher'),
       (3, 'Methodological care', 'teacher');


--
-- Dumping data for table `groupe_etude`
--

INSERT
IGNORE INTO `groupe_etude` (`id`, `description`, `libelle`, `nombre_etudiant`)
VALUES (25908, NULL, 'Group for 10 person', 10),
       (25907, NULL, 'Group for 5 person', 5),
       (25906, NULL, 'Group for 2 person', 2),
       (25905, NULL, 'Group for 1 person', 1);



INSERT
IGNORE INTO `interet_etudiant` (`id`, `code`, `libelle`)
VALUES (1, '1', 'Discussing with family'),
       (2, '2', 'Boosting my studies '),
       (3, '3', 'Self cultivation'),
       (4, '4', 'Boosting my career\r\n'),
       (5, '5', 'Travel'),
       (6, '6', 'Other');



INSERT
IGNORE INTO `niveau_etude` (`id`, `code`, `libelle`)
VALUES (1, '1', 'Secondary'),
       (2, '2', 'Primary '),
       (3, '3', 'College'),
       (4, '4', 'Other');


INSERT
IGNORE INTO `level_test_configuration`(`id`, `note_max`, `note_min`, `parcours`)
VALUES (1, 10, 0, 4220),
       (2, 20, 10, 4805),
       (3, 30, 20, 5390),
       (4, 40, 30, 1),
       (5, 50, 40, 5865);

INSERT
IGNORE
INTO `role` (`id`, `authority`)
VALUES (1, 'ADMIN'),
    (2, 'TEACHER'),
    (3, 'STUDENT'),
    (4, 'SUPER_ADMIN');


-- Dumping data for table `section`
--

INSERT
IGNORE INTO `skill` (`id`, `code`, `libelle`)
VALUES (1, NULL, 'Reading'),
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



INSERT
IGNORE INTO `super_categorie_section` (`id`, `code`, `libelle`)
VALUES (159, 'Obligatory', 'Obligatory'),
       (160, 'Additional', 'Additional');



INSERT
IGNORE INTO `type_de_question` (`id`, `lib`, `ref`)
VALUES (2, 'Write it up', 't2'),
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

--
-- Dumping data for table `type_home_work`
--

INSERT
IGNORE INTO `type_home_work` (`id`, `lib`,`code`)
VALUES (5, 'Watch it', 'ADDITIONAL'),
       (4, 'Phrasebook', 'ADDITIONAL'),
       (3, 'Write it Up', 'OBLIGATORY'),
       (2, "Let's Practice", 'OBLIGATORY'),
       (1, 'Reading', 'ADDITIONAL'),
       (6, 'Life Story','ADDITIONAL');


INSERT
IGNORE INTO `price` (`id`, `for_group`, `lib`, `nre_course`, `nre_hours`, `nre_month`, `old_price`, `price`)
VALUES (3, false, 'SILVER', 8, 8, 1, 1500, 879),
       (4, false, 'GOLD', 24, 24, 3, 4500, 2519),
       (5, false, 'PLATINUM', 48, 48, 6, 9000, 4799),
       (6, true, 'SILVER', 8, 8, 1, 350, 199),
       (7, true, 'GOLD', 24, 24, 3, 1000, 549),
       (39571, true, 'PLATINUM', 48, 48, 6, 2000, 999);

INSERT
IGNORE INTO `pack_student` (`id`, `code`, `description`, `expectations`, `for_groupe`, `img_url`, `libelle`, `nombre_cours`, `old_price`, `pre_requisites`, `rating`, `total_students`, `why_take_this_course`, `level`, `price`) VALUES
    (6200, 'c1', NULL, NULL, false, NULL, 'GOLD', 30, '3900', NULL, '5', 9, NULL, 1, 4);

INSERT
IGNORE INTO `parcours` (`id`, `code`, `date_creation`, `date_publication`, `description`, `libelle`, `nombre_cours`,
                        `numero_order`, `centre`)
VALUES (1, '1 ELEMENTRY', NULL, NULL, NULL, '1 ELEMENTARY', 51, 0, 83),
       (5095, '2 PRE INTERMEDIATE', NULL, NULL, NULL, '2 PRE INTERMEDIATE', 51, 0, 83),
       (13000, '3 INTERMEDIATE', NULL, NULL, NULL, '3 INTERMEDIATE', 51, 0, 83),
       (25000, '4 UPPER INTERMEDIATE', NULL, NULL, NULL, '4 UPPER INTERMEDIATE', 51, 0, 83),
       (31050, '5 ADVANCED', NULL, NULL, NULL, '5 ADVANCED', 51, 0, 83);


INSERT
IGNORE INTO `user` (`dtype`, `id`, `account_non_expired`, `account_non_locked`, `addresse`, `age`, `country`, `credentials_non_expired`, `date_naissance`, `enabled`, `image`, `nom`, `numero`, `password`, `prenom`, `role`, `skype`, `token`, `username`, `ville`, `group_option`, `langue`, `ref`, `teacher_locality`, `about`, `fonction`, `groupe_etude`, `interet_etudiant`, `niveau_etude`, `pack_student`, `parcours`, `skill`, `statut_social`, `categorie_prof`, `level_max`, `level_min`, `type_teacher`) VALUES
('Admin', 3, true, true, 'Marrakech', 22, NULL, true, NULL, true, 'http://localhost:8036/user/image/alc.image.driver@gmail.com/alc.image.driver@gmail.com.jpg', 'Zouani Khalid', '0760102030', '$2a$10$Yn//JIg2kioe4w0yU8Rx3.KpmGvHo8eD/F/Ryk5qLpIrL7WzM7OE6', 'Younes', 'ADMIN', NULL, NULL, 'alc.image.driver@gmail.com', 'Marrakech', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('Prof', 2, true, true, NULL, 0, NULL, true, NULL, true, 'http://localhost:8036/user/image/engflexy.contact@gmail.com/engflexy.contact@gmail.com.jpg', 'Kamal Zouani', '0605120314', '$2a$10$41byyLEhF/lKgxXmm8.MfO5aJX5N7lZU3UuoHsKd7bxh52.ybmwne', 'teacher', 'TEACHER', NULL, NULL, 'engflexy.contact@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL),
('Etudiant', 4, true, true, NULL, 0, NULL, true, NULL, true, 'http://localhost:8036/user/image/profile/y.elmoudene@engflexy.com', 'Youssef Elmoudene', '0605120314', '$2a$10$oRMvvb.gFdrYrIghJaOLWu7HIu/Yl6MhtxCM7ReNf7atqH1goz7N6', NULL, 'STUDENT', NULL, NULL, 'y.elmoudene@engflexy.com', NULL, NULL, 'ar', NULL, ' ', NULL, NULL, 25905, NULL, NULL, NULL, 1, 9, NULL, NULL, NULL, NULL, NULL);

--
INSERT INTO `user_authorities` (`user_id`, `authorities`)
VALUES (3, 1),
       (2, 2),
       (4, 3);

