package com.infosys.hackathon.travelservice.json.processors.filters.directory;

import org.springframework.cglib.core.Predicate;

import com.infosys.hackathon.services.directory.EmployeeOfficeAddress;

public class CityPredicate implements Predicate {

	private String expectedValue;

	public CityPredicate(String expectedValue) {
		super();
		this.expectedValue = expectedValue;
	}

	@Override
	public boolean evaluate(Object object) {
		EmployeeOfficeAddress ofc = (EmployeeOfficeAddress) object;
		return ofc.getCity().equals(expectedValue);
	}

}
