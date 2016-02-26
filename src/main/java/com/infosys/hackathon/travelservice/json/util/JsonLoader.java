package com.infosys.hackathon.travelservice.json.util;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infosys.hackathon.services.directory.EmployeeOfficeAddress;
import com.infosys.hackathon.travelservice.json.containers.EmployeeOfficeAddressContainer;

public class JsonLoader<T> {

	public T load(String jsonPath, Class<T> clazz) throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(
				DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

		InputStream is = ClassLoader.getSystemResourceAsStream(jsonPath);
		return objectMapper.readValue(is, clazz);

	}

	public static void main(String[] args) {
		try {
			JsonLoader<EmployeeOfficeAddressContainer> loader = new JsonLoader<EmployeeOfficeAddressContainer>();
			EmployeeOfficeAddressContainer offices = loader.load(
					"jsondata/directory/office.json",
					EmployeeOfficeAddressContainer.class);
			for (EmployeeOfficeAddress ofc : offices.getOffices()) {
				System.out.println(ofc);
			}

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
