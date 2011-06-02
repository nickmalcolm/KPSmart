package tests;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JPasswordField;

import org.junit.Test;

import routes.DistributionCentre;

 
import backend.KPSBackend;
 
import junit.framework.TestCase;

public class XmlLoadingTests extends TestCase{
	
	
	KPSBackend kBackend;
	Set<DistributionCentre> dists;
	
	//Basic as test just to see that all the xml loaded in properly
	protected void setUp() throws ParseException{
		
		kBackend = new KPSBackend();
		kBackend.parseXMLRecord();
		dists = kBackend.getDistributionCentres();
		System.out.println("test");
		System.out.println(dists.size());
	
	}
	
	@Test
	public void testTest(){
		assertEquals(1, 1);
	}
	
	
	@Test
	public void distCentreTestsTest(){
		assertTrue(dists.size()>0);
//		for (DistributionCentre d : dists){
//			assertEquals(1, 1);
//			assertTrue(d.getCity() != "" );
//			assertTrue(d.getCountry() != "" );
//			assertTrue(d.getName() != "" );
//		}
	}
	
	
//	//Checking if all attributes are set
//	@Test
//	public void distrubutionCentersLoadingTest(){
//		
//		assertEquals(1, 1);
//		System.out.println("test");
//		for (DistributionCentre d : dists){
//			assertTrue(d.getCity() != null );
//			assertTrue(d.getCountry() != null );
//			assertTrue(d.getName() != null );
//			
//		}
//		
//	}
 

}
