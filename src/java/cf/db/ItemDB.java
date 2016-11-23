/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.db;

import cf.bean.ItemInfo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author apple
 */
public class ItemDB {
    String dburl;
    String dbUser;
    String dbPassword;
    
    public ItemDB(){
        
    }
    
    public ItemDB(String dburl, String dbUser, String dbPassword) {
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
    
       public void CreateItemInfoTable(){
       Connection cnnct = null;
        Statement stmnt = null;
        
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "CREATE TABLE IF NOT EXISTS ItemInfo ("
                    + "id int NOT NULL AUTO_INCREMENT,"
                    + "item_id varchar(30) NOT NULL,"
                    + "item_name varchar(50) NOT NULL,"
                    + "category varchar(20) NOT NULL,"
                    + "designer_name varchar(30) NOT NULL,"
                    + "price double NOT NULL,"
                    + "descriptions varchar(255) NOT NULL,"                   
                    + "img varchar(255) NOT NULL,"
                    + "item_status varchar(15) DEFAULT 'AVAILABLE',"
                    + "UNIQUE (item_id),"
                    + "PRIMARY KEY (id)"
                    + ")";
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
    
    public boolean addItemInfo(String itemId, String itemName, String category, String designerName, double price, String descriptions, String img){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO ItemInfo VALUES (null,?,?,?,?,?,?,?,DEFAULT)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, itemId);
            pStmnt.setString(2, itemName);
            pStmnt.setString(3, category);
            pStmnt.setString(4, designerName);
            pStmnt.setDouble(5, price);
            pStmnt.setString(6, descriptions);
            pStmnt.setString(7, img);
//            pStmnt.setString(8, "AVAILABLE");
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
    
    public boolean editItemInfo(int id, double price , String descriptions, String itemStatus){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "UPDATE ItemInfo SET price = ? , descriptions = ? , item_status =? WHERE id = ? ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setDouble(1, price);
            pStmnt.setString(2, descriptions);
            pStmnt.setString(3, itemStatus);
            pStmnt.setInt(4, id);
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
    
    public boolean editItemInfo(ItemInfo itemInfo){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "UPDATE ItemInfo SET price = ? , descriptions = ? , item_status = ? WHERE id = ? ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setDouble(1, itemInfo.getPrice());
            pStmnt.setString(2, itemInfo.getDescriptions());
            pStmnt.setString(3, itemInfo.getItemStatus());
            pStmnt.setInt(4, itemInfo.getId());
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
    
    public ArrayList<ItemInfo> selectAllItem(){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ItemInfo item = null;
        ArrayList<ItemInfo> items = new ArrayList();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM ItemInfo";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
//            pStmnt.setString(1, tel);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while(rs.next()){
                item = new ItemInfo();
                item.setId(rs.getInt("id"));
                item.setItemId(rs.getString("item_id"));
                item.setItemName(rs.getString("item_name"));
                item.setCategory(rs.getString("category"));
                item.setDesignerName(rs.getString("designer_name"));
                item.setPrice(rs.getDouble("price"));
                item.setDescriptions(rs.getString("descriptions"));
                item.setImg(rs.getString("img"));
                item.setItemStatus(rs.getString("item_status"));
                items.add(item);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return items;
    }
    
    public ArrayList<ItemInfo> selectAvailableItem(){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ItemInfo item = null;
        ArrayList<ItemInfo> items = new ArrayList();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM ItemInfo WHERE item_status = 'AVAILABLE';";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
//            pStmnt.setString(1, tel);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while(rs.next()){
                item = new ItemInfo();
                item.setId(rs.getInt("id"));
                item.setItemId(rs.getString("item_id"));
                item.setItemName(rs.getString("item_name"));
                item.setCategory(rs.getString("category"));
                item.setDesignerName(rs.getString("designer_name"));
                item.setPrice(rs.getDouble("price"));
                item.setDescriptions(rs.getString("descriptions"));
                item.setImg(rs.getString("img"));
                item.setItemStatus(rs.getString("item_status"));
                items.add(item);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return items;
    }
    
    public boolean checkItemId(String itemId){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isVaild = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM ItemInfo WHERE item_id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, itemId);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while(rs.next()){
                isVaild = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return isVaild;
    }
    
    public ItemInfo queryItemDetail(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ItemInfo item = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM ItemInfo WHERE item_id = ? AND item_status = 'AVAILABLE';";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                item = new ItemInfo();
                item.setId(rs.getInt("id"));
                item.setItemId(rs.getString("item_id"));
                item.setItemName(rs.getString("item_name"));
                item.setCategory(rs.getString("category"));
                item.setDesignerName(rs.getString("designer_name"));
                item.setPrice(rs.getDouble("price"));
                item.setDescriptions(rs.getString("descriptions"));
                item.setImg(rs.getString("img"));
                item.setItemStatus(rs.getString("item_status"));
                
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
        return item;
    }
    
    public ArrayList<ItemInfo> searchItemByInput(String input) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ItemInfo item = null;
        ArrayList<ItemInfo> items = new ArrayList();
        String search = "%"+input+"%";
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM ItemInfo WHERE item_name LIKE ? OR category LIKE ? OR designer_name LIKE ? OR price LIKE ? AND item_status = 'AVAILABLE';";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
//            pStmnt.setString(1, "%" + input + "%");
//            pStmnt.setString(2, "%" + input + "%");
//            pStmnt.setString(3, "%" + input + "%");
//            pStmnt.setString(4, "%" + input + "%");
            pStmnt.setString(1, search);
            pStmnt.setString(2, search);
            pStmnt.setString(3, search);
            pStmnt.setString(4, search);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            
            while(rs.next()) {
                item = new ItemInfo();
                item.setId(rs.getInt("id"));
                item.setItemId(rs.getString("item_id"));
                item.setItemName(rs.getString("item_name"));
                item.setCategory(rs.getString("category"));
                item.setDesignerName(rs.getString("designer_name"));
                item.setPrice(rs.getDouble("price"));
                item.setDescriptions(rs.getString("descriptions"));
                item.setImg(rs.getString("img"));
                item.setItemStatus(rs.getString("item_status"));
                items.add(item);
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
        return items;
    }
    
    public boolean dropItemInfoTable(){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "DROP TABLE ItemInfo ";
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
