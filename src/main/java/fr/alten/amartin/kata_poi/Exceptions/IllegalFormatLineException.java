package fr.alten.amartin.kata_poi.exceptions;

/**
 * The IllegalFormatLineException is there to highlight error linked to PoiOfInterest file reading
 * 
 * <pre>
 * Example
 * - The header line is not correct, coordinates lines has incorrect type or has not the right number of elements
 * </pre>
 * 
 * @author AMARTIN
 *
 */
public class IllegalFormatLineException extends RuntimeException{

	public IllegalFormatLineException() {
	}

	public IllegalFormatLineException(String message) {
		super(message);
	}
}
