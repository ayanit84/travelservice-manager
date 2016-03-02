package com.infosys.hackathon.travelservice.json.processors.policy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Component;

import com.infosys.hackathon.services.directory.dto.SearchParameter;
import com.infosys.hackathon.services.policy.EligibilityInformation;
import com.infosys.hackathon.travelservice.exceptions.JsonDatabaseException;
import com.infosys.hackathon.travelservice.exceptions.JsonLookupException;
import com.infosys.hackathon.travelservice.json.containers.EligibilityContainer;
import com.infosys.hackathon.travelservice.json.processors.JsonProcessor;
import com.infosys.hackathon.travelservice.json.processors.filters.country.CountryPredicate;
import com.infosys.hackathon.travelservice.json.processors.filters.policy.JobLevelPredicate;
import com.infosys.hackathon.travelservice.json.util.JsonLoader;

@Component
public class EligibilityJsonProcessor implements
		JsonProcessor<EligibilityContainer, EligibilityInformation> {

	private static final String ELIGIBILITY_JSON_DATABASE = "jsondata/policy/eligibility.json";

	private EligibilityContainer jsonContainer;
	private JsonLoader<EligibilityContainer> jsonLoader;

	public EligibilityJsonProcessor() throws JsonDatabaseException {
		this.jsonLoader = new JsonLoader<EligibilityContainer>();
		this.jsonContainer = jsonLoader.load(getJsonFileName(),
				EligibilityContainer.class);
	}

	public EligibilityContainer getJsonContainer() {
		return jsonContainer;
	}

	@Override
	public String getJsonFileName() {
		return ELIGIBILITY_JSON_DATABASE;
	}

	@Override
	public EligibilityContainer getContainer() {
		return getJsonContainer();
	}

	@Override
	public List<EligibilityInformation> getData() {
		return jsonContainer.getEligibility();
	}

	@Override
	// Expecting search params - "country" & "jobLevel"
	public List<EligibilityInformation> lookup(Map<String, Object> searchParams)
			throws JsonLookupException {

		List<EligibilityInformation> filteredEligibilityInformation = null;
		try {
			filteredEligibilityInformation = new ArrayList<EligibilityInformation>(getData());

			// FilterByCountry
			CollectionUtils.filter(
					filteredEligibilityInformation,
					new CountryPredicate((String) searchParams.get(SearchParameter.Country.getKey())));

			// FilterByJobLevel
			CollectionUtils.filter(
					filteredEligibilityInformation,
					new JobLevelPredicate((String) searchParams
							.get(SearchParameter.JobLevel.getKey())));
		} catch (Exception e) {
			throw new JsonLookupException(e.getMessage());
		}

		return filteredEligibilityInformation;
	}
	
	public static void main(String[] args) {
		try {
			EligibilityJsonProcessor proc = new EligibilityJsonProcessor();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(SearchParameter.Country.getKey(), "us");
			params.put(SearchParameter.JobLevel.getKey(), "4");
			List<EligibilityInformation> eData = proc.lookup(params);
			for (EligibilityInformation data : eData) {
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
