package de.uniba.dsg.serverless.db.model;

import java.io.IOException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.uniba.dsg.serverless.model.Customer;

public class CustomerConverter implements DynamoDBTypeConverter<String, Customer> {

	ObjectMapper mapper = new ObjectMapper();

	@Override
	public String convert(Customer object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			return "";
		}
	}

	@Override
	public Customer unconvert(String object) {
		if ("".equals(object)) {
			return new Customer();
		}
		try {
			return mapper.readValue(object, Customer.class);
		} catch (IOException e) {
			System.err.println("IO Exception while parsing customer from dynamo db");
			return new Customer();
		}

	}

}