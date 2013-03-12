package com.aureabox.webcore.web.facesbean.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.aureabox.webcore.model.AppUser;
import com.aureabox.webcore.service.UserService;
import com.aureabox.webcore.web.facesbean.form.ClassicFormBean;

@SuppressWarnings("serial")
public class UserFormBean extends ClassicFormBean {
	@Autowired
	protected UserService userService;
	protected AppUser user;

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	@Override
	protected String getEntityName() {
		return "user";
	}

	@Override
	protected String getSubDomain() {
		return "users";
	}
}
