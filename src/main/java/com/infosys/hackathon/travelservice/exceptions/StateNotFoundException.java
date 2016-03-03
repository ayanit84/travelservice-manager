package com.infosys.hackathon.travelservice.exceptions;

import com.infosys.hackathon.services.exceptions.TravelAssistException;

public class StateNotFoundException extends TravelAssistException {

	private static final long serialVersionUID = 1L;

	public StateNotFoundException(String message) {
		super(message);
	}

}
