package com.ecs.exceptions;

public class UserNotLoggedInException extends RuntimeException{

	public UserNotLoggedInException() {
		super();
		
	}

	public UserNotLoggedInException(String message) {
		super(message);
		
	}

}
