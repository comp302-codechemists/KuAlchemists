package artifactScreens;

import Business.Ingredient;
import Business.IngredientStorage;
import Business.KUAlchemistsGame;
import Business.Player;
import Screens.BuyArtifactFrame;
import Screens.MainGameFrame;
import networking.Client;
import Screens.BuyArtifactFrame;
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

public class ElixirOfInsightFrame extends ArtifactFrame {
	
	private JComboBox<String> order1;
	private JComboBox<String> order2;
	private JComboBox<String> order3;
	

	private Ingredient ing1;
	private Ingredient ing2;
	private Ingredient ing3;
	
	private JFrame frame;
	
	
	public ElixirOfInsightFrame(KUAlchemistsGame game, JFrame frame) {
		super(game);
		setBackground("/artifactBackgrounds/ElixirOfInsightArtifact.png");
		setIngredientImages();
		setReorderingLogic();
		setButton();
		this.frame = frame;
	}
	

	
	private void setIngredientImages() {
		
		JLabel orderLabel = new JLabel("Order the top three ingredient cards in the deck as you wish.");
		orderLabel.setForeground(Color.WHITE);
		orderLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		orderLabel.setBounds(45, 350, 500, 75);
		backgroundPanel.add(orderLabel);
		
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
		ingredient1.setBounds(70, 200, 60, 200);
		backgroundPanel.add(ingredient1);
		
		JLabel ingredient2 = new JLabel(icon2);
		ingredient2.setBounds(160, 200, 60, 200);
		backgroundPanel.add(ingredient2);
		
		JLabel ingredient3 = new JLabel(icon3);
		ingredient3.setBounds(250, 200, 60, 200);
		backgroundPanel.add(ingredient3);
		
	}
	
	private void setReorderingLogic() {
		String[] options = {"1", "2", "3"};
		 
		order1 = new JComboBox<String>(options);
		
		order1.setMaximumRowCount(3);
		order1.setBounds(60, 400, 60, 44);	
		backgroundPanel.add(order1);
		
		order2 = new JComboBox<String>(options);
		
		order2.setMaximumRowCount(3);
		order2.setBounds(150, 400, 60, 44);
		backgroundPanel.add(order2);
		
		order3 = new JComboBox<String>(options);
		
		order3.setMaximumRowCount(3);
		order3.setBounds(240, 400, 60, 44);
		backgroundPanel.add(order3);
		
	    order1.setSelectedItem("1");
	    order2.setSelectedItem("2");
	    order3.setSelectedItem("3");
	}
	
	private void setButton() {
		JButton submit = new JButton("Submit");
		submit.setBounds(170, 450, 100, 30);
		backgroundPanel.add(submit);
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
                        frame.dispose();
                        MainGameFrame mf = new MainGameFrame(game);
                        
                        
                        if(game.isOnline()) {

                        	mf.updatePlayerName(Client.instance.getUsername());
                        	Client.instance.setView(mf);
                        	System.out.println("Did Elixir  as " + Client.instance.getUsername() + " newIngreList: " + game.getIngredientStorage().ingredientList);;

                        	Client.instance.sendMessage("ELIXIR," + selectedOrder1 + "," + selectedOrder2 + "," + selectedOrder3);
                       
                        }
                        
                    } else {
                        // Display an error message or handle the case where the selected items are the same
                        JOptionPane.showMessageDialog(null, "Please select different index for each ingredient.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
 
            }
        });
		
	}
	
}
