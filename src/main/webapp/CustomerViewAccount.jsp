<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
 
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View customer</title>
<style type="text/css">
td{color:blue;}
 
</style>
</head>
<body style="Background-color:lightblue;">
<a href="CustomerHomePage.html"><span style="color:black">Back</span></a><br><br>	
  
<%
HttpSession hs=request.getSession();
Long account=(Long)hs.getAttribute("account");
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","sagar");
PreparedStatement ps=con.prepareStatement("select* from customer where accountno=?");
ps.setLong(1, account);
ResultSet rs= ps.executeQuery();
%>
<table cellpadding="20px" border="2" align="center" style="color:red" >
<th>ID</th>
<th>Name</th>
<th>Email</th>
<th>Phoneno</th>
<th>Address</th>
<th>Pincode</th>
<th>Balance</th>
<th>AccountNo</th>
<th>Password</th>
<%if(rs.next()){ %>
<tr>
<td><%=rs.getInt(1)%></td>
<td><%=rs.getString(2) %></td>
<td><%=rs.getString(3)%></td>
<td><%=rs.getLong(4) %></td>
<td><%=rs.getString(5) %></td>
<td><%=rs.getInt(6) %></td>
<td><%=rs.getDouble(7) %></td>
<td><%=rs.getLong(8) %></td>
<td><%=rs.getLong(9) %></td>
</tr>
<%} %>

</table>

</body>
</html>