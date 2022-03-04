package fr.alten.amartin.kata_poi.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.alten.amartin.kata_poi.exceptions.CoherenceCoordinateException;
import fr.alten.amartin.kata_poi.exceptions.OutOfRangeNumberException;
import fr.alten.amartin.kata_poi.model.Area;

class AreaTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAreaFullConstructNormalSuccess() {
		Area a = new Area(50, 60.5f, 50.5f, 61);
		assertAll("should return a correct area with the 4 coordinates in constructor",
				() -> assertEquals(50, a.getMinLat()),
				() -> assertEquals(60.5f, a.getMinLon()),
				() -> assertEquals(50.5f, a.getMaxLat()),
				() -> assertEquals(61, a.getMaxLon()),
				() -> assertEquals("Area [minLat=50.0, minLon=60.5, maxLat=50.5, maxLon=61.0]", a.toString())
				);
	}

	@Test
	void testAreaFullConstructMinSuccess() {
		Area a = new Area(-90, -180, -89.5f, -179.5f);
		assertAll("should return a correct area with the 4 coordinates in constructor with smallest values possible",
				() -> assertEquals(-90,a.getMinLat()),
				() -> assertEquals(-180, a.getMinLon()),
				() -> assertEquals(-89.5f, a.getMaxLat()),
				() -> assertEquals(-179.5f, a.getMaxLon())
				);
	}
	
	@Test
	void testAreaFullConstructMaxSuccess() {
		Area a = new Area(89.5f, 179.5f, 90, 180);
		assertAll("should return a correct area with the 4 coordinates in constructor with biggest values possible",
				() -> assertEquals(89.5f,a.getMinLat()),
				() -> assertEquals(179.5f, a.getMinLon()),
				() -> assertEquals(90, a.getMaxLat()),
				() -> assertEquals(180, a.getMaxLon())
				);		
	}
	
	@Test
	void testAreaHalfParamsConstructNormalSuccess() {
		Area a = new Area(50, 60.5f);
		assertAll("should return a correct area with the 4 coordinates in constructor",
				() -> assertEquals(50, a.getMinLat()),
				() -> assertEquals(60.5f, a.getMinLon()),
				() -> assertEquals(50.5f, a.getMaxLat()),
				() -> assertEquals(61, a.getMaxLon())
				);
	}
	
	@Test
	void testAreaHalfParamsConstructFail() {
		Exception e = Assertions.assertThrows(IllegalArgumentException.class, ()->{
			Area a = new Area(50, 999);
		});
		assertEquals("The value is out of Coordinate limit", e.getMessage());
	}
	
	@Test
	void testAreaFullConstructUnitError() {
		Exception e = Assertions.assertThrows(IllegalArgumentException.class, ()->{
			Area a = new Area(89.4f, 179.4f, 90, 180);
		});
		assertEquals("The value is not an increment of the UNIT", e.getMessage());
	}
	
	@Test
	void testAreaFullConstructRangeError() {
		Exception e1 = Assertions.assertThrows(IllegalArgumentException.class, ()->{
			Area a1 = new Area(-90, -180, 90, -179.5f);
		});

		Exception e2 = Assertions.assertThrows(IllegalArgumentException.class, ()->{
			Area a2 = new Area(-90, -180, -89.5f, 180);
		});
		assertEquals("The difference between maxLatitude and minLatitude is bigger than the UNIT", e1.getMessage());
		assertEquals("The difference between maxLongitude and minLongitude is bigger than the UNIT", e2.getMessage());
	}
	
	@Test
	void testAreaFullConstructCoherenceError() {
		Exception e1 = Assertions.assertThrows(IllegalArgumentException.class, ()->{
			Area a1 = new Area(5, 100, 4.5f, 100.5f);
		});

		Exception e2 = Assertions.assertThrows(IllegalArgumentException.class, ()->{
			Area a2 = new Area(5, 100, 5.5f, 99.5f);
		});
		assertEquals("MaxLatitude lower than MinLatitude", e1.getMessage());
		assertEquals("MaxLongitude lower than MinLongitude", e2.getMessage());
	}
	
	@Test
	void testEqualsAndHashcodeObject() {
		Area a1 = new Area(89.5f, 179.5f, 90, 180);
		Area a2 = new Area(89.5f, 179.5f, 90, 180);
		Area a3 = new Area(5, 10, 5.5f, 10.5f);
		
		assertAll("Test if equals possibles results are correct", 
				() -> assertTrue(a1.equals(a1) && a1.equals(a2) && a2.equals(a1)),
				() -> assertFalse(a1.equals(null) || a1.equals("HELLO WORLD!") || a1.equals(a3)),
				() -> assertEquals(a1.hashCode(), a2.hashCode())
				);
	}

	
}
