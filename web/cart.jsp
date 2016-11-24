<%@page import="java.util.ArrayList"%>
<%@page import="cf.bean.OrderDetails"%>
<%@page import="cf.bean.ShoppingCart"%>
<%@taglib uri="/WEB-INF/tlds/cartItem" prefix="cartItem" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <script>
            $(function () {

            });
            function dropItem(id, element) {

                row = element.parent().parent();
                $.ajax({
                    type: "POST",
                    url: "ShoppingCartServlet",
                    data: {
                        action: 'dropItem',
                        itemId: id
                    }, success: function (data) {

                    },
                    dataType: 'json'
                }).always(function (data) {
                    if (data.status)
                        row.fadeOut(500).remove();
                    else
                        alert('Unable to remove item');
                    updateTotal();

                });
            }
            function updateTotal() {
                price = 0;
                $.each($("td[id='dtprice']"), function (key, value) {
                    price += Number($(value).text().replace('$', '').replace(',', ''));
                });
                $('#totalValue').text('$ ' + price);
            }
        </script>
        <div class="container">
            <h2>Shopping Cart</h2>
            <div class="panel panel-default">
                <!--<div class="panel-body">-->
                <%
                    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
                    ArrayList<OrderDetails> arr = null;
                    if (cart != null) {
                        arr = cart.getCart();
                    }

                %>
                <cartItem:showItems items="<%=arr%>" tagType="cart"/>
                <!--</div>-->
            </div>
        </div>
    </body>
</html>