package kpsmart;

import java.awt.BorderLayout;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import priority.Priority;
import routes.DistributionCentre;

import gui.*;
import backend.*;

public class KPSmart implements ActionListener{
	
	KPSBackend kBackend;
	KPSFrame kFrame;
	JPasswordField kPasswordField;
	
	public KPSmart() {
	
		kBackend = new KPSBackend();
		kBackend.parseXMLRecord();
		kFrame = new KPSFrame(this, kBackend.getDistributionCentres(), kBackend.findFirms());
		kPasswordField = new JPasswordField(10);
		//KPSpasswordField.setActionCommand("OK");
		//KPSpasswordField.addActionListener(this);
	
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		
		//FILE OPTIONS
		if ("New".equals(e.getActionCommand())) {
			System.out.println(kBackend.testMethod());
			return;
		}
				
		//ACTION OPTIONS
		if ("Send Mail".equals(e.getActionCommand())) {
			kFrame.displayPanel("mailPanel");
			return;
		}
		
		if ("Update Costs".equals(e.getActionCommand())) {
			kFrame.displayPanel("updatePanel");
		}
		
		if ("View Business Figures".equals(e.getActionCommand())) {
			kFrame.displayPanel("eventsPanel");
		}
		
		if ("Sign in as manager".equals(e.getActionCommand())) {
			kPasswordField = new JPasswordField(10);
			JOptionPane.showMessageDialog(kFrame, kPasswordField, "Password Required", JOptionPane.WARNING_MESSAGE);
		
			String pass = String.valueOf(kPasswordField.getPassword());
			if(kBackend.authenticateManager(pass)) {
				kFrame.manager();
				System.out.println("MANAGER MODE");
				return;
			}
			
		}
		
		if ("Sign out as manager".equals(e.getActionCommand())) {
			kFrame.notManager();
			if (kFrame.getPanel("eventsPanel")) {
				kFrame.displayPanel("defaultPanel");
			}
			return;
		}
		
		//HELP OPTIONS
		if ("About KPSSmart".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(kFrame, "KPSSmart by\n  Nick Malcolm\n  Liam O'Connor\n " +
					" Janella Espinas\n  Sean Arnold\n  Robert Crowe");
			return;
		}
		
		//OK BUTTON HANDLING
		if ("OK".equals(e.getActionCommand())) {
			System.out.println(kFrame.getPanel("mailPanel")); //For testing
			
			//MAIL PANEL
			if (kFrame.getPanel("mailPanel")) {

				ArrayList<Object> info = kFrame.returnMailPanelInfo();
				for (Object o : info) {
					System.out.println(String.valueOf(o));
				}
				int i = 0;
				int id = Integer.valueOf(String.valueOf(info.get(i++)));
				i++; //Address
				double weight = Double.valueOf(String.valueOf(info.get(i++)));
				double volume = Double.valueOf(String.valueOf(info.get(i++)));
				String o = String.valueOf(info.get(i++));
				DistributionCentre origin = null;
				String d = String.valueOf(info.get(i++));
				DistributionCentre destination = null;
				Priority priority = (Priority)info.get(i);
				for (DistributionCentre dist : kBackend.getDistributionCentres()) {
					if (dist.getName().equals(o)) { origin = dist; }
					if (dist.getName().equals(d)) { destination = dist; }
				}
				System.out.println("part 1 of mail sending - in kpsmart");
				kBackend.sendMail(id, weight, volume, origin, destination, priority);
				kFrame.resetMailPanel();
			}	
			System.out.println("3");
		}
		
		//CANCEL BUTTON HANDLING
		if ("Cancel".equals(e.getActionCommand())) {
			kFrame.resetAll();
		}
		
		//EXit BUTTON HANDLING
		if ("Exit".equals(e.getActionCommand())) {
			System.exit(0);
		}
	}
}
