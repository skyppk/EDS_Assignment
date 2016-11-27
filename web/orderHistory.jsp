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
    
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <%
            if(userInfo.getLoginId() == null )
                response.sendRedirect("error.jsp?msg=You have not permission to visit this page !");
        %>
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
                            <th>Status</th>
                            <th>Detail</th>
                        </tr>
                        <% for (OrderInfo order:orders) {%>
                        <tr>
                            <td><%=order.getOrderId()%></td>
                            <td><%=order.getOrderDate()%></td>
                            <td>$ <%=order.getOrderPrice()%></td>
                            <td><%=order.getDeliveryDate()%> <%=order.getDeliveryTime()%></td>
                            <td><%=order.getDeliveryAddress()%></td>
                            <td><%=order.getOrderStatus()%></td>
                            <td><button class="btn btn-default" id="Cancel" onclick="location.href = 'orderAction?action=detail&id=<%=order.getLoginId()%>&order=<%=order.getOrderId()%>'">Details</button></td>
                        </tr>
                                    <% } %>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
