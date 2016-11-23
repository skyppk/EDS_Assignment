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
    
    
}
