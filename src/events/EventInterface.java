package events;

import java.util.Date;

public interface EventInterface extends Comparable<EventInterface> {
	
	public Date getTimestamp();
	
}
