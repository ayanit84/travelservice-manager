package com.infosys.hackathon.travelservice.json.containers;

import java.util.List;

import com.infosys.hackathon.services.JsonContainer;
import com.infosys.hackathon.services.directory.EmployeDirectoryInformation;

public class EmployeeDirectoryInformationContainer implements JsonContainer {
	private List<EmployeDirectoryInformation> employees;

	public List<EmployeDirectoryInformation> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeDirectoryInformation> employees) {
		this.employees = employees;
	}

}
