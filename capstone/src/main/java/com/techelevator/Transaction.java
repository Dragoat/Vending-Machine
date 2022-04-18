package com.techelevator;

import javax.crypto.Mac;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;

public class Transaction {
    public static String balancemessage = "Current Money Provided: $";
    public static BigDecimal balance = new BigDecimal(0.00).setScale(2);
    private static BigDecimal pastBalance = balance;
    private static int quarters;
    private static int dimes;
    private static int nickels;
    private static double balanceD;
    private static final Queue receipt = new LinkedList<String>();

    //returns the balance
    public static BigDecimal getBalance() {
        return Transaction.balance;
    }

    //method to feed money into the vending machine
    public static void feedCash(String feed) throws FileNotFoundException {
        if(feed.equals("$1")) {
            balance = balance.add(new BigDecimal(1.00));
        } else if(feed.equals("$2")) {
            balance = balance.add(new BigDecimal(2.00));
        } else if(feed.equals("$5")) {
            balance = balance.add(new BigDecimal(5.00));
        } else if(feed.equals("$10")) {
            balance = balance.add(new BigDecimal(10.00));
        }
        System.out.println("Current Money Provided: $" + balance);
        //updates audit log with money feed
        writeAuditLog("FEED MONEY", pastBalance, balance);
    }

    //method to make change when asked
    public static void makeChange(BigDecimal balance) throws FileNotFoundException {
        BigDecimal oldBalance = balance;
        balanceD = (balance.doubleValue()*100);
        quarters = (int) (balanceD/25);
        balanceD -= (quarters*25);
        dimes = (int) (balanceD/10);
        balanceD -= (dimes*10);
        nickels = (int) balanceD/5;
        Transaction.balance = balance.multiply(new BigDecimal(0.00));
        oldBalance = oldBalance.multiply(new BigDecimal(0.00));
        System.out.println("Your Change: " + quarters + " quarters " + dimes + " dimes " + nickels + " nickels.");
        //updates audit log with change
        writeAuditLog("GIVE CHANGE", oldBalance, balance);
    }

    //the method below will allow customer to buy item

    public static void buySnacks(String snackCode) throws FileNotFoundException {
        //when the code doesn't match, it'll come out invalid
        if(!MachineItems.itemCode.containsKey(snackCode)) {
            System.out.println("Code Invalid, please try a valid code");
        } else if (MachineItems.itemCode.containsKey(snackCode)) {
            //checking if the balance the customer has provided is enough
            if(balance.compareTo(MachineItems.itemPrice.get(snackCode)) == -1) {
                System.out.println("INSUFFICIENT FUNDS");
            } else if(MachineItems.itemStock.get(snackCode) == 0) {
                //checks if item is out of stock
                System.out.println("OUT OF STOCK");
            } else if(balance.compareTo(MachineItems.itemPrice.get(snackCode)) == 1 || balance.compareTo(MachineItems.itemPrice.get(snackCode)) == 0) {
                //the balance will update
                BigDecimal oldBalance = balance;
                //receipt
                receipt.add(MachineItems.itemName.get(snackCode) +" - $"+ MachineItems.itemPrice.get(snackCode));
                balance = balance.subtract(MachineItems.itemPrice.get(snackCode));
                //taking from the stock
                MachineItems.itemStock.put(snackCode, MachineItems.itemStock.get(snackCode)-1);
                writeAuditLog(MachineItems.itemName.get(snackCode), oldBalance, balance);
                System.out.println("Enjoy! \n" + "Your balance is now: $" + balance);
                //printing out the messages based on codes
                if(snackCode.contains("A")) {
                    Chips.getMessage();
                } else if (snackCode.contains("B")) {
                    Candy.getMessage();
                } else if(snackCode.contains("C")) {
                    Beverage.getMessage();
                } else if(snackCode.contains("D")) {
                    Gum.getMessage();
                }
            }

        }
    }

    public static void getReceipt() throws FileNotFoundException{
        if (receipt.size() != 0) {
            Double total = 0.0; //@change to big decimal
            System.out.println(System.lineSeparator() + "***Receipt Print***"); //@line separate needed
            //System.out.println(System.lineSeparator());
            for (Object f : receipt) {
                total += Double.parseDouble(f.toString().substring(f.toString().length() - 4));

                System.out.println(f);

            }
            System.out.println(System.lineSeparator() + "Items bought - " + receipt.size() + "\nTotal Spent - $" + String.format("%.2f", total));
            System.out.println("Thank you! Come again!!!");

        }
    }

    //log to audit all the transactions that are being done
    public static void writeAuditLog(String transaction, BigDecimal oldBalance, BigDecimal currentBalance) throws FileNotFoundException {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter specificFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        String dateFormatted = dateTime.format(specificFormat);
        try(PrintWriter auditLog = new PrintWriter(new FileOutputStream("AuditLog.txt", true))) {
            auditLog.println(">" + dateFormatted + " " + transaction + " $" + oldBalance + " $" + currentBalance);
        }
    }
}
