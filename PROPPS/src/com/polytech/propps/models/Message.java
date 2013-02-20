package com.polytech.propps.models;

import java.sql.Date;

public class Message implements IModel{
	protected int ID_Message;
	protected String sMessage;
	protected int ID_Utilisateur;
	protected Date dtMessage;
	
	public Message(int ID_Message, String sMessage, int ID_Utilisateur, Date dtMessage) {
		this.ID_Message = ID_Message;
		this.dtMessage = dtMessage;
		this.ID_Utilisateur = ID_Utilisateur;
		this.sMessage = sMessage;
	}
	
	@Override
	public void insertOrUpdate() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	
	
}
