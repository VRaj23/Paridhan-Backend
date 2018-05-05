package varadraj.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import varadraj.common.model.address.Address;
import varadraj.common.service.AddressService;
import varadraj.user.model.LoginRequest;
import varadraj.user.model.customer.Customer;
import varadraj.user.model.customer.CustomerCreationRequest;
import varadraj.user.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	AddressService addressService;

//VALIDATIONS
	private boolean isValidCustomer(CustomerCreationRequest customerRequest) {
		//TODO username must not contain special char
		if(customerRequest == null)
			return false;
		if(customerRequest.getUsername() == null)
			return false;
		if(customerRequest.getPassword()  == null)
			return false;
		if(customerRequest.getName() == null)
			return false;
		return true;		
	}
	
	
//CREATE
	public Customer addCustomer(CustomerCreationRequest customerRequest) {
		Customer savedCustomer = null;
		
		if(this.isValidCustomer(customerRequest)) {
			
			
			Address savedAddress = addressService.addAddress(customerRequest.getAddressCreationRequest());
			if(savedAddress == null) {
				return null;}
			
			Customer customer = new Customer(customerRequest.getUsername()
					, passwordEncoder.encode(customerRequest.getPassword())
					, customerRequest.getName()
					, savedAddress
					, customerRequest.getEmail());
			
			savedCustomer = customerRepo.save(customer);
		}
				
		return savedCustomer;
	}
	
//READ
	
	public boolean validateLogin(LoginRequest loginRequest) {
		
		if( (loginRequest.getUsername() == null) || (loginRequest.getPassword() == null) )
			return false;
		
		Customer customer = customerRepo.findByUsername(loginRequest.getUsername());
		if( customer == null)
			return false;
		
		return passwordEncoder.matches(loginRequest.getPassword(), customer.getPassword() );
	}
	
	public Customer findByCustomerUsername(String username) {
		if(username == null)
			return null;
		else
			return customerRepo.findByUsername(username);
	}
	
	public Customer findByCustomerID(long customerID) {
		if(customerID <=0)
			return null;
		else
			return customerRepo.findByCustomerID(customerID);
	}

	
//UPDATE
//DELETE


}
