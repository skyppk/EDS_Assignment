/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.servlet;

import cf.bean.OrderInfo;
import cf.db.OrderDB;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "OrderHistory", urlPatterns = {"/orderHistory"})
public class OrderHistory extends HttpServlet {

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
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        if (id != null) {
            ArrayList<OrderInfo> orders;
            
            RequestDispatcher rd;
            if ("now".equalsIgnoreCase(action)) {
                orders = db.queryExistingOrder(id,"WAITING");
                request.setAttribute("orders", orders);
                String message = request.getParameter("message");
                if (message!=null&&!message.equals("")) {
                    request.setAttribute("message", message);
                }
                rd = getServletContext().getRequestDispatcher("/manageExistingOrder.jsp");
            } else {
                orders = db.queryOrderHistory(id);
                request.setAttribute("orders", orders);
                rd = getServletContext().getRequestDispatcher("/orderHistory.jsp");
            }
            rd.forward(request, response);
        }
    }
}
