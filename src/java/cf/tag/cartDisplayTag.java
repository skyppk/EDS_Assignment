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
            for (OrderDetails item : items) {
                //out.println(item.getItemName() + "<br>");
                out.println("<div class=\"row\"");
                out.println("<div class=\"col-sm-6 col-md-4\">");
                out.println("<div class=\"caption\">");
                out.println("<h4 style=\"white-space: nowrap; text-overflow: ellipsis; overflow:hidden;\">" + item.getItemName() + "</h4>");
                out.println("<p> $" + item.getBuyPrice() + " x " + item.getQuantity() + "</p>");
                out.println("<p> TOTAL $" + item.getDetailsPrice()+ "</p>");
                out.println("</div>");
                out.println("</div>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
