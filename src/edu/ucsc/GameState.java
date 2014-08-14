package edu.ucsc;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GameState{
	private boolean changeSeason=false;
	private int apple=1;
	private int orange=2;
	private int cherry=3;
	private int nut=4;
	private int lemon=5;
	private int lime=6;
	private final int MAX_X=4;
	private final int MIN_X=-4;
	

	private final int MAX_Y=10;
	private final int MIN_Y=0;
	private int direction = 0;
	private Point position;
	private ArrayList<String> addressBook; //this is where the things get stored in birdbook 
	private HashMap<String, Tree> treeMap;
	private boolean winState=false;
	private int steps=0; //COUNTER
	private int season=0; //SEASON 
	private int poison=1;
	private int alreadyEnteredOrchard=0;
	private int deadTrees=0;

	//TODO: create win condition

	

	public GameState(){
		//complete reset in GameState
		position = new Point(0,0);

		addressBook = new ArrayList<String>(100); //ensures size is limited
		addressBook.add("&Shed");

		//Create Trees (Put on one line)
		treeMap = new HashMap<String, Tree>(); //contains names as keys
		//TODO: align
		treeMap.put("Apple", 				new Tree(0,0, apple, "/orchard-game/assets/MS_Project_Trees_apple.jpg"));
		treeMap.put("Ananasrenette",	 	new Tree(0,1, apple, "/orchard-game/assets/MS_Project_Trees_ananasrenette.jpg"));
		treeMap.put("Amanogawa", 			new Tree(1,0, cherry, "/orchard-game/assets/MS_Project_Trees_amanogawa.jpg"));
		treeMap.put("Accolade", 			new Tree(1,1, cherry, "/orchard-game/assets/MS_Project_Trees_accolade.jpg"));
		treeMap.put("Almond", 				new Tree(4,7, nut, "/orchard-game/assets/MS_Project_Trees_Almond.jpg"));
		treeMap.put("AmericanHazelnut", 	new Tree(3,5, nut, "/orchard-game/assets/MS_Project_Trees_AmericanHazelnut.jpg"));
		treeMap.put("ArkansasBlack",		new Tree(-3,2, apple, "/orchard-game/assets/MS_Project_Trees_ArkansasBlack.jpg"));
		treeMap.put("Bahianinha", 			new Tree(-4,5, orange, "/orchard-game/assets/MS_Project_Trees_bahianinha.jpg"));
		treeMap.put("Belladonna",	 		new Tree(-2,9, orange, "/orchard-game/assets/MS_Project_Trees_Belladonna.jpg"));
		treeMap.put("Berna", 				new Tree(-4,10, orange, "/orchard-game/assets/MS_Project_Trees_Berna.jpg"));
		treeMap.put("BlackWalnut",			new Tree(4,9, nut, "/orchard-game/assets/MS_Project_Trees_BlackWalnut.jpg"));
		treeMap.put("BloodOrange",	 		new Tree(-2,5, orange, "/orchard-game/assets/MS_Project_Trees_BloodOrange.jpg"));
		treeMap.put("BonnieBrae", 			new Tree(2,10, lemon,"/orchard-game/assets/MS_Project_Trees_BonnieBrae.jpg"));
		treeMap.put("Bramley", 				new Tree(-4,0, apple,"/orchard-game/assets/MS_Project_Trees_Bramley.jpg"));
		treeMap.put("Cadanera", 			new Tree(-4,9, orange,"/orchard-game/assets/MS_Project_Trees_Cadanera.jpg"));
		treeMap.put("Calabrese", 			new Tree(-3,10, orange, "/orchard-game/assets/MS_Project_Trees_Calabrisi.jpg"));
		treeMap.put("CaliforniaNavel", 		new Tree(-2,6, orange, "/orchard-game/assets/MS_Project_Trees_CaliforniaNavel.jpg"));
		treeMap.put("CaraCara", 			new Tree(-2,8, orange, "/orchard-game/assets/MS_Project_Trees_CaraCara.jpg"));
		treeMap.put("Carvalhal", 			new Tree(-2,10, orange, "/orchard-game/assets/MS_Project_Trees_Carvalhal.jpg"));
		treeMap.put("Cherry", 				new Tree(3,1, cherry, "/orchard-game/assets/MS_Project_Trees_Cherry.jpg"));
		treeMap.put("Chestnut", 			new Tree(4,6, nut, "/orchard-game/assets/MS_Project_Trees_Chestnut.jpg"));
		treeMap.put("ChineseChestnut", 		new Tree(4,10, nut, "/orchard-game/assets/MS_Project_Trees_ChineseChestnut.jpg"));
		treeMap.put("Clanor", 				new Tree(-3,9, orange,"/orchard-game/assets/MS_Project_Trees_Clanor.jpg"));		
		treeMap.put("Colorata", 			new Tree(2,1, cherry,"/orchard-game/assets/MS_Project_Trees_Colorata.jpg"));
		treeMap.put("DreamNavel", 			new Tree(-4,6, orange,  "/orchard-game/assets/MS_Project_Trees_DreamNavel.jpg"));
		treeMap.put("Eureka", 				new Tree(2,8, lemon, "/orchard-game/assets/MS_Project_Trees_Eureka.jpg"));
		treeMap.put("FemmineloStTeresa", 	new Tree(3,10, lemon, "/orchard-game/assets/MS_Project_Trees_FemmineloStTeresa.jpg"));
		treeMap.put("Fuji", 				new Tree(-4,1, apple,"/orchard-game/assets/MS_Project_Trees_Fuji.jpg"));
		treeMap.put("Fukuhara", 			new Tree(-1,10, orange, "/orchard-game/assets/MS_Project_Trees_Fukuhara.jpg"));
		treeMap.put("Gala", 				new Tree(-3,1, apple,"/orchard-game/assets/MS_Project_Trees_Gala.jpg"));
		treeMap.put("Gardner", 				new Tree(-1,9, orange, "/orchard-game/assets/MS_Project_Trees_Gardner.jpg"));
		treeMap.put("GoldenDelicious",		new Tree(-4,3, apple, "/orchard-game/assets/MS_Project_Trees_GoldenDelicious.jpg"));
		treeMap.put("Goldenrette", 			new Tree(-4,2, apple, "/orchard-game/assets/MS_Project_Trees_Goldenrette.jpg"));
		treeMap.put("Granny", 				new Tree(-2,0, apple, "/orchard-game/assets/MS_Project_Trees_Granny.jpg"));
		treeMap.put("Homosassa", 			new Tree(-1,8, orange, "/orchard-game/assets/MS_Project_Trees_Homosassa.jpg"));
		treeMap.put("HoneyLocust", 			new Tree(3,2, nut, "/orchard-game/assets/MS_Project_Trees_HoneyLocust.jpg"));
		treeMap.put("Jaffa", 				new Tree(-1,7, orange, "/orchard-game/assets/MS_Project_Trees_Jaffa.jpg"));
		treeMap.put("Jhambiri", 			new Tree(3,9, orange, "/orchard-game/assets/MS_Project_Trees_Jhambiri.jpg"));
		treeMap.put("Jincheng", 			new Tree(-1,6, orange, "/orchard-game/assets/MS_Project_Trees_Jincheng.jpg"));
		treeMap.put("KaffirLime", 			new Tree(1,5, lime, "/orchard-game/assets/MS_Project_Trees_KaffirLime.jpg"));
		treeMap.put("Kanzan", 				new Tree(2,0, cherry, "/orchard-game/assets/MS_Project_Trees_Kanzan.jpg"));
		treeMap.put("KeyLime", 				new Tree(1,4, lime, "/orchard-game/assets/MS_Project_Trees_KeyLime.jpg"));
		treeMap.put("Kona", 				new Tree(0,10, orange, "/orchard-game/assets/MS_Project_Trees_Kona.jpg"));
		treeMap.put("LateNavel", 			new Tree(-3,6, orange, "/orchard-game/assets/MS_Project_Trees_LateNavel.jpg"));
		treeMap.put("Lemon", 				new Tree(2,9, lemon, "/orchard-game/assets/MS_Project_Trees_Lemon.jpg"));
		treeMap.put("Limequat", 			new Tree(2,2, lime, "/orchard-game/assets/MS_Project_Trees_Limequat.jpg"));
		treeMap.put("Lisbon", 				new Tree(3,8, lemon, "/orchard-game/assets/MS_Project_Trees_Lisbon.jpg"));
		treeMap.put("Macadamia", 			new Tree(4,5, nut, "/orchard-game/assets/MS_Project_Trees_Macadamia.jpg"));
		treeMap.put("Macetera", 			new Tree(0,9, orange, "/orchard-game/assets/MS_Project_Trees_Macetera.jpg"));
		treeMap.put("Malta", 				new Tree(0,8, orange,"/orchard-game/assets/MS_Project_Trees_Malta.jpg"));
		treeMap.put("Maltese", 				new Tree(-2,7, orange, "/orchard-game/assets/MS_Project_Trees_Maltese.jpg"));
		treeMap.put("Marrs", 				new Tree(0,7, orange,"/orchard-game/assets/MS_Project_Trees_Marrs.jpg"));
		treeMap.put("McIntosh", 			new Tree(-1,0, apple, "/orchard-game/assets/MS_Project_Trees_McIntosh.jpg"));
		treeMap.put("Meyer", 				new Tree(2,7, lemon, "/orchard-game/assets/MS_Project_Trees_Meyer.jpg"));
		treeMap.put("Midsweet", 			new Tree(0,6, orange, "/orchard-game/assets/MS_Project_Trees_Midsweet.jpg"));
		treeMap.put("Morello", 				new Tree(3,0, cherry, "/orchard-game/assets/MS_Project_Trees_Morello.jpg"));
		treeMap.put("Moro", 				new Tree(-3,7, orange, "/orchard-game/assets/MS_Project_Trees_Moro.jpg"));
		treeMap.put("Mosambi", 				new Tree(-1,5, orange, "/orchard-game/assets/MS_Project_Trees_Mosambi.jpg"));
		treeMap.put("MuskLime", 			new Tree(1,3, lime, "/orchard-game/assets/MS_Project_Trees_MuskLime.jpg"));
		treeMap.put("NavelOrange", 			new Tree(-2,4, orange, "/orchard-game/assets/MS_Project_Trees_NavelOrange.jpg"));
		treeMap.put("NorthernHardyPecan", 	new Tree(3,4, nut, "/orchard-game/assets/MS_Project_Trees_Northern-hardyPecan.jpg"));
		treeMap.put("Orange", 				new Tree(-4,4, orange, "/orchard-game/assets/MS_Project_Trees_Orange.jpg"));
		treeMap.put("PacificRose", 			new Tree(-3,3, apple, "/orchard-game/assets/MS_Project_Trees_PacificRose.jpg"));
		treeMap.put("ParsonBrown", 			new Tree(0,5, orange, "/orchard-game/assets/MS_Project_Trees_ParsonBrown.jpg"));
		treeMap.put("Pecan", 				new Tree(4,4, nut, "/orchard-game/assets/MS_Project_Trees_Pecan.jpg"));
		treeMap.put("Pera", 				new Tree(-1,4, orange, "/orchard-game/assets/MS_Project_Trees_Pera.jpg"));
		treeMap.put("PersianLime", 			new Tree(1,2, lime, "/orchard-game/assets/MS_Project_Trees_PersianLime.jpg"));
		treeMap.put("PineappleOrange", 		new Tree(-1,3, orange, "/orchard-game/assets/MS_Project_Trees_PineappleOrange.jpg"));
		treeMap.put("PinkLady", 			new Tree(-1,1, apple, "/orchard-game/assets/MS_Project_Trees_PinkLady.jpg"));
		treeMap.put("Pistachio", 			new Tree(4,2, nut, "/orchard-game/assets/MS_Project_Trees_Pistachio.jpg"));
		treeMap.put("Ponderosa", 			new Tree(2,6, lemon, "/orchard-game/assets/MS_Project_Trees_Ponderosa.jpg"));
		treeMap.put("Queen", 				new Tree(0,3, orange, "/orchard-game/assets/MS_Project_Trees_Queen.jpg"));
		treeMap.put("RangpurLime", 			new Tree(2,4, lime, "/orchard-game/assets/MS_Project_Trees_RangpurLime.jpg"));
		treeMap.put("RedDelicious", 		new Tree(-2,1, apple, "/orchard-game/assets/MS_Project_Trees_RedDelicious.jpg"));
		treeMap.put("RedOak", 				new Tree(3,3, nut, "/orchard-game/assets/MS_Project_Trees_RedOak.jpg"));
		treeMap.put("RhodeRed", 			new Tree(-1,2, orange, "/orchard-game/assets/MS_Project_Trees_RhodeRed.jpg"));
		treeMap.put("Roble", 				new Tree(0,4, orange, "/orchard-game/assets/MS_Project_Trees_Roble.jpg"));
		treeMap.put("Sampion", 				new Tree(-2,2, apple, "/orchard-game/assets/MS_Project_Trees_Sampion.jpg"));
		treeMap.put("Sanguinelli", 			new Tree(-4,7, orange, "/orchard-game/assets/MS_Project_Trees_Sanguinelli.jpg"));
		treeMap.put("ScarletNavel", 		new Tree(-4,8, orange, "/orchard-game/assets/MS_Project_Trees_ScarletNavel.jpg"));
		treeMap.put("ShagbarkHickory", 		new Tree(4,8, nut, "/orchard-game/assets/MS_Project_Trees_ShagbarkHickory.jpg"));
		treeMap.put("Shirotai", 			new Tree(4,1, cherry, "/orchard-game/assets/MS_Project_Trees_Shirotai.jpg"));
		treeMap.put("Sorrento", 			new Tree(1,6, lemon, "/orchard-game/assets/MS_Project_Trees_Sorrento.jpg"));
		treeMap.put("SpanishLime", 			new Tree(2,3, lime, "/orchard-game/assets/MS_Project_Trees_SpanishLime.jpg"));
		treeMap.put("SugarOrange", 			new Tree(-3,5, orange, "/orchard-game/assets/MS_Project_Trees_SugarOrange.jpg"));
		treeMap.put("Sunstar", 				new Tree(0,2, orange, "/orchard-game/assets/MS_Project_Trees_Sunstar.jpg"));
		treeMap.put("Tangerine", 			new Tree(-3,4, orange, "/orchard-game/assets/MS_Project_Trees_Tangerine.jpg"));
		treeMap.put("Tarocco", 				new Tree(-3,8, orange, "/orchard-game/assets/MS_Project_Trees_Tarocco.jpg"));
		treeMap.put("Tomango", 				new Tree(1,10, orange, "/orchard-game/assets/MS_Project_Trees_Tomango.jpg"));
		treeMap.put("Ukon", 				new Tree(4,0, cherry, "/orchard-game/assets/MS_Project_Trees_Ukon.jpg"));
		treeMap.put("VariegatedPink", 		new Tree(2,5, lemon, "/orchard-game/assets/MS_Project_Trees_VariegatedPink.jpg"));
		treeMap.put("Verna", 				new Tree(3,7, lemon, "/orchard-game/assets/MS_Project_Trees_Verna.jpg"));
		treeMap.put("Vicieda", 				new Tree(1,8, orange, "/orchard-game/assets/MS_Project_Trees_Vicieda.jpg"));
		treeMap.put("Walnut", 				new Tree(4,3, nut, "/orchard-game/assets/MS_Project_Trees_Walnut.jpg"));
		treeMap.put("Washington", 			new Tree(-3,0, apple,"/orchard-game/assets/MS_Project_Trees_Washington.jpg"));
		treeMap.put("Westin", 				new Tree(1,7, orange, "/orchard-game/assets/MS_Project_Trees_Westin.jpg"));
		treeMap.put("YellowTransparent",	new Tree(-2,3, apple, "/orchard-game/assets/MS_Project_Trees_YellowTransparent.jpg"));
		treeMap.put("YenBen", 				new Tree(3,6, lemon, "/orchard-game/assets/MS_Project_Trees_YenBen.jpg"));
		insertRandomPests(6);
	}
	
	
	
	public ArrayList<String> getAddressBook() {
		return addressBook;
	}

	public int getAlreadyEnteredOrchard() {
		return alreadyEnteredOrchard;
	}

	public void setAlreadyEnteredOrchard(int alreadyEnteredOrchard) {
		this.alreadyEnteredOrchard = alreadyEnteredOrchard;
	}
	
	public int getDeadTrees() {
		return deadTrees;
	}

	public void setDeadTrees(int deadTrees) {
		this.deadTrees = deadTrees;
	}
	
	public void insertRandomPollinator(){
		Random random = new Random();
		List<Tree> treeList=new ArrayList<>(treeMap.values());
		Tree randomTree = treeList.get(random.nextInt(treeList.size()));
		int randomPollinator;
		while (randomTree.getResident()!=0){
			randomTree = treeList.get(random.nextInt(treeList.size()));
		}
		randomPollinator= random.nextInt(4)+5;
		randomTree.setResident(randomPollinator);
	}
	
	public void insertRandomPests(int numTrees){
		Random random = new Random();
		List<Tree> treeList=new ArrayList<>(treeMap.values());
		Tree randomTree = treeList.get(random.nextInt(treeList.size()));
		int randomPest;
		
		for (int i =0; i < numTrees; i++){
			while (randomTree.getResident()!=0){
				randomTree = treeList.get(random.nextInt(treeList.size()));
			}
			randomPest = random.nextInt(4)+1;
			randomTree.setSeason(season);
			randomTree.setResident(randomPest);
		}
	}
	
	public void advanceSeason(){
		season+=1;
	}
	
	public int getSeason(){
		return season;
	}
	
	public int getSteps() {
		return steps;
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
	
	public void addToAddressBook(String s){
		addressBook.add(s);
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
	
	public int takeSteps(){
		return steps+=3;
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
	
	public boolean isTreeDead(){
		for(String treeName : treeMap.keySet()){
			if (season > treeMap.get(treeName).getSeason() && treeMap.get(treeName).getResident()!=0 && 5>treeMap.get(treeName).getResident()){
				treeMap.get(treeName).setTreeSpecies();
				deadTrees +=1;
				return true;
			}
		}
		return false;
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
	
	public String getPoisonString(int poisonTree){
		switch(poisonTree){
		case 1:
			return "Apple";
		case 2:
			return "Orange";
		case 3: 
			return "Cherry";
		case 4: 
			return "Nut";
		case 5:
			return "Lemon";
		default:
			return "Lime";
		}
	}
	
	public void changePoison(int i){
		poison = i;
	}
	
	public boolean changeSeason(boolean thisSeason){
		changeSeason = thisSeason;
		return changeSeason;
	}

	public boolean getChangeSeason(){
		return changeSeason;
	}
	
	public boolean winState(){
		if(getSteps()%200==0 && getSteps()!=0){
			advanceSeason();
			if(getSeason()==5){
				winState = true;
			}else{
				winState = false;
			}
		}
		return winState;
	}

}