package fr.alten.amartin.kata_poi.model;

import java.util.Objects;

import fr.alten.amartin.kata_poi.exceptions.CoherenceCoordinateException;
import fr.alten.amartin.kata_poi.exceptions.OutOfRangeNumberException;

/** Represent an Area (with 4 coordinates: max/min latitude/longitude)
 * 
 * @author Rellique
 *
 */
public final class Area {

	private float minLat;
	private float minLon;
	private float maxLat;
	private float maxLon;
	
	public Area(float minLat, float minLon, float maxLat, float maxLon) {
		super();
		try {
			setMinLat(minLat);
			setMinLon(minLon);
			setMaxLat(maxLat);
			setMaxLon(maxLon);
			
			float diffLat = Math.abs(this.minLat - this.maxLat);
			float diffLon = Math.abs(this.minLon - this.maxLon);
			
			if (diffLat != CoordinateConstants.UNIT)
				throw new CoherenceCoordinateException("The difference between maxLatitude and minLatitude is bigger than the UNIT");
			if (diffLon != CoordinateConstants.UNIT)
				throw new CoherenceCoordinateException("The difference between maxLongitude and minLongitude is bigger than the UNIT");
			if (this.minLon >= this.maxLon)
				throw new CoherenceCoordinateException("MaxLongitude lower than MinLongitude");
			if (this.minLat >= this.maxLat)
				throw new CoherenceCoordinateException("MaxLatitude lower than MinLatitude");
		} catch (Exception e) {
//			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	public Area(float minLat, float minLon) {
		super();
		try {
			setMinLat(minLat);
			setMinLon(minLon);
			setMaxLat(minLat + CoordinateConstants.UNIT);
			setMaxLon(minLon + CoordinateConstants.UNIT);			
		} catch (Exception e) {
//			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	
	public float getMinLat() {
		return minLat;
	}

	public float getMinLon() {
		return minLon;
	}

	public float getMaxLat() {
		return maxLat;
	}

	public float getMaxLon() {
		return maxLon;
	}

	public void setMinLat(float minLat) throws OutOfRangeNumberException {
		if (minLat < CoordinateConstants.MIN_LAT || minLat > (CoordinateConstants.MAX_LAT - CoordinateConstants.UNIT))
			throw new OutOfRangeNumberException("The value is out of Coordinate limit");
		if (minLat % CoordinateConstants.UNIT != 0)
			throw new IllegalArgumentException("The value is not an increment of the UNIT");
		this.minLat = minLat;
	}
	

	public void setMinLon(float minLon) throws OutOfRangeNumberException {
		if (minLon < CoordinateConstants.MIN_LONG || minLon > (CoordinateConstants.MAX_LONG - CoordinateConstants.UNIT))
			throw new OutOfRangeNumberException("The value is out of Coordinate limit");
		if (minLon % CoordinateConstants.UNIT != 0)
			throw new IllegalArgumentException("The value is not an increment of the UNIT");
		this.minLon = minLon;
	}

	public void setMaxLat(float maxLat) throws OutOfRangeNumberException {
		if (maxLat < (CoordinateConstants.MIN_LAT + CoordinateConstants.UNIT) || maxLat > CoordinateConstants.MAX_LAT)
			throw new OutOfRangeNumberException("The value is out of Coordinate limit");
		if (maxLat % CoordinateConstants.UNIT != 0)
			throw new IllegalArgumentException("The value is not an increment of the UNIT");
		this.maxLat = maxLat;
	}

	public void setMaxLon(float maxLon) throws OutOfRangeNumberException {
		if ((maxLon < (CoordinateConstants.MIN_LONG + CoordinateConstants.UNIT) || maxLon > CoordinateConstants.MAX_LONG))
			throw new OutOfRangeNumberException("The value is out of Coordinate limit");
		if (maxLon % CoordinateConstants.UNIT != 0)
			throw new IllegalArgumentException("The value is not an increment of the UNIT");
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
