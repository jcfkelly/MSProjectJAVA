package edu.ucsc;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class GameMainPanel extends JPanel{
	 
	 private final ImageIcon antsIcon = 		new ImageIcon("assets/MS_Project_Ants.png");
	 private final ImageIcon aphidsIcon = 		new ImageIcon("assets/MS_Project_Aphids.png");
	 private final ImageIcon beesIcon = 		new ImageIcon("assets/MS_Project_Bees.png");
	 private final ImageIcon frogsIcon = 		new ImageIcon("assets/MS_Project_Frogs.png");
	 private final ImageIcon gophersIcon = 		new ImageIcon("assets/MS_Project_Gophers.png");
	 private final ImageIcon waspsIcon = 		new ImageIcon("assets/MS_Project_Wasps.png");
	 private final ImageIcon butterfliesIcon =	new ImageIcon("assets/MS_Project_Butterflies.png");
	 private final ImageIcon ladybugsIcon = 	new ImageIcon("assets/MS_Project_Ladybugs.png");
	
	private static final long serialVersionUID = 1L;
	public final int MAX_INTRO_STATE=13;
	private int introState = -1;
	private final Dimension SIZE = new Dimension(400,270);

    public GameMainPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(Color.WHITE);
    }

    @Override
    public Dimension getPreferredSize() {
        return SIZE;
    }

    @Override
    public Dimension getMaximumSize() {
        return SIZE;
    }

    @Override
    public Dimension getMinimumSize() {
        return SIZE;
    }

    public void setIntroState(int state) {
    	this.introState = state;
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        switch (introState) {
            case 0:
                textPane.setText("Type Start or Press Next");
                break;
            case 1:
            	textPane.setText("Your Mission: (Should you chose to accept)\n\n"
            	+ "You own the local orchard. Given that you are new \n"
            	+ "to this you have a field guide (Guide) and an \n"
            	+ "Address Book (Book) to help you sort out the \n"
            	+ "orchard."
            	+ "\n\n Press Next to continue.");
            	break;
            case 2:
            	textPane.setText("The Guide lists all the pests and pollinators \n"
            	+ "that can be found on trees in the orchard. There \n"
            	+ "are six types of trees in the orchard: Apple, \n"
            	+ "Orange, Lemon, Lime, Cherry, and Nuts. There \n"
            	+ "is pesticide in a shed for each type of tree. \n"
            	+ "Each pesticide safe for a tree matches the ribbon \n"
            	+ "on the tree."
            	+ "\n\n Press Next to continue");
            	break;
            case 3:
            	textPane.setText("Your Mission (contd): \n\n"
            	+ "You have to be careful when applying the pesticide \n"
            	+ "to a tree though, while the pesticide will kill pests, \n"
            	+ "it will also kill polinators. Pollinators are good for \n"
            	+ "the orchard because a tree with pollinators cannot be \n"
            	+ "infested by pests. Use the Guide to find out whether \n"
            	+ "what you see on a tree is a pest or not."
            	+ "\n\n Press Next to continue");
            	break;
            case 4:
            	textPane.setText("Remember that spelling, capitalization, and spacing are important. \n"
            	+ "trees do not have spaces in their names (which are written \n"
            	+ "on the tree) and RedOak is more likely to be helpful than \n"
            	+ "Red Oak, red oak, redoak, or RadOak. Remember that you find \n"
            	+ "pests and pollinators, you do not put them in the trees."
            	+ "\n\n Press Next to continue.");
            	break;
            case 5:
            	textPane.setText("Help: Inventory \n\n"
            	+ "Your inventory has a Guide, and a Book. When the game \n"
            	+ "starts you will also see a counter, a season tracker, \n"
            	+ "and a jar of tree-safe pesticide. The Guide has drawings \n"
            	+ "of pests and pollinators. The Book has the address of \n"
            	+ "the Shed, and can store addresses of trees you think are \n"
            	+ "important. Remember to save your addresses before going to \n"
            	+ "Shed, becase if you do not save an address to walk back to, \n"
            	+ "you will be stuck in the Shed forever."
            	+ "\n\n Press Next to continue.");            	
            	break;
            case 6:
            	textPane.setText("Help: other commands \n\n"
            			+ "Help - sends you to the help section. \n\n"
            			+ "Exit - exits the help section. \n\n"
            			+ "Quit - quits the game \n\n"
            			+ "Guide - opens the field guide (capitalization matters)\n\n"
            			+ "Book - opens the address book (capitalization matters)\n\n"
            			+ "Turn - turns left or turns right\n\n"
            			+ "\n Press Next to continue.");
            	break;
            case 7:
            	textPane.setText("Help: Tree-Safe Pesticide and Colors \n\n"
            			+ "Apple is red, Orange is orange, Cherry is pink, \n"
            	+ "Nut is blue, Lemon is yellow, and Lime is green."
            	+ "\n remember using an Apple poison on an Orange tree \n"
            	+ "can kill the tree. Do not use an incorrect poison on the tree."
            	+ "\n\n Press Next to continue.");
            	break;
            case 8:

            	textPane.setText("Help: what about & and *? \n\n"
            	+ "There are two symbols which will help you in your attempt \n"
            	+ "to send pests to a merciful end: * and &. \n\n"
            	+ "If you are in the same area as an Apple \n"
            	+ "tree, you could use: \n"
            	+ "*Book = &Apple \n"
            	+ "to store the address of the Apple tree in your special address \n"
            	+ "book called Book. If you want to know what creature is living in \n"
            	+ "the Apple tree, you can use:\n"
            	+ "look *Apple \n"
            	+ "which tells you what pest or pollinator is in the Apple tree."
            	+ "\n\n Press Next to continue.");
            	break;
            case 9:
            	textPane.setText("HELP: = \n\n"
            			+ "Book only takes addresses. Use \n"
            			+ "of = also requires knowledge of * and & \n\n"
            			+ "*Book = &Apple "
            			+ "\n stores address of the Apple tree in Book. \n"
            			+ "*Sunstar = *Poison \n"
            			+ "puts contents of Poison in the Sunstar tree. \n"
            			+ "Poison holds different types of poison. \n"
            			+ "*Poison = *NutPoison \n"
            			+ "would store a serving of Nut pesticide in \n"
            			+ "the Poison container if you are in the Shed."
            			+ "\n\n Press Next to continue.");
            	break;
            case 10:
            	textPane.setText("HELP: Walk \n\n"
            			+ "Walk - You can walk forward and to addresses: \n "
            			+ "walk - you walk forward \n"
            			+ "walk &Apple - you walk to the address of the Apple tree. \n"
            			+ "walk north - you walk north \n"
            			+ "walk east - you walk east \n"
            			+ "walk south - you walk south \n"
            			+ "walk west - you walk west \n"
            			+ "turn right and turn left changes your orientation."
            			+ "\n\n Press Next to continue.");
            	break;
            case 11:
            	textPane.setText("Note: \n\n"
            	+ "If left too long (aka a season) pests will destroy a tree. \n"
            	+ "If your orchard loses 15% of the trees (approximately 12), \n"
            	+ "then you are in the red and cannot recover. Find the pests \n"
            	+ "and destroy them before they destroy your livilihood! \n\n"
            	+ "Remember that the command \n\n"
            	+ "walk &Shed \n\n"
            	+ "requires you to have an address in the Book to walk back to like:\n\n"
            	+ "walk &Sunstar"
            	+ "\n\n Press Next to continue.");
            	break;
            case 12:
            	textPane.setText("Use walk to explore the orchard and find the pests\n"
            			+ "and kill them with the appropriate tree-safe pesticide.\n"
            			+ "Remember that the pesticide (in the Poison container) \n"
            			+ "kills both pests and pollinators. \n"
            			+ "Also, Apple poison kills any tree that isn't an Apple tree.\n"
            			+ "Any tree-safe poison cannot be used on other trees."
            			+ "\n\n Press Next to continue.");
            	break;
            case 13:
            	textPane.setText("If you feel competent, press next or type \n"
            			+ "'Enter Orchard'\n\n"
            			+ "(If you need help during gameplay, type 'help' in \n"
            			+ "the textbar.)");
            	break;

        }
        removeAll();
        add(textPane);
        revalidate();
        repaint();

    }
    
    public void showTree(Tree tree){
    	removeAll();
		JLayeredPane treeLayer = new JLayeredPane();
		treeLayer.setPreferredSize(SIZE);
		treeLayer.setMinimumSize(SIZE);
		treeLayer.setMaximumSize(SIZE);
    	ImageIcon treeImageIcon = new ImageIcon(tree.getTreeSpecies());
		JLabel treeLabel = new JLabel();
		treeLabel.setSize(SIZE);
		treeLabel.setIcon(treeImageIcon);
		treeLayer.add(treeLabel, 0);
		JLabel residentLabel = new JLabel();
		residentLabel.setSize(SIZE);
		if (tree.getResident()==1){
			residentLabel.setIcon(antsIcon);
		}else if (tree.getResident()==2){
			residentLabel.setIcon(waspsIcon);
		}else if (tree.getResident()==3){
			residentLabel.setIcon(gophersIcon);
		}else if (tree.getResident()==4){
			residentLabel.setIcon(aphidsIcon);
		}else if (tree.getResident()==5){
			residentLabel.setIcon(butterfliesIcon);
		}else if (tree.getResident()==6){
			residentLabel.setIcon(beesIcon);
		}else if (tree.getResident()==7){
			residentLabel.setIcon(frogsIcon);
		}else if (tree.getResident()==8){
			residentLabel.setIcon(ladybugsIcon);
		}
		treeLayer.add(residentLabel, 0);
		add(treeLayer);
		revalidate();
		repaint();
	}
    
    public void showBook(ArrayList<String> addressBook){
    	introState = 14;
    	removeAll();
        DefaultListModel<String> bookModel = new DefaultListModel<>();
        for (String address : addressBook) {
            bookModel.addElement(address);
        }
        JList<String> book = new JList<>(bookModel);
        book.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(book);
        scrollPane.setPreferredSize(SIZE);
        scrollPane.setMinimumSize(SIZE);
        scrollPane.setMaximumSize(SIZE);
        add(scrollPane);
        revalidate();
        repaint();
    }
    
    public void showGuide(){
    	introState = 15;
    	removeAll();
    	JPanel guidePanel = new JPanel();
    	guidePanel.setPreferredSize(SIZE);
    	guidePanel.setMinimumSize(SIZE);
    	guidePanel.setMaximumSize(SIZE);
    	ImageIcon guideIcon = new ImageIcon("assets/MS_Project_insideFieldGuide.jpg");
    	JLabel guideLabel = new JLabel();
    	guideLabel.setSize(SIZE);
    	guideLabel.setIcon(guideIcon);
    	add(guideLabel);
    	revalidate();
    	repaint();
    }
    
    public void showShed(){
    	introState = 16;
    	removeAll();
    	JPanel shedPanel = new JPanel();
    	shedPanel.setPreferredSize(SIZE);
    	shedPanel.setMinimumSize(SIZE);
    	shedPanel.setMaximumSize(SIZE);
    	ImageIcon shedIcon = new ImageIcon("assets/MS_Project_poison.jpg");
    	JLabel shedLabel = new JLabel();
    	shedLabel.setSize(SIZE);
    	shedLabel.setIcon(shedIcon);
    	add(shedLabel);
    	revalidate();
    	repaint();
    }
    
    public void showEndCondition(boolean winState){
    	ImageIcon endConditionIcon;
    	introState=17;
    	removeAll();
    	JPanel endCondition = new JPanel();
    	endCondition.setPreferredSize(SIZE);
    	endCondition.setMinimumSize(SIZE);
    	endCondition.setMaximumSize(SIZE);
    	if(winState){
    		endConditionIcon = new ImageIcon("assets/MS_Project_WinScreen.jpg");
    	}else{
    		endConditionIcon = new ImageIcon("assets/MS_Project_LoseScreen.jpg");
    	}
    	JLabel labelCondition = new JLabel();
    	labelCondition.setSize(SIZE);
    	labelCondition.setIcon(endConditionIcon);
    	add(labelCondition);
    	revalidate();
    	repaint();
    }
    
    public boolean isInGame(){
    	return introState==-1;
    }
    
    public int getIntroState(){
    	return introState;
    }
    
}
