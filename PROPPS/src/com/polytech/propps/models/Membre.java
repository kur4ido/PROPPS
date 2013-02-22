package com.polytech.propps.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.polytech.propps.bdd.Base;
import com.polytech.propps.utils.Pair;

public class Membre extends Utilisateur{
	private final static String colPresta = "bPresta";
	private final static String colDtPresta = "dtFinPresta";
	private final static String colContrat = "bContrat";
	private final static String colNbExp = "nbExp";
	public final static String colIDMembre = "ID_Membre";
	public final static String colIDContact = "ID_Contact";
	
	
	protected Profil profil;
	protected boolean bContrat, bPresta;
	protected Date dtFinPresta;
	protected ArrayList<Membre> lstContacts;
	protected ArrayList<ExperiencePro> lstExperiencePro;
	protected ArrayList<Expertise> lstExpertise;
	protected HashMap<Integer,Notification> lstNotifEnvoi;
	protected HashMap<Integer,Notification> lstNotifRecept;
	
	
	private boolean bFillExpertise,bFillContact,bFillExperiencePro,bFillNotif; 
	
	
	public Membre(int ID) {
		super(ID);
		super.bFill = false;
		bFillExpertise = false;
		bFillContact = false;
		bFillNotif = false;
		lstContacts = new ArrayList<Membre>();
		lstExperiencePro = new ArrayList<ExperiencePro>();
		lstExpertise = new ArrayList<Expertise>();
		lstNotifEnvoi = new HashMap<Integer, Notification>();
		lstNotifRecept = new HashMap<Integer, Notification>();
	}
	
	public Membre(String sNom,String sPrenom,String sEmail, String sPassword,Profil profil, boolean bContrat,boolean bPresta,
			Date dtFinPresta) {
		super(sNom,sPrenom,sEmail,sPassword);
		this.profil = profil;
		this.bContrat = bContrat;
		this.bPresta = bPresta;
		this.dtFinPresta = dtFinPresta;
		lstContacts = new ArrayList<Membre>();
		lstExperiencePro = new ArrayList<ExperiencePro>();
		lstExpertise = new ArrayList<Expertise>();
		lstNotifEnvoi = new HashMap<Integer, Notification>();
		lstNotifRecept = new HashMap<Integer, Notification>();
		super.bFill = true;
		bFillExpertise = true;
		bFillContact = true;
		bFillNotif = true;
	}
	
	
	private Membre(int ID_Utilisateur,String sNom,String sPrenom,String sEmail,Profil profil, boolean bContrat,boolean bPresta,
			Date dtFinPresta) {
		super(sNom,sPrenom,sEmail,null);
		super.ID_Utilisateur = ID_Utilisateur;
		this.profil = profil;
		this.bContrat = bContrat;
		this.bPresta = bPresta;
		this.dtFinPresta = dtFinPresta;
		lstContacts = new ArrayList<Membre>();
		lstExperiencePro = new ArrayList<ExperiencePro>();
		lstExpertise = new ArrayList<Expertise>();
		super.bFill = true;
		bFillExpertise = false;
		bFillContact = false;
		bFillExperiencePro = false;
	}
	
	/**
	 * Methode permettant de remplir la liste d'expertise du membre
	 * 
	 * Nombre de requêtes SQL : 1
	 */
	public void fillExpertise() {
		if(!bFillExpertise) {
			Base b = new Base();
			try {
				b.connect();
				b.procedureInit("Membre_getExpertiseByID", 1);
				b.setParamInt("_" + colIDMembre, super.ID_Utilisateur);
				ResultSet result = b.executeQuery();
				lstExpertise = new ArrayList<Expertise>();
				while(result.next()) {
					lstExpertise.add(new Expertise(result.getInt(Expertise.colID)));
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				b.close();
			}
			bFillExpertise = true;
		}
	}
	
	/**
	 * Methode permettant de remplir la liste de contact du membre.
	 * 
	 * Les informations remplies sont les suivantes :
	 * 		Nom, prénom, email, adresse (Données utilisateur)
	 * 		Profil, disponibilité (bPresta, bContrat, dtFinPresta)
	 * 
	 * Les informations type liste ne sont pas remplies (expertise, experiences pro et contacts), elles ne
	 * le seront qu'au moment de l'appel à leurs getter respectifs.
	 * 
	 * Nombre de requêtes SQL : 1
	 */
	public void fillContact() {
		if(!bFillContact) {
			Base b = new Base();
			try {
				b.connect();
				b.procedureInit("Membre_getContactByID", 1);
				b.setParamInt("_" + colIDMembre, super.ID_Utilisateur);
				ResultSet result = b.executeQuery();
				lstContacts = new ArrayList<Membre>();
				while(result.next()) {
					//Instanciation du profil
					Profil p  = (result.getObject(Profil.colID) == null ? null : new Profil(result.getInt(Profil.colID)));
					
					//Instanciation du membre
					Membre m = new Membre(result.getInt(colID), result.getString(colNom),result.getString(colPrenom),
							 result.getString(colEmail),p,result.getBoolean(colContrat),result.getBoolean(colPresta),result.getDate(colDtPresta));
					//Instanciaion de l'adresse
					Adresse a = new Adresse(result.getString(Adresse.colVille), result.getString(Adresse.colCP),
							result.getString(Adresse.colAdresse), result.getString(Adresse.colPays));
					m.setAdresse(a);
					
					//Ajout dans la liste de contact
					lstContacts.add(m);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				b.close();
			}
			bFillContact = true;
		}
	}
	
	/**
	 * Methode permettant le remplissage des listes de notifications (reçues et envoyées)
	 * 
	 * Nombre de requêtes SQL : 2
	 */
	public void fillNotification() {
		Base b = new Base();
		try {
			b.connect();
			b.procedureInit("Membre_getNotif", 2);
			b.setParamInt("_" + colIDMembre, super.ID_Utilisateur);
			b.setParamBool("_" + Notification.colBRecue,true);
			ResultSet result = b.executeQuery();
			lstNotifRecept = new HashMap<Integer, Notification>();
			while (result.next()) {
				//Remplissage du profil
				Profil p  = (result.getObject(Profil.colID) == null ? null : new Profil(result.getInt(Profil.colID)));
				//Instanciation du membre source (celui qui a envoyé la notification)
				Membre m = new Membre(result.getInt(Notification.colID_Source), result.getString(colNom),result.getString(colPrenom),
						 result.getString(colEmail),p,result.getBoolean(colContrat),result.getBoolean(colPresta),result.getDate(colDtPresta));
				//Instanciation de l'adresse du membre source
				Adresse a = new Adresse(result.getString(Adresse.colVille), result.getString(Adresse.colCP),
						result.getString(Adresse.colAdresse), result.getString(Adresse.colPays));
				m.setAdresse(a);
				
				//Instanciation de l'objet notification
				Notification n = new Notification(result.getInt(Notification.colID_Notif),m, this, result.getDate(Notification.colDtNotif),
						result.getBoolean(Notification.colVuSource), result.getBoolean(Notification.colVuDest),
						result.getBoolean(Notification.colAccept));
				//Ajout dans la liste concernée
				lstNotifRecept.put(n.getID(),n);
			}
			
			/*On réitère le processus pour les notifications envoyées*/
			
			b.procedureInit("Membre_getNotif", 2);
			b.setParamInt("_" + colIDMembre, super.ID_Utilisateur);
			b.setParamBool("_" + Notification.colBRecue,false);
			result = b.executeQuery();
			lstNotifEnvoi = new HashMap<Integer, Notification>();
			while (result.next()) {
				//Remplissage du profil
				Profil p  = (result.getObject(Profil.colID) == null ? null : new Profil(result.getInt(Profil.colID)));
				//Instanciation du membre source (celui qui a envoyé la notification)
				Membre m = new Membre(result.getInt(Notification.colID_Dest), result.getString(colNom),result.getString(colPrenom),
						 result.getString(colEmail),p,result.getBoolean(colContrat),result.getBoolean(colPresta),result.getDate(colDtPresta));
				//Instanciation de l'adresse du membre source
				Adresse a = new Adresse(result.getString(Adresse.colVille), result.getString(Adresse.colCP),
						result.getString(Adresse.colAdresse), result.getString(Adresse.colPays));
				m.setAdresse(a);
				
				//Instanciation de l'objet notification
				Notification n = new Notification(result.getInt(Notification.colID_Notif),this, m, result.getDate(Notification.colDtNotif),
						result.getBoolean(Notification.colVuSource), result.getBoolean(Notification.colVuDest),
						result.getBoolean(Notification.colAccept));
				//Ajout dans la liste concernée
				lstNotifEnvoi.put(n.getID(),n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			b.close();
		}
		bFillNotif = true;
	}
	
	
	/**
	 * Methode permettant de remplir les informations générales du membre, ainsi que ses données utilisateur.
	 * Le remplissage d'expérience professionnelles se fait également ici.
	 * 
	 * Nombre de requêtes SQL : 1
	 */
	public void fill() {
		if(!super.bFill || !bFillExperiencePro) {
			Base b = new Base();
			try {
				b.connect();
				b.procedureInit("Membre_getByID", 1);
				b.setParamInt("_" + colID, super.ID_Utilisateur);
				ResultSet result = b.executeQuery();
				if(result.next()) {
					super.sNom = result.getString(colNom);
					super.sPrenom = result.getString(colPrenom);
					super.sEmail = result.getString(colEmail);
					super.sPassword = result.getString(colPassword);
					super.adresse.setAdresse(result.getString(Adresse.colAdresse));
					super.adresse.setVille(result.getString(Adresse.colVille));
					super.adresse.setCodePostal(result.getString(Adresse.colCP));
					super.adresse.setPays(result.getString(Adresse.colPays));
					
					profil = (result.getObject(Profil.colID) == null ? null : new Profil(result.getInt(Profil.colID)));
					
					bContrat = result.getBoolean(colContrat);
					bPresta = result.getBoolean(colPresta);
					dtFinPresta = result.getDate(colDtPresta);
					lstExperiencePro = new ArrayList<ExperiencePro>();
					if(result.getInt(colNbExp) > 0) {
						ExperiencePro ep = new ExperiencePro(result.getInt(ExperiencePro.colID));
						lstExperiencePro.add(ep);
					}
				}
				while(result.next()) {
					ExperiencePro ep = new ExperiencePro(result.getInt(ExperiencePro.colID));
					lstExperiencePro.add(ep);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				b.close();
			}
			bFill = true;
			bFillExperiencePro = true;
		}
	}
	
	/**
	 * Méthode permettant l'insertion ou la mise à jour dans la base de données du membre instancié.
	 * 
	 * Nombre de requêtes SQL : 1 + nbExpPro + nbExperise
	 */
	@Override
	public void insertOrUpdate() {
		Base b = new Base();
		try {
			super.insertOrUpdate();
			b.connect();
			b.procedureInit("Membre_insertOrUpdate", 5);
			b.setParamInt("_" + colID, super.ID_Utilisateur);
			b.setParamBool("_" + colContrat, bContrat);
			b.setParamBool("_" + colPresta, bPresta);
			b.setParamInt("_" + Profil.colID, (profil == null ? null : profil.getID()));
			b.setParamDate("_" + colDtPresta, dtFinPresta);
			b.execute();
			
			ArrayList<ExperiencePro> lstExpProTemp = new ArrayList<ExperiencePro>();
			for(ExperiencePro ep : lstExperiencePro) {
				b.procedureInit("Membre_ajouterExperiencePro", 8);
				//b.setParamInt("_" + colID, super.ID_Utilisateur);
				b.setParamInt("_" + Profil.colID, (ep.getProfil() == null ? null : ep.getProfil().getID()));
				b.setParamInt("_" + Societe.colID, ep.getSociete().getID());
				b.setParamDate("_" + ExperiencePro.colDtDebut, ep.getDtDebut());
				b.setParamDate("_" +  ExperiencePro.colDtFin, ep.getDtFin());
				b.setParamString("_" + ExperiencePro.colDescription, ep.getDescription());
				b.setParamString("_" + ExperiencePro.colPosteOccupe, ep.getPosteOccupe());
				b.setParamString("_" + ExperiencePro.colDirection, ep.getDirection());
				b.setParamInt("_" + colIDMembre, super.ID_Utilisateur);
				ResultSet result = b.executeQuery();
				if(result.next()) {
					int ID = result.getInt(ExperiencePro.colID);
					ep.setID(ID);
					lstExpProTemp.add(ep);
				}
			}
			lstExperiencePro = lstExpProTemp;
			for(Expertise e : lstExpertise) {
				b.procedureInit("Membre_ajouterExpertise", 2);
				b.setParamInt("_" + colID, super.ID_Utilisateur);
				b.setParamInt("_" + Expertise.colID,e.getID());
				b.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			b.close();
		}
		
	}

	

	@Override
	public void delete() {
		Base b = new Base();
		try {
			b.connect();
			b.procedureInit("Membre_delete", 1);
			b.setParamInt("_" + colID, super.ID_Utilisateur);
			b.execute();
			super.delete();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			b.close();
		}
		
	}

	public void addExperiencePro(ExperiencePro e) {
		if(lstExperiencePro.contains(e)) {
			lstExperiencePro.remove(e);
			lstExperiencePro.add(e);
		}
		lstExperiencePro.add(e);
		bFillExperiencePro = true;
	}
	
	/**
	 * Méthode modélisant la demande de mise en relation par le membre courant
	 * au membre donné en paramètre. Cette demande est enregistrée uniquement dans
	 * la base de données pour le membre destinataire.
	 * 
	 * @param m : le membre destinataire
	 */
	public void demanderContact(Membre m) {
		Notification n = new Notification(-1,this,m,null,false,false,false);
		n.insertOrUpdate();
		lstNotifEnvoi.put(n.getID(),n);
	}
	
	
	/**
	 * Méthode modélisant la réponse à une demande de mise en relation.
	 * Cette réponse entraine la mise à jour suivante :
	 * 	Si la réponse est positive, une relation est établie.
	 * Dans tous les cas, la ligne correspondant à la notification concernée est
	 * mise à jour (bAccept prend le résultat de la réponse et bVuDestinataire
	 * est fixé à vrai)
	 * 
	 * @param ID_Notif : l'identifiant de la notification concernée
	 * @param bAccept : la réponse formulée par le membre courant
	 */
	public void reponseDemande(int ID_Notif, boolean bAccept) {
		if(!bFillNotif) {
			fillNotification();
		}
		if(lstNotifRecept.containsKey(ID_Notif)) {
			Notification n = lstNotifRecept.get(ID_Notif);
			if(bAccept) {
				addContact(n.getSource());
			}
			n.setbAccept(bAccept);
			n.setbVuDest(true);
			n.insertOrUpdate();
			lstNotifRecept.remove(ID_Notif);
			lstNotifRecept.put(n.getID(), n);
		}
	}
	
	/**
	 * Méthode modélisant la réponse à une demande de mise en relation.
	 * Cette réponse entraine la mise à jour suivante :
	 * 	Si la réponse est positive, une relation est établie.
	 * Dans tous les cas, la ligne correspondant à la notification concernée est
	 * mise à jour (bAccept prend le résultat de la réponse et bVuDestinataire
	 * est fixé à vrai)
	 * 
	 * @param m : le membre qui a envoyé l'invitation
	 * @param bAccept : la réponse formulée par le membre courant
	 */
	public void reponseDemande(Membre m, boolean bAccept) {
		if(!bFillNotif) {
			fillNotification();
		}
		Notification n = new Notification(-1,m,this,null,false,false,false);
		for(Map.Entry<Integer, Notification> entre : lstNotifRecept.entrySet()) {
			if(entre.getValue().getSource().getID_Utilisateur() == m.getID_Utilisateur()) {
				n = entre.getValue();
			}
		}
		if(bAccept) {
			addContact(m);
		}
		n.setbAccept(bAccept);
		n.setbVuDest(true);
		n.insertOrUpdate();
	}
	
	/**
	 * Méthode permettant d'ajouter une relation entre deux membres (dans la base
	 * ainsi que dans les instances locales au système)
	 * 
	 * Nombre de requêtes SQL : 1
	 * @param m le membre à ajouter
	 */
	public void addContact(Membre m) {
		Base b = new Base();
		try {
			b.connect();
			b.procedureInit("Membre_ajouterContact", 2);
			b.setParamInt("_" + colIDMembre, super.ID_Utilisateur);
			b.setParamInt("_" + colIDContact, m.ID_Utilisateur);
			b.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			b.close();
		}
		add(m);
		m.add(this);
	}
	
	private void add(Membre m) {
		lstContacts.add(m);
	}
	
	/**
	 * Methode déstinée a savoir si le membre donnée en paramètre est
	 * en relation avec le membre courant.
	 * 
	 * @param m : le membre à tester
	 * @return Vrai si le membre fait partie des contacts du membre courant.
	 */
	public boolean aCommeAmi(Membre m) {
		if(!bFillContact) {
			fillContact();
		}
		return lstContacts.contains(m);
	}
	
	/**
	 * Methode permettant de savoir si le membre courant a demandé
	 * au membre passé en paramètre de se mettre en relation avec lui.
	 * 
	 * @param m : le membre à tester
	 * @return
	 */
	public boolean aEnvoyeInvit(Membre m) {
		if(!bFillNotif) {
			fillNotification();
		}
		for(Map.Entry<Integer, Notification> entre : lstNotifEnvoi.entrySet()) {
			if(entre.getValue().getDestinataire().getID_Utilisateur() == m.getID_Utilisateur()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Methode permettant de savoir si le membre courat a été invité
	 * par le membre passé en paramètre.
	 * 
	 * @param m : le membre à tester
	 * @return
	 */
	public boolean aEteInvitePar(Membre m) {
		if(!bFillNotif) {
			fillNotification();
		}
		for(Map.Entry<Integer, Notification> entre : lstNotifRecept.entrySet()) {
			if(entre.getValue().getSource().getID_Utilisateur() == m.getID_Utilisateur()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Algorithme de calcul de l'expérience (le cumul des période est pris en
	 * compte)
	 * 
	 * @return un objet Date.sql correspondant à la durée passéd à travailler
	 */
	public Date getExperience() {
		if(!bFillExperiencePro) {
			fill();
		}
		ArrayList<Pair<Date,Date>> lstIntervalles = new ArrayList<Pair<Date,Date>>();
		long date = 0;
		for(ExperiencePro e : lstExperiencePro) {
			Date tmp = (e.getDtFin() == null ? new Date(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH) : e.getDtFin());
			lstIntervalles.add(new Pair<Date, Date>(e.getDtDebut(), tmp));
		}
		for(int i = lstIntervalles.size() - 1  ; i > 0 ; i--) {
			Date d1 = lstIntervalles.get(i).first;
			Date d2 = lstIntervalles.get(i).second;
			for(int j = i - 1; j > 0 ; j--) {
				Pair<Date,Date> p = lstIntervalles.get(j);
				if(p.first.compareTo(d1) >= 0 && p.first.compareTo(d2) <= 0) {
					p.first = d1;
					p.second = (p.second.compareTo(d2) > 0 ? p.second : d2);
					lstIntervalles.remove(i);
					j = -1;
				} else if(p.second.compareTo(d1) >= 0 && p.second.compareTo(d2) <= 0) {
					p.second = d2;
					p.first = (p.first.compareTo(d1) < 0 ? p.second : d1);
					lstIntervalles.remove(i);
					j = -1;
				}
			}
		}
		for(Pair<Date,Date> p : lstIntervalles) {
			date += p.second.getTime() - p.first.getTime();
		}
		return new Date(date);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Membre) {
			Membre m = (Membre) o;
			return (m.getID_Utilisateur() == super.ID_Utilisateur);
		}
		return false;
	}
	
	/*---Getters & Setters---*/
	
	public boolean isbFillExpertise() {
		return bFillExpertise;
	}

	public boolean isbFillContact() {
		return bFillContact;
	}

	public boolean isbFillExperiencePro() {
		return bFillExperiencePro;
	}

	public Profil getProfil() {
		if(!super.bFill) {
			fill();
		}
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
		super.bFill = true;
	}

	public boolean hasContrat() {
		if(!super.bFill) {
			fill();
		}
		return bContrat;
	}

	public void setHasContrat(boolean bContrat) {
		this.bContrat = bContrat;
		super.bFill = true;
	}

	public boolean isPresta() {
		if(!super.bFill) {
			fill();
		}
		return bPresta;
	}

	public void setIsPresta(boolean bPresta) {
		this.bPresta = bPresta;
		super.bFill = true;
	}

	public Date getDtFinPresta() {
		if(!super.bFill) {
			fill();
		}
		return dtFinPresta;
	}

	public void setDtFinPresta(Date dtFinPresta) {
		this.dtFinPresta = dtFinPresta;
		super.bFill = true;
	}

	public ArrayList<Membre> getLstContacts() {
		if(!bFillContact) {
			fillContact();
		}
		return lstContacts;
	}

	public ArrayList<ExperiencePro> getLstExperiencePro() {
		if(!bFillExperiencePro) {
			fill();
		}
		return lstExperiencePro;
	}

	public ArrayList<Expertise> getLstExpertise() {
		if(!bFillExpertise) {
			fillExpertise();
		}
		return lstExpertise;
	}

	public void addExpertise(Expertise e) {
		this.lstExpertise.add(e);
		bFillExpertise = true;
	}
	
	public HashMap<Integer, Notification> getLstNotifEnvoi() {
		if(!bFillNotif) {
			fillNotification();
		}
		return lstNotifEnvoi;
	}

	public HashMap<Integer, Notification> getLstNotifRecept() {
		if(!bFillNotif) {
			fillNotification();
		}
		return lstNotifRecept;
	}
	
	/*-------Methodes statiques--------*/
	public static ArrayList<Membre> rechercheRapide(String s) {
		ArrayList<Membre> resultat = new ArrayList<Membre>();
		Base b = new Base();
		try {
			b.connect();
			b.procedureInit("rechercheRapide", 1);
			b.setParamString("_Str", s);
			ResultSet result = b.executeQuery();
			while(result.next()) {
				Profil p  = (result.getObject(Profil.colID) == null ? null : new Profil(result.getInt(Profil.colID)));
				
				//Instanciation du membre
				Membre m = new Membre(result.getInt(Utilisateur.colID), result.getString(colNom),result.getString(colPrenom),
						 result.getString(colEmail),p,result.getBoolean(colContrat),result.getBoolean(colPresta),result.getDate(colDtPresta));
				//Instanciaion de l'adresse
				Adresse a = new Adresse(result.getString(Adresse.colVille), result.getString(Adresse.colCP),
						result.getString(Adresse.colAdresse), result.getString(Adresse.colPays));
				m.setAdresse(a);
				resultat.add(m);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			b.close();
		}
		return resultat;
	}

}
