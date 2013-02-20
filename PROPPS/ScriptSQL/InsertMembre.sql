
INSERT INTO Adresse(sAdresse,sVille,sCodePostal,sPays)
VALUES("Adresse1","Ville1","CodePostal1","Pays1");# 1 ligne affectée.


INSERT INTO Utilisateur(sNom,sPrenom,sEmail,sPassword,ID_Adresse)
VALUES("Nom1","Prenom1","Email1@lol.fr","Password1",@@Identity);# 1 ligne affectée.


INSERT INTO Membre(ID_Utilisateur,ID_Profil,dtFinPresta,bPresta,bContrat)
VALUES (@@IDENTITY,1,NULL,FALSE,FALSE);# 1 ligne affectée.



INSERT INTO Adresse(sAdresse,sVille,sCodePostal,sPays)
VALUES("Adresse2","Ville2","CodePostal2","Pays2");# 1 ligne affectée.


SET @varID_Adresse = @@IDENTITY;# MySQL a retourné un résultat vide (aucune ligne).


INSERT INTO Utilisateur(sNom,sPrenom,sEmail,sPassword,ID_Adresse)
VALUES("Nom2","Prenom2","Email2@lol.fr","Password2",@@IDENTITY);# 1 ligne affectée.


INSERT INTO Membre(ID_Utilisateur,ID_Profil,dtFinPresta,bPresta,bContrat)
VALUES (@@IDENTITY,1,NULL,FALSE,TRUE);# 1 ligne affectée.



INSERT INTO Adresse(sAdresse,sVille,sCodePostal,sPays)
VALUES("Adresse3","Ville3","CodePostal3","Pays3");# 1 ligne affectée.


INSERT INTO Utilisateur(sNom,sPrenom,sEmail,sPassword,ID_Adresse)
VALUES("Nom3","Prenom3","Email3@lol.fr","Password3",@@IDENTITY);# 1 ligne affectée.


INSERT INTO Membre(ID_Utilisateur,ID_Profil,dtFinPresta,bPresta,bContrat)
VALUES (@@IDENTITY,1,"2013-09-09",TRUE,FALSE);# 1 ligne affectée.


INSERT INTO Adresse(sAdresse,sVille,sCodePostal,sPays)
VALUES("Adresse4","Ville4","CodePostal4","Pays4");# 1 ligne affectée.



INSERT INTO Utilisateur(sNom,sPrenom,sEmail,sPassword,ID_Adresse)
VALUES("Nom4","Prenom4","Email4","Password4",@@IDENTITY);# 1 ligne affectée.


INSERT INTO Membre(ID_Utilisateur,ID_Profil,dtFinPresta,bPresta,bContrat)
VALUES (@@IDENTITY,2,NULL,FALSE,FALSE);# 1 ligne affectée.
