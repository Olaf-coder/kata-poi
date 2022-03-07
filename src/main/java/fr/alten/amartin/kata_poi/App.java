package fr.alten.amartin.kata_poi;

import java.io.IOException;
import fr.alten.amartin.kata_poi.controller.PoiSearchEngine;
import fr.alten.amartin.kata_poi.exceptions.IllegalFormatLineException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	try {
			PoiSearchEngine pse = new PoiSearchEngine("src/test/resources/poi.csv");
			String result = pse.findNstDensestAreasToJson(2);
			System.out.println(result);
		} catch (IllegalFormatLineException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
