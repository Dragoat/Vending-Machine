package com.techelevator;

import com.techelevator.view.CompileItemInventory;
import com.techelevator.view.Menu;
import com.techelevator.view.Purchase;

import java.util.Arrays;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

	public Boolean mainMenuSelected = true;
	private Menu menu;
	CompileItemInventory compile = new CompileItemInventory();
	Purchase purchase = new Purchase(menu);

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (mainMenuSelected) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			//maybe replace if's with a switch
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// calls CompileItemInventory.java to display vending machine items
				compile.printDisplay();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				purchase.purchaseMenuSelected = true;
				mainMenuSelected = false;

				purchase.purchaseMenuCall();
			} else if(choice.equals(MAIN_MENU_OPTION_EXIT)){
				break; //to close app
			}
		}
	}


	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		new CompileItemInventory(); //constructs product hash map at run through CompileInventory.java
		cli.run();
	}

}
