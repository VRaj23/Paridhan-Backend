package varadraj.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import varadraj.common.model.address.Address;
import varadraj.common.model.address.AddressCreationRequest;
import varadraj.common.model.address.AddressResponse;
import varadraj.common.service.AddressService;
import varadraj.common.service.EmailService;
import varadraj.exception.InvalidInputException;
import varadraj.user.model.LoginRequest;
import varadraj.user.model.customer.Customer;
import varadraj.user.model.customer.CustomerCreationRequest;
import varadraj.user.model.customer.CustomerResponse;
import varadraj.user.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	EmailService emailService;

	
//CREATE
	public Optional<Customer> addCustomer(Optional<CustomerCreationRequest> request) throws InvalidInputException {
		Optional<AddressCreationRequest> addressRequest = 
				Optional.ofNullable(request.map(CustomerCreationRequest::getAddressCreationRequest)
				.orElseThrow(InvalidInputException::new));
		
		Optional<Address> savedAddress = addressService.addAddress(addressRequest);
		Address address = null;
		if(savedAddress.isPresent()) {
			address = savedAddress.get();
		}
		else
			throw new InvalidInputException();
	
		
		return Optional.ofNullable(customerRepo.save(new Customer
				( request.map(CustomerCreationRequest::getUsername).orElseThrow(InvalidInputException::new)
				, passwordEncoder.encode(request.map(CustomerCreationRequest::getPassword).orElseThrow(InvalidInputException::new))
				, request.map(CustomerCreationRequest::getName).orElseThrow(InvalidInputException::new)
				, address
				, request.map(CustomerCreationRequest::getEmail).orElse(null))));
	}
	
//READ
	
	public boolean validateLogin(LoginRequest loginRequest) {
		
		if( (loginRequest.getUsername() == null) || (loginRequest.getPassword() == null) )
			return false;
		
		Optional<Customer> customer = customerRepo.findByUsername(loginRequest.getUsername());
		if( !customer.isPresent())
			return false;
		
		return passwordEncoder.matches(loginRequest.getPassword(), customer.map(Customer::getPassword).get() );
	}
	
	public Optional<Customer> findByCustomerUsername(String username) {
		if(username == null)
			return Optional.empty();
		else
			return customerRepo.findByUsername(username);
	}
	
	public Optional<Customer> findByCustomerID(long customerID) {
		if(customerID <=0)
			return Optional.empty();
		else
			return customerRepo.findByCustomerID(customerID);
	}

	public Optional<CustomerResponse> getCustomerInfo(String username) {
		Optional<Customer> checkCustomer = this.findByCustomerUsername(username);
		if(!checkCustomer.isPresent())
			return Optional.empty();
		Customer customer = checkCustomer.get();
		Address address = customer.getAddress();
		
		 return Optional.of(new CustomerResponse
				(customer.getCustomerID()
						, customer.getUsername()
						, customer.getName()
						, customer.getEmail()
						, new AddressResponse
								( address.getAddressID()
								, address.getHouseNumber()
								, address.getArea()
								, address.getLandmark()
								, address.getCity().getCityName()
								, address.getCity().getState().getStateName()
								, address.getPincode())));
		
	}

	
//UPDATE
//DELETE
	
//HELPER
	public CustomerResponse getCustomerResponse(Customer customer) {
		return new CustomerResponse(
				customer.getCustomerID(), 
				customer.getUsername(), 
				customer.getName(), 
				customer.getEmail(), 
				addressService.getAddressResponse(customer.getAddress()));
		
	}
	
//UTIL
	public void sendWelcomeEmail(Optional<CustomerCreationRequest> request) {
		String email = request.map(CustomerCreationRequest::getEmail).orElse(null);
		String name = request.map(CustomerCreationRequest::getName).orElse(null);
		if((email != null)&&(name != null)) {
			try {
				emailService.sendWelcomeMail(email, name);
			}catch(MailException e) {
				System.out.println("Welcome e-mail could not be sent to "+name);
			}
		}
	}


}
