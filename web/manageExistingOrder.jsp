<%-- 
    Document   : manageExistingOrder
    Created on : 2016/11/25, 上午 04:15:02
    Author     : nanasemaru
--%>

<%@page import="cf.bean.OrderInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>C & F</title>
        <%
            ArrayList<OrderInfo> orders = (ArrayList<OrderInfo>) request.getAttribute("orders");
            String message = request.getParameter("message");
        %>

    </head>
    <body>
        <%@include file="menu.jsp" %>
        <%
            System.out.println("test staff Info : "+staffInfo);
            if(staffInfo.getLoginId() == null )
            response.sendRedirect("error.jsp?msg=You have not permission to visit this page !");
        %>
        <div class="container">
            <h2>Existing Order</h2>
            <div class="panel panel-default">
                <div class="table-responsive">
                    <table class="table">
                        <tr>
                            <th>Order ID</th>
                            <th>Order Date</th>
                            <th>Price</th>
                            <th>Delivery Date</th>
                            <th>Address</th>
                            <th>Change Delivery Date</th>
                            <th>Cancel Order</th>
                        </tr>
                        <% for (OrderInfo order : orders) {%>
                        <tr>
                            <td><%=order.getOrderId()%></td>
                            <td><%=order.getOrderDate()%></td>
                            <td>$ <%=order.getOrderPrice()%></td>
                            <td><%=order.getDeliveryDate()%> <%=order.getDeliveryTime()%></td>
                            <td><%=order.getDeliveryAddress()%></td>
                            <td><button class="btn btn-default" id="changeDate">Change Date</button></td>
                            <% if (order.getOrderPrice() > 10000) {%>
                            <td><button class="btn btn-default" id="Cancel" onclick="location.href = 'orderAction?action=cancel&id=<%=order.getLoginId()%>&order=<%=order.getOrderId()%>'">Cancel</button></td>
                            <% } else {%>
                            <td>Not Allow</td>
                            <% } %>
                        </tr>
                        <% } %>
                    </table>
                </div>
            </div>
        </div>
        <%
            if (message != null) {

                out.print("<script>alert(\"" + message + "\");</script>");
                System.out.println("NOT NULL!!!");
            }
        %>
    </body>
</html>
