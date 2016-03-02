package com.infosys.hackathon.travelservice.json.containers;

import java.util.List;

import com.infosys.hackathon.services.JsonContainer;
import com.infosys.hackathon.services.policy.EligibilityInformation;

public class EligibilityContainer implements JsonContainer {

	private List<EligibilityInformation> eligibility;

	public List<EligibilityInformation> getEligibility() {
		return eligibility;
	}

	public void setEligibility(List<EligibilityInformation> eligibility) {
		this.eligibility = eligibility;
	}

	public EligibilityContainer(List<EligibilityInformation> eligibility) {
		super();
		this.eligibility = eligibility;
	}

	public EligibilityContainer() {
		super();
	}
	
	

}
