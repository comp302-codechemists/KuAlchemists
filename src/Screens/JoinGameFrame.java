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
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
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
	    	IPAdress.setText("172.16.126.0");
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
	    	
	        backgroundPanel.add(joinButton);



	    	joinButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	try {
						joinButtonClicked();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	        });
	        
	        
	    }
	    
	    private void joinButtonClicked() throws UnknownHostException, IOException {
	        PlaySong.play("ButtonClick");
	        String IP = IPAdress.getText();
	            
	                Socket socket = new Socket("localhost",1271);
	                Client newClient = new Client(socket,this);
	        Client.instance = newClient;       
	        KUAlchemistsGame.instance.client = newClient;
	        KUAlchemistsGame.instance.client.sendMessage("LOBBYJOIN");
	        joinButton.setEnabled(false);
	    }

	    
	    
	}


