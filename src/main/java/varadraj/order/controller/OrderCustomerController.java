package varadraj.order.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.common.model.JsonResponseMessage;
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
	
	@GetMapping("/getAll")
	public JsonResponse<List<Orders>> getCustomerOrders(Principal user) {
		
		return new JsonResponse<List<Orders>>(200
				,JsonResponseMessage.OK,
				orderService.getAllCustomerOrders(user.getName()));
	}
	
	@PostMapping("/addOrder")
	public JsonResponse<Void> addOrder(@RequestBody OrderCreationRequest request,Principal user) {
		
		Orders newOrder = orderService.addOrder(request, user.getName());
		
		if(newOrder == null) {
			return new JsonResponse<Void>(400, JsonResponseMessage.INVALID_INPUT,null);}
		else {
			return new JsonResponse<Void>(201, JsonResponseMessage.CREATED,null);}
	}

}
