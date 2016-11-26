/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.servlet;

import cf.bean.ItemInfo;
import cf.db.ItemDB;
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
@WebServlet(urlPatterns = {"/searchItem"})
public class HandleSearch extends HttpServlet {

    private ItemDB db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        db = new ItemDB(dbUrl, dbUser, dbPassword);
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
        String keyword = request.getParameter("keyword");
        String type = request.getParameter("type");
        if ("all".equalsIgnoreCase(action)) {
            ArrayList<ItemInfo> items = db.searchItemByInput(keyword);
            request.setAttribute("items", items);
            request.setAttribute("keyword", keyword);
            request.setAttribute("type", action);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/showItems.jsp");
            rd.forward(request, response);
        } else if ("Dress".equalsIgnoreCase(action) || "Jacket".equalsIgnoreCase(action) || "Accessory".equalsIgnoreCase(action)) {
            ArrayList<ItemInfo> items = db.searchItemByInputFilter(action, keyword);
            request.setAttribute("items", items);
            request.setAttribute("keyword", keyword);
            request.setAttribute("type", action);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/showItems.jsp");
            rd.forward(request, response);
        } else if ("Designer".equalsIgnoreCase(action)) {
            ArrayList<ItemInfo> items = db.searchItemByDesigner(keyword);
            request.setAttribute("items", items);
            request.setAttribute("keyword", keyword);
            request.setAttribute("type", action);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/showItems.jsp");
            rd.forward(request, response);
        } else if ("asc".equalsIgnoreCase(action)||"desc".equalsIgnoreCase(action)) {
            ArrayList<ItemInfo> items;
            if ("Designer".equalsIgnoreCase(type)) {
                if ("all".equalsIgnoreCase(type)) {
                    items = db.searchAllItemBySortingDesigner(keyword, action);
                } else {
                    items = db.searchItemBySortingDesigner(keyword, action);
                }
            } else {
                if ("all".equalsIgnoreCase(type)) {
                    items = db.searchAllItemBySorting(keyword, action);
                } else {
                    items = db.searchItemBySorting(type, keyword, action);
                }
            }
            request.setAttribute("items", items);
            request.setAttribute("keyword", keyword);
            request.setAttribute("type", type);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/showItems.jsp");
            rd.forward(request, response);
        }
    }
}
