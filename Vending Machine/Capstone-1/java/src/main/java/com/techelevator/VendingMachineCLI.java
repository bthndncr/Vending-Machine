package com.techelevator;


/**************************************************************************************************************************
*  This is your Vending Machine Command Line Interface (CLI) class
*  
*  It is instantiated and invoked from the VendingMachineApp (main() application)
*  
*  Your code should be placed in here
***************************************************************************************************************************/
import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE      = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT          = "Exit";
	private static final String MAIN_MENU_OPTION_HIDDEN 	   = "";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													    MAIN_MENU_OPTION_PURCHASE,
													    MAIN_MENU_OPTION_EXIT,
													    MAIN_MENU_OPTION_HIDDEN
													    };
	
	private Menu vendingMenu; 
	public static Inventory inventory = new Inventory();
	public static BookKeeping bookKeeping = new BookKeeping();
	Purchase purchase = new Purchase(vendingMenu, inventory);
	
	public VendingMachineCLI(Menu menu) {  // Constructor - user will pass a menu for this class to use
		this.vendingMenu = menu;  
		// Make the Menu the user object passed, our Menu
	}
	/**************************************************************************************************************************
	*  VendingMachineCLI main processing loop
	*  
	*  Display the main menu and process option chosen
	***************************************************************************************************************************/

	public void run() {
		boolean shouldProcess = true;         
		while(shouldProcess) {                
			String choice = (String)vendingMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS); 
			switch(choice) {                 
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					displayItems();          
					break;                    
			
				case MAIN_MENU_OPTION_PURCHASE:
					purchaseItems();         
					break;                   
			
				case MAIN_MENU_OPTION_EXIT:
					endMethodProcessing();    
					shouldProcess = false;   
					break;                    
				case MAIN_MENU_OPTION_HIDDEN:
					bookKeeping.displayReport(inventory);
			}	
		}
		return;                             
	}
/********************************************************************************************************
 * Methods used to perform processing
 ********************************************************************************************************/
	public  void displayItems() {      
		purchase.displayMenuItems();
	}
	
	public void purchaseItems() {	 
		purchase = new Purchase(vendingMenu, inventory);
		purchase.runSubMenu();
	}
	
	public static void endMethodProcessing() { 
		bookKeeping.report(inventory);
		bookKeeping.auditClose();
		System.out.println("\nHave a nice day! :)");
	}
}
