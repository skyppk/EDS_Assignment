/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.tag;

import cf.bean.OrderInfo;
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
public class orderListTag extends SimpleTagSupport {

    private ArrayList<OrderInfo> orders;

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
            out.println("<th>Order ID</th>");
            out.println("<th>Login ID</th>");
            out.println("<th>Delivery Type</th>");
            out.println("<th>Delivery Date</th>");
            out.println("<th>Delivery Time</th>");
            out.println("<th>Delivery Address</th>");
            out.println("<th>Order Price</th>");
            out.println("<th>Order Date</th>");
            out.println("<th>Order Status</th>");
            out.println("<th colspan=\"2\">Update Status</th>");
            out.println("</tr>");
            if(orders == null){
                System.out.println("fk you");
            }
            if (orders != null && orders.size()>0) {
                for (OrderInfo order : orders) {
                    //out.println(item.getItemName() + "<br>");   
                    out.println("<form method=\"POST\" action=\"orderHistory\"><tr><td style=\"vertical-align:middle;\">");
                    out.println(order.getOrderId());
                    out.println("</td><td style=\"vertical-align:middle; white-space: nowrap; text-overflow: ellipsis; overflow:hidden;\">");
                    out.println(order.getLoginId());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(order.getDeliveryType());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(order.getDeliveryDate());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(order.getDeliveryTime());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(order.getDeliveryAddress());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(order.getOrderDetails());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(order.getOrderDate());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(order.getOrderStatus());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    
                    if(order.getOrderStatus().equalsIgnoreCase("Waiting") && order.getDeliveryType().equalsIgnoreCase("self-pick")){
                        out.println("<a href=\"orderHistory?action=update&id=" + order.getId() + "&status=ARRIVED\">");
                        out.println("<button type=\"button\" class=\"btn btn-default\" onclick=\"return confirm('Are you sure to continue ?')\">Arrived</button>");
                        out.println("</a>");
                    
                    }if(order.getOrderStatus().equalsIgnoreCase("Arrived") && order.getDeliveryType().equalsIgnoreCase("self-pick")){
                        out.println("<a href=\"orderHistory?action=update&id=" + order.getId() + "&status=FINISH\">");
                        out.println("<button type=\"button\" class=\"btn btn-default\" onclick=\"return confirm('Are you sure to continue ?')\">Finish</button>");
                        out.println("</a>");
                    }
         
                    
                    
                    out.println("</td></tr></form>");
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
                out.println("</table></div><div class=\"panel-body\"><p class=\"text-center\">No Order</p></div>");
            }
        } catch (IOException ioe) {
            System.out.println("Error generating prime: " + ioe);
        }
    }

    public void setOrders(ArrayList orders) {
        this.orders = orders;
    }
    
}
