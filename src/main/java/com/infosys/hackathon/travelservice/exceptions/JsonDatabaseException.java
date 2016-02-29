package com.infosys.hackathon.travelservice.exceptions;

public class JsonDatabaseException extends TravelAssistException {

	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public JsonDatabaseException(String message) {
		this.errorMessage = message;
	}

	@Override
	public String getMessage() {
		return this.errorMessage;
	}
}
