<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@page import="com.rays.pro4.controller.PortfolioManagementCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<!-- jQuery Library -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<!-- jQuery UI Library -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- Our custom JavaScript file -->
<script src="/Project04/js/datepicker.js"></script>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<form action="<%=ORSView.PORTFOLIO_MANAGEMENT_CTL%>" method="post">

		<jsp:useBean id="bean"
			class="com.rays.pro4.Bean.PortfolioManagementBean" scope="request"></jsp:useBean>



		<div align="center">
			<h1>
				<font color="navy"> <%
 	if (bean != null && bean.getId() > 0) {
 %> Update <%
 	} else {
 %> Add <%
 	}
 %> Portfolio
				</font>
			</h1>




			<h3>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</h3>
			<h3>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</h3>

			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			<table>

				<tr>
					<th>Portfolio Name :</th>
					<td><input type="text" name="portfolioName"
						placeholder="Enter Portfolio Name "
						value="<%=DataUtility.getStringData(bean.getPortfolioName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("portfolioName", request)%></font></td>
				</tr>

				<tr>
					<th>Initial Investment Amount :</th>
					<td><input type="text" name="initialInvestmentAmount"
						placeholder="Enter Initial Investment Amount	"
						value="<%=DataUtility.getStringData(bean.getInitialInvestmentAmount())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("initialInvestmentAmount", request)%></font></td>
				</tr>

				<tr>
					<th>Risk Tolerance Level :</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("Low", "Low");
							map.put("Medium", "Medium");
							map.put("High", "High");
						%> <%=HTMLUtility.getList("riskToleranceLevel", bean.getRiskToleranceLevel(), map)%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("riskToleranceLevel", request)%></font></td>
				</tr>

				<tr>
					<th>Investment Strategy :</th>
					<td><input type="text" name="investmentStrategy"
						placeholder="Select Investment Strategy"
						value="<%=DataUtility.getStringData(bean.getInvestmentStrategy())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("investmentStrategy", request)%></font></td>
				</tr>





				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=PortfolioManagementCtl.OP_UPDATE%>">
						<input type="submit" name="operation"
						value="<%=PortfolioManagementCtl.OP_CANCEL%>"> <%
 	} else {
 %>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=PortfolioManagementCtl.OP_SAVE%>">
						<input type="submit" name="operation"
						value="<%=PortfolioManagementCtl.OP_RESET%>"> <%
 	}
 %>
				</tr>

			</table>
		</div>
	</form>

</body>
</html>