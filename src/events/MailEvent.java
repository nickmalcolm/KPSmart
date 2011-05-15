package events;

import java.util.Date;

import routes.Vehicle;
import backend.Mail;

public class MailEvent extends Event {
	
	public MailEvent(Vehicle vehicle, Date timestamp, Mail mail) {
		super(vehicle, timestamp);
		this.mail = mail;
	}

	private Mail mail; // the mail the MailEvent is associated with
	
	public Mail getMail(){
		return mail;
	}

}
