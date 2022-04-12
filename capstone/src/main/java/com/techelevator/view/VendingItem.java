package com.techelevator.view;

public class VendingItem {

    String itemMenuPrint;
    String itemCode;
    String itemName;
    double itemPrice;
    String itemType;

    public VendingItem(String itemMenuPrint, String itemCode, String itemName, double itemPrice, String itemType){
        this.itemMenuPrint = itemMenuPrint;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemType = itemType;

        //System.out.println(itemCode+ itemName+ itemPrice+ itemType);
    }
    public String getItemMenuPrint(){return itemMenuPrint;}
    public String getItemCode() {return itemCode;}
    public String getItemName() {return itemName;}
    public double getItemPrice() {return itemPrice;}
    public String getItemType() {return itemType;}

    @Override
    public String toString() {
        return itemMenuPrint;

//        return "VendingItem{" +
//                "itemCode='" + itemCode + '\'' +
//                ", itemName='" + itemName + '\'' +
//                ", itemPrice=" + itemPrice +
//                ", itemType='" + itemType + '\'' +
//                '}';
    }
}
