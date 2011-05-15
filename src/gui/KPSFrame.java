package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import routes.DistributionCentre;

public class KPSFrame extends JFrame {
	
	private KPSTitlePanel title;
	private JPanel panel;
	private KPSDefaultPanel defaultPanel;
	private KPSMailPanel mailPanel;
	private KPSOutput output;
	private KPSMenuBar menuBar;
	String[] data = {"one", "two", "three"};
	
	public KPSFrame(ActionListener a, Set<DistributionCentre> s) {
		title = new KPSTitlePanel();
		panel = new JPanel(new CardLayout());
		defaultPanel = new KPSDefaultPanel();
		defaultPanel.setName("defaultPanel");
		mailPanel = new KPSMailPanel(a, s);
		mailPanel.setName("mailPanel");
		output = new KPSOutput(data);
		menuBar = new KPSMenuBar(a);
		
		panel.add(defaultPanel, "defaultPanel");
		panel.add(mailPanel, "mailPanel");
		
		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);
		//this.add(new JLabel("Output"));
		//this.add(output, BorderLayout.SOUTH);
		this.setJMenuBar(menuBar);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setFocusable(true);
		this.setResizable(false);
		this.setVisible(true);
		//output.setEnabled(false);
	}
	
	public void sendMail(String s) {
		//panel.setBackground(Color.RED);
		//panel.add(output);
		CardLayout cards = (CardLayout) panel.getLayout();
		cards.show(panel, s);
	}
	
	public void resetMailPanel() {
		CardLayout cards = (CardLayout) panel.getLayout();
		cards.show(panel, "defaultPanel");
		for (Component c : panel.getComponents()) {
			if (c.getName().equals("mailPanel")) {
				((KPSPanel)c).reset();
				System.out.println("Resetting");
			}
		}
	}
	
	public void resetAll() {
		CardLayout cards = (CardLayout) panel.getLayout();
		cards.show(panel, "defaultPanel");
		for (Component c : panel.getComponents()) {
			((KPSPanel)c).reset();
		}
	}
	
	public String getPanel() {
		Component tmp = panel.getComponent(0);
		for (Component c : panel.getComponents()) {
			if (panel.getComponentZOrder(c) > panel.getComponentZOrder(tmp)) {
				tmp = c;
			}
		}
		return tmp.getName();
	}
	
	public ArrayList returnMailPanelInfo() {
		return mailPanel.returnInfo();
	}

	public void manager() {
		menuBar.manager();	
	}
	
	public void notManager() {
		menuBar.notManager();
	}

}
