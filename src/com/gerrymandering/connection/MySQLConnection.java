package com.gerrymandering.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 
public class MySQLConnection {
 

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        String url,user,password;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
			// url = "jdbc:mysql://localhost:3306/Mapleflix_db";
	         user = "root";
	         password ="Pxwstco96328.";
	         url="jdbc:mysql://localhost:3306/Gerrymandering_db?autoReconnect=true&useSSL=false";
	         connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
           
        
        return connection;
    }
  }