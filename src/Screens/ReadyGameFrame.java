package Screens;
import javax.swing.*;

import Business.KUAlchemistsGame;
import Business.Player;
import Controllers.PlayGameController;
import DesignSystem.GameFrame;
import networking.*;
import soundEffects.PlaySong;
import uiHelpers.MagicFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class ReadyGameFrame extends MagicFrame {
	private JButton ReadyButton;
	private JTextArea playerName;
	 public ReadyGameFrame()
	    {
	    	this(null); 
	    }
	    
	    
	    public ReadyGameFrame(KUAlchemistsGame game) 
	    {
	    	super(game, GameFrame.welcomeFrameWidth, GameFrame.welcomeFrameHeight);
	    	setBackground("/BackgroundImages/welcomeBackground.png");
	    	this.playerName = new JTextArea();
	    	playerName.setBounds(320, 190, 100, 30);
	    	playerName.setLineWrap(true);
	    	playerName.setText("172.16.126.0");
	    	playerName.setWrapStyleWord(true);
	    	backgroundPanel.add(playerName);
	    	this.setReadyButton();

	        
	       
	     }
	    
	    
	    private void setReadyButton()
	    {
	    	ReadyButton = new JButton("READY");
	    	ReadyButton.setHorizontalTextPosition(SwingConstants.CENTER);
	    	ReadyButton.setVerticalTextPosition(SwingConstants.BOTTOM);
	    	ReadyButton.setBackground(Color.green); 
	    	ReadyButton.setForeground(Color.WHITE); 
	    	ReadyButton.setFont(new Font("Arial", Font.BOLD, 12)); 
	    	ReadyButton.setBounds(320, 230, 100, 30);
	    	
	    	


	    	ReadyButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	joinButtonClicked();
	            }
	        });
	        
	        backgroundPanel.add(ReadyButton);
	        
	    }
	    
	    private void joinButtonClicked() {
	        PlaySong.play("ButtonClick");
	        String confirmedName = playerName.getText();
	        Player newPlayer = new Player (confirmedName, "avatar1");
	        
	        
	        ReadyButton.setEnabled(false);
	        
	    }

	    
	    
	}

