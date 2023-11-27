package Business;
import javax.swing.SwingUtilities;
import Screens.WelcomeFrame;

public class Main {
	
	IngredientStorage ingredientStorage = new IngredientStorage();
	
    public static void main(String[] args) {
        // Create and display the frame
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WelcomeFrame().setVisible(true);
                System.out.println("SONDENEME");
            }
        });
    }

}
