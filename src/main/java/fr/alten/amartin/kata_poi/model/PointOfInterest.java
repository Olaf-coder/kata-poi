/**
 * 
 */
package fr.alten.amartin.kata_poi.model;

import java.util.Objects;

import fr.alten.amartin.kata_poi.exceptions.OutOfRangeNumberException;

/** Represent a Point of Interest (with a name and a precise coordinate (latitude and longitude)
 * @author AMARTIN
 *
 */
public final class PointOfInterest {

	private String id;
	private float latitude;
	private float longitude;
	
	/**
	 * 
	 * @param id
	 * @param latitude
	 * @param longitude
	 */
	public PointOfInterest(String id, float latitude, float longitude) {
		super();
		try {
			setId(id);
			setLatitude(latitude);
			setLongitude(longitude);
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}	
	}
	
	public PointOfInterest(String id, double latitude, double longitude) {
		super();
		try {
			setId(id);
			setLatitude((float)latitude);
			setLongitude((float)longitude);
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		
	}

	public String getId() {
		return id;
	}
	
	public float getLatitude() {
		return latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	/**
	 * Set value of latitude while verifing it is not of map range {@link CoordinateConstants}
	 * @param id
	 * @throws NullPointerException
	 */
	public void setId(final String id) throws NullPointerException{
		if (id == null)
			throw new NullPointerException("id is null");
		this.id = id;
	}

	/**
	 * Set value of latitude while verifying it is not of map range (see {@link CoordinateConstants} for map ranges)
	 * @param latitude
	 * @throws OutOfRangeNumberException
	 */
	public void setLatitude(final float latitude) throws OutOfRangeNumberException {
		if (latitude > CoordinateConstants.MAX_LAT || latitude < CoordinateConstants.MIN_LAT)
			throw new OutOfRangeNumberException("Incorrect latitude");
		this.latitude = latitude;
	}

	/** 
	 * Set value of longitude while verifying it is not of map range (see {@link CoordinateConstants} for map ranges)
	 * @param longitude
	 * @throws OutOfRangeNumberException
	 */
	public void setLongitude(final float longitude) throws OutOfRangeNumberException {
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
