package de.uniba.dsg.serverless.get;

import java.util.ArrayList;
import java.util.List;

import de.uniba.dsg.serverless.model.Item;

public class ItemRepositoryImpl implements ItemRepository {

	private List<Item> getAllAvailableItems() {
		List<Item> itemList = new ArrayList<>();

		itemList.add(new Item("0001", "Bohemian Rhapsody DVD", 5, 1999));
		itemList.add(new Item("0002", "Skyfall DVD", 2, 999));
		itemList.add(new Item("0003", "X-Trio DVD", 15, 599));
		itemList.add(new Item("000A", "HarrayPotter extended DVD", 1, 2499));
		itemList.add(new Item("000B", "Hunger Games DVD", 15, 2999));
		itemList.add(new Item("000C", "TV", 3, 29999));

		return itemList;
	}

	public List<Item> getAvailableItems() {

		return this.getAllAvailableItems();

	}

	@Override
	public List<Item> getAvailableItems(int maxPrice) {

		List<Item> items = this.getAllAvailableItems();
		List<Item> cheapItems = new ArrayList<>();
		
		for(Item item : items ) {
			if(item.getPrice() < maxPrice ) {
				cheapItems.add(item);
			}
		}
		
		return cheapItems;
	}
}
