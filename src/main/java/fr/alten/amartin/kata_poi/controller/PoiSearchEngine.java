package fr.alten.amartin.kata_poi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.alten.amartin.kata_poi.exceptions.IllegalFormatLineException;
import fr.alten.amartin.kata_poi.model.Area;
import fr.alten.amartin.kata_poi.model.CoordinateConstants;
import fr.alten.amartin.kata_poi.model.PointOfInterest;

/**
 * Serves to search Point Of Interest in a File
 * This is the main Object of the package.
 * <pre>
 * {@link #calculatePoisInArea(Area)}
 * {@link #calculatePoisInArea(float, float)}
 * {@link #calculatePoisInArea(double, double)}
 * {@link #findNstDensestAreas(int)}
 * </pre>
 * @author AMARTIN
 *
 */
public class PoiSearchEngine {

	private ArrayList<PointOfInterest> poiListFromFile;
	private ArrayList<Area> denseAreaDescList;
	private LinkedHashMap<Area, Integer> areaWithNbPoiDescMap;
	

	public PoiSearchEngine(final Object poiFilePath) throws IOException, IllegalFormatLineException {
		poiListFromFile = PoiFileConvertor.getInstance().createPOIArrayFromFile(poiFilePath);
		calculateAreaDensiestList();
		
	}

	/**
	 * Calculate the POI number in the given area
	 * 
	 * @param minLat the minimal Latitude of the targeted Area
	 * @param minLon the minimal Longitude of the targeted Area
	 * @return the number of POI detected in the Area
	 */
	public int calculatePoisInArea(final float minLat, final float minLon) {
		Area targetArea = new Area(minLat, minLon);
		float maxLat = targetArea.getMaxLat();
		float maxLon = targetArea.getMaxLon();

		Predicate<PointOfInterest> poiLatInAreaLatRange = poi -> poi.getLatitude() >= minLat && poi.getLatitude() <= maxLat;
		Predicate<PointOfInterest> poiLonInAreaLonRange = poi -> poi.getLongitude() >= minLon && poi.getLongitude() <= maxLon;
		Predicate<PointOfInterest> predicatePoiInArea = poiLatInAreaLatRange.and(poiLonInAreaLonRange);
		
		List<PointOfInterest> listPoi = poiListFromFile.stream()
				.filter(predicatePoiInArea)
				.collect(Collectors.toList());
		return listPoi.size();
	}
	
	/**
	 * Calculate the POI number in the given area
	 * 
	 * @param minLat the minimal Latitude of the targeted Area
	 * @param minLon the minimal Longitude of the targeted Area
	 * @return the number of POI detected in the Area
	 */
	public int calculatePoisInArea(final double minLat, final double minLon) {
		return calculatePoisInArea((float)minLat, (float)minLon);
	}
	
	/**
	 * Calculate the POI number in the given area
	 * 
	 * @param Area the targeted Area in which we must find Poi
	 * @return the number of POI find in the Area
	 */
	public int calculatePoisInArea(Area area) {
		return calculatePoisInArea(area.getMinLat(), area.getMinLon());
	}

	/**
	 * Calculate density for area (with at least 1 POI) and ranked them in decreasing order.
	 * Musn't be called outside PoiSearchEngine
	 */
	private void calculateAreaDensiestList() {
		float iterLat;
		float iterLon;
		Map<Area, Integer> unsortedPoiPerArea = new HashMap<>();
		
		for (iterLat = CoordinateConstants.MIN_LAT; iterLat < CoordinateConstants.MAX_LAT; iterLat += CoordinateConstants.UNIT) {
			for (iterLon = CoordinateConstants.MIN_LONG; iterLon < CoordinateConstants.MAX_LONG; iterLon += CoordinateConstants.UNIT) {
				int result = calculatePoisInArea(iterLat, iterLon);
				if (result > 0)
				{
					unsortedPoiPerArea.put(new Area(iterLat, iterLon), result);
				}
			}	
		}
		
		areaWithNbPoiDescMap = unsortedPoiPerArea.entrySet()
				  .stream()
				  .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				  .collect(Collectors.toMap(
				    Map.Entry::getKey, 
				    Map.Entry::getValue, 
				    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		
		denseAreaDescList = new ArrayList<>(areaWithNbPoiDescMap.keySet());
	}
	
	/**
	 * Get the first Densest Areas.
	 * 
	 * @param nAreas the number of densest areas
	 * @return an List<Area> with nAreas first densest areas
	 */
	public List<Area> findNstDensestAreas(final int nAreas) {
		int size = nAreas > denseAreaDescList.size() || nAreas < 0 ? denseAreaDescList.size() : nAreas;
		return (denseAreaDescList.subList(0, size));
	}
	
	/**
	 * Get the first Densest Areas. in JsonString
	 * 
	 * @param nAreas the number of densest areas
	 * @return a json String that represent the List<Area> with nAreas first densest areas
	 * @throws JsonProcessingException 
	 */
	public String findNstDensestAreasToJson(final int nAreas) throws JsonProcessingException {
		int size = nAreas > denseAreaDescList.size() || nAreas < 0 ? denseAreaDescList.size() : nAreas;
		ObjectMapper mapper = new ObjectMapper();
		
		List<Area> listFilteredDenseAreaDescList = denseAreaDescList.subList(0, size);
		return mapper.writeValueAsString(listFilteredDenseAreaDescList);
	}
}
