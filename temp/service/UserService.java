package com.bytepoxic.core.service;

import java.util.Date;
import java.util.List;

import com.bytepoxic.core.model.AppRole;
import com.bytepoxic.core.model.AppUser;
import com.bytepoxic.core.model.TrackingType;
import com.bytepoxic.core.model.UserTrack;
import com.bytepoxic.core.throwing.CoreException;

public interface UserService {
	void createAppRole(AppRole appRole) throws CoreException;
	AppRole findAppRole(Long id) throws CoreException;
	List<AppRole> listAppRoles() throws CoreException;
	List<AppRole> listAppRoles(int firstResult, int maxResults) throws CoreException;
	void updateAppRole(AppRole appRole) throws CoreException;
	void deleteAppRole(Long id) throws CoreException;
	void deleteAppRoles(List<AppRole> roles) throws CoreException;
	List<AppRole> findAppRolesByName(String name) throws CoreException;
	AppRole findAppRoleByName(String name) throws CoreException;
	long countAppRoles() throws CoreException;

	void createAppUser(AppUser appUser) throws CoreException;
	AppUser findAppUser(Long id) throws CoreException;
	List<AppUser> listAppUsers() throws CoreException;
	List<AppUser> listAppUsers(int firstResult, int maxResults) throws CoreException;
	void updateAppUser(AppUser appUser) throws CoreException;
	void deleteAppUser(Long id) throws CoreException;
	List<AppUser> findAppUsersByUsername(String name) throws CoreException;
	AppUser findAppUserByUsername(String username) throws CoreException;
	long countAppUsers() throws CoreException;

	void trackUser(AppUser appUser, TrackingType type) throws CoreException;
	void trackUserSignin(AppUser appUser) throws CoreException;
	void trackUserSignout(AppUser appUser) throws CoreException;
	UserTrack findUserTrack(Long id) throws CoreException;
	List<UserTrack> listUserTracks() throws CoreException;
	List<UserTrack> listUserTracks(int firstResult, int maxResults) throws CoreException;
	List<UserTrack> findUserTracksByUser(AppUser appUser) throws CoreException;
	List<UserTrack> findUserTracksByUserAndDate(AppUser appUser, Date date) throws CoreException;
	List<UserTrack> findUserTracksByDate(Date date) throws CoreException;
}
