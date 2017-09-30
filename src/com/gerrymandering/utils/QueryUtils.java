package com.gerrymandering.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.gerrymandering.beans.Account;
import com.gerrymandering.beans.Person;
//import com.gerrymandering.constants.Attributies;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;


public class QueryUtils {
	
	
	public static boolean verifyUser(Connection conn, String userName, String password) {
		boolean status = false;
		CallableStatement cStmt = null;
		ResultSet result = null;
		String query = "{call verifyUser(?, ?, ?)}";
		
		try {
			conn.setAutoCommit(false);
			cStmt =  (CallableStatement) conn.prepareCall(query);
			cStmt.setString(1, userName);
			cStmt.setString(2, password);
			cStmt.registerOutParameter(3, Types.INTEGER);
			
			result = cStmt.executeQuery();
			int outputValue = cStmt.getInt(3); 
			status = (outputValue == 1)? true : false;

			conn.commit();
			
		}catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
		}finally {
			try {
				if(result != null)
					result.close();
				if(cStmt != null)
					cStmt.close();
				
				
			} catch (SQLException e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return status;
	}
	
	public static String getRole(Connection conn, String userName) {
		boolean status = false;
		CallableStatement cStmt = null;
		ResultSet result = null;
		String role = null;
		String query = "{call findRole(?, ?, ?)}";
		
		try {
			conn.setAutoCommit(false);
			cStmt =  (CallableStatement) conn.prepareCall(query);
			cStmt.setString(1, userName);
			cStmt.registerOutParameter(2, Types.VARCHAR);
			cStmt.registerOutParameter(3, Types.INTEGER);
	
			result = cStmt.executeQuery();
			int outputValue = cStmt.getInt(3); 
			status = (outputValue == 1)? true : false;
			
			if(status)
				role = cStmt.getString(2);
			
			conn.commit();
			
		}catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
		}finally {
			try {
				if(result != null)
					result.close();
				if(cStmt != null)
					cStmt.close();
					
			} catch (SQLException e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return role;
	}
	
	
	

		public static int addPerson(Connection conn, String id, String email, String name1, String name2, String address,
				String zip, String phone) {
			int result = -1;
			PreparedStatement preparedStatement = null;
			String role = "customer";
			String query = "INSERT INTO person " + "VALUES (?,?,?,?,?,?,?)";
			try {
				conn.setAutoCommit(false);
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, role);
				preparedStatement.setString(3, name1);
				preparedStatement.setString(4, name2);
				preparedStatement.setString(5, address);
				preparedStatement.setString(6, phone);
				preparedStatement.setInt(7, Integer.parseInt(zip));
				result = preparedStatement.executeUpdate();
				conn.commit();
			} catch (SQLException e) {
				GerrymanderingUtils.rollBack(conn);
			} finally {
				GerrymanderingUtils.closeUp(null, preparedStatement);
			}
			if (result == 1) {
				return 1;
			} else {
				return 0;
			}
		}

		public static int editPerson(Connection conn, String id, String email, String name1, String name2, String address,
				String zip, String phone) {
			int result = -1;
			PreparedStatement preparedStatement = null;
			String query = "UPDATE person " + "SET firstname=? , lastname=? , address=? , telephone=? , zipcode=? "
					+ "WHERE email=?";
			try {
				conn.setAutoCommit(false);
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setString(1, name1);
				preparedStatement.setString(2, name2);
				preparedStatement.setString(3, address);
				preparedStatement.setString(4, phone);
				preparedStatement.setInt(5, Integer.parseInt(zip));
				preparedStatement.setString(6, email);
				result = preparedStatement.executeUpdate();
				conn.commit();
			} catch (SQLException e) {
				GerrymanderingUtils.rollBack(conn);
			} finally {
				GerrymanderingUtils.closeUp(null, preparedStatement);
			}
			if (result == 1) {
				return 1;
			} else {
				return 0;
			}
		}

		public static int deletePerson(Connection conn, String email) {
			int result = -1;
			PreparedStatement preparedStatement = null;
			String query = "DELETE FROM person " + "WHERE email=? AND role='customer'";
			try {
				conn.setAutoCommit(false);
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setString(1, email);
				result = preparedStatement.executeUpdate();
				conn.commit();
			} catch (SQLException e) {
				GerrymanderingUtils.rollBack(conn);
			} finally {
				GerrymanderingUtils.closeUp(null, preparedStatement);
			}
			if (result == 1) {
				return 1;
			} else {
				return 0;
			}
		}

		public static ArrayList<Person> mailList(Connection conn) {
			ArrayList<Person> rtn = new ArrayList<Person>();
			Person person = null;
	
			String firstname = null;
			String lastname = null;
			String address = null;
			String email = null;
			ResultSet result = null;
			PreparedStatement preparedStatement = null;
			String query = "SELECT  P.firstname, P.lastname, P.address, P.email " + "FROM person P "
					+ "WHERE   P.role='customer'";
			try {
				conn.setAutoCommit(false);
				preparedStatement = conn.prepareStatement(query);
				result = preparedStatement.executeQuery();
				while (result.next()) {
					
					firstname = result.getString("firstname");
					lastname = result.getString("lastname");
					address = result.getString("address");
					email = result.getString("email");
					person = new Person();
					person.setEmail(email);
					person.setAddress(address);
					person.setFirstName(firstname);
					person.setLastName(lastname);
					
					rtn.add(person);
				}
				conn.commit();
			} catch (SQLException e) {
				GerrymanderingUtils.rollBack(conn);
				return null;
			} finally {
				GerrymanderingUtils.closeUp(result, preparedStatement);
			}
			return rtn;
		}


}
