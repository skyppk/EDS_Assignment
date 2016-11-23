/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
    
    public Connection getConnection() throws SQLException, IOException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(dburl, dbUser, dbPassword);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public void CreateGiftInfoTable(){
       Connection cnnct = null;
        Statement stmnt = null;
        
        try{
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
        } catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        } 
    }
    
    public void CreateBonusHistoryTable(){
       Connection cnnct = null;
        Statement stmnt = null;
        
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "CREATE TABLE IF NOT EXISTS BonusHistory ("
                    + "bonus_id int NOT NULL AUTO_INCREMENT,"
                    + "login_id varchar(25) NOT NULL,"
                    + "gift_id int NOT NUL,"
                    + "gift_descriptions varchar(255) NOT NULL,"
                    + "gift_img varchar(255) NOT NULL,"
                    + "used_point double NOT NULL,"
                    + "PRIMARY KEY (gift_id)"
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
    
    
}
