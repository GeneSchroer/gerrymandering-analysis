package com.gerrymandering.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gerrymandering.beans.Person;
import com.gerrymandering.constants.ConstantAttributes;
import com.gerrymandering.constants.ConstantPath;
import com.gerrymandering.utils.GerrymanderingUtils;
import com.gerrymandering.utils.UpdateUtils;
import com.mysql.jdbc.Connection;


@WebServlet(urlPatterns = {ConstantPath.ADD_USER_JSP })

public class RegisterUser extends HttpServlet{


private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	
//		Person person = GerrymanderingUtils.getLoginedUser(request.getSession());
//		if(person == null){
//    		//redirect to the login page if the verification failed 
//    		response.sendRedirect(request.getContextPath() + ConstantPath.LOGIN_URL);		
//    	}
//		else{
//			String role = person.getRole();
//			if(role.equals(ConstantAttributes.MANAGER)){
//					addEmployeeToDB(request,response);
//			}
//			else
//				response.sendRedirect(request.getContextPath() + ConstantPath.LOGIN_URL);		
//			
//		}
//		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(ConstantPath.ADD_USER_URL);
		dispatcher.forward(request, response);
		addUsertoDB(request,response);
    }
	
	private void addUsertoDB(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {		
    	
		Connection connection = (Connection) GerrymanderingUtils.getStoredConnection(request);
    	RequestDispatcher dispatcher = null;
		String firstName = request.getParameter("firstName");
		String lastName  = request.getParameter("lastName");
		String address = request.getParameter("address");
		String zipcode = request.getParameter("zipcode");
		String phoneNumber = request.getParameter("telephone");
		String email = request.getParameter("email");
//		String date = request.getParameter("date");
//		String role = request.getParameter("role");
		Person newPerson = new Person();
		
		newPerson.setFirstName(firstName);
		newPerson.setLastName(lastName);
		newPerson.setAddress(address);
		newPerson.setZipCode(zipcode);
		newPerson.setTelephone(phoneNumber);
		newPerson.setEmail(email);
		newPerson.setRole("user");
		
				
		boolean status = UpdateUtils.addPersonInfo(connection, newPerson);
		int statusCode = status == true? 1:0;
		System.out.println(status);
	
		request.setAttribute(ConstantAttributes.STATUS, statusCode);	
		dispatcher =  request.getServletContext().getRequestDispatcher(ConstantPath.ADD_USER_URL);
		dispatcher.forward(request, response);
	}
	
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}