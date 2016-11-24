<%-- 
    Document   : login
    Created on : 2016/11/20, 下午 01:55:45
    Author     : nanasemaru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="message" class="java.lang.String" scope="request"/>
<%@ page errorPage="error.jsp"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <h2>Login</h2>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form method="post" action="login">
                        <input type="hidden" name="action" value="authenticate">
                        <div class="form-group">
                            <label for="username">Userame:</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <!--                        <div class="checkbox">
                                                    <label><input type="checkbox"> Remember me</label>
                                                </div>-->
                        <div>
                            <label style="color: red"><%=message%></label>
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
