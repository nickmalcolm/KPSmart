package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class KPSMenuBar extends JMenuBar {
	
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
	
	/**
	 * Creates a custom menu bar for use with KPSFrame
	 * @param actionlistener Passed by parent
	 */
	public KPSMenuBar(ActionListener actionlistener) {
		
		fileMenu = new JMenu("File");
			
			fSave = new JMenuItem("Save", KeyEvent.VK_S);
			fSave.addActionListener(actionlistener);
			fileMenu.add(fSave);
			
			fExit = new JMenuItem("Exit", KeyEvent.VK_E);
			fExit.addActionListener(actionlistener);
			fileMenu.add(fExit);
		
		actionMenu = new JMenu("Actions");
		
			aSend = new JMenuItem("Send Mail", KeyEvent.VK_M);
			aSend.addActionListener(actionlistener);
			actionMenu.add(aSend);
			
			aUpdateCosts = new JMenuItem("Update Costs", KeyEvent.VK_U);
			aUpdateCosts.addActionListener(actionlistener);
			actionMenu.add(aUpdateCosts);
			
			aBusinessFigures = new JMenuItem("View Business Figures", KeyEvent.VK_V);
			aBusinessFigures.addActionListener(actionlistener);
			actionMenu.add(aBusinessFigures);
		
			aAuthenticate = new JMenuItem("Sign in as manager", KeyEvent.VK_S);
			aAuthenticate.addActionListener(actionlistener);
			actionMenu.add(aAuthenticate);
			
			aDeauthenticate = new JMenuItem("Sign out as manager", KeyEvent.VK_O);
			aDeauthenticate.addActionListener(actionlistener);
			actionMenu.add(aDeauthenticate);
			
			notManager();
		
			helpMenu = new JMenu("Help");
		
			hAbout = new JMenuItem("About KPSSmart", KeyEvent.VK_A);
			hAbout.addActionListener(actionlistener);
			helpMenu.add(hAbout);
		
		this.add(fileMenu);
		this.add(actionMenu);
		this.add(helpMenu);
	}

	/**
	 * Switches menu options for manager mode
	 */
	public void manager() {
		aAuthenticate.setEnabled(false);
		aDeauthenticate.setEnabled(true);
	}
	
	/**
	 * Switches menu options for manager mode
	 */
	public void notManager() {
		aAuthenticate.setEnabled(true);
		aDeauthenticate.setEnabled(false);
	}

}
