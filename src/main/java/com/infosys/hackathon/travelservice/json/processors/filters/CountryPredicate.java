package com.infosys.hackathon.travelservice.json.processors.filters;

import org.springframework.cglib.core.Predicate;

import com.infosys.hackathon.services.directory.EmployeeOfficeAddress;
import com.infosys.hackathon.services.policy.EligibilityInformation;

public class CountryPredicate implements Predicate {

	private String expectedValue;

	public CountryPredicate(String expectedValue) {
		super();
		this.expectedValue = expectedValue;
	}

	@Override
	public boolean evaluate(Object object) {
		boolean retVal = false;
		if (object instanceof EmployeeOfficeAddress) {
			EmployeeOfficeAddress ofc = (EmployeeOfficeAddress) object;
			retVal = ofc.getCountry().equals(expectedValue);
		} else if (object instanceof EligibilityInformation) {
			EligibilityInformation data = (EligibilityInformation) object;
			retVal = data.getCountry().equals(expectedValue);
		}
		
		return retVal;
	}

}
