/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.db;

import cf.bean.GiftItem;
import cf.bean.UserInfo;
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
public class GiftDB {

    String dburl;
    String dbUser;
    String dbPassword;

    public GiftDB() {
    }

    public GiftDB(String dburl, String dbUser, String dbPassword) {
        this.dburl = dburl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(dburl, dbUser, dbPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void CreateGiftInfoTable() {
        Connection cnnct = null;
        Statement stmnt = null;

        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "CREATE TABLE IF NOT EXISTS GiftInfo ("
                    + "gift_id int NOT NULL AUTO_INCREMENT,"
                    + "gift_name varchar(30) NOT NULL,"
                    + "gift_descriptions varchar(255) NOT NULL,"
                    + "gift_img varchar(255) NOT NULL,"
                    + "needed_point double NOT NULL,"
                    + "PRIMARY KEY (gift_id)"
                    + ")";
            stmnt.execute(sql);
            stmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void CreateBonusHistoryTable() {
        Connection cnnct = null;
        Statement stmnt = null;

        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "CREATE TABLE IF NOT EXISTS BonusHistory ("
                    + "bonus_id int NOT NULL AUTO_INCREMENT,"
                    + "login_id varchar(25) NOT NULL,"
                    + "gift_id int,"
                    + "gift_name varchar(30) NOT NULL,"
                    + "gift_descriptions varchar(255) NOT NULL,"
                    + "gift_img varchar(255) NOT NULL,"
                    + "used_point double NOT NULL,"
                    + "PRIMARY KEY (bonus_id)"
                    + ");";
            stmnt.execute(sql);
            stmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public ArrayList<GiftItem> pullGiftItemList(){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<GiftItem> items = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM GiftInfo";
            System.out.println(preQueryStatement);
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                GiftItem item = new GiftItem(
                        rs.getInt("gift_id"),
                        rs.getString("gift_name"),
                        rs.getString("gift_descriptions"),
                        rs.getString("gift_img"),
                        rs.getDouble("needed_point")
                );
                
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
    public GiftItem getSingleItem(String id){
        for(GiftItem item : pullGiftItemList()){
            if(item.getGiftID() == Integer.parseInt(id))
                return item;
        }
        return null;
    }
    public boolean redeemItem(GiftItem item,UserInfo info){
        Connection cnnct = null;
        Statement stmt = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            cnnct.setAutoCommit(false);
            stmt = cnnct.createStatement();
            stmt.addBatch("UPDATE AccountInfo SET bonus_point = bonus_point - " + item.getPointRequired() + "WHERE login_id = '" + info.getLoginId() + "'");
            if(item==null || info==null){
                throw new SQLException();
            }            
            stmt.addBatch("INSERT INTO BonusHistory (login_id,gift_id,gift_name,gift_descriptions,gift_img,used_point)"
                    +" VALUES ("+
                            "'"+info.getLoginId()+"',"+
                            "'"+item.getGiftID()+"',"+
                            "'"+item.getName()+"',"+
                            "'"+item.getDesc()+"',"+
                            "'"+item.getImgsrc()+"',"+
                            "'"+item.getPointRequired()+"'"+
                    ")");
            
            int counts[] = stmt.executeBatch();
            cnnct.commit();
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
    public ArrayList<GiftItem> getHistory(UserInfo info){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<GiftItem> items = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM BonusHistory WHERE login_id = '"+info.getLoginId()+"'";
            System.out.println(preQueryStatement);
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                GiftItem item = new GiftItem(
                        rs.getInt("bonus_id"),
                        rs.getString("gift_name"),
                        rs.getString("gift_descriptions"),
                        rs.getString("gift_img"),
                        rs.getDouble("used_point")
                );
                
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
    
    public GiftItem getGiftDetails(int id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        GiftItem item = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM GiftInfo WHERE gift_id = '" + id + "'";
            System.out.println(preQueryStatement);
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                item = new GiftItem(
                        rs.getInt("gift_id"),
                        rs.getString("gift_name"),
                        rs.getString("gift_descriptions"),
                        rs.getString("gift_img"),
                        rs.getDouble("needed_point")
                );
                
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
    
    public boolean editGiftInfo(int id, String name, String descriptions, double needPoint, String img) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE GiftInfo SET gift_name = ? , gift_descriptions = ? , needed_point = ? , gift_img = ? WHERE gift_id = ? ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, name);
            pStmnt.setString(2, descriptions);
            pStmnt.setDouble(3, needPoint);
            pStmnt.setString(4, img);
            pStmnt.setInt(5, id);
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
}
