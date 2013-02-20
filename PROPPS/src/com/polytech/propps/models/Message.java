package com.polytech.propps.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.polytech.propps.bdd.Base;

public class Message implements IModel{
	public static String colID = "ID_Message";
	public static String colMessage = "sMessage";
	public static String colDtMessage = "dtMessage";
	
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
		Base b = new Base();
		try {
			b.connect();
			b.procedureInit("Message_insertOrUpdate", 3);
			b.setParamInt("_" + colID, ID_Message);
			b.setParamInt("_" + Utilisateur.colID,ID_Utilisateur);
			b.setParamString("_" + colMessage, sMessage);
			ResultSet result = b.executeQuery();
			if(result.next()) {
				dtMessage = result.getDate(colDtMessage);
				ID_Message = result.getInt(colID);
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
			b.procedureInit("Message_delete", 1);
			b.setParamInt("_" + colID, ID_Message);
			b.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			b.close();
		}
	}
	
	
}
