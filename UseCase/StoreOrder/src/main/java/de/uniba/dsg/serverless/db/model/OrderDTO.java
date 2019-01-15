package de.uniba.dsg.serverless.db.model;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import de.uniba.dsg.serverless.model.Customer;
import de.uniba.dsg.serverless.model.Item;
import de.uniba.dsg.serverless.model.Order;

@DynamoDBTable(tableName = "Order")
public class OrderDTO {

	private String orderId;
	private Customer customer;
	private List<Item> items;

	public OrderDTO(Order input, String orderId) {
		this.orderId = orderId;
		customer = input.getCustomer();
		items = input.getItemList();
	}

	public String getDetailedValidationMessage() {
		// TODO 
		return "";
	}

	@DynamoDBHashKey
	@DynamoDBAttribute(attributeName = "ID")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@DynamoDBTypeConverted(converter = CustomerConverter.class)
	@DynamoDBAttribute(attributeName = "customer")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@DynamoDBTypeConverted(converter = ItemListConverter.class)
	@DynamoDBAttribute(attributeName = "items")
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OrderDTO [orderId=" + orderId + ", customer=" + customer + ", items=" + items + "]";
	}

	
}


