/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.servlet;

import cf.bean.OrderInfo;
import cf.db.OrderDB;
import cf.db.UserDB;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nanasemaru
 */
@WebServlet(urlPatterns = {"/orderAction"})
public class OrderAction extends HttpServlet {

    OrderDB db;
    UserDB userDb;

    @Override
    public void init() throws ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        db = new OrderDB(dbUrl, dbUser, dbPassword);
        userDb = new UserDB(dbUrl, dbUser, dbPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;
        try {
            String id = request.getParameter("id");
            String order = request.getParameter("order");
            String action = request.getParameter("action");
            
            if ("cancel".equalsIgnoreCase(action)) {
                OrderInfo o = db.queryOrderById(id, order);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
                Date orderDate = format.parse(o.getOrderDate());
                Date deliveryDate = format2.parse(o.getDeliveryDate());
                Date date = new Date();
                String message = null;
                System.out.println(date.getTime() - orderDate.getTime());
                if ((date.getTime() - orderDate.getTime()) < 24 * 60 * 60 * 1000) {
                    if ((deliveryDate.getTime()) - date.getTime() > 24 * 60 * 60 * 1000) {
                        if (userDb.editMoney(id, order, o.getOrderPrice())) {
                            message = "Order Id " + order + " is cancelled";
                        }
                    } else {
                        message = "Cancel is not available";
                    }
                } else {
                    message = "Cancel is not available";
                }
                response.sendRedirect("orderHistory?action=now&id=" + id + "&message=" + message);
            } else if ("detail".equalsIgnoreCase(action)) {
                OrderInfo o = db.queryOrderDetailById(id, order);
                request.setAttribute("order", o);
                rd = getServletContext().getRequestDispatcher("/orderDetail.jsp");
                rd.forward(request, response);
            } else if ("changeDate".equalsIgnoreCase(action)) {
                String date = request.getParameter("date");
                String time = request.getParameter("time");
                String message = null;
                if (db.updateDeliveryDate(id, order, date, time)) {
                    message = "Delivery date changed to " + date + " " + time;    
                } else {
                    message = "Cannot change delivery date.";
                }
                response.sendRedirect("orderHistory?action=now&id=" + id + "&message=" + message);
            }
        } catch (Exception ex) {
            rd = getServletContext().getRequestDispatcher("/error.jsp");
                rd.forward(request, response);
        }
//            request.setAttribute("order", order);
//            RequestDispatcher rd;
//            rd = getServletContext().getRequestDispatcher("/manageExistingOrder.jsp");
//            rd.forward(request, response);
    }
}
