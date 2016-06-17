package com.cassol.geo.model;

import java.util.ArrayList;
import java.util.List;


/**
 * The GeoUtil. <br>
 * 
 * @see {@link Builder}
 * @author Robson Cassol (robsoncassol@gmail.com)
 */


public class GeoPoint {

	private Double altitude;
	private Double latitude;
	private Double longitude;
	public static final double EARTH_RADIUS = 6378137;

	public GeoPoint() {
	}

	public GeoPoint(Double altitude, Double latitude, Double longitude) {
		super();
		this.altitude = altitude;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public GeoPoint(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	
	public GeoPoint translateCoordinates(double angle, int distance){
		double dist = new Double(distance)/EARTH_RADIUS;
		double brng = Math.toRadians(angle);
		double lat1 = Math.toRadians(latitude);
		double lon1 = Math.toRadians(longitude);

		double newLat = Math.asin( Math.sin(lat1)*Math.cos(dist) + Math.cos(lat1)*Math.sin(dist)*Math.cos(brng) );
		double a = Math.atan2(Math.sin(brng)*Math.sin(dist)*Math.cos(lat1), Math.cos(dist)-Math.sin(lat1)*Math.sin(newLat));
		double newLon = lon1 + a;

		newLon = (newLon+ 3*Math.PI) % (2*Math.PI) - Math.PI;

		return new GeoPoint(Math.toDegrees(newLat), Math.toDegrees(newLon));
	}

	public float distance(GeoPoint p2) {
	    double dLat = Math.toRadians(p2.getLatitude() - getLatitude());
	    double dLng = Math.toRadians(p2.getLongitude() - getLongitude());
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(getLatitude())) * Math.cos(Math.toRadians(p2.getLatitude())) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    float dist = (float) (EARTH_RADIUS * c);

	    return dist;
	}
	
	public List<GeoPoint> quadrant(int meters){
		List<GeoPoint> points = new ArrayList<GeoPoint>();
		
		for(int i = 0; i < 360; i+=90)
			points.add(this.translateCoordinates(i, meters));
		
		return points;
	}

	@Override
	public String toString() {
		return latitude + ", " + longitude + "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((altitude == null) ? 0 : altitude.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeoPoint other = (GeoPoint) obj;
		if (altitude == null) {
			if (other.altitude != null)
				return false;
		} else if (!altitude.equals(other.altitude))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		return true;
	}


}
