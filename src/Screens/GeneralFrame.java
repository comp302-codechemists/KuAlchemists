package Screens;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public abstract class GeneralFrame extends JFrame{
	
	public GeneralFrame() {
		
		this.setAppearance();
      
        // This is how to do relative path:
        Image img = new ImageIcon(this.getClass().getResource("/Images/bck.png")).getImage();
        

        setIconImage(img);
       
	}
	
	private void setAppearance()
    {
    	// As soon as the constructor is called,
    	// set visibility of the frame to true.
    	this.setVisible(true);
    	this.setResizable(false);
    	this.setTitle("KU Alchemists Game");
    	this.setSize(1540, 820);
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.getContentPane().setLayout(null);
    }

}
