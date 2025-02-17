<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@page import="com.rays.pro4.Bean.RouteBean"%>
<%@ page import="com.rays.pro4.controller.RouteListCtl"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />

<title>Route List</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>

<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#udate").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2002',
		});
	});
</script>

</head>
<body>
	<%@ include file="Header.jsp"%>

	<form action="<%=ORSView.ROUTE_LIST_CTL%>" method="post">
		<jsp:useBean id="bean" class="com.rays.pro4.Bean.RouteBean"
			scope="request"></jsp:useBean>
		<center>
			<div align="center">
				<h1>Route List</h1>
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>
			</div>

			<%List<RouteBean> proList = (List<RouteBean>)request.getAttribute("proList");
				int next = DataUtility.getInt(request.getAttribute("nextlist").toString());
				
			%>

			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List<RouteBean> list = (List<RouteBean>) ServletUtility.getList(request);
				Iterator<RouteBean> it = list.iterator();

				if (list.size() != 0) {
			%>
			<table width="100%" align="center">
				<tr>
					
					<label>Name</font>:</label> <input type="text" name="Name" placeholder="Enter Name" value="<%=ServletUtility.getParameter("Name", request) %>">
						&emsp;
						
						
						
						
												
						
						
						&nbsp; <input type="submit" name="operation" value="<%=RouteListCtl.OP_SEARCH %>"> &nbsp; 
						<input type="submit" name="operation" value="<%=RouteListCtl.OP_RESET%>">
				</tr>
			</table>
			<br>

			<table border="1" width="100%" align="center" cellpadding="6px"
				cellspacing=".2">
				<tr style="background: skyblue">
					<th><input type="checkbox" id="select_all" name="select">Select
						All</th>
					<th>Id</th>
					<th>Name</th>
					<th>Start Date</th>
					<th>Allowed Speed</th>
					<th>Vehicle Id</th>
					<th>Edit</th>
				</tr>

				<%
					while (it.hasNext()) {
							bean = it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getStartDate()%></td>
					<td><%=bean.getAllowedSpeed()%></td>
					<td><%=bean.getVehicleId()%></td>
					<td><a href="RouteCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>

			<table width="100%">
				<tr>
					<th></th>
					<%
						if (pageNo == 1) {
					%>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=RouteListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=RouteListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>
					<td><input type="submit" name="operation"
						value="<%=RouteListCtl.OP_DELETE%>"></td>
					<td><input type="submit" name="operation"
						value="<%=RouteListCtl.OP_NEW%>"></td>
					<td align="right"><input type="submit" name="operation"
						value="<%=RouteListCtl.OP_NEXT%>"
						<%=(list.size() < pageSize || next == 0) ? "disabled" : ""%>></td>
				</tr>
			</table>
			<%
				}
				if (list.size() == 0) {
			%>
			<td align="center"><input type="submit" name="operation"
				value="<%=RouteListCtl.OP_BACK%>"></td>
			<%
				}
			%>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</center>
	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>