package backend;

import priority.*;

public class Mail {
	
	private int ID;
	
	private double weight;
	private double volume;
	
	private String origin;
	private String destination;
	
	private Priority priority;
	
	public Mail(int ID, double weight, double volume, String origin, String destination, Priority priority) {
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

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
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
}
