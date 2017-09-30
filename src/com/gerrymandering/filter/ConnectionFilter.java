package com.gerrymandering.filter;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.gerrymandering.connection.ConnectionUtils;
import com.gerrymandering.utils.GerrymanderingUtils;

@WebFilter( urlPatterns = { "/*" }, filterName = "connectionFilter" )
public class ConnectionFilter implements Filter {

    public ConnectionFilter() {
      
    }

	public void destroy() {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		 Connection connection = null;
         try {
            
        	 connection = ConnectionUtils.getConnection();
        	 connection.setAutoCommit(false);
        	 GerrymanderingUtils.storeConnection(request, connection);
             chain.doFilter(request, response);
             connection.commit();
         } catch (Exception e) {
             e.printStackTrace();
             ConnectionUtils.rollback(connection);
            
         } finally {
             ConnectionUtils.closeConnection(connection);
         }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	
	
	  
	 

}
