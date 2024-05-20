
-- Supprime toutes les données

DELETE FROM compte;
DELETE FROM Astuce;
DELETE FROM Resultat;
DELETE FROM Poste;
DELETE FROM Question;
DELETE FROM Theme;
DELETE FROM Media;
DELETE FROM Joueur;



-- Compte

INSERT INTO compte (idcompte, nom, prenom,date_naissance,motdepasse, email, flagadmin ) VALUES 
( 1, 'Kameni', 'Agnès', {d '2004-08-31' },'kameni', 'kameni@jfox.fr', TRUE ),
( 2, 'Feune', 'Aaron',{d '2004-02-11' },'feune', 'feune@jfox.fr', FALSE );

ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 3;

--Astuce
INSERT INTO Astuce (libelle) VALUES
('Utilisez la recherche pour trouver des réponses plus rapidement'),
('Préparez-vous avant de commencer le quiz');

--Resultat
INSERT INTO Resultat(idresultat,heure,score) VALUES
(1,'10:00:00', 85),
(2,'11:00:00', 90),
(3,'12:00:00', 95);


--Poste
INSERT INTO Poste (idposte,libelle) VALUES
(1,'Administrateur'),
(2,'Utilisateur');

--Question
INSERT INTO Question (idquestion,enonce, explication, idastuce) VALUES
(1,'Quel est le plus grand océan?', 'L''océan Pacifique est le plus grand océan.', 2);

--Theme
INSERT INTO Theme (nom, description) VALUES
('Histoire', 'Questions sur la population.'),
('Science', 'Questions sur la science et la technologie.'),
('Culture', 'Questions sur la culture générale.');

--Media
INSERT INTO Media (titre, description) VALUES
('Son de la mer', 'Un son de la mer pour détendre.');

--Joueur
INSERT INTO Joueur (ville, categorie, idposte) VALUES
('Limoges', 'Expert', 2),
('Limoges', 'Débutant', 2),
('Feytiat', 'Intermédiaire', 2);


--Parcours
INSERT INTO Parcours (mode) VALUES
('Facile'),
('Difficile'),
('Intervalle');

--Statistique
INSERT INTO Statistique (libelle) VALUES
('Nombre de questions posées'),
('Score moyen par joueur'),
('Temps moyen par question');

--Quizz
INSERT INTO Quiz (description, idtheme) VALUES
('Quiz général culturel', 1),
('Quiz scientifique', 2),
('Quiz historique', 3);

--Configuration_Poste
INSERT INTO Configuration_Poste (heure, idposte) VALUES
('09:00:00', 1),
('10:00:00', 2),
('11:00:00', 2);

--Reponse
INSERT INTO Reponse (libelle, vraie, idquestion) VALUES
('Limoges', FALSE, 1),
('Limoges', TRUE, 1);

--administrer
INSERT INTO administrer (idcompte, idposte) VALUES
(1, 1),
(2, 2);

--elaborer
INSERT INTO elaborer (idcompte, idconf) VALUES
(1, 1),
(2, 2), 
(1, 2);

--posseder
INSERT INTO posseder (idquizz, idresultat) VALUES
(1, 1), 
(2, 2), 
(3, 3);

INSERT INTO contenir (idquizz, idquestion) VALUES
(1, 1), 
(2, 1); 

INSERT INTO inclure (idquestion, idmedia) VALUES
(1, 1);

INSERT INTO effectuer (idquizz, idjoueur) VALUES
(1, 1), 
(2, 2);

INSERT INTO etre_affilier_à (idquizz, idparcours) VALUES
(1, 1),
(2, 2);

INSERT INTO consulter (idcompte, idstat) VALUES
(1, 1),
(2, 2);

INSERT INTO generer (idresultat, idstat) VALUES
(1, 1),
(2, 2);