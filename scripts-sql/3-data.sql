
-- Supprime toutes les données

DELETE FROM compte;


-- Compte

INSERT INTO compte (idcompte, nom, prenom,date_naissance,motdepasse, email, flagadmin ) VALUES 
( 1, 'Kameni', 'Agnès', {d '2004-08-31' },'kameni', 'kameni@jfox.fr', TRUE ),
( 2, 'Feune', 'Aaron',{d '2004-02-11' },'feune', 'feune@jfox.fr', FALSE );

ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 3;

