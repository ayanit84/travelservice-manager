package com.infosys.hackathon.travelservice.json.processors.filters.country;

import org.springframework.cglib.core.Predicate;

import com.infosys.hackathon.services.country.CountryDetails;

public class CountryPredicate implements Predicate {

	private String expectedValue;

	public CountryPredicate(String expectedValue) {
		super();
		this.expectedValue = expectedValue;
	}

	@Override
	public boolean evaluate(Object object) {
		CountryDetails country = (CountryDetails) object;
		return country.getCode().equals(expectedValue);
	}

}
