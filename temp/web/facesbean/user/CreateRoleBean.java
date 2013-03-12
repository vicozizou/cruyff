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
@Component("createRoleBean")
@Scope("request")
public class CreateRoleBean extends RoleFormBean {
	private static Log logger = LogFactory.getLog(CreateRoleBean.class);

	@PostConstruct
	public void onLoad() {
		role = new AppRole();
		toogleNew();
	}

	public String save() {
		try {
			userService.createAppRole(role);
			return getListActionUrl();
		} catch (WebCoreException e) {
			logger.error("Could not create role", e);
			MessageUtils.addErrorMessage("msg", "app.entity.generic.save_error", new String[] { "users.roles.entity_name" });
		}
		return null;
	}
}
