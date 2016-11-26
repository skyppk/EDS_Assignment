<%-- 
    Document   : newRegister
    Created on : 2016/11/24, 上午 07:36:19
    Author     : apple
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cf.bean.UserInfo"%>
<%@ page errorPage="error.jsp"%>
<%@taglib uri="/WEB-INF/tlds/manageCustomer" prefix="managecustomer" %>
<jsp:useBean id="newRegister" class="java.util.ArrayList<UserInfo>" scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Register</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <%
            System.out.println("test staff Info : "+staffInfo);
            if(staffInfo.getLoginId() == null )
            response.sendRedirect("error.jsp?msg=You have not permission to visit this page !");
        %>
        <div class="container">
            <h2>New Register</h2>
            <div class="panel panel-default">
                <!--<div class="panel-body">-->

                <managecustomer:showCust customers="<%=newRegister%>"/>
                <!--</div>-->
            </div>
        </div>
    </body>
</html>
