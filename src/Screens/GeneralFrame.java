package Screens;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public abstract class GeneralFrame extends JFrame{
	
	public GeneralFrame() {
		//setExtendedState(JFrame.MAXIMIZED_BOTH); 		
        //setResizable(false);
		
		setSize(1540, 820);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("KUAlchemists Game");
        getContentPane().setLayout(null);
      
        // This is how to do relative path:
        Image img = new ImageIcon(this.getClass().getResource("/Images/bck.png")).getImage();
        
        // This works only with absolute path:
        //ImageIcon img = new ImageIcon("C:\\Users\\simge\\git\\KuAlchemists\\src\\bck.png");
        setIconImage(img);
       
	}

}
