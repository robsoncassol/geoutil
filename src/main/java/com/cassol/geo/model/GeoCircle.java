package com.cassol.geo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * The GeoUtil. <br>
 * 
 * @see {@link Builder}
 * @author Robson Cassol (robsoncassol@gmail.com)
 */

public class GeoCircle implements GeoContainer{
	
	private GeoPoint center;
	private Float radius;
	
	
	public GeoCircle(GeoPoint center, Float radius) {
		super();
		this.center = center;
		this.radius = radius;
	}

	@Override
	public boolean contains(GeoPoint point) {
		float distance = center.distance(point);
		return radius > distance;
	}

	@Override
	public GeoPoint center() {
		return center;
	}

	@Override
	public Float radius() {
		return radius;
	}

	@Override
	public List<GeoPoint> points() {
		return new ArrayList<GeoPoint>(Arrays.asList(center));
	}

}
