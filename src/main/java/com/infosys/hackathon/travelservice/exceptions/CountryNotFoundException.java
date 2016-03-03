package com.infosys.hackathon.travelservice.exceptions;

import com.infosys.hackathon.services.exceptions.TravelAssistException;

public class CountryNotFoundException extends TravelAssistException {

	private static final long serialVersionUID = 1L;

	public CountryNotFoundException(String message) {
		super(message);
	}

}
