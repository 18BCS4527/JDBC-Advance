package com.atus.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;

class Users{
	int id;
	String name;
	String address;
	Long number;
	public Users(int id, String name, String address, Long number) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.number = number;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	
	
}
public class MultipleInsertByUsingPrepareStatment {

	public void performSQlAction(List<Users> users) throws SQLException {
		DriverManager.registerDriver(new Driver());
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root@123");
		PreparedStatement preStm = con.prepareStatement("insert into user values(?,?,?,?);");
		for(Users u:users) {
			preStm.setInt(1, u.getId());
			preStm.setString(2, u.getName());
			preStm.setString(3, u.getAddress());
			preStm.setLong(4, u.getNumber());
			preStm.addBatch();
		}
		
		int[] a= preStm.executeBatch();
		System.out.println("Updated Rows "+a.length); 
		
	}
	public static void main(String[] args) {
		List<Users> users = new ArrayList();
		users.add(new Users(2,"Ramu","Kadiri",1234567890L));
		users.add(new Users(3,"Sai Kumar","Karnool",9876543212L));
		users.add(new Users(4,"Uday","Nandlya",876543210L));
		users.add(new Users(5,"Danam","Guntur",76543211123L));
		users.add(new Users(6,"Varun","Bengaluru",66543212345L));
		users.add(new Users(7,"Sree","Hyd",5432111112345L));
		try {
			MultipleInsertByUsingPrepareStatment mulPrepStm= new MultipleInsertByUsingPrepareStatment();
			mulPrepStm.performSQlAction(users);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
