package Controllers;

import Business.KUAlchemistsGame;
import artifactScreens.ArtifactFrame;
import artifactScreens.DiscountArtifactFrame;
import artifactScreens.ElixirOfInsightFrame;
import artifactScreens.MagicMortarArtifactFrame;
import artifactScreens.PrintingPressArtifactFrame;
import artifactScreens.ReputationBoosterArtifactFrame;
import artifactScreens.WisdomIdolArtifactFrame;

public class buyArtifactController {
	
	private KUAlchemistsGame game;
	
	public buyArtifactController(KUAlchemistsGame game)
	{
		this.game = game;
	}
	
	public String buyArtifactHandler() {
		String boughtArtifact = game.currentPlayer.buyArtifact();
		game.nextPlayer();
		return boughtArtifact;
	}
	
	public ArtifactFrame getArtifactFrame(String artifactName)
	{
		switch(artifactName) {
		case "DiscountArtifact":
			return new DiscountArtifactFrame();
		
		case "ReputationBoosterArtifact":
			return new ReputationBoosterArtifactFrame();
		case "PrintingPressArtifact":
			return new PrintingPressArtifactFrame();
		case "WisdomIdolArtifact":
			return new WisdomIdolArtifactFrame();
		case "MagicMortarArtifact":
			return new MagicMortarArtifactFrame();
		case "ElixirOfInsight":
			return new ElixirOfInsightFrame();
		default:
			return null;
		}
		
			
	}

}
