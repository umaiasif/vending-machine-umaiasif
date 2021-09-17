/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codealong.vendingmachine.controller;

import com.codealong.vendingmachine.currencyanditem.Change;
import com.codealong.vendingmachine.currencyanditem.VendableItem;
import com.codealong.vendingmachine.dao.PersistanceException;
import java.security.Provider.Service;
import javax.swing.text.View;
import com.codealong.vendingmachine.service.InsufficientFundsException;
import com.codealong.vendingmachine.service.ServiceLayer;
import com.codealong.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;
        
/**
 *
 * @author umair
 */
public class VendingMachineController {
    private final ServiceLayer service;
    private final VendingMachineView view; 
    private final BalanceInMachine balance;
    
    public VendingMachineController(ServiceLayer service, VendingMachineView view){
        this.service = service;
        this.view = view;
        this.balance = new BalanceInMachine();
    }
    
    public void run(){
        view.showEntryBanner();
        try{
            //program starts and asks to insert money so that it can show choices
            showVendorItems();
            addToMachine(insertMoney());
            view.showBalance(getBalance());
            do{ 
                int choice = getChoice();
                int exitChoice = service.getAllItemsInStock().size()+1;
                if (choice == exitChoice){
                    break;
                    //if user chooses to exit
                }
                
                VendableItem item = choiceToItem(choice);
                try{
                    BigDecimal change = vendItem(item);
                    if(keepVending()){
                        showItemsInStock();
                        view.showKeepVending();
                        addToMachine(change);
                        view.showBalance(getBalance());
                    } else{
                        break;
                    } // exits the sequence if user chooses not to vend further
                } catch (InsufficientFundsException ife){
                    view.showInsufficientFundMsg(getBalance(), item);
                    // insert more money
                    addToMachine(insertMoney());
                    view.showBalance(getBalance());
                    // To display the vend items again
                    showItemsInStock();
                }
            }while (true);
        }catch (PersistanceException e){
            view.showError(e.getMessage());
            System.exit(0);
        }
        
        view.showExitMessage();
                    
                    }
    private void showVendorItems() throws PersistanceException{
        List<VendableItem> itemsInStock = service.getAllItemsInStock();
        if(itemsInStock.isEmpty()){
            view.showOutOfService();
            System.exit(0);
        }else{
            view.showInStock(itemsInStock);
        }
    }
    private void showItemsInStock() throws PersistanceException {
        List<VendableItem> itemsInStock = service.getAllItemsInStock();
        if (itemsInStock.isEmpty()) {
            view.showOutOfService();        
            System.exit(0);
        } else {
            view.showInStock(itemsInStock);
        }
    }
    
    private BigDecimal insertMoney(){
        return view.insertMoney();
    }
       
      
    private int getChoice() throws PersistanceException{
        return view.insertChoice(1, service.getAllItemsInStock().size()+1);
    }
    
    private BigDecimal vendItem(VendableItem item) throws PersistanceException, InsufficientFundsException{
        BigDecimal change = service.vendItem(getBalance(), item);
        view.showVend(item);
        
        if (change.equals(BigDecimal.ZERO) == false){
            view.showChange(new Change(change));
        }
        
        removeFromMachine(getBalance());
        return change;
    }
    
    private VendableItem choiceToItem(int currentChoice) throws PersistanceException{
        return service.getAllItemsInStock().get(currentChoice-1);
    }
    
    private boolean keepVending(){
    return view.askToKeepVending();
    }
    
    private BigDecimal getBalance(){
        return this.balance.getBalance();
    }
    
    private void addToMachine(BigDecimal amount){
        this.balance.add(amount);
    }
    
    private void removeFromMachine(BigDecimal amount){
        this.balance.remove(amount);
    }
}
        
                
                
            
        
