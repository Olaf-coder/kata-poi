package fr.alten.amartin.kata_poi.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.alten.amartin.kata_poi.model.CoordinateConstants;

class CoordinateConstantsTest {
	
	/**
	 * TEST : Successes
	 */

	@Test
	void testAllConstValues() {
		assertAll(
				() -> assertEquals(90, CoordinateConstants.getInstance().maxLat),
				() -> assertEquals(-90, CoordinateConstants.getInstance().minLat),
				() -> assertEquals(180, CoordinateConstants.getInstance().maxLon),
				() -> assertEquals(-180, CoordinateConstants.getInstance().minLon),
				() -> assertEquals(  0.5,CoordinateConstants.getInstance().unit)
				);
	}

}
