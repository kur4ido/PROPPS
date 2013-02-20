package com.polytech.propps.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.polytech.propps.bdd.Base;

public class Notification {
	public static final String colBRecue = "bNotifRecues";
	public static final String colVuSource = "bVuSource";
	public static final String colVuDest = "bVuDest";
	public static final String colAccept = "bAccept";
	public static final String colDtNotif = "dtNotif";
	public static final String colID_Source = "ID_Source";
	public static final String colID_Dest = "ID_Destinataire";
	public static final String colID_Notif = "ID_Notification";
	
	protected Membre source,destinataire;
	protected Date dtDemande;
	protected boolean bVuSource, bVuDest,bAccept;
	protected int ID_Notification;
	
	
	public Notification(int ID_Notification,Membre source, Membre destinataire, Date dtDemande, boolean bVuSource,
			boolean bVuDest	, boolean bAccept) {
		this.ID_Notification = ID_Notification;
		this.source = source;
		this.destinataire = destinataire;
		this.dtDemande = dtDemande;
		this.bVuDest = bVuDest;
		this.bVuSource = bVuSource;
		this.bAccept = bAccept;
	}

	public boolean isbVuSource() {
		return bVuSource;
	}

	public void setbVuSource(boolean bVuSource) {
		this.bVuSource = bVuSource;
	}

	public boolean isbVuDest() {
		return bVuDest;
	}

	public void setbVuDest(boolean bVuDest) {
		this.bVuDest = bVuDest;
	}

	public boolean isbAccept() {
		return bAccept;
	}

	public void setbAccept(boolean bAccept) {
		this.bAccept = bAccept;
	}

	public Membre getSource() {
		return source;
	}

	public Membre getDestinataire() {
		return destinataire;
	}

	public Date getDtDemande() {
		return dtDemande;
	}

	public int getID() {
		return ID_Notification;
	}
	
	public void insertOrUpdate() {
		Base b = new Base();
		try {
			b.connect();
			b.procedureInit("Notification_insertOrUpdate", 6);
			b.setParamInt("_" + Notification.colID_Source, source.getID_Utilisateur());
			b.setParamInt("_" + Notification.colID_Dest,destinataire.getID_Utilisateur());
			b.setParamInt("_" + colID_Notif, ID_Notification);
			b.setParamBool("_" + colAccept, bAccept);
			b.setParamBool("_" + colVuSource, bVuSource);
			b.setParamBool("_" + colVuDest, bVuDest);
			ResultSet result = b.executeQuery();
			if(result.next()) {
				dtDemande = result.getDate(colDtNotif);
				ID_Notification = result.getInt(colID_Notif);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			b.close();
		}
		
	}
}
