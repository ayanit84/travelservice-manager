package com.infosys.hackathon.travelservice.restservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.hackathon.travelservice.json.processors.policy.EligibilityJsonProcessor;


@RestController
@RequestMapping("/service/policy")
public class PolicyCentralService {
	
	@Autowired
	private EligibilityJsonProcessor eligibilityJsonProcessor;
	

}
