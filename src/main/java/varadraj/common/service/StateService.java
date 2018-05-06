package varadraj.common.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.common.model.state.State;
import varadraj.common.model.state.StateCreationRequest;
import varadraj.common.repository.StateRepository;
import varadraj.exception.InvalidInputException;;

@Service
public class StateService {
	
	@Autowired
	private StateRepository stateRepo;


//CREATE	
	public Optional<State> addState(Optional<StateCreationRequest> stateRequest) throws InvalidInputException {
		
		String name = stateRequest.map(StateCreationRequest::getStateName).orElseThrow(InvalidInputException::new);
		
		return Optional.ofNullable(stateRepo.save(new State(name)));
	}
	
//READ

	public Optional<State> findByStateID(long stateID) {
			return stateRepo.findByStateID(stateID);
	}


}
