package varadraj.order.model;

import varadraj.common.model.Address;

public class OrderHeaderCreationRequest {

	private long customerID;
	private double amount;
	private Address deliveryAddress;
	
	public OrderHeaderCreationRequest() {}
	
	public OrderHeaderCreationRequest(long customerID, double amount, Address deliveryAddress) {
		super();
		this.customerID = customerID;
		this.amount = amount;
		this.deliveryAddress = deliveryAddress;
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
	
	
}
