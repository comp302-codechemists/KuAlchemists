package Screens;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class StartGameFrame extends GeneralFrame{
	public StartGameFrame() {

       
	}
	
    public static void main(String[] args) {
        // Create and display the frame
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartGameFrame().setVisible(true);
            }
        });
    }
	

}
