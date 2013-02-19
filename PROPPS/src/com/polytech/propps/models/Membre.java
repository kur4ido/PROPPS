package com.polytech.propps.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.polytech.propps.bdd.Base;

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
	protected HashMap<Integer,ExperiencePro> lstExperiencePro;
	protected ArrayList<Expertise> lstExpertise;
	protected ArrayList<Notification> lstNotifEnvoi;
	protected ArrayList<Notification> lstNotifRecept;
	
	private boolean bFillExpertise,bFillContact,bFillExperiencePro; 
	
	
	public Membre(int ID) {
		super(ID);
		super.bFill = false;
		bFillExpertise = false;
		bFillContact = false;
		lstContacts = new ArrayList<Membre>();
		lstExperiencePro = new HashMap<Integer, ExperiencePro>();
		lstExpertise = new ArrayList<Expertise>();
	}
	
	public Membre(String sNom,String sPrenom,String sEmail, String sPassword,Profil profil, boolean bContrat,boolean bPresta,
			Date dtFinPresta) {
		super(sNom,sPrenom,sEmail,sPassword);
		this.profil = profil;
		this.bContrat = bContrat;
		this.bPresta = bPresta;
		this.dtFinPresta = dtFinPresta;
		lstContacts = new ArrayList<Membre>();
		lstExperiencePro = new HashMap<Integer, ExperiencePro>();
		lstExpertise = new ArrayList<Expertise>();
		super.bFill = true;
		bFillExpertise = true;
		bFillContact = true;
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
		lstExperiencePro = new HashMap<Integer, ExperiencePro>();
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
					Membre m = new Membre(result.getInt(colIDContact), result.getString(colNom),result.getString(colPrenom),
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
			b.setParamInt("_" + colID, super.ID_Utilisateur);
			b.setParamBool("_" + Notification.colBRecue,true);
			ResultSet result = b.executeQuery();
			lstNotifRecept = new ArrayList<Notification>();
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
				Notification n = new Notification(m, this, result.getDate(Notification.colDtNotif),
						result.getBoolean(Notification.colVuSource), result.getBoolean(Notification.colVuDest),
						result.getBoolean(Notification.colAccept));
				//Ajout dans la liste concernée
				lstNotifRecept.add(n);
			}
			
			/*On réitère le processus pour les notifications envoyées*/
			
			b.procedureInit("Membre_getNotif", 2);
			b.setParamInt("_" + colID, super.ID_Utilisateur);
			b.setParamBool("_" + Notification.colBRecue,false);
			result = b.executeQuery();
			lstNotifRecept = new ArrayList<Notification>();
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
				Notification n = new Notification(this, m, result.getDate(Notification.colDtNotif),
						result.getBoolean(Notification.colVuSource), result.getBoolean(Notification.colVuDest),
						result.getBoolean(Notification.colAccept));
				//Ajout dans la liste concernée
				lstNotifEnvoi.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			b.close();
		}
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
					if(result.getInt(colNbExp) > 0) {
						ExperiencePro ep = new ExperiencePro(result.getInt(ExperiencePro.colID));
						lstExperiencePro.put(ep.getID(), ep);
					}
				}
				lstExperiencePro = new HashMap<Integer, ExperiencePro>();
				while(result.next()) {
					ExperiencePro ep = new ExperiencePro(result.getInt(ExperiencePro.colID));
					lstExperiencePro.put(ep.getID(), ep);
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
			HashMap<Integer, ExperiencePro> lstExpProTemp = new HashMap<Integer, ExperiencePro>();
			for(Map.Entry<Integer, ExperiencePro> entre : lstExperiencePro.entrySet()) {
				ExperiencePro ep = entre.getValue();
				b.procedureInit("Membre_ajouterExperiencePro", 8);
				b.setParamInt("_" + colID, super.ID_Utilisateur);
				b.setParamInt("_" + Profil.colID, (ep.getProfil() == null ? null : ep.getProfil().getID()));
				b.setParamInt("_" + Societe.colID, ep.getSociete().getID());
				b.setParamDate("_" + ExperiencePro.colDtDebut, ep.getDtDebut());
				b.setParamDate("_" +  ExperiencePro.colDtFin, ep.getDtFin());
				b.setParamString("_" + ExperiencePro.colDescription, ep.getDescription());
				b.setParamString("_" + ExperiencePro.colPosteOccupe, ep.getPosteOccupe());
				b.setParamString("_" + ExperiencePro.colDirection, ep.getDirection());
				ResultSet result = b.executeQuery();
				if(result.next()) {
					int ID = result.getInt(ExperiencePro.colID);
					ep.setID(ID);
					lstExpProTemp.put(ID, ep);
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
		lstExperiencePro.put(e.getID(),e);
	}
	
	/**
	 * Méthode modélisant la demande de mise en relation par le membre courant
	 * au membre donné en paramètre. Cette demande est enregistrée uniquement dans
	 * la base de données pour le membre destinataire.
	 * 
	 * @param m : le membre destinataire
	 */
	public void demanderContact(Membre m) {
		Base b = new Base();
		try {
			b.connect();
			b.procedureInit("Membre_envoyerNotif", 2);
			b.setParamInt("_" + Notification.colID_Source, super.ID_Utilisateur);
			b.setParamInt("_" + Notification.colID_Dest,m.getID_Utilisateur());
			ResultSet result = b.executeQuery();
			if(result.next()) {
				Notification n = new Notification(this, m,result.getDate(Notification.colDtNotif)
						, false, false, false);
				lstNotifEnvoi.add(n);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			b.close();
		}
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
			b.setParamInt("_" + colID, super.ID_Utilisateur);
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
		return bContrat;
	}

	public void setHasContrat(boolean bContrat) {
		this.bContrat = bContrat;
		
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

	public HashMap<Integer, ExperiencePro> getLstExperiencePro() {
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
	}
	
	/*-------Methodes statiques--------*/
	public ArrayList<Membre> rechercheRapide(String s) {
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
				Membre m = new Membre(result.getInt(colIDContact), result.getString(colNom),result.getString(colPrenom),
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
