<%-- 
    Document   : orderDetail
    Created on : 2016/11/26, 下午 05:01:39
    Author     : nanasemaru
--%>
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
        <div class="container">
            <h2>Order Details (<%=order.getOrderId()%>)<small class="pull-right"><a href="javascript:history.back()" class="btn btn-default">Back</a></small></h2>
            <div class="panel panel-default">
                <div class="table-responsive">
                    <table class="table">
                        <tr>
                            <th>Item ID</th>
                            <th>Item Name</th>
                            <th>Quantity</th>
                            <th>Unit Price</th>
                            <th>Price</th>
                        </tr>
        <%
            ArrayList<OrderDetails> details = order.getOrderDetails();
            for (OrderDetails detail:details) {
        %>
            
            <tr>
                <td><%=detail.getItemId()%></td>
                <td><%=detail.getItemName()%></td>
                <td><%=detail.getQuantity()%></td>
                <td><%=detail.getBuyPrice()%></td>
                <td><%=detail.getDetailsPrice()%></td>
            </tr>
            <% } %>
        </div>
    </body>
</html>
