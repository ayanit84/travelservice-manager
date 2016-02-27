package com.infosys.hackathon.travelservice.json.processors.directory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.infosys.hackathon.services.directory.EmployeeOfficeAddress;
import com.infosys.hackathon.travelservice.exceptions.JsonDatabaseException;
import com.infosys.hackathon.travelservice.json.containers.EmployeeOfficeAddressContainer;
import com.infosys.hackathon.travelservice.json.processors.JsonProcessor;
import com.infosys.hackathon.travelservice.json.util.JsonLoader;

@Component
public class DirectoryOfficeJsonProcessor implements
	JsonProcessor<EmployeeOfficeAddressContainer, EmployeeOfficeAddress> {

	private static final String JSON_DATABASE = "jsondata/directory/office.json";

	private EmployeeOfficeAddressContainer jsonContainer;

	private JsonLoader<EmployeeOfficeAddressContainer> jsonLoader;

	public DirectoryOfficeJsonProcessor() {
		jsonLoader = new JsonLoader<EmployeeOfficeAddressContainer>();
	}

	@Override
	public String getJsonFileName() {
		return JSON_DATABASE;
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

	public static void main(String[] args) {
		try {
			DirectoryOfficeJsonProcessor proc = new DirectoryOfficeJsonProcessor();
			List<EmployeeOfficeAddress> ofc = proc.getData();
			for (EmployeeOfficeAddress employeeOfficeAddress : ofc) {
				System.out.println(employeeOfficeAddress);
			}
		} catch (JsonDatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
