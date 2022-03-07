/**
 * 
 */
package fr.alten.amartin.kata_poi.model;

import java.util.Objects;

import fr.alten.amartin.kata_poi.exceptions.OutOfRangeNumberException;

/** 
 * Represent a Point of Interest (with a name and a precise coordinate thanks to two value: latitude and longitude
 * 
 * @author AMARTIN
 */
public final class PointOfInterest {

	private String id;
	private float latitude;
	private float longitude;
	
	/**
	 * Create Point Of Interest
	 * 
	 * @param id String
	 * @param latitude float
	 * @param longitude float
	 * @throws IllegalArgumentException in case id is NULL
	 * @throws OutOfRangeNumberException if latitude or longitude exceed MAX/MIN limits
	 */
	public PointOfInterest(String id, float latitude, float longitude) throws IllegalArgumentException, OutOfRangeNumberException {
		super();
		setId(id);
		setLatitude(latitude);
		setLongitude(longitude);
	}
	
	/**
	 * Create Point Of Interest
	 * 
	 * @param id String
	 * @param latitude double
	 * @param longitude double
	 * @throws IllegalArgumentException in case id is NULL
	 * @throws OutOfRangeNumberException if latitude or longitude exceed MAX/MIN limits
	 */
	public PointOfInterest(String id, double latitude, double longitude) throws IllegalArgumentException, OutOfRangeNumberException {
		super();
		setId(id);
		setLatitude((float)latitude);
		setLongitude((float)longitude);
	}

	/**
	 * Get the id of the Point of Interest
	 * 
	 * @return id in String
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Get the latitude value of the Point of Interest
	 * 
	 * @return latitude point in float value
	 */
	public float getLatitude() {
		return latitude;
	}

	/**
	 * Get the longitude value of the Point of Interest
	 * 
	 * @return longitude point in float value
	 */
	public float getLongitude() {
		return longitude;
	}

	/**
	 * Set value of id while verifing it is not null {@link CoordinateConstants}
	 * 
	 * @param id
	 * @throws IllegalArgumentException in case id is NULL
	 */
	public void setId(final String id) throws IllegalArgumentException{
		if (id == null)
			throw new IllegalArgumentException("Id is null");
		this.id = id;
	}

	/**
	 * Set value of latitude while verifying it is not out of map range (see {@link CoordinateConstants} for map ranges)
	 * 
	 * @param latitude
	 * @throws OutOfRangeNumberException if longitude exceed the {@link CoordinateConstants}.[MAX/MIN]_LAT LIMIT
	 */
	public void setLatitude(final float latitude) throws OutOfRangeNumberException{
		if (latitude > CoordinateConstants.MAX_LAT || latitude < CoordinateConstants.MIN_LAT)
			throw new OutOfRangeNumberException("Incorrect latitude");
		this.latitude = latitude;
	}

	/** 
	 * Set value of longitude while verifying it is not out of map range (see {@link CoordinateConstants} for map ranges)
	 * 
	 * @param longitude
	 * @throws OutOfRangeNumberException if longitude exceed the {@link CoordinateConstants}.[MAX/MIN]_LONG LIMIT
	 */
	public void setLongitude(final float longitude)  throws OutOfRangeNumberException{
		if (longitude > CoordinateConstants.MAX_LONG || longitude < CoordinateConstants.MIN_LONG)
			throw new OutOfRangeNumberException("Incorrect longitude");
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "PointOfInterest [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, latitude, longitude);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PointOfInterest other = (PointOfInterest) obj;
		return Objects.equals(id, other.id) 
				&& Float.floatToIntBits(latitude) == Float.floatToIntBits(other.latitude)
				&& Float.floatToIntBits(longitude) == Float.floatToIntBits(other.longitude);
	}
	
	
	
	
}
