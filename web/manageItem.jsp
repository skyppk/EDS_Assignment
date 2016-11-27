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
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">New Item</h4>
                    </div>
                    <div class="modal-body">
                        <form method="post" action="editItem?action=new" id="form1">
                            <div class="form-group">
                                <label for="itemId" class="form-control-label">Item ID:</label>
                                <input type="text" class="form-control" id="itemId" name="itemId" required>
                            </div>
                            <div class="form-group">
                                <label for="itemName" class="form-control-label">Item Name:</label>
                                <input type="text" class="form-control" id="itemName" name="itemName" required>
                            </div>
                            <div class="form-group">
                                <label for="category" class="form-control-label">Category:</label>
                                <input type="text" class="form-control" id="category" name="category" required>
                            </div>
                            <div class="form-group">
                                <label for="designer" class="form-control-label">Designer Name:</label>
                                <input type="text" class="form-control" id="designer" name="designer" required>
                            </div>
                            <div class="form-group">
                                <label for="price" class="form-control-label">Price:</label>
                                <div class="input-group">
                                    <span class="input-group-addon">$</span>
                                    <input type="number" class="form-control" id="price" name="price" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="desciptions" class="form-control-label">Desciptions:</label>
                                <textarea class="form-control" id="desciptions" name="desciptions" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="image" class="form-control-label">Image:</label>
                                <input type="text" class="form-control" id="image" name="image" placeholder="name.jpeg" required>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary" form="form1">Save changes</button>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="menu.jsp" %>
        <%
            System.out.println("test staff Info : " + staffInfo);
            if (staffInfo.getLoginId() == null) {
                response.sendRedirect("error.jsp?msg=You have not permission to visit this page !");
            }
        %>

        <div class="container">
            <h2>Manage Item<small class="pull-right"><button class="btn btn-default" data-toggle="modal" data-target="#myModal">New</button></small></h2>
            <div class="panel panel-default">
                <!--<div class="panel-body">-->

                <listitem:listItemTag items="<%=items%>"/>
                <!--</div>-->
            </div>
        </div>
    </body>
</html>
