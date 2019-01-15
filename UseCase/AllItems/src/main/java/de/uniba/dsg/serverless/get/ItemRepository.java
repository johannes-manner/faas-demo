package de.uniba.dsg.serverless.get;

import java.util.List;

import de.uniba.dsg.serverless.model.Item;

public interface ItemRepository {
	
	public List<Item> getAvailableItems();
	
	public List<Item> getAvailableItems( int maxPrice );

}
