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
		Map<PrioritisedRoute, Double> deliveryTimes = kBackend.calculateDeliveryTimes(2);
		PrioritisedRoute pRoute1 =null;
		PrioritisedRoute pRoute2 = null;
		
		for(PrioritisedRoute p : deliveryTimes.keySet()){
			 if(p.getRoute().getD1().getName().equals("Auckland") 
					 && p.getRoute().getD2().getName().equals("Sydney") 
					 && p.getPriority() == Priority.INTERNATIONAL_AIR){
				 pRoute1 = p;
			 }
			 else if (p.getRoute().getD1().getName().equals("Palmerston North") 
					 && p.getRoute().getD2().getName().equals("Wellington") 
					 && p.getPriority() == Priority.DOMESTIC){
				 pRoute2 = p;
			 }
		 }
		System.out.println(pRoute2.toString());
		System.out.println(deliveryTimes.get(pRoute2));
		assertEquals(deliveryTimes.get(pRoute1), 1.0);
		assertEquals(deliveryTimes.get(pRoute2), 1.0);
	}
	
	//amount of mail
	//Checking if right for 1st 3 dummy events
	@Test
	public void testAmountOfMail(){
		Map<PrioritisedRoute, Integer> mailAmount = kBackend.calculateAmountOfMail(3);
		PrioritisedRoute pRoute1 =null;
		PrioritisedRoute pRoute2 = null;
		
		for(PrioritisedRoute p : mailAmount.keySet()){
			 if(p.getRoute().getD1().getName().equals("Auckland") 
					 && p.getRoute().getD2().getName().equals("Sydney") 
					 && p.getPriority() == Priority.INTERNATIONAL_AIR){
				 pRoute1 = p;
			 }
			 else if (p.getRoute().getD1().getName().equals("Palmerston North") 
					 && p.getRoute().getD2().getName().equals("Wellington") 
					 && p.getPriority() == Priority.DOMESTIC){
				 pRoute2 = p;
			 }
		 }
		assert(mailAmount.get(pRoute1) == 1);
		assert(mailAmount.get(pRoute2) == 2);
	}
	
	//  wieght of mail
	//Checking if right for 1st 3 dummy event 
	//simple test for 1st piece of mail
	@Test
	public void testWeightOfMail(){
		Map<PrioritisedRoute, Double> mailWeight = kBackend.calculateTotalWeightOfMail(2);
		PrioritisedRoute pRoute1 = null;
		PrioritisedRoute pRoute2 = null;
		
		for(PrioritisedRoute p : mailWeight.keySet()){
			 if(p.getRoute().getD1().getName().equals("Auckland") 
					 && p.getRoute().getD2().getName().equals("Sydney") 
					 && p.getPriority() == Priority.INTERNATIONAL_AIR){
				 pRoute1 = p;
			 }
			 else if (p.getRoute().getD1().getName().equals("Palmerston North") 
					 && p.getRoute().getD2().getName().equals("Wellington") 
					 && p.getPriority() == Priority.DOMESTIC){
				 pRoute2 = p;
			 }
		 }
		assertEquals(mailWeight.get(pRoute1), 28.0);
		assertEquals(mailWeight.get(pRoute2), 17.0);
		
	}
	
	

	//volume of mail  
	//Checking if right for 1st 3 dummy events
	@Test
	public void testVolumeOfMail(){
		Map<PrioritisedRoute, Double> mailVolume = kBackend.calculateTotalVolumeOfMail(2);
		PrioritisedRoute pRoute1 =null;
		PrioritisedRoute pRoute2 = null;
		
		for(PrioritisedRoute p : mailVolume.keySet()){
			 if(p.getRoute().getD1().getName().equals("Auckland") 
					 && p.getRoute().getD2().getName().equals("Sydney") 
					 && p.getPriority() == Priority.INTERNATIONAL_AIR){
				 pRoute1 = p;
			 }
			 else if (p.getRoute().getD1().getName().equals("Palmerston North") 
					 && p.getRoute().getD2().getName().equals("Wellington") 
					 && p.getPriority() == Priority.DOMESTIC){
				 pRoute2 = p;
			 }
		 }

		System.out.println(pRoute2.toString());
		System.out.println(mailVolume.get(pRoute2));
		assertEquals(mailVolume.get(pRoute1), 23.0);
		assertEquals(mailVolume.get(pRoute2), 7.0);
	}
		
		
	
	
	//expenditure
	//Checking if right for 1st 3 dummy events
	@Test
	public void testExpenditure(){
		Double expenditure = kBackend.calculateExpenditure(3);
		assertEquals(expenditure, 15.3);
		
		
	}
	
	
}
