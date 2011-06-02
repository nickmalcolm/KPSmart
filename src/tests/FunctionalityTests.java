package tests;

import java.text.ParseException;

import junit.framework.TestCase;

import org.junit.Test;

import priority.Priority;

import routes.DistributionCentre;
import routes.Firm;
import routes.Route;
import routes.Vehicle;

import backend.Day;
import backend.KPSBackend;

public class FunctionalityTests extends TestCase{
	
	
	KPSBackend kBackend;
	
	
	 
	protected void setUp() throws ParseException{
		
		kBackend = new KPSBackend();
		kBackend.parseXMLRecord();
 
	
	}
	
	
	//Updating a transport transport
	//showing old vehicle cost is 10 then gets updated too 100

	@Test
	public void testUpdating(){
		
		
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
	
		Route route = kBackend.findRoute(origin, destination);
		Vehicle showVehicle = route.getVehicles().get(0);
		
		assertEquals(showVehicle.getTransportCostPerCC(), 10.0);
		
		kBackend.updateTransport(origin,destination,100 ,  100, showVehicle.getFrequency(), showVehicle.getDuration(),
				showVehicle.getDay(), showVehicle.getPriority(),showVehicle.getFirm());
		
		
		assertEquals(showVehicle.getTransportCostPerCC(), 100.0);
		
		
	}
	
	//For when no vehicle or route exists a new one is created
	@Test
	public void testNewTransport(){
		DistributionCentre origin = null;
		DistributionCentre destination = null;
	 
		for(DistributionCentre d : kBackend.getDistributionCentres()){
			if(d.getCity().equalsIgnoreCase("Christchurch")){
				origin = d;
			}
			if(d.getCity().equalsIgnoreCase("Auckland")){
				destination = d;
			}
			 
		}
	 
		
		//showing no route
		assertEquals(kBackend.findRoute(origin, destination) , null);
	 
		
		kBackend.updateTransport(origin,destination,100 ,  100,1,5,
				Day.FRIDAY_FRIDAY_GETTING_DOWN_ON_FRIDAY, Priority.DOMESTIC ,kBackend.findFirms().get(0) );
		
		
		assertTrue(kBackend.findRoute(origin, destination) != null);
		
	}
	
	
	//updating prices
	
	
	@Test
	public void testPriceUpdate(){
		DistributionCentre origin = null;
		DistributionCentre destination = null;
		DistributionCentre testCentre =null;
		for(DistributionCentre d : kBackend.getDistributionCentres()){
			if(d.getCity().equalsIgnoreCase("Christchurch")){
				origin = d;
			}
			if(d.getCity().equalsIgnoreCase("Dunedin")){
				destination = d;
			}
			if(d.getCity().equalsIgnoreCase("Auckland")){
				testCentre = d;
			}
		}
		Route route = kBackend.findRoute(origin, destination);
		Vehicle showVehicle = route.getVehicles().get(0);
		
		kBackend.updatePrice(origin, destination, 100, 100, showVehicle.getPriority(), showVehicle.getFirm());
		
		assertEquals(showVehicle.getCustomerCostPerCC(), 100.0);
		
		//Should no route exist update should fail and method returns null
		
		assertEquals(kBackend.updatePrice(origin, testCentre, 100, 100, showVehicle.getPriority(), showVehicle.getFirm()), null);
		
		
	}
	
	
	
	//Discontiuing a transport vehicle
	
	@Test
	public void testDiscontinue(){
		

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
	
		Route route = kBackend.findRoute(origin, destination);
		Vehicle showVehicle = route.getVehicles().get(0);
		
		kBackend.discontinueTransport(origin,  destination,showVehicle.getPriority(), showVehicle.getFirm())  ;
		
		
		assertFalse(route.getVehicles().contains(showVehicle));
	}
	
}
