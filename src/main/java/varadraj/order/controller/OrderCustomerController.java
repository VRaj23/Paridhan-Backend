package varadraj.order.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.common.model.ResponseMessage;
import varadraj.common.model.address.Address;
import varadraj.common.model.address.AddressCreationRequest;
import varadraj.common.service.AddressService;
import varadraj.exception.InvalidInputException;
import varadraj.order.model.OrderCreationRequest;
import varadraj.order.model.Orders;
import varadraj.order.service.OrderService;

@RestController
@CrossOrigin
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping("/auth/customer/order")
public class OrderCustomerController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private AddressService addressService;
	
	
	@PostMapping("/add")
	public JsonResponse<Void> addOrders(@RequestBody List<OrderCreationRequest> requests, Principal user){
		try {
			List<Orders> savedOrder = orderService.addOrders(requests, user.getName());
			
			new Thread(() -> this.orderService.sendOrderPlacedMail(user.getName(), savedOrder)).start();
			
			return new JsonResponse<Void>(201, ResponseMessage.CREATED,null);
			
		} catch(InvalidInputException e) {
			return new JsonResponse<Void>(400, ResponseMessage.INVALID_INPUT,null); 
		} catch (Exception e) {
			return new JsonResponse<Void>(500, ResponseMessage.ERROR ,null);
		}
	}
	
	@PostMapping("/deliveryAddress")
	public JsonResponse<Long> addDeliveryAddress(@RequestBody AddressCreationRequest request) {
		try {
			Optional<Address> address = addressService.addAddress( Optional.ofNullable(request));
			if( address.isPresent() )
				return new JsonResponse<Long>(201
						,ResponseMessage.CREATED
						,address.map(Address::getAddressID).get() );
			else
				return new JsonResponse<Long>(500, ResponseMessage.ERROR,null);
		}catch(InvalidInputException e) {
			return new JsonResponse<Long>(400, ResponseMessage.INVALID_INPUT, null);
		}
	}
	
	@PatchMapping("/cancel/{orderID}")
	public JsonResponse<Void> cancelOrder(@PathVariable long orderID){
		if(orderService.cancelOrder(orderID)) {
			return new JsonResponse<Void>(200,ResponseMessage.OK,null);
		}else {
			return new JsonResponse<Void>(400, ResponseMessage.INVALID_INPUT,null);
		}		
	}

}
