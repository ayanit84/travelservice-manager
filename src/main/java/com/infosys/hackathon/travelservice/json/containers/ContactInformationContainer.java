package com.infosys.hackathon.travelservice.json.containers;

import java.util.List;

import com.infosys.hackathon.services.JsonContainer;
import com.infosys.hackathon.services.contact.ContactInformation;

public class ContactInformationContainer implements JsonContainer {
	private List<ContactInformation> countries;

	public List<ContactInformation> getCountries() {
		return countries;
	}

	public void setCountries(List<ContactInformation> countries) {
		this.countries = countries;
	}

}
