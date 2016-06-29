package com.cassol.geo.model;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class GeoPolygonTest {

	@Test
	public void testGetCenter() {
		GeoPolygon polygon = build_a_polygon();
		GeoPoint centerid = polygon.getCenter();
		System.out.println(centerid);
		assertEquals(new GeoPoint(-23.2286058, -45.9381834), centerid);
	}

	@Test
	public void calculateTheFarthestPointFromCenter() {
		GeoPolygon polygon = new GeoPolygon();
		GeoPoint point5 = new GeoPoint(-23.225312, -45.941732);
		
		polygon.addVertex(new GeoPoint(-23.227148, -45.937022))
			   .addVertex(new GeoPoint(-23.228578, -45.933326))
			   .addVertex(new GeoPoint(-23.231796, -45.936765))
			   .addVertex(new GeoPoint(-23.230195, -45.942072))
			   .addVertex(point5)
			   .build();
		
		GeoPoint farthestPoint = polygon.farthestPointFromCenter();
		System.out.println(farthestPoint);
		assertEquals(point5, farthestPoint);
	}
	
	@Test
	public void calculateTheFarthestPointFromCenterInMeters() {
		GeoPolygon polygon = build_a_polygon();
		Float meters = polygon.farthestPointFromCenterInMeters();
		System.out.println(meters);
		assertEquals(new Float(515.9649), meters);
	}
	
	@Test
	public void calculateIfThePointIsInsideThePolygon() {
		GeoPolygon polygon = build_a_polygon();
		Assert.assertTrue(polygon.contains(new GeoPoint(-23.230485, -45.937856)));
	}

	@Test
	public void calculateIfThePointIsOutsideThePolygon() {
		GeoPolygon polygon = build_a_polygon();
		Assert.assertFalse(polygon.contains(new GeoPoint(-24.230485, -45.937856)));
	}

	@Test
	public void calculateIfThePointIsInsideTheConvexPolygon() {
		GeoPolygon polygon = new GeoPolygon()
				.addVertex(new GeoPoint(2,3))
			    .addVertex(new GeoPoint(4, 1))
			    .addVertex(new GeoPoint(4, 3))
			    .addVertex(new GeoPoint(6,3))
			    .addVertex(new GeoPoint(6,1))
			    .addVertex(new GeoPoint(7,1))
			    .addVertex(new GeoPoint(7,5))
			    .addVertex(new GeoPoint(3,5))
			    .build();
		
		Assert.assertFalse(polygon.contains(new GeoPoint(5,2)));
	}

	private GeoPolygon build_a_polygon() {
		return new GeoPolygon()
				.addVertex(new GeoPoint(-23.227148, -45.937022))
			    .addVertex(new GeoPoint(-23.228578, -45.933326))
			    .addVertex(new GeoPoint(-23.231796, -45.936765))
			    .addVertex(new GeoPoint(-23.230195, -45.942072))
			    .addVertex(new GeoPoint(-23.225312, -45.941732))
			    .build();
	}

}
