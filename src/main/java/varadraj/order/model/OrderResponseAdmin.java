package varadraj.order.model;

import java.time.LocalDateTime;

import varadraj.common.model.address.AddressResponse;
import varadraj.user.model.customer.CustomerResponse;

public class OrderResponseAdmin extends OrderResponseCustomer{
	
	private CustomerResponse customerResponse;
	private AddressResponse addressResponse;
	
	public OrderResponseAdmin() {}

	public OrderResponseAdmin(long orderID, String productName, String colorName, String sizeChar, String brandName,
			long imageID, String typeName, String statusName, double amount, int quantity,
			LocalDateTime creationDateTime, CustomerResponse customerResponse, AddressResponse addressResponse) {
		super(orderID, productName, colorName, sizeChar, brandName, imageID, typeName, statusName, amount, quantity,
				creationDateTime);
		this.customerResponse = customerResponse;
		this.addressResponse = addressResponse;
	}

	public CustomerResponse getCustomerResponse() {
		return customerResponse;
	}

	public void setCustomerResponse(CustomerResponse customerResponse) {
		this.customerResponse = customerResponse;
	}

	public AddressResponse getAddressResponse() {
		return addressResponse;
	}

	public void setAddressResponse(AddressResponse addressResponse) {
		this.addressResponse = addressResponse;
	}
	
	
}
