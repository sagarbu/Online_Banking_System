package online_banking_system;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet("/saveadmin")

public class SaveAdmin extends GenericServlet{
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    Random random=new Random();
    int id=random.nextInt(10);
    String name=req.getParameter("name");
    String email=req.getParameter("email");
    String phoneno=req.getParameter("phoneno");
    String password=req.getParameter("pwd");
  
    
    try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","sagar");
		
		PreparedStatement ps1=con.prepareStatement("select * from admin where id=?");
		ps1.setInt(1, id);
		ResultSet rs=ps1.executeQuery();
		if(rs.next())
		{
			res.setContentType("text/html");
			PrintWriter pw=res.getWriter();
			pw.print("<body style=Background-color:lightblue ;><center><h3 style=color:red top:50%>The Enter ID Is Alredy Exist<h3></center></body>");
			RequestDispatcher rd=req.getRequestDispatcher("AdminSignUp.html");
			rd.include(req, res);
		}
		else
		{
		PreparedStatement ps=con.prepareStatement("insert into admin(id,name,email,phone,password)values(?,?,?,?,?)");
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setString(3, email);
		ps.setLong(4,Long.parseLong(phoneno));
		ps.setString(5, password);
		ps.execute();
		
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		pw.print("<body style=Background-color:lightblue ;><center><h3 style=color:red top:50%>The Account get Created<h3></center></body>");
		RequestDispatcher rd=req.getRequestDispatcher("AdminHomepage.html");
		rd.include(req, res);
		 
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
  