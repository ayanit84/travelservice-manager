package com.infosys.hackathon.travelservice.json.processors.directory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Component;

import com.infosys.hackathon.services.directory.EmployeeDirectoryInformation;
import com.infosys.hackathon.services.directory.dto.SearchParameter;
import com.infosys.hackathon.travelservice.exceptions.JsonDatabaseException;
import com.infosys.hackathon.travelservice.exceptions.JsonLookupException;
import com.infosys.hackathon.travelservice.json.containers.EmployeeDirectoryInformationContainer;
import com.infosys.hackathon.travelservice.json.processors.JsonProcessor;
import com.infosys.hackathon.travelservice.json.processors.filters.directory.OfficeIdPredicate;
import com.infosys.hackathon.travelservice.json.util.JsonLoader;

@Component
public class DirectoryEmployeeJsonProcessor
		implements
		JsonProcessor<EmployeeDirectoryInformationContainer, EmployeeDirectoryInformation> {

	private static final String JSON_DATABASE = "jsondata/directory/employee.json";

	private EmployeeDirectoryInformationContainer jsonContainer;

	private JsonLoader<EmployeeDirectoryInformationContainer> jsonLoader;

	public DirectoryEmployeeJsonProcessor() throws JsonDatabaseException {
		this.jsonLoader = new JsonLoader<EmployeeDirectoryInformationContainer>();
		this.jsonContainer = this.jsonLoader.load(getJsonFileName(),
				EmployeeDirectoryInformationContainer.class);
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
	public List<EmployeeDirectoryInformation> getData() {
		return this.jsonContainer.getEmployees();
	}

	@Override
	public List<EmployeeDirectoryInformation> lookup(
			Map<String, Object> searchParams) throws JsonLookupException {
		List<EmployeeDirectoryInformation> filteredEmployees = null;
		try {
			filteredEmployees = new ArrayList<EmployeeDirectoryInformation>(
					getData());
			CollectionUtils.filter(
					filteredEmployees,
					new OfficeIdPredicate((Integer) searchParams
							.get(SearchParameter.officeId.getKey())));
		} catch (Exception e) {
			throw new JsonLookupException(e.getMessage());
		}
		return filteredEmployees;
	}

	public static void main(String[] args) {
		try {
			DirectoryEmployeeJsonProcessor proc = new DirectoryEmployeeJsonProcessor();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(SearchParameter.officeId.getKey(), 56);
			List<EmployeeDirectoryInformation> ofc = proc.lookup(params);
			for (EmployeeDirectoryInformation emp : ofc) {
				System.out.println(emp);
			}
		} catch (JsonDatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonLookupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}