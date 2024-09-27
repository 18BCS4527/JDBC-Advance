package com.atus.jdbc;

import java.sql.DriverManager;
import java.util.Enumeration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	int a = 10;
    	
    	System.out.println(a);
    	try {
            Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                java.sql.Driver driver = drivers.nextElement();
                System.out.println("Driver Name: " + driver.getClass().getName());
            }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}
