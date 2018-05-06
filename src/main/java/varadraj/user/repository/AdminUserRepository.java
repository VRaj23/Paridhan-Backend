package varadraj.user.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import varadraj.user.model.AdminUser;

public interface AdminUserRepository extends CrudRepository<AdminUser, String>{

	public Optional<AdminUser> findByUsername(String username);
}
