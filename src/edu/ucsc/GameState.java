package edu.ucsc;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class GameState{
	private Point position;
	private ArrayList<String> addressBook; //this is where the things get stored in birdbook 
	private HashMap<String, Tree> treeMap;
	private boolean winState=false;
	
	//check whether winState ever works in debugging
	public GameState(){
		//complete reset in GameState
		position = new Point(0,-1);
		addressBook = new ArrayList<String>(100); //ensures size is limited
		for(int i =0 ; i<100; i++){ //all empty strings
			addressBook.add(null);
		}

		treeMap = new HashMap<String, Tree>(); //contains names as keys
		//Create Trees (Put on one line)
		treeMap.put("Apple", new Tree(0,1,0));
		treeMap.put("Apple", new Tree(1,1,0));
		treeMap.put("Apple", new Tree(1,0,0));
		treeMap.put("Apple", new Tree(1,1,0));
	}
		
	public void movePosition(int dx, int dy){
		int x = position.x;
		int y = position.y;
		position.move(x+dx, y+dy); //This is because it cannot go into the lake
		//System.out.println(position.toString());
	}
	
	public void moveToPoint(Point p){
		position.move(p.x, p.y);
	}
	
	public Point getPosition(){
		return position;
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
	
	public Tree getTree(String treeName){
		return treeMap.get(treeName);
	}
			
	public boolean winState(){
		return winState;
	}

}