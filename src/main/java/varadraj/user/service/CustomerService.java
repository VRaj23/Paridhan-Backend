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

//VALIDATIONS
	private boolean isValidCustomer(Customer customer) {
		//TODO username must not contain special char
		if(customer == null)
			return false;
		if(customer.getUsername() == null)
			return false;
		else if(customer.getPassword() == null)
			return false;
		else if(customer.getName() == null)
			return false;
		else if(customer.getAddress() == null)
			return false;
		else if(customer.getEmail() == null)//TODO validate email
			return false;
		else
			return true;		
	}
	
	
//CREATE
	public Customer addCustomer(Customer customer) {
		Customer registeredCustomer = null;
		
		if(this.isValidCustomer(customer)) {
			Address savedAddress = addressService.addAddress(customer.getAddress());
			if(savedAddress == null) {
				return null;}
			
			String textPassword = customer.getPassword();
			
			customer.setPassword(passwordEncoder.encode(textPassword));
			
			customer.setCreationDateTime(LocalDateTime.now());
			
			Customer temp = new Customer(customer.getUsername()
					,passwordEncoder.encode(textPassword)
					,customer.getName()
					,savedAddress
					,customer.getEmail());
			
			Customer savedCustomer = customerRepo.save(temp);
			registeredCustomer = savedCustomer;
		}
				
		return registeredCustomer;
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
	
	public String getName(String username) {
		return this.findByCustomerUsername(username).getName();
	}
	
//UPDATE
//DELETE


}
