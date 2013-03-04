package com.bytepoxic.core.service;

import java.util.List;

import com.bytepoxic.core.model.Location;
import com.bytepoxic.core.model.Nationality;
import com.bytepoxic.core.throwing.CoreException;

public interface LocationService {
	void createLocation(Location location) throws CoreException;
	Location findCachedLocation(Long id) throws CoreException;
	Location findLocation(Long id) throws CoreException;
	List<Location> listLocations() throws CoreException;
	List<Location> listLocations(int firstResult, int maxResults) throws CoreException;
	void updateLocation(Location location) throws CoreException;
	void deleteLocation(Location location) throws CoreException;
	List<Location> findLocationsByParent(Location parent) throws CoreException;
	List<Location> findMainLocations() throws CoreException;
	Location buildLocationTree(Location start) throws CoreException;
	List<Location> findLocationsByName(String name) throws CoreException;
	List<Location> findLocationsByCode(String code) throws CoreException;
	long countLocations() throws CoreException;
	Location getLocationTree() throws CoreException;
	
	void createNationality(Nationality nationality) throws CoreException;
	Nationality findNationality(Long id) throws CoreException;
	List<Nationality> listNationalities() throws CoreException;
	List<Nationality> listNationalities(int firstResult, int maxResults) throws CoreException;
	void updateNationality(Nationality nationality) throws CoreException;
	void deleteNationality(Long id) throws CoreException;
	List<Nationality> findNationalitiesByName(String name) throws CoreException;
	long countNationalities() throws CoreException;
}
