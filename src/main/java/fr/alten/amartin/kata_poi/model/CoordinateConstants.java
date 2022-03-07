package fr.alten.amartin.kata_poi.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import fr.alten.amartin.kata_poi.controller.PoiFileConvertor;

/**
 * <pre>CoordinateConstants represent the maximal and minimal limit of the MAP where you can find the POI.
 * In the current version, you can create the property file PoiMapConfig.properties in order to define the limit of the MAP
 * The file must be a Java resource in order to be considered by the library.
 * If there is not PoiMapConfig.properties the values will be :
 * minLat=-90
 * maxLat=90
 * minLon=-180
 * maxLon=180
 * unit=0.5
 * </pre>
 *  
 * @author AMARTIN
 *
 */
public final class CoordinateConstants {

	private static CoordinateConstants instance;
	
	public float minLat = -90;
	public float maxLat = 90;
	public float minLon = -180;
	public float maxLon = 180;
	public float unit = 0.5f;
	
	private CoordinateConstants() {
		String result = "";
    	InputStream inputStream = null;
    	Properties prop = new Properties();
    	String propFileName = "PoiMapConfig.properties";

		inputStream = ClassLoader.getSystemResourceAsStream(propFileName);
		
		try {
			if (inputStream != null) {
				prop.load(inputStream);
				minLat = Float.parseFloat(prop.getProperty("MinLatitude"));
				maxLat = Float.parseFloat(prop.getProperty("MaxLatitude"));
				minLon = Float.parseFloat(prop.getProperty("MinLongitude"));
				maxLon = Float.parseFloat(prop.getProperty("MaxLongitude"));
				unit = Float.parseFloat(prop.getProperty("Unit"));
				inputStream.close();
			}
		} catch (Exception e) {
		}
		
		
	}
	
	/**
	 * Return an instance of CoordinateConstants. PoiMapConfig.properties must be a Java resources, or else the default value of the map will be:
	 * minLat=-90
	 * maxLat=90
	 * minLon=-180
	 * maxLon=180
	 * unit=0.5
	 * @return an instance of {@link CoordinateConstants}
	 */
	public static CoordinateConstants getInstance() {
		if (instance == null) {
			synchronized (CoordinateConstants.class) {
				if(instance == null) {
					instance = new CoordinateConstants();
				}
			}
		}
		return instance;
	}

}
