package com.aureabox.webcore.web.facesbean.user;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aureabox.webcore.model.AppRole;
import com.aureabox.webcore.service.UserService;
import com.aureabox.webcore.throwing.WebCoreException;
import com.aureabox.webcore.util.MessageUtils;
import com.aureabox.webcore.web.facesbean.form.ClassicListBean;

@SuppressWarnings("serial")
@Component("listRolesBean")
@Scope("request")
public class ListRolesBean extends ClassicListBean {
	private static Log logger = LogFactory.getLog(ListRolesBean.class);

	@Autowired
	private UserService userService;
	private List<AppRole> roles;
	private AppRole selectedRole;

	@PostConstruct
	public void onLoad() {
		refreshRoles();
		setShowRemove(false);
	}

	public List<AppRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AppRole> roles) {
		this.roles = roles;
	}

	public AppRole getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(AppRole selectedRole) {
		this.selectedRole = selectedRole;
	}

	private void refreshRoles() {
		if (logger.isDebugEnabled()) {
			logger.debug("Getting role list");
		}
		try {
			roles = userService.listAppRoles();
		} catch (WebCoreException e) {
			logger.error("Could not get roles", e);
			MessageUtils.addErrorMessage("msg", "app.entity.generic.list_error", new String[] { "users.roles.entities_name" });
		}
	}

	public void refresh() {
		refreshRoles();
	}

	@Override
	protected String getEntityName() {
		return "role";
	}

	@Override
	protected String getSubDomain() {
		return "users";
	}
}
