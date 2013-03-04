package com.aureabox.webcore.web.facesbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aureabox.webcore.util.FacesUtils;
import com.aureabox.webcore.util.LogUtils;

@SuppressWarnings("serial")
@Component("localeBean")
@Scope("application")
public class LocaleBean implements Serializable {
	private static Log logger = LogFactory.getLog(LocaleBean.class);

	private final Map<String, Locale> supportedLocales = buildLocales();
	private final List<SelectItem> locales = refreshToUI();

	public Map<String, Locale> getSupportedLocales() {
		return supportedLocales;
	}

	public List<SelectItem> getLocales() {
		return locales;
	}

	private Map<String, Locale> buildLocales() {
		Map<String, Locale> locales = new HashMap<String, Locale>();
		for (Iterator<Locale> lit = FacesUtils.getApplication().getSupportedLocales(); lit.hasNext();) {
			Locale loc = lit.next();
			locales.put(loc.getLanguage(), loc);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Found locales:" + LogUtils.formatMap(locales));
		}
		FacesUtils.setLocale(FacesUtils.getApplication().getDefaultLocale());
		return locales;
	}

	private List<SelectItem> refreshToUI() {
		List<SelectItem> locales = new ArrayList<SelectItem>(supportedLocales.size());
		ResourceBundle labels = FacesUtils.getContext().getApplication().getResourceBundle(FacesUtils.getContext(), "app");
		for (String key : supportedLocales.keySet()) {
			locales.add(new SelectItem(key, labels.getString(key)));
		}
		return locales;
	}
}
