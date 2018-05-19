package varadraj.unit.common.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import varadraj.common.model.state.State;
import varadraj.common.model.state.StateCreationRequest;
import varadraj.common.service.StateService;
import varadraj.jwt.JwtGenerator;
import varadraj.jwt.JwtValidator;
/*
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressAdminStateTest{
	
	private MockMvc mockMvc;
	
	private JacksonTester<State> stateJackon;
	
	@MockBean
	private StateService stateService;
	
    @Autowired
    private WebApplicationContext context;
    
    @MockBean
    private JwtGenerator generator; 
    
    @MockBean
    private JwtValidator validator;
	
	@Before
	public void setUp() throws Exception{
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        
        JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void addState_whenServiceReturnsState_status201() throws Exception {
	//CREATE
		State state = new State("someState");
		String[] tokenDetail = {"username","admin"};
		
	//CONDITION
		Mockito.when(
				stateService.addState(Mockito.any(StateCreationRequest.class)))
				.thenReturn(state);
		
		Mockito.when(
				validator.validateToken(Mockito.anyString()) )
				.thenReturn(tokenDetail);
	//TEST
		mockMvc.perform(post("/auth/admin/address/state")
				.header("Authorization", "Bearer "+generator.generateToken(tokenDetail[0], tokenDetail[1]))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(stateJackon.write(state).getJson()))
				.andDo(print())
				.andExpect(jsonPath("$.status",Matchers.is(201)))
				.andReturn();
	//VERIFY
		Mockito.verify(stateService).addState(Mockito.any(StateCreationRequest.class));
	}
	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void addState_whenServiceReturnsNULL_status500() throws Exception {
	//CREATE
		State state = new State();
		String[] tokenDetail = {"username","admin"};
		
	//CONDITION
		Mockito.when(
				stateService.addState(Mockito.any(StateCreationRequest.class)))
				.thenReturn(null);
		
		Mockito.when(
				validator.validateToken(Mockito.anyString()) )
				.thenReturn(tokenDetail);
	//TEST
		mockMvc.perform(post("/auth/admin/address/state")
				.header("Authorization", "Bearer "+generator.generateToken(tokenDetail[0], tokenDetail[1]))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(stateJackon.write(state).getJson()))
				.andDo(print())
				.andExpect(jsonPath("$.status",Matchers.is(500)))
				.andReturn();
	//VERIFY
		Mockito.verify(stateService).addState(Mockito.any(StateCreationRequest.class));
	}
}
*/