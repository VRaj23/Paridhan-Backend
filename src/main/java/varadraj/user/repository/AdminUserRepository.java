package varadraj.user.repository;

import org.springframework.data.repository.CrudRepository;

import varadraj.user.model.AdminUser;

public interface AdminUserRepository extends CrudRepository<AdminUser, String>{

	public AdminUser findByUsername(String username);
}
