declare @varID_Adresse INT DEFAULT -1;
declare @varID_Utilisateur INT DEFAULT -1;

INSERT INTO Adresse(sAdresse,sVille,sCodePostal,sPays)
VALUES("Adresse1","Ville1","CodePostal1","Pays1");

SET @varID_Adresse = @@IDENTITY;

INSERT INTO Utilisateur(sNom,sPrenom,sEmail,sPassword,@@Identity)
VALUES("Nom1","Prenom1","Email1@lol.fr","Password1");

INSERT INTO Membre(ID_Utilisateur,ID_Profil,dtFinPresta,bPresta,bContrat)
VALUES (@@IDENTITY,1,NULL,FALSE,FALSE);

--Membre 2
INSERT INTO Adresse(sAdresse,sVille,sCodePostal,sPays)
VALUES("Adresse2","Ville2","CodePostal2","Pays2");

SET @varID_Adresse = @@IDENTITY;

INSERT INTO Utilisateur(sNom,sPrenom,sEmail,sPassword,@@Identity)
VALUES("Nom2","Prenom2","Email2@lol.fr","Password2");

INSERT INTO Membre(ID_Utilisateur,ID_Profil,dtFinPresta,bPresta,bContrat)
VALUES (@@IDENTITY,1,NULL,FALSE,TRUE);

--Membre 3

INSERT INTO Adresse(sAdresse,sVille,sCodePostal,sPays)
VALUES("Adresse3","Ville3","CodePostal3","Pays3");

SET @varID_Adresse = @@IDENTITY;

INSERT INTO Utilisateur(sNom,sPrenom,sEmail,sPassword,@@Identity)
VALUES("Nom3","Prenom3","Email3@lol.fr","Password3");

INSERT INTO Membre(ID_Utilisateur,ID_Profil,dtFinPresta,bPresta,bContrat)
VALUES (@@IDENTITY,1,"2013-09-09",TRUE,FALSE);

--Membre 4

INSERT INTO Adresse(sAdresse,sVille,sCodePostal,sPays)
VALUES("Adresse4","Ville4","CodePostal4","Pays4");

SET @varID_Adresse = @@IDENTITY;

INSERT INTO Utilisateur(sNom,sPrenom,sEmail,sPassword,@@Identity)
VALUES("Nom4","Prenom4","Email4","Password4");

INSERT INTO Membre(ID_Utilisateur,ID_Profil,dtFinPresta,bPresta,bContrat)
VALUES (@@IDENTITY,2,NULL,FALSE,FALSE);