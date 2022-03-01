package fr.alten.amartin.kata_poi.Domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PointOfInterestTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPointOfInterestSuccessNormal() {
		
		PointOfInterest poi = new PointOfInterest("id1", 50, 100);
		assertEquals("id1", poi.getId());
		assertEquals(50, poi.getLatitude(), 0.0);
		assertEquals(100, poi.getLongitude(), 0.0);
	}
	
	@Test
	public void testPointOfInterestSuccessMin() {
		
		PointOfInterest poi = new PointOfInterest("id1", -90, -180);
		assertEquals("id1", poi.getId());
		assertEquals(-90, poi.getLatitude(), 0.0);
		assertEquals(-180, poi.getLongitude(), 0.0);
	}
	
	@Test
	public void testPointOfInterestSuccessMax() {
		
		PointOfInterest poi = new PointOfInterest("id1", 90, 180);
		assertEquals("id1", poi.getId());
		assertEquals(90, poi.getLatitude(), 0.0);
		assertEquals(180, poi.getLongitude(), 0.0);
	}
	
	@Test
	public void testPointOfInterestFailName( ) {
		
	}
	
	@Test
	public void testPointOfInterestFailMin( ) {
		
	}
	
	@Test
	public void testPointOfInterestFailMax( ) {
		
	}
	
//	@Test
//	public void testPointOfInterestFail() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testEqualsObjectTrue() {
//		fail("Not yet implemented");
//	}
	
	

}
