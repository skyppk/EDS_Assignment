<%-- 
    Document   : manageItem
    Created on : 2016/11/25, 上午 07:11:44
    Author     : apple
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cf.bean.ItemInfo"%>
<%@page import="cf.bean.StaffInfo"%>
<%@ page errorPage="error.jsp"%>
<%@taglib uri="/WEB-INF/tlds/listitem" prefix="listitem" %>
<jsp:useBean id="items" class="java.util.ArrayList<ItemInfo>" scope="request"/>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Item</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <%
            System.out.println("test staff Info : "+staffInfo);
            if(staffInfo.getLoginId() == null )
            response.sendRedirect("error.jsp?msg=You have not permission to visit this page !");
        %>
        
        <div class="container">
            <h2>Manage Item</h2>
            <div class="panel panel-default">
                <!--<div class="panel-body">-->

                <listitem:listItemTag items="<%=items%>"/>
                <!--</div>-->
            </div>
        </div>
    </body>
</html>
