package com.infosys.hackathon.travelservice.json.processors.directory;

import java.util.List;

import com.infosys.hackathon.services.directory.EmployeDirectoryInformation;
import com.infosys.hackathon.travelservice.exceptions.JsonDatabaseException;
import com.infosys.hackathon.travelservice.json.containers.EmployeeDirectoryInformationContainer;
import com.infosys.hackathon.travelservice.json.processors.JsonProcessor;

public class DirectoryEmployeeJsonProcessor implements
	JsonProcessor<EmployeeDirectoryInformationContainer, EmployeDirectoryInformation> {

	@Override
	public String getJsonFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeDirectoryInformationContainer getContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeDirectoryInformation> getData() throws JsonDatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

}