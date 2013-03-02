package com.aureabox.webcore.util;

import javax.faces.application.FacesMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

public class MessageUtils {
	private static Log logger = LogFactory.getLog(MessageUtils.class);

	public static void addMessage(FacesMessage.Severity severity, String bundleName, String summary, Object[] summaryParams, String msg, Object[] params) {
		if (!StringUtils.hasText(summary) && !StringUtils.hasText(msg)) {
			logger.warn("Ignoring message creation since both summary and message are empty");
			return;
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Adding message %s|%s|%s|%s|%s", severity, bundleName, summary, msg, params));
		}
		boolean hasBundle = StringUtils.hasText(bundleName);
		String msgSummary = summary;
		String text = msg;
		if (hasBundle) {
			msgSummary = FacesUtils.getBundleText(bundleName, summary, summaryParams);
			text = FacesUtils.getBundleText(bundleName, msg, params);
		}
		FacesMessage facesMsg = new FacesMessage(severity, msgSummary, text);
		FacesUtils.getContext().addMessage(null, facesMsg);
	}

	public static void addMessage(FacesMessage.Severity severity, String bundleName, String summary, Object[] summaryParams, String msg) {
		addMessage(severity, bundleName, summary, summaryParams, msg, null);
	}

	public static void addMessage(FacesMessage.Severity severity, String bundleName, String summary, String msg, Object[] params) {
		addMessage(severity, bundleName, summary, null, msg, params);
	}

	public static void addMessage(FacesMessage.Severity severity, String bundleName, String msg, Object[] params) {
		addMessage(severity, bundleName, null, msg, params);
	}

	public static void addMessage(FacesMessage.Severity severity, String bundleName, String msg) {
		addMessage(severity, bundleName, msg, (Object[]) null);
	}

	public static void addMessage(FacesMessage.Severity severity, String bundleName, String summary, String msg) {
		addMessage(severity, bundleName, summary, null, msg, null);
	}

	public static void addMessage(FacesMessage.Severity severity, String msg) {
		addMessage(severity, null, null, msg);
	}

	public static void addSuccessMessage(String bundleName, String summary, Object[] summaryParams, String msg, Object[] params) {
		addMessage(FacesMessage.SEVERITY_INFO, bundleName, summary, summaryParams, msg, params);
	}

	public static void addSuccessMessage(String bundleName, String summary, Object[] summaryParams, String msg) {
		addSuccessMessage(bundleName, summary, summaryParams, msg, null);
	}

	public static void addSuccessMessage(String bundleName, String summary, String msg, Object[] params) {
		addSuccessMessage(bundleName, summary, null, msg, params);
	}

	public static void addSuccessMessage(String bundleName, String msg, Object[] params) {
		addSuccessMessage(bundleName, null, msg, params);
	}

	public static void addSuccessMessage(String bundleName, String msg) {
		addSuccessMessage(bundleName, msg, (Object[]) null);
	}

	public static void addSuccessMessage(String bundleName, String summary, String msg) {
		addSuccessMessage(bundleName, summary, null, msg, null);
	}

	public static void addSuccessMessage(String msg) {
		addSuccessMessage(null, null, msg);
	}

	public static void addErrorMessage(String bundleName, String summary, Object[] summaryParams, String msg, Object[] params) {
		addMessage(FacesMessage.SEVERITY_ERROR, bundleName, summary, summaryParams, msg, params);
	}

	public static void addErrorMessage(String bundleName, String summary, Object[] summaryParams, String msg) {
		addErrorMessage(bundleName, summary, summaryParams, msg, null);
	}

	public static void addErrorMessage(String bundleName, String summary, String msg, Object[] params) {
		addErrorMessage(bundleName, summary, null, msg, params);
	}

	public static void addErrorMessage(String bundleName, String msg, Object[] params) {
		addErrorMessage(bundleName, null, msg, params);
	}

	public static void addErrorMessage(String bundleName, String msg) {
		addErrorMessage(bundleName, null, msg, (Object[]) null);
	}

	public static void addErrorMessage(String bundleName, String summary, String msg) {
		addErrorMessage(bundleName, summary, null, msg, null);
	}

	public static void addErrorMessage(String msg) {
		addErrorMessage(null, null, msg);
	}
}
