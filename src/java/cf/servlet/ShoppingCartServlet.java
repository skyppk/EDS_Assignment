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
        switch (action) {
            case "pushItem":
                doPushItem(request, response);
                break;
            case "dropItem":
                doDropItem(request, response);
                break;
            case "modifyQuantity":
                doModifyQuantity(request, response);
                break;
                
        }

        if ("placeOrder".equalsIgnoreCase(action)) {
            //System.out.println("fuking damn1");
            HttpSession session = request.getSession();
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            ArrayList<OrderDetails> order = cart.getCart();
            //System.out.println("fuckkkkkk" + order);
            for (OrderDetails o : order) {
                //System.out.println("fuckkkkkk" + o.getItemId());

                ItemInfo info = db.queryItemDetail(o.getItemId());
                o.setBuyPrice(info.getPrice());
                o.setDetailsPrice(info.getPrice() * o.getQuantity());
            }
            session.removeAttribute("cart");
            session.setAttribute("cart", cart);

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/placeOrder.jsp");
            rd.forward(request, response);
        }
//        } else {
//
//            String itemId = request.getParameter("itemId");
//            String itemName = request.getParameter("itemName");
//            double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
//            int quantity = Integer.parseInt(request.getParameter("quantity"));
//            System.out.println("damnnnnnnnnnn" + itemId);
//            HttpSession session = request.getSession();
//            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
//            if (cart == null) {
//                cart = new ShoppingCart();
//            }
//            cart.getCart().add(new OrderDetails(itemId, itemName, quantity, itemPrice, quantity * itemPrice, "pig.png"));
//            //response.getWriter().println(test);
//            session.setAttribute("cart", cart);
//        }
    }

    private void doPushItem(HttpServletRequest request, HttpServletResponse response) {
        String itemId = request.getParameter("itemId");
        String itemName = request.getParameter("itemName");
        double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String img = request.getParameter("img");

        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
        }
        cart.addCart(new OrderDetails(itemId, itemName, quantity, itemPrice, quantity * itemPrice, img));
        //response.getWriter().println(test);
        session.setAttribute("cart", cart);

    }

    private void doDropItem(HttpServletRequest request, HttpServletResponse response) {

        try {
            String itemId = request.getParameter("itemId");
            HttpSession session = request.getSession();
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            if (cart != null) {
                ArrayList<OrderDetails> arr = cart.getCart();
                for (OrderDetails element : arr) {
                    if (element.getItemId().equals(itemId)) {
                        arr.remove(element);
                        break;
                    }
                }
                makeResponse(response, true, null);

            } else {
                makeResponse(response, false, null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void doModifyQuantity(HttpServletRequest request, HttpServletResponse response) {

        try {
            String itemId = request.getParameter("itemId");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            System.out.println("check itemid" + itemId + "check quantity :" + quantity );
            HttpSession session = request.getSession();
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            if(quantity <= 0){
                cart.removeDetails(itemId);
                
            }else{
                cart.setDetails(itemId, quantity);
            }
                
                
            
//                ArrayList<OrderDetails> arr = cart.getCart();
//                for (OrderDetails element : arr) {
//                    if (element.getItemId().equals(itemId)) {
//                        element.setDetailsPrice(element.getBuyPrice() * quantity);
//                        element.setQuantity(quantity);
//                        break;
//                    }
//                }
//                session.setAttribute("cart", cart);
                response.sendRedirect("cart.jsp");

            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    private void makeResponse(HttpServletResponse response, boolean status, String json)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{\"status\":" + status + (json != null ? json : "") + "}");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
