/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codealong.vendingmachine.ui;

import com.codealong.vendingmachine.currencyanditem.Change;
import com.codealong.vendingmachine.currencyanditem.VendableItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author umair
 */
public class VendingMachineView {
    private final UserIO io;
    public VendingMachineView(UserIO io){
        this.io = io;
    }
    //Display Banner for the console
    public void showEntryBanner(){
        io.println("============================");
        io.println("\tVending Machine");
        io.println("============================");
    }
    
    //Show all unavailable items & no. of items in stock
    public void showOutOfStock(List<VendableItem>allItemsOutOfStock){
        allItemsOutOfStock.forEach((item) -> {
        io.print("\t");
        printItem(item);
    });   
    }
    public void showInStock(List<VendableItem> allItemsInStock) {
        allItemsInStock.forEach((item) -> {
            
            io.print((allItemsInStock.indexOf(item) + 1) + "\t");
            printItem(item);
        });

        io.println("ENTER  " + (allItemsInStock.size() + 1) + "  TO EXIT");
    }
     
    //Print list of items in vending machine, if 0 items prints out of stock

    private void printItem(VendableItem item) { 
        io.print(item.getName() + "--- $" + item.getPrice() + "--- ");
        if (item.getCount()<=0){
            io.println("Out Of Stock");
        } else {
            io.println(item.getCount() + "");
        }
        }
        
    
    
    //Out of Service message
    public void showOutOfService(){
        io.print("Out of Service!");
            }
    
    //Print list of items in vending machine, if 0 items prints out of stock
   
        
    
    //converts money inserted into big decimal otherwise gives an exception
     public BigDecimal insertMoney(){
         BigDecimal result = null;
         do{
             System.out.println("insert money: $");
           
             
             String input = io.readString("$");
             try{
                 result = new BigDecimal(input);
             }
             catch (NumberFormatException e){
                 io.println("Invalid amount of money inserted");
             }
         }while (result == null);
         result = result.setScale(2, RoundingMode.DOWN);
         return result;
     }
     //Show Balance for the user
     public void showBalance(BigDecimal balance){
         io.println("Your Balance is : $" + balance);
     }
     
     //Choosing an item from the machine
     public int insertChoice(int min, int max){
         int result = -1;
         do{
             System.out.println("Insert Choice:");
             result = io.readInt("", min,max);
             if(result == -1){
                 io.print("Invalid Choice");
             }
         }while (result==1);
         return result;
     }
     
     public void showVend(VendableItem item){
         io.println("Dispensing the selected item: " + item.getName());
     }
     
     public void showChange(Change change){
         io.print("You have : $");
         if (change.getQuarters() > 0){
             io.println(change.getQuarters() + "Quarters");
         }
         
         if (change.getDimes() > 0){
         io.println(change.getDimes() + "Dimes");
     }
         if (change.getNickles() > 0){
             io.println(change.getNickles() + "Nickles");
         }
         
         if (change.getPennies() > 0) {
             io.println(change.getPennies() + "Pennies");
         }
         io.println("in change");
     }
     
     //Print low balance and amount needed to purchase the selection
     public void showInsufficientFundMsg(BigDecimal amountInMachine, VendableItem item){
         BigDecimal amountToAdd = item.getPrice().subtract(amountInMachine);
         io.println("Not enough funds to purchase item: "
         + item.getName() + "You only have $" + amountInMachine
         + "Please insert $" + amountToAdd + " to dispense the item: " +item.getName());
     }
     
     public void showExitMessage(){
         io.println("Thank you for your purchase! Goodbye!");
     }
     
     public void showError(String message){
         io.println(message);
     }
     
     public void showKeepVending(){
         io.println("You have continued to purchase and added change back to the machine");
     }
     
     public boolean askToKeepVending(){
         System.out.println("Do you want to make another purchase? (y/n)");
         String userInput = io.readString("");
         return "y".equalsIgnoreCase(userInput.charAt(userInput.length() - 1)+ "");
     }
}
     
             
         
                         
            
