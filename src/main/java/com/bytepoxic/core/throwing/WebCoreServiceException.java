package com.aureabox.webcore.throwing;

@SuppressWarnings("serial")
public class WebCoreServiceException extends WebCoreException {
	public WebCoreServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public WebCoreServiceException(String arg0) {
		super(arg0);
	}

	public WebCoreServiceException(Throwable arg0) {
		super(arg0);
	}
}
