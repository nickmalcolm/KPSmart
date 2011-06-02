package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.PrioritisedRoute;
import events.Event;

import routes.DistributionCentre;
import routes.Firm;

public class KPSFrame extends JFrame {
	
	private KPSTitlePanel title; //*Nobody* expects the Spanish Inquisition!
	private JPanel panel;
	private KPSDefaultPanel defaultPanel;
	private KPSMailPanel mailPanel;
	private KPSEventsPanel eventsPanel;
	private KPSUpdatePanel updatePanel;
	private KPSMenuBar menuBar;
	private String currentPanel;
	String[] data = {"one", "two", "three"};
	
	/**
	 * Creates a new KSPFrame with CardLayout
	 * @param actionlistener Passed from parent program
	 * @param centres Set of DistributionCentre used to populate components
	 * @param firms Set of Firm used to populate components
	 */
	public KPSFrame(ActionListener actionlistener, Set<DistributionCentre> centres, List<Firm> firms) {
		title = new KPSTitlePanel();
		panel = new JPanel(new CardLayout());
		defaultPanel = new KPSDefaultPanel();
		defaultPanel.setName("defaultPanel");
		mailPanel = new KPSMailPanel(actionlistener, centres);
		mailPanel.setName("mailPanel");
		eventsPanel = new KPSEventsPanel(actionlistener);
		eventsPanel.setName("eventsPanel");
		updatePanel = new KPSUpdatePanel(actionlistener, centres, firms);
		updatePanel.setName("updatePanel");
		currentPanel = defaultPanel.getName();
		menuBar = new KPSMenuBar(actionlistener);
		
		panel.add(defaultPanel, "defaultPanel");
		panel.add(mailPanel, "mailPanel");
		panel.add(eventsPanel, "eventsPanel");
		panel.add(updatePanel, "updatePanel");
		
		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);
		this.setJMenuBar(menuBar);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setFocusable(true);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * Displays the requested panel via CardLayout
	 * @param panelName Name of the panel to display
	 */
	public void displayPanel(String panelName) {
		CardLayout cards = (CardLayout) panel.getLayout();
		cards.show(panel, panelName);
		currentPanel = panelName;
	}
	
	/**
	 * Specifically resets the KPSMailPanel
	 */
	public void resetMailPanel() {
		CardLayout cards = (CardLayout) panel.getLayout();
		cards.show(panel, "defaultPanel");
		mailPanel.reset();
	}
	
	/**
	 * Specifically resets the KPSUpdatePanel
	 */
	public void resetUpdatePanel() {
		CardLayout cards = (CardLayout) panel.getLayout();
		cards.show(panel, "defaultPanel");
		updatePanel.reset();
	}
	
	/**
	 * Resets all panels
	 */
	public void resetAll() {
		CardLayout cards = (CardLayout) panel.getLayout();
		cards.show(panel, "defaultPanel");
		for (Component c : panel.getComponents()) {
			((KPSPanel)c).reset();
		}
	}
	
	/**
	 * Checks if the current panel is the same what it is thought to be
	 * @param panelName The name of the panel wanted to check
	 * @return True if the panel is equal, false otherwise
	 */
	public boolean getPanel(String panelName) {
		return currentPanel.equals(panelName);
	}
	
	/**
	 * Returns an ArrayList of information from the KPSMailPanel
	 * (WARNING: multiple types inside, handle with care)
	 * @return An ArrayList of undefined info
	 */
	public ArrayList returnMailPanelInfo() {
		return mailPanel.returnInfo();
	}
	
	/**
	 * Returns the desire event to display up to from the KPSEventsPanel
	 * @return An integer index of the desired event
	 */
	public int returnEventTime() {
		return eventsPanel.returnEventTime();
	}
	
	/**
	 * Sets the current desired event to display in KPSEventsPanel
	 * @param eventTime The integer index of the desired event
	 */
	public void setEventTime(int eventTime) {
		eventsPanel.setEventTime(eventTime);
	}
	
	/**
	 * Populates the KPSEventsPanel with the passed information. 
	 * Must be called each time an update is required.
	 * @param currentNumberOfEvents The current number of events to display
	 * @param deliveryTimes Map of double values with PrioritisedRoutes as keys
	 * @param amountOfMail Map of integer values with PrioritisedRoutes as keys
	 * @param weightOfMail Map of double values with PrioritisedRoutes as keys
	 * @param volumeOfMail Map of double values with PrioritisedRoutes as keys
	 * @param criticalRoutes Map of double values with PrioritisedRoutes as keys
	 * @param events List of all events
	 * @param revenue double value of current total revenue
	 * @param expenditure double value of current total expenditure
	 * @param totalNumberOfEvents The total number of events
	 */
	public void populateEvents(int currentNumberOfEvents, Map<PrioritisedRoute, Double> deliveryTimes, 
			Map<PrioritisedRoute, Integer> amountOfMail, Map<PrioritisedRoute, Double> weightOfMail,
			Map<PrioritisedRoute, Double> volumeOfMail, Map<PrioritisedRoute, Double> criticalRoutes,
			List<Event> events, double revenue, double expenditure, int totalNumberOfEvents) {
		
		eventsPanel.populate(currentNumberOfEvents, deliveryTimes, amountOfMail, weightOfMail, volumeOfMail, criticalRoutes, events, revenue, expenditure, totalNumberOfEvents);
		
	}
	
	/**
	 * Returns an ArrayList of information from the KPSUpdatePanel
	 * (WARNING: multiple types inside, handle with care)
	 * @return An ArrayList of undefined info
	 */
	public ArrayList returnCustomerPriceUpdateInfo() {
		return updatePanel.returnCustomerPriceUpdateInfo();
	}
	
	/**
	 * Returns an ArrayList of information from the KPSUpdatePanel
	 * (WARNING: multiple types inside, handle with care)
	 * @return An ArrayList of undefined info
	 */
	public ArrayList returnTransportCostUpdateInfo() {
		return updatePanel.returnTransportCostUpdateInfo();
	}
	
	/**
	 * Returns an ArrayList of information from the KPSUpdatePanel
	 * (WARNING: multiple types inside, handle with care)
	 * @return An ArrayList of undefined info
	 */
	public ArrayList returnDiscontinueTransportInfo() {
		return updatePanel.returnDiscontinueTransportInfo();
	}

	/**
	 * Activates manager mode in KPSEventsPanel
	 */
	public void manager() {
		menuBar.manager();	
	}
	
	/**
	 * Deactivates manager mode in KPSEventsPanel
	 */
	public void notManager() {
		menuBar.notManager();
	}
	
	/**
	 * Disables the back button for event scrolling in KPSEventsPanel
	 */
	public void disableBackward() {
		eventsPanel.disableBackward();
	}
	
	/**
	 * Disables the forward button for event scrolling in KPSEventsPanel
	 */
	public void disableForward() {
		eventsPanel.disableForward();
	}
	
	/**
	 * Enables the back button for event scrolling in KPSEventsPanel
	 */
	public void enableBackward() {
		eventsPanel.enableBackward();
	}
	
	/**
	 * Enables the forward button for event scrolling in KPSEventsPanel
	 */
	public void enableForward() {
		eventsPanel.enableForward();
	}

}
