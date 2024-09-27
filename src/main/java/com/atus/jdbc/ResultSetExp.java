package com.atus.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ResultSetExp {
	public void performSQlOperation() throws ClassNotFoundException, SQLException {
//		Class.forName("com.mysql.cj.jdbc.Driver");
		DriverManager.registerDriver(new Driver());
		System.out.println(DriverManager.getDriver("jdbc:mysql://localhost:3306/ecommerce?user=root&password=root@123"));
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce?user=root&password=root@123");
		Statement stm = con.createStatement();
		ResultSet result = stm.executeQuery("select * from User;");
		while(result.next()) {
			System.out.println(result.getInt(1)+", "+result.getString("name"));  
		}
		con.close();

	}

	public static void main(String[] args) {
		ResultSetExp user = new ResultSetExp();
		try {
			user.performSQlOperation();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
