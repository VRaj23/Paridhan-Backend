package varadraj.user.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.common.model.ResponseMessage;
import varadraj.exception.InvalidInputException;
import varadraj.jwt.JwtGenerator;
import varadraj.user.model.LoginRequest;
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
	public JsonResponse<Void> registerUser(@RequestBody CustomerCreationRequest request) {
		try {
			if( customerService.addCustomer(Optional.ofNullable(request)).isPresent()) {
				new Thread(() -> customerService.sendWelcomeEmail(Optional.ofNullable(request))).start();;
				return new JsonResponse<Void>(201, ResponseMessage.CREATED, null);
			}
			else
				return new JsonResponse<Void>(500, ResponseMessage.ERROR, null);
		} catch (InvalidInputException e) {
			return new JsonResponse<Void>(500,ResponseMessage.INVALID_INPUT,null);
		}
	}
	
	@PostMapping("customer/login")
	public JsonResponse<String> login(@RequestBody LoginRequest loginRequest) {
		if(customerService.validateLogin(loginRequest)) {
			return new JsonResponse<String>(200
					, ResponseMessage.OK
					, this.jwtGenerator.generateToken(loginRequest.getUsername(),"user"));
		}
		return new JsonResponse<String>(401
				, ResponseMessage.BAD_CREDENTIALS
				, null);
	}
	
	@GetMapping("/auth/customer/info")
	public JsonResponse<CustomerResponse> getCustomerInfo(Principal principalUser) {
		
		Optional<CustomerResponse> response = customerService.getCustomerInfo(principalUser.getName());
		if( response.isPresent())
			return new JsonResponse<CustomerResponse>(200
				, ResponseMessage.OK
				, response.get());
		else
			return new JsonResponse<CustomerResponse>(500, ResponseMessage.ERROR,null);
	}
	
}