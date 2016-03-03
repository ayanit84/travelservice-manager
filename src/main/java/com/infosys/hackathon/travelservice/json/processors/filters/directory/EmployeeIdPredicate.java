package com.infosys.hackathon.travelservice.json.processors.filters.directory;

import org.springframework.cglib.core.Predicate;

import com.infosys.hackathon.services.directory.EmployeeDirectoryInformation;

public class EmployeeIdPredicate implements Predicate {

	private int expectedValue;

	public EmployeeIdPredicate(int expectedValue) {
		super();
		this.expectedValue = expectedValue;
	}

	@Override
	public boolean evaluate(Object object) {
		EmployeeDirectoryInformation emp = (EmployeeDirectoryInformation) object;
		return emp.getEmpNo() == expectedValue;
	}

}
