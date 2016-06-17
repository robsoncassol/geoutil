package com.cassol.geo.model;

import java.util.List;


/**
 * The GeoUtil. <br>
 * 
 * @see {@link Builder}
 * @author Robson Cassol (robsoncassol@gmail.com)
 */

public interface GeoContainer {

	boolean contains(GeoPoint point);
	GeoPoint center();
	Float radius();
	List<GeoPoint> points();
	
}
