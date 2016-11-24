/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.servlet;

import cf.bean.GiftItem;
import cf.bean.UserInfo;
import cf.db.GiftDB;
import cf.db.UserDB;
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
@WebServlet(name = "GiftServlet", urlPatterns = {"/redeem"})
public class GiftServlet extends HttpServlet {
    private GiftDB db;
    private UserDB userDB;
    @Override
    public void init() throws ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        
        db = new GiftDB(dbUrl, dbUser, dbPassword);
        userDB = new UserDB(dbUrl, dbUser, dbPassword);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String action = request.getParameter("action");
         switch(action){
             case "getList":
                 getItemList(response);
                 break;
             case "getHistory":
                 break;
         }
    }
    private void getItemList(HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<GiftItem> items = db.pullGiftItemList();
         String json = "[";
                 for(int i=0;i<items.size();i++){
                     GiftItem item = items.get(i);
                     json += "{";
                     json +=    "\"id\":\""+item.getGiftID()+"\",";
                     json +=    "\"name\":\""+item.getName()+"\",";
                     json +=    "\"desc\":\""+item.getDesc()+"\",";
                     json +=    "\"imgsrc\":\""+item.getImgsrc()+"\",";
                     json +=    "\"ptreq\":\""+item.getPointRequired()+"\"";
                     json += "}";
                     if(i<items.size()-1)
                        json += ",";
                 }
                 
         json += "]";
         response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            String choice = request.getParameter("giftChoice");
            GiftItem item = db.getSingleItem(choice);
            HttpSession session = request.getSession();
            UserInfo info = (UserInfo)session.getAttribute("userInfo");
            
            RequestDispatcher rd;
            if(item!=null){
                if(info.getBonusPoints() - item.getPointRequired() >= 0){
                    boolean status = db.redeemItem(item, info);
                    if(status){
                        session.setAttribute("userInfo", userDB.getUserInfo(info.getLoginId(), info.getPassword()));
                        rd = getServletContext().getRequestDispatcher("/index.jsp");
                    }else{
                        rd = getServletContext().getRequestDispatcher("/error.jsp");
                        request.setAttribute("msg", "Database error");
                    }
                }
                else{
                    rd = getServletContext().getRequestDispatcher("/error.jsp");
                    request.setAttribute("msg", "Insufficiant bonus point");
                }
            }
            else{
                rd = getServletContext().getRequestDispatcher("/error.jsp");
                    request.setAttribute("msg", "Item not found");
            }
            rd.forward(request, response);
        }
        catch(Exception ex){
            
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
