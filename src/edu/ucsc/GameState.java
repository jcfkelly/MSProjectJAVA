package edu.ucsc;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class GameState{
	private final int MAX_X=4;
	private final int MIN_X=-4;
	private final int MAX_Y=9;
	private final int MIN_Y=0;
	private int direction = 0;
	private Point position;
	private ArrayList<String> addressBook; //this is where the things get stored in birdbook 
	private HashMap<String, Tree> treeMap;
	private boolean winState=false;
	
	//check whether winState ever works in debugging
	public GameState(){
		//complete reset in GameState
		position = new Point(0,0);
		addressBook = new ArrayList<String>(100); //ensures size is limited
		for(int i =0 ; i<100; i++){ //all empty strings
			addressBook.add(null);
		}
		addressBook.add(0, "&Shed");

		treeMap = new HashMap<String, Tree>(); //contains names as keys
		//Create Trees (Put on one line)
		treeMap.put("Apple", new Tree(0,0,0, "assets/MS_Project_Trees_apple.jpg"));
		treeMap.put("Ananasrenette", new Tree(0,1,0, "assets/MS_Project_Trees_ananasrenette.jpg"));
		treeMap.put("Amanogawa", new Tree(1,0,0, "assets/MS_Project_Trees_amanogawa.jpg"));
		treeMap.put("Bahianinha", new Tree(1,1,0, "assets/MS_Project_Trees_bahianinha.jpg"));
		
	}
		
	public boolean movePosition(int dx, int dy){
		int x = position.x;
		int y = position.y;
		boolean success = true;

		if(x == MIN_X && dx<0){
			dx=0;
			success = false;
		}
		else if(x == MAX_X && dx>0){
			dx=0;
			success = false;
		}
		if(y == MIN_Y && dy <0){
			dy=0;
			success = false;
		}
		else if(y == MAX_Y && dy>0){
			dy=0;
			success = false;
		}
		
		position.move(x+dx, y+dy); //This is because it cannot go into the lake
		return success;
	}
	
	public ArrayList addToAddressBook(String s){
		addressBook.add(s);
		return addressBook;
	}
	
	public void moveToPoint(Point p){
		position.move(p.x, p.y);
	}
	
	public Point getPosition(){
		return position;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getDirection(){
		return direction;
	}
	
	public void turnRight(){
		direction += 90;
		direction%=360;
	}
	
	public void turnLeft(){
		direction -= 90;
		direction%=360;
	}
	
	public boolean moveForward(){
		if (direction ==0){
			return movePosition(0,1);
		}else if(direction ==180){
			return movePosition(0,-1);
		}else if (direction ==90){
			return movePosition(1,0);
		}else{ //this should be 270
			return movePosition(-1,0);
		}
	}
	
	
	//Tree		
	public boolean isBorder(Point p){
		if(p.x < -9 || p.x >9 || p.y <-9 || p.y >9){
			return true;
		}else{
			return false;
		}
	}

	public void renameTree(String oldName, String newName){
		if(oldName==null || newName == null){
			return; //nothing to do here
		}
		if (!doesTreeExist(oldName)){
			return; //nothing to do here
		}
		if(newName.equalsIgnoreCase("")){
			return; //nothing to do here
		}
		if(doesTreeExist(newName)){
			return;
		}
		Tree temp = treeMap.get(oldName);
		treeMap.put(newName, temp);
		treeMap.remove(oldName);
	}
	
	public boolean doesTreeExist(String tree){
		return treeMap.containsKey(tree);
	}
	
	//turns a location into a string name for a tree
	public String treeFromLocation(Point p){
		for (String treeName : treeMap.keySet()){
			if (treeMap.get(treeName).getLocation().equals(p)){
				return treeName;
			}
		}
		return null;
	}
	
	public Tree getTreeFromLocation(Point p){
		if (treeFromLocation(p) != null){
			return getTree(treeFromLocation(p));
		}else{
			return null;
		}
	}
	
	public Tree getTree(String treeName){
		return treeMap.get(treeName);
	}
	
	public boolean winState(){
		return winState;
	}

}