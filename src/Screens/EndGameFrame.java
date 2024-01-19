package Screens;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Business.KUAlchemistsGame;
import Business.Player;
import soundEffects.PlaySong;
import uiHelpers.MagicFrame;

import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class EndGameFrame extends GeneralFrame {
	
	
	private KUAlchemistsGame game;
	private Player player;
	private ImageIcon avatarIcon;
	private JLabel avatarLabel;
	private JPanel winnerPanel;
	private JLabel winnerLbl;
	private JLabel fireworkLabel;
	private JLabel fireworkLabel_1;


	public EndGameFrame(KUAlchemistsGame game, Player player) {
		
		super(game);
		this.player = player;
		
		
	    PlaySong.play("Winner");

		setWindow();
		setShowWinner();
		
	}
	
	private void setWindow() {
		
	   	this.setVisible(true);
    	this.setResizable(false);
    	this.setTitle("KU Alchemists Game");
    	this.setBackground("/Images/endgame_bck.gif");
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.getContentPane().setLayout(null);
		
		
	}
	
	
	private void setShowWinner() {
		
	
		String avatar = "/images/" + player.getAvatarPath() + ".png";
		avatarIcon = new ImageIcon(this.getClass().getResource(avatar));
        
        Image scaledImage = avatarIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		
        avatarIcon = new ImageIcon(scaledImage);
		
		winnerPanel = new JPanel();
		winnerPanel.setBounds(400, 300, 500, 500);
		backgroundPanel.add(winnerPanel);
		
		winnerPanel.setLayout(null);
		winnerPanel.setOpaque(false);
		avatarLabel = new JLabel(avatarIcon);
		avatarLabel.setBounds(250, 26, 200, 200);
		winnerPanel.add(avatarLabel);
		
		
		String winner = "Winner is: " + player.getUserName();
		winnerLbl = new JLabel(winner);
		winnerLbl.setForeground(Color.WHITE);
		winnerLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		winnerLbl.setBounds(250, 246, 200, 151);
		winnerPanel.add(winnerLbl);
		
		
		
	}

}

