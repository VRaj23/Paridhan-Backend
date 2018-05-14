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
import varadraj.common.model.city.City;
import varadraj.common.model.city.CityCreationRequest;
import varadraj.common.model.state.State;
import varadraj.common.repository.CityRepository;
import varadraj.common.service.CityService;
import varadraj.common.service.StateService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class CityServiceTest {
	/*
	@MockBean
	DocumentationPluginsBootstrapper mock; //for Swagger2

	@MockBean
	WebMvcRequestHandlerProvider handler;  //for Swagger2
	
	@Mock
	private StateService StateService;
	
	@Mock
	private CityRepository cityRepo;
	
	@InjectMocks
	private CityService cityService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addCity_HappyPath() {
	//CREATE
		CityCreationRequest cityCreationRequest = new CityCreationRequest();
		City city = new City();
		city.setCityName("city");
		
		State state = new State();
		state.setStateName("state");
		city.setState(state);
		
	//CONDITON
		when(cityRepo.save(city)).thenReturn(city);
		
	//TEST
		City savedCity = cityService.addCity(cityCreationRequest);
		
	//ASSERT
		assertEquals(city.getCityName(), savedCity.getCityName());
		assertEquals(city.getState(), state);
	
	//VERIFY
		verify(cityRepo).save(city);
	}
	
	@Test
	public void addCity_CityNameNULL() {
	//CREATE
		City city = new City();
		CityCreationRequest cityCreationRequest = null;
		State state = new State();
		state.setStateName("state");
		city.setState(state);
		
	//TEST
		City savedCity = cityService.addCity(cityCreationRequest);
		
	//ASSERT
		assertNull(savedCity);
	
	//VERIFY
		verify(cityRepo,never()).save(city);
	}
	
	@Test
	public void addCity_StateNULL() {
	//CREATE
		CityCreationRequest cityCreationRequest = null;
		City city = new City();
		city.setCityName("city");
		
	//TEST
		City savedCity = cityService.addCity(cityCreationRequest);
		
	//ASSERT
		assertNull(savedCity);
		
	//VERIFY
		verify(cityRepo,never()).save(city);
	}
*/
}
