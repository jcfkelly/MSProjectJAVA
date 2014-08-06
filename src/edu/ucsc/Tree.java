package edu.ucsc;

import java.awt.Point;

public class Tree {
	//Trees hold locations (address)
	//Trees hold nothing 0, pest 1, or pollinator 2
	//Tree health (alive or dead)
	
	private Point location;
	private int pest;
	private boolean alive;
	private String species;
	
	public Point getLocation(){
		return location; 
	}
	
	public int getPest(){
		return pest;
	}
	
	public boolean getAlive(){
		return alive;
	}
	
	public boolean setAlive(){
		alive = !alive;
		return alive;
	}
	
	public String getTreeSpecies(){
		return species;
	}
	
	public Tree(int x, int y, Integer pollinator, String type){ 
		this.location = new Point(x, y);
		this.pest = pollinator;
		this.alive = true;
		this.species = type;
	}
}

