package artifactScreens;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Business.KUAlchemistsGame;
import DesignSystem.GameFrame;
import uiHelpers.MagicFrame;

public abstract class ArtifactFrame extends MagicFrame{
			
	public ArtifactFrame(KUAlchemistsGame game) {
		super(game, GameFrame.artifactFrameWidth, GameFrame.artifactFrameHeight);
	}
}
