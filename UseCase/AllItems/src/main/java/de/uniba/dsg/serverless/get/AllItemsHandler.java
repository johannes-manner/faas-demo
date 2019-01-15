package de.uniba.dsg.serverless.get;

import java.util.LinkedHashMap;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import de.uniba.dsg.serverless.model.Item;

public class AllItemsHandler implements RequestHandler<LinkedHashMap<String,String>, List<Item>> {

    @Override
    public List<Item> handleRequest(LinkedHashMap<String,String> input, Context context) {
    	
    	context.getLogger().log("New Request: " + input);
    	
    	int maxPrice = -1;
    	if ( input.containsKey("maxPrice")) {
    		maxPrice = Integer.parseInt(input.get("maxPrice"));
    	}
    	
        ItemRepository service = new ItemRepositoryImpl();
        List<Item> itemList;
        		
        if(maxPrice == -1) {
        	itemList = service.getAvailableItems();
        } else {
        	itemList = service.getAvailableItems(maxPrice);
        }
        
        context.getLogger().log("Available items: " + itemList.size());

        return itemList;
    }

}
