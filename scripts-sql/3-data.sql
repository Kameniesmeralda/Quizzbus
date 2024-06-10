-- Clean up all data
DELETE FROM compte;
DELETE FROM Resultat;
DELETE FROM Joueur;
DELETE FROM Poste;
DELETE FROM Configuration_Poste;
DELETE FROM Quiz;
DELETE FROM Question;
DELETE FROM avoir;
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
INSERT INTO compte (idcompte, nom, prenom, date_naissance, motdepasse, email, flagAdmin, ville, categorie) VALUES 
(1, 'Kameni', 'Agnès', '2004-08-31', 'kameni', 'kameni@jfox.fr', TRUE, 'bordeaux', 'mineur'),
(2, 'Feune', 'Aaron', '2004-02-11', 'feune', 'feune@jfox.fr', FALSE, 'lille', 'adulte'),
(3, 'Nkamdoum', 'Anaelle', '2004-02-11', 'anaelle', 'anaelle@jfox.fr', FALSE, 'paris', 'adulte'),
(4, 'Melong', 'Leina', '2004-02-11', 'leina', 'leina@jfox.fr', FALSE, 'lyon', 'adulte');

ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 3;

-- Theme
INSERT INTO Theme (idtheme, nom, description) VALUES
(1, 'Connaissance générale', 'Questions sur divers sujets de culture générale.'),
(2, 'Cycles de l’eau', 'Questions sur les cycles de l’eau, naturel et artificiel.'),
(3, 'Faune / Flore', 'Questions sur les animaux et les plantes.'),
(4, 'Les milieux et habitats', 'Questions sur les différents milieux et habitats naturels.'),
(5, 'Consommation, économie et bons gestes', 'Questions sur la consommation, l’économie et les gestes éco-responsables.');

-- Resultat
INSERT INTO Resultat(idresultat, heure, score) VALUES
(1, '10:00:00', 85),
(2, '11:00:00', 90),
(3, '12:00:00', 95);

-- Insert into Quiz before Question
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

-- Question
INSERT INTO Question (enonce, astuce, reponse1, reponse2, reponse3, reponse4, reponse, idquizz) VALUES 
('Combien y a-t-il d’océans sur Terre ?', 'devine', '3', '4', '5', '6', 4, 1),
('Quelle est la couleur de l’eau ?', 'devine', 'Blanc', 'Rouge', 'Bleu', 'Vert', 3, 2),
('dd', 'devine', 's', 'c', 'q', 'e', 0, 3);

-- Relation theme et question (classe avoir)
INSERT INTO avoir (idquestion, idtheme) VALUES 
(1, 1),
(2, 1),
(3, 1),
(3, 4);

-- Parcours
INSERT INTO Parcours(mode) VALUES
('mode aventure'),
('mode classique');
