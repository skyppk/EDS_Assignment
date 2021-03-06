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
                 getHistory( request,response);
                 break;
             case "manage":
                 manageGift(request,response);
                 break;
             case "getEditGift":
                 getGiftDetail(request,response);
                 break;
                 
         }
    }
    private void getHistory(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session = request.getSession();
        ArrayList<GiftItem> items = db.getHistory((UserInfo)session.getAttribute("userInfo"));
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
    
    private void getGiftDetail(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println("test gift id :" + id);
                GiftItem gift = db.getGiftDetails(id);
                String targetURL = "editGift.jsp";
                request.setAttribute("gift", gift);
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/" + targetURL);
                rd.forward(request, response);
    }
    
    private void manageGift(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<GiftItem> gifts = db.pullGiftItemList();
         request.setAttribute("gifts", gifts);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/manageGift.jsp");
            rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        try{
            String choice = request.getParameter("giftChoice");
            GiftItem item = db.getSingleItem(choice);
            UserInfo info = (UserInfo)session.getAttribute("userInfo");
            
            if(item!=null){
                if(info.getBonusPoints() - item.getPointRequired() >= 0){
                    boolean status = db.redeemItem(item, info);
                    if(status){
                        session.setAttribute("userInfo", userDB.getUserInfo(info.getLoginId(), info.getPassword()));
                        rd = getServletContext().getRequestDispatcher("/index.jsp");
                    }else{
                        rd = getServletContext().getRequestDispatcher("/error.jsp");
                        session.setAttribute("errmsg", "Database error");
                    }
                }
                else{
                    rd = getServletContext().getRequestDispatcher("/error.jsp");
                    session.setAttribute("errmsg", "Insufficiant bonus point");
                }
            }
            else{
                rd = getServletContext().getRequestDispatcher("/error.jsp");
                    session.setAttribute("errmsg", "Item not found");
            }
            rd.forward(request, response);
        }
        catch(Exception ex){
             rd = getServletContext().getRequestDispatcher("/error.jsp");
             session.setAttribute("errmsg", ex);
             rd.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
