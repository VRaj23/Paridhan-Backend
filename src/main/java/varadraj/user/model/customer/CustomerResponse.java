package varadraj.user.model.customer;

import varadraj.common.model.address.AddressResponse;

public class CustomerResponse {
	
    private long customerID;
    private String username;
    private String name;
    private String email;
    private AddressResponse addressResponse;
    
    public CustomerResponse() {}

	public CustomerResponse(long customerID, String username, String name, String email,
			AddressResponse addressResponse) {
		super();
		this.customerID = customerID;
		this.username = username;
		this.name = name;
		this.email = email;
		this.addressResponse = addressResponse;
	}

	public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AddressResponse getAddressResponse() {
		return addressResponse;
	}

	public void setAddressResponse(AddressResponse addressResponse) {
		this.addressResponse = addressResponse;
	}
    
    

}
