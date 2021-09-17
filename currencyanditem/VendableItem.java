/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codealong.vendingmachine.currencyanditem;

import java.math.BigDecimal;

/**
 *
 * @author umair
 */
public class VendableItem {
    private final String name;
    private final BigDecimal price;
    private int count;
    
    //This will create an item with name, default count of 1 and price
    public VendableItem(String name, BigDecimal price){
        this.name = name;
        this.price = price;
        this.count = 1;
    }
    //This will create an item with name and price and count
    public VendableItem(String name, BigDecimal price, int count){
        this.name = name;
        this.price = price;
        this.count = count;
        
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count){
        this.count = count;
    }
    
    public String getName(){
        return name;
        
    }
    
    public BigDecimal getPrice(){
        return price;
    }
}
