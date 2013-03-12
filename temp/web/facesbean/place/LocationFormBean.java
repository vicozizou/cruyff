package com.aureabox.webcore.web.facesbean.place;

import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.LatLng;
import org.springframework.beans.factory.annotation.Autowired;

import com.aureabox.webcore.model.Location;
import com.aureabox.webcore.service.LocationService;
import com.aureabox.webcore.web.facesbean.form.TreeFormBean;

@SuppressWarnings("serial")
public class LocationFormBean extends TreeFormBean {
	private static double DEFAULT_LATTITUDE = 0.0d;
	private static double DEFAULT_LONGTITUDE = 0.0d;

	@Autowired
	protected LocationService locationService;
	protected Location location;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	protected String getEntityName() {
		return "location";
	}

	@Override
	protected String getSubDomain() {
		return "places";
	}

	public void onStateChange(StateChangeEvent event) {
		location.setZoom(event.getZoomLevel());
	}

	public void onPointSelect(PointSelectEvent event) {
		LatLng coords = event.getLatLng();
		location.setLatitude(coords.getLat());
		location.setLongitude(coords.getLng());
	}

	public int getMapZoom() {
		return location != null && location.isMapValid() ? location.getZoom() : 0;
	}

	public double[] getValidCoords() {
		return getValidCoords(location);
	}

	public double[] getValidCoords(Location current) {
		if (current.hasValidCoords()) {
			return new double[] { current.getLatitude(), current.getLongitude() };
		}
		if (!current.hasParent()) {
			return new double[] { DEFAULT_LATTITUDE, DEFAULT_LONGTITUDE };
		}
		return getValidCoords(current.getParent());
	}

	@Override
	protected Long getParentId() {
		return location != null ? location.getId() : null;
	}
}
