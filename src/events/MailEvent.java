package events;

import java.util.Date;

import priority.Priority;
import routes.DistributionCentre;
import routes.Route;
import routes.Vehicle;
import backend.Day;
import backend.Mail;

/**
 * A mail event represents the transportation of a piece of mail from 
 * one location to another.
 * 
 * @author Nicholas Malcolm - malcolnich - 300170288
 *
 */
public class MailEvent extends Event {


	private Mail mail; // the mail the MailEvent is associated with
	private Day day;
	private Route route;
	private double profitOnRoute = 0.0;
	
	/**
	 * Creates a Mail Event
	 * @param vehicle - the vehicle on which this piece of mail is being transported
	 * @param day - the day on which this mail was given to the vehicle
	 * @param mail - the mail object
	 */
	public MailEvent(Vehicle vehicle,Day day , Mail mail , Date timestamp) {
		super(vehicle, timestamp);
		this.mail = mail;
		this.day = day;
	}


	public Mail getMail() {
		return mail;
	}
	
	public double getProfitOnRoute() {
		return profitOnRoute;
	}
	
	public void setProfitOnRoute(double price) {
		profitOnRoute = price;
	}
	
	public Route getRoute() {
		return route;
	}
	
	public void setRoute(Route r) {
		route = r;
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
		
		return super.getTimestamp();
	}
	
	public String displayString() {
		String str = "Mail Event: \n" +
				"\t"+mail.displayString()+"\n"+
				"\tDay: "+day.toString();
		return str;
	}
 


}
