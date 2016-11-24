<%-- 
    Document   : orderHistory
    Created on : 2016/11/25, 上午 02:12:19
    Author     : nanasemaru
--%>

<%@page import="cf.bean.OrderInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page errorPage="error.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    ArrayList<OrderInfo> orders = (ArrayList<OrderInfo>) request.getAttribute("orders");
    System.out.println("damnnn"+orders.size());
    
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <h2>Order History</h2>
            <div class="panel panel-default">
                <div class="table-responsive">
                    <table class="table">
                        <tr>
                            <th>Order ID</th>
                            <th>Order Date</th>
                            <th>Price</th>
                            <th>Delivery Date</th>
                            <th>Address</th>
                        </tr>
                        <% for (OrderInfo order:orders) {%>
                        <tr>
                            <td><%=order.getOrderId()%></td>
                            <td><%=order.getOrderDate()%></td>
                            <td><%=order.getOrderPrice()%></td>
                            <td><%=order.getDeliveryDate()%></td>
                            <td><%=order.getDeliveryAddress()%></td>
                        </tr>
                                    <% } %>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
