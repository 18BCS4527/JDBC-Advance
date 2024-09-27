package com.atus.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class TransactionIsolationExaps {
	Connection con;
	Connection con1;
	Connection con2;
	public void createConnection() throws SQLException {
		DriverManager.registerDriver(new Driver()); 
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce?user=root&password=root@123");
		con.setAutoCommit(false);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
		con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce?user=root&password=root@123");
		con1.setAutoCommit(false);
		con1.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
		con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce?user=root&password=root@123");
		con2.setAutoCommit(false);
		con2.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		System.out.println("Connection Created");
	}
	public void insertUser(Users u) throws SQLException {
		PreparedStatement preStm = con.prepareStatement("insert into user values(?,?,?,?);");
		preStm.setInt(1, u.getId());
		preStm.setString(2, u.getName());
		preStm.setString(3, u.getAddress());
		preStm.setLong(4, u.getNumber());
		preStm.executeUpdate();
		System.out.println("User inserted but not commited");
	}
	
	public void getUserData(int userID) throws SQLException {
		PreparedStatement preStm = con1.prepareStatement("select * from user where userId=?;");
		preStm.setInt(1, userID); 
		ResultSet result = preStm.executeQuery();
		while(result.next()) {
			System.out.println(result.getString(2));
		}
		System.out.println("Finding uncommited data");
	}
	
	public void updateUserNumber(int userID,Long number) throws SQLException {
		PreparedStatement preStm = con2.prepareStatement("update user set number = ? where  userId=?;");
		preStm.setLong(1, number); 
		preStm.setInt(2, userID); 
		int count=preStm.executeUpdate();
		System.out.println("Updating uncommited data "+count);
	}
	
	public static void main(String[] args) {
		try {
			TransactionIsolationExaps transactionExp = new TransactionIsolationExaps();
			transactionExp.createConnection();
			transactionExp.insertUser(new Users(4527,"Sruthi","Vijayawada",1234567897L)); 
			transactionExp.getUserData(7);
			transactionExp.updateUserNumber(4527, 998877665544L); 
//			transactionExp.con.commit();
			transactionExp.con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
