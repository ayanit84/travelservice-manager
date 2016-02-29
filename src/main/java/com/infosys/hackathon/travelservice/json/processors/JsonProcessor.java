package com.infosys.hackathon.travelservice.json.processors;

import java.util.List;
import java.util.Map;

import com.infosys.hackathon.services.JsonContainer;
import com.infosys.hackathon.services.JsonData;
import com.infosys.hackathon.travelservice.exceptions.JsonLookupException;

public interface JsonProcessor<T extends JsonContainer, V extends JsonData> {

	public String getJsonFileName();

	public T getContainer();

	public List<V> getData();

	public List<V> lookup(Map<String, Object> searchParams)
			throws JsonLookupException;
}
