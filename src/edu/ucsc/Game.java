package edu.ucsc;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JTextArea;

import edu.ucsc.Input;

public class Game {
	private static GameState gameState = new GameState();
	public static GameState getGameState(){
		return gameState;
	}
	
	public static void resetGameState(){
		gameState = new GameState();
	}
	
	public static void main(String[] args) throws IOException { 
		//System.out.println(gameState.pointerTree("unknown1"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean keepLooping = true;
		while(keepLooping){
			System.out.println("Enter a command: ");
			String input = br.readLine();
			keepLooping = gameLoop(input, null, null, null);
		}
	}
	
	public static void gameOutput(JTextArea area, String output){//console and GUI
		if (area == null){
			System.out.println(output);
		}else{
			area.append(output + "\n");
		}
	}
	
	public static boolean gameLoop(String input, JTextArea area, GameMainPanel panel, GameGUI gameGUI){
		//screen should echo commands (show is is command not computer answer)
		if (input.equalsIgnoreCase("quit")){
			gameOutput(area, "Goodbye!");
			return false;
		}
		
		if(gameState.winState()){
			gameOutput(area, "You win!");
			return false;
		}		
		
		if(gameState.getDeadTrees()>12){
			gameOutput(area, "15% of your orchard has died. \n You cannot sustain the farm. \n You have to sell the orchard. You lose.");
			return false;
		}
		
		int commandType = Input.isCommand(input);
		
		String otherWords = Input.getOtherWords(input);
		String allWords = Input.getAllWords(input);
		Point p = gameState.getPosition();
		gameOutput(area, ">>" + allWords);
		if (commandType == 0){//for START
			next(area, panel, p, gameGUI);
		}
		else if (commandType == 1){//for LOOK
			look(area, p, otherWords);
		}
		else if (commandType == 2){ //for WALK
			if(panel.isInGame()){
				walk(gameGUI, panel, area, p, otherWords);
			}else if(panel.getIntroState()==14){
				walkFromShed(panel, area, otherWords);
			}else{
				gameOutput(area, "You cannot walk while you are reading things or looking"
						+ "\n in the Book or Guide.");
			}
		}
		else if (commandType == 3){//for HELP
			help(panel, area, gameGUI);
		}
		else if (commandType == 4){//for TURN
			if(panel.isInGame()){
				turn(area, p, otherWords);
			}else{
				gameOutput(area, "You cannot turn while you are reading things.");
			}
		}
		else if (commandType == 5){//for ENTER
			//only enter if you are in the first time through introState
			if (gameState.getAlreadyEnteredOrchard()==0){
				if (otherWords.equalsIgnoreCase("orchard")) {
	                enter(panel, area, p, gameGUI);
	            } else {
	                gameOutput(area, "You cannot enter " + otherWords);
	            }
				gameState.setAlreadyEnteredOrchard(1);
			}else{
				gameOutput(area, "You are already in the orchard. \n you probably need to exit back to game.");
			}
		}
		else if(commandType == 6){//for EXIT
			if (gameState.getAlreadyEnteredOrchard()!=0){
				if (!panel.isInGame()){
					exit(panel, area);				
				}else{
					gameOutput(area, "You cannot exit the game, but you can quit it.");
				}
			}else{
				gameOutput(area, "You cannot exit to the game because you have not yet entered the orchard.");
			}
		}else if(commandType == 7){//for EQUALS
			equals(gameGUI, panel, area, p, allWords);
		}else if(commandType == 8){//for GUIDE
			if(panel.getIntroState()==-1){
				guide(gameGUI, panel, area);
			}else{
				gameOutput(area, "You cannot look in the Guide unless you are in the game.");}
		}else if(commandType == 9){//for BOOK
			if(panel.getIntroState()==-1){
				book(gameGUI, panel, area);
			}else{
				gameOutput(area, "You cannot look in the Book unless you are in the game.");
			}
		}
		
		else if (commandType == -1){
			gameOutput(area, "That is not an option. Did you mean to use \n"
					+ "put, look, next, start, exit, quit, or walk?");
		}
		return true;
	}
	
	public static void book(GameGUI gameGUI, GameMainPanel panel,
			JTextArea area) {
		panel.showBook(gameState.getAddressBook());
		gameOutput(area, "You are now looking in the Address Book.");
	}

	public static void guide(GameGUI gameGUI, GameMainPanel panel,
			JTextArea area) {
		panel.showGuide();
		gameOutput(area, "You are now looking in the Field Guide.");
	}
	
	private static void next(JTextArea area, GameMainPanel panel,
			Point p, GameGUI gameGUI){
		if (panel == null){
			return;
		}
		int introState = panel.getIntroState();
		if (introState == -1){
			gameOutput(area, "Error: This is not in the game.");			
		}else if(introState == panel.MAX_INTRO_STATE){
			enter(panel, area, p, gameGUI);
		}else{
			panel.setIntroState(introState+1);
		}
	}
	
	private static void enter(GameMainPanel panel, JTextArea area, Point p, GameGUI gameGUI){
		panel.setIntroState(-1);
		panel.showTree(gameState.getTreeFromLocation(gameState.getPosition()));
		gameGUI.onEnterOrchard();
        gameOutput(area, "You enter the Orchard.");
	}
	
	private static void help(GameMainPanel panel, JTextArea area, GameGUI gameGUI){
		panel.setIntroState(1);
        gameOutput(area, "You are looking at the help menu. \n"
        		+ "Type next to move through, or exit to get back to the game.");
	}
	
	public static void exit(GameMainPanel panel, JTextArea area){
		panel.setIntroState(-1);
		panel.showTree(gameState.getTreeFromLocation(gameState.getPosition()));
		gameOutput(area, "You exit back to the game.");
	}
	
	public static String refreshSteps(){
		StringBuilder sb = new StringBuilder();
		sb.append(gameState.getSteps());
		String strI = sb.toString();
		return strI;
	}
	
	private static String getObject(String otherWords){
		int indexOfEquals = otherWords.indexOf(" = ");
		if (indexOfEquals == -1){
			return "";
		}else{
			return otherWords.substring(indexOfEquals+3);
		}
	}
	
	private static String getSubject(String otherWords){
		int indexOfEquals = otherWords.indexOf(" = ");
		if (indexOfEquals == -1){
			return otherWords;
		}
		return otherWords.substring(0, indexOfEquals);
	}
	
	private static void equals(GameGUI gameGUI, GameMainPanel panel, JTextArea area, Point p, String otherWords){
		//Command Subject = Object
		if(getSubject(otherWords).contains("addressbook")||getSubject(otherWords).equals("book") || getSubject(otherWords).equals("*book") ||getSubject(otherWords).equals("&book") ){
			gameOutput(area, "You cannot use book, or addressBook because Book \n is the variable for the address book. Capitalization is important. \n Did you mean to use Book, *Book, or &Book?");		
		}else if(getSubject(otherWords).equals("*Book")){
			//warn for wrong tree
			if (getObject(otherWords).startsWith("&") && gameState.doesTreeExist(getObject(otherWords).substring(1))){
				gameOutput(area, "You put the address of " + getObject(otherWords).substring(1) + " in the Book.");
				gameState.addToAddressBook(getObject(otherWords));
			}else{
				gameOutput(area, "You cannot put that in the Book, because it is not an address.");
			}
		}else if(getSubject(otherWords).equals("Book")){
			//distinguish lowercase book from Book
			if (getObject(otherWords).startsWith("&")){
				gameOutput(area, "You cannot replace the Book with an address.");
			}else{
				gameOutput(area, "You cannot replace the Book with an object");
			}
		}else if(getSubject(otherWords).equals("&Book")){
			if (getObject(otherWords).startsWith("&")){
				gameOutput(area, "You cannot trade the Book with the address of an object.");
			}else{
				gameOutput(area, "You cannot replace the Book with an object");
			}
		}else if(getSubject(otherWords).contains("fieldguide")){
			gameOutput(area, "You cannot use guide, or fieldGuide because Guide \n is the variable for the field guide. Capitalization is important.\n Did you mean to use Guide?");
		}else if(getSubject(otherWords).equals("*Poison")){
			if(!panel.isInGame()){
				if(getObject(otherWords).startsWith("*")){
					if(getObject(otherWords).substring(1).equals("ApplePoison")){
						gameState.changePoison(1);
						gameGUI.setPoison(1);
						gameOutput(area, "You put the Apple pesticide in the container");
					}else if(getObject(otherWords).substring(1).equals("OrangePoison")){
						gameState.changePoison(2);
						gameGUI.setPoison(2);
						gameOutput(area, "You put the Orange pesticide in the container");
					}else if(getObject(otherWords).substring(1).equals("CherryPoison")){
						gameState.changePoison(3);
						gameGUI.setPoison(3);
						gameOutput(area, "You put the Cherry pesticide in the container");
					}else if(getObject(otherWords).substring(1).equals("NutPoison")){
						gameState.changePoison(4);
						gameGUI.setPoison(4);
						gameOutput(area, "You put the Nut pesticide in the container");
					}else if(getObject(otherWords).substring(1).equals("LemonPoison")){
						gameState.changePoison(5);
						gameGUI.setPoison(5);
						gameOutput(area, "You put the Lemon pesticide in the container");
					}else if(getObject(otherWords).substring(1).equals("LimePoison")){
						gameState.changePoison(6);
						gameGUI.setPoison(6);
						gameOutput(area, "You put the Lime pesticide in the container.");
					}else if(getObject(otherWords).startsWith("&")){
						gameOutput(area, "You cannot put an address in the poison container.");
					}else{
						gameOutput(area, "You cannot put a whole container of pesticide in the poison container.");
					}
				}else{
					
				}
			}else{
				gameOutput(area, "You are not in the shed.");
			}
		}

		else if(getSubject(otherWords).startsWith("*") && gameState.doesTreeExist(getSubject(otherWords).substring(1))){
			if (getObject(otherWords).equals("*Poison")){
				int poison = gameState.getPoison();
				Tree thisTree = gameState.getTree(getSubject(otherWords).substring(1));
				int thisResident = thisTree.getResident();
				String poisonString = gameState.getPoisonString(poison);
				if(poison == thisTree.getTreeType()){
					if(thisResident==0){
						gameOutput(area, "You put the "+ poisonString + " pesticide in the tree, it does nothing \n because nothing is in the tree. \n You have wasted your one shot of poison spray.");
						gameGUI.setPoison(0);
						gameState.changePoison(0);
					}else if (poison == 0){
						gameOutput(area, "You do not have any pesticide in your container. Perhaps you \n should save this address and get more pesticide from the shed.");
					}else{
						gameOutput(area, "You put the "+ poisonString +" pesticide in the tree, killing the "+ thisTree.getResidentString() +".");
						thisTree.setResident(0);
						panel.showTree(thisTree);
						gameGUI.setPoison(0);
						gameState.changePoison(0);
					}
				}else{
					if (poison==0){
						gameOutput(area, "You cannot spray pesticide from an empty container. Save your \n address in Book and then go to the Shed for more pesticide.");
					}else{
						gameOutput(area, "You cannot put "+ poisonString +" poison in a "+ gameState.getPoisonString(thisTree.getTreeType())+" tree.");
					}
				}	
			}else{
				gameOutput(area, "You attempt to put pesticide in " + getSubject(otherWords) + " but you probably messed \n up capitalization, forgot there are no spaces in tree names, \n or spelled something incorrectly.");
			}
		}else if(getSubject(otherWords).startsWith("*") && !getSubject(otherWords).substring(1).equalsIgnoreCase("poison")){
			if(getObject(otherWords).startsWith("*") && !getObject(otherWords).substring(1).equalsIgnoreCase("poison")){
				gameOutput(area, "You cannot move unknown into another tree.");
			}else if(getObject(otherWords).startsWith("&")){
				gameOutput(area, "You cannot dig up an unknown to put it at this tree's location.");
			}else{
				gameOutput(area, "You cannot plant a new "+ otherWords+ " to replace this tree.");	
			}
		}else if(getSubject(otherWords).startsWith("&") && !getSubject(otherWords).substring(1).equalsIgnoreCase("poison") && gameState.doesTreeExist(getSubject(otherWords).substring(1))){
			if(getObject(otherWords).startsWith("*")){
				gameOutput(area, "You cannot move pests or pollinators to an address.");
			}else if(getObject(otherWords).startsWith("&")){
				gameOutput(area, "You cannot dig up a tree to put another here.");
			}else{
				gameOutput(area, "You cannot plant a new tree of type "+ otherWords+ " here.");	
			}
		}else if(gameState.doesTreeExist(getSubject(otherWords))&& !getObject(otherWords).substring(1).equalsIgnoreCase("poison")){
			if(getObject(otherWords).startsWith("*")){
				gameOutput(area, "You cannot replace a tree with pests or pollinators.");
			}else if(getObject(otherWords).startsWith("&")){
				gameOutput(area, "You cannot dig up this tree and move it to a new address.");
			}else{
				gameOutput(area, "You cannot plant a new tree of type "+ otherWords+ " here.");	
			}
		}else{
			gameOutput(area, "That is not the poison container, a tree, or the address book.");
		}
	}
	
	private static void look(JTextArea area, Point p, String otherWords) {
		//fix error messages
		if (otherWords.equalsIgnoreCase("")){
			gameOutput(area, "You see what is in front of you.");
		}else if(otherWords.equals("*Poison")){
			if(gameState.getPoison()==1){
				gameOutput(area, "You see the Apple poison");
			}else if(gameState.getPoison()==2){
				gameOutput(area, "You see the Orange poison");
			}else if(gameState.getPoison()==3){
				gameOutput(area, "You see the Cherry poison");
			}else if(gameState.getPoison()==4){
				gameOutput(area, "You see the Nut poison");
			}else if(gameState.getPoison()==5){
				gameOutput(area, "You see the Lemon poison");
			}else if(gameState.getPoison()==6){
				gameOutput(area, "You see the Lime poison");
			}
		}
		else if(otherWords.startsWith("*") && gameState.doesTreeExist(otherWords.substring(1))){
			Tree localTree = gameState.getTree(getSubject(otherWords).substring(1));
			if (localTree.getTreeType()==0){
				gameOutput(area, "The tree is dead. You see it is empty. Pests must have killed it.");
			}else{
				if(localTree.getResident() == 0){
					gameOutput(area, "You see the tree is empty.");
				}else if(localTree.getResident()==1){
					gameOutput(area, "You see the tree contains ants. Use the Guide \n to figure out if it is a pest that destroys or pollinator that protects the tree.");
				}else if(localTree.getResident()==2){
					gameOutput(area, "You see the tree contains wasps. Use the Guide \n to figure out if it is a pest that destroys or pollinator that protects the tree.");
				}else if(localTree.getResident()==3){
					gameOutput(area, "You see the tree contains gophers. Use the Guide \n to figure out if it is a pest that destroys or pollinator that protects the tree.");
				}else if(localTree.getResident()==4){
					gameOutput(area, "You see the tree contains aphids. Use the Guide \n to figure out if it is a pest that destroys or pollinator that protects the tree.");
				}else if(localTree.getResident()==5){
					gameOutput(area, "You see the tree contains butterflies. Use the \n Guide to figure out if it is a pest that destroys or pollinator that protects the tree.");
				}else if(localTree.getResident()==6){
					gameOutput(area, "You see the tree contains bees. Use the Guide \n to figure out if it is a pest that destroys or pollinator that protects the tree.");
				}else if(localTree.getResident()==7){
					gameOutput(area, "You see the tree contains frogs. Use the Guide \n to figure out if it is a pest that destroys or pollinator that protects the tree.");
				}else if(localTree.getResident()==8){
					gameOutput(area, "You see the tree contains ladybugs. Use the Guide \n to figure out if it is a pest that destroys or pollinator that protects the tree.");
				}				
			}
		}else if(otherWords.startsWith("&")){
			gameOutput(area, "Error: you cannot look at addresses.");
		}else{
			gameOutput(area, "Error: " +otherWords + " is neither a Tree, nor is it an object in game.");
		}
	}
	
	private static void turn(JTextArea area, Point p, String otherWords){
		if (otherWords.equalsIgnoreCase("right")){
			gameState.turnRight();
			gameOutput(area, "You turned right");
		}else if (otherWords.equalsIgnoreCase("left")){
			gameState.turnLeft();
			gameOutput(area, "You turned left");
		}
	}
	
	private static void walkFromShed(GameMainPanel panel, JTextArea area, String otherWords){
		if (otherWords.startsWith("&")){
			if (gameState.doesTreeExist(otherWords.substring(1)) && gameState.getAddressBook().contains(otherWords)){
				//bound with address book
				panel.showTree(gameState.getTree(otherWords.substring(1)));
				gameOutput(area, "You walk to " + otherWords.substring(1) + ".");
				gameState.moveToPoint(gameState.getTree(otherWords.substring(1)).getLocation());
			}else{
				gameOutput(area, "You cannot walk to an address that is not stored in Book.");
			}
		}else{
			gameOutput(area, "You cannot walk out of the shed. You need an address \n to walk to. Hope you saved an address in the Book.");
		}
	}
	
	private static void walk(GameGUI gameGUI, GameMainPanel panel, JTextArea area, Point p, String otherWords){
		if (gameState.getSeason()!=0 && gameState.getSteps()%200==0){
			gameState.changeSeason(true);
		}else{
			if (otherWords.startsWith("&")){
				if (gameState.doesTreeExist(otherWords.substring(1)) && gameState.getAddressBook().contains(otherWords.substring(1))){
					//bound with address book
					panel.showTree(gameState.getTree(otherWords.substring(1)));
					gameOutput(area, "You walk to " + otherWords.substring(1) + ".");
					gameState.moveToPoint(gameState.getTree(otherWords.substring(1)).getLocation());
				}else if (otherWords.substring(1).equals("Shed")){
					panel.showShed();
					gameOutput(area, "You should probably use * to move the poisons around. \n "
							+ "Like put *Poison = *ApplePoison");
				}
				gameState.takeSteps();
				gameGUI.refreshCounter();
				return;
			}else if(otherWords.equals("&Book") || otherWords.equals("&Guide")){
				gameOutput(area,"You cannot walk to your inventory.");
				return;
			}
			if(otherWords.equalsIgnoreCase("north")){
				gameState.setDirection(0);
			}else if(otherWords.equalsIgnoreCase("South")){
				gameState.setDirection(180);
			}else if(otherWords.equalsIgnoreCase("east")){
				gameState.setDirection(90);
			}else if(otherWords.equalsIgnoreCase("west")){
				gameState.setDirection(270);
			}else if (!otherWords.equals("")){
				gameOutput(area, "Error: not a valid direction or address.");
				return;
			}
			
			if (gameState.moveForward()){
				int dir = gameState.getDirection();
					if(dir == 0){
						gameOutput(area, "You walk North");
					}else if (dir ==90){
						gameOutput(area, "You walk East");
					}else if(dir ==180){
						gameOutput(area, "You walk South");
					}else{//dir == 270
						gameOutput(area, "You walk West");
					}
					gameState.takeStep();
					gameGUI.refreshCounter();
			}else{
				//ERROR
				gameOutput(area, "You have reached the border of the Orchard. You cannot continue to walk in this direction.");
			}
		}
		panel.showTree(gameState.getTreeFromLocation(gameState.getPosition()));		
	}
}

