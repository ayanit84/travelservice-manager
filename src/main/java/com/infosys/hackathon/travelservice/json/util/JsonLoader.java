package com.infosys.hackathon.travelservice.json.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonLoader<T> {

	public List<T> load(String jsonPath, Class T) {
		ObjectMapper objectMapper = new ObjectMapper();
		InputStream is = T.getClass().getResourceAsStream(jsonPath);
		// JsonNode node = objectMapper.readTree(is);
		return new ArrayList<T>();
	}

}
