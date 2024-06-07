package com.kk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final String query="update bookdata set bookname=?,bookedition=?,bookprice=?  where id=?";
       
  
    
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		
		 PrintWriter pw=res.getWriter();
		 
		 int id=Integer.parseInt(req.getParameter("id"));
		 
		 //get the data which we want to edit

		 
		 
		 
		 String url="jdbc:mysql://localhost:3306/book";
		 String username = "root"; 
	     String password = "root"; 
	     
		
		 
		 String bookname=req.getParameter("bookname");
		 String bookedition=req.getParameter("bookedition");
		 float price=Float.parseFloat(req.getParameter("bookprice"));
		 
	     
	     try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				Connection con=DriverManager.getConnection(url, username, password);
				
				PreparedStatement ps=con.prepareStatement(query);
				ps.setString(1, bookname);
				ps.setString(2, bookedition);
				ps.setFloat(3, price);
				ps.setInt(4, id);
				
				int count= ps.executeUpdate();
				
				if(count==1)
				{
					pw.println("<h3>record is edited successfully </h3>");
				}
				else
				{
					
					pw.println("<h3>record is not  edited successfully </h3>");
				}
				
				
			
		
			} catch (SQLException e) {
			
				e.printStackTrace();
				pw.print("<h2> "+e.getMessage()  +" </h2>");
			}
			
		} catch (ClassNotFoundException e)
	     {
			
			e.printStackTrace();
			pw.print("<h2> "+e.getMessage()  +" </h2>");
		}
	        
	     pw.println( "<a href='Home.html'>"+ "Home"+ "</a>");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
