<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.rays.pro4.Bean.MedicationBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@page import="com.rays.pro4.Model.MedicationModel"%>
<%@page import="com.rays.pro4.controller.MedicationListCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<title>Medication List</title>

<script src="<%=ORSView.APP_CONTEXT %>/js/jquery.min.js""></script>
<script src="<%=ORSView.APP_CONTEXT %>/js/Checkbox11.js"></script>


</head>
<body>
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.MedicationBean"
		scope="request"></jsp:useBean>


	<form action="<%=ORSView.MEDICATION_LIST_CTL%>" method="post">
		<%@include file="Header.jsp"%>
<%
HashMap map = (HashMap)request.getAttribute("illness1");
%>
		<center>

			<div align="center">
				<h1>Medication List</h1>
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>
			</div>



			<%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<MedicationBean> it = list.iterator();

	       			if(list.size() !=0){
                    %>

			<table width="100%" align="center">
				<tr align="center">
					<td align="center"><label>Full Name:</label> <input type="text"
						name="fullName" placeholder="Enter Full Name" Size="22"
						value="<%=ServletUtility.getParameter("fullName", request)%>">
						<!-- 	&nbsp;
                 --> 
						<!--     &nbsp;
 -->
						<label>Illness:</label>
						<%=
							

						HTMLUtility.getList2("illness",DataUtility.getString(bean.getIllness()), map)
						%> 
						<!--  &nbsp; --> <input type="submit" name="operation"
						value="<%=MedicationListCtl.OP_SEARCH%>"> <input
						type="submit" name="operation"
						value="<%=MedicationListCtl.OP_RESET%>"></td>
				</tr>
			</table>

			<br>

			<table border="1" width="100%" align="center" cellpadding=6px
				cellspacing=".2">
				<tr style="background: skyblue">
					<th><input type="checkbox" id="select_all" name="select">Select
						All.</th>

					<th>S No.</th>
					<th>Full Name.</th>
					<th>Illness.</th>
					<th>Prescription Date.</th>
					<th>Dosage.</th>
					<th>Edit</th>
				</tr>

				<%
                	while(it.hasNext())
                	{
                	 bean = it.next();
                %>



				<tr align="center">
					<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=bean.getId() %>">
					<td><%=index++%></td>
					<td><%=bean.getFullName()%></td>
					<td><%=map.get(Integer.parseInt(bean.getIllness()))%></td>
					<td><%=bean.getPrescriptionDate()%></td>
					<td><%=bean.getDosage()%></td>
					<td><a href="MedicationCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
                    }
                %>
			</table>
			<table width="100%">
				<tr>
					<%if(pageNo == 1){ %>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=MedicationListCtl.OP_PREVIOUS%>"> <%}else{ %>
					<td><input type="submit" name="operation"
						value="<%=MedicationListCtl.OP_PREVIOUS%>"></td>
					<%} %>

					<td><input type="submit" name="operation"
						value="<%=MedicationListCtl.OP_DELETE%>"></td>
					<td><input type="submit" name="operation"
						value="<%=MedicationListCtl.OP_NEW%>"></td>

					<% MedicationModel model = new MedicationModel();                  
                  %>
					<% if(list.size() < pageSize || model.nextPK()-1 == bean.getId()){ %>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=MedicationListCtl.OP_NEXT%>"></td>
					<%}else{ %>
					<td align="right"><input type="submit" name="operation"
						value="<%=MedicationListCtl.OP_NEXT%>"></td>
					<%} %>

				</tr>
			</table>
			<%}if(list.size() == 0){ %>
			<td align="center"><input type="submit" name="operation"
				value="<%=MedicationListCtl.OP_BACK%>"></td>
			<% } %>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</center>

	<%@include file="Footer.jsp"%>
</body>
</html>