package varadraj.order.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.common.model.JsonResponseMessage;
import varadraj.exception.InvalidInputException;
import varadraj.order.model.OrderCreationRequest;
import varadraj.order.model.OrderResponseCustomer;
import varadraj.order.service.OrderService;

@RestController
@CrossOrigin
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping("/auth/customer/order")
public class OrderCustomerController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/getAll")
	public JsonResponse<List<OrderResponseCustomer>> getCustomerOrders(Principal user) {
		return new JsonResponse<List<OrderResponseCustomer>>(200
				,JsonResponseMessage.OK,
				orderService.getAllCustomerOrders(user.getName()));
	}
	
	@PostMapping("/addOrder")
	public JsonResponse<Void> addOrder(@RequestBody OrderCreationRequest request,Principal user) {
		try {
			if( orderService.addOrder(Optional.ofNullable(request), user.getName()).isPresent())
				return new JsonResponse<Void>(201, JsonResponseMessage.CREATED,null);
			else
				return new JsonResponse<Void>(500, JsonResponseMessage.ERROR ,null);
		}catch(InvalidInputException e) {
			return new JsonResponse<Void>(400, JsonResponseMessage.INVALID_INPUT,null);
		}			
	}
	
	@DeleteMapping("/cancel/{orderID}")
	public JsonResponse<Void> cancelOrder(@PathVariable long orderID){
		if(orderService.cancelOrder(orderID)) {
			return new JsonResponse<Void>(200,JsonResponseMessage.OK,null);
		}else {
			return new JsonResponse<Void>(400, JsonResponseMessage.INVALID_INPUT,null);
		}		
	}

}
