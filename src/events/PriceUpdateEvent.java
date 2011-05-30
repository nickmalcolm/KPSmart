package events;

import java.util.Date;

import backend.Day;

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

	public Date getDate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String displayString() {
		String str = "Price Update Event: \n" +
				"\tVehicle: "+super.getVehicle()+"\n"+
				"\tPrice per CC: "+costPerCC+"\n"+
				"\tPrice per G: "+costPerG+"\n"
				;
		
		return str;
	}

}
