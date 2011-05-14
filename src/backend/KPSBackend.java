package backend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

import com.thoughtworks.xstream.XStream;

import events.*;
import priority.*;
import routes.*;

public class KPSBackend {
	private Set<DistributionCentre> distributionCentres;
	private ArrayList<Route> routes;
	private ArrayList<Mail> activeMail;
	private ArrayList<Event> events;
	private ArrayList<Event> displayedEvents;
	private XStream xstream;
	private String xmlFile;
	
	//private String password;
	private int passwordHash;
	private boolean isManager;
	
	public KPSBackend() {
		
		routes = new ArrayList<Route>();
		activeMail = new ArrayList<Mail>();
		events = new ArrayList<Event>();
		//password = "sototessecure";
		passwordHash = 653306037;
		isManager = false;
		
	}
	
	public String testMethod() {
		return "This is a test";
	}
	
	//Parses the XML record(s) and retains their contents in memory
	@SuppressWarnings("unchecked")
	public void parseXMLRecord() {
		xstream = new XStream();
		try{
			//Reads in all the XML files from disk
			String routeXMLInput;
			String mailXMLinput;
			String eventsXMLInput;
			
			File fileToRead = new File("routesXML.xml");
			routeXMLInput = readFileToString(fileToRead);
			
			fileToRead = new File("mailXML.xml");
			mailXMLinput = readFileToString(fileToRead);
			
			fileToRead = new File("eventsXML.xml");
			eventsXMLInput = readFileToString(fileToRead);
			
		//Finally parses the files back into objects.
		routes = (ArrayList<Route>)xstream.fromXML(routeXMLInput);
		activeMail =(ArrayList<Mail>)xstream.fromXML(mailXMLinput);
		events = (ArrayList<Event>)xstream.fromXML(eventsXMLInput);
		
		}catch(Exception e){
			System.out.println("Exception!: " +e+"\n ");
			e.printStackTrace(); //Keep this here for debugging
		}
	}
	
	//Method to read the disk file and put into a string.
	public String readFileToString(File f){
		if (f!=null){
			try{
				String output = "";
				Scanner scanner = new Scanner(new FileReader(f));
				while(scanner.hasNextLine())
					output += scanner.nextLine();
				scanner.close();
				return output;
			}catch (Exception e) {
				System.out.println("Error reading external file!");
				return null;
			}
		}
		else return null;
	}
	
	//Creates the XML record. Returns true if record is created successfully.
	public boolean createXMLRecord(){
		xstream = new XStream();
		try{
			String routesXML = xstream.toXML(routes);
			String mailXML = xstream.toXML(activeMail);
			String eventsXML = xstream.toXML(events);
			
			//Then save (and hash?) XML file
			//Save routes file
			FileWriter fileWriter = new FileWriter("routesXML.xml");
			BufferedWriter bufWriter = new BufferedWriter(fileWriter);
			bufWriter.write(routesXML);
			bufWriter.close();
			fileWriter.close();
			
			//Save mail file
			fileWriter = new FileWriter("mailXML.xml");
			bufWriter = new BufferedWriter(fileWriter);
			bufWriter.write(mailXML);
			bufWriter.close();
			fileWriter.close();
			
			//Save events file
			fileWriter = new FileWriter("eventsXML.xml");
			bufWriter = new BufferedWriter(fileWriter);
			bufWriter.write(eventsXML);
			bufWriter.close();
			fileWriter.close();
			
			return true;
		}catch (Exception e) {
			System.out.println("Exception!: " +e+"\n ");
			e.printStackTrace(); //Keep this here for debugging
			return false;
		}
		
	}
	
	//Authenticates a manager to allow for extra options
	public boolean authenticateManager(String s) {
		//System.out.println(passwordHasher(s));
		//if (s.equals(password)) {
		if (passwordHash == passwordHasher(s)) {
			isManager = true;
		}
		
		return isManager;
		
	}
	
	//Releases the manager mode and removes extra options
	public void deauthenticateManager() {
		isManager = false;
	}
	
	public Map<PrioritisedRoute, Double> getCriticalRoute(){
		Map<PrioritisedRoute, Double> result = new HashMap<PrioritisedRoute, Double>();
		// loop through every route
		for (Route route : routes){
			// loop through every priority in route
			// for (Priority priority : [list of priorities])
				// for (Vehicle  vehicle : route.getVehiclesByPriority) {
				
					//calculate avg delivery cost from origin->destination w/priority
				
					// calculate avg customer revenue from origin->destination w/priority
				
					// if avg cost > avg revenue, add new PrioritisedRoute(origin, destination, priority) into Map result
					// and map Revenue - expenditure to PrioritisedRoute
		}
		return result;
	}
	
	/** METHODS FOR CALCULATIONS */
	public Double calculateRevenue(){
		Double sum = 0.0;
		
		// loop through events
		for (Event event : displayedEvents){
			// if mail event, add costPerG and costPerCC to total revenue
			if (event instanceof MailEvent){
				Mail mail = ((MailEvent)event).getMail();
				sum += (event.getVehicle().getCustomerCostPerCC() * mail.getVolume()) + (event.getVehicle().getCustomerCostPerG() * mail.getWeight());
			}
		}
		return sum;
	}
	
	public Double calculateDeliveryTimes(Priority priority, DistributionCentre origin, DistributionCentre destination){
		// get all mails corresponding to priority/origin/destination
		Route route = findRoute(origin, destination);
		List<Vehicle> vehicles = route.getVehiclesByPriority(priority);
		double sum = 0.0;
		int numEvents = 0;
		// yuck code! want to buy LINQ query/database...
		// loop through events and find all mail corresponding to correct vehicle
		for (Event event : displayedEvents){
			// check if event is a MailEvent
			if (event instanceof MailEvent){
				for (Vehicle vehicle : vehicles){
					// check if event is on the correct route/priority
					if (((MailEvent)event).getVehicle().equals(vehicle)){
						sum += vehicle.getDuration();
						numEvents++;
					}
				}
			}
		}
		// return avg delivery time
		return sum / numEvents;
	}	
	
	public Map<DistributionCentre, Integer> calculateAmountOfMail(DistributionCentre origin){
		Map<DistributionCentre, Integer> result = new HashMap<DistributionCentre, Integer>();

		// calculate total no. of mails
		for (Event event : displayedEvents){
			// check if event is a MailEvent
			if (event instanceof MailEvent){
				MailEvent mailEvent = (MailEvent) event;
				Mail mail = mailEvent.getMail();
				if (mail.getOrigin().equals(origin)){
					int count = result.get(mail.getOrigin()) != null ? (result.get(mail.getOrigin()) + 1) : 1;
					result.put(mail.getOrigin(), count);
				}
			}
		}
		return result;
	}
	
	public Map<DistributionCentre, Double> calculateTotalVolumeOfMail(DistributionCentre origin){
		Map<DistributionCentre, Double> result = new HashMap<DistributionCentre, Double>();

		// calculate total no. of mails
		for (Event event : displayedEvents){
			// check if event is a MailEvent
			if (event instanceof MailEvent){
				MailEvent mailEvent = (MailEvent) event;
				Mail mail = mailEvent.getMail();
				if (mail.getOrigin().equals(origin)){
					double vol = result.get(mail.getOrigin()) != null ? (result.get(mail.getOrigin()) + mail.getVolume()) : mail.getVolume();
					result.put(mail.getOrigin(), vol);
				}
			}
		}
		return result;
	}
	
	public Map<DistributionCentre, Double> calculateTotalWeightOfMail(DistributionCentre origin){
		Map<DistributionCentre, Double> result = new HashMap<DistributionCentre, Double>();

		// calculate total no. of mails
		for (Event event : displayedEvents){
			// check if event is a MailEvent
			if (event instanceof MailEvent){
				MailEvent mailEvent = (MailEvent) event;
				Mail mail = mailEvent.getMail();
				if (mail.getOrigin().equals(origin)){
					double weight = result.get(mail.getOrigin()) != null ? (result.get(mail.getOrigin()) + mail.getWeight()) : mail.getWeight();
					result.put(mail.getOrigin(), weight);
				}
			}
		}
		return result;
	}
	
	public Double calculateExpenditure(){
		Double sum = 0.0;
		
		// loop through events
		for (Event event : displayedEvents){
			// if mail event, add costPerG and costPerCC to total revenue
			if (event instanceof MailEvent){
				Mail mail = ((MailEvent)event).getMail();
				sum += (event.getVehicle().getTransportCostPerCC() * mail.getVolume()) + (event.getVehicle().getTransportCostPerG() * mail.getWeight());
			}
		}
		return sum;
	}
	
	public int getNumberOfEvents(){
		return displayedEvents.size();
	}
	
	/** METHODS FOR EVENTS */
	public void sendMail(int ID, double weight, double volume, DistributionCentre origin, DistributionCentre destination, Priority priority) {
		Mail mail = new Mail(ID, weight, volume, origin, destination, priority);
		activeMail.add(mail);
		getMail(ID);
		// add new MailEvents
		events.addAll(mail.getEvents());
	}
	
	//Updates the customer price for a route
	public Event updatePrice(DistributionCentre origin, DistributionCentre destination, double pricePerG, double pricePerCC, Priority priority, Firm firm) {
		Route route = findRoute(origin, destination);
		if (route == null)
			return null;
		
		Vehicle vehicle = route.getVehicle(priority, firm);
		if (vehicle == null)
			return null;
		
		vehicle.updateCustomerCost(pricePerG, pricePerCC);
		
		// add to event log
		Event event = new PriceUpdateEvent(pricePerCC, pricePerG); // TODO: add details to event
		events.add(event); 
		return event;
	}
	
	public Event updateTransport(DistributionCentre origin, DistributionCentre destination, double pricePerG, double pricePerCC, int frequency, int durationInMinutes,
			Day day, Priority priority, Firm firm) {
		Route route = findRoute(origin, destination);
		// if no route found, create one
		if (route == null){
			route = new Route(origin, destination);
			routes.add(route);
		}
		
		Vehicle vehicle = route.getVehicle(priority, firm);
		// if no vehicle found, create one
		if (vehicle == null){
			vehicle = new Vehicle(route.getVehicles().size(), pricePerG, pricePerCC, frequency, durationInMinutes, priority, firm);
			route.addVehicle(vehicle);
		}
		// else update the transport cost
		else {
			vehicle.updateTransportCost(pricePerG, pricePerCC);
		}
		// add to event log TODO change events
		Event event = new TransportUpdateEvent(pricePerCC, pricePerG, frequency, durationInMinutes, day, origin, destination);
		events.add(event); 
		return event;
	}
	
	public Event discontinueTransport(DistributionCentre origin, DistributionCentre destination, Priority priority, Firm firm, Day day) {
		Route route = findRoute(origin, destination);
		if (route == null)
			return null;
		
		Vehicle vehicle = route.getVehicle(priority, firm);
		if (vehicle == null)
			return null;
		
		route.discontinueTransport(vehicle.getID());
		
		// add to event log
		Event event = new DiscontinueTransportEvent(firm, priority, destination, destination); // TODO: add details to event
		events.add(event); 
		return event;
	}
	
	public void getMail(int ID) {
		for (Mail m : activeMail) {
			if (m.getID() == ID) {
				String answer = "ID: " + m.getID()
					+ "\nOrigin: " + m.getOrigin()
					+ "\nDestination: " + m.getDestination()
					+ "\nWeight: " + m.getWeight()
					+ "\nVolume: " + m.getVolume()
					+ "\nPriority: " + m.getPriority();
				System.out.println(answer);
				return;
			}
		}
		System.out.println("Mail does not exist");
	}
	
	
	public List<Event> getEvents(String filter){
		// get list of events
		
		// add filter to list of events (events.filter(String filter)?)
		// change displayedEvents to filtered list of events
		
		// return list of events
		
		return events;
	}
	
	public List<Event> stepEventsForward(){
		// step one event forward
		// add latest event to list of displayedEvents
		
		// return list of displayedEvents
		
		return events;
	}
	
	public List<Event> stepEventsBackward(){
		// step one event backward
		// add latest event to list of displayedEvents
		
		// return list of displayedEvents
		return events;
	}
	
	public List<Event> getAllEvents(){
		displayedEvents = events;
		return events;
	}
	
	public int passwordHasher(String s) {
		int hashed = s.hashCode();
		hashed = (int) Math.floor((hashed*3621873+1321798)/Math.PI);
		return hashed;
	}
	
	
	// Helper methods
	/**
	 * Finds the route corresponding to the origin and destination.
	 */
	private Route findRoute(DistributionCentre origin, DistributionCentre destination){
		for (Route route : this.routes){
			if (route.getD1().equals(origin) && route.getD2().equals(destination))
				return route;
		}
		// route not found, return null
		return null;
	}
}
