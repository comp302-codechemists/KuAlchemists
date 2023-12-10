package DesignSystem;

import java.awt.Color;

import javax.swing.JLabel;

public class DashboardLabel extends JLabel{
	
	private String labelText;
	
	public DashboardLabel(String labelText)
	{
		this.labelText = labelText;
		setText(labelText);
		setForeground(Color.white);
		setOpaque(false);
		setFont(GameText.labelText);
	}
	
}