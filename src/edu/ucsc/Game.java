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
			next(area, panel, p, gameGUI);
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
			if (otherWords.equalsIgnoreCase("orchard")) {
                enter(panel, area, p, gameGUI);
            } else {
                gameOutput(area, "You cannot enter " + otherWords);
            }
		}
		else if(commandType == 6){//for EXIT
			
		}else if(commandType == 7){//for EQUALS
			
		}
		else if (commandType == -1){
			gameOutput(area, "That is not an option. Did you mean to use put, look, next, start, exit, quit, or walk?");
		}
		return true;
	}
	
	private static void next(JTextArea area, GameMainPanel panel, Point p, GameGUI gameGUI){
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
	
	private static void exit(GameMainPanel panel, JTextArea area){
		panel.showTree(gameState.getTreeFromLocation(gameState.getPosition()));
		gameOutput(area, "You exit back to the game.");
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
		if(getSubject(otherWords).equals("*Book")){
			if (getObject(otherWords).startsWith("&") && gameState.doesTreeExist(getObject(otherWords).substring(1))){
				gameOutput(area, "You put the address " + getObject(otherWords) + " in the Book.");
			}else{
				gameOutput(area, "You cannot put that in the Book, because it is not an address.");
			}
		}else if(getSubject(otherWords).equals("*Poison")){
			if(getObject(otherWords).equals("*Apple")){
				gameState.changePoison(1);
				gameOutput(area, "You put the Apple pesticide in the container");
			}else if(getObject(otherWords).equals("*Orange")){
				gameState.changePoison(2);
				gameOutput(area, "You put the Orange pesticide in the container");
			}else if(getObject(otherWords).equals("*Cherry")){
				gameState.changePoison(3);
				gameOutput(area, "You put the Cherry pesticide in the container");
			}else if(getObject(otherWords).equals("*Nut")){
				gameState.changePoison(4);
				gameOutput(area, "You put the Nut pesticide in the container");
			}else if(getObject(otherWords).equals("*Lemon")){
				gameState.changePoison(5);
				gameOutput(area, "You put the Lemon pesticide in the container");
			}else if(getObject(otherWords).equals("*Lime")){
				gameState.changePoison(6);
				gameOutput(area, "You put the Lime pesticide in the container.");
			}else if(getObject(otherWords).startsWith("&")){
				gameOutput(area, "You cannot put an address in the poison container.");
			}else{
				gameOutput(area, "You cannot put a whole container of pesticide in the poison container.");
			}
		}
		else if(getSubject(otherWords).startsWith("*") && gameState.doesTreeExist(getSubject(otherWords).substring(1))){
			if (getObject(otherWords).equals("*Poison")){
				if(gameState.getPoison()==1){
					gameOutput(area, "You put the Apple pesticide in the tree");
				}else if(gameState.getPoison()==2){
					gameOutput(area, "You put the Orange pesticide in the tree");
				}else if(gameState.getPoison()==3){
					gameOutput(area, "You put the Cherry pesticide in the tree");
				}else if(gameState.getPoison()==4){
					gameOutput(area, "You put the Nut pesticide in the tree");
				}else if(gameState.getPoison()==5){
					gameOutput(area, "You put the Lemon pesticide in the tree");
				}else if(gameState.getPoison()==6){
					gameOutput(area, "You put the Lime pesticide in the tree.");
				}
			}else{
				gameOutput(area, "You attempt to put pesticide in " + getObject(otherWords) + " but it does not exist.");
			}
		}
		else{
			gameOutput(area, "That is not the poison container or the address book.");
		}
	}
	
	private static void look(JTextArea area, Point p, String otherWords) {
		/*
		  NOTE: I need to fix this so that if the tree is renamed it looks up the new "rename" :( 
		  */
		if (otherWords.equalsIgnoreCase("")){
			//Set of conditionals that do description of location
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
			if(localTree.getPest() == 1){
				gameOutput(area, "You see the tree contains a pest");
			}else if(localTree.getPest()==0){
				gameOutput(area, "You see the tree is empty.");					
			}else if(localTree.getPest()==2){
				gameOutput(area, "You see the tree contains a pollinator");
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
		}
		else{
			//ERROR
			gameOutput(area, "You have reached the border of the Orchard. You cannot continue to walk in this direction.");
		}
		panel.showTree(gameState.getTreeFromLocation(gameState.getPosition()));		
	}
}

