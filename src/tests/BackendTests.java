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
	
	//delivery times
	//Checking if right for 1st 3 dummy events
	@Test
	public void testDeliveryTimes(){
		Map<PrioritisedRoute, Double> deliveryTimes = kBackend.calculateDeliveryTimes(3);
		 
		
		
	}
	
	//amount of mail
	//Checking if right for 1st 3 dummy events
	@Test
	public void testAmountOfMail(){
		Map<PrioritisedRoute, Integer> amountOfMail = kBackend.calculateAmountOfMail(3);
		 for(PrioritisedRoute p : amountOfMail.keySet()){
			 if(p.getRoute() )
		 }
		
		
	}
	
	//  wieght of mail
	//Checking if right for 1st   dummy event 
	//simple test for 1st piece of mail
	@Test
	public void testWeightOfMail(){
		Map<PrioritisedRoute, Double> mailWieght = kBackend.calculateTotalWeightOfMail(1);
		
		
		double wieght = 0;
		
		 
		 
		
	 
	}
	
	

	//volume of mail  
	//Checking if right for 1st 3 dummy events
	@Test
	public void testVolumeOfMail(){
		Map<PrioritisedRoute, Double> mailWieght = kBackend.calculateTotalVolumeOfMail(3);
	 
		
		
	}
	
	//expenditure
	//Checking if right for 1st 3 dummy events
	@Test
	public void testExpenditure(){
		Double expenditure = kBackend.calculateExpenditure(3);
		assertEquals(expenditure, 15.3);
		
		
	}
	
	
}
