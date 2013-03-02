package com.aureabox.webcore.service;

import java.util.Date;
import java.util.List;

import com.aureabox.webcore.model.AppRole;
import com.aureabox.webcore.model.AppUser;
import com.aureabox.webcore.model.TrackingType;
import com.aureabox.webcore.model.UserTrack;
import com.aureabox.webcore.throwing.WebCoreException;

public interface UserService {
	void createAppRole(AppRole appRole) throws WebCoreException;
	AppRole findAppRole(Long id) throws WebCoreException;
	List<AppRole> listAppRoles() throws WebCoreException;
	List<AppRole> listAppRoles(int firstResult, int maxResults) throws WebCoreException;
	void updateAppRole(AppRole appRole) throws WebCoreException;
	void deleteAppRole(Long id) throws WebCoreException;
	void deleteAppRoles(List<AppRole> roles) throws WebCoreException;
	List<AppRole> findAppRolesByName(String name) throws WebCoreException;
	AppRole findAppRoleByName(String name) throws WebCoreException;
	long countAppRoles() throws WebCoreException;

	void createAppUser(AppUser appUser) throws WebCoreException;
	AppUser findAppUser(Long id) throws WebCoreException;
	List<AppUser> listAppUsers() throws WebCoreException;
	List<AppUser> listAppUsers(int firstResult, int maxResults) throws WebCoreException;
	void updateAppUser(AppUser appUser) throws WebCoreException;
	void deleteAppUser(Long id) throws WebCoreException;
	List<AppUser> findAppUsersByUsername(String name) throws WebCoreException;
	AppUser findAppUserByUsername(String username) throws WebCoreException;
	long countAppUsers() throws WebCoreException;

	void trackUser(AppUser appUser, TrackingType type) throws WebCoreException;
	void trackUserSignin(AppUser appUser) throws WebCoreException;
	void trackUserSignout(AppUser appUser) throws WebCoreException;
	UserTrack findUserTrack(Long id) throws WebCoreException;
	List<UserTrack> listUserTracks() throws WebCoreException;
	List<UserTrack> listUserTracks(int firstResult, int maxResults) throws WebCoreException;
	List<UserTrack> findUserTracksByUser(AppUser appUser) throws WebCoreException;
	List<UserTrack> findUserTracksByUserAndDate(AppUser appUser, Date date) throws WebCoreException;
	List<UserTrack> findUserTracksByDate(Date date) throws WebCoreException;
}
