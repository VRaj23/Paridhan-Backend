package varadraj.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import varadraj.jwt.JwtGenerator;
import varadraj.common.model.JsonResponse;
import varadraj.common.model.ResponseMessage;
import varadraj.user.model.LoginRequest;
import varadraj.user.service.AdminUserService;

@RestController
@CrossOrigin
public class AdminController {
	
	@Autowired
	private AdminUserService adminService;
	
	private JwtGenerator jwtGenerator;
	
	public AdminController(JwtGenerator jwtGenerator) {
		this.jwtGenerator = jwtGenerator;
	}

	@PostMapping("/admin/login")
	public JsonResponse<String> adminLogin(@RequestBody LoginRequest loginRequest) {
		
		if(adminService.validateLogin(loginRequest)) {
			return new JsonResponse<String>(200
					,ResponseMessage.OK
					,jwtGenerator.generateToken(loginRequest.getUsername(),"admin"));
			}
		else
			return new JsonResponse<String>(401
					,ResponseMessage.BAD_CREDENTIALS
					,null);
	}
	
	
	@PreAuthorize(value = "hasRole('ADMIN')")
	@PostMapping("/auth/admin/resetPassword")
	public JsonResponse<Void> adminPasswordReset() {
		
		return new JsonResponse<Void>(501,ResponseMessage.NOT_IMPLEMENTED,null);
	}

}
