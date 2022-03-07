package fr.alten.amartin.kata_poi.exceptions;

import fr.alten.amartin.kata_poi.model.CoordinateConstants;

/***
 * The OutOfRangeNumberException is there to expresse when a value exceed the {@link CoordinateConstants}[MAX/MIN]_[LAT/LONG]
 * 
 * @author AMARTIN
 *
 */
public class OutOfRangeNumberException extends RuntimeException {

	public OutOfRangeNumberException() {
	}

	public OutOfRangeNumberException(String message) {
		super(message); 
	}


}
