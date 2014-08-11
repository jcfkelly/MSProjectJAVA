package edu.ucsc;

import javax.swing.*;

import java.awt.*;

public class GameMainPanel extends JPanel{
	 
	 final ImageIcon antsIcon = 		new ImageIcon("assets/MS_Project_Ants.png");
	 final ImageIcon aphidsIcon = 		new ImageIcon("assets/MS_Project_Aphids.png");
	 final ImageIcon beesIcon = 		new ImageIcon("assets/MS_Project_Bees.png");
	 final ImageIcon frogsIcon = 		new ImageIcon("assets/MS_Project_Frogs.png");
	 final ImageIcon gophersIcon = 		new ImageIcon("assets/MS_Project_Gophers.png");
	 final ImageIcon waspsIcon = 		new ImageIcon("assets/MS_Project_Wasps.png");
	 final ImageIcon butterfliesIcon =	new ImageIcon("assets/MS_Project_Butterflies.png");
	 final ImageIcon ladybugsIcon = 	new ImageIcon("assets/MS_Project_Ladybugs.png");
	
	private static final long serialVersionUID = 1L;
	public final int MAX_INTRO_STATE=11;
	private int introState = -1;
	
    public GameMainPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(Color.WHITE);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 270);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(400, 270);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(400, 270);
    }

    public void setIntroState(int introState) {
    	this.introState = introState;
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
		treeLayer.setSize(400, 270);
    	ImageIcon treeImageIcon = new ImageIcon(tree.getTreeSpecies());
		JLabel treeLabel = new JLabel();
		treeLabel.setIcon(treeImageIcon);
		treeLayer.setLayer(treeLabel, 0);
		if (tree.getResident()==1){
			JLabel antLabel = new JLabel();
			antLabel.setIcon(antsIcon);
			treeLayer.setLayer(antLabel, 1);
		}else if (tree.getResident()==2){
			JLabel waspLabel = new JLabel();
			waspLabel.setIcon(waspsIcon);
			treeLayer.setLayer(waspLabel, 1);
		}else if (tree.getResident()==3){
			JLabel gopherLabel = new JLabel();
			gopherLabel.setIcon(gophersIcon);
			treeLayer.setLayer(gopherLabel, 1);
		}else if (tree.getResident()==4){
			JLabel aphidLabel = new JLabel();
			aphidLabel.setIcon(aphidsIcon);
			treeLayer.setLayer(aphidLabel, 1);
		}else if (tree.getResident()==5){
			JLabel butterflyLabel = new JLabel();
			butterflyLabel.setIcon(butterfliesIcon);
			treeLayer.setLayer(butterflyLabel, 1);
		}else if (tree.getResident()==6){
			JLabel beeLabel = new JLabel();
			beeLabel.setIcon(beesIcon);
			treeLayer.setLayer(beeLabel, 1);
		}else if (tree.getResident()==7){
			JLabel frogLabel = new JLabel();
			frogLabel.setIcon(frogsIcon);
			treeLayer.setLayer(frogLabel, 1);	
		}else if (tree.getResident()==8){
			JLabel ladybugLabel = new JLabel();
			ladybugLabel.setIcon(ladybugsIcon);
			treeLayer.setLayer(ladybugLabel, 1);
		}
		add(treeLayer);
		revalidate();
		repaint();
	}
    
    public int getIntroState(){
    	return introState;
    }
    
}
