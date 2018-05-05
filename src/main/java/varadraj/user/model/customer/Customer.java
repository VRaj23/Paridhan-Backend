package varadraj.user.model.customer;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


import varadraj.common.model.address.Address;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long customerID;
	
	@Column(unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	private String name;
	
	private String email;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "addressID")
	private Address address;
	
	private LocalDateTime creationDateTime;
	
	public Customer() {}

	public Customer(String username, String password, String name, Address address, String email) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.address = address;
		this.creationDateTime = LocalDateTime.now();
		this.email = email;
	}
	
	public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}	

}
