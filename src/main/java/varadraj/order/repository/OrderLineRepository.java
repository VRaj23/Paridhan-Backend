package varadraj.order.repository;

import org.springframework.data.repository.CrudRepository;

import varadraj.order.model.OrderLine;

public interface OrderLineRepository extends CrudRepository<OrderLine, Long>{
	
	public OrderLine findByOrderLineID(long lineID);

}
