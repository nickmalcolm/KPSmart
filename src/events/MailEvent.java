package events;

import java.util.Date;

import priority.Priority;
import routes.DistributionCentre;
import routes.Route;
import routes.Vehicle;
import backend.Day;
import backend.Mail;

public class MailEvent extends Event {


	private Mail mail; // the mail the MailEvent is associated with
	private Day day;
	private Route route;
	private double profitOnRoute = 0.0;
	
	public MailEvent(Vehicle vehicle,Day day , Mail mail) {
		super(vehicle, null);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String displayString() {
		String str = "Mail Event: \n" +
				"\t"+mail.displayString()+"\n"+
				"\tDay: "+day.toString();
		return str;
	}


}
