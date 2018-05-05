package varadraj.common.model.city;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import varadraj.common.model.state.State;


@Entity
@Table(name = "address_city")
public class City {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="city_id")
	private long cityID;
	
	@Column(name="city_name")
	private String cityName;
	
	@JoinColumn(name="state_id")
	@ManyToOne
	private State state;
	
	public City() {}


	public City(String cityName, State state) {
		super();
		this.cityName = cityName;
		this.state = state;
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


	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	

}
