package com.infosys.hackathon.travelservice.json.processors;

import java.util.List;

import com.infosys.hackathon.services.JsonContainer;
import com.infosys.hackathon.services.JsonData;
import com.infosys.hackathon.travelservice.exceptions.JsonDatabaseException;

public interface JsonProcessor<T extends JsonContainer, V extends JsonData> {

	public String getJsonFileName();

	public T getContainer();

	public List<V> getData() throws JsonDatabaseException;
}
