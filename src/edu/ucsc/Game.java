package edu.ucsc;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import edu.ucsc.Input;

public class Game {
	private static int direction = 0;
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
	
	public static void changeMainPanel(JTextArea area, GameMainPanel panel, String otherWords){
		if(otherWords.startsWith("*") && gameState.doesTreeExist(otherWords.substring(1))){
			ImageIcon imageJPanel = new ImageIcon(gameState.getTree(otherWords.substring(1)).getTreeSpecies());
			JLabel label = new JLabel();
			label.setIcon(imageJPanel);
			panel.add(label);
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
			next(area, panel, p, otherWords);
		}
		else if (commandType == 1){//for LOOK
			look(area, p, otherWords);
		}
		else if (commandType == 2){ //for WALK		
			walk(panel, area, p, otherWords, direction);
		}
		else if (commandType == 3){//for RENAME
			//rename(area, p, otherWords);
		}
		else if (commandType == 4){//for TURN
			turn(area, p, otherWords, direction);
		}
		else if (commandType == 5){//for ENTER
			
		}
		else if (commandType == -1){
			gameOutput(area, "That is not an option. Did you mean to use put, look, next, start, exit, quit, or walk?");
		}
		return true;
	}
	
	private static void next(JTextArea area, GameMainPanel panel, Point p, String otherWords){
		if (panel == null){
			return;
		}
		int introState = panel.getIntroState();
		if (introState == -1){
			gameOutput(area, "Error: This is not in the game.");			
		}else if(introState == panel.MAX_INTRO_STATE){
			panel.setIntroState(-1);
			enter(panel, area, p, "orchard");
		}else{
			panel.setIntroState(introState+1);
		}
	}
	
	private static void enter(GameMainPanel panel, JTextArea area, Point p, String otherWords){
		if (otherWords.equalsIgnoreCase("orchard")){
			walk(panel, area, new Point(0,-1), "", 0);
		}else{
			gameOutput(area, "You cannot enter " + otherWords);
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
			gameOutput(area, "You see the contents of " + otherWords.substring(1));
		}
	}
	
	private static int turn(JTextArea area, Point p, String otherWords, int direction){
		if (otherWords.equalsIgnoreCase("right")){
			direction = direction + 90;
		}else if (otherWords.equalsIgnoreCase("left")){
			direction = direction - 90;
		}
		direction = direction%360;
		return direction;
	}
	
	private static void walk(GameMainPanel panel, JTextArea area, Point p, String otherWords, int direction){
		if (otherWords.substring(0).equals("&") && otherWords.length() >= 2){
			gameOutput(area, "You walk to address.");
		}
		else if (otherWords.equalsIgnoreCase("north") || direction== 0){
			gameState.movePosition(0,1);
			if(gameState.isBorder(p)){ //refactored to be helper function
				gameOutput(area, "You cannot walk that way, you have reached the edge of the map.");
					gameState.movePosition(0, -1);
			}else{
				gameOutput(area, "You move north");
				panel.gamePanel(gameState.getPosition());
				System.out.println(p);
			}
			
		}else if (otherWords.equalsIgnoreCase("south") || direction == 180){
			gameState.movePosition(0,-1);
			if(gameState.isBorder(p)){
				gameOutput(area, "You cannot walk that way, you have reached the edge of the map.");
				gameState.movePosition(0, 1);
			}else{
				gameOutput(area, "You move south");
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
	}
}

