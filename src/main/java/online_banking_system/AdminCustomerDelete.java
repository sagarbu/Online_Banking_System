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
import javax.servlet.http.HttpSession;
@WebServlet("/customerdelete")
public class AdminCustomerDelete extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  String accountno=req.getParameter("acn");
		    
		 try {
			     
			 
			     Class.forName("com.mysql.cj.jdbc.Driver");
			     Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","sagar");
			     PreparedStatement ps=con.prepareStatement("Select * from customer where accountno=?");
		 	     ps.setLong(1, Long.parseLong(accountno));
		 	     ResultSet rs= ps.executeQuery();
		 	     
		 	 if(rs.next())
		 	 {
		 		 PreparedStatement ps1=con.prepareStatement("Delete from customer where accountno=?");
		 	     ps1.setLong(1, Long.parseLong(accountno));
		 	     ps1.execute();
		 	     resp.setContentType("text/html");
		 		 PrintWriter pw=resp.getWriter();
		 	     pw.print("<body style=Background-color:lightblue;><center> <h3 style=color:red>Your Account Is Deleted Succesfully<h3></center></body>");
		 		 RequestDispatcher rd=req.getRequestDispatcher("AdminMenu.html");
		 		 rd.include(req, resp);	 
		 	 }
		 	 
		 	 else
		 	 {   resp.setContentType("text/html");
		 		 PrintWriter pw=resp.getWriter();
		 	     pw.print("<body style=Background-color:lightblue;><center> <h3 style=color:red>Enter Valid Account Number<h3></center></body>");
		 		 RequestDispatcher rd=req.getRequestDispatcher("AdminCustomerDelete.html");
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


