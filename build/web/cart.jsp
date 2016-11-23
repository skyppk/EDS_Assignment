<%@page import="java.util.ArrayList"%>
<%@page import="cf.bean.OrderDetails"%>
<%@page import="cf.bean.ShoppingCart"%>
<%@taglib uri="/WEB-INF/tlds/cartItem" prefix="item" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <h2>Shopping Cart</h2>
            <div class="panel panel-default">
                <div class="panel-body">
                    <%
                        ShoppingCart cart = (ShoppingCart)session.getAttribute( "cart" );
                        ArrayList<OrderDetails> arr = null;
                        if(cart!=null)
                            arr = cart.getCart();
                        
                    %>
                    <item:showItems items="<%=arr%>"/>
                </div>
            </div>
        </div>
    </body>
</html>