package varadraj.unit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import springfox.documentation.spring.web.plugins.DocumentationPluginsBootstrapper;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import varadraj.common.model.address.Address;
import varadraj.common.model.address.AddressCreationRequest;
import varadraj.common.service.AddressService;
import varadraj.user.model.customer.Customer;
import varadraj.user.model.customer.CustomerCreationRequest;
import varadraj.user.repository.CustomerRepository;
import varadraj.user.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class CustomerServiceTest {
	
	@MockBean
	DocumentationPluginsBootstrapper mock; //for Swagger2

	@MockBean
	WebMvcRequestHandlerProvider handler;  //for Swagger2

	@Mock
	private CustomerRepository customerRepo;
	
	@Mock
	private AddressService addressService;
	
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	
	@InjectMocks
	private CustomerService customerService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addCustomer_HappyPath(){
	//CREATE
		CustomerCreationRequest customerCreationRequest = new CustomerCreationRequest();
		Customer customer = new Customer();
		customer.setName("someName");
		customer.setPassword("somePassword");
		customer.setUsername("someUsername");
		customer.setEmail("some@email.com");
		Address address = new Address();
		customer.setAddress(address);
	//CONDITION
		when(customerRepo.save(Mockito.any(Customer.class))).thenReturn(customer);
		when(addressService.addAddress(Mockito.any(AddressCreationRequest.class))).thenReturn(address);
		when(passwordEncoder.encode(Mockito.anyString())).thenAnswer(i -> i.getArguments()[0]);
	//TEST
		Customer savedCustomer = customerService.addCustomer(customerCreationRequest);
	//ASSERT
		assertNotNull(savedCustomer);
		assertEquals(customer.getUsername(), savedCustomer.getUsername());
	//VERIFY
		verify(customerRepo).save(Mockito.any(Customer.class));
	}
	
	@Test
	public void addCustomer_whenAddressNULL_thenReturnNULL(){
	//CREATE
		CustomerCreationRequest customerCreationRequest = new CustomerCreationRequest();
		Customer customer = new Customer();
		customer.setUsername("username");
		customer.setName("someName");
		customer.setPassword("somePassword");
	//TEST
		Customer savedCustomer = customerService.addCustomer(customerCreationRequest);
	//ASSERT
		assertNull(savedCustomer);
	//VERIFY
		verify(customerRepo,never()).save(customer);
	}
	
	@Test
	public void addCustomer_whenNULL_thenReturnNULL(){
	//CREATE
		CustomerCreationRequest customerCreationRequest = new CustomerCreationRequest();
		Customer customer = null;
	//TEST
		Customer savedCustomer = customerService.addCustomer(customerCreationRequest);
	//ASSERT
		assertNull(savedCustomer);
	//VERIFY
		verify(customerRepo,never()).save(customer);
	}
	
}
