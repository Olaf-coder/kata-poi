/**
 * 
 */
package fr.alten.amartin.kata_poi.Domain;

import java.util.Objects;

import fr.alten.amartin.kata_poi.Exceptions.OutOfRangeNumberException;

/**
 * @author Rellique
 *
 */
public final class PointOfInterest {

	private String id;
	private float latitude;
	private float longitude;
	
	public PointOfInterest(String id, float latitude, float longitude) {
		super();
		try {
			setId(id);
			setLatitude(latitude);
			setLongitude(longitude);
		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
		
//		this.id = id;
//		this.latitude = latitude;
//		this.longitude = longitude;
		
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

	public void setId(final String id) throws NullPointerException{
		if (id == null)
			throw new NullPointerException("id is null");
		this.id = id;
	}

	public void setLatitude(final float latitude) throws OutOfRangeNumberException {
		if (latitude > 90 || latitude < -90)
			throw new OutOfRangeNumberException("Incorrect latitude");
		this.latitude = latitude;
	}

	public void setLongitude(final float longitude) throws OutOfRangeNumberException {
		if (longitude > 180 || longitude < -180)
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
		return Objects.equals(id, other.id) && Float.floatToIntBits(latitude) == Float.floatToIntBits(other.latitude)
				&& Float.floatToIntBits(longitude) == Float.floatToIntBits(other.longitude);
	}
	
	
	
	
}
