package Screens;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Business.KUAlchemistsGame;
import Business.Player;

import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Image;

public class EndGameFrame extends JFrame {
	
	private KUAlchemistsGame game;
	private Player player;
	private ImageIcon avatarIcon;
	private JLabel avatarLabel;
	private JPanel winnerPanel;
	private JLabel winnerLbl;
	private JLabel fireworkLabel;
	private JLabel fireworkLabel_1;


	public EndGameFrame(KUAlchemistsGame game, Player player) {
		
		this.player = player;
		this.game = game;
		
		setWindow();
		setShowWinner();
		
		setAnimation();
		//PlaySong.play("Winner");
	}
	
	private void setWindow() {
		
	   	this.setVisible(true);
    	this.setResizable(false);
    	this.setTitle("KU Alchemists Game");
    	this.setSize(1200, 800);
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.getContentPane().setLayout(null);
		
		
	}
	
	
	private void setAnimation() {
		
		Icon imgIcon = new ImageIcon(this.getClass().getResource("/Images/firework.gif"));		
		fireworkLabel = new JLabel(imgIcon);
		fireworkLabel.setLocation(45, 181);
		fireworkLabel.setSize(319, 287);
		getContentPane().add(fireworkLabel);
		
		fireworkLabel_1 = new JLabel(imgIcon);
		fireworkLabel_1.setBounds(738, 181, 306, 287);
		getContentPane().add(fireworkLabel_1);
		
	}
	
	private void setShowWinner() {
		
		String avatar = "/images/" + player.getAvatarPath() + ".png";
		avatarIcon = new ImageIcon(this.getClass().getResource(avatar));
        
        Image scaledImage = avatarIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		
        avatarIcon = new ImageIcon(scaledImage);
		
		winnerPanel = new JPanel();
		winnerPanel.setBounds(425, 304, 274, 309);
		getContentPane().add(winnerPanel);
		
		winnerPanel.setLayout(null);
		avatarLabel = new JLabel(avatarIcon);
		avatarLabel.setBounds(37, 26, 200, 200);
		winnerPanel.add(avatarLabel);
		
		
		String winner = "Winner is: " + player.getUserName();
		winnerLbl = new JLabel(winner);
		winnerLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		winnerLbl.setBounds(37, 246, 200, 37);
		winnerPanel.add(winnerLbl);
		
		
		
	}
}