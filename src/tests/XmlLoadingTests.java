package tests;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JPasswordField;

import org.junit.Test;

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
		assertTrue(kBackend.getDistributionCentres().size()>0);
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
		kBackend.findRoute()
		
	}
//	
//	@Test
//	public void testEvents(){
//		assertTrue(kBackend.().size()>0);
//		for (Route r : dists){
//			assertEquals(1, 1);
//			assertTrue(d.getCity() != "" );
//			assertTrue(d.getCountry() != "" );
//			assertTrue(d.getName() != "" );
//		}
//	}
 

}
