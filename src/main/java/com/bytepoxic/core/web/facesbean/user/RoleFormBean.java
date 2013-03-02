package com.aureabox.webcore.web.facesbean.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.aureabox.webcore.model.AppRole;
import com.aureabox.webcore.service.UserService;
import com.aureabox.webcore.web.facesbean.form.ClassicFormBean;

@SuppressWarnings("serial")
public class RoleFormBean extends ClassicFormBean {
	@Autowired
	protected UserService userService;
	protected AppRole role;

	public AppRole getRole() {
		return role;
	}

	public void setRole(AppRole role) {
		this.role = role;
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
