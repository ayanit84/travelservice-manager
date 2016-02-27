package com.infosys.hackathon.travelservice.json.containers;

import java.util.List;

import com.infosys.hackathon.services.JsonContainer;
import com.infosys.hackathon.services.directory.EmployeeOfficeAddress;

public class EmployeeOfficeAddressContainer implements JsonContainer {
	private List<EmployeeOfficeAddress> offices;

	public List<EmployeeOfficeAddress> getOffices() {
		return offices;
	}

	public void setOffices(List<EmployeeOfficeAddress> offices) {
		this.offices = offices;
	}
}
