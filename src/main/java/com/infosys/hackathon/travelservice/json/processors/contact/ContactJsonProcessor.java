package com.infosys.hackathon.travelservice.json.processors.contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Component;

import com.infosys.hackathon.services.contact.ContactInformation;
import com.infosys.hackathon.services.directory.dto.SearchParameter;
import com.infosys.hackathon.travelservice.exceptions.JsonDatabaseException;
import com.infosys.hackathon.travelservice.exceptions.JsonLookupException;
import com.infosys.hackathon.travelservice.json.containers.ContactInformationContainer;
import com.infosys.hackathon.travelservice.json.processors.JsonProcessor;
import com.infosys.hackathon.travelservice.json.processors.filters.contact.CountryPredicate;
import com.infosys.hackathon.travelservice.json.util.JsonLoader;

@Component
public class ContactJsonProcessor
		implements
		JsonProcessor<ContactInformationContainer, ContactInformation> {

	private static final String JSON_DATABASE = "jsondata/helpdesk/contact.json";

	private ContactInformationContainer jsonContainer;

	private JsonLoader<ContactInformationContainer> jsonLoader;

	public ContactJsonProcessor() throws JsonDatabaseException {
		this.jsonLoader = new JsonLoader<ContactInformationContainer>();
		this.jsonContainer = this.jsonLoader.load(getJsonFileName(),
				ContactInformationContainer.class);
	}

	@Override
	public String getJsonFileName() {
		return JSON_DATABASE;
	}

	@Override
	public ContactInformationContainer getContainer() {
		return this.jsonContainer;
	}

	@Override
	public List<ContactInformation> getData() {
		return this.jsonContainer.getCountries();
	}

	@Override
	public List<ContactInformation> lookup(Map<String, Object> searchParams) throws JsonLookupException {
		List<ContactInformation> filteredCountries = null;
		try {
			filteredCountries = new ArrayList<ContactInformation>(
					getData());
			CollectionUtils.filter(filteredCountries,new CountryPredicate(searchParams.get(SearchParameter.Country.getKey()).toString()));
		} catch (Exception e) {
			throw new JsonLookupException(e.getMessage());
		}
		return filteredCountries;
	}
	
	public static void main(String[] args) {
		try {
			ContactJsonProcessor proc = new ContactJsonProcessor();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(SearchParameter.Country.getKey(), "us");
			List<ContactInformation> ofc = proc.lookup(params);
			for (ContactInformation contactInformation : ofc) {
				System.out.println(contactInformation);
			}
		} catch (JsonDatabaseException | JsonLookupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}


}