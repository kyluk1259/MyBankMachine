/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybankmachine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Kyle's PC
 */
public class MyBankMachine {

    public static double balance;
    public static String dispBalance, bankName;
    public static ATM account;
    public static Font atm;
    public static javax.swing.border.Border blackline = BorderFactory.createLineBorder(Color.BLACK);
    public static DecimalFormat two = new DecimalFormat(".##");
    public static Color background, fontColor;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        init();
        atmMain();
    }

    public static void atmMain() {

        dispBalance = two.format(balance);
        atm = new Font("Arial", Font.BOLD, 30);   

        JFrame main = new JFrame("ATM");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = (JPanel) main.getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(background);

        JLabel bank = new JLabel(bankName);
        contentPane.add(bank);
        bank.setFont(atm);
        bank.setForeground(fontColor);
        Dimension title = bank.getPreferredSize();
        bank.setBounds(20, 40, title.width, title.height);

        JLabel money = new JLabel("$" + dispBalance);
        contentPane.add(money);
        money.setFont(atm);
        money.setForeground(Color.BLACK);
        money.setBackground(Color.WHITE);
        Dimension size = money.getPreferredSize();
        money.setBounds(275 - size.width/6, 100, size.width, size.height);
        System.out.print(size.width);
        
        JPanel rectangle = new JPanel();
        rectangle.setBackground(Color.WHITE);
        contentPane.add(rectangle);
        rectangle.setBorder(blackline);
        rectangle.setBounds(275 - money.getWidth(), 100, size.width+(money.getWidth()), size.height);
        
        JButton deposit = new JButton("Deposit");
        contentPane.add(deposit);
        deposit.setBorder(blackline);
        deposit.setBounds(75, 200, 200, 75);
        
        JButton withdraw = new JButton("Withdraw");
        contentPane.add(withdraw);
        withdraw.setBorder(blackline);
        withdraw.setBounds(75, 350, 200, 75);
        
        JButton history = new JButton("Balance History");
        contentPane.add(history);
        history.setBorder(blackline);
        history.setBounds(325, 200, 200, 75);

        main.setSize(600, 600);
        main.setVisible(true);
    }

    public static void init(){
        account = new ATM();
        
        balance = account.toDouble();
        bankName = account.toString().toUpperCase();
        
        background = Color.LIGHT_GRAY;
        fontColor = Color.BLACK;
        
        if(bankName.contains("TD")){
            background = Color.GREEN;
            bankName = "TD Canada Trust ATM";
        }else if(bankName.contains("BMO") || bankName.contains("MONTREAL")){
            background = Color.CYAN;
            bankName = "Bank of Montreal ATM";
        }else if(bankName.contains("RBC") || bankName.contains("ROYAL")){
            background = Color.BLUE;
            fontColor = Color.WHITE;
            bankName = "Royal Bank of Canada ATM";
        }else if(bankName.contains("SCOTIABANK") || bankName.contains("NOVA")){
            background = Color.RED;
            bankName = "Bank of Nova Scotia ATM";
        }else{
            background = Color.LIGHT_GRAY;
            bankName += " ATM";
        }
    }
    
}
