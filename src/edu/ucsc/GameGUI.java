package edu.ucsc;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameGUI extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private GameMainPanel gameMainPanel;
    private JTextField textField;
    private JTextArea textArea;
    private MyTextActionListener gameLoop;
    private GridBagConstraints c;
    private GridBagLayout gridbag;
    private ImageIcon poisonIcon;
    private JLabel poisonLabel = new JLabel();
    private ImageIcon seasonIcon;
    private JLabel seasonLabel = new JLabel();
    private JTextArea counter = new JTextArea();
    private JButton nextButton;
    
    public GameGUI() {
        super(new GridBagLayout());
        gridbag = (GridBagLayout)getLayout();
        c = new GridBagConstraints();

        gameMainPanel = new GameMainPanel();
        gameMainPanel.setIntroState(0);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.0;
        c.gridheight = 4;
        gridbag.setConstraints(gameMainPanel, c);
        add(gameMainPanel);

        textField = new JTextField();
        gameLoop = new MyTextActionListener();
        textField.addActionListener(gameLoop);
        textField.getDocument().putProperty("name", "Text Field");
        textField.setPreferredSize(new Dimension(400,20));
        textField.setMinimumSize(new Dimension(400,20));
        textField.setMaximumSize(new Dimension(400,20));
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 1.0;
        gridbag.setConstraints(textField, c);
        add(textField);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.getDocument().putProperty("name", "Text Area");
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        scrollPane.setMaximumSize(new Dimension(400, 150));
        scrollPane.setMinimumSize(new Dimension(400, 150));
        c.gridx = 0;
        c.gridy = 7;
        c.weightx = 0.0;
        c.gridheight = 2;
        gridbag.setConstraints(scrollPane, c);
        add(scrollPane);
        
        nextButton = new JButton("Next ");
        c.gridx = 1;
        c.gridy = 4;
        c.weightx = 0.0;
        c.gridheight = 1;
        c.weighty = 0.0;
        gridbag.setConstraints(nextButton, c);
        add(nextButton);
        nextButton.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				typeNext();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
        	
        });

        
        JButton button = new JButton("Enter");
        button.addActionListener(this);
        c.gridx = 1;
        c.gridy = 4;
        c.weightx = 0.0;
        c.gridheight = 1;
        c.weighty = 0.0;
        gridbag.setConstraints(button, c);
        add(button);
        
        
        
        
        setPreferredSize(new Dimension(740, 580));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }


    
    class MyTextActionListener implements ActionListener {
        /** Handle the text field Return. */
        public void actionPerformed(ActionEvent e) {
            String input = textField.getText();
            boolean keepLooping = Game.gameLoop(input, textArea, gameMainPanel, GameGUI.this);
            if (!keepLooping){
                textField.removeActionListener(gameLoop);
            }
            textField.setText("");
        }
    }

    public void typeHelp(){
    	nextButton.setVisible(true);
    	revalidate();
    	repaint();
    }
    
    public void hideNextButton(){
    		nextButton.setVisible(false);
			revalidate();
	    	repaint();
    }
    
    public void typeNext(){
    	if(gameMainPanel.getIntroState()!=-1){
			Game.next(textArea, gameMainPanel, GameGUI.this);
		}

    	if(gameMainPanel.isInGame()){
    		hideNextButton();
		}
    }
    
    public void refreshCounter(){
    	counter.setText("Counter: \n" + Game.refreshSteps());
    }
    
    public void onEnterOrchard(){
    	hideNextButton();
    	//counter
    	counter.setSize(70, 25);
    	counter.setText("Counter: \n" + Game.refreshSteps());
    	c.gridx = 5;
        c.gridy = 2;
        gridbag.setConstraints(counter, c);
        add(counter);
        
    	//inventory
        final JButton guideButton = new JButton(new ImageIcon("assets/MS_Project_fieldGuide.jpg"));
        c.gridx = 4;
        c.gridy = 1;
        gridbag.setConstraints(guideButton, c);
        add(guideButton);
        guideButton.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				String guideButtonImage;
				if(gameMainPanel.getIntroState()==13){
					guideButtonImage = "assets/MS_Project_fieldGuide.jpg";
					Game.exit(GameGUI.this, gameMainPanel, textArea);
				}else{
					guideButtonImage = "assets/MS_Project_backButton.jpg";
					Game.guide(GameGUI.this, gameMainPanel, textArea);
				}
		    	guideButton.setIcon(new ImageIcon(guideButtonImage));
		    	revalidate();
		    	repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
        	
        });
        final JButton bookButton = new JButton(new ImageIcon("assets/MS_Project_addressBook.jpg"));
        bookButton.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				String bookButtonImage;
				if(gameMainPanel.getIntroState()==12){
					bookButtonImage = "assets/MS_Project_addressBook.jpg";
					Game.exit(GameGUI.this, gameMainPanel, textArea);
				}else{
					bookButtonImage = "assets/MS_Project_backButton.jpg";
					Game.book(GameGUI.this, gameMainPanel, textArea);
				}
		    	bookButton.setIcon(new ImageIcon(bookButtonImage));
		    	revalidate();
		    	repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
        	
        });
        c.gridx = 4;
        c.gridy = 2;
        gridbag.setConstraints(bookButton, c);
        add(bookButton);      
        
        poisonIcon = new ImageIcon("assets/MS_Project_poison_apple.jpg");
        poisonLabel.setIcon(poisonIcon);
        c.gridx = 5;
        c.gridy = 3;
        gridbag.setConstraints(poisonLabel, c);
        add(poisonLabel);
        
        seasonIcon = new ImageIcon("assets/MS_Project_Seasons_1.jpg");
        seasonLabel.setIcon(seasonIcon);
        c.gridx = 5;
        c.gridy = 1;
        gridbag.setConstraints(seasonLabel, c);
        add(seasonLabel);        
        revalidate();
        repaint();
    }
    
    public void setSeasonIcon(int season){
    	String seasonImage;
    	switch(season){
    	case 0:
    		seasonImage = "assets/MS_Project_Seasons_1.jpg";
    		break;
    	case 1:
    		seasonImage = "assets/MS_Project_Seasons_2.jpg";
    		break;
    	case 2:
    		seasonImage = "assets/MS_Project_Seasons_3.jpg";
    		break;
    	case 3:
    		seasonImage = "assets/MS_Project_Seasons_4.jpg";
    		break;
    	default:
    		seasonImage = "assets/MS_Project_Seasons_0.jpg";
    		break;	
    	}
    	seasonIcon = new ImageIcon(seasonImage);
    	seasonLabel.setIcon(seasonIcon);
    	revalidate();
    	repaint();
    }
    
    public void setPoisonIcon(int poison){
    	String poisonImage;
    	switch(poison){
    		case 0:
    			poisonImage = "assets/MS_Project_poison_empty.jpg";
    			break;		
    		case 1:
    			poisonImage = "assets/MS_Project_poison_apple.jpg";
    			break;
    		case 2:
    			poisonImage = "assets/MS_Project_poison_orange.jpg";
    			break;
    		case 3:
    			poisonImage = "assets/MS_Project_poison_Cherry.jpg";
    			break;
    		case 4:
    			poisonImage = "assets/MS_Project_poison_nut.jpg";
    			break;
    		case 5:
    			poisonImage = "assets/MS_Project_poison_lemon.jpg";
    			break;
    		default:
    			poisonImage = "assets/MS_Project_poison_Lime.jpg";
    			break;
    	}
    	poisonIcon = new ImageIcon(poisonImage);
    	poisonLabel.setIcon(poisonIcon);
    	revalidate();
    	repaint();
    }
    
    /** Handle button click. */
    public void actionPerformed(ActionEvent e) {
        gameLoop.actionPerformed(e);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GameGUI");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new GameGUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}