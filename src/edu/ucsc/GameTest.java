package edu.ucsc;

import static org.junit.Assert.*;

import java.awt.Point;

import javax.swing.JTextArea;

import org.junit.BeforeClass;
import org.junit.Test;

public class GameTest {

	private static GameState gameState;
	private static JTextArea area;
	
	@BeforeClass
	public static void testSetup(){
		//fresh gameState for each test
		Game.resetGameState();
		gameState = Game.getGameState();
		area = new JTextArea();
	}
	
/*	@Test
	public void testGameLoop() {
		fail("Not yet implemented");
	}
*/
	
	@Test 
	public void testExit(){
		assertFalse("Exit works!", Game.gameLoop("exit", area));
		assertEquals("Exit says goodbye.", "Goodbye!\n", area.getText());
	}
	
	@Test
	public void testWalk(){
		assertEquals("Start at 0,0", new Point(0,0), gameState.getPosition());

		assertTrue("Walk North", Game.gameLoop("walk north", area));
		assertEquals("Walk North puts you at 0,1", new Point(0,1), gameState.getPosition());
		
		area.setText(""); //reset so only one line
		assertTrue("Tree1", Game.gameLoop("Walk Tree1", area));
		assertEquals("Tree1","You cannot walk to an object, but you can walk to an address. Maybe you meant to use &\n", area.getText());
	
		area.setText(""); //reset so only one line
		assertTrue("&Tree", Game.gameLoop("Walk &Tree", area));
		assertEquals("&Tree", "That Tree does not exist; thus, you cannot walk to that tree.\n", area.getText());
		
		area.setText(""); //reset so only one line
		assertTrue("&Tree297", Game.gameLoop("Walk &Tree297", area));
		assertEquals("&Tree297", "You walk to Tree297\n", area.getText());
		assertEquals("&Tree297 location", gameState.getTree("Tree297").getLocation(), gameState.getPosition());
		
	}
}

