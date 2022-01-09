/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codealong.vendingmachine.dao;

import com.codealong.vendingmachine.currencyanditem.VendableItem;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author umair
 */
public class VendingMachineDaoFileImplTest {
    private final VendingMachineDaoFileImpl dao = new VendingMachineDaoFileImpl();
    
    VendableItem A = new VendableItem("A", new BigDecimal("0.25"), 0);
    VendableItem B = new VendableItem("B", new BigDecimal("0.50"), 0);
    VendableItem C = new VendableItem("C", new BigDecimal("1.00"), 0);
    VendableItem D = new VendableItem("D", new BigDecimal("2.00"), 0);
    public VendingMachineDaoFileImplTest() {
    }

   
      
    @BeforeEach
    public void setUp() {
        try {
            //First addition will return null
            dao.addItem(A);
            dao.addItem(B);
            dao.addItem(C);
            dao.addItem(D);
        } catch (PersistanceException e){
        }
    }
    
    @AfterEach
    public void tearDown() {
        try {
            for (VendableItem item : dao.getAllItems()){
                dao.removeItem(item.getName());
            }   
            } catch (PersistanceException e){
        }
    }

    @org.junit.jupiter.api.Test
    public void testGetItem() {
        try{
            assertEquals(dao.getItem("A"), A);
            assertEquals(dao.getItem("B"), B);
            assertEquals(dao.getItem("C"), C);
            assertEquals(dao.getItem("D"), D);
            assertNull(dao.getItem("NullItem"));
          
        } catch (PersistanceException e){
            fail();
        }
    }
    @org.junit.jupiter.api.Test
    public void testCount(){
        try {
            dao.setCount("A", 10);
            assertEquals(dao.getItem("A").getCount(), 10);
        } catch (PersistanceException e){
            fail();
        }}
    
    @org.junit.jupiter.api.Test
    public void testSetCountLessThanZero() {
        try {
            dao.setCount("A", -1);
            fail();
        } catch (PersistanceException e) {
            // should catch negative values as a error
        }
    }

    @org.junit.jupiter.api.Test
     public void testGetAllItems() {
        try {
            List<VendableItem> items = dao.getAllItems();
            // size is specified in setup method
            assertEquals(items.size(), 4+2);
        } catch (PersistanceException e) {
            fail();
        }
    }
}



            
        