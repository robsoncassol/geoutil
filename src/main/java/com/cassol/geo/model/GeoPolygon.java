package com.cassol.geo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;



/**
 * The GeoUtil. <br>
 * 
 * @see {@link Builder}
 * @author Robson Cassol (robsoncassol@gmail.com)
 */

public class GeoPolygon implements GeoContainer{
	
	private List<GeoPoint> vertices = new ArrayList<GeoPoint>();
	private GeoPoint center;
	
	public GeoPolygon(List<GeoPoint> vertices) {
		super();
		for (GeoPoint geoPoint : vertices) {
			this.addVertex(geoPoint);
		}
		build();
	}

	public GeoPolygon() {
		super();
	}
	
	public GeoPolygon addVertex(GeoPoint point){
		vertices.add(point);
		return this;
	}
	
	public void build(){
		center = centroid(vertices);
	}
	
	public GeoPoint getCenter() {
		return center;
	}
	
	public List<GeoPoint> getKnots() {
		return vertices;
	}
	
	
	private GeoPoint centroid(List<GeoPoint> knots)  {
	    Double centroidX = 0.0, centroidY = 0.0;

	        for(GeoPoint knot : knots) {
	            centroidX += knot.getLatitude();
	            centroidY += knot.getLongitude();
	        }
	    return new GeoPoint(centroidX / knots.size(), centroidY / knots.size());
	}

	
	public GeoPoint farthestPointFromCenter() {
		Entry<Float, GeoPoint> lastEntry = farthestPointFromCenterEntry();
		return lastEntry.getValue();
	}
	
	public Float farthestPointFromCenterInMeters() {
		Entry<Float, GeoPoint> lastEntry = farthestPointFromCenterEntry();
		return lastEntry.getKey();
	}

	private Entry<Float, GeoPoint> farthestPointFromCenterEntry() {
		GeoPoint centroid = centroid(vertices);
		TreeMap<Float,GeoPoint> map = new TreeMap<Float, GeoPoint>();
		for (GeoPoint geoPoint : vertices) {
			map.put(centroid.distance(geoPoint), geoPoint);
		}
		
		Entry<Float, GeoPoint> lastEntry = map.lastEntry();
		return lastEntry;
	}

	@Override
	public GeoPoint center() {
		return center;
	}

	@Override
	public Float radius() {
		return farthestPointFromCenterInMeters();
	}

	@Override
	public List<GeoPoint> points() {
		return vertices;
	}

	@Override
	public boolean contains(GeoPoint point) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
