package events;

import routes.DistributionCentre;

public class TransportUpdateEvent extends Event {
	
	static enum DAY { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY_FRIDAY_GETTING_DOWN_ON_FRIDAY, SATURDAY, SUNDAY}
	
	private double costPerCC;
	private double costPerG;
	private int frequency;
	private int durationInMinutes;
	private DAY day;
	private DistributionCentre origin;
	private DistributionCentre destination;
	
	
	/**
	 * Creates a Transport Update Event, used to record a 
	 * change in some Transport's variables
	 * 
	 * @param costPerCC - Cost per cubic centimetre
	 * @param costPerG - Cost per gram
	 * @param frequency - how many times per day the transport runs
	 * @param durationInMinutes - duration of a transport event
	 * @param day - the day on which the transport departs
	 * @param origin - the DistributionCentre from which the transport departs
	 * @param destination - the DistributionCentre at which the transport arrives
	 */
	public TransportUpdateEvent(double costPerCC, double costPerG,
			int frequency, int durationInMinutes, DAY day,
			DistributionCentre origin, DistributionCentre destination) {
		super();
		this.costPerCC = costPerCC;
		this.costPerG = costPerG;
		this.frequency = frequency;
		this.durationInMinutes = durationInMinutes;
		this.day = day;
		this.origin = origin;
		this.destination = destination;
	}
	
	
	public double getCostPerCC() {
		return costPerCC;
	}

	public double getCostPerG() {
		return costPerG;
	}

	public int getFrequency() {
		return frequency;
	}

	public int getDurationInMinutes() {
		return durationInMinutes;
	}

	public DAY getDay() {
		return day;
	}

	public DistributionCentre getOrigin() {
		return origin;
	}

	public DistributionCentre getDestination() {
		return destination;
	}

}
