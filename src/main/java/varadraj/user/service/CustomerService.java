package varadraj.user.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import varadraj.common.model.Address;
import varadraj.common.service.AddressService;
import varadraj.user.model.Customer;
import varadraj.user.model.LoginRequest;
import varadraj.user.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	AddressService addressService;
	
	public boolean validateRegistrationRequest(Customer customer) {
		//TODO username must not contain special char
		return true;
	}
	
	public boolean validateLogin(LoginRequest loginRequest) {
		
		if( (loginRequest.getUsername() == null) || (loginRequest.getPassword() == null) )
			return false;
		
		Customer customer = customerRepo.findByUsername(loginRequest.getUsername());
		if( customer == null)
			return false;
		
		return passwordEncoder.matches(loginRequest.getPassword(), customer.getPassword() );
	}
	
	
//CREATE
	public void addCustomer(Customer customer) {
		String passwd = customer.getPassword().trim();
		
		//TODO address == null ? Remove HardCoding
		Address address = new Address();
		address.setCity("Kolkata");
		addressService.addAddress(address);
		if(customer.getAddress() != null)
			address = addressService.addAddress(customer.getAddress());
		//
		
		customer.setPassword(passwordEncoder.encode(passwd));
		customer.setAddress(address);
		customer.setCreationDateTime(LocalDateTime.now());
		customerRepo.save(customer);
	}
	
//READ
	public Customer findByCustomerUsername(String username) {
		return customerRepo.findByUsername(username);
	}
	
	public Customer findByCustomerID(long customerID) {
		return customerRepo.findByCustomerID(customerID);
	}
	
	public String getName(String username) {
		return customerRepo.findByUsername(username).getName();
	}
	
//UPDATE
//DELETE


}
