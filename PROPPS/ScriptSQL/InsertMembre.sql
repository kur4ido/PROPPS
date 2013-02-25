
INSERT INTO Adresse(sAdresse,sVille,sCodePostal,sPays)
VALUES("Adresse1","Ville1","CodePostal1","Pays1");


INSERT INTO Utilisateur(sNom,sPrenom,sEmail,sPassword,ID_Adresse)
VALUES("Nom1","Prenom1","Email1@yahoo.fr",SHA1("Password1"),@@Identity);


INSERT INTO Membre(ID_Utilisateur,ID_Profil,dtFinPresta,bPresta,bContrat)
VALUES (@@IDENTITY,1,NULL,FALSE,FALSE);



INSERT INTO Adresse(sAdresse,sVille,sCodePostal,sPays)
VALUES("Adresse2","Ville2","CodePostal2","Pays2");


INSERT INTO Utilisateur(sNom,sPrenom,sEmail,sPassword,ID_Adresse)
VALUES("Nom2","Prenom2","Email2@gmail.com",SHA1("Password2"),@@IDENTITY);


INSERT INTO Membre(ID_Utilisateur,ID_Profil,dtFinPresta,bPresta,bContrat)
VALUES (@@IDENTITY,1,NULL,FALSE,TRUE);



INSERT INTO Adresse(sAdresse,sVille,sCodePostal,sPays)
VALUES("Adresse3","Ville3","CodePostal3","Pays3");


INSERT INTO Utilisateur(sNom,sPrenom,sEmail,sPassword,ID_Adresse)
VALUES("Nom3","Prenom3","Email3@lycos.fr",SHA1("Password3"),@@IDENTITY);


INSERT INTO Membre(ID_Utilisateur,ID_Profil,dtFinPresta,bPresta,bContrat)
VALUES (@@IDENTITY,1,"2013-09-09",TRUE,FALSE);


INSERT INTO Adresse(sAdresse,sVille,sCodePostal,sPays)
VALUES("Adresse4","Ville4","CodePostal4","Pays4");



INSERT INTO Utilisateur(sNom,sPrenom,sEmail,sPassword,ID_Adresse)
VALUES("Nom4","Prenom4","Email4@aol.fr",SHA1("Password4"),@@IDENTITY);


INSERT INTO Membre(ID_Utilisateur,ID_Profil,dtFinPresta,bPresta,bContrat)
VALUES (@@IDENTITY,2,NULL,FALSE,FALSE);


