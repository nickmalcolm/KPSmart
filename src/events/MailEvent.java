package events;

import java.util.Date;

import priority.Priority;
import routes.DistributionCentre;
import routes.Vehicle;
import backend.Day;
import backend.Mail;

public class MailEvent extends Event {


	private Mail mail; // the mail the MailEvent is associated with
	private Day day;
	
	public MailEvent(Vehicle vehicle,Day day , Mail mail) {
		super(vehicle, null);
		this.mail = mail;
		this.day = day;
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

	public Date getDate() {
		// TODO Auto-generated method stub
		return null;
	}



}
