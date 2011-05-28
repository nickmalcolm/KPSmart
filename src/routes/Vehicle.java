package routes;

import backend.Day;
import priority.*;

public class Vehicle {
	
	private int ID;
	
	private double customerCostPerG = 0.0;
	private double customerCostPerCC = 0.0;
	private double transportCostPerG = 0.0;
	private double transportCostPerCC = 0.0;
	
	private int frequency;
	private int duration;
	
	private Day transportDay;
	
	private Priority priority;
	
	private Firm firm;
	
	public Vehicle(int ID, double pricePerG, double pricePerCC, int frequency, int duration, Priority priority, Firm firm,Day day) {	
		this.ID = ID;
		this.customerCostPerG = pricePerG;
		this.customerCostPerCC = pricePerCC;
		this.frequency = frequency;
		this.duration = duration;
		this.priority = priority;
		this.firm = firm;
		this.transportDay = day;
		
	}
	
	public double calculateProfitPerG() {
		return transportCostPerG - customerCostPerG;
	}
	
	public double calculateProfitPerCC() {
		return transportCostPerCC - customerCostPerCC;
	}
	
	public void updateTransportCost(double pricePerG, double pricePerCC) {
		this.transportCostPerG = pricePerG;
		this.transportCostPerCC = pricePerCC;
	}
	
	public void updateCustomerCost(double pricePerG, double pricePerCC) {
		this.customerCostPerG = pricePerG;
		this.customerCostPerCC = pricePerCC;
	}

	public int getID() {
		return ID;
	}
	
	public Day getDay() {
		return transportDay;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public double getCustomerCostPerG() {
		return customerCostPerG;
	}

	public double getCustomerCostPerCC() {
		return customerCostPerCC;
	}

	public double getTransportCostPerG() {
		return transportCostPerG;
	}

	public double getTransportCostPerCC() {
		return transportCostPerCC;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Firm getFirm() {
		return firm;
	}

	public void setFirm(Firm firm) {
		this.firm = firm;
	}
	
	public String toString(){
		return firm+" "+ID;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Vehicle))
			return false;
		Vehicle other = (Vehicle) obj;
		if (this.firm.equals(other.getFirm()) && this.priority.equals(other.getPriority())){ // && this.origin == other.origin && this.destination == other.destination
			return true;
		}
		return false;
	}
	
}
