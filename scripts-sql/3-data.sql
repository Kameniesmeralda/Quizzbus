
-- Supprime toutes les données

DELETE FROM compte;
DELETE FROM Question;
DELETE FROM Astuce;
DELETE FROM Resultat;
DELETE FROM Configuration_Poste;
DELETE FROM Joueur;
DELETE FROM Poste;
DELETE FROM Quiz;
DELETE FROM Theme;
DELETE FROM Media;

DELETE FROM Parcours;
DELETE FROM Statistique;


DELETE FROM Reponse;
DELETE FROM administrer;
DELETE FROM elaborer;
DELETE FROM posseder;
DELETE FROM contenir;
DELETE FROM inclure;
DELETE FROM effectuer;
DELETE FROM etre_affilier_à;
DELETE FROM consulter;
DELETE FROM generer;


-- Compte

INSERT INTO compte (idcompte, nom, prenom,date_naissance,motdepasse, email, flagadmin ) VALUES 
( 1, 'Kameni', 'Agnès', {d '2004-08-31' },'kameni', 'kameni@jfox.fr', TRUE ),
( 2, 'Feune', 'Aaron',{d '2004-02-11' },'feune', 'feune@jfox.fr', FALSE );

ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 3;

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



--Resultat
INSERT INTO Resultat(idresultat,heure,score) VALUES
(1,'10:00:00', 85),
(2,'11:00:00', 90),
(3,'12:00:00', 95);




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


--Theme
INSERT INTO Theme (idtheme,nom, description) VALUES
(1,'Connaissance générale', 'Questions sur divers sujets de culture générale.'),
(2,'Cycles de l’eau', 'Questions sur les cycles de l’eau, naturel et artificiel.'),
(3,'Faune / Flore', 'Questions sur les animaux et les plantes.'),
(4,'Les milieux et habitats', 'Questions sur les différents milieux et habitats naturels.'),
(5,'Consommation, économie et bons gestes', 'Questions sur la consommation, l’économie et les gestes éco-responsables.');

--Media
INSERT INTO Media (titre, description) VALUES
('Son de la mer', 'Un son de la mer pour détendre.');

--Joueur
--INSERT INTO Joueur (ville, categorie, idposte) VALUES
--('Limoges', 'Cycle 1 et 2', 3),
--('Limoges', 'Cycle 3', 3),
--('Feytiat', 'Gd Public', 3);


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


--Configuration_Poste
--INSERT INTO Configuration_Poste (heure, idposte) VALUES
--('09:00:00', 1),
--('10:00:00', 2),
--('11:00:00', 2);

--Reponse
INSERT INTO Reponse (libelle, vraie) VALUES 
('5', TRUE),
('3', FALSE),
('7', FALSE),
('Transparente', TRUE),
('Bleue', FALSE),
('Verte', FALSE),
('Dans les rivières', TRUE),
('Dans les océans', FALSE),
('Dans les glaciers', FALSE),
('Pour grandir', TRUE),
('Pour bouger', FALSE),
('Pour dormir', FALSE),
('Non', TRUE),
('Oui', FALSE),
('Seulement après filtration', FALSE),
('Océan Pacifique', TRUE),
('Océan Atlantique', FALSE),
('Océan Indien', FALSE),
('Pluie', TRUE),
('Neige', FALSE),
('Grêle', FALSE),
('Les glaciers', TRUE),
('Les océans', FALSE),
('Les lacs', FALSE),
('Solide', TRUE),
('Liquide', FALSE),
('Gazeux', FALSE),
('3%', TRUE),
('50%', FALSE),
('97%', FALSE),
('Le cycle de l’eau', TRUE),
('Le cycle du carbone', FALSE),
('Le cycle de l’azote', FALSE),
('Le Nil', TRUE),
('L’Amazone', FALSE),
('Le Mississippi', FALSE),
('H2O', TRUE),
('O2', FALSE),
('CO2', FALSE),
('CO2', TRUE),
('O2', FALSE),
('N2', FALSE),
('Pluie', TRUE),
('Neige', FALSE),
('Grêle', FALSE),
('Elle s’évapore', TRUE),
('Elle gèle', FALSE),
('Elle devient salée', FALSE),
('Pour grandir', TRUE),
('Pour respirer', FALSE),
('Pour se déplacer', FALSE),
('Dans les rivières', TRUE),
('Dans les nuages', FALSE),
('Dans le sable', FALSE),
('Elle gèle', TRUE),
('Elle s’évapore', FALSE),
('Elle bouillonne', FALSE),
('Évaporation', TRUE),
('Condensation', FALSE),
('Sublimation', FALSE),
('Nappe phréatique', TRUE),
('Eau de surface', FALSE),
('Eau de pluie', FALSE),
('Ils donnent de la pluie', TRUE),
('Ils absorbent l’eau', FALSE),
('Ils stockent de l’eau', FALSE),
('Elles reçoivent constamment de l’eau', TRUE),
('Elles sont artificielles', FALSE),
('Elles sont gelées', FALSE),
('Par leurs feuilles', TRUE),
('Par leurs racines', FALSE),
('Par leurs fruits', FALSE),
('Infiltration', TRUE),
('Évaporation', FALSE),
('Transpiration', FALSE),
('À cause de l’altitude', TRUE),
('À cause des forêts', FALSE),
('À cause des rivières', FALSE),
('Percolation', TRUE),
('Condensation', FALSE),
('Précipitation', FALSE),
('Cycle de l’eau potable', TRUE),
('Cycle de l’eau usée', FALSE),
('Cycle de l’eau naturelle', FALSE),
('Ils contrôlent le débit', TRUE),
('Ils nettoient l’eau', FALSE),
('Ils évaporent l’eau', FALSE),
('Baleine bleue', TRUE),
('Grand requin blanc', FALSE),
('Calamar géant', FALSE),
('Nénuphar', TRUE),
('Algue', FALSE),
('Roseau', FALSE),
('Poisson migrateur', TRUE),
('Poisson d’eau douce', FALSE),
('Poisson de récif', FALSE),
('Héron', TRUE),
('Pigeon', FALSE),
('Moineau', FALSE),
('Pour pondre des œufs', TRUE),
('Pour se nourrir', FALSE),
('Pour hiberner', FALSE),
('Le blanchiment des coraux', TRUE),
('Les requins', FALSE),
('Les méduses', FALSE),
('Castor', TRUE),
('Loutre', FALSE),
('Canard', FALSE),
('Gerris', TRUE),
('Abeille', FALSE),
('Fourmi', FALSE),
('Pour pondre des œufs', TRUE),
('Pour se nourrir', FALSE),
('Pour se protéger', FALSE),
('Aucune', TRUE),
('Tilapia', FALSE),
('Saumon', FALSE),
('En réduisant l’érosion', TRUE),
('En fournissant de la nourriture', FALSE),
('En produisant de l’oxygène', FALSE),
('Elles sont sensibles à la pollution', TRUE),
('Elles mangent des insectes', FALSE),
('Elles vivent longtemps', FALSE),
('Baleine bleue', TRUE),
('Orque', FALSE),
('Dauphin', FALSE),
('Elle cause l’ingestion et l’enchevêtrement', TRUE),
('Elle augmente la nourriture disponible', FALSE),
('Elle améliore la qualité de l’eau', FALSE),
('Élodée', TRUE),
('Nénuphar', FALSE),
('Cresson', FALSE),
('Marécage', TRUE),
('Étang', FALSE),
('Ruisseau', FALSE),
('Aux pôles', TRUE),
('Dans les déserts', FALSE),
('Sous terre', FALSE),
('Elles filtrent l’eau', TRUE),
('Elles produisent du bois', FALSE),
('Elles créent de l’ombre', FALSE),
('Forêt riparienne', TRUE),
('Forêt tropicale', FALSE),
('Forêt boréale', FALSE),
('Eau douce', TRUE),
('Eau salée', FALSE),
('Eau minérale', FALSE),
('Estuaire', TRUE),
('Marais', FALSE),
('Lagon', FALSE),
('Elles abritent de nombreuses espèces', TRUE),
('Elles produisent beaucoup d’oxygène', FALSE),
('Elles sont faciles à cultiver', FALSE),
('Par l’érosion', TRUE),
('Par la sédimentation', FALSE),
('Par la pollution', FALSE),
('Les côtes tropicales', TRUE),
('Les montagnes', FALSE),
('Les déserts', FALSE),
('Elles enrichissent le sol en nutriments', TRUE),
('Elles protègent contre les tempêtes', FALSE),
('Elles augmentent la température', FALSE);

