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
@WebServlet("/BookList")
public class BookListServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;
	private static final String query="select id,bookname,bookedition,bookprice from bookdata";
       
  
    
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		
		 PrintWriter pw=res.getWriter();
		 
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
				
				ResultSet rs = ps.executeQuery();
				
				pw.println("<table border='1' align='center'>");
				pw.println("<tr>");
				pw.println("<th>Book id</th>");
				pw.println("<th>Book name</th>");
				pw.println("<th>Book edition</th>");
				pw.println("<th>Book price</th>");
				pw.println("<th>edit</th>");
				pw.println("<th>delete</th>");
				pw.println("</tr>");
				
				
				
				while(rs.next())
				{
					pw.println("<tr>");
					pw.println("<td>"+ rs.getInt(1)+"</td>");
					pw.println("<td>"+ rs.getString(2)+"</td>");
					pw.println("<td>"+ rs.getString(3)+"</td>");
					pw.println("<td>"+ rs.getFloat(4)+"</td>");
					pw.println("<td> <a href='editScreen?id="+rs.getInt(1)+"'>Edit</a></td>");
					pw.println("<td> <a href='deleteurl?id="+rs.getInt(1)+ "'>Delete</a></td>");
					pw.println("</tr>");
				}
				pw.println("</table>");
				
					
		
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
