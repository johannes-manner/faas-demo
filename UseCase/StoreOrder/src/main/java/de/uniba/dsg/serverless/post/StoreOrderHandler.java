package de.uniba.dsg.serverless.post;

import java.util.UUID;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import de.uniba.dsg.serverless.db.model.OrderDTO;
import de.uniba.dsg.serverless.model.Order;

public class StoreOrderHandler implements RequestHandler<Order, OrderIncomingMessage> {

	private AmazonDynamoDB client;
	private String REGION;

	@Override
	public OrderIncomingMessage handleRequest(Order input, Context context) {
		context.getLogger().log("Input: " + input);

		this.initEnvironmentVariables();
		this.initDynamoDbClient();

		boolean successfulValidated = this.validateOrder(input);

		OrderIncomingMessage message;
		OrderDTO order = new OrderDTO(input, UUID.randomUUID().toString());
		
		//
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		System.out.println("Successfully stored customer");
		//
		
		if (successfulValidated) {
			
			mapper.save(order);
			
			message = new OrderIncomingMessage("",
					"The order was successfully placed in the database. You get feedback, when the order was processed.");
		} else {
			message = new OrderIncomingMessage("NONE",
					order.getDetailedValidationMessage());
		}

		return message;
	}

	private boolean validateOrder(Order input) {
		// TODO Auto-generated method stub
		return true;
	}

	private void initEnvironmentVariables() {
		REGION = System.getenv("REGION");
	}

	private void initDynamoDbClient() {
		AmazonDynamoDBClientBuilder clientBuilder = AmazonDynamoDBClientBuilder.standard();
		clientBuilder.setRegion(REGION);
		client = clientBuilder.build();
	}
}
