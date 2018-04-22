package varadraj.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.model.JsonResponse;
import varadraj.user.model.LoginRequest;

@RestController
@RequestMapping("/user")
public class UserController {

	@PostMapping("/registerUser")
	public JsonResponse registerUser(@RequestBody LoginRequest loginRequest) {
		
		return new JsonResponse(501,"not implemented");
	}
	
	@PostMapping("/login")
	public JsonResponse login(@RequestBody LoginRequest loginRequest) {
		
		return new JsonResponse(501,"not implemented");
	}
}
