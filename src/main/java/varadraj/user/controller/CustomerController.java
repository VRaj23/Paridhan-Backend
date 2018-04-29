package varadraj.user.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import varadraj.jwt.JwtGenerator;
import varadraj.common.model.JsonResponse;
import varadraj.user.model.Customer;
import varadraj.user.model.LoginRequest;
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
	public JsonResponse registerUser(@RequestBody Customer customer) {
		
		if(!customerService.validateRegistrationRequest(customer))
			return new JsonResponse(400,"Customer Registration Failed");
		
		customerService.addCustomer(customer);
		return new JsonResponse(201,"Customer Registered");
	}
	
	@PostMapping("customer/login")
	public JsonResponse login(@RequestBody LoginRequest loginRequest) {
		if(customerService.validateLogin(loginRequest)) {
			return new JsonResponse(200, this.jwtGenerator.generateToken(loginRequest.getUsername(),"user"));
		}
		return new JsonResponse(401,"Username or Password is incorrect");
	}
	
	@GetMapping("/auth/customer/name")
	@CrossOrigin
	public JsonResponse getName(Principal principalUser) {
		return new JsonResponse(200,customerService.getName(principalUser.getName()));
	}
	
}
