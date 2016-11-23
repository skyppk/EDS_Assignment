/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.db;

import cf.bean.UserInfo;
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
public class UserDB {

    String dburl;
    String dbUser;
    String dbPassword;

    public UserDB() {

    }

    public UserDB(String dburl, String dbUser, String dbPassword) {
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

    public void CreateUserInfoTable() {
        Connection cnnct = null;
        Statement stmnt = null;

        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "CREATE TABLE IF NOT EXISTS UserInfo ("
                    + "id int NOT NULL AUTO_INCREMENT,"
                    + "login_id varchar(25),"
                    + "last_name varchar(25) NOT NULL,"
                    + "first_name varchar(25) NOT NULL,"
                    + "sex varchar(1) NOT NULL,"
                    + "birthday date,"
                    + "tel varchar(8) NOT NULL,"
                    + "address varchar(255) NOT NULL,"
                    + "email varchar(255) NOT NULL,"
                    + "user_status varchar(15) DEFAULT 'NEW',"
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
    
    public void CreateAccountInfoTable() {
        Connection cnnct = null;
        Statement stmnt = null;

        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "CREATE TABLE IF NOT EXISTS AccountInfo ("
                    + "login_id varchar(25) NOT NULL,"
                    + "password varchar(60) NOT NULL,"
                    + "money double DEFAULT '0',"
                    + "credit_amount int DEFAULT '0',"
                    + "bonus_point double DEFAULT '1000',"
                    + "account_type varchar(20) DEFAULT 'CUSTOMER',"
                    + "PRIMARY KEY (login_id)"
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

    public boolean addUserInfo(String lastName,String firstName, String sex, String birthday, String tel, String address, String email) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO UserInfo VALUES (null,null,?,?,?,?,?,?,?,DEFAULT)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, lastName);
            pStmnt.setString(2, firstName);
            pStmnt.setString(3, sex);
            pStmnt.setString(4, birthday);
            pStmnt.setString(5, tel);
            pStmnt.setString(6, address);
            pStmnt.setString(7, email);
//            pStmnt.setString(8, "New");
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

    public boolean addUserAccountInfo(int id, String loginId,String password) {
        Connection cnnct = null;
        Statement stmt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            cnnct.setAutoCommit(false);
            stmt = cnnct.createStatement();
            stmt.addBatch("UPDATE UserInfo SET login_id = '" + loginId + "', user_status = 'ACCEPTED' WHERE id = '" + id + "'");
            stmt.addBatch("INSERT INTO AccountInfo VALUES ( '" + loginId + "','" + password + "',DEFAULT,DEFAULT,DEFAULT,DEFAULT)");
            int counts[] = stmt.executeBatch();
            cnnct.commit();
            System.out.println("Committed " + counts.length);
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
    
//    int id,String lastName,String firstName, String sex, String birthday, 
    public boolean editUserInfo(String loginId,String password, String tel, String address, String email) {
        Connection cnnct = null;
        Statement stmt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            cnnct.setAutoCommit(false);
            stmt = cnnct.createStatement();
            stmt.addBatch("UPDATE UserInfo SET tel = '" + tel + "', address = '" + address + "', email = '" + email + "' WHERE login_id = '" + loginId + "'");
            stmt.addBatch("UPDATE AccountInfo SET password = '" + password + "' WHERE login_id = '" + loginId + "'");
            int counts[] = stmt.executeBatch();
            cnnct.commit();
            System.out.println("Committed " + counts.length);
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
    
    public boolean editUserInfo(UserInfo userInfo) {
        Connection cnnct = null;
        Statement stmt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            cnnct.setAutoCommit(false);
            stmt = cnnct.createStatement();
            stmt.addBatch("UPDATE UserInfo SET tel = '" + userInfo.getTel() + "', address = '" + userInfo.getAddress() + "', email = '" + userInfo.getEmail() + "' WHERE login_id = '" + userInfo.getLoginId() + "'");
            stmt.addBatch("UPDATE AccountInfo SET password = '" + userInfo.getPassword() + "' WHERE login_id = '" + userInfo.getLoginId() + "'");
            int counts[] = stmt.executeBatch();
            cnnct.commit();
            System.out.println("Committed " + counts.length);
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
    
    public boolean editAccountInfo(String loginId,String userStatus,double money, int creditAmount) {
        Connection cnnct = null;
        Statement stmt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            cnnct.setAutoCommit(false);
            stmt = cnnct.createStatement();
            stmt.addBatch("UPDATE UserInfo SET user_status = '" + userStatus + "' WHERE login_id = '" + loginId + "'");
            stmt.addBatch("UPDATE AccountInfo SET money = '" + money + "', credit_amount = '" + creditAmount + "' WHERE login_id = '" + loginId + "'");
            int counts[] = stmt.executeBatch();
            cnnct.commit();
            System.out.println("Committed " + counts.length);
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
    
//    public boolean editRecord(CustomerBean cb){
//        Connection cnnct = null;
//        PreparedStatement pStmnt = null;
//        boolean isSuccess = false;
//        try{
//            cnnct = getConnection();
//            String preQueryStatement = "UPDATE CUSTOMER SET name = ? , tel = ? , age = ? WHERE custId = ? ";
//            pStmnt = cnnct.prepareStatement(preQueryStatement);
//            pStmnt.setString(1, cb.getName());
//            pStmnt.setString(2, cb.getTel());
//            pStmnt.setInt(3, cb.getAge());
//            pStmnt.setString(4, cb.getCustid());
//            int rowCount = pStmnt.executeUpdate();
//            if(rowCount >= 1){
//                isSuccess = true;
//            }
//            pStmnt.close();
//            cnnct.close();
//        } catch (SQLException ex){
//            while(ex != null){
//                ex.printStackTrace();
//                ex = ex.getNextException();
//            }
//        } catch (IOException ex){
//            ex.printStackTrace();
//        }
//        return isSuccess;
//    }
    
    public boolean isValidUser(String longinId, String pwd) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isValid = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM UserInfo INNER JOIN AccountInfo ON UserInfo.login_id = AccountInfo.login_id WHERE AccountInfo.login_id = ? AND password = ? AND user_status = 'ACCEPTED' AND account_type = 'CUSTOMER';";
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

    public UserInfo getUserInfo(String longinId, String pwd) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isValid = false;
        UserInfo user = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT id, UserInfo.login_id AS login_id, password, last_name, first_name, sex, birthday, tel, address, email, user_status, money, credit_amount, bonus_point FROM UserInfo INNER JOIN AccountInfo ON UserInfo.login_id = AccountInfo.login_id WHERE AccountInfo.login_id = ? AND password = ? AND user_status = 'ACCEPTED' AND account_type = 'CUSTOMER';";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, longinId);
            pStmnt.setString(2, pwd);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                user = new UserInfo();
                user.setId(rs.getInt("id"));
                user.setLoginId(rs.getString("login_id"));
                user.setPassword(rs.getString("password"));
                user.setLastName(rs.getString("last_name"));
                user.setFirstName(rs.getString("first_name"));
                user.setSex(rs.getString("sex"));
                user.setBirthday(rs.getString("birthday"));
                user.setTel(rs.getString("tel"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setUserStatus(rs.getString("user_status"));
                user.setMoney(rs.getDouble("money"));
                user.setCreditAmount(rs.getInt("credit_amount"));
                user.setBonusPoints(rs.getDouble("bonus_points"));
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
        return user;
    }

    public boolean checkEmail(String email) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isValid = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM UserInfo WHERE email = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, email);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (!rs.next()) {
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
    
    public boolean dropUserInfoTable(){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "DROP TABLE UserInfo ";
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
    
    public boolean dropAccountInfoTable(){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "DROP TABLE AccountInfo ";
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
