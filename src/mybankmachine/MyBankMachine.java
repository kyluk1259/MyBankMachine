/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybankmachine;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Kyle's PC
 */
public class MyBankMachine {

    public static double balance;
    public static String dispBalance, bankName;
    public static ATM account;
    public static Font atm;
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static double depositAmt, withdrawAmt;
    public static int window;
    public static final int WIDTH = (int) screenSize.width;
    public static final int HEIGHT = (int) screenSize.height;
    public static Border blackline = BorderFactory.createLineBorder(Color.BLACK);
    public static DecimalFormat two = new DecimalFormat("0.00");
    public static Color background, fontColor;
    public static JFrame main, depositWindow, withdrawWindow, historyWindow, interestWindow, current;
    public static JButton deposit, withdraw, history, interest, close, accept, cancel;
    public static JPanel contentPane, rectangle, depositPane, withdrawPane, historyPane, interestPane;
    public static JFormattedTextField input;
    public static JLabel money;
    public static NumberFormat number = NumberFormat.getNumberInstance();
    public static NumberFormatter numbers = new NumberFormatter(number);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        init();
        atmScreen();
    }

    public static void init() {
        account = new ATM();

        balance = account.toDouble();
        bankName = account.toString().toUpperCase();

        background = Color.LIGHT_GRAY;
        fontColor = Color.BLACK;

        if (bankName.contains("TD") || bankName.contains("TORONTO")) {
            background = Color.GREEN;
            bankName = "TD Canada Trust ATM";
        } else if (bankName.contains("BMO") || bankName.contains("MONTREAL")) {
            background = Color.CYAN;
            bankName = "Bank of Montreal ATM";
        } else if (bankName.contains("RBC") || bankName.contains("ROYAL")) {
            background = Color.BLUE;
            fontColor = Color.WHITE;
            bankName = "Royal Bank of Canada ATM";
        } else if (bankName.contains("SCOTIABANK") || bankName.contains("NOVA") || bankName.contains("SCOTIA")) {
            background = Color.RED;
            bankName = "Bank of Nova Scotia ATM";
        } else {
            background = Color.LIGHT_GRAY;
            bankName += " ATM";
        }

        numbers.setValueClass(Double.class);
        numbers.setMinimum(0.00);
        numbers.setMaximum(Double.MAX_VALUE);
        numbers.setAllowsInvalid(true);
        numbers.setCommitsOnValidEdit(true);

    }

    public static void atmScreen() {

        atm = new Font("Arial", Font.BOLD, 30);

        main = new JFrame("ATM");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        draw();
        buttons();

        main.setSize(600, 600);

    }

    public static void draw() {

        dispBalance = two.format(balance);

        contentPane = (JPanel) main.getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(background);

        JLabel bank = new JLabel(bankName);
        contentPane.add(bank);
        bank.setFont(atm);
        bank.setForeground(fontColor);
        Dimension title = bank.getPreferredSize();
        bank.setBounds(20, 40, title.width, title.height);

        money = new JLabel("");
        money.setText("$" + dispBalance);
        contentPane.add(money);
        money.setFont(atm);
        money.setForeground(Color.BLACK);
        Dimension size = money.getPreferredSize();
        money.setBounds(275, 100, size.width, size.height);

        rectangle = new JPanel();
        rectangle.setBackground(Color.WHITE);
        contentPane.add(rectangle);
        rectangle.setBorder(blackline);
        rectangle.setBounds(75, 100, 450, size.height);

        input = new JFormattedTextField(numbers);
        input.setBorder(blackline);
        input.setBounds(200, 200, 200, size.height);
        input.setFont(atm);
        input.setHorizontalAlignment((int) JFormattedTextField.CENTER_ALIGNMENT);

        main.setVisible(true);
    }

    public static void buttons() {

        deposit = new JButton("Deposit");
        contentPane.add(deposit);
        deposit.setBorder(blackline);
        deposit.setBounds(75, 200, 200, 75);
        deposit.addActionListener(click);

        withdraw = new JButton("Withdraw");
        contentPane.add(withdraw);
        withdraw.setBorder(blackline);
        withdraw.setBounds(325, 200, 200, 75);
        withdraw.addActionListener(click);

        interest = new JButton("Compound Interest");
        contentPane.add(interest);
        interest.setBorder(blackline);
        interest.setBounds(75, 350, 200, 75);
        interest.addActionListener(click);

        close = new JButton("Exit Account");
        contentPane.add(close);
        close.setBorder(blackline);
        close.setBounds(325, 350, 200, 75);
        close.addActionListener(click);

        accept = new JButton("");
        accept.setBorder(blackline);
        accept.setBounds(325, 475, 200, 75);

        cancel = new JButton("Go Back");
        cancel.setBorder(blackline);
        cancel.setBounds(75, 475, 200, 75);

    }

    public static void depositWindow() {

        window = 1;

        depositWindow = new JFrame("Deposit");
        depositWindow.setSize(main.getSize());
        depositWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        depositWindow.setLocation(WIDTH / 2, HEIGHT / 2);
        depositWindow.setAlwaysOnTop(true);

        depositPane = (JPanel) depositWindow.getContentPane();
        depositPane.setLayout(null);
        depositPane.setBackground(background);

        JLabel dollarSign = new JLabel("$");
        dollarSign.setFont(atm);
        dollarSign.setForeground(Color.BLACK);
        dollarSign.setBounds(210, 200, 40, 40);

        accept.setText("Deposit Money");
        accept.addActionListener(click);

        cancel.addActionListener(click);

        depositPane.add(money);
        depositPane.add(rectangle);
        depositPane.add(dollarSign);
        depositPane.add(input);
        depositPane.add(accept);
        depositPane.add(cancel);

        current = depositWindow;
        current.setVisible(true);

    }

    public static void withdrawWindow() {

        window = 2;

        withdrawWindow = new JFrame("Withdraw");
        withdrawWindow.setSize(main.getSize());
        withdrawWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        withdrawWindow.setLocation(WIDTH / 2, HEIGHT / 2);
        withdrawWindow.setAlwaysOnTop(true);

        withdrawPane = (JPanel) withdrawWindow.getContentPane();
        withdrawPane.setLayout(null);
        withdrawPane.setBackground(background);

        JLabel dollarSign = new JLabel("$");
        dollarSign.setFont(atm);
        dollarSign.setForeground(Color.BLACK);
        dollarSign.setBounds(210, 200, 40, 40);

        accept.setText("Withdraw Money");
        accept.addActionListener(click);

        cancel.addActionListener(click);

        withdrawPane.add(money);
        withdrawPane.add(rectangle);
        withdrawPane.add(dollarSign);
        withdrawPane.add(input);
        withdrawPane.add(accept);
        withdrawPane.add(cancel);

        current = withdrawWindow;
        current.setVisible(true);
    }

    public static ActionListener click = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == deposit) {
                depositWindow();
                main.setFocusableWindowState(false);
            }

            if (e.getSource() == withdraw) {
                withdrawWindow();
                main.setFocusableWindowState(false);
            }

            if (e.getSource() == history) {

            }

            if (e.getSource() == interest) {

            }

            if (e.getSource() == close) {
                System.exit(0);
            }

            if (e.getSource() == accept) {

                Dimension size = new Dimension();

                switch (window) {
                    case 1:

                        String in = input.getText();
                        in = in.replace(",", "");
                        System.out.print(input.getText() + "\n");
                        depositAmt = Double.parseDouble(input.getText());
                        System.out.println(depositAmt);
                        balance += depositAmt;
                        dispBalance = two.format(balance);
                        money.setText("$" + dispBalance);
                        size = money.getPreferredSize();
                        money.setBounds(275, 100, size.width, size.height);
                        input.setText("0");

                    case 2:

                        in = input.getText();
                        in = in.replace(",", "");

                        for (int i = 0; i < in.length(); i++) {
                            if (in.charAt(i) < 47 && in.charAt(i) > 58) {
                                input.setText("0");                   
                                break;
                            } else {
                                withdrawAmt = Double.parseDouble(in);
                            }
                        }
                        if (withdrawAmt > balance) {
                            input.setText("0");
                        } else {
                            balance -= withdrawAmt;
                            dispBalance = two.format(balance);
                            money.setText("$" + dispBalance);
                            size = money.getPreferredSize();
                            money.setBounds(275, 100, size.width, size.height);
                            input.setText("0");
                        }
                }
            }

            if (e.getSource() == cancel) {
                current.dispose();
                main.setFocusableWindowState(true);
                contentPane.removeAll();
                draw();
                buttons();
                money.setText("$" + dispBalance);
                Dimension size = money.getPreferredSize();
                money.setBounds(275, 100, size.width, size.height);
            }
        }
    };
}
