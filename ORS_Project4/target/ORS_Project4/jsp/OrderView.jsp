<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="com.rays.pro4.Bean.CustomerBean"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@page import="com.rays.pro4.controller.OrderCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Order View</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function () {
            $("#udate4").datepicker({
                changeMonth: true,
                changeYear: true,
                yearRange: '1980:2022',
            });
        });
    </script>
</head>
<body>
    <jsp:useBean id="bean" class="com.rays.pro4.Bean.OrderBean" scope="request"></jsp:useBean>

    <form action="<%=ORSView.ORDER_CTL%>" method="post">
        <%@include file="Header.jsp"%>

        <%
            List<CustomerBean> clist = (List<CustomerBean>) request.getAttribute("customerList");
        %>

        <center>
            <h1>
                <%
                    if (bean != null && bean.getId() > 0) {
                %>
                Update Order
                <%
                    } else {
                %>
                Add Order
                <%
                    }
                %>
            </h1>

            <div>
                <h3 style="color: green"><%=ServletUtility.getSuccessMessage(request)%></h3>
                <h3 style="color: red"><%=ServletUtility.getErrorMessage(request)%></h3>
            </div>

            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdby" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedby" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createddatetime" value="<%=bean.getCreatedDatetime()%>">
            <input type="hidden" name="modifieddatetime" value="<%=bean.getModifiedDatetime()%>">

            <table>
                <tr>
                    <th align="left">Customer Name <span style="color: red">*</span>:</th>
                    <td>
                        <%=HTMLUtility.getList("customerId", String.valueOf(bean.getCustomerId()), clist)%>
                    </td>
                    <td style="color: red"><%=ServletUtility.getErrorMessage("customerName", request)%></td>
                </tr>

                <tr>
                    <th align="left">Order Date <span style="color: red">*</span>:</th>
                    <td>
                        <input type="text" name="orderDate" id="udate4" readonly="readonly" 
                               placeholder="Order Date" size="25" 
                               value="<%=DataUtility.getDateString(bean.getOrderDate())%>">
                    </td>
                    <td style="color: red"><%=ServletUtility.getErrorMessage("orderDate", request)%></td>
                </tr>

                <tr>
                    <th align="left">Total Amount <span style="color: red">*</span>:</th>
                    <td>
                        <input type="text" name="totalAmount" maxlength="10" 
                               placeholder="Enter Total Amount" size="25" 
                               value="<%=DataUtility.getStringData(bean.getTotalAmount())%>">
                    </td>
                    <td style="color: red"><%=ServletUtility.getErrorMessage("totalAmount", request)%></td>
                </tr>

                <tr>
                    <th></th>
                    <td>
                        <%
                            if (bean.getId() > 0) {
                        %>
                        <input type="submit" name="operation" value="<%=OrderCtl.OP_UPDATE%>">
                        <input type="submit" name="operation" value="<%=OrderCtl.OP_CANCEL%>">
                        <%
                            } else {
                        %>
                        <input type="submit" name="operation" value="<%=OrderCtl.OP_SAVE%>">
                        <input type="submit" name="operation" value="<%=OrderCtl.OP_RESET%>">
                        <%
                            }
                        %>
                    </td>
                </tr>
            </table>
        </center>
    </form>
    <%@ include file="Footer.jsp"%>
</body>
</html>
