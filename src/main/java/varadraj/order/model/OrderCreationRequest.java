package varadraj.order.model;

public class OrderCreationRequest {
	
	private double amount;
	private long deliveryAddressID;
	private long productLineID;
	private int quantity = 1;
	

	public OrderCreationRequest() {}


	public OrderCreationRequest(double amount, long deliveryAddressID, long productLineID, int quantity) {
		super();
		this.amount = amount;
		this.deliveryAddressID = deliveryAddressID;
		this.productLineID = productLineID;
		this.quantity = quantity;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public long getDeliveryAddressID() {
		return deliveryAddressID;
	}


	public void setDeliveryAddressID(long deliveryAddressID) {
		this.deliveryAddressID = deliveryAddressID;
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
