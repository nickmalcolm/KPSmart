package backend;

import java.util.ArrayList;

import events.*;
import priority.*;
import routes.*;

public class KPSBackend {
	
	private ArrayList<Route> routes;
	private ArrayList<Mail> activeMail;
	
	//private String password;
	private int passwordHash;
	private boolean isManager;
	
	public KPSBackend() {
		
		routes = new ArrayList<Route>();
		activeMail = new ArrayList<Mail>();

		//password = "sototessecure";
		passwordHash = 653306037;
		isManager = false;
		
	}
	
	public String testMethod() {
		return "This is a test";
	}
	
	//Parses the XML record(s) and retains their contents in memory
	public void parseXMLRecord() {
		
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
	
	public void sendMail(int ID, double weight, double volume, String origin, String destination, Priority priority) {
		Mail mail = new Mail(ID, weight, volume, origin, destination, priority);
		activeMail.add(mail);
		getMail(ID);
	}
	
	//Updates the customer price for a route
	public Event updatePrice(DistributionCentre origin, DistributionCentre destination) {
		//Needs two nodes
		
		//Work out routes
		
		return null;
	}
	
	public Event updateTransport() {
		return null;
	}
	
	public Event discontinueTransport() {
		return null;
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
	
	public int passwordHasher(String s) {
		int hashed = s.hashCode();
		hashed = (int) Math.floor((hashed*3621873+1321798)/Math.PI);
		return hashed;
	}
}
