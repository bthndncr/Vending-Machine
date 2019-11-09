package com.techelevator;

import com.techelevator.view.Menu;   // Bring in Menu class provided

public class VendingMachineApp {
	/*************************************************************************************************************************
	*  This is the application program to instantiate a Vending Machine as start it running
	*  
	*  DO NOT PUT ANY NEW CODE HERE!
	***************************************************************************************************************************/
		
		public static void main(String[] args) {
			Menu appMenu = new Menu(System.in, System.out);                // Instantiate a menu for Vending Machine to use
			VendingMachineCLI vendingCli = new VendingMachineCLI(appMenu);
			System.out.println("                                                                                                                \n" + 
					" @@@@@@   @@@  @@@  @@@@@@@   @@@@@@@@  @@@@@@@              @@@  @@@  @@@@@@@@  @@@  @@@  @@@@@@@    @@@@@@   \n" + 
					"@@@@@@@   @@@  @@@  @@@@@@@@  @@@@@@@@  @@@@@@@@             @@@  @@@  @@@@@@@@  @@@@ @@@  @@@@@@@@  @@@@@@@@  \n" + 
					"!@@       @@!  @@@  @@!  @@@  @@!       @@!  @@@             @@!  @@@  @@!       @@!@!@@@  @@!  @@@  @@!  @@@  \n" + 
					"!@!       !@!  @!@  !@!  @!@  !@!       !@!  @!@             !@!  @!@  !@!       !@!!@!@!  !@!  @!@  !@!  @!@  \n" + 
					"!!@@!!    @!@  !@!  @!@@!@!   @!!!:!    @!@!!@!   @!@!@!@!@  @!@  !@!  @!!!:!    @!@ !!@!  @!@  !@!  @!@  !@!  \n" + 
					" !!@!!!   !@!  !!!  !!@!!!    !!!!!:    !!@!@!    !!!@!@!!!  !@!  !!!  !!!!!:    !@!  !!!  !@!  !!!  !@!  !!!  \n" + 
					"     !:!  !!:  !!!  !!:       !!:       !!: :!!              :!:  !!:  !!:       !!:  !!!  !!:  !!!  !!:  !!!  \n" + 
					"    !:!   :!:  !:!  :!:       :!:       :!:  !:!              ::!!:!   :!:       :!:  !:!  :!:  !:!  :!:  !:!  \n" + 
					":::: ::   ::::: ::   ::        :: ::::  ::   :::               ::::     :: ::::   ::   ::   :::: ::  ::::: ::  \n" + 
					":: : :     : :  :    :        : :: ::    :   : :                :      : :: ::   ::    :   :: :  :    : :  :");
			vendingCli.run();                              						  // Tell the Vending MachineCLI to start running
		}
	}


