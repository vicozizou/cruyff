package com.aureabox.backdoor.bean.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.util.StringUtils;

import com.aureabox.backdoor.bean.AbstractBackDoor;
import com.aureabox.backdoor.bean.BackDoorBean;
import com.aureabox.backdoor.throwing.BackDoorException;
import com.aureabox.webcore.model.AppUser;
import com.aureabox.webcore.model.UserStatus;
import com.aureabox.webcore.service.UserService;

public class AppUserBackDoorBean extends AbstractBackDoor {
	@Autowired
	private UserService userService;
	private BackDoorBean personBackDoor;
	private BackDoorBean userRoleBackDoor;
	private PasswordEncoder passwordEncoder;

	public void setPersonBackDoor(BackDoorBean personBackDoor) {
		this.personBackDoor = personBackDoor;
	}

	public void setUserRoleBackDoor(BackDoorBean userRoleBackDoor) {
		this.userRoleBackDoor = userRoleBackDoor;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public int getFieldsCount() {
		return 7;
	}

	@Override
	public void parseValues(String[] values, Object target) {
		// days_to_expire, deleted, last_login_date, login_attempts,
		// "password",password_encrypted, user_status, username, id
		int i = 0;
		AppUser appUser = (AppUser) target;

		try {
			appUser.setDaysToExpire(Integer.valueOf(values[i++]));
		} catch (NumberFormatException e) {
			logger.warn(String.format("Days to expire is not numeric value: %s", values[i - 1]));
		}

		try {
			appUser.setLastLoginDate(DATE_FORMAT.parse(values[i++]));
		} catch (Exception e) {
			logger.warn(String.format("Wrong login date %s, ignoring", values[i - 1]));
		}

		try {
			appUser.setLoginAttempts(Integer.valueOf(values[i++]));
		} catch (NumberFormatException e) {
			logger.warn(String.format("Login attemps is not numeric value", values[i - 1]));
			appUser.setLoginAttempts(0);
		}

		appUser.setPassword(resolvePasswd(values[i++]));
		appUser.setUserStatus(resolveUserStatus(values[i++]));
		appUser.setUsername(values[i++]);

		try {
			appUser.setId(Long.valueOf(values[i++]));
		} catch (NumberFormatException e) {
			logger.warn(String.format("Id is not numeric value: %s", values[i - 1]));
		}
	}

	private String resolvePasswd(String value) {
		if (passwordEncoder == null) {
			return value;
		}

		return passwordEncoder.encodePassword(value, null);
	}

	private UserStatus resolveUserStatus(String value) {
		if (UserStatus.ACTIVE.getStatus().equalsIgnoreCase(value)) {
			return UserStatus.ACTIVE;
		} else if (UserStatus.BLOCKED.getStatus().equalsIgnoreCase(value)) {
			return UserStatus.BLOCKED;
		} else {
			return UserStatus.EXPIRED;
		}
	}

	@Override
	public void processLine(String[] values) throws BackDoorException {
		if (canProcess(values)) {
			if (isReset()) {
				state = new AppUser();
			}

			current.parseValues(values, state);

			if (isMainBean()) {
				AppUser user = (AppUser) state;

				try {
					userService.updateAppUser(user);

					if (logger.isDebugEnabled()) {
						logger.debug(String.format("App user persisted: %s", user.toString()));
					}

					reset();
				} catch (Exception e) {
					throw new BackDoorException("Error persisting app user", e);
				}
			}
		}
	}

	@Override
	public void handleComment(String line) {
		String command = extractCommand(line);
		current = this;

		if (StringUtils.hasText(command)) {
			if (command.equalsIgnoreCase("personBackDoor")) {
				current = personBackDoor;
			} else if (command.equalsIgnoreCase("userRoleBackDoor")) {
				current = userRoleBackDoor;
			}

			return;
		}
	}
}
