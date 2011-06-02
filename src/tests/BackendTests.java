package tests;

import java.text.ParseException;
import java.util.Map;

import org.junit.Test;

import priority.Priority;

import routes.DistributionCentre;
import routes.Route;

import backend.KPSBackend;
import backend.PrioritisedRoute;
import junit.framework.TestCase;

public class BackendTests extends TestCase{
	
	KPSBackend kBackend;
	
	
	 
	protected void setUp() throws ParseException{
		
		kBackend = new KPSBackend();
		kBackend.parseXMLRecord();
 
	
	}
	
	
	//adding mail event where we lose lots o money , should be cit route
	@Test
	public void testCritRoute(){
		 
		 
		DistributionCentre origin = null;
		DistributionCentre destination = null;
		for(DistributionCentre d : kBackend.getDistributionCentres()){
			if(d.getCity().equalsIgnoreCase("Christchurch")){
				origin = d;
			}
			if(d.getCity().equalsIgnoreCase("Dunedin")){
				destination = d;
			}
		}
		Route route = kBackend.findRoute(origin,destination);
		
		kBackend.sendMail(1, 1, 1, origin, destination, Priority.DOMESTIC);
		
		Map<PrioritisedRoute, Double> critical = kBackend.getCriticalRoute(4);
		
		critical.containsKey(route);
		}
		
	
	//see if reveune is right for 1st 2 dummy events
	@Test
	public void testRevenue(){
		assertEquals(kBackend.calculateRevenue(1), 26.0);
	
		assertEquals(kBackend.calculateRevenue(2), 35.8);
	
		
		
	}
 
	
	
}
