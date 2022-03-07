package fr.alten.amartin.kata_poi.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import fr.alten.amartin.kata_poi.exceptions.IllegalFormatLineException;
import fr.alten.amartin.kata_poi.exceptions.OutOfRangeNumberException;
import fr.alten.amartin.kata_poi.model.PointOfInterest;

/**
 * Singleton that enable to convert a file that contains points of interest into an usable list of {@link PointOfInterest} object for your program 
 * 
 * {@link #getInstance()}
 * {@link #createPOIArrayFromFile(Object)}
 * @author AMARTIN
 *
 */
public class PoiFileConvertor {

	private static PoiFileConvertor instance;
	
	static final String[] POI_HEADER = {"@id", "@lat", "@lon"};
	
	private PoiFileConvertor() {
		
	}
	
	/**
	 * Get or initialize the instance of the Singleton PoiFileConvertor 
	 * @return
	 */
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
	
	/**
	 * Verify the integrity of the header in the file. Just there to raise an exception early and avoid to read the rest of the file
	 * 
	 * @param firstLine is the expected header of the poi file. for now: "@id @lat @lon"
	 * @throws NullPointerException is the String line is null
	 * @throws IllegalFormatLineException if the header is not exactly "@id @lat @lon"
	 */
	public void verifyFirstLine(final String firstLine) throws NullPointerException, IllegalFormatLineException{
		if (firstLine == null)
			throw new NullPointerException();
		String[] fLSplited = firstLine.split(" ");
		
		if (!(Arrays.equals(POI_HEADER, fLSplited))) {
			throw new IllegalFormatLineException();
		}
	}
	
	/**
	 * Create a Poi object from a string. Can also be use to simply verify the integrity of the line
	 * 
	 * @param poiLine is the String extracted from the PoiFile 
	 * @return a new {@link PointOfInterest} object
	 * @throws IllegalFormatLineException if the line has not a correct format: not 3 elements, lat or lon are not number
	 * @throws NullPointerException if the String line is null 
	 * @throws OutOfRangeNumberException when creates the new PointOfInterest object
	 */
	public PointOfInterest createPoiFromLine(final String poiLine) throws IllegalFormatLineException, NullPointerException, OutOfRangeNumberException {
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
	
	/**
	 * Verifies the integrity of the given file in parameters. Deprecated 
	 * @see #createPOIArrayFromFile(Object) instead
	 * @deprecated 
	 * @param poiFilePath poiFile (path String or directly a File object) is the file that contains the list of Poi.
	 * @throws IOException if BufferedReader has a problem when in closing phase.
	 * @throws IllegalFormatLineException if the file object is null or not a File nor a String path
	 */
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
				createPoiFromLine(line);
			}
		}
		fileReader.close();
	}
	
	
	/**
	 * Create an ArrayList<PointOfInterest> in order to be treated by PoiSearchEngine or your program
	 * It also verifies the integrity of the given file in parameters 
	 * 
	 * @param poiFile (path String or directly a File object) is the file that contains the list of Poi.
	 * @return the ArrayList<PointOfInterest> in order to be treated in your program
	 * @throws IOException if BufferedReader has a problem when in closing phase.
	 * @throws IllegalFormatLineException if the file object is null or not a File nor a String path
	 */
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
				poiList.add(createPoiFromLine(line));
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
