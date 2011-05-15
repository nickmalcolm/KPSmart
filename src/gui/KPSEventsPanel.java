package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class KPSEventsPanel extends JPanel implements KPSPanel{
	
	//Events panel
	private JPanel eventsPanel;
	private JTextField displayedEventsField;
	private JScrollPane displayedEvents;
	private JTextField totalEventsField;
	private JButton forward;
	private JButton backward;
	
	//Business figures panel
	private JPanel businessPanel;
	private JLabel revenueLabel;
	private JTextField revenueField;
	private JLabel expenditureLabel;
	private JTextField expenditureField;
	private JLabel averageDeliveryLabel;
	private JTextField averageDeliveryField;
	private JLabel amountOfMailLabel;
	private JTextField amountOfMailField;
	
	//Critical routes panel
	private JPanel criticalPanel;
	private JLabel criticalRoutesLabel;
	private JTextField criticalRoutesField;
	private JScrollPane criticalRoutes;
	
	
	public KPSEventsPanel() {
		super();
		this.setPreferredSize(new Dimension(600,600));
		this.setBackground(Color.GRAY);
		
		//Events
		eventsPanel = new JPanel();
		eventsPanel.setPreferredSize(new Dimension(350, 590));
		
		displayedEventsField = new JTextField();
		displayedEventsField.setEditable(false);
		displayedEvents = new JScrollPane(displayedEventsField);
		displayedEvents.setPreferredSize(new Dimension(340, 525));
		
		totalEventsField = new JTextField();
		totalEventsField.setEditable(false);
		totalEventsField.setPreferredSize(new Dimension(340, 25));
		
		forward = new JButton(">");
		backward = new JButton("<");
		Box buttons = new Box(BoxLayout.X_AXIS);
		buttons.add(backward);
		buttons.add(Box.createRigidArea(new Dimension(100, 0)));
		buttons.add(forward);
		
		Box allEvents = new Box(BoxLayout.Y_AXIS);
		allEvents.add(displayedEvents);
		allEvents.add(totalEventsField);
		allEvents.add(Box.createRigidArea(new Dimension(0, 5)));
		allEvents.add(buttons);
		
		eventsPanel.add(allEvents);
		
		//Business figures
		businessPanel = new JPanel();
		businessPanel.setPreferredSize(new Dimension(235, 170));
		Box businessBox = new Box(BoxLayout.Y_AXIS);
		businessBox.setPreferredSize(new Dimension(225, 160));
		
		revenueLabel = new JLabel("Revenue:");
		revenueLabel.setFont(new Font("Arial", 0, 12));
		revenueField = new JTextField();
		revenueField.setEditable(false);
		revenueField.setAlignmentX(LEFT_ALIGNMENT);
		businessBox.add(revenueLabel);
		businessBox.add(revenueField);
		businessBox.add(Box.createRigidArea(new Dimension(0, 5)));
		
		expenditureLabel = new JLabel("Expenditure:");
		expenditureLabel.setFont(new Font("Arial", 0, 12));
		expenditureField = new JTextField();
		expenditureField.setEditable(false);
		expenditureField.setAlignmentX(LEFT_ALIGNMENT);
		businessBox.add(expenditureLabel);
		businessBox.add(expenditureField);
		businessBox.add(Box.createRigidArea(new Dimension(0, 5)));
		
		averageDeliveryLabel = new JLabel("Average Delivery Time:");
		averageDeliveryLabel.setFont(new Font("Arial", 0, 12));
		averageDeliveryField = new JTextField();
		averageDeliveryField.setEditable(false);
		averageDeliveryField.setAlignmentX(LEFT_ALIGNMENT);
		businessBox.add(averageDeliveryLabel);
		businessBox.add(averageDeliveryField);
		businessBox.add(Box.createRigidArea(new Dimension(0, 5)));
		
		amountOfMailLabel = new JLabel("Amount of Mail:");
		amountOfMailLabel.setFont(new Font("Arial", 0, 12));
		amountOfMailField = new JTextField();
		amountOfMailField.setEditable(false);
		amountOfMailField.setAlignmentX(LEFT_ALIGNMENT);
		businessBox.add(amountOfMailLabel);
		businessBox.add(amountOfMailField);
		businessBox.add(Box.createRigidArea(new Dimension(0, 5)));
		
		businessPanel.add(businessBox);
		
		//Critical routes
		criticalPanel = new JPanel();
		criticalPanel.setPreferredSize(new Dimension(235, 420));
		Box criticalBox = new Box(BoxLayout.Y_AXIS);
		criticalBox.setPreferredSize(new Dimension(225, 410));
		
		criticalRoutesLabel = new JLabel("Critical Routes:");
		criticalRoutesLabel.setFont(new Font("Arial", 0, 12));
		criticalRoutesLabel.setAlignmentX(LEFT_ALIGNMENT);
		criticalRoutesField = new JTextField();
		criticalRoutesField.setEditable(false);
		criticalRoutes = new JScrollPane();
		//criticalRoutes.setPreferredSize(new Dimension(225, 380));
		criticalRoutes.setAlignmentX(LEFT_ALIGNMENT);
		criticalRoutes.add(criticalRoutesField);
		
		criticalBox.add(criticalRoutesLabel);
		criticalBox.add(criticalRoutes);
		
		criticalPanel.add(criticalBox);
		
		Box leftSide = new Box(BoxLayout.Y_AXIS);
		leftSide.add(eventsPanel);
		Box rightSide = new Box(BoxLayout.Y_AXIS);
		rightSide.add(businessPanel);
		rightSide.add(criticalPanel);
		Box completeBox = new Box(BoxLayout.X_AXIS);
		completeBox.add(leftSide);
		completeBox.add(Box.createRigidArea(new Dimension(5, 0)));
		completeBox.add(rightSide);
		
		
		this.add(completeBox);
		
		
		
		//Business Figures
		
		//Critical routes
	}

	public void reset() {}

}
