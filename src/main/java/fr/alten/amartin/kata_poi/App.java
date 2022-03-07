package fr.alten.amartin.kata_poi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import fr.alten.amartin.kata_poi.controller.PoiSearchEngine;
import fr.alten.amartin.kata_poi.exceptions.IllegalFormatLineException;
import fr.alten.amartin.kata_poi.model.CoordinateConstants;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
		
    	try {
			PoiSearchEngine pse = new PoiSearchEngine("src/test/resources/poi.csv");
			String result = pse.findNstDensestAreasToJson(2);
			System.out.println(result);
		} catch (IllegalFormatLineException | IOException e) {
			e.printStackTrace();
		}
    	
    }
}
