<%-- 
    Document   : index
    Created on : 2016/11/19, 下午 06:33:10
    Author     : apple
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="accountType" class="java.lang.String" scope="session" />
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>C & F</title>
    </head>
    <body>
        <%      
            if (accountType == null || accountType.equals("")||accountType.equalsIgnoreCase("CUSTOMER")) {
        %>
        <jsp:forward page="product?action=all" />
        <%
        } else if (!accountType.equalsIgnoreCase("CUSTOMER") ) {
        %>
        <jsp:forward page="handleCustomer?action=listNew" />
        <%
        }
        %>
    </body>
</html>
