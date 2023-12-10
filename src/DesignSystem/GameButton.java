package DesignSystem;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class GameButton extends JButton{

	private String buttonText;
	
	public GameButton(String buttonText) 
	{
		this.buttonText = buttonText;
		setText(buttonText);
		setForeground(Color.white);
		setFont(GameText.normalText);
		setBorder(new LineBorder(Color.white, 2));
		setOpaque(false);
		
		addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle mouse click event if needed
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Handle mouse press event if needed
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Handle mouse release event if needed
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Change button appearance on mouse enter (hover effect)
            	setBorder(new LineBorder(Color.green, 2));
            	setForeground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restore button appearance on mouse exit
            	setBorder(new LineBorder(Color.white, 2));
            	setForeground(Color.white);
            }
		});
	}

}