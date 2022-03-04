/**
 * 
 */
package fr.alten.amartin.kata_poi.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.alten.amartin.kata_poi.exceptions.IllegalFormatLineException;
import fr.alten.amartin.kata_poi.model.Area;
import fr.alten.amartin.kata_poi.model.PointOfInterest;

/**
 * @author Rellique
 *
 */

//TODO Faire les TU avant de DEV
class PoiFileConvertorTest {

//	private final POIFileConvertor pfc = POIFileConvertor.getInstance();
//	private final ArrayList<PointOfInterest> poiListEmpty = new ArrayList<PointOfInterest>();
//	private final ArrayList<PointOfInterest> poiListModel = new ArrayList<PointOfInterest>();


	private PoiFileConvertor pfc = PoiFileConvertor.getInstance();
	private ArrayList<PointOfInterest> poiListModel;
	private String poiFilepath;
	private String poiErrorHeaderFilepath;
	private String poiErrorLineFormatFilepath;
	private String poiErrorLineNumberFilepath;
	private String poiErrorLineEmptyFilepath;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		poiListModel = new ArrayList<PointOfInterest>();
		poiListModel.add(new PointOfInterest("id1", -48.6, -37.7));
		poiListModel.add(new PointOfInterest("id2", -27.1, 8.4));
		poiListModel.add(new PointOfInterest("id3", 6.6, -6.9));
		poiListModel.add(new PointOfInterest("id4", -2.3, 38.3));
		poiListModel.add(new PointOfInterest("id5", 6.8, -6.9));
		poiListModel.add(new PointOfInterest("id6", -2.5, 38.3));
		poiListModel.add(new PointOfInterest("id7", 0.1, -0.1));
		poiListModel.add(new PointOfInterest("id8", -2.1, 38.1));
		poiFilepath = "src/test/resources/poi.csv";
		poiErrorHeaderFilepath = "src/test/resources/poiErrorHeader.csv";
		poiErrorLineFormatFilepath = "src/test/resources/poiErrorLineFormat.csv";
		poiErrorLineNumberFilepath = "src/test/resources/poiErrorLineNumber.csv";
		poiErrorLineEmptyFilepath = "src/test/resources/poiErrorLineEmpty.csv";
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	
	//Successfull Tests
	
	@Test
	void testVerifyFirstLineSuccess() throws IllegalFormatLineException{
		pfc.verifyFirstLine("@id @lat @lon");
	}

	@Test
	void testVerifyPoiLineSuccess() throws IllegalFormatLineException {
		pfc.verifyPoiLine("id1 51 32");
		pfc.verifyPoiLine("1 51 32");
		pfc.verifyPoiLine("id1 51.5 32");
		pfc.verifyPoiLine("id1 51 32.5");
		pfc.verifyPoiLine("id1 -51.5 32");
		pfc.verifyPoiLine("id1 51 -32.5");
	}

//	@Test
//	void testVerifyIntegrityFileFromFilepathSuccess() throws IllegalFormatLineException, IOException{
//		pfc.verifyIntegrityFile(poiFilepath);
//	}
//	
//	
//	@Test
//	void testVerifyIntegrityFileFromFileObjectSuccess() throws IllegalFormatLineException, IOException{
//		pfc.verifyIntegrityFile(new File(poiFilepath));
//	}

	@Test
	void testCreatePOIArrayFromFilePathSuccess() throws IOException, IllegalFormatLineException{
		assertEquals(poiListModel, pfc.createPOIArrayFromFile(poiFilepath));
	}

	@Test
	void testCreatePOIArrayFromFileObjectSuccess() throws IOException, IllegalFormatLineException{
		assertEquals(poiListModel, pfc.createPOIArrayFromFile(poiFilepath));
	}

	//	@Test
	//	void testCreateFileFromPOIArraySuccess() {
	//		
	//	} 
	
	//TODO Error Tests
	@Test
	void testVerifyFirstLineError() {
		Exception e1 = Assertions.assertThrows(NullPointerException.class, ()->{
			pfc.verifyFirstLine(null);
		});

		Exception e2 = Assertions.assertThrows(IllegalFormatLineException.class, ()->{
			pfc.verifyFirstLine("@id @lon");
		});
	}

	@Test
	void testVerifyPoiLineError() {
		Exception e1 = Assertions.assertThrows(NullPointerException.class, ()->{
			pfc.verifyPoiLine(null);
		});

		Exception e2 = Assertions.assertThrows(IllegalFormatLineException.class, ()->{
			pfc.verifyPoiLine("");
		});

		Exception e3 = Assertions.assertThrows(NumberFormatException.class, ()->{
			pfc.verifyPoiLine("1d51 cc 0");
		});
	}

	
	@Test 
	void testVerifyIntegrityFileError() {
		Exception e1 = Assertions.assertThrows(IllegalArgumentException.class, ()->{
			pfc.verifyIntegrityFile(null);
		});
		Exception e2 = Assertions.assertThrows(IllegalArgumentException.class, ()->{
			pfc.verifyIntegrityFile(50);
		});
		Exception e3 = Assertions.assertThrows(IOException.class, ()->{
			pfc.verifyIntegrityFile("");
		});

		Exception e4 = Assertions.assertThrows(IllegalFormatLineException.class, ()->{
			pfc.createPOIArrayFromFile(poiErrorHeaderFilepath);
		});
		
		Exception e5 = Assertions.assertThrows(IllegalFormatLineException.class, ()->{
			pfc.createPOIArrayFromFile(poiErrorLineFormatFilepath);
		});
		

		Exception e6 = Assertions.assertThrows(IllegalFormatLineException.class, ()->{
			pfc.createPOIArrayFromFile(poiErrorLineEmptyFilepath);
		});

		Exception e7 = Assertions.assertThrows(NumberFormatException.class, ()->{
			pfc.createPOIArrayFromFile(poiErrorLineNumberFilepath);
		});
	}
	
	
	@Test
	void testCreatePOIArrayFromFileError() throws IOException, IllegalFormatLineException {
//		Exception e1 = Assertions.assertThrows(IllegalArgumentException.class, ()->{
//			pfc.createPOIArrayFromFile(null);
//		});
		Exception e1 = Assertions.assertThrows(IllegalArgumentException.class, ()->{
			pfc.createPOIArrayFromFile(null);
		});
		Exception e2 = Assertions.assertThrows(IllegalArgumentException.class, ()->{
			pfc.createPOIArrayFromFile(50);
		});
		Exception e3 = Assertions.assertThrows(IOException.class, ()->{
			pfc.createPOIArrayFromFile("");
		});

		Exception e4 = Assertions.assertThrows(IllegalFormatLineException.class, ()->{
			pfc.createPOIArrayFromFile(poiErrorHeaderFilepath);
		});
		
		Exception e5 = Assertions.assertThrows(IllegalFormatLineException.class, ()->{
			pfc.createPOIArrayFromFile(poiErrorLineFormatFilepath);
		});

		Exception e6 = Assertions.assertThrows(IllegalFormatLineException.class, ()->{
			pfc.createPOIArrayFromFile(poiErrorLineEmptyFilepath);
		});
		Exception e7 = Assertions.assertThrows(NumberFormatException.class, ()->{
			pfc.createPOIArrayFromFile(poiErrorLineNumberFilepath);
		});
		//illegalFormatLine in file
		//NumberFormatExcpetion in file
	}
}
