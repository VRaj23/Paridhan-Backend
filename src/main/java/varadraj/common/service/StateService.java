package varadraj.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.common.model.State;
import varadraj.common.repository.StateRepository;

@Service
public class StateService {
	
	@Autowired
	private StateRepository stateRepo;

//VALIDATIONS
	private boolean isValid(State state) {
		if(state.getStateName() == null)
			return false;
		else
			return true;
	}

	
	
//CREATE	
	public State addState(State state) {
		State savedState = null;
		
		if(this.isValid(state)) {
			savedState = stateRepo.save(state);
		}
		
		return savedState;
	}


}
