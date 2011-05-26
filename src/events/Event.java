package events;

import java.util.Date;

import routes.Vehicle;

// note @Nick, just added some dummy code to get the vehicle associated with an event as per class diagram. -J
public abstract class Event implements EventInterface{
	private Vehicle vehicle;
	private Date timestamp;
	
	public Event(Vehicle vehicle, Date timestamp){
		this.vehicle = vehicle;
		this.timestamp = timestamp;
	}
	
	public Vehicle getVehicle(){
		return vehicle;
	}
	
	public Date getTimestamp(){
		return timestamp;
	}

	public int compareTo(EventInterface o) {
		// TODO Auto-generated method stub
		return o.getTimestamp().compareTo(timestamp);
	}
	
	public String toString(){
		return timestamp.toString();
	}
	
}
