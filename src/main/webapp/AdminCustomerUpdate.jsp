<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<head>
<meta charset="ISO-8859-1">
<title> Admin Customer Update</title>
<style type="text/css">
               
            body
            {       
                  background-image: url("bgimage.jpg");
                  background-repeat: no-repeat;
                  background-size: cover;
            }
            
            a
             {
             color:darkblue;
             text-decoration:none;
             font-family:arial;
             }	
             
             form
             {  
              
               margin:0px 600px;
               border-radius:20px;
               width:400px;
               height:600px;
               background-color: rgba(41,39,39,0.2);
               box-shadow: 0 5px 30px black;
               justify-content:center;
               display:flex; 
               flex-direction: column;
               align-items:center;
             } 
             
             input
             {
             background-color: whitesmoke;
             border-color: black;
             
             }
             
             label
             { 
                color: darkblue;
                font-family:arial;
                font-weight:bold;
                
             }
               
		
</style>
</head>
<body>
<% 

HttpSession hs=request.getSession();
Long account=(Long)hs.getAttribute("updateaccount");

Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","sagar");
PreparedStatement ps=con.prepareStatement("Select * from customer where accountno=?");
ps.setLong(1,account);
 ResultSet rs=ps.executeQuery();
 if(rs.next())
 {
%>
<form action="admincustomerupdate" method="post"> 
     
 <a href="AdminMenu.html"><span style="color:white">Back</span></a>
 <h2 align="center" style="color:darkred;  font-weight: bold;  font-family:verdana;" >UPDATE ACCOUNT</h2>
  
 <label>Name:</label>
<input type="text" name="name" required value="<%=rs.getString("name")%>"><br><br>
<label>Email:</label>
 <input type="text" name="email" required value="<%=rs.getString("email")%>"><br><br>
 <label>PhoneNo:</label>
 <input type="text" name="phoneno" required value="<%=rs.getString("phoneno")%>"><br><br>
 <label>Address:</label>
 <input type="text" name="address" required value="<%=rs.getString("address")%>"><br><br>
 <label>Pincode:</label>
 <input type="text" name="pnc" required value="<%=rs.getString("pincode")%>"><br><br>
 <input type="submit" value="Submit" style="background-color:darkblue; width:100px; height:30px; color:white; font-weight: bold;">
 </form>
<%
 }
 else
 {
	 response.setContentType("text/html");
	 PrintWriter pw=response.getWriter();
	 pw.print("<body style=Background-color:lightblue;><center><h3 style=color:red>Invalid Account Number<h3></center></body>");
	 RequestDispatcher rd=request.getRequestDispatcher("AdminHomepage.html");
	 rd.include(request, response); 
 }

%>
</body>
</html>