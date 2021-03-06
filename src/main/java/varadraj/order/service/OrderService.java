package varadraj.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.common.model.address.Address;
import varadraj.common.service.AddressService;
import varadraj.common.service.EmailService;
import varadraj.exception.InvalidInputException;
import varadraj.order.model.OrderCreationRequest;
import varadraj.order.model.OrderResponse;
import varadraj.order.model.OrderStatus;
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
	
	@Autowired
	private EmailService emailService;

		
//CREATE
	
	public List<Orders> addOrders(List<OrderCreationRequest> requests, String username) throws Exception{
		List<Orders> savedOrdersList = new ArrayList<Orders>();
		
		for(OrderCreationRequest request : requests) {
			Optional<Orders> savedOrder = this.addOrder(Optional.ofNullable(request), username);
			if(savedOrder.isPresent()) {
				savedOrdersList.add(savedOrder.get());
			}else {
				throw new Exception();
			}
		}
		return savedOrdersList;
	}
	
	private Optional<Orders> addOrder(Optional<OrderCreationRequest> request, String username) throws InvalidInputException{

		int quantity = request.map(OrderCreationRequest::getQuantity).orElseThrow(InvalidInputException::new);
		if(quantity <= 0)
			throw new InvalidInputException();
		
		Optional<Customer> customer = customerService.findByCustomerUsername(username);
		if(!customer.isPresent())
			return Optional.empty();
		
		Optional<Address> address = addressService.findByAddressID
				(request.map(OrderCreationRequest::getDeliveryAddressID).orElseThrow(InvalidInputException::new ));
		if(!address.isPresent())
			return Optional.empty();
		
		
		return Optional.ofNullable(orderRepo.save(new Orders
				(customer.get()
				, productService.findByLineID(request.map(OrderCreationRequest::getProductLineID).orElseThrow(InvalidInputException::new))
				, quantity
				, productService.getPrice(request.get().getProductLineID()) * quantity //TODO warn mismatch
				, address.get())));
	}
	
	
//READ
	
	public List<OrderResponse> getAllCustomerOrders(String username) {
		Optional<Customer> customer = customerService.findByCustomerUsername(username);
		List<OrderResponse> orders = new ArrayList<>();
		if(customer.isPresent()) {
			orderRepo.findByCustomerOrderByLastUpateDateTimeDesc(customer.get())
			.forEach( (order) -> orders.add(this.getOrderResponseAdmin(order)));
		}		
		return orders;
	}
	
	public OrderResponse getCustomerOrder(String username, Long orderID) {
		Optional<Customer> customer = customerService.findByCustomerUsername(username);
		Optional<Orders> order = orderRepo.findById(orderID);
		if(customer.isPresent() 
		   && order.isPresent() 
		   && order.get().getCustomer().equals(customer.get())) {
			return this.getOrderResponseAdmin(order.get());
		}
		return null;
	}
	
	public List<OrderResponse> getAllOrdersAdmin(){
		List<OrderResponse> orders = new ArrayList<>();
		orderRepo.findAll().forEach((order) -> orders.add(this.getOrderResponseAdmin(order)));
		return orders;
	}
	
	public OrderResponse getOrderAdmin(Long orderID) {
		Optional<Orders> order = orderRepo.findById(orderID);
		return order.isPresent() ? this.getOrderResponseAdmin(order.get()) : null;
	}


//UPDATE
//DELETE
	public boolean cancelOrder(long orderID) {
		Optional<Orders> order = orderRepo.findById(orderID);
		if( (order.isPresent()) &&  (order.get().getOrderStatus() != OrderStatus.DELIVERED)) {
				Orders cancelledOrder = order.get();
				cancelledOrder.setOrderStatus(OrderStatus.CANCELLED);
				orderRepo.save(cancelledOrder);
				return true;
		}
		return false;
	}
//HELPER	
	
	private OrderResponse getOrderResponseAdmin(Orders order) {
		
		return new OrderResponse(
				order.getOrderID(), 
				order.getItem().getName(), 
				order.getItem().getColor().getName(), 
				order.getItem().getSize().getSizeCharacter(), 
				order.getItem().getProductHeader().getBrand().getName(), 
				order.getItem().getProductHeader().getPrimaryImage().getImageID(), 
				order.getItem().getProductHeader().getProductType().getDescription(), 
				order.getOrderStatus().toString(), 
				order.getAmount(), 
				order.getQuantity(), 
				order.getCreationDateTime(), 
				customerService.getCustomerResponse(order.getCustomer()), 
				addressService.getAddressResponse( order.getDeliveryAddress() )
				);
	}
	
//UTIL
	public void sendOrderPlacedMail(String username, List<Orders> orders) {
		Optional<Customer> customer = customerService.findByCustomerUsername(username);
		if(customer.isPresent()) {
			this.emailService.sendOrderPlacedMail(
					  customer.get().getEmail()
					, customer.get().getName()
					, orders);
		}
	}
}
