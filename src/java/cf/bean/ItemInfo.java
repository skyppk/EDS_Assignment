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
public class ItemInfo implements Serializable{
    int id;
    String itemId;
    String itemName;
    String category;
    String designerName;
    double price;
    String descriptions;
    String img;
    String itemStatus;

    public ItemInfo() {
    }

    public ItemInfo(int id, String itemId, String itemName, String category, String designerName, double price, String descriptions, String img, String itemStatus) {
        this.id = id;
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.designerName = designerName;
        this.price = price;
        this.descriptions = descriptions;
        this.img = img;
        this.itemStatus = itemStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    
    
}
