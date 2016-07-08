package com.cassol.geo.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class GeoPointTest {
	

	@Test
	public void calculateDistanteBetweenPoints() {
		Float distance = new GeoPoint(-23.227148, -45.937022).distance(new GeoPoint(-23.228578, -45.933326));
		assertEquals(new Float(410.23218), distance);
	}

	@Test
	public void testTranslateCoordinatesZeroDegrees100Meters() {
		testTransalte(0,100);
	}

	@Test
	public void testTranslateCoordinates90Degrees100Meters() {
		testTransalte(90,100);
	}

	@Test
	public void testTranslateCoordinates180Degrees100Meters() {
		testTransalte(180,100);
	}
	
	@Test
	public void testTranslateCoordinates270Degrees100Meters() {
		testTransalte(270,100);
	}

	private void testTransalte(double angle, int distance) {
		GeoPoint geoPoint = new GeoPoint(-23.214254, -45.935765);
		GeoPoint translatedPoint1 = geoPoint.translateCoordinates(angle, distance);
		System.out.println(geoPoint + "  -->  " + translatedPoint1);
		float distance1 = geoPoint.distance(translatedPoint1);
		assertTrue(distance1 > 90);
		assertTrue(distance1 < 110);
	}
	
	
	@Test
	public void testQuadrant() {
		GeoPoint geoPoint = new GeoPoint(-23.214254, -45.935765);
		List<GeoPoint> quadrant = geoPoint.quadrant(100);
		Assert.assertEquals(4,quadrant.size());
	}
	

}
