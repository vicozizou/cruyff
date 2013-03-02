package com.aureabox.webcore.web.facesbean;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aureabox.webcore.util.FacesUtils;

@SuppressWarnings("serial")
@Component("prefBean")
@Scope("session")
public class PreferencesBean implements Serializable {
	private static Log logger = LogFactory.getLog(PreferencesBean.class);

	@Autowired
	private LocaleBean localeBean;
	private String currentLocale;
	private boolean localeLinkMode = false;

	public String getCurrentLocale() {
		return currentLocale;
	}

	public void setCurrentLocale(String locale) {
		this.currentLocale = locale;
	}

	public boolean isLocaleLinkMode() {
		return localeLinkMode;
	}

	public void setLocaleLinkMode(boolean localeLinkMode) {
		this.localeLinkMode = localeLinkMode;
	}

	@PostConstruct
	public void onLoad() {
		Locale locale = FacesUtils.getApplication().getDefaultLocale();
		currentLocale = locale.getLanguage();
		switchLocale(locale);
	}

	public Locale getActualLocale() {
		return resolveLocale();
	}

	public Locale resolveLocale() {
		return resolveLocale(currentLocale);
	}

	private Locale resolveLocale(String lang) {
		Locale locale = null;
		for (String key : getSupportedLocales().keySet()) {
			if (key.equals(lang)) {
				locale = getSupportedLocales().get(key);
				break;
			}
		}
		return locale;
	}

	public void switchLocale(Locale locale) {
		if (!currentLocale.equals(locale.getLanguage())) {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Chaging locale from %s to %s", currentLocale, locale.getLanguage()));
			}
			FacesUtils.setLocale(locale);
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("New locale is the same, ignoring it");
			}
		}
	}

	public String onCurrentLocaleChanged() {
		String locale = FacesUtils.getFromParams("currentLocale");
		switchLocale(resolveLocale(locale));
		setCurrentLocale(locale);
		return null;
	}

	public void onCurrentLocaleChanged(ValueChangeEvent event) {
		Locale locale = null;
		if (event == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Change event is null, setting to default");
			}
			locale = FacesUtils.getApplication().getDefaultLocale();
		} else {
			locale = resolveLocale(event.getNewValue().toString());
		}
		switchLocale(locale);
	}

	public Map<String, Locale> getSupportedLocales() {
		return localeBean.getSupportedLocales();
	}

	public List<SelectItem> getLocales() {
		return localeBean.getLocales();
	}
}
