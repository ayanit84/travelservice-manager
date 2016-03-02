package com.infosys.hackathon.travelservice.json.processors.directory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Component;

import com.infosys.hackathon.services.directory.EmployeeOfficeAddress;
import com.infosys.hackathon.services.directory.dto.SearchParameter;
import com.infosys.hackathon.services.directory.dto.SearchRequest;
import com.infosys.hackathon.travelservice.exceptions.JsonDatabaseException;
import com.infosys.hackathon.travelservice.exceptions.JsonLookupException;
import com.infosys.hackathon.travelservice.json.containers.EmployeeOfficeAddressContainer;
import com.infosys.hackathon.travelservice.json.processors.JsonProcessor;
import com.infosys.hackathon.travelservice.json.processors.filters.directory.CityPredicate;
import com.infosys.hackathon.travelservice.json.processors.filters.directory.CountryPredicate;
import com.infosys.hackathon.travelservice.json.processors.filters.directory.StatePredicate;
import com.infosys.hackathon.travelservice.json.util.JsonLoader;

@Component
public class DirectoryOfficeJsonProcessor implements
		JsonProcessor<EmployeeOfficeAddressContainer, EmployeeOfficeAddress> {

	private static final String JSON_DATABASE = "jsondata/directory/office.json";

	private EmployeeOfficeAddressContainer jsonContainer;

	private JsonLoader<EmployeeOfficeAddressContainer> jsonLoader;

	public DirectoryOfficeJsonProcessor() throws JsonDatabaseException {
		jsonLoader = new JsonLoader<EmployeeOfficeAddressContainer>();
		this.jsonContainer = jsonLoader.load(getJsonFileName(),
				EmployeeOfficeAddressContainer.class);
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
	public List<EmployeeOfficeAddress> getData() {
		return this.jsonContainer.getOffices();
	}

	@Override
	public List<EmployeeOfficeAddress> lookup(Map<String, Object> searchParams)
			throws JsonLookupException {
		List<EmployeeOfficeAddress> filteredOffices = null;

		if (!searchParams.containsKey(SearchParameter.Country.getKey())) {
			throw new JsonLookupException("Invalid Parameter");
		}
		try {
			filteredOffices = new ArrayList<EmployeeOfficeAddress>(getData());
			CollectionUtils.filter(filteredOffices, new CountryPredicate(
					searchParams.get(SearchParameter.Country.getKey())
							.toString()));
			if (searchParams.containsKey(SearchParameter.State.getKey())) {
				CollectionUtils.filter(filteredOffices, new StatePredicate(
						searchParams.get(SearchParameter.State.getKey())
								.toString()));
			}
			if (searchParams.containsKey(SearchParameter.City.getKey())) {
				CollectionUtils.filter(filteredOffices, new CityPredicate(
						searchParams.get(SearchParameter.City.getKey())
								.toString()));
			}
		} catch (Exception e) {
			throw new JsonLookupException(e.getMessage());
		}
		return filteredOffices;
	}

	public static void main(String[] args) {
		try {
			DirectoryOfficeJsonProcessor proc = new DirectoryOfficeJsonProcessor();
			List<EmployeeOfficeAddress> ofc = proc.lookup(new SearchRequest(
					"IN", "Odisha", "Bhubaneswar").getMap());
			for (EmployeeOfficeAddress employeeOfficeAddress : ofc) {
				System.out.println(employeeOfficeAddress);
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
