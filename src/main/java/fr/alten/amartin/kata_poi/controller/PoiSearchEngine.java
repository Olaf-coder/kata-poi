package fr.alten.amartin.kata_poi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import fr.alten.amartin.kata_poi.exceptions.IllegalFormatLineException;
import fr.alten.amartin.kata_poi.model.Area;
import fr.alten.amartin.kata_poi.model.CoordinateConstants;
import fr.alten.amartin.kata_poi.model.PointOfInterest;

public class PoiSearchEngine {

	private List<PointOfInterest> poiListFromFile;
	private List<Area> denseAreaDescList;
	private LinkedHashMap<Area, Integer> areaWithNbPoiDescMap;
	

	public PoiSearchEngine(final Object poiFilePath) throws IOException, IllegalFormatLineException {
		poiListFromFile = PoiFileConvertor.getInstance().createPOIArrayFromFile(poiFilePath);
		calculateAreaDensiestList();
		
	}
	
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
	
	public int calculatePoisInArea(final double minLat, final double minLon) {
		return calculatePoisInArea((float)minLat, (float)minLon);
	}

	public void calculateAreaDensiestList() {
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
		
		denseAreaDescList = new ArrayList<Area>(areaWithNbPoiDescMap.keySet());
		denseAreaDescList.forEach(t -> System.out.println(t));
	}
	
	public List<Area> findNstDensestAreas(final int nAreas) {
		int size = nAreas > denseAreaDescList.size() || nAreas < 0 ? denseAreaDescList.size() : nAreas;
		return denseAreaDescList.subList(0, size);
	}
}
