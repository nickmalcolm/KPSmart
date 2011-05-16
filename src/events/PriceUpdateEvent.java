package events;

import java.util.Date;

import routes.Vehicle;

public class PriceUpdateEvent extends Event {
	
	private double costPerCC;
	private double costPerG;

	/**
	 * 
	 * @param costPerCC
	 * @param costPerG
	 */
	public PriceUpdateEvent(Vehicle vehicle, Date timestamp, double costPerCC, double costPerG) {
		super(vehicle, timestamp);
		this.costPerCC = costPerCC;
		this.costPerG = costPerG;
	}
	
	public double getCostPerCC() {
		return costPerCC;
	}
	public double getCostPerG() {
		return costPerG;
	}
	
	

}
