package varadraj.user.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import varadraj.user.model.customer.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
	Optional<Customer> findByUsername(String username);
	
	Optional<Customer> findByCustomerID(long customerID);

}
