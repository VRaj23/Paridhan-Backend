package varadraj.order.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import varadraj.order.model.OrderHeader;
import varadraj.user.model.Customer;

public interface OrderHeaderRepository extends CrudRepository<OrderHeader, Long>{

	public OrderHeader findByOrderHeaderID(long headerID);
	
	public List<OrderHeader> findByCustomer(Customer customer);
}
