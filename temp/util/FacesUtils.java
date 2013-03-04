package com.aureabox.webcore.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;

import javax.faces.application.Application;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.util.StringUtils;

import com.aureabox.webcore.model.AppUser;

public class FacesUtils {
	private static Log logger = LogFactory.getLog(FacesUtils.class);

	public static FacesContext getContext() {
		if (logger.isDebugEnabled()) {
			logger.debug("Getting Faces context");
		}
		return FacesContext.getCurrentInstance();
	}

	public static ExternalContext getExternal() {
		if (logger.isDebugEnabled()) {
			logger.debug("Getting Faces External context");
		}
		return getContext().getExternalContext();
	}
	
	public static String getReqContextPath() {
		return getExternal().getRequestContextPath();
	}

	public static String getBundleText(String bundleName, String key, Object[] params) {
		if (!StringUtils.hasText(bundleName) || !StringUtils.hasText(key)) {
			logger.warn(String.format("Ignoring bundle key resolving since the bundle and/or key are empty: %s/%s", bundleName, key));
			return null;
		}
		FacesContext context = getContext();
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Getting bundle key %s for bundle %s with params %s", key, bundleName, params));
		}
		try {
			String text = context.getApplication().getResourceBundle(context, bundleName).getString(key);
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Found text %s for key %s", text, key));
			}
			if (params != null) {
				if (logger.isDebugEnabled()) {
					logger.debug("Params are not null trying to get a formatted text");
				}
				String[] formattedParams = getBundleParams(bundleName, params);
				MessageFormat format = new MessageFormat(text);
				text = format.format(formattedParams, new StringBuffer(), null).toString();
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("Found parametrized text %s", text));
				}
			}
			return text;
		} catch (MissingResourceException e) {
			logger.warn(String.format("Could not find text for key %s in bundle %s, returning key", key, bundleName));
		}
		return key;
	}

	private static String[] getBundleParams(String bundleName, Object[] params) {
		String[] formatted = new String[params.length];
		for (int i = 0; i < params.length; i++) {
			formatted[i] = getBundleText(bundleName, params[i].toString());
		}
		return formatted;
	}

	public static String getBundleText(String bundleName, String key) {
		return getBundleText(bundleName, key, null);
	}

	public static Map<String, String> getParamsMap() {
		if (logger.isDebugEnabled()) {
			logger.debug("Getting request parameter map");
		}
		return getExternal().getRequestParameterMap();
	}

	public static String getFromParams(String param) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Getting parameter %s", param));
		}
		return getParamsMap().get(param);
	}

	public static String getFromParams(String param, String defValue) {
		String value = getFromParams(param);
		return StringUtils.hasText(value) ? value : defValue;
	}

	public static Map<String, Object> getSessionMap() {
		if (logger.isDebugEnabled()) {
			logger.debug("Getting session map");
		}
		return getExternal().getSessionMap();
	}

	public static Object getFromSession(String key) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Getting session attribute %s", key));
		}
		return getSessionMap().get(key);
	}

	public static void addToSession(String key, Object value) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Adding to session value %s with key", value, key));
		}
		getSessionMap().put(key, value);
	}

	public static void addToSession(String key, Map<String, Object> values) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Adding to session values %s with key", values, key));
		}
		getSessionMap().putAll(values);
	}

	public static Object removeFromSession(String key) {
		return getSessionMap().remove(key);
	}

	public static void invalidateSession() {
		if (logger.isDebugEnabled()) {
			logger.debug("Invalidating session");
		}
		getExternal().invalidateSession();
	}

	public static AppUser getCurrentUser() {
		if (logger.isDebugEnabled()) {
			logger.debug("Getting current user");
		}
		SecurityContext context = (SecurityContext) getFromSession(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
		if (context != null && context.getAuthentication() != null) {
			AppUser user = (AppUser) context.getAuthentication().getPrincipal();
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Found on session current user %s", user));
			}
			return user;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("No current user found on session");
		}
		return null;
	}

	public static Application getApplication() {
		if (logger.isDebugEnabled()) {
			logger.debug("Getting application");
		}
		return getContext().getApplication();
	}

	public static UIViewRoot getViewRoot() {
		if (logger.isDebugEnabled()) {
			logger.debug("Getting view root");
		}
		return getContext().getViewRoot();
	}

	public static Locale getLocale() {
		return getViewRoot().getLocale();
	}

	public static void setLocale(Locale locale) {
		getViewRoot().setLocale(locale);
	}
}
