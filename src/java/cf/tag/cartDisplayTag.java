/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.tag;

import cf.bean.OrderDetails;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author nanasemaru
 */
public class cartDisplayTag extends SimpleTagSupport {

    private ArrayList<OrderDetails> items;
//    private String tagType;

    public void setItems(ArrayList<OrderDetails> items) {
        this.items = items;
    }

//    public void setTagType(String tagType) {
//        this.tagType = tagType;
//    }
    @Override
    public void doTag() throws JspException, IOException {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
            out.println("<div class=\"table-responsive\"><table class=\"table\">");
            out.println("<tr>");
            out.println("<th></th>");
            out.println("<th>Name</th>");
            out.println("<th>Unit Price</th>");
            out.println("<th>Quantity</th>");
            out.println("<th>Price</th>");
            out.println("<th>Action</th>");
            out.println("</tr>");
            for (OrderDetails item : items) {
                //out.println(item.getItemName() + "<br>");   
                out.println("<tr><td>");
                out.println("<img class=\"img-rounded\" style=\"max-height:70px;\" src=\"img/" + item.getImg() + "\" alt=\"No Image\">");
                out.println("</td><td style=\"vertical-align:middle; white-space: nowrap; text-overflow: ellipsis; overflow:hidden;\">");
                out.println(item.getItemName());
                out.println("</td><td style=\"vertical-align:middle;\">");
                out.println("$ " + item.getDetailsPrice());
                out.println("</td><td style=\"vertical-align:middle;\">");
                out.println(item.getQuantity());
                out.println("</td><td style=\"vertical-align:middle;\">");
                out.println("$ " + item.getBuyPrice());
                out.println("</td><td style=\"vertical-align:middle;\">");
                out.println("<button type=\"button\" class=\"btn btn-default\">Cancel</button>");
                out.println("</td></tr>");

//                out.println("<div class=\"row\"");
//                out.println("<div class=\"col-sm-6 col-md-4\">");
//                out.println("<div class=\"caption\">");
//                out.println("<h4 style=\"white-space: nowrap; text-overflow: ellipsis; overflow:hidden;\">" + item.getItemName() + "</h4>");
//                out.println("<p> $" + item.getBuyPrice() + " x " + item.getQuantity() + "</p>");
//                out.println("<p> TOTAL $" + item.getDetailsPrice()+ "</p>");
//                out.println("</div>");
//                out.println("</div>");
            }
            out.println("</table></div>");
            out.println("<div class=\"panel-body\" style=\"padding-right:20px;\">");
            out.println("<div class=\"row pull-right\"><h4><small>Total Price: </small>$ DEFAULT");
            out.println("<small><button type=\"button\" class=\"btn btn-default\">Order</button></small></h4>");
            out.println("</div></div></div>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
