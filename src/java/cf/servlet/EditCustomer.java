/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.servlet;

import cf.bean.UserInfo;
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

/**
 *
 * @author apple
 */
@WebServlet(urlPatterns = {"/handleCustomer"})
public class EditCustomer extends HttpServlet{
    private UserDB db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        db = new UserDB(dbUrl, dbUser, dbPassword);
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
        String action = request.getParameter("action");
        if ("list".equalsIgnoreCase(action)) {
            ArrayList<UserInfo> allUsers = db.selectAllUser();
//            if(allUsers == null){
//                System.out.println("fk you!");
//            }
//            for(UserInfo ui: allUsers){
//                System.out.println("fkkkkk" + ui.getLoginId());
//            }
            
            request.setAttribute("allUsers", allUsers);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/manageCustomer.jsp");
            rd.forward(request, response);
        } 
        else if ("listNew".equalsIgnoreCase(action)) {
            ArrayList<UserInfo> newRegister = db.selectNewUser();
//            if(allUsers == null){
//                System.out.println("fk you!");
//            }
//            for(UserInfo ui: allUsers){
//                System.out.println("fkkkkk" + ui.getLoginId());
//            }
            
            request.setAttribute("newRegister", newRegister);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/newRegister.jsp");
            rd.forward(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }
    }
}
