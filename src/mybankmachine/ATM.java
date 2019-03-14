/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybankmachine;

import javax.swing.JOptionPane;

/**
 *
 * @author Kyle's PC
 */
public class ATM {
    
    private final String bankName;
    private double balance;
    
    public ATM(){
        bankName = JOptionPane.showInputDialog("What bank are you with?");
        balance = Double.parseDouble(JOptionPane.showInputDialog("What is your current balance?"));
    }
    
    public String toString(){
        String bank = bankName;
        
        return bank;
    }
    
    public double toDouble(){
        double currentBalance = balance;
        
        return currentBalance;
    }
    
}
