/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.tag;

import cf.bean.ItemInfo;
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
public class itemsTag extends SimpleTagSupport {

    private ArrayList<ItemInfo> items;
//    private String tagType;

    public void setItems(ArrayList<ItemInfo> items) {
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

            if (items != null) {
                for (ItemInfo item : items) {
                    out.println("<div class=\"col-sm-6 col-md-4\">");
                    out.println("<div class=\"thumbnail\">");
                    out.println("<a href=\"product?action=detail&id="+item.getItemId()+"\">");
                    out.println("<img src=\"img/"+item.getImg()+"\" alt=\"No image\">");
                    out.println("<div class=\"caption\">");
                    out.println("<h4 style=\"white-space: nowrap; text-overflow: ellipsis; overflow:hidden;\">"+item.getItemName()+"</h4>");
                    out.println("<p class=\"text-right\">$ "+item.getPrice()+"</p>");
                    out.println("</div>");
                    out.println("</a>");
                    out.println("</div>");
                    out.println("</div>");
                }
            }
        } catch (IOException ioe) {
            System.out.println("Error generating prime: " + ioe);
        }
    }
}
