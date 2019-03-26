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

    private static String bankName, input;
    private static double balance;
    private static int window = 1;

    public ATM() {
        setup();
    }

    //Get information for ATM constructor
    public static void setup() {
        switch (window) {
            case 1:
                bankName = JOptionPane.showInputDialog("What bank are you with?");
                input = bankName;
                check();
                break;

            case 2:
                input = JOptionPane.showInputDialog("What is your current balance?");
                if (input.charAt(0) == 36) {
                    input = input.substring(1);
                }
                System.out.print(input);
                check();
                balance = Double.parseDouble(input);
                break;
        }
    }

    public static void check() {    //method to check proper inputs

        if (input == null) {    //terminate program on exit
            System.exit(0);
        } else if (isNullOrWhitespace(input)) {     //force input
            setup();
        } else if (window == 2 && !isNotNumerical(input)) {
            setup();
        } else {    //next input
            window++;
            setup();
        }
    }

    public static boolean isNullOrWhitespace(String input) { //check for proper inputs, return true of false
        if (input == null) {
            return true;
        }

        for (int i = 0; i < input.length(); i++) {
            if (!Character.isWhitespace(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotNumerical(String input) {   //check for number inputs, return true of false

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) < 46 && input.charAt(i) > 57 || input.charAt(i) == 47) {
                return false;
            }
        }
        return true;
    }

    public String toString() {  //Return bank name as string
        String bank = bankName;

        return bank;
    }

    public double toDouble() {  //Return balance as a double
        double currentBalance = balance;

        return currentBalance;
    }

}
