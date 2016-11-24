<%-- 
    Document   : profile
    Created on : 2016/11/24, 上午 09:50:13
    Author     : nanasemaru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>C & F</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <h2>Profile</h2>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form class="form-horizontal" method="post" action="editProfile.jsp">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Login ID</label>
                            <div class="col-sm-10">
                                <p class="form-control-static"><%=userInfo.getLoginId()%></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Name</label>
                            <div class="col-sm-10">
                                <p class="form-control-static"><%=userInfo.getFirstName()%> <%=userInfo.getLastName()%></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Tel</label>
                            <div class="col-sm-10">
                                <p class="form-control-static"><%=userInfo.getTel()%></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Email</label>
                            <div class="col-sm-10">
                                <p class="form-control-static"><%=userInfo.getEmail()%></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Address</label>
                            <div class="col-sm-10">
                                <p class="form-control-static"><%=userInfo.getAddress()%></p>
                            </div>
                        </div>
                        <div class="form-inline pull-right">
                            <button type="button" class="btn btn-default" onclick="location.href = 'changePassword.jsp'">Change Password</button>
                            <button type="submit" class="btn btn-default">Edit Profile</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>