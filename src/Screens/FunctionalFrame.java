package Screens;
import Business.KUAlchemistsGame;
import DesignSystem.GameFrame;
import uiHelpers.MagicFrame;

public abstract class FunctionalFrame extends MagicFrame{
	
	public FunctionalFrame(KUAlchemistsGame game) {
		super(game, GameFrame.functionalFrameWidth, GameFrame.functionalFrameHeight);
	}
}
