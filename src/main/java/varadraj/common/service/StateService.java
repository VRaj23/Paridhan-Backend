package varadraj.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.common.model.state.State;
import varadraj.common.model.state.StateCreationRequest;
import varadraj.common.repository.StateRepository;

@Service
public class StateService {
	
	@Autowired
	private StateRepository stateRepo;

//VALIDATIONS
	private boolean isValidRequest(StateCreationRequest stateRequest) {
		if(stateRequest.getStateName() == null)
			return false;
		else
			return true;
	}

	
	
//CREATE	
	public State addState(StateCreationRequest stateRequest) {
		State savedState = null;
		
		if(this.isValidRequest(stateRequest)) {
			State state = new State(stateRequest.getStateName());
			savedState = stateRepo.save(state);
		}
		
		return savedState;
	}

//READ

	public State findByStateID(long stateID) {
			return stateRepo.findByStateID(stateID);
	}


}
