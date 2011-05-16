package events;

import java.util.Date;

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
	 * @param firm
	 * @param priority
	 * @param origin
	 * @param destination
	 */
	public DiscontinueTransportEvent(Vehicle vehicle, Date timestamp, Firm firm, Priority priority,
			DistributionCentre origin, DistributionCentre destination) {
		super(vehicle, timestamp);
		this.firm = firm;
		this.priority = priority;
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

}
