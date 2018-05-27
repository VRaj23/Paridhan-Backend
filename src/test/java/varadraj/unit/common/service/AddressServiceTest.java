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
import varadraj.common.model.address.Address;
import varadraj.common.model.address.AddressCreationRequest;
import varadraj.common.model.city.City;
import varadraj.common.model.state.State;
import varadraj.common.repository.AddressRepository;
import varadraj.common.service.AddressService;
import varadraj.common.service.CityService;
import varadraj.exception.InvalidInputException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class AddressServiceTest {

	@MockBean
	DocumentationPluginsBootstrapper mock; //for Swagger2

	@MockBean
	WebMvcRequestHandlerProvider handler;  //for Swagger2
	
	@MockBean
	JavaMailSender emailSender; //for Email Service
	
	@Mock
	private AddressRepository addressRepo;
	
	@Mock
	private CityService cityService;
	
	@InjectMocks
	private AddressService addressService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addAddress_whenRequestValid_thenSave() throws InvalidInputException {
	//CREATE
		AddressCreationRequest addressCreationRequest 
			= new AddressCreationRequest("123", "area", "landmark", 2, 456123);
		
		State state = new State("state");
		state.setStateID(1);
		
		City city = new City("city", state);
		city.setCityID(2);
		
		Address address = new Address("123", "area", "landmark", city, 456123);
	
	//CONDITION
		when(cityService.findByCityID(2)).thenReturn(Optional.of(city));
		when(addressRepo.save(Mockito.any(Address.class))).thenReturn(address);
		
	//TEST
		Optional<Address> savedAddress = addressService.addAddress(Optional.of(addressCreationRequest));
		
	//ASSERT
		assertTrue(savedAddress.isPresent());
		assertEquals(address, savedAddress.get());
		assertEquals(address.getCity(), savedAddress.get().getCity());
		
	//VERIFY
		verify(addressRepo).save(Mockito.any(Address.class));
	}

}
