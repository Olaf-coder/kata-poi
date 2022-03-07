package fr.alten.amartin.kata_poi.model;

import java.util.Objects;

import fr.alten.amartin.kata_poi.exceptions.CoherenceCoordinateException;
import fr.alten.amartin.kata_poi.exceptions.OutOfRangeNumberException;

/**
 * Represent an Area (with 4 coordinates: max/min latitude/longitude)
 * 
 * @author AMARTIN
 */
public final class Area {

	private float minLat;
	private float maxLat;
	private float minLon;
	private float maxLon;
	private CoordinateConstants cc = CoordinateConstants.getInstance();
	
	/**
	 * Create the area with minimal and maximal limits for longitude and latitude in argument
	 * 
	 * @param minLat
	 * @param minLon
	 * @param maxLat
	 * @param maxLon
	 * @throws OutOfRangeNumberException if values of minLat, minLon, maxLat or maxLon exceed limits
	 * @throws CoherenceCoordinateException if minLat, minLon, maxLat or maxLon are not an increment of UNIT
	 */
	public Area(float minLat, float minLon, float maxLat, float maxLon) throws OutOfRangeNumberException, CoherenceCoordinateException{
		super();
		setMinLat(minLat);
		setMinLon(minLon);
		setMaxLat(maxLat);
		setMaxLon(maxLon);
		
		float diffLat = Math.abs(this.minLat - this.maxLat);
		float diffLon = Math.abs(this.minLon - this.maxLon);
		
		if (diffLat != cc.unit)
			throw new CoherenceCoordinateException("The difference between maxLatitude and minLatitude is bigger than the UNIT");
		if (diffLon != cc.unit)
			throw new CoherenceCoordinateException("The difference between maxLongitude and minLongitude is bigger than the UNIT");
		if (this.minLon >= this.maxLon)
			throw new CoherenceCoordinateException("MaxLongitude lower than MinLongitude");
		if (this.minLat >= this.maxLat)
			throw new CoherenceCoordinateException("MaxLatitude lower than MinLatitude");
	}
	
	/**
	 * Create the area with only minimal limits in argument (limit exception thanks to internal calculation)
	 * 
	 * @param minLat
	 * @param minLon
	 * @throws OutOfRangeNumberException if values of minLat or minLon exceed limits
	 * @throws CoherenceCoordinateException if minLat or minLon are not an increment of UNIT
	 */
	public Area(float minLat, float minLon) throws OutOfRangeNumberException, CoherenceCoordinateException{
		super();
		setMinLat(minLat);
		setMinLon(minLon);
		setMaxLat(minLat + cc.unit);
		setMaxLon(minLon + cc.unit);	
	}
	
	/**
	 * Get the minimal limit of Latitude
	 * @return minLat
	 */
	public float getMinLat() {
		return minLat;
	}
	
	/**
	 * Get the minimal limit of Longitude
	 * @return minLon
	 */
	public float getMinLon() {
		return minLon;
	}
	
	/**
	 * Get the maximal limit of Latitude
	 * @return maxLat
	 */
	public float getMaxLat() {
		return maxLat;
	}

	/**
	 * Get the maximal limit of Longitude
	 * @return maxLon
	 */
	public float getMaxLon() {
		return maxLon;
	}

	/**
	 * Set the Maximal Latitude limit of the area
	 * 
	 * @param minLat
	 * @throws OutOfRangeNumberException if minLat exceed the {@link CoordinateConstants} MIN_LAT or (MAX_LAT - UNIT) limits.
	 * @throws CoherenceCoordinateException if minLat is not an increment of {@link CoordinateConstants}.UNIT
	 */
	public void setMinLat(float minLat) throws OutOfRangeNumberException, CoherenceCoordinateException{
		if (minLat < cc.minLat || minLat > (cc.maxLat - cc.unit))
			throw new OutOfRangeNumberException("The value is out of Coordinate limit");
		if (minLat % cc.unit != 0)
			throw new CoherenceCoordinateException("The value is not an increment of the UNIT");
		this.minLat = minLat;
	}
	
	/**
	 * Set the Minimal Longitude limit of the area
	 * 
	 * @param minLon
	 * @throws OutOfRangeNumberException if minLon exceed the {@link CoordinateConstants} MIN_LONG or (MAX_LONG - UNIT) limits.
	 * @throws CoherenceCoordinateException if minLon is not an increment of {@link CoordinateConstants}.UNIT
	 */
	public void setMinLon(float minLon) throws OutOfRangeNumberException, CoherenceCoordinateException{
		if (minLon < cc.minLon || minLon > (cc.maxLon - cc.unit))
			throw new OutOfRangeNumberException("The value is out of Coordinate limit");
		if (minLon % cc.unit != 0)
			throw new CoherenceCoordinateException("The value is not an increment of the UNIT");
		this.minLon = minLon;
	}

	/**
	 * Set the Maximal Latitude limit of the area
	 * 
	 * @param maxLat
	 * @throws OutOfRangeNumberException if maxLat exceed the {@link CoordinateConstants} (MIN_LAT + UNIT) or MAX_LAT limits.
	 * @throws CoherenceCoordinateException if maxLat is not an increment of {@link CoordinateConstants}.UNIT
	 */
	public void setMaxLat(float maxLat) throws OutOfRangeNumberException, CoherenceCoordinateException{
		if (maxLat < (cc.minLat + cc.unit) || maxLat > cc.maxLat)
			throw new OutOfRangeNumberException("The value is out of Coordinate limit");
		if (maxLat % cc.unit != 0)
			throw new CoherenceCoordinateException("The value is not an increment of the UNIT");
		this.maxLat = maxLat;
	}

	/**
	 * Set the Maximal Longitude limit of the area
	 * 
	 * @param maxLon
	 * @throws OutOfRangeNumberException if maxLon exceed the {@link CoordinateConstants} (MIN_LONG + UNIT) or MAX_LONG limits.
	 * @throws CoherenceCoordinateException if maxLon is not an increment of {@link CoordinateConstants}.UNIT
	 */
	public void setMaxLon(float maxLon) throws OutOfRangeNumberException, CoherenceCoordinateException{
		if ((maxLon < (cc.minLon + cc.unit) || maxLon > cc.maxLon))
			throw new OutOfRangeNumberException("The value is out of Coordinate limit");
		if (maxLon % cc.unit != 0)
			throw new CoherenceCoordinateException("The value is not an increment of the UNIT");
		this.maxLon = maxLon;
	}

	
	
	@Override
	public String toString() {
		return "Area [minLat=" + minLat + ", minLon=" + minLon + ", maxLat=" + maxLat + ", maxLon=" + maxLon + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maxLat, maxLon, minLat, minLon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Area other = (Area) obj;
		return Float.floatToIntBits(maxLat) == Float.floatToIntBits(other.maxLat)
				&& Float.floatToIntBits(maxLon) == Float.floatToIntBits(other.maxLon)
				&& Float.floatToIntBits(minLat) == Float.floatToIntBits(other.minLat)
				&& Float.floatToIntBits(minLon) == Float.floatToIntBits(other.minLon);
	}

}
