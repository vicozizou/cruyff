package com.bytepoxic.core.throwing;

@SuppressWarnings("serial")
public abstract class CoreException extends Exception {
	public CoreException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CoreException(String arg0) {
		super(arg0);
	}

	public CoreException(Throwable arg0) {
		super(arg0);
	}
}
