package gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.regex.Pattern;

import javax.swing.*;

import backend.Day;

import priority.Priority;

import routes.*;

public class KPSUpdatePanel extends JPanel implements KPSPanel{
	
	private Box completeBox;
	
	//Labels
	private JLabel customerOriginsLabel;
	private JLabel customerDestinationsLabel;
	private JLabel customerFirmLabel;
	private JLabel customerPriceGLabel;
	private JLabel customerPriceCCLabel;
	private JLabel customerPriorityLabel;
	
	private JLabel transportOriginsLabel;
	private JLabel transportDestinationsLabel;
	private JLabel transportFirmLabel;
	private JLabel transportCostGLabel;
	private JLabel transportCostCCLabel;
	private JLabel transportDayLabel;
	private JLabel transportFrequencyLabel;
	private JLabel transportDurationLabel;
	private JLabel transportPriorityLabel;
	
	private JLabel discontinueOriginsLabel;
	private JLabel discontinueDestinationsLabel;
	private JLabel discontinueFirmLabel;
	private JLabel discontinuePriorityLabel;
	
	//Update Customer Cost Fields
	private Box customerCompleteBox;
	private Box customerFieldsBox;
	private Box customerLeftBox;
	private Box customerRightBox;
	private Box customerButtonBox;
	private JComboBox customerOrigins;
	private JComboBox customerDestinations;
	private JComboBox customerFirms;
	private JComboBox customerPriority;
	private JTextField customerPriceCC;
	private JTextField customerPriceG;
	private JButton customerButton;
	private JButton customerCancel;
	
	//Update Transport Costs Fields
	private Box transportCompleteBox;
	private Box transportFieldsBox;
	private Box transportLeftBox;
	private Box transportRightBox;
	private Box transportDayBox;
	private Box transportButtonBox;
	private JComboBox transportOrigins;
	private JComboBox transportDestinations;
	private JComboBox transportFirms;
	private JComboBox transportPriority;
	private JComboBox transportDay;
	private JTextField transportPriceCC;
	private JTextField transportPriceG;
	private JTextField transportFreqeuency;
	private JTextField transportDuration;
	private JButton  transportButton;
	private JButton transportCancel;
	
	//Discontinue Transport Fields
	private Box discontinueCompleteBox;
	private Box discontinueFieldsBox;
	private Box discontinueLeftBox;
	private Box discontinueRightBox;
	private Box discontinueButtonBox;
	private JComboBox discontinueOrigins;
	private JComboBox discontinueDestinations;
	private JComboBox discontinueFirms;
	private JComboBox discontinuePriority;
	private JButton discontinueButton;
	private JButton discontinueCancel;
	
	//Arrays
	private String[] originList;
	private String[] destList;
	private String[] firmList;
	private Priority[] priorityList = Priority.values();
	private Day[] dayList = Day.values();

	/**
	 * Creates a KPSUpdatePanel for use with KPSFrame
	 * @param actionlistener Passed by parent
	 * @param centres Set of DistributionCentre elements for population
	 * @param firms List of Firm elements for population
	 */
	public KPSUpdatePanel(ActionListener actionlistener, Set<DistributionCentre> centres, List<Firm> firms) {
		super();
		
		//ERROR CHECKING
		if (centres != null) {
			originList = new String[centres.size()];
			destList = new String[centres.size()];
			int i = 0;
			for (DistributionCentre d : centres) {
				originList[i] = d.getName();
				destList[i++] = d.getName();
			}
		}
		//XML FILE FAILED TO LOAD
		else {
			originList = new String[1];
			destList = new String[1];
			originList[0] = "ERROR LOADING ORIGINS";
			destList[0] = "ERROR LOADING DESTINATIONS";
		}
		
		if (firms != null) {
			firmList = new String[firms.size()];
			int i = 0;
			for (Firm f : firms) {
				firmList[i++] = f.getName();
			}
		}
		else {
			firmList = new String[1];
			firmList[0] = "ERROR LOADING FIRMS";
		}
		
		this.setPreferredSize(new Dimension(600,600));
		
		customerOriginsLabel = new JLabel("Origin:");
		customerDestinationsLabel = new JLabel("Destination:");
		customerFirmLabel = new JLabel("Firms");
		customerPriceGLabel = new JLabel("Customer Price Per G:");
		customerPriceCCLabel = new JLabel("Customer Price Per CC:");
		customerPriorityLabel = new JLabel("Priority:");
		
		transportOriginsLabel = new JLabel("Origin:");
		transportDestinationsLabel = new JLabel("Destination:");
		transportFirmLabel = new JLabel("Firms");
		transportCostGLabel = new JLabel("Transport Cost Per G");
		transportCostCCLabel = new JLabel("Transport Cost Per CC:");
		transportDayLabel = new JLabel("Day:");
		transportFrequencyLabel = new JLabel("Frequency:");
		transportDurationLabel = new JLabel("Duration:");
		transportPriorityLabel = new JLabel("Priority:");
		
		discontinueOriginsLabel = new JLabel("Origin:");
		discontinueDestinationsLabel = new JLabel("Destination:");
		discontinueFirmLabel = new JLabel("Firms");
		discontinuePriorityLabel = new JLabel("Priority:");
		
		customerOriginsLabel.setFont(new Font("Arial", 0, 14));
		customerDestinationsLabel.setFont(new Font("Arial", 0, 14));
		customerFirmLabel.setFont(new Font("Arial", 0, 14));
		customerPriceGLabel.setFont(new Font("Arial", 0, 14));
		customerPriceCCLabel.setFont(new Font("Arial", 0, 14));
		customerPriorityLabel.setFont(new Font("Arial", 0, 14));
		
		transportOriginsLabel.setFont(new Font("Arial", 0, 14));
		transportDestinationsLabel.setFont(new Font("Arial", 0, 14));
		transportFirmLabel.setFont(new Font("Arial", 0, 14));
		transportCostGLabel.setFont(new Font("Arial", 0, 14));
		transportCostCCLabel.setFont(new Font("Arial", 0, 14));
		transportDayLabel.setFont(new Font("Arial", 0, 14));
		transportFrequencyLabel.setFont(new Font("Arial", 0, 14));
		transportDurationLabel.setFont(new Font("Arial", 0, 14));
		transportPriorityLabel.setFont(new Font("Arial", 0, 14));
		
		discontinueOriginsLabel.setFont(new Font("Arial", 0, 14));
		discontinueDestinationsLabel.setFont(new Font("Arial", 0, 14));
		discontinueFirmLabel.setFont(new Font("Arial", 0, 14));
		discontinuePriorityLabel.setFont(new Font("Arial", 0, 14));
		
		customerOriginsLabel.setAlignmentX(LEFT_ALIGNMENT);
		customerDestinationsLabel.setAlignmentX(LEFT_ALIGNMENT);
		customerFirmLabel.setAlignmentX(LEFT_ALIGNMENT);
		customerPriceGLabel.setAlignmentX(LEFT_ALIGNMENT);
		customerPriceCCLabel.setAlignmentX(LEFT_ALIGNMENT);
		customerPriorityLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		transportOriginsLabel.setAlignmentX(LEFT_ALIGNMENT);
		transportDestinationsLabel.setAlignmentX(LEFT_ALIGNMENT);
		transportFirmLabel.setAlignmentX(LEFT_ALIGNMENT);
		transportCostGLabel.setAlignmentX(LEFT_ALIGNMENT);
		transportCostCCLabel.setAlignmentX(LEFT_ALIGNMENT);
		transportDayLabel.setAlignmentX(LEFT_ALIGNMENT);
		transportFrequencyLabel.setAlignmentX(LEFT_ALIGNMENT);
		transportDurationLabel.setAlignmentX(LEFT_ALIGNMENT);
		transportPriorityLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		discontinueOriginsLabel.setAlignmentX(LEFT_ALIGNMENT);
		discontinueDestinationsLabel.setAlignmentX(LEFT_ALIGNMENT);
		discontinueFirmLabel.setAlignmentX(LEFT_ALIGNMENT);
		discontinuePriorityLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		completeBox = new Box(BoxLayout.Y_AXIS);
		
		//Customer fields
		customerCompleteBox = new Box(BoxLayout.Y_AXIS);
		customerFieldsBox = new Box(BoxLayout.X_AXIS);
		customerLeftBox = new Box(BoxLayout.Y_AXIS);
		customerRightBox = new Box(BoxLayout.Y_AXIS); 
		customerButtonBox = new Box(BoxLayout.X_AXIS);
		
		customerOrigins = new JComboBox(originList);
		customerDestinations = new JComboBox(destList);
		customerFirms = new JComboBox(firmList);
		customerPriority = new JComboBox(priorityList);
		customerPriceCC = new JTextField();
		customerPriceG = new JTextField();
		
		customerButton = new JButton("Update Customer Cost");
		customerButton.addActionListener(actionlistener);
		customerCancel = new JButton("Cancel");
		customerCancel.addActionListener(actionlistener);
		
		customerOrigins.setAlignmentX(LEFT_ALIGNMENT);
		customerDestinations.setAlignmentX(LEFT_ALIGNMENT);
		customerFirms.setAlignmentX(LEFT_ALIGNMENT);
		customerPriority.setAlignmentX(LEFT_ALIGNMENT);
		customerPriceCC.setAlignmentX(LEFT_ALIGNMENT);
		customerPriceG.setAlignmentX(LEFT_ALIGNMENT);
		
		customerLeftBox.add(customerOriginsLabel);
		customerLeftBox.add(customerOrigins);
		customerLeftBox.add(Box.createRigidArea(new Dimension(0, 5)));
		customerLeftBox.add(customerPriorityLabel);
		customerLeftBox.add(customerPriority);
		customerLeftBox.add(Box.createRigidArea(new Dimension(0, 5)));
		customerLeftBox.add(customerPriceGLabel);
		customerLeftBox.add(customerPriceG);
		customerLeftBox.setPreferredSize(new Dimension(280,150));
		
		customerRightBox.add(customerDestinationsLabel);
		customerRightBox.add(customerDestinations);
		customerRightBox.add(Box.createRigidArea(new Dimension(0, 5)));
		customerRightBox.add(customerFirmLabel);
		customerRightBox.add(customerFirms);
		customerRightBox.add(Box.createRigidArea(new Dimension(0, 5)));
		customerRightBox.add(customerPriceCCLabel);
		customerRightBox.add(customerPriceCC);
		customerRightBox.setPreferredSize(new Dimension(280,150));
		
		customerButtonBox.add(customerButton);
		customerButtonBox.add(Box.createRigidArea(new Dimension(5, 0)));
		customerButtonBox.add(customerCancel);
		
		customerFieldsBox.add(customerLeftBox);
		customerFieldsBox.add(Box.createRigidArea(new Dimension(5, 0)));
		customerFieldsBox.add(customerRightBox);
		customerCompleteBox.add(customerFieldsBox);
		customerCompleteBox.add(Box.createRigidArea(new Dimension(0, 5)));
		customerCompleteBox.add(customerButtonBox);
		customerCompleteBox.setPreferredSize(new Dimension(580,165));
		
		JPanel customerPanel = new JPanel();
		customerPanel.setPreferredSize(new Dimension(590,175));
		customerPanel.add(customerCompleteBox, BorderLayout.CENTER);
		completeBox.add(customerPanel);
		completeBox.add(Box.createRigidArea(new Dimension(0, 5)));
		
		//Transport Cost fields
		transportCompleteBox = new Box(BoxLayout.Y_AXIS);
		transportFieldsBox = new Box(BoxLayout.X_AXIS);
		transportLeftBox = new Box(BoxLayout.Y_AXIS);
		transportRightBox = new Box(BoxLayout.Y_AXIS); 
		transportDayBox = new Box(BoxLayout.Y_AXIS);
		transportButtonBox = new Box(BoxLayout.X_AXIS);
		
		transportOrigins = new JComboBox(originList);
		transportDestinations = new JComboBox(destList);
		transportFirms = new JComboBox(firmList);
		transportPriority = new JComboBox(priorityList);
		transportPriceCC = new JTextField();
		transportPriceG = new JTextField();
		transportDay = new JComboBox(dayList);
		transportFreqeuency = new JTextField();
		transportDuration = new JTextField();
		
		transportButton = new JButton("Update Transport Cost");
		transportButton.addActionListener(actionlistener);
		transportCancel = new JButton("Cancel");
		transportCancel.addActionListener(actionlistener);
				
		transportOrigins.setAlignmentX(LEFT_ALIGNMENT);
		transportDestinations.setAlignmentX(LEFT_ALIGNMENT);
		transportFirms.setAlignmentX(LEFT_ALIGNMENT);
		transportPriority.setAlignmentX(LEFT_ALIGNMENT);
		transportPriceCC.setAlignmentX(LEFT_ALIGNMENT);
		transportPriceG.setAlignmentX(LEFT_ALIGNMENT);
		transportDay.setAlignmentX(LEFT_ALIGNMENT);
		transportFreqeuency.setAlignmentX(LEFT_ALIGNMENT);
		transportDuration.setAlignmentX(LEFT_ALIGNMENT);
		
		transportLeftBox.add(transportOriginsLabel);
		transportLeftBox.add(transportOrigins);
		transportLeftBox.add(Box.createRigidArea(new Dimension(0, 5)));
		transportLeftBox.add(transportPriorityLabel);
		transportLeftBox.add(transportPriority);
		transportLeftBox.add(Box.createRigidArea(new Dimension(0, 5)));
		transportLeftBox.add(transportCostGLabel);
		transportLeftBox.add(transportPriceG);
		transportLeftBox.add(Box.createRigidArea(new Dimension(0, 5)));
		transportLeftBox.add(transportFrequencyLabel);
		transportLeftBox.add(transportFreqeuency);
		transportLeftBox.setPreferredSize(new Dimension(280,230));
		
		transportRightBox.add(transportDestinationsLabel);
		transportRightBox.add(transportDestinations);
		transportRightBox.add(Box.createRigidArea(new Dimension(0, 5)));
		transportRightBox.add(transportFirmLabel);
		transportRightBox.add(transportFirms);
		transportRightBox.add(Box.createRigidArea(new Dimension(0, 5)));
		transportRightBox.add(transportCostCCLabel);
		transportRightBox.add(transportPriceCC);
		transportRightBox.add(Box.createRigidArea(new Dimension(0, 5)));
		transportRightBox.add(transportDurationLabel);
		transportRightBox.add(transportDuration);
		transportRightBox.setPreferredSize(new Dimension(280,230));
		
		transportDayBox.add(transportDayLabel);
		transportDayBox.add(transportDay);
		transportDayBox.setAlignmentX(CENTER_ALIGNMENT);
		
		transportButtonBox.add(transportButton);
		transportButtonBox.add(Box.createRigidArea(new Dimension(5, 0)));
		transportButtonBox.add(transportCancel);
		
		transportFieldsBox.add(transportLeftBox);
		transportFieldsBox.add(Box.createRigidArea(new Dimension(5, 0)));
		transportFieldsBox.add(transportRightBox);
		transportCompleteBox.add(transportFieldsBox);
		transportCompleteBox.add(Box.createRigidArea(new Dimension(0, 5)));
		transportCompleteBox.add(transportDayBox);
		transportCompleteBox.add(Box.createRigidArea(new Dimension(0, 5)));
		transportCompleteBox.add(transportButtonBox);
		transportCompleteBox.setPreferredSize(new Dimension(580,265));

		JPanel transportPanel = new JPanel();
		transportPanel.setPreferredSize(new Dimension(590,275));
		transportPanel.add(transportCompleteBox, BorderLayout.CENTER);
		completeBox.add(transportPanel);
		completeBox.add(Box.createRigidArea(new Dimension(0, 5)));
		
		//Discontinue fields
		discontinueCompleteBox = new Box(BoxLayout.Y_AXIS);
		discontinueFieldsBox = new Box(BoxLayout.X_AXIS);
		discontinueLeftBox = new Box(BoxLayout.Y_AXIS);
		discontinueRightBox = new Box(BoxLayout.Y_AXIS); 
		discontinueButtonBox = new Box(BoxLayout.X_AXIS);
		discontinueOrigins = new JComboBox(originList);
		discontinueDestinations = new JComboBox(destList);
		discontinueFirms = new JComboBox(firmList);
		discontinuePriority = new JComboBox(priorityList);
		discontinueButton = new JButton("Discontinue Transport");
		discontinueButton.addActionListener(actionlistener);
		discontinueCancel = new JButton("Cancel");
		discontinueCancel.addActionListener(actionlistener);
		
		discontinueOrigins.setAlignmentX(LEFT_ALIGNMENT);
		discontinueDestinations.setAlignmentX(LEFT_ALIGNMENT);
		discontinueFirms.setAlignmentX(LEFT_ALIGNMENT);
		discontinuePriority.setAlignmentX(LEFT_ALIGNMENT);
		
		discontinueLeftBox.add(discontinueOriginsLabel);
		discontinueLeftBox.add(discontinueOrigins);
		discontinueLeftBox.add(Box.createRigidArea(new Dimension(0, 5)));
		discontinueLeftBox.add(discontinuePriorityLabel);
		discontinueLeftBox.add(discontinuePriority);
		discontinueLeftBox.setPreferredSize(new Dimension(280,230));
		
		discontinueRightBox.add(discontinueDestinationsLabel);
		discontinueRightBox.add(discontinueDestinations);
		discontinueRightBox.add(Box.createRigidArea(new Dimension(0, 5)));
		discontinueRightBox.add(discontinueFirmLabel);
		discontinueRightBox.add(discontinueFirms);
		discontinueRightBox.setPreferredSize(new Dimension(280,230));
		
		discontinueButtonBox.add(discontinueButton);
		discontinueButtonBox.add(Box.createRigidArea(new Dimension(5, 0)));
		discontinueButtonBox.add(discontinueCancel);
		
		discontinueFieldsBox.add(discontinueLeftBox);
		discontinueFieldsBox.add(Box.createRigidArea(new Dimension(5, 0)));
		discontinueFieldsBox.add(discontinueRightBox);
		discontinueCompleteBox.add(discontinueFieldsBox);
		discontinueCompleteBox.add(Box.createRigidArea(new Dimension(0, 5)));
		discontinueCompleteBox.add(discontinueButtonBox);
		discontinueCompleteBox.setPreferredSize(new Dimension(580,120));
		
		JPanel discontinuePanel = new JPanel();
		discontinuePanel.setPreferredSize(new Dimension(590,130));
		discontinuePanel.add(discontinueCompleteBox, BorderLayout.CENTER);
		completeBox.add(discontinuePanel);
		
		this.add(completeBox);
		this.setBackground(Color.GRAY);
		
	}
	
	/**
	 * Returns an ArrayList of customer price update information
	 * (WARNING: The contents are undefined and variable. Handle with care!)
	 * @return An ArrayList of mail information of differing types
	 */
	public ArrayList returnCustomerPriceUpdateInfo() {
		//Error checking of text fields
		try {
			double d = Double.valueOf(String.valueOf(this.customerPriceCC.getText()));
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Customer Price Per CC MUST be a number", "Invalid Entry Format", JOptionPane.ERROR_MESSAGE);
			reset();
			return null;
		}
		try {
			double d = Double.valueOf(String.valueOf(this.customerPriceG.getText()));
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Customer Price Per G MUST be a number", "Invalid Entry Format", JOptionPane.ERROR_MESSAGE);
			reset();
			return null;
		}
		ArrayList info = new ArrayList();
		info.add(this.customerOrigins.getSelectedItem());
		info.add(this.customerDestinations.getSelectedItem());
		info.add(this.customerFirms.getSelectedItem());
		info.add(this.customerPriority.getSelectedItem());
		info.add(this.customerPriceCC.getText());
		info.add(this.customerPriceG.getText());
		return info;
	}
	
	/**
	 * Returns an ArrayList of transport cost update information
	 * (WARNING: The contents are undefined and variable. Handle with care!)
	 * @return An ArrayList of mail information of differing types
	 */
	public ArrayList returnTransportCostUpdateInfo() {
		//Error checking of text fields
		try {
			double d = Double.valueOf(String.valueOf(this.transportPriceG.getText()));
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Transport Cost Per G MUST be a number", "Invalid Entry Format", JOptionPane.ERROR_MESSAGE);
			reset();
			return null;
		}
		try {
			double d = Double.valueOf(String.valueOf(this.transportPriceCC.getText()));
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Transport Cost Per CC MUST be a number", "Invalid Entry Format", JOptionPane.ERROR_MESSAGE);
			reset();
			return null;
		}
		try {
			int d = Integer.valueOf(String.valueOf(this.transportFreqeuency.getText()));
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Transport Frequency MUST be a number", "Invalid Entry Format", JOptionPane.ERROR_MESSAGE);
			reset();
			return null;
		}
		try {
			int d = Integer.valueOf(String.valueOf(this.transportDuration.getText()));
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Transport Duration MUST be a number", "Invalid Entry Format", JOptionPane.ERROR_MESSAGE);
			reset();
			return null;
		}
		ArrayList info = new ArrayList();
		info.add(this.transportOrigins.getSelectedItem());
		info.add(this.transportDestinations.getSelectedItem());
		info.add(this.transportFirms.getSelectedItem());
		info.add(this.transportPriority.getSelectedItem());
		info.add(this.transportPriceCC.getText());
		info.add(this.transportPriceG.getText());
		info.add(this.transportFreqeuency.getText());
		info.add(this.transportDuration.getText());
		info.add(this.transportDay.getSelectedItem());
		return info;
	}
	
	/**
	 * Returns an ArrayList of discontinue transport information
	 * (WARNING: The contents are undefined and variable. Handle with care!)
	 * @return An ArrayList of mail information of differing types
	 */
	public ArrayList returnDiscontinueTransportInfo() {
		ArrayList info = new ArrayList();
		info.add(this.discontinueOrigins.getSelectedItem());
		info.add(this.discontinueDestinations.getSelectedItem());
		info.add(this.discontinueFirms.getSelectedItem());
		info.add(this.discontinuePriority.getSelectedItem());
		return info;
	}

	/**
	 * Resets the panel
	 */
	public void reset() {
		
		//Update Customer Cost Fields
		customerOrigins.setSelectedIndex(0);
		customerDestinations.setSelectedIndex(0);
		customerFirms.setSelectedIndex(0);
		customerPriority.setSelectedIndex(0);
		customerPriceCC.setText("");
		customerPriceG.setText("");
		
		//Update Transport Costs Fields
		transportOrigins.setSelectedIndex(0);
		transportDestinations.setSelectedIndex(0);
		transportFirms.setSelectedIndex(0);
		transportPriority.setSelectedIndex(0);
		transportPriceCC.setText("");
		transportPriceG.setText("");
		transportFreqeuency.setText("");
		transportDuration.setText("");
		transportDay.setSelectedIndex(0);

		//Discontinue Transport Fields
		discontinueOrigins.setSelectedIndex(0);
		discontinueDestinations.setSelectedIndex(0);
		discontinueFirms.setSelectedIndex(0);
		discontinuePriority.setSelectedIndex(0);
	}

}
