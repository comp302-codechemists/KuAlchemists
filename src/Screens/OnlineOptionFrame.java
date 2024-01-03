package Screens;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Business.KUAlchemistsGame;
import DesignSystem.GameFrame;
import soundEffects.PlaySong;
import uiHelpers.MagicFrame;

public class OnlineOptionFrame extends MagicFrame {
	private JButton hostButton;
    private JButton joinButton;
	 public OnlineOptionFrame()
	    {
	    	this(null); 
	    }
	    
	    
	    public OnlineOptionFrame(KUAlchemistsGame game) 
	    {
	    	super(game, GameFrame.welcomeFrameWidth, GameFrame.welcomeFrameHeight);
	    	setBackground("/BackgroundImages/welcomeBackground.png");
	    
	        
	    	this.setHostButton();
	    	this.setJoinButton();
	   }
	    
	    
	    private void setHostButton()
	    {
	    	hostButton = new JButton("Host Game");
	    	hostButton.setHorizontalTextPosition(SwingConstants.CENTER);
	    	hostButton.setVerticalTextPosition(SwingConstants.BOTTOM);
	    	hostButton.setBackground(Color.green); 
	    	hostButton.setForeground(Color.WHITE); 
	    	hostButton.setFont(new Font("Arial", Font.BOLD, 12)); 
	    	hostButton.setBounds(220, 230, 100, 30);


	    	hostButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                hostButtonClicked();
	            }
	        });
	        
	        backgroundPanel.add(hostButton);
	        
	    }
	    
	    
	    private void setJoinButton()
	    {
	    	joinButton = new JButton("Join Game");
	    	joinButton.setHorizontalTextPosition(SwingConstants.CENTER);
	    	joinButton.setVerticalTextPosition(SwingConstants.BOTTOM);
	    	joinButton.setBackground(Color.green); 
	    	joinButton.setForeground(Color.WHITE); 
	    	joinButton.setFont(new Font("Arial", Font.BOLD, 12)); 
	    	joinButton.setBounds(420, 230, 100, 30);
	    	
	    	


	    	joinButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	joinButtonClicked();
	            }
	        });
	        
	        backgroundPanel.add(joinButton);
	        
	    }
	    
	    
	    private void hostButtonClicked() {
	    	
	    	   
	    	PlaySong.play("ButtonClick");
	    	
	    	new HostGameFrame();
	    	this.dispose();
	    	
	    }
	    
	    private void joinButtonClicked() {
	    	PlaySong.play("ButtonClick");
	    	new JoinGameFrame();
	    	this.dispose();


	    }
}
