package com.polytech.html;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.polytech.bdd.TestJDBC;



public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			TestJDBC test = new TestJDBC();
			test.executerTests(null);
	}

}
