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
import javax.servlet.http.HttpSession;

/**
 *
 * @author apple
 */
@WebServlet(urlPatterns = {"/editCustomer"})
public class EditCustomer extends HttpServlet {

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
        if ("password".equalsIgnoreCase(action)) {
            String user = request.getParameter("user");
            String oldPwd = request.getParameter("oldPwd");
            String newPwd1 = request.getParameter("newPwd1");
            String newPwd2 = request.getParameter("newPwd2");
            System.out.println(user + oldPwd);
            if (db.isValidUser(user, oldPwd)) {
                if (newPwd1.equals(newPwd2)) {
                    if (db.editPassword(user, newPwd1)) {
                        HttpSession session = request.getSession(true);
                        UserInfo info = new UserInfo();
                        info = db.getUserInfo(user, newPwd1);
                        session.removeAttribute("userInfo");
                        session.setAttribute("userInfo", info);
                        request.setAttribute("message", "<label style=\"color: green\">Password Updated</label>");
                    }
                } else {
                    request.setAttribute("message", "<label style=\"color: red\">Password doesn't match the confirmation</label>");
                }
            } else {
                request.setAttribute("message", "<label style=\"color: red\">Old password isn't valid</label>");
            }
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/changePassword.jsp");
            rd.forward(request, response);
//            request.setAttribute("allUsers", "");
//            RequestDispatcher rd;
//            rd = getServletContext().getRequestDispatcher("/manageCustomer.jsp");
//            rd.forward(request, response);
        } else if ("profile".equalsIgnoreCase(action)) {
            System.out.println("PROFILE");
            String user = request.getParameter("user");
            String pwd = request.getParameter("pwd");
            String tel = request.getParameter("tel");
            String email = request.getParameter("email");
            String address = request.getParameter("address");

            if (db.editUserInfo(user, tel, address, email)) {
                HttpSession session = request.getSession(true);
                        UserInfo info = new UserInfo();
                        info = db.getUserInfo(user, pwd);
                        session.removeAttribute("userInfo");
                        session.setAttribute("userInfo", info);
                request.setAttribute("message", "<label style=\"color: green\">Information Changed</label>");
            } else {
                request.setAttribute("message", "<label style=\"color: red\">Edit Failed</label>");
            }
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/editProfile.jsp");
            rd.forward(request, response);
//            request.setAttribute("newRegister", "");
//            RequestDispatcher rd;
//            rd = getServletContext().getRequestDispatcher("/newRegister.jsp");
//            rd.forward(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }
    }
}
