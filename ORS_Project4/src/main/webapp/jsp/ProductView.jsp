<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@page import="com.rays.pro4.controller.ProductCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- jQuery Library -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<!-- jQuery UI Library -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- Our custom JavaScript file -->
<script src="/Project04/js/datepicker.js"></script>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<form action="<%=ORSView.PRODUCT_CTL%>" method="post">

		<jsp:useBean id="bean" class="com.rays.pro4.Bean.ProductBean"
			scope="request"></jsp:useBean>



		<div align="center">
		 <h1><font color="navy">
                <%
					if (bean != null && bean.getId() > 0) {
				%>
				Update
				<%
					} else {
				%>
				Add
				<%
					}
				%>
				Product
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
					<th>Product Name :</th>
					<td><input type="text" name="name"
						placeholder="Enter Product Name"
						value="<%=DataUtility.getStringData(bean.getName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>
				<tr>

					<th>Category :</th>
					<td><input type="text" name="category"
						placeholder="Select Category "
						value="<%=DataUtility.getStringData(bean.getCategory())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("category", request)%></font></td>
				</tr>


				<tr>
					<th>Price :</th>
					<td><input type="text" name="price"
						placeholder="Enter Price"
						value="<%=DataUtility.getStringData(bean.getPrice())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("price", request)%></font></td>
				</tr>
				<tr>
					<th>Quantity Available :</th>
					<td><input type="text" name="stockQuantity"
						placeholder="Enter Price"
						value="<%=DataUtility.getStringData(bean.getStockQuantity())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("stockQuantity", request)%></font></td>
				</tr>
 <tr>
                    <th></th>
                    <%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2">
					<input type="submit" name="operation" value="<%=ProductCtl.OP_UPDATE%>">
					<input type="submit" name="operation" value="<%=ProductCtl.OP_CANCEL%>">
						<%
							} else {
						%>
					<td align="left" colspan="2">
					<input type="submit" name="operation" value="<%=ProductCtl.OP_SAVE%>">
					<input type="submit" name="operation" value="<%=ProductCtl.OP_RESET%>">
						<%
							}
						%>
                </tr>
				 
			</table>
		</div>
	</form>

</body>
</html>