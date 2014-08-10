package edu.ucsc;

import java.awt.Point;

public class Tree {
	//Trees hold locations (address)
	//Trees hold nothing 0, 
	//pest 1,2,3,4
	//pollinator 5,6,7,8
	//Tree health (alive or dead)
	
	private Point location;
	private boolean alive;
	private String species;
	private int treeType;
	private int resident=0;

	
	public int getResident() {
		return resident;
	}

	public void setResident(int resident) {
		this.resident = resident;
	}

	public Point getLocation(){
		return location; 
	}

	public boolean getAlive(){
		return alive;
	}
	
	public boolean setAlive(){
		alive = !alive;
		return alive;
	}
	
	public String setTreeSpecies(String s){
		species = s;
		return species;
	}
	
	public String getTreeSpecies(){
		return species;
	}
	
	public int getTreeType() {
		return treeType;
	}

	public Tree(int x, int y, int treeType, String type){ 
		this.location = new Point(x, y);
		this.resident = 0;
		this.treeType = treeType;
		this.alive = true;
		this.species = type;
	}
}

