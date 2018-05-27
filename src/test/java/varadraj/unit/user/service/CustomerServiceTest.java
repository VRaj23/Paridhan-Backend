package varadraj.unit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import springfox.documentation.spring.web.plugins.DocumentationPluginsBootstrapper;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import varadraj.common.model.address.Address;
import varadraj.common.model.address.AddressCreationRequest;
import varadraj.common.service.AddressService;
import varadraj.exception.InvalidInputException;
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
	
	@MockBean
	JavaMailSender emailSender; //for Email Service

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
	public void addCustomerTest_whenValidRequest_thenSave() throws InvalidInputException {
		
		AddressCreationRequest addressCreationRequest = new AddressCreationRequest();
		
		CustomerCreationRequest request = new CustomerCreationRequest(
				 "username" //user name
				,"password" //password
				,"name" //name
				,"email" //email
				,addressCreationRequest //addressCreation Request
				);
		
		Address address = new Address();
		
		Customer customer = new Customer("username", passwordEncoder.encode("password"), "name", address, "email");
		
		when(addressService.addAddress(Optional.of(addressCreationRequest))).thenReturn(Optional.of(address));
		when(customerRepo.save(Mockito.any(Customer.class))).thenReturn(customer);
		
		Optional<Customer> savedCustomer = customerService.addCustomer(Optional.of(request));
		
		assertTrue(savedCustomer.isPresent());
		assertEquals(savedCustomer.get().getUsername(), customer.getUsername());
		
		
	}
	
}
