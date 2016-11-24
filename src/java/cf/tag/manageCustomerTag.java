/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.tag;

import cf.bean.ItemInfo;
import cf.bean.OrderDetails;
import cf.bean.UserInfo;
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
public class manageCustomerTag extends SimpleTagSupport {

    private ArrayList<UserInfo> customers;

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
            out.println("<th>Login ID</th>");
            out.println("<th>Last Name</th>");
            out.println("<th>First Name</th>");
            out.println("<th>Sex</th>");
            out.println("<th>Birthday</th>");
            out.println("<th>Tel</th>");
            out.println("<th>Address</th>");
            out.println("<th>Email</th>");
            out.println("<th>Money</th>");
            out.println("<th>Credit Amount</th>");
            out.println("<th>Bonus Point</th>");
            out.println("<th>User Status</th>");
            out.println("<th colspan=\"2\">Action</th>");
            out.println("</tr>");
            if(customers == null){
                System.out.println("fk you");
            }
            if (customers != null) {
                for (UserInfo user : customers) {
                    //out.println(item.getItemName() + "<br>");   
                    out.println("<tr><td style=\"vertical-align:middle;\">");
                    out.println(user.getLoginId());
                    out.println("</td><td style=\"vertical-align:middle; white-space: nowrap; text-overflow: ellipsis; overflow:hidden;\">");
                    out.println(user.getLastName());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(user.getFirstName());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(user.getSex());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(user.getBirthday());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(user.getTel());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(user.getAddress());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(user.getEmail());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(df.format(user.getMoney()));
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(df.format(user.getCreditAmount()));
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(df.format(user.getBonusPoints()));
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    out.println(user.getUserStatus());
                    out.println("</td><td style=\"vertical-align:middle;\">");
                    if(user.getUserStatus().equalsIgnoreCase("NEW")){
                        out.println("<a href=\"editCustomer?action=newUser&id=" + user.getId() + "\">");
                        out.println("<button type=\"button\" class=\"btn btn-default\" onclick=\"return confirm('Are you sure to continue ?')\">Accept</button>");
                        out.println("</a>");
                        out.println("</td><td style=\"vertical-align:middle;\">");
                        out.println("<a href=\"editCustomer?action=decline&id=" + user.getId() + "&userStatus=DECLINE\">");
                        out.println("<button type=\"button\" class=\"btn btn-default\" onclick=\"return confirm('Are you sure to continue ?')\">Decline</button>");
                        out.println("</a>");
                    }else if(user.getUserStatus().equalsIgnoreCase("ACCEPTED")){
                        out.println("<a href=\"handleCustomer?action=getEditCustomer&id=" + user.getId() + "\">");
                        out.println("<button type=\"button\" class=\"btn btn-default\" onclick=\"return confirm('Are you sure to continue ?')\">Edit</button>");
                        out.println("</a>");
                        out.println("</td><td style=\"vertical-align:middle;\">");
                        out.println("<a href=\"editCustomer?action=updateStatus&id=" + user.getId() + "&userStatus=BLOCK\">");
                        out.println("<button type=\"button\" class=\"btn btn-default\" onclick=\"return confirm('Are you sure to continue ?')\">Block</button>");
                        out.println("</a>");
                    }else if(user.getUserStatus().equalsIgnoreCase("BLOCK")){
                        out.println("<a href=\"editCustomer?action=updateStatus&id=" + user.getId() + "&userStatus=ACCEPTED\">");
                        out.println("<button type=\"button\" class=\"btn btn-default\" onclick=\"return confirm('Are you sure to continue ?')\">Accept</button>");
                        out.println("</a>");
                    }else if(user.getUserStatus().equalsIgnoreCase("DECLINE")){
                        out.println("<a href=\"editCustomer?action=newUser&id=" + user.getId() + "\">");
                        out.println("<button type=\"button\" class=\"btn btn-default\" onclick=\"return confirm('Are you sure to continue ?')\">Accept</button>");
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

    public void setCustomers(ArrayList customers) {
        this.customers = customers;
    }
    
}
