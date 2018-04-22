package varadraj.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.jwt.JwtGenerator;
import varadraj.model.JsonResponse;
import varadraj.user.model.LoginRequest;
import varadraj.user.service.AdminUserService;

@RestController
@RequestMapping("/user")
public class AdminController {
	
	@Autowired
	private AdminUserService adminService;
	
	private JwtGenerator jwtGenerator;
	
	public AdminController(JwtGenerator jwtGenerator) {
		this.jwtGenerator = jwtGenerator;
	}

	@PostMapping("/adminLogin")
	public JsonResponse adminLogin(@RequestBody LoginRequest loginRequest) {
		
		if(adminService.validateLogin(loginRequest)) {
			return new JsonResponse(200,jwtGenerator.generateToken(loginRequest.getUsername()));
			}
		else
			return new JsonResponse(401,"Admin Login Failed");
	}
	
	@PostMapping("/admin/resetPassword")
	public JsonResponse adminPasswordReset() {
		
		return new JsonResponse(501,"not implemented");
	}
}
