package com.aureabox.webcore.web.facesbean.user;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aureabox.webcore.model.AppUser;
import com.aureabox.webcore.service.UserService;
import com.aureabox.webcore.throwing.WebCoreException;
import com.aureabox.webcore.util.MessageUtils;
import com.aureabox.webcore.web.facesbean.form.ClassicListBean;

@SuppressWarnings("serial")
@Component("listUsersBean")
@Scope("request")
public class ListUsersBean extends ClassicListBean {
	private static Log logger = LogFactory.getLog(ListUsersBean.class);

	@Autowired
	private UserService userService;
	private List<AppUser> users;
	private AppUser selectedUser;

	@PostConstruct
	public void onLoad() {
		refreshUsers();
		setShowRemove(false);
	}

	public List<AppUser> getUsers() {
		return users;
	}

	public void setUser(List<AppUser> users) {
		this.users = users;
	}

	public AppUser getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedRole(AppUser selectedUser) {
		this.selectedUser = selectedUser;
	}

	private void refreshUsers() {
		if (logger.isDebugEnabled()) {
			logger.debug("Getting user list");
		}
		try {
			users = userService.listAppUsers();
		} catch (WebCoreException e) {
			logger.error("Could not get users", e);
			MessageUtils.addErrorMessage("msg", "app.entity.generic.list_error", new String[] { "users.users.entities_name" });
		}
	}

	public void refresh() {
		refreshUsers();
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
