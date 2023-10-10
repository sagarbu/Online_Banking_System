package online_banking_system;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/logincustomer")
public class LoginCustomer  extends HttpServlet{

	@Override
	 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  String email=req.getParameter("email");
		 String password=req.getParameter("password");
		  
		 
		    try {
				 Class.forName("com.mysql.cj.jdbc.Driver");
				 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","sagar");
				 PreparedStatement ps=con.prepareStatement("select * from customer where email=? and password=?");
				 ps.setString(1, email);
			 	 ps.setLong(2, Long.parseLong(password));
			 	 ResultSet rs=ps.executeQuery();
			 
				    if(rs.next())
					{
						 RequestDispatcher rd=req.getRequestDispatcher("CustomerMenu.html");
						 rd.forward(req, resp);
					}
					 
					else
					{    resp.setContentType("text/html");
						 PrintWriter pw=resp.getWriter();
						 pw.print("<body style=Background-color:lightblue;><center> <h3 style=color:red>Invalid Credentials <h3></center></body>");
						 RequestDispatcher rd=req.getRequestDispatcher("CustomerLogin.html");
						 rd.include(req, resp);
								    
				    }
				    }
					
				      
				 	 
		    catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
		
	}
}

	 


