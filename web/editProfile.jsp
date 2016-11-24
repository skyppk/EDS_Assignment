<%-- 
    Document   : editProfile
    Created on : 2016/11/24, 下午 12:31:55
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
            <h2>Edit Profile</h2>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form method="post" action="#">
                        <div class="form-group">
                            <label for="oldPwd">Old Password</label>
                            <input type="password" class="form-control" id="oldPwd" name="oldPwd" required>
                        </div>
                        <div class="form-group">
                            <label for="newPwd1">New Password</label>
                            <input type="password" class="form-control" id="newPwd1" name="newPwd1" required>
                        </div>
                        <div class="form-group">
                            <label for="newPwd2">Confirm New Password</label>
                            <input type="password" class="form-control" id="newPwd2" name="newPwd2" required>
                        </div>
                        <div class="form-inline pull-right">
                            <button type="reset" class="btn btn-default" id="pwd">Reset</button>
                            <button type="submit" class="btn btn-default" id="profile">Update Password</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
