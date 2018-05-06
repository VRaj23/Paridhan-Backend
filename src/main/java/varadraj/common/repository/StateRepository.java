package varadraj.common.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import varadraj.common.model.state.State;

public interface StateRepository extends CrudRepository<State, Long>{
	
	Optional<State> findByStateID(long stateID);

}
