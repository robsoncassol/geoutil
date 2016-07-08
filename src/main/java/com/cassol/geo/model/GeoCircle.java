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
	
	private static final long serialVersionUID = 7730558254610056883L;
	
	private GeoPoint center;
	private Float radius;
	
	
	public GeoCircle(GeoPoint center, Number radius) {
		super();
		this.center = center;
		this.radius = radius.floatValue();
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

	@Override
	public Double north() {
		return center.translateCoordinates(0D, radius.intValue()).getLatitude();
	}

	@Override
	public Double south() {
		return center.translateCoordinates(180D, radius.intValue()).getLatitude();
	}

	@Override
	public Double east() {
		return center.translateCoordinates(90D, radius.intValue()).getLongitude();
	}

	@Override
	public Double west() {
		return center.translateCoordinates(270D, radius.intValue()).getLongitude();
	}

}
