package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;

import priority.Priority;
import routes.DistributionCentre;

public class KPSMailPanel extends JPanel{
	
	private Box mailPanelTitle;
	//private JLabel mailPanelLabel;
	private JLabel mailPanelInstructions;
	
	private Box contentsBox;
	private Box buttonsBox;
	
	private JLabel idLabel;
	private JFormattedTextField idField;
	
	private JLabel addressDetailsLabel;
	private JTextField addressField;
	
	private JLabel weightLabel;
	private JSlider weightSlider;
	
	private JLabel volumeLabel;
	private JSlider volumeSlider;
	
	private JLabel originLabel;
	private JComboBox origins;
	private String[] originList;
	
	private JLabel destLabel;
	private JComboBox destinations;
	private String[] destList;
	
	private JLabel priorityLabel;
	private JComboBox priorities;
	private Priority[] priorityList = Priority.values();
	
	private JButton okButton;
	private JButton cancelButton;
	
	public KPSMailPanel(ActionListener a, Set<DistributionCentre> s) {
		super();
		
		if (s != null) {}
		else {
			originList = new String[1];
			destList = new String[1];
			originList[0] = "Your mum's house";
			destList[0] = "TROLOLOL!";
		}
		
		mailPanelTitle = new Box(BoxLayout.Y_AXIS);
		//mailPanelLabel = new JLabel("SEND MAIL");
		//mailPanelLabel.setFont(new Font("Arial", 1, 42));
		//mailPanelLabel.setAlignmentX(CENTER_ALIGNMENT);
		mailPanelInstructions = new JLabel("Please enter the correct details below and push the OK button.");
		mailPanelInstructions.setFont(new Font("Arial", 0, 16));
		mailPanelInstructions.setAlignmentX(CENTER_ALIGNMENT);
		//mailPanelTitle.add(mailPanelLabel);
	
		mailPanelTitle.add(mailPanelInstructions);
		
		contentsBox = new Box(BoxLayout.Y_AXIS);
		buttonsBox = new Box(BoxLayout.X_AXIS);
		
		idLabel = new JLabel("ID:");
		idLabel.setFont(new Font("Arial", 0, 14));
		idField = new JFormattedTextField("RANDOM ID HERE");
		idField.setEditable(false);
		idField.setAlignmentX(LEFT_ALIGNMENT);
		contentsBox.add(idLabel);
		contentsBox.add(idField);
		contentsBox.add(Box.createRigidArea(new Dimension(0,5)));
		
		addressDetailsLabel = new JLabel("Address Details:");
		addressDetailsLabel.setFont(new Font("Arial", 0, 14));
		addressField = new JTextField();
		addressField.setAlignmentX(LEFT_ALIGNMENT);
		contentsBox.add(addressDetailsLabel);
		contentsBox.add(addressField);
		contentsBox.add(Box.createRigidArea(new Dimension(0,5)));
		
		weightLabel = new JLabel("Weight:");
		weightLabel.setFont(new Font("Arial", 0, 14));
		weightSlider = new JSlider(0, 50, 0);
		weightSlider.setMinimumSize(new Dimension(0,0));
		weightSlider.setMaximumSize(addressField.getMaximumSize());
		weightSlider.setMajorTickSpacing(10);
		weightSlider.setMinorTickSpacing(1);
		weightSlider.setPaintTicks(true);
		weightSlider.setPaintLabels(true);
		weightSlider.setAlignmentX(LEFT_ALIGNMENT);
		contentsBox.add(weightLabel);
		contentsBox.add(weightSlider);
		contentsBox.add(Box.createRigidArea(new Dimension(0,5)));
		
		volumeLabel = new JLabel("Volume:");
		volumeLabel.setFont(new Font("Arial", 0, 14));
		volumeSlider = new JSlider(0, 50, 0);
		volumeSlider.setMinimumSize(new Dimension(0,0));
		volumeSlider.setMaximumSize(addressField.getMaximumSize());
		volumeSlider.setMajorTickSpacing(10);
		volumeSlider.setMinorTickSpacing(1);
		volumeSlider.setPaintTicks(true);
		volumeSlider.setPaintLabels(true);
		volumeSlider.setAlignmentX(LEFT_ALIGNMENT);
		contentsBox.add(volumeLabel);
		contentsBox.add(volumeSlider);
		contentsBox.add(Box.createRigidArea(new Dimension(0,5)));
		
		originLabel = new JLabel("Origin:");
		originLabel.setFont(new Font("Arial", 0, 14));
		origins = new JComboBox(originList);
		origins.setAlignmentX(LEFT_ALIGNMENT);
		contentsBox.add(originLabel);
		contentsBox.add(origins);
		contentsBox.add(Box.createRigidArea(new Dimension(0,5)));
		
		destLabel = new JLabel("Destination:");
		destLabel.setFont(new Font("Arial", 0, 14));
		destinations = new JComboBox(destList);
		destinations.setAlignmentX(LEFT_ALIGNMENT);
		contentsBox.add(destLabel);
		contentsBox.add(destinations);
		contentsBox.add(Box.createRigidArea(new Dimension(0,5)));
		
		priorityLabel = new JLabel("Priority:");
		priorityLabel.setFont(new Font("Arial", 0, 14));
		priorities = new JComboBox(priorityList);
		priorities.setAlignmentX(LEFT_ALIGNMENT);
		contentsBox.add(priorityLabel);
		contentsBox.add(priorities);
		contentsBox.add(Box.createRigidArea(new Dimension(0,5)));
		
		okButton = new JButton("OK");
		okButton.addActionListener(a);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(a);
		//okButton.setAlignmentX(CENTER_ALIGNMENT);
		buttonsBox.add(okButton);
		buttonsBox.add(Box.createRigidArea(new Dimension(5,0)));
		buttonsBox.add(cancelButton);
		contentsBox.setAlignmentX(CENTER_ALIGNMENT);
		
		Box completeBox = new Box(BoxLayout.Y_AXIS);
		completeBox.add(contentsBox);
		completeBox.add(buttonsBox);
		completeBox.setPreferredSize(new Dimension(550,400));
		
		JPanel contentsPanel = new JPanel();
		contentsPanel.setPreferredSize(new Dimension(590,590));
		contentsPanel.add(mailPanelTitle);
		contentsPanel.add(completeBox, BorderLayout.CENTER);
		
		//this.add(mailPanelTitle, BorderLayout.CENTER); 
		this.add(contentsPanel, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(600, 600));
		this.setBackground(Color.GRAY);
	}

}