package fr.alten.amartin.kata_poi.model;

/**
 * <p>CoordinateConstants represent the maximal and minimal limit of the MAP where you can find the POI.</p>
 * <p>Note for the next version of the library: use a property file  and verify if MAX/MIN_LAT/LON % UNIT == 0.</p>
 *  
 * @author Rellique
 *
 */
public final class CoordinateConstants {

	public static final float MAX_LAT = 90;
	public static final float MIN_LAT = -90;
	public static final float MAX_LONG = 180;
	public static final float MIN_LONG = -180;
	public static final float UNIT = 0.5f;
	
	private CoordinateConstants() {
		
	}

}
