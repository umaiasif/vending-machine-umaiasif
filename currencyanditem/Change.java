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
public class Change {
    private final int quarters;
    private final int dimes;
    private final int nickles;
    private final int pennies;
    
    public Change(BigDecimal total, BigDecimal itemPrice){
        BigDecimal change = total.subtract(itemPrice);
        
        if (change.doubleValue() < 0){
            throw new IllegalArgumentException("Item Price > total");
        }
        
        BigDecimal[] div = change.divideAndRemainder(Denomination.QUARTER.getVal());
        quarters = div[0].intValue();
        change = div[1];
        
        div = change.divideAndRemainder(Denomination.DIME.getVal());
        dimes = div[0].intValue();
        change = div[1];
        
        div = change.divideAndRemainder(Denomination.NICKLE.getVal());
        nickles = div[0].intValue();
        change = div[1]; 
        
        div = change.divideAndRemainder(Denomination.PENNY.getVal());
        pennies = div[0].intValue();
        
    }
    
    public Change(BigDecimal change){
        this(change, new BigDecimal("0.00"));
    }
    
    public int getQuarters(){
        return quarters;
    }
    
    public int getDimes(){
        return dimes;
    }
    
    public int getNickles(){
        return nickles;
    }
    
    public int getPennies(){
        return pennies;
    }
    }
    
    
   
    

