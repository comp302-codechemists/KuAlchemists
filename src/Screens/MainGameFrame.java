package Screens;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class MainGameFrame extends GeneralFrame{
	private JTable table;
	
	/* Displays the entire game board, including all game objects (Board, Tokens,
	*Ingredients, Potions, Publication Cards, and Deduction Board).
	*Provides comprehensive information about each player's resources, scores, and
	*progress during the game.
	*/
	
	public MainGameFrame() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(1241, 0, 285, 783);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel(4, 3);

        
		model.setValueAt(" Information", 0, 0);
		model.setValueAt(" Reputation Points", 1, 0);
		model.setValueAt(" Ingredient Cards", 2, 0);
		model.setValueAt(" Artifact Cards", 3, 0);
		
		//TODO Change with real player names, use Player class.
		model.setValueAt(" Player 1", 0, 1);
		model.setValueAt(" Player 2", 0, 2);
		
		JTable table = new JTable(model);
		table.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.setRowHeight(30);
		
		table.setSurrendersFocusOnKeystroke(true);
		table.setBounds(10, 50, 264, 120);
		panel.add(table);
		
		JButton endGame = new JButton("End Game");
		endGame.setFont(new Font("Tahoma", Font.PLAIN, 12));
		endGame.setBounds(174, 730, 100, 21);
		panel.add(endGame);
		
		JButton exitGame = new JButton("Exit Game");
		exitGame.setFont(new Font("Tahoma", Font.PLAIN, 12));
		exitGame.setBounds(65, 730, 99, 21);
		panel.add(exitGame);
		
		JButton howTo = new JButton("How to Play?");
		howTo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		howTo.setBounds(63, 699, 101, 21);
		panel.add(howTo);
		
		JButton btnNewButton = new JButton("Pause Game");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(174, 699, 100, 21);
		panel.add(btnNewButton);
		
	}
	
	
	
	
	
    public static void main(String[] args) {
        // Create and display the frame
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainGameFrame().setVisible(true);
            }
        });
    }
}
