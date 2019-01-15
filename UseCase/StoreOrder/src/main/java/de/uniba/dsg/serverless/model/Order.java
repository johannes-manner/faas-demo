package de.uniba.dsg.serverless.model;

import java.util.List;

public class Order {

	private Customer customer;
	private List<Item> itemList;

	public Order() {

	}

	public Order(Customer customer, List<Item> itemList) {
		super();
		this.customer = customer;
		this.itemList = itemList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "Order [customer=" + customer + ", itemList=" + itemList + "]";
	}

}
