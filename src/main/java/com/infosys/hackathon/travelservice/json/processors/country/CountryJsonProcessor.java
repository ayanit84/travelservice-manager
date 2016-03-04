package com.infosys.hackathon.travelservice.json.processors.country;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Component;

import com.infosys.hackathon.services.KeyValuePair;
import com.infosys.hackathon.services.country.CityDetails;
import com.infosys.hackathon.services.country.CountryDetails;
import com.infosys.hackathon.services.country.StateDetails;
import com.infosys.hackathon.travelservice.exceptions.CountryNotFoundException;
import com.infosys.hackathon.travelservice.exceptions.JsonDatabaseException;
import com.infosys.hackathon.travelservice.exceptions.JsonLookupException;
import com.infosys.hackathon.travelservice.exceptions.StateNotFoundException;
import com.infosys.hackathon.travelservice.json.containers.CountryContainer;
import com.infosys.hackathon.travelservice.json.processors.JsonProcessor;
import com.infosys.hackathon.travelservice.json.processors.filters.country.CountryPredicate;
import com.infosys.hackathon.travelservice.json.util.JsonLoader;

@Component
public class CountryJsonProcessor
	implements JsonProcessor<CountryContainer, CountryDetails> {

	private static final String JSON_DATABASE = "jsondata/country/country.json";

	private CountryContainer jsonContainer;
	private JsonLoader<CountryContainer> jsonLoader;

	public CountryJsonProcessor() throws JsonDatabaseException {
		this.jsonLoader = new JsonLoader<CountryContainer>();
		this.jsonContainer = this.jsonLoader.load(JSON_DATABASE,
			CountryContainer.class);
	}

	@Override
	public String getJsonFileName() {
		return JSON_DATABASE;
	}

	@Override
	public CountryContainer getContainer() {
		return this.jsonContainer;
	}

	@Override
	public List<CountryDetails> getData() {
		return this.jsonContainer.getCountries();
	}

	@Override
	public List<CountryDetails> lookup(Map<String, Object> searchParams)
		throws JsonLookupException {
		List<CountryDetails> filteredCountry = null;
		try {
			filteredCountry = new ArrayList<CountryDetails>(getData());
			CollectionUtils.filter(filteredCountry,
				new CountryPredicate(searchParams.get("country").toString()));
		} catch (Exception e) {
			throw new JsonLookupException(e.getMessage());
		}
		return filteredCountry;
	}

	public List<KeyValuePair> getCountries() {
		List<KeyValuePair> countries = new ArrayList<KeyValuePair>();
		for (CountryDetails eachCountry : getData()) {
			KeyValuePair pair = new KeyValuePair(eachCountry.getCode(),
				eachCountry.getName());
			countries.add(pair);
		}
		return countries;
	}

	public List<KeyValuePair> getStates(String countryCode)
		throws CountryNotFoundException {
		List<KeyValuePair> states = new ArrayList<KeyValuePair>();
		CountryDetails matchedCountry = findCountry(countryCode);

		for (StateDetails eachState : matchedCountry.getStates()) {
			KeyValuePair pair = new KeyValuePair(eachState.getCode(),
				eachState.getName());
			states.add(pair);
		}
		return states;
	}

	public List<KeyValuePair> getCities(String countryCode, String stateCode)
		throws CountryNotFoundException, StateNotFoundException {
		List<KeyValuePair> cities = new ArrayList<KeyValuePair>();
		CountryDetails matchedCountry = findCountry(countryCode);
		StateDetails matchedState = findState(matchedCountry, stateCode);

		for (CityDetails eachCity : matchedState.getCities()) {
			KeyValuePair pair = new KeyValuePair(eachCity.getCode(),
				eachCity.getName());
			cities.add(pair);
		}
		return cities;
	}

	public List<KeyValuePair> getAllCities(String countryCode)
		throws CountryNotFoundException, StateNotFoundException {
		List<KeyValuePair> cities = new ArrayList<KeyValuePair>();
		CountryDetails matchedCountry = findCountry(countryCode);
		List<StateDetails> allStates = matchedCountry.getStates();

		for (StateDetails eachState : allStates) {
			for (CityDetails eachCity : eachState.getCities()) {
				cities.add(
					new KeyValuePair(eachCity.getCode(), eachCity.getName()));
			}
		}
		return cities;
	}

	private CountryDetails findCountry(String countryCode)
		throws CountryNotFoundException {
		CountryDetails matchedCountry = null;
		for (CountryDetails eachCountry : getData()) {
			if (eachCountry.getCode().equals(countryCode)) {
				matchedCountry = eachCountry;
				break;
			}
		}

		if (matchedCountry == null) {
			throw new CountryNotFoundException(countryCode + " not found");
		}
		return matchedCountry;
	}

	private StateDetails findState(CountryDetails countryDetail,
		String stateCode) throws StateNotFoundException {
		StateDetails matchedState = null;
		for (StateDetails eachState : countryDetail.getStates()) {
			if (eachState.getCode().equals(stateCode)) {
				matchedState = eachState;
				break;
			}
		}

		if (matchedState == null) {
			throw new StateNotFoundException(stateCode
				+ " not found under country: " + countryDetail.getCode());
		}
		return matchedState;
	}

	public static void main(String[] args) {
		try {
			CountryJsonProcessor proc = new CountryJsonProcessor();

			List<CountryDetails> countries = proc.getData();
			for (CountryDetails con : countries) {
				System.out.println(con.getStates());
			}

			System.out.println(proc.getCountries());
			System.out.println(proc.getStates("us"));
			System.out.println(proc.getCities("us", "az"));
			System.out.println(proc.getStates("xx"));
		} catch (JsonDatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CountryNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StateNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
