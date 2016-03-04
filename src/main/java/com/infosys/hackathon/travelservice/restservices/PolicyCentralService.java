package com.infosys.hackathon.travelservice.restservices;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.hackathon.services.ResultCodes;
import com.infosys.hackathon.services.directory.dto.SearchParameter;
import com.infosys.hackathon.services.policy.EligibilityInformation;
import com.infosys.hackathon.services.policy.dto.PolicyDetailsResponse;
import com.infosys.hackathon.travelservice.exceptions.JsonLookupException;
import com.infosys.hackathon.travelservice.json.processors.policy.EligibilityJsonProcessor;

@RestController
@RequestMapping("/service/policy")
public class PolicyCentralService {

	private static Logger LOGGER = Logger.getLogger(PolicyCentralService.class);

	@Autowired
	private EligibilityJsonProcessor eligibilityJsonProcessor;

	@RequestMapping(value = "/details/{country}/{jobLevel}", method = RequestMethod.GET)
	public PolicyDetailsResponse getPolicyDetails(@PathVariable String country,
			@PathVariable String jobLevel) {
		
		LOGGER.info("getPolicyDetails request: " + country +","+jobLevel);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(SearchParameter.Country.getKey(), country);
		params.put(SearchParameter.JobLevel.getKey(), jobLevel);

		PolicyDetailsResponse response = new PolicyDetailsResponse();
		EligibilityInformation eligibilityInformation = null;
		try {
			eligibilityInformation = eligibilityJsonProcessor
					.getEligibilityInformation(params);

			response.setResult(ResultCodes.Success);
			response.setEligibilityDetails(eligibilityInformation);
			
			LOGGER.info("Eligibilty Details Returned" + eligibilityInformation);
			
		} catch (JsonLookupException e) {
			LOGGER.error("error occured while getting eligibility details"
					+ e.getMessage());
			response.setResult(ResultCodes.Failure);
		}

		return response;

	}

}
