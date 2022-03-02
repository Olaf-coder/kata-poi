package fr.alten.amartin.kata_poi.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.alten.amartin.kata_poi.model.CoordinateConstants;

class CoordinateConstantsTest {
	@Test
	void testAllConstValues() {
		assertAll(
				() -> assertEquals( CoordinateConstants.MAX_LAT, 90),
				() -> assertEquals( CoordinateConstants.MIN_LAT, -90),
				() -> assertEquals( CoordinateConstants.MAX_LONG, 180),
				() -> assertEquals( CoordinateConstants.MIN_LONG, -180),
				() -> assertEquals( CoordinateConstants.UNIT, 0.5)
				);
	}

}
