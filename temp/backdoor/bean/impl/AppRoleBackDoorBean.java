package com.bytepoxic.backdoor.bean.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.bytepoxic.backdoor.bean.AbstractBackDoor;
import com.bytepoxic.backdoor.throwing.BackDoorException;
import com.bytepoxic.core.model.AppRole;
import com.bytepoxic.core.service.UserService;

public class AppRoleBackDoorBean extends AbstractBackDoor {
	@Autowired
	private UserService userService;

	@Override
	public void processLine(String[] values) throws BackDoorException {
		if (canProcess(values)) {
			AppRole appRole = new AppRole();
			parseValues(values, appRole);

			try {
				userService.createAppRole(appRole);

				if (logger.isDebugEnabled()) {
					logger.debug(String.format("AppRole persisted: %s", appRole.toString()));
				}
			} catch (Exception e) {
				throw new BackDoorException("Error persisting app role", e);
			}
		}
	}

	@Override
	public int getFieldsCount() {
		return 2;
	}

	@Override
	public void handleComment(String line) {
	}

	@Override
	public void parseValues(String[] values, Object target) {
		AppRole appRole = (AppRole) target;
		appRole.setDescription(values[0]);
		appRole.setName(values[1]);

		if (logger.isDebugEnabled()) {
			logger.debug(String.format("AppRole parsed: %s", appRole.toString()));
		}
	}
}
