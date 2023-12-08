package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import Business.KUAlchemistsGame;
import Business.Player;
import Controllers.buyArtifactController;

public class BuyArtifactFrame extends FunctionalFrame{

	JButton button;
	
	public BuyArtifactFrame(KUAlchemistsGame game, Player player) {
		super(game, player);
		this.setBackground("buyArtifactBackground");
		this.setButton();
	}
	
	private void setButton()
	{
		button = new JButton();
		button.setText("Buy");
		button.setBounds(500, 500, 150, 30);
		button.setForeground(Color.white);
		button.setFont(new Font("Tahoma", Font.ITALIC, 15));
		button.setOpaque(false);
		button.setBorder(new LineBorder(Color.white, 2));
		backgroundPanel.add(button);
		button.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buyArtifactController controller = new buyArtifactController(game);
                String boughtArtifact = controller.buyArtifactHandler();
                
			    if (boughtArtifact != null)
			    {
			    	String message = "Artifact bought: " + boughtArtifact;
				    JOptionPane.showMessageDialog(null, message, "Artifact Bought", JOptionPane.INFORMATION_MESSAGE);
			    }
			    else
			    {
			    	String message = "Artifact Storage is empty!";
			    	JOptionPane.showMessageDialog(null, message, "Artifact Bought", JOptionPane.WARNING_MESSAGE);
			    }
			    
			    // Close the frame
			    BuyArtifactFrame.this.dispose();
			    new MainGameFrame(game);
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
