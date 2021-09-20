/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codealong.vendingmachine.dao;

/**
 *
 * @author umair
 */
public interface VendingMachineAuditDao {
    public void writeAudit(String auditEntry) throws PersistanceException;
    
}
