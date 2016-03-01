package com.infosys.hackathon.travelservice.json.containers;

import java.util.Map;

import com.infosys.hackathon.services.JsonContainer;
import com.infosys.hackathon.services.directory.Policy.EligibilityInformation;

public class EligibilityContainer implements JsonContainer {

	private Map<String, Integer> eligibilityCriteria;
	private EligibilityInformation eligibilityInformation;

	public EligibilityInformation getEligibilityInformation() {
		return eligibilityInformation;
	}

	public Map<String, Integer> getEligibilityCriteria() {
		return eligibilityCriteria;
	}

	public void setEligibilityCriteria(Map<String, Integer> eligibilityCriteria) {
		this.eligibilityCriteria = eligibilityCriteria;
	}

	public void setEligibilityInformation(
			EligibilityInformation eligibilityInformation) {
		this.eligibilityInformation = eligibilityInformation;
	}

	public EligibilityContainer(Map<String, Integer> eligibilityCriteria,
			EligibilityInformation eligibilityInformation) {
		super();
		this.eligibilityCriteria = eligibilityCriteria;
		this.eligibilityInformation = eligibilityInformation;
	}

	@Override
	public String toString() {
		return "EligibilityContainer [eligibilityCriteria="
				+ eligibilityCriteria + ", eligibilityInformation="
				+ eligibilityInformation + "]";
	}

}
