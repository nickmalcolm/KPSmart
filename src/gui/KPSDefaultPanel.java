package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class KPSDefaultPanel extends JPanel implements KPSPanel{
	
	/**
	 * Creates a blank panel of 600x600 with no functionality. Used only
	 * as a cover for CardLayout.
	 */
	public KPSDefaultPanel() {
		super();
		this.setPreferredSize(new Dimension(600, 600));
		this.setBackground(Color.GRAY);
	}
	
	/**
	 * Resets the frame. Effectively does nothing.
	 */
	public void reset() {}

}
