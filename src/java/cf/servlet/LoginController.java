/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.servlet;

import cf.bean.UserInfo;
import cf.db.UserDB;
import java.io.IOException;
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
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private UserDB userDb;

    @Override
    public void init() throws ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dpPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        
        userDb = new UserDB(dbUrl, dbUser, dbPassword);
    }
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(!isAuthenticated(req) && !("authenticate".equals(action))){
            doLogin(req, resp);
            return;
        }
        if("authenticate".equals(action)){
            doAuthenticate(req, resp);
        } else if ("logout".equals(action)){
            doLogout(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
    }
    
    private void doAuthenticate(HttpServletRequest req, HttpServletResponse resp ) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String targetURL;
        
        if (this.userDb.isValidUser(username, password)){
            HttpSession session = req.getSession(true);
            UserInfo user = new UserInfo();
            user = userDb.getUserInfo(username, password);
            
            session.setAttribute("userInfo", user);
            targetURL = "/index.jsp";
        } else {
            targetURL = "/index.jsp"; //TODO
        }
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(req, resp);
    }
    
    private boolean isAuthenticated(HttpServletRequest req){
        boolean result = false;
        HttpSession session = req.getSession();
        if (session.getAttribute("userInfo") != null){
            result = true;
        }
        return result;
    }
    
    private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String targetURL = "index.jsp";
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(req, resp);
    }
    
    private void doLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if(session != null){
            session.removeAttribute("userInfo");
            session.invalidate();
        }
        doLogin(req, resp);
    }
}