package com.techelevator.view;

import java.io.InputStream;
import java.util.Scanner;

import static com.techelevator.view.Menu.*;
import static java.lang.System.out;

public class Purchase {
    private static final String PURCHASE_MENU_OPTION_MONEY_FEED = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH = "Finish Transaction";
    public static final String PURCHASE_MENU_DISPLAY_MONEY_PROVIDED = "\n Current Money Provided: $"; //MONEY GOES HERE
    public static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_MONEY_FEED, PURCHASE_MENU_OPTION_SELECT_PRODUCT,
                                                                PURCHASE_MENU_OPTION_FINISH};
    private static int moneyProvided = 0;

    public Boolean purchaseMenuSelected=false;
    private Menu menu;
    CompileItemInventory compile = new CompileItemInventory();

    public Purchase(){}

    public Purchase(Menu menu) {
        this.menu = menu;
    }

    public static double updateCurrentMoney(){
        //out.println(PURCHASE_MENU_DISPLAY_MONEY_PROVIDED + 22);
        return moneyProvided;
    }

    public void purchaseMenuCall(){
        Menu menu = new Menu(System.in, out); // ask how this makes the new menu

        String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

        while (purchaseMenuSelected){
            if(choice.equals(PURCHASE_MENU_OPTION_MONEY_FEED)){
                //money feed method
                menu.moneyIntake(); //takes user money input
                purchaseMenuCall(); //returns to menu
            } else if(choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)){
                //display vending items for selection

                compile.printDisplay(); //display menu again
                orderIntake();
                purchaseMenuCall();
            } else if(choice.equals(PURCHASE_MENU_OPTION_FINISH)){
                //finish transaction
                out.println("\n finish me");
            }

        }

    }


// converts string user input to integer and adds it to current money provided
    public static void feedMoney(String payedMoney){

        moneyProvided += Integer.parseInt(payedMoney);
    }
}
