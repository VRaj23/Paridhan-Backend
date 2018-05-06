package varadraj.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.common.model.address.Address;
import varadraj.common.service.AddressService;
import varadraj.exception.InvalidInputException;
import varadraj.order.model.OrderCreationRequest;
import varadraj.order.model.OrderResponseAdmin;
import varadraj.order.model.OrderResponseCustomer;
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

		
//CREATE
	
	public Optional<Orders> addOrder(Optional<OrderCreationRequest> request, String username) throws InvalidInputException{
		Optional<Customer> customer = customerService.findByCustomerUsername(username);
		if(!customer.isPresent())
			return Optional.empty();
		
		Optional<Address> address = addressService.findByAddressID
				(request.map(OrderCreationRequest::getDeliveryAddressID).orElseThrow(InvalidInputException::new ));
		if(!address.isPresent())
			return Optional.empty();
		
		int quantity = request.map(OrderCreationRequest::getQuantity).orElseThrow(InvalidInputException::new);
		if(quantity <= 0)
			throw new InvalidInputException();
		
		
		return Optional.ofNullable(orderRepo.save(new Orders
				(customer.get()
				, productService.findByLineID(request.map(OrderCreationRequest::getProductLineID).orElseThrow(InvalidInputException::new))
				, quantity
				, productService.getPrice(request.get().getProductLineID()) * quantity //TODO warn mismatch
				, address.get())));
	}
	
	
//READ
	
	public List<OrderResponseCustomer> getAllCustomerOrders(String username) {
		Optional<Customer> customer = customerService.findByCustomerUsername(username);
		List<OrderResponseCustomer> orders = new ArrayList<>();
		orderRepo.findByCustomer(customer.get())
			.forEach( (order) -> orders.add(this.getOrderResponseCustomer(order)));
		return orders;
	}
	
	public List<OrderResponseAdmin> getAllOrdersAdmin(){
		List<OrderResponseAdmin> orders = new ArrayList<>();
		orderRepo.findAll().forEach((order) -> orders.add(this.getOrderResponseAdmin(order)));
		return orders;
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
	private OrderResponseCustomer getOrderResponseCustomer(Orders order) {
		return new OrderResponseCustomer(
				order.getOrderID(), 
				order.getItem().getName(), 
				order.getItem().getColor().getName(), 
				order.getItem().getSize().getSizeCharacter(), 
				order.getItem().getProductHeader().getBrand().getName(), 
				order.getItem().getProductHeader().getPrimaryImage().getImageID(),//TODO show correct image for the line 
				order.getItem().getProductHeader().getProductType().getDescription(), 
				order.getOrderStatus().toString(), 
				order.getAmount(), 
				order.getQuantity(), 
				order.getCreationDateTime());
	}
	
	
	private OrderResponseAdmin getOrderResponseAdmin(Orders order) {
		
		return new OrderResponseAdmin(
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
}
