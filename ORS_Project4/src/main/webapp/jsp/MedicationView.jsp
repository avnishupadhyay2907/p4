<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.rays.pro4.controller.MedicationCtl"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<title>Medication Registration Page</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#udate4").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2007',

		});
	});
</script>


</head>
<body>
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.MedicationBean"
		scope="request"></jsp:useBean>

	<form action="<%=ORSView.MEDICATION_CTL%>" method="post">
		<%@include file="Header.jsp"%>
		<%
			HashMap map = (HashMap) request.getAttribute("illness1");
		%>


		<center>
			<h1>
				<%
					if (bean != null && bean.getId() > 0) {
				%>
				<tr>
					<th><font>Update Medication</font></th>
				</tr>
				<%
					} else {
				%>
				<tr>
					<th><font>Add Medication</font></th>
				</tr>
				<%
					}
				%>
			</h1>

			<div>
				<h3>
					<font style="color: green"><%=ServletUtility.getSuccessMessage(request)%></font>
					</h1>
					<h3>
						<font style="color: red"><%=ServletUtility.getErrorMessage(request)%></font>
						</h1>
			</div>

			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdby" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedby"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createddatetime" value="<%=bean.getCreatedDatetime()%>">
			<input type="hidden" name="modifieddatetime"
				value="<%=bean.getModifiedDatetime()%>">

			<table>





				<tr>
					<th align="left">FullName <span style="color: red">*</span> :
					</th>
					<td><input type="text" name="fullName"
						placeholder="Enter Full Name" size="25"
						value="<%=DataUtility.getStringData(bean.getFullName())%>"></td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("fullName", request)%></font>
					</td>
				</tr>

				<tr>
					<th style="padding: 3px"></th>
				</tr>

				<tr>
					<th align="left">Illness <span style="color: red">*</span> :
					</th>
					<td><%=HTMLUtility.getList2("illness", String.valueOf(bean.getIllness()), map)%></td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("illness", request)%></font></td>
				</tr>

				<tr>
					<th style="padding: 3px"></th>
				</tr>
				<tr>
					<th align="left">Prescription Date <span style="color: red">*</span>
						:
					</th>
					<td><input type="text" name="prescriptionDate" id="udate4"
						readonly="readonly" placeholder=" PrescriptionDate" size="25"
						value="<%=DataUtility.getDateString(bean.getPrescriptionDate())%>"></td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("prescriptionDate", request)%></font></td>
				</tr>

				<tr>
					<th style="padding: 3px"></th>
				</tr>
				<tr>
					<th align="left">Dosage <span style="color: red">*</span> :
					</th>
					<td><input type="text" name="dosage"
						placeholder="Enter Dosage" size="25"
						value="<%=DataUtility.getStringData(bean.getDosage())%>"></td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("dosage", request)%></font>
					</td>
				</tr>

				<tr>
					<th style="padding: 3px"></th>
				</tr>


				<tr>
					<th></th>
					<%
						if (bean.getId() > 0) {
					%>
					<td>&nbsp; &emsp; <input type="submit" name="operation"
						value="<%=MedicationCtl.OP_UPDATE%>"> &nbsp; &nbsp; <input
						type="submit" name="operation"
						value="<%=MedicationCtl.OP_CANCEL%>"></td>
					<%
						} else {
					%>
					<td>&nbsp; &emsp; <input type="submit" name="operation"
						value="<%=MedicationCtl.OP_SAVE%>"> &nbsp; &nbsp; <input
						type="submit" name="operation" value="<%=MedicationCtl.OP_RESET%>"></td>

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