package com.ecs.exceptions;

public class SomethingWentWrongException  extends RuntimeException{

	public SomethingWentWrongException() {
		super();
		
	}

	public SomethingWentWrongException(String message) {
		super(message);
		
	}

}
