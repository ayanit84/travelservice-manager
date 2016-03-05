package com.infosys.hackathon.travelservice.json.processors.filters.dos;

import org.springframework.cglib.core.Predicate;

import com.infosys.hackathon.services.dos.OnsiteActivity;

public class CountryPredicate implements Predicate {

	private String expectedValue;

	public CountryPredicate(String expectedValue) {
		super();
		this.expectedValue = expectedValue;
	}

	@Override
	public boolean evaluate(Object object) {
		OnsiteActivity activity = (OnsiteActivity) object;
		return activity.getCountry().equals(expectedValue);
	}

}
