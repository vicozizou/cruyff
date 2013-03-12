package com.aureabox.webcore.web.facesbean.form;

public enum FormType {
	CLASSIC_TYPE("classic"), LIST_TYPE("type"), TREE_TYPE("tree");

	private String type;

	private FormType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
