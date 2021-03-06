package varadraj.order.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import varadraj.order.model.Orders;
import varadraj.user.model.customer.Customer;

public interface OrderRepository extends CrudRepository<Orders, Long>{
	
	List<Orders> findByCustomerOrderByLastUpateDateTimeDesc(Customer customer);

}
