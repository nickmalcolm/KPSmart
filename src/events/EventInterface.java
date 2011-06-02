package events;

import java.util.Date;

import backend.Day;

/**
 * Defines methods which must be implemented by an Event
 * @author Nicholas Malcolm - malcolnich - 300170288
 *
 */
public interface EventInterface extends Comparable<EventInterface> {
	
	public Date getDate();
	
	public String displayString();
	
}
