package varadraj.user.repository;

import org.springframework.data.repository.CrudRepository;

import varadraj.user.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
	Customer findByUsername(String username);
	
	Customer findByCustomerID(long customerID);

}
