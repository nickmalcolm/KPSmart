package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import priority.Priority;
import routes.*;

public class KPSMailPanel extends JPanel implements KPSPanel, ChangeListener{
	
	private Box contentsBox;
	private Box buttonsBox;
	
	private JLabel idLabel;
	private JTextField idField;
	
	private JLabel addressDetailsLabel;
	private JTextField addressField;
	
	private JLabel weightLabel;
	private JSlider weightSlider;
	private JTextField weightField;
	
	private JLabel volumeLabel;
	private JSlider volumeSlider;
	private JTextField volumeField;
	
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
	
	/**
	 * Constructs a KPSMailPanel for use with KPSFrame
	 * @param actionlistener ActionListener passed by parent
	 * @param distributionCentreSet Set of DistributionCentre elements for population JComboBoxes
	 */
	public KPSMailPanel(ActionListener actionlistener, Set<DistributionCentre> distributionCentreSet) {
		super();
		
		//ERROR CHECKING
		if (distributionCentreSet != null) {
			originList = new String[distributionCentreSet.size()];
			destList = new String[distributionCentreSet.size()];
			int i = 0;
			for (DistributionCentre d : distributionCentreSet) {
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
		
		//INITIALIZE AND SET COMPONENTS
		contentsBox = new Box(BoxLayout.Y_AXIS);
		buttonsBox = new Box(BoxLayout.X_AXIS);
		
		//UNIQUE ID COMPONENTS
		idLabel = new JLabel("ID:");
		idLabel.setFont(new Font("Arial", 0, 14));
		idField = new JTextField((String.valueOf((int)(Math.random()*100000000))));
		idField.setEditable(false);
		idField.setAlignmentX(LEFT_ALIGNMENT);
		
		contentsBox.add(idLabel);
		contentsBox.add(idField);
		contentsBox.add(Box.createRigidArea(new Dimension(0,5)));
		
		//ADDRESS BOX COMPONENTS (LEGACY BUT STILL LOOKS NICE)
		addressDetailsLabel = new JLabel("Address Details:");
		addressDetailsLabel.setFont(new Font("Arial", 0, 14));
		addressField = new JTextField();
		addressField.setAlignmentX(LEFT_ALIGNMENT);
		
		contentsBox.add(addressDetailsLabel);
		contentsBox.add(addressField);
		contentsBox.add(Box.createRigidArea(new Dimension(0,5)));
		
		//WEIGHT COMPONENTS
		weightLabel = new JLabel("Weight (G):");
		weightLabel.setFont(new Font("Arial", 0, 14));
		weightLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		weightSlider = new JSlider(0, 350, 0);
		weightSlider.setPreferredSize(new Dimension(530, 50));
		weightSlider.setMaximumSize(new Dimension(530, 50));
		weightSlider.setMajorTickSpacing(50);
		weightSlider.setMinorTickSpacing(10);
		weightSlider.setPaintTicks(true);
		weightSlider.setPaintLabels(true);
		weightSlider.setAlignmentX(LEFT_ALIGNMENT);
		weightSlider.addChangeListener(this);
		
		weightField = new JTextField();
		weightField.setEditable(false);
		weightField.setAlignmentX(LEFT_ALIGNMENT);
		weightField.setMaximumSize(new Dimension(40, 25));
		weightField.setPreferredSize(new Dimension(40, 25));
		weightField.setText(String.valueOf(weightSlider.getValue()));
		weightField.setMargin(new Insets(0, 5, 0, 5));
		
		//Quick fix based on presentation comments
		contentsBox.add(weightLabel);
		Box boxfix = new Box(BoxLayout.X_AXIS);
		boxfix.setAlignmentX(LEFT_ALIGNMENT);
		boxfix.add(weightSlider);
		boxfix.add(Box.createRigidArea(new Dimension(10,0)));
		boxfix.add(weightField);
		contentsBox.add(boxfix);
		contentsBox.add(Box.createRigidArea(new Dimension(0,5)));
		
		//VOLUME COMPONENTS
		volumeLabel = new JLabel("Volume (CC):");
		volumeLabel.setFont(new Font("Arial", 0, 14));
		volumeLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		volumeSlider = new JSlider(0, 50, 0);
		volumeSlider.setPreferredSize(new Dimension(530, 50));
		volumeSlider.setMaximumSize(new Dimension(530, 50));
		volumeSlider.setMajorTickSpacing(10);
		volumeSlider.setMinorTickSpacing(1);
		volumeSlider.setPaintTicks(true);
		volumeSlider.setPaintLabels(true);
		volumeSlider.setAlignmentX(LEFT_ALIGNMENT);
		volumeSlider.addChangeListener(this);
		
		volumeField = new JTextField();
		volumeField.setEditable(false);
		volumeField.setAlignmentX(LEFT_ALIGNMENT);
		volumeField.setMaximumSize(new Dimension(40, 25));
		volumeField.setPreferredSize(new Dimension(40, 25));
		volumeField.setText(String.valueOf(volumeSlider.getValue()));
		volumeField.setMargin(new Insets(0, 10, 0, 0));
		
		//Quick fix based on presentation comments #2
		contentsBox.add(volumeLabel);
		Box boxfix2 = new Box(BoxLayout.X_AXIS);
		boxfix2.setAlignmentX(LEFT_ALIGNMENT);
		boxfix2.add(volumeSlider);
		boxfix2.add(Box.createRigidArea(new Dimension(10,0)));
		boxfix2.add(volumeField);
		contentsBox.add(boxfix2);
		contentsBox.add(Box.createRigidArea(new Dimension(0,5)));
		
		//ORIGIN COMPONENTS
		originLabel = new JLabel("Origin:");
		originLabel.setFont(new Font("Arial", 0, 14));
		origins = new JComboBox(originList);
		origins.setAlignmentX(LEFT_ALIGNMENT);
		
		contentsBox.add(originLabel);
		contentsBox.add(origins);
		contentsBox.add(Box.createRigidArea(new Dimension(0,5)));
		
		//DESTINATION COMPONENTS
		destLabel = new JLabel("Destination:");
		destLabel.setFont(new Font("Arial", 0, 14));
		destinations = new JComboBox(destList);
		destinations.setAlignmentX(LEFT_ALIGNMENT);
		
		contentsBox.add(destLabel);
		contentsBox.add(destinations);
		contentsBox.add(Box.createRigidArea(new Dimension(0,5)));
		
		//PRIORITY COMPONENTS
		priorityLabel = new JLabel("Priority:");
		priorityLabel.setFont(new Font("Arial", 0, 14));
		priorities = new JComboBox(priorityList);
		priorities.setAlignmentX(LEFT_ALIGNMENT);
		
		contentsBox.add(priorityLabel);
		contentsBox.add(priorities);
		contentsBox.add(Box.createRigidArea(new Dimension(0,5)));
		
		//BUTTON COMPONENTS
		okButton = new JButton("Send");
		okButton.addActionListener(actionlistener);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(actionlistener);

		buttonsBox.add(okButton);
		buttonsBox.add(Box.createRigidArea(new Dimension(5,0)));
		buttonsBox.add(cancelButton);
		contentsBox.setAlignmentX(CENTER_ALIGNMENT);
		
		//PUT IT ALL TOGETHER
		Box completeBox = new Box(BoxLayout.Y_AXIS);
		completeBox.add(contentsBox);
		completeBox.add(buttonsBox);
		completeBox.setPreferredSize(new Dimension(570,400));
		
		JPanel contentsPanel = new JPanel();
		contentsPanel.setPreferredSize(new Dimension(590,590));
		contentsPanel.add(completeBox, BorderLayout.CENTER);
		
		this.add(contentsPanel, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(600, 600));
		this.setBackground(Color.GRAY);
	}
	
	/**
	 * Returns an ArrayList of mail information
	 * (WARNING: The contents are undefined and variable. Handle with care!)
	 * @return An ArrayList of mail information of differing types
	 */
	public ArrayList returnInfo() {
		ArrayList info = new ArrayList();
		info.add(this.idField.getText());
		info.add(this.addressField.getText());
		info.add(this.weightSlider.getValue());
		info.add(this.volumeSlider.getValue());
		info.add(this.origins.getSelectedItem());
		info.add(this.destinations.getSelectedItem());
		info.add(this.priorities.getSelectedItem());
		return info;
	}
	
	/**
	 * Resets the panel
	 */
	public void reset() {
		idField.setText((String.valueOf((int)(Math.random()*100000000))));
		addressField.setText("");
		weightSlider.setValue(0);
		volumeSlider.setValue(0);
		origins.setSelectedIndex(0);
		destinations.setSelectedIndex(0);
		priorities.setSelectedIndex(0);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		weightField.setText(String.valueOf(weightSlider.getValue()));
		volumeField.setText(String.valueOf(volumeSlider.getValue()));
	}

}
