package events;

import routes.Vehicle;

// note @Nick, just added some dummy code to get the vehicle associated with an event as per class diagram. -J
public abstract class Event {
	private Vehicle vehicle;
	private int timeStamp;
	
	public Vehicle getVehicle(){
		return vehicle;
	}
	
	public int getTimeStamp(){
		return timeStamp;
	}
}
