package events;

import java.util.Date;

import backend.Day;

import routes.Vehicle;

public abstract class Event implements EventInterface{
	private Vehicle vehicle;
	private Day day;
	private Date timestamp;
	
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

	public int compareTo(EventInterface o) {
		// TODO Auto-generated method stub
		return o.getDate().compareTo(timestamp);
	}
	
	public String toString(){
		return day.toString();
	}
	
}
