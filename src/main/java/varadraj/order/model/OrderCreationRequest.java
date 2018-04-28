package varadraj.order.model;

import varadraj.common.model.Address;

public class OrderCreationRequest {
	
	private long customerID;
	private double amount;
	private Address deliveryAddress;
	private long productLineID;
	private int quantity = 1;
	
	public OrderCreationRequest(long customerID, double amount, Address deliveryAddress, long productLineID,
			int quantity) {
		super();
		this.customerID = customerID;
		this.amount = amount;
		this.deliveryAddress = deliveryAddress;
		this.productLineID = productLineID;
		this.quantity = quantity;
	}

	public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
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
