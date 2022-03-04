package fr.alten.amartin.kata_poi.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.alten.amartin.kata_poi.model.PointOfInterest;
/**
 * 
 * @author AMARTIN
 *
 */
class PointOfInterestTest {

	/**
	 * TEST : Successes
	 */

	@Test
	public void testPointOfInterestSuccessNormal() {
		PointOfInterest poi = new PointOfInterest("id1", 50, 100);
		assertAll("should return a correct ordinary poi",
		() -> assertEquals("id1", poi.getId()),
		() -> assertEquals(50, poi.getLatitude(), 0.0),
		() -> assertEquals(100, poi.getLongitude(), 0.0),
		() -> assertEquals("PointOfInterest [id=id1, latitude=50.0, longitude=100.0]", poi.toString())
		);
	}
	
	@Test
	public void testPointOfInterestSuccessMin() {
		PointOfInterest poi = new PointOfInterest("id1", -90, -180);
		assertAll("should return a correct poi with minimal coordinate",
				() -> assertEquals("id1", poi.getId()),
				() -> assertEquals(-90, poi.getLatitude(), 0.0),
				() -> assertEquals(-180, poi.getLongitude(), 0.0)
				);
	}
	
	@Test
	public void testPointOfInterestSuccessMax() {
		PointOfInterest poi = new PointOfInterest("id1", 90, 180);
		assertAll("should return a correct poi with Maximal coordinate",
				() -> assertEquals("id1", poi.getId()),
				() -> assertEquals(90, poi.getLatitude(), 0.0),
				() -> assertEquals(180, poi.getLongitude(), 0.0)
				);
	}


	@Test
	public void testEqualsAndHashcodesObject() {
		PointOfInterest poi1 = new PointOfInterest("id1", 38, 51);
		PointOfInterest poi2 = new PointOfInterest("id1", 38, 51);
		PointOfInterest poi3 = new PointOfInterest("id2", 66, 77);
		
		assertAll("Test if equals possibles results are correct", 
				() -> assertTrue(poi1.equals(poi1) && poi1.equals(poi2) && poi2.equals(poi1)),
				() -> assertFalse(poi1.equals(null) || poi1.equals("HELLO WORLD!") || poi1.equals(poi3)),
				() -> assertTrue(poi1.hashCode() == poi2.hashCode())
//				() -> assertFalse(poi1.equals("HELLO WORLD!")), 
//				() -> assertTrue(poi1.equals(poi2) && poi2.equals(poi1)),
//				() -> assertFalse(poi1.equals(poi3))
				);
	}
	
	/**
	 * TEST : Failures
	 */

	@Test
	public void testPointOfInterestFailName() {
		Exception e = Assertions.assertThrows(IllegalArgumentException.class, ()->{
			PointOfInterest poi = new PointOfInterest(null, 90, 180);
		});
		assertEquals("id is null", e.getMessage());
	}
	
	@Test
	public void testPointOfInterestFailMin() {
		Exception e1 = Assertions.assertThrows(IllegalArgumentException.class, ()->{
			PointOfInterest poi1 = new PointOfInterest("id1", -91, 0);
		});
		
		Exception e2 = Assertions.assertThrows(IllegalArgumentException.class, ()->{
			PointOfInterest poi2 = new PointOfInterest("id1", 0, -181);
		});
		assertAll("should check if exception reached when value is bellow minimal requested ",
		() -> assertEquals("Incorrect latitude", e1.getMessage()),
		() -> assertEquals("Incorrect longitude", e2.getMessage())
		);
	}
	
	@Test
	public void testPointOfInterestFailMax() {

		Exception e1 = Assertions.assertThrows(IllegalArgumentException.class, ()->{
			PointOfInterest poi1 = new PointOfInterest("id1", 91, 0);
		});
		
		Exception e2 = Assertions.assertThrows(IllegalArgumentException.class, ()->{
			PointOfInterest poi2 = new PointOfInterest("id1", 0, 181);
		});
		assertAll("should check if exception is raised when value is above maximal requested ",
		() -> assertEquals("Incorrect latitude", e1.getMessage()),
		() -> assertEquals("Incorrect longitude", e2.getMessage())
		);
	}
	
	
	

}
