package com.infosys.hackathon.travelservice.json.processors;

import java.util.List;
import java.util.Map;

import com.infosys.hackathon.services.dos.OnsiteActivity;
import com.infosys.hackathon.travelservice.exceptions.JsonLookupException;
import com.infosys.hackathon.travelservice.json.containers.OnsiteActivityContainer;
import com.infosys.hackathon.travelservice.json.util.JsonLoader;

public class OnsiteActivityJsonProcessor implements JsonProcessor<OnsiteActivityContainer, OnsiteActivity> {
	private static final String ONSITE_ACTIVITY_JSON_DATABASE = "jsondata/dos/dos.json";
	
	private OnsiteActivityContainer jsonContainer;
	private JsonLoader<OnsiteActivityContainer> jsonLoader;
	@Override
	public String getJsonFileName() {
		
		return ONSITE_ACTIVITY_JSON_DATABASE;
	}

	@Override
	public OnsiteActivityContainer getContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OnsiteActivity> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OnsiteActivity> lookup(Map<String, Object> searchParams) throws JsonLookupException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
