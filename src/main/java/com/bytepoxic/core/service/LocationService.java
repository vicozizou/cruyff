package com.aureabox.webcore.service;

import java.util.List;

import com.aureabox.webcore.model.Location;
import com.aureabox.webcore.model.Nationality;
import com.aureabox.webcore.throwing.WebCoreException;

public interface LocationService {
	void createLocation(Location location) throws WebCoreException;
	Location findCachedLocation(Long id) throws WebCoreException;
	Location findLocation(Long id) throws WebCoreException;
	List<Location> listLocations() throws WebCoreException;
	List<Location> listLocations(int firstResult, int maxResults) throws WebCoreException;
	void updateLocation(Location location) throws WebCoreException;
	void deleteLocation(Location location) throws WebCoreException;
	List<Location> findLocationsByParent(Location parent) throws WebCoreException;
	List<Location> findMainLocations() throws WebCoreException;
	Location buildLocationTree(Location start) throws WebCoreException;
	List<Location> findLocationsByName(String name) throws WebCoreException;
	List<Location> findLocationsByCode(String code) throws WebCoreException;
	long countLocations() throws WebCoreException;
	Location getLocationTree() throws WebCoreException;
	
	void createNationality(Nationality nationality) throws WebCoreException;
	Nationality findNationality(Long id) throws WebCoreException;
	List<Nationality> listNationalities() throws WebCoreException;
	List<Nationality> listNationalities(int firstResult, int maxResults) throws WebCoreException;
	void updateNationality(Nationality nationality) throws WebCoreException;
	void deleteNationality(Long id) throws WebCoreException;
	List<Nationality> findNationalitiesByName(String name) throws WebCoreException;
	long countNationalities() throws WebCoreException;
}
