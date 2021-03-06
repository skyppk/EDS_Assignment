<%-- 
    Document   : placeOrder
    Created on : 2016/11/24, 下午 07:21:28
    Author     : nanasemaru
--%>

<%@page import="cf.bean.UserInfo"%>
<%@page import="cf.bean.OrderDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cf.bean.ShoppingCart"%>
<%@taglib uri="/WEB-INF/tlds/cartItem" prefix="cartItem" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <script>
            <% UserInfo uif = (UserInfo) (session.getAttribute("userInfo"));%>
                
            var loggedin = <%= uif.getLoginId() != null%>;
            var address = "<%= uif.getAddress()%>";
            $(function(){
                if(loggedin)
                    $('#address').val(address);
                $('#deliveryRdo').on('click',function(){
                    $('#deliveryAddGroup,#deliveryDateGroup,#deliveryTimeGroup').fadeIn(500);
                });
                $('#sPickRdo').on('click',function(){
                    $('#deliveryAddGroup,#deliveryDateGroup,#deliveryTimeGroup').fadeOut(500);
                });
            });
            function checkStatus() {
                if (loggedin) {
                    $.ajax({
                        type: "POST",
                        url: "order",
                        data: $('form').serialize()
                        , success: function (data) {
                            if (data.status){
                                alert("Order successful\n\nRedirecting to order history...");
                                // T O D O : ORDER HISTORY JSP
                                window.location = "index.jsp";
                            }
                            else {
                                //alert(data.msg+"\n\nRedirecting to home page...");
                                window.location = "error.jsp?msg="+data.msg;
                            }
                        },
                        dataType: 'json'
                    });
                } else
                    alert('You must login first!');
            }
        </script>
        <div class="container">
            <h2>Place Order</h2>
            <div class="panel panel-default">
                <%
                    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
                    ArrayList<OrderDetails> arr = null;
                    if (cart != null) {
                        arr = cart.getCart();
                    }
                %>
                <cartItem:showItems items="<%=arr%>" tagType="placeOrder" />
            </div>
        </div>
    </body>
</html>
