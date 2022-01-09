/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codealong.vendingmachine.service;

import java.math.BigDecimal;
import com.codealong.vendingmachine.dao.PersistanceException;
import java.util.List;
import com.codealong.vendingmachine.currencyanditem.VendableItem;

/**
 *
 * @author umair
 */
public interface ServiceLayer {
    public VendableItem getItem(String name) throws PersistanceException, NoItemInventoryException;
    
    
    public void setCount(String name, int newCount) throws PersistanceException;
    
    
    public List<VendableItem> getAllItems() throws PersistanceException;
    
    public List<VendableItem> getAllItemsInStock() throws PersistanceException;
            
    public List<VendableItem> getAllItemsOutOfStock() throws PersistanceException;
    
    public BigDecimal vendItem(BigDecimal amountInMachine, VendableItem item) throws InsufficientFundsException, PersistanceException;
    
    public VendableItem addItem(VendableItem item) throws PersistanceException;
    
    public VendableItem removeItem(String itemName) throws PersistanceException;
    
}
