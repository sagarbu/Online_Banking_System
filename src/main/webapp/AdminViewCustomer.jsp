<%@page import="java.io.PrintWriter"%>
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
<%
String accountno=request.getParameter("acn");
Long account=Long.parseLong(accountno);
HttpSession hs=request.getSession();
hs.setAttribute("updateaccount",account);
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","sagar");
PreparedStatement ps=con.prepareStatement("select* from customer where accountno=?");
ps.setLong(1, account);
ResultSet rs= ps.executeQuery();

%>
 <a href="AdminMenu.html"><span style="color:white">Back</span></a><br>
<table cellpadding="20px" border="2" align="center" style="color:red" >
<%if(rs.next()){ %>
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
<%}
else
{
	 response.setContentType("text/html");
	 PrintWriter pw=response.getWriter();
	 pw.print("<body style=Background-color:lightblue;><center> <h3 style=color:red>Enter Valid Account Number<h3></center></body>");
	 RequestDispatcher rd=request.getRequestDispatcher("AdminViewCustomer.html");
	 rd.include(request, response);
			    
}
%>

</table>

</body>
</html>