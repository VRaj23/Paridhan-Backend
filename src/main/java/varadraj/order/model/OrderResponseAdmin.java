package varadraj.order.model;

import java.time.LocalDateTime;

import varadraj.common.model.address.AddressResponse;
import varadraj.user.model.customer.CustomerResponse;

public class OrderResponseAdmin extends OrderResponseCustomer{
	
	private CustomerResponse customerResponse;
	private AddressResponse deliveryAddress;
	
	public OrderResponseAdmin() {}

	public OrderResponseAdmin(long orderID, String productName, String colorName, String sizeChar, String brandName,
			long imageID, String typeName, String statusName, double amount, int quantity,
			LocalDateTime creationDateTime, CustomerResponse customerResponse, AddressResponse addressResponse) {
		super(orderID, productName, colorName, sizeChar, brandName, imageID, typeName, statusName, amount, quantity,
				creationDateTime);
		this.customerResponse = customerResponse;
		this.deliveryAddress = addressResponse;
	}

	public CustomerResponse getCustomerResponse() {
		return customerResponse;
	}

	public void setCustomerResponse(CustomerResponse customerResponse) {
		this.customerResponse = customerResponse;
	}

	public AddressResponse getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(AddressResponse deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}	
	
}
