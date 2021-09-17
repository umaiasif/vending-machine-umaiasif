/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codealong.vendingmachine.service;

import com.codealong.vendingmachine.currencyanditem.VendableItem;
import com.codealong.vendingmachine.dao.PersistanceException;
import com.codealong.vendingmachine.dao.VendingMachineDao;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author umair
 */
public class ServiceLayerImpl implements ServiceLayer{

    
    private final VendingMachineDao dao;
    
    public ServiceLayerImpl(VendingMachineDao dao){
        this.dao = dao;
    }
    
    @Override
    public VendableItem getItem(String name) throws PersistanceException, NoItemInventoryException {
        VendableItem item = dao.getItem(name); //throws an exception if theres no item in inventory
    if (item != null && item.getCount() == 0) {
            throw new NoItemInventoryException();
        }
        return item;
    }
    @Override
    public void setCount(String name, int newCount) throws PersistanceException {
        if (newCount < 0){
            throw new PersistanceException ("newCount must be >= 0");
                    }
        dao.setCount(name, newCount);
    }
    
    @Override
    public List<VendableItem> getAllItems() throws PersistanceException {
        return dao.getAllItems();
        
    }
    
    @Override
    public List<VendableItem> getAllItemsInStock() throws PersistanceException {
        return dao.getAllItems()
                .stream()
                .filter((item) -> item.getCount() > 0)
                .collect(Collectors.toList());
    }
    
    
    @Override
    public List<VendableItem> getAllItemsOutOfStock() throws PersistanceException {
        return dao.getAllItems()
                .stream()
                .filter((item) -> item.getCount() <= 0)
                .collect(Collectors.toList());
    }

      @Override
    public BigDecimal vendItem(BigDecimal amountInMachine, VendableItem item) throws InsufficientFundsException, PersistanceException {
        if (item.getPrice().compareTo(amountInMachine) > 0) {
            throw new InsufficientFundsException("Insufficient Funds");
        }
        setCount(item.getName(),item.getCount()-1);
        return amountInMachine.subtract(item.getPrice());
    }
    //Admin Block

    @Override
    public VendableItem addItem(VendableItem item) throws PersistanceException {
        return dao.addItem(item);
    }
    

    @Override
    public VendableItem removeItem(String itemName) throws PersistanceException {
        return dao.removeItem(itemName);
    }
    }
    

