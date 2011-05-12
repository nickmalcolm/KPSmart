package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class KPSPanel extends JLayeredPane {
	
	public KPSPanel () {
		super();
		this.setPreferredSize(new Dimension(600, 600));
		this.setBackground(Color.GRAY);
		//this.add("Test", new JTextField(10));
	}
}
