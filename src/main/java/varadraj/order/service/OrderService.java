package varadraj.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.order.model.OrderCreationRequest;
import varadraj.order.model.OrderHeader;
import varadraj.order.model.OrderHeaderCreationRequest;
import varadraj.order.model.OrderLine;
import varadraj.order.model.OrderLineCreationRequest;
import varadraj.order.repository.OrderHeaderRepository;
import varadraj.order.repository.OrderLineRepository;
import varadraj.product.service.ProductService;
import varadraj.user.service.CustomerService;

@Service
public class OrderService {

	@Autowired
	private OrderHeaderRepository headerRepo;
	
	@Autowired
	private OrderLineRepository lineRepo;
	
	@Autowired
	private CustomerService  customerService;
	
	@Autowired
	private ProductService productService;
	
	public boolean isValidRequest(OrderCreationRequest request) {
		//TODO validations
		return true;
	}
		
//CREATE
	
	public void addOrder(OrderCreationRequest request) {
			OrderHeader header = headerRepo.save( createOrderHeader(request.getHeader()) ); 
			
			for(OrderLineCreationRequest lineRequest : request.getLines()) {
				lineRepo.save( createOrderLine(lineRequest, header) );
			}
	}
	
	private OrderHeader createOrderHeader(OrderHeaderCreationRequest headerRequest) {
		return new OrderHeader
				(customerService.findByCustomerID(headerRequest.getCustomerID())
				,0
				,headerRequest.getAmount()//TODO calculate; don't use value provided in request
				,headerRequest.getDeliveryAddress()
				);
	}
	
	private OrderLine createOrderLine(OrderLineCreationRequest lineRequest, OrderHeader header) {
		return new OrderLine
				(header, 
				productService.findByLineID(lineRequest.getProductLineID()),
				lineRequest.getQuantity());
	}
	
	
//READ
	
	//TODO get all orders
	
	//TODO get customer orders
	
//UPDATE
//DELTE
}
