<%-- 
    Document   : orderDetail
    Created on : 2016/11/26, 下午 05:01:39
    Author     : nanasemaru
--%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="cf.bean.OrderDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cf.bean.OrderInfo"%>
<jsp:useBean id="order" class="OrderInfo" scope="request"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>C & F</title>

    </head>
    <body>
        <%@include file="menu.jsp" %>
        <%
            if(userInfo.getLoginId() == null )
                response.sendRedirect("error.jsp?msg=You have not permission to visit this page !");
        %>
        <div class="container">
            <h2>Order Details (<%=order.getOrderId()%>)<small class="pull-right"><a href="javascript:history.back()" class="btn btn-default">Back</a></small></h2>
            <div class="panel panel-default">
                <div class="table-responsive">
                    <table class="table">
                        <tr>
                            <th></th>
                            <th>Item ID</th>
                            <th>Item Name</th>
                            <th>Quantity</th>
                            <th>Unit Price</th>
                            <th>Price</th>
                        </tr>
                        <%
                            ArrayList<OrderDetails> details = order.getOrderDetails();
                            DecimalFormat df = new DecimalFormat("$ #,##0.00");
                            for (OrderDetails detail : details) {
                        %>

                        <tr>
                            <td><img src="img/<%=detail.getImg()%>" alt="No Image" style="height: 50px;"></td>
                            <td style="vertical-align:middle;"><%=detail.getItemId()%></td>
                            <td style="vertical-align:middle;"><%=detail.getItemName()%></td>
                            <td style="vertical-align:middle;"><%=detail.getQuantity()%></td>
                            <td style="vertical-align:middle;"><%=detail.getBuyPrice()%></td>
                            <td style="vertical-align:middle;"><%=detail.getDetailsPrice()%></td>
                        </tr>
                        <% }%>
                    </table>
                </div>
            <div class="panel-body">
                <strong class="pull-right">Total: <%=df.format(order.getOrderPrice())%></strong>
            </div>
        </div>
    </body>
</html>
