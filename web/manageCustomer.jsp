<%-- 
    Document   : manageCustomer
    Created on : 2016/11/23, 下午 11:35:14
    Author     : apple
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cf.bean.UserInfo"%>

<%@ page errorPage="error.jsp"%>
<%@taglib uri="/WEB-INF/tlds/manageCustomer" prefix="managecustomer" %>
<jsp:useBean id="allUsers" class="java.util.ArrayList<UserInfo>" scope="request"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Customer</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <h2>Manage Customer</h2>
            <div class="panel panel-default">
                <!--<div class="panel-body">-->
                
                <managecustomer:showCust customers="<%=allUsers%>"/>
                <!--</div>-->
            </div>
        </div>
    </body>
</html>
