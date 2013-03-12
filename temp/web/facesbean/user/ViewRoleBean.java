package com.aureabox.webcore.web.facesbean.user;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aureabox.webcore.model.AppRole;
import com.aureabox.webcore.throwing.WebCoreException;
import com.aureabox.webcore.util.MessageUtils;

@SuppressWarnings("serial")
@Component("viewRoleBean")
@Scope("request")
public class ViewRoleBean extends RoleFormBean {
	private static Log logger = LogFactory.getLog(ViewRoleBean.class);

	@PostConstruct
	public void onLoad() {
		String id = getIdParam();
		try {
			Long roleId = Long.valueOf(id);
			role = userService.findAppRole(roleId);
			if (role != null) {
				toogleEdit();
			} else {
				handleUnfound();
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Cannot manipulate %s as valid role id", id));
			}
			handleUnfound();
		}
	}

	private void handleUnfound() {
		role = new AppRole();
		MessageUtils.addErrorMessage("msg", "users.roles.role_not_found");
		toogleReadOnly();
	}

	public void update() {
		try {
			userService.updateAppRole(role);
			MessageUtils.addSuccessMessage("msg", "app.entity.generic.update_success", new String[] { "users.roles.entity_name" });
		} catch (WebCoreException e) {
			logger.error("Could not create role", e);
			MessageUtils.addErrorMessage("msg", "app.entity.generic.save_error", new String[] { "users.roles.entity_name" });
		}
	}

	public String remove() {
		if (role != null) {
			try {
				userService.deleteAppRole(role.getId());
				MessageUtils.addSuccessMessage("msg", "users.roles.remove_success");
				return getListActionUrl();
			} catch (WebCoreException e) {
				logger.error(String.format("Could not delete role %s", role), e);
				MessageUtils.addErrorMessage("msg", "users.roles.remove_error");
			}
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Role to delete is null");
			}
			MessageUtils.addErrorMessage("msg", "users.roles.role_not_found");
		}
		return null;
	}
}
