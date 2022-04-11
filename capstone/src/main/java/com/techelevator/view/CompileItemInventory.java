package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static com.techelevator.view.Menu.*;

public class CompileItemInventory {
    public Map<String, List<String>> productMap = new LinkedHashMap<>();

    //constructs the product hash map at run using the slot identifier as the
    // map key to accsess the productInformation list
    public CompileItemInventory() {
        //creats a file to hold the vending machin inventory information from the provided file
        Scanner providerItemListFile = new Scanner("C:\\Users\\charles\\OneDrive\\Desktop\\MA_CODE_FOLDER\\00 capston project\\module-1-capstone-pair\\capstone\\vendingmachine.csv");
        String providerItemList = providerItemListFile.nextLine();
        File itemList = new File(providerItemList);

        //try catch uses to check itemList file path exists
        try(Scanner scannedItemList = new Scanner(itemList)){
            while (scannedItemList.hasNextLine()){
                //splits each line of text by "|" and saves all elements to a list
                //list was used over array to so allow the addition of a fifth element for stock count
                List<String> productInformation = new ArrayList<>(List.of(scannedItemList.nextLine().split("\\|")));
                //adds stock count of 5 to the new list

                    productInformation.add("5");

                // save new list into a hash map with (0) as the keyword and the list as the value
                productMap.put(productInformation.get(0), productInformation);
            }
        }catch (FileNotFoundException t){
            System.out.println("product list not found");
        }
    }

    public void setProductMap(Map<String, List<String>> productMap) {
        this.productMap = productMap;
    }

    //when called, will display all vending inventory
    public void printDisplay(){
        for(String item : productMap.keySet()){
            List<String> itemToPrint = productMap.get(item);
            //if statment added to check for sock count
            //still needs sock count deductor

            //System.out.println(itemToPrint.get(0) + " --> " + itemToPrint.get(1) + " - $" + itemToPrint.get(2) + " - " + itemToPrint.get(4) + " in stock");
            if(itemToPrint.get(4).equals("0")){
                itemToPrint.add(4, "SOLD OUT");
                System.out.println(itemToPrint.get(0) + " --> " + itemToPrint.get(1) + " - $" + itemToPrint.get(2) + " - " + itemToPrint.get(4));
            } else {
                System.out.println(itemToPrint.get(0) + " --> " + itemToPrint.get(1) + " - $" + itemToPrint.get(2) + " - " + itemToPrint.get(4) + " in stock");
            }
        }
    }
}
