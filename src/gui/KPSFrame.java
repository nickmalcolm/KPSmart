package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class KPSFrame extends JFrame {
	
	private KPSPanel panel;
	private KPSOutput output;
	private KPSMenuBar menuBar;
	
	public KPSFrame(ActionListener a) {
		 
		panel = new KPSPanel();
		output = new KPSOutput();
		menuBar = new KPSMenuBar(a);
		
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.NORTH);
		this.add(new JLabel("Output"));
		this.add(output, BorderLayout.SOUTH);
		this.setJMenuBar(menuBar);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setFocusable(true);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void manager() {
		menuBar.manager();	
	}
	
	public void notManager() {
		menuBar.notManager();
	}

}
