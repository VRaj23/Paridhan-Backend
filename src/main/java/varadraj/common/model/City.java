package varadraj.common.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "address_city")
public class City {
	
	@Id
	private long cityID;
	
	private String name;
	
	@JoinColumn(name="state_id")
	@ManyToOne
	private State state;
	
	public City() {}

	public City(String name, State state) {
		super();
		this.name = name;
		this.state = state;
	}

	public long getCityID() {
		return cityID;
	}

	public void setCityID(long cityID) {
		this.cityID = cityID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	

}
