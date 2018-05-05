package varadraj.common.model.city;

public class CityResponse {

	private long cityID;
	private String cityName;
	private String stateName;
	
	public CityResponse() {}

	public CityResponse(long cityID, String cityName, String stateName) {
		super();
		this.cityID = cityID;
		this.cityName = cityName;
		this.stateName = stateName;
	}

	public long getCityID() {
		return cityID;
	}

	public void setCityID(long cityID) {
		this.cityID = cityID;
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
	
	
}
