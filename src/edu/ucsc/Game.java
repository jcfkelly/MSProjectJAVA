package edu.ucsc;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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
			keepLooping = gameLoop(input, null);
		}
	}
	public static void gameOutput(JTextArea area, String output){//console and GUI
		if (area == null){
			System.out.println(output);
		}else{
			area.append(output + "\n");
		}
	}
	
	public static boolean gameLoop(String input, JTextArea area){
		
		if (input.equalsIgnoreCase("exit")){
			gameOutput(area, "Goodbye!");
			return false;
		}
		if(gameState.winState()){
			gameOutput(area, "You win!");
			return false;
		}
		//System.out.println(Input.isCommand(input));
		int commandType = Input.isCommand(input);
		
		//System.out.print(input); //for debugging
		String otherWords = Input.getOtherWords(input);
		Point p = gameState.getPosition();
		if (commandType == 0){//for PUT
			put(area, p, otherWords);
		
		}
		else if (commandType == 1){//for LOOK
			look(area, p, otherWords);
			
		}
		else if (commandType == 2){ //for WALK		
			walk(area, p, otherWords);
		}
		else if (commandType == 3){//for RENAME
			rename(area, p, otherWords);
		}
		else if (commandType == -1){
			gameOutput(area, "That is not an option. Did you mean to use put, look, rename, or walk?");
		}
		return true;
	}
	
	private static void rename(JTextArea area, Point p, String otherWords) {
		if (otherWords.equalsIgnoreCase("") && !otherWords.contains(",")){
			gameOutput(area, "You did not say: rename OLDNAME, NEWNAME.");
		}else if(!otherWords.equals("")){
			//System.out.println(otherWords.indexOf(","));
			if(otherWords.contains(",")){
				int indexOfComma = otherWords.indexOf(",");
				int indexOfSpace = otherWords.indexOf(" ");
				String oldName = otherWords.substring(0, indexOfComma);
				String newName;
				//System.out.println(otherWords.substring(indexOfComma + 2));
				if(indexOfSpace == -1){
					newName = otherWords.substring(indexOfComma+1);	
				}else{
					newName = otherWords.substring(indexOfComma+2);
				}
				if(gameState.doesTreeExist(oldName) && !gameState.doesTreeExist(newName)){
					//needs to store in gameState that the oldName is replaced with the newName
					gameOutput(area, "You rename " + oldName + " with " + newName + ".");
					gameState.renameTree(oldName, newName);
				}else if(gameState.doesTreeExist(newName)){
					gameOutput(area, "You try to rename " + oldName + " with " + newName + ", but " +newName+" already exists. Please choose another name for " +oldName+".");
				}else{
					gameOutput(area, "You attempt to rename something that does not exist.");
				}
				gameState.renameTree(oldName, newName);
			}else{
				gameOutput(area, "You need the format: rename OLDNAME, NEWNAME. Maybe you forgot the comma.");
			}
		}
		
	}
	private static void put(JTextArea area, Point p, String otherWords) {
		String object = Input.getObject(otherWords);
		String subject = Input.getSubject(otherWords);
		/*Needs cases:
		 If not labeled, then no object with that name found.
		 If uses put Put &Tree2 in BirdBook[1], puts the address of Tree2 on page 1 of BirdBook. 
		 If Put Nest1 in BirdBook[1], You can’t put a nest in the book.
		 If Put &Nest1 in BirdBook[1], You have written the location of that nest on book page 1.
		 If Put *Tree1 in Cage[1], Sorry can’t put whole nests in a cage, just birds.
		*/
		if (object.equals("Sanctuary")){
			if (subject.equals("Cage") && p == new Point(0,0)){
				if (gameState.birdsInCage() == 0){
					gameOutput(area, "You realize there is nothing in the Cage to empty anywhere.");
				}
				gameOutput(area, "You empty the " + gameState.birdsInCage() + " birds from the Cage into the Sanctuary.");
				gameState.putBirdsInSanctuary();
			}else{
				gameOutput(area, "You cannot empty the birds from the Cage into the Sanctuary because you are not near the Sanctuary.");
			}
		}
		else if (object.equals("Cage")){				
			//Need to put cases: if tree is object, then what case of tree works. If nest is object what works
			//NOTE: remember that you must be either in line of sight for putting things in cage.
			//Also, if birds are already in cage... overwrite with new birds 
			if(subject.startsWith("Tree") && gameState.doesTreeExist(subject) && gameState.getTree(subject).getLocation().equals(p)){
				gameOutput(area, "You cannot put trees in the Cage.");	
			}else if (subject.startsWith("&Tree") && gameState.doesTreeExist(subject.substring(1))){
				gameOutput(area, "You cannot put an address in the Cage.");
			}else if (subject.startsWith("*Tree") && gameState.doesTreeExist(subject.substring(1))){
				gameOutput(area, "You cannot put nests in the Cage, only the contents of the nest in a Cage. Maybe you need to use another token, like **Tree.");
			}else if (subject.startsWith("**Tree") && gameState.doesTreeExist(subject.substring(2)) && !gameState.getTree(subject.substring(2)).getLocation().equals(p)){
				gameOutput(area, "You must be in the same location as the Tree to put the birds in Cage.");
			}
			else if (subject.startsWith("**Tree") && gameState.doesTreeExist(subject.substring(2)) && gameState.getTree(subject.substring(2)).getLocation().equals(p)){
				//needs to also empty the nests and put contents of ALL nests in the tree in the Cage
				gameOutput(area, "You put all the birds from "+ subject.substring(2) + "in the Cage.");
				gameState.putBirdsInCageFromTree(subject.substring(2));
			}else if(subject.startsWith("Nest") && gameState.doesNestExist(subject)){
				//this looks at nest and returns that you cannot put nests in cages. 
				gameOutput(area, "You cannot put a nest in the Cage.");
			}else if(subject.startsWith("&Nest")&& gameState.doesNestExist(subject.substring(1))){
				gameOutput(area, "You cannot put an address in the Cage.");
			}else if(subject.startsWith("*Nest")&& gameState.doesNestExist(subject.substring(1)) && gameState.getNestLocation(subject.substring(1)).equals(p)){
				//needs to also empty the nests
				gameOutput(area, "You put all the birds from " + subject.substring(1) + " in the Cage.");
				gameState.putBirdsInCageFromNest(subject.substring(1));
			}else if(subject.startsWith("*Nest")&& gameState.doesNestExist(subject.substring(1)) && !gameState.getNestLocation(subject.substring(1)).equals(p)){
				gameOutput(area, "You cannot put a nest in the Cage if you are not in the same place as the Nest.");
			}else if (subject.equalsIgnoreCase("bird") || subject.equalsIgnoreCase("&bird") || subject.equalsIgnoreCase("*bird")){
				gameOutput(area, "You cannot put only one bird in the Cage, as you cannot pluck a bird out of thin air. Maybe you should try using the & and * tokens on things that exist.");
			}else if (subject.equalsIgnoreCase("birds") || subject.equalsIgnoreCase("&birds") || subject.equalsIgnoreCase("*birds")){
				gameOutput(area, "You cannot put birds in the Cage, as birds is not the name of an object. Use your & and * tokens on things that exist.");
			}else{
				gameOutput(area, "You attempt to put something that does not exist in the Cage. Remember, only birds can go in cages by means of manipulating Nests and Trees and the tokens * and &.");
			}
		}else if (object.startsWith("BirdBook") && subject.startsWith("&Tree") && gameState.doesTreeExist(subject.substring(1))){
			int lBracket = otherWords.indexOf("[");
			int rBracket = otherWords.indexOf("]");
			int page = -1;
			if (lBracket > -1 && rBracket >-1){
				try {
					page = Integer.parseInt(otherWords.substring(lBracket+1, rBracket));
					gameOutput(area, "You put the address of " + subject.substring(1) + " in " + object + ".");
					gameState.putInBirdBook(page, subject.substring(1)); //gets the integer between lB and rB, then puts tree call in second half. 
				}catch (NumberFormatException e){
					//don't do anything?
					gameOutput(area, "Do not enter non-integers between brackets.");
				}
			}
		}
		else if (object.startsWith("BirdBook") && subject.startsWith("&Nest")){
			gameOutput(area, "You put the address of "+ subject.substring(1) +" in " +object+ ".");
		}
		else if (otherWords.equalsIgnoreCase("")){
			gameOutput(area, "You did not elaborate on what you wished to put where.");
		} 
		else if (!object.equals("Cage")|| !object.startsWith("BirdBook") || !object.equals("Sanctuary")){
			//object must be Cage, BirdBook or Sanctuary
			gameOutput(area, "You cannot put "+subject + " in " + object + ".");
		}else{
			gameOutput(area, "You did not say where to put " + otherWords + ".");
		}
		
	}
	private static void look(JTextArea area, Point p, String otherWords) {
		/*
		  NOTE: I need to fix this so that if the tree is renamed it looks up the new "rename" :( 
		  */
		//String i = Input.getAfterIn(input);
		if (otherWords.equalsIgnoreCase("")){
			//Set of conditionals that do description of location
			if(p.equals(new Point(0,0))){
				gameOutput(area, "You see the Bird Sanctuary.");
			}else{
				if(gameState.getTreesAtLocationString(p).equals("")){
					//If there are no trees at the location
					if(gameState.isClearing(p)){
						gameOutput(area, "You see a clearing. This area has no trees.");
					}else{
						gameOutput(area, "You cannot walk there, there is a lake in the way.");
					}
				}else{
					List<String> listOfTrees =  gameState.getTreesAtLocation(p);
					StringBuilder sb = new StringBuilder("You see ");
					String prefix = "";
					//plain strings are inefficient for adding
					for(String treeName: listOfTrees){
						Tree thisTree = gameState.getTree(treeName);
						sb.append(prefix + treeName + " which contains " + thisTree.getNumNests() + " nests: " + gameState.getNestsAtTree(thisTree));
						prefix = ", ";
					}
					gameOutput(area, sb.toString());
				}
			}
		}
		//This happens if you rename a tree with things: 
		else if (gameState.doesTreeExist(otherWords) && gameState.getTree(otherWords).getLocation().equals(p)){
			Tree thisTree = gameState.getTree(otherWords);
			if(thisTree != null && thisTree.getLocation().equals(p)){
				gameOutput(area, "You see " + otherWords + " which contains " + thisTree.getNumNests() + " nests: " + gameState.getNestsAtTree(thisTree));
			}
		}
		
		//TREE set in LOOK
		else if(otherWords.startsWith("Tree")){
			Tree thisTree = gameState.getTree(otherWords);
			if(thisTree != null && thisTree.getLocation().equals(p)){
				gameOutput(area, "You see " + otherWords + " which contains " + thisTree.getNumNests() + " nests: " + gameState.getNestsAtTree(thisTree));
			}else{
				gameOutput(area, "You are not in the Tree's location, so you cannot see that tree.");
			}
			
		}
		else if(otherWords.startsWith("&Tree")){
			Tree thisTree = gameState.getTree(otherWords.substring(1));
			//remember to code in whether this address exists in BirdBook...
			if(thisTree !=null && thisTree.getLocation().equals(p) && gameState.findAddressInBirdBook(otherWords.substring(1))!=-1){
				//If the tree exists, and the tree is within sight AND the address is in birdbook, write out to AREA
				gameOutput(area, "You see you wrote address of " + otherWords.substring(1) + " in BirdBook");
			}else{
				gameOutput(area, "The location of " + otherWords.substring(1) + " has not been recorded in BirdBook.");
			}
		}else if(otherWords.startsWith("*Tree")){
			Tree thisTree = gameState.getTree(otherWords.substring(1));
			if(thisTree!=null && thisTree.getLocation().equals(p)){
				//if the tree exists and the tree is in sight
				if (gameState.nestsInTree(otherWords.substring(1)).size() == 0){//not size? 
					gameOutput(area, "You see " + otherWords + " containing 0 birds");
				}else{
					gameOutput(area, "You see " + otherWords + " containing "+ gameState.nestsInTree(otherWords.substring(1)) + " birds");
				}
			}else{
				gameOutput(area, otherWords + " is not in this location. You do not know how many birds exist in that tree as a result.");
			}
		}
		//NEST set in LOOK
		else if(otherWords.startsWith("Nest")){
			//if Tree is recorded in BirdBook, then nest is. If Nest is not, then do not return. 
			if(gameState.doesNestExist(otherWords)){
				gameOutput(area, "You see " + otherWords + ", which contains " + gameState.getNest(otherWords).getBird() + " birds.");
			}else{
				gameOutput(area, "You cannot see " + otherWords + " because it is not at your location.");
			}
		}else if(otherWords.startsWith("&Nest")){
			if(gameState.doesNestExist(otherWords.substring(1)) && gameState.getNestLocation(otherWords.substring(1)).equals(p)){
				gameOutput(area, "You see you wrote address of " + otherWords.substring(1) + " in BirdBook");
			}else{
				gameOutput(area, "The location of " + otherWords.substring(1) + " has not been recorded.");
			}			
		}else if(otherWords.startsWith("*Nest")){
			if(gameState.doesNestExist(otherWords.substring(1))){
				gameOutput(area, "You cannot see " + otherWords.substring(1)+ " in BirdBook because it is not an address. BirdBook only stores addresses.");
			}else{
				gameOutput(area, otherWords.substring(1) + " is not visible from this location.");
			}
		}
		
		//SANCTUARY set in LOOK
		else if(otherWords.equalsIgnoreCase("sanctuary")){
			if (gameState.getPosition().equals(new Point(0,0))){
				gameOutput(area, "You see the Bird Sanctuary.");
			}else{
				gameOutput(area, "You look for the Sanctuary, but you are not in the area with the Sanctuary.");
			}
		}
		else if(otherWords.equalsIgnoreCase("&sanctuary")){
			if(gameState.findAddressInBirdBook(otherWords.substring(1)) !=-1){
				gameOutput(area, "You see the address for the Sanctuary is written in the Bird Book on page 1.");
			}else{
				gameOutput(area, "You wrote something new over the address of the Sanctuary. You no longer know where it is.");
			}
		}
		else if(otherWords.equalsIgnoreCase("& sanctuary")){
			gameOutput(area, "Did you mean to type &sanctuary for the address?");
		}
		else if(otherWords.equalsIgnoreCase("*sanctuary")){
			if (gameState.getPosition().equals(new Point(0,0))){
				gameOutput(area, "You see "+ gameState.birdsInSanctuary() +" birds in the sanctuary.");
			}else{
				gameOutput(area, "You look for the birds in the Sanctuary, but you are not near the Sanctuary, and you are not psychic.");
			}

		}
		else if(otherWords.equalsIgnoreCase("* sanctuary")){
			gameOutput(area, "Did you mean to type *sanctuary for the number of birds in sanctuary?");
		}

		//BIRDBOOK set in LOOK
		else if (otherWords.startsWith("birdBook")|| otherWords.startsWith("birdBook") || otherWords.startsWith("birdbook")){
			gameOutput(area, "Did you mean to look at 'BirdBook'? ");
		}
		else if (otherWords.startsWith("&birdBook")|| otherWords.startsWith("&birdBook") || otherWords.startsWith("&birdbook")){
			gameOutput(area, "Did you mean to look at '&BirdBook'? ");
		}
		else if (otherWords.startsWith("*birdBook")|| otherWords.startsWith("*birdBook") || otherWords.startsWith("*birdbook")){
			gameOutput(area, "Did you mean to look at '*BirdBook'? ");
		}
		else if(otherWords.startsWith("BirdBook")){
			//System.out.println(otherWords.substring(0,rBracket));
			if(otherWords.equals("BirdBook")){
				gameOutput(area, "You look at the bird book. You see the cover says 'BirdBook'. Seems like the spacing is important.");
			}else{
				int lBracket = otherWords.indexOf("[");
				int rBracket = otherWords.indexOf("]");
				int page = -1;
				if (lBracket > -1 && rBracket >-1){
					try {
						page = Integer.parseInt(otherWords.substring(lBracket+1, rBracket));							
					}catch (NumberFormatException e){
						//don't do anything?
						gameOutput(area, "Do not enter non-integers between brackets.");
					}
				}else{
					gameOutput(area, "You did not format BirdBook like an array. You need to put something like: BirdBook[2].");
				}
				if (page > -1){
					if (gameState.doesBirdbookExist(page)){
						//This should have BirdBook[1]... BirdBook[300] 
						gameOutput(area, "You look at page " + page + ". You see you have written an address on page " + gameState.readFromBirdBook(page) + ".");
					}else{
						gameOutput(area, "You try to look at " + otherWords + ", but you didn't write anything in birdbook on that page.");
					}	
				}
			}
		}
		else if(otherWords.startsWith("&BirdBook")){
			int lBracket = otherWords.indexOf("[");
			int rBracket = otherWords.indexOf("]");
			if(otherWords.equals("&BirdBook")){
				if (lBracket > -1 && rBracket >-1){
					gameOutput(area, "You look at address of " + otherWords.substring(0, rBracket+1) + " , but it is a page in BirdBook, and therefore does not have an independent address from BirdBook.");	
				}else{
					gameOutput(area, "You attempt to look at address of birdbook, which is in your pocket. That was silly.");
				}
			}else{
				gameOutput(area, "You try to look at address of " + otherWords + ", but that is not a page in BirdBook.");
			}
			
		}
		else if(otherWords.equalsIgnoreCase("*BirdBook")){
			gameOutput(area, "You look at content of birdbook, it contains all the addresses you put in birdbook.");
		}
		else if(otherWords.equalsIgnoreCase("inventory")){
			gameOutput(area, "You see you have a Cage that currently holds " + gameState.birdsInCage() + " birds and a BirdBook that holds "+ gameState.howManyAddresses()+" addresses.");
		}
		//CAGE set
		else if(otherWords.equalsIgnoreCase("birdcage") || otherWords.equals("cage") || otherWords.equals("&cage") ||otherWords.equals("*cage")){
			gameOutput(area, "You may be looking for specific object 'Cage', '&Cage', or '*Cage'.");
		}
		else if(otherWords.equals("Cage")){
			gameOutput(area, "You see the Cage. It can hold birds.");
		}
		else if(otherWords.equals("&Cage")){
			gameOutput(area, "You look attempt to look at the address of Cage, which is in your hand. That was silly.");
		}
		else if(otherWords.equals("*Cage")){
			if (gameState.birdsInCage() == 0){
				gameOutput(area, "You look at content of Cage, which is empty.");
			}else{
				gameOutput(area, "You look at content of Cage, which is "+ gameState.birdsInCage() +" birds.");
			}
		}else{
			gameOutput(area, "You don't see that.");
		}
	}
	
	private static void walk(JTextArea area, Point p, String otherWords){
		if (otherWords.length() > 3 && otherWords.substring(0, 4).equals("Tree")){
			gameOutput(area, "You cannot walk to an object, but you can walk to an address. Maybe you meant to use &");
		}
		else if(otherWords.length() > 4 && otherWords.substring(0,5).equals("&Tree")){
			//System.out.println(otherWords.substring(5));
			if(otherWords.equals("&Tree")){
				gameOutput(area, "That Tree does not exist; thus, you cannot walk to that tree.");
			}
			else if(!otherWords.substring(5).equals("") && gameState.doesTreeExist(otherWords.substring(1))){
				Tree inputTree = gameState.getTree(otherWords.substring(1));
				gameState.moveToPoint(inputTree.getLocation());
				gameOutput(area, "You walk to " + otherWords.substring(1));
			}else{
				gameOutput(area, "That Tree does not exist, and therefore you cannot walk to that tree.");
			}
		}
		else if(otherWords.length() > 4 && otherWords.substring(0,5).equals("*Tree")){
			if(otherWords.equals("*Tree")){
				gameOutput(area, "That Tree does not exist; thus, you cannot find nests on a nonexistent tree.");
			}
			else if(!otherWords.substring(5).equals("") && gameState.doesTreeExist(otherWords.substring(1))){
				gameOutput(area, "You cannot walk to the address of a Nest because you cannot climb.");
			}else{
				gameOutput(area, "That Tree does not exist, and therefore points to no nests.");
			}
		}
		else if (otherWords.equalsIgnoreCase("north") || otherWords.equalsIgnoreCase("north ")){
			gameState.movePosition(0,1);
			if(gameState.isBorder(p)){ //refactored to be helper function
				gameOutput(area, "You cannot walk that way, you have reached the edge of the map.");
					gameState.movePosition(0, -1);
			}else if(gameState.isLake(p)){//refactored to be helper function
				gameOutput(area, "You cannot walk that way, it is a lake.");
					gameState.movePosition(0, -1);
				}else{
					gameOutput(area, "You move north");
					System.out.println(p);
				}
		}else if (otherWords.equalsIgnoreCase("south") || otherWords.equalsIgnoreCase("south ")){
			gameState.movePosition(0,-1);
			if(gameState.isBorder(p)){
				gameOutput(area, "You cannot walk that way, you have reached the edge of the map.");
				gameState.movePosition(0, 1);
			}else if(gameState.isLake(p)){
				gameOutput(area, "You cannot walk that way, it is a lake.");
					gameState.movePosition(0, 1);
				}else{
					gameOutput(area, "You move south");
					System.out.println(p);
				}
		}else if (otherWords.equalsIgnoreCase("east") || otherWords.equalsIgnoreCase("east ")){
			gameState.movePosition(1,0);
			if(gameState.isBorder(p)){
				gameOutput(area, "You cannot walk that way, you have reached the edge of the map.");
				
					gameState.movePosition(-1,0);
				
			}else if(gameState.isLake(p)){
				gameOutput(area, "You cannot walk that way, it is a lake.");
					gameState.movePosition(-1, 0);
				}else{							
					gameOutput(area, "You move east");
					System.out.println(p);
				}
		}else if (otherWords.equalsIgnoreCase("west") || otherWords.equalsIgnoreCase("west ")){
			gameState.movePosition(-1,0);
			if(gameState.isBorder(p)){
				gameOutput(area, "You cannot walk that way, you have reached the edge of the map.");
					gameState.movePosition(1,0);
			}else if(gameState.isLake(p)){
				gameOutput(area, "You cannot walk that way, it is a lake.");
					gameState.movePosition(1,0);
				}else{
					gameOutput(area, "You move west");
					System.out.println(p);
				}
		}else if (otherWords.equalsIgnoreCase("")){
			gameOutput(area, "You did not say which direction to go.");
		}else { 
			gameOutput(area, "That is not a direction, please choose north, south, east, or west after walk.");
			}
	}
}

