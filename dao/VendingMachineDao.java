/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codealong.vendingmachine.dao;

import com.codealong.vendingmachine.currencyanditem.VendableItem;
import java.util.List;

/**
 *
 * @author umair
 */
public interface VendingMachineDao {
    
    public VendableItem getItem (String name) throws PersistanceException;
    
    
    public void setCount(String name, int newCount) throws PersistanceException;
    
    
    public List<VendableItem> getAllItems() throws PersistanceException;
    
    
    public VendableItem addItem(VendableItem item) throws PersistanceException;
    
    
    public VendableItem removeItem(String name) throws PersistanceException;
}
