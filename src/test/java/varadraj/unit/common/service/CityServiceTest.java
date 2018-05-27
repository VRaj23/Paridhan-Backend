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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import springfox.documentation.spring.web.plugins.DocumentationPluginsBootstrapper;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import varadraj.common.model.city.City;
import varadraj.common.model.city.CityCreationRequest;
import varadraj.common.model.state.State;
import varadraj.common.repository.CityRepository;
import varadraj.common.service.CityService;
import varadraj.common.service.StateService;
import varadraj.exception.InvalidInputException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class CityServiceTest {
	
	@MockBean
	DocumentationPluginsBootstrapper mock; //for Swagger2

	@MockBean
	WebMvcRequestHandlerProvider handler;  //for Swagger2
	
	@MockBean
	JavaMailSender emailSender; //for Email Service
	
	@Mock
	private StateService stateService;
	
	@Mock
	private CityRepository cityRepo;
	
	@InjectMocks
	private CityService cityService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addCity_whenValidRequest_thenSave() throws InvalidInputException {
	//CREATE
		CityCreationRequest cityCreationRequest = new CityCreationRequest("city", 1);
		
		State state = new State("state");
		state.setStateID(1);
		
		City city = new City("city", state);
		
	//CONDITON
		when(stateService.findByStateID(1)).thenReturn(Optional.of(state));
		when(cityRepo.save(Mockito.any(City.class))).thenReturn(city);
		
		
	//TEST
		Optional<City> savedCity = cityService.addCity(Optional.of(cityCreationRequest));
		
	//ASSERT
		assertTrue(savedCity.isPresent());
		assertEquals(city.getCityName(), savedCity.get().getCityName());
		assertEquals(city.getState(), savedCity.get().getState());
	
	//VERIFY
		verify(cityRepo).save(Mockito.any(City.class));
		verify(stateService).findByStateID(1);
	}
	
	@Test(expected = InvalidInputException.class)
	public void addCity_whenCityNameNULL_throwException() throws InvalidInputException {
		CityCreationRequest cityCreationRequest = new CityCreationRequest(null, 1);
		
		State state = new State("state");
		state.setStateID(1);
		
		when(stateService.findByStateID(1)).thenReturn(Optional.of(state));
		
		cityService.addCity(Optional.of(cityCreationRequest));
		
	}
	
	@Test(expected = InvalidInputException.class)
	public void addCity_whenStateIDisInvalid_thenThrowException() throws InvalidInputException {
	
		CityCreationRequest cityCreationRequest = new CityCreationRequest("city", 1);
		
		when(stateService.findByStateID(1)).thenReturn(Optional.empty());
		
		cityService.addCity(Optional.of(cityCreationRequest));
	}

}
