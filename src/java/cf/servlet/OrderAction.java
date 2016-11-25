/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.servlet;

import cf.bean.OrderInfo;
import cf.db.OrderDB;
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

    @Override
    public void init() throws ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        db = new OrderDB(dbUrl, dbUser, dbPassword);
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
        try {
            String id = request.getParameter("id");
            String order = request.getParameter("order");
            String action = request.getParameter("action");
            if ("cancel".equalsIgnoreCase(action)) {
                OrderInfo o = db.queryOrderById(id, order);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date orderDate = format.parse(o.getOrderDate());
                Date date = new Date();
                String message = null;
                System.out.println(date.getTime() - orderDate.getTime());
                if ((date.getTime() - orderDate.getTime()) > 24 * 60 * 60 * 1000) {
                    if ((orderDate.getTime()) - date.getTime() > 24 * 60 * 60 * 1000) {
                        if (db.deleteOrderById(id, order)) {
                            message = "Order Id " + order + " is cancelled";
                        }
                    } else {
                        message = "Cancel is not available";
                    }
                } else {
                    message = "Cancel is not available";
                }
                response.sendRedirect("orderHistory?action=now&id="+ id +"&message="+ message);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            
        }
//            request.setAttribute("order", order);
//            RequestDispatcher rd;
//            rd = getServletContext().getRequestDispatcher("/manageExistingOrder.jsp");
//            rd.forward(request, response);
    }
}
