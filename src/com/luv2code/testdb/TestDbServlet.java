package com.luv2code.testdb;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response ) throws ServletException{
		//setup connection variables
		
		String user = "admin";
		String pass = "Daniel900628";		
				
		String jdbcUrl= "jdbc:mysql://mysqltest.cbevecjbhf34.us-east-1.rds.amazonaws.com/web_customer_tracker?useSSL=false";
		String driver = "com.mysql.jdbc.Driver";
		//get connection to database
		try {
			PrintWriter out = response.getWriter();
			out.println("Connection to database "+ jdbcUrl);
			Class.forName(driver);
			
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			out.println("SUCCESS!!!");
			myConn.close();
			
		}catch(Exception exc) {
			exc.printStackTrace();
			throw new ServletException(exc);
		}
		
		
		
		
	}
	
}
