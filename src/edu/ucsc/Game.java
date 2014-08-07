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
			keepLooping = gameLoop(input, null, null);
		}
	}
	
	public static void gameOutput(JTextArea area, String output){//console and GUI
		if (area == null){
			System.out.println(output);
		}else{
			area.append(output + "\n");
		}
	}
	
	public static boolean gameLoop(String input, JTextArea area, GameMainPanel panel){
		
		if (input.equalsIgnoreCase("quit")){
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
		if (commandType == 0){//for START
			next(area, panel, p);
		}
		else if (commandType == 1){//for LOOK
			look(area, p, otherWords);
		}
		else if (commandType == 2){ //for WALK		
			walk(panel, area, p, otherWords);
		}
		else if (commandType == 3){//for RENAME
			//rename(area, p, otherWords);
		}
		else if (commandType == 4){//for TURN
			turn(area, p, otherWords);
		}
		else if (commandType == 5){//for ENTER
			
		}
		else if(commandType == 6){//for EXIT
			
		}else if(commandType == 7){//for EQUALS
			
		}
		else if (commandType == -1){
			gameOutput(area, "That is not an option. Did you mean to use put, look, next, start, exit, quit, or walk?");
		}
		return true;
	}
	
	private static void next(JTextArea area, GameMainPanel panel, Point p){
		if (panel == null){
			return;
		}
		int introState = panel.getIntroState();
		if (introState == -1){
			gameOutput(area, "Error: This is not in the game.");			
		}else if(introState == panel.MAX_INTRO_STATE){
			panel.setIntroState(-1);
			enter(panel, area, p);
		}else{
			panel.setIntroState(introState+1);
		}
	}
	
	private static void enter(GameMainPanel panel, JTextArea area, Point p){
			walk(panel, area, new Point(0,-1), "");
			panel.showTree(gameState.getTree("Apple"));
			gameOutput(area, "You enter the Orchard.");
		
	}
	
	private static void exit(Point p){
	//TODO make code here:
		//make sure that it returns to either the entrance (if at the beginning)
		//or to last place in game
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
	
	private static void equals(JTextArea area, Point p, String otherWords){
		//Command Subject = Object
		if(getSubject(otherWords).startsWith("*Book")){
			gameOutput(area, "");
		}else{
			gameOutput(area, "That is not the poison container or the address book.");
		}
	}
	
	
	/*
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
	*/
	
	private static void look(JTextArea area, Point p, String otherWords) {
		/*
		  NOTE: I need to fix this so that if the tree is renamed it looks up the new "rename" :( 
		  */
		if (otherWords.equalsIgnoreCase("")){
			//Set of conditionals that do description of location
			gameOutput(area, "You see what is in front of you.");
		}else if(otherWords.startsWith("*") && gameState.doesTreeExist(otherWords.substring(1))){
			Tree localTree = gameState.getTree(getSubject(otherWords).substring(1));
			if(localTree.getPest() == 1){
				gameOutput(area, "You see the tree contains a pest");
			}else if(localTree.getPest()==0){
				gameOutput(area, "You see the tree is empty.");					
			}else if(localTree.getPest()==2){
				gameOutput(area, "You see the tree contains a pollinator");
			}else{
				gameOutput(area, "Error: you have messed up integers in the code. I don't know how you got here to break it.");
			}
		}else if(otherWords.startsWith("&")){
			gameOutput(area, "Error: you cannot look at addresses.");
		}else{
			gameOutput(area, "Error: " +otherWords + " is not a Tree, nor is it an object in game.");
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
	
	private static void walk(GameMainPanel panel, JTextArea area, Point p, String otherWords){
		if (otherWords.startsWith("&") && gameState.doesTreeExist(otherWords.substring(1))){
			panel.showTree(gameState.getTree(otherWords.substring(1)));
			gameOutput(area, "You walk to " + otherWords.substring(1) + ".");
			gameState.moveToPoint(gameState.getTree(otherWords.substring(1)).getLocation());
		}else if(otherWords.equals("&Book") || otherWords.equals("&Guide")){
			gameOutput(area,"You cannot walk to your inventory.");
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
			if(gameState.movePosition(p.x, p.y)==false){
				gameOutput(area, "You have reached the border of the Orchard. You cannot continue to walk in this direction.");
			}else{
				if(dir == 0){
					gameState.movePosition(0, 1);
					gameOutput(area, "You walk North");
				}else if (dir ==90){
					gameState.movePosition(1,0);
					gameOutput(area, "You walk East");
				}else if(dir ==180){
					gameState.movePosition(0, -1);
					gameOutput(area, "You walk South");
				}else{//dir == 270
					gameState.movePosition(-1,0);
					gameOutput(area, "You walk West");
				}
			}
		}
		else{
			//ERROR
			gameOutput(area, "Error: You did not name a direction or valid address.");
		}
		/*
		if (otherWords.equalsIgnoreCase("north") || direction== 0){
			gameState.movePosition(0,1);
			if(gameState.isBorder(p)){ //refactored to be helper function
				gameOutput(area, "You cannot walk that way, you have reached the edge of the map.");
					gameState.movePosition(0, -1);
			}else{
				gameOutput(area, "You move north");
				panel.showTree(gameState.getTreeFromLocation(p));
				System.out.println(p);
			}
		}else if (otherWords.equalsIgnoreCase("south") || direction == 180){
			gameState.movePosition(0,-1);
			if(gameState.isBorder(p)){
				gameOutput(area, "You cannot walk that way, you have reached the edge of the map.");
				gameState.movePosition(0, 1);
			}else{
				gameOutput(area, "You move south");
				panel.showTree(gameState.getTreeFromLocation(p));
				System.out.println(p);
				}
		}else if (otherWords.equalsIgnoreCase("east") || direction ==90){
			gameState.movePosition(1,0);
			if(gameState.isBorder(p)){
				gameOutput(area, "You cannot walk that way, you have reached the edge of the map.");
					gameState.movePosition(-1,0);
			}else{							
				gameOutput(area, "You move east");
				System.out.println(p);
				}
		}else if (otherWords.equalsIgnoreCase("west") || direction == 270){
			gameState.movePosition(-1,0);
			if(gameState.isBorder(p)){
				gameOutput(area, "You cannot walk that way, you have reached the edge of the map.");
					gameState.movePosition(1,0);
			}else{
					gameOutput(area, "You move west");
					System.out.println(p);
				}
		}else { 
			gameOutput(area, "That is not an address, or a direction after walk, and you did not choose to just walk.");
			}
			*/
	}
}

