package Screens;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public abstract class GeneralFrame extends JFrame{
	
	public GeneralFrame() {
		setExtendedState(JFrame.MAXIMIZED_BOTH); 		
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("KUAlchemists Game");
        ImageIcon img = new ImageIcon("C:\\Users\\simge\\git\\KuAlchemists\\src\\bck.png");
        setIconImage(img.getImage());
       
	}

}
