package com.infosys.hackathon.travelservice.json.processors.directory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.infosys.hackathon.services.directory.EmployeeDirectoryInformation;
import com.infosys.hackathon.travelservice.exceptions.JsonDatabaseException;
import com.infosys.hackathon.travelservice.json.containers.EmployeeDirectoryInformationContainer;
import com.infosys.hackathon.travelservice.json.processors.JsonProcessor;
import com.infosys.hackathon.travelservice.json.util.JsonLoader;

@Component
public class DirectoryEmployeeJsonProcessor implements
	JsonProcessor<EmployeeDirectoryInformationContainer, EmployeeDirectoryInformation> {
	private static final String JSON_DATABASE = "jsondata/directory/employee.json";

	private EmployeeDirectoryInformationContainer jsonContainer;

	private JsonLoader<EmployeeDirectoryInformationContainer> jsonLoader;

	public DirectoryEmployeeJsonProcessor() {
		this.jsonLoader = new JsonLoader<EmployeeDirectoryInformationContainer>();
	}

	@Override
	public String getJsonFileName() {
		return JSON_DATABASE;
	}

	@Override
	public EmployeeDirectoryInformationContainer getContainer() {
		return this.jsonContainer;
	}

	@Override
	public List<EmployeeDirectoryInformation> getData()
		throws JsonDatabaseException {
		this.jsonContainer = this.jsonLoader.load(getJsonFileName(),
			EmployeeDirectoryInformationContainer.class);
		return this.jsonContainer.getEmployees();
	}

	public static void main(String[] args) {
		try {
			DirectoryEmployeeJsonProcessor proc = new DirectoryEmployeeJsonProcessor();
			List<EmployeeDirectoryInformation> ofc = proc.getData();
			for (EmployeeDirectoryInformation employeeOfficeAddress : ofc) {
				System.out.println(employeeOfficeAddress);
			}
		} catch (JsonDatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}