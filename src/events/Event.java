package events;

import routes.Vehicle;

// note @Nick, just added some dummy code to get the vehicle associated with an event as per class diagram. -J
public abstract class Event {
	private Vehicle vehicle;
	
	public Vehicle getVehicle(){
		return vehicle;
	}
}
