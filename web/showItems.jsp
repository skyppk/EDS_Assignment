<%-- 
    Document   : showItems
    Created on : 2016/11/22, 下午 11:19:09
    Author     : nanasemaru
--%>

<%@page import="cf.bean.ItemInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cf.db.ItemDB"%>
<%@taglib uri="/WEB-INF/tlds/items" prefix="items" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<ItemInfo> items = (ArrayList<ItemInfo>) request.getAttribute("items");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <div class="row">
                <items:showItems items="<%=items%>"/>
                
            </div>
        </div>
    </body>
</html>
