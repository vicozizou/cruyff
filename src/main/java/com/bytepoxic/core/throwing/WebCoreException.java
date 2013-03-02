package com.aureabox.webcore.throwing;

@SuppressWarnings("serial")
public abstract class WebCoreException extends Exception {
	public WebCoreException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public WebCoreException(String arg0) {
		super(arg0);
	}

	public WebCoreException(Throwable arg0) {
		super(arg0);
	}
}
