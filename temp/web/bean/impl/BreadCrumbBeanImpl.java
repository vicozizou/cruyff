package com.aureabox.webcore.web.bean.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.aureabox.webcore.util.LogUtils;
import com.aureabox.webcore.web.bean.BreadCrumbBean;

@Component("breadCrumbBean")
public class BreadCrumbBeanImpl implements BreadCrumbBean {
	private static Log logger = LogFactory.getLog(BreadCrumbBeanImpl.class);

	private BreadCrumb root;
	private Map<String, List<BreadCrumb>> urls;
	private boolean useRootWhenUnfound;

	public BreadCrumb getRootBreadCrumb() {
		return root;
	}

	public void setRoot(String root) {
		this.root = extractBreadCrumb("home", root);
	}

	public void setUrls(Map<String, List<String>> newUrls) {
		urls = new HashMap<String, List<BreadCrumb>>();
		for (String urlList : newUrls.keySet()) {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Url list found %s", urlList));
			}
			String[] urlTokens = StringUtils.tokenizeToStringArray(urlList, ",");
			for (String url : urlTokens) {
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("Url found %s", url));
				}
				if (!url.startsWith("/")) {
					url = "/" + url;
				}
				List<BreadCrumb> breadCrumbs = new ArrayList<BreadCrumb>();
				if (root != null) {
					breadCrumbs.add(root);
				}
				urls.put(url, breadCrumbs);
				List<String> values = newUrls.get(urlList);
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("Found values for url %s: %s", url, LogUtils.formatCollection(values)));
				}
				if (values != null) {
					for (String value : values) {
						BreadCrumb breadCrumb = extractBreadCrumb(url, value);
						if (breadCrumb != null) {
							breadCrumbs.add(breadCrumb);
						}
					}
				}
			}
		}
	}

	public boolean isUseRootWhenUnfound() {
		return useRootWhenUnfound;
	}

	public void setUseRootWhenUnfound(boolean useRootWhenUnfound) {
		this.useRootWhenUnfound = useRootWhenUnfound;
	}

	private BreadCrumb extractBreadCrumb(String url, String value) {
		String[] urlValues = StringUtils.tokenizeToStringArray(value, "|");
		if (urlValues.length == 2) {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Found breadcrumbs %s:%s for url %s", urlValues[0], urlValues[1], url));
			}
			return new BreadCrumb(urlValues[0], urlValues[1]);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Didn't found any breadcrumbs for url %s", url));
		}
		return null;
	}

	@Override
	public List<BreadCrumb> getBreadCrumbs(String url) {
		return urls.get(url);
	}
}
