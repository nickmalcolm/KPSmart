package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class KPSPanel extends JLayeredPane {
	
	public KPSPanel (CardLayout cardLayout) {
		super();
		this.setPreferredSize(new Dimension(600, 600));
		this.setBackground(Color.WHITE);
		//this.add("Test", new JTextField(10));
	}
}
