package com.aureabox.webcore.web.facesbean.place;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aureabox.webcore.model.Location;
import com.aureabox.webcore.throwing.WebCoreException;
import com.aureabox.webcore.util.MessageUtils;

@SuppressWarnings("serial")
@Component("createLocationBean")
@Scope("request")
public class CreateLocationBean extends LocationFormBean {
	private static Log logger = LogFactory.getLog(CreateLocationBean.class);

	@PostConstruct
	public void onLoad() {
		location = new Location();
		Location parent = null;
		String pid = getParentIdParam();
		try {
			parent = locationService.findCachedLocation(Long.valueOf(pid));
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Cannot manipulate %s as valid location id", pid));
			}
		}
		location.setParent(parent);
		toogleNew();
	}

	public String save() {
		try {
			locationService.createLocation(location);
			return location.hasParent() ? getRedirectViewActionUrl(location.getParent().getId()) : getListActionUrl();
		} catch (WebCoreException e) {
			logger.error("Could not create role", e);
			MessageUtils.addErrorMessage("msg", "app.entity.generic.save_error", new String[] { "places.locations.entity_name" });
		}
		return null;
	}
}
