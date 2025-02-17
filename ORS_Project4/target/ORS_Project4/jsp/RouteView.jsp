
<%@page import="com.rays.pro4.controller.RouteCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<title>Add Vehicle</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#udatee").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2020',
		});
	});
</script>
<body>
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.RouteBean"
		scope="request"></jsp:useBean>
	<%@ include file="Header.jsp"%>

	<center>

		<form action="<%=ORSView.ROUTE_CTL%>" method="post">

			<div align="center">
				<h1>

					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<tr>
						<th><font size="5px"> Update Vehicle </font></th>
					</tr>
					<%
						} else {
					%>
					<tr>
						<th><font size="5px"> Add Route </font></th>
					</tr>
					<%
						}
					%>
				</h1>

				<h3>
					<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>

			</div>

			<input type="hidden" name="id" value="<%=bean.getId()%>">

			<table>
				<tr>
					<th align="left">Name <span style="color: red">*</span> :
					</th>
					<td><input type="text" name="Name"
						placeholder="Enter Name" size="26"
						value="<%=DataUtility.getStringData(bean.getName())%>"></td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("Name", request)%></font></td>

				</tr>

				<tr>
					<th style="padding: 3px"></th>
				</tr>

				<tr>
					<th align="left">Start Date <span style="color: red">*</span>
						:
					</th>
					<td><input type="text" name="StartDate"
						placeholder="Enter Start Date" size="26" readonly="readonly"
						id="udatee"
						value="<%=DataUtility.getStringData(bean.getStartDate())%>">
					</td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("StartDate", request)%></font></td>
				</tr>
				<tr>
					<th style="padding: 3px"></th>
				</tr>

				<tr>
					<th align="left">Allowed Speed<span style="color: red">*</span>
						:
					</th>
					<td> <input type="text" name="AllowedSpeed" placeholder="Enter Allowed Speed" size="26"
               value="<%= DataUtility.getStringData(bean.getAllowedSpeed()) %>"/></td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("AllowedSpeed", request)%></font></td>
				</tr>
				<tr>
				<tr>
					<th style="padding: 3px"></th>
				</tr>
				<tr>
					<th align="left">VehicleId <span style="color: red">*</span>

					</th>
					<td>  <input type="text" name="VehicleId" placeholder="Enter Vehicle Id" size="26"
               value="<%= DataUtility.getStringData(bean.getVehicleId()) %>"/>
					</td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("VehicleId", request)%></font></td>
				</tr>
				<tr>
					<th style="padding: 3px"></th>
				</tr>


				<tr>
					<th></th>
					<%
						if (bean.getId() > 0) {
					%>
					 <td colspan="2">&nbsp; &nbsp;
        <input type="submit" name="operation" value="<%=RouteCtl.OP_UPDATE%>">&nbsp;
        <input type="submit" name="operation" value="<%=RouteCtl.OP_CANCEL%>">
    </td>

					<%
						} else {
					%>
 <td colspan="2">&nbsp; &nbsp;
        <input type="submit" name="operation" value="<%=RouteCtl.OP_SAVE%>">&nbsp;
        <input type="submit" name="operation" value="<%=RouteCtl.OP_RESET%>">
    </td>

					<%
						}
					%>
				</tr>
			</table>
		</form>
	</center>

	<%@ include file="Footer.jsp"%>
</body>
</html>
