package com.polytech.propps.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.polytech.propps.bdd.Base;

public class Expertise {
	protected static HashMap<Integer,Expertise> listOfExpertise;
	public final static String colID = "ID_Domaine";
	public final static String colDomaine= "sDomaine";
	public final static String colType = "sType";
	
	protected int ID_Expertise;
	protected String sDomaine, sType;
	
	public static HashMap<Integer,Expertise> getListOfExpertise(){
		return listOfExpertise;
	}
	
	public Expertise(int ID) {
		ID_Expertise = ID;
		if(listOfExpertise.containsKey(ID)) {
			Expertise e = listOfExpertise.get(ID);
			sDomaine = e.getDomaine();
			sType = e.getType();
		}
	}
	
	private Expertise(int ID, String sDomaine, String sType) {
		ID_Expertise = ID;
		this.sDomaine = sDomaine;
		this.sType = sType;
	}
	
	public static void fillList() {
		listOfExpertise = new HashMap<Integer, Expertise>();
		Base b = new Base();
		try {
			b.connect();
			b.procedureInit("Expertise_getAll", 0);
			ResultSet result = b.executeQuery();
			while(result.next()) {
				int ID = result.getInt(colID);
				listOfExpertise.put(ID, new Expertise(ID,result.getString(colDomaine),result.getString(colType)));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			b.close();
		}
	}
	
	public String getType() {
		return sType;
	}
	
	public String getDomaine() {
		return sDomaine;
	}

	public int getID() {
		return ID_Expertise;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o != null && o instanceof Expertise) {
			Expertise e = (Expertise) o;
			return ID_Expertise == e.ID_Expertise;
		}
		return false;
	}
}
