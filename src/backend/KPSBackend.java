package backend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

import priority.Priority;
import routes.DistributionCentre;
import routes.Firm;
import routes.Route;
import routes.Vehicle;

import com.thoughtworks.xstream.XStream;

import events.DiscontinueTransportEvent;
import events.Event;
import events.EventList;
import events.MailEvent;
import events.PriceUpdateEvent;
import events.TransportUpdateEvent;

public class KPSBackend {
	private Set<DistributionCentre> distributionCentres;
	private ArrayList<Route> routes;
	private ArrayList<Mail> allMail;
	private EventList<Event> events;
	private XStream xstream;

	private DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
	private Date currentDate = new Date();

	//private String password;
	private int passwordHash;
	private boolean isManager;

	public KPSBackend() {

		routes = new ArrayList<Route>();
		allMail = new ArrayList<Mail>();
		events = new EventList<Event>();
		//distributionCentres = new TreeSet();
		//password = "sototessecure";
		passwordHash = 653306037;
		isManager = false;

	}

	public String testMethod() {
		return "This is a test";
	}

	//Parses the XML record(s) and retains their contents in memory
	@SuppressWarnings("unchecked")
	/**
	 * Loads the XML files from the external path and loads the data into the program.
	 * The XStream library is used to read in the XML and create the objects using the data to 
	 * use later.
	 * @return true The XML has been parsed in correctly without errors
	 * @return false The XML parse has encountered an error
	 */
	public boolean parseXMLRecord() {
		xstream = new XStream();
		xstream.setMode(XStream.NO_REFERENCES);
		String file = "";
		try{
			//Reads in all the XML files from disk
			String routeXMLInput;
			String mailXMLinput;
			String eventsXMLInput;
			String distCentreXMLInput;
			
			//Reading routes file
			file = "routesXML.xml";
			File fileToRead = new File(file);
			routeXMLInput = readFileToString(fileToRead);

			//Reading mail file
			file = "mailXML.xml";
			fileToRead = new File(file);
			mailXMLinput = readFileToString(fileToRead);

			//Reading events file
			file = "eventsXML.xml";
			fileToRead = new File(file);
			eventsXMLInput = readFileToString(fileToRead);

			//Reading distribution centre file
			file = "distCentreXML.xml";
			fileToRead = new File(file);
			distCentreXMLInput = readFileToString(fileToRead);

			//Tells XStream some pretty obvious things. (It gets confused sometimes)
			xstream.alias("DistributionCentre", DistributionCentre.class);
			xstream.alias("transportDay", Day.class);
			
			//Finally parses the files back into objects.
			routes = (ArrayList<Route>)xstream.fromXML(routeXMLInput);
			allMail =(ArrayList<Mail>)xstream.fromXML(mailXMLinput);
			ArrayList<Event> arrayEvents = (ArrayList<Event>)xstream.fromXML(eventsXMLInput);
			for (Event event : arrayEvents){
				events.add(event);
			}
			distributionCentres = (Set<DistributionCentre>)xstream.fromXML(distCentreXMLInput);
			
			return true;
		}catch(Exception e){
			System.out.println("-----There has been an error creating the XML files!-----");
			System.out.println("Error loading file: "+file);			
			System.out.println("Exception!: " +e+"\n ");
			//e.printStackTrace(); //Keep this here for debugging
			return false;
		}
	}

	/**
	 * This reads the file from the disk, stepping through it and converting it into a string.
	 * @param f The file that is to b e read
	 * @return The string that has been read from the file. Null if an error has been encountered.
	 */
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
				System.out.println("Error reading external file "+f.getName()+"!");
				return null;
			}
		}
		else return null;
	}

	//Creates the XML record. Returns true if record is created successfully.
	/**
	 * Saves the current state of the program into the external XML files.
	 * Once saved, these XML files will be re-loaded the next time the program is run.
	 * @return boolean Returns true if saved correctly, false if there has been an error.
	 */
	public boolean createXMLRecord(){
		xstream = new XStream();
		xstream.setMode(XStream.NO_REFERENCES);
		FileWriter fileWriter;
		BufferedWriter bufWriter;
		String file = "";
		try{
			String routesXML = xstream.toXML(routes);
			String mailXML = xstream.toXML(allMail);
			String eventsXML = xstream.toXML(events.getList());
			String distCentXMl = xstream.toXML(distributionCentres);

			//Then save XML file
			//Save routes file
			file = "routesXML.xml";
			fileWriter = new FileWriter(file);
			bufWriter = new BufferedWriter(fileWriter);
			bufWriter.write(routesXML);
			bufWriter.close();
			fileWriter.close();

			//Save mail file
			file = "mailXML.xml";
			fileWriter = new FileWriter(file);
			bufWriter = new BufferedWriter(fileWriter);
			bufWriter.write(mailXML);
			bufWriter.close();
			fileWriter.close();

			//Save events file
			file = "eventsXML.xml";
			fileWriter = new FileWriter(file);
			bufWriter = new BufferedWriter(fileWriter);
			bufWriter.write(eventsXML);
			bufWriter.close();
			fileWriter.close();
			
			//Save distribution Centres
			file = "distCentreXML.xml";
			fileWriter = new FileWriter(file);
			bufWriter = new BufferedWriter(fileWriter);
			bufWriter.write(distCentXMl);
			bufWriter.close();
			fileWriter.close();

			return true;
		}catch (Exception e) {
			System.out.println("-----There has been an error creating the XML files!-----");
			System.out.println("Error saving file: "+file);
			//System.out.println("Exception!: " +e+"\n ");
			//e.printStackTrace(); //Keep this here for debugging
			return false;
		}

	}

	public Set<DistributionCentre> getDistributionCentres() {
		return distributionCentres;
	}

	public ArrayList<Route> getRoutes() {
		return routes;
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

	/**
	 * Calculates critical routes where the average cost > average revenue.
	 * @param eventTime	The event timeframe for calculations.
	 * @return	Map of PrioritisedRoute with difference between cost and revenue.
	 */
	public Map<PrioritisedRoute, Double> getCriticalRoute(int eventTime){
		Map<PrioritisedRoute, Double> result = new HashMap<PrioritisedRoute, Double>();

		// select the events within an appropriate timeframe
		List<Event> displayedEvents = getEvents(eventTime);

		// loop through events
		for (Event event : displayedEvents){
			if (!(event instanceof MailEvent)){
				applyEvent(event);
			}
		}

		// loop through every route
		for (Route route : routes){
			// loop through every priority in route
			for (Priority priority : Priority.values()){
				// for (Priority priority : [list of priorities])
				for (Vehicle  vehicle : route.getVehiclesByPriority(priority)) {

					//calculate avg delivery cost from origin->destination w/priority
					double avgDelivCost =  (vehicle.getTransportCostPerCC()+vehicle.getTransportCostPerG());

					// calculate avg customer revenue from origin->destination w/priority
					double avgCustCost =  (vehicle.getCustomerCostPerCC()+vehicle.getCustomerCostPerG());

					// if avg cost > avg revenue, add new PrioritisedRoute(origin, destination, priority) into Map result
					if(avgDelivCost > avgCustCost){
						PrioritisedRoute newCritRoute = new PrioritisedRoute();
						newCritRoute.setPriority(priority);
						newCritRoute.setRoute(route);
						result.put(newCritRoute, (avgDelivCost-avgCustCost));
					}
				}
				// and map Revenue - expenditure to PrioritisedRoute
			}
		}
		return result;
	}

	/* METHODS FOR CALCULATIONS */
	/**
	 * Calculates company revenue according to a given timeframe.
	 * @param eventTime	The event timeframe for calculations.
	 * @return	The revenue across all events within that event timeframe.
	 */
	public Double calculateRevenue(int eventTime){
		Double sum = 0.0;

		// select the events within an appropriate timeframe
		List<Event> displayedEvents = getEvents(eventTime);

		// loop through events
		for (Event event : displayedEvents){
			// if mail event, add costPerG and costPerCC to total revenue
			if (event instanceof MailEvent){
				Mail mail = ((MailEvent)event).getMail();
				sum += (event.getVehicle().getCustomerCostPerCC() * mail.getVolume()) + (event.getVehicle().getCustomerCostPerG() * mail.getWeight());
			}
			else {
				applyEvent(event);
			}
		}
		return sum;
	}

	/**
	 * Calculates the delivery time for all mails with a given priority for a given route.
	 * @param priority	The priority of the route.
	 * @param origin	The origin of the route.
	 * @param destination	The destination of the route.
	 * @param eventTime	The event timeframe for calculations.
	 * @return	Map of PrioritisedRoute with the average delivery time of that route.
	 */
	public Map<PrioritisedRoute, Double> calculateDeliveryTimes(int eventTime){
		Map<PrioritisedRoute, Double> result = new HashMap<PrioritisedRoute, Double>();

		List<Event> displayedEvents = getEvents(eventTime);

		// yuck code! want to buy LINQ query/database...
		// loop through every route
		for (Route route : routes){
			// loop through every priority in route
			for (Priority priority : Priority.values()){
				PrioritisedRoute pRoute = new PrioritisedRoute();
				pRoute.setRoute(route);
				pRoute.setPriority(priority);
				double sum = 0.0;
				int numEvents = 0;
				// loop through each vehicle in priority
				for (Vehicle  vehicle : route.getVehiclesByPriority(priority)) {
					// loop through events and find all mail corresponding to correct vehicle
					for (Event event : displayedEvents){
						// check if event is a MailEvent
						if (event instanceof MailEvent){
							// check if event is on the correct route/priority
							if (((MailEvent)event).getVehicle().equals(vehicle)){
								sum += vehicle.getDuration();
								numEvents++;
							}
						}
						else {
							applyEvent(event);
						}
					}
				}
				result.put(pRoute, (numEvents == 0 ? 0 : (sum / numEvents)));
			}
		}
		// return avg delivery time
		return result;
	}	

	/**
	 * Calculates the total amount of mail from a given origin to each of its destinations.
	 * @param origin	The origin of the route.
	 * @param eventTime	The event timeframe for calculations.
	 * @return	Map of PrioritisedRoute with the total amount of mail for that route.
	 */
	public Map<PrioritisedRoute, Integer> calculateAmountOfMail(int eventTime){
		Map<PrioritisedRoute, Integer> result = new HashMap<PrioritisedRoute, Integer>();

		List<Event> displayedEvents = getEvents(eventTime);

		// loop through every route
		for (Route route : routes){
			// loop through every priority in route
			for (Priority priority : Priority.values()){
				PrioritisedRoute pRoute = new PrioritisedRoute();
				pRoute.setRoute(route);
				pRoute.setPriority(priority);
				int mailCount = 0;
				// loop through events and find all mail corresponding to correct vehicle
				for (Event event : displayedEvents){
					// if a mail event, increment number of mails for the PrioritisedRoute
					if (event instanceof MailEvent){
						MailEvent mailEvent = (MailEvent) event;
						Mail mail = mailEvent.getMail();
						if (mail.getOrigin().equals(route.getD1()) && mail.getDestination().equals(route.getD2()) && mail.getPriority().equals(priority)){
							mailCount++;
						}
					}
					// if not a mail, apply the change in price
					else {
						applyEvent(event);
					}
				}
				result.put(pRoute, mailCount);
			}
		}
		// return avg delivery time
		return result;
	}

	/**
	 * Calculates the total volume of mail from a given origin to each of its destinations.
	 * @param origin	The origin of the route.
	 * @param eventTime	The event timeframe for calculations.
	 * @return	Map of PrioritisedRoute with the total volume of mail for that route.
	 */
	public Map<PrioritisedRoute, Double> calculateTotalVolumeOfMail(int eventTime){
		Map<PrioritisedRoute, Double> result = new HashMap<PrioritisedRoute, Double>();

		List<Event> displayedEvents = getEvents(eventTime);

		// loop through every route
		for (Route route : routes){
			// loop through every priority in route
			for (Priority priority : Priority.values()){
				PrioritisedRoute pRoute = new PrioritisedRoute();
				pRoute.setRoute(route);
				pRoute.setPriority(priority);
				double vol = 0;
				// loop through events and find all mail corresponding to that route
				for (Event event : displayedEvents){
					// if a mail event, add volume to total volume for the PrioritisedRoute
					if (event instanceof MailEvent){
						MailEvent mailEvent = (MailEvent) event;
						Mail mail = mailEvent.getMail();
						if (mail.getOrigin().equals(route.getD1()) && mail.getDestination().equals(route.getD2()) && mail.getPriority().equals(priority)){
							vol = vol + mail.getVolume();
						}
					}
					// if not a mail, apply the change in price
					else {
						applyEvent(event);
					}
				}
				result.put(pRoute, vol);
			}
		}
		// return avg delivery time
		return result;
	}

	/**
	 * Calculates the total weight of mail from a given origin to each of its destinations.
	 * @param origin	The origin of the route.
	 * @param eventTime	The event timeframe for calculations.
	 * @return	Map of PrioritisedRoute with the total weight of mail for that route.
	 */
	public Map<PrioritisedRoute, Double> calculateTotalWeightOfMail(int eventTime){
		Map<PrioritisedRoute, Double> result = new HashMap<PrioritisedRoute, Double>();

		List<Event> displayedEvents = getEvents(eventTime);

		// loop through every route
		for (Route route : routes){
			// loop through every priority in route
			for (Priority priority : Priority.values()){
				PrioritisedRoute pRoute = new PrioritisedRoute();
				pRoute.setRoute(route);
				pRoute.setPriority(priority);
				double weight = 0;
				// loop through events and find all mail corresponding to correct vehicle
				for (Event event : displayedEvents){
					// if a mail event, add weight to total weight for the PrioritisedRoute
					if (event instanceof MailEvent){
						MailEvent mailEvent = (MailEvent) event;
						Mail mail = mailEvent.getMail();
						if (mail.getOrigin().equals(route.getD1()) && mail.getDestination().equals(route.getD2()) && mail.getPriority().equals(priority)){
							weight = weight + mail.getWeight();
						}
					}
					// if not a mail, apply the change in price
					else {
						applyEvent(event);
					}
				}
				result.put(pRoute, weight);
			}
		}
		// return avg delivery time
		return result;
	}

	/**
	 * Calculates the total company expenditure within a given timeframe.
	 * @param eventTime	The event timeframe for calculations.
	 * @return	Total expenditure across all events in the given event timeframe.
	 */
	public Double calculateExpenditure(int eventTime){
		Double sum = 0.0;

		List<Event> displayedEvents = getEvents(eventTime);

		// loop through events
		for (Event event : displayedEvents){
			// if mail event, add costPerG and costPerCC to total revenue
			if (event instanceof MailEvent){
				Mail mail = ((MailEvent)event).getMail();
				sum += (event.getVehicle().getTransportCostPerCC() * mail.getVolume()) + (event.getVehicle().getTransportCostPerG() * mail.getWeight());
			}
			// if not a mail, apply any change in price
			else {
				applyEvent(event);
			}
		}
		return sum;
	}

	/* METHODS FOR EVENTS */
	/**
	 * Applies the event changes to the data. Allows for dynamic costs, etc.
	 * @param event	The event to be applied.
	 */
	private void applyEvent(Event event) {
		if (event instanceof PriceUpdateEvent){
			event.getVehicle().updateCustomerCost(((PriceUpdateEvent)event).getCostPerG(), ((PriceUpdateEvent)event).getCostPerCC());
		}
		else if (event instanceof TransportUpdateEvent){
			event.getVehicle().updateTransportCost(((TransportUpdateEvent)event).getCostPerG(), ((TransportUpdateEvent)event).getCostPerCC());
		}
		else if (event instanceof DiscontinueTransportEvent){
			for (Route route : routes){
				route.discontinueTransport(event.getVehicle().getID());
			}
		}
	}

	/**
	 * Creates a mail and adds all associated MailEvents to the list of events.
	 * @param ID
	 * @param weight
	 * @param volume
	 * @param origin
	 * @param destination
	 * @param priority
	 */
	public boolean sendMail(int ID, double weight, double volume, DistributionCentre origin, DistributionCentre destination, Priority priority) {
		
		ArrayList<MailEvent> mailEvents = CreateMailEvents(ID,weight,volume,origin,destination,priority);
		
		
		if(origin.getCity().equalsIgnoreCase(destination.getCity())){
			return false;
		}
		
		
		if(mailEvents == null){
			//Didnt find a route by air 
			return false ;
		}
		//System.out.println("mailevents: " + mailEvents.size());
		//System.out.println("created arraylist of mail events");

		Collections.reverse(mailEvents);
		if (mailEvents.size() > 0){
			//Make a piece of mail
			Mail tempMail = new Mail(ID, weight, volume, origin, destination, priority);
			//Add mail events to it
			tempMail.setEvents(mailEvents);
			//Add mail to all mail
			allMail.add(tempMail);
		 
			// add new MailEvents
			for (Event event : tempMail.getEvents()){
				events.add(event);
			}
			/* testing
			System.out.println("events: " + events.getSize());
			for (Event event : events.getList()){
				System.out.println(event.getClass() + ", " + event.getVehicle());
			}*/
			//FOund route and created mail
			return true;

		}
		return false;
	}

	/**
	 * Updates the customer price for a route given an origin, destination, priority and firm. If the route does not exist,
	 * or if a vehicle with that priority does not exist, a null event is returned.
	 * @param origin
	 * @param destination
	 * @param pricePerG	The new price per gram charged to the customer.
	 * @param pricePerCC	The new price per cubic centimetre charged to the customer.
	 * @param priority
	 * @param firm	The firm the vehicle belongs to.
	 */
	public Event updatePrice(DistributionCentre origin, DistributionCentre destination, double pricePerG, double pricePerCC, Priority priority, Firm firm) {
		Vehicle vehicle = getVehicle(origin, destination, priority, firm);
		if (vehicle == null){
			return null;
		}
		vehicle.updateCustomerCost(pricePerG, pricePerCC);

		// add to event log
		Event event = new PriceUpdateEvent(vehicle, currentDate, pricePerCC, pricePerG);
		events.add(event); 
		return event;
	}

	/**
	 * Updates the transport cost for a route given an origin, destination, priority and firm. If the route does not exist,
	 * a new route is created. If a vehicle is not associated with that priority and firm, a new vehicle is created.
	 * @param origin
	 * @param destination
	 * @param pricePerG	The new cost per gram for the company.
	 * @param pricePerCC	The new cost per cubic centimetre for the company.
	 * @param frequency The frequency at which the transport delivers.
	 * @param duration	The time taken for the transport to deliver the mail.
	 * @param day	The day the transport is available.
	 * @param priority	The prority of mail carried.
	 * @param firm	The firm the vehicle belongs to.
	 */
	public Event updateTransport(DistributionCentre origin, DistributionCentre destination, double pricePerG, double pricePerCC, int frequency, int duration,
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
			vehicle = new Vehicle(route.getVehicles().size(), pricePerG, pricePerCC, frequency, duration, priority, firm ,day);
			route.addVehicle(vehicle);
		}
		// else update the transport cost
		else {
			vehicle.updateTransportCost(pricePerG, pricePerCC);
		}
		Event event = new TransportUpdateEvent(vehicle, pricePerCC, pricePerG, frequency, duration, currentDate, origin, destination);
		events.add(event); 
		return event;
	}

	/**
	 * Discontinues a transport vehicle given an origin, destination, priority, firm and day. If no such transport exists, a null event
	 * is returned.
	 * @param origin
	 * @param destination
	 * @param priority	The prority of mail carried.
	 * @param firm	The firm the vehicle belongs to.
	 * @param day	The day the transport is available.
	 */
	public boolean discontinueTransport(DistributionCentre origin, DistributionCentre destination, Priority priority, Firm firm) {
		Route route = findRoute(origin, destination);
		if (route == null)
			return false;
		boolean hasDiscontinued = false;
		// iterate through all vehicles corresponding to priority and firm
		for (Vehicle vehicle : route.getVehiclesByPriority(priority)){
			if (vehicle.getFirm().equals(firm)){
				// discontinue those vehicles
				route.discontinueTransport(vehicle.getID());

				// add to event log
				Event event = new DiscontinueTransportEvent(vehicle, currentDate, destination, destination);
				events.add(event);
				hasDiscontinued = true;
			}
		}
		return hasDiscontinued;
	}


	/**
	 * Gets a sublist of events within a given event timeframe.
	 * @param eventTime	The event timeframe.
	 * @return	Sublist of events within timeframe.
	 */
	public List<Event> getEvents(int eventTime){
		if (events.getSize() == 0){
			return new ArrayList<Event>();
		}
		
		// get list of events
		if (eventTime > events.getSize())
			eventTime = events.getSize();
		else if (eventTime < 0)
			eventTime = 0;

		List<Event> displayedEvents = events.getList().subList(0, eventTime);

		return displayedEvents;
	}

	/**
	 * Returns a list of all events, and updates all vehicles to most current prices.
	 * @return events
	 */
	public List<Event> getAllEvents(){
		// apply each event in order to update vehicle costs to most recent version
		for (Event event : events.getList()){
			applyEvent(event);
		}
		return events;
	}

	/**
	 * Returns the total number of events.
	 * @return	Total number of events.
	 */
	public int getNumberOfEvents(){
		return events.getSize();
	}

	public int passwordHasher(String s) {
		int hashed = s.hashCode();
		hashed = (int) Math.floor((hashed*3621873+1321798)/Math.PI);
		return hashed;
	}


	// Helper methods
	/**
	 * Returns vehicle corresponding to origin, destination, priority and firm.
	 */
	private Vehicle getVehicle(DistributionCentre origin, DistributionCentre destination, Priority priority, Firm firm){
		Route route = findRoute(origin, destination);
		if (route == null)
			return null;

		Vehicle vehicle = route.getVehicle(priority, firm);
		if (vehicle == null)
			return null;

		return vehicle;
	}

	/**
	 * Finds the firms currently in the system.
	 */
	public List<Firm> findFirms(){
		List<Firm> result = new ArrayList<Firm>();
		for (Route route : this.routes){
			for (Vehicle vehicle : route.getVehicles()){
				if (!result.contains(vehicle.getFirm())){
					result.add(vehicle.getFirm());
				}
			}
		}
		return result;
	}

	/**
	 * Finds the route corresponding to the origin and destination.
	 */
	public Route findRoute(DistributionCentre origin, DistributionCentre destination){
		for (Route route : this.routes){
			if (route.getD1().equals(origin) && route.getD2().equals(destination))
				return route;
			else if (route.getD2().equals(origin) && route.getD1().equals(destination))
				return route;

		}
		// route not found, return null
		return null;
	}

	/**
	 * 
	 * Returns a double which is the total cost of a piece of mail.
	 * @return cost
	 */
	//Total cost of a piece of mail.
	public  double returnMailCost(double weight, double volume, DistributionCentre origin, DistributionCentre destination, Priority priority){
		SearchNode goalNode = CalculateRoute(origin, destination, weight, volume, priority);
		if(goalNode == null){
			return 0.0;
		}
		double cost = goalNode.getTotalPathCost();
		return cost;
	}
	
	/**
	 * @param ID		The ID of the mail  
	 * @param weight    The wieght of the mail 
	 * @param volume	The volume of the  mail 
	 * @param origin 	
	 * @param destination
	 * @param priority
	 * 
	 * Creates a list of mail events for a peice of mail by passing all relevant info to 
	 * the calclate route method getting back a search node and then reconstructing the path
	 * the mail took and returning it
	 * 
	 * @return mailEvents
	 */
	//Creates mail eveents for travel between 2 node, connected or unconnected
	public  ArrayList<MailEvent> CreateMailEvents(int ID, double weight, double volume, DistributionCentre origin, DistributionCentre destination, Priority priority){
		//to be added to a peice of mail later.
		ArrayList<MailEvent> mailEvents = new ArrayList<MailEvent>();
		//the search node that was at the destination.

		SearchNode goalNode = CalculateRoute(origin, destination, weight, volume, priority);

		//HAve a goal search node, now go back through nodes making a mail event for each route
		for(SearchNode s = goalNode ; s != null && s.getPreviousSearchNode() != null ; s = s.getPreviousSearchNode()){
			//Make Mail for mail event eg mail between 2 nodes
			Mail tempMail = new Mail(ID, weight, volume, s.getPreviousSearchNode().getCurrentDistributionCentre()
					, s.getCurrentDistributionCentre(), priority);
			//Make a mail event and add mail to it
			MailEvent tempMailEvent = new MailEvent(s.getVehicle(), s.getVehicle().getDay(), tempMail ,currentDate);
		
			
			//Step 3 -- ...........?
			//Step 4 -- Profit *Trollface.jpeg*
			
			tempMailEvent.setProfitOnRoute(s.getProfit());	
			tempMailEvent.setRoute(findRoute(s.getCurrentDistributionCentre(), s.getPreviousSearchNode().getCurrentDistributionCentre()));
			System.out.println(" PROFIT = " + tempMailEvent.getProfitOnRoute());
			//add event to array to add to overall mail
			mailEvents.add(tempMailEvent);
		}

		return mailEvents;
	}
	
	
	/** 
	 * @param weight    The wieght of the mail 
	 * @param volume	The volume of the  mail 
	 * @param origin 	
	 * @param destination
	 * @param priority
	 * 
	 * Calculates the optimal route for a piece of mail to take based on the 
	 * cheapest route, also makes sure that if no route is possible null is returned
	 * 
	 * @return mailEvents
	 */
	//Calculates path between nodes
	public SearchNode  CalculateRoute(DistributionCentre o,DistributionCentre d , Double Weight, Double Volume , Priority p){
		DistributionCentre destination = d;	
		DistributionCentre origin = o;
		Double wieght = Weight;
		Double volume = Volume;
		Priority priority = p;
		
		//should fail if equal

		if(origin.getCity().equals(destination.getCity())){
	 
			return null;
		}
		
		ArrayList<DistributionCentre> searched = new ArrayList<DistributionCentre>(); // The set of nodes already evaluated. 
		PriorityQueue<SearchNode> fringe = new PriorityQueue<SearchNode>();			  // Fringe nodes

		SearchNode search = new SearchNode(origin, null, wieght, volume, null);
		fringe.offer(search); //Queue our starting node.
 

		while(fringe.size() != 0){
 
			SearchNode tempNode = fringe.poll(); 	//Remove node closest
			searched.add(tempNode.current);			//Temp node added to visited 
		 

			if(tempNode.getCurrentDistributionCentre().equals(destination)){	//Goal node has been reached
				//Returning final searchNode to do stuff with
				return tempNode;
			}

			for(DistributionCentre r : tempNode.getConnectingNodes(p)){
				if(searched.contains(r)){	//if node has been visited then carry on
					continue;
				}


				if(!fringe.contains(r)){	//if y not in fringe, add it
					// find vehicle to attach to node
					Route tempRoute = findRoute(r, tempNode.getCurrentDistributionCentre());
					//For air travel
					// only use air route if air priority					
					if(priority == Priority.INTERNATIONAL_AIR){
						for(Vehicle v : tempRoute.getVehicles()){
							if ((v.getPriority().equals(Priority.INTERNATIONAL_AIR)) ||(v.getPriority().equals(Priority.DOMESTIC))){		
								SearchNode tempSearchNode = new SearchNode(r, tempNode, wieght, volume, v);
								fringe.add(tempSearchNode);
							}
						}
					}
					else{
						for(Vehicle v : tempRoute.getVehicles()){
							SearchNode tempSearchNode = new SearchNode(r, tempNode, wieght, volume, v);		
							fringe.add(tempSearchNode);
						}
					}
				}	

			}
 
		}
 

		//NO ROUTE EXISTS 
		return null;

	}

	//For finding route
	/**
	 * 
	 * Used in the Dijkstra s  search algorithm 
	 * 
	 * @return mailEvents
	 */
	private class SearchNode implements Comparable<SearchNode>{


		private DistributionCentre current; 
		private SearchNode previous ; 		
		private double totalPathCost ; //Total cost to this distribution center
		private double routeCost;		//cost from previous distribution center
		private Vehicle vehicle;
		private double routeProfit;		//What we make / lose on a route (critical routes)
		private double wieght;
		private double volume;
		
		public SearchNode(DistributionCentre current , SearchNode previous , double wieght , Double volume , Vehicle v ){
			this.current = current;
			this.volume = volume;
			this.wieght = wieght;
			this.previous = previous;
			this.vehicle = v;
			if (vehicle == null){
				routeCost = 0;
			}
			else {
				routeCost = (vehicle.getCustomerCostPerCC()*volume) + (vehicle.getCustomerCostPerG()*wieght);
				
			}
			if (previous == null){
				totalPathCost = 0;
			}
			else {
				totalPathCost = previous.totalPathCost + routeCost;
			}
			
			
		}


		public DistributionCentre getCurrentDistributionCentre() {
			return current;
		}
		public SearchNode getPreviousSearchNode() {
			return previous;
		}
		public double getTotalPathCost() {
			return totalPathCost;
		} 
		public double getRouteCost() {
			return routeCost;
		}
		public double getProfit() { 
			routeProfit = routeCost - ((vehicle.getTransportCostPerCC()*volume) + (vehicle.getTransportCostPerG()*wieght));
			return routeCost;		
		}
		public Vehicle getVehicle() {
			return vehicle;
		}
		
		/**
		 * Find all nodes that a have a route in common with the current dist center 
		 * also checks if they can be reached with current priority
		 * eg if air priority and there is no air vehicle between 2 dist centers then that
		 * node wont be returned.
		 */
		//Gets all connecting nodes that are valid (eg air nodes if air priority)
		public ArrayList<DistributionCentre> getConnectingNodes(Priority pri){

			Priority p = pri;
			ArrayList<DistributionCentre> connected = new ArrayList<DistributionCentre>();
			ArrayList<DistributionCentre> connectedByAir = new ArrayList<DistributionCentre>();
			//Find all nodes that connect to this node
			for(Route r : routes){
				if(r.getD1().equals(current)){
					connected.add(r.getD2());
				}
				if(r.getD2().equals(current)){
					connected.add(r.getD1());
				}
			}
			//If Priority is air find nodes that can be reached by plane
			if(p == Priority.INTERNATIONAL_AIR){
				//Go through all centers that connect to here
				for(DistributionCentre d : connected){
					//get the routes
					Route r = findRoute(current, d);
					//for those routes see if they have an air vehicle
					for(Vehicle v : r.getVehicles()){
						if((v.getPriority().equals(Priority.INTERNATIONAL_AIR)) ||(v.getPriority().equals(Priority.DOMESTIC))){  //THIS DOSNET EXIST YET I THINK ITS WHATS NEEDED
							//if they do add the dist center to air centers
							connectedByAir.add(d);
						}
					}
				}
				return connectedByAir;
			}


			return connected;
		}

		//For priority queue , compared vaule is total cost so far
		public int compareTo(SearchNode s) {
			if (this.totalPathCost < s.getTotalPathCost()) return -1;
			if (this.totalPathCost > s.getTotalPathCost()) return 1;
			return 0;
		}
	}
}

