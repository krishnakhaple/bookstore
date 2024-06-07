package com.kk.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String query="insert into bookdata(BOOKNAME,BOOKEDITION,BOOKPRICE) VALUES(?,?,?)";
       
  
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		
		 PrintWriter pw=res.getWriter();
		 
		 String url="jdbc:mysql://localhost:3306/book";
		 String username = "root"; 
	     String password = "root"; 
	     
	     String bookname=req.getParameter("bookName");
	     String bookEdition=req.getParameter("bookEdition");
	     float bookprice=Float.parseFloat(req.getParameter("bookPrice"));
	     
	     try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				Connection con=DriverManager.getConnection(url, username, password);
				
				PreparedStatement ps=con.prepareStatement(query);
				ps.setString(1, bookname);
				ps.setString(2, bookEdition);
				ps.setFloat(3, bookprice);
				int count=ps.executeUpdate();
				
				if(count==1)
				{
					pw.println("<h2>"+"record registered successfully"  +"</h2>");
				}
				else
				{
					pw.println("<h2>"+"record not registered successfully"  +"</h2>");
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
	     
	    pw.println( "<a href='Home.html'>home</a>");
	    pw.println("<br>");
	    pw.println( "<a href='BookList'>BookList</a>");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
