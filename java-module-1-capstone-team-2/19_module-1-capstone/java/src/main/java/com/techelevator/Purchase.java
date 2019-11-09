package com.techelevator;

import java.math.BigDecimal;
import java.util.Scanner;
import com.techelevator.view.Menu;

public class Purchase {
	
	private static final String SUB_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String SUB_MENU_OPTION_SELECT     = "Select Product";
	private static final String SUB_MENU_OPTION_FINISH     = "Finish Transaction";
	private static final String[] SUB_MENU_OPTIONS = { SUB_MENU_OPTION_FEED_MONEY
													, SUB_MENU_OPTION_SELECT
													, SUB_MENU_OPTION_FINISH};
	
	private static final String YES_OPTION = "Yes";
	private static final String NO_OPTION = "No";
	private static final String[] YES_NO_OPTIONS = {YES_OPTION, NO_OPTION};
	
	Transaction transaction = new Transaction(new BigDecimal("0"), VendingMachineCLI.bookKeeping);
	public Inventory inventory;
	public Menu vendingSubMenu;

	public Purchase(Menu menu, Inventory inventory) {
		this.inventory = inventory;
		this.vendingSubMenu = menu;
		
	}

	public void runSubMenu() {
		boolean shouldProcess = true;         			
		while(shouldProcess) {                		
			String choice = (String)vendingSubMenu.getChoiceFromOptions(SUB_MENU_OPTIONS);  
			
			switch(choice) {                  		
			
				case SUB_MENU_OPTION_FEED_MONEY:
					transaction.insertMoney();           		
					break;                   		
			
				case SUB_MENU_OPTION_SELECT:
				
					if(!selection()) {
						System.out.println("\n*** Invalid entry ***");
						break; 
					} else {
						break;
					}
					
				case SUB_MENU_OPTION_FINISH:
					transaction.endTransaction();    
					shouldProcess = false;    
					break;                    
			}	
		}
		return;                               // End method and return to caller
	}
	
	

	public void yesOrNoMenu() {

		boolean shouldProcess = true;         // Loop control variable
		
		while(shouldProcess) {                // Loop until user indicates they want to exit
			
			String choice = (String)vendingSubMenu.getChoiceFromOptions(YES_NO_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                  // Process based on user menu choice
			
				case YES_OPTION:
					transaction.yesTransactionProcess();        
					shouldProcess = false;
					break;
					
				case NO_OPTION:
					shouldProcess = false;
					break;                    
			}	
		}
		return;                               // End method and return to caller
	}
	
	private boolean selection()  {
		boolean process = true;
		Scanner scanner = new Scanner(System.in);
		displayMenuItems();
		System.out.println("Please choose an item (e.g., A2)");
		String selectedItem = scanner.nextLine();
		
		for(Product aProduct : inventory.inventoryList) {
			if(aProduct.getProductCode().equals(selectedItem.toUpperCase())) {
				System.out.println("You chose: " + aProduct.getProductCode()  + " " + aProduct.getName() + " $" + aProduct.getPrice() + " stock: " + aProduct.getQuantity());
				if(aProduct.getQuantity() == 0) {
					System.out.println("\n*** SOLD OUT ***\n");
					process = true;
					return process;
				}
				System.out.println("Are you sure this is what you want?");
				transaction.theProduct = aProduct;
				yesOrNoMenu();
				process = true;
				return process;
				
			} else {
				process = false;
				
			}
		}
		return process;
		
	}
	
	public void displayMenuItems() {
			System.out.println();
			System.out.println(String.format("%-25s", "") + String.format("%-10s", "ITEM#") 
							+ String.format("%-20s", "NAME")
							+ String.format("%-10s", "PRICE")
							+ String.format("%-10s", "TYPE")
							+ String.format("%-5s", "QUANTITY"));
			
			for(Product theProduct : inventory.inventoryList) {
				String screen = String.format("%-25s", "") + "=========================================================\n" 
							  + String.format("%-25s", "") + String.format("%-10s", theProduct.getProductCode()) 
							  + String.format("%-20s", theProduct.getName()) 
							  + String.format("%-10s", theProduct.getPrice()) 
							  + String.format("%-10s", theProduct.getType() 
							  + "\t\t" + theProduct.getQuantity());
			
				System.out.println(screen);
			}
			System.out.println(String.format("%-25s", "") + "=========================================================\n");
		
	}
	

	
}
