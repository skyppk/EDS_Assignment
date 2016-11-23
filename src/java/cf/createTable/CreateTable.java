/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.createTable;

import cf.bean.ItemInfo;
import cf.bean.OrderDetails;
import cf.db.ItemDB;
import cf.db.OrderDB;
import cf.db.UserDB;
import java.util.ArrayList;

/**
 *
 * @author apple
 */
public class CreateTable {

    public static void main(String[] arg) {
        String url = "jdbc:mysql://localhost:3306/ESD_Assignment";
//        String url = "jdbc:mysql://dev16.asuscomm.com:3306/ESD_Assignment";
        String username = "root";
        String password = "";
//        String password = "root";
        UserDB userDb = new UserDB(url, username, password);
        ItemDB itemDb = new ItemDB(url, username, password);
        OrderDB orderDb = new OrderDB(url, username, password);
        userDb.dropUserInfoTable();
        userDb.dropAccountInfoTable();
        itemDb.dropItemInfoTable();
        orderDb.dropOrderInfoTable();
        orderDb.dropOrderDetailsTable();
        userDb.CreateUserInfoTable();
        itemDb.CreateItemInfoTable();
        userDb.CreateAccountInfoTable();
        orderDb.CreateOrderInfoTable();
        orderDb.CreateOrderDetailsTable();
//        addItemInfo(String itemId, String itemName, String category, String designerName, double price, String descriptions, String img, String itemStatus )

        itemDb.addItemInfo("IT1", "Item1", "PIG", "SYW", 33.1, "SYW IS A PIG", "pig.png");
        itemDb.addItemInfo("IT2", "Goosecraft Leather Jacket With Faux Fur Collar", "Jacket", "Goosecraft", 2800, "Body: 100% Real Leather, Lining: 100% Polyester.", "j1.jpeg");
        itemDb.addItemInfo("IT3", "Rains Waterproof Jacket", "Jacket", "Rains", 750, "Body: 50% Polyester, 50% Polyurethane.", "j2.jpeg");
        itemDb.addItemInfo("IT4", "Goosecraft Leather Bomber Jacket", "Jacket", "Goosecraft", 2100, "Body: 100% Real Leather, Body Lining: 100% Cotton, Sleeve Lining: 100% Polyester.", "j3.jpeg");
        itemDb.addItemInfo("IT5", "Free People Tiny Dot Printed Trapeze Slip Dress", "Dress", "Free People", 780, "Body: 100% Rayon, Trim: 100% Nylon.", "d1.jpeg");
        itemDb.addItemInfo("IT6", "Samsoe & Samsoe Theta T Neck Dress", "Dress", "Samsoe & Samsoe", 1400, "Main: 82% Viscose, 18% Silk.", "d2.jpeg");
        itemDb.addItemInfo("IT7", "C/meo Collective Need Dress with Pleats", "Dress", "C/meo Collective", 1500, "Body: 100% Polyester.", "d3.jpeg");
        itemDb.addItemInfo("IT9", "Lovers + Friends Floral Cami Maxi Dress with Split", "Dress", "Lovers + Friends", 1950, "Body: 70% Viscose, 30% Nylon, Lining: 97% Polyester, 3% Elastane.", "d4.jpeg");
        
        
//        addUserInfo(String lastName,String firstName, String sex, String birthday, int tel, String address, String email)

        userDb.addUserInfo("Wong", "Shuk Yan", "F", "1995-9-2", "34329483", "pig street", "sywispig@gmail.com");
        userDb.addUserAccountInfo(1, "ting", "ting");
//        addOrderInfo(String orderId, String loginId, String deliveryType, String deliverDate, String deliveryTime, String deliveryAddress, double orderPrice)
        ArrayList<OrderDetails> orderDetails = new ArrayList();
        ArrayList<OrderDetails> orderDetails2 = new ArrayList();
        ArrayList<ItemInfo> items = itemDb.selectAvailableItem();
        for(int i = 0; i < items.size(); i++){
            ItemInfo item = items.get(i);
            OrderDetails od = new OrderDetails(item.getItemId(),item.getItemName(),2,item.getPrice(),2 * item.getPrice());
            orderDetails.add(od);
        }
        orderDb.addOrderInfo("o1", "syw", "prick", "2034-4-4", "PM", "pig street", 999.4,orderDetails);
        orderDb.addOrderInfo("o2", "syw", "prick", "2034-4-4", "PM", "pig street", 999.4,orderDetails2);
        
        
        if(userDb.isValidUser("ting", "ting")){
            System.out.println("Sus");
        };
        
    }
}
