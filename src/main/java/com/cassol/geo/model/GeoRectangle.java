package com.cassol.geo.model;

import java.util.List;

public class GeoRectangle implements GeoContainer{
	
	private static final long serialVersionUID = 1030300668803644558L;
	
	private GeoPoint center;
	
	
	public GeoRectangle(GeoPoint leftTop, GeoPoint rightTop, GeoPoint leftBotton, GeoPoint rightBotton) {
		super();
	}

	@Override
	public boolean contains(GeoPoint point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GeoPoint center() {
		return center;
	}

	@Override
	public Float radius() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GeoPoint> points() {
		return null;
	}

	@Override
	public Double north() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double south() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double east() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double west() {
		// TODO Auto-generated method stub
		return null;
	}

}
