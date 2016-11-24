/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.servlet;

import cf.bean.ItemInfo;
import cf.bean.OrderDetails;
import cf.bean.ShoppingCart;
import cf.db.ItemDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author xeonyan
 */
@WebServlet(name = "ShoppingCartServlet", urlPatterns = {"/ShoppingCartServlet"})
public class ShoppingCartServlet extends HttpServlet {

    private ItemDB db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        db = new ItemDB(dbUrl, dbUser, dbPassword);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("placeOrder".equalsIgnoreCase(action)) {
            System.out.println("fuking damn1");
            HttpSession session = request.getSession();
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            ArrayList<OrderDetails> order = cart.getCart();
            System.out.println("fuckkkkkk"+order);
            for (OrderDetails o : order) {
                System.out.println("fuckkkkkk"+o.getItemId());
                
                ItemInfo info = db.queryItemDetail(o.getItemId());
                o.setBuyPrice(info.getPrice());
                o.setDetailsPrice(info.getPrice() * o.getQuantity());
            }
            session.removeAttribute("cart");
            session.setAttribute("cart", cart);
            
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/placeOrder.jsp");
            rd.forward(request, response);
        } else {
            
            String itemId = request.getParameter("itemId");
            String itemName = request.getParameter("itemName");
            double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            System.out.println("damnnnnnnnnnn"+itemId);
            HttpSession session = request.getSession();
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            if (cart == null) {
                cart = new ShoppingCart();
            }
            cart.getCart().add(new OrderDetails(itemId, itemName, quantity, itemPrice, quantity * itemPrice, "pig.png"));
            //response.getWriter().println(test);
            session.setAttribute("cart", cart);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
