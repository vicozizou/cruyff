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
@Component("viewLocationBean")
@Scope("request")
public class ViewLocationBean extends LocationFormBean {
	private static Log logger = LogFactory.getLog(ViewLocationBean.class);

	@PostConstruct
	public void onLoad() {
		String id = getIdParam();
		try {
			Long locationId = Long.valueOf(id);
			location = locationService.findCachedLocation(locationId);
			if (location != null) {
				toogleEdit();
			} else {
				handleUnfound();
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Cannot manipulate %s as valid location id", id));
			}
			handleUnfound();
		}
	}

	private void handleUnfound() {
		location = new Location();
		MessageUtils.addErrorMessage("msg", "places.locations.location_not_found");
		toogleReadOnly();
	}

	public void update() {
		try {
			locationService.updateLocation(location);
			MessageUtils.addSuccessMessage("msg", "app.entity.generic.update_success", new String[] { "places.locations.entity_name" });
		} catch (WebCoreException e) {
			logger.error("Could not create location", e);
			MessageUtils.addErrorMessage("msg", "app.entity.generic.save_error", new String[] { "places.locations.entity_name" });
		}
	}

	public String remove() {
		if (location != null) {
			try {
				String result = location.hasParent() ? getRedirectViewActionUrl(location.getParent().getId()) : getListActionUrl();
				locationService.deleteLocation(location);
				MessageUtils.addSuccessMessage("msg", "places.locations.remove_success");
				return result;
			} catch (WebCoreException e) {
				logger.error(String.format("Could not delete location %s", location), e);
				MessageUtils.addErrorMessage("msg", "places.locations.remove_error");
			}
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Location to delete is null");
			}
			MessageUtils.addErrorMessage("msg", "places.locations.location_not_found");
		}
		return null;
	}
}
