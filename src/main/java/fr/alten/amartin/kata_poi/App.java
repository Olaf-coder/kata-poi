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
//    	PointOfInterest poi2 = new PointOfInterest(null, 100, 800);
//    	PointOfInterest poi3 = new PointOfInterest("holliday", 100, 300);
        
//        System.out.println("poi1.equals(poi2): " + poi1.equals(poi2));
//        System.out.println("poi2.equals(poi1) : " + poi2.equals(poi1));
//        System.out.println("poi1.equals(poi1): " + poi1.equals(poi1));
//        System.out.println("poi2.equals(poi2) : " + poi2.equals(poi2));

        System.out.println(poi1.toString());
        System.out.println(a1.toString());
//        System.out.println(poi2.toString());
//        System.out.println(poi3.toString());

        ArrayList<String> mylist = new ArrayList<String>();
        
//        HashMap<Integer, String> mylist = new HashMap<Integer, String>();
//        mylist.entrySet().stream().filter
    }
}
