<%-- 
    Document   : manageExistingOrder
    Created on : 2016/11/25, 上午 04:15:02
    Author     : nanasemaru
--%>

<%@page import="java.sql.Time"%>
<%@page import="cf.bean.OrderInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>C & F</title>
        <%
            ArrayList<OrderInfo> orders = (ArrayList<OrderInfo>) request.getAttribute("orders");
            String message = request.getParameter("message");
        %>

    </head>
    <body>
        <%@include file="menu.jsp" %>
        <%
            if (userInfo.getLoginId() == null) {
                response.sendRedirect("error.jsp?msg=You have not permission to visit this page !");
            }
        %>
        <div class="container">

            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Change Delivery Date</h4>
                        </div>
                        <div class="modal-body">
                            <form method="post" action="orderAction?action=changeDate" id="form1">
                                <div class="form-group">
                                    <label for="date" class="form-control-label">Delivery Date:</label>
                                    <input type="date" class="form-control" id="date" name="date">
                                </div>
                                <div class="form-group">
                                    <label for="time" class="form-control-label">Delivery Time:</label><br>
                                    <label class="radio-inline">
                                        <input type="radio" name="time" id="AM" value="AM"> AM
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="time" id="PM" value="PM"> PM
                                    </label>
                                    <input type="hidden" name="id" id="user">
                                    <input type="hidden" name="order" id="order">
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

            <h2>Existing Order</h2>
            <div class="panel panel-default">
                <div class="table-responsive">
                    <table class="table">
                        <tr>
                            <th>Order ID</th>
                            <th>Order Date</th>
                            <th>Price</th>
                            <th>Delivery Date</th>
                            <th>Address</th>
                            <th>Change Delivery Date</th>
                            <th>Cancel Order</th>
                        </tr>
                        <% for (OrderInfo order : orders) {%>
                        <tr>
                            <td><%=order.getOrderId()%></td>
                            <td><%=order.getOrderDate()%></td>
                            <td>$ <%=order.getOrderPrice()%></td>
                            <td><%=order.getDeliveryDate()%> <%=order.getDeliveryTime()%></td>
                            <td><%=order.getDeliveryAddress()%></td>
                            <td><button class="btn btn-default" id="changeDate" data-toggle="modal" data-target="#myModal" data-date="<%=order.getDeliveryDate()%>" data-time="<%=order.getDeliveryTime()%>" data-order="<%=order.getOrderId()%>" data-user="<%=order.getLoginId()%>">Change Date</button></td>
                            <% if (order.getOrderPrice() > 10000) {%>
                            <td><button class="btn btn-default" id="Cancel" onclick="location.href = 'orderAction?action=cancel&id=<%=order.getLoginId()%>&order=<%=order.getOrderId()%>'">Cancel</button></td>
                            <% } else {%>
                            <td>Not Allow</td>
                            <% } %>
                        </tr>
                        <% } %>
                    </table>
                </div>
            </div>
        </div>
        <%
            if (message != null) {

                out.print("<script>alert(\"" + message + "\");</script>");
                System.out.println("NOT NULL!!!");
            }
        %>
        <script>
            $('#myModal').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget); // Button that triggered the modal
                var date = button.data('date');
                var time = button.data('time');// Extract info from data-* attributes
                var id = button.data('user');
                var order = button.data('order');
                // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
                // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
                var modal = $(this);
                $('#date').val(date);
                document.getElementById(time).checked = true;
                $('#user').val(id);
                $('#order').val(order);
//                modal.find('.modal-body input').val(recipient)
            });
            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth() + 1; //January is 0!

            var yyyy = today.getFullYear();
            if (dd < 10) {
                dd = '0' + dd
            }
            if (mm < 10) {
                mm = '0' + mm
            }
            today = yyyy+ '-' + mm + '-' + dd;

            $('#date').attr('min', today);

//            alert($('#theDate').attr('value'));
        </script>
    </body>
</html>
