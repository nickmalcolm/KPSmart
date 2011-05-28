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
import routes.Firm;

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
		
		//SEND MAIL HANDLING
		if ("Send".equals(e.getActionCommand())) {
			
			//Check for mail panel
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

				kBackend.sendMail(id, weight, volume, origin, destination, priority);
				kFrame.resetMailPanel();
			}	
		}
		
		//CUSTOMER PRICE UPDATE HANDLING
		if ("Update Customer Cost".equals(e.getActionCommand())) {
			ArrayList info = kFrame.returnCustomerPriceUpdateInfo();
			if (info != null) {
				int i = 0;
				DistributionCentre origin = null;
				DistributionCentre destination = null;
				String o = String.valueOf(info.get(i++));
				String d = String.valueOf(info.get(i++));
				Firm firm = new Firm((String)info.get(i++));
				Priority priority = (Priority)info.get(i++);
				double customerPriceCC = Double.valueOf(String.valueOf(info.get(i++)));
				double customerPriceG = Double.valueOf(String.valueOf(info.get(i)));
				
				for (DistributionCentre dist : kBackend.getDistributionCentres()) {
					if (dist.getName().equals(o)) { origin = dist; }
					if (dist.getName().equals(d)) { destination = dist; }
				}
				
				kBackend.updatePrice(origin, destination, customerPriceG, customerPriceCC, priority, firm);
				System.out.println("UPDATE CUSTOMER SUCCESSFUL");
				kFrame.resetUpdatePanel();
			}

		}
		
		//TRANSPORT COST UPDATE HANDLING
		if ("Update Transport Cost".equals(e.getActionCommand())) {
			ArrayList info = kFrame.returnTransportCostUpdateInfo();
			if (info != null) {
				int i = 0;
				DistributionCentre origin = null;
				DistributionCentre destination = null;
				String o = String.valueOf(info.get(i++));
				String d = String.valueOf(info.get(i++));
				Firm firm = new Firm((String)info.get(i++));
				Priority priority = (Priority)info.get(i++);
				double transportPriceCC = Double.valueOf(String.valueOf(info.get(i++)));
				double transportPriceG = Double.valueOf(String.valueOf(info.get(i++)));
				int frequency = Integer.valueOf(String.valueOf(info.get(i++)));;
				int duration = Integer.valueOf(String.valueOf(info.get(i++)));;;
				Day day = (Day)info.get(i);
				
				for (DistributionCentre dist : kBackend.getDistributionCentres()) {
					if (dist.getName().equals(o)) { origin = dist; }
					if (dist.getName().equals(d)) { destination = dist; }
				}
				
				kBackend.updateTransport(origin, destination, transportPriceG, transportPriceCC, frequency, duration, day, priority, firm);
				System.out.println("UPDATE TRANSPORT SUCCESSFUL");
				kFrame.resetUpdatePanel();
			}

		}
		
		//DISCONTINUE TRANSPORT HANDLING
		if ("Discontinue Transport".equals(e.getActionCommand())) {
			ArrayList info = kFrame.returnDiscontinueTransportInfo();
			if (info != null) {
				int i = 0;
				DistributionCentre origin = null;
				DistributionCentre destination = null;
				String o = String.valueOf(info.get(i++));
				String d = String.valueOf(info.get(i++));
				Firm firm = new Firm((String)info.get(i++));
				Priority priority = (Priority)info.get(i++);
				
				for (DistributionCentre dist : kBackend.getDistributionCentres()) {
					if (dist.getName().equals(o)) { origin = dist; }
					if (dist.getName().equals(d)) { destination = dist; }
				}
				
				kBackend.discontinueTransport(origin, destination, priority, firm);
				System.out.println("DISCONTINUE TRANSPORT SUCCESSFUL");
				
			}
			
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
