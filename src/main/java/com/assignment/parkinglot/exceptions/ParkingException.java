package com.assignment.parkinglot.exceptions;

public class ParkingException extends Exception {
	private static final long serialVersionUID = 1L;

	public ParkingException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParkingException(String message) {
		super(message);
	}

}
