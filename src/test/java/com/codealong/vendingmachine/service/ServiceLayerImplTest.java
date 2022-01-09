/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codealong.vendingmachine.service;

import com.codealong.vendingmachine.currencyanditem.VendableItem;
import com.codealong.vendingmachine.dao.PersistanceException;
import com.codealong.vendingmachine.dao.VendingMachineDaoFileImpl;
import java.math.BigDecimal;
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
public class ServiceLayerImplTest {
    
    private final ServiceLayerImpl service = new ServiceLayerImpl(new VendingMachineDaoFileImpl());
    
    VendableItem A = new VendableItem("A", new BigDecimal("2.25"), 1);
    VendableItem B = new VendableItem("B", new BigDecimal("2.50"), 1);
    VendableItem C = new VendableItem("C", new BigDecimal("4.00"), 1);
    VendableItem D = new VendableItem("D", new BigDecimal("6.00"), 1);
  
    @BeforeEach
    public void setUp() {
        try {
            // Whenever there's something added for the first time addItem should return null
            service.addItem(A);
            service.addItem(B);
            service.addItem(C);
            service.addItem(D);
        } catch (PersistanceException e) {
           
        }
    }
    
    
    @AfterEach
    public void tearDown() {
        try {
            for (VendableItem item : service.getAllItems()) {
                service.removeItem(item.getName());
            }
        } catch (PersistanceException e) {
            
        }
    }
    

    @Test
    public void testGetItem() {
        try {
           
            assertEquals(service.getItem("A"), A);
            assertEquals(service.getItem("B"), B);
            assertEquals(service.getItem("C"), C);
            assertEquals(service.getItem("D"), D);
        } catch (PersistanceException | NoItemInventoryException e) {
            fail();
        }}           
            

    @Test
    public void testGetItemNoExist() {
        try {
            // Setup contains the names
            assertNull(service.getItem("NotAName"));
        } catch (PersistanceException | NoItemInventoryException e) {
            fail();
        }
    }

    @Test
    public void testGettingCountZero() {
        VendableItem item = new VendableItem("X", new BigDecimal("1.00"), 0);
        try {
            service.addItem(item); 
            service.getItem("X"); 
            fail();
        } catch (PersistanceException e) {
            fail();
        } catch (NoItemInventoryException i) {
           
        }
    }

    
    @Test
    public void testSetCount() throws Exception {
        try {
            service.setCount("A", 10);
            assertEquals(service.getItem("A").getCount(), 10);
        } catch (PersistanceException e) {
            fail();
        }
    }

    @Test
    public void testSetCountLessThanZero() {
        try {
            service.setCount("A", -1);
            fail();
        } catch (PersistanceException e) {
            // negative values should be caught as errors
        }
    }

    
    @Test
    public void testGetAllItemsInStock() throws Exception {
        try {
            for (VendableItem item : service.getAllItems()) {
                item.setCount(1);
            }
            // setup method contains sizes 
            assertEquals(service.getAllItemsInStock().size(), service.getAllItems().size());
            for (VendableItem item : service.getAllItems()) {
                item.setCount(0);
            }
            assertEquals(service.getAllItemsInStock().size(), 0);
        } catch (PersistanceException e) {
            fail();
        }
    }

    
    @Test
    public void testGetAllItemsOutOfStock() throws Exception {
        try {
            for (VendableItem item : service.getAllItems()) {
                item.setCount(1);
            }
            // setup method contains sizes 
            assertEquals(service.getAllItemsOutOfStock().size(), 0);
            for (VendableItem item : service.getAllItems()) {
                item.setCount(0);
            }
            assertEquals(service.getAllItemsOutOfStock().size(), service.getAllItems().size());
        } catch (PersistanceException e) {
            fail();
        }
    }

    @Test
    public void testVendItem() {
        // A = 2.25
        // B = 2.50
        // C = 4.00
        // D = 6.00
        try {
            BigDecimal amount = new BigDecimal("7.75");

            BigDecimal change = service.vendItem(amount, service.getItem("A"));
            assertEquals(change, new BigDecimal("9.50"));
            amount = change;

            change = service.vendItem(amount, service.getItem("B"));
            assertEquals(change, new BigDecimal("5.00"));
            amount = change;

            change = service.vendItem(amount, service.getItem("C"));
            assertEquals(change, new BigDecimal("8.00"));
            amount = change;

            change = service.vendItem(amount, service.getItem("D"));
            assertEquals(change, new BigDecimal("0.00"));

        } catch (InsufficientFundsException | PersistanceException | NoItemInventoryException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void insufficentFundsToVend() {
        try {
            BigDecimal amount = new BigDecimal("1.00");
            VendableItem item = new VendableItem("Test", new BigDecimal("1.25"), 1);
            service.addItem(item); 
            service.vendItem(amount, item);
            fail();
        } catch (InsufficientFundsException e) {
            
        } catch (PersistanceException ex) {
            fail();
        }
    }
}

    

