package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class KPSMenuBar extends JMenuBar { //implements ActionListener{
	
	private JMenu fileMenu;
	private JMenuItem fSave;
	private JMenuItem fExit;
	
	private JMenu actionMenu;
	private JMenuItem aSend;
	private JMenuItem aUpdateCosts;
	private JMenuItem aBusinessFigures;
	private JMenuItem aAuthenticate;
	private JMenuItem aDeauthenticate;

	private JMenu helpMenu;
	private JMenuItem hAbout;
	
	public KPSMenuBar(ActionListener a) {
		
		fileMenu = new JMenu("File");
			
			fSave = new JMenuItem("Save", KeyEvent.VK_S);
			fSave.addActionListener(a);
			fileMenu.add(fSave);
			
			fExit = new JMenuItem("Exit", KeyEvent.VK_E);
			fExit.addActionListener(a);
			fileMenu.add(fExit);
		
		actionMenu = new JMenu("Actions");
		
			aSend = new JMenuItem("Send Mail", KeyEvent.VK_M);
			aSend.addActionListener(a);
			actionMenu.add(aSend);
			
			aUpdateCosts = new JMenuItem("Update Costs", KeyEvent.VK_U);
			aUpdateCosts.addActionListener(a);
			actionMenu.add(aUpdateCosts);
			
			aBusinessFigures = new JMenuItem("View Business Figures", KeyEvent.VK_V);
			aBusinessFigures.addActionListener(a);
			actionMenu.add(aBusinessFigures);
		
			aAuthenticate = new JMenuItem("Sign in as manager", KeyEvent.VK_S);
			aAuthenticate.addActionListener(a);
			actionMenu.add(aAuthenticate);
			
			aDeauthenticate = new JMenuItem("Sign out as manager", KeyEvent.VK_O);
			aDeauthenticate.addActionListener(a);
			actionMenu.add(aDeauthenticate);
			
			notManager();
		
		helpMenu = new JMenu("Help");
		
			hAbout = new JMenuItem("About KPSSmart", KeyEvent.VK_A);
			hAbout.addActionListener(a);
			helpMenu.add(hAbout);
		
		this.add(fileMenu);
		this.add(actionMenu);
		this.add(helpMenu);
	}

	public void manager() {
		aAuthenticate.setEnabled(false);
		aDeauthenticate.setEnabled(true);
		//aBusinessFigures.setEnabled(true);
	}
	
	public void notManager() {
		aAuthenticate.setEnabled(true);
		aDeauthenticate.setEnabled(false);
		//aBusinessFigures.setEnabled(false);
	}

}
