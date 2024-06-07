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

@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static final String query="select bookname,bookedition,bookprice from bookdata where id=?";
       
  
    
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		
		 PrintWriter pw=res.getWriter();
		 
		 int id=Integer.parseInt(req.getParameter("id"));
		 
		 
		 String url="jdbc:mysql://localhost:3306/book";
		 String username = "root"; 
	     String password = "root"; 
	     
//	     String bookname=req.getParameter("bookName");
//	     String bookEdition=req.getParameter("bookEdition");
//	     float bookprice=Float.parseFloat(req.getParameter("bookPrice"));
	     
	     try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				Connection con=DriverManager.getConnection(url, username, password);
				
				PreparedStatement ps=con.prepareStatement(query);
				ps.setInt(1, id);
				
				ResultSet rs = ps.executeQuery();
				
				rs.next();
				pw.println("<form action='editurl?id="+id+"' method='post'>");
				pw.println("<table align='center'>");
				
				pw.println("<tr>");
				pw.println("<td>book name</td>");
				pw.println("<td><input type='text' name='bookname' value='"+rs.getString(1)+"'></td>");
				pw.println("</tr>");
				
				pw.println("<tr>");
				pw.println("<td>book edition</td>");
				pw.println("<td><input type='text' name='bookedition' value='"+rs.getString(2) +"'></td> ");
				pw.println("</tr>");
				
				pw.println("<tr>");
				pw.println("<td>book price</td>");
				pw.println("<td><input type='text' name='bookprice' value='"+ rs.getFloat(3)+"'></td>");
				pw.println("</tr>");
				
				pw.println("<tr>");
				pw.println("<td><input type='submit' value='Edit' > </td>");
				pw.println("<td><input type='reset' value='Cancel'></td>");
				
				pw.println("</tr>");
				pw.println("</table>");
				pw.println("</form>");
				
				
				
			
		
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
