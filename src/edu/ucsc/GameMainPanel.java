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
	public final int MAX_INTRO_STATE=11;
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
        switch (introState) {
            case 0:
                textPane.setText("Type Start or Next");
                break;
            case 1:
            	textPane.setText("Your Mission: (Should you chose to accept)\n\n"
            			+ "You own the local orchard. Given \n"
            			+ "that you are new to this you have \n"
            			+ "a field guide (Guide) and an Address \n"
            			+ "Book (Book) to help you sort out the \n"
            			+ "orchard. The Guide lists all the \n"
            			+ "pests and pollinators that can be \n"
            			+ "found on trees in the orchard.");
            	break;
            case 2:
            	textPane.setText("Your Mission(Cont):\n\n"
            + "Different pests grow on different \n"
            	+ "types of trees in the orchard. There \n"
            	+ "are six types of trees in the orchard \n"
            	+ "Apple, Orange, Lemon, Lime, Cherry, \n"
            	+ "and Nuts. There is pesticide in a \n"
            	+ "shed for each type of tree. The \n"
            	+ "address of the shed is written down \n"
            	+ "in your Book. You can only carry one \n"
            	+ "type of pesticide at a time. ");
            	break;
            case 3:
            	textPane.setText("Your Mission (contd): \n\n"
            			+ "You have to be careful when applying \n"
            			+ "the pesticide to a tree though, while \n"
            			+ "the pesticide will kill pests, it \n"
            			+ "will also kill polinators. \n"
            			+ "Pollinators are good for the orchard \n"
            			+ "because a tree with pollinators \n"
            			+ "cannot be infested by pests. Use the \n"
            			+ "Guide to find out whether what you \n"
            			+ "see on a tree is a pest or not.");
            	break;
            case 4:
            	textPane.setText("Help: Inventory \n\n"
            			+ "Your inventory has a Guide, and a Book. \n"
            			+ "When the game starts you will also see \n"
            			+ "a counter, a season tracker, and a jar \n"
            			+ "of tree-safe pesticide. \n"
            			+ "The Guide has drawings of pests and \n"
            			+ "pollinators. \n"
            			+ "The Book has the address of the shed, \n"
            			+ "and can store addresses of trees you \n"
            			+ "think are important.");
            	break;
            case 5:
            	textPane.setText("Help: other commands \n\n"
            			+ "Exit :: exits the help section. \n\n"
            			+ "Quit :: quits the game \n\n"
            			+ "Guide :: opens the field guide \n\n"
            			+ "Book :: opens the address book \n\n"
            			+ "Put :: used in conjunction with the = sign");
            	break;
            case 6:
            	textPane.setText("Help: what about & and *? \n\n"
            			+ "There are two symbols which wil help you \n"
            			+ "in your attempt to send pests to a merciful \n"
            			+ "end: * and &. \n\n"
            			+ "While trial and error may be appealing, \n"
            			+ "perhaps some examples of how to use * and \n"
            			+ "& would benefit you in your journey to \n"
            			+ "remove these pests. ");
            	break;
            case 7:
            	textPane.setText("How do you use * and &? \n\n"
            			+ "If you are in the same area as an Apple \n"
            			+ "tree, you could use: \n"
            			+ "put *Book = &Apple \n"
            			+ "to store the address of the Apple tree in \n"
            			+ "your special address book called Book. \n\n"
            			+ "If you want to know what creature is \n"
            			+ "living in the Apple tree, you can use:\n"
            			+ "*Apple \n"
            			+ "which tells you what pest or pollinator \n"
            			+ "is in the Apple tree.");
            	break;
            case 8:
            	textPane.setText("HELP: Walk \n\n"
            			+ "Walk - You can walk forward and to addresses: \n "
            			+ "walk :: you walk forward \n"
            			+ "walk &Apple :: you walk to the address of the Apple tree. \n"
            			+ "walk north :: you walk north \n"
            			+ "walk east :: you walk east \n"
            			+ "walk south :: you walk south \n"
            			+ "walk west :: you walk west \n"
            			+ "turn right and turn left changes your orientation.");
            	break;
            case 9:
            	textPane.setText("HELP: = \n\n"
            			+ "Book only takes addreses. Use \n"
            			+ "of = also requires knowledge of * and & \n\n"
            			+ "put *Book = &Apple :: stores address of the \n"
            			+ "Apple tree in Book. \n"
            			+ "put *Sunstar = *Poison \n"
            			+ "puts poison in the Sunstar tree.");
            	break;
            case 10:
            	textPane.setText("Note: \n\n"
            			+ "If left too long (aka a season)"
            			+ "pests will destroy a tree. If your \n"
            			+ "orchard loses 15% of the trees \n"
            			+ "(approximately 12), then you are in the \n"
            			+ "red and cannot reconver. Find the pests \n"
            			+ "and destroy them before they destroy \n"
            			+ "your livilihood! \n\n"
            			+ "Remember that the command \n\n"
            			+ "walk &Shed \n\n"
            			+ "requires you to have an address in the"
            			+ "Book to walk back to like \n\n"
            			+ "walk &Sunstar");
            	break;
            case 11:
            	textPane.setText("If you feel competent, \n"
            			+ " press next or type \n"
            			+ "“Enter Orchard”\n\n"
            			+ "(If you need help during \n"
            			+ "gameplay, type “help” in \n"
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
    	introState = 12;
    	removeAll();
    	JScrollPane scrollPane = new JScrollPane();
    	scrollPane.setPreferredSize(SIZE);
    	scrollPane.setMaximumSize(SIZE);
    	scrollPane.setMinimumSize(SIZE);
    	StringBuilder sb = new StringBuilder(addressBook.size());
    	for (String address: addressBook){
    		if(address==null || address.equals("")){
    			continue;
    		}else{
    			sb.append(address);
    			sb.append("\n");
    		}
    	}
    	JTextArea textArea = new JTextArea(sb.toString(), addressBook.size(), 1);
    	textArea.setBackground(Color.PINK);
    	scrollPane.setBackground(Color.ORANGE);
    	scrollPane.add(textArea);
    	revalidate();
    	repaint();
    }
    
    public void showGuide(){
    	introState = 13;
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
    	introState = 14;
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
    
    public boolean isInGame(){
    	return introState==-1;
    }
    
    public int getIntroState(){
    	return introState;
    }
    
}
