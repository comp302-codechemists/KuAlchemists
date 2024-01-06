package Controllers;

import Business.Artifact;
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
			return new DiscountArtifactFrame(game);
		case "ReputationBoosterArtifact":
			return new ReputationBoosterArtifactFrame(game);
		case "PrintingPressArtifact":
			return new PrintingPressArtifactFrame(game);
		case "WisdomIdolArtifact":
			return new WisdomIdolArtifactFrame(game);
		case "MagicMortarArtifact":
			return new MagicMortarArtifactFrame(game);
		case "ElixirOfInsightArtifact":
			return new ElixirOfInsightFrame(game);
		default:
			return null;
		}			
	}	

}
