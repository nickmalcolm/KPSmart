package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class KPSDefaultPanel extends JPanel implements KPSPanel{
	
	public KPSDefaultPanel() {
		super();
		this.setPreferredSize(new Dimension(600, 600));
		this.setBackground(Color.GRAY);
	}
	
	public void reset() {}

}
