package Screens;
import javax.swing.*;

import Business.KUAlchemistsGame;
import Controllers.PlayGameController;
import DesignSystem.GameFrame;
import soundEffects.PlaySong;
import uiHelpers.MagicFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class JoinGameFrame extends MagicFrame {
	private JButton joinButton;
	private JTextArea IPAdress;
	 public JoinGameFrame()
	    {
	    	this(null); 
	    }
	    
	    
	    public JoinGameFrame(KUAlchemistsGame game) 
	    {
	    	super(game, GameFrame.welcomeFrameWidth, GameFrame.welcomeFrameHeight);
	    	setBackground("/BackgroundImages/welcomeBackground.png");
	    	this.IPAdress = new JTextArea();
	    	IPAdress.setBounds(320, 190, 100, 30);
	    	IPAdress.setLineWrap(true);
	    	IPAdress.setWrapStyleWord(true);
	    	backgroundPanel.add(IPAdress);
	    	this.setJoinButton();

	        
	       
	     }
	    
	    
	    private void setJoinButton()
	    {
	    	joinButton = new JButton("Join Game");
	    	joinButton.setHorizontalTextPosition(SwingConstants.CENTER);
	    	joinButton.setVerticalTextPosition(SwingConstants.BOTTOM);
	    	joinButton.setBackground(Color.green); 
	    	joinButton.setForeground(Color.WHITE); 
	    	joinButton.setFont(new Font("Arial", Font.BOLD, 12)); 
	    	joinButton.setBounds(320, 230, 100, 30);
	    	
	    	


	    	joinButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	joinButtonClicked();
	            }
	        });
	        
	        backgroundPanel.add(joinButton);
	        
	    }
	    
	    private void joinButtonClicked() {
	    	PlaySong.play("ButtonClick");


	    }
	    
	    
	    
	}


