package edu.ucsc;

public class Nest {
	private int height; //location of nest IN tree (location)
	private int bird; //what's in the nest (a number)
	
	public int getHeight(){
		return height;  //can't return two things, so do I need to make a new "location and nest" thing? 
	}
	
	public int getBird(){
		return bird;
	}
	
	public void zeroBird(){
		this.bird = 0;
	}
	
	public Nest(int z, int bird){ //array of nests
		this.height = z;
		this.bird = bird;
	}
}
