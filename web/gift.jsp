<%@page import="cf.bean.UserInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cf.bean.OrderDetails"%>
<%@page import="cf.bean.ShoppingCart"%>

<%@ page errorPage="error.jsp"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gift Redemption</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <script>
            $(function () {
                $.get("redeem",{
                    action: "getList"
                }, function (data) {
                    console.log(data);
                    data.forEach(function (element) {
                        str =
                                '<tr>' +
                                '<td><img style="width:150px" src="img/' + element.imgsrc + '"></td>' +
                                '<td>' + element.name + '</td>' +
                                '<td>' + element.desc + '</td>' +
                                '<td>' + element.ptreq + '</td>' +
                                '<td><input type="radio" name="giftChoice" value="' + element.id + '"></td>' +
                                '</tr>';
                        $('tbody').append(str);
                    });
                }, "json");
            });
        </script>
        <div class="container">
            <h2>Gift Redemption</h2>
            <div class="panel panel-default">
                <%
                    UserInfo uinfo = (UserInfo) session.getAttribute("userInfo");
                    if (uinfo.getLoginId() == null)
                %>
                <h3> You now have <%= uinfo.getBonusPoints() %> bonus points to redeem gifts</h3>
                <form action="redeem" method="POST">
                <div class="table-responsive">
                    <table class="table">
                        <tr>
                            <th>Image</th>
                            <th>Gift Name</th>
                            <th>Description</th>
                            <th>Points required</th>
                            <th>Select Gift</th>
                        </tr>
                    </table>

                </div>
                <div class="panel-body">
                    <div class="row pull-right" style="margin-right: 20px;">
                        
                        <input type="submit" value="Redeem" id="submit" class="btn btn-default">
                    </div>
                </div>
                </form>
            </div>

        </div>
    </body>
</html>