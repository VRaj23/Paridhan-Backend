package varadraj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long addressID;
	
	@Column(nullable = false)
	
	private String city;
	
	private int pincode;
	
	private String address;	
	
	public Address() {}
	
	public Address(String city, int pincode, String address) {
		super();
		this.city = city;
		this.pincode = pincode;
		this.address = address;
	}
	
	
	
	public long getAddressID() {
		return addressID;
	}

	public void setAddressID(long addressID) {
		this.addressID = addressID;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
