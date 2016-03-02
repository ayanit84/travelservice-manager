package com.infosys.hackathon.travelservice.json.processors.filters.contact;

import org.springframework.cglib.core.Predicate;

import com.infosys.hackathon.services.contact.ContactInformation;

public class CountryPredicate implements Predicate {

	private String expectedValue;

	public CountryPredicate(String expectedValue) {
		super();
		this.expectedValue = expectedValue;
	}

	@Override
	public boolean evaluate(Object object) {
		ContactInformation country = (ContactInformation) object;
		return country.getCountry().equals(expectedValue);
	}

}
