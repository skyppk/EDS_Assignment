/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.servlet;

import cf.bean.OrderDetails;
import cf.bean.ShoppingCart;
import cf.bean.UserInfo;
import cf.db.OrderDB;
import cf.db.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;
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
@WebServlet(name = "OrderServlet", urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {
private OrderDB db;
private UserDB userDb;

    @Override
    public void init() throws ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        
        db = new OrderDB(dbUrl, dbUser, dbPassword);
        userDb = new UserDB(dbUrl, dbUser, dbPassword);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            UserInfo info = (UserInfo)session.getAttribute("userInfo");
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            
            ArrayList<OrderDetails> order = cart.getCart();
            /**
             * OrderInfo oInfo = new OrderInfo(
                    -1,
                    generateOrderID(),
                    info.getLoginId(),
                    request.getParameter("deliveryType"),
                    request.getParameter("date"),
                    request.getParameter("time"),
                    request.getParameter("address"),
                    Double.parseDouble(request.getParameter("total")),
                    null,
                    null,
                    order
            );
            **/
            double price = Double.parseDouble(request.getParameter("total"));
            String orderDate = request.getParameter("date");
            if(checkBalance(info,price)){
                boolean status = db.addOrderInfo(
                        generateOrderID(),
                        info.getLoginId(),
                        request.getParameter("deliveryType"),
                        (orderDate.equals("") ? null : orderDate),
                        request.getParameter("time"),
                        request.getParameter("address"),
                        price,
                        order,
                        getBonus(price)
                );
                if(status){
                            UserInfo user = new UserInfo();
                            ShoppingCart sc = new ShoppingCart();
                            user = userDb.getUserInfo(info.getLoginId(), info.getPassword());
                            session.setAttribute("accountType", user.getAccountType());
                            session.setAttribute("userInfo", user);
                            session.setAttribute("cart", sc);
                    makeResponse(response,true,null);
                }
                    
                else
                    makeResponse(response,false,"\"msg\": \"Database error\"");
                /**
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                if(status)
                    rd.forward(request, response);**/
            }
            else
                makeResponse(response,false,"\"msg\":\"Insufficient balance in your account\"");
          
    }
    private double getBonus(double price){
        System.out.println("bonus points?" + price);
        if(price > 2000){
            return price * 0.05;
        }else{
            return 0 ;
        }
        
    }
     private void makeResponse(HttpServletResponse response, boolean status, String json)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{\"status\":" + status + (json != null ? ","+json : "") + "}");
    }
    private boolean checkBalance(UserInfo uinfo,double price){
        double balance = uinfo.getMoney();
        return ((balance + uinfo.getCreditAmount()) - price) > 0;
          
    }
    private String generateOrderID(){
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0,9);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
