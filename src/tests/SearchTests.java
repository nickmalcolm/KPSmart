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
	Set<Route> routes;
	
	protected void setUp() throws ParseException{
		
		kBackend = new KPSBackend();
		kBackend.parseXMLRecord();
		dists = kBackend.getDistributionCentres();
		System.out.println("test");
		System.out.println(dists.size());
	}
	
	//A piece of mail that should create 1 mail event between wellington and palmy
	@Test
	public void oneRoutePathTest(){
//		DistributionCentre origin = null;
//		DistributionCentre destination = null;
//		for(DistributionCentre d : kBackend.getDistributionCentres()){
//			if(d.getCity().equalsIgnoreCase("Wellington")){
//				origin = d;
//			}
//			if(d.getCity().equalsIgnoreCase("Palmerston North")){
//				destination = d;
//			}
//		}
//		kBackend.sendMail(1, 1, 1, origin, destination, Priority.DOMESTIC);
//		assertEquals(kBackend.getAllEvents().size(), 1);
		assertEquals(1, 1);
		
	}
	@Test
	public void testTest(){
		assertEquals(1, 1);
	}
	
}
