package com.bytepoxic.core.service.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bytepoxic.core.service.UserService;
import com.bytepoxic.core.throwing.CoreException;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	private static Log logger = LogFactory.getLog(UserDetailsService.class);

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Attempting to load user with username %s", username));
		}
		UserDetails userDetails = null;
		try {
			userDetails = userService.findAppUserByUsername(username);
		} catch (CoreException e) {
			throw new UsernameNotFoundException("Could not retrieve user", e);
		}

		if (userDetails == null) {
			throw new UsernameNotFoundException(String.format("User %s not found.", username));
		}

		return userDetails;
	}
}
