package com.infosys.hackathon.travelservice.json.processors.filters;

import org.springframework.cglib.core.Predicate;

import com.infosys.hackathon.services.directory.EmployeeOfficeAddress;

public class StatePredicate implements Predicate {

	private String expectedValue;

	public StatePredicate(String expectedValue) {
		super();
		this.expectedValue = expectedValue;
	}

	@Override
	public boolean evaluate(Object object) {
		EmployeeOfficeAddress ofc = (EmployeeOfficeAddress) object;
		return ofc.getState().equals(expectedValue);
	}

}
