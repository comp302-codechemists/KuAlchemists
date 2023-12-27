package Screens;

import Business.KUAlchemistsGame;
import DesignSystem.GameFrame;
import uiHelpers.MagicFrame;

public abstract class GeneralFrame extends MagicFrame{
	
	public GeneralFrame(KUAlchemistsGame game) {
		super(game, GameFrame.generalFrameWidth, GameFrame.generalFrameHeight);
	}
}
