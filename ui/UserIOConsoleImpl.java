/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codealong.vendingmachine.ui;

import java.util.Scanner;

/**
 *
 * @author umair
 */
public class UserIOConsoleImpl implements UserIO{
    private Scanner sc;
    
    public UserIOConsoleImpl(){
        sc = new Scanner(System.in);
    }

    @Override
    public void print(String message) {
        System.out.print(message);

    }

    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float readFloat(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public int readInt(String prompt, int min, int max) {
        print(prompt);
        try {
            int val = Integer.parseInt(sc.nextLine());
            if (val >= min && val <= max) {
                return val;
            }
        } catch (NumberFormatException e) {
            // do nothing
        }
        return -1;
    }

    @Override
    public long readLong(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        return sc.nextLine();
    }
    @Override
    public int readInt(String prompt) {
       print(prompt);
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
    }}}
    
    

