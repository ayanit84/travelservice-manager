package com.infosys.hackathon.travelservice.restservices;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.hackathon.services.ResultCodes;
import com.infosys.hackathon.services.directory.dto.SearchResponse;
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

	@RequestMapping(value = "/{country}/{state}/{city}", method = RequestMethod.GET)
	public SearchResponse search(@PathVariable String country,
		@PathVariable String state, @PathVariable String city) {
		LOGGER.info("search request: " + country);
		// TODO
		SearchResponse response = new SearchResponse();
		response.setResult(ResultCodes.Success);
		return response;
	}

}
