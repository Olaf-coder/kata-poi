package fr.alten.amartin.kata_poi.exceptions;

import fr.alten.amartin.kata_poi.model.CoordinateConstants;

/**
 * The CoherenceCoordinateException is there to highlight coherence problems linked to coordinates in an vArea Object.
 * 
 * <pre>Example:
 * - The difference between Min and Max Latitude/Longitude of the area is higher/lower than the defined {@link CoordinateConstants}.LIMIT
 * - Minimal coordinate of Area is higher than the Maximal.
 * </pre>
 * 
 * @author AMARTIN
 *
 */
public class CoherenceCoordinateException extends RuntimeException{

	/**
     * Constructs an {@code CoherenceCoordinateException} with no
     * detail message.
     */
	public CoherenceCoordinateException() {
	}

	/**
     * Constructs an {@code CoherenceCoordinateException} with the
     * specified detail message.
     *
     * @param message the detail message.
     */
	public CoherenceCoordinateException(String message) {
		super(message);
	}
}
