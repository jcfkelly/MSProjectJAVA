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
	private int resident;

	public int getResident() {
		return resident;
	}

	public String getResidentString(){
		switch(resident){
			case 1:
				return "Ants";
			case 2: 
				return "Wasps";
			case 3: 
				return "Gophers";
			case 4: 
				return "Aphids";
			case 5:
				return "Butterflies";
			case 6: 
				return "Bees";
			case 7:
				return "Frogs";
			case 8:
				return "Ladybugs";
			default: 
				return "none";
		}
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
	
	public void setAlive(boolean alive){
		this.alive = alive;
		if (!alive){
			setTreeSpecies("assets/MS_Project_Trees_dead.jpg");
		}
	}
	
	public void setTreeSpecies(String species){
		this.species = species;
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

