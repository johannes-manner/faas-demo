package de.uniba.dsg.serverless.db.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.uniba.dsg.serverless.model.Item;

public class ItemListConverter implements DynamoDBTypeConverter<String, List<Item>>{

	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public String convert(List<Item> object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			return "";
		}
	}

	@Override
	public List<Item> unconvert(String object) {
		if ("".equals(object)) {
			return new ArrayList<>();
		}
		try {
			List<Item> readValue = mapper.readValue(object, List.class);
			return readValue;
		} catch (IOException e) {
			System.err.println("IO Exception while parsing customer from dynamo db");
			return new ArrayList<>();
		}
	}

	
}
