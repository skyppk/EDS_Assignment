/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.servlet;

import cf.bean.OrderDetails;
import cf.bean.ShoppingCart;
import java.io.IOException;
import java.io.PrintWriter;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String itemId = request.getParameter("itemId");
            String itemName = request.getParameter("itemName");
            double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            
            HttpSession session=request.getSession();  
            ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
            if(cart==null)
                cart = new ShoppingCart();
            cart.getCart().add(new OrderDetails(itemId,itemName,quantity,itemPrice,quantity*itemPrice));
            //response.getWriter().println(test);
            session.setAttribute("cart",cart); 
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
