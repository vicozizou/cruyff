package com.aureabox.webcore.web.facesbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aureabox.webcore.util.FacesUtils;
import com.aureabox.webcore.web.bean.BreadCrumbBean;
import com.aureabox.webcore.web.bean.BreadCrumbBean.BreadCrumb;

@SuppressWarnings("serial")
@Component("navBean")
@Scope("request")
public class NavigationBean implements Serializable {
	private static Log logger = LogFactory.getLog(NavigationBean.class);

	@Autowired
	private BreadCrumbBean breadCrumbBean;
	private String path;

	@PostConstruct
	public void onLoad() {
		path = FacesUtils.getExternal().getRequestServletPath();
	}

	public List<BreadCrumb> getBreadCrumbs() {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Getting bread crumbs for %s", path));
		}
		return breadCrumbBean.getBreadCrumbs(path);
	}

	public BreadCrumb getRoot() {
		return breadCrumbBean.getRootBreadCrumb();
	}

	public boolean isUseRootWhenUnfound() {
		return breadCrumbBean.isUseRootWhenUnfound();
	}

	public boolean isThisPageRoot() {
		return path.equals(getRoot().getUrl());
	}
}
