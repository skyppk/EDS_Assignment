/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.tag;

import cf.bean.GiftItem;
import cf.bean.ItemInfo;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author apple
 */
public class giftListTag extends SimpleTagSupport {

    private ArrayList<GiftItem> gifts;

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
            out.println("<th>Gift ID</th>");
            out.println("<th>Gift Name</th>");
            out.println("<th>Gift Description</th>");
            out.println("<th>Needed Point</th>");
            out.println("<th>Gift Image</th>");
            out.println("<th>Edit Item</th>");
            out.println("</tr>");
            if (gifts == null) {
                System.out.println("fk you");
            }
            if (gifts != null && gifts.size() > 0) {
                for (GiftItem gift : gifts) {
                    //out.println(item.getItemName() + "<br>");   
                    out.println("<tr><td style=\"vertical-align:middle;\">");
                    out.println(gift.getGiftID());
                    out.println("</td><td style=\"vertical-align:middle; white-space: nowrap; text-overflow: ellipsis; overflow:hidden;\">");
                    out.println(gift.getName());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(gift.getDesc());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(df.format(gift.getPointRequired()));
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(gift.getImgsrc());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println("<a href=\"redeem?action=getEditGift&id=" + gift.getGiftID()+ "\">");
                    out.println("<button type=\"button\" class=\"btn btn-default\" onclick=\"return confirm('Are you sure to continue ?')\">Edit</button>");
                    out.println("</a>");
                    

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
                out.println("</table></div><div class=\"panel-body\"><p class=\"text-center\">No Gift</p></div>");
            }
        } catch (IOException ioe) {
            System.out.println("Error generating prime: " + ioe);
        }
    }

    public void setGifts(ArrayList gifts) {
        this.gifts = gifts;
    }

}
