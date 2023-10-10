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
@WebServlet("/customerpassword")
public class CustomerPasswordChange extends HttpServlet{
	
	  @Override
	   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String account=req.getParameter("acn");
		 String oldPassword=req.getParameter("opwd");
		 String newPassword=req.getParameter("npwd");
		 
		 
		     try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","sagar");
				PreparedStatement ps=con.prepareStatement("select* from customer where accountno=?  ");
			    ps.setLong(1, Long.parseLong(account));
			    
			    ResultSet rs1=ps.executeQuery();
			    if(rs1.next())
			    {
			    
			    	PreparedStatement ps1=con.prepareStatement("select * from customer where password=?");
			        ps1.setLong(1, Long.parseLong(oldPassword));
			        ResultSet rs=ps1.executeQuery();
			      
			        if(rs.next())
			        {   
			        	 
			        	PreparedStatement ps2=con.prepareStatement("update  customer set password=? where accountno=?");
				        ps2.setLong(1,  Long.parseLong(newPassword));
			        	ps2.setLong(2, Long.parseLong(account));
			        	ps2.execute();
			        	
			        	 resp.setContentType("text/html");
				 		 PrintWriter pw=resp.getWriter();
				 	     pw.print("<body style=Background-color:lightblue;><center> <h3 style=color:red>Your Password Get Changed<h3></center></body>");
				 		 RequestDispatcher rd=req.getRequestDispatcher("CustomerHomePage.html");
				 		 rd.include(req, resp);	
			        }
			        else
			        {
			        	 resp.setContentType("text/html");
				 		 PrintWriter pw=resp.getWriter();
				 	     pw.print("<body style=Background-color:lightblue;><center> <h3 style=color:red>Invalid Password<h3></center></body>");
				 		 RequestDispatcher rd=req.getRequestDispatcher("CustomerPasswordChange.html");
				 		 rd.include(req, resp);	
			        }
			        
			    }
			    else
			    {
			    	 resp.setContentType("text/html");
			 		 PrintWriter pw=resp.getWriter();
			 	     pw.print("<body style=Background-color:lightblue;><center> <h3 style=color:red>Invalid Account Number<h3></center></body>");
			 		 RequestDispatcher rd=req.getRequestDispatcher("CustomerPasswordChange.html");
			 		 rd.include(req, resp);
			    }
			    
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			
			
	}

}






