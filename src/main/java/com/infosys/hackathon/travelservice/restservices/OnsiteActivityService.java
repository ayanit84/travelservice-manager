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
import com.infosys.hackathon.services.directory.dto.SearchParameter;
import com.infosys.hackathon.services.dos.OnsiteActivity;
import com.infosys.hackathon.services.dos.dto.OnsiteActivityResponse;
import com.infosys.hackathon.travelservice.exceptions.JsonLookupException;
import com.infosys.hackathon.travelservice.json.processors.OnsiteActivityJsonProcessor;

@RestController
@RequestMapping("/service/activity")
public class OnsiteActivityService {
	private static Logger LOGGER = Logger.getLogger(OnsiteActivityService.class);

	@Autowired
	private OnsiteActivityJsonProcessor jsonProcessor;

	@RequestMapping(value = "/fetch/{country}", method = RequestMethod.GET)
	public OnsiteActivityResponse getOnsiteActivityDetails(@PathVariable String country) {
		LOGGER.info("getOnsiteActivityDetails request: " + country);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(SearchParameter.Country.getKey(), country);

		OnsiteActivityResponse response = new OnsiteActivityResponse();
		List<OnsiteActivity> onsiteActivities = null;
		OnsiteActivity onsiteActivity = null;

		try {
			onsiteActivities = jsonProcessor.lookup(params);
			if (onsiteActivities != null) {
				response.setOnsiteActivity(onsiteActivities.get(0));
				response.setResult(ResultCodes.Success);
			}
			LOGGER.info("onsite activity Details Returned" + onsiteActivity);
			
		} catch (JsonLookupException e) {
			LOGGER.error("error occured while getting onsite activity Details"
					+ e.getMessage());
			response.setResult(ResultCodes.Failure);
		}
		return response;
	}

}
