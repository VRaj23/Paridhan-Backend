package varadraj.integration.common.repository;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import varadraj.common.model.state.State;
import varadraj.common.repository.StateRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class StateRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private StateRepository stateRepo;
	
	@Test
	public void findStateByStateID() {
	//CREATE
		State state = new State();
		state.setStateName("someState");
		entityManager.persist(state);
	
	//TEST
		Optional<State> foundState = stateRepo.findByStateID(0);
	
	//ASSERT
		//assertEquals(state.getStateName(), foundState.getStateName());
	}

}
