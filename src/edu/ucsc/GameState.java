package edu.ucsc;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class GameState{
	private final int MAX_X=4;
	private final int MIN_X=-4;
	private final int MAX_Y=10;
	private final int MIN_Y=0;
	private int direction = 0;
	private Point position;
	private ArrayList<String> treeNames;
	private ArrayList<String> addressBook; //this is where the things get stored in birdbook 
	private HashMap<String, Tree> treeMap;
	private boolean winState=false;
	private int steps=0;
	private int poison=1;
	
	//check whether winState ever works in debugging
	public GameState(){
		//complete reset in GameState
		position = new Point(0,0);
		treeNames = new ArrayList<String>(80);
		treeNames.add("Berna");
		treeNames.add("Calabrese");
		treeNames.add("Carvalhal");
		treeNames.add("Fukuhara"); 
		treeNames.add("Kona");
		treeNames.add("Tomango"); 
		treeNames.add("BonnieBrae"); 
		treeNames.add("FemmineloStTeresa"); 
		treeNames.add("Chinese Chestnut");
		treeNames.add("Cadanera");
		treeNames.add("Clanor");
		treeNames.add("Belladonna");
		treeNames.add("Gardner");
		treeNames.add("Macetera");
		treeNames.add("Verna");
		treeNames.add("Lemon");
		treeNames.add("Jhambiri");	
		treeNames.add("BlackWalnut");
		treeNames.add("ScarletNavel");
		treeNames.add("Tarocco");
		treeNames.add("CaraCara");
		treeNames.add("Homosassa");
		treeNames.add("Malta");
		treeNames.add("Vicieda");
		treeNames.add("Eureka");
		treeNames.add("Lisbon");
		treeNames.add("ShagbarkHickory");
		treeNames.add("Sanguinelli");
		treeNames.add("Moro");
		treeNames.add("Maltese");
		treeNames.add("Jaffa");
		treeNames.add("Marrs");
		treeNames.add("Westin");
		treeNames.add("Meyer");
		treeNames.add("Verna");
		treeNames.add("Almond");
		treeNames.add("DreamNavel");
		treeNames.add("LateNavel");
		treeNames.add("CaliforniaNavel");
		treeNames.add("Jincheng");
		treeNames.add("Midsweet");
		treeNames.add("Sorrento");
		treeNames.add("Ponderosa");
		treeNames.add("YenBen");
		treeNames.add("Chestnut");
		treeNames.add("Bahianinha");
		treeNames.add("SugarOrange");
		treeNames.add("BloodOrange");
		treeNames.add("Mosambi");
		treeNames.add("ParsonBrown");
		treeNames.add("KaffirLime");
		treeNames.add("VariegatedPink");
		treeNames.add("AmericanHazelnut");
		treeNames.add("Macadamia");
		treeNames.add("Orange");
		treeNames.add("Tangerine");
		treeNames.add("NavelOrange");
		treeNames.add("Pera");
		treeNames.add("Roble");
		treeNames.add("KeyLime");
		treeNames.add("RangpurLime");
		treeNames.add("NorthernHardyPecan");
		treeNames.add("Pecan");
		treeNames.add("GoldenDelicious");
		treeNames.add("PacificRose");
		treeNames.add("YellowTransparent");
		treeNames.add("PineappleOrange");
		treeNames.add("Queen");
		treeNames.add("MuskLime");
		treeNames.add("SpanishLime");
		treeNames.add("RedOak");
		treeNames.add("Walnut");
		treeNames.add("Goldenrette");
		treeNames.add("ArkansasBlack");
		treeNames.add("Sampion");
		treeNames.add("RhodeRed");
		treeNames.add("Sunstar");
		treeNames.add("PersianLime");
		treeNames.add("Limequat");
		treeNames.add("HoneyLocust");
		treeNames.add("Pistachio");
		treeNames.add("Fuji");
		treeNames.add("Gala");
		treeNames.add("RedDelicious");
		treeNames.add("PinkLady");
		treeNames.add("Ananasrenette");
		treeNames.add("Accolade");
		treeNames.add("Colorata");
		treeNames.add("Cherry");
		treeNames.add("Shirotai");
		treeNames.add("Bramley");
		treeNames.add("Washington");
		treeNames.add("Granny");
		treeNames.add("McIntosh");
		treeNames.add("Apple");
		treeNames.add("Amanogawa");
		treeNames.add("Kanzan");
		treeNames.add("Morello");
		treeNames.add("Ukon");

		addressBook = new ArrayList<String>(100); //ensures size is limited
		for(int i =0 ; i<100; i++){ //all empty strings
			addressBook.add(null);
		}
		addressBook.add(0, "&Shed");

		//Create Trees (Put on one line)
		treeMap = new HashMap<String, Tree>(); //contains names as keys
		treeMap.put("Apple", new Tree(0,0,0, "assets/MS_Project_Trees_apple.jpg"));
		treeMap.put("Ananasrenette", new Tree(0,1,0, "assets/MS_Project_Trees_ananasrenette.jpg"));
		treeMap.put("Amanogawa", new Tree(1,0,0, "assets/MS_Project_Trees_amanogawa.jpg"));
		treeMap.put("Bahianinha", new Tree(1,1,0, "assets/MS_Project_Trees_bahianinha.jpg"));
		treeMap.put("Accolade", new Tree(1,1,0, "assets/MS_Project_Trees_accolade.jpg"));
		treeMap.put("Almond", new Tree(4,7,0, "assets/MS_Project_Trees_Almond.jpg"));
		treeMap.put("AmericanHazelnut", new Tree(3,5,0, "assets/MS_Project_Trees_AmericanHazelnut.jpg"));
		treeMap.put("ArkansasBlack", new Tree(-3,2,0, "assets/MS_Project_Trees_ArkansasBlack.jpg"));
		treeMap.put("Bahianinha", new Tree(-4,5,0, "assets/MS_Project_Trees_bahianinha.jpg"));
		treeMap.put("Belladonna", new Tree(-2,8,0, "assets/MS_Project_Trees_Belladonna.jpg"));
		treeMap.put("Berna", new Tree(-4,10,0, "assets/MS_Project_Trees_Berna.jpg"));
		treeMap.put("BlackWalnut", new Tree(4,9,0, "assets/MS_Project_Trees_BlackWalnut.jpg"));
		treeMap.put("BloodOrange", new Tree(-2,5,0, "assets/MS_Project_Trees_BloodOrange.jpg"));
		treeMap.put("BonnieBrae", new Tree(2,10,0, "assets/MS_Project_Trees_BonnieBrae.jpg"));
		treeMap.put("Bramley", new Tree(-4,0,0, "assets/MS_Project_Trees_Bramley.jpg"));
		treeMap.put("Cadanera", new Tree(-4,9,0, "assets/MS_Project_Trees_Cadanera.jpg"));
		treeMap.put("Calabrese", new Tree(-3,10,0, "assets/MS_Project_Trees_Calabrisi.jpg"));
		treeMap.put("CaliforniaNavel", new Tree(-2,6,0, "assets/MS_Project_Trees_CaliforniaNavel.jpg"));
		treeMap.put("CaraCara", new Tree(-2,8,0, "assets/MS_Project_Trees_CaraCara.jpg"));
		treeMap.put("Carvalhal", new Tree(-2,10,0, "assets/MS_Project_Trees_Carvalhal.jpg"));
		treeMap.put("Cherry", new Tree(3,1,0, "assets/MS_Project_Trees_Cherry.jpg"));
		treeMap.put("Chestnut", new Tree(4,6,0, "assets/MS_Project_Trees_Chestnut.jpg"));
		treeMap.put("ChineseChestnut", new Tree(4,10,0, "assets/MS_Project_Trees_ChineseChestnut.jpg"));
		treeMap.put("Clanor", new Tree(-3,9,0, "assets/MS_Project_Trees_Clanor.jpg"));		
		treeMap.put("Colorata", new Tree(2,1,0, "assets/MS_Project_Trees_Colorata.jpg"));
		treeMap.put("DreamNavel", new Tree(-4,6,0, "assets/MS_Project_Trees_DreamNavel.jpg"));
		treeMap.put("Eureka", new Tree(2,8,0, "assets/MS_Project_Trees_Eureka.jpg"));
		treeMap.put("FemmineloStTeresa", new Tree(3,10,0, "assets/MS_Project_Trees_FemmineloStTeresa.jpg"));
		treeMap.put("Fuji", new Tree(-4,1,0, "assets/MS_Project_Trees_Fuji.jpg"));
		treeMap.put("Fukuhara", new Tree(-1,10,0, "assets/MS_Project_Trees_Fukuhara.jpg"));
		treeMap.put("Gala", new Tree(-3,1,0, "assets/MS_Project_Trees_Gala.jpg"));
		treeMap.put("Gardner", new Tree(-1,9,0, "assets/MS_Project_Trees_Gardner.jpg"));
		treeMap.put("GoldenDelicious", new Tree(-4,3,0, "assets/MS_Project_Trees_GoldenDelicious.jpg"));
		treeMap.put("Goldenrette", new Tree(-4,2,0, "assets/MS_Project_Trees_Goldenrette.jpg"));
		treeMap.put("Granny", new Tree(-2,0,0, "assets/MS_Project_Trees_Granny.jpg"));
		treeMap.put("Homosassa", new Tree(-1,8,0, "assets/MS_Project_Trees_Homosassa.jpg"));
		treeMap.put("HoneyLocust", new Tree(3,2,0, "assets/MS_Project_Trees_HoneyLocust.jpg"));
		treeMap.put("Jaffa", new Tree(1,7,0, "assets/MS_Project_Trees_Jaffa.jpg"));
		treeMap.put("Jhambiri", new Tree(0,0,0, "assets/MS_Project_Trees_Jhambiri.jpg"));
		treeMap.put("Jincheng", new Tree(1,6,0, "assets/MS_Project_Trees_Jincheng.jpg"));
		treeMap.put("KaffirLime", new Tree(1,5,0, "assets/MS_Project_Trees_KaffirLime.jpg"));
		treeMap.put("Kanzan", new Tree(2,0,0, "assets/MS_Project_Trees_Kanzan.jpg"));
		treeMap.put("KeyLime", new Tree(1,4,0, "assets/MS_Project_Trees_KeyLime.jpg"));
		treeMap.put("Kona", new Tree(0,10,0, "assets/MS_Project_Trees_Kona.jpg"));
		treeMap.put("LateNavel", new Tree(-3,6,0, "assets/MS_Project_Trees_LateNavel.jpg"));
		treeMap.put("Lemon", new Tree(2,9,0, "assets/MS_Project_Trees_Lemon.jpg"));
		treeMap.put("Limequat", new Tree(2,2,0, "assets/MS_Project_Trees_Limequat.jpg"));
		treeMap.put("Lisbon", new Tree(3,8,0, "assets/MS_Project_Trees_Lisbon.jpg"));
		treeMap.put("Macadamia", new Tree(4,5,0, "assets/MS_Project_Trees_Macadamia.jpg"));
		treeMap.put("Macetera", new Tree(0,9,0, "assets/MS_Project_Trees_Macetera.jpg"));
		treeMap.put("Malta", new Tree(0,8,0, "assets/MS_Project_Trees_Malta.jpg"));
		treeMap.put("Maltese", new Tree(-2,7,0, "assets/MS_Project_Trees_Maltese.jpg"));
		treeMap.put("Marrs", new Tree(0,0,7, "assets/MS_Project_Trees_Marrs.jpg"));
		treeMap.put("McIntosh", new Tree(-1,0,0, "assets/MS_Project_Trees_McIntosh.jpg"));
		treeMap.put("Meyer", new Tree(2,7,0, "assets/MS_Project_Trees_Meyer.jpg"));
		treeMap.put("Midsweet", new Tree(0,6,0, "assets/MS_Project_Trees_Midsweet.jpg"));
		treeMap.put("Morello", new Tree(3,0,0, "assets/MS_Project_Trees_Morello.jpg"));
		treeMap.put("Moro", new Tree(-3,7,0, "assets/MS_Project_Trees_Moro.jpg"));
		treeMap.put("Mosambi", new Tree(-1,5,0, "assets/MS_Project_Trees_Mosambi.jpg"));
		treeMap.put("MuskLime", new Tree(1,3,0, "assets/MS_Project_Trees_MuskLime.jpg"));
		treeMap.put("NavelOrange", new Tree(-2,4,0, "assets/MS_Project_Trees_NavelOrange.jpg"));
		treeMap.put("NorthernHardyPecan", new Tree(3,4,0, "assets/MS_Project_Trees_Northern-hardyPecan.jpg"));
		treeMap.put("Orange", new Tree(-4,4,0, "assets/MS_Project_Trees_Orange.jpg"));
		treeMap.put("PacificRose", new Tree(-3,3,0, "assets/MS_Project_Trees_PacificRose.jpg"));
		treeMap.put("ParsonBrown", new Tree(0,5,0, "assets/MS_Project_Trees_ParsonBrown.jpg"));
		treeMap.put("Pecan", new Tree(4,4,0, "assets/MS_Project_Trees_Pecan.jpg"));
		treeMap.put("Pera", new Tree(1,4,0, "assets/MS_Project_Trees_Pera.jpg"));
		treeMap.put("PersianLime", new Tree(1,2,0, "assets/MS_Project_Trees_PersianLime.jpg"));
		treeMap.put("PineappleOrange", new Tree(1,3,0, "assets/MS_Project_Trees_PineappleOrange.jpg"));
		treeMap.put("PinkLady", new Tree(-1,1,0, "assets/MS_Project_Trees_PinkLady.jpg"));
		treeMap.put("Pistachio", new Tree(4,2,0, "assets/MS_Project_Trees_Pistachio.jpg"));
		treeMap.put("Ponderosa", new Tree(2,6,0, "assets/MS_Project_Trees_Ponderosa.jpg"));
		treeMap.put("Queen", new Tree(0,3,0, "assets/MS_Project_Trees_Queen.jpg"));
		treeMap.put("RangpurLime", new Tree(2,4,0, "assets/MS_Project_Trees_RangpurLime.jpg"));
		treeMap.put("RedDelicious", new Tree(-2,1,0, "assets/MS_Project_Trees_RedDelicious.jpg"));
		treeMap.put("RedOak", new Tree(3,3,0, "assets/MS_Project_Trees_RedOak.jpg"));
		treeMap.put("RhodeRed", new Tree(-1,2,0, "assets/MS_Project_Trees_RhodeRed.jpg"));
		treeMap.put("Roble", new Tree(0,4,0, "assets/MS_Project_Trees_Roble.jpg"));
		treeMap.put("Sampion", new Tree(-2,2,0, "assets/MS_Project_Trees_Sampion.jpg"));
		treeMap.put("Sanguinelli", new Tree(-4,7,0, "assets/MS_Project_Trees_Sanguinelli.jpg"));
		treeMap.put("ScarletNavel", new Tree(-4,8,0, "assets/MS_Project_Trees_ScarletNavel.jpg"));
		treeMap.put("ShagbarkHickory", new Tree(4,8,0, "assets/MS_Project_Trees_ShagbarkHickory.jpg"));
		treeMap.put("Shirotai", new Tree(4,1,0, "assets/MS_Project_Trees_Shirotai.jpg"));
		treeMap.put("Sorrento", new Tree(1,6,0, "assets/MS_Project_Trees_Sorrento.jpg"));
		treeMap.put("SpanishLime", new Tree(2,3,0, "assets/MS_Project_Trees_SpanishLime.jpg"));
		treeMap.put("SugarOrange", new Tree(-3,5,0, "assets/MS_Project_Trees_SugarOrange.jpg"));
		treeMap.put("Sunstar", new Tree(0,2,0, "assets/MS_Project_Trees_Sunstar.jpg"));
		treeMap.put("Tangerine", new Tree(-3,4,0, "assets/MS_Project_Trees_Tangerine.jpg"));
		treeMap.put("Tarocco", new Tree(-3,8,0, "assets/MS_Project_Trees_Tarocco.jpg"));
		treeMap.put("Tomango", new Tree(1,10,0, "assets/MS_Project_Trees_Tomango.jpg"));
		treeMap.put("Ukon", new Tree(4,0,0, "assets/MS_Project_Trees_Ukon.jpg"));
		treeMap.put("VariegatedPink", new Tree(2,5,0, "assets/MS_Project_Trees_VariegatedPink.jpg"));
		treeMap.put("Verna", new Tree(1,9,0, "assets/MS_Project_Trees_Verna.jpg"));
		treeMap.put("Vicieda", new Tree(1,8,0, "assets/MS_Project_Trees_Vicieda.jpg"));
		treeMap.put("Walnut", new Tree(4,3,0, "assets/MS_Project_Trees_Walnut.jpg"));
		treeMap.put("Washinton", new Tree(-3,0,0, "assets/MS_Project_Trees_Washington.jpg"));
		treeMap.put("Westin", new Tree(1,7,0, "assets/MS_Project_Trees_Westin.jpg"));
		treeMap.put("YellowTransparent", new Tree(-2,3,0, "assets/MS_Project_Trees_YellowTransparent.jpg"));
		treeMap.put("YenBen", new Tree(3,6,0, "assets/MS_Project_Trees_YenBen.jpg"));
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
	
	public int takeStep(){
		return steps += 1;
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
		if(p.x < -4 || p.x > 4 || p.y < 0 || p.y >10){
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
	
	public int getPoison(){
		return poison;
	}
	
	public void changePoison(int i){
		poison = i;
	}
	
	public boolean winState(){
		return winState;
	}

}