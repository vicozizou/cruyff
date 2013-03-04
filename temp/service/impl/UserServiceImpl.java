package com.aureabox.webcore.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.aureabox.webcore.model.AppRole;
import com.aureabox.webcore.model.AppUser;
import com.aureabox.webcore.model.TrackingType;
import com.aureabox.webcore.model.UserTrack;
import com.aureabox.webcore.service.UserService;
import com.aureabox.webcore.throwing.WebCoreException;
import com.aureabox.webcore.throwing.WebCoreServiceException;
import com.aureabox.webcore.util.LogUtils;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
	private static Log logger = LogFactory.getLog(UserServiceImpl.class);

	@Override
	@Transactional
	@Secured({"ROLE_ADMIN"})
	public void createAppRole(AppRole appRole) throws CoreException {
		if (appRole == null) {
			throw new CoreServiceException("AppRole is null, cannot create it");
		}
		try {
			appRole.persist();
		} catch (Exception e) {
			throw new CoreServiceException(String.format("Error persisting appRole %s", appRole), e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("AppRole persisted with id %s", appRole.getId()));
		}
	}

	@Override
	public AppRole findAppRole(Long id) throws CoreException {
		try {
			AppRole appRole = AppRole.findAppRole(id);
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("AppRole with id %s%sfound", id, appRole == null ? " was not " : ""));
			}
			return appRole;
		} catch (Exception e) {
			throw new CoreServiceException(String.format("Error finding appRole with %s", id), e);
		}
	}

	@Override
	public List<AppRole> listAppRoles() throws CoreException {
		try {
			List<AppRole> appRoles = AppRole.findAllAppRoles();
			if (logger.isDebugEnabled()) {
				logger.debug("AppRoles found:" + LogUtils.formatCollection(appRoles));
			}
			return appRoles;
		} catch (Exception e) {
			throw new CoreServiceException("Error listing appRoles", e);
		}
	}

	@Override
	public List<AppRole> listAppRoles(int firstResult, int maxResults) throws CoreException {
		try {
			List<AppRole> appRoles = AppRole.findAppRoleEntries(firstResult, maxResults);
			if (logger.isDebugEnabled()) {
				logger.debug("AppRoles found:" + LogUtils.formatCollection(appRoles));
			}
			return appRoles;
		} catch (Exception e) {
			throw new CoreServiceException("Error listing appRoles", e);
		}
	}

	@Override
	@Transactional
	@Secured({"ROLE_ADMIN"})
	public void updateAppRole(AppRole appRole) throws CoreException {
		if (appRole == null) {
			throw new CoreServiceException("AppRole is null, cannot update it");
		}
		try {
			appRole.merge();
		} catch (Exception e) {
			throw new CoreServiceException(String.format("Error updating appRole %s", appRole), e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("AppRole updated: %s", appRole));
		}
	}

	@Override
	@Transactional
	@Secured({"ROLE_ADMIN"})
	public void deleteAppRole(Long id) throws CoreException {
		if (id == null) {
			throw new CoreServiceException("AppRole id is null, cannot delete it");
		}
		AppRole appRole = findAppRole(id);
		if (appRole != null) {
			try {
				appRole.remove();
			} catch (Exception e) {
				throw new CoreServiceException(String.format("Error updating appRole with id %s", id), e);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("AppRole with id %s%sdeleted", id, appRole == null ? " was not " : ""));
		}
	}

	@Override
	@Transactional
	@Secured({"ROLE_ADMIN"})
	public void deleteAppRoles(List<AppRole> roles) throws CoreException {
		if (roles == null) {
			throw new CoreServiceException("AppRole ids are null, cannot delete them");
		}
		for (AppRole role : roles) {
			deleteAppRole(role.getId());
		}
	}

	@Override
	public List<AppRole> findAppRolesByName(String name) throws CoreException {
		if (!StringUtils.hasText(name)) {
			logger.warn("AppRole name is empty, cannot find appRoles");
			return null;
		}
		try {
			List<AppRole> appRoles = AppRole.findAppRolesByName(name).getResultList();
			if (logger.isDebugEnabled()) {
				logger.debug("AppRoles found:" + LogUtils.formatCollection(appRoles, "\n"));
			}
			return appRoles;
		} catch (Exception e) {
			throw new CoreServiceException(String.format("Error finding appRoles with name %s", name), e);
		}
	}

	@Override
	public AppRole findAppRoleByName(String name) throws CoreException {
		if (!StringUtils.hasText(name)) {
			logger.warn("AppRole name is empty, cannot find appRole");
			return null;
		}
		try {
			List<AppRole> appRoles = findAppRolesByName(name);
			AppRole appRole = null;
			if (appRoles != null && !appRoles.isEmpty()) {
				appRole = appRoles.get(0);
			}
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("AppRole found: %s", appRole != null ? appRole : "none"));
			}
			return appRole;
		} catch (Exception e) {
			throw new CoreServiceException(String.format("Error finding appRole with name %s", name), e);
		}
	}

	@Override
	public long countAppRoles() throws CoreException {
		try {
			long count = AppRole.countAppRoles();
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Found %s appRoles", count));
			}
			return count;
		} catch (Exception e) {
			throw new CoreServiceException("Error counting appRoles", e);
		}
	}

	@Override
	@Transactional
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	public void createAppUser(AppUser appUser) throws CoreException {
		if (appUser == null) {
			throw new CoreServiceException("AppUser is null, cannot create it");
		}
		try {
			appUser.persist();
		} catch (Exception e) {
			throw new CoreServiceException(String.format("Error persisting appUser %s", appUser), e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("AppUser persisted with id %s", appUser.getId()));
		}
	}

	@Override
	public AppUser findAppUser(Long id) throws CoreException {
		try {
			AppUser appUser = AppUser.findAppUser(id);
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("AppUser with id %s%sfound", id, appUser == null ? " was not " : ""));
			}
			return appUser;
		} catch (Exception e) {
			throw new CoreServiceException(String.format("Error finding appUser with %s", id), e);
		}
	}

	@Override
	public List<AppUser> listAppUsers() throws CoreException {
		try {
			List<AppUser> appUsers = AppUser.findAllAppUsers();
			if (logger.isDebugEnabled()) {
				logger.debug("AppUsers found:" + LogUtils.formatCollection(appUsers));
			}
			return appUsers;
		} catch (Exception e) {
			throw new CoreServiceException("Error listing appUsers", e);
		}
	}

	@Override
	public List<AppUser> listAppUsers(int firstResult, int maxResults) throws CoreException {
		try {
			List<AppUser> appUsers = AppUser.findAppUserEntries(firstResult, maxResults);
			if (logger.isDebugEnabled()) {
				logger.debug("AppUsers found:" + LogUtils.formatCollection(appUsers));
			}
			return appUsers;
		} catch (Exception e) {
			throw new CoreServiceException("Error listing appUsers", e);
		}
	}

	@Override
	@Transactional
	@Secured({"ROLE_ADMIN"})
	public void updateAppUser(AppUser appUser) throws CoreException {
		if (appUser == null) {
			throw new CoreServiceException("AppUser is null, cannot update it");
		}
		try {
			appUser.merge();
		} catch (Exception e) {
			throw new CoreServiceException(String.format("Error updating appUser %s", appUser), e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("AppUser updated: %s", appUser));
		}
	}

	@Override
	@Transactional
	@Secured({"ROLE_ADMIN"})
	public void deleteAppUser(Long id) throws CoreException {
		if (id == null) {
			throw new CoreServiceException("AppUser id is null, cannot delete it");
		}
		AppUser appUser = findAppUser(id);
		if (appUser != null) {
			try {
				appUser.remove();
			} catch (Exception e) {
				throw new CoreServiceException(String.format("Error updating appUser with id %s", id), e);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("AppUser with id %s%sdeleted", id, appUser == null ? " was not " : ""));
		}
	}

	@Override
	public List<AppUser> findAppUsersByUsername(String username) throws CoreException {
		if (!StringUtils.hasText(username)) {
			logger.warn("AppUser username is empty, cannot find appUsers");
			return null;
		}
		try {
			List<AppUser> appUsers = AppUser.findAppUsersByUsername(username).getResultList();
			if (logger.isDebugEnabled()) {
				logger.debug("AppUsers found:" + LogUtils.formatCollection(appUsers, "\n"));
			}
			return appUsers;
		} catch (Exception e) {
			throw new CoreServiceException(String.format("Error finding appUsers with username %s", username), e);
		}
	}

	@Override
	public AppUser findAppUserByUsername(String username) throws CoreException {
		if (!StringUtils.hasText(username)) {
			logger.warn("AppUser name is empty, cannot find appUser");
			return null;
		}
		try {
			List<AppUser> appUsers = findAppUsersByUsername(username);
			AppUser appUser = null;
			if (appUsers != null && !appUsers.isEmpty()) {
				appUser = appUsers.get(0);
			}
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("AppUser found: %s", appUser != null ? appUser : "none"));
			}
			return appUser;
		} catch (Exception e) {
			throw new CoreServiceException(String.format("Error finding appUser with name %s", username), e);
		}
	}

	@Override
	public long countAppUsers() throws CoreException {
		try {
			long count = AppUser.countAppUsers();
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Found %s appUsers", count));
			}
			return count;
		} catch (Exception e) {
			throw new CoreServiceException("Error counting appUsers", e);
		}
	}

	@Override
	@Transactional
	public void trackUser(AppUser appUser, TrackingType type) throws CoreException {
		if (appUser == null) {
			throw new CoreServiceException("AppUser is null, cannot create it");
		}
		if (type == null) {
			throw new CoreServiceException("Tracking type is null, cannot create it");
		}
		UserTrack userTrack = new UserTrack();
		try {
			userTrack.setTracked(appUser);
			userTrack.setTrackingType(type);
			userTrack.setTrackingDate(new Date());
			userTrack.persist();
		} catch (Exception e) {
			throw new CoreServiceException(String.format("Error persisting userTrack %s", userTrack), e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("UserTrack persisted with id %s", userTrack.getId()));
		}
	}

	@Override
	@Transactional
	public void trackUserSignin(AppUser appUser) throws CoreException {
		trackUser(appUser, TrackingType.USER_SIGNIN);
	}

	@Override
	@Transactional
	public void trackUserSignout(AppUser appUser) throws CoreException {
		trackUser(appUser, TrackingType.USER_SIGNOUT);
	}

	@Override
	public UserTrack findUserTrack(Long id) throws CoreException {
		try {
			UserTrack userTrack = UserTrack.findUserTrack(id);
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("UserTrack with id %s%sfound", id, userTrack == null ? " was not " : ""));
			}
			return userTrack;
		} catch (Exception e) {
			throw new CoreServiceException(String.format("Error finding userTrack with %s", id), e);
		}
	}

	@Override
	public List<UserTrack> listUserTracks() throws CoreException {
		try {
			List<UserTrack> userTracks = UserTrack.findAllUserTracks();
			if (logger.isDebugEnabled()) {
				logger.debug("UserTracks found:" + LogUtils.formatCollection(userTracks));
			}
			return userTracks;
		} catch (Exception e) {
			throw new CoreServiceException("Error listing userTracks", e);
		}
	}

	@Override
	public List<UserTrack> listUserTracks(int firstResult, int maxResults) throws CoreException {
		try {
			List<UserTrack> userTracks = UserTrack.findUserTrackEntries(firstResult, maxResults);
			if (logger.isDebugEnabled()) {
				logger.debug("UserTracks found:" + LogUtils.formatCollection(userTracks));
			}
			return userTracks;
		} catch (Exception e) {
			throw new CoreServiceException("Error listing userTracks", e);
		}
	}

	@Override
	public List<UserTrack> findUserTracksByUser(AppUser appUser) throws CoreException {
		if (appUser == null) {
			logger.warn("AppUser is null, cannot find userTracks");
			return null;
		}
		try {
			List<UserTrack> userTracks = UserTrack.findUserTracksByTracked(appUser).getResultList();
			if (logger.isDebugEnabled()) {
				logger.debug("UserTracks found:" + LogUtils.formatCollection(userTracks, "\n"));
			}
			return userTracks;
		} catch (Exception e) {
			throw new CoreServiceException(String.format("Error finding userTracks with user %s", appUser), e);
		}
	}

	@Override
	public List<UserTrack> findUserTracksByDate(Date date) throws CoreException {
		if (date == null) {
			logger.warn("Tracking date is null, cannot find userTracks");
			return null;
		}
		try {
			List<UserTrack> userTracks = UserTrack.findUserTracksByTrackingDate(date).getResultList();
			if (logger.isDebugEnabled()) {
				logger.debug("UserTracks found:" + LogUtils.formatCollection(userTracks, "\n"));
			}
			return userTracks;
		} catch (Exception e) {
			throw new CoreServiceException(String.format("Error finding userTracks with date %s", date), e);
		}
	}

	@Override
	public List<UserTrack> findUserTracksByUserAndDate(AppUser appUser, Date date) throws CoreException {
		if (appUser == null) {
			logger.warn("AppUser is null, cannot find userTracks");
			return null;
		}
		if (date == null) {
			logger.warn("Tracking date is null, cannot find userTracks");
			return null;
		}
		try {
			List<UserTrack> userTracks = UserTrack.findUserTracksByTrackedAndTrackingDate(appUser, date).getResultList();
			if (logger.isDebugEnabled()) {
				logger.debug("UserTracks found:" + LogUtils.formatCollection(userTracks, "\n"));
			}
			return userTracks;
		} catch (Exception e) {
			throw new CoreServiceException(String.format("Error finding userTracks with user %s and date %s", appUser, date), e);
		}
	}
}
