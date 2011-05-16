package events;


import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;

import org.junit.Test;

public class EventsTests {
	@Test
	public void testSort() throws ParseException{
		MailEvent old = new MailEvent(null, DateFormat.getDateInstance(DateFormat.SHORT).parse("11/31/2010"), null);
		MailEvent newer = new MailEvent(null, DateFormat.getDateInstance(DateFormat.SHORT).parse("12/31/2010"), null);
		assertTrue(old.compareTo(newer) > 0);
		assertTrue(newer.compareTo(old) < 0);
	}

}
