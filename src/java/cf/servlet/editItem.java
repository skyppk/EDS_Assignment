/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.servlet;

import cf.bean.StaffInfo;
import cf.db.ItemDB;
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
 * @author apple
 */
@WebServlet(urlPatterns = {"/editItem"})
public class editItem extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        StaffInfo staffInfo = (StaffInfo)session.getAttribute("staffInfo");
        if (staffInfo.getLoginId() == null) {
        response.sendRedirect("error.jsp?msg=You have not permission to visit this page !");
        }
        if ("confirm".equalsIgnoreCase(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String designerName = request.getParameter("designerName");
            String descriptions = request.getParameter("descriptions");
            double price = Double.parseDouble(request.getParameter("price"));
            if (db.editItemInfo(id, price, descriptions, designerName));
            response.sendRedirect("product?action=list");

        } else if ("cancel".equalsIgnoreCase(action)) {
            response.sendRedirect("product?action=list");
        } else if ("updateStatus".equalsIgnoreCase(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String status = request.getParameter("status");
            if (db.changeItemStatus(id, status));
            response.sendRedirect("product?action=list");
        } else if ("new".equalsIgnoreCase(action)) {
            String itemId = request.getParameter("itemId");
            String itemName = request.getParameter("itemName");
            String category = request.getParameter("category");
            String designer = request.getParameter("designer");
            double price = Double.parseDouble(request.getParameter("price"));
            String desciptions = request.getParameter("desciptions");
            String image = request.getParameter("image");

            db.addItemInfo(itemId, itemName, category, designer, price, desciptions, image);
            response.sendRedirect("product?action=list");
        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }
    }
}
