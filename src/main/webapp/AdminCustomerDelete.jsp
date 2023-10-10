<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Customer Delete</title>
</head>
<body>

<%
HttpSession hs=request.getSession();
Long account=(Long)hs.getAttribute("updateaccount");
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","sagar");
PreparedStatement ps=con.prepareStatement("Delete  from customer where accountno=?");
ps.setLong(1,account);
ps.execute();

response.setContentType("text/html");
 PrintWriter pw=response.getWriter();
 pw.print("<body style=Background-color:lightblue;><center> <h3 style=color:red>Customer Deleted Successfully<h3></center></body>");
 RequestDispatcher rd=request.getRequestDispatcher("AdminMenu.html");
 rd.include(request, response);	

%>
 

</body>
</html>