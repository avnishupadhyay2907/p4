<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.pro4.Bean.CustomerBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<h1 style="color: navy;" align="center	">Customer List</h1>
	<form action="<%=ORSView.CUSTOMER_LIST_CTL%>" method="post">



		<%
			List list = (List) request.getAttribute("list");
		%>
		<div width="100%" align="center">
			<tr>
				<th><font style="color:red">First Name :</font></th>
				<td><input type="text" name="firstName"
					placeholder="serach FirstName"></td>
				<td><input type="submit" name="operation" value="search"></td>
			</tr>

			<tr >
    <th style="color: red;">Last Name :</th>
				<td><input type="text" name="lastName"
					placeholder="serach Last Name"></td>
				<td><input type="submit" name="operation" value="search"></td>
			</tr>
			</div>
		<table border="1px" width="100%" align="center">
			<tr>

				<th>Click</th>
				<th>Id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Phone</th>
				<th>Email</th>
				<!-- 				<th>Password</th>
 -->

				<%
					Iterator it = list.iterator();
				%>

				<%
					while (it.hasNext()) {
						CustomerBean bean = (CustomerBean) it.next();
				%>
			
			<tr align="center">

				<td><input type="checkbox" id="selectall" name="ids"
					value="<%=bean.getId()%>"></td>
				<td><%=bean.getId()%></td>
				<td><%=bean.getFirstName()%></td>

				<td><%=bean.getLastName()%></td>
				<td><%=bean.getPhone()%></td>
				<td><%=bean.getEmail()%></td>


				<%
					}
				%>
			
		</table>
		<tr>
			<th></th>
			<td><input type="submit" name="operation" value="Previous"></td>
		</tr>

		<tr>
			<th></th>
			<td><input type="submit" name="operation" value="Delete"></td>
		</tr>

		<tr>
			<th></th>
			<td><input type="submit" name="operation" value="Next"></td>
		</tr>

		<tr>
			<th></th>
			<td><input type="submit" name="operation" value="New"></td>
		</tr>
	</form>
</body>
</html>