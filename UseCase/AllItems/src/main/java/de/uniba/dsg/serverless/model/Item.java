package de.uniba.dsg.serverless.model;


public class Item {

	private String artikelId;
	private String name;
	private int available;
	private int price;
	
	public Item() {
		
	}

	public Item(String artikelId, String name, int available, int price) {
		super();
		this.artikelId = artikelId;
		this.name = name;
		this.available = available;
		this.price = price;
	}

	public String getArtikelId() {
		return artikelId;
	}

	public void setArtikelId(String artikelId) {
		this.artikelId = artikelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [artikelId=" + artikelId + ", name=" + name + ", available=" + available + ", price=" + price
				+ "]";
	}

}
