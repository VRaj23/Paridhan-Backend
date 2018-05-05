package varadraj.unit.common.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
	public void addState_HappyPath() {
	//CREATE
		State state = new State();
		state.setStateName("Sample");
		
	//CONDITION
		when(stateRepo.save(state)).thenReturn(state);
		
	//TEST
		State savedState = stateService.addState(new StateCreationRequest());
		
	//ASSERT
		assertEquals(state.getStateName(), savedState.getStateName());
		
	//VERIFY
		verify(stateRepo).save(state);
	}
	
	@Test
	public void addState_StateNameNULL() {
	//CREATE
		State state = new State();
		
	//TEST
		State savedState = stateService.addState(new StateCreationRequest());
		
	//ASSERT
		assertNull(savedState);
		
	//VERIFY	
		verify(stateRepo, never()).save(state);
	}

}
