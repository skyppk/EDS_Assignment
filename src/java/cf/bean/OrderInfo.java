/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author apple
 */
public class OrderInfo implements Serializable{
    int id;
    String orderId;
    String loginId;
    String deliveryType;
    String deliveryDate;
    String deliveryTime;
    String deliveryAddress;
    double orderPrice;
    String orderDate;
    String orderStatus;
    ArrayList<OrderDetails> orderDetails;

    public OrderInfo() {
    }

    public OrderInfo(int id, String orderId, String loginId, String deliveryType, String deliveryDate, String deliveryTime, String deliveryAddress, double orderPrice, String orderDate, String orderStatus, ArrayList<OrderDetails> orderDetails) {
        this.id = id;
        this.orderId = orderId;
        this.loginId = loginId;
        this.deliveryType = deliveryType;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
        this.deliveryAddress = deliveryAddress;
        this.orderPrice = orderPrice;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderDetails = orderDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ArrayList<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
   
    public void addOrderDetails(OrderDetails orderDetails){
        this.orderDetails.add(orderDetails);
    }
    
    public void removeOrderDetails(OrderDetails orderDetails){
        this.orderDetails.remove(orderDetails);
    }
}
