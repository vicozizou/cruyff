package com.aureabox.webcore.web.facesbean.form;

import java.io.Serializable;

public interface FormBasedBean extends Serializable {
	String LIST_ACTION = "list";
	String NEW_ACTION = "create";
	String VIEW_ACTION = "view";
	String FORM_SUFFIX = "Form";
	String EXTENSION = ".jsf";
	String ID_PARAM = "id";
	String ENTITY_PLURAL_SUFFIX = "s";
	String PARAM_ID_DELMITER = ":";

	String getFormId();
	int getCols();
	String getListActionUrl();
	String getNewActionUrl();
	String getViewActionUrl(String idParam, Long id, boolean includePath);
	String getViewActionUrl(String paramId, Long id);
	String getViewActionUrl(Long id, boolean includePath);
	String getViewActionUrl(Long id);
	String getFormType();
}
