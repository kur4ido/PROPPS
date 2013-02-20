package com.polytech.propps.models;

import java.sql.Date;

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
	
	public Notification(Membre source, Membre destinataire, Date dtDemande, boolean bVuSource,
			boolean bVuDest	, boolean bAccept) {
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
}
