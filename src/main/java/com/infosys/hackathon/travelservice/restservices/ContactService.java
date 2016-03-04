package com.infosys.hackathon.travelservice.restservices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.hackathon.services.ResultCodes;
import com.infosys.hackathon.services.contact.ContactInformation;
import com.infosys.hackathon.services.contact.SearchResponse;
import com.infosys.hackathon.services.directory.EmployeeDirectoryInformation;
import com.infosys.hackathon.services.directory.dto.SearchParameter;
import com.infosys.hackathon.travelservice.exceptions.JsonLookupException;
import com.infosys.hackathon.travelservice.json.processors.contact.ContactJsonProcessor;
import com.infosys.hackathon.travelservice.json.processors.directory.DirectoryEmployeeJsonProcessor;

@RestController
@RequestMapping("/service/contact")
public class ContactService {

	private static final Logger LOGGER = Logger.getLogger(ContactService.class);

	@Autowired
	private ContactJsonProcessor contactProcessor;

	@Autowired
	private DirectoryEmployeeJsonProcessor employeeProcessor;

	@RequestMapping(value = "/fetch/{country}", method = RequestMethod.GET)
	public SearchResponse searchContact(@PathVariable String country) {
		LOGGER.info("search country: " + country);
		SearchResponse response = new SearchResponse();

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(SearchParameter.Country.getKey(), country);
			List<ContactInformation> countries = contactProcessor
				.lookup(params);

			for (ContactInformation contactInfo : countries) {
				Map<String, Object> bphrParams = new HashMap<String, Object>();
				bphrParams.put(SearchParameter.EmpId.getKey(),
					contactInfo.getbphr());
				List<EmployeeDirectoryInformation> bphrList = employeeProcessor
					.lookupEmp(bphrParams);
				response.setBphr(bphrList.get(0));

				Map<String, Object> hocParams = new HashMap<String, Object>();
				hocParams.put(SearchParameter.EmpId.getKey(),
					contactInfo.getCountryHead());
				List<EmployeeDirectoryInformation> hocList = employeeProcessor
					.lookupEmp(hocParams);
				response.setCountryHead(hocList.get(0));

			}

			response.setResult(ResultCodes.Success);
			response.setCountry(countries.get(0));

		} catch (JsonLookupException e) {
			LOGGER.error("error in search service" + e.getMessage());
			response.setResult(ResultCodes.Failure);
		}

		return response;
	}
}
