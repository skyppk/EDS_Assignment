/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.bean;

import java.util.ArrayList;

/**
 *
 * @author apple
 */
public class ShoppingCart {
    ArrayList<OrderDetails> cart;

    public ShoppingCart() {
        cart = new ArrayList();
    }

    public ShoppingCart(ArrayList<OrderDetails> cart) {
        this.cart = cart;
    }

    public ArrayList<OrderDetails> getCart() {
        return cart;
    }

    public void setCart(ArrayList<OrderDetails> cart) {
        this.cart = cart;
    }
    
    public void addCart(OrderDetails orderDetails){
        boolean check = true;
        for(OrderDetails oDs : cart){
            if(oDs.getItemId().equals(orderDetails.getItemId())){
                oDs.setQuantity(oDs.getQuantity() + orderDetails.getQuantity());
                oDs.setDetailsPrice(oDs.getDetailsPrice()+ orderDetails.getDetailsPrice());
                check = false;
                break;
            }
        }
        if(check){
            cart.add(orderDetails);
        }
    }
    
    public boolean setDetails(String itemId, int quantity){
        boolean check = false;
        for(OrderDetails oDs : cart){
            System.out.println("check od" + itemId + "check :" );
            System.out.println("check id" + oDs.getItemId() + "check :" );
            if(oDs.getItemId().equals(itemId)){
                oDs.setQuantity(quantity);
                oDs.setDetailsPrice(oDs.getBuyPrice() * quantity);
                System.out.println("check new" + oDs.getItemId() + "check :" );
                check = true;
                break;
            }
        }
        return check;
    }
    
    public boolean removeDetails(String itemId){
        boolean check = false;
        for(OrderDetails oDs : cart){
            System.out.println("remove od" + oDs.getItemId() + "check :" );
            if(oDs.getItemId().equals(itemId)){
                cart.remove(oDs);
                System.out.println("reomve new" + oDs.getItemId() + "check :" );
                check = true;
                break;
            }
        }
        return check;
    }
}
