package varadraj.unit.common.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import varadraj.common.model.city.City;
import varadraj.common.model.state.State;
import varadraj.common.model.city.CityCreationRequest;
import varadraj.common.service.CityService;
import varadraj.jwt.JwtGenerator;
import varadraj.jwt.JwtValidator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressAdminCityTest {
	
	private MockMvc mockMvc;
	
	@MockBean
	private CityService cityService;
	
	@MockBean
	private WebApplicationContext context;
	
	@MockBean
	private JwtGenerator generator;
	
	@MockBean
	private JwtValidator validator;
	
	@Before
	public void setUp() throws Exception{
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
	}
	
	@Test
	public void addCity_HappyPath() {
	//CREATE
		State state = new State("someState");
		City city = new City("someCity", state);
		String[] tokenDetail = {"username","admin"};
	
	//CONDITION
		Mockito.when(
				cityService.addCity(Mockito.any(CityCreationRequest.class)))
				.thenReturn(city);
		
		Mockito.when(
				validator.validateToken(Mockito.anyString()) )
				.thenReturn(tokenDetail);
		
	}

}
