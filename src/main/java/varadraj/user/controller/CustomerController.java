package varadraj.user.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.common.model.JsonResponseMessage;
import varadraj.common.model.address.Address;
import varadraj.common.model.address.AddressResponse;
import varadraj.jwt.JwtGenerator;
import varadraj.user.model.LoginRequest;
import varadraj.user.model.customer.Customer;
import varadraj.user.model.customer.CustomerCreationRequest;
import varadraj.user.model.customer.CustomerResponse;
import varadraj.user.service.CustomerService;

@RestController
@CrossOrigin
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	
	private JwtGenerator jwtGenerator;
	
	public CustomerController(JwtGenerator jwtGenerator) {
		this.jwtGenerator = jwtGenerator;
	}

	@PostMapping("customer/registerUser")
	public JsonResponse<Void> registerUser(@RequestBody CustomerCreationRequest customerCreationRequest) {
		
		Customer registeredCustomer = customerService.addCustomer(customerCreationRequest);
		
		if(registeredCustomer == null) {
			return new JsonResponse<Void>(400,JsonResponseMessage.INVALID_INPUT,null);}
		else {
			return new JsonResponse<Void>(201,JsonResponseMessage.CREATED,null);
		}
	}
	
	@PostMapping("customer/login")
	public JsonResponse<String> login(@RequestBody LoginRequest loginRequest) {
		if(customerService.validateLogin(loginRequest)) {
			return new JsonResponse<String>(200
					, JsonResponseMessage.OK
					, this.jwtGenerator.generateToken(loginRequest.getUsername(),"user"));
		}
		return new JsonResponse<String>(401
				, JsonResponseMessage.BAD_CREDENTIALS
				, null);
	}
	
	@GetMapping("/auth/customer/info")
	public JsonResponse<CustomerResponse> getCustomerInfo(Principal principalUser) {
		Customer customer = customerService.findByCustomerUsername(principalUser.getName());
		Address address = customer.getAddress();
		return new JsonResponse<CustomerResponse>(200
				, JsonResponseMessage.OK
				,new CustomerResponse
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
	
}