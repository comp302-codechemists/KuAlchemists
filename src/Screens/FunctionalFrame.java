package Screens;
import javax.swing.JFrame;

import Business.KUAlchemistsGame;
import Business.Player;

public abstract class FunctionalFrame extends JFrame{
	
	
	public KUAlchemistsGame game;
	public Player player;
	
	public FunctionalFrame(KUAlchemistsGame game, Player player) {
		
		this.game = game;
		this.player = player;
		this.setAppearance();
	}
	
	
	
	private void setAppearance()
    {
    	// As soon as the constructor is called,
    	// set visibility of the frame to true.
    	this.setVisible(true);
    	this.setResizable(false);
    	this.setTitle("KU Alchemists Game");
    	this.setSize(1200, 686);
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.getContentPane().setLayout(null);
    }
	
}
