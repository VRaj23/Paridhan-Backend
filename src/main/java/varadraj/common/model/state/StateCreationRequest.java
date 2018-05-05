package varadraj.common.model.state;

public class StateCreationRequest {
	
	private String stateName;
	
	public StateCreationRequest() {}

	public StateCreationRequest(String stateName) {
		super();
		this.stateName = stateName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	

}
