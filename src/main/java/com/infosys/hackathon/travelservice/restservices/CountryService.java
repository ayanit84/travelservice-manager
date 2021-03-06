package com.infosys.hackathon.travelservice.restservices;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.hackathon.services.ResultCodes;
import com.infosys.hackathon.services.country.dto.CountryServiceResponse;
import com.infosys.hackathon.travelservice.exceptions.CountryNotFoundException;
import com.infosys.hackathon.travelservice.exceptions.StateNotFoundException;
import com.infosys.hackathon.travelservice.json.processors.country.CountryJsonProcessor;

@RestController
@RequestMapping("/service/country")
public class CountryService {

	private static final Logger LOGGER = Logger.getLogger(CountryService.class);

	@Autowired
	private CountryJsonProcessor countryJsonProcessor;

	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public CountryServiceResponse fetchCountries() {
		LOGGER.info("fetching all countries");
		CountryServiceResponse response = new CountryServiceResponse();
		try {
			response.setDetails(countryJsonProcessor.getCountries());
			response.setResult(ResultCodes.Success);
			LOGGER.info("successfully fetched " + response.getDetails().size()
				+ " countries");
		} catch (Exception e) {
			response.setResult(ResultCodes.Failure);
			LOGGER.error("error while fetching countries " + e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/states/{country}", method = RequestMethod.GET)
	public CountryServiceResponse fetchStates(@PathVariable String country) {
		LOGGER.info("fetching all states for country " + country);
		CountryServiceResponse response = new CountryServiceResponse();
		try {
			response.setDetails(countryJsonProcessor.getStates(country));
			response.setResult(ResultCodes.Success);
			LOGGER.info("successfully fetched " + response.getDetails().size()
				+ " states");
		} catch (CountryNotFoundException e) {
			response.setResult(ResultCodes.Failure);
			LOGGER.error("error while fetching states " + e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/cities/{country}/{state}", method = RequestMethod.GET)
	public CountryServiceResponse fetchCities(@PathVariable String country,
		@PathVariable String state) {
		LOGGER.info(
			"fetching all cities for country " + country + ", state " + state);
		CountryServiceResponse response = new CountryServiceResponse();
		try {
			response.setDetails(countryJsonProcessor.getCities(country, state));
			response.setResult(ResultCodes.Success);
			LOGGER.info("successfully fetched " + response.getDetails().size()
				+ " cities");
		} catch (CountryNotFoundException | StateNotFoundException e) {
			response.setResult(ResultCodes.Failure);
			LOGGER.error("error while fetching cities " + e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/allcities/{country}", method = RequestMethod.GET)
	public CountryServiceResponse fetchAllCities(@PathVariable String country) {
		LOGGER.info("fetching all cities for country " + country);
		CountryServiceResponse response = new CountryServiceResponse();
		try {
			response.setDetails(countryJsonProcessor.getAllCities(country));
			response.setResult(ResultCodes.Success);
			LOGGER.info("successfully fetched " + response.getDetails().size()
				+ " cities");
		} catch (CountryNotFoundException | StateNotFoundException e) {
			response.setResult(ResultCodes.Failure);
			LOGGER.error("error while fetching cities " + e.getMessage());
		}
		return response;
	}
}
