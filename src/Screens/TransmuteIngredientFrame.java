package Screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.Player;
import Controllers.TransmuteController;

public class TransmuteIngredientFrame extends FunctionalFrame{

	public TransmuteIngredientFrame(KUAlchemistsGame game, Player player) {
		super(game, player);
		this.setBackground("transmuteIngredientBackground");
		this.setIngredients();
		this.setButton();
	}
	
	private void setIngredients() {
	    TransmuteController controller = new TransmuteController(game);
	    List<Ingredient> currentIngredients = controller.getPlayersCurrentIngredients();

	    

	}

	private void setButton()
	{
		JButton button = new JButton();
		button.setText("Transmute");
		button.setBounds(500, 500, 150, 30);
		button.setForeground(Color.white);
		button.setFont(new Font("Tahoma", Font.ITALIC, 15));
		button.setOpaque(false);
		button.setBorder(new LineBorder(Color.white, 2));
		backgroundPanel.add(button);
		button.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				/*TransmuteController controller = new TransmuteController(game);
                String transmutedIngredient = controller.handleTransmute("Terror Root");
                
			    if (transmutedIngredient != null)
			    {
			    	String message = "Ingredient transmuted: " + transmutedIngredient;
				    JOptionPane.showMessageDialog(null, message, "Ingredient Transmuted", JOptionPane.INFORMATION_MESSAGE);
			    }
			    else
			    {
			    	String message = "Ingredient Storage is empty!";
			    	JOptionPane.showMessageDialog(null, message, "Ingredient Taken", JOptionPane.WARNING_MESSAGE);
			    }
			    
			    // Close the frame
			    TransmuteIngredientFrame.this.dispose();*/
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
	            	button.setBorder(new LineBorder(Color.yellow, 2));
	            	button.setForeground(Color.yellow);
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                // Restore button appearance on mouse exit
	            	button.setBorder(new LineBorder(Color.white, 2));
	            	button.setForeground(Color.white);
	            }
	        });
		
		backgroundPanel.add(button);
	}

}
