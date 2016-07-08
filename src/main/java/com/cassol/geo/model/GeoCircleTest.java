package com.cassol.geo.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeoCircleTest {

	@Test
	public void testNorthSouthEastWest() {
		double latitude = -23.214254;
		double longitude = -45.935765;
		GeoCircle geoCircle = new GeoCircle(new GeoPoint(latitude, longitude), 100);

		assertTrue(latitude < geoCircle.north());
		assertTrue(latitude > geoCircle.south());
		
		assertTrue(longitude < geoCircle.east());
		assertTrue(longitude > geoCircle.west());
	}
}
