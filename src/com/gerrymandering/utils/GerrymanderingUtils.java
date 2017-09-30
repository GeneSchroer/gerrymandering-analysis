package com.gerrymandering.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import com.gerrymandering.beans.Person;
import com.gerrymandering.beans.Account;

public class GerrymanderingUtils {
	public static final String CONNECTION = "connection";
	public static final String LOGINED_USER = "loginedUser";
//	public static final String MOVIES_DETAIL = "movie_detail";
	public static void closeUp(ResultSet result, Statement statement){
		try {
			if(result != null)
				result.close();
			if(statement != null)
				statement.close();
		} catch (SQLException e2) {
			System.out.println(e2.getMessage());
		}
	}
	public static void rollBack(Connection conn){
		try {
			conn.rollback();
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
		}
	}
	public static void storeConnection(ServletRequest request, Connection conn) {
	       request.setAttribute(CONNECTION, conn);
	   }
	
	public static Connection getStoredConnection(ServletRequest request) {
	       Connection conn = (Connection) request.getAttribute(CONNECTION);
	       return conn;
	   }
	
	public static void storeLoginedUser(HttpSession session, Account loginedUser) {
	       session.setAttribute(LOGINED_USER, loginedUser);
	 }
	public static Person getLoginedUser(HttpSession session) {
		return  (Person) session.getAttribute(LOGINED_USER);
	}
	public static void removeLoginedUser(HttpSession session) {
		  session.removeAttribute(LOGINED_USER);
	}
	
	
	/*
	public static void storeMoviesDetail(HttpSession session, ArrayList<Movie> movieList) {
	       session.setAttribute(MOVIES_DETAIL, movieList);
	 }
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Movie>  getMoviesDetail(HttpSession session) {
		return  ( ArrayList<Movie>) session.getAttribute(MOVIES_DETAIL);
	}
	*/
	
}
