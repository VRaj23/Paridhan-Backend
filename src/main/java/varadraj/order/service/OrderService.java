package varadraj.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.order.model.OrderCreationRequest;
import varadraj.order.model.Orders;
import varadraj.order.repository.OrderRepository;
import varadraj.product.service.ProductService;
import varadraj.user.service.CustomerService;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	
	@Autowired
	private CustomerService  customerService;
	
	@Autowired
	private ProductService productService;
	
	public boolean isValidRequest(OrderCreationRequest request) {
		if(request.getAmount()<=0)
			return false;
		if(request.getQuantity() <= 0)
			return false;
		
		return true;
	}
		
//CREATE
	
	public void addOrder(OrderCreationRequest request) {
			Orders order = new Orders(
					customerService.findByCustomerUsername(request.getUsername())
					, productService.findByLineID(request.getProductLineID())
					, request.getAmount()//TODO calculate; don't use value provided in request
					, request.getQuantity()
					, request.getDeliveryAddress());
			
			orderRepo.save(order);
	}

	
//READ
	
	//TODO get all orders
	
	//TODO get customer orders
	
//UPDATE
//DELTE
}
