/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codealong.vendingmachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author umair
 */
public class VendingMachineAuditDaoImpl implements VendingMachineAuditDao{
    
    public static final String File = "audit.txt";
    
    public void writeAudit(String auditEntry) throws PersistanceException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(File, true));
        }
        catch (IOException e){
            throw new PersistanceException("Could not continue writing audit information.", e);
        }
        
        LocalDateTime ts = LocalDateTime.now();
        out.println(ts.toString() + " : " +auditEntry);
        out.flush();
            
        }
    }

