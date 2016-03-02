package com.infosys.hackathon.travelservice.json.processors.country;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.cglib.core.CollectionUtils;

import com.infosys.hackathon.services.country.CountryDetails;
import com.infosys.hackathon.travelservice.exceptions.JsonDatabaseException;
import com.infosys.hackathon.travelservice.exceptions.JsonLookupException;
import com.infosys.hackathon.travelservice.json.containers.CountryContainer;
import com.infosys.hackathon.travelservice.json.processors.JsonProcessor;
import com.infosys.hackathon.travelservice.json.processors.filters.country.CountryPredicate;
import com.infosys.hackathon.travelservice.json.util.JsonLoader;

public class CountryJsonProcessor implements
		JsonProcessor<CountryContainer, CountryDetails> {

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
			CollectionUtils.filter(filteredCountry, new CountryPredicate(
					searchParams.get("country").toString()));
		} catch (Exception e) {
			throw new JsonLookupException(e.getMessage());
		}
		return filteredCountry;
	}

	public static void main(String[] args) {
		try {
			CountryJsonProcessor proc = new CountryJsonProcessor();

			List<CountryDetails> countries = proc.getData();
			for (CountryDetails con : countries) {
				System.out.println(con.getStates());
			}
		} catch (JsonDatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
