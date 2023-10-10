package online_banking_system;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/admincustomerupdate")
public class AdminCustomerUpdate extends HttpServlet {
	
	@Override
	protected void doPost	(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		 String name=req.getParameter("name");
		 String email=req.getParameter("email");
		 String phoneno=req.getParameter("phoneno");
		 String address=req.getParameter("address");
		 String pincode=req.getParameter("pnc");
		 
		 HttpSession hs=req.getSession();
		 Long account=(Long)hs.getAttribute("updateaccount");
	 
		 try {

		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","sagar");
		        PreparedStatement ps=con.prepareStatement("update customer set name=?, email=?,  phoneno=? ,address=? ,pincode=? where accountno=?");
				ps.setString(1,name);
				ps.setString(2, email);
				ps.setDouble(3, Double.parseDouble(phoneno));
				ps.setString(4, address);
				ps.setInt(5, Integer.parseInt(pincode));
				ps.setLong(6,account);
				ps.executeUpdate();
				
				 resp.setContentType("text/html");
		 		 PrintWriter pw=resp.getWriter();
		 	     pw.print("<body style=Background-color:lightblue;><center><h3 style=color:red>Customer Data Updated Successfully<h3></center></body>");
		 		 RequestDispatcher rd=req.getRequestDispatcher("AdminMenu.html");
		 		 rd.include(req, resp);	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
}



