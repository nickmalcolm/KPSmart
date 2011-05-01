package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class KPSPanel extends JPanel {
	
	public KPSPanel () {
		super();
		this.setPreferredSize(new Dimension(600, 300));
		this.setBackground(Color.GRAY);
		//this.add("Test", new JTextField(10));
	}
}
