package online_banking_system;

 
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/savecustomer")
public class SaveCustomer extends HttpServlet{
  
	 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 
		Random random=new Random();
		int id=random.nextInt(10);
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String address=req.getParameter("address");
		String phoneno=req.getParameter("phoneno"); 
		String pincode=req.getParameter("pnc");
		String balance=req.getParameter("balance");
		long password=random.nextLong(100000);
		 
		 long account=random.nextLong(100000000000l);
		 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","sagar");
			
			PreparedStatement ps=connection.prepareStatement("select * from customer where id=?");
			ps.setInt(1,  id);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{    
				resp.setContentType("text/html");
				PrintWriter pw=resp.getWriter();
				pw.print("<body style=Background-color:lightblue ;><h3 style=color:red >The Id already exist<h3></body>");
				RequestDispatcher rd=req.getRequestDispatcher("CustomerSignUp.html");
				rd.include(req, resp);
			}
			else
			{
				PreparedStatement ps1=connection.prepareStatement("insert into customer(id,name,email,phoneno,accountno,pincode,balance,address,password)values(?,?,?,?,?,?,?,?,?)");
				ps1.setInt(1,  id);
				ps1.setString(2, name);
				ps1.setString(3, email);
				ps1.setString(4, phoneno);
				ps1.setLong(5, account);
				ps1.setInt(6, Integer.parseInt(pincode));
				ps1.setDouble(7, Double.parseDouble(balance));
				ps1.setString(8, address);
				ps1.setLong(9, password);
				ps1.execute();
			    resp.setContentType("text/html");
			    PrintWriter pw=resp.getWriter();
			    pw.print("<body style=Background-color:lightblue ;><center><h3 style=color:red>Your Account Get Created<h3></center></body>");
			    RequestDispatcher rd=req.getRequestDispatcher("CustomerLogin.html");
			    rd.include(req, resp);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}