package varadraj.common.model.address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import varadraj.common.model.city.City;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long addressID;
	
	private String houseNumber;//flat, house no.
	private String area; //area, colony
	private String landmark;
	
	@JoinColumn(name = "city_id")
	@ManyToOne
	private City city;
	
	private int pincode;	
	
	public Address() {}

	public Address(String houseNumber, String area, String landmark, City city,  int pincode) {
		super();
		this.houseNumber = houseNumber;
		this.area = area;
		this.landmark = landmark;
		this.city = city;
		this.pincode = pincode;
	}


	public long getAddressID() {
		return addressID;
	}

	public void setAddressID(long addressID) {
		this.addressID = addressID;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	
}
