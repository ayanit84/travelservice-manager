package com.infosys.hackathon.travelservice.restservices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.hackathon.services.ResultCodes;
import com.infosys.hackathon.services.contact.ContactInformation;
import com.infosys.hackathon.services.contact.SearchResponse;
import com.infosys.hackathon.services.directory.dto.SearchParameter;
import com.infosys.hackathon.travelservice.exceptions.JsonLookupException;
import com.infosys.hackathon.travelservice.json.processors.contact.ContactJsonProcessor;
import com.infosys.hackathon.travelservice.json.processors.directory.DirectoryEmployeeJsonProcessor;

@RestController
@RequestMapping("/service/contact")
public class ContactService {

	private static final Logger LOGGER = Logger
			.getLogger(ContactService.class);

	@Autowired
	private ContactJsonProcessor contactProcessor;

	@Autowired
	private DirectoryEmployeeJsonProcessor employeeProcessor;

	@RequestMapping(value = "/fetch", method = RequestMethod.POST, headers = "content-type=application/json")
	public SearchResponse searchContact(@RequestParam String country) {
		LOGGER.info("search country: " + country);
		SearchResponse response = new SearchResponse();
		List<ContactInformation> searchedCountry = null;

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(SearchParameter.Country.getKey(), country);
			List<ContactInformation> offices = contactProcessor.lookup(params);
//			searchedEmployees = new ArrayList<ContactInformation>();
//			Map<String, Object> searchParams = new HashMap<String, Object>();
//			for (EmployeeOfficeAddress ofc : offices) {
//				searchParams.put(SearchParameter.officeId.getKey(),
//						ofc.getOfficeId());
//				List<EmployeeDirectoryInformation> tempList = employeeProcessor
//						.lookup(searchParams);
//
//				searchedEmployees.addAll(tempList);
//			}

			response.setResult(ResultCodes.Success);
			response.setCountry(offices.get(0));
//			LOGGER.info("successfully processed the search request, returning "
//					+ searchedCountry.size() + " employees");

		} catch (JsonLookupException e) {
			LOGGER.error("error in search service" + e.getMessage());
			response.setResult(ResultCodes.Failure);
		}

		return response;
	}
}
