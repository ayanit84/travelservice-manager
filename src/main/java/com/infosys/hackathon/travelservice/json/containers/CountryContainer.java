package com.infosys.hackathon.travelservice.json.containers;

import java.util.List;

import com.infosys.hackathon.services.JsonContainer;
import com.infosys.hackathon.services.country.CountryDetails;

public class CountryContainer implements JsonContainer {
	private List<CountryDetails> countries;

	public List<CountryDetails> getCountries() {
		return countries;
	}

	public void setCountries(List<CountryDetails> countries) {
		this.countries = countries;
	}

}
