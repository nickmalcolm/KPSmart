package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class KPSMenuBar extends JMenuBar { //implements ActionListener{
	
	private JMenu fileMenu;
	private JMenuItem fNew;
	private JMenuItem fOpen;
	private JMenuItem fClose;
	private JMenuItem fSave;
	private JMenuItem fPrint;
	private JMenuItem fExit;
	
	private JMenu editMenu;
	private JMenuItem eUndo;
	private JMenuItem eRedo;
	
	private JMenu mailMenu;
	private JMenuItem mSend;
	
	private JMenu viewMenu;
	private JMenuItem vRevenue;
	private JMenuItem vDeliveryTimes;
	private JMenuItem vAmountOfMail;
	
	private JMenu toolsMenu;
	private JMenuItem tAuthenticate;

	private JMenu helpMenu;
	private JMenuItem hAbout;
	
	public KPSMenuBar(ActionListener a) {
		
		fileMenu = new JMenu("File");
		
			fNew = new JMenuItem("New", KeyEvent.VK_N);
			fNew.addActionListener(a);
			fileMenu.add(fNew);
			
			fOpen = new JMenuItem("Open", KeyEvent.VK_O);
			fOpen.addActionListener(a);
			fileMenu.add(fOpen);
			
			fClose = new JMenuItem("Close", KeyEvent.VK_C);
			fClose.addActionListener(a);
			fileMenu.add(fClose);
			
			fSave = new JMenuItem("Save", KeyEvent.VK_S);
			fSave.addActionListener(a);
			fileMenu.add(fSave);
			
			fPrint = new JMenuItem("Print", KeyEvent.VK_P);
			fPrint.addActionListener(a);
			fileMenu.add(fPrint);
			
			fExit = new JMenuItem("Exit", KeyEvent.VK_E);
			fExit.addActionListener(a);
			fileMenu.add(fExit);
		
		editMenu = new JMenu("Edit");
		
			eUndo = new JMenuItem("Undo", KeyEvent.VK_U);
			eUndo.addActionListener(a);
			editMenu.add(eUndo);
			
			eRedo = new JMenuItem("Redo", KeyEvent.VK_R);
			eRedo.addActionListener(a);
			editMenu.add(eRedo);
			
		mailMenu = new JMenu("Mail");
			
			mSend = new JMenuItem("Send Mail", KeyEvent.VK_S);
			mSend.addActionListener(a);
			mailMenu.add(mSend);
		
		viewMenu = new JMenu("View");
		
			vRevenue = new JMenuItem("Revenue", KeyEvent.VK_V);
			vRevenue.addActionListener(a);
			viewMenu.add(vRevenue);
			
			vDeliveryTimes = new JMenuItem("Average Delivery Times", KeyEvent.VK_T);
			vDeliveryTimes.addActionListener(a);
			viewMenu.add(vDeliveryTimes);
			
			vAmountOfMail = new JMenuItem("Amount of Mail", KeyEvent.VK_A);
			vAmountOfMail.addActionListener(a);
			viewMenu.add(vAmountOfMail);
			
		viewMenu.setEnabled(false);
		
		toolsMenu = new JMenu("Tools");
		
			tAuthenticate = new JMenuItem("Authenticate as manager", KeyEvent.VK_M);
			tAuthenticate.addActionListener(a);
			toolsMenu.add(tAuthenticate);
		
		helpMenu = new JMenu("Help");
		
			hAbout = new JMenuItem("About KPSSmart", KeyEvent.VK_A);
			hAbout.addActionListener(a);
			helpMenu.add(hAbout);
		
		this.add(fileMenu);
		this.add(editMenu);
		this.add(mailMenu);
		this.add(viewMenu);
		this.add(toolsMenu);
		this.add(helpMenu);
	}

	public void manager() {
		viewMenu.setEnabled(true);
	}
	
	public void notManager() {
		viewMenu.setEnabled(false);
	}

//

}
