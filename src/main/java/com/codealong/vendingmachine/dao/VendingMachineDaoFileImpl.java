/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codealong.vendingmachine.dao;

import com.codealong.vendingmachine.currencyanditem.VendableItem;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 *
 * @author umair
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao{
    

    @Override
    public VendableItem getItem(String name) throws PersistanceException { 
        readFile();
        return inventory.get(name);
    }
        

    @Override
    public void setCount(String name, int newCount) throws PersistanceException { 
        if(newCount < 0){
            throw new PersistanceException("newCount must be >= 0");
        }
        getItem(name).setCount(newCount);
        writeFile();
    }

    @Override
    public List<VendableItem> getAllItems() throws PersistanceException { 
        readFile();
        return new ArrayList<>(inventory.values());
    }
//Admin Block
    @Override
    public VendableItem addItem(VendableItem item) throws PersistanceException {  readFile();
        VendableItem previousMapping = inventory.put(item.getName(), item);
        writeFile();
        return previousMapping;
    }

    @Override
    public VendableItem removeItem(String itemName) throws PersistanceException {
        readFile();
        VendableItem removed = inventory.remove(itemName);
        writeFile();
        return removed;
    }
 private Map<String, VendableItem> inventory;
    
    private static final String FILE_NAME = "itemData.txt";
    private static final String DELIMITER = "::";
    
    private void writeFile() throws PersistanceException{
        List<VendableItem> listOfItems = getAllItems();
        
        try (PrintWriter writer = new PrintWriter(FILE_NAME)){
            
            StringBuilder lineBuilder = new StringBuilder();
            
            for (VendableItem item : listOfItems){
                lineBuilder.append(item.getName());
                lineBuilder.append(DELIMITER);
                lineBuilder.append(item.getPrice());
                lineBuilder.append(DELIMITER);
                lineBuilder.append(item.getCount());
                lineBuilder.append("\n");
            }
         writer.println(lineBuilder.toString());
            writer.flush();
            
            lineBuilder.delete(0, lineBuilder.length());
        }catch (FileNotFoundException ex){
            throw new PersistanceException("Cannot Find Items");
        }
    }
    
    private void readFile() throws PersistanceException{
        
        if(inventory != null){
            return;
        } else {
            inventory = new TreeMap<>();
        }
        
        File file = new File (FILE_NAME);
        if(file.exists() == false){
            
            try{
                file.createNewFile();
                createDefaultItemSet();
            }catch (IOException io){
                throw new PersistanceException("Cannot find machine");
            }
        }
        
        try (Scanner sc = new Scanner(file)){
            while(sc.hasNext()){
                
                String [] lineParts = sc.nextLine().split(DELIMITER);
                
                String name = lineParts[0];
                BigDecimal price = new BigDecimal (lineParts[1]);
                int quantity = Integer.parseInt(lineParts[2]);
                
                VendableItem item = new VendableItem(name, price, quantity);
                
                inventory.put(name, item);
            }
        } catch (IOException io){
            throw new PersistanceException("Cannot find items");
        } catch (NumberFormatException nfe){
            throw new PersistanceException("Error getting item");
        }
        
        if (getAllItems().isEmpty()){
            createDefaultItemSet();
        }}
        
        private void createDefaultItemSet() throws PersistanceException{
            VendableItem kitkat = new VendableItem("KITKAT",new BigDecimal("1.25"),10);
            VendableItem coke = new VendableItem("Coke",new BigDecimal("1.75"),3);
            VendableItem ruffles = new VendableItem("Ruffles",new BigDecimal("0.25"),10);
            VendableItem chocolatechip = new VendableItem("Chocolate Chip",new BigDecimal("1.00"),1);
            VendableItem lays = new VendableItem("Lays",new BigDecimal("0.75"),25);
            VendableItem phone = new VendableItem("Expensive Phone",new BigDecimal("2000.00"),2);
            inventory.put(kitkat.getName(), kitkat);
            inventory.put(coke.getName(), coke);
            inventory.put(ruffles.getName(), ruffles);
            inventory.put(chocolatechip.getName(), chocolatechip);
            inventory.put(lays.getName(), lays);
            inventory.put(phone.getName(), phone);
            writeFile();
        }}
    