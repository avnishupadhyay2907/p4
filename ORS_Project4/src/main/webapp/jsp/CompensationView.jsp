<%@page import="com.rays.pro4.controller.CompensationCtl"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@page import="com.rays.pro4.Bean.StaffMemberBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title> Student Registration Page</title>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#udate4" ).datepicker({
      changeMonth: true,
      changeYear: true,
	  yearRange:'1980:2002', 
	  
    });
  } );
  </script>


</head>
<body>
    <jsp:useBean id="bean" class="com.rays.pro4.Bean.CompensationBean" scope="request"></jsp:useBean>
    	
	<form action="<%=ORSView.COMPENSATION_CTL%>" method="post">
    <%@include file="Header.jsp"%>
    
    <% 
    	List <StaffMemberBean> slist = (List <StaffMemberBean>)request.getAttribute("staffMemberList");
    
    %>
    
    <center>
        <h1>
        	<%
        		if( bean != null && bean.getId()>0){
        	%> 
        	<tr><th><font>Update Compensation</font></th></tr>
        	<% }else{%>
        	<tr><th><font>Add Compensation</font></th></tr>
        	<% }%>
        </h1>
		
		<div>
		<h3><font style="color: green"><%=ServletUtility.getSuccessMessage(request) %></font></h1>
		<h3><font style="color: red"><%=ServletUtility.getErrorMessage(request) %></font>
		</h1>
		</div>
		
		<input type="hidden" name="id" value="<%=bean.getId()%>">
		<input type="hidden" name="createdby" value="<%=bean.getCreatedBy()%>">
		<input type="hidden" name="modifiedby" value="<%=bean.getModifiedBy()%>">
		<input type="hidden" name="createddatetime" value="<%=bean.getCreatedDatetime()%>">
		<input type="hidden" name="modifieddatetime" value="<%=bean.getModifiedDatetime()%>">

	<table>
	
		<tr>
		<th align="left">Staff Member Name <span style="color: red">*</span> :</th>
		<td><%=HTMLUtility.getList("staffMemberName", String.valueOf(bean.getStaffMemberId()), slist) %>
		<td style="position: fixed"><font color="red" ><%=ServletUtility.getErrorMessage("staffMemberName", request)%></font>
		</td>
		</tr>
		
	  <tr><th style="padding: 3px"></th></tr>    	
		
		<tr>
		<th align="left">Payment Amount <span style="color: red">*</span> :</th>
		<td><input type="text" name="paymentAmount" placeholder="Enter Payment Amount" size="25" value="<%=DataUtility.getStringData(bean.getPaymentAmount())%>"></td>
		<td style="position: fixed"><font  color="red"><%=ServletUtility.getErrorMessage("paymentAmount", request)%></font>
		</td>
		</tr>
		
		  <tr><th style="padding: 3px"></th></tr>    
		
		
		 <tr>
		  <th align="left">Date Applied <span style="color: red">*</span> :</th>
          <td><input type="text" name="dateApplied" id="udate4" readonly="readonly" placeholder=" date Applied" size="25"  value="<%=DataUtility.getDateString(bean.getDateApplied())%>"></td> 
         <td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("dateApplied", request)%></font></td>
                </tr>
   
   <tr><th style="padding: 3px"></th></tr>    
	
		<tr>
		<th align="left">State <span style="color: red">*</span> :</th>
		<td><input type="text" name="state" maxlength="10" placeholder="Enter State" size="25" value="<%=DataUtility.getStringData(bean.getState())%>"></td>
		<td style="position: fixed" ><font color="red"><%=ServletUtility.getErrorMessage("state", request)%></font>
		</td>
		</tr>
	
		  <tr><th style="padding: 3px"></th></tr>    
	

	<tr>
	<th></th>
		<%
		if(bean.getId() > 0){ %>
		<td>
		 &nbsp;  &emsp;
		<input type="submit" name="operation" value="<%=CompensationCtl.OP_UPDATE %>">
		 &nbsp;  &nbsp;
		<input type="submit" name="operation" value="<%=CompensationCtl.OP_CANCEL%>"></td>
		<%}else{ %>
		<td>
		 &nbsp;  &emsp;
		<input type="submit" name="operation" value="<%=CompensationCtl.OP_SAVE %>">
		 &nbsp;  &nbsp;
		<input type="submit" name="operation" value="<%=CompensationCtl.OP_RESET%>"></td>
	
		<%} %>
	</tr>
	
	</table>
</form>
</center>

<%@ include file = "Footer.jsp" %>
</body></html>