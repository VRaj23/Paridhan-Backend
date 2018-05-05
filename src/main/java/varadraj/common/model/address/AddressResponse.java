package varadraj.common.model.address;

public class AddressResponse {

    private long addressID;
    private String houseNumber;
    private String area;
    private String landmark;
    private String cityName;
    private String stateName;
    private int pincode;
    
    public AddressResponse() {}
    
	public AddressResponse(long addressID, String houseNumber, String area, String landmark, String cityName,
			String stateName, int pincode) {
		super();
		this.addressID = addressID;
		this.houseNumber = houseNumber;
		this.area = area;
		this.landmark = landmark;
		this.cityName = cityName;
		this.stateName = stateName;
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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
    
	
    
}
