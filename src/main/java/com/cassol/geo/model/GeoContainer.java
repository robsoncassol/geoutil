package com.cassol.geo.model;

import java.io.Serializable;
import java.util.List;


/**
 * The GeoUtil. <br>
 * 
 * @see {@link Builder}
 * @author Robson Cassol (robsoncassol@gmail.com)
 */

public interface GeoContainer extends Serializable{

	boolean contains(GeoPoint point);
	GeoPoint center();
	Float radius();
	List<GeoPoint> points();
	
	Double north(); 
	Double south(); 
	Double east(); 
	Double west(); 
	
}
