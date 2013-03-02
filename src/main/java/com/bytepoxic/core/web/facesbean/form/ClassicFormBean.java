package com.aureabox.webcore.web.facesbean.form;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.aureabox.webcore.util.FacesUtils;

@SuppressWarnings("serial")
@Component("formBean")
public abstract class ClassicFormBean extends GenericFormBean {
	protected boolean resetEnabled;
	protected boolean cancelEnabled;
	protected boolean saveEnabled;
	protected boolean updateEnabled;
	protected boolean removeEnabled;
	protected boolean showReset;
	protected boolean showCancel;
	protected boolean showSave;
	protected boolean showUpdate;
	protected boolean showRemove;
	protected ViewMode mode;

	public ClassicFormBean() {
		formType = FormType.CLASSIC_TYPE;
	}

	public boolean isResetHidden() {
		return !resetEnabled;
	}

	public boolean isCancelHidden() {
		return !cancelEnabled;
	}

	public boolean isSaveHidden() {
		return !saveEnabled;
	}

	public boolean isUpdateHidden() {
		return !updateEnabled;
	}

	public boolean isRemoveHidden() {
		return !removeEnabled;
	}

	public boolean isShowReset() {
		return showReset;
	}

	public boolean isShowCancel() {
		return showCancel;
	}

	public boolean isShowSave() {
		return showSave;
	}

	public boolean isShowUpdate() {
		return showUpdate;
	}

	public boolean isShowRemove() {
		return showRemove;
	}

	public ViewMode getMode() {
		return mode;
	}

	protected void setMode(ViewMode mode) {
		this.mode = mode;
	}

	protected void enableReadOnly() {
		saveEnabled = false;
		updateEnabled = false;
		removeEnabled = false;
		restrictReset();
		allowCancel();
	}

	protected void enableNew() {
		saveEnabled = true;
		updateEnabled = false;
		removeEnabled = false;
		allowReset();
		allowCancel();
	}

	protected void enableEdit() {
		saveEnabled = false;
		updateEnabled = true;
		removeEnabled = true;
		allowReset();
		allowCancel();
	}

	protected void toogleReadOnly() {
		setMode(ViewMode.READ_ONLY_MODE);
		showSave = false;
		showUpdate = false;
		showRemove = false;
		enableReadOnly();
		hideReset(false);
		showCancel(false);
	}

	protected void toogleNew() {
		setMode(ViewMode.NEW_MODE);
		showSave = true;
		showUpdate = false;
		showRemove = false;
		enableNew();
		showReset(false);
		showCancel(false);
	}

	protected void toogleEdit() {
		setMode(ViewMode.EDIT_MODE);
		showSave = false;
		showUpdate = true;
		showRemove = true;
		enableEdit();
		showReset(false);
		showCancel(false);
	}

	protected void showReset(boolean propagate) {
		showReset = true;
		if (propagate) {
			allowReset();
		}
	}

	protected void hideReset(boolean propagate) {
		showReset = false;
		if (propagate) {
			restrictReset();
		}
	}

	protected void showReset() {
		showReset(true);
	}

	protected void hideReset() {
		hideReset(true);
	}

	protected void allowReset() {
		resetEnabled = true;
	}

	protected void restrictReset() {
		resetEnabled = false;
	}

	protected void showCancel(boolean propagate) {
		showCancel = true;
		if (propagate) {
			allowCancel();
		}
	}

	protected void hideCancel(boolean propagate) {
		showCancel = false;
		if (propagate) {
			restrictCancel();
		}
	}

	protected void showCancel() {
		showCancel(true);
	}

	protected void hideCancel() {
		hideCancel(true);
	}

	protected void allowCancel() {
		cancelEnabled = true;
	}

	protected void restrictCancel() {
		cancelEnabled = false;
	}

	public int getCols() {
		int cols = 0;
		if (showReset) {
			cols++;
		}
		if (showCancel) {
			cols++;
		}
		if (showSave) {
			cols++;
		}
		if (showUpdate) {
			cols++;
		}
		if (showRemove) {
			cols++;
		}
		return cols;
	}

	public String getParam(String param) {
		return FacesUtils.getFromParams(param);
	}

	public String getIdParam() {
		String id = getParam(ID_PARAM);
		if (!StringUtils.hasText(id)) {
			id = getParam(getFormId() + PARAM_ID_DELMITER + ID_PARAM);
		}
		return id;
	}

	public String getCancel() {
		return getListActionUrl();
	}

	public boolean isReadOnly() {
		return mode == ViewMode.READ_ONLY_MODE;
	}

	public boolean isNew() {
		return mode == ViewMode.NEW_MODE;
	}

	public boolean isEdit() {
		return mode == ViewMode.EDIT_MODE;
	}
}
