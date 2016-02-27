package com.infosys.hackathon.travelservice.json.processors.directory;

import java.util.List;

import com.infosys.hackathon.services.directory.EmployeeOfficeAddress;
import com.infosys.hackathon.travelservice.exceptions.JsonDatabaseException;
import com.infosys.hackathon.travelservice.json.containers.EmployeeOfficeAddressContainer;
import com.infosys.hackathon.travelservice.json.processors.JsonProcessor;
import com.infosys.hackathon.travelservice.json.util.JsonLoader;

public class DirectoryOfficeJsonProcessor implements
	JsonProcessor<EmployeeOfficeAddressContainer, EmployeeOfficeAddress> {

	private EmployeeOfficeAddressContainer jsonContainer;

	private JsonLoader<EmployeeOfficeAddressContainer> jsonLoader;

	public DirectoryOfficeJsonProcessor() {
		jsonLoader = new JsonLoader<EmployeeOfficeAddressContainer>();
	}

	@Override
	public String getJsonFileName() {
		return "jsondata/directory/office.json";
	}

	@Override
	public EmployeeOfficeAddressContainer getContainer() {
		return jsonContainer;
	}

	@Override
	public List<EmployeeOfficeAddress> getData() throws JsonDatabaseException {
		this.jsonContainer = jsonLoader.load(getJsonFileName(),
			EmployeeOfficeAddressContainer.class);
		return this.jsonContainer.getOffices();
	}

}
