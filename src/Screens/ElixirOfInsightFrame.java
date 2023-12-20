package Screens;

import Business.Ingredient;
import Business.IngredientStorage;
import Business.KUAlchemistsGame;
import Business.Player;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ElixirOfInsightFrame extends JFrame {

	public KUAlchemistsGame game;
	public Player player;
	
	JPanel backgroundPanel;
	
	private JComboBox<String> order1;
	private JComboBox<String> order2;
	private JComboBox<String> order3;
	

	private Ingredient ing1;
	private Ingredient ing2;
	private Ingredient ing3;
	
	
	public ElixirOfInsightFrame(KUAlchemistsGame game, Player player) {
		
		this.game = game;
		this.player = player;
		this.setAppearance();
		
		
		setIngredientImages();
		setReorderingLogic();
		setButton();
		

	}
	
	private void setIngredientImages() {
		
		JLabel orderLabel = new JLabel("Order the top three ingredient cards in the deck as you wish.");
		orderLabel.setForeground(Color.WHITE);
		orderLabel.setFont(new Font("Tahoma", Font.ITALIC, 25));
		orderLabel.setBounds(178, 165, 829, 75);
		getContentPane().add(orderLabel);
		
		//Get ingredients
		List<Ingredient> ingList = game.getIngredientStorage().getIngredientList();
		ing1 = ingList.get(0);
		ing2 = ingList.get(1);
		ing3 = ingList.get(2);
		
		//Get their images
    	int index1 = Ingredient.getIngredientIndex(ing1.getName());
    	Image image1 = new ImageIcon(this.getClass().getResource("/Images/ingredient" + index1 + ".png")).getImage();
    	Image newImage1 = image1.getScaledInstance(60, 100, Image.SCALE_DEFAULT);
    	ImageIcon icon1 = new ImageIcon(newImage1);
    	

    	int index2 = Ingredient.getIngredientIndex(ing2.getName());
    	Image image2 = new ImageIcon(this.getClass().getResource("/Images/ingredient" + index2 + ".png")).getImage();
    	Image newImage2 = image2.getScaledInstance(60, 100, Image.SCALE_DEFAULT);
    	ImageIcon icon2 = new ImageIcon(newImage2);
    	
    	
    	int index3 = Ingredient.getIngredientIndex(ing3.getName());
    	Image image3 = new ImageIcon(this.getClass().getResource("/Images/ingredient" + index3 + ".png")).getImage();
    	Image newImage3 = image3.getScaledInstance(60, 100, Image.SCALE_DEFAULT);
    	ImageIcon icon3 = new ImageIcon(newImage3);
    	
    	
    	//Set the images
		
		JLabel ingredient1 = new JLabel(icon1);
		ingredient1.setBounds(270, 279, 120, 200);
		getContentPane().add(ingredient1);
		
		JLabel ingredient2 = new JLabel(icon2);
		ingredient2.setBounds(476, 277, 120, 200);
		getContentPane().add(ingredient2);
		
		JLabel ingredient3 = new JLabel(icon3);
		ingredient3.setBounds(675, 279, 120, 200);
		getContentPane().add(ingredient3);
		
	}
	
	private void setReorderingLogic() {
		String[] options = {"1", "2", "3"};
		 
		order1 = new JComboBox<String>(options);
		
		order1.setMaximumRowCount(3);
		order1.setBounds(270, 489, 120, 44);	
		getContentPane().add(order1);
		
		order2 = new JComboBox<String>(options);
		
		order2.setMaximumRowCount(3);
		order2.setBounds(476, 489, 120, 44);
		getContentPane().add(order2);
		
		order3 = new JComboBox<String>(options);
		
		order3.setMaximumRowCount(3);
		order3.setBounds(675, 489, 120, 44);
		getContentPane().add(order3);
		
	    order1.setSelectedItem("1");
	    order2.setSelectedItem("2");
	    order3.setSelectedItem("3");
	}


	private void setAppearance()
	{
		// As soon as the constructor is called,
		// set visibility of the frame to true.
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("KU Alchemists Game");
		this.setSize(1200, 686);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		

	}
	
	private void setButton() {
		JButton submit = new JButton("Submit Ordering");
		submit.setBounds(497, 549, 177, 50);
		getContentPane().add(submit);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOrder1 = (String) order1.getSelectedItem();
                String selectedOrder2 = (String) order2.getSelectedItem();
                String selectedOrder3 = (String) order3.getSelectedItem();
                
                if (!selectedOrder1.equals(selectedOrder2) &&
                        !selectedOrder1.equals(selectedOrder3) &&
                        !selectedOrder2.equals(selectedOrder3)) {
                        // None of them are the same, proceed with your logic
                        System.out.println("Selected Order 1: " + selectedOrder1);
                        System.out.println("Selected Order 2: " + selectedOrder2);
                        System.out.println("Selected Order 3: " + selectedOrder3);
                        
                        List<Ingredient> ingredientList = game.getIngredientStorage().getIngredientList();
                        ingredientList.set(Integer.parseInt(selectedOrder1) - 1, ing1);
                        ingredientList.set(Integer.parseInt(selectedOrder2) - 1, ing2);
                        ingredientList.set(Integer.parseInt(selectedOrder3) - 1, ing3);
                        game.getIngredientStorage().setIngredientList(ingredientList);
                        ElixirOfInsightFrame.this.dispose();
                        MainGameFrame mf = new MainGameFrame(game);

                     
                    } else {
                        // Display an error message or handle the case where the selected items are the same
                        JOptionPane.showMessageDialog(null, "Please select different index for each ingredient.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
 
            }
        });
		
	}
	
}
