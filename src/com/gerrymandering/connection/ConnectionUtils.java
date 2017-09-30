package com.gerrymandering.connection;
import java.sql.Connection;
import java.sql.SQLException;
public class ConnectionUtils {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
      return MySQLConnection.getConnection();
	}
	public static void closeConnection(Connection con){
		try{
			con.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}	
	}
	public static void rollback(Connection con){
		try{
			con.rollback();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}

	