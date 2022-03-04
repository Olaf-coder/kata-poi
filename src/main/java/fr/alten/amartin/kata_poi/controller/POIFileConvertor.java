package fr.alten.amartin.kata_poi.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import fr.alten.amartin.kata_poi.exceptions.IllegalFormatLineException;
import fr.alten.amartin.kata_poi.model.PointOfInterest;

public class PoiFileConvertor {

	private static PoiFileConvertor instance;
	
	static final String[] POI_HEADER = {"@id", "@lat", "@lon"};
	
	private PoiFileConvertor() {
		
	}
	
	public static PoiFileConvertor getInstance() {
		if (instance == null) {
			synchronized (PoiFileConvertor.class) {
				if(instance == null) {
					instance = new PoiFileConvertor();
				}
			}
		}
		return instance;
	}
	
	public void verifyFirstLine(final String firstLine) throws IllegalFormatLineException {
		if (firstLine == null)
			throw new NullPointerException();
		String[] fLSplited = firstLine.split(" ");
		
		if (!(Arrays.equals(POI_HEADER, fLSplited))) {
			throw new IllegalFormatLineException();
		}
	};
	
	public PointOfInterest verifyPoiLine(final String poiLine) throws IllegalFormatLineException, NumberFormatException {
		if (poiLine == null)
			throw new NullPointerException();
		String[] pLSplited = poiLine.split(" ");
		if (pLSplited.length != 3)
			throw new IllegalFormatLineException();
		String id = pLSplited[0];
		float lat = Float.parseFloat(pLSplited[1]);
		float lon = Float.parseFloat(pLSplited[2]);
		return new PointOfInterest(id, lat, lon);
	}
	
	public void verifyIntegrityFile(final Object poiFilePath) throws IllegalFormatLineException, IOException {
		FileReader fileReader = null;
		String line;
		String incorrectType;

		if (poiFilePath instanceof File)
			fileReader = new FileReader((File)poiFilePath);
		else if (poiFilePath instanceof String)
			fileReader = new FileReader((String)poiFilePath);
		else {
			if (poiFilePath == null)
				incorrectType = "Null";
			else
				incorrectType = poiFilePath.getClass().getSimpleName();
			throw new IllegalArgumentException(incorrectType + " instead of File or String");
		}
		try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
			verifyFirstLine(bufferedReader.readLine());
			while ((line = bufferedReader.readLine()) != null) {
				verifyPoiLine(line);
			}
		}
		fileReader.close();
	}
	
	public ArrayList<PointOfInterest> createPOIArrayFromFile(final Object poiFile) throws IOException, IllegalFormatLineException {
		
		ArrayList<PointOfInterest> poiList = new ArrayList<>();
		FileReader fileReader = null;
		String line;
		String incorrectType;

		if (poiFile instanceof File)
			fileReader = new FileReader((File)poiFile);
		else if (poiFile instanceof String)
			fileReader = new FileReader((String)poiFile);
		else {
			if (poiFile == null)
				incorrectType = "Null";
			else
				incorrectType = poiFile.getClass().getSimpleName();
			throw new IllegalArgumentException(incorrectType + " instead of File or String");
		}
		try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
			verifyFirstLine(bufferedReader.readLine());
			while ((line = bufferedReader.readLine()) != null)
				poiList.add(verifyPoiLine(line));
		}
		fileReader.close();
		return poiList;
	}
	
	
	
//	public void createFileFromPOIArray(final ArrayList<PointOfInterest> poiList) {
//		
//	}

	// // BillPugh implementation, non thread-safe?
//	private POIFileConvertor() {
//		// TODO Auto-generated constructor stub
//	}
//	
//	// Inner class to provide instance of class
//	private static class InnerSingleton {
//		private static final POIFileConvertor INSTANCE = new POIFileConvertor();
//	}
//	 
//	public static POIFileConvertor getInstance() {
//		return InnerSingleton.INSTANCE;
//	}
}
