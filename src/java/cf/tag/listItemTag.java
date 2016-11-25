/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.tag;

import cf.bean.ItemInfo;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author apple
 */
public class listItemTag extends SimpleTagSupport {

    private ArrayList<ItemInfo> items;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
            DecimalFormat df = new DecimalFormat("$ #,##0.0");
            
            out.println("<div class=\"table-responsive\"><table class=\"table\">");
            out.println("<tr>");
            out.println("<th>Item ID</th>");
            out.println("<th>Item Name</th>");
            out.println("<th>Category</th>");
            out.println("<th>Designer Name</th>");
            out.println("<th>Price</th>");
            out.println("<th>Desciptions</th>");
            out.println("<th>Image</th>");
            out.println("<th>Item Status</th>");
            out.println("<th colspan=\"2\">Action</th>");
            out.println("</tr>");
            if(items == null){
                System.out.println("fk you");
            }
            if (items != null) {
                for (ItemInfo item : items) {
                    //out.println(item.getItemName() + "<br>");   
                    out.println("<tr><td style=\"vertical-align:middle;\">");
                    out.println(item.getItemId());
                    out.println("</td><td style=\"vertical-align:middle; white-space: nowrap; text-overflow: ellipsis; overflow:hidden;\">");
                    out.println(item.getItemName());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(item.getCategory());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(item.getDesignerName());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(df.format(item.getPrice()));
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(item.getDescriptions());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println("<img src =\"img/" + item.getImg() + "\" style=\"max-height:40px;\">");
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(item.getItemStatus());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    if(item.getItemStatus().equalsIgnoreCase("AVAILABLE")){
                        out.println("<a href=\"product?action=getEditItem&id=" + item.getItemId()+ "\">");
                        out.println("<button type=\"button\" class=\"btn btn-default\" onclick=\"return confirm('Are you sure to continue ?')\">Edit</button>");
                        out.println("</a>");
                        out.println("</td><td style=\"vertical-align:middle;\">");
                        out.println("<a href=\"editItem?action=updateStatus&id=" + item.getId() + "&status=UNAVAILABLE\">");
                        out.println("<button type=\"button\" class=\"btn btn-default\" onclick=\"return confirm('Are you sure to continue ?')\">Change Status</button>");
                        out.println("</a>");
                    }else if(item.getItemStatus().equalsIgnoreCase("UNAVAILABLE")){
                        out.println("<a href=\"editItem?action=updateStatus&id=" + item.getId() + "&status=AVAILABLE\">");
                        out.println("<button type=\"button\" class=\"btn btn-default\" onclick=\"return confirm('Are you sure to continue ?')\">Change Status</button>");
                        out.println("</a>");
                    }
         
                    
                    
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
                
            } else {
                out.println("<p>No User !</p>");
            }
        } catch (IOException ioe) {
            System.out.println("Error generating prime: " + ioe);
        }
    }

    public void setItems(ArrayList items) {
        this.items = items;
    }
    
}
