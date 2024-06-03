
-- Supprime toutes les données

DELETE FROM compte;


DELETE FROM Resultat;


DELETE FROM Joueur;
DELETE FROM Poste;
DELETE FROM Configuration_Poste;


DELETE FROM Quiz;
DELETE FROM avoir;
DELETE FROM Theme;
DELETE FROM Media;

DELETE FROM Parcours;
DELETE FROM Statistique;
DELETE FROM Reponse;
DELETE FROM Question;
DELETE FROM Astuce;

DELETE FROM administrer;
DELETE FROM elaborer;
DELETE FROM posseder;
DELETE FROM contenir;
DELETE FROM inclure;
DELETE FROM effectuer;
DELETE FROM etre_affilier_à;
DELETE FROM consulter;
DELETE FROM generer;
DELETE FROM etre_associer;


-- Compte

INSERT INTO compte (idcompte, nom, prenom,date_naissance,motdepasse, email, flagadmin ) VALUES 
( 1, 'Kameni', 'Agnès', {d '2004-08-31' },'kameni', 'kameni@jfox.fr', TRUE ),
( 2, 'Feune', 'Aaron',{d '2004-02-11' },'feune', 'feune@jfox.fr', FALSE );

ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 3;


--Theme
INSERT INTO Theme (idtheme,nom, description) VALUES
(1,'Connaissance générale', 'Questions sur divers sujets de culture générale.'),
(2,'Cycles de l’eau', 'Questions sur les cycles de l’eau, naturel et artificiel.'),
(3,'Faune / Flore', 'Questions sur les animaux et les plantes.'),
(4,'Les milieux et habitats', 'Questions sur les différents milieux et habitats naturels.'),
(5,'Consommation, économie et bons gestes', 'Questions sur la consommation, l’économie et les gestes éco-responsables.');


--Resultat
INSERT INTO Resultat(idresultat,heure,score) VALUES
(1,'10:00:00', 85),
(2,'11:00:00', 90),
(3,'12:00:00', 95);


--Astuce
INSERT INTO Astuce (idastuce, libelle) VALUES 
(1, 'C’est plus que trois mais moins que cinq.'),
(2, 'Pensez à la couleur que vous voyez dans un verre d’eau.'),
(3, 'Ce sont des lieux comme les rivières et les lacs.'),
(4, 'Pensez à ce que les plantes boivent pour grandir.'),
(5, 'Pensez au goût de l’eau salée.'),
(6, 'Entre un et trois litres.'),
(7, 'Il borde l’Asie et l’Amérique.'),
(8, 'C’est une précipitation liquide.'),
(9, 'Ce sont les grands réservoirs sous terre.'),
(10, 'Pensez à la glace.'),
(11, 'C’est moins de 5%.'),
(12, 'Cela inclut l’évaporation et la précipitation.'),
(13, 'Elle traverse plusieurs pays africains.'),
(14, 'Sa formule chimique est H2O.'),
(15, 'C’est le même gaz que celui produit par les voitures.'),
(16, 'Pensez à la pluie.'),
(17, 'Elle s’évapore et devient un nuage.'),
(18, 'Elles boivent pour grandir.'),
(19, 'Elle retourne dans les rivières et les lacs.'),
(20, 'Pensez à la glace.'),
(21, 'Cela se passe quand l’eau chauffe.'),
(22, 'Ce sont des réservoirs sous la terre.'),
(23, 'Ils se forment par condensation et donnent de la pluie.'),
(24, 'Elles reçoivent constamment de l’eau des pluies et des sources.'),
(25, 'Elles utilisent leurs feuilles.'),
(26, 'C’est un type de percolation.'),
(27, 'L’altitude et les basses températures jouent un rôle.'),
(28, 'Cela concerne le déplacement de l’eau souterraine.'),
(29, 'C’est le cycle de l’eau potable.'),
(30, 'Ils contrôlent le débit et créent des réserves.'),
(31, 'Il est plus grand qu’un éléphant et vit dans l’océan.'),
(32, 'Elle produit de l’oxygène.'),
(33, 'Pensez aux saumons.'),
(34, 'Pensez aux hérons.'),
(35, 'Elles y pondent leurs œufs.'),
(36, 'Cela est causé par des changements climatiques.'),
(37, 'C’est un rongeur avec de grandes dents.'),
(38, 'Il glisse sur la surface de l’eau.'),
(39, 'Ils pondent des œufs dans l’eau.'),
(40, 'Elle tolère des eaux très salées.'),
(41, 'Elles absorbent l’impact des vagues.'),
(42, 'Leur peau est très perméable aux changements de l’eau.'),
(43, 'Il s’alimente principalement de krill.'),
(44, 'Cela cause des décès par ingestion ou enchevêtrement.'),
(45, 'Elle pousse souvent dans les étangs et les lacs.'),
(46, 'Pensez à un marécage.'),
(47, 'Ils sont en haut des montagnes ou aux pôles.'),
(48, 'Elles abritent de nombreux animaux.'),
(49, 'C’est une forêt riveraine.'),
(50, 'C’est de l’eau douce.'),
(51, 'Ce sont des estuaires.'),
(52, 'Elles offrent des habitats riches et diversifiés.'),
(53, 'Elles érodent les sols et transportent les sédiments.'),
(54, 'Ce sont des forêts côtières dans les régions tropicales.'),
(55, 'Elles sont riches en nutriments déposés par les crues.'),
(56, 'Ils alimentent les rivières et les lacs en eau de fonte.'),
(57, 'Ils offrent des ressources abondantes et variées.');




--Question
INSERT INTO Question (idquestion, enonce, idastuce) VALUES 
(1, 'Combien y a-t-il d’océans sur Terre ?', 1),
(2, 'Quelle est la couleur de l’eau ?', 2),
(3, 'Où trouve-t-on de l’eau douce ?', 3),
(4, 'À quoi sert l’eau pour les plantes ?', 4),
(5, 'Peut-on boire l’eau de la mer ?', 5),
(6, 'Combien de litres d’eau doit-on boire chaque jour pour être en bonne santé ?', 6),
(7, 'Quel est le plus grand océan du monde ?', 7),
(8, 'Comment s’appelle l’eau qui tombe du ciel sous forme de pluie ?', 8),
(9, 'Quelle est la principale source d’eau douce sur Terre ?', 9),
(10, 'Quel est l’état de l’eau à 0 degré Celsius ?', 10),
(11, 'Quelle est la proportion d’eau douce disponible sur Terre par rapport à l’eau salée ?', 11),
(12, 'Quel est le cycle qui décrit le mouvement de l’eau à travers la Terre et l’atmosphère ?', 12),
(13, 'Quelle est la plus longue rivière du monde ?', 13),
(14, 'Quel est le principal composant chimique de l’eau ?', 14),
(15, 'Quel gaz est majoritairement responsable de l’effet de serre et contribue au réchauffement des océans ?', 15),
(16, 'Comment s’appelle l’eau qui tombe du ciel ?', 16),
(17, 'Que se passe-t-il quand l’eau se réchauffe au soleil ?', 17),
(18, 'Pourquoi les plantes ont-elles besoin d’eau ?', 18),
(19, 'Où va l’eau de la pluie après être tombée sur le sol ?', 19),
(20, 'Que fait l’eau quand il fait très froid ?', 20),
(21, 'Quel est le nom du processus par lequel l’eau passe de l’état liquide à l’état gazeux ?', 21),
(22, 'Comment s’appelle l’eau souterraine ?', 22),
(23, 'Quel est le rôle des nuages dans le cycle de l’eau ?', 23),
(24, 'Pourquoi les rivières ne s’assèchent-elles pas ?', 24),
(25, 'Comment les plantes transpirent-elles l’eau ?', 25),
(26, 'Comment s’appelle le processus par lequel l’eau de pluie s’infiltre dans le sol pour recharger les nappes phréatiques ?', 26),
(27, 'Pourquoi les montagnes ont-elles des chutes de neige ?', 27),
(28, 'Quel est le terme pour le mouvement de l’eau à travers le sol ?', 28),
(29, 'Quel est le cycle artificiel où l’eau est traitée et redistribuée pour l’usage humain ?', 29),
(30, 'Comment les barrages aident-ils dans la gestion de l’eau ?', 30),
(31, 'Quel est le plus grand animal marin ?', 31),
(32, 'Quelle plante pousse dans les étangs et aide les poissons à respirer ?', 32),
(33, 'Comment s’appelle un poisson qui peut vivre en eau douce et en eau salée ?', 33),
(34, 'Quel oiseau est souvent vu près de l’eau et a de longues jambes ?', 34),
(35, 'Pourquoi les grenouilles ont-elles besoin d’eau ?', 35),
(36, 'Quelle est la principale menace pour les récifs coralliens dans le monde ?', 36),
(37, 'Quel animal construit des barrages pour créer des étangs ?', 37),
(38, 'Quel insecte peut marcher sur l’eau grâce à la tension superficielle ?', 38),
(39, 'Pourquoi les amphibiens ont-ils besoin de l’eau pour se reproduire ?', 39),
(40, 'Quelle espèce de poisson peut vivre dans la mer Morte ?', 40),
(41, 'Comment les mangroves protègent-elles les côtes ?', 41),
(42, 'Pourquoi les grenouilles sont-elles des indicateurs de la santé des écosystèmes aquatiques ?', 42),
(43, 'Quel mammifère marin est connu pour être le plus grand animal sur Terre ?', 43),
(44, 'Comment la pollution plastique affecte-t-elle la faune marine ?', 44),
(45, 'Quelle plante aquatique est essentielle pour produire de l’oxygène dans les écosystèmes d’eau douce ?', 45),
(46, 'Comment s’appelle un grand lac entouré de végétation humide ?', 46),
(47, 'Où se trouvent les glaciers ?', 47),
(48, 'Pourquoi les plantes des zones humides sont-elles importantes ?', 48),
(49, 'Comment appelle-t-on une forêt près de l’eau ?', 49),
(50, 'Quel type d’eau trouve-t-on dans un étang ?', 50),
(51, 'Comment s’appelle une zone où l’eau douce rencontre l’eau salée ?', 51),
(52, 'Pourquoi les zones humides sont-elles importantes pour la biodiversité ?', 52),
(53, 'Comment les rivières modifient-elles les paysages ?', 53),
(54, 'Quel est le principal habitat des mangroves ?', 54),
(55, 'Pourquoi les plaines inondables sont-elles importantes pour l’agriculture ?', 55);


--Relation theme et question (classe avoir)
INSERT INTO avoir (idquestion, idtheme) VALUES 
(1, 1),
(2, 1),
(3, 1),
(3, 4),
(4, 2),
(4, 3),
(5, 1),
(6, 1),
(7, 1),
(8, 2),
(9, 2),
(10, 1),
(11, 2),
(12, 2),
(13, 1),
(14, 1),
(15, 5),
(16, 2),
(17, 2),
(18, 2),
(18, 3),
(19, 2),
(20, 2),
(21, 2),
(22, 2),
(23, 2),
(24, 2),
(25, 2),
(25, 3),
(26, 2),
(27, 4),
(28, 4),
(29, 2),
(30, 5),
(31, 3),
(32, 3),
(33, 3),
(34, 3),
(35, 3),
(36, 5),
(37, 3),
(38, 3),
(39, 3),
(40, 3),
(41, 4),
(42, 4),
(43, 3),
(44, 5),
(45, 3),
(46, 4),
(47, 4),
(48, 4),
(49, 4),
(50, 4),
(51, 4),
(52, 4),
(53, 4),
(54, 4),
(55, 4);

--Media
INSERT INTO Media (titre, description) VALUES
('Son de la mer', 'Un son de la mer pour détendre.');



--poste
INSERT INTO Poste(idposte,libelle) VALUES
(1,'Poste 1'),
(2,'Poste 2'),
(3,'Poste 3');
--Joueur
INSERT INTO Joueur (ville, categorie, idposte) VALUES
('Limoges', 'Cycle 1 et 2', 3),
('Bordeaux', 'Cycle 3', 3),
('Saint-Juinien', 'Gd Public', 3);

--Configuration_Poste
INSERT INTO Configuration_Poste (heure, idposte) VALUES
('09:00:00', 1),
('10:00:00', 2),
('11:00:00', 2);



--Parcours
INSERT INTO Parcours (mode) VALUES
('Quizz individuel'),
('Mode parcours');

--Statistique
INSERT INTO Statistique (libelle) VALUES
('Nombre de participations par quiz'),
('Score moyen par quiz'),
('Nombre total de réponses correctes par utilisateur'),
('Temps moyen passé par quiz'),
('Taux de réussite par question'),
('Nombre total de quiz complétés par utilisateur'),
('Nombre moyen de questions répondues par session'),
('Meilleure performance par utilisateur'),
('Nombre de quiz créés par thème'),
('Pourcentage de bonnes réponses par thème');

-- Insertion des enregistrements dans la table Quiz
INSERT INTO Quiz (description, idtheme) VALUES
('Quiz de connaissance générale pour cycle 1 et 2', 1),
('Quiz de connaissance générale pour Cycle 3', 1),
('Quiz de connaissance générale pour Gd Public', 1),
('Quiz sur les cycles de l’eau pour cycle 1 et 2', 2),
('Quiz sur les cycles de l’eau pour Cycle 3', 2),
('Quiz sur les cycles de l’eau pour Gd Public', 2),
('Quiz sur la faune et la flore pour cycle 1 et 2', 3),
('Quiz sur la faune et la flore pour Cycle 3', 3),
('Quiz sur la faune et la flore pour Gd Public', 3),
('Quiz sur les milieux et habitats pour cycle 1 et 2', 4),
('Quiz sur les milieux et habitats pour Cycle 3', 4),
('Quiz sur les milieux et habitats pour Gd Public', 4),
('Quiz sur la consommation, l’économie et les bons gestes pour cycle 1 et 2', 5),
('Quiz sur la consommation, l’économie et les bons gestes pour Cycle 3', 5),
('Quiz sur la consommation, l’économie et les bons gestes pour Gd Public', 5);



--Reponse
INSERT INTO Reponse (idReponse, libelle) VALUES
(1, '5'),
(2, '3'),
(3, '4'),
(4, '6'),

(5, 'Incolore'),
(6, 'Bleue'),
(7, 'Verte'),
(8, 'Jaune'),

(9, 'Dans les rivières'),
(10, 'Dans les océans'),
(11, 'Dans les mers'),
(12, 'Dans les déserts'),

(13, 'Pour la photosynthèse'),
(14, 'Pour produire du nectar'),
(15, 'Pour attirer les insectes'),
(16, 'Pour se protéger du soleil'),

(17, 'Non'),
(18, 'Oui'),
(19, 'Parfois'),
(20, 'Seulement avec du sel'),

(21, '2 litres'),
(22, '1 litre'),
(23, '3 litres'),
(24, '4 litres'),

(25, 'Océan Pacifique'),
(26, 'Océan Atlantique'),
(27, 'Océan Indien'),
(28, 'Océan Arctique'),

(29, 'Pluie'),
(30, 'Neige'),
(31, 'Grêle'),
(32, 'Rosée'),

(33, 'Les glaciers'),
(34, 'Les mers'),
(35, 'Les océans'),
(36, 'Les lacs salés'),

(37, 'Solide'),
(38, 'Liquide'),
(39, 'Gazeux'),
(40, 'Plasma'),

(41, '2.5%'),
(42, '10%'),
(43, '25%'),
(44, '50%'),

(45, 'Cycle de l’eau'),
(46, 'Cycle du carbone'),
(47, 'Cycle de l’azote'),
(48, 'Cycle du soufre'),

(49, 'Le Nil'),
(50, 'L’Amazone'),
(51, 'Le Yangtsé'),
(52, 'Le Mississippi'),

(53, 'H₂O'),
(54, 'CO₂'),
(55, 'O₂'),
(56, 'N₂'),

(57, 'CO₂'),
(58, 'O₂'),
(59, 'N₂'),
(60, 'CH₄'),

(61, 'Pluie'),
(62, 'Neige'),
(63, 'Grêle'),
(64, 'Rosée'),

(65, 'Elle s’évapore'),
(66, 'Elle gèle'),
(67, 'Elle se condense'),
(68, 'Elle disparaît'),

/*(69, 'Pour la photosynthèse', TRUE, 18),
(70, 'Pour se nourrir', FALSE, 18),
(71, 'Pour grandir', FALSE, 18),
(72, 'Pour respirer', FALSE, 18),

(73, 'Dans les nappes phréatiques', TRUE, 19),
(74, 'Dans les océans', FALSE, 19),
(75, 'Dans les rivières', FALSE, 19),
(76, 'Dans les lacs', FALSE, 19),
(77, 'Elle gèle', TRUE, 20),

(78, 'Elle s’évapore', FALSE, 20),
(79, 'Elle bouillonne', FALSE, 20),
(80, 'Elle disparaît', FALSE, 20),

(81, 'Évaporation', TRUE, 21),
(82, 'Condensation', FALSE, 21),
(83, 'Précipitation', FALSE, 21),
(84, 'Transpiration', FALSE, 21),

(85, 'Eau souterraine', TRUE, 22),
(86, 'Eau de surface', FALSE, 22),
(87, 'Eau douce', FALSE, 22),
(88, 'Eau salée', FALSE, 22),

(89, 'Transporter l’eau', TRUE, 23),
(90, 'Absorber l’eau', FALSE, 23),
(91, 'Evaporer l’eau', FALSE, 23),
(92, 'Sédimenter l’eau', FALSE, 23),

(93, 'Sources d’eau continue', TRUE, 24),
(94, 'Évaporation lente', FALSE, 24),
(95, 'Absorption par le sol', FALSE, 24),
(96, 'Débit régulé', FALSE, 24),

(97, 'Par les stomates', TRUE, 25),
(98, 'Par les racines', FALSE, 25),
(99, 'Par les feuilles', FALSE, 25),
(100, 'Par le tronc', FALSE, 25),

(101, 'Infiltration', TRUE, 26),
(102, 'Évaporation', FALSE, 26),
(103, 'Condensation', FALSE, 26),
(104, 'Transpiration', FALSE, 26),

(105, 'Altitude élevée', TRUE, 27),
(106, 'Température basse', FALSE, 27),
(107, 'Proximité des océans', FALSE, 27),
(108, 'Humidité élevée', FALSE, 27),

(109, 'Pércolation', TRUE, 28),
(110, 'Transpiration', FALSE, 28),
(111, 'Évaporation', FALSE, 28),
(112, 'Condensation', FALSE, 28),

(113, 'Cycle hydrologique', TRUE, 29),
(114, 'Cycle du carbone', FALSE, 29),
(115, 'Cycle du soufre', FALSE, 29),
(116, 'Cycle de l’azote', FALSE, 29),

(117, 'Contrôler le débit d’eau', TRUE, 30),
(118, 'Augmenter le débit d’eau', FALSE, 30),
(119, 'Éliminer les polluants', FALSE, 30),
(120, 'Favoriser l’évaporation', FALSE, 30),

(121, 'La baleine bleue', TRUE, 31),
(122, 'Le requin blanc', FALSE, 31),
(123, 'Le dauphin', FALSE, 31),
(124, 'Le cachalot', FALSE, 31),

(125, 'Le nénuphar', TRUE, 32),
(126, 'La fougère', FALSE, 32),
(127, 'L’orchidée', FALSE, 32),
(128, 'Le cactus', FALSE, 32),

(129, 'Poisson amphihalin', TRUE, 33),
(130, 'Poisson catadrome', FALSE, 33),
(131, 'Poisson anadrome', FALSE, 33),
(132, 'Poisson euryhalin', FALSE, 33),

(133, 'Le héron', TRUE, 34),
(134, 'Le pingouin', FALSE, 34),
(135, 'Le perroquet', FALSE, 34),
(136, 'L’aigle', FALSE, 34),

(137, 'Pour respirer et pondre des œufs', TRUE, 35),
(138, 'Pour se nourrir', FALSE, 35),
(139, 'Pour dormir', FALSE, 35),
(140, 'Pour se cacher', FALSE, 35),

(141, 'Le réchauffement climatique', TRUE, 36),
(142, 'La surpêche', FALSE, 36),
(143, 'Les tsunamis', FALSE, 36),
(144, 'La pollution sonore', FALSE, 36),

(145, 'Le castor', TRUE, 37),
(146, 'La loutre', FALSE, 37),
(147, 'Le ragondin', FALSE, 37),
(148, 'Le rat musqué', FALSE, 37),

(149, 'Le gerris', TRUE, 38),
(150, 'Le moustique', FALSE, 38),
(151, 'Le criquet', FALSE, 38),
(152, 'La fourmi', FALSE, 38),

(153, 'Pour la ponte et le développement des larves', TRUE, 39),
(154, 'Pour se nourrir', FALSE, 39),
(155, 'Pour hiberner', FALSE, 39),
(156, 'Pour migrer', FALSE, 39),

(157, 'Aucun', TRUE, 40),
(158, 'Le tilapia', FALSE, 40),
(159, 'Le saumon', FALSE, 40),
(160, 'La truite', FALSE, 40),

(161, 'En réduisant l’érosion', TRUE, 41),
(162, 'En augmentant l’évaporation', FALSE, 41),
(163, 'En absorbant le sel', FALSE, 41),
(164, 'En produisant des marées', FALSE, 41),

(165, 'Elles sont sensibles aux changements environnementaux', TRUE, 42),
(166, 'Elles vivent longtemps', FALSE, 42),
(167, 'Elles migrent sur de longues distances', FALSE, 42),
(168, 'Elles se reproduisent rapidement', FALSE, 42),

(169, 'La baleine bleue', TRUE, 43),
(170, 'Le requin blanc', FALSE, 43),
(171, 'Le dauphin', FALSE, 43),
(172, 'Le cachalot', FALSE, 43),

(173, 'Elle est ingérée par les animaux marins', TRUE, 44),
(174, 'Elle augmente la salinité de l’eau', FALSE, 44),
(175, 'Elle diminue l’oxygénation de l’eau', FALSE, 44),
(176, 'Elle modifie le pH de l’eau', FALSE, 44),

(177, 'La jacinthe d’eau', TRUE, 45),
(178, 'Le nénuphar', FALSE, 45),
(179, 'La fougère', FALSE, 45),
(180, 'Le cactus', FALSE, 45),

(181, 'Marécage', TRUE, 46),
(182, 'Étang', FALSE, 46),
(183, 'Lac', FALSE, 46),
(184, 'Rivière', FALSE, 46),

(185, 'Aux pôles et sur les montagnes', TRUE, 47),
(186, 'Dans les déserts', FALSE, 47),
(187, 'Dans les plaines', FALSE, 47),
(188, 'Dans les vallées', FALSE, 47),

(189, 'Elles filtrent l’eau et fournissent un habitat', TRUE, 48),
(190, 'Elles augmentent l’évaporation', FALSE, 48),
(191, 'Elles diminuent l’infiltration', FALSE, 48),
(192, 'Elles augmentent la salinité', FALSE, 48),

(193, 'Ripisylve', TRUE, 49),
(194, 'Savane', FALSE, 49),
(195, 'Steppe', FALSE, 49),
(196, 'Prairie', FALSE, 49),

(197, 'Eau douce', TRUE, 50),
(198, 'Eau salée', FALSE, 50),
(199, 'Eau saumâtre', FALSE, 50),
(200, 'Eau minérale', FALSE, 50),

(201,'Estuaire', TRUE, 51),
(202,'Lagune', FALSE, 51),
(203,'Marais', FALSE, 51),
(204,'Rivière', FALSE, 51),*/

(205,'Elles abritent de nombreuses espèces'),
(206,'Elles sont belles'),
(207,'Elles sont faciles à drainer'),
(208,'Elles produisent du sel'),

(209,'En érodant le sol'),
(210,'En construisant des montagnes'),
(211,'En filtrant l’eau'),
(212,'En absorbant l’eau'),

(213,'Zones côtières tropicales'),
(214,'Déserts'),
(215,'Montagnes'),
(216,'Forêts tempérées'),

(217,'Elles enrichissent le sol en nutriments'),
(218,'Elles retiennent l’eau'),
(219,'Elles protègent des inondations'),
(220,'Elles fournissent de l’ombre');



