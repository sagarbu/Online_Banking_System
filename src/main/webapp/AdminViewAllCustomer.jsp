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
<title>View All</title>
<style type="text/css">
td{color:blue;}
 
</style>
</head>
<body style="Background-color:lightblue;">
<%
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","sagar");
Statement st= con.createStatement();
ResultSet rs= st.executeQuery("Select * from customer");
%>
<a href="AdminMenu.html"><span style="color:white">Back</span></a><br>
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
<th>Delete</th>
<th>Update</th>
<%while(rs.next()){ %>
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
<td><a href="AdminCustomerDelete.jsp?account=<%=rs.getLong("accountno")%>">Delete</a> </td>
<td><a href="AdminCustomerUpdate.jsp?account=<%=rs.getLong("accountno")%>">Update</a> </td>
</tr>
<%} %>

</table>

</body>
</html>