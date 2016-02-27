package com.infosys.hackathon.travelservice.json.containers;

import java.util.List;

import com.infosys.hackathon.services.JsonContainer;
import com.infosys.hackathon.services.directory.EmployeeDirectoryInformation;

public class EmployeeDirectoryInformationContainer implements JsonContainer {
	private List<EmployeeDirectoryInformation> employees;

	public List<EmployeeDirectoryInformation> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDirectoryInformation> employees) {
		this.employees = employees;
	}

}
