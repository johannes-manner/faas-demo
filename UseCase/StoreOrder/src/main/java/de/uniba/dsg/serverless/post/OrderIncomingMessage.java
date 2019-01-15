package de.uniba.dsg.serverless.post;

public class OrderIncomingMessage {

	private final String orderID;
	private final String message;

	public OrderIncomingMessage(String orderID, String message) {
		super();
		this.orderID = orderID;
		this.message = message;
	}

	public String getOrderID() {
		return orderID;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "OrderIncomingMessage [orderID=" + orderID + ", message=" + message + "]";
	}

}
