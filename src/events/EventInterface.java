package events;

import java.util.Date;

import backend.Day;

public interface EventInterface extends Comparable<EventInterface> {
	
	public Date getDate();
	
	public String displayString();
	
}
