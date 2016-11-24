/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.bean;

import java.io.Serializable;

/**
 *
 * @author xeonyan
 */
public class GiftItem  implements Serializable{
    private int giftID;
    private String name;
    private String desc;
    private String imgsrc;
    private double pointRequired;
    public GiftItem(){
        
    }
    public GiftItem(int giftID,String name,String desc,String imgsrc,double pointRequired){
        this.giftID = giftID;
        this.name = name;
        this.desc = desc;
        this.imgsrc = imgsrc;
        this.pointRequired = pointRequired;
    }

    /**
     * @return the giftID
     */
    public int getGiftID() {
        return giftID;
    }

    /**
     * @param giftID the giftID to set
     */
    public void setGiftID(int giftID) {
        this.giftID = giftID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the imgsrc
     */
    public String getImgsrc() {
        return imgsrc;
    }

    /**
     * @param imgsrc the imgsrc to set
     */
    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    /**
     * @return the pointRequired
     */
    public double getPointRequired() {
        return pointRequired;
    }

    /**
     * @param pointRequired the pointRequired to set
     */
    public void setPointRequired(double pointRequired) {
        this.pointRequired = pointRequired;
    }
    
}
