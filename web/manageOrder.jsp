<%-- 
    Document   : manageOrder
    Created on : 2016/11/25, 上午 11:56:19
    Author     : nanasemaru
--%>
<%@page import="cf.bean.OrderInfo"%>
<%@ page errorPage="error.jsp"%>
<%@taglib uri="/WEB-INF/tlds/orderlist" prefix="orderlist" %>
<jsp:useBean id="orders" class="java.util.ArrayList<OrderInfo>" scope="request"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <h2>Manage Order</h2>
            <div class="panel panel-default">
                <!--<div class="panel-body">-->
                
                <orderlist:orderListTag orders="<%=orders%>"/>
                <!--</div>-->
            </div>
        </div>
    </body>
</html>
