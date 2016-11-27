/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.db;

import cf.bean.OrderDetails;
import cf.bean.OrderInfo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author apple
 */
public class OrderDB {
    String dburl;
    String dbUser;
    String dbPassword;

    public OrderDB() {
    }

    public OrderDB(String dburl, String dbUser, String dbPassword) {
        this.dburl = dburl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }
    
    public Connection getConnection() throws SQLException, IOException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(dburl, dbUser, dbPassword);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public void CreateOrderInfoTable(){
       Connection cnnct = null;
        Statement stmnt = null;
        
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "CREATE TABLE IF NOT EXISTS OrderInfo ("
                    + "id int NOT NULL AUTO_INCREMENT,"
                    + "order_id varchar(20) NOT NULL,"
                    + "login_id varchar(25) NOT NULL,"
                    + "delivery_type varchar(15) NOT NULL,"
                    + "delivery_date Date,"
                    + "delivery_time varchar(2),"
                    + "delivery_address varchar(255),"
                    + "order_price double NOT NULL,"
                    + "order_date DateTime DEFAULT CURRENT_TIMESTAMP,"
                    + "order_status varchar(15) DEFAULT 'WAITING',"
                    + "UNIQUE (order_id),"
                    + "PRIMARY KEY (id)"
                    + ");";
            stmnt.execute(sql);
            stmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        } 
    }
    
    public void CreateOrderDetailsTable(){
       Connection cnnct = null;
        Statement stmnt = null;
        
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "CREATE TABLE IF NOT EXISTS OrderDetails ("
                    + "id int NOT NULL AUTO_INCREMENT,"
                    + "order_id varchar(20) NOT NULL,"
                    + "item_id varchar(30) NOT NULL,"
                    + "item_name varchar(50) NOT NULL,"
                    + "quantity int NOT NULL,"
                    + "buy_price double NOT NULL,"
                    + "details_price double NOT NULL,"
                    + "item_img varchar(255) NOT NULL,"
                    + "PRIMARY KEY (id)"
                    + ");";
            stmnt.execute(sql);
            stmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        } 
    }
    
    public ArrayList<OrderInfo> queryOrderHistory(String userId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        OrderInfo order = null;
        ArrayList<OrderInfo> orders = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM OrderInfo WHERE login_id = ? order by id desc limit 10;";
            System.out.println(preQueryStatement);
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, userId);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                order = new OrderInfo();
                order.setId(rs.getInt("id"));
                order.setLoginId(rs.getString("login_id"));
                order.setOrderId(rs.getString("order_id"));
                order.setDeliveryType(rs.getString("delivery_type"));
                order.setDeliveryDate(rs.getString("delivery_date"));
                order.setDeliveryTime(rs.getString("delivery_time"));
                order.setDeliveryAddress(rs.getString("delivery_address"));
                order.setOrderDate(rs.getString("order_date"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setOrderPrice(rs.getDouble("order_price"));
                orders.add(order);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return orders;
    }
    
    public ArrayList<OrderInfo> queryAllOrder() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        OrderInfo order = null;
        ArrayList<OrderInfo> orders = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM OrderInfo;";
            System.out.println(preQueryStatement);
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                order = new OrderInfo();
                order.setId(rs.getInt("id"));
                order.setLoginId(rs.getString("login_id"));
                order.setOrderId(rs.getString("order_id"));
                order.setDeliveryType(rs.getString("delivery_type"));
                order.setDeliveryDate(rs.getString("delivery_date"));
                order.setDeliveryTime(rs.getString("delivery_time"));
                order.setDeliveryAddress(rs.getString("delivery_address"));
                order.setOrderDate(rs.getString("order_date"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setOrderPrice(rs.getDouble("order_price"));
                
                orders.add(order);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return orders;
    }
    
    public ArrayList<OrderInfo> queryExistingOrder(String userId,String status) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        OrderInfo order = null;
        ArrayList<OrderInfo> orders = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM OrderInfo WHERE login_id = ? AND order_status = ? order by id desc limit 10;";
            System.out.println(preQueryStatement);
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, userId);
            pStmnt.setString(2, status);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                order = new OrderInfo();
                order.setId(rs.getInt("id"));
                order.setLoginId(rs.getString("login_id"));
                order.setOrderId(rs.getString("order_id"));
                order.setDeliveryType(rs.getString("delivery_type"));
                order.setDeliveryDate(rs.getString("delivery_date"));
                order.setDeliveryTime(rs.getString("delivery_time"));
                order.setDeliveryAddress(rs.getString("delivery_address"));
                order.setOrderDate(rs.getString("order_date"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setOrderPrice(rs.getDouble("order_price"));
                
                orders.add(order);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return orders;
    }
    
    public OrderInfo queryOrderById(String id,String orderId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        OrderInfo order = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM OrderInfo WHERE login_id = ? AND order_id = ? order by id desc;";
            System.out.println(preQueryStatement);
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            pStmnt.setString(2, orderId);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                order = new OrderInfo();
                order.setId(rs.getInt("id"));
                order.setLoginId(rs.getString("login_id"));
                order.setOrderId(rs.getString("order_id"));
                order.setDeliveryType(rs.getString("delivery_type"));
                order.setDeliveryDate(rs.getString("delivery_date"));
                order.setDeliveryTime(rs.getString("delivery_time"));
                order.setDeliveryAddress(rs.getString("delivery_address"));
                order.setOrderDate(rs.getString("order_date"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setOrderPrice(rs.getDouble("order_price"));                
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return order;
    }
    
    public OrderInfo queryOrderDetailById(String id,String orderId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        OrderInfo order = new OrderInfo();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT OrderInfo.id as oId, OrderDetails.order_id, login_id, delivery_date, delivery_time, delivery_type, delivery_address, order_price,order_date,order_status, item_id, item_name,quantity,buy_price,details_price FROM OrderInfo INNER JOIN OrderDetails ON OrderInfo.order_id = OrderDetails.order_id WHERE login_id = ? AND OrderDetails.order_id = ?;";
            System.out.println(preQueryStatement);
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            pStmnt.setString(2, orderId);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            ArrayList<OrderDetails> details = new ArrayList();
            OrderDetails detail;
            
            while (rs.next()) {
//                order = new OrderInfo();
                order.setId(rs.getInt("oid"));
                order.setLoginId(rs.getString("login_id"));
                order.setOrderId(rs.getString("order_id"));
                order.setDeliveryType(rs.getString("delivery_type"));
                order.setDeliveryDate(rs.getString("delivery_date"));
                order.setDeliveryTime(rs.getString("delivery_time"));
                order.setDeliveryAddress(rs.getString("delivery_address"));
                order.setOrderDate(rs.getString("order_date"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setOrderPrice(rs.getDouble("order_price"));    
                
                detail = new OrderDetails();
                detail.setItemId(rs.getString("item_id"));
                detail.setItemName(rs.getString("item_name"));
//                detail.setImg(rs.getString("item_id"));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setBuyPrice(rs.getDouble("buy_price"));
                detail.setDetailsPrice(rs.getDouble("details_price"));
                detail.setImg(rs.getString("item_img"));
                details.add(detail);
            }
            order.setOrderDetails(details);
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return order;
    }
    
    public boolean updateStatus(String id,String status) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        int ids = Integer.parseInt(id);
        System.out.println(ids);
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE OrderInfo SET order_status = ? WHERE id = ?;";
            System.out.println(preQueryStatement);
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, status);
            pStmnt.setInt(2, ids);
            ResultSet rs = null;
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public boolean updateDeliveryDate(String userId, String order, String date, String time) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE OrderInfo SET delivery_date = ?, delivery_time = ? WHERE login_id = ? AND order_id = ?;";
            
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, date);
            pStmnt.setString(2, time);
            pStmnt.setString(3, userId);
            pStmnt.setString(4, order);
            System.out.println(preQueryStatement);
             System.out.println(pStmnt);
            ResultSet rs = null;
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
//    public boolean deleteOrderById(String id,String orderId) {
//        Connection cnnct = null;
//        PreparedStatement pStmnt = null;
//        boolean isSuccess = false;
//        try {
//            cnnct = getConnection();
//            String preQueryStatement = "DELETE FROM OrderInfo WHERE login_id = ? AND order_id = ?";
//            pStmnt = cnnct.prepareStatement(preQueryStatement);
//            pStmnt.setString(1, id);
//            pStmnt.setString(2, orderId);
//            int rowCount = pStmnt.executeUpdate();
//            if (rowCount >= 1) {
//                isSuccess = true;
//            }
//            pStmnt.close();
//            cnnct.close();
//        } catch (SQLException ex) {
//            while (ex != null) {
//                ex.printStackTrace();
//                ex = ex.getNextException();
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return isSuccess;
//    }
    
    public boolean addOrderInfo(String orderId, String loginId, String deliveryType, String deliveryDate, String deliveryTime, String deliveryAddress, double orderPrice, ArrayList<OrderDetails> orderDetails,double bonusPoint){
        Connection cnnct = null;
        Statement stmt = null;
        PreparedStatement pStmnt = null;
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String now = formatter.format(today);
        String deliveryDay = deliveryDate != null ? deliveryDate : now;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            cnnct.setAutoCommit(false);
            stmt = cnnct.createStatement();
            stmt.addBatch("INSERT INTO OrderInfo VALUES (null,'" + orderId + "','" + loginId + "','" + deliveryType + "','" + deliveryDay + "','" + deliveryTime + "','" + deliveryAddress + "','" + orderPrice + "',DEFAULT,DEFAULT)");
            if(orderDetails.isEmpty()){
                throw new SQLException();
            }
            for(int i = 0; i < orderDetails.size();i++){
                OrderDetails od = orderDetails.get(i);
                stmt.addBatch("INSERT INTO OrderDetails VALUES (null,'" + orderId + "','" + od.getItemId() + "','" + od.getItemName() + "','" + od.getQuantity() + "','" + od.getBuyPrice() + "','" + od.getDetailsPrice() + "','" + od.getImg() + "')");
            }
            
            stmt.addBatch("UPDATE AccountInfo SET bonus_point = bonus_point + " + bonusPoint + " , money = money - "+ orderPrice + "WHERE login_id = '" + loginId + "'");
            
            int counts[] = stmt.executeBatch();
            System.out.println("Order Details :" + counts.length);
//            String preQueryStatement = "INSERT INTO OrderInfo VALUES (null,?,?,?,?,?,?,?,DEFAULT,DEFAULT)";
//            pStmnt = cnnct.prepareStatement(preQueryStatement);
//            pStmnt.setString(1, orderId);
//            pStmnt.setString(2, loginId);
//            pStmnt.setString(3, deliveryType);
//            pStmnt.setString(4, deliveryDate);
//            pStmnt.setString(5, deliveryTime);
//            pStmnt.setString(6, deliveryAddress);
//            pStmnt.setDouble(7, orderPrice);
//            pStmnt.addBatch();
//            String preQueryStatement2 = "INSERT INTO OrderDetails VALUES (null,?,?,?,?,?,?)";
//            for(int i =0; i < orderDetails.size();i++){
//                OrderDetails od = orderDetails.get(i);
//                pStmnt = cnnct.prepareStatement(preQueryStatement2);
//                pStmnt.setString(1, orderId);
//                pStmnt.setString(2, od.getItemId());
//                pStmnt.setString(3, od.getItemName());
//                pStmnt.setInt(4, od.getQuantity());
//                pStmnt.setDouble(5, od.getBuyPrice());
//                pStmnt.setDouble(6, od.getDetailsPrice());
//                pStmnt.addBatch();
//            }
//            int[] rowCount = pStmnt.executeBatch();
//            System.out.println("Order Details :" + rowCount.length);
            cnnct.commit();
//            pStmnt.close();
            cnnct.close();
            isSuccess = true;
            
        } catch (SQLException ex){
            if(cnnct != null){
                try{
                    cnnct.rollback();
                }catch(SQLException ex1){
                    ex1.printStackTrace();
                }
            }
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    
    
    public boolean dropOrderInfoTable(){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "DROP TABLE OrderInfo ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >= 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public boolean dropOrderDetailsTable(){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "DROP TABLE OrderDetails ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >= 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
}
