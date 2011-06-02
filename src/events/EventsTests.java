package events;

import java.text.DateFormat;
import java.text.ParseException;

import junit.framework.TestCase;

import org.junit.Test;

import backend.Day;

/**
 * Some Event tests
 * @author Nicholas Malcolm - malcolnich - 300170288
 *
 */
public class EventsTests extends TestCase{
	MailEvent old;
	MailEvent newer;
	
	PriceUpdateEvent p1;
	PriceUpdateEvent p2;
	
	EventList<Event> list;
	
	protected void setUp() throws ParseException{
		old = new MailEvent(null, Day.MONDAY, null);
		newer = new MailEvent(null, Day.TUESDAY, null);
		p1 = new PriceUpdateEvent(null, DateFormat.getDateInstance(DateFormat.SHORT).parse("11/12/2010"), 0, 0);
		p2 = new PriceUpdateEvent(null, DateFormat.getDateInstance(DateFormat.SHORT).parse("11/08/2010"), 0, 0);
		list = new EventList<Event>();
		list.add(old);
		list.add(newer);
		list.add(p1);
		list.add(p2);
	}
	
	@Test
	public void testSort(){
		assertTrue(old.compareTo(newer) > 0);
		assertTrue(newer.compareTo(old) < 0);
	}

	@Test
	public void testNoFilter(){
		assertTrue("List should contain old", list.contains(old));
		assertTrue(list.contains(newer));
		assertTrue(list.contains(p1));
		assertTrue(list.contains(p2));
	}

	@Test
	public void testOrder(){
		assertEquals("Newer should be first out", newer, list.get(0));
		assertEquals(old, list.get(1));
		assertEquals(p1, list.get(2));
		assertEquals(p2, list.get(3));
		
	}
	

	@Test
	public void testFilterOnlyMail(){
		EventList<Event> filtered = list.showOnly(MailEvent.class);
		
		for(Event e : filtered){
			assertTrue(e.getClass().isInstance(MailEvent.class));
		}
	}

	@Test
	public void testFilterOnlyPriceUpdates(){
		EventList<Event> filtered = list.showOnly(PriceUpdateEvent.class);
		
		for(Event e : filtered){
			assertTrue(e.getClass().isInstance(PriceUpdateEvent.class));
		}
	}
	
}
