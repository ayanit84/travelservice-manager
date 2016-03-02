package com.infosys.hackathon.travelservice.json.processors.filters.policy;

import org.springframework.cglib.core.Predicate;

import com.infosys.hackathon.services.policy.EligibilityInformation;

public class CountryPredicate implements Predicate {

	private String expectedValue;

	public CountryPredicate(String expectedValue) {
		super();
		this.expectedValue = expectedValue;
	}

	@Override
	public boolean evaluate(Object object) {
			EligibilityInformation data = (EligibilityInformation) object;
			return data.getCountry().equals(expectedValue);
	}

}
