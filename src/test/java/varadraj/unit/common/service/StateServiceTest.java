package varadraj.unit.common.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import springfox.documentation.spring.web.plugins.DocumentationPluginsBootstrapper;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import varadraj.common.model.state.State;
import varadraj.common.model.state.StateCreationRequest;
import varadraj.common.repository.StateRepository;
import varadraj.common.service.StateService;
import varadraj.exception.InvalidInputException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
public class StateServiceTest {
	
	@MockBean
	DocumentationPluginsBootstrapper mock; //for Swagger2

	@MockBean
	WebMvcRequestHandlerProvider handler;  //for Swagger2
	
	@Mock
	private StateRepository stateRepo;
	
	@InjectMocks
	private StateService stateService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addState_whenValidRequest_thenSave() throws InvalidInputException {
		State state = new State("Sample");
		StateCreationRequest request = new StateCreationRequest("Sample");
		
		when(stateRepo.save(Mockito.any(State.class)) ).thenReturn(state);
		
		Optional<State> savedState = stateService.addState(Optional.of(request));
		
		assertTrue(savedState.isPresent());
		assertEquals(state.getStateName(), savedState.get().getStateName());
		
		verify(stateRepo).save(Mockito.any(State.class));
	}
	
	@Test(expected = InvalidInputException.class)
	public void addState_whenStateNameNULL_throwException() throws InvalidInputException {

		stateService.addState(Optional.of(new StateCreationRequest()));
	}

}
