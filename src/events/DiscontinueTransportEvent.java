package events;

import java.util.Date;

import backend.Day;

import priority.Priority;
import routes.DistributionCentre;
import routes.Firm;
import routes.Vehicle;

public class DiscontinueTransportEvent extends Event {
	
	private Firm firm;
	private Priority priority;
	private DistributionCentre origin;
	private DistributionCentre destination;

	
	/**
	 * 
	 * @param origin
	 * @param destination
	 */
	public DiscontinueTransportEvent(Vehicle vehicle, Date timestamp,
			DistributionCentre origin, DistributionCentre destination) {
		super(vehicle, timestamp);
		this.firm = vehicle.getFirm();
		this.priority = vehicle.getPriority();
		this.origin = origin;
		this.destination = destination;

	}
	
	
	public Firm getFirm() {
		return firm;
	}
	public Priority getPriority() {
		return priority;
	}
	public DistributionCentre getOrigin() {
		return origin;
	}
	public DistributionCentre getDestination() {
		return destination;
	}


	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return null;
	}

}
