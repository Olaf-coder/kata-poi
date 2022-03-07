package fr.alten.amartin.kata_poi;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fr.alten.amartin.kata_poi.model.Area;
import fr.alten.amartin.kata_poi.model.PointOfInterest;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	PointOfInterest poi1 = new PointOfInterest("Hello", 1, 2);
    	Area a1 = new Area(50, 101);
    	PointOfInterest poi2 = new PointOfInterest("idx", (int)100, 800);
    	PointOfInterest poi3 = new PointOfInterest(null, 100, 800);
    	PointOfInterest poi4 = new PointOfInterest("holliday", 100, 300);
        

    }
}
