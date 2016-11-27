<%-- 
    Document   : editProfile
    Created on : 2016/11/24, 下午 12:31:55
    Author     : nanasemaru
--%>

<jsp:useBean id="message" class="java.lang.String" scope="request"/>
<%@ page errorPage="error.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>C & F</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <%
            if(userInfo.getLoginId() == null )
                response.sendRedirect("error.jsp?msg=You have not permission to visit this page !");
        %>
        <div class="container">
            <h2>Edit Profile</h2>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form method="post" action="editCustomer">
                        <div class="form-group">
                            <label for="tel">Tel</label>
                            <input type="tel" class="form-control" id="tel" name="tel" value="<%=userInfo.getTel()%>" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" id="email" name="email" value="<%=userInfo.getEmail()%>" required>
                        </div>
                        <div class="form-group">
                            <label for="address">Address</label>
                            <input type="address" class="form-control" id="address" name="address" value="<%=userInfo.getAddress()%>" required>
                        </div>
                        <div>
                            <%=message%>
                        </div>
                        <div class="form-inline pull-right">
                            <input type="hidden" name="action" value="profile">
                            <input type="hidden" name="user" value="<%=userInfo.getLoginId()%>">
                            <input type="hidden" name="pwd" value="<%=userInfo.getPassword()%>">
                            <button type="reset" class="btn btn-default" id="pwd">Reset</button>
                            <button type="submit" class="btn btn-default" id="profile">Update Password</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
