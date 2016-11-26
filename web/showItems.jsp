<%-- 
    Document   : showItems
    Created on : 2016/11/22, 下午 11:19:09
    Author     : nanasemaru
--%>

<%@page import="cf.bean.ItemInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cf.db.ItemDB"%>
<%@taglib uri="/WEB-INF/tlds/items" prefix="items" %>
<%@ page errorPage="error.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<ItemInfo> items = (ArrayList<ItemInfo>) request.getAttribute("items");
    String keyword = (String) request.getAttribute("keyword");
    String type = (String) request.getAttribute("type");
    String message = "";
    if (keyword != null) {
        if (!"".equalsIgnoreCase(keyword)) {
            message = "Searching Result of \"" + keyword + "\"";
            if (type != null) {
                if (!"all".equalsIgnoreCase(type)) {
                    message += " in " + type;
                }
            }
        }
    }
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
            <p><%=message%></p>
            <div class="dropdown">
                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    Sort by
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                    <li><a href="searchItem?action=asc&type=<%=type%>&keyword=<%=keyword%>">Increasing Name</a></li>
                    <li><a href="searchItem?action=desc&type=<%=type%>&keyword=<%=keyword%>">Decreasing Name</a></li>
                    <!--                    <li><a href="#">Something else here</a></li>
                                        <li role="separator" class="divider"></li>
                                        <li><a href="#">Separated link</a></li>-->
                </ul>
            </div>
            <p></p>
            <div class="row" style="display:flex; flex-wrap: wrap;">
                <items:showItems items="<%=items%>"/>
            </div>
        </div>
    </body>
</html>
