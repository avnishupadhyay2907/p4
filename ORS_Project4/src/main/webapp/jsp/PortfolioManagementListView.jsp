<%@page import="com.rays.pro4.Bean.PortfolioManagementBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.pro4.Model.PortfolioManagementModel"%>
<%@page import="com.rays.pro4.controller.PortfolioManagementListCtl"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<title>Portfolio List</title>



</head>
<body>

	<jsp:useBean id="bean"
		class="com.rays.pro4.Bean.PortfolioManagementBean" scope="request"></jsp:useBean>

	<form action="<%=ORSView.PORTFOLIO_MANAGEMENT_LIST_CTL%>" method="post">
		<%@include file="Header.jsp"%>

		<center>

			<div align="center">
				<h1>Portfolio List</h1>
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
				Iterator<PortfolioManagementBean> it = list.iterator();

				if (list.size() != 0) {
			%>

			<table width="100%" align="center">
				<tr>
					<td align="right"><label> Share Name:</label> <input
						type="text" name="portfolioName" placeholder="Enter Share Name"
						Size="22"
						value="<%=ServletUtility.getParameter("portfolioName", request)%>">
						<!-- 	&nbsp;
                 --> <label>Last Name:</label> <input type="text"
						name="lastName" placeholder="Enter last Name" Size="22"
						value="<%=ServletUtility.getParameter("lastName", request)%>">
						<!--     &nbsp;
 --> <label>EmailId:</label> <input type="text" name="email"
						placeholder="Enter Email_id" Size="22"
						value="<%=ServletUtility.getParameter("email", request)%>">
						<!--  &nbsp; --> <input type="submit" name="operation"
						value="<%=PortfolioManagementListCtl.OP_SEARCH%>"> <input
						type="submit" name="operation"
						value="<%=PortfolioManagementListCtl.OP_RESET%>"></td>
				</tr>
			</table>

			<br>

			<table border="1" width="100%" align="center" cellpadding=6px
				cellspacing=".2">
				<tr style="background: skyblue">
					<th><input type="checkbox" id="select_all" name="select">Select
						All.</th>

					<th>S No.</th>
					<th>Share Name.</th>
					<th>Invested Amount.</th>
					<th>Risk Level.</th>
					<th>Investment Strategy.</th>

					<th>Edit</th>
				</tr>

				<%
					while (it.hasNext()) {
							bean = it.next();
				%>



				<tr align="center">
					<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=bean.getId()%>">
					<td><%=index++%></td>
					<td><%=bean.getPortfolioName()%></td>
					<td><%=bean.getInitialInvestmentAmount()%></td>
					<td><%=bean.getRiskToleranceLevel()%></td>
					<td><%=bean.getInvestmentStrategy()%></td>

					<td><a href="PortfolioManagementCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
					<%
						if (pageNo == 1) {
					%>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=PortfolioManagementListCtl.OP_PREVIOUS%>"> <%
 	} else {
 %>
					<td><input type="submit" name="operation"
						value="<%=PortfolioManagementListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>

					<td><input type="submit" name="operation"
						value="<%=PortfolioManagementListCtl.OP_DELETE%>"></td>
					<td><input type="submit" name="operation"
						value="<%=PortfolioManagementListCtl.OP_NEW%>"></td>

					<%
						PortfolioManagementModel model = new PortfolioManagementModel();
					%>
					<%
						if (list.size() < pageSize || model.nextPk() - 1 == bean.getId()) {
					%>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled"
						value="<%=PortfolioManagementListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%>
					<td align="right"><input type="submit" name="operation"
						value="<%=PortfolioManagementListCtl.OP_NEXT%>"></td>
					<%
						}
					%>

				</tr>
			</table>
			<%
				}
				if (list.size() == 0) {
			%>
			<td align="center"><input type="submit" name="operation"
				value="<%=PortfolioManagementListCtl.OP_BACK%>"></td>
			<%
				}
			%>

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