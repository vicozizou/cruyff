package com.aureabox.webcore.web.facesbean.user;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aureabox.webcore.model.AppRole;
import com.aureabox.webcore.model.AppUser;
import com.aureabox.webcore.util.FacesUtils;

@SuppressWarnings("serial")
@Component("userBean")
@Scope("session")
public class BaseUserBean implements Serializable {
	private static final String ROLE_ADMIN = "ROLE_ADMIN";
	private static final String ROLE_USER = "ROLE_USER";
	private static Log logger = LogFactory.getLog(BaseUserBean.class);

	private AppUser currentUser;
	private boolean authenticated = false;
	private Map<String, AppRole> roleMap = new HashMap<String, AppRole>();

	public AppUser getCurrentUser() {
		return currentUser;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	@PostConstruct
	public void onLoad() {
		if (logger.isDebugEnabled()) {
			logger.debug("Getting current user");
		}
		currentUser = FacesUtils.getCurrentUser();
		Set<AppRole> userRoles = currentUser.getRoles();
		if (userRoles != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Creating role map");
			}
			for (AppRole userRole : userRoles) {
				roleMap.put(userRole.getName(), userRole);
			}
		}
	}

	public boolean hasRole(String role) {
		return roleMap.containsKey(role.toUpperCase());
	}

	public boolean isAdminRole() {
		return hasRole(ROLE_ADMIN);
	}

	public boolean isUserRole() {
		return hasRole(ROLE_USER);
	}
}
