package tests;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JPasswordField;

import org.junit.Test;

import priority.Priority;

import routes.DistributionCentre;
import routes.Route;

 
import backend.KPSBackend;
 
import junit.framework.TestCase;

public class XmlLoadingTests extends TestCase{
	
	
	KPSBackend kBackend;
	
	
	//Basic as test just to see that all the xml loaded in properly
	protected void setUp() throws ParseException{
		
		kBackend = new KPSBackend();
		kBackend.parseXMLRecord();
 
	
	}
	
	
	@Test
	public void testDistCentres(){
		assertEquals(kBackend.getDistributionCentres().size() , 9);
		for (DistributionCentre d : kBackend.getDistributionCentres()){
			assertEquals(1, 1);
			assertTrue(d.getCity() != "" );
			assertTrue(d.getCountry() != "" );
			assertTrue(d.getName() != "" );
		}
	}
	
	//Just checking that routes exist where they should
	@Test
	public void testRoutes(){
		assertTrue(kBackend.getRoutes().size() == 10);
		for (Route r: kBackend.getRoutes()){
			assertEquals(1, 1);
			assertTrue(r.getD1() != null );
			assertTrue(r.getD2() != null  );
		}
		
	}

	//Sving and Loading, add 1 mail event save (now there are 4 saved)
	//Add another mail event (5 now 1st 4 saved
	//then load up another kpsmart (should b 4 events)
	
	@Test
	public void testSaveLoad(){
		int events = kBackend.getNumberOfEvents();
		DistributionCentre origin = null;
		DistributionCentre destination = null;
		for(DistributionCentre d : kBackend.getDistributionCentres()){
			if(d.getCity().equalsIgnoreCase("Wellington")){
				origin = d;
			}
			if(d.getCity().equalsIgnoreCase("Palmerston North")){
				destination = d;
			}
		}
		kBackend.sendMail(1, 1, 1, origin, destination, Priority.DOMESTIC);
		kBackend.createXMLRecord();
		kBackend.sendMail(1, 1, 1, origin, destination, Priority.DOMESTIC);
		assertEquals(kBackend.getNumberOfEvents(), events + 2);
		kBackend.parseXMLRecord();
		 
		
		KPSBackend kBackend2 = new KPSBackend();
		kBackend2.parseXMLRecord();
		
		assertEquals(kBackend2.getNumberOfEvents(), events + 1);
		
	}
 

}
