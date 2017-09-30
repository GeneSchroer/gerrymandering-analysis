package com.gerrymandering.utils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.gerrymandering.beans.Person;
import com.mysql.jdbc.Connection;
public class UpdateUtils {

	public static boolean addPersonInfo(Connection conn, Person newPerson) {
		
		PreparedStatement preparedStatement = null;
		PreparedStatement addPersonStmt  = null;
		int status = 0;
		String addPerosn = "INSERT INTO Person (email,role,firstname,lastname,address,telephone, zipcode) "+
					"  VALUES(?, ?, ?, ? ,?, ?, ?) ";
		Statement stmt = null;
		
		
		try {
			conn.setAutoCommit(false);
			//add person
			stmt = conn.createStatement();
			stmt.execute("SET FOREIGN_KEY_CHECKS=0");

			preparedStatement  = conn.prepareStatement(addPerosn);
			preparedStatement.setString(1, newPerson.getEmail());
			preparedStatement.setString(2, newPerson.getRole());
			preparedStatement.setString(3, newPerson.getFirstName());
			preparedStatement.setString(4, newPerson.getLastName());
			preparedStatement.setString(5, newPerson.getAddress());
			preparedStatement.setString(6, newPerson.getTelephone());
			preparedStatement.setInt(7, Integer.parseInt(newPerson.getZipCode()));
			
			status = preparedStatement.executeUpdate();
				
			conn.commit();
		}catch (SQLException e) {
			GerrymanderingUtils.rollBack(conn);
	
			
		}finally {
		
			GerrymanderingUtils.closeUp(null,preparedStatement);
			GerrymanderingUtils.closeUp(null,addPersonStmt);
			GerrymanderingUtils.closeUp(null,stmt);
		}

		return status == 1? true: false;
	}

}
