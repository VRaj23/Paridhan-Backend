package varadraj.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.common.service.AddressService;
import varadraj.order.model.OrderCreationRequest;
import varadraj.order.model.Orders;
import varadraj.order.repository.OrderRepository;
import varadraj.product.service.ProductService;
import varadraj.user.model.customer.Customer;
import varadraj.user.service.CustomerService;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	
	@Autowired
	private CustomerService  customerService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private AddressService addressService;

//VALIDATIONS
	private boolean isValidRequest(OrderCreationRequest request) {
		if(request == null)
			return false;
		if(request.getAmount()<=0)
			return false;
		if(request.getQuantity() <= 0)
			return false;
		if(request.getDeliveryAddressID() < 1)
			return false;
		if(addressService.findByAddressID(request.getDeliveryAddressID()) == null)
			return false;
		if(productService.findByLineID(request.getProductLineID()) == null)
			return false;
		
		return true;
	}

		
//CREATE
	
	public Orders addOrder(OrderCreationRequest request, String username) {
		Orders order = null; 	
		if(this.isValidRequest(request)) {
			
			order = new Orders(
					customerService.findByCustomerUsername(username)
					, productService.findByLineID(request.getProductLineID())
					, productService.getPrice(request.getProductLineID()) * request.getQuantity()//TODO Warn Mismatch
					, request.getQuantity()
					, addressService.findByAddressID(request.getDeliveryAddressID()));
			
			order = orderRepo.save(order);
		}
		return order;
	}
	
	
//READ
	
	//TODO get all orders
	
	public List<Orders> getAllCustomerOrders(String username) {
		Customer customer = customerService.findByCustomerUsername(username);
		List<Orders> orders = new ArrayList<>();
		orderRepo.findByCustomer(customer).forEach(orders::add);
		return orders;
	}
	
	public List<Orders> getAllOrdersAdmin(){
		List<Orders> orders = new ArrayList<>();
		orderRepo.findAll().forEach(orders::add);
		return orders;
	}
	
//UPDATE
//DELTE
}
