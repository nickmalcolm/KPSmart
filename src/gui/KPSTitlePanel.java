package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class KPSTitlePanel extends JPanel{
	
	ClassLoader cl = this.getClass().getClassLoader();
	Image title = Toolkit.getDefaultToolkit().getImage(cl.getResource("images/kpsmart.jpg"));
	
	public KPSTitlePanel() {
		super();
		this.setPreferredSize(new Dimension(600, 100));
	}
	
	public void paint(Graphics g) {
		g.setFont(new Font("Arial", 1, 60));
		g.drawImage(title, 0, 0, this);
	}

}
