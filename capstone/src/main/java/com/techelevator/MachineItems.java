package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//1 - creating a class to extends to all item types
public class MachineItems {
    //2- creating the File object
    private static final File itemList = new File("C:\\Users\\rmall\\OneDrive\\Desktop\\Assignments\\module-1-capstone-pair\\capstone\\vendingmachine.csv");
    //3- these maps will contain the items code, price, name, stock, and type
    public static Map<String, String> itemCode = new HashMap<>();
    public static Map<String, BigDecimal> itemPrice = new HashMap<>();
    public static Map<String, String> itemName = new HashMap<>();
    public static Map<String, Integer> itemStock = new HashMap<>();
    public static Map<String, String> itemType =  new HashMap<>();
    public static String[] itemsArray = new String[4];

    //4- this method will read the file and store them in the appropriate maps

    public static void readFile() throws FileNotFoundException {
        try(Scanner fileReader = new Scanner(itemList)) {
            while(fileReader.hasNextLine()) {
                String fileLine = fileReader.nextLine();
                itemsArray = fileLine.split("\\|");
                itemCode.put(itemsArray[0], fileLine);
                itemPrice.put(itemsArray[0], new BigDecimal(itemsArray[2]));
                itemName.put(itemsArray[0], itemsArray[1]);
                itemStock.put(itemsArray[0], 5);
                itemType.put(itemsArray[0], itemsArray[3]);
            }
        }
    }
}
