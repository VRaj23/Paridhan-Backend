package varadraj.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import varadraj.user.model.AdminUser;
import varadraj.user.model.LoginRequest;
import varadraj.user.repository.AdminUserRepository;

@Service
public class AdminUserService {

	@Autowired
	private AdminUserRepository adminRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public boolean validateLogin(LoginRequest loginRequest) {
		if(loginRequest.getUsername() == null || loginRequest.getPassword() == null)
			return false;
		
		AdminUser admin = adminRepo.findByUsername(loginRequest.getUsername().trim());
		
		
		return passwordEncoder.matches(loginRequest.getPassword().trim(), admin.getPasswordHash());
	}
}
