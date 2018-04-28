package varadraj.order.repository;

import org.springframework.data.repository.CrudRepository;

import varadraj.order.model.Orders;

public interface OrderRepository extends CrudRepository<Orders, Long>{

}
