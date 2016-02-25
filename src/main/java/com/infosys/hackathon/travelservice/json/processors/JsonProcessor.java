package com.infosys.hackathon.travelservice.json.processors;

import java.util.List;

public interface JsonProcessor<T> {
	public String getJsonFileName();

	public List<T> getData();
}
