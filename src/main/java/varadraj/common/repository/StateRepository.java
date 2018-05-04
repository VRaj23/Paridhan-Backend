package varadraj.common.repository;

import org.springframework.data.repository.CrudRepository;

import varadraj.common.model.State;

public interface StateRepository extends CrudRepository<State, Long>{
	
	public State findByStateID(long stateID);
	
	public State findByStateName(String name);

}
