package events;

import backend.Mail;

public class MailEvent extends Event {
	private Mail mail; // the mail the MailEvent is associated with
	
	public Mail getMail(){
		return mail;
	}
}
