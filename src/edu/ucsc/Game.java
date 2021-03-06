package edu.ucsc;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JTextArea;

import edu.ucsc.Input;

public class Game {
	private static boolean inShed = false;
	private static boolean fromEquals = false;
	private static boolean fromShed = false;
	private static int shedNum = 16;
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
		if (gameState.getSteps()%50==49){
			gameState.areTreesDead();
			gameState.incrementSeason(gameState.getSeason()+1);
			gameGUI.setSeasonIcon(gameState.getSeason());
		}
		
		if (input.equalsIgnoreCase("quit")){
			gameOutput(area, "Goodbye!");
			return false;
		}
		
		if(gameState.winState()){
			panel.showEndCondition(gameState.winState());
			gameOutput(area, "You win!");
			return false;
		}		
		
		if(gameState.getDeadTrees()>12){
			panel.showEndCondition(gameState.winState());
			gameOutput(area, "15% of your orchard has died. \n You cannot sustain the Orchard. \n You have to sell the Orchard. You lose.");
			return false;
		}
		
		int commandType = Input.isCommand(input);
		
		String otherWords = Input.getOtherWords(input);
		String allWords = Input.getAllWords(input);
		Point p = gameState.getPosition();
		gameOutput(area, ">>" + allWords);
		if (commandType == 0){//for START
			if (panel.getIntroState()==0){
				gameGUI.typeNext();
			}else if (!panel.isInGame()){
				gameOutput(area, "You can only type start once. \n"
						+ "Please type next or press next to continue.");
			}else{
				gameOutput(area, "You are in the game already.");
			}
		}
		else if (commandType == 1){//for LOOK
			look(area, p, otherWords);
		}
		else if (commandType == 2){ //for WALK
			if(panel.isInGame()){
				walk(gameGUI, panel, area, otherWords);
			}else if(panel.getIntroState()==shedNum){
				walkFromShed(gameGUI, panel, area, otherWords);
			}else{
				gameOutput(area, "You cannot walk while you are reading things \n"
						+ "or looking in the Book or Guide.");
			}
		}
		else if (commandType == 3){//for HELP
			help(panel, area, gameGUI, otherWords);
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
			if (!gameState.getAlreadyEnteredOrchard()){
				if (otherWords.equalsIgnoreCase("orchard")) {
	                enter(panel, area, gameGUI);
	                gameState.setAlreadyEnteredOrchard(true);
	            } else {
	                gameOutput(area, "You cannot enter " + otherWords);
	            }	
			}else{
				gameOutput(area, "You are already in the orchard. \n you probably need to exit back to game.");
			}
		}
		else if(commandType == 6){//for EXIT
			if (gameState.getAlreadyEnteredOrchard()){
				if (!panel.isInGame() && panel.getIntroState()!=shedNum){
					exit(gameGUI, panel, area);			
				}else if(panel.getIntroState()==shedNum){
					gameOutput(area, "You cannot exit the Shed, but you can walk back \n"
							+ "to an address stored in Book.");
				}else{
					gameOutput(area, "You cannot exit the game, but you can quit it.");
				}
			}else{
				gameOutput(area, "You cannot exit to the game because you have not yet \n"
						+ "entered the orchard.");
			}
		}else if(commandType == 7){//for EQUALS
			equalsSign(gameGUI, panel, area, p, allWords);
		}else if(commandType == 8){//for GUIDE
			if(panel.getIntroState()==-1){
				gameGUI.onGuideButtonClick();
			}else{
				gameOutput(area, "You cannot look in the Guide unless you \n"
						+ "are in the game.");}
		}else if(commandType == 9){//for BOOK
			if(panel.getIntroState()==-1){
				gameGUI.onBookButtonClick();
			}else{
				gameOutput(area, "You cannot look in the Book unless you \n"
						+ "are in the game.");
			}
		}else if(commandType ==10){
			gameGUI.typeNext();
		}else if(commandType == 11){
			if(fromEquals){
				YesNo(area, allWords, gameState.getTreeFromLocation(p), panel, gameGUI, gameState.getPoisonString(gameState.getPoison()));
				fromEquals = false;
			}else if(fromShed){
				YesNo(area, allWords, gameState.getTreeFromLocation(p), panel, gameGUI, gameState.getPoisonString(gameState.getPoison()));
				fromShed = false;
			}else{
				gameOutput(area, "You can only reply Yes/No when you are \n"
						+ "answering a question.");
			}
		}else if(commandType == 12){
			gameGUI.typeBack();
		}
		
		else if (commandType == -1){
			checkTree(area, allWords);
		}
		return true;
	}
	
	private static void YesNo(JTextArea area, String otherWords, Tree thisTree, GameMainPanel panel, GameGUI gameGUI, String poisonString){
		if(fromEquals){
			if (otherWords.equalsIgnoreCase("yes") || otherWords.equalsIgnoreCase("Y")){
				gameOutput(area, "You put the "+ poisonString +" pesticide \n"
						+ "in a "+ gameState.getPoisonString(thisTree.getTreeType())+" tree. \n"
						+ "You have killed this tree.");
				thisTree.setResident(0);
				thisTree.setAlive(false);
				panel.showTree(thisTree);
				gameGUI.setPoisonIcon(0);
				gameState.changePoison(0);
			}else{
				gameOutput(area, "You decide not to put a "+ poisonString +" \n"
						+ "pesticide in a "+ gameState.getPoisonString(thisTree.getTreeType())+" tree. "
								+ "\n The tree lives.");
				fromEquals = false;
			}
		}else if(fromShed){
			if (otherWords.equalsIgnoreCase("yes") || otherWords.equalsIgnoreCase("Y")){
				panel.showShed();
				gameOutput(area, "You walk to shed. You did not write an address \n"
						+ "to get back to, so you need to use walk to get to the "
						+ "\n entrance of the orchard.");
			}else{
				gameOutput(area, "You decide not to walk to the Shed before \n"
						+ "writing an address to walk back to.");
				fromShed = false;
			}
		}
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
	
	public static void next(JTextArea area, GameMainPanel panel, GameGUI gameGUI){
		if (panel == null){
			return;
		}
		int introState = panel.getIntroState();
		if (introState == -1){
			gameOutput(area, "Error: This is not in the game.");			
		}else if(introState == panel.MAX_INTRO_STATE){
			gameState.setAlreadyEnteredOrchard(true);
			enter(panel, area, gameGUI);
		}else{
			panel.setIntroState(introState+1);
		}
	}
	
	public static void back(JTextArea area, GameMainPanel panel, GameGUI gameGUI){
		if (panel == null){
			return;
		}
		int introState = panel.getIntroState();
		if (introState == -1){
			gameOutput(area, "Error: This is not in the game.");			
		}else if(introState < 2){
			gameOutput(area, "Error: You cannot go any further back.");
		}else{
			panel.setIntroState(introState-1);
		}
	}
	
	private static void enter(GameMainPanel panel, JTextArea area, GameGUI gameGUI){
		panel.setIntroState(-1);
		panel.showTree(gameState.getTreeFromLocation(gameState.getPosition()));
		gameGUI.onEnterOrchard();
        gameOutput(area, "You enter the Orchard.\n"
        		+ "Maybe you should walk around and find the pests \n"
        		+ "before they kill trees. Remember to look at trees \n"
        		+ "to see if that is a pest or if it is just fruit.");
	}
	
	private static void help(GameMainPanel panel, JTextArea area, GameGUI gameGUI, String otherWords){
		 if(otherWords.equalsIgnoreCase("walk")){
				panel.setIntroState(10);
				gameGUI.typeHelp();
		        gameOutput(area, "You are looking at the help menu. \n"
		        		+ "Type next to move through, or exit to get back \n"
		        		+ "to the game.");
			}else if(otherWords.equalsIgnoreCase("poison")){
				panel.setIntroState(7);
				gameGUI.typeHelp();
		        gameOutput(area, "You are looking at the help menu. \n"
		        		+ "Type next to move through, or exit to get back \n"
		        		+ " to the game.");
			}else{
				panel.setIntroState(4);
				gameGUI.typeHelp();
		        gameOutput(area, "You are looking at the help menu. \n"
		        		+ "Type next to move through, or exit to get back \n"
		        		+ "to the game.");
			}
	}
	
	public static void exit(GameGUI gameGUI, GameMainPanel panel, JTextArea area){
		gameGUI.resetButtons();
		gameGUI.hideNextButton();
		gameGUI.hideBackButton();
		if(inShed && panel.getIntroState()!=shedNum){
			panel.setIntroState(shedNum);
			panel.showShed();
			gameOutput(area, "You exit back to the Shed.");
		}else{
			panel.setIntroState(-1);
			panel.showTree(gameState.getTreeFromLocation(gameState.getPosition()));
			gameOutput(area, "You exit back to the game.");
		}
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
	
	private static void equalsSign(GameGUI gameGUI, GameMainPanel panel, JTextArea area, Point p, String otherWords){
		//Command Subject = Object
		if(getSubject(otherWords).equalsIgnoreCase("addressbook")||getSubject(otherWords).equals("book") || getSubject(otherWords).equals("*book") ||getSubject(otherWords).equals("&book") ){
			//if Subject is some form of mis-spelled address book
			gameOutput(area, "You cannot use book, or addressBook because Book "
					+ "\n is the variable for the address book. \n"
					+ "Capitalization is important. "
					+ "\n Did you mean to use Book, *Book, or &Book?");		
		}else if(getSubject(otherWords).equals("*Book")){
			//putting things in book
			if (getObject(otherWords).startsWith("&") && gameState.doesTreeExist(getObject(otherWords).substring(1))){
				if(gameState.treeFromLocation(gameState.getPosition()).equalsIgnoreCase(getObject(otherWords).substring(1))){
					if(!gameState.isInBook(getObject(otherWords))){
						gameOutput(area, "You put the address of " + getObject(otherWords).substring(1) + " in the Book.");
						gameState.addToAddressBook(getObject(otherWords));
					}else{
						gameOutput(area, "Error: You already wrote the address in the Book.");
					}
				}else{
					gameOutput(area, "Error: That tree is not visible. \n "
							+ "You tried to put the address of a tree from another \n"
							+ "location in the Book.");
				}
			}else{
				gameOutput(area, "Error: You cannot put that in the \n"
						+ "Book, because " + getObject(otherWords)
						+ "\n is not an address. "
						+ "\n Did you mis-spell the name of a tree?");
			}
		}else if(getSubject(otherWords).equals("Book")){
			if (getObject(otherWords).startsWith("&")){
				gameOutput(area, "Error: You cannot replace the Book \n"
						+ "with an address.");
			}else if (getObject(otherWords).startsWith("*")){
				if(gameState.doesTreeExist(getObject(otherWords).substring(1)) && gameState.getTree(getObject(otherWords).substring(1)).getResident()!=0){
					gameOutput(area, "Error: You cannot replace the Book \n"
							+ "with the contents of a tree.");					
				}else{
					gameOutput(area, "Error: You cannot replace the Book \n"
							+ "with the contents of a tree. \n "
							+ "Furthermore, this tree is empty.");
				}
			}else{
				gameOutput(area, "Error: You cannot replace the Book \n"
						+ "with an object");
			}
		}else if(getSubject(otherWords).equals("&Book")){
			if (getObject(otherWords).startsWith("&")){
				gameOutput(area, "Error: You cannot trade the address of Book"
						+ "\n with the address of an object.");
			}else{
				gameOutput(area, "Error: You cannot replace the address of Book "
						+ "\n with an object");
			}
		}else if(getSubject(otherWords).contains("fieldguide")){
			gameOutput(area, "Error: You cannot use guide, or fieldGuide "
					+ "\n because Guide is the variable for the "
					+ "\n field guide. Capitalization is important."
					+ "\n Did you mean to use Guide?");
		}else if(getSubject(otherWords).equals("*Poison")){
			//Subject is Poison
			if(panel.getIntroState()==shedNum){
				inShed = true;
				if(getObject(otherWords).startsWith("*")){
					if(getObject(otherWords).substring(1).equals("ApplePoison")){
						gameState.changePoison(1);
						gameGUI.setPoisonIcon(1);
						gameOutput(area, "You put the Apple pesticide in the container");
					}else if(getObject(otherWords).substring(1).equals("OrangePoison")){
						gameState.changePoison(2);
						gameGUI.setPoisonIcon(2);
						gameOutput(area, "You put the Orange pesticide in the container");
					}else if(getObject(otherWords).substring(1).equals("CherryPoison")){
						gameState.changePoison(3);
						gameGUI.setPoisonIcon(3);
						gameOutput(area, "You put the Cherry pesticide in the container");
					}else if(getObject(otherWords).substring(1).equals("NutPoison")){
						gameState.changePoison(4);
						gameGUI.setPoisonIcon(4);
						gameOutput(area, "You put the Nut pesticide in the container");
					}else if(getObject(otherWords).substring(1).equals("LemonPoison")){
						gameState.changePoison(5);
						gameGUI.setPoisonIcon(5);
						gameOutput(area, "You put the Lemon pesticide in the container");
					}else if(getObject(otherWords).substring(1).equals("LimePoison")){
						gameState.changePoison(6);
						gameGUI.setPoisonIcon(6);
						gameOutput(area, "You put the Lime pesticide in the container.");
					}else if(getObject(otherWords).startsWith("&")){
						gameOutput(area, "Error: You cannot put an address in \n"
								+ "the poison container.");
					}else{
						gameOutput(area, "Error: You have mis-spelled the pesticide \n"
								+ "container. Did you type the name \n"
								+ "like *NutPoison to get the contents \n"
								+ "of the container?");
					}
				}else if(getObject(otherWords).startsWith("&")){
					gameOutput(area, "Error: You cannot put the address of \n"
							+ "a container of pesticide in the \n"
							+ "poison container. Did you try the \n"
							+ "name of the container with an \n"
							+ "asterisk like: *LimePoison?");
				}else{
					gameOutput(area, "Error: You cannot put a whole \n"
							+ "container of pesticide in the poison \n"
							+ "container. Did you try the name of the \n"
							+ "container with an asterisk like: *NutPoison?");
				}
			}else{
				gameOutput(area, "You are not in the shed.");
			}
		}
		else if(gameState.doesTreeExist(getSubject(otherWords))){
			//Subject is tree 
			if(gameState.doesTreeExist(getObject(otherWords))){
				gameOutput(area, "Error: You cannot replace a tree with \n"
						+ "another tree.");				
			}else if(getObject(otherWords).startsWith("&") && gameState.doesTreeExist(getObject(otherWords))){
				gameOutput(area, "Error: You cannot replace a tree with \n"
						+ "the address of another tree");				
			}else if(getObject(otherWords).startsWith("*") && gameState.doesTreeExist(getObject(otherWords))){
				gameOutput(area, "Error: You cannot replace a tree \n"
						+ "with the contents of another tree");				
			}else{
				if(gameState.getTree(getObject(otherWords).substring(1)).getResident()!=0){
					gameOutput(area, "Error: You cannot replace the Book \n"
							+ "with the contents of a tree.");					
				}else{
					gameOutput(area, "Error: You cannot replace the Book \n"
							+ "with the contents of a tree. \n "
							+ "Furthermore, this tree is empty.");
				}
				gameOutput(area, "Error: You cannot replace a tree \n"
						+ "with " + getObject(otherWords) + ". \n "
						+ "Did you mis-spell something, not capitalize \n"
						+ "properly, or add extra spaces?");
			}
		}
		else if(getSubject(otherWords).startsWith("&") && gameState.doesTreeExist(getSubject(otherWords).substring(1))){
			//Subject is &Tree 
			if(gameState.doesTreeExist(getObject(otherWords))){
				gameOutput(area, "Error: You cannot replace the address \n"
						+ "of a tree with another tree");				
			}else if(getObject(otherWords).startsWith("&") && gameState.doesTreeExist(getObject(otherWords))){
				gameOutput(area, "Error: You cannot replace the address \n"
						+ "of a tree with the address of another tree");				
			}else if(getObject(otherWords).startsWith("*") && gameState.doesTreeExist(getObject(otherWords))){
				gameOutput(area, "Error: You cannot replace the address \n"
						+ "of a tree with the contents of another tree");				
			}else{
				gameOutput(area, "Error: You cannot replace the address \n"
						+ "of a tree with " + getObject(otherWords) + ". \n "
						+ "Did you mis-spell something, not capitalize \n"
						+ "properly, or add extra spaces?");
			}
		}
		else if(getSubject(otherWords).startsWith("*") && gameState.doesTreeExist(getSubject(otherWords).substring(1))){
			//if the Subject is *Tree
			if (getObject(otherWords).equals("*Poison")){
				int poison = gameState.getPoison();
				Tree thisTree = gameState.getTree(getSubject(otherWords).substring(1));
				int thisResident = thisTree.getResident();
				String poisonString = gameState.getPoisonString(poison);
				if(poison == thisTree.getTreeType()){
					if(thisResident==0){
						gameOutput(area, "You put the "+ poisonString + " pesticide \n"
								+ "in the tree, it does nothing because \n"
								+ "nothing is in the tree. You have \n"
								+ "wasted your one shot of poison spray. \n"
								+ "remember to save this address before \n"
								+ "walking to the Shed for a refill.");
						gameGUI.setPoisonIcon(0);
						gameState.changePoison(0);
					}else if (poison == 0){
						gameOutput(area, "You do not have any pesticide in \n"
								+ "your container. Perhaps you should \n"
								+ "save this address and get more \n"
								+ "pesticide from the Shed.");
					}else{
						gameOutput(area, "You put the "+ poisonString +" pesticide \n"
								+ "in the tree, killing the "+ thisTree.getResidentString() +".");
						thisTree.setResident(0);
						panel.showTree(thisTree);
						gameGUI.setPoisonIcon(0);
						gameState.changePoison(0);
					}
				}else{
					if (poison==0){
						gameOutput(area, "You cannot spray pesticide from an \n"
								+ " empty container. Save your address \n"
								+ "in Book and then go to the Shed for \n"
								+ "more pesticide.");
					}else{
						gameOutput(area, "You are about to put a "+ poisonString +"\n"
								+ " poison in a "+ gameState.getPoisonString(thisTree.getTreeType())+" tree. "
								+ "\n You might kill this tree if you \n"
								+ "do not use the tree-safe poison. \n"
								+ "Do you still want to spray this tree \n"
								+ "with the poison?");
						fromEquals = true;
					}
				}	
			}else{
				gameOutput(area, "You attempt to put pesticide in " + getSubject(otherWords) + " \n"
						+ "but you probably messed up capitalization, \n"
						+ "forgot there are no spaces in tree names, \n "
						+ "or spelled something incorrectly.");
			}
		}else if(getSubject(otherWords).startsWith("*") && !getSubject(otherWords).substring(1).equalsIgnoreCase("poison")){
			//poison is subject
			if(getObject(otherWords).startsWith("*") && !getObject(otherWords).substring(1).equalsIgnoreCase("poison")){
				gameOutput(area, "You cannot move unknown into another tree.");
			}else if(getObject(otherWords).startsWith("&")){
				gameOutput(area, "You cannot dig up an unknown to put it \n"
						+ "at this tree's location.");
			}else{
				gameOutput(area, "You cannot plant a new "+ otherWords+ " \n"
						+ "to replace this tree.");	
			}
		}else if(getSubject(otherWords).startsWith("&") && !getSubject(otherWords).substring(1).equalsIgnoreCase("poison") && gameState.doesTreeExist(getSubject(otherWords).substring(1))){
			if(getObject(otherWords).startsWith("*")){
				gameOutput(area, "You cannot move pests or pollinators to an \n"
						+ " address.");
			}else if(getObject(otherWords).startsWith("&")){
				gameOutput(area, "You cannot dig up a tree to put another here.");
			}else{
				gameOutput(area, "You cannot plant a new tree of type "+ otherWords+ " here.");	
			}
		}else if(gameState.doesTreeExist(getSubject(otherWords))&& !getObject(otherWords).substring(1).equalsIgnoreCase("poison")){
			if(getObject(otherWords).startsWith("*")){
				gameOutput(area, "You cannot replace a tree with pests or pollinators.");
			}else if(getObject(otherWords).startsWith("&")){
				gameOutput(area, "You cannot dig up this tree and move \n"
						+ "it to a new address.");
			}else{
				gameOutput(area, "You cannot plant a new tree of type "+ otherWords+ " here.");	
			}
		}else{
			checkTree(area, otherWords);
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
			}else if(gameState.getPoison()==0){
				gameOutput(area, "You see the empty Poison container");
			}
		}
		else if(otherWords.startsWith("*") && gameState.doesTreeExist(otherWords.substring(1))){
			if (gameState.getPosition().equals(gameState.getTree(otherWords.substring(1)).getLocation())){
				Tree localTree = gameState.getTree(getSubject(otherWords).substring(1));
				String thisTreeType = "";
				if(localTree.getTreeType()==1){
					thisTreeType = "Apple";
				}else if(localTree.getTreeType()==2){
					thisTreeType = "Orange";
				}else if(localTree.getTreeType()==3){
					thisTreeType = "Cherry";
				}else if(localTree.getTreeType()==4){
					thisTreeType = "Nut";
				}else if(localTree.getTreeType()==5){
					thisTreeType = "Lemon";
				}else if(localTree.getTreeType()==6){
					thisTreeType = "Lime";
				}
				
				if (localTree.getTreeType()==0){
					int numDeadTrees = gameState.getDeadTrees();
					gameState.setDeadTrees(numDeadTrees + 1);
					gameOutput(area, "The "+ thisTreeType +" tree is dead. \n"
							+ "You see it is empty. Pests must have killed it.");
				}else{
					if(localTree.getResident() == 0){
						gameOutput(area, "You see the "+ thisTreeType +" tree is empty.");
					}else if(localTree.getResident()==1){
						gameOutput(area, "You see the "+ thisTreeType +" tree contains ants. \n"
								+ "Use the Guide to figure out if it is a \n"
								+ "pest that destroys or pollinator that protects \n"
								+ "the tree. Also, remember to store the address \n"
								+ "of the tree in Book.");
					}else if(localTree.getResident()==2){
						gameOutput(area, "You see the "+ thisTreeType +" tree contains wasps. \n"
								+ "Use the Guide to figure out if it is a pest \n"
								+ "that destroys or pollinator that protects \n"
								+ "the tree. Also, remember to store the address of \n"
								+ "the tree in Book.");
					}else if(localTree.getResident()==3){
						gameOutput(area, "You see the "+ thisTreeType +" tree contains gophers. \n"
								+ "Use the Guide to figure out if it is a pest \n"
								+ "that destroys or pollinator that protects the tree. "
								+ "\n Also, remember to store the address of the tree in Book.");
					}else if(localTree.getResident()==4){
						gameOutput(area, "You see the "+ thisTreeType +" tree contains aphids. \n"
								+ "Use the Guide to figure out if it is a pest \n"
								+ "that destroys or pollinator that protects the tree. "
								+ "\n Also, remember to store the address of the tree in Book.");
					}else if(localTree.getResident()==5){
						gameOutput(area, "You see the "+ thisTreeType +" tree contains butterflies. \n"
								+ "Use the Guide to figure out if it is a pest \n"
								+ "that destroys or pollinator that protects the tree. "
								+ "\n Also, remember to store the address of the tree in Book.");
					}else if(localTree.getResident()==6){
						gameOutput(area, "You see the "+ thisTreeType +" tree contains bees. \n"
								+ "Use the Guide to figure out if it is a pest \n"
								+ "that destroys or pollinator that protects the tree. "
								+ "\n Also, remember to store the address of the tree in Book.");
					}else if(localTree.getResident()==7){
						gameOutput(area, "You see the "+ thisTreeType +" tree contains frogs. \n"
								+ "Use the Guide to figure out if it is a pest \n"
								+ "that destroys or pollinator that protects the tree. "
								+ "\n Also, remember to store the address of the tree in Book.");
					}else if(localTree.getResident()==8){
						gameOutput(area, "You see the "+ thisTreeType +" tree contains ladybugs. \n"
								+ "Use the Guide to figure out if it is a pest \n"
								+ "that destroys or pollinator that protects the tree. "
								+ "\n Also, remember to store the address of the tree in Book.");
					}
				}
			}else{
				if(gameState.getPosition()!=gameState.getTree(otherWords.substring(1)).getLocation()){
					gameOutput(area, "Error: You cannot look at a tree \n"
							+ "that is not in the location.");
				}else{
					gameOutput(area, "Error: that is not the name of a tree \n"
							+ "in this game. If you think it exists, \n"
							+ "it may be spelled incorrectly, have extra spaces, \n"
							+ "or have incorrect capitalization");
				}
			}
		}else if(otherWords.startsWith("&")){
			gameOutput(area, "Error: you cannot look at addresses.");
		}else{
			gameOutput(area, "Error: " +otherWords + " is neither a Tree,\n"
					+ " nor is it an object in game. \n "
					+ "If you think it exists, it may be spelled incorrectly,\n"
					+ " have extra spaces, or have incorrect capitalization.");
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
	
	private static void walkFromShed(GameGUI gameGUI, GameMainPanel panel, JTextArea area, String otherWords){
		if (otherWords.startsWith("&")){
			if (gameState.doesTreeExist(otherWords.substring(1)) && gameState.getAddressBook().contains(otherWords)){
				//bound with address book
				panel.showTree(gameState.getTree(otherWords.substring(1)));
				gameOutput(area, "You walk to " + otherWords.substring(1) + ".");
				gameState.moveToPoint(gameState.getTree(otherWords.substring(1)).getLocation());
				exit(gameGUI, panel, area);
			}else{
				gameOutput(area, "You cannot walk to an address that \n"
						+ "is not stored in Book.");
			}
			gameState.takeSteps();
			gameGUI.refreshCounter();
		}else{
			if(otherWords.equals("")){
				panel.setIntroState(-1);
				walk(gameGUI, panel, area, "&Apple");
				gameOutput(area, "You walk out of the Shed to the Apple tree.");
			}else{
				gameOutput(area, "You cannot walk there. \n"
						+ "You need an address to walk to. \n"
						+ "Hope you saved an address in the Book.");
			}
		}
		inShed = false;
	}
	
	private static void checkTree(JTextArea area, String otherWords){
		if (gameState.doesTreeExist(otherWords)){
			gameOutput(area, "You need to use look *"+otherWords+" if you want to see the content of the tree. \n"
					+ "It is unclear what you are trying to do.");
		}else if (otherWords.startsWith("&") && gameState.doesTreeExist(otherWords.substring(1))){
			gameOutput(area, otherWords+" should not be used alone. Did you mean *Book = "+otherWords+"?");
		}else if (otherWords.startsWith("*") && gameState.doesTreeExist(otherWords.substring(1))){
			gameOutput(area, otherWords+" should not be used alone. Did you mean look "+otherWords+"?");
		}else{
			gameOutput(area, "That is not the poison container, a tree, \n"
					+ "or the address book. Did you mis-spell something, \n"
					+ "capitalize incorrectly, or add extra space? \n"
					+ "Or did you mean to use turn, look, exit, quit, \n"
					+ "help, or walk? \n"
					+ "Also remember that the = sign needs a space in \n"
					+ "front and a space behind it.");
		}
	}
	
	private static void walk(GameGUI gameGUI, GameMainPanel panel, JTextArea area, String otherWords){
		if (gameState.getSteps()%20==19){
			gameState.insertRandomPests(1);
		}
		if(gameState.getSteps()%40==39){
			gameState.insertRandomPollinator();
		}

			if(otherWords.equals("&Apple")){
				gameState.takeSteps();
				gameGUI.refreshCounter();
				gameState.moveToPoint(new Point(0,0));
				panel.showTree(gameState.getTree(otherWords.substring(1)));
				return;
			}
			
			if (otherWords.startsWith("&")){
				if (gameState.doesTreeExist(otherWords.substring(1)) && gameState.getAddressBook().contains(otherWords.substring(1))){
					//bound with address book
					inShed = false;
					panel.showTree(gameState.getTree(otherWords.substring(1)));
					gameOutput(area, "You walk to " + otherWords.substring(1) + ".");
					gameState.moveToPoint(gameState.getTree(otherWords.substring(1)).getLocation());
					gameState.takeSteps();
					gameGUI.refreshCounter();
					return;
				}else if (otherWords.substring(1).equals("Shed")){
					inShed = true;
					if (gameState.getAddressBook().size()>2){
						panel.showShed();
						gameOutput(area, "You should probably use * to move the\n"
								+ " poisons around. Like *Poison = *ApplePoison");
					}else{
						gameOutput(area, "Are you sure you want to walk to the \n"
								+ "shed without any addresses stored in the book?");
						fromShed = true;
					}
					gameState.takeSteps();
					gameGUI.refreshCounter();
					return;
				}else if (!gameState.doesTreeExist(otherWords.substring(1))){
					gameOutput(area, "Error: Something is mis-spelled, \n"
							+ "not capitalized, or not spaced correctly.");
					return;
				}
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
				gameOutput(area, "You have reached the border of the Orchard. \n"
						+ "You cannot continue to walk in this direction.");
			}
		panel.showTree(gameState.getTreeFromLocation(gameState.getPosition()));		
	}
}

