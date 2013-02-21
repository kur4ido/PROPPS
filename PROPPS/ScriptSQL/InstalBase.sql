SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `PROPPS_DB` DEFAULT CHARACTER SET utf8 ;
USE `PROPPS_DB` ;

-- -----------------------------------------------------
-- Table `PROPPS_DB`.`Adresse`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `PROPPS_DB`.`Adresse` (
  `ID_Adresse` INT NOT NULL AUTO_INCREMENT ,
  `sVille` VARCHAR(250) NULL ,
  `sCodePostal` VARCHAR(250) NULL ,
  `sPays` VARCHAR(250) NULL ,
  `sAdresse` VARCHAR(250) NULL ,
  PRIMARY KEY (`ID_Adresse`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PROPPS_DB`.`Utilisateur`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `PROPPS_DB`.`Utilisateur` (
  `ID_Utilisateur` INT NOT NULL AUTO_INCREMENT ,
  `ID_Adresse` INT NOT NULL ,
  `sNom` VARCHAR(250) NOT NULL ,
  `sPrenom` VARCHAR(250) NOT NULL ,
  `sEmail` VARCHAR(250) NOT NULL ,
  `sPassword` VARCHAR(40) NOT NULL ,
  PRIMARY KEY (`ID_Utilisateur`) ,
  INDEX `fk_Utilisateur_Adresse1` (`ID_Adresse` ASC) ,
  UNIQUE INDEX `sEmail_UNIQUE` (`sEmail` ASC) ,
  CONSTRAINT `fk_Utilisateur_Adresse1`
    FOREIGN KEY (`ID_Adresse` )
    REFERENCES `PROPPS_DB`.`Adresse` (`ID_Adresse` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PROPPS_DB`.`Profil`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `PROPPS_DB`.`Profil` (
  `ID_Profil` INT NOT NULL AUTO_INCREMENT ,
  `sProfil` VARCHAR(250) NOT NULL ,
  PRIMARY KEY (`ID_Profil`) ,
  UNIQUE INDEX `sProfil_UNIQUE` (`sProfil` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PROPPS_DB`.`Membre`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `PROPPS_DB`.`Membre` (
  `ID_Utilisateur` INT NOT NULL ,
  `ID_Profil` INT NULL ,
  `dtFinPresta` DATETIME NULL ,
  `bContrat` TINYINT(1) NULL ,
  `bPresta` TINYINT(1) NULL ,
  PRIMARY KEY (`ID_Utilisateur`) ,
  INDEX `fk_Membre_Profil1` (`ID_Profil` ASC) ,
  CONSTRAINT `fk_Membre_Utilisateur`
    FOREIGN KEY (`ID_Utilisateur` )
    REFERENCES `PROPPS_DB`.`Utilisateur` (`ID_Utilisateur` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Membre_Profil1`
    FOREIGN KEY (`ID_Profil` )
    REFERENCES `PROPPS_DB`.`Profil` (`ID_Profil` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PROPPS_DB`.`DomaineExpertise`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `PROPPS_DB`.`DomaineExpertise` (
  `ID_Domaine` INT NOT NULL AUTO_INCREMENT ,
  `sDomaine` VARCHAR(250) NOT NULL ,
  `sType` VARCHAR(250) NOT NULL ,
  PRIMARY KEY (`ID_Domaine`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PROPPS_DB`.`ExpertiseMembre`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `PROPPS_DB`.`ExpertiseMembre` (
  `ID_Domaine` INT NOT NULL ,
  `ID_Utilisateur` INT NOT NULL ,
  PRIMARY KEY (`ID_Domaine`, `ID_Utilisateur`) ,
  INDEX `fk_DomaineExpertise_has_Membre_Membre1` (`ID_Utilisateur` ASC) ,
  INDEX `fk_DomaineExpertise_has_Membre_DomaineExpertise1` (`ID_Domaine` ASC) ,
  CONSTRAINT `fk_DomaineExpertise_has_Membre_DomaineExpertise1`
    FOREIGN KEY (`ID_Domaine` )
    REFERENCES `PROPPS_DB`.`DomaineExpertise` (`ID_Domaine` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DomaineExpertise_has_Membre_Membre1`
    FOREIGN KEY (`ID_Utilisateur` )
    REFERENCES `PROPPS_DB`.`Membre` (`ID_Utilisateur` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PROPPS_DB`.`Societe`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `PROPPS_DB`.`Societe` (
  `ID_Societe` INT NOT NULL AUTO_INCREMENT ,
  `sNom` VARCHAR(250) NOT NULL ,
  PRIMARY KEY (`ID_Societe`) ,
  UNIQUE INDEX `sNom_UNIQUE` (`sNom` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PROPPS_DB`.`ExperiencePro`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `PROPPS_DB`.`ExperiencePro` (
  `ID_ExperiencePro` INT NOT NULL AUTO_INCREMENT ,
  `dtDebut` DATETIME NOT NULL ,
  `dtFin` DATETIME NULL ,
  `sDirection` VARCHAR(250) NOT NULL ,
  `sPosteOccupe` VARCHAR(250) NOT NULL ,
  `sDescription` TEXT NULL ,
  `ID_Profil` INT NOT NULL ,
  `ID_Membre` INT NOT NULL ,
  `ID_Societe` INT NOT NULL ,
  PRIMARY KEY (`ID_ExperiencePro`) ,
  INDEX `fk_ExperiencePro_Profil1` (`ID_Profil` ASC) ,
  INDEX `fk_ExperiencePro_Membre1` (`ID_Membre` ASC) ,
  INDEX `fk_ExperiencePro_Societe1` (`ID_Societe` ASC) ,
  CONSTRAINT `fk_ExperiencePro_Profil1`
    FOREIGN KEY (`ID_Profil` )
    REFERENCES `PROPPS_DB`.`Profil` (`ID_Profil` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ExperiencePro_Membre1`
    FOREIGN KEY (`ID_Membre` )
    REFERENCES `PROPPS_DB`.`Membre` (`ID_Utilisateur` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ExperiencePro_Societe1`
    FOREIGN KEY (`ID_Societe` )
    REFERENCES `PROPPS_DB`.`Societe` (`ID_Societe` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PROPPS_DB`.`DomaineExperiencePro`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `PROPPS_DB`.`DomaineExperiencePro` (
  `ID_ExperiencePro` INT NOT NULL ,
  `ID_Domaine` INT NOT NULL ,
  PRIMARY KEY (`ID_ExperiencePro`, `ID_Domaine`) ,
  INDEX `fk_ExperiencePro_has_DomaineExpertise_DomaineExpertise1` (`ID_Domaine` ASC) ,
  INDEX `fk_ExperiencePro_has_DomaineExpertise_ExperiencePro1` (`ID_ExperiencePro` ASC) ,
  CONSTRAINT `fk_ExperiencePro_has_DomaineExpertise_ExperiencePro1`
    FOREIGN KEY (`ID_ExperiencePro` )
    REFERENCES `PROPPS_DB`.`ExperiencePro` (`ID_ExperiencePro` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ExperiencePro_has_DomaineExpertise_DomaineExpertise1`
    FOREIGN KEY (`ID_Domaine` )
    REFERENCES `PROPPS_DB`.`DomaineExpertise` (`ID_Domaine` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PROPPS_DB`.`Recruteur`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `PROPPS_DB`.`Recruteur` (
  `ID_Utilisateur` INT NOT NULL ,
  `ID_Societe` INT NOT NULL ,
  PRIMARY KEY (`ID_Utilisateur`) ,
  INDEX `fk_Recruteur_Societe1` (`ID_Societe` ASC) ,
  CONSTRAINT `fk_Recruteur_Utilisateur1`
    FOREIGN KEY (`ID_Utilisateur` )
    REFERENCES `PROPPS_DB`.`Utilisateur` (`ID_Utilisateur` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Recruteur_Societe1`
    FOREIGN KEY (`ID_Societe` )
    REFERENCES `PROPPS_DB`.`Societe` (`ID_Societe` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PROPPS_DB`.`Administrateur`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `PROPPS_DB`.`Administrateur` (
  `ID_Admin` INT NOT NULL AUTO_INCREMENT ,
  `sEmail` VARCHAR(250) NOT NULL ,
  `nHashPW` INT NULL ,
  UNIQUE INDEX `sEmail_UNIQUE` (`sEmail` ASC) ,
  PRIMARY KEY (`ID_Admin`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PROPPS_DB`.`Notification`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `PROPPS_DB`.`Notification` (
  `ID_Notification` INT NOT NULL AUTO_INCREMENT ,
  `dtNotif` DATETIME NOT NULL ,
  `bVuDest` TINYINT(1) NOT NULL ,
  `bVuSource` TINYINT(1) NOT NULL ,
  `bAccept` TINYINT(1) NOT NULL ,
  `ID_Source` INT NOT NULL ,
  `ID_Destinataire` INT NOT NULL ,
  PRIMARY KEY (`ID_Notification`) ,
  INDEX `fk_Notifications_Membre1` (`ID_Source` ASC) ,
  INDEX `fk_Notifications_Membre2` (`ID_Destinataire` ASC) ,
  CONSTRAINT `fk_Notifications_Membre1`
    FOREIGN KEY (`ID_Source` )
    REFERENCES `PROPPS_DB`.`Membre` (`ID_Utilisateur` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Notifications_Membre2`
    FOREIGN KEY (`ID_Destinataire` )
    REFERENCES `PROPPS_DB`.`Membre` (`ID_Utilisateur` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PROPPS_DB`.`Contact`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `PROPPS_DB`.`Contact` (
  `ID_Membre` INT NOT NULL ,
  `ID_Contact` INT NOT NULL ,
  `dtRelation` DATETIME NOT NULL ,
  PRIMARY KEY (`ID_Membre`, `ID_Contact`) ,
  INDEX `fk_Membre_has_Membre_Membre2` (`ID_Contact` ASC) ,
  INDEX `fk_Membre_has_Membre_Membre1` (`ID_Membre` ASC) ,
  CONSTRAINT `fk_Membre_has_Membre_Membre1`
    FOREIGN KEY (`ID_Membre` )
    REFERENCES `PROPPS_DB`.`Membre` (`ID_Utilisateur` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Membre_has_Membre_Membre2`
    FOREIGN KEY (`ID_Contact` )
    REFERENCES `PROPPS_DB`.`Membre` (`ID_Utilisateur` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PROPPS_DB`.`Message`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `PROPPS_DB`.`Message` (
  `ID_Message` INT NOT NULL ,
  `ID_Utilisateur` INT NOT NULL ,
  `dtMessage` DATETIME NOT NULL ,
  `sMessage` TEXT NOT NULL ,
  PRIMARY KEY (`ID_Message`) ,
  INDEX `fk_Messages_Utilisateur1` (`ID_Utilisateur` ASC) ,
  CONSTRAINT `fk_Messages_Utilisateur1`
    FOREIGN KEY (`ID_Utilisateur` )
    REFERENCES `PROPPS_DB`.`Utilisateur` (`ID_Utilisateur` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- procedure Utilisateur_insertOrUpdate
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`Utilisateur_insertOrUpdate`(IN _ID_Utilisateur INT, IN _sNom VARCHAR(250),IN _sPrenom VARCHAR(250),
    IN _sEmail VARCHAR(250), IN _sPassword VARCHAR(250),IN _sVille VARCHAR(250), IN _sCodePostal VARCHAR(250),
    IN _sAdresse VARCHAR(250), IN _sPays VARCHAR(250))
BEGIN
    declare varID_Adresse INT DEFAULT -1;

    IF (_ID_Utilisateur < 0) THEN
        INSERT INTO Adresse(sVille,sCodePostal,sPays,sAdresse)
        VALUES (_sVille,_sCodePostal,_sPays,_sAdresse);
        
        SET varID_Adresse = @@IDENTITY;

        INSERT INTO Utilisateur(sNom,sPrenom,sEmail,sPassword,ID_Adresse)
        VALUES(_sNom,_sPrenom,_sEmail,SHA1(_sPassword),varID_Adresse);
        
        SELECT @@IDENTITY as ID_Utilisateur, varID_Adresse as ID_Adresse;
    ELSE
        UPDATE Utilisateur
        SET sPrenom = _sPrenom,
            sNom = _sNom,
            sEmail = _sEmail
        WHERE ID_Utilisateur = _ID_Utilisateur;

        SET varID_Adresse = (SELECT ID_Adresse FROM Utilisateur WHERE ID_Utilisateur = _ID_Utilisateur);
        
        UPDATE Adresse
        SET sVille = _sVille,
            sCodePostal = _sCodePostal,
            sAdresse = _sAdresse,
            sPays = _sPays
        WHERE ID_Adresse = varID_Adresse;

        SELECT _ID_Utilisateur as ID_Utilisateur;
    END IF;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Membre_ajouterExpertise
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$
CREATE PROCEDURE `PROPPS_DB`.`Membre_ajouterExpertise` (IN _ID_Utilisateur INT, IN _ID_Domaine INT)

BEGIN
    DELETE
    FROM ExpertiseMembre
    WHERE ID_Utilisateur = _ID_Utilisateur AND ID_Domaine = _ID_Domaine;

    INSERT INTO ExpertiseMembre(ID_Utilisateur,ID_Domaine)
    VALUES (_ID_Utilisateur,_ID_Domaine);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Membre_getByID
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$
CREATE PROCEDURE `PROPPS_DB`.`Membre_getByID` (IN _ID_Utilisateur INT)
BEGIN
    declare varNbExp INT;
    SET varNbExp = (SELECT Count(*) FROM ExperiencePro WHERE ID_Membre = _ID_Utilisateur);

    SELECT M.*,U.*, A.*,E.ID_ExperiencePro,varNbExp as nbExp
    FROM Membre as M inner join Utilisateur as U on M.ID_Utilisateur = U.ID_Utilisateur
        inner join Adresse as A on U.ID_Adresse = A.ID_Adresse 
        left outer join ExperiencePro as E on E.ID_Membre = M.ID_Utilisateur
    WHERE M.ID_Utilisateur = _ID_Utilisateur;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Recruteur_insertOrUpdate
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`Recruteur_insertOrUpdate` (IN _ID_Utilisateur INT, IN _ID_Societe INT)
BEGIN
    DELETE FROM Recruteur WHERE ID_Utilisateur = _ID_Utilisateur;
    INSERT INTO Recruteur (`ID_Utilisateur`,`ID_Societe`)
    VALUES(_ID_Utilisateur,_ID_Societe);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Societe_insertOrUpdate
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`Societe_insertOrUpdate` (IN _ID_Societe INT, IN _sNom VARCHAR(250))
BEGIN
    IF(_ID_Societe = -1) THEN
        INSERT INTO Societe(sNom)
        VALUES (_sNom);
        SELECT * FROM Societe WHERE ID_Societe = @@IDENTITY;
    ELSE
        UPDATE Societe
        SET sNom = _sNom
        WHERE ID_Societe = _ID_Societe;
        SELECT * FROM Societe WHERE ID_Societe = _ID_Societe;
    END IF;
    
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Societe_getAll
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`Societe_getAll` ()
BEGIN
    SELECT *
    FROM Societe;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Membre_ajouterExperiencePro
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$
CREATE PROCEDURE `PROPPS_DB`.`Membre_ajouterExperiencePro` (IN _ID_Membre INT, IN _ID_Profil INT, IN _ID_Societe INT,
IN _dtDebut DATETIME, IN _dtFin DATETIME, IN _sDirection VARCHAR(250), IN _sPosteOccupe VARCHAR(250), IN _sDescription TEXT)
BEGIN
    IF (_dtDebut >= _dtFin) THEN
        SET _dtFin := NULL;
    END IF;
    INSERT INTO ExperiencePro(dtDebut,dtFin,sDirection,sPosteOccupe,sDescription,ID_Profil,ID_Membre,ID_Societe)
    VALUES(_dtDebut,_dtFin,_sDirection,_sPosteOccupe,sDescription,_ID_Profil,_ID_Membre,_ID_Societe);
    SELECT @@Identity as ID_ExperiencePro;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ExperiencePro_ajouterExpertise
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`ExperiencePro_ajouterExpertise` (IN _ID_ExperiencePro INT, IN _ID_Domaine INT)
BEGIN
    DELETE
    FROM DomaineExperiencePro
    WHERE ID_ExperiencePro = _ID_ExperiencePro AND ID_Domaine = _ID_Domaine;

    INSERT INTO DomaineExperiencePro(ID_ExperiencePro,ID_Domaine)
    VALUES (_ID_ExperiencePro,_ID_Domaine);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Membre_getContactByID
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`Membre_getContactByID` (IN _ID_Membre INT)
BEGIN
    SELECT Membre.*
    FROM Contact inner join Membre on Contact.ID_Contact = Membre.ID_Utilisateur
            inner join Utilisateur on Membre.ID_Utilisateur = Utilisateur.ID_Utilisateur
            inner join Adresse on Adresse.ID_Adresse = Utilisateur.ID_Utilisateur
    WHERE Contact.ID_Membre = _ID_Membre;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Membre_ajouterContact
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$
CREATE PROCEDURE `PROPPS_DB`.`Membre_ajouterContact` (IN _ID_Membre INT, IN _ID_Contact INT)
-- --------------------------------------------------------------------------------
-- Membre_ajouterContact
-- Permet d'effectuer un ajout de contact réciproque pour les deux membres passés en
-- paramètre.
-- --------------------------------------------------------------------------------
BEGIN
    #On évite le cas où la relation existe déjà
    DELETE
    FROM Contact
    WHERE ID_Contact = _ID_Contact AND ID_Membre = _ID_Membre;

    DELETE
    FROM Contact
    WHERE ID_Contact = _ID_Membre AND ID_Membre = _ID_Contact;

    INSERT  INTO Contact(ID_Membre,ID_Contact,dtRelation)
    VALUES (_ID_Membre,_ID_Contact,NOW()),(_ID_Contact,_ID_Membre,NOW());
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Notification_insertOrUpdate
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$
CREATE PROCEDURE `PROPPS_DB`.`Notification_insertOrUpdate` (IN _ID_Notification INT,IN _ID_Source INT, IN _ID_Destinataire INT,
                    IN _bVuSource BOOL, IN _bVuDest BOOL, IN _bAccept BOOL)
BEGIN
    IF(_ID_Notification < 0) THEN
        INSERT INTO Notification(ID_Source,ID_Destinataire,dtNotif,bVuSource,bVuDest,bAccept)
        VALUES(_ID_Source,_ID_Destinataire,NOW(),FALSE,FALSE,FALSE);

        SELECT * FROM Notification WHERE ID_Notification = @@Identity;
    ELSE
        UPDATE Notification
        SET    bVuSource = _bVuSource,
               bVuDest   = _bVuDest,
               bAccept   = _bAccept
        WHERE ID_Notification = _ID_Notification;

         SELECT * FROM Notification WHERE ID_Notification = _ID_Notification;
    END IF;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure rechercheContact
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`rechercheContact` (IN _ID_Membre INT, IN _sNom VARCHAR(250),IN _sPrenom VARCHAR(250),
    IN _sEmail VARCHAR(250), IN _ID_Societe INT)
BEGIN
    IF _ID_Societe = -1 THEN
        SELECT *
        FROM Membre
        WHERE not ID_Membre = _ID_Membre
            AND (_sEmail = '' OR sEmail = _sEmail)
            AND (_sNom = '' OR sNom like CONCAT('%',_sNom,'%'))
            AND (_sPrenom = '' OR sPrenom like CONCAT('%',_sPrenom,'%'));
    ELSE
        SELECT DISTINCT Membre.*
        FROM Membre INNER JOIN ExperiencePro on Membre.ID_Membre = ExperiencePro.ID_Membre
        WHERE not Membre.ID_Membre = _ID_Membre
            AND (_sEmail = '' OR sEmail = _sEmail)
            AND (_sNom = '' OR sNom like CONCAT('%',_sNom,'%'))
            AND (_sPrenom = '' OR sPrenom like CONCAT('%',_sPrenom,'%'))
            AND (_ID_Societe = ID_Societe)
        ORDER BY dtFin DESC;
    END IF;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Membre_getNotif
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$
CREATE PROCEDURE `PROPPS_DB`.`Membre_getNotif` (IN _ID_Membre INT, IN _bNotifRecues BOOL)
BEGIN
-- ----------------------------------------------------------------------------------
-- Membre_getNotif
-- Note: Permet d'obtenir pour un membre donné l'ensemble de ses notifications reçues
--      ou envoyées (Selon la valeur du booléen bNotifRecues
-- ----------------------------------------------------------------------------------
    IF _bNotifRecues THEN
        #Cas où l'on veut les notifications reçues
        SELECT *
        FROM Notification inner join Membre on Notification.ID_Source = Membre.ID_Utilisateur
            inner join Utilisateur on Membre.ID_Utilisateur = Utilisateur.ID_Utilisateur
            inner join Adresse on Utilisateur.ID_Adresse = Adresse.ID_Adresse
        WHERE ID_Destinataire = _ID_Membre
        ORDER BY dtNotif DESC;
    ELSE
        #Cas où l'on veut les notification envoyées
        SELECT *
        FROM Notification inner join Membre on Notification.ID_Destinataire = Membre.ID_Utilisateur
            inner join Utilisateur on Membre.ID_Utilisateur = Utilisateur.ID_Utilisateur 
            inner join Adresse on Utilisateur.ID_Adresse = Adresse.ID_Adresse
        WHERE ID_Source = _ID_Membre
        ORDER BY dtNotif DESC;
    END IF;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Membre_modifier
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`Membre_modifier` (IN _ID_Membre INT, IN _ID_Profil INT,IN _bPresta BOOL,
    IN _bEstSousContrat BOOL, IN _dtFinPresta DATETIME)

BEGIN
    IF _ID_Profil = -1 THEN
        SET _ID_Profil = NULL;
    END IF;

    UPDATE Membre
    SET ID_Profil = _ID_Profil,
        bPresta = _bPresta,
        bEstSousContrat = _bEstSousContrat,
        dtFinPresta = _dtFinPresta
    WHERE ID_Utilisateur = _ID_Membre;
    
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Utilisateur_modifierAdresse
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$




CREATE PROCEDURE `PROPPS_DB`.`Utilisateur_modifierAdresse` (IN _ID_Utilisateur INT, IN _sCodePostal VARCHAR(250),
    IN _sVille VARCHAR(250), IN _sPays VARCHAR(250), IN _sAdresse VARCHAR(250))
BEGIN
    declare vID_Adresse INT default -1;
    
    SET vID_Adresse = (SELECT ID_Adresse FROM Utilisateur WHERE ID_Utilisateur = _ID_Utilisateur);

    UPDATE Adresse
    SET sVille = _sVille,
        sCodePostal = _sCodePostal,
        sPays = _sPays,
        sAdresse = _sAdresse
    WHERE ID_Adresse = vID_Adresse;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Membre_getExpertiseByID
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`Membre_getExpertiseByID` (IN _ID_Membre INT)
BEGIN
    SELECT *
    FROM ExpertiseMembre inner join DomaineExpertise on ExpertiseMembre.ID_Domaine = DomaineExpertise.ID_Domaine
    WHERE ExpertiseMembre.ID_Utilisateur = _ID_Membre;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Membre_getExperienceProByID
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`Membre_getExperienceProByID` (IN _ID_Membre INT)
BEGIN
    SELECT *
    FROM ExperiencePro
    WHERE ID_Membre = _ID_Membre;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ExperiencePro_getByID
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$
CREATE PROCEDURE `PROPPS_DB`.`ExperiencePro_getByID` (IN _ID_ExperiencePro INT)
BEGIN
    declare varNbExpertise INT;
    SET varNbExpertise = (SELECT Count(*) FROM DomaineExperiencePro WHERE ID_ExperiencePro = _ID_ExperiencePro);
    
    SELECT E.*,D.*,varNbExpertise as NbExpertise
    FROM ExperiencePro as E left outer join DomaineExperiencePro as D on E.ID_ExperiencePro = D.ID_ExperiencePro
    WHERE E.ID_ExperiencePro = _ID_ExperiencePro;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Recruteur_getIDByLoginPW
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`Recruteur_getIDByLoginPW` (IN _sEmail VARCHAR(250),IN _sPassword VARCHAR(250))
BEGIN
    SELECT Recruteur.ID_Utilisateur
    FROM Utilisateur inner join Recruteur on Utilisateur.ID_Utilisateur = Recruteur.ID_Utilisateur
    WHERE sEmail = _sEmail AND sPassword = SHA1(_sPassword);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Membre_getIDByLoginPW
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`Membre_getIDByLoginPW` (IN _sEmail VARCHAR(250),IN _sPassword VARCHAR(250))
BEGIN
    SELECT Membre.ID_Utilisateur
    FROM Utilisateur inner join Membre on Utilisateur.ID_Utilisateur = Membre.ID_Utilisateur
    WHERE sEmail = _sEmail AND sPassword = SHA1(_sPassword);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Utilisateur_getUtilisateurByEmail
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`Utilisateur_getUtilisateurByEmail` (IN _sEmail VARCHAR(250))
BEGIN
    SELECT *
    FROM Utilisateur
    WHERE sEmail = _sEmail;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Recruteur_rechercher
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`Recruteur_rechercher` (IN _ID_Domaine INT, IN _ID_Profil INT,IN _bPresta BOOL,
IN _dtDispo DATETIME)
BEGIN
    IF _bPresta THEN
        SELECT M.*
        FROM Membre
        WHERE ID_Profil = _ID_Profil
            AND dtFinPresta < _dtDispo;
    ELSE
        SELECT M.*
        FROM Membre
        WHERE ID_Profil = _ID_Profil
            AND bPresta = _bPresta;
    END IF;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Societe_getByID
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`Societe_getByID` (IN _ID_Societe INT)
BEGIN
    SELECT *
    FROM Societe
    WHERE _ID_Societe = ID_Societe;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Membre_insertOrUpdate
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`Membre_insertOrUpdate` (IN _ID_Utilisateur INT, IN _ID_Profil INT,
IN _bContrat BOOL, IN _bPresta BOOL, IN _dtFinPresta DATETIME)
BEGIN
    DELETE FROM Membre WHERE ID_Utilisateur = _ID_Utilisateur;
    INSERT INTO Membre(ID_Utilisateur,ID_Profil,bContrat,bPresta,dtFinPresta)
    VALUES(_ID_Utilisateur,_ID_Profil,_bContrat,_bPresta,_dtFinPresta);
    
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Utilisateur_updatePassword
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`Utilisateur_updatePassword` (IN _ID_Utilisateur INT, IN _sPassword VARCHAR(255))
BEGIN
    declare varHash VARCHAR(40);
    SET varHash = SHA1(_sPassword);

    UPDATE Utilisateur
    SET sPassword = varHash
    WHERE ID_Utilisateur = _ID_Utilisateur;

    SELECT varHash as sPassword;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Utilisateur_delete
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$
CREATE PROCEDURE `PROPPS_DB`.`Utilisateur_delete` (IN _ID_Utilisateur INT)
BEGIN
    declare varID_Adresse INT;

    DELETE FROM Message WHERE ID_Utilisateur = _ID_Utilisateur;

    SET varID_Adresse = (SELECT ID_Adresse FROM Utilisateur WHERE ID_Utilisateur = _ID_Utilisateur);

    DELETE FROM Utilisateur WHERE ID_Utilisateur = _ID_Utilisateur;
    DELETE FROM Adresse WHERE ID_Adresse = varID_Adresse;
 
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Membre_delete
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$
CREATE PROCEDURE `PROPPS_DB`.`Membre_delete` (IN _ID_Utilisateur INT)
BEGIN
    DELETE FROM DomaineExperiencePro 
    WHERE ID_ExperiencePro IN (SELECT ID_ExperiencePro 
                               FROM ExperiencePro 
                                WHERE ID_Membre = _ID_Utilisateur);

    DELETE FROM ExperiencePro WHERE ID_Membre = _ID_Utilisateur;
    DELETE FROM Notification WHERE ID_Source = _ID_Utilisateur;
    DELETE FROM Notification WHERE ID_Destinataire = _ID_Utilisateur;

    DELETE FROM ExpertiseMembre WHERE ID_Utilisateur = _ID_Utilisateur;
    DELETE FROM Membre WHERE ID_Utilisateur = _ID_Utilisateur;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Profil_getAll
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`Profil_getAll` ()
BEGIN
    SELECT * FROM Profil;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Expertise_getAll
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`Expertise_getAll` ()
BEGIN
    SELECT * FROM DomaineExpertise;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure rechercheRapide
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`rechercheRapide` (IN _Str VARCHAR(250))
BEGIN
    SELECT *
    FROM Utilisateur as U inner join Membre as M on U.ID_Utilisateur = M.ID_Utilisateur
        inner join Adresse as A on A.ID_Adresse = U.ID_Adresse
    WHERE CONCAT(U.sNom, U.sPrenom) like CONCAT("%",_Str,"%")
        OR CONCAT(U.sPrenom, U.sNom) like CONCAT("%",_Str,"%")
    Order by U.sNom, U.sPrenom;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Recruteur_delete
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`Recruteur_delete` (IN _ID_Utilisateur INT)
BEGIN
    DELETE FROM Recruteur WHERE ID_Utilisateur = _ID_Utilisateur;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Recruteur_getByID
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$


CREATE PROCEDURE `PROPPS_DB`.`Recruteur_getByID` (IN _ID_Utilisateur INT)
BEGIN
    SELECT * 
    FROM Utilisateur inner join Recruteur on Utilisateur.ID_Utilisateur = Recruteur.ID_Utilisateur
        inner join Adresse on Adresse.ID_Adresse = Utilisateur.ID_Adresse
    WHERE Recruteur.ID_Utilisateur = _ID_Utilisateur;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Utilisateur_getMessages
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$
CREATE PROCEDURE `PROPPS_DB`.`Utilisateur_getMessages` (IN _ID_Utilisateur INT)
BEGIN
    SELECT * 
    FROM Message
    WHERE ID_Utilisateur = _ID_Utilisateur
    ORDER BY dtMessage DESC;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Message_insertOrUpdate
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$
CREATE PROCEDURE `PROPPS_DB`.`Message_insertOrUpdate` (IN _ID_Message INT, IN _ID_Utilisateur INT,
                IN _sMessage TEXT)
BEGIN
    IF _ID_Message < 0 THEN
        INSERT INTO Message(ID_Utilisateur,dtMessage,sMessage)
        VALUES (_ID_Utilisateur,NOW(),_sMessage);

        SELECT * FROM Message WHERE ID_Message = @@IDENTITY;
    ELSE
        UPDATE Message
        SET sMessage = _sMessage
        WHERE ID_Message = _ID_Message;
        
        SELECT * FROM Message WHERE ID_Message = _ID_Message;
    END IF;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Message_delete
-- -----------------------------------------------------

DELIMITER $$
USE `PROPPS_DB`$$
CREATE PROCEDURE `PROPPS_DB`.`Message_delete` (IN _ID_Message INT)
BEGIN
    DELETE FROM Message WHERE ID_Message = _ID_Message;
END$$

DELIMITER ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
