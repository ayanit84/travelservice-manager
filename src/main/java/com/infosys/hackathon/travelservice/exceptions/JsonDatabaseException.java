package com.infosys.hackathon.travelservice.exceptions;

import com.infosys.hackathon.services.exceptions.TravelAssistException;

public class JsonDatabaseException extends TravelAssistException {

	private static final long serialVersionUID = 1L;

	public JsonDatabaseException(String message) {
		super(message);
	}
}
