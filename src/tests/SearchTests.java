package tests;

import java.text.ParseException;
import java.util.Set;

import org.junit.Test;

import junit.framework.TestCase;

import priority.Priority;

import events.Event;
import events.MailEvent;

import routes.DistributionCentre;
import routes.Route;

import backend.KPSBackend;

public class SearchTests extends TestCase{

	KPSBackend kBackend;
	Set<DistributionCentre> dists;

	
	protected void setUp() throws ParseException{
		
		kBackend = new KPSBackend();
		kBackend.parseXMLRecord();
		dists = kBackend.getDistributionCentres();
 
	}
	
	//A piece of mail that should create 1 mail event between wellington and palmy
	@Test
	public void testRoute(){
	
		int previousSize = kBackend.getNumberOfEvents();
 
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
		assertEquals(kBackend.getNumberOfEvents(), previousSize +1);
		
	}
	
	//A path that takes multiple routes
	@Test
	public void testRoutesPath(){
		
		int previousSize = kBackend.getNumberOfEvents();
 
		DistributionCentre origin = null;
		DistributionCentre destination = null;
		for(DistributionCentre d : kBackend.getDistributionCentres()){
			if(d.getCity().equalsIgnoreCase("Wellington")){
				origin = d;
			}
			if(d.getCity().equalsIgnoreCase("Auckland")){
				destination = d;
			}
		}
	
		kBackend.sendMail(1, 1, 1, origin, destination, Priority.DOMESTIC);
		//there are 3 routes needed to go through
		assertEquals(kBackend.getNumberOfEvents(), previousSize +4);
		
	}
	
	
	//Theres a single route bewteen auckland and rototua but its cheaper to go via hamilton
	@Test
	public void testBestPrice(){
		
		int previousSize = kBackend.getNumberOfEvents();
		System.out.println(previousSize+ "SIZEIEiEIEIEiiEIiEie");
 
		DistributionCentre origin = null;
		DistributionCentre destination = null;
		for(DistributionCentre d : kBackend.getDistributionCentres()){
			if(d.getCity().equalsIgnoreCase("Auckland")){
				origin = d;
			}
			if(d.getCity().equalsIgnoreCase("Rotorua")){
				destination = d;
			}
		}
	  
		kBackend.sendMail(1, 1, 1, origin, destination, Priority.DOMESTIC);
		//there are 4 routes needed to go through
		assertEquals(kBackend.getNumberOfEvents(), previousSize +2);
		
	}
	
	//Should fail when goin to NY by air as there is no air vehicle
	@Test
	public void testNoAir(){
		
		int previousSize = kBackend.getNumberOfEvents();
		
 
		DistributionCentre origin = null;
		DistributionCentre destination = null;
		for(DistributionCentre d : kBackend.getDistributionCentres()){
			if(d.getCity().equalsIgnoreCase("Auckland")){
				origin = d;
			}
			if(d.getCity().equalsIgnoreCase("New York")){
				destination = d;
			
			}
		}
		assertFalse(kBackend.sendMail(1, 1, 1, origin, destination, Priority.INTERNATIONAL_AIR));
		}
	
	
	//Should fail when origin and dest are same
	@Test
	public void testSame(){
		
		int previousSize = kBackend.getNumberOfEvents();
		
 
		DistributionCentre origin = null;
		DistributionCentre destination = null;
		System.out.println("HEREZZZZZZ");
		for(DistributionCentre d : kBackend.getDistributionCentres()){
			if(d.getCity().equalsIgnoreCase("Auckland")){
				origin = d;
			}
			if(d.getCity().equalsIgnoreCase("Auckland")){
				destination = d;
			}
		}
		assertFalse(kBackend.sendMail(1, 1, 1, origin, destination, Priority.DOMESTIC));
	}
}
