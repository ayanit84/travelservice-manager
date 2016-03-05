package com.infosys.hackathon.travelservice.json.processors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Component;

import com.infosys.hackathon.services.directory.dto.SearchParameter;
import com.infosys.hackathon.services.dos.OnsiteActivity;
import com.infosys.hackathon.travelservice.exceptions.JsonDatabaseException;
import com.infosys.hackathon.travelservice.exceptions.JsonLookupException;
import com.infosys.hackathon.travelservice.json.containers.OnsiteActivityContainer;
import com.infosys.hackathon.travelservice.json.processors.filters.dos.CountryPredicate;
import com.infosys.hackathon.travelservice.json.util.JsonLoader;

@Component
public class OnsiteActivityJsonProcessor implements JsonProcessor<OnsiteActivityContainer, OnsiteActivity> {
	private static final String ONSITE_ACTIVITY_JSON_DATABASE = "jsondata/dos/dos.json";

	private OnsiteActivityContainer jsonContainer;
	private JsonLoader<OnsiteActivityContainer> jsonLoader;

	public OnsiteActivityJsonProcessor() throws JsonDatabaseException {
		this.jsonLoader = new JsonLoader<OnsiteActivityContainer>();
		this.jsonContainer = jsonLoader.load(getJsonFileName(), OnsiteActivityContainer.class);
	}

	@Override
	public String getJsonFileName() {

		return ONSITE_ACTIVITY_JSON_DATABASE;
	}

	@Override
	public OnsiteActivityContainer getContainer() {
		return jsonContainer;
	}

	@Override
	public List<OnsiteActivity> getData() {
		return jsonContainer.getOnsiteActivity();
	}

	// Expecting country as input
	@Override
	public List<OnsiteActivity> lookup(Map<String, Object> searchParams) throws JsonLookupException {
		List<OnsiteActivity> filteredOnsiteActivity = null;
		try {
			filteredOnsiteActivity = new ArrayList<OnsiteActivity>(getData());

			// Filter By Country
			CollectionUtils.filter(filteredOnsiteActivity,
					new CountryPredicate((String) searchParams.get(SearchParameter.Country.getKey())));
		} catch (Exception e) {
			throw new JsonLookupException(e.getMessage());
		}
		return filteredOnsiteActivity;
	}
	
	public static void main(String[] args) {
		try {
			OnsiteActivityJsonProcessor proc = new OnsiteActivityJsonProcessor();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(SearchParameter.Country.getKey(), "us");
			List<OnsiteActivity> eData = proc.lookup(params);
			for (OnsiteActivity data : eData) {
				System.out.println(data);
			}
		} catch (JsonDatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonLookupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
