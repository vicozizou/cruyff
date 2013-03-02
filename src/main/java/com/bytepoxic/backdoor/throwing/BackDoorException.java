package com.aureabox.backdoor.throwing;

@SuppressWarnings("serial")
public class BackDoorException extends Exception {

	public BackDoorException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public BackDoorException(String arg0) {
		super(arg0);
	}

}
