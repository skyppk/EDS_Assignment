/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.db;

import cf.bean.OrderDetails;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
    
    public boolean addOrderInfo(String orderId, String loginId, String deliveryType, String deliveryDate, String deliveryTime, String deliveryAddress, double orderPrice, ArrayList<OrderDetails> orderDetails){
        Connection cnnct = null;
        Statement stmt = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            cnnct.setAutoCommit(false);
            stmt = cnnct.createStatement();
            stmt.addBatch("INSERT INTO OrderInfo VALUES (null,'" + orderId + "','" + loginId + "','" + deliveryType + "','" + deliveryDate + "','" + deliveryTime + "','" + deliveryAddress + "','" + orderPrice + "',DEFAULT,DEFAULT)");
            if(orderDetails.isEmpty()){
                throw new SQLException();
            }
            for(int i = 0; i < orderDetails.size();i++){
                OrderDetails od = orderDetails.get(i);
                stmt.addBatch("INSERT INTO OrderDetails VALUES (null,'" + orderId + "','" + od.getItemId() + "','" + od.getItemName() + "','" + od.getQuantity() + "','" + od.getBuyPrice() + "','" + od.getDetailsPrice() + "')");
            }
            
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
