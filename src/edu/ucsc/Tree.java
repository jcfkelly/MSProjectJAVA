package edu.ucsc;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;

public class Tree {
	private Point location;
	private List<String> nestNames;
	//to do constructors : 
	
	public Point getLocation() {
		return location; 
	}
	
	public List<String> getNests(){
		return nestNames;
	}
	
	public int getNumNests(){
		return nestNames.size(); //lists have size
	}
	
	public Tree(int x, int y, String... nestNames){ //array of nests
		this.location = new Point(x, y);
		this.nestNames = Arrays.asList(nestNames);
	}
}

