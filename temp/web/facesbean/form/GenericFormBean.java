package com.aureabox.webcore.web.facesbean.form;

import org.springframework.util.StringUtils;

import com.aureabox.webcore.util.FacesUtils;

@SuppressWarnings("serial")
public abstract class GenericFormBean implements FormBasedBean {
	protected FormType formType;

	public String getFormType() {
		return formType.getType();
	}

	public String getFormId() {
		return getEntityName() + FORM_SUFFIX;
	}

	private static String getListOperation(String name) {
		return LIST_ACTION + StringUtils.capitalize(name) + ENTITY_PLURAL_SUFFIX + EXTENSION;
	}

	private static String getNewOperation(String name) {
		return NEW_ACTION + StringUtils.capitalize(name) + EXTENSION;
	}

	private static String getViewOperation(String name) {
		return VIEW_ACTION + StringUtils.capitalize(name) + EXTENSION;
	}

	public String getListActionUrl() {
		return String.format("/faces/%s/%s?faces-redirect=true", getSubDomain(), getListOperation(getEntityName()));
	}

	public String getNewActionUrl() {
		return String.format("/faces/%s/%s?faces-redirect=true", getSubDomain(), getNewOperation(getEntityName()));
	}

	public String getViewActionUrl() {
		return String.format("/faces/%s/%s", getSubDomain(), getViewOperation(getEntityName()));
	}

	public String getViewActionUrl(String idParam, Long id, boolean includePath, boolean redirect) {
		idParam = StringUtils.hasText(idParam) ? idParam : ID_PARAM;
		String prefix = includePath ? FacesUtils.getReqContextPath() : "";
		String holder = redirect ? "/faces/%s/%s?faces-redirect=true&%s=%s" : "/faces/%s/%s?%s=%s";
		return prefix + String.format(holder, getSubDomain(), getViewOperation(getEntityName()), idParam, (id != null ? id : ""));
	}

	public String getViewActionUrl(String idParam, Long id, boolean includePath) {
		return getViewActionUrl(idParam, id, includePath, false);
	}

	public String getViewActionUrl(String idParam, Long id) {
		return getViewActionUrl(idParam, id, true);
	}

	public String getViewActionUrl(Long id, boolean includePath) {
		return getViewActionUrl(null, id, includePath);
	}

	public String getViewActionUrl(Long id) {
		return getViewActionUrl(null, id);
	}

	public String getRedirectViewActionUrl(String idParam, Long id, boolean includePath) {
		return getViewActionUrl(idParam, id, includePath, true);
	}

	public String getRedirectViewActionUrl(String idParam, Long id) {
		return getRedirectViewActionUrl(null, id, true);
	}

	public String getRedirectViewActionUrl(Long id, boolean includePath) {
		return getRedirectViewActionUrl(null, id, includePath);
	}

	public String getRedirectViewActionUrl(Long id) {
		return getRedirectViewActionUrl(id, false);
	}

	public String getMakeNew() {
		return getNewActionUrl();
	}

	protected abstract String getEntityName();

	protected abstract String getSubDomain();
}
