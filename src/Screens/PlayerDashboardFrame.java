package Screens;

import Business.Player;

public class PlayerDashboardFrame extends GeneralFrame{
	
	/*
	 * A dedicated panel that showcases the current player's avatar, available resources, and actions.
	 * Offers interactive buttons for actions like collecting ingredients, brewing potions, and
	 * submitting publications.
	 */
	
	public PlayerDashboardFrame(Player player) {
		
		
	}
	
	
	
    public static void main(String[] args) {
        // Create and display the frame   
    	Player player = new Player("Simge", "Path", null, null, 10, 0, null);
        new PlayerDashboardFrame(player).setVisible(true);
    }
}
