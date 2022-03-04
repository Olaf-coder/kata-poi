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
				() -> assertEquals(90, CoordinateConstants.MAX_LAT),
				() -> assertEquals(-90, CoordinateConstants.MIN_LAT),
				() -> assertEquals(180, CoordinateConstants.MAX_LONG),
				() -> assertEquals(-180, CoordinateConstants.MIN_LONG),
				() -> assertEquals(  0.5,CoordinateConstants.UNIT)
				);
	}

}
