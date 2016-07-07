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
	
	private static final long serialVersionUID = 4440572788874968195L;
	
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
	
	public GeoPolygon build(){
		center = centroid(vertices);
		return this;
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
	public boolean contains(GeoPoint p) {
		int i;
		double angle = 0;
		GeoPoint p1 = new GeoPoint();
		GeoPoint p2 = new GeoPoint();
		int n = vertices.size();

		for (i = 0; i < vertices.size(); i++) {
			p1.setLatitude(vertices.get(i).getLatitude() - p.getLatitude());
			p1.setLongitude(vertices.get(i).getLongitude() - p.getLongitude());
			p2.setLatitude(vertices.get((i + 1) % n).getLatitude() - p.getLatitude());
			p2.setLongitude(vertices.get((i + 1) % n).getLongitude() - p.getLongitude());
			angle += Angle2D(p1.getLatitude(), p1.getLongitude(), p2.getLatitude(), p2.getLongitude());
		}

		if (Math.abs(angle) < Math.PI)
			return false;
		else
			return true;
	}
	
	/*
	   Return the angle between two vectors on a plane
	   The angle is from vector 1 to vector 2, positive anticlockwise
	   The result is between -pi -> pi
	*/
	private double Angle2D(double x1, double y1, double x2, double y2) {
		double dtheta, theta1, theta2;

		theta1 = Math.atan2(y1, x1);
		theta2 = Math.atan2(y2, x2);
		dtheta = theta2 - theta1;

		while (dtheta > Math.PI)
			dtheta -= 2 * Math.PI;

		while (dtheta < -Math.PI)
			dtheta += 2 * Math.PI;

		return dtheta;
	}

	/**
	 * Return the most northerly point 
	 */
	@Override
	public Double north() {
		return vertices.stream().mapToDouble(GeoPoint::getLongitude).max().getAsDouble();
	}

	/**
	 * Return the southernmost point 
	 */
	@Override
	public Double south() {
		return vertices.stream().mapToDouble(GeoPoint::getLongitude).min().getAsDouble();
	}

	/**
	 * Return the point further east 
	 */
	@Override
	public Double east() {
		return vertices.stream().mapToDouble(GeoPoint::getLatitude).max().getAsDouble();
	}
	
	/**
	 * the furthest point west
	 */
	@Override
	public Double west() {
		return vertices.stream().mapToDouble(GeoPoint::getLatitude).min().getAsDouble();
	}
	
	
	

}
