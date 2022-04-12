package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class compileItems {
//    VendingItem vendingItem = new VendingItem();

    public static Map<String, Object> itemMap = new LinkedHashMap<>();

    public compileItems(){
//        Scanner fileInput = new Scanner("C:\\Users\\charles\\OneDrive\\Desktop\\MA_CODE_FOLDER\\00 capston project\\module-1-capstone-pair\\capstone\\vendingmachine.csv");
//        String fileProvided = fileInput.nextLine();
//        File inventoryFile = new File(fileProvided);
        File inventoryFile = new File("C:\\Users\\charles\\OneDrive\\Desktop\\MA_CODE_FOLDER\\00 capston project\\module-1-capstone-pair\\capstone\\vendingmachine.csv");
        try(Scanner i = new Scanner(inventoryFile)) {
            while (i.hasNextLine()){
                String itemMenuPrint = i.nextLine();
                String[] itemInfo = i.nextLine().split("\\|");
                VendingItem newItem = new VendingItem(itemMenuPrint, itemInfo[0],itemInfo[1],Double.parseDouble(itemInfo[2]),itemInfo[3]);
                itemMap.put(itemInfo[0], newItem);
            }
        } catch (FileNotFoundException e) {
            System.out.println(inventoryFile.getAbsolutePath() + "not found");
        }
    }

    public static void itemDisplay(){
        for (Object f : itemMap.keySet()){
            Object w = itemMap.get(f);
            System.out.println(w.toString());
        }
    }
}
