package edu.ucsc;
 
import javax.swing.*;
 
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
 
import java.awt.event.*;
 
public class GameGUI extends JPanel 
                               implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField textField;
    JTextArea textArea;
    MyTextActionListener gameLoop;
 
    public GameGUI() {
        super(new GridBagLayout());
        GridBagLayout gridbag = (GridBagLayout)getLayout();
        GridBagConstraints c = new GridBagConstraints();
 
        JButton button = new JButton("Enter");
        button.addActionListener(this);
 
        textField = new JTextField(20);
        gameLoop = new MyTextActionListener();
        textField.addActionListener(gameLoop);
        textField.getDocument().putProperty("name", "Text Field");
 
        textArea = new JTextArea();
        textArea.getDocument().putProperty("name", "Text Area");
 
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(200, 75));
  
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        gridbag.setConstraints(textField, c);
        add(textField);
 
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.0;
        c.gridheight = 2;
        c.fill = GridBagConstraints.BOTH;
        gridbag.setConstraints(scrollPane, c);
        add(scrollPane);
 
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.0;
        c.gridheight = 1;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        gridbag.setConstraints(button, c);
        add(button);
 
        setPreferredSize(new Dimension(450, 250));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }
 
    class MyTextActionListener implements ActionListener {
        /** Handle the text field Return. */
        public void actionPerformed(ActionEvent e) {
            String input = textField.getText();
            boolean keepLooping = Game.gameLoop(input, textArea);
            if (!keepLooping){
            	textField.removeActionListener(gameLoop);
            }
            textField.setText("");
        	
        	/*int selStart = textArea.getSelectionStart();
            int selEnd = textArea.getSelectionEnd();
 
            textArea.replaceRange(textField.getText(),
                                  selStart, selEnd);
            textField.selectAll();*/
        }
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
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