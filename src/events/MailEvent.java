package events;

import java.util.Date;

import priority.Priority;
import routes.DistributionCentre;
import routes.Vehicle;
import backend.Mail;

public class MailEvent extends Event {


	private Mail mail; // the mail the MailEvent is associated with
	
	
	public MailEvent(Vehicle vehicle, Date timestamp, Mail mail) {
		super(vehicle, timestamp);
		this.mail = mail;
	}


	public Mail getMail() {
		return mail;
	}


	public double getVolume() {
		return getMail().getVolume();
	}


	public double getWeight() {
		return getMail().getWeight();
	}


	public DistributionCentre getOrigin() {
		return getMail().getOrigin();
	}


	public DistributionCentre getDestination() {
		return getMail().getDestination();
	}


	public Priority getPriority() {
		return getMail().getPriority();
	}

}
