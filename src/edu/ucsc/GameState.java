package edu.ucsc;


import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameState{
	private int birdsInSanctuary;
	private int birdsInCage;
	private Point position;
	private ArrayList<String> birdBook; //this is where the things get stored in birdbook 
	private HashMap<String, Tree> treeMap;
	private HashMap<String, Nest> nestMap;
	private boolean winState=false;
	
	//check whether winState ever works in debugging
	public GameState(){
		//complete reset in GameState
		birdsInSanctuary = 0;
		birdsInCage = 0;
		position = new Point(0,0);
		birdBook = new ArrayList<String>(100); //ensures size is limited
		for(int i =0 ; i<100; i++){ //all empty strings
			birdBook.add(null);
		}
		treeMap = new HashMap<String, Tree>(); //contains names as keys
		nestMap = new HashMap<String, Nest>(); 
		putInBirdBook(0, "Sanctuary"); //make first page of birdbook contain location of Sanctuary
		
		if(birdsInSanctuary >= 20){
			winState = true;
		}
		//Create Nests
		//Note: "NotNest" means Tree takes in a "nest", but it doesn't exist in visual land.
		//in Tree together
		nestMap.put("Nest1", new Nest(7, 3));
		nestMap.put("Nest2", new Nest(6, 0));
		nestMap.put("Nest3", new Nest(5, 3));
		//in Tree together
		nestMap.put("Nest4", new Nest(4, 2));
		//in Tree together
		nestMap.put("Nest5", new Nest(3, 1));
		//in Tree together
		nestMap.put("Nest6", new Nest(4, 1));
		nestMap.put("Nest7", new Nest(5, 2));
		nestMap.put("Nest8", new Nest(6, 0));
		//in Tree together
		nestMap.put("Nest9", new Nest(7, 2));
		//in Tree together
		nestMap.put("Nest10", new Nest(8, 3));
		//in Tree together
		nestMap.put("Nest11", new Nest(9, 3));
		//in Tree together
		nestMap.put("Nest12", new Nest(1, 0));
		nestMap.put("Nest13", new Nest(7, 3));
		//in Tree together
		nestMap.put("Nest14", new Nest(2, 2));
		//in Tree together
		nestMap.put("Nest15", new Nest(8, 1));
		//in Tree together
		nestMap.put("Nest16", new Nest(7, 1));
		//in Tree together
		nestMap.put("Nest17", new Nest(8, 2));
		nestMap.put("Nest18", new Nest(4, 1));
		//in Tree together
		nestMap.put("Nest19", new Nest(9, 2));
		nestMap.put("Nest20", new Nest(2, 2));
		nestMap.put("Nest21", new Nest(6, 3));
		//in Tree together
		nestMap.put("Nest22", new Nest(2, 1));
		//in Tree together
		nestMap.put("Nest23", new Nest(5, 3));
		//in Tree together
		nestMap.put("Nest24", new Nest(3, 2));
		//in Tree together
		nestMap.put("Nest25", new Nest(7, 1));
		//in Tree together
		nestMap.put("Nest26", new Nest(5, 1));
		nestMap.put("Nest27", new Nest(8, 2));
		//in Tree together
		nestMap.put("Nest28", new Nest(6, 1));
		//in Tree together
		nestMap.put("Nest29", new Nest(4, 2));
		//in Tree together
		nestMap.put("Nest30", new Nest(6, 3));
		//in Tree together
		nestMap.put("Nest31", new Nest(7, 1));
		nestMap.put("Nest32", new Nest(2, 1));
		nestMap.put("Nest33", new Nest(5, 0));
		//in Tree together
		nestMap.put("Nest34", new Nest(3, 1));
		//in Tree together
		nestMap.put("Nest35", new Nest(4, 1));
		//in Tree together
		nestMap.put("Nest36", new Nest(2, 0));
		nestMap.put("Nest37", new Nest(9, 1));
		nestMap.put("Nest38", new Nest(5, 2));
		//in Tree together
		nestMap.put("Nest39", new Nest(5, 1));
		//in Tree together
		nestMap.put("Nest40", new Nest(1, 1));
		
		//Create Trees (Put on one line)
		treeMap.put("Tree1", new Tree(9, 9, "Nest1", "Nest2", "Nest3"));
		treeMap.put("Tree2", new Tree(6, 8, "Nest4"));
		treeMap.put("Tree3", new Tree(9,6, "Nest5"));
		treeMap.put("Tree4", new Tree(3, 1, "Nest6", "Nest7", "Nest8"));
		treeMap.put("Tree5", new Tree(-9, 9));
		treeMap.put("Tree6", new Tree(-8, 9));
		treeMap.put("Tree7", new Tree(-1, 4, "Nest9"));
		treeMap.put("Tree8", new Tree(-2, 1, "Nest10"));
		treeMap.put("Tree9", new Tree(-3, 1, "Nest11"));
		treeMap.put("Tree10", new Tree(-4, -4, "Nest12", "Nest13"));
		treeMap.put("Tree11", new Tree(-7,9));
		treeMap.put("Tree12", new Tree(4, -6, "Nest14"));
		treeMap.put("Tree13", new Tree(-9, 2, "Nest15"));
		treeMap.put("Tree14", new Tree(-6,9));
		treeMap.put("Tree15", new Tree(-5,9));
		treeMap.put("Tree16", new Tree(-4,9));
		treeMap.put("Tree17", new Tree(-8,-8, "Nest16"));
		treeMap.put("Tree18", new Tree(-4, -4, "Nest17","Nest18"));
		treeMap.put("Tree19", new Tree(-6, 6, "Nest19", "Nest20", "Nest21"));
		treeMap.put("Tree20", new Tree(-5,9));
		treeMap.put("Tree21", new Tree(1,-8, "Nest22"));
		treeMap.put("Tree22", new Tree(1,8, "Nest23"));
		treeMap.put("Tree23", new Tree(-4,9));
		treeMap.put("Tree24", new Tree(-3,9));
		treeMap.put("Tree25", new Tree(-1,9));
		treeMap.put("Tree26", new Tree(0,9));
		treeMap.put("Tree27", new Tree(1,9));
		treeMap.put("Tree28", new Tree(2,9));
		treeMap.put("Tree29", new Tree(3,9));
		treeMap.put("Tree30", new Tree(4,9));
		treeMap.put("Tree31", new Tree(5,9));
		treeMap.put("Tree32", new Tree(6,9));
		treeMap.put("Tree33", new Tree(7,0, "Nest24"));
		treeMap.put("Tree34", new Tree(-8, -2, "Nest25"));
		treeMap.put("Tree35", new Tree(8,-7, "Nest26","Nest27"));
		treeMap.put("Tree36", new Tree(-7,7, "Nest28"));
		treeMap.put("Tree37", new Tree(7,9));
		treeMap.put("Tree38", new Tree(8,9));
		treeMap.put("Tree39", new Tree(9,8));
		treeMap.put("Tree40", new Tree(9,7));
		treeMap.put("Tree41", new Tree(9,5));
		treeMap.put("Tree42", new Tree(9,4));
		treeMap.put("Tree43", new Tree(9,3));
		treeMap.put("Tree44", new Tree(9,2));
		treeMap.put("Tree45", new Tree(9,1));
		treeMap.put("Tree46", new Tree(9, 0));
		treeMap.put("Tree47", new Tree(9,-1));
		treeMap.put("Tree48", new Tree(0, -4, "Nest29"));
		treeMap.put("Tree49", new Tree(9,-2));
		treeMap.put("Tree50", new Tree(9,-3));
		treeMap.put("Tree51", new Tree(9,-4));
		treeMap.put("Tree52", new Tree(9,-5));
		treeMap.put("Tree53", new Tree(9,-6));
		treeMap.put("Tree54", new Tree(9,-7));
		treeMap.put("Tree55", new Tree(9,-8));
		treeMap.put("Tree56", new Tree(9,-9));
		treeMap.put("Tree57", new Tree(8,-9));
		treeMap.put("Tree58", new Tree(7,-9));
		treeMap.put("Tree59", new Tree(6,-9));
		treeMap.put("Tree60", new Tree(-5, 2, "Nest30"));
		treeMap.put("Tree61", new Tree(5,-9));
		treeMap.put("Tree62", new Tree(4,-9));
		treeMap.put("Tree63", new Tree(3,-9));
		treeMap.put("Tree64", new Tree(2,-9));
		treeMap.put("Tree65", new Tree(1,-9));
		treeMap.put("Tree66", new Tree(0, -9));
		treeMap.put("Tree67", new Tree(-1,-9));
		treeMap.put("Tree68", new Tree(-2,-9));
		treeMap.put("Tree69", new Tree(-3,-9));
		treeMap.put("Tree70", new Tree(-4,-9));
		treeMap.put("Tree71", new Tree(-5,-9));
		treeMap.put("Tree72", new Tree(-2, 9, "Nest31","Nest32","Nest33"));
		treeMap.put("Tree73", new Tree(-7, -5, "Nest34"));
		treeMap.put("Tree74", new Tree(7, 6, "Nest35"));
		treeMap.put("Tree75", new Tree(-6,-9));
		treeMap.put("Tree76", new Tree(-7, -9));
		treeMap.put("Tree77", new Tree(-8,-9));
		treeMap.put("Tree78", new Tree(-9,-9));
		treeMap.put("Tree79", new Tree(-9,-8));
		treeMap.put("Tree80", new Tree(-9,-7));
		treeMap.put("Tree81", new Tree(-9,-6));
		treeMap.put("Tree82", new Tree(-9,-5));
		treeMap.put("Tree83", new Tree(-9,-4));
		treeMap.put("Tree84", new Tree(-9,-3));
		treeMap.put("Tree85", new Tree(-9,-2));
		treeMap.put("Tree86", new Tree(-9,-1));
		treeMap.put("Tree87", new Tree(-9,0));
		treeMap.put("Tree88", new Tree(-9,1));
		treeMap.put("Tree89", new Tree(-9,3));
		treeMap.put("Tree90", new Tree(-9,4));
		treeMap.put("Tree91", new Tree(-9,5));
		treeMap.put("Tree92", new Tree(3, -3, "Nest36","Nest37","Nest38"));
		treeMap.put("Tree93", new Tree(-9,6));
		treeMap.put("Tree94", new Tree(-9,7));
		treeMap.put("Tree95", new Tree(-9,8));
		treeMap.put("Tree96", new Tree(-9,9));
		treeMap.put("Tree97", new Tree(-7,8));
		treeMap.put("Tree98", new Tree(-6,8));
		treeMap.put("Tree99", new Tree(-5,8));
		treeMap.put("Tree100", new Tree(-4,8));
		treeMap.put("Tree101", new Tree(-3,8));
		treeMap.put("Tree102", new Tree(-2,8));
		treeMap.put("Tree103", new Tree(-1,8));
		treeMap.put("Tree104", new Tree(0, 8));
		treeMap.put("Tree105", new Tree(2, 5, "Nest39"));
		treeMap.put("Tree106", new Tree(2,8));
		treeMap.put("Tree107", new Tree(3,8));
		treeMap.put("Tree108", new Tree(4,8));
		treeMap.put("Tree109", new Tree(5,8));
		treeMap.put("Tree110", new Tree(7,8));
		treeMap.put("Tree111", new Tree(8,8));
		treeMap.put("Tree112", new Tree(8,7));
		treeMap.put("Tree113", new Tree(8,6));
		treeMap.put("Tree114", new Tree(8,5));
		treeMap.put("Tree115", new Tree(8,2));
		treeMap.put("Tree116", new Tree(8, 0));
		treeMap.put("Tree117", new Tree(8,-1));
		treeMap.put("Tree118", new Tree(8,-2));
		treeMap.put("Tree119", new Tree(8,-3));
		treeMap.put("Tree120", new Tree(6, -3, "Nest40"));
		treeMap.put("Tree121", new Tree(8,-4));
		treeMap.put("Tree122", new Tree(8,-5));
		treeMap.put("Tree123", new Tree(8,-6));
		treeMap.put("Tree124", new Tree(8,-8));
		treeMap.put("Tree125", new Tree(7,-8));
		treeMap.put("Tree126", new Tree(6,-8));
		treeMap.put("Tree127", new Tree(5,-8));
		treeMap.put("Tree128", new Tree(4,-8));
		treeMap.put("Tree129", new Tree(3,-8));
		treeMap.put("Tree130", new Tree(2,-8));
		treeMap.put("Tree131", new Tree(0, -8));
		treeMap.put("Tree132", new Tree(-1,-8));
		treeMap.put("Tree133", new Tree(-2,-8));
		treeMap.put("Tree134", new Tree(-3,-8));
		treeMap.put("Tree135", new Tree(-4,-8));
		treeMap.put("Tree136", new Tree(-5,-8));
		treeMap.put("Tree137", new Tree(-6,-8));
		treeMap.put("Tree138", new Tree(-7,-8));
		treeMap.put("Tree139", new Tree(-8,-7));
		treeMap.put("Tree140", new Tree(-8,-6));
		treeMap.put("Tree141", new Tree(-8,-5));
		treeMap.put("Tree142", new Tree(-8,-4));
		treeMap.put("Tree143", new Tree(-8,-3));
		treeMap.put("Tree144", new Tree(-8,-1));
		treeMap.put("Tree145", new Tree(-8,0));
		treeMap.put("Tree146", new Tree(-8,1));
		treeMap.put("Tree147", new Tree(-8,2));
		treeMap.put("Tree148", new Tree(-8,3));
		treeMap.put("Tree149", new Tree(-8,4));
		treeMap.put("Tree150", new Tree(-8,5));
		treeMap.put("Tree151", new Tree(-6,7));
		treeMap.put("Tree152", new Tree(-5,7));
		treeMap.put("Tree153", new Tree(-4,7));
		treeMap.put("Tree154", new Tree(-3,7));
		treeMap.put("Tree155", new Tree(-2,7));
		treeMap.put("Tree156", new Tree(-1,7));
		treeMap.put("Tree157", new Tree(0, 7));
		treeMap.put("Tree158", new Tree(1,7));
		treeMap.put("Tree159", new Tree(2,7));
		treeMap.put("Tree160", new Tree(3,7));
		treeMap.put("Tree161", new Tree(4,7));
		treeMap.put("Tree162", new Tree(5,7));
		treeMap.put("Tree163", new Tree(6,7));
		treeMap.put("Tree164", new Tree(7,7));
		treeMap.put("Tree165", new Tree(7,-1));
		treeMap.put("Tree166", new Tree(7,-2));
		treeMap.put("Tree167", new Tree(7,-3));
		treeMap.put("Tree168", new Tree(7,-4));
		treeMap.put("Tree169", new Tree(7,-5));
		treeMap.put("Tree170", new Tree(7,-6));
		treeMap.put("Tree171", new Tree(7,-7));
		treeMap.put("Tree172", new Tree(6,-7));
		treeMap.put("Tree173", new Tree(5,-7));
		treeMap.put("Tree174", new Tree(4,-7));
		treeMap.put("Tree175", new Tree(3,-7));
		treeMap.put("Tree176", new Tree(2,-7));
		treeMap.put("Tree177", new Tree(1,-7));
		treeMap.put("Tree178", new Tree(0, -7));
		treeMap.put("Tree179", new Tree(-1,-7));
		treeMap.put("Tree180", new Tree(-2,-7));
		treeMap.put("Tree181", new Tree(-3,-7));
		treeMap.put("Tree182", new Tree(-4,-7));
		treeMap.put("Tree183", new Tree(-7,-7));
		treeMap.put("Tree184", new Tree(-7,-6));
		treeMap.put("Tree185", new Tree(-7,-4));
		treeMap.put("Tree186", new Tree(-7,-3));
		treeMap.put("Tree187", new Tree(-7,-2));
		treeMap.put("Tree188", new Tree(-7,-1));
		treeMap.put("Tree189", new Tree(-7, 0));
		treeMap.put("Tree190", new Tree(-7,1));
		treeMap.put("Tree191", new Tree(-7,2));
		treeMap.put("Tree192", new Tree(-7,3));
		treeMap.put("Tree193", new Tree(-7,4));
		treeMap.put("Tree194", new Tree(-7,5));
		treeMap.put("Tree195", new Tree(-7,6));
		treeMap.put("Tree196", new Tree(-5,6));
		treeMap.put("Tree197", new Tree(-4,6));
		treeMap.put("Tree198", new Tree(-3,6));
		treeMap.put("Tree199", new Tree(-2,6));
		treeMap.put("Tree200", new Tree(-1,6));
		treeMap.put("Tree201", new Tree(0,6));
		treeMap.put("Tree202", new Tree(1,6));
		treeMap.put("Tree203", new Tree(2,6));
		treeMap.put("Tree204", new Tree(3,6));
		treeMap.put("Tree205", new Tree(4,6));
		treeMap.put("Tree206", new Tree(6, 0));
		treeMap.put("Tree207", new Tree(6,-1));
		treeMap.put("Tree208", new Tree(6,-2));
		treeMap.put("Tree209", new Tree(5,-6));
		treeMap.put("Tree210", new Tree(6,-4));
		treeMap.put("Tree211", new Tree(6,-5));
		treeMap.put("Tree212", new Tree(6,-6));
		treeMap.put("Tree213", new Tree(3,-6));
		treeMap.put("Tree214", new Tree(2,-6));
		treeMap.put("Tree215", new Tree(1,-6));
		treeMap.put("Tree216", new Tree(0, -6));
		treeMap.put("Tree217", new Tree(-1,-6));
		treeMap.put("Tree218", new Tree(-2,-6));
		treeMap.put("Tree219", new Tree(-3,-6));
		treeMap.put("Tree220", new Tree(-4,-6));
		treeMap.put("Tree221", new Tree(-6,-4));
		treeMap.put("Tree222", new Tree(-6,-3));
		treeMap.put("Tree223", new Tree(-6,-2));
		treeMap.put("Tree224", new Tree(-6,-1));
		treeMap.put("Tree225", new Tree(-6, 0));
		treeMap.put("Tree226", new Tree(-6,1));
		treeMap.put("Tree227", new Tree(-6,2));
		treeMap.put("Tree228", new Tree(-6,3));
		treeMap.put("Tree229", new Tree(-6,4));
		treeMap.put("Tree230", new Tree(-6,5));
		treeMap.put("Tree231", new Tree(-5,5));
		treeMap.put("Tree232", new Tree(-4,5));
		treeMap.put("Tree233", new Tree(-3,5));
		treeMap.put("Tree234", new Tree(-2,5));
		treeMap.put("Tree235", new Tree(-1,5));
		treeMap.put("Tree236", new Tree(0, 5));
		treeMap.put("Tree237", new Tree(1,5));
		treeMap.put("Tree238", new Tree(3,5));
		treeMap.put("Tree239", new Tree(5, 0));
		treeMap.put("Tree240", new Tree(5,-1));
		treeMap.put("Tree241", new Tree(5,-2));
		treeMap.put("Tree242", new Tree(5,-3));
		treeMap.put("Tree243", new Tree(5,-4));
		treeMap.put("Tree244", new Tree(5,-5));
		treeMap.put("Tree245", new Tree(4,-5));
		treeMap.put("Tree246", new Tree(4,-4));
		treeMap.put("Tree247", new Tree(4,-3));
		treeMap.put("Tree248", new Tree(4,-2));
		treeMap.put("Tree249", new Tree(4,-1));
		treeMap.put("Tree250", new Tree(4, 0));
		treeMap.put("Tree251", new Tree(4,1));
		treeMap.put("Tree252", new Tree(3,-5));
		treeMap.put("Tree253", new Tree(2,-5));
		treeMap.put("Tree254", new Tree(1,-5));
		treeMap.put("Tree255", new Tree(0, -5));
		treeMap.put("Tree256", new Tree(-1,-5));
		treeMap.put("Tree257", new Tree(-2,-5));
		treeMap.put("Tree258", new Tree(-3,-5));
		treeMap.put("Tree259", new Tree(-4,-5));
		treeMap.put("Tree260", new Tree(-5,-4));
		treeMap.put("Tree261", new Tree(-5,-3));
		treeMap.put("Tree262", new Tree(-5,-2));
		treeMap.put("Tree263", new Tree(-5,-1));
		treeMap.put("Tree264", new Tree(-5, 0));
		treeMap.put("Tree265", new Tree(-5,-1));
		treeMap.put("Tree266", new Tree(-5,3));
		treeMap.put("Tree267", new Tree(-5,4));
		treeMap.put("Tree268", new Tree(-4,4));
		treeMap.put("Tree269", new Tree(-4,3));
		treeMap.put("Tree270", new Tree(-4,2));
		treeMap.put("Tree271", new Tree(-4,1));
		treeMap.put("Tree272", new Tree(-4, 0));
		treeMap.put("Tree273", new Tree(-4,-1));
		treeMap.put("Tree274", new Tree(-4,-2));
		treeMap.put("Tree275", new Tree(-4,-3));
		treeMap.put("Tree276", new Tree(-3,4));
		treeMap.put("Tree277", new Tree(-2,4));
		treeMap.put("Tree278", new Tree(0, 4));
		treeMap.put("Tree279", new Tree(1,4));
		treeMap.put("Tree280", new Tree(2,4));
		treeMap.put("Tree281", new Tree(-3,3));
		treeMap.put("Tree282", new Tree(-3,2));
		treeMap.put("Tree283", new Tree(0, 3));
		treeMap.put("Tree284", new Tree(1,3));
		treeMap.put("Tree285", new Tree(2,3));
		treeMap.put("Tree286", new Tree(0, 2));
		treeMap.put("Tree287", new Tree(1,2));
		treeMap.put("Tree288", new Tree(2,2));
		treeMap.put("Tree289", new Tree(3,2));
		treeMap.put("Tree290", new Tree(-3,-4));
		treeMap.put("Tree291", new Tree(-2,-4));
		treeMap.put("Tree292", new Tree(-1,-4));
		treeMap.put("Tree293", new Tree(1,-4));
		treeMap.put("Tree294", new Tree(2,-4));
		treeMap.put("Tree295", new Tree(3,-4));
		treeMap.put("Tree296", new Tree(-1,1));
		treeMap.put("Tree297", new Tree(0, 1));
		treeMap.put("Tree298", new Tree(1,1));
		treeMap.put("Tree299", new Tree(2,1));
		treeMap.put("Tree300", new Tree(-3,0));
		treeMap.put("Tree301", new Tree(-2, 0));
		treeMap.put("Tree302", new Tree(-1, 0));
		treeMap.put("Tree303", new Tree(1, 0));
		treeMap.put("Tree304", new Tree(2, 0));
		treeMap.put("Tree305", new Tree(3, 0));
		treeMap.put("Tree306", new Tree(-3,-1));
		treeMap.put("Tree307", new Tree(-2,-1));
		treeMap.put("Tree308", new Tree(-1,-1));
		treeMap.put("Tree309", new Tree(0, -1));
		treeMap.put("Tree310", new Tree(1,-1));
		treeMap.put("Tree311", new Tree(2,-1));
		treeMap.put("Tree312", new Tree(3,-1));
		treeMap.put("Tree313", new Tree(-3, -2));
		treeMap.put("Tree314", new Tree(-2,-2));
		treeMap.put("Tree315", new Tree(-1,-2));
		treeMap.put("Tree316", new Tree(0, -2));
		treeMap.put("Tree317", new Tree(1,-2));
		treeMap.put("Tree318", new Tree(2,-2));
		treeMap.put("Tree319", new Tree(3,-2));
		treeMap.put("Tree320", new Tree(-3,-3));
		treeMap.put("Tree321", new Tree(-2,-3));
		treeMap.put("Tree322", new Tree(-1,-3));
		treeMap.put("Tree323", new Tree(2,-3));
		treeMap.put("Tree324", new Tree(1,-3));
		treeMap.put("Tree325", new Tree(0, -3));
		treeMap.put("Tree326", new Tree(-6,7));
		treeMap.put("Tree327", new Tree(-5,7));
		treeMap.put("Tree328", new Tree(-4,7));
		treeMap.put("Tree329", new Tree(-3,7));
		treeMap.put("Tree330", new Tree(-2,7));
		treeMap.put("Tree331", new Tree(-6,6));
		treeMap.put("Tree332", new Tree(-5,6));
		treeMap.put("Tree333", new Tree(-4,6));
		treeMap.put("Tree334", new Tree(-3,6));
		treeMap.put("Tree335", new Tree(-2,6));
		treeMap.put("Tree336", new Tree(-3,5));
		treeMap.put("Tree337", new Tree(-4,4));
		treeMap.put("Tree338", new Tree(-5,4));
		treeMap.put("Tree339", new Tree(-6,4));
		treeMap.put("Tree340", new Tree(-6,5));
		treeMap.put("Tree341", new Tree(-5,6));
		treeMap.put("Tree342", new Tree(-4,6));
		treeMap.put("Tree343", new Tree(-5,5));
		treeMap.put("Tree344", new Tree(-4,5));
		treeMap.put("Tree345", new Tree(1, 0));
		treeMap.put("Tree346", new Tree(2, 0));
		treeMap.put("Tree347", new Tree(3, 0));
		treeMap.put("Tree348", new Tree(4, 0));
		treeMap.put("Tree349", new Tree(1,-1));
		treeMap.put("Tree350", new Tree(2,-1));
		treeMap.put("Tree351", new Tree(3,-1));
		treeMap.put("Tree352", new Tree(4,-1));
		treeMap.put("Tree353", new Tree(5,-1));
		treeMap.put("Tree354", new Tree(1,-2));
		treeMap.put("Tree355", new Tree(2,-2));
		treeMap.put("Tree356", new Tree(3,-2));
		treeMap.put("Tree357", new Tree(4,-2));
		treeMap.put("Tree358", new Tree(5,-2));
		treeMap.put("Tree359", new Tree(2,-3));
		treeMap.put("Tree360", new Tree(3,-3));
		treeMap.put("Tree361", new Tree(4,-3));
		treeMap.put("Tree362", new Tree(2,-4));
		treeMap.put("Tree363", new Tree(3,-4));
		treeMap.put("Tree364", new Tree(4,-4));
		treeMap.put("Tree365", new Tree(-9,1));
		treeMap.put("Tree366", new Tree(-8,1));
		treeMap.put("Tree367", new Tree(-7,1));
		treeMap.put("Tree368", new Tree(-6,1));
		treeMap.put("Tree369", new Tree(-5,1));
		treeMap.put("Tree370", new Tree(-9,0));
		treeMap.put("Tree371", new Tree(-8, 0));
		treeMap.put("Tree372", new Tree(-7, 0));
		treeMap.put("Tree373", new Tree(-6, 0));
		treeMap.put("Tree374", new Tree(-5, 0));
		treeMap.put("Tree375", new Tree(-9,-1));
		treeMap.put("Tree376", new Tree(-8,-1));
		treeMap.put("Tree377", new Tree(-7,-1));
		treeMap.put("Tree378", new Tree(-6,-1));
		treeMap.put("Tree379", new Tree(-5,-1));
		treeMap.put("Tree380", new Tree(-3,1));
		treeMap.put("Tree381", new Tree(-3,1));
		treeMap.put("Tree382", new Tree(-3,-1));
		treeMap.put("Tree383", new Tree(-2,-1));
		treeMap.put("Tree384", new Tree(-3,-2));
		treeMap.put("Tree385", new Tree(-2,-2));
		treeMap.put("Tree386", new Tree(-2,-3));
		treeMap.put("Tree387", new Tree(-3,-3));
		treeMap.put("Tree388", new Tree(-3,-3));
		treeMap.put("Tree389", new Tree(-3,-2));
		treeMap.put("Tree390", new Tree(-3,-1));
		treeMap.put("Tree391", new Tree(-2,-1));
		treeMap.put("Tree392", new Tree(-2,-2));
		treeMap.put("Tree393", new Tree(-2,-3));
		treeMap.put("Tree394", new Tree(-2,-8));
		treeMap.put("Tree395", new Tree(-1,-8));
		treeMap.put("Tree396", new Tree(8, 0));
		treeMap.put("Tree397", new Tree(8,-1));
		treeMap.put("Tree398", new Tree(7,-6));
		treeMap.put("Tree399", new Tree(7,-6));
	}
	
	public String putInBirdBook(int i, String s){
		String message;
		if(i <0 || i >= 100){
			message = "Not in Range: 0-100";
			return message;
		}
		birdBook.set(i,s);
		message = "Successfully put bird in BirdBook.";
		return message;
	}
	
	public int findAddressInBirdBook(String address){
		//checks for if in BirdBook
		int ret = -1;
		for (int i =0; i < birdBook.size(); ++i){
			if (birdBook.get(i).equals(address)){
				ret = i;
				break;
			}
		}
		return ret;
	}
	
	public boolean doesBirdbookExist(int i){
		if(i >= birdBook.size() || i < 0){
			return false;
		}
		return birdBook.get(i) != null; //if birdbook[3] is null, will return false
	}
	
	public String readFromBirdBook(int i){
		if(doesBirdbookExist(i)){
			return birdBook.get(i);
		}else{
			return null; //check for null
		}
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
	public List<String> getTreesAtLocation(Point p){
		List<String> ret = new ArrayList<String>(treeMap.size());
		for (String key: treeMap.keySet()){
			if(treeMap.get(key).getLocation().equals(p)){
				ret.add(key);
			}
		}
		return ret;
	}
	
	public String getTreesAtLocationString(Point p){
		StringBuilder sb = new StringBuilder();
		String prefix = "";
		for(String tree: getTreesAtLocation(p)){
			sb.append(prefix);
			prefix = ", ";
			sb.append(tree);
		}
		return sb.toString();
	}
	
	public boolean isLake(Point p){
		if(p.equals(new Point(5,6)) || p.equals(new Point(5,1))|| p.equals(new Point(6,6)) ||
				p.equals(new Point(6,1))|| p.equals(new Point(4,5))|| p.equals(new Point(4,2))|| 
				p.equals(new Point(3,4))|| p.equals(new Point(3,3))|| p.equals(new Point(7,5))|| 
				p.equals(new Point(7,2))|| p.equals(new Point(8,4))|| p.equals(new Point(8,3)) ){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isClearing(Point p){
		if(p.equals(new Point(-8,8)) || p.equals(new Point(-8,7))|| p.equals(new Point(-8,6))|| 
								p.equals(new Point(-2,3))|| p.equals(new Point(-2,2)) ||
								p.equals(new Point(-1,3))|| p.equals(new Point(-1,2))
								|| p.equals(new Point(7,1)) || p.equals(new Point(8,1))
								|| p.equals(new Point(-6,-5))|| p.equals(new Point(-6,-6))|| p.equals(new Point(-6,-7))
								|| p.equals(new Point(-5,-5))|| p.equals(new Point(-5,-6))|| p.equals(new Point(-5,-7))
								){
			return true;
		}else{
			return false;
		}
	}
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
	
	public boolean doesNestExist(String nest){
		return nestMap.containsKey(nest);
	}
	
	public int getNumTrees(Point p){
		return getTreesAtLocation(p).size();
	}
	
	public Tree getTree(String treeName){
		return treeMap.get(treeName);
	}
	
	//Nest
	public Nest getNest(String nest){
		return nestMap.get(nest);
	}
	
	public String getNestsAtTree(Tree tree){
		StringBuilder sb = new StringBuilder();
		String prefix = "";
		for(String nest: tree.getNests()){
			sb.append(prefix);
			prefix = ", ";
			sb.append(nest);
		}
		return sb.toString();
	}
	
	public Nest pointerNest(String nestName){
		return nestMap.get(nestName);
	}
	
	public Point getNestLocation(String nestName){
		for(Tree tree: treeMap.values()){
			for(String currentNest: tree.getNests()){
				if(currentNest.equalsIgnoreCase(nestName)){
					return tree.getLocation();
				}
			}
		}
		//should not reach here unless nestName DNE
		return null;
	}
	
	public int locationNest(String nestName){
		//What Tree is this nest in? 
		return nestMap.get(nestName).getHeight();
	}
	
	public List<String> nestsInTree(String s){
		return treeMap.get(s).getNests();
	}
	
	public int howManyAddresses(){
		return birdBook.size();
	}
	
	//Birds
	public int birdsInSanctuary(){
		return birdsInSanctuary;
	}
	
	public void putBirdsInSanctuary(){
		birdsInSanctuary = birdsInSanctuary + birdsInCage;
	}
	
	public void putBirdsInCageFromNest(String nest){
		//zeros birds in nest and overwrites birds in cage
		birdsInCage = getNest(nest).getBird();
		getNest(nest).zeroBird();
	}
	
	public void putBirdsInCageFromTree(String tree){
		//**Tree takes stuff from all nests in tree
		Tree thisTree = getTree(tree);
		if (thisTree ==null){
			return;
		}
		//in each tree add all nests together and put in cage
		birdsInCage = 0;
		for (String nestName: thisTree.getNests()){
			birdsInCage += getNest(nestName).getBird();
			getNest(nestName).zeroBird();
		}
	}
	public boolean winState(){
		return winState;
	}
	public int birdsInCage(){
		return birdsInCage;
	}
}