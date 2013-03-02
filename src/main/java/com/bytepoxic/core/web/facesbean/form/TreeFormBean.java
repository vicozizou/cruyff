package com.aureabox.webcore.web.facesbean.form;

import org.springframework.util.StringUtils;

@SuppressWarnings("serial")
public abstract class TreeFormBean extends ClassicFormBean implements TreeFormBasedBean {
	protected boolean newEnabled = false;
	protected boolean showNew = false;

	public TreeFormBean() {
		formType = FormType.TREE_TYPE;
	}

	public boolean isNewEnabled() {
		return newEnabled;
	}

	public boolean isNewHidden() {
		return !newEnabled;
	}

	public boolean isShowNew() {
		return showNew;
	}

	protected void enableNew() {
		super.enableNew();
		newEnabled = false;
	}

	protected void enableEdit() {
		super.enableEdit();
		newEnabled = true;
	}

	protected void enableReadOnly() {
		super.enableReadOnly();
		newEnabled = false;
	}

	protected void toogleEdit() {
		super.toogleEdit();
		showNew = true;
	}

	protected void toogleNew() {
		super.toogleNew();
		showNew = false;
	}

	protected void toogleReadOnly() {
		super.toogleReadOnly();
		showNew = false;
	}

	public int getCols() {
		int cols = super.getCols();
		if (showNew) {
			cols++;
		}
		return cols;
	}

	public String getParentIdParam() {
		String id = getParam(PARENT_ID_PARAM);
		if (!StringUtils.hasText(id)) {
			id = getParam(getFormId() + PARAM_ID_DELMITER + PARENT_ID_PARAM);
		}
		return id;
	}

	public String getMakeNew() {
		return super.getMakeNew() + String.format("&%s=%s", PARENT_ID_PARAM, getParentId());
	}

	protected abstract Long getParentId();
}
