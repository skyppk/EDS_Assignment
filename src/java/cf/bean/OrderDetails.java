/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.bean;

/**
 *
 * @author apple
 */
public class OrderDetails {
    String itemId;
    String itemName;
    int quantity;
    double buyPrice;
    double detailsPrice;

    public OrderDetails() {
    }

    public OrderDetails(String itemId, String itemName, int quantity, double buyPrice, double detailsPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
        this.detailsPrice = detailsPrice;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getDetailsPrice() {
        return detailsPrice;
    }

    public void setDetailsPrice(double detailsPrice) {
        this.detailsPrice = detailsPrice;
    }
    
    
}
