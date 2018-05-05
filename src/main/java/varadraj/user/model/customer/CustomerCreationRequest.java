package varadraj.user.model.customer;

import varadraj.common.model.address.AddressCreationRequest;

public class CustomerCreationRequest {

    private String username;
    private String password;
    private String name;
    private String email;
    private AddressCreationRequest addressCreationRequest;
    
    public CustomerCreationRequest() {}

	public CustomerCreationRequest(String username, String password, String name, String email,
			AddressCreationRequest addressCreationRequest) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.addressCreationRequest = addressCreationRequest;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public AddressCreationRequest getAddressCreationRequest() {
		return addressCreationRequest;
	}

	public void setAddressCreationRequest(AddressCreationRequest addressCreationRequest) {
		this.addressCreationRequest = addressCreationRequest;
	}
    
    
}
