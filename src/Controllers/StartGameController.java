package Controllers;

import java.util.ArrayList;
import java.util.List;

import Business.KUAlchemistsGame;

public class StartGameController {
	
	KUAlchemistsGame game;
	
	public StartGameController(KUAlchemistsGame game)
	{
		this.game = game;
	}
	
	public void handleStartGame(String p1name, String p2name, String avatar1, String avatar2) {
		
		List<String> nameList = new ArrayList<String>();
		nameList.add(p1name);
		nameList.add(p2name);
		List<String> avatarList = new ArrayList<String>();
		avatarList.add(avatar1);
		avatarList.add(avatar2);
		
    	game.play(nameList,avatarList);

	}
	
	public void handleStartGame(String p1name, String p2name, String p3name, 
								String avatar1, String avatar2, String avatar3) {
		
		List<String> nameList = new ArrayList<String>();
		nameList.add(p1name);
		nameList.add(p2name);
		nameList.add(p3name);
		List<String> avatarList = new ArrayList<String>();
		avatarList.add(avatar1);
		avatarList.add(avatar2);
		avatarList.add(avatar3);
		
    	game.play(nameList,avatarList);

	}
	
	public void handleStartGame(String p1name, String p2name, String p3name, String p4name, 
								String avatar1, String avatar2, String avatar3, String avatar4) {
		
		List<String> nameList = new ArrayList<String>();
		nameList.add(p1name);
		nameList.add(p2name);
		nameList.add(p3name);
		nameList.add(p4name);
		List<String> avatarList = new ArrayList<String>();
		avatarList.add(avatar1);
		avatarList.add(avatar2);
		avatarList.add(avatar3);
		avatarList.add(avatar4);
		
    	game.play(nameList,avatarList);

	}
	
	

}
