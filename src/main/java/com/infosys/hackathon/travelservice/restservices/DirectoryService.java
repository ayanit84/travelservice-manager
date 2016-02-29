package com.infosys.hackathon.travelservice.restservices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.hackathon.services.ResultCodes;
import com.infosys.hackathon.services.directory.EmployeeDirectoryInformation;
import com.infosys.hackathon.services.directory.EmployeeOfficeAddress;
import com.infosys.hackathon.services.directory.dto.SearchParameter;
import com.infosys.hackathon.services.directory.dto.SearchRequest;
import com.infosys.hackathon.services.directory.dto.SearchResponse;
import com.infosys.hackathon.travelservice.exceptions.JsonLookupException;
import com.infosys.hackathon.travelservice.json.processors.directory.DirectoryEmployeeJsonProcessor;
import com.infosys.hackathon.travelservice.json.processors.directory.DirectoryOfficeJsonProcessor;

@RestController
@RequestMapping("/service/directory")
public class DirectoryService {

	private static final Logger LOGGER = Logger
			.getLogger(DirectoryService.class);

	@Autowired
	private DirectoryEmployeeJsonProcessor employeeProcessor;

	@Autowired
	private DirectoryOfficeJsonProcessor officeProcessor;

	@RequestMapping(value = "/search", method = RequestMethod.POST, headers = "content-type=application/json")
	public SearchResponse searchEmployee(
			@RequestBody SearchRequest searchRequest) {
		LOGGER.info("search request: " + searchRequest);
		SearchResponse response = new SearchResponse();
		List<EmployeeDirectoryInformation> searchedEmployees = null;

		try {

			List<EmployeeOfficeAddress> offices = officeProcessor
					.lookup(searchRequest.getMap());
			searchedEmployees = new ArrayList<EmployeeDirectoryInformation>();
			Map<String, Object> searchParams = new HashMap<String, Object>();
			for (EmployeeOfficeAddress ofc : offices) {
				searchParams.put(SearchParameter.officeId.getKey(),
						ofc.getOfficeId());
				List<EmployeeDirectoryInformation> tempList = employeeProcessor
						.lookup(searchParams);

				searchedEmployees.addAll(tempList);
			}

			response.setResult(ResultCodes.Success);
			response.setEmployees(searchedEmployees);
			LOGGER.info("successfully processed the search request, returning "
					+ searchedEmployees.size() + " employees");

		} catch (JsonLookupException e) {
			LOGGER.error("error in search service" + e.getMessage());
			response.setResult(ResultCodes.Failure);
		}

		return response;
	}
}
