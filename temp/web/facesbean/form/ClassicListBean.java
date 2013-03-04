package com.aureabox.webcore.web.facesbean.form;

@SuppressWarnings("serial")
public abstract class ClassicListBean extends GenericFormBean {
	protected boolean newEnabled = true;
	protected boolean removeEnabled = true;
	protected boolean refreshEnabled = true;
	protected boolean showNew = true;
	protected boolean showRemove = true;
	protected boolean showRefresh = true;

	public ClassicListBean() {
		formType = FormType.LIST_TYPE;
	}

	public boolean isNewHidden() {
		return !newEnabled;
	}

	public void setNewEnabled(boolean newEnabled) {
		this.newEnabled = newEnabled;
	}

	public boolean isRemoveHidden() {
		return !removeEnabled;
	}

	public void setRemoveEnabled(boolean removeEnabled) {
		this.removeEnabled = removeEnabled;
	}

	public boolean isRefreshHidden() {
		return !refreshEnabled;
	}

	public void setRefreshEnabled(boolean refreshEnabled) {
		this.refreshEnabled = refreshEnabled;
	}

	public boolean isShowNew() {
		return showNew;
	}

	public void setShowNew(boolean showNew) {
		this.showNew = showNew;
	}

	public boolean isShowRemove() {
		return showRemove;
	}

	public void setShowRemove(boolean showRemove) {
		this.showRemove = showRemove;
	}

	public boolean isShowRefresh() {
		return showRefresh;
	}

	public void setShowRefresh(boolean showRefresh) {
		this.showRefresh = showRefresh;
	}

	public int getCols() {
		int cols = 0;
		if (showNew) {
			cols++;
		}
		if (showRefresh) {
			cols++;
		}
		if (showRemove) {
			cols++;
		}
		return cols;
	}

	public String getViewUrl() {
		return getViewActionUrl();
	}

	public String getViewUrl(Long id) {
		return getViewActionUrl(id);
	}
}
