package com.aureabox.webcore.web.facesbean;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.aureabox.webcore.model.AppUser;
import com.aureabox.webcore.service.UserService;
import com.aureabox.webcore.throwing.WebCoreException;
import com.aureabox.webcore.util.FacesUtils;
import com.aureabox.webcore.util.MessageUtils;

@SuppressWarnings("serial")
@Component("loginBean")
@Scope("request")
public class LoginBean implements Serializable {
	private static Log logger = LogFactory.getLog(LoginBean.class);

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	private String username = "user";
	private String password = "user";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String loginUser() {
		try {
			Authentication request = new UsernamePasswordAuthenticationToken(getUsername(), getPassword());
			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
		} catch (LockedException le) {
			logger.error("Error while trying to login", le);
			MessageUtils.addErrorMessage("msg", "login.failed.locked_account");
			return null;
		} catch (DisabledException de) {
			logger.error("Error while trying to login", de);
			MessageUtils.addErrorMessage("msg", "login.failed.disabled_account");
			return null;
		} catch (AuthenticationException ae) {
			logger.error("Error while trying to login", ae);
			MessageUtils.addErrorMessage("msg", "login.failed");
			return null;
		}

		try {
			userService.trackUserSignin((AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		} catch (WebCoreException e) {
			logger.error("Cound not track user sign in", e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("User %s was authenticated", username));
		}
		return "/faces/index?faces-redirect=true";
	}

	public String logoutUser() {
		AppUser user = FacesUtils.getCurrentUser();
		try {
			userService.trackUserSignout(user);
		} catch (WebCoreException e) {
			logger.error("Cound not track user sign out", e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Logging off%s, bye", user != null ? " " + user.getUsername() : ""));
		}
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/faces/index?faces-redirect=true";
	}
}
