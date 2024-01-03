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


public class HostGameFrame extends MagicFrame{
	private JButton startGameButton;
	private JTextArea chatTextArea;
	
	
	private JTextArea IPAdress;
	 public HostGameFrame()
	    {
	    	this(null); 
	    }
	    
	    
	    public HostGameFrame(KUAlchemistsGame game) 
	    {
	    	super(game, GameFrame.welcomeFrameWidth, GameFrame.welcomeFrameHeight);
	    	setBackground("/BackgroundImages/welcomeBackground.png");
	    	
	    	
	    	this.chatTextArea = new JTextArea();
	    	chatTextArea.setEditable(false);
	    	chatTextArea.setBounds(50, 50, 670, 100); 
	    	backgroundPanel.add(chatTextArea);
	        
	    	startGameButton = new JButton("Start Game");
	    	startGameButton.setBounds(335, 160, 100, 30);
	    	
	    	startGameButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	startGameButtonClicked();
	            }
	        });
	    	backgroundPanel.add(startGameButton);
	     }
	    
	    
	    
	    
	 
	    
	    private void startGameButtonClicked() {
	    	PlaySong.play("ButtonClick");


	    }
	    
	    
	    
	    
	}