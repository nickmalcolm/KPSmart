package events;

import java.util.Date;

import backend.Day;

import routes.Vehicle;

/**
 * This class enables records of events to be kept.
 * It must be subclassed into a specific type of event
 * to be used.
 * 
 * @author Nicholas Malcolm - malcolnich - 300170288
 *
 */
public abstract class Event implements EventInterface{
	private Vehicle vehicle;
	private Day day;
	private Date timestamp;
	
	/**
	 * Creates an event
	 * 
	 * @param vehicle - Every event has a vehicle
	 * @param timestamp
	 */
	public Event(Vehicle vehicle, Date timestamp){
		this.vehicle = vehicle;
		this.timestamp = timestamp;
	}
	
	public Vehicle getVehicle(){
		return vehicle;
	}
	
	public Day getDay(){
		return day;
	}
	public Date getTimestamp(){
		return timestamp;
	}

	/**
	 * Orders events by date
	 */
	public int compareTo(EventInterface o) {
		// TODO Auto-generated method stub
	
		return o.getDate().compareTo(timestamp);
		
	}
	
	public String toString(){
		return day.toString();
	}
	
}
