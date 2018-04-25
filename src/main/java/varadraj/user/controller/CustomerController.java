package varadraj.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.jwt.JwtGenerator;
import varadraj.common.model.JsonResponse;
import varadraj.user.model.Customer;
import varadraj.user.model.LoginRequest;
import varadraj.user.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	private JwtGenerator jwtGenerator;
	
	public CustomerController(JwtGenerator jwtGenerator) {
		this.jwtGenerator = jwtGenerator;
	}

	@PostMapping("/registerUser")
	public JsonResponse registerUser(@RequestBody Customer customer) {
		
		if(!customerService.validateRegistrationRequest(customer))
			return new JsonResponse(400,"Customer Registration Failed");
		
		customerService.addCustomer(customer);
		return new JsonResponse(201,"Customer Registered");
	}
	
	@PostMapping("/login")
	public JsonResponse login(@RequestBody LoginRequest loginRequest) {
		if(customerService.validateLogin(loginRequest)) {
			return new JsonResponse(200, this.jwtGenerator.generateToken(loginRequest.getUsername()));
		}
		return new JsonResponse(401,"Username or Password is incorrect");
	}
}
