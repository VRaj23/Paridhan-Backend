package varadraj.common.model.address;

public class AddressCreationRequest {

    private String houseNumber;
    private String area;
    private String landmark;
    private long cityID;
    private int pincode;
    
    public AddressCreationRequest() {}

	public AddressCreationRequest(String houseNumber, String area, String landmark, long cityID, int pincode) {
		super();
		this.houseNumber = houseNumber;
		this.area = area;
		this.landmark = landmark;
		this.cityID = cityID;
		this.pincode = pincode;
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

	public long getCityID() {
		return cityID;
	}

	public void setCityID(long cityID) {
		this.cityID = cityID;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
    
}
