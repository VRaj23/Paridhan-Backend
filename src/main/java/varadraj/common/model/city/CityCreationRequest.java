package varadraj.common.model.city;

public class CityCreationRequest {
	
	private String cityName;
	private long stateID;
	
	public CityCreationRequest() {}
	
	public CityCreationRequest(String cityName, long stateID) {
		super();
		this.cityName = cityName;
		this.stateID = stateID;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public long getStateID() {
		return stateID;
	}

	public void setStateID(long stateID) {
		this.stateID = stateID;
	}
	

	

}
