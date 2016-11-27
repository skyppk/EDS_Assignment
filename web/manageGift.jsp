<%-- 
    Document   : manageGift
    Created on : 2016/11/25, 下午 05:22:37
    Author     : apple
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cf.bean.GiftItem"%>

<%@ page errorPage="error.jsp"%>
<%@taglib uri="/WEB-INF/tlds/giftlist" prefix="giftlist" %>
<jsp:useBean id="gifts" class="java.util.ArrayList<GiftItem>" scope="request"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Customer</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <%
            System.out.println("test staff Info : "+staffInfo);
            if(staffInfo.getLoginId() == null )
            response.sendRedirect("error.jsp?msg=You have not permission to visit this page !");
        %>
        <div class="container">
            <h2>Manage Customer</h2>
            <div class="panel panel-default">
                <!--<div class="panel-body">-->

                <giftlist:giftListTag gifts="<%=gifts%>"/>
                <!--</div>-->
            </div>
        </div>
    </body>
</html>
