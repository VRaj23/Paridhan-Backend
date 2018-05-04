package varadraj.unit.common.service;

import static org.junit.Assert.assertEquals;
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
import varadraj.common.model.Address;
import varadraj.common.model.City;
import varadraj.common.model.State;
import varadraj.common.repository.AddressRepository;
import varadraj.common.service.AddressService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class AddressServiceTest {
	
	@MockBean
	DocumentationPluginsBootstrapper mock; //for Swagger2

	@MockBean
	WebMvcRequestHandlerProvider handler;  //for Swagger2
	
	@Mock
	private AddressRepository addressRepo;
	
	@InjectMocks
	private AddressService addressService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addAddress_HappyPath() {
	//CREATE
		Address address = new Address();
		address.setHouse_number("Flat No. 1");
		address.setArea("areaORcolony");
		address.setLandmark("someplace near house");
		
		State state = new State();
		state.setStateName("state");
		City city = new City();
		city.setState(state);
		city.setName("city");		
		address.setCity(city);
		
		address.setCity(city);
		address.setPincode(123456);
	
	//CONDITION
		when(addressRepo.save(address)).thenReturn(address);
	//TEST
		Address savedAddress = addressService.addAddress(address);
		
	//ASSERT
		assertEquals(address, savedAddress);
		
	//VERIFY
		verify(addressRepo).save(address);
	}

}
