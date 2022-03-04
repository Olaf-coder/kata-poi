package fr.alten.amartin.kata_poi.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.alten.amartin.kata_poi.model.Area;

class PoiSearchEngineTest {

	private PoiSearchEngine PoiSE;
	private PoiSearchEngine PoiSENoLine;
	private String poiFilepath;
	private String poiFilepathNoLine;
	private ArrayList<Area> targetAreaList;
	private ArrayList<Area> densiestAreaSortedFullList;
	
	@BeforeEach
	void setUp() throws Exception {
		poiFilepath = "src/test/resources/poi.csv";
		poiFilepathNoLine = "src/test/resources/poiErrorNoLine.csv";
		PoiSE = new PoiSearchEngine(poiFilepath);
		PoiSENoLine = new PoiSearchEngine(poiFilepathNoLine);
		targetAreaList = new ArrayList<Area>();
		targetAreaList.add(new Area((float) -2.5, (float) 38));
		targetAreaList.add(new Area((float) 6.5, (float) -7));
		

		densiestAreaSortedFullList = new ArrayList<Area>();
		densiestAreaSortedFullList.add(new Area((float) -2.5, (float) 38));
		densiestAreaSortedFullList.add(new Area((float) 6.5, (float) -7));
		densiestAreaSortedFullList.add(new Area((float) -3, (float) 38));
		densiestAreaSortedFullList.add(new Area((float) -27.5, (float) 8));
		densiestAreaSortedFullList.add(new Area((float) -49, (float) -38));
		densiestAreaSortedFullList.add(new Area((float) 0, (float) -0.5));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * TEST Success
	 */
	@Test
	void testCalculatePoisInArea() {
		assertEquals(2, PoiSE.calculatePoisInArea(6.5, -7));
	}
	
	@Test
	void testCalculateAreaDensiestList() {
		PoiSE.calculateAreaDensiestList();
	}
	
	@Test
	void testFindNstDensestAreas() {
		assertEquals(targetAreaList, PoiSE.findNstDensestAreas(2));
	}
	
	@Test
	void testFindNstDensestAreasOverLen() {
		assertEquals(densiestAreaSortedFullList, PoiSE.findNstDensestAreas(100));
	}
	
	/**
	 * TEST FAIL
	 */
	@Test
	void testCalculatePoisInAreaFail() {
		assertEquals(0, PoiSENoLine.calculatePoisInArea(6.5, -7));
	}
	
	
	@Test
	void testFindNstDensestAreasFail() {
		assertEquals(densiestAreaSortedFullList, PoiSE.findNstDensestAreas(-100));
	}
}
