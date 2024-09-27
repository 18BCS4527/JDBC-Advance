package com.atus.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class InsertUser {
	
	public void performSQlOperation() throws ClassNotFoundException, SQLException {
//	  Class.forName("com.mysql.cj.jdbc.Driver");
	  DriverManager.registerDriver(new Driver());
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root@123");
	  Statement stm = con.createStatement();
	  int result = stm.executeUpdate("Insert into User values(1, 'Dinesh Reddy','Anantapur',6300126068);");
	  System.out.println(result==1?"Inserted one column":"Failed with Exception");

	}
	public static void main(String[] args) {
		InsertUser user = new InsertUser();
		try {
			user.performSQlOperation();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
