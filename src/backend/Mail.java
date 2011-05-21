package backend;

import java.awt.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

 




import events.MailEvent;
import priority.*;
import routes.DistributionCentre;
import routes.Route;

public class Mail {
	
	private int ID;
	
	private double weight;
	private double volume;
	
	private DistributionCentre origin;
	private DistributionCentre destination;
	
	private Priority priority;
	
	private ArrayList<MailEvent> events;
	
	public Mail(int ID, double weight, double volume, DistributionCentre origin, DistributionCentre destination, Priority priority) {
		this.ID = ID;
		this.weight = weight;
		this.volume = volume;
		this.origin = origin;
		this.destination = destination;
		this.priority = priority;
		 
	}
	
	public int getID() {
		return ID;
	}

	public DistributionCentre getOrigin() {
		return origin;
	}

	public DistributionCentre getDestination() {
		return destination;
	}

	public double getWeight() {
		return weight;
	}

	public double getVolume() {
		return volume;
	}

	public Priority getPriority() {
		return priority;
	}

	public ArrayList<MailEvent> getEvents() {
		return events;
	}
	public void setEvents(ArrayList<MailEvent> m) {
		events = m;
	}
	

	
	 
	
	


}
