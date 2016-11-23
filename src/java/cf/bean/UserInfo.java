/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.bean;

import java.io.Serializable;

/**
 *
 * @author apple
 */
public class UserInfo implements Serializable{
    int id;
    String loginId;
    String password;
    String lastName;
    String firstName;
    String sex;
    String birthday;
    String tel;
    String address;
    String email;
    String userStatus;
    double money;
    int creditAmount;
    double bonusPoints;
    String accountType;

    public UserInfo() {
    }

    public UserInfo(int id, String loginId, String password, String lastName, String firstName, String sex, String birthday, String tel, String address, String email, String userStatus, double money, int creditAmount, double bonusPoints, String accountType) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.sex = sex;
        this.birthday = birthday;
        this.tel = tel;
        this.address = address;
        this.email = email;
        this.userStatus = userStatus;
        this.money = money;
        this.creditAmount = creditAmount;
        this.bonusPoints = bonusPoints;
        this.accountType = accountType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(int creditAmount) {
        this.creditAmount = creditAmount;
    }

    public double getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(double bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
 
    
}
