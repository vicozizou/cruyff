package com.bytepoxic.core.throwing;

@SuppressWarnings("serial")
public class CoreServiceException extends CoreException {
	public CoreServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CoreServiceException(String arg0) {
		super(arg0);
	}

	public CoreServiceException(Throwable arg0) {
		super(arg0);
	}
}
