<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.pro4.Bean.ProductBean"%>
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
	<h1 style="color: navy;" align="center	">Product List</h1>
	<form action="<%=ORSView.PRODUCT_LIST_CTL%>" method="post">



		<%
			List list = (List) request.getAttribute("list");
		%>

		<tr>
			<th>Product Name :</th>
			<td><input type="text" name="name" placeholder="serach name"></td>
			<td><input type="submit" name="operation" value="search"></td>

		</tr>


		<table border="1px" width="100%" align="center">
			<tr>

				<th>Click</th>
				<th>Id</th>
				<th>Product Name</th>
				<th>Category</th>
				<th>Price</th>
				<th>Stock Quantity</th>
				<th>Edit</th>

				<%
					Iterator it = list.iterator();
				%>

				<%
					while (it.hasNext()) {
						ProductBean bean = (ProductBean) it.next();
				%>
			
			<tr align="center">

				<td><input type="checkbox" id="selectall" name="ids"
					value="<%=bean.getId()%>"></td>
				<td><%=bean.getId()%></td>
				<td><%=bean.getName()%></td>

				<td><%=bean.getCategory()%></td>
				<td><%=bean.getPrice()%></td>
				<td><%=bean.getStockQuantity()%></td>
				<td><a href="<%=ORSView.PRODUCT_CTL%>?id=<%=bean.getId()%>"
					<%if (bean.getId() == RoleBean.ADMIN) {%> onclick="return false;"
					<%}%>>edit</a></td>

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