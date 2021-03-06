/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.db;

import cf.bean.StaffInfo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author apple
 */
public class StaffDB {
    String dburl;
    String dbUser;
    String dbPassword;

    public StaffDB() {
    }

    public StaffDB(String dburl, String dbUser, String dbPassword) {
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

    public void CreateStaffInfoTable() {
        Connection cnnct = null;
        Statement stmnt = null;

        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "CREATE TABLE IF NOT EXISTS StaffInfo ("
                    + "id int NOT NULL AUTO_INCREMENT,"
                    + "login_id varchar(25) NOT NULL,"
                    + "last_name varchar(25) NOT NULL,"
                    + "first_name varchar(25) NOT NULL,"
                    + "sex varchar(1) NOT NULL,"
                    + "tel varchar(8) NOT NULL,"
                    + "email varchar(255) NOT NULL,"
                    + "position varchar(20) NOT NULL,"
                    + "UNIQUE (email),"
                    + "PRIMARY KEY (id)"
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
    
    public boolean addStaffInfo(String loginId,String password,String lastName, String firstName,String sex, String tel, String email, String position, String accountType) {
        Connection cnnct = null;
        Statement stmt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            cnnct.setAutoCommit(false);
            stmt = cnnct.createStatement();
            stmt.addBatch("INSERT INTO StaffInfo VALUES ( null,'" + loginId + "','" + lastName + "','" + firstName + "','" + sex + "','" + tel + "','" + email + "','" + position + "')");
            stmt.addBatch("INSERT INTO AccountInfo VALUES ( '" + loginId + "','" + password + "',DEFAULT,DEFAULT,DEFAULT,'" + accountType + "')");
            int counts[] = stmt.executeBatch();
            cnnct.commit();
            System.out.println("Staff " + counts.length);
            stmt.close();
            cnnct.close();
            isSuccess = true;
        } catch (SQLException ex) {
            if(cnnct != null){
                try{
                    cnnct.rollback();
                }catch(SQLException ex1){
                    ex1.printStackTrace();
                }
            }
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public boolean isValidUser(String longinId, String pwd) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isValid = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM StaffInfo INNER JOIN AccountInfo ON StaffInfo.login_id = AccountInfo.login_id WHERE AccountInfo.login_id = ? AND password = ? AND NOT account_type = 'CUSTOMER';";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, longinId);
            pStmnt.setString(2, pwd);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                isValid = true;
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
        return isValid;
    }

    public StaffInfo getStaffInfo(String longinId, String pwd) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isValid = false;
        StaffInfo staff = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT id,AccountInfo.login_id AS login_id,password,last_name,first_name,sex,tel,email,position,account_type FROM StaffInfo INNER JOIN AccountInfo ON StaffInfo.login_id = AccountInfo.login_id WHERE AccountInfo.login_id = ? AND password = ? AND NOT account_type = 'CUSTOMER';";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, longinId);
            pStmnt.setString(2, pwd);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                staff = new StaffInfo();
                staff.setId(rs.getInt("id"));
                staff.setLoginId(rs.getString("login_id"));
                staff.setPassword(rs.getString("password"));
                staff.setLastName(rs.getString("last_name"));
                staff.setFirstName(rs.getString("first_name"));
                staff.setSex(rs.getString("sex"));
                staff.setTel(rs.getString("tel"));
                staff.setEmail(rs.getString("email"));
                staff.setPosition(rs.getString("position"));
                staff.setAccountType(rs.getString("account_type"));
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
        return staff;
    }
    
    public boolean dropStaffInfoTable(){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "DROP TABLE StaffInfo ";
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
