package varadraj.order.model;

public class OrderLineCreationRequest {
	
	private long productLineID;
	private int quantity = 1;
	
	public OrderLineCreationRequest() {}
	
	public OrderLineCreationRequest(long productLineID, int quantity) {
		super();
		this.productLineID = productLineID;
		this.quantity = quantity;
	}

	public long getProductLineID() {
		return productLineID;
	}

	public void setProductLineID(long productLineID) {
		this.productLineID = productLineID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
