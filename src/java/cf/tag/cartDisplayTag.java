/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.tag;

import cf.bean.OrderDetails;
import cf.bean.UserInfo;
import java.io.IOException;
import java.text.DecimalFormat;
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
    private String tagType;

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

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
            DecimalFormat df = new DecimalFormat("$ #,##0.00");

            if (items != null&&items.size()!=0) {
                out.println("<div class=\"table-responsive\"><table class=\"table\">");
                out.println("<tr>");
                out.println("<th></th>");
                out.println("<th>Name</th>");
                out.println("<th>Unit Price</th>");
                out.println("<th>Quantity</th>");
                out.println("<th>Price</th>");
                if ("cart".equalsIgnoreCase(tagType)) {
                    out.println("<th>Action</th>");
                }
                out.println("</tr>");

                double total = 0;
                for (OrderDetails item : items) {
                    //out.println(item.getItemName() + "<br>");   
                    out.println("<tr><td>");
                    out.println("<img class=\"img-rounded\" style=\"max-height:70px;\" src=\"img/" + item.getImg() + "\" alt=\"No Image\">");
                    out.println("</td><td style=\"vertical-align:middle; white-space: nowrap; text-overflow: ellipsis; overflow:hidden;\">");
                    out.println(item.getItemName());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(df.format(item.getBuyPrice()));
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(item.getQuantity());
                    out.println("</td><td id='dtprice' style=\"vertical-align:middle;\">");
                    out.println(df.format(item.getDetailsPrice()));
                    if ("cart".equalsIgnoreCase(tagType)) {
                        out.println("</td><td style=\"vertical-align:middle;\">");
                        out.println("<button onclick=\"dropItem('" + item.getItemId() + "',$(this))\" type=\"button\" class=\"btn btn-default\">Cancel</button>");
                    }
                    out.println("</td></tr>");
                    total += item.getDetailsPrice();
//                out.println("<div class=\"row\"");
//                out.println("<div class=\"col-sm-6 col-md-4\">");
//                out.println("<div class=\"caption\">");
//                out.println("<h4 style=\"white-space: nowrap; text-overflow: ellipsis; overflow:hidden;\">" + item.getItemName() + "</h4>");
//                out.println("<p> $" + item.getBuyPrice() + " x " + item.getQuantity() + "</p>");
//                out.println("<p> TOTAL $" + item.getDetailsPrice()+ "</p>");
//                out.println("</div>");
//                out.println("</div>");
                }
                String sTotal = df.format(total);
                out.println("</table></div>");

                if ("cart".equalsIgnoreCase(tagType)) {
                    out.println("<div class=\"panel-body\" style=\"padding-right:20px;\">");
                    out.println("<div class=\"row pull-right\"><h4><small>Total Price: </small><span id='totalValue'>" + sTotal);
                    out.println("</span><small><button type=\"button\" class=\"btn btn-default\" onclick=\"location.href='ShoppingCartServlet?action=placeOrder'\">Order</button></small></h4>");
                    out.println("</div></div></div>");
                } else if ("placeOrder".equalsIgnoreCase(tagType)) {
                    out.println("");
                    out.println("<ul class=\"list-group\"><li class=\"list-group-item text-right\"><small>Total Price: </small><span id=\"totalPriceTag\" style=\"font-size: 18px;\"><b>" + sTotal+"</b></span></li></ul><div class=\"panel-body\"\">");
                    out.println("<form action=\"order\" method=\"post\">");
                    
                    out.println("<div class=\"form-group\">");
                    out.println("<label for=\"deliveryType\">Delivery Type</label><br>");
                    out.println("<label class=\"radio-inline\"><input id=\"deliveryRdo\" type=\"radio\" name=\"deliveryType\" value=\"delivery\" checked>Delivery</label>");
                    out.println("<label class=\"radio-inline\"><input id=\"sPickRdo\" type=\"radio\" name=\"deliveryType\" value=\"self-pick\">Self-pick</label>");
                    out.println("</div>");
                    
                    out.println("<div id=\"deliveryAddGroup\" class=\"form-group\">");
                    out.println("<label for=\"address\">Delivery address</label>");
                    out.println("<input name=\"address\" type=\"address\" class=\"form-control\" id=\"address\">");
                    out.println("</div>");
                    
                    out.println("<div id=\"deliveryDateGroup\" class=\"form-group\">");
                    out.println("<label for=\"date\">Delivery Date</label>");
                    out.println("<input name=\"date\" type=\"date\" class=\"form-control\" id=\"date\">");
                    out.println("</div>");
                    
                    out.println("<div id=\"deliveryTimeGroup\" class=\"form-group\">");
                    out.println("<label for=\"time\">Delivery Time:</label><br>");
                    out.println("<label class=\"radio-inline\"><input type=\"radio\" name=\"time\" value=\"AM\" checked>AM</label>");
                    out.println("<label class=\"radio-inline\"><input type=\"radio\" name=\"time\" value=\"PM\" checked>PM</label>");
                    out.println("<input type=\"hidden\" id=\"totalPrice\" name=\"total\" value=\""+total+"\">");
                    out.println("</div>");
                    
                    out.println("<button onclick=\"checkStatus()\" type=\"button\" class=\"btn btn-default\">Submit</button>");
                    out.println("</div>");
                }
            } else {
                out.println("<div class=\"panel-body\"><p class=\"text-center\">No item</p></div>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
