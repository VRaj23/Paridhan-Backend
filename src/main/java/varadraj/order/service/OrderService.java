package varadraj.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.common.model.Address;
import varadraj.order.model.OrderCreationRequest;
import varadraj.order.model.Orders;
import varadraj.order.repository.OrderRepository;
import varadraj.product.service.ProductService;
import varadraj.user.model.Customer;
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
	
	public void addOrder(OrderCreationRequest request, String username) {
			
			
			Orders order = new Orders(
					customerService.findByCustomerUsername(username)
					, productService.findByLineID(request.getProductLineID())
					, productService.getPrice(request.getProductLineID()) * request.getQuantity()//TODO Warn Mismatch
					, request.getQuantity()
					, this.deliveryAddressNullCheck(request.getDeliveryAddress(), username));
			
			orderRepo.save(order);
	}
	
	private Address deliveryAddressNullCheck(Address requestAddress, String username) {
		if(requestAddress == null)
			return customerService.findByCustomerUsername(username).getAddress();
		else
			return requestAddress;
	}

	
//READ
	
	//TODO get all orders
	
	public List<Orders> getAllCustomerOrders(String username) {
		Customer customer = customerService.findByCustomerUsername(username);
		List<Orders> orders = new ArrayList<>();
		orderRepo.findByCustomer(customer).forEach(orders::add);
		return orders;
	}
	
//UPDATE
//DELTE
}
